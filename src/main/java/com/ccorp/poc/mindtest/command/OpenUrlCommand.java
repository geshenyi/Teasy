package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import com.ccorp.poc.mindtest.model.domain.result.TestStep;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * Created by ssge on 2015/11/10.
 */
public class OpenUrlCommand extends BaseCommand{

    private String url;

//    @Override
//    public void constructCommand(String script) {
//        ArrayList<String> params = this.extractParams(script);
//        this.url = params.get(0);
//    }

    @Override
    protected void constructSpecificCommand(String script) {
        ArrayList<String> params = this.extractParams(script);
        this.url = params.get(0);
    }

//    @Override
//    public void execute(WebDriver webDriver, ScriptExecutionContext context) {
//        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "open web page " + url);
//        webDriver.get(url);
//    }

    @Override
    protected void executeSpecificCommand(WebDriver webDriver, ScriptExecutionContext context, TestStep testStep) throws InterruptedException {
        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "open web page " + url);
        testStep.addLog("open web page " + url);
        webDriver.get(url);
        webDriver.manage().window().maximize();
//        Thread.currentThread().sleep(2000);
    }

    @Override
    public String getLog() {
        return "open web page " + url;
    }

    @Override
    public int getParamsCount() {
        return 1;
    }


}
