package cn.toprs.projectmanagenment.controller;

import cn.toprs.projectmanagenment.entity.ProjectInfo;
import cn.toprs.projectmanagenment.entity.SysUser;
import cn.toprs.projectmanagenment.result.Result;
import cn.toprs.projectmanagenment.result.ResultGenerator;
import cn.toprs.projectmanagenment.service.ProjectService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author StrangeLuo
 */
@Controller
@RequestMapping("/static")
public class ProjectController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public ProjectService projectService;

    @GetMapping("/projectInsert")
    public String projectInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "projectInsert";
    }

    @GetMapping("/projectList")
    public String projectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "projectList";
    }

    @GetMapping("/projectInfo")
    public String projectInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "projectInfo";
    }
    /**
     * 进入项目修改页
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("/projectUpdate")
    public String projectUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "projectUpdate";
    }

    /**
     * 新增项目
     * @param projectInfo
     * @param result
     * @return
     */
    @GetMapping("/insertProject")
    @ResponseBody
    public Result insertProjecr(@Validated({ProjectInfo.Add.class})ProjectInfo projectInfo, BindingResult result){

        if(result.hasErrors()){
            return ResultGenerator.genFailResult(result.getFieldError().getDefaultMessage());
        }

        return projectService.insertProject(projectInfo);
    }

    /**
     * 更新项目
     * @param projectInfo
     * @param result
     * @return
     */
    @GetMapping("/updateProject")
    @ResponseBody
    public Result updateProject(@Validated({ProjectInfo.Update.class})ProjectInfo projectInfo, BindingResult result){
        if(result.hasErrors()){
            return ResultGenerator.genFailResult(result.getFieldError().getDefaultMessage());
        }
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        //获取登录信息
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        return projectService.updateProject(projectInfo,username);
    }

    /**
     * 删除项目
     * @param id
     * @return
     */
    @GetMapping("/deleteProject")
    @ResponseBody
    public Result deleteProject(Integer id){
        if("".equals(id)||id==null){
            return ResultGenerator.genFailResult("项目编号不能为空");
        }
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        //获取登录信息
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        return projectService.deleteProject(id,username);
    }

    /**
     * 条件查询
     * @param request
     * @param response
     * @param projectInfo
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("/selectPJInfo")
    @ResponseBody
    public Result selectProject(HttpServletRequest request, HttpServletResponse response,ProjectInfo projectInfo) throws ServletException, IOException {

        /*SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        //获取登录信息
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();*/

        String username = "admin";

        String userInfoStr =  stringRedisTemplate.opsForValue().get(username);
        SysUser userInfo =  JSONObject.parseObject(userInfoStr,new TypeReference<SysUser>() {});
        if(userInfo==null){
            request.getRequestDispatcher("/").forward(request,response);
        }
        projectInfo.setUsername(userInfo.getUsername());
        projectInfo.setUserarea(userInfo.getUserarea());


        return projectService.selectProjectByCon(projectInfo);
    }

    /**
     * 模糊查询
     * @param request
     * @param response
     * @param projectInfo
     * @return
     * @throws ServletException
     * @throws IOException
     */
   /* @GetMapping("/selectPJinfo")
    @ResponseBody
    public Result selectPJinfo(HttpServletRequest request, HttpServletResponse response,ProjectInfo projectInfo) throws ServletException, IOException {

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        //获取登录信息
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();

        String userInfoStr =  stringRedisTemplate.opsForValue().get(username);
        SysUser userInfo =  JSONObject.parseObject(userInfoStr,new TypeReference<SysUser>() {});
        if(userInfo==null){
            request.getRequestDispatcher("/").forward(request,response);
        }
        projectInfo.setUsername(userInfo.getUsername());
        projectInfo.setUserarea(userInfo.getUserarea());

        return projectService.selectProjectByLike(projectInfo);
    }*/

}
