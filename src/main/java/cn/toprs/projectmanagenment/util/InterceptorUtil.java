package cn.toprs.projectmanagenment.util;

import cn.toprs.projectmanagenment.result.ResultGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Administrator
 * 拦截器报错，返回值
 *
 */
public class InterceptorUtil {
    public static void returnErrorMessage(HttpServletResponse response, String errorMessage) throws IOException {
        response.setContentType("application/json");

        //指定响应以UTF-8格式编码内容
        response.setCharacterEncoding("UTF-8");
        //通知浏览器以何种编码格式打开内容
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String jsonOfRST = mapper.writeValueAsString(ResultGenerator.genFailResult(errorMessage)) ;

        out.print(jsonOfRST);
        out.flush();
    }

    public static void returnUnauthorizedMessage(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        //指定响应以UTF-8格式编码内容
        response.setCharacterEncoding("UTF-8");
        //通知浏览器以何种编码格式打开内容
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String jsonOfRST = mapper.writeValueAsString(ResultGenerator.genUnauthorizedResult()) ;

        out.print(jsonOfRST);
        out.flush();
    }

}
