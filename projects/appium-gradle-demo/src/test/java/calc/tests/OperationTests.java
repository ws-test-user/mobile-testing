package calc.tests;

import calc.base.CalcTest;
import calc.pages.Digit;
import calc.pages.Operation;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests for calculator operations.
 */
public class OperationTests extends CalcTest {
    @Test
    public void sumNumbers() {
        this.calc.clickNumber(Digit.ONE);
        this.calc.performOperation(Operation.PLUS);
        this.calc.clickNumber(Digit.TWO);
        this.calc.performOperation(Operation.EQUALS);
        Assert.assertEquals(this.calc.getResult(), "3", "Sum is not correct.");
    }

    @Test
    public void subtractNumbers() {
        this.calc.clickNumber(Digit.NINE);
        this.calc.performOperation(Operation.MINUS);
        this.calc.clickNumber(Digit.EIGHT);
        this.calc.performOperation(Operation.EQUALS);
        Assert.assertEquals(this.calc.getResult(), "1", "Subtract is not correct.");
    }

    @Test
    public void divideNumbers() {
        this.calc.clickNumber(Digit.NINE);
        this.calc.performOperation(Operation.DIVIDE);
        this.calc.clickNumber(Digit.TREE);
        this.calc.performOperation(Operation.EQUALS);
        Assert.assertEquals(this.calc.getResult(), "3", "Divide is not correct.");
    }

    @Test
    public void multiplyNumbers() {
        this.calc.clickNumber(Digit.TWO);
        this.calc.performOperation(Operation.MULTIPLY);
        this.calc.clickNumber(Digit.TREE);
        this.calc.performOperation(Operation.EQUALS);
        Assert.assertEquals(this.calc.getResult(), "6", "Multiply is not correct.");
    }
}
