package org.firstinspires.ftc.teamcode;

// Basic code is from https://docs.revrobotics.com/color-sensor/application-examples
// Also more info on how to configure is there.
 
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TestColorSensor extends LinearOpMode {

    public static final int RED_THRESHOLD       =  100 ;

    // Define some example drivemotor to demo logic
    DcMotor leftDrive  = null;
    DcMotor rightDrive = null;
    // Define a variable for our color sensor
    ColorSensor color;

    @Override
    public void runOpMode() {

        // Local hardware defs, would usually bring in a seperate HardwareMyBot class
        // Define and Initialize Motors
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);


        // Get the color sensor from hardwareMap
        color = hardwareMap.get(ColorSensor.class, "sensor_color");
        
        // Wait for the Play button to be pressed
        waitForStart();
 
        // While the Op Mode is running drive forward slowly until
        color.enableLed(true);  // turn on LED

        while (opModeIsActive()  && color.red() > 100) {

            leftDrive.setPower(0.2);
            rightDrive.setPower(0.2);

            telemetry.addData("Red",   color.red());
            telemetry.addData("Green", color.green());
            telemetry.addData("Blue",  color.blue());
            telemetry.addData("Alpha", color.alpha());
            telemetry.addData("ARGB", color.argb());

            telemetry.update();
        }

        color.enableLed(false);

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
