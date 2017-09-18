package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class ViewController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name="UserService")
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/find")
    @ResponseBody
    public Map<String, Object> find(User user, HttpServletRequest) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("你以通过springmvc进入controller方法");
        logger.info("你以通过springmvc进入controller方法");
        User loginuser = userService.findByUsernameAndPwd(user.getUsername(), user.getPassword());
        if (loginuser != null)
            map.put("result", "success");
        else
            map.put("result", "fail");
        return map;
    }

    @RequestMapping("/success")
    public String success() {
        System.out.println("登录成功...");
        logger.info("登录成功...");

        return "success";
    }
}
