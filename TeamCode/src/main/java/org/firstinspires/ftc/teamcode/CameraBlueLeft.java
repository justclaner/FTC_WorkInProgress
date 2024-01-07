package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.processors.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "Camera Blue (Truss on Left)")
public class CameraBlueLeft extends OpMode {

    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
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

        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        clawRight = hardwareMap.get(Servo.class, "clawRight");
        clawLeft.setDirection(Servo.Direction.REVERSE);

        clawLeft.scaleRange(0,1);
        clawRight.scaleRange(0,1);

        clawLeft.setPosition(0.34);
        clawRight.setPosition(0.1);

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

    double autoPower = 0.2;
    @Override
    public void start() {
        visionPortal.stopStreaming();
        telemetry.addData("Identified", visionProcessor.getSelection());
        switch (visionProcessor.getSelection()) {
            case LEFT:
                move("Forward",19,autoPower);
                move("CC", 20, autoPower);
                move("Forward", 7, autoPower);
                move("Backward", 7, autoPower);
                move("C", 20, autoPower);
                move("Forward",35,autoPower);
                move("Left",70,autoPower);
                move("CC", 55, autoPower);
                move("Right",40,autoPower);
                move("Forward",20,autoPower);
                move("Right",10,autoPower);
                break;
            case MIDDLE:
                move("Forward",28,autoPower);
                move("Backward", 10, autoPower);
                move("Right",20,autoPower);
                move("Forward",40,autoPower);
                move("Left",75,autoPower);
                move("CC", 55, autoPower);
                move("Right",50,autoPower);
                move("Forward",15,autoPower);
                move("Right",10,autoPower);
                break;
            case RIGHT:
                move("Forward",18,autoPower);
                move("C",15,autoPower);
                move("Forward",6,autoPower);
                move("Backward",6,autoPower);
                move("CC",15,autoPower);
                move("Forward",35,autoPower);
                move("Left",65,autoPower);
                move("CC", 55, autoPower);
                move("Right",40,autoPower);
                move("C",20,autoPower);
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
        }
        whileActive();
        }
    public void whileActive() {
        while (frontLeft.isBusy() && backRight.isBusy()) {
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
    }


    public void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

    public int mathTicks(double inches) {
        double raw = Math.round(inches*537.7/(3.779*3.14));
        return (int)raw;
    }

    public double mathInches(int ticks){
        double raw= Math.round(ticks*(3.779*Math.PI)/537.7);
        return (double)raw;
    }

}
