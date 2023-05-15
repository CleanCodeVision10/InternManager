package com.alpha.interns.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect
{

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.infy.infyinterns.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(Exception exception)
    {
        LOGGER.error(exception.getMessage());
    }

}
