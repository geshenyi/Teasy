package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * Created by ssge on 2015/11/10.
 */
public class WaitCommand implements ICommand {

    public int waitSeconds;

//    @Override
//    public ArrayList<String> extractParams(String script) {
//        ArrayList<String> param = Lists.newArrayList();
//        param.add(script.split(" ")[1]);
//        return param;
//    }

    @Override
    public void constructCommand(String script) {
        ArrayList<String> param = extractParams(script);
        this.waitSeconds = Integer.parseInt(param.get(0));
    }

    @Override
    public void execute(WebDriver webDriver, ScriptExecutionContext context) {
        WebSocketBroadcaster.broadcast(context.getWebSocketService(), "/topic/"+context.getUuid(), "Wait for " + waitSeconds + " seconds...");
        try {
            Thread.currentThread().sleep(this.waitSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
