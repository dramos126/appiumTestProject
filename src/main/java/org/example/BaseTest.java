package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.net.URL;
import java.util.List;

public class BaseTest {
    AndroidDriver driver;
    String login = "iamlearningautomoation";
    String password = "Directv1";
    @BeforeTest
    public void setUp() throws Exception {
        // Desired capabilities type of object and capabilities is the name of the object = to the right
        // is creating a new object of DesiredCapabilites
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String name = "";
        // these are setting device setting capabilities
        // https://appium.io/docs/en/writing-running-appium/caps/#uiautomator2
        capabilities.setCapability("appium:deviceName", "pixel c");
        capabilities.setCapability("appium:udid", "912X1U9FM");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.google.android.gm");
        capabilities.setCapability("appActivity", "com.google.android.gm.ui.MailActivityGmail");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        //full reset
        //capabilities.setCapability("fullReset", true);

        //reset after test capability
        //capabilities.setCapability("fullReset", true);


        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @Test
    void blankTest() throws InterruptedException {
        // Does something now change comment
        WebElement newInGmail = driver.findElement(By.id("com.google.android.gm:id/welcome_tour_title"));
        WebElement allTheFeatures = driver.findElement(By.id("com.google.android.gm:id/welcome_tour_text"));
        WebElement gotIt = driver.findElement(By.id("com.google.android.gm:id/welcome_tour_got_it"));
        Assert.assertEquals(newInGmail.getText(), "New in Gmail");
        Assert.assertEquals(allTheFeatures.getText(), "All the features you love with a fresh new look");
        Assert.assertEquals(gotIt.getText(), "GOT IT");
        gotIt.click();
        try {
            // try is to catch
            Thread.sleep(500);
            //   System.out.println("This is before throwing a new exception");
            //   throw new Exception("Uncaught Exception sleep throws and why we put it in try catch");
            // below is a webelement that could not be found so it threw an exception to be caught
            // WebElement addAnEmailAddress = driver.findElement(By.id("com.google.android.gm:id/setup_addresses_add_another"));

        } catch (Exception e) {
            // will only ever get into a catch block if an exception is thrown. not everytime an exception is thrown
           System.out.println("Caught interrupted exception that can be thrown from sleep in this example");
         //   throw new Exception("Failed to find id Add an email address");

        }
        // Thread.sleep(500);

        // driver.manage().timeouts().implicitlyWait(60);
        // wait.until(Assert.assertEquals(""););
        // Page 2 after selecting Got it
        // need to figure out wait
        //wait(7000);
        // WebElement youCanNow = driver.findElement(By.id("com.google.android.gm:id/setup_addresses_title"));
        //  Assert.assertEquals(youCanNow.getText(), "You can now add all your email addresses. Learn more");
         WebElement addAnEmailAddress = driver.findElement(By.id("com.google.android.gm:id/setup_addresses_add_another"));
         Assert.assertEquals(addAnEmailAddress.getText(), "Add an email address");
         addAnEmailAddress.click();
         Thread.sleep(500);
         List<WebElement> emailProviders = driver.findElements(By.id("com.google.android.gm:id/account_setup_label"));
         emailProviders.get(0).click();
        // A

        // String login = login, "iamlearningautomoation";
        //String password = "Directv1";
    }
}
