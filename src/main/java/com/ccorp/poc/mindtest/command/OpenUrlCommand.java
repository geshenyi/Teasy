package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * Created by ssge on 2015/11/10.
 */
public class OpenUrlCommand implements ICommand{

    private String url;

    @Override
    public void constructCommand(String script) {
        ArrayList<String> params = this.extractParams(script);
        this.url = params.get(0);
    }

    @Override
    public void execute(WebDriver webDriver, ScriptExecutionContext context) {
        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "open web page " + url);
        webDriver.get(url);
    }

    @Override
    public int getParamsCount() {
        return 1;
    }


}
