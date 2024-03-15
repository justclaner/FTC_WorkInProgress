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

@Autonomous(name = "Camera Red Linear (Truss on Right)", group = "a")
public class RightRed extends LinearOpMode {

    //region Initializations
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    Servo clawLeft = null;
    Servo clawRight = null;
    DcMotor linearSlideLeft = null;  // 0
    DcMotor linearSlideRight = null; // 1
    Servo armLeft;    //2
    Servo armRight; //3
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

        armLeft = hardwareMap.get(Servo.class, "armLeft");
        armRight = hardwareMap.get(Servo.class, "armRight");

        clawLeft.setDirection(Servo.Direction.REVERSE);
        armLeft.setDirection(Servo.Direction.REVERSE);
        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        clawLeft.scaleRange(0,1);
        clawRight.scaleRange(0,1);
        armLeft.scaleRange(0,1);
        armRight.scaleRange(0,1);


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Camera"), visionProcessor);
        //endregion

        openClaw();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-36,-63,Math.toRadians(-90));//change
        drive.setPoseEstimate(startPose);

        Trajectory backUp = drive.trajectoryBuilder(startPose)
                .back(5)
                .build();

        //region left
//        Trajectory left1 = drive.trajectoryBuilder(startPose)
//                .lineToLinearHeading(new Pose2d(-37,-34,Math.toRadians(0))) //purple
//                .build();

//        Trajectory left2 = drive.trajectoryBuilder(left1.end())
//                .forward(3)
//                .build();

//        Trajectory left3 = drive.trajectoryBuilder(left2.end())
//                .lineTo(new Vector2d(-34,-12))
//                .build();

//        Trajectory left4 = drive.trajectoryBuilder(left3.end())
//                .lineTo(new Vector2d(12,-12))
//                .splineTo(new Vector2d(49,-30),0) //yellow pixel
//                .build();

        Trajectory left1 = drive.trajectoryBuilder(backUp.end())
                .lineToLinearHeading(new Pose2d(-37,-34,Math.toRadians(180))) //purple
                .build();

        Trajectory left2 = drive.trajectoryBuilder(left1.end())
                .back(3)
                .build();

        Trajectory left3 = drive.trajectoryBuilder(left2.end())
                .lineTo(new Vector2d(-34,-20))
                .splineToConstantHeading(new Vector2d(-30,-12),0)
                .lineTo(new Vector2d(12,-12))
                .build();

        Trajectory left4 = drive.trajectoryBuilder(left3.end())
                .lineToLinearHeading(new Pose2d(49,-30,0)) //yellow pixel
                .build();
        //endregion

        //region middle
//        Trajectory mid1 = drive.trajectoryBuilder(startPose)
//                .lineTo(new Vector2d(-36,-33)) //purple
//                .build();
//
//        Trajectory mid2 = drive.trajectoryBuilder(mid1.end())
//                .forward(2)
//                .build();
//
//        Trajectory mid3 = drive.trajectoryBuilder(mid2.end())
//                .strafeLeft(48)
//                .build();
//
//        Trajectory mid4 = drive.trajectoryBuilder(mid3.end())
//                .lineToLinearHeading(new Pose2d(49,-35,0)) //yellow pixel
//                .build();

        //new
        Trajectory mid1 = drive.trajectoryBuilder(backUp.end())
                .lineToLinearHeading(new Pose2d(-36,-33,Math.toRadians(90))) //purple
                .build();

        Trajectory mid2 = drive.trajectoryBuilder(mid1.end())
                .back(3)
                .build();

        Trajectory mid3 = drive.trajectoryBuilder(mid2.end())
                .strafeRight(48)
                .build();

        Trajectory mid4 = drive.trajectoryBuilder(mid3.end())
                .lineToLinearHeading(new Pose2d(49,-35,0)) //yellow pixel
                .build();
        //endregion

        //region right
//        Trajectory right1 = drive.trajectoryBuilder(startPose)
//                .lineToLinearHeading(new Pose2d(-36,-32,Math.toRadians(180)))
//                .build();
//
//        Trajectory right2 = drive.trajectoryBuilder(right1.end())
//                .back(3) //purple
//                .build();
//
//        Trajectory right3 = drive.trajectoryBuilder(right2.end())
//                .forward(4)
//                .splineTo(new Vector2d(-42,-20),Math.toRadians(90))
//                .splineTo(new Vector2d(-28,-12),0)
//
//                .lineTo(new Vector2d(12,-12))
//                .splineTo(new Vector2d(49,-42),0) //yellow pixel
//                .build();
        Trajectory right1 = drive.trajectoryBuilder(backUp.end())
                .lineToLinearHeading(new Pose2d(-36,-32,0))
                .build();

        Trajectory right2 = drive.trajectoryBuilder(right1.end())
                .forward(3) //purple
                .build();

        Trajectory right3 = drive.trajectoryBuilder(right2.end())
                .back(5)
                .splineToConstantHeading(new Vector2d(-42,-12),Math.toRadians(90))
                .build();

        Trajectory right4 = drive.trajectoryBuilder(right3.end())
                .lineTo(new Vector2d(12,-12))
                .splineTo(new Vector2d(49,-42),0) //yellow pixel
                .build();
        //endregion


        while (!isStarted()) {
            telemetry.addData("Identified", visionProcessor.getSelection());
            telemetry.update();
        }

        waitForStart();

        visionPortal.stopStreaming();

        switch (visionProcessor.getSelection()) {
            case LEFT:

                drive.followTrajectory(backUp); //comment this out for old route
                drive.followTrajectory(left1);
                stopRobot(0.1);
                drive.followTrajectory(left2);
                drive.followTrajectory(left3);
                drive.followTrajectory(left4);

                break;
            case MIDDLE:
            drive.followTrajectory(backUp); //comment this out for old route
            drive.followTrajectory(mid1);
            stopRobot(0.1);
            drive.followTrajectory(mid2);
            drive.followTrajectory(mid3);
            drive.followTrajectory(mid4);

                break;
            case RIGHT:

                drive.followTrajectory(backUp); //comment this out for old route
                drive.followTrajectory(right1);
                drive.followTrajectory(right2);
                stopRobot(0.1);
                drive.followTrajectory(right3);
                drive.followTrajectory(right4);
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
