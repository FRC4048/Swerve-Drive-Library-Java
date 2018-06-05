package org.usfirst.frc4048.swerve.drive;

/**
 * Base class for enclosure. Implements common behavior that helps with the robot driving:
 * - Move method that takes into account current position and optimizes the movement to reduce angle rotation
 * - Allows the wheel to make full rotation (when reaching full rotation don't go back to 0, rather keep rotation in same direction)
 * This class uses abstract lower-level implementations of setSpeed and setAngle to be implemented by hardware-specific sub-classes
 */
public abstract class BaseEnclosure implements SwerveEnclosure {

    private String name;
    private double gearRatio;

    public BaseEnclosure(String name, double gearRatio) {
        this.name = name;
        this.gearRatio = gearRatio;
    }

    /**
     *
     * @param speed: the speed to move the wheel, -1.0 being full backwards, 0 being stop +1.0 being full forward
     * @param angle: the angle to turn the wheel, 0 being forward, -1.0 being full turn counterclockwise, +1.0 being full turn clockwise
     */
    public void move(double speed, double angle)
	{
		int encPosition = getEncPosition();
		angle = convertAngle(angle, encPosition);
		
		if(shouldReverse(angle, encPosition))
		{
			if(angle < 0)
				angle += 0.5;
			else
				angle -= 0.5;
			
			speed *= -1.0;
		}
		
		setSpeed(speed);
		
		if(speed != 0.0) {
			setAngle(angle); 
		}
	}
    public String getName() {
        return name;
    }

    /**
     * @return the value of the angle encoder (used to calculate current wheel position)
     * TODO: This should be converted to -1 - +1 range...
     */
    protected abstract int getEncPosition();

    /**
     * Sets the value of the angle encoder (used for aligning wheel in case of drift)
     * @param encPosition the current encoder value
     * TODO: This should be converted to -1 - +1 range...
     */
    protected abstract void setEncPosition(int encPosition);

    /**
     * Set the value of the drive motor
     * @param speed the speed value to set: -1 - full backwards, 0 - stop, +1 - full forward
     */
    protected abstract void setSpeed(double speed);

    /**
     * Set the angle for the steer motor
     * @param abgle the angle value: -0.5 - counterclockwise 180 degrees, 0 - forward 180 degrees, +0.5 - 180 degrees clockwise
     */
    protected abstract void setAngle(double abgle);


    private boolean shouldReverse(double wa, double encoderValue){

        double ea = SwerveUtils.convertEncoderValue(encoderValue, gearRatio);

        //Convert the next wheel angle, which is from -.5 to .5, to 0 to 1
        if (wa < 0) wa += 1;

        //Find the difference between the two (not sure if the conversion from (-0.5 to 0.5) to (0 to 1) above is needed)
        //Difference between the two points. May be anything between -1 to 1, but we are looking for a number between -.5 to .5
        double longDifference = Math.abs(wa - ea);

        //finds shortest distance (0 to 0.5), always positive though (which is what we want)
        double difference = Math.min(longDifference, 1.0-longDifference);

        //If the difference is greater than 1/4, then return true (aka it is easier for it to turn around and go backwards than go forward)
        if (difference > 0.25) return true;
        else return false;
    }

    private double convertAngle(double angle, double encoderValue) {
        //angles are between -.5 and .5
        //This is to allow the motors to rotate in continuous circles (pseudo code on the Team 4048 forum)
        double encPos = encoderValue / gearRatio;

        double temp = angle;
        temp += (int)encPos;

        encPos = encPos % 1;

        if ((angle - encPos) > 0.5) temp -= 1;

        if ((angle - encPos) < -0.5) temp += 1;

        return temp;
    }
}
