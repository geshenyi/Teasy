//package com.ccorp.poc.mindtest.command;
//
//import com.ccorp.poc.mindtest.model.domain.Flow;
//import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
//import com.ccorp.poc.mindtest.model.domain.result.TestStep;
//import com.ccorp.poc.mindtest.model.webinput.TestScript;
//import com.ccorp.poc.mindtest.parser.ScriptParser;
//import org.openqa.selenium.WebDriver;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by ssge on 2015/11/24.
// */
//public class ApplyCommand extends BaseCommand{
//
//    private String flowName;
//
//    @Override
//    protected void constructSpecificCommand(String script) {
//        ArrayList<String> params = this.extractParams(script);
//        this.flowName = params.get(0);
//    }
//
//    @Override
//    protected void executeSpecificCommand(WebDriver webDriver, ScriptExecutionContext context, TestStep testStep) {
//        Flow applyFlow = fetchFlow(context);
//        ScriptParser scriptParser = new ScriptParser();
//        TestScript testScript = new TestScripot(convertPlainScriptToList(plainScript));
//        List<ICommand> commands = scriptParser.parseScripts(testScript, commandProps);
//    }
//
//    @Override
//    public String getLog() {
//        return null;
//    }
//
//    @Override
//    public int getParamsCount() {
//        return 1;
//    }
//
//    private Flow fetchFlow(ScriptExecutionContext context){
//        return context.getFlowService().fetchFlowByName(this.flowName);
//    }
//}
