package org.firstinspires.ftc.teamcode.Autonomous.OpMode;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.processors.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

import com.acmerobotics.roadrunner.geometry.Pose2d;


@Autonomous(name = "Camera Blue (Truss on Left)", group = "b")
public class LeftBlueOld extends OpMode {
//commit 3
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    DcMotor linearSlideLeft = null;  // 0
    DcMotor linearSlideRight = null; // 1
    Servo clawLeft = null;
    Servo clawRight = null;
    private FirstVisionProcessor visionProcessor;

    private VisionPortal visionPortal;

    @Override
    public void init() {
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

//        closeClaw();
            openClaw();
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Camera"), visionProcessor);


        setRunUsingEncoders();


    }

    @Override
    public void init_loop() {
        telemetry.addData("Identified", visionProcessor.getSelection());
    }

    double autoPower = 0.25;

    @Override
    public void start() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(10,-58.5,Math.toRadians(90)); //change
        drive.setPoseEstimate(startPose);

//        double startTime = System.currentTimeMillis();
        visionPortal.stopStreaming();
        telemetry.addData("Identified", visionProcessor.getSelection());
        switch (visionProcessor.getSelection()) {
            case LEFT:
//                linearSlideMove(10,0.2);

                move("Backward",26.5,autoPower);
                move("CC",25,autoPower);
                move("Backward",4.5,autoPower);

                move("Forward",10,autoPower);
                move("Left",30,autoPower);
                move("Backward",90,autoPower);
                move("Right",20,autoPower);
                move("Backward",10,autoPower);

                //hi

//                move("Forward",26.5,autoPower);
//                move("CC",25,autoPower);
//                move("Forward",5.5,autoPower);
//                openClaw();
//
//                move("Backward",10,autoPower);
//                move("Right",30,autoPower);
//                move("Forward",90,autoPower);
//                move("Left",20,autoPower);
//                move("Forward",10,autoPower);



                break;
            case MIDDLE:
//                linearSlideMove(10,0.2);
                move("Backward",28,autoPower);

                move ("Forward", 10,autoPower);
                move("Left",20,0.4);
                move("Backward",40,autoPower);
                move("Right",110,autoPower);
                move("Forward",20,autoPower);
                move("Right",20,autoPower);




                break;
            case RIGHT:
//                linearSlideMove(10,0.2);
                move("Backward",12,autoPower);
                move("Left",13,autoPower); //test this again
                move("Backward",9,autoPower);

                move("Forward",5.5,autoPower);
                move("Left",14,autoPower);
                move("Backward",40,autoPower);
                move("Right",115,autoPower);
                move("Forward",20,autoPower);
                move("Right",10,autoPower);

//                move("Forward",12,autoPower);
//                move("Right",13.5,autoPower); //test this again
//                move("Forward",9,autoPower);
//                openClaw();
//
//                move("Backward",5.5,autoPower);
//                move("Right",14,autoPower);
//                move("Forward",40,autoPower);
//                move("Left",115,autoPower);
//                move("Backward",20,autoPower);
//                move("Left",10,autoPower);

                break;
            case NONE:
                break;
        }
    }

    @Override
    public void loop() {
    }

    public void move(String direction, double inches, double power) {
        resetEncoders();
        switch(direction) {
            case "Left":
                targetLeft(inches);
                break;
            case "Forward":
                targetForward(inches);
                break;
            case "Right":
                targetRight(inches);
                break;
            case "Backward":
                targetBackward(inches);
                break;
            case "C":
                targetC(inches);
                break;
            case "CC":
                targetCC(inches);
                break;
            case "NE":
                targetNE(inches);
                break;
            case "NW":
                targetNW(inches);
                break;
            case "SE":
                targetSE(inches);
                break;
            case "SW":
                targetSW(inches);
                break;
        }
        setRunToPosition();

        switch (direction) {
            case "Left":
                driveLeft(power);
                break;
            case "Forward":
                driveForward(power);
                break;
            case "Right":
                driveRight(power);
                break;
            case "Backward":
                driveBackward(power);
                break;
            case "C":
                rotateC(power);
                break;
            case "CC":
                rotateCC(power);
                break;
            case "NE":
                driveNE(power);
                break;
            case "NW":
                driveNW(power);
                break;
            case "SE":
                driveSE(power);
                break;
            case "SW":
                driveSW(power);
                break;
        }
        whileActive();
        }
    public void whileActive() {
        while ((frontLeft.isBusy() && backRight.isBusy()) || linearSlideLeft.isBusy()) {
            telemetry.addData("frontLeft	:", mathInches(frontLeft.getCurrentPosition()));
            telemetry.addData("frontRight:", mathInches(frontRight.getCurrentPosition()));
            telemetry.addData("backLeft	:", mathInches(backLeft.getCurrentPosition()));
            telemetry.addData("backRight	:", mathInches(backRight.getCurrentPosition()));
            telemetry.update();
        }
    }

    public void setRunToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setRunUsingEncoders(){
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void targetForward(double inches) {
        frontLeft.setTargetPosition(mathTicks(inches));
        frontRight.setTargetPosition(mathTicks(inches));
        backLeft.setTargetPosition(mathTicks(inches));
        backRight.setTargetPosition(mathTicks(inches));
    }

    public void targetBackward(double inches) {
        frontLeft.setTargetPosition(mathTicks(-inches));
        frontRight.setTargetPosition(mathTicks(-inches));
        backLeft.setTargetPosition(mathTicks(-inches));
        backRight.setTargetPosition(mathTicks(-inches));
    }

    public void targetLeft(double inches) {
        frontLeft.setTargetPosition(mathTicks(-inches));
        frontRight.setTargetPosition(mathTicks(inches));
        backLeft.setTargetPosition(mathTicks(inches));
        backRight.setTargetPosition(mathTicks(-inches));
    }

    public void targetRight(double inches) {
        frontLeft.setTargetPosition(mathTicks(inches));
        frontRight.setTargetPosition(mathTicks(-inches));
        backLeft.setTargetPosition(mathTicks(-inches));
        backRight.setTargetPosition(mathTicks(inches));
    }

    public void targetC(double inches) {
        frontLeft.setTargetPosition(mathTicks(inches));
        frontRight.setTargetPosition(mathTicks(-inches));
        backLeft.setTargetPosition(mathTicks(inches));
        backRight.setTargetPosition(mathTicks(-inches));
    }

    public void targetCC(double inches) {
        frontLeft.setTargetPosition(mathTicks(-inches));
        frontRight.setTargetPosition(mathTicks(inches));
        backLeft.setTargetPosition(mathTicks(-inches));
        backRight.setTargetPosition(mathTicks(inches));
    }
    public void targetNE (double inches) {
        frontLeft.setTargetPosition(mathTicks(inches));
        frontRight.setTargetPosition(mathTicks(0));
        backLeft.setTargetPosition(mathTicks(0));
        backRight.setTargetPosition(mathTicks(inches));
    }

    public void targetNW (double inches) {
        frontLeft.setTargetPosition(mathTicks(0));
        frontRight.setTargetPosition(mathTicks(inches));
        backLeft.setTargetPosition(mathTicks(inches));
        backRight.setTargetPosition(mathTicks(0));
    }
    public void targetSE (double inches) {
        frontLeft.setTargetPosition(mathTicks(0));
        frontRight.setTargetPosition(mathTicks(-inches));
        backLeft.setTargetPosition(mathTicks(-inches));
        backRight.setTargetPosition(mathTicks(0));
    }
    public void targetSW (double inches) {
        frontLeft.setTargetPosition(mathTicks(-inches));
        frontRight.setTargetPosition(mathTicks(0));
        backLeft.setTargetPosition(mathTicks(0));
        backRight.setTargetPosition(mathTicks(-inches));
    }
    public void driveForward(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    public void stopDriving(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void driveBackward(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
    }
    public void driveLeft(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }
    public void driveRight(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }
    public void rotateC(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(-power);
    }
    public void rotateCC(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(power);
    }
    public void driveNE(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(power);
    }

    public void driveNW(double power) {
        frontLeft.setPower(0);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(0);
    }

    public void driveSE(double power) {
        frontLeft.setPower(0);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(0);
    }

    public void driveSW(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(-power);
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

        linearSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linearSlideLeft.setPower(power);
        linearSlideLeft.setPower(power);
        whileActive();
    }
    public int mathTicks(double inches) {
        double raw = Math.round(inches*537.7/(3.779*3.14));
        return (int)raw;
    }

    public double mathInches(int ticks){
        double raw= Math.round(ticks*(3.779*Math.PI)/537.7);
        return (double)raw;
    }

    public void stopRobot(double seconds) {

        try {
            Thread.sleep((long)(seconds*1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
