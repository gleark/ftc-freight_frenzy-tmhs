package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@Autonomous(name="Auto Drive By Time V0", group="AutoBlue")
//@Disabled
public class PushbotAutoDriveByTime_v0 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot_v9 robot   = new HardwarePushbot_v9();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.4;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 3 seconds
        driveStraight(FORWARD_SPEED, 3000);

        robot.allStop();

        // Step 2:  Spin right for 1.3 seconds
        turnRight(TURN_SPEED, 1300);

        robot.allStop();

        // Step 3:  Drive Backwards for 1 Second
        driveStraight(-FORWARD_SPEED, 1000);

        robot.allStop();

        // Step 4:  Stop and close the claw.
        robot.leftClaw.setPosition(1.0);
        robot.rightClaw.setPosition(0.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }  // end of runOpMode

    void driveStraight (double motorPower, double timeInMilliSeconds){
        // drive straight for a given amount of time(msec)
        runtime.reset();
        robot.leftDrive.setPower(motorPower);
        robot.rightDrive.setPower(motorPower);
        while (opModeIsActive() && runtime.milliseconds() < timeInMilliSeconds){
            telemetry.addData("Straight", ": %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.allStop();
    }

    void turnRight (double motorPower, double timeInMilliSeconds){
        // drive straight for a given amount of time(msec)
        runtime.reset();
        robot.turnRight(motorPower);
        while (opModeIsActive() && runtime.milliseconds() < timeInMilliSeconds){
            telemetry.addData("Turn Right", ": %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.allStop();
    }

    void turnLeft (double motorPower, double timeInMilliSeconds){
        // drive straight for a given amount of time(msec)
        runtime.reset();
        robot.turnLeft(motorPower);
        while (opModeIsActive() && runtime.milliseconds() < timeInMilliSeconds){
            telemetry.addData("Turn Left", ": %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.allStop();
    }

}
