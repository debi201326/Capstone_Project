package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void initDriver() {

        try {

            boolean useGrid = Boolean.parseBoolean(System.getProperty("grid", "false"));

            if (useGrid) {
                driver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        new ChromeOptions()
                );
            } else {
                driver = new ChromeDriver();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;   
        }
    }
}