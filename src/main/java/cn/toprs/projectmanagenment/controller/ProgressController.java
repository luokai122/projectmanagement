package cn.toprs.projectmanagenment.controller;

import cn.toprs.projectmanagenment.entity.ProgressInfo;
import cn.toprs.projectmanagenment.result.Result;
import cn.toprs.projectmanagenment.result.ResultCodeEnum;
import cn.toprs.projectmanagenment.result.ResultGenerator;
import cn.toprs.projectmanagenment.service.ImgService;
import cn.toprs.projectmanagenment.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/static")
public class ProgressController {

    @Autowired
    public ProgressService progressService;

    @Autowired
    public ImgService imgService;




    /**
     * 查询进程
     * @param id
     * @return
     */
    @GetMapping("/selectProgress")
    @ResponseBody
    public Result selectProgress(Integer id){
        if("".equals(id)||id==null){
            return ResultGenerator.genFailResult("项目编号不能为空");
        }
        return progressService.selectProgress(id);
    }

    /**
     * 新增进程
     * @param progressInfo
     * @param result
     * @return
     * @throws IOException
     */
    @PostMapping("/insertProgress")
    @ResponseBody
    public Result insertProgress(@Validated(ProgressInfo.Add.class)ProgressInfo progressInfo, BindingResult result) throws IOException {
        if(result.hasErrors()){
            return ResultGenerator.genFailResult(result.getFieldError().getDefaultMessage());
        }

        /*SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        //获取登录信息
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();*/

        String username = "admin";

        if(progressInfo.getImgs()!=null){
            String imgUrl = null;

            for(int i=0;i<progressInfo.getImgs().length;i++){
                Result imgResult = imgService.uploadImg(progressInfo.getImgs()[i]);

                if(!imgResult.getCode().equals(ResultCodeEnum.SUCCESS)){
                    return imgResult;
                }
                if(i==0){
                    imgUrl = imgResult.getData().toString()+";";
                }else {
                    imgUrl = imgUrl+imgResult.getData().toString()+";";
                }

            }

            progressInfo.setImg(imgUrl.toString());
        }


        return progressService.insertProgress(progressInfo,username);
    }

    /**
     * 删除进程
     * @param progressInfo
     * @param result
     * @return
     * @throws IOException
     */
    @GetMapping("/deleteProgress")
    @ResponseBody
    public Result deleteProgress( @Validated(ProgressInfo.Delete.class)ProgressInfo progressInfo,BindingResult result) throws IOException {
        if(result.hasErrors()){
            return ResultGenerator.genFailResult(result.getFieldError().getDefaultMessage());
        }

        /*SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        //获取登录信息
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();*/
        String username = "admin";
        return progressService.deleteProgress(progressInfo,username);
    }


}
