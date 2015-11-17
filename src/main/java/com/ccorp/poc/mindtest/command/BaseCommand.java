package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.exception.ExecutionErrorException;
import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.domain.result.TestResult;
import com.ccorp.poc.mindtest.model.domain.result.TestStep;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;

/**
 * Created by ssge on 2015/11/15.
 */
public abstract class BaseCommand implements ICommand{

    private String script;

    @Override
    public void constructCommand(String script){
        this.script = script;
        constructSpecificCommand(script);
    }

    @Override
    public String getScript(){
        return this.script;
    }

    protected abstract void constructSpecificCommand(String script);

    @Override

    public void execute(WebDriver webDriver, ScriptExecutionContext context, TestResult result){
        TestStep testStep = new TestStep(this.getScript());
        try {
            executeSpecificCommand(webDriver, context, testStep);
            String screenShotpath = this.takeSnapShot(webDriver, context);
            testStep.setScreenShotPath(screenShotpath);
            testStep.setHasError(false);
            result.updateResultForOneStep(testStep);
        }catch(Exception e){
            e.printStackTrace();
            String screenShotpath = this.takeSnapShot(webDriver, context);
            testStep.setScreenShotPath(screenShotpath);
            testStep.setException(ExceptionUtils.getStackTrace(e));
            testStep.setHasError(true);
            result.setStatus("fail");
            result.updateResultForOneStep(testStep);
            throw new ExecutionErrorException(e);
        }


    }


    protected abstract void executeSpecificCommand(WebDriver webDriver, ScriptExecutionContext context, TestStep testStep);

    @Override
    abstract public int getParamsCount();
}
