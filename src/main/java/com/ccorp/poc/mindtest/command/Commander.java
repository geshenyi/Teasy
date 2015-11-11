package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.WebSocketBroadcaster;
import com.ccorp.poc.mindtest.model.webinput.TestScript;
import com.ccorp.poc.mindtest.parser.ScriptParser;
import com.ccorp.poc.mindtest.service.WebSocketService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Created by ssge on 2015/11/10.
 */
@Component
public class Commander {

    @Autowired
    private WebSocketService webSocketService;


    public void dictate(String plainScript, Properties commandProps, String uuid, String path) {
        ScriptExecutionContext context = new ScriptExecutionContext(webSocketService, path, uuid);
        WebSocketBroadcaster.broadcast(webSocketService, "/topic/" + uuid, "Parsing scripts...");
        TestScript testScript = new TestScript(convertPlainScriptToList(plainScript));
        ScriptParser scriptParser = new ScriptParser();
        List<ICommand> commands = scriptParser.parseScripts(testScript, commandProps);
        WebSocketBroadcaster.broadcast(webSocketService, "/topic/" + uuid, "About to start...");

        WebDriver webDriver = new ChromeDriver();
        WebSocketBroadcaster.broadcast(webSocketService, "/topic/" + uuid, "Webdriver created...");
        try {
            commands.stream().forEach(command -> {
                command.execute(webDriver, context);
                command.afterAction(webDriver, context);
            });
        } finally {
            webDriver.close();
        }

    }

    private List<String> convertPlainScriptToList(String plainScript) {
        List<String> scripts = Lists.newArrayList();
        String[] scriptArray = plainScript.split("\n");
        Stream.of(scriptArray).filter(script -> !Strings.isNullOrEmpty(script)).forEach(script -> scripts.add(script));
        return scripts;
    }
}
