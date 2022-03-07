package com.nowcoder.community.controller;

import com.nowcoder.community.service.TestService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
//@Controller
public class HelloController {

    @Autowired
    TestService testService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello, springboot";
    }

    @RequestMapping("/test")
    public String test() {
        String testService = this.testService.testService();
        return testService;
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + "=" + value);
        }
        System.out.println(request.getParameter("message"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET
    //  /student?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "students list";
    }

    @RequestMapping(path = "/students2/current={current}/limit={limit}" , method = RequestMethod.GET)
    public String getStudents2(
            @PathVariable(name = "current", required = false) int current,
            @PathVariable(name = "limit", required = false) int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "students2 list";
    }

    @RequestMapping(path = "/studentInfo", method = RequestMethod.POST)
    public String getStudentInfo(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "提交成功";
    }

    //响应html数据
    @RequestMapping(path = "/teacherInfo", method = RequestMethod.GET)
    public ModelAndView getTeacherInfo() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "张三");
        mv.addObject("age", "30");
        mv.setViewName("/demo/teacherInfo");
        return mv;
    }

    @RequestMapping(path = "/teacherInfo2", method = RequestMethod.GET)
    public String getTeacherInfo2(Model model) {
        ModelAndView mv = new ModelAndView();
        model.addAttribute("name", "李四");
        model.addAttribute("age", "40");
        return "/demo/teacherInfo";
    }

    //响应json数据
    @RequestMapping(path = "/jsonData", method = RequestMethod.GET)
    public Map<String, Object> jsonData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("salary", 8000);
        return map;
    }

    // cookie示例
    @GetMapping("/cookie/set")
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的范围
        cookie.setPath("/community/cookie");
        // 设置cookie的生存时间
        cookie.setMaxAge(60 * 5);
        // 发送cookie
        response.addCookie(cookie);
        return "set cookie";
    }

    @GetMapping("/cookie/get")
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie" + "->" + code;
    }

    // session示例
    @GetMapping("/session/set")
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "test");
        return "set session";
    }

    @GetMapping("/session/get")
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

    // ajax示例
    @RequestMapping(path = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0, "操作成功");
    }
}
