package com.nowcoder.community;

import com.nowcoder.community.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class TransactionTests {

    @Autowired
    private HelloService helloService;

    @Test
    public void testSave1() {
        System.out.println(helloService.save1());
    }

    @Test
    public void testSave2() {
        System.out.println(helloService.save2());
    }
}
