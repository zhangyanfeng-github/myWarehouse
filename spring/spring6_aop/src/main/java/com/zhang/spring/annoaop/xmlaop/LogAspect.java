package com.zhang.spring.annoaop.xmlaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//切面类
@Component  //告知Spring，IoC容器
public class LogAspect {

    //设置切入点和通知类型
    //通知类型：
    // 前置通知
    public void beforeMethod(JoinPoint joinPoint) {     //加上这个参数，可以获取方法名和参数值
        String methodName = joinPoint.getSignature().getName();     //获取
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->前置通知，方法名称：" + methodName + "，参数：" + Arrays.toString(args));
    }

    // 后置通知
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->后置通知，方法名称：" + methodName + "，参数：" + Arrays.toString(args));
    }

    // 返回通知
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->返回通知，方法名称：" + methodName + "，参数：" + Arrays.toString(args) + "，返回值：" + result);
    }

    // 异常通知
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable errer) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->返回通知，方法名称：" + methodName + "，参数：" + Arrays.toString(args) + "，异常：" + errer);
    }

    // 环绕通知
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argString = Arrays.toString(args);
        Object result = null;
        try {
            System.out.println("环绕通知>>>目标方法之前执行");

            //调用目标方法
            result = joinPoint.proceed();

            System.out.println("环绕通知>>>目标方法返回之后执行");
        } catch (Throwable throwable) {

            throwable.printStackTrace();
            System.out.println("环绕通知>>>目标方法出现异常执行");

        } finally {
            System.out.println("环绕通知>>>目标方法执行完毕执行");
        }
        return result;
    }


    //重用切入点表达式
    @Pointcut(value = "execution(public int com.zhang.spring.annoaop.xmlaop.CalculatorImpl.*(..)))")
    public void pointCut() {
    }
}
