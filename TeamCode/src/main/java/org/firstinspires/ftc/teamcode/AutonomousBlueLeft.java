package org.firstinspires.ftc.teamcode;
import static java.lang.Math.round;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Auto Blue Left")
public class AutonomousBlueLeft extends LinearOpMode {
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    DcMotor linearSlideLeft = null;
    DcMotor linearSlideRight = null;
    public void runOpMode() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        linearSlideLeft = hardwareMap.get(DcMotor.class,"linearSlideLeft");
//        linearSlideRight = hardwareMap.get(DcMotor.class,"linearSlideRight");


          setRunUsingEncoders();

        //linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();


        resetEncoders();
        targetForward(5);
        setRunToPosition();
        driveForward(0.7);
        whileActive();


        resetEncoders();
        targetLeft(48);
        setRunToPosition();
        driveLeft(0.7);
        whileActive();



        }


    public void whileActive() {
        while (opModeIsActive() && frontLeft.isBusy() && backRight.isBusy()) {
            idle();
            telemetry.addData("frontLeft	:", frontLeft.getCurrentPosition());
            telemetry.addData("frontLeft	Target:", frontLeft.getTargetPosition());
            telemetry.addData("frontRight:", frontRight.getCurrentPosition());
            telemetry.addData("backLeft	:", backLeft.getCurrentPosition());
            telemetry.addData("backRight	:", backRight.getCurrentPosition());
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
        frontRight.setTargetPosition(mathTicks(0));
        backLeft.setTargetPosition(mathTicks(0));
        backRight.setTargetPosition(mathTicks(-inches));
    }

    public void targetCC(double inches) {
        frontLeft.setTargetPosition(mathTicks(-inches));
        frontRight.setTargetPosition(mathTicks(0));
        backLeft.setTargetPosition(mathTicks(0));
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
}
