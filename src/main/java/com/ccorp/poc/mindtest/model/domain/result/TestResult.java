package com.ccorp.poc.mindtest.model.domain.result;

import com.ccorp.poc.mindtest.command.ICommand;
import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.model.webinput.TestScript;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ssge on 2015/11/15.
 */
public class TestResult {
    private String status = "fail";
    private String exception;
    private List<String> steps;
    private TestStatistic statistic;
    private List<TestStep> executedSteps;
    private List<TestStep> executedVerification;
    private ScriptExecutionContext context;

    public TestResult() {

    }

    public TestResult(TestScript testScript, List<ICommand> commands, ScriptExecutionContext context) {
        this.context = context;
        this.steps = testScript.getSteps();
        this.statistic = new TestStatistic().withTotalSteps(this.steps.size())
                .withTotalVerification(this.steps.stream().filter(step -> step.startsWith("verify")).count());
        executedSteps = Lists.newArrayList();
        executedVerification = Lists.newArrayList();

    }

    public TestResult updateResultForOneStep(TestStep testStep){
        executedSteps.add(testStep);
        if(testStep.getStep().startsWith("verify")){
            executedVerification.add(testStep);
        }
        this.statistic.updateForOneStep(testStep);

        return this;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public TestStatistic getStatistic() {
        return statistic;
    }

    public void setStatistic(TestStatistic statistic) {
        this.statistic = statistic;
    }

    public List<TestStep> getExecutedSteps() {
        return executedSteps;
    }

    public void setExecutedSteps(List<TestStep> executedSteps) {
        this.executedSteps = executedSteps;
    }

    public List<TestStep> getExecutedVerification() {
        return executedVerification;
    }

    public void setExecutedVerification(List<TestStep> executedVerification) {
        this.executedVerification = executedVerification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
