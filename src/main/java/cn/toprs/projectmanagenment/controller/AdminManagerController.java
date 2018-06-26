package cn.toprs.projectmanagenment.controller;

import cn.toprs.projectmanagenment.entity.SysUser;
import cn.toprs.projectmanagenment.result.Result;
import cn.toprs.projectmanagenment.result.ResultGenerator;
import cn.toprs.projectmanagenment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author StrangeLuo
 */
@Controller
public class AdminManagerController {

    @Autowired
    public AdminService adminService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 用户增加
     * @param user
     * @param result
     * @return
     */
    @GetMapping("/insertUser")
    @ResponseBody
    public Result insertUser(@Validated({SysUser.Add.class}) SysUser user, BindingResult result){

        if(result.hasErrors()){
            return ResultGenerator.genFailResult(result.getFieldError().getDefaultMessage());
        }
        return adminService.insertUser(user);
    }

    /**
     * 删除用户
     * @param user
     * @param result
     * @return
     */
    @GetMapping("/deleteUser")
    @ResponseBody
    public Result deleteUser(@Validated({SysUser.Delete.class}) SysUser user, BindingResult result){
        if(result.hasErrors()){
            return ResultGenerator.genFailResult(result.getFieldError().getDefaultMessage());
        }
        stringRedisTemplate.delete(user.getUsername());

        return adminService.deleteUser(user);
    }

    /**
     *修改用户
     * @param user
     * @param result
     * @return
     */
    @GetMapping("/updateUser")
    @ResponseBody
    public Result updateUser(@Validated({SysUser.Update.class}) SysUser user, BindingResult result){
        if(result.hasErrors()){
            return ResultGenerator.genFailResult(result.getFieldError().getDefaultMessage());
        }

        stringRedisTemplate.delete(user.getUsername());
        return adminService.updateUser(user);
    }

    @GetMapping("/selectUser")
    @ResponseBody
    public Result selectUser(String selectStr){
        return adminService.selectUser(selectStr);
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    public Result getAllUser(String selectStr){

        return adminService.getAllUser();
    }

    /**
     * 登录页
     * @return
     */
    @RequestMapping("/admin")
    public  String login(){
        return "admin";
    }

}
