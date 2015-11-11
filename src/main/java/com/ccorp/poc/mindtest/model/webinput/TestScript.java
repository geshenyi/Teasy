package com.ccorp.poc.mindtest.model.webinput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssge on 2015/11/10.
 */
public class TestScript {
    private List<String> steps = new ArrayList<>();

    public TestScript(List<String> steps) {
        this.steps = steps;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
