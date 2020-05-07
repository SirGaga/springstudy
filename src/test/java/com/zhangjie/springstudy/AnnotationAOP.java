package com.zhangjie.springstudy;

import com.zhangjie.annotation.condition.MathCalculator;
import com.zhangjie.annotation.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationAOP {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator calculator = applicationContext.getBean(MathCalculator.class);
        calculator.div(1,1);

    }
}
