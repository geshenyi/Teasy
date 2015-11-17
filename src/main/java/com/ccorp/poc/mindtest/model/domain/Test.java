package com.ccorp.poc.mindtest.model.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by ssge on 2015/11/13.
 */
public class Test {

    @Id
    private String id;
    private String owner;
    private String name;
    private String script;

    public Test(){

    }

    public Test(String id, String script, String owner, String name) {
        this.id = id;
        this.script = script;
        this.owner = owner;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
