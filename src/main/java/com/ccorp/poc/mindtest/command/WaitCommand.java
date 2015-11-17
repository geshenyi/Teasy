package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import com.ccorp.poc.mindtest.model.domain.result.TestStep;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * Created by ssge on 2015/11/10.
 */
public class WaitCommand extends BaseCommand {

    public int waitSeconds;

//    @Override
//    public ArrayList<String> extractParams(String script) {
//        ArrayList<String> param = Lists.newArrayList();
//        param.add(script.split(" ")[1]);
//        return param;
//    }

//    @Override
//    public void constructCommand(String script) {
//        ArrayList<String> param = extractParams(script);
//        this.waitSeconds = Integer.parseInt(param.get(0));
//    }

    @Override
    protected void constructSpecificCommand(String script) {
        ArrayList<String> param = extractParams(script);
        this.waitSeconds = Integer.parseInt(param.get(0));
    }

//    @Override
//    public void execute(WebDriver webDriver, ScriptExecutionContext context) {
//        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "Wait for " + waitSeconds + " seconds...");
//        try {
//            Thread.currentThread().sleep(this.waitSeconds * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void executeSpecificCommand(WebDriver webDriver, ScriptExecutionContext context, TestStep testStep) {
        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "Wait for " + waitSeconds + " seconds...");
        testStep.addLog("Wait for " + waitSeconds + " seconds...");
        try {
            Thread.currentThread().sleep(this.waitSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLog() {
        return "Wait for " + waitSeconds + " seconds...";
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
