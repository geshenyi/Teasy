package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import com.ccorp.poc.mindtest.model.domain.result.TestStep;
import com.ccorp.poc.mindtest.utility.ByFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * Created by ssge on 2015/11/10.
 */
public class ClickCommand extends BaseCommand{
    private String elementIdentifier;
    private By by;

//    @Override
//    public void constructCommand(String script) {
//        ArrayList<String> params = this.extractParams(script);
//        this.elementIdentifier = params.get(0);
//        by = ByFactory.buildBy(params.get(0), params.get(1));
//    }

    @Override
    protected void constructSpecificCommand(String script) {
        ArrayList<String> params = this.extractParams(script);
        this.elementIdentifier = params.get(0);
        by = ByFactory.buildBy(params.get(0), params.get(1));
    }

//    @Override
//    public void execute(WebDriver webDriver, ScriptExecutionContext context) {
//
//    }

    @Override
    protected void executeSpecificCommand(WebDriver webDriver, ScriptExecutionContext context, TestStep testStep) {
        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "Click on element " + elementIdentifier + " by " + by.toString());
        testStep.addLog("Click on element " + elementIdentifier + " by " + by.toString());
        webDriver.findElement(by).click();
    }

    @Override
    public String getLog() {
        return "Click on element " + elementIdentifier + " by " + by.toString();
    }

    @Override
    public int getParamsCount() {
        return 2;
    }
}
