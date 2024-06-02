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
    Servo armLeft;    //2
    Servo armRight; //3

    Servo clawRotator; //1 expansion
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

        clawRotator = hardwareMap.get(Servo.class,"servoRotator");

        clawLeft.setDirection(Servo.Direction.REVERSE);
        armLeft.setDirection(Servo.Direction.REVERSE);
        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        clawLeft.scaleRange(0,1);
        clawRight.scaleRange(0,1);
        armLeft.scaleRange(0,1);
        armRight.scaleRange(0,1);
        clawRotator.scaleRange(0,1);


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Camera"), visionProcessor);
        //endregion

        closeClaw();
//        stopRobot(0.25);
//        positionWrist("high");
//        stopRobot(0.1);
//        positionArm("high");

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(12,63,Math.toRadians(90)); //change
        drive.setPoseEstimate(startPose);

        Trajectory backUp = drive.trajectoryBuilder(startPose)
                .back(5)
                .build();

        //region left
        Trajectory left1 = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(24,36)) //purple pixel
                .build();

        Trajectory left2 = drive.trajectoryBuilder(left1.end())
                .forward(5)
                ////49,-42
                .splineTo(new Vector2d(58,41),Math.toRadians(0)) //yellow pixel
                .addTemporalMarker(1,() -> {
                    positionArm("mid");
                })
                        .build();

        //new
//        Trajectory left1 = drive.trajectoryBuilder(backUp.end())
//                .lineToLinearHeading(new Pose2d(23,40,Math.toRadians(-90))) //purple pixel
//                .build();
//
//        Trajectory left2 = drive.trajectoryBuilder(left1.end())
//                .back(10)
//                .build();
//
//        Trajectory left3 = drive.trajectoryBuilder(left2.end())
//
//                //originally 49,42
//                .lineToLinearHeading(new Pose2d(58.5,59.5,0)) //yellow pixel
//                .build();
        //endregion

        //region middle
        Trajectory mid1 = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(12,33)) //purple pixel
                .build();

        Trajectory mid2 = drive.trajectoryBuilder(mid1.end())
                .forward(3)
                ////49,-35
                .splineTo(new Vector2d(27,50),0)
                .addTemporalMarker(2.5,() -> {
                    positionArm("mid");
                })
                .splineTo(new Vector2d(56,38),0) //yellow pixel
                .build();

        //new
//        Trajectory mid2 = drive.trajectoryBuilder(backUp.end())
//                .lineToLinearHeading(new Pose2d(12,34,Math.toRadians(-90))) //purple pixel
//                .build();
//
//        Trajectory mid3 = drive.trajectoryBuilder(mid2.end(),true)
//                .back(3)
//                .splineTo(new Vector2d(58.5,59.5),0)
//                .build();

//        Trajectory mid3 = drive.trajectoryBuilder(mid2.end())
//                .back(20)
//                .build();
//
//        Trajectory mid4 = drive.trajectoryBuilder(mid3.end())
//                .splineToLinearHeading(new Pose2d(49,35,0),Math.toRadians(0)) //yellow pixel
//                .build();


        //endregion

        //region right
        Trajectory right1 = drive.trajectoryBuilder(startPose)
                .lineToLinearHeading(new Pose2d(12,30,Math.toRadians(0)))
                .build();

        Trajectory right2 = drive.trajectoryBuilder(right1.end())
                .back(4.2)  //purple pixel
                .build();


        //49,28
        Trajectory right3 = drive.trajectoryBuilder(right2.end())
                .splineTo(new Vector2d(56,27.5),0) //yellow pixel
                .addTemporalMarker(0.3,() -> {
                    positionArm("mid");
                })
                .build();

        //new
//        Trajectory right1 = drive.trajectoryBuilder(backUp.end())
//                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(180))
////                                        ,SampleMecanumDrive.getVelocityConstraint(12, Math.toRadians(180), 14.2),
////                                        SampleMecanumDrive.getAccelerationConstraint(12)
//                )
//                .build();
//
//        Trajectory right2 = drive.trajectoryBuilder(right1.end())
//                .forward(2) //purple pixel
//                .build();
//
//        Trajectory right3 = drive.trajectoryBuilder(right2.end(),true)
//                .back(5)
//                .splineTo(new Vector2d(58.5,59.5),0)
//                .build();

        //future
