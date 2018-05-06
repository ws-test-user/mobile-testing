package calc.tests;

import calc.base.CalcTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * Tests for calculator operations.
 */
public class OperationTests extends CalcTest {
    @Test
    public void sumNumbers() {
        this.calc.clickNumber("1");
        this.calc.performOperation("plus");
        this.calc.clickNumber("2");
        this.calc.performOperation("equals");
        Assert.assertEquals(this.calc.getResult(), "3", "Sum is not correct.");
    }

    @Test
    public void subtractNumbers() {
        this.calc.clickNumber("9");
        this.calc.performOperation("minus");
        this.calc.clickNumber("8");
        this.calc.performOperation("equals");
        Assert.assertEquals(this.calc.getResult(), "1", "Subtract is not correct.");
    }

    @Test
    @Ignore("Not Implemented.")
    public void deviceNumbers() {

    }

    @Test
    @Ignore("Not Implemented.")
    public void multiplyNumbers() {

    }
}
