package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import com.ccorp.poc.mindtest.utility.ByFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

/**
 * Created by ssge on 2015/11/10.
 */
public class HoverOverCommand implements ICommand {

    private String elementIdentifier;
    private By by;

    @Override
    public void constructCommand(String script) {
        ArrayList<String> params = this.extractParams(script);
        this.elementIdentifier = params.get(0);
        by = ByFactory.buildBy(params.get(0), params.get(1));
    }

    @Override
    public void execute(WebDriver webDriver, ScriptExecutionContext context) {
        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "Mouse over on element " + elementIdentifier + " by " + by.toString());
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(by)).build().perform();
    }

    @Override
    public int getParamsCount() {
        return 2;
    }
}
