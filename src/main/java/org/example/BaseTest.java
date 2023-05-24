package org.example;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.net.URL;
import java.util.List;

public class BaseTest {
    AndroidDriver driver;
    String login = "iamlearningautomation";
    String password = "Directv1@";

    @BeforeTest
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String name = "";
        capabilities.setCapability("appium:deviceName", "pixel c");
        capabilities.setCapability("appium:udid", "912X1U9FM");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.google.android.gm");
        //capabilities.setCapability("appActivity", "com.google.android.gm.ui.MailActivityGmail");
        capabilities.setCapability("appActivity", "com.google.android.gm.ConversationListActivityGmail");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("fastReset", true);
        capabilities.setCapability("newCommandTimeout", 600);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), capabilities);
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

            Thread.sleep(500);

        } catch (Exception e) {
            System.out.println("Caught interrupted exception that can be thrown from sleep in this example");
        }

        WebElement addAnEmailAddress = driver.findElement(By.id("com.google.android.gm:id/setup_addresses_add_another"));
        Assert.assertEquals(addAnEmailAddress.getText(), "Add an email address");
        addAnEmailAddress.click();
        Thread.sleep(500);
        //Locator strategy to make a list if Elements by certian id
        List<WebElement> emailProviders = driver.findElements(By.id("com.google.android.gm:id/account_setup_label"));
        //This will click the first item in the list
        emailProviders.get(0).click();
        // Wait in code why wait New DOM has not appeared yet and will fail
        Thread.sleep(5000);
        // List<WebElement> emailOrPhone = driver.findElements(By.className("android.widget.TextView"));
        driver.findElement(By.xpath("//*[@resource-id='identifierId']")).sendKeys(login);
        // Custom xpath to find 2 attributes on the same page in teh same hierachry path
        String nextButtonXPath = "//*[@class='android.widget.Button' and @text='Next']";
        driver.findElement(By.xpath(nextButtonXPath)).click();
        Thread.sleep(500);
        // Custom xpath to find the child of a locator resource on dif heiarchy path
        String enterPasswordXPath = "//*[@resource-id='password']//*[@class='android.widget.EditText']";
        driver.findElement(By.xpath(enterPasswordXPath)).sendKeys(password);
        String nextButtonPasswordXPath = "//*[@class='android.widget.Button' and @text='Next']";
        driver.findElement(By.xpath(nextButtonPasswordXPath)).click();
        //String iAgreeButtonXPath = "//*[@class='android.widget.Button' and @text='I agree']";
        //driver.findElement(By.xpath("//*[@resource-id='signinconsentNext']//*[@class='android.widget.Button'")).click();
        // driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.widget.Button")).click();
        //  String iAgreeButtonXPath = "//*[@resource-id='signinconsentNext']//*[@class='android.widget.Button']";
        // driver.findElement(By.xpath(iAgreeButtonXPath)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@resource-id='signinconsentNext']")).click();
        // need to swipe down before next line will throw an error
        Thread.sleep(500);
        //NEED TO SWIPE DOWN FOR BELOW LOCATOR TO BE FOUND
        // driver.findElement(By.xpath("//*[@text='Accept' and @class='android.widget.Button']")).click();

        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

// Create a new TouchAction object and perform the swipe down action
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release().perform();
    }

//    public void scroll() throws InterruptedException {
//        Dimension dimensions = driver.manage().window().getSize();
//        for (int i = 1; i > 4; i++) {
//            Double screenHeightStart = dimensions.getHeight() * 0.5;
//            int scrollStart = screenHeightStart.intValue();
//            Double screenHeightEnd = dimensions.getHeight() * 0.2;
//            int scrollEnd = screenHeightEnd.intValue();
//            driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
//        }
//    }
}