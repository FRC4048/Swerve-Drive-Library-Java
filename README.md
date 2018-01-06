# Team 4048 Swerve Drive Module
The swerve drive library encapsulates the code necessary to deploy a swerve drive sustem to your robot.
The library is designed with the goal of being adjustable to various hardware configuration and is distributed with some hardware implementations out-of-the-box.

The swerve drive module is divided into several subsystems:
## Math
Responsible for the mathematical calculations required to drive the robot.
The main class (`ServeMath`) contains the methods that would take a drive command input (e.g. Joystick
commands) and respond with a drive directive - the speed and angle to set on each drive wheel.
This code is **not** driving the robot - it is simply performing the calculations needed. Also,
the code has no external dependencies and can be adapted to work with any hardware.
## Drive
Responsible for actually interacting with the robot. Its main class (`SwerveDrive`)
is the orchestrator of the robot movement: it takes the drive input, sends it to the Math subsystem
and then drives the hardware through the use of `SwerveEnclosure`.
## Hardware Abstraction
In order to facilitate reuse and testing, the subsystem uses an abstraction layer that allows it to be independent of the hardware
actually used on the robot. This is achieved through an interface (`SwerveEnclosure`) that declares the
methods required by the rest of the library. Various interfaces for the Enclosure are available:
- CanTalonSwerveEnclosure
- GenericEnclosure
- TestEnclosure

Naturally, users of the library can add new implementations for their hardware.

# Usage
Below is a sample usage of the library with the CanTalon enclosure. Modify this to fit your setup, as necessary:

```Java

.
.
.
public class SwerveDriveSubsystem extends Subsystem {

    public static final double GEAR_RATIO = (1988d/1.2);
    private static final double L = 19;
    private static final double W = 27.5;

    // --- MOTOR LAYOUT ---
    /*
	 *                  Front
	 *      Wheel 2 -------------- Wheel 1
	 *          |                   |
	 *          |                   |
	 *          |                   |
	 *   Left   |                   |   Right
	 *          |                   |
	 *          |                   |
	 *          |                   |
	 *      Wheel 3 -------------- Wheel 4
	 *                  Back
	 */

    public void init() {

        swerveEnclosure1 = new CanTalonSwerveEnclosure("enc 1", RobotMap.swerveDriveSubsystemCANTalon1, RobotMap.swerveDriveSubsystemCANTalon2, GEAR_RATIO);
        swerveEnclosure2 = new CanTalonSwerveEnclosure("enc 2", RobotMap.swerveDriveSubsystemCANTalon3, RobotMap.swerveDriveSubsystemCANTalon4, GEAR_RATIO);
        swerveEnclosure3 = new CanTalonSwerveEnclosure("enc 3", RobotMap.swerveDriveSubsystemCANTalon5, RobotMap.swerveDriveSubsystemCANTalon6, GEAR_RATIO);
        swerveEnclosure4 = new CanTalonSwerveEnclosure("enc 4", RobotMap.swerveDriveSubsystemCANTalon7, RobotMap.swerveDriveSubsystemCANTalon8, GEAR_RATIO);

        swerveDrive = new SwerveDrive(swerveEnclosure1, swerveEnclosure2, swerveEnclosure3, swerveEnclosure4,
                    W, L);
    }

}
```