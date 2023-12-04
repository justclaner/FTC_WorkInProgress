package org.firstinspires.ftc.teamcode;
import android.graphics.RenderNode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.ServoController;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.servo;
@TeleOp(name = "TeleOp Actual")
public class Telemetry extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor linearSlideLeft;
    DcMotor linearSlideRight;
//    private RenderNode claw;

    //servo claw;


    @Override
    public void init() {

//        hardware motors = new hardware(hardwareMap);

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        linearSlideLeft = hardwareMap.get(DcMotor.class, "linearSlideLeft");
        linearSlideRight = hardwareMap.get(DcMotor.class, "linearSlideRight");


        //linearSlideLeft = hardwareMap.get(DcMotor.class,"linearSlideLeft");
        //linearSlideRight = hardwareMap.get(DcMotor.class,"linearSlideRight");
        //claw = hardwareMap.get(Servo.class, "claw");
        //claw.scaleRange(0,1);


        //claw.setDirection(Servo.Direction.REVERSE);

        //originally only backLeft reverse
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideLeft.setDirection(DcMotorSimple.Direction.REVERSE); // or right


    }

    @Override
    public void loop() {
        double xAxisMovement = gamepad1.left_stick_x;
        double yAxisMovement = gamepad1.left_stick_y;
        double rAxisMovement = 0.6*gamepad1.right_stick_x;
        double lAxisMovement = 0.4* gamepad2.left_stick_y;

        double linearSlidePower = lAxisMovement;

        double frontLeftPower = yAxisMovement - xAxisMovement - rAxisMovement;
        double frontRightPower = yAxisMovement + xAxisMovement + rAxisMovement;
        double backLeftPower = yAxisMovement + xAxisMovement - rAxisMovement;
        double backRightPower = yAxisMovement - xAxisMovement + rAxisMovement;

        frontLeft.setPower(-0.7* frontLeftPower);
        frontRight.setPower(-0.7 * frontRightPower);
        backLeft.setPower(-0.7 * backLeftPower);
        backRight.setPower(-0.7 * backRightPower);
//        if (linearSlideLeft.getCurrentPosition() < 10000) {
//            linearSlideLeft.setPower(0.7 * linearSlidePower);
//            linearSlideRight.setPower(0.7 * linearSlidePower);
//        } else if (linearSlideLeft.getCurrentPosition() >= 10000) {
//            linearSlideLeft.setPower(0);
//            linearSlideRight.setPower(0);
//        }
        telemetry.addData("frontLeft	:", frontLeft.getPower());
        telemetry.addData("frontRight:", frontRight.getPower());
        telemetry.addData("backLeft	:", backLeft.getPower());
        telemetry.addData("backRight	:", backRight.getPower());
        telemetry.addData("Left Slide Pos: ", linearSlideLeft.getCurrentPosition());
        telemetry.addData("Right Slide Pos: ", linearSlideRight.getCurrentPosition());


//        if (gamepad2.right_bumper) { //open claw
//            RenderNode.setPosition(Constants.openVal);
//        } else if (gamepad2.left_bumper) { //close claw
//            RenderNode.setPosition(Constants.closeVal);
//        }
    }

    double ticksPerRotation = 537.7;
    double wheelDiameter = 3.779;
    public int mathTicks(double inches) {
        double raw = Math.round(inches*ticksPerRotation/(wheelDiameter*Math.PI));
        return (int)raw;
    }

    public double mathInches(int ticks){
        double raw= Math.round(ticks*(ticksPerRotation*Math.PI)/wheelDiameter);
        return (double)raw;
    }

}