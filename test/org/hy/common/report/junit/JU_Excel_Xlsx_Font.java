package org.hy.common.report.junit;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hy.common.Date;
import org.hy.common.report.ExcelHelp;
import org.junit.Test;





/**
 * 测试单元：Xlsx的字体 
 *
 * @author      ZhengWei(HY)
 * @createDate  2020-11-11
 * @version     v1.0
 */
public class JU_Excel_Xlsx_Font
{
    
    @Test
    public void test_XlsxFont(Font i_FromFont)
    {
        XSSFWorkbook v_Workbook = new XSSFWorkbook();
        XSSFSheet    v_Sheet    = v_Workbook.createSheet("测试字体");
        
        
        XSSFFont v_Font = v_Workbook.createFont();
        v_Font.setColor(new XSSFColor());
        ExcelHelp.copyFont(i_FromFont ,v_Font);
        
        
        for (int v_RowIndex=0; v_RowIndex<=9; v_RowIndex++)
        {
            XSSFRow  v_Row  = v_Sheet.createRow(v_RowIndex);
            
            for (int v_ColIndex=0; v_ColIndex<1; v_ColIndex++)
            {
                XSSFCell      v_Cell = v_Row.createCell(v_ColIndex);
                XSSFCellStyle v_CellStyle = v_Workbook.createCellStyle();
                
                v_CellStyle.setFillForegroundColor((short)(v_RowIndex + 1));
                v_CellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                v_CellStyle.setFont(v_Font);
                
                v_Cell.setCellStyle(v_CellStyle);
                v_Cell.setCellValue("" + (v_RowIndex + 1));
            }
        }
        
        ExcelHelp.save(v_Workbook ,"C:\\Users\\ZhengWei\\Desktop\\测试字体" + Date.getNowTime().getFull_ID() + ".xlsx");
    }
    
}
