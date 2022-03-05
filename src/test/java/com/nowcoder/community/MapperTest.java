package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DiscussPostMapper discussPostMapper;

    @Autowired
    LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(1);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder24@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "hello");
        System.out.println(rows);
    }

    @Test
    public void selectDiscussPosts() {
        List<DiscussPost> posts = discussPostMapper.selectDiscussPosts(0, 0, 5);
        for (DiscussPost post : posts) {
            System.out.println(post);
        }
    }

    @Test
    public void selectDiscussPostRows() {
        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    @Test
    public void testLoginTicket() {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(150);
        ticket.setTicket(CommunityUtil.generateUUID());
        ticket.setStatus(1);
        ticket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        String s = "a41f0ed0060a4f1e8becc9da472ee550";

        loginTicketMapper.insertLoginTicket(ticket);
//        System.out.println(loginTicketMapper.selectByTicket(s));
//        loginTicketMapper.updateStatus(s, 0);
    }
}
