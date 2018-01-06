package org.usfirst.frc4048.swerve.drive.cantalon;

/**
 * Configuration class for the Swerve enclosure
 */
public class CanTalonEnclosureConfig {
	// The enclosure name
	private String name;
    // The CAN device number for the Drive Motor
    private int driveDeviceNumber;
    // The CAN device number for the Steer Motor
    private int steerDeviceNumber;

    // TODO: Move all the other constant init numbers here and make optionals

    public CanTalonEnclosureConfig(String name, int driveDeviceNumber, int steerDeviceNumber) {
    	this.name = name;
        this.driveDeviceNumber = driveDeviceNumber;
        this.steerDeviceNumber = steerDeviceNumber;
    }

    public String getName() {
		return name;
	}

	public int getDriveDeviceNumber() {
        return driveDeviceNumber;
    }

    public int getSteerDeviceNumber() {
        return steerDeviceNumber;
    }
}
