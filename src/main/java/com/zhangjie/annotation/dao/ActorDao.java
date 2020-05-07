package com.zhangjie.annotation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ActorDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = " insert into actor (first_name,last_name) values(?,?)";
        jdbcTemplate.update(sql,"jingtong","zhang");
    }

}
