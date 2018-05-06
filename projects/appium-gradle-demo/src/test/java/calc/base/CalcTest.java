package calc.base;

import base.MobileTest;
import calc.pages.Calc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Base calculator test class (extends MobileTest class).
 */
public class CalcTest extends MobileTest {

    protected Calc calc;

    /**
     * Instantiate new Calc object.
     */
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        this.calc = new Calc(this.client.getDriver());
    }

    /**
     * Clean calculator between tests.
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        this.calc.clean();
    }
}
