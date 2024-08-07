package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

//Q: Create a package com.library.aspect and add a class LoggingAspect with a method to log execution times.
public class LoggingAspect {
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + duration + "ms");
        return proceed;
    }
}
