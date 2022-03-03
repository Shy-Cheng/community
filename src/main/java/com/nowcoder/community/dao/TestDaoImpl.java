package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao{

    @Override
    public String test() {
        return "TestDaoImpl method";
    }
}
