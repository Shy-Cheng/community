package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("1348656979@qq.com", "测试主题", "<h1>测试内容</h1>");
        System.out.println("发送成功");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("name", "ShayneC");
        String content = templateEngine.process("/demo/testMail", context);
        System.out.println(content);
        mailClient.sendMail("1348656979@qq.com", "html测试", content);
    }
}
