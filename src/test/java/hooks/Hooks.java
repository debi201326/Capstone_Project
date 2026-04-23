package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;

public class Hooks {

    @Before("@ui")
    public void setUpUI() {
        DriverFactory.initDriver();
        System.out.println("Browser started");
    }

    @After("@ui")
    public void tearDownUI() {
        DriverFactory.quitDriver();
        System.out.println("Browser closed");
    }
}