package com.fpnn.sdk;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shiwangxing on 2017/11/29.
 */
public class ErrorRecorder implements ErrorRecorderInterface {

    //-----------------[ Static Properties & Methods ]-------------------
    private static ErrorRecorderInterface instance = new ErrorRecorder();
    public static ErrorRecorderInterface getInstance() {
        return instance;
    }
    public static void setErrorRecorder(ErrorRecorderInterface ins) {
        instance = ins;
    }

    public static void record(String message) {
        instance.recordError(message);
    }

    public static void record(Exception e) {
        instance.recordError(e);
    }

    public static void record(String message, Exception e) {
        instance.recordError(message, e);
    }

    //-----------------[ Properties ]-------------------
    private LinkedList<String> cache;

    public ErrorRecorder() {
        cache = new LinkedList<>();
    }

    private void addCache(String message){
        if (cache.size() >= 200)
            cache.removeLast();
        cache.add(message);
    }
    public void recordError(String message) {
        addCache(message);
    }
    public void recordError(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        addCache(sw.toString());
    }
    public void recordError(String message, Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        addCache(message + " Error Stack: " + sw.toString());
    }

    public void clear() {
        cache.clear();
    }

    public void println() {
        for (String str: cache)
        {
            System.out.println(str);
        }
    }
}
