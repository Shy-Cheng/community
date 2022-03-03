package com.nowcoder.community.service;

import com.nowcoder.community.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestDao testDao;

    @Override
    public String testService() {
        return testDao.test();
    }
}
