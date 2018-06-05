package org.usfirst.frc4048.swerve.math;

import java.util.Arrays;
import java.util.List;

/**
 * This is the main class for the swerveDrive library.
 * The library handles the calculations required to drive a robot using SwerveDrive wheels. These wheels have two motors:
 * A drive motor that moves the robot and a turn motor that changes the wheel assembly's direction.
 *
 * MOTOR LAYOUT
 *
 * 					Front
 * 		Wheel 2 -------------- Wheel 1
 * 			|					|
 * 			|					|
 * 			|					|
 *	Left 	|					|   Right
 * 			|					|
 * 			|					|
 * 			|					|
 * 		Wheel 3 -------------- Wheel 4
 * 					Back
 *
 * The library supports two modes: Robot centric and Field centric. In Robot centric mode the robot turns relative to its
 * current position: 45 degrees to the right will turn the robot 45 degrees to the right (for example, if it is pointing
 * north before the turn, it will point north-east after the turn. In Field centric mode the robot turns to face the given
 * number of degrees relative to the firld's orientation: 0 means straight ahead down the field, 90 means to the right, etc.
 */
public class SwerveMath {
    // Robot dimensions. Units are of no importance. Required
    private final double length;
    private final double width;

    // The diagonal of the robot dimensions. Internal
    private final double diagonal;

    // The scale factor to control robot maximum speed. Optional.
    private final double SCALE_SPEED = 1.00;

    // The "Centric" mode for the robot
    	private CentricMode centricMode = CentricMode.ROBOT;

    public void setModeField() {
		centricMode= CentricMode.FIELD;
	}
    /**
     * Constructor
     * @param width the robot width (units do not matter)
     * @param length the robot length (units do not matter)
     */
    public SwerveMath(double width, double length) {
        assert (width > 0) : "Width has to be larger than 0";
        assert (length > 0) : "Length has to be larger than 0";

        this.width = width;
        this.length = length;

        diagonal = Math.sqrt(Math.pow(this.length, 2) + Math.pow(this.width, 2));
    }


    public CentricMode getCentricMode() {
        return centricMode;
    }


    public void setCentricMode(CentricMode centricMode) {
        this.centricMode = centricMode;
    }


    /**
     * move
     * Moves the robot based on 3 inputs - fwd (forward), str(strafe), and rcw(rotation clockwise)
     * Inputs are between -1 and 1, with 1 being full power, -1 being full reverse, and 0 being neutral.
     * The method uses gyro for field centric driving, if it is enabled.
     * @param fwd the forward power value range -1.0(back) - 1.0(fwd)
     * @param str the strafe power value range -1.0(left) - 1.0(right)
     * @param rcw the rotation power value range -1.0(ccw) - 1.0(cw)
     * @param gyroValue the value of the gyro input to be used by the calculation. Optional. Only used when the robot is in field-centric mode. Values are 0-360
     * @return List of wheel movement directives. The list indices correspond to the wheel numbering scheme as above, zero-based.
     */
    public List<SwerveDirective> move(double fwd, double str, double rcw, Double gyroValue) {

        if ((gyroValue == null) && centricMode.equals(CentricMode.FIELD)) {
            throw new IllegalStateException("Cannot use field centric mode without a Gyro value");
        }
        
        //Adjust for Gyro (if wanted)
        if (isFieldCentric()){
            //Convert the gyro angle (in degrees) to radians.
            double gyro = (gyroValue * Math.PI) / 180;

            double temp = fwd * Math.cos(gyro) + str * Math.sin(gyro);
            str = -fwd * Math.sin(gyro) + str * Math.cos(gyro);
            fwd = temp;
        }

        //These 4 variables are used in the swerve drive calculations.
        double a = str - rcw*(length / diagonal);
        double b = str + rcw*(length / diagonal);
        double c = fwd - rcw*(width / diagonal);
        double d = fwd + rcw*(width / diagonal);

        //These are the equations for the wheel speed, for motors 1-4.
        double ws1 =  Math.sqrt(Math.pow(b,2)+Math.pow(c,2));
        double ws2 =  Math.sqrt(Math.pow(b,2)+Math.pow(d,2));
        double ws3 =  Math.sqrt(Math.pow(a,2)+Math.pow(d,2));
        double ws4 =  Math.sqrt(Math.pow(a,2)+Math.pow(c,2));

        //These are the equations for the wheel angle, for motors 1-4
        double wa1 =  Math.atan2(b,c)*180/Math.PI;
        double wa2 =  Math.atan2(b,d)*180/Math.PI;
        double wa3 =  Math.atan2(a,d)*180/Math.PI;
        double wa4 =  Math.atan2(a,c)*180/Math.PI;

        //This is to normalize the speed (if the largest speed is greater than 1, change accordingly).
        double max = ws1;
        if(ws2>max) max = ws2;
        if(ws3>max) max = ws3;
        if(ws4>max) max = ws4;
        if(max>1){
            ws1/=max;
            ws2/=max;
            ws3/=max;
            ws4/=max;
        }

        //Wheel angle was in the range of -180 to 180. Now its -.5 to .5
        wa1/=360;
        wa2/=360;
        wa3/=360;
        wa4/=360;

        //Used to scale the movement speeds for testing (so you don't crash into walls)
        ws1*=SCALE_SPEED;
        ws2*=SCALE_SPEED;
        ws3*=SCALE_SPEED;
        ws4*=SCALE_SPEED;

        SwerveDirective d1 = new SwerveDirective(wa1, ws1);
        SwerveDirective d2 = new SwerveDirective(wa2, ws2);
        SwerveDirective d3 = new SwerveDirective(wa3, ws3);
        SwerveDirective d4 = new SwerveDirective(wa4, ws4);

        return Arrays.asList(d1, d2, d3, d4);
    }

    private boolean isFieldCentric() {
        return centricMode.equals(CentricMode.FIELD);
    }

}
