package org.hy.common.report;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;




/**
 * 报表的辅助类 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 */
public class ReportHelp
{
    
    /**
     * 创建一个工作薄
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @return
     */
    public final static RWorkbook createWorkbook()
    {
        return new RWorkbook(new HSSFWorkbook());
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_SheetName  Excel工作表的名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @return
     */
    public final static RWorkbook write(String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate)
    {
        return write(null ,i_SheetName ,i_Datas ,i_RTemplate);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_Workbook   工作薄对象
     * @param i_SheetName  工作表名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @return
     */
    public final static RWorkbook write(RWorkbook i_Workbook ,String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate)
    {
        RWorkbook v_DataWorkbook  = i_Workbook;
        HSSFSheet v_DataSheet     = null;
        HSSFSheet v_TemplateSheet = null;
        
        if ( null == v_DataWorkbook )
        {
            v_DataWorkbook = createWorkbook();
        }
        
        v_DataSheet     = ExcelHelp.createSheet(v_DataWorkbook.getWorkbook() ,i_SheetName);
        v_TemplateSheet = i_RTemplate.getTemplateSheet();
        
        // 数据工作表的整体(所有)列的列宽，复制于模板
        ExcelHelp.copyColumnsWidth(v_TemplateSheet ,v_DataSheet);
        // 数据工作表的打印区域，复制于模板
        ExcelHelp.copyPrintSetup  (v_TemplateSheet ,v_DataSheet);
        
        writeTitle(v_DataWorkbook ,v_DataSheet ,i_Datas ,i_RTemplate);
        
        for (int v_DataIndex=1; v_DataIndex<=i_Datas.size(); v_DataIndex++)
        {
            writeData(v_DataWorkbook ,v_DataSheet ,v_DataIndex ,i_Datas.get(v_DataIndex - 1) ,i_RTemplate);
        }
        
        return v_DataWorkbook;
    }
    
    
    
    /**
     * 复制模板工作表的标题区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param i_Offset     偏移量。下标从 1 开始。
     */
    public final static void copyImagesTitle(RTemplate i_RTemplate ,HSSFSheet i_DataSheet, int i_Offset)
    {
        int v_OffsetRow = (i_RTemplate.getRowCountTitle() - i_RTemplate.getTitleBeginRow()) * i_Offset;
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitleBeginRow() ,i_RTemplate.getTitleEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的数据区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param i_Offset     偏移量。下标从 1 开始。
     */
    public final static void copyImagesData(RTemplate i_RTemplate ,HSSFSheet i_DataSheet, int i_Offset)
    {
        int v_OffsetRow = i_RTemplate.getRowCountTitle() * (i_Offset <= 1 ? 0 : 1) + i_RTemplate.getRowCountData() * (i_Offset - 1);
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getDataBeginRow() ,i_RTemplate.getDataEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的合计区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param i_Offset     偏移量。下标从 1 开始。
     */
    public final static void copyImagesTotal(RTemplate i_RTemplate ,HSSFSheet i_DataSheet, int i_Offset)
    {
        // 通过数据计算合计
        int v_OffsetRow = i_RTemplate.getRowCountTitle() * (i_Offset <= 1 ? 0 : 1) + i_RTemplate.getRowCountData() * i_Offset;
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTotalBeginRow() ,i_RTemplate.getTotalEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 按报表模板格式写标题
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_DataWorkbook  数据工作薄
     * @param i_DataSheet     数据工作表
     * @param i_DataIndex     数据索引号。下标从 1 开始
     * @param i_Datas         数据
     * @param i_RTemplate     报表模板对象
     */
    public final static void writeTitle(RWorkbook i_DataWorkbook ,HSSFSheet i_DataSheet ,Object i_Datas ,RTemplate i_RTemplate) 
    {
        HSSFSheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int       v_TemplateRowCount = i_RTemplate.getRowCountTitle();

        copyMergedRegionsTitle(i_RTemplate ,i_DataSheet ,0);  // 按模板合并单元格
        copyImagesTitle(       i_RTemplate ,i_DataSheet ,0);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int     v_TemplateRowNo = i_RTemplate.getTitleBeginRow() + v_RowNo;
            HSSFRow v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            if ( v_TemplateRow == null ) 
            {
                v_TemplateRow = v_TemplateSheet.createRow(v_TemplateRowNo);
            }
            
            int     v_DataRowNo = v_RowNo;
            HSSFRow v_DataRow   = i_DataSheet.getRow(v_DataRowNo);
            if ( v_DataRow == null ) 
            {
                v_DataRow = i_DataSheet.createRow(v_DataRowNo);
            }
            
            copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,v_DataRow ,i_Datas);
        }
    } 
    
    
    
    /**
     * 按报表模板格式写入一行数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_DataWorkbook  数据工作薄
     * @param i_DataSheet     数据工作表
     * @param i_DataIndex     数据索引号。下标从 1 开始
     * @param i_Datas         数据
     * @param i_RTemplate     报表模板对象
     */
    public final static void writeData(RWorkbook i_DataWorkbook ,HSSFSheet i_DataSheet ,int i_DataIndex, Object i_Datas ,RTemplate i_RTemplate) 
    {
        HSSFSheet v_TemplateSheet      = i_RTemplate.getTemplateSheet();
        int       v_TemplateTitleCount = i_RTemplate.getRowCountTitle();
        int       v_TemplateRowCount   = i_RTemplate.getRowCountData();
        
        copyMergedRegionsData(i_RTemplate ,i_DataSheet ,i_DataIndex);  // 按模板合并单元格
        copyImagesData(       i_RTemplate ,i_DataSheet ,i_DataIndex);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++) 
        {
            int     v_TemplateRowNo = i_RTemplate.getDataBeginRow() + v_RowNo;
            HSSFRow v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            if ( v_TemplateRow == null ) 
            {
                v_TemplateRow = v_TemplateSheet.createRow(v_TemplateRowNo);
            }
            
            int     v_DataRowNo = v_TemplateTitleCount + (i_DataIndex - 1) * v_TemplateRowCount + v_RowNo;
            HSSFRow v_DataRow   = i_DataSheet.getRow(v_DataRowNo);
            if ( v_DataRow == null ) 
            {
                v_DataRow = i_DataSheet.createRow(v_DataRowNo);
            }
            
            copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,v_DataRow ,i_Datas);
        }
    }
    
    
    
    /**
     * 复制模板工作表的标题区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param i_Offset     偏移量。下标从 1 开始。
     */
    public final static void copyMergedRegionsTitle(RTemplate i_RTemplate ,HSSFSheet i_DataSheet, int i_Offset)
    {
        int v_OffsetRow = (i_RTemplate.getRowCountTitle() - i_RTemplate.getTitleBeginRow()) * i_Offset;
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitleBeginRow() ,i_RTemplate.getTitleEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的数据区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param i_Offset     偏移量。下标从 1 开始。
     */
    public final static void copyMergedRegionsData(RTemplate i_RTemplate ,HSSFSheet i_DataSheet, int i_Offset)
    {
        int v_OffsetRow = i_RTemplate.getRowCountTitle() * (i_Offset <= 1 ? 0 : 1) + i_RTemplate.getRowCountData() * (i_Offset - 1);
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getDataBeginRow() ,i_RTemplate.getDataEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的合计区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param i_Offset     偏移量。下标从 1 开始。
     */
    public final static void copyMergedRegionsTotal(RTemplate i_RTemplate ,HSSFSheet i_DataSheet, int i_Offset)
    {
        // 通过数据计算合计
        int v_OffsetRow = i_RTemplate.getRowCountTitle() * (i_Offset <= 1 ? 0 : 1) + i_RTemplate.getRowCountData() * i_Offset;
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTotalBeginRow() ,i_RTemplate.getTotalEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 行复制功能
     * 
     * @param fromRow
     * @param toRow
     */
    public final static void copyRow(RTemplate i_RTemplate ,HSSFRow i_TemplateRow ,RWorkbook i_DataWorkbook ,HSSFRow i_DataRow ,Object i_Datas) 
    {
        i_DataRow.setHeight(    i_TemplateRow.getHeight());
        i_DataRow.setZeroHeight(i_TemplateRow.getZeroHeight());
        
        int v_CellCount = i_TemplateRow.getLastCellNum();
        for (int v_CellIndex=0; v_CellIndex<v_CellCount; v_CellIndex++) 
        {
            HSSFCell v_TemplateCell = i_TemplateRow.getCell(v_CellIndex);
            if ( v_TemplateCell == null ) 
            {
                v_TemplateCell = i_TemplateRow.createCell(v_CellIndex);
            }
            
            HSSFCell v_DataCell = i_DataRow.getCell(v_CellIndex);
            if ( v_DataCell == null ) 
            {
                v_DataCell = i_DataRow.createCell(v_CellIndex);
            }
            
            copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataCell ,i_Datas);
        }
    }
    
    
    
    /**
     * 复制单位格
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate
     * @param i_TemplateCell
     * @param i_DataWorkbook
     * @param i_DataCell
     * @param i_Datas
     */
    public final static void copyCell(RTemplate i_RTemplate ,HSSFCell i_TemplateCell ,RWorkbook i_DataWorkbook ,HSSFCell i_DataCell ,Object i_Datas)
    {
        // 复制样式
        i_DataCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,i_TemplateCell.getCellStyle().getIndex()));

        // 复制评论
        if ( i_TemplateCell.getCellComment() != null ) 
        {
            i_DataCell.setCellComment(i_TemplateCell.getCellComment());
        }
        
        // 复制数据类型
        CellType v_CellType = i_TemplateCell.getCellTypeEnum();
        i_DataCell.setCellType(v_CellType);
        
        if ( v_CellType == CellType.NUMERIC ) 
        {
            if ( HSSFDateUtil.isCellDateFormatted(i_TemplateCell) ) 
            {
                i_DataCell.setCellValue(i_TemplateCell.getDateCellValue());
            } 
            else 
            {
                i_DataCell.setCellValue(i_TemplateCell.getNumericCellValue());
            }
        }
        else if ( v_CellType == CellType.STRING ) 
        {
            HSSFRichTextString v_TemplateRichText = i_TemplateCell.getRichStringCellValue();
            String             v_ValueName        = v_TemplateRichText.toString();
            
            if ( i_RTemplate.isExists(v_ValueName) )
            {
                Object v_Value = i_RTemplate.getValue(v_ValueName ,i_Datas);
                i_DataCell.setCellValue(v_Value.toString());
            }
            else 
            {
                copyRichTextStyle(i_RTemplate ,v_TemplateRichText ,i_DataWorkbook ,i_DataCell);
            }
        } 
        else if ( v_CellType == CellType.BOOLEAN ) 
        {
            i_DataCell.setCellValue(i_TemplateCell.getBooleanCellValue());
        } 
        else if ( v_CellType == CellType.FORMULA) 
        {
            i_DataCell.setCellFormula(i_TemplateCell.getCellFormula());
        } 
        else 
        {
            // Nothing.
        }
    }
    
    
    
    /**
     * 复制高级文本及格式
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate         模板对象
     * @param i_TemplateRichText  高级文本对象
     * @param i_DataWorkbook      数据工作薄
     * @param i_DataCell          数据单元格
     */
    public final static void copyRichTextStyle(RTemplate i_RTemplate ,HSSFRichTextString i_TemplateRichText ,RWorkbook i_DataWorkbook ,HSSFCell i_DataCell) 
    {
        int    v_FontCount = i_TemplateRichText.numFormattingRuns();
        String v_Text      = i_TemplateRichText.toString();
        int    v_TextLen   = v_Text.length();
        
        if ( v_FontCount >= 1 )
        {
            HSSFRichTextString v_DataRichTextString = new HSSFRichTextString(v_Text);
            
            for (int v_FontIndex=v_FontCount-1; v_FontIndex >= 0; v_FontIndex--) 
            {
                int   v_FirstIndex = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
                short v_IDX        = i_TemplateRichText.getFontOfFormattingRun( v_FontIndex);
                Font  v_DataFont   = i_DataWorkbook.getFont(i_RTemplate ,v_IDX);
                
                v_DataRichTextString.applyFont(v_FirstIndex, v_TextLen, v_DataFont);
                v_TextLen = v_FirstIndex;
            }
            
            i_DataCell.setCellValue(v_DataRichTextString);
        }
        else
        {
            i_DataCell.setCellValue(v_Text);
        }
    }
    
}
