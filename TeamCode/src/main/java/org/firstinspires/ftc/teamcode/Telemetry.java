package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "TeleOp WIP")
public class Telemetry extends OpMode {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    @Override
    public void init() {
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {
        double xAxisMovement = gamepad1.left_stick_x;
        double yAxisMovement = gamepad1.left_stick_y;
        double rAxisMovement = gamepad1.right_stick_x;
        double lAxisMovement = gamepad2.left_stick_y;


        double frontLeftPower = yAxisMovement + xAxisMovement - rAxisMovement;
        double frontRightPower = yAxisMovement - xAxisMovement + rAxisMovement;
        double backLeftPower = yAxisMovement - xAxisMovement - rAxisMovement;
        double backRightPower = yAxisMovement + xAxisMovement + rAxisMovement;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

}
