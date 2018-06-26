package cn.toprs.projectmanagenment.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import cn.toprs.projectmanagenment.entity.Person;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 数据导出到Excel工具类
 *
 * @author luokai
 *
 */
public class ExportUtil {
    /**
     * 设置导出Excel的表名
     *
     * @return
     */
    public String getSheetName() {
        return "测试导出数据";
    }
    /**
     * 设置导出Excel的列名
     *
     * @return
     */
    public String getSheetTitleName() {
        return "序号,姓名,年龄,居住地,性别";
    }
    /**
     * 创建 sheet 的第一行,标题行
     *
     * @param sheet
     * @param strTitle
     */
    private void createSheetTitle(HSSFSheet sheet, String strTitle) {
        // 创建该表格(sheet)的第一行
        HSSFRow row = sheet.createRow(1);
        sheet.setDefaultColumnWidth(4);
        HSSFCell cell = null;
        String[] strArray = strTitle.split(",");
        for (int i = 0; i < strArray.length; i++) {
            // 创建该行的第一列
            cell = row.createCell(i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(strArray[i]));
        }
    }
    @SuppressWarnings("resource")
    public InputStream getExcelStream(List<Person> personList) throws IOException {
        // 创建一个 Excel 文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建一个表格 Sheet
        HSSFSheet sheet = wb.createSheet(this.getSheetName());
        //设置行宽
        sheet.setColumnWidth(0, 3766);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 6));
        // 创建 sheet 的第一行,标题行
        // 行号从0开始计算
        this.createSheetTitle(sheet, this.getSheetTitleName());
        // 设置 sheet 的主体内容
        this.createSheetBody(personList, sheet);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wb.write(output);
        byte[] ba = output.toByteArray();
        InputStream is = new ByteArrayInputStream(ba);
        return is;
    }
    private void createSheetBody(List<Person> personList, HSSFSheet sheet) {
        if (personList == null || personList.size() < 1) {
            return;
        }
        // 表格(sheet) 的第二行, 第一行是标题, Excel中行号, 列号 是由 0 开始的
        int rowNum = 2;
        //列
        HSSFCell cell = null;
        //行
        HSSFRow row = null;
        for (Iterator<Person> it = personList.iterator(); it.hasNext(); rowNum++) {
            Person person = (Person) it.next();
            if (person == null) {
                person = new Person();
            }
            row = sheet.createRow(rowNum);
            int i = 0;
            //编号
            cell = row.createCell(i++);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(rowNum + ""));
            // name
            cell = row.createCell(i++);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(person.getName()));
            // age
            cell = row.createCell(i++);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(person.getAge()));
            // addr
            cell = row.createCell(i++);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(person.getAddr()));
            // sex
            cell = row.createCell(i++);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(person.getSex()));
        }
    }
}

