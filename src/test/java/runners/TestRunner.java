package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

import utils.ExcelReader;
import utils.FeatureGenerator;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        tags = "@ui or @api",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setup() throws Exception {

        Object[][] data = ExcelReader.getData(
                "src/test/resources/testdata/loginData.xlsx",
                "Sheet1"
        );

        FeatureGenerator.generateFeature(data);

        System.out.println("Feature file generated successfully");
    }
}