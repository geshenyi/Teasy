package com.ccorp.poc.mindtest.parser;

import com.ccorp.poc.mindtest.command.ICommand;
import com.ccorp.poc.mindtest.model.webinput.TestScript;
import com.ccorp.poc.mindtest.exception.CommandNotFoundException;
import com.ccorp.poc.mindtest.service.WebSocketService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * Created by ssge on 2015/11/10.
 */
@Component("ScriptParser")
public class ScriptParser implements IParser {

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public List<ICommand> parseScripts(TestScript testScript, Properties commandProps) {
        List<ICommand> commands = Lists.newArrayList();
        testScript.getSteps().stream().forEach(eachScript -> {
                    ICommand command = this.fetchConcreteCommand(eachScript, commandProps);
                    command.constructCommand(eachScript);
                    commands.add(command);
                }
        );
        return commands;
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

    public String replaceContentInQuotes(String text) {
        return text.replaceAll("\".*?\"", "*");
    }

}
