package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.exception.VerificationFailureException;
import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import com.ccorp.poc.mindtest.utility.ByFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * Created by ssge on 2015/11/10.
 */
public class VerifyElementPresent implements ICommand {

    private String elementIdentifier;
    private By by;

    @Override
    public void constructCommand(String script) {
        ArrayList<String> params = this.extractParams(script);
        this.elementIdentifier = params.get(0);
        this.by = ByFactory.buildBy(this.elementIdentifier, params.get(1));
    }

    @Override
    public void execute(WebDriver webDriver, ScriptExecutionContext context) {
        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "Verify if element " + elementIdentifier + " by " + by.toString() + " is present");
        WebElement element = webDriver.findElement(by);
        if(element == null){
            throw new VerificationFailureException("Element by " + by.toString() + " is not present");
        }
    }

    @Override
    public int getParamsCount() {
        return 2;
    }
}
