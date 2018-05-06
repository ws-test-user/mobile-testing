**TestNG - Data Driven Tests**

Simple data driven tests in this package:
demo_03_testng_data_driven

Notes:

If data provider is in other class, then class name should be specified in annotation:
```
@Test(dataProviderClass = CsvDataProvider.class, dataProvider = "csvDataProvider")
```
and data provider method must be static:
```
@DataProvider(name = "csvDataProvider")
public static Object[][] createData() throws IOException {
```