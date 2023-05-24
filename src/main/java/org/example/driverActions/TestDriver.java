package org.example.driverActions;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class TestDriver {
    AppiumDriver driver;

    public TestDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By element) throws Exception {
        WebElement elementFound;
        try {
            elementFound = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(aDriver -> aDriver.findElement(element));
        } catch (TimeoutException e) {
            throw new Exception("Failed to find element " + element);
        }
        return elementFound;
    }

    // h
    public List<WebElement> findAll(By element) throws Exception {
        try {
            find(element);
        } catch (Exception e) {
            System.out.println("sysout - failed to find element returning empty list");
        }
        return driver.findElements(element);
    }

    public void sendKeys(By element, String keys) throws Exception {
        find(element).sendKeys(keys);
    }

    public void tap(By element) throws Exception {
        find(element).click();
    }

    public void tapAtIndex(By element, int index) throws Exception {
        findAll(element).get(index).click();
    }
}
