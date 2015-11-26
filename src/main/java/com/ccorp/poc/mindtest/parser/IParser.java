package com.ccorp.poc.mindtest.parser;

import com.ccorp.poc.mindtest.command.ICommand;
import com.ccorp.poc.mindtest.model.webinput.TestScript;

import java.util.List;
import java.util.Properties;

/**
 * Created by ssge on 2015/11/10.
 */
public interface IParser {
    public List<ICommand> parseScripts(TestScript plainScript, Properties properties) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
