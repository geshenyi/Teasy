package com.ccorp.poc.mindtest.model.domain.result;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ssge on 2015/11/15.
 */
public class TestStep {
    private String step;
    private List<String> stepLog;
    private String screenShotPath;
    private String exception;
    private boolean hasError;

    public TestStep() {

    }

    public TestStep(String step) {
        this.step = step;
        this.stepLog = Lists.newArrayList();
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public List<String> getStepLog() {
        return stepLog;
    }

    public void setStepLog(List<String> stepLog) {
        this.stepLog = stepLog;
    }

    public String getScreenShotPath() {
        return screenShotPath;
    }

    public void setScreenShotPath(String screenShotPath) {
        this.screenShotPath = screenShotPath;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<String> addLog(String log) {
        this.stepLog.add(log);
        return this.stepLog;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
