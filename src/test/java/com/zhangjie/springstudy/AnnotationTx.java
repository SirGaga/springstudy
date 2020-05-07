package com.zhangjie.springstudy;

import com.zhangjie.annotation.service.ActorService;
import com.zhangjie.annotation.tx.TxConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationTx {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        ActorService actorService = applicationContext.getBean(ActorService.class);
        actorService.insertActor();
        applicationContext.close();

    }
}