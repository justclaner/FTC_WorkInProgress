package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


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
        linearSlideLeft = hardwareMap.get(DcMotor.class,"linearslideLeft");
        linearSlideRight = hardwareMap.get(DcMotor.class,"linearslideRight");


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();


    }
}
