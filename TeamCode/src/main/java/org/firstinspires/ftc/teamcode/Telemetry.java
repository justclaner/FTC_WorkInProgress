package org.firstinspires.ftc.teamcode;
import android.graphics.RenderNode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.ServoController;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.servo;
@TeleOp(name = "TeleOp (Use This)")
public class Telemetry extends OpMode {
    //GoBILDA 5202/3/4 series
    DcMotor frontLeft;  // 2
    DcMotor frontRight; // 0
    DcMotor backLeft;  // 1
    DcMotor backRight; // 3

    DcMotor linearSlideLeft;  // 0
    DcMotor linearSlideRight; // 1


    Servo clawLeft;  //1

    Servo clawRight;  //0

    Servo armLeft;    //2
    Servo armRight; //3
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


      clawLeft = hardwareMap.get(Servo.class, "clawLeft");
      clawRight = hardwareMap.get(Servo.class, "clawRight");
        armLeft = hardwareMap.get(Servo.class, "armLeft");
        armRight = hardwareMap.get(Servo.class, "armRight");

        armLeft.setDirection(Servo.Direction.REVERSE);
        clawLeft.setDirection(Servo.Direction.REVERSE);

      clawLeft.scaleRange(0,1);
      clawRight.scaleRange(0,1);
      armLeft.scaleRange(0,1);
      armRight.scaleRange(0,1);

      clawLeft.setPosition(0.34);
      clawRight.setPosition(0.1);



//        armRight.setPosition(1);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);







        //originally only backLeft reverse

       // frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);
       backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
       frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

      //  linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE); // or right
        linearSlideLeft.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    int a2Pressed = 0;

    double armPosition = 0;
    double sPos = 0;
    String linearSlideStatus = "Off";

    @Override
    public void loop() {

        double xAxisMovement = gamepad1.left_stick_x;
        double yAxisMovement = gamepad1.left_stick_y;
        double rAxisMovement = gamepad1.right_stick_x;
        double lAxisMovement = 0.6* gamepad2.left_stick_y;
        double aAxisMovement = 0.05*gamepad2.right_stick_y;

        //removes joystick drift; do not move this code to anywhere else
        if (Math.abs(xAxisMovement) < 0.05) {
            xAxisMovement = 0;
        } else if (Math.abs(yAxisMovement) < 0.05) {
            yAxisMovement = 0;
        } else if (Math.abs(rAxisMovement) < 0.05) {
            rAxisMovement = 0;
        } else if (Math.abs(lAxisMovement) < 0.05) {
            lAxisMovement = 0;
        }

        //movement
        double frontLeftPower = yAxisMovement - xAxisMovement - rAxisMovement;
        double frontRightPower = yAxisMovement + xAxisMovement + rAxisMovement;
        double backLeftPower = yAxisMovement + xAxisMovement - rAxisMovement;
        double backRightPower = yAxisMovement - xAxisMovement + rAxisMovement;

        //0.5 originally
        double multiplier = -0.7;
        frontLeft.setPower(multiplier * frontLeftPower);
        frontRight.setPower(multiplier * frontRightPower);
        backLeft.setPower(multiplier * backLeftPower);
        backRight.setPower(multiplier * backRightPower);



        if (gamepad2.a) {
            //a is toggle for downward linear slide movement
            if (a2Pressed == 0) {
                a2Pressed = 1;
                linearSlideStatus = "On";
            } else {
                a2Pressed = 0;
                linearSlideStatus = "Off";
            }
        }




        if (gamepad1.x) {
        clawRight.setPosition(0.1);
        clawLeft.setPosition(0.34);
        }

        if (gamepad1.y) {
          clawLeft.setPosition(0.55);
          clawRight.setPosition(0.32);
        }
//            armPosition += aAxisMovement;
            //arm.setPosition(armPosition);

        if (gamepad2.left_bumper) {
            armLeft.setPosition(0);
            armRight.setPosition(0);
        }
        if (gamepad2.right_bumper) {
            armLeft.setPosition(1);
            armRight.setPosition(1);
        }
        




        linearSlideLeft.setPower(0.4*(lAxisMovement + a2Pressed));
        linearSlideRight.setPower(0.4*(lAxisMovement + a2Pressed));



            //-1620 maximum linear slide extension
//        if (linearSlideLeft.getCurrentPosition() >= -1620 && linearSlideLeft.getCurrentPosition() <= 1) {
//            linearSlideLeft.setPower(0.3 * linearSlidePower);
//            linearSlideRight.setPower(0.3 * linearSlidePower);
//        } else  {
//            linearSlideLeft.setPower(0);
//            linearSlideRight.setPower(0);
//        }
        telemetry.addData("frontLeft", frontLeft.getPower());
        telemetry.addData("frontRight", frontRight.getPower());
        telemetry.addData("backLeft", backLeft.getPower());
        telemetry.addData("backRight", backRight.getPower());
        telemetry.addData("Left Slide Power", linearSlideLeft.getPower());
        telemetry.addData("Right Slide Power", linearSlideRight.getPower());
        telemetry.addData("Left Slide Pos", linearSlideLeft.getCurrentPosition());
        telemetry.addData("Right Slide Pos", linearSlideRight.getCurrentPosition());
        telemetry.addData("Linear Slide Status (Suspend)", linearSlideStatus);
        telemetry.addData("clawArm Position", clawRight.getPosition());
        telemetry.addData("clawArm Position", clawLeft.getPosition());
        telemetry.addData("rightArm Position", armRight.getPosition());
        telemetry.addData("leftArm Position", armLeft.getPosition());
        telemetry.update();

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