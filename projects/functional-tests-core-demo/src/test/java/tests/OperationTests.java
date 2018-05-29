package tests;

import calc.Calc;
import functional.tests.core.mobile.basetest.MobileTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OperationTests extends MobileTest {

    private Calc calc;

    @BeforeClass
    public void calcTests() {
        calc = new Calc();
    }

    @Test
    public void sum() throws Exception {
        this.calc.clickDigit(1);
        this.calc.performOperation("plus");
        this.calc.clickDigit(2);
        this.calc.performOperation("equals");
        Assert.assertEquals(this.calc.getResult(), "3", "Result is not correct!");
        functional.tests.core.mobile.element.UIElement el = this.find.byText("3");
        this.imageVerification.verifyElement(el, "element_3", 1.0D);
        this.imageVerification.verifyScreen("calc_after_1_2");
    }

    @Test
    public void divide() throws Exception {
        this.calc.clickDigit(1);
        this.calc.performOperation("minus");
        this.calc.clickDigit(2);
        this.calc.performOperation("equals");
        Assert.assertEquals(this.calc.getResult(), "-1", "Result is not correct!");
        this.imageVerification.verifyScreen("calc_after_1_2");
    }
}
