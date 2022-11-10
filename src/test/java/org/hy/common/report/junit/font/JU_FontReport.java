package org.hy.common.report.junit.font;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hy.common.Date;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：字体演示
 *
 * @author      ZhengWei(HY)
 * @createDate  2020-05-14
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_FontReport
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_FontReport() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    /**
     * 生成测试数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-14
     * @version     v1.0
     *
     * @param i_Size
     * @return
     */
    public List<FontTest> getDatas(int i_Size)
    {
        List<FontTest> v_Datas = new ArrayList<FontTest>();
        
        for (int i=1; i<=i_Size; i++)
        {
            FontTest v_Data  = new FontTest();
            int      v_PSize = v_Data.gatPropertySize();
            
            for (int v_PIndex=0; v_PIndex<v_PSize; v_PIndex++)
            {
                v_Data.setPropertyValue(v_PIndex ,i + "-" + v_PIndex + " - ABC1234");
            }
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
    
    
    @Test
    public void test_Font() throws RTemplateException
    {
        RTemplate      v_RTemplate = (RTemplate)XJava.getObject("Report_Font_xlsx");
        List<FontTest> v_Datas     = this.getDatas(1);
        
        RWorkbook v_RWorkbook02 = ReportHelp.toExcel(v_Datas ,v_RTemplate);
        
        ExcelHelp.save(v_RWorkbook02.getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\FontReport_" + Date.getNowTime().getFull_ID() + ".xlsx");
    }
    
    
    
    @Test
    public void test_FontOne() throws IOException
    {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet 01");
        Row row = sheet.createRow(1);
        Font font = wb.createFont();
        font.setBold(true);
        font.setColor((short) 13);
        font.setFontHeightInPoints((short) 24);
        font.setFontName("宋体");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        Cell cell = row.createCell(1);
        cell.setCellValue("这是测试字体格式的");
        cell.setCellStyle(cellStyle);
        FileOutputStream fileOutputStream = new FileOutputStream("D://font.xlsx");
        wb.write(fileOutputStream);
        wb.close();
    }
    
}
