package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.ServoController;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "TeleOp WIP")
public class Telemetry extends OpMode {
     DcMotor frontLeft;
     DcMotor frontRight;
     DcMotor backLeft;
     DcMotor backRight;

     DcMotor linearSlideLeft;
     DcMotor linearSlideRight;


    @Override
    public void init() {


        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideLeft= hardwareMap.get(DcMotor.class,"linearslideLeft");
        linearSlideRight= hardwareMap.get(DcMotor.class,"linearslideRight");

    }
@Override
    public void loop() {
        double xAxisMovement = gamepad1.left_stick_x;
        double yAxisMovement = gamepad1.left_stick_y;
        double rAxisMovement = 0.6*gamepad1.right_stick_x;
        double lAxisMovement = 0.6*gamepad2.left_stick_y;

        double linearSlideLeftPower = lAxisMovement;
        double linearSlideRightPower=lAxisMovement;
        double frontLeftPower = yAxisMovement - xAxisMovement - rAxisMovement;
        double frontRightPower = yAxisMovement + xAxisMovement + rAxisMovement;
        double backLeftPower = yAxisMovement + xAxisMovement - rAxisMovement;
        double backRightPower = yAxisMovement - xAxisMovement + rAxisMovement;

        frontLeft.setPower(0.7*frontLeftPower);
        frontRight.setPower(0.7*frontRightPower);
        backLeft.setPower(0.7*backLeftPower);
        backRight.setPower(0.7*backRightPower);

    }

}
