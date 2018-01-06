package org.usfirst.frc4048.swerve.drive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc4048.swerve.drive.mockhardware.TestAnalogInput;
import org.usfirst.frc4048.swerve.drive.mockhardware.TestEnclosure;

/**
 * Testing the entire system, with code simulating the subsystem initialization
 */
public class SwerveSubsystemTest {

    private static final int ZERO1 = 748;
    private static final int ZERO2 = 2830;
    private static final int ZERO3 = 1765;
    private static final int ZERO4 = 613;

    private static final double L = 19;
    private static final double W = 27.5;

    private static final double GEAR_RATIO = 1988/1.2;

    private TestEnclosure swerveEnclosure1;
    private TestEnclosure swerveEnclosure2;
    private TestEnclosure swerveEnclosure3;
    private TestEnclosure swerveEnclosure4;

    private TestAnalogInput analogInput1;
    private TestAnalogInput analogInput2;
    private TestAnalogInput analogInput3;
    private TestAnalogInput analogInput4;

//    private TestGyro gyro;

    private SwerveDrive classUnderTest;

    @Before
    public void init() throws Exception {
        // The swerve enclosures under test
        swerveEnclosure1 = new TestEnclosure("Enclosure 1", GEAR_RATIO);
        swerveEnclosure2 = new TestEnclosure("Enclosure 2", GEAR_RATIO);
        swerveEnclosure3 = new TestEnclosure("Enclosure 3", GEAR_RATIO);
        swerveEnclosure4 = new TestEnclosure("Enclosure 4", GEAR_RATIO);

        // The absolute encoders
        analogInput1 = new TestAnalogInput();
        analogInput2 = new TestAnalogInput();
        analogInput3 = new TestAnalogInput();
        analogInput4 = new TestAnalogInput();

        classUnderTest = new SwerveDrive(swerveEnclosure1, swerveEnclosure2, swerveEnclosure3, swerveEnclosure4, W, L);
    }

    @Test
    public void testInitWheelsAtZero() throws Exception {
        resetEncoders(ZERO1, ZERO2, ZERO3, ZERO4);

        assertEnclosure(0, 0.0, 0.0, swerveEnclosure1);
        assertEnclosure(0, 0.0, 0.0, swerveEnclosure2);
        assertEnclosure(0, 0.0, 0.0, swerveEnclosure3);
        assertEnclosure(0, 0.0, 0.0, swerveEnclosure4);
    }

    @Test
    public void testForwardWheelsAtZero() throws Exception {
        resetEncoders(ZERO1, ZERO2, ZERO3, ZERO4);
        classUnderTest.move(1.0, 0.0, 0.0, null);

        assertEnclosure(0, 1.0, 0.0, swerveEnclosure1);
        assertEnclosure(0, 1.0, 0.0, swerveEnclosure2);
        assertEnclosure(0, 1.0, 0.0, swerveEnclosure3);
        assertEnclosure(0, 1.0, 0.0, swerveEnclosure4);
    }

    @Test
    public void testStrafeRightWheelsAtZero() throws Exception {
        resetEncoders(ZERO1, ZERO2, ZERO3, ZERO4);
        classUnderTest.move(0.0, 1.0, 0.0, null);

        assertEnclosure(0, 1.0, 0.25, swerveEnclosure1);
        assertEnclosure(0, 1.0, 0.25, swerveEnclosure2);
        assertEnclosure(0, 1.0, 0.25, swerveEnclosure3);
        assertEnclosure(0, 1.0, 0.25, swerveEnclosure4);
    }

    @Test
    public void testForwardRightWheelsAtZero() throws Exception {
        resetEncoders(ZERO1, ZERO2, ZERO3, ZERO4);
        classUnderTest.move(1.0, 1.0, 0.0, null);

        assertEnclosure(0, 1.0, 0.125, swerveEnclosure1);
        assertEnclosure(0, 1.0, 0.125, swerveEnclosure2);
        assertEnclosure(0, 1.0, 0.125, swerveEnclosure3);
        assertEnclosure(0, 1.0, 0.125, swerveEnclosure4);
    }

