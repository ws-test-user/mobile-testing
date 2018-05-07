package calc.tests;

import calc.base.CalcTest;
import calc.pages.Digit;
import calc.pages.Operation;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Tests for system operations:
 * - runInBackground.
 * - rotation.
 */
public class SystemTests extends CalcTest {

    @Test
    public void runInBackground() {
        // Perform some operation
        this.calc.clickNumber(Digit.ONE);
        this.calc.performOperation(Operation.PLUS);
        this.calc.clickNumber(Digit.TWO);
        this.calc.performOperation(Operation.EQUALS);

        // Verify result
        Assert.assertEquals(this.calc.getResult(), "3", "Sum is not correct.");

        // Run in background
        this.calc.runInBackground(Duration.ofSeconds(5));

        // Verify result is the same
        Assert.assertEquals(this.calc.getResult(), "3", "Sum is not correct.");
    }

    @Test
    public void rotate() {
        // Perform some operation
        this.calc.clickNumber(Digit.ONE);
        this.calc.performOperation(Operation.PLUS);
        this.calc.clickNumber(Digit.TWO);
        this.calc.performOperation(Operation.EQUALS);

        // Verify result
        Assert.assertEquals(this.calc.getResult(), "3", "Sum is not correct.");

        // Rotate
        this.calc.rotate(ScreenOrientation.LANDSCAPE);

        // Verify result is the same
        Assert.assertEquals(this.calc.getResult(), "3", "Sum is not correct.");

    }
}