//        Trajectory right3 = drive.trajectoryBuilder(right2.end())
//                .back(5)
//                .build();
//
//        Trajectory right4 = drive.trajectoryBuilder(right3.end())
//                .lineToLinearHeading(new Pose2d(49,28,0)) //yellow pixel
//                .build();
        //endregion

        Trajectory backUpLeft = drive.trajectoryBuilder(left2.end())
                .back(6)
                .build();

        Trajectory backUpLeft2 = drive.trajectoryBuilder(backUpLeft.end())
                .lineToLinearHeading(new Pose2d(48,12,Math.toRadians(-90))
                        ,SampleMecanumDrive.getVelocityConstraint(12, Math.toRadians(180), 14.2),
                        SampleMecanumDrive.getAccelerationConstraint(12)
                )
                .addTemporalMarker(0.1, () -> {
                    positionArm("high");
                })
                .build();

        Trajectory backUpMiddle = drive.trajectoryBuilder(mid2.end())
                .back(6)
                .build();

        Trajectory backUpMiddle2 = drive.trajectoryBuilder(backUpMiddle.end())
                .lineToLinearHeading(new Pose2d(48,12,Math.toRadians(-90))
                        ,SampleMecanumDrive.getVelocityConstraint(12, Math.toRadians(180), 14.2),
                        SampleMecanumDrive.getAccelerationConstraint(12)
                )
                .addTemporalMarker(0.1, () -> {
                    positionArm("high");
                })
                .build();

        Trajectory backUpRight = drive.trajectoryBuilder(right3.end())
                .back(6)
                .build();

        Trajectory backUpRight2 = drive.trajectoryBuilder(backUpRight.end())
                .lineToLinearHeading(new Pose2d(48,12,Math.toRadians(-90))
                        ,SampleMecanumDrive.getVelocityConstraint(12, Math.toRadians(180), 14.2),
                        SampleMecanumDrive.getAccelerationConstraint(12)
                )
                .addTemporalMarker(0.1, () -> {
                    positionArm("high");
                })
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

                stopRobot(0.1);
                positionWrist("mid");
                stopRobot(1.5);
                openClaw();
                stopRobot(0.5);
                drive.followTrajectory(backUpLeft);
                drive.followTrajectory(backUpLeft2);
//                drive.followTrajectory(backUp);
//                drive.followTrajectory(left1);
//                stopRobot(0.1);
//                drive.followTrajectory(left2);
//                drive.followTrajectory(left3);
                break;
            case MIDDLE:

                drive.followTrajectory(mid1);
                stopRobot(0.1);
                drive.followTrajectory(mid2);

                stopRobot(0.1);
                positionWrist("mid");
                stopRobot(1.5);
                openClaw();
                stopRobot(0.5);
                drive.followTrajectory(backUpMiddle);
                drive.followTrajectory(backUpMiddle2);
//                drive.followTrajectory(backUp);
//                drive.followTrajectory(mid2);
//                stopRobot(0.1);
//                drive.followTrajectory(mid3);
//               // drive.followTrajectory(mid4);
                break;
            case RIGHT:
                drive.followTrajectory(right1);
                drive.followTrajectory(right2);
                stopRobot(0.1);
                drive.followTrajectory(right3);

                stopRobot(0.1);
                positionWrist("mid");
                stopRobot(1.5);
                openClaw();
                stopRobot(0.5);
                drive.followTrajectory(backUpRight);
                drive.followTrajectory(backUpRight2);
//                drive.followTrajectory(backUp);
//                drive.followTrajectory(right1);
//                drive.followTrajectory(right2);
//                stopRobot(0.1);
//                drive.followTrajectory(right3);
//             //   drive.followTrajectory(right4);
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
        clawLeft.setPosition(0.5);
        clawRight.setPosition(0.5);
    }
    public void closeClaw() {
        clawLeft.setPosition(0.8);
        clawRight.setPosition(0.8);
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
    public void positionArm(String position) {
        switch (position) {
            case "low":
                armLeft.setPosition(0.27);
                armRight.setPosition(0.27);
                break;
            case "mid":
                armLeft.setPosition(0.46);
                armRight.setPosition(0.46);
                break;
            case "high":
                armLeft.setPosition(1);
                armRight.setPosition(1);
                break;
        }
    }

    public void positionWrist(String position) {
        switch (position) {
            case "low":
                clawRotator.setPosition(0.89);
                break;
            case "mid":
                clawRotator.setPosition(0.835);
                break;
            case "high":
                clawRotator.setPosition(0);
                break;
        }
    }
}