    @Test
    public void testInitWheelsAtEighthTurn() throws Exception {
        resetEncoders(ZERO1 + 500, ZERO2 + 500, ZERO3 + 500, ZERO4 + 500);

        assertEnclosure(207, 0.0, 0.0, swerveEnclosure1);
        assertEnclosure(207, 0.0, 0.0, swerveEnclosure2);
        assertEnclosure(207, 0.0, 0.0, swerveEnclosure3);
        assertEnclosure(207, 0.0, 0.0, swerveEnclosure4);
    }

    @Test
    public void testForwardWheelsAtEighthTurn() throws Exception {
        resetEncoders(ZERO1 + 500, ZERO2 + 500, ZERO3 + 500, ZERO4 + 500);
        classUnderTest.move(1.0, 0.0, 0.0, null);

        assertEnclosure(207, 1.0, 0.0, swerveEnclosure1);
        assertEnclosure(207, 1.0, 0.0, swerveEnclosure2);
        assertEnclosure(207, 1.0, 0.0, swerveEnclosure3);
        assertEnclosure(207, 1.0, 0.0, swerveEnclosure4);
    }

    @Test
    public void testStrafeRightWheelsAtEighthTurn() throws Exception {
        resetEncoders(ZERO1 + 500, ZERO2 + 500, ZERO3 + 500, ZERO4 + 500);
        classUnderTest.move(0.0, 1.0, 0.0, null);

        assertEnclosure(207, 1.0, 0.25, swerveEnclosure1);
        assertEnclosure(207, 1.0, 0.25, swerveEnclosure2);
        assertEnclosure(207, 1.0, 0.25, swerveEnclosure3);
        assertEnclosure(207, 1.0, 0.25, swerveEnclosure4);
    }

    @Test
    public void testForwardRightWheelsAtEighthTurn() throws Exception {
        resetEncoders(ZERO1 + 500, ZERO2 + 500, ZERO3 + 500, ZERO4 + 500);
        classUnderTest.move(1.0, 1.0, 0.0, null);

        assertEnclosure(207, 1.0, 0.125, swerveEnclosure1);
        assertEnclosure(207, 1.0, 0.125, swerveEnclosure2);
        assertEnclosure(207, 1.0, 0.125, swerveEnclosure3);
        assertEnclosure(207, 1.0, 0.125, swerveEnclosure4);
    }

    @Test
    public void testInitWheelsAtQuarterTurn() throws Exception {
        resetEncoders(ZERO1 + 1000, ZERO2 + 1000, ZERO3 + 1000, ZERO4 + 1000);

        assertEnclosure(414, 0.0, 0.0, swerveEnclosure1);
        assertEnclosure(414, 0.0, 0.0, swerveEnclosure2);
        assertEnclosure(414, 0.0, 0.0, swerveEnclosure3);
        assertEnclosure(414, 0.0, 0.0, swerveEnclosure4);
    }

    @Test
    public void testForwardWheelsAtQuarterTurn() throws Exception {
        resetEncoders(ZERO1 + 1000, ZERO2 + 1000, ZERO3 + 1000, ZERO4 + 1000);
        classUnderTest.move(1.0, 0.0, 0.0, null);

        assertEnclosure(414, 1.0, 0.0, swerveEnclosure1);
        assertEnclosure(414, 1.0, 0.0, swerveEnclosure2);
        assertEnclosure(414, 1.0, 0.0, swerveEnclosure3);
        assertEnclosure(414, 1.0, 0.0, swerveEnclosure4);
    }

    @Test
    public void testStrafeRightWheelsAtQuarterTurn() throws Exception {
        resetEncoders(ZERO1 + 1000, ZERO2 + 1000, ZERO3 + 1000, ZERO4 + 1000);
        classUnderTest.move(0.0, 1.0, 0.0, null);

        assertEnclosure(414, 1.0, 0.25, swerveEnclosure1);
        assertEnclosure(414, 1.0, 0.25, swerveEnclosure2);
        assertEnclosure(414, 1.0, 0.25, swerveEnclosure3);
        assertEnclosure(414, 1.0, 0.25, swerveEnclosure4);
    }

