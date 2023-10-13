package org.firstinspires.ftc.teamcode;
import static java.lang.Math.round;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


public class AutonomousOp extends LinearOpMode {
    public void runOpMode() {
        DcMotor frontLeft = null;
        DcMotor frontRight = null;
        DcMotor backLeft = null;
        DcMotor backRight = null;
        DcMotor linearSlideLeft = null;
        DcMotor linearSlideRight = null;

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        linearSlideLeft = hardwareMap.get(DcMotor.class,"linearslideLeft");
//        linearSlideRight = hardwareMap.get(DcMotor.class,"linearslideRight");


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(mathTicks(5));


        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        telemetry.addData("frontLeft	:", frontLeft.getCurrentPosition());
        telemetry.addData("frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("backLeft	:", backLeft.getCurrentPosition());
        telemetry.addData("backRight	:", backRight.getCurrentPosition());


    }

        public void DriveForward(double power) {

        }

        public int mathTicks(double x) {
        double raw = Math.round(x*537.7/(3.75*3.14));
        return (int)raw;
        }
}
