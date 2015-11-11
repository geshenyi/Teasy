package com.ccorp.poc.mindtest.command;

import com.ccorp.poc.mindtest.exception.CommandParamNotMatchException;
import com.ccorp.poc.mindtest.model.domain.ScriptExecutionContext;
import com.ccorp.poc.mindtest.utility.PathUtil;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ssge on 2015/11/10.
 */
public interface ICommand {

    public void constructCommand(String script);
    public void execute(WebDriver webDriver, ScriptExecutionContext context);
    public int getParamsCount();
    public default void afterAction(WebDriver webDriver, ScriptExecutionContext context){
        File srcFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(context.getPath()+"/"+this.toString()+".png"));
            context.getWebSocketService().notify("/topic/image/"+context.getUuid(), PathUtil.getLastSegmentInPath(context.getPath())+"/"+this.toString()+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public default ArrayList<String> extractParams(String script){
        ArrayList<String> params = Lists.newArrayList();
        Pattern pattern = Pattern.compile("\".*?\"");
        Matcher matcher = pattern.matcher(script);
        boolean canFind = matcher.find();
        while(canFind){
            params.add(script.substring(matcher.start()+1, matcher.end()-1));
            canFind = matcher.find(matcher.end());
        }
        if(params.size()!=getParamsCount()){
            throw new CommandParamNotMatchException("command param number not match");
        }
        return params;
    }

}
