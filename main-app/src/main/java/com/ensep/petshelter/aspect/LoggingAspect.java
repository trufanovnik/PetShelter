package com.ensep.petshelter.aspect;

import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Array;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    public LoggingAspect() {
        log.info("LoggingAspect initialized!"); // Сообщение должно появиться при старте
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerLog() {}

    @Pointcut("execution(public * com.ensep.petshelter.services.*.*(..))")
    public void serviceLog() {}

    @Before("controllerLog()")
    public void doBeforeControllerLog(JoinPoint jp) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            log.info("NEW REQUEST: IP: {}, URL: {}, HTTP_METHOD: {}, CONTROLLER_METHOD: {}.{}",
                    request.getRemoteAddr(),
                    request.getRequestURL().toString(),
                    request.getMethod(),
                    jp.getSignature().getDeclaringTypeName(),
                    jp.getSignature().getName());
        } catch (IllegalStateException e) {
            log.warn("No HTTP request context available for method: {}.{}",
                    jp.getSignature().getDeclaringTypeName(),
                    jp.getSignature().getName());
        }
    }

    @Before("serviceLog()")
    public void doBeforeServiceLog(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : "METHOD HAS NO ARGUMENTS";

        log.info("RUN SERVICE: SERVICE_METHOD: {}.{}\nMETHOD ARGUMENTS: [{}]", className, methodName, argsString);
    }
}
