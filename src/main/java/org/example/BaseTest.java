package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

public class BaseTest {
    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        // Desiredcapabilities type of object and capabilities is the name of the object = to the right
        // is creating a new object of DesiredCapabilites
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String name = "";
        // these are setting device setting capabilities
        // https://appium.io/docs/en/writing-running-appium/caps/#uiautomator2
        capabilities.setCapability("appium:deviceName", "pixel c");
        capabilities.setCapability("appium:udid", "912X1U9FM");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        capabilities.setCapability("appium:automationName", "uiautomator2");



        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @Test
    void blankTest() {
       // Does nothing
    }
}
