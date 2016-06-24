package cn.cjam.web.interceptors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 打印 ISyncInfo输入参数 方便调试
 * Created by jam on 2016/5/31.
 */
@Aspect
@Service
public class PrintParameterIntercept {

    private static final Logger logger = LoggerFactory.getLogger(PrintParameterIntercept.class);

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* cn.cjam..*(..))")
    public void aspectPrintParameter(){	}

    @Before("aspectPrintParameter()")
    public void checkBefore(JoinPoint joinPoint){
        String format = String.format("jam|PrintParameter|threadId:{}|name:{}", Thread.currentThread().getId(), joinPoint.getSignature().getName());
        logger.info(format);
    }
}

