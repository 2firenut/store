package com.firenut.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 用于统计业务方法执行的时长
 */
@Aspect
@Component
public class TimerAspect {

    @Around("execution(* com.firenut.store.service.Impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//    记录起始的时间
        long start=System.currentTimeMillis();

//        执行连接点方法
        Object result=pjp.proceed();

//        记录结束的时间
        long end=System.currentTimeMillis();

        System.out.println("耗时"+(end-start));

        return result;
    }

}
