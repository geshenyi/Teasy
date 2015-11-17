package com.ccorp.poc.mindtest.parser;

import com.ccorp.poc.mindtest.command.ICommand;
import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.result.TestResult;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by ssge on 2015/11/10.
 */

public class ParserTest {

    public ScriptParser scriptParser = new ScriptParser();

    @Test
    public void testRegexpReplace() {

        String replaced = scriptParser.replaceContentInQuotes("open url \"www.stubhub.com\"");
        Assert.assertEquals(replaced, "open url *");
    }

    @Test
    public void testReplaceMultipleParams() {
        String replaced = scriptParser.replaceContentInQuotes("type \"slce002_q5@tixqa.com\" to \"email-id\" by \"id\"");
        Assert.assertEquals(replaced, "type * to * by *");
    }

    @Test
    public void testICommandExtractParams() {
        ICommand command = new ICommand() {
            @Override
            public String getScript() {
                return null;
            }

            @Override
            public String getLog() {
                return null;
            }

            @Override
            public void constructCommand(String script) {

            }

            @Override
            public void execute(WebDriver webDriver, ScriptExecutionContext context, TestResult testResult) {

            }


            @Override
            public int getParamsCount() {
                return 0;
            }
        };
        ArrayList<String> params = command.extractParams("open url \"http://www.stubcorp.com\"");
        Assert.assertEquals(params, Lists.newArrayList("http://www.stubcorp.com"));
    }

    @Test
    public void testICommandExtractMultipleParams(){
        ICommand command = new ICommand() {
            @Override
            public String getScript() {
                return null;
            }

            @Override
            public String getLog() {
                return null;
            }

            @Override
            public void constructCommand(String script) {

            }

            @Override
            public void execute(WebDriver webDriver, ScriptExecutionContext context, TestResult testResult) {

            }

//            @Override
//            public void execute(WebDriver webDriver, ScriptExecutionContext context) {
//
//            }

            @Override
            public int getParamsCount() {
                return 0;
            }
        };
        ArrayList<String> params = command.extractParams("type \"slce002_q5@tixqa.com\" to \"email-id\" by \"id\"");
        Assert.assertEquals(params, Lists.newArrayList("slce002_q5@tixqa.com","email-id","id"));
    }

    @Test
    public void testContextPath(){
        String path = "C:\\Users\\ssge\\Documents\\GitHub\\MindTest\\build\\classes\\artifacts\\MindTest_war_exploded\\screenshot\\d0fc69d7-bfdb-48b9-b040-7ea8835ef269";
        System.out.println(path.substring(path.lastIndexOf("\\")+1, path.length()));
    }
}
