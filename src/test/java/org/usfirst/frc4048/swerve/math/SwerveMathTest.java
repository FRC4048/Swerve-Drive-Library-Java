package org.usfirst.frc4048.swerve.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test code for the SwerveDrive library
 */
public class SwerveMathTest {

    private List<Double> encoders;

    @Before
    public void beforeEach() {
        encoders = Arrays.asList(0.0, 0.0, 0.0, 0.0);
    }

    @Test(expected = AssertionError.class)
    public void testWidth0() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(0, 1);
    }

    @Test(expected = AssertionError.class)
    public void testLength0() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 0);
    }

    @Test
    public void testNullGyroSuccess() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
    }

    @Test(expected = IllegalStateException.class)
    public void testNullGyroFail() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        classUnderTest.setCentricMode(CentricMode.FIELD);
        List<SwerveDirective> result = classUnderTest.move(0.0, 0.0, 0.0, null);
    }

    @Test
    public void testDontMove() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(0.0, 0.0, 0.0, null);

        // Result: Stay in place
        assertDirective(0.0, 0.0, result.get(0));
        assertDirective(0.0, 0.0, result.get(1));
        assertDirective(0.0, 0.0, result.get(2));
        assertDirective(0.0, 0.0, result.get(3));
    }

    @Test
    public void testMoveForward() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(1.0, 0.0, 0.0, null);

        // Result: Move full speed ahead
        assertDirective(0.0, 1.0, result.get(0));
        assertDirective(0.0, 1.0, result.get(1));
        assertDirective(0.0, 1.0, result.get(2));
        assertDirective(0.0, 1.0, result.get(3));
    }

    @Test
    public void testMoveBackward() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(-1.0, 0.0, 0.0, null);

        // Result: Move full speed back
        assertDirective(0.5, 1.0, result.get(0));
        assertDirective(0.5, 1.0, result.get(1));
        assertDirective(0.5, 1.0, result.get(2));
        assertDirective(0.5, 1.0, result.get(3));
    }

    @Test
    public void testTurnClockwise() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(0.0, 0.0, 1.0, null);

        // Result: rotate in place
        assertDirective(0.375, 1.0, result.get(0));
        assertDirective(0.125, 1.0, result.get(1));
        assertDirective(-0.125, 1.0, result.get(2));
        assertDirective(-0.375, 1.0, result.get(3));
    }

    @Test
    public void testTurnCounterClockwise() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(0.0, 0.0, -1.0, null);

        // Result: rotate in place
        assertDirective(-0.125, 1.0, result.get(0));
        assertDirective(-0.375, 1.0, result.get(1));
        assertDirective(0.375, 1.0, result.get(2));
        assertDirective(0.125, 1.0, result.get(3));
    }

    @Test
    public void testStrafeRight() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(0.0, 1.0, 0.0, null);

        // Result: Move right
        assertDirective(0.25, 1.0, result.get(0));
        assertDirective(0.25, 1.0, result.get(1));
        assertDirective(0.25, 1.0, result.get(2));
        assertDirective(0.25, 1.0, result.get(3));
    }

    @Test
    public void testStrafeLeft() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(0.0, -1.0, 0.0, null);

        // Result: Move left
        assertDirective(-0.25, 1.0, result.get(0));
        assertDirective(-0.25, 1.0, result.get(1));
        assertDirective(-0.25, 1.0, result.get(2));
        assertDirective(-0.25, 1.0, result.get(3));
    }

    @Test
    public void testMoveDiagonalNE() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(1.0, 1.0, 0.0, null);

        // Result: Move move forward and to the right
        assertDirective(0.125, 1.0, result.get(0));
        assertDirective(0.125, 1.0, result.get(1));
        assertDirective(0.125, 1.0, result.get(2));
        assertDirective(0.125, 1.0, result.get(3));
    }

    @Test
    public void testMoveDiagonalNW() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(1.0, -1.0, 0.0, null);

        // Result: Move forward and to the left
        assertDirective(-0.125, 1.0, result.get(0));
        assertDirective(-0.125, 1.0, result.get(1));
        assertDirective(-0.125, 1.0, result.get(2));
        assertDirective(-0.125, 1.0, result.get(3));
    }

    @Test
    public void testArcForwardRight() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(1.0, 0.0, 1.0, null);

        // Result: Move in an arc forward and to the right
        assertDirective(0.1875, 0.414, result.get(0));
        assertDirective(0.0625, 1.0, result.get(1));
        assertDirective(-0.0625, 1.0, result.get(2));
        assertDirective(-0.1875, 0.414, result.get(3));
    }

    @Test
    public void testArcBackwardLeft() throws Exception {
        SwerveMath classUnderTest = new SwerveMath(1, 1);
        List<SwerveDirective> result = classUnderTest.move(-1.0, 0.0, 1.0, null);

        // Result: Move in an arc backwards and to teh left
        assertDirective(0.4375, 1.0, result.get(0));
        assertDirective(0.3125, 0.414, result.get(1));
        assertDirective(-0.3125, 0.414, result.get(2));
        assertDirective(-0.4375, 1.0, result.get(3));
    }


    // Test with robot and field centric
    // Test with different width and length
    // Test with different encoder values


    private void assertDirective(double expectedAngle, double expectedSpeed, SwerveDirective actual) throws Exception {
        Assert.assertEquals(expectedAngle, actual.getAngle(), 0.01);
        Assert.assertEquals(expectedSpeed, actual.getSpeed(), 0.01);
    }

}
