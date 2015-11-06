package com.ccorp.poc.mindtest.controller;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/**
 * Created by ssge on 2015/11/3.
 */
public class TestSelenium {
    public static void main(String[] args) throws IOException {
//        new FirefoxDriver();
//        System.setProperty("webdriver.chrome.driver", "/Users/chuck/Documents/chromedriver/chromedriver");
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.slce002.com");
        Actions actions = new Actions(webDriver);
//        new WebDriverWait(webDriver, 60).until((WebDriver input) ->
//                        null != input.findElement(By.id("header-sign-in"))
//        );
        WebElement webElement = webDriver.findElement(By.id("header-sign-in"));
        actions.moveToElement(webElement).moveToElement(webDriver.findElement(By.id("myhub"))).click().build().perform();
        new WebDriverWait(webDriver, 60).until((WebDriver input) ->
            null != input.findElement(By.id("email-id"))
        );
        WebElement emailIdInput = webDriver.findElement(By.id("email-id"));
        emailIdInput.sendKeys("slce002_q5@tixqa.com");
        WebElement passwordIdInput = webDriver.findElement(By.id("password"));
        passwordIdInput.sendKeys("abcd1234");
        File srcFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\screenshot.png"));
        passwordIdInput.submit();

//        webElement.click();
//        webElement.cli
//        WebElement myhub = webDriver.findElement(By.id("myhub"));

        webDriver.quit();


    }
}
