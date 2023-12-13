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
    //GoBILDA 5202/3/4 series
    DcMotor frontLeft;  // 2
    DcMotor frontRight; // 0
    DcMotor backLeft;  // 1
    DcMotor backRight; // 3

    DcMotor linearSlideLeft;  // 0
    DcMotor linearSlideRight; // 1

//    private RenderNode claw;

    //Servo claw;

    double servoMininumPosition = 0;
    double servoMaximumPosition = 0.25;
    @Override
    public void init() {

//        hardware motors = new hardware(hardwareMap);

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
//
        linearSlideLeft = hardwareMap.get(DcMotor.class, "linearSlideLeft");
        linearSlideRight = hardwareMap.get(DcMotor.class, "linearSlideRight");

//      claw = hardwareMap.get(Servo.class, "claw");

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        //claw = hardwareMap.get(Servo.class, "claw");
        //claw.scaleRange(0,1);


        //claw.setDirection(Servo.Direction.REVERSE);

        //originally only backLeft reverse
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE); // or right


    }
    int aPressed = 0;
    int bPressed = 0;
    @Override
    public void loop() {

        double xAxisMovement = gamepad1.left_stick_x;
        double yAxisMovement = gamepad1.left_stick_y;
        double rAxisMovement = 0.6*gamepad1.right_stick_x;

        double lAxisMovement = 0.6* gamepad2.left_stick_y;



        //movement
        double frontLeftPower = yAxisMovement - xAxisMovement - rAxisMovement;
        double frontRightPower = yAxisMovement + xAxisMovement + rAxisMovement;
        double backLeftPower = yAxisMovement + xAxisMovement - rAxisMovement;
        double backRightPower = yAxisMovement - xAxisMovement + rAxisMovement;

        frontLeft.setPower(-0.7* frontLeftPower);
        frontRight.setPower(-0.7 * frontRightPower);
        backLeft.setPower(-0.7 * backLeftPower);
        backRight.setPower(-0.7 * backRightPower);

        if (gamepad2.a) {
            //a is toggle for downward linear slide movement
            if (aPressed == 0) {
                aPressed = 1;
            } else {
                aPressed = 0;
            }
        }


        if (gamepad2.b) {
            //claw.setPosition(clawMaximumPosition);
        }

        if (gamepad2.y) {
            //claw.setPosition(clawMinimumPosition);
        }






//        if (aPressed == true) {
//            linearSlideLeft.setPower(0.3);
//            linearSlideRight.setPower(0.3);
//        } else {
//            linearSlideLeft.setPower(0.3*linearSlidePower);
//            linearSlideRight.setPower(0.3*linearSlidePower);
//        }
        linearSlideLeft.setPower(0.3*(lAxisMovement + aPressed));
        linearSlideRight.setPower(0.3*(lAxisMovement + aPressed));


//        if (bPressed == true) {
//            linearSlideLeft.setPower(-0.3);
//            linearSlideRight.setPower(-0.3);
//        } else {
//            linearSlideLeft.setPower(0.3*linearSlidePower);
//            linearSlideRight.setPower(0.3*linearSlidePower);
//        }

            //-1620 maximum linear slide extension
//        if (linearSlideLeft.getCurrentPosition() >= -1620 && linearSlideLeft.getCurrentPosition() <= 1) {
//            linearSlideLeft.setPower(0.3 * linearSlidePower);
//            linearSlideRight.setPower(0.3 * linearSlidePower);
//        } else  {
//            linearSlideLeft.setPower(0);
//            linearSlideRight.setPower(0);
//        }
        telemetry.addData("frontLeft	:", frontLeft.getPower());
        telemetry.addData("frontRight:", frontRight.getPower());
        telemetry.addData("backLeft	:", backLeft.getPower());
        telemetry.addData("backRight	:", backRight.getPower());
        telemetry.addData("Left Slide Power	:", linearSlideLeft.getPower());
        telemetry.addData("Right Slide Power	:", linearSlideRight.getPower());


        telemetry.addData("Left Slide Pos: ", linearSlideLeft.getCurrentPosition());
        telemetry.addData("Right Slide Pos: ", linearSlideRight.getCurrentPosition());

        telemetry.addData("A Pressed :", aPressed);


//        if (gamepad2.right_bumper) { //open claw
//            RenderNode.setPosition(Constants.openVal);
//        } else if (gamepad2.left_bumper) { //close claw
//            RenderNode.setPosition(Constants.closeVal);
//        }
    }

//    public void stop() {
//        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlideLeft.setTargetPosition(-1600);
//        linearSlideRight.setTargetPosition(-1600);
//        linearSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        linearSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        linearSlideLeft.setPower(0.3);
//        linearSlideRight.setPower(0.3);
//        while (frontLeft.isBusy() && backRight.isBusy()) {
//
//        }
//    }

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