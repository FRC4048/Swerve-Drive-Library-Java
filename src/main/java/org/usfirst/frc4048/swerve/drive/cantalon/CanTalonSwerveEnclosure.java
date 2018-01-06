package org.usfirst.frc4048.swerve.drive.cantalon;

import com.ctre.CANTalon;
import org.usfirst.frc4048.swerve.drive.BaseEnclosure;
import org.usfirst.frc4048.swerve.drive.SwerveEnclosure;

/**
 * An implementation of the SwerveEnclosure using CanTalon motors and encoders
 */
public class CanTalonSwerveEnclosure extends BaseEnclosure implements SwerveEnclosure {

    private CANTalon driveMotor;
    private CANTalon steerMotor;

    private CanTalonEnclosureConfig config;

    public CanTalonSwerveEnclosure(String name, CANTalon driveMotor, CANTalon steerMotor, double gearRatio) {

        super(name, gearRatio);

        this.driveMotor = driveMotor;
        this.steerMotor = steerMotor;
    }

    @Override
    public void stop() {
        // TODO: deprecated...
        this.steerMotor.stopMotor();
        this.driveMotor.stopMotor();
    }

    @Override
    public void setSpeed(double power) {
        driveMotor.set(power);
    }

    @Override
    public void setAngle(double angle) {
        steerMotor.set(angle);
        // Enables the PID
        steerMotor.enable();
    }

    @Override
    public int getEncPosition() {
        return steerMotor.getEncPosition();
    }

    @Override
    public void setEncPosition(int position) {
        steerMotor.setEncPosition(position);
    }

    //TODO: Add setInverted for both motors

    public CANTalon getDriveMotor() {
        return driveMotor;
    }

    public CANTalon getSteerMotor() {
        return steerMotor;
    }
}
