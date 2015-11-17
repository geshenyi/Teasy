package com.ccorp.poc.mindtest.model.domain.result;

/**
 * Created by ssge on 2015/11/15.
 */
public class TestStatistic {
    private long totalSteps = 0;
    private long executedSteps = 0;
    private long totalVerification = 0;
    private long passedVerificatin = 0;
    private long failedVerification = 0;
    private long skippedVerification = 0;

    public TestStatistic() {

    }

    public TestStatistic(long totalSteps, long executedSteps, long totalVerification, long passedVerificatin,
                         long failedVerification, long skippedVerification) {
        this.totalSteps = totalSteps;
        this.executedSteps = executedSteps;
        this.totalVerification = totalVerification;
        this.passedVerificatin = passedVerificatin;
        this.failedVerification = failedVerification;
        this.skippedVerification = skippedVerification;
    }

    public TestStatistic withTotalSteps(long totalSteps) {
        this.setTotalSteps(totalSteps);
        return this;
    }

    public TestStatistic withTotalVerification(long totalVerification) {
        this.setTotalVerification(totalVerification);
        return this;
    }

    public void updateForOneStep(TestStep testStep) {
        this.executedSteps += 1;
        if(testStep.getStep().startsWith("verify")){
            if(testStep.isHasError()){
                this.failedVerification += 1;
            }else{
                this.passedVerificatin += 1;
            }
        }
    }

    public long getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(long totalSteps) {
        this.totalSteps = totalSteps;
    }

    public long getExecutedSteps() {
        return executedSteps;
    }

    public void setExecutedSteps(long executedSteps) {
        this.executedSteps = executedSteps;
    }

    public long getTotalVerification() {
        return totalVerification;
    }

    public void setTotalVerification(long totalVerification) {
        this.totalVerification = totalVerification;
    }

    public long getPassedVerificatin() {
        return passedVerificatin;
    }

    public void setPassedVerificatin(long passedVerificatin) {
        this.passedVerificatin = passedVerificatin;
    }

    public long getFailedVerification() {
        return failedVerification;
    }

    public void setFailedVerification(long failedVerification) {
        this.failedVerification = failedVerification;
    }

    public long getSkippedVerification() {
        return skippedVerification;
    }

    public void setSkippedVerification(long skippedVerification) {
        this.skippedVerification = skippedVerification;
    }


}
