package cn.toprs.projectmanagenment.service;

import cn.toprs.projectmanagenment.entity.Person;
import cn.toprs.projectmanagenment.util.ExportUtil;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelService {

    public String excel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        // 设置导出文件的名称
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String("数据导出Excel测试.xls".getBytes(), "iso-8859-1"));
        // 模拟表格需要导出的数据
        Person p1 = new Person("张三", "22", "北京", "男");
        Person p2 = new Person("李四", "23", "济南", "女");
        Person p3 = new Person("王五", "24", "上海", "男");
        List<Person> personList = new ArrayList<Person>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        // 实际应用中这个地方会判断获取的数据，如果没有对应的数据则不导出，如果超过2000条，则只导出2000条
        if (personList.size() == 0) {
            PrintWriter print = response.getWriter();
            print.write("没有需要导出的数据！");
            return null;
        }
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ExportUtil dataExportUtil = new ExportUtil();
            bis = new BufferedInputStream(dataExportUtil.getExcelStream(personList));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
        } catch (final IOException e) {
            System.out.println("数据导出列表导出异常！");
        } finally {

            if (bis != null) {
                bis.close();
                return null;
            }
            if (bos != null) {
                bos.close();
                return null;
            }
        }
        return null;
    }

}
