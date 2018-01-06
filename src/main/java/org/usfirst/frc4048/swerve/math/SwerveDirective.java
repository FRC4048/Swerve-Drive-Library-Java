package org.usfirst.frc4048.swerve.math;

/**
 * SwerveDirective is the instruction for drive control action. Use the values from this class to set the angle and speed of
 * an individual wheel.
 */
public class SwerveDirective {
    private double angle;
    private double speed;

    public SwerveDirective(double angle, double speed) {
        this.angle = angle;
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public double getSpeed() {
        return speed;
    }
}
