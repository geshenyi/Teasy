package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import com.ccorp.poc.mindtest.model.domain.result.TestResult;
import com.ccorp.poc.mindtest.model.webinput.TestScript;
import com.ccorp.poc.mindtest.parser.ScriptParser;
import com.ccorp.poc.mindtest.service.FlowService;
import com.ccorp.poc.mindtest.service.WebSocketService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * Created by ssge on 2015/11/10.
 */
@Component
public class Commander {

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private FlowService flowService;

    @Autowired
    private ScriptParser parser;

    public TestResult dictate(String plainScript, Properties commandProps, String uuid, String path) {
        ScriptExecutionContext context = new ScriptExecutionContext(flowService, webSocketService, path, uuid);
        WebSocketBroadcaster.broadcast(webSocketService, "/topic/" + uuid, "Parsing scripts...");
//        ScriptParser scriptParser = new ScriptParser();
        TestScript testScript = new TestScript(parser.convertPlainScriptToList(plainScript));
        List<ICommand> commands = parser.parseScripts(testScript, commandProps);
        WebSocketBroadcaster.broadcast(webSocketService, "/topic/" + uuid, "About to start...");
        TestResult result = this.initTestResult(testScript, commands, context);
        WebDriver webDriver = new ChromeDriver();
        WebSocketBroadcaster.broadcast(webSocketService, "/topic/" + uuid, "Webdriver created...");
        try {
            commands.stream().forEach(command -> {
                    command.execute(webDriver, context, result);
            });
            result.setStatus("success");
        } catch(Exception e){
            e.printStackTrace();
            result.setException(ExceptionUtils.getStackTrace(e));
            webSocketService.notify("/topic/"+uuid, ExceptionUtils.getStackTrace(e));
        }finally {
            webDriver.quit();
            return result;
        }

    }

    private TestResult initTestResult(TestScript testScript, List<ICommand> commands, ScriptExecutionContext context) {
        TestResult testResult = new TestResult(testScript, commands, context);
        return testResult;
    }
}
