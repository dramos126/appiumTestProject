package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.example.driverActions.TestDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.URL;

public class BaseTest {
    TestDriver driver;
    String login = "iamlearningautomation";
    String password = "Directv1@";

    @BeforeTest
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "pixel c");
        capabilities.setCapability("appium:udid", "912X1U9FM");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.google.android.gm");
        //capabilities.setCapability("appActivity", "com.google.android.gm.ui.MailActivityGmail");
        capabilities.setCapability("appActivity", "com.google.android.gm.ConversationListActivityGmail");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("fastReset", true);
        capabilities.setCapability("newCommandTimeout", 600);
        driver = new TestDriver( new AndroidDriver(new URL("http://0.0.0.0:4723"), capabilities));
    }


    @Test
    void blankTest() throws Exception {
        WebElement newInGmail = driver.find(By.id("com.google.android.gm:id/welcome_tour_title"));
        WebElement allTheFeatures = driver.find(By.id("com.google.android.gm:id/welcome_tour_text"));
        WebElement gotIt = driver.find(By.id("com.google.android.gm:id/welcome_tour_got_it"));
        Assert.assertEquals(newInGmail.getText(), "New in Gmail");
        Assert.assertEquals(allTheFeatures.getText(), "All the features you love with a fresh new look");
        Assert.assertEquals(gotIt.getText(), "GOT IT");
        gotIt.click();

        // use functions from TestDriver to inline actions
        driver.tap(By.id("com.google.android.gm:id/setup_addresses_add_another"));
        driver.tapAtIndex(By.id("com.google.android.gm:id/account_setup_label"), 0);
        driver.sendKeys(By.xpath("//*[@resource-id='identifierId']"), login);
        driver.tap(By.xpath("//*[@class='android.widget.Button' and @text='Next']"));
        driver.sendKeys(By.xpath("//*[@resource-id='password']//*[@class='android.widget.EditText']"), password);
        driver.tap(By.xpath("//*[@class='android.widget.Button' and @text='Next']"));
        driver.tap(By.xpath("//*[@resource-id='signinconsentNext']"));
    }
}