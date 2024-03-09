package org.firstinspires.ftc.teamcode.Autonomous.LinearOpMode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.processors.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "Camera Blue Linear (Truss on Right)", group = "a")
public class RightBlue extends LinearOpMode {

    //region Initializations
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    Servo clawLeft = null;
    Servo clawRight = null;
    DcMotor linearSlideLeft = null;  // 0
    DcMotor linearSlideRight = null; // 1
    private FirstVisionProcessor visionProcessor;

    private VisionPortal visionPortal;
    //endregion

    @Override
    public void runOpMode() {
        //region hardwareMap and directions
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        linearSlideLeft = hardwareMap.get(DcMotor.class, "linearSlideLeft");
        linearSlideRight = hardwareMap.get(DcMotor.class, "linearSlideRight");

        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        clawRight = hardwareMap.get(Servo.class, "clawRight");
        clawLeft.setDirection(Servo.Direction.REVERSE);
        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);

        clawLeft.scaleRange(0,1);
        clawRight.scaleRange(0,1);


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Camera"), visionProcessor);
        //endregion

        openClaw();


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(12,63,Math.toRadians(90)); //change
        drive.setPoseEstimate(startPose);

        //trajectories here
        Trajectory left1 = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(23,40)) //purple pixel
                .build();

        Trajectory left2 = drive.trajectoryBuilder(new Pose2d(23,40,Math.toRadians(90)))
                .forward(5) //check for continuity error
                .splineTo(new Vector2d(49,42),0) //yellow pixel
                        .build();

        Trajectory mid1 = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(12,33)) //purple pixel
                .build();

        Trajectory mid2 = drive.trajectoryBuilder(startPose)
                .splineTo(new Vector2d(49,35),0) //yellow pixel
                .build();

        Trajectory right1 = drive.trajectoryBuilder(startPose)
                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(0)))
                .build();

        Trajectory right2 = drive.trajectoryBuilder(startPose)                                .back(2)  //purple pixel
                .back(2)  //purple pixel
                .build();

        Trajectory right3 = drive.trajectoryBuilder(startPose)                                .back(2)  //purple pixel
                .splineTo(new Vector2d(49,28),0) //yellow pixel
                .build();


        while (!isStarted()) {
            telemetry.addData("Identified", visionProcessor.getSelection());
            telemetry.update();
        }

        waitForStart();

        visionPortal.stopStreaming();

        switch (visionProcessor.getSelection()) {
            case LEFT:
                drive.followTrajectory(left1);
                stopRobot(0.1);
                drive.followTrajectory(left2);

                break;
            case MIDDLE:
                drive.followTrajectory(mid1);
                stopRobot(0.1);
                drive.followTrajectory(mid2);
 //middle trajectories
                break;
            case RIGHT:
                drive.followTrajectory(right1);
                drive.followTrajectory(right2);
                stopRobot(0.1);
                drive.followTrajectory(right3);
//                move("Backward",40,autoPower);
                break;
            case NONE:
                break;
        }
    }

    //region Encoder Movement
    public void whileActive() {
        while (linearSlideLeft.isBusy()) {
            telemetry.addData("linearSlideLeft",mathInches(linearSlideLeft.getCurrentPosition()));
            telemetry.addData("linearSlideRight",mathInches(linearSlideRight.getCurrentPosition()));
            telemetry.update();
        }
    }

    public void setRunToPosition() {
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }



    public void resetEncoders() {
        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void openClaw() {
        clawLeft.setPosition(0.34);
        clawRight.setPosition(0.1);
    }
    public void closeClaw() {
        clawLeft.setPosition(0.55);
        clawRight.setPosition(0.32);
    }

    public void linearSlideMove(double inches, double power) {
        resetEncoders();
        linearSlideLeft.setTargetPosition(mathTicks(inches));
        linearSlideRight.setTargetPosition(mathTicks(inches));

        setRunToPosition();

        linearSlideLeft.setPower(power);
        linearSlideLeft.setPower(power);
        whileActive();
    }

    public int mathTicks(double inches) {
        double raw = Math.round(inches*537.7/(3.779*3.14));
        return (int)raw;
    }

    public double mathInches(int ticks){
        double raw = Math.round(ticks*(3.779*Math.PI)/537.7);
        return (double)raw;
    }

    public void stopRobot(double seconds) {
        try {
            Thread.sleep((long)(seconds*1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion

}
