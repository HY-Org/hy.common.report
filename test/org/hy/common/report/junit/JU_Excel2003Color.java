package org.hy.common.report.junit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.hy.common.report.ExcelHelp;
import org.junit.Test;





/**
 * 测试单元：2003版本的单元格颜色 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-20
 * @version     v1.0
 */
public class JU_Excel2003Color
{
    
    @Test
    public void test_001()
    {
        HSSFWorkbook v_Workbook = new HSSFWorkbook();
        HSSFSheet    v_Sheet    = v_Workbook.createSheet("测试单元格颜色");
        
        v_Sheet.setColumnWidth(0 ,2560);
        
        for (int v_RowIndex=0; v_RowIndex<4000; v_RowIndex++)
        {
            HSSFRow v_Row = v_Sheet.createRow(v_RowIndex);
            
            for (int v_ColIndex=0; v_ColIndex<1; v_ColIndex++)
            {
                HSSFCell      v_Cell = v_Row.createCell(v_ColIndex);
                HSSFCellStyle v_CellStyle = v_Workbook.createCellStyle();
                
                v_CellStyle.setFillForegroundColor((short)(v_RowIndex + 1));
                v_CellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                
                v_Cell.setCellStyle(v_CellStyle);
                v_Cell.setCellValue("" + (v_RowIndex + 1));
            }
        }
        
        ExcelHelp.save(v_Workbook ,"/Users/hy/Downloads/测试2003版本的单元格颜色");
    }
    
}
