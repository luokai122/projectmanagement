package cn.toprs.projectmanagenment.service;

import cn.toprs.projectmanagenment.result.Result;
import cn.toprs.projectmanagenment.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @author StrangeLuo
 */
@Service
public class ImgService {

    @Value("${web.upload-path}")
    private String path ;

    private String [] types={".jpg",".bmp",".jpeg",".png",".gif"};



    public Result uploadImg(MultipartFile file) throws IOException {
        String fileName;
        if(file==null){
            return ResultGenerator.genFailResult("文件不能为空！");
        }
        if (!file.isEmpty()) {
            fileName = file.getOriginalFilename().toLowerCase();
            String type = fileName.substring(fileName.lastIndexOf("."));
            if (Arrays.asList(types).contains(type)){

                BufferedOutputStream out = null;
                File fileSourcePath = new File(path);
                if (!fileSourcePath.exists()) {
                    fileSourcePath.mkdirs();
                }
                fileName = Calendar.getInstance().getTimeInMillis()+type;
                out = new BufferedOutputStream(
                        new FileOutputStream(new File(fileSourcePath, fileName)));
                out.write(file.getBytes());
                out.flush();
                out.close();

                return ResultGenerator.genSuccessResult(fileName);
            }
            return ResultGenerator.genFailResult("此格式不支持！");
        }
        return ResultGenerator.genFailResult("文件不能为空！");
    }

}
