package com.zhangjie.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 切面类
 * @Aspect：告诉Spring当前类是一个切面类
 */
@Aspect
public class LogAspect {
    //抽取公共的切入点表达式
    //1.本类引用
    //2.其他的切面引用
    @Pointcut("execution(public int com.zhangjie.annotation.condition.MathCalculator.*(..))")
    public void pointCut(){

    }

    //在目标方法之前切入，切入点表达式（指定在哪个方法切入）
    //@Before("public int com.zhangjie.annotation.condition.MathCalculator.div(int,int)")
    //@Before("pointCut()")
    @Before("com.zhangjie.annotation.aop.LogAspect.pointCut()")
    public  void logStart(JoinPoint jointPoint){
        System.out.println(jointPoint.getSignature().getDeclaringType()+"."+jointPoint.getSignature().getName()+"运行...参数列表是:"+ Arrays.asList(jointPoint.getArgs()));
    }

    //在目标方法运行结束之后运行，无论是正常结束还是异常结束都运行
    @After("pointCut()")
    public void logEnd(){
        System.out.println("除法结束...");
    }

    @AfterReturning(value="pointCut()",returning="result")
    public void logReturn(Object result){
        System.out.println("除法正常结束...运行结果:"+result);
    }

    @AfterThrowing(value = "pointCut()",throwing="exception")
    public void logException(Exception exception){
        System.out.println("除法异常...异常信息:"+exception);
    }
}
