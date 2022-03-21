package com.nowcoder.community;

import com.nowcoder.community.dao.*;
import com.nowcoder.community.entity.*;
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

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    MessageMapper messageMapper;

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

    @Test
    public void testInsertDiscussPost() {
        DiscussPost post = new DiscussPost();
        post.setUserId(151);
        post.setTitle("aaaaa");
        post.setContent("aaa");
        post.setCreateTime(new Date());

        discussPostMapper.insertDiscussPost(post);
    }

    @Test
    public void selectCommentsByEntity() {
        List<Comment> commentList = commentMapper.selectCommentsByEntity(1, 228, 0, Integer.MAX_VALUE);
        for (Comment comment : commentList) {
            System.out.println(comment);
        }
    }

    @Test
    public void selectMessages() {
        List<Message> conversations = messageMapper.selectConversations(111, 0, Integer.MAX_VALUE);
        for (Message conversation : conversations) {
            System.out.println(conversation);
        }

        int conversationCount = messageMapper.selectConversationCount(111);
        System.out.println(conversationCount);

        List<Message> letters = messageMapper.selectLetters("111_112", 0, Integer.MAX_VALUE);
        for (Message letter : letters) {
            System.out.println(letter);
        }

        int letterCount = messageMapper.selectLetterCount("111_112");
        System.out.println(letterCount);

        int unreadCount = messageMapper.selectLetterUnreadCount(131, "111_131");
        System.out.println(unreadCount);
    }

    @Test
    public void updatePost() {
        discussPostMapper.updateType(289, 1);
    }
}
