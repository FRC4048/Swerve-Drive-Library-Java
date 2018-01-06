package org.usfirst.frc4048.swerve.drive;

/**
 * A Utilities class that contains some useful methods
 */
public class SwerveUtils {

	/**
	 * Convert encoder value from whatever the encoder returns to the -0.5 -
	 * +0.5 range expected by the math library The encoder is assumed to be
	 * continuous in the sense that it keeps counting beyond one turn and can go
	 * to negative values as well
	 * 
	 * @param encoderValue
	 *            the value returned by the encoder
	 * @param gearRatio
	 *            the ratio of values in a single turn
	 * @return the encoder value in the -0.5 to +0.5 range
	 */
	public static double convertEncoderValue(double encoderValue, double gearRatio) {
		double encPos = encoderValue;
		// Reverse
//		encPos *= -1;
		// Make the scale of 1 rotation to be from 0 to 1 (1 being 1 rotation)
		// if that makes sense (it can be any whole number, with the number
		// corresponding to how many rotations it has gone through)
		encPos /= gearRatio;
		// Take the mod of that number, so it displays only a number from 0 to 1
		// (inclusive, exclusive)
		encPos = encPos % 1;
		
		return encPos;
	}

	/**
	 * Convert the -0.5 - +0.5 range (for -180 to +180 degrees) to the value
	 * expected by the encoder by using the encoder's gear ratio.
	 * 
	 * @param angle
	 *            the angle to convert (in -0.5 to +0.5 range)
	 * @param gearRatio
	 *            the ratio of values in a single turn
	 * @return the encoder value in the encoder units
	 */
	public static double convertAngle(double angle, double gearRatio) {
		double encVal = angle;
		
		// Make the scale of 1 rotation to be from 0 to 1 (1 being 1 rotation)
		// if that makes sense (it can be any whole number, with the number
		// corresponding to how many rotations it has gone through)
		encVal *= gearRatio;

		return encVal;
	}
}
