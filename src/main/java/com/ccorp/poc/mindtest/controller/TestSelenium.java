package com.ccorp.poc.mindtest.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by ssge on 2015/11/3.
 */
public class TestSelenium {
    public static void main(String[] args) {
//        new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "/Users/chuck/Documents/chromedriver/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.baidu.com");
        webDriver.quit();
    }
}
