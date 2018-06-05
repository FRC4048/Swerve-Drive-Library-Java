package org.usfirst.frc4048.swerve.drive;

import org.usfirst.frc4048.swerve.math.CentricMode;
import org.usfirst.frc4048.swerve.math.SwerveDirective;
import org.usfirst.frc4048.swerve.math.SwerveMath;

import java.util.List;

/**
 * The main class for the SwerveDrive subsystem: This class handles all aspects of controlling the swerve drive.
 * Use this class in your program if you want the easiest way to integrate swerve drive into your robot.
 */
public class SwerveDrive {
    // Enclosures 1-4 are the drive/steer combos
    private SwerveEnclosure swerveEnclosure1;
    private SwerveEnclosure swerveEnclosure2;
    private SwerveEnclosure swerveEnclosure3;
    private SwerveEnclosure swerveEnclosure4;

    private final SwerveMath swerveMath;

    public SwerveDrive(SwerveEnclosure swerveEnclosure1,
                       SwerveEnclosure swerveEnclosure2,
                       SwerveEnclosure swerveEnclosure3,
                       SwerveEnclosure swerveEnclosure4,
                       double width, double length) {

        this.swerveEnclosure1 = swerveEnclosure1;
        this.swerveEnclosure2 = swerveEnclosure2;
        this.swerveEnclosure3 = swerveEnclosure3;
        this.swerveEnclosure4 = swerveEnclosure4;

        // instantiate the swerve library with a gyro provider using pigeon1
        swerveMath = new SwerveMath(width, length);

    }

    /**
     * move
     * Moves the robot based on 3 inputs - fwd (forward), str(strafe), and rcw(rotation clockwise)
     * Inputs are between -1 and 1, with 1 being full power, -1 being full reverse, and 0 being neutral.
     * The method uses gyro for field centric driving, if it is enabled.
     *
     * @param fwd
     * @param str
     * @param rcw
     * @param gyroValue the value of the gyro input to be used by the calculation. Optional. Only used when the robot is in field-centric mode.
     */
    public void move(double fwd, double str, double rcw, Double gyroValue) {
        // Get the move command calculated
        List<SwerveDirective> swerveDirectives = swerveMath.move(fwd, str, rcw, gyroValue);

        swerveEnclosure1.move(swerveDirectives.get(0).getSpeed(), swerveDirectives.get(0).getAngle());
        swerveEnclosure2.move(swerveDirectives.get(1).getSpeed(), swerveDirectives.get(1).getAngle());
        swerveEnclosure3.move(swerveDirectives.get(2).getSpeed(), swerveDirectives.get(2).getAngle());
        swerveEnclosure4.move(swerveDirectives.get(3).getSpeed(), swerveDirectives.get(3).getAngle());
    }

    /**
     * Stop the robot (set speed to 0)
     * @throws Exception 
     */
    public void stop() {
        swerveEnclosure1.stop();
        swerveEnclosure2.stop();
        swerveEnclosure3.stop();
        swerveEnclosure4.stop();
    }

    /**
     * Change the centric-mode of the robot (this can be done dynamically any time and will affect
     * the robot behavior from that point on)
     */
    public void setCentricMode(CentricMode centricMode) {
        this.swerveMath.setCentricMode(centricMode);
    }
    
    public void setModeField() {
		this.swerveMath.setModeField();
	}

}
