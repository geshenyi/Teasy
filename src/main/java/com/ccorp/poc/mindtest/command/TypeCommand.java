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
public class TypeCommand extends BaseCommand{

    private String text;
    private String elementIdentifier;
    private By by;

//    @Override
//    public void constructCommand(String script) {
//        ArrayList<String> params = this.extractParams(script);
//        this.text = params.get(0);
//        this.elementIdentifier = params.get(1);
//        by = ByFactory.buildBy(elementIdentifier, params.get(2));
//    }

    @Override
    protected void constructSpecificCommand(String script) {
        ArrayList<String> params = this.extractParams(script);
        this.text = params.get(0);
        this.elementIdentifier = params.get(1);
        by = ByFactory.buildBy(elementIdentifier, params.get(2));
    }

//    @Override
//    public void execute(WebDriver webDriver, ScriptExecutionContext context) {
//        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "Type " + text + " into element " + elementIdentifier + " by " + by.toString());
//        webDriver.findElement(by).sendKeys(text);
//    }

    @Override
    protected void executeSpecificCommand(WebDriver webDriver, ScriptExecutionContext context, TestStep testStep) {
        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "Type " + text + " into element " + elementIdentifier + " by " + by.toString());
        testStep.addLog("Type " + text + " into element " + elementIdentifier + " by " + by.toString());
        webDriver.findElement(by).sendKeys(text);
    }

    @Override
    public String getLog() {
        return "Type " + text + " into element " + elementIdentifier + " by " + by.toString();
    }

    @Override
    public int getParamsCount() {
        return 3;
    }
}
