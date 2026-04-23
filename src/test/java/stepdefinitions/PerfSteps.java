package stepdefinitions;

import io.cucumber.java.en.Then;
import utils.JMeterUtils;

public class PerfSteps {

    @Then("performance test is executed with threshold {int} ms")
    public void runPerformanceTest(int threshold) throws Exception {
        JMeterUtils.runTest(threshold);
    }
}