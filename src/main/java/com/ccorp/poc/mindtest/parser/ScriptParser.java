package com.ccorp.poc.mindtest.parser;

import com.ccorp.poc.mindtest.command.ICommand;
import com.ccorp.poc.mindtest.exception.CommandNotFoundException;
import com.ccorp.poc.mindtest.model.domain.Flow;
import com.ccorp.poc.mindtest.model.webinput.TestScript;
import com.ccorp.poc.mindtest.service.FlowService;
import com.ccorp.poc.mindtest.service.WebSocketService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by ssge on 2015/11/10.
 */
@Component("ScriptParser")
public class ScriptParser implements IParser {

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private FlowService flowService;

    @Override
    public List<ICommand> parseScripts(TestScript testScript, Properties commandProps) {
        List<String> processedScript = Lists.newArrayList();
        parseFlow(testScript.getSteps(), processedScript);
        testScript.setSteps(processedScript);
        List<ICommand> commands = Lists.newArrayList();
        testScript.getSteps().stream().forEach(eachScript -> {
                    ICommand command = this.fetchConcreteCommand(eachScript, commandProps);
                    command.constructCommand(eachScript);
                    commands.add(command);
                }
        );
        return commands;
    }

    private void parseFlow(List<String> steps, List<String> processedScript) {
        for (int i = 0, s = steps.size(); i < s; i++) {
            if(steps.get(i).startsWith("apply")){
                Flow flow = fetchSpecificFlow(extractParams(steps.get(i)).get(0));
                parseFlow(convertPlainScriptToList(flow.getScript()), processedScript);
            }else{
                processedScript.add(steps.get(i));
            }
        }
    }

    private Flow fetchSpecificFlow(String flowName){
        return flowService.fetchFlowByName(flowName);
    }

    public List<ICommand> parseScripts(String plainScript, Properties commandProps) {
        TestScript testScript = new TestScript(convertPlainScriptToList(plainScript));
        return this.parseScripts(testScript, commandProps);
    }


    private ICommand fetchConcreteCommand(String eachScript, Properties commandProps) {
        ICommand command = null;
        String commandClass = (String) commandProps.get(replaceContentInQuotes(eachScript));
        if (Strings.isNullOrEmpty(commandClass)) {
            throw new CommandNotFoundException("Has this command " + eachScript + " been configured?");
        }
        try {
            command = (ICommand) Class.forName(commandClass).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new CommandNotFoundException("Cannot find command class or get error with instantiation", e);
        }
        return command;
    }

    public  ArrayList<String> extractParams(String script){
        ArrayList<String> params = Lists.newArrayList();
        Pattern pattern = Pattern.compile("\".*?\"");
        Matcher matcher = pattern.matcher(script);
        boolean canFind = matcher.find();
        while(canFind){
            params.add(script.substring(matcher.start()+1, matcher.end()-1));
            canFind = matcher.find(matcher.end());
        }
        return params;
    }

    public String replaceContentInQuotes(String text) {
        return text.replaceAll("\".*?\"", "*");
    }

    public List<String> convertPlainScriptToList(String plainScript) {
        List<String> scripts = Lists.newArrayList();
        String[] scriptArray = plainScript.split("\n");
        Stream.of(scriptArray).filter(script -> !Strings.isNullOrEmpty(script)).forEach(script -> scripts.add(script));
        return scripts;
    }

}
