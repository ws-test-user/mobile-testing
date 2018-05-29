package tests;

import functional.tests.core.mobile.basetest.MobileTest;
import org.testng.annotations.Test;

public class SystemTests extends MobileTest {

    @Test
    public void runInBackground() {
        this.log.info("This is run in background test.");
    }

    @Test
    public void rotate() {
        this.log.info("This is run rotate test.");
    }
}
