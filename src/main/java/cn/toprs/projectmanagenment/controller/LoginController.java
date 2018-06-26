package cn.toprs.projectmanagenment.controller;


import cn.toprs.projectmanagenment.entity.Msg;

import cn.toprs.projectmanagenment.entity.SysUser;

import cn.toprs.projectmanagenment.service.UserInfoService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author StrangeLuo
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public UserInfoService userInfoService;



    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录主页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        //获取登录信息
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        LOGGER.info("用户"+username+"登录成功！");

        //如果username获得的session信息为空就重新查询
        if(stringRedisTemplate.opsForValue().get(username)==null){
            SysUser userInfo = userInfoService.getUserInfo(username);
            JSONObject userinfoJson = (JSONObject) JSONObject.toJSON(userInfo);
            stringRedisTemplate.opsForValue().set(username,userinfoJson.toJSONString());
        }

        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }

    /**
     * 登录页
     * @return
     */
    @RequestMapping("/login")
    public  String login(){
        return "login";
    }

    @RequestMapping("/index")
    public  String index(){
        return "area";
    }
}
