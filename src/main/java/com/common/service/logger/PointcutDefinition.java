package com.common.service.logger;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by root on 11/15/16.
 */
@Aspect
@Component
public class PointcutDefinition {
 /*   @Pointcut("execution(* *(..)) && within(com.common.controller.*)")
    public void controllerLayer() {
    }


    @Before("controllerLayer()")
    public void beforeAccountMethodExecution(JoinPoint joinPoint) {
   //     System.out.println("Logging account access. Account: ");
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        User principal;
        try {
            principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Date timestamp = new Date(new java.util.Date().getTime());
            if (principal.getUsername() != null) {
                for (Object o : args) {
                    Boolean doInspect = true;
                    if (o instanceof ServletRequestDataBinder) doInspect = false;
                    if (o instanceof ExtendedModelMap) doInspect = false;
                    if (doInspect) {
                        System.out.print("username "+principal.getUsername());
                        System.out.print("  time  "+timestamp);
                      //  System.out.println(joinPoint.getStaticPart().getSignature().getName());
                        try {
                            System.out.println("  method  "+joinPoint.getSignature().getName());
                            for(int i=0;i<joinPoint.getArgs().length;i++){
                                System.out.println("args  "+joinPoint.getArgs()[i]+"   "+joinPoint.getArgs()[i].getClass().getName());
                            }
                            for(int i=0;i<joinPoint.getThis().getClass().getDeclaredMethods().length;i++){
                                //System.out.println("decl methods  "+joinPoint.getThis().getClass().getDeclaredMethods()[i]);
                                for(int j=0;j<joinPoint.getThis().getClass().getDeclaredMethods()[i].getTypeParameters().length;j++){
                                    System.out.println("decl methods  "+joinPoint.getThis().getClass().getDeclaredMethods()[i].getTypeParameters()[j]+"    "+joinPoint.getThis().getClass().getDeclaredMethods()[i].getTypeParameters()[j].getName());
                                }
                            }
                            System.out.println();
                        } catch (Exception e) {

                        }
                    }
                }
            }
        } catch (Exception e) {
        }




    }*/
}
