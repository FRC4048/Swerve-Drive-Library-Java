package org.usfirst.frc4048.swerve.drive.mockhardware;

import org.usfirst.frc4048.swerve.drive.BaseEnclosure;
import org.usfirst.frc4048.swerve.drive.SwerveEnclosure;
import org.usfirst.frc4048.swerve.drive.SwerveUtils;

/**
 * Mock enclosure for testing purposes
 */
public class TestEnclosure extends BaseEnclosure {

    private int encPosition;
    private double speed;
    private double angle;

    public TestEnclosure(String name, double gearRatio) {
        super(name, gearRatio);
    }

    @Override
    public int getEncPosition() {
        return encPosition;
    }

    @Override
    public void setEncPosition(int encPosition) {
        this.encPosition = encPosition;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void stop() {
        this.speed = 0;
        this.angle = 0;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAngle() {
        return angle;
    }

}
