package com.common.service.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by root on 11/15/16.
 */
@Aspect
@Component
//@EnableAspectJAutoProxy
public class ControllerLogger {

    private  final static Logger LOG = Logger.getLogger(ControllerLogger.class);
//    @Before(value = "com.common.service.logger.PointcutDefinition.controllerLayer()")
//    public void beforeAccountMethodExecution() {
//        System.out.println("Logging account access. Account: ");
//    }

//    @Around("execution(* com.common.controller.BasicController.*(..)) ")
//    public void advice() {
//        System.out.println("qwe");
//    }
//    @Around("execution(* *(..)) && within(com.common.service.DeleteFile.*)")
//    public void someFromService() {
//        System.out.println("qweewq12312312312");
//    }

//
//    @Before("execution(* com.common.controller.BasicController.index(..))")
//    public void iindex() {
//        System.out.println("qwe");
//    }

//    @Before(value = "com.common.controller.BasicController.*()")
//    public void before(JoinPoint jp) {
//        System.out.println("Logging account access. Account: ");
//    }

}
