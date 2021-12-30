
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TankDrive and Scuttle", group="Pushbot")
@Disabled
public class PushbotTeleopTank_Iterative_v2 extends OpMode{
    /* Declare OpMode members. */
    HardwarePushbot_v1 robot       = new HardwarePushbot_v1(); // use the class created to define a Pushbot's hardware

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("", "Hello Driver");    //
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
    }

    // Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        if (gamepad1.left_bumper) {
            // scuttle Left
            scuttleLeft(robot.SCUTTLE_POWER);
        } else if (gamepad1.right_bumper) {
            // scuttle Right
            scuttleRight(robot.SCUTTLE_POWER);
        } else {
            robot.leftFrontDrive.setPower(left);
            robot.rightFrontDrive.setPower(right);
            robot.leftBackDrive.setPower(left);
            robot.rightBackDrive.setPower(right);
        }
        // Send telemetry message to signify robot running;
        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
    }

    // Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {
    }

    public void scuttleLeft(double power){
        robot.leftFrontDrive.setPower(-power);
        robot.leftBackDrive.setPower(+power);
        robot.rightFrontDrive.setPower(+power);
        robot.rightBackDrive.setPower(-power);
    }

    public void scuttleRight(double power){
        robot.leftFrontDrive.setPower(+power);
        robot.leftBackDrive.setPower(-power);
        robot.rightFrontDrive.setPower(-power);
        robot.rightBackDrive.setPower(+power);
    }
}
