package com.ccorp.poc.mindtest.utility;

import org.openqa.selenium.By;

/**
 * Created by ssge on 2015/11/10.
 */
public class ByFactory {
    public static By buildBy(String identifier, String byType) {
        By by = null;
        switch (byType) {
            case "id":
                by = By.id(identifier);
                break;
            case "class":
                by = By.className(identifier);
                break;
            case "xpath":
                by = By.xpath(identifier);
                break;
            case "linkText":
                by = By.linkText(identifier);
                break;
            case "tag":
                by = By.tagName(identifier);
                break;
        }
        return by;
    }

}