    @Test
    public void testForwardRightWheelsAtQuarterTurn() throws Exception {
        resetEncoders(ZERO1 + 1000, ZERO2 + 1000, ZERO3 + 1000, ZERO4 + 1000);
        classUnderTest.move(1.0, 1.0, 0.0, null);

        assertEnclosure(414, 1.0, 0.125, swerveEnclosure1);
        assertEnclosure(414, 1.0, 0.125, swerveEnclosure2);
        assertEnclosure(414, 1.0, 0.125, swerveEnclosure3);
        assertEnclosure(414, 1.0, 0.125, swerveEnclosure4);
    }

    @Test
    public void testInitWithWheelsAtHalfTurn() throws Exception {
        resetEncoders(ZERO1 + 2000, ZERO2 + 2000, ZERO3 + 2000, ZERO4 + 2000);

        assertEnclosure(828, 0.0, 0.0, swerveEnclosure1);
        assertEnclosure(828, 0.0, 0.0, swerveEnclosure2);
        assertEnclosure(828, 0.0, 0.0, swerveEnclosure3);
        assertEnclosure(828, 0.0, 0.0, swerveEnclosure4);
    }

    @Test
    public void testForwardWheelsAtHalfTurn() throws Exception {
        resetEncoders(ZERO1 + 2000, ZERO2 + 2000, ZERO3 + 2000, ZERO4 + 2000);
        classUnderTest.move(1.0, 0.0, 0.0, null);

        assertEnclosure(828, -1.0, -0.5, swerveEnclosure1);
        assertEnclosure(828, -1.0, -0.5, swerveEnclosure2);
        assertEnclosure(828, -1.0, -0.5, swerveEnclosure3);
        assertEnclosure(828, -1.0, -0.5, swerveEnclosure4);
    }

    @Test
    public void testStrafeRightWheelsAtHalfTurn() throws Exception {
        resetEncoders(ZERO1 + 2000, ZERO2 + 2000, ZERO3 + 2000, ZERO4 + 2000);
        classUnderTest.move(0.0, 1.0, 0.0, null);

        assertEnclosure(828, 1.0, 0.25, swerveEnclosure1);
        assertEnclosure(828, 1.0, 0.25, swerveEnclosure2);
        assertEnclosure(828, 1.0, 0.25, swerveEnclosure3);
        assertEnclosure(828, 1.0, 0.25, swerveEnclosure4);
    }

    @Test
    public void testForwardRightWheelsAtHalfTurn() throws Exception {
        resetEncoders(ZERO1 + 2000, ZERO2 + 2000, ZERO3 + 2000, ZERO4 + 2000);
        classUnderTest.move(1.0, 1.0, 0.0, null);

        assertEnclosure(828, -1.0, -0.375, swerveEnclosure1);
        assertEnclosure(828, -1.0, -0.375, swerveEnclosure2);
        assertEnclosure(828, -1.0, -0.375, swerveEnclosure3);
        assertEnclosure(828, -1.0, -0.375, swerveEnclosure4);
    }

    private void resetEncoders(int value1, int value2, int value3, int value4) {
        // Set the values of the absolute encoders to initial position
        analogInput1.setValue(value1);
        analogInput2.setValue(value2);
        analogInput3.setValue(value3);
        analogInput4.setValue(value4);

        // code from resetQuadEncoders
        swerveEnclosure1.setEncPosition((int) ((analogInput1.getValue() - ZERO1) / 4000.0 * GEAR_RATIO));
        swerveEnclosure2.setEncPosition((int) ((analogInput2.getValue() - ZERO2) / 4000.0 * GEAR_RATIO));
        swerveEnclosure3.setEncPosition((int) ((analogInput3.getValue() - ZERO3) / 4000.0 * GEAR_RATIO));
        swerveEnclosure4.setEncPosition((int) ((analogInput4.getValue() - ZERO4) / 4000.0 * GEAR_RATIO));
    }

    private void assertEnclosure(double encoder, double speed, double angle, TestEnclosure actual) {
        Assert.assertEquals(encoder, actual.getEncPosition(), 0.001);
        Assert.assertEquals(speed, actual.getSpeed(), 0.001);
        Assert.assertEquals(angle, actual.getAngle(), 0.001);
    }

}
