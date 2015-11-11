package com.ccorp.poc.mindtest.utility;

/**
 * Created by ssge on 2015/11/11.
 */
public class PathUtil {
    public static String getLastSegmentInPath(String path){
        return path.substring(path.lastIndexOf("\\")+1, path.length());
    }
}
