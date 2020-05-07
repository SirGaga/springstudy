package com.zhangjie.annotation.service;

import com.zhangjie.annotation.dao.ActorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActorService {
    @Autowired
    private ActorDao actorDao;

    @Transactional
    public void insertActor(){
        actorDao.insert();
        System.out.println("插入完成...");
        int i = 10 / 0;
    }
}
