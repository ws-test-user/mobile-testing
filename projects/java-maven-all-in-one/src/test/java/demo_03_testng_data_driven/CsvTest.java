package demo_03_testng_data_driven;

import org.testng.annotations.Test;

/**
 * Data driven test which use csv data provider.
 */
public class CsvTest {

    // This test method declares that its data
    // should be supplied by the Data Provider named "simpleDataProvider"
    @Test(dataProviderClass = CsvDataProvider.class, dataProvider = "csvDataProvider")
    public void verifyNameAndAgeFromCsv(String name, String age) {
        System.out.println(name + " age is " + age);
    }
}
