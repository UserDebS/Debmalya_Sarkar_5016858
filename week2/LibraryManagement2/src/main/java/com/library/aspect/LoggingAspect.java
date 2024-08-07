package com.library.aspect;


public class LoggingAspect {
    // Q: Define advice methods in LoggingAspect for logging before and after method execution.
    public void loggingBefore() {
        System.out.println("Method execution started");
    }

    public void loggingAfter() {
        System.out.println("Method execution ended");
    }
}
