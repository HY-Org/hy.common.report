package org.hy.common.report;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hy.common.Help;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RValue;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.event.ValueListener;





/**
 * Excel模板占位符为依据，Java对象映射转为Excel数据文件。
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 */
public class JavaToExcel
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
    public final static RWorkbook createWorkbook(RTemplate i_RTemplate)
    {
        if ( "xls".equalsIgnoreCase(i_RTemplate.getExcelVersion()) )
        {
            return new RWorkbook(new HSSFWorkbook());
        }
        else if ( "xlsx".equalsIgnoreCase(i_RTemplate.getExcelVersion()) )
        {
            return new RWorkbook(new XSSFWorkbook());
        }
        else
        {
            return null;
        }
    }
    
    
    
    /**
     * 创建颜色。
     * 
     * 写本方法的原因是：从2003版本的模板中复制单元格颜色时，会出现颜色失真的问题。
     * 
     * 但最终也没有解决。因为：当单元格的颜色设置为非标准颜色时，就会失真，但设置为标准颜色时，是正常的。
     * 
     * 也因为此，本方法与 i_ToCellStyle.setFillBackgroundColor(i_FromCellStyle.getFillBackgroundColor()); 的效果是相同的。
     * 
     * 本方法作为研究使用而保留下来，但不没有使用价值。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-21
     * @version     v1.0
     *
     * @param i_FromColor
     * @param i_DataWorkbook
     * @return
     */
    @Deprecated
    public final static HSSFColor createColor(HSSFColor i_FromColor ,HSSFWorkbook i_DataWorkbook)
    {
        short [] v_RGBHex    = i_FromColor.getTriplet();
        byte     v_ByteRed   = (byte)v_RGBHex[0];
        byte     v_ByteGreen = (byte)v_RGBHex[1];
        byte     v_ByteBlue  = (byte)v_RGBHex[2];
        
        HSSFPalette v_Palette   = i_DataWorkbook.getCustomPalette();
        HSSFColor   v_DataColor = v_Palette.findColor(v_ByteRed ,v_ByteGreen ,v_ByteBlue);
        
        if ( v_DataColor == null )
        {
            v_Palette.setColorAtIndex(i_FromColor.getIndex() ,v_ByteRed ,v_ByteGreen ,v_ByteBlue);
            
            return v_Palette.getColor(i_FromColor.getIndex());
        }
        
        return  v_DataColor;
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
     * @createDate  2017-03-28
     * @version     v1.0
     *
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @return
     */
    public final static RWorkbook write(List<?> i_Datas ,RTemplate i_RTemplate)
    {
        return write(null ,null ,i_Datas ,i_RTemplate);
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
    public final static RWorkbook write(RWorkbook i_Workbook ,List<?> i_Datas ,RTemplate i_RTemplate)
    {
        return write(i_Workbook ,null ,i_Datas ,i_RTemplate);
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
        Sheet     v_DataSheet     = null;
        Sheet     v_TemplateSheet = null;
        
        if ( null == v_DataWorkbook )
        {
            v_DataWorkbook = createWorkbook(i_RTemplate);
        }
        
        v_DataSheet     = ExcelHelp.createSheet(v_DataWorkbook.getWorkbook() ,Help.NVL(i_SheetName ,i_RTemplate.getName()));
        v_TemplateSheet = i_RTemplate.getTemplateSheet();
        
        ExcelHelp.copySheet(       v_TemplateSheet ,v_DataSheet);
        // 数据工作表的整体(所有)列的列宽，复制于模板
        ExcelHelp.copyColumnsWidth(v_TemplateSheet ,v_DataSheet);
        // 数据工作表的打印区域，复制于模板
        ExcelHelp.copyPrintSetup  (v_TemplateSheet ,v_DataSheet);
        // 数据工作表的整体(所有)列的样式，复制于模板
        copyColumnsStyle(i_RTemplate ,v_TemplateSheet ,v_DataWorkbook ,v_DataSheet);
        
        RSystemValue v_RSystemValue = new RSystemValue();
        int          v_DataRowIndex = 0;
        
        v_RSystemValue.setRowNo(           1);
        v_RSystemValue.setRowCount(        i_Datas.size());
        v_RSystemValue.setRowSubtotalCount(i_Datas.size());
        
        if ( i_RTemplate.getRowCountTitle() >= 1 )
        {
            writeTitle(v_DataWorkbook ,v_DataSheet ,v_RSystemValue ,i_Datas.get(0) ,i_RTemplate);
        }
        
        if ( i_RTemplate.getRowCountData() >= 1 )
        {
            v_DataRowIndex = i_RTemplate.getRowCountTitle();

            if ( i_RTemplate.getRowCountSubtotal() >= 1 )
            {
                // 模板中有小计的
                for (; v_RSystemValue.getRowNo()<=v_RSystemValue.getRowCount(); )
                {
                    v_DataRowIndex = writeData(    v_DataWorkbook ,v_DataSheet ,v_DataRowIndex ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                    v_DataRowIndex = writeSubtotal(v_DataWorkbook ,v_DataSheet ,v_DataRowIndex ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                    
                    v_RSystemValue.setRowNo(v_RSystemValue.getRowNo() + 1);
                }
            }
            else
            {
                for (; v_RSystemValue.getRowNo()<=v_RSystemValue.getRowCount(); )
                {
                    v_DataRowIndex = writeData(v_DataWorkbook ,v_DataSheet ,v_DataRowIndex ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                    
                    v_RSystemValue.setRowNo(v_RSystemValue.getRowNo() + 1);
                }
            }
        }
        
        if ( i_RTemplate.getRowCountTotal() >= 1 )
        {
            writeTotal(v_DataWorkbook ,v_DataSheet ,v_DataRowIndex ,v_RSystemValue ,i_Datas ,i_RTemplate);
        }
        
        return v_DataWorkbook;
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
     * @param i_RSystemValue  系统变量信息
     * @param i_Datas         数据
     * @param i_RTemplate     报表模板对象
     */
    public final static void writeTitle(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RSystemValue i_RSystemValue ,Object i_Datas ,RTemplate i_RTemplate) 
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = i_RTemplate.getRowCountTitle();

        copyMergedRegionsTitle(i_RTemplate ,i_DataSheet ,0);  // 按模板合并单元格
        copyImagesTitle(       i_RTemplate ,i_DataSheet ,0);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getTitleBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            if ( v_TemplateRow == null ) 
            {
                v_TemplateRow = v_TemplateSheet.createRow(v_TemplateRowNo);
            }
            
            int v_DataRowNo = v_RowNo;
            Row v_DataRow   = i_DataSheet.getRow(v_DataRowNo);
            if ( v_DataRow == null ) 
            {
                v_DataRow = i_DataSheet.createRow(v_DataRowNo);
            }
            
            copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,i_RSystemValue ,v_DataRow ,i_Datas);
        }
    } 
    
    
    
    /**
     * 按报表模板格式写入数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_DataWorkbook  数据工作薄
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     * @param i_RSystemValue  系统变量信息
     * @param i_Datas         数据
     * @param i_RTemplate     报表模板对象
     * @param                 返回数据工作表中已写到哪一行的行号。
     */
    public final static int writeData(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,int i_DataRowIndex ,RSystemValue i_RSystemValue, Object i_Datas ,RTemplate i_RTemplate) 
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = i_RTemplate.getRowCountData();
        int   v_DataRowIndex     = i_DataRowIndex;
        
        copyMergedRegionsData(i_RTemplate ,i_DataSheet ,i_DataRowIndex);  // 按模板合并单元格
        copyImagesData(       i_RTemplate ,i_DataSheet ,i_DataRowIndex);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++) 
        {
            int v_TemplateRowNo = i_RTemplate.getDataBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            if ( v_TemplateRow == null ) 
            {
                v_TemplateRow = v_TemplateSheet.createRow(v_TemplateRowNo);
            }
            
            int v_DataRowNo = v_RowNo + i_DataRowIndex;
            Row v_DataRow   = i_DataSheet.getRow(v_DataRowNo);
            if ( v_DataRow == null ) 
            {
                v_DataRow = i_DataSheet.createRow(v_DataRowNo);
            }
            
            v_DataRowIndex ++;
            v_DataRowIndex += copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,i_RSystemValue ,v_DataRow ,i_Datas);
        }
        
        return v_DataRowIndex;
    }
    
    
    
    /**
     * 按报表模板格式写入小计
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-27
     * @version     v1.0
     *
     * @param i_DataWorkbook  数据工作薄
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     * @param i_RSystemValue  系统变量信息
     * @param i_Datas         数据
     * @param i_RTemplate     报表模板对象
     * 
     * @param                 返回数据工作表中已写到哪一行的行号。
     */
    public final static int writeSubtotal(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,int i_DataRowIndex ,RSystemValue i_RSystemValue, Object i_Datas ,RTemplate i_RTemplate) 
    {
        Sheet v_TemplateSheet            = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCountSubtotal = i_RTemplate.getRowCountSubtotal();
        int   v_DataRowIndex             = i_DataRowIndex;
        
        copyMergedRegionsSubtotal(i_RTemplate ,i_DataSheet ,i_DataRowIndex);  // 按模板合并单元格
        copyImagesSubtotal(       i_RTemplate ,i_DataSheet ,i_DataRowIndex);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCountSubtotal; v_RowNo++) 
        {
            int v_TemplateRowNo = i_RTemplate.getSubtotalBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            if ( v_TemplateRow == null ) 
            {
                v_TemplateRow = v_TemplateSheet.createRow(v_TemplateRowNo);
            }
            
            int v_DataRowNo = v_RowNo + i_DataRowIndex;
            Row v_DataRow   = i_DataSheet.getRow(v_DataRowNo);
            if ( v_DataRow == null ) 
            {
                v_DataRow = i_DataSheet.createRow(v_DataRowNo);
            }
            
            v_DataRowIndex ++;
            v_DataRowIndex += copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,i_RSystemValue ,v_DataRow ,i_Datas);
        }
        
        return v_DataRowIndex;
    }
    
    
    
    /**
     * 按报表模板格式写入合计
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_DataWorkbook  数据工作薄
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     * @param i_RSystemValue  系统变量信息
     * @param i_Datas         数据
     * @param i_RTemplate     报表模板对象
     */
    public final static void writeTotal(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,int i_DataRowIndex ,RSystemValue i_RSystemValue, Object i_Datas ,RTemplate i_RTemplate) 
    {
        Sheet v_TemplateSheet         = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCountTotal = i_RTemplate.getRowCountTotal();
        
        copyMergedRegionsTotal(i_RTemplate ,i_DataSheet ,i_DataRowIndex);  // 按模板合并单元格
        copyImagesTotal(       i_RTemplate ,i_DataSheet ,i_DataRowIndex);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCountTotal; v_RowNo++) 
        {
            int v_TemplateRowNo = i_RTemplate.getTotalBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            if ( v_TemplateRow == null ) 
            {
                v_TemplateRow = v_TemplateSheet.createRow(v_TemplateRowNo);
            }
            
            int v_DataRowNo = v_RowNo + i_DataRowIndex;
            Row v_DataRow   = i_DataSheet.getRow(v_DataRowNo);
            if ( v_DataRow == null ) 
            {
                v_DataRow = i_DataSheet.createRow(v_DataRowNo);
            }
            
            copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,i_RSystemValue ,v_DataRow ,i_Datas);
        }
    }
    
    
    
    /**
     * 复制模板工作表的标题区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate     模板信息对象
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     */
    public final static void copyImagesTitle(RTemplate i_RTemplate ,Sheet i_DataSheet, int i_DataRowIndex)
    {
        int v_OffsetRow = i_DataRowIndex - i_RTemplate.getTitleBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitleBeginRow() ,i_RTemplate.getTitleEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的数据区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate     模板信息对象
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     */
    public final static void copyImagesData(RTemplate i_RTemplate ,Sheet i_DataSheet, int i_DataRowIndex)
    {
        int v_OffsetRow = i_DataRowIndex - i_RTemplate.getDataBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getDataBeginRow() ,i_RTemplate.getDataEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的小计区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-27
     * @version     v1.0
     *
     * @param i_RTemplate     模板信息对象
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     */
    public final static void copyImagesSubtotal(RTemplate i_RTemplate ,Sheet i_DataSheet, int i_DataRowIndex)
    {
        int v_OffsetRow = i_DataRowIndex - i_RTemplate.getSubtotalBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getSubtotalBeginRow() ,i_RTemplate.getSubtotalEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的合计区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate     模板信息对象
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     */
    public final static void copyImagesTotal(RTemplate i_RTemplate ,Sheet i_DataSheet, int i_DataRowIndex)
    {
        int v_OffsetRow = i_DataRowIndex - i_RTemplate.getTotalBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTotalBeginRow() ,i_RTemplate.getTotalEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的标题区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate     模板信息对象
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     */
    public final static void copyMergedRegionsTitle(RTemplate i_RTemplate ,Sheet i_DataSheet, int i_DataRowIndex)
    {
        int v_OffsetRow = i_DataRowIndex - i_RTemplate.getTitleBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitleBeginRow() ,i_RTemplate.getTitleEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的数据区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate     模板信息对象
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     */
    public final static void copyMergedRegionsData(RTemplate i_RTemplate ,Sheet i_DataSheet, int i_DataRowIndex)
    {
        int v_OffsetRow = i_DataRowIndex - i_RTemplate.getDataBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getDataBeginRow() ,i_RTemplate.getDataEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的小计区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-27
     * @version     v1.0
     *
     * @param i_RTemplate     模板信息对象
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     */
    public final static void copyMergedRegionsSubtotal(RTemplate i_RTemplate ,Sheet i_DataSheet, int i_DataRowIndex)
    {
        int v_OffsetRow = i_DataRowIndex - i_RTemplate.getSubtotalBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getSubtotalBeginRow() ,i_RTemplate.getSubtotalEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的合计区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate     模板信息对象
     * @param i_DataSheet     数据工作表
     * @param i_DataRowIndex  数据工作表中已写到哪一行的行号。下标从 0 开始。
     */
    public final static void copyMergedRegionsTotal(RTemplate i_RTemplate ,Sheet i_DataSheet, int i_DataRowIndex)
    {
        int v_OffsetRow = i_DataRowIndex - i_RTemplate.getTotalBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTotalBeginRow() ,i_RTemplate.getTotalEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制批注
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-19
     * @version     v1.0
     *
     * @param i_RTemplate     模板对象
     * @param i_TemplateCell  模板单元格对象
     * @param i_DataWorkbook  工作薄对象
     * @param i_DataCell      数据单元格对象
     */
    public final static void copyComment(RTemplate i_RTemplate ,Cell i_TemplateCell ,RWorkbook i_DataWorkbook ,Cell i_DataCell)
    {
        if ( i_TemplateCell.getCellComment() == null )
        {
            return;
        }
        
        Comment      v_TemplateComment = i_TemplateCell.getCellComment();
        ClientAnchor v_TemplateAnchor  = v_TemplateComment.getClientAnchor();
        Comment      v_DataComment     = null;
        
        if ( v_TemplateComment instanceof HSSFComment )
        {
            HSSFPatriarch v_Patriarch = (HSSFPatriarch) i_DataCell.getSheet().createDrawingPatriarch();
            v_DataComment = v_Patriarch.createCellComment(new HSSFClientAnchor(v_TemplateAnchor.getDx1() 
                                                                              ,v_TemplateAnchor.getDy1()
                                                                              ,v_TemplateAnchor.getDx2() 
                                                                              ,v_TemplateAnchor.getDy2() 
                                                                              ,v_TemplateAnchor.getCol1()
                                                                              ,i_DataCell.getRowIndex()
                                                                              ,v_TemplateAnchor.getCol2()
                                                                              ,i_DataCell.getRowIndex() + v_TemplateAnchor.getRow2() - v_TemplateAnchor.getRow1()));
        }
        else if ( v_TemplateComment instanceof XSSFComment )
        {
            XSSFDrawing v_Patriarch = (XSSFDrawing) i_DataCell.getSheet().createDrawingPatriarch();
            v_DataComment = v_Patriarch.createCellComment(new XSSFClientAnchor(v_TemplateAnchor.getDx1() 
                                                                              ,v_TemplateAnchor.getDy1()
                                                                              ,v_TemplateAnchor.getDx2() 
                                                                              ,v_TemplateAnchor.getDy2() 
                                                                              ,v_TemplateAnchor.getCol1()
                                                                              ,i_DataCell.getRowIndex()
                                                                              ,v_TemplateAnchor.getCol2()
                                                                              ,i_DataCell.getRowIndex() + v_TemplateAnchor.getRow2() - v_TemplateAnchor.getRow1()));
        }
        
        v_DataComment.setAuthor( v_TemplateComment.getAuthor());
        v_DataComment.setColumn( v_TemplateComment.getColumn());
        v_DataComment.setRow(    v_TemplateComment.getRow());
        v_DataComment.setVisible(v_TemplateComment.isVisible());
        
        i_DataCell.setCellComment(v_DataComment);
        
        copyRichTextStyleComment(i_RTemplate ,v_TemplateComment.getString() ,i_DataWorkbook ,i_DataCell);
    }
    
    
    
    /**
     * 复制模板工作表的整体(所有)列的列样式到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-19
     * @version     v1.0
     *
     * @param i_RTemplate      模板对象
     * @param i_TemplateSheet  源工作表
     * @param i_DataSheet      目标工作表
     */
    public final static void copyColumnsStyle(RTemplate i_RTemplate ,Sheet i_TemplateSheet ,RWorkbook i_DataWorkbook ,Sheet i_DataSheet) 
    {
        Row v_Row = i_TemplateSheet.getRow(0);
        if ( null == v_Row ) 
        {
            return;
        }
        
        short v_ColumnCount = v_Row.getLastCellNum();
        
        if ( i_TemplateSheet instanceof HSSFSheet )
        {
            HSSFSheet v_DataSheet = (HSSFSheet)i_DataSheet;
            
            for (int v_ColumnIndex = 0; v_ColumnIndex < v_ColumnCount; v_ColumnIndex++) 
            {
                CellStyle v_ColumnStyle = i_TemplateSheet.getColumnStyle(v_ColumnIndex);
                if ( v_ColumnStyle != null )
                {
                    v_DataSheet.setDefaultColumnStyle(v_ColumnIndex ,i_DataWorkbook.getCellStyle(i_RTemplate ,v_ColumnStyle.getIndex()));
                }
            }
        }
        else if ( i_TemplateSheet instanceof XSSFSheet )
        {
            XSSFSheet v_DataSheet = (XSSFSheet)i_DataSheet;
            
            for (int v_ColumnIndex = 0; v_ColumnIndex < v_ColumnCount; v_ColumnIndex++) 
            {
                CellStyle v_ColumnStyle = i_TemplateSheet.getColumnStyle(v_ColumnIndex);
                if ( v_ColumnStyle != null )
                {
                    v_DataSheet.setDefaultColumnStyle(v_ColumnIndex ,i_DataWorkbook.getCellStyle(i_RTemplate ,v_ColumnStyle.getIndex()));
                }
            }
        }
    }
    
    
    
    /**
     * 行复制功能
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate      模板
     * @param i_TemplateRow    模板中的行对象
     * @param i_DataWorkbook   数据工作薄
     * @param i_RSystemValue   系统变量信息
     * @param i_DataRow        数据中的行对象
     * @param i_Datas          本行对应的数据
     * 
     * @return                 返回本方法内一共生成多少新行。
     */
    public final static int copyRow(RTemplate i_RTemplate ,Row i_TemplateRow ,RWorkbook i_DataWorkbook ,RSystemValue i_RSystemValue ,Row i_DataRow ,Object i_Datas) 
    {
        i_DataRow.setHeight(    i_TemplateRow.getHeight());
        i_DataRow.setZeroHeight(i_TemplateRow.getZeroHeight());
        
        int     v_RowNum    = i_DataRow.getRowNum();
        int     v_CellCount = i_TemplateRow.getLastCellNum();
        int     v_ForSize   = 0;
        boolean v_IsFor     = false;
        for (int v_CellIndex=0; v_CellIndex<v_CellCount; v_CellIndex++) 
        {
            Cell v_TemplateCell = i_TemplateRow.getCell(v_CellIndex);
            if ( v_TemplateCell == null ) 
            {
                v_TemplateCell = i_TemplateRow.createCell(v_CellIndex);
            }
            
            Cell v_DataCell = i_DataRow.getCell(v_CellIndex);
            if ( v_DataCell == null ) 
            {
                v_DataCell = i_DataRow.createCell(v_CellIndex);
            }
            
            RValue v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataCell ,i_RSystemValue ,i_Datas ,null);
            
            if ( v_RValue.getIteratorSize() > 0 )
            {
                // 合并前面列的单元格
                if ( !v_IsFor )
                {
                    v_ForSize = v_RValue.getIteratorSize();
                    v_IsFor   = true;
                    
                    i_RSystemValue.setRowSubtotalCount(i_RSystemValue.getRowSubtotalCount() + v_ForSize - 1);
                    
                    // 创建待合并的新行
                    for (int v_RowIndex=1; v_RowIndex<=v_ForSize-1; v_RowIndex++)
                    {
                        if ( i_DataRow.getSheet().getRow(v_RowIndex + v_RowNum) == null )
                        {
                            i_DataRow.getSheet().createRow(v_RowIndex + v_RowNum);
                        }
                    }
                    
                    // 合并
                    for (int v_MergedColIndex=0; v_MergedColIndex<v_CellIndex; v_MergedColIndex++)
                    {
                        // 创建待合并的新列，并设置单元格的格式
                        for (int v_RowIndex=1; v_RowIndex<=v_ForSize-1; v_RowIndex++)
                        {
                            Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex);
                            Cell v_DataForCell = v_DataForRow.getCell(v_MergedColIndex);
                            
                            if ( v_DataForCell == null ) 
                            {
                                v_DataForCell = v_DataForRow.createCell(v_MergedColIndex);
                            }
                            
                            v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                        }
                        
                        i_DataRow.getSheet().addMergedRegion(new CellRangeAddress(v_RowNum 
                                                                                 ,v_RowNum + v_ForSize - 1
                                                                                 ,v_MergedColIndex
                                                                                 ,v_MergedColIndex));
                    }
                }
                
                // 填充小计分项数据
                for (int v_ForIndex=1; v_ForIndex<v_ForSize; v_ForIndex++)
                {
                    Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_ForIndex);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null ) 
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataForCell ,i_RSystemValue ,i_Datas ,v_RValue);
                }
            }
            else if ( v_IsFor )
            {
                // 创建待合并的新列，并设置单元格的格式
                for (int v_RowIndex=1; v_RowIndex<=v_ForSize-1; v_RowIndex++)
                {
                    Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null ) 
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                }
                
                i_DataRow.getSheet().addMergedRegion(new CellRangeAddress(v_RowNum 
                                                                         ,v_RowNum + v_ForSize - 1
                                                                         ,v_CellIndex
                                                                         ,v_CellIndex));
            }
        }
        
        return v_ForSize == 0 ? v_ForSize : v_ForSize - 1;
    }
    
    
    
    /**
     * 复制单位格
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate      模板对象
     * @param i_TemplateCell   模板中的单元格对象
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataCell       数据中的单元格对象
     * @param i_RSystemValue   系统变量信息
     * @param i_Datas          本行对应的数据
     * @param io_RValue        小计循环的迭代器
     * @return                 
     */
    public final static RValue copyCell(RTemplate i_RTemplate ,Cell i_TemplateCell ,RWorkbook i_DataWorkbook ,Cell i_DataCell ,RSystemValue i_RSystemValue ,Object i_Datas ,RValue io_RValue)
    {
        // 复制样式
        i_DataCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,i_TemplateCell.getCellStyle().getIndex()));

        // 复制评论
        copyComment(i_RTemplate ,i_TemplateCell ,i_DataWorkbook ,i_DataCell);
        
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
            RichTextString v_TemplateRichText = i_TemplateCell.getRichStringCellValue();
            String         v_ValueName        = v_TemplateRichText.toString();
            
            if ( i_RTemplate.isExists(v_ValueName) )
            {
                RValue        v_RValue   = i_RTemplate.getValue(v_ValueName ,i_Datas ,i_RSystemValue ,io_RValue);
                ValueListener v_Listener = i_RTemplate.getListener(v_ValueName);
                
                if ( v_Listener != null )
                {
                    v_RValue.setValue(Help.NVL(v_Listener.getValue(i_RTemplate ,i_TemplateCell ,i_DataCell ,i_RSystemValue ,i_Datas ,v_RValue.getValue())));
                }
                
                if ( null != v_RValue.getValue() )
                {
                    i_DataCell.setCellValue(v_RValue.getValue().toString());
                }
                else
                {
                    i_DataCell.setCellValue("");
                }
                
                return v_RValue;
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
        
        return io_RValue == null ? new RValue() : io_RValue;
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
    public final static void copyRichTextStyle(RTemplate i_RTemplate ,RichTextString i_TemplateRichText ,RWorkbook i_DataWorkbook ,Cell i_DataCell) 
    {
        int    v_FontCount = i_TemplateRichText.numFormattingRuns();
        String v_Text      = i_TemplateRichText.toString();
        int    v_TextLen   = v_Text.length();
        
        if ( v_FontCount >= 1 )
        {
            RichTextString v_DataRichText = null;
            
            if ( i_TemplateRichText instanceof HSSFRichTextString )
            {
                v_DataRichText = new HSSFRichTextString(v_Text);
                
                for (int v_FontIndex=v_FontCount-1; v_FontIndex >= 0; v_FontIndex--) 
                {
                    int   v_FirstIndex = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
                    short v_IDX        = ((HSSFRichTextString)i_TemplateRichText).getFontOfFormattingRun(v_FontIndex);
                    Font  v_DataFont   = i_DataWorkbook.getFont(i_RTemplate ,v_IDX);
                    
                    v_DataRichText.applyFont(v_FirstIndex, v_TextLen, v_DataFont);
                    v_TextLen = v_FirstIndex;
                }
                
                i_DataCell.setCellValue(v_DataRichText);
            }
            else if ( i_TemplateRichText instanceof XSSFRichTextString )
            {
                i_DataCell.setCellValue(i_TemplateRichText);
//                v_DataRichText = new XSSFRichTextString(v_Text); // i_DataCell.getRow().getSheet().getWorkbook().getCreationHelper().createRichTextString(v_Text);
//                
//                for (int v_FontIndex=v_FontCount-1; v_FontIndex >= 0; v_FontIndex--) 
//                {
//                    int  v_FirstIndex   = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
//                    Font v_TemplateFont = ((XSSFRichTextString)i_TemplateRichText).getFontOfFormattingRun(v_FontIndex);
//                    if ( v_TemplateFont != null )
//                    {
//                        v_DataRichText.applyFont(v_FirstIndex, v_TextLen, v_TemplateFont);
//                    }
//                    
//                    v_TextLen = v_FirstIndex;
//                }
            }
        }
        else
        {
            i_DataCell.setCellValue(v_Text);
        }
    }
    
    
    
    /**
     * 复制批注的高级文本及格式
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-19
     * @version     v1.0
     *
     * @param i_RTemplate         模板对象
     * @param i_TemplateRichText  高级文本对象
     * @param i_DataWorkbook      数据工作薄
     * @param i_DataCell          数据单元格
     */
    public final static void copyRichTextStyleComment(RTemplate i_RTemplate ,RichTextString i_TemplateRichText ,RWorkbook i_DataWorkbook ,Cell i_DataCell) 
    {
        int    v_FontCount = i_TemplateRichText.numFormattingRuns();
        String v_Text      = i_TemplateRichText.toString();
        int    v_TextLen   = v_Text.length();
        
        RichTextString v_DataRichText = null;
        
        if ( i_TemplateRichText instanceof HSSFRichTextString )
        {
            v_DataRichText = new HSSFRichTextString(v_Text);
            
            for (int v_FontIndex=v_FontCount-1; v_FontIndex >= 0; v_FontIndex--) 
            {
                int   v_FirstIndex = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
                short v_IDX        = ((HSSFRichTextString)i_TemplateRichText).getFontOfFormattingRun( v_FontIndex);
                Font  v_DataFont   = i_DataWorkbook.getFont(i_RTemplate ,v_IDX);
                
                v_DataRichText.applyFont(v_FirstIndex, v_TextLen, v_DataFont);
                v_TextLen = v_FirstIndex;
            }
            
            i_DataCell.getCellComment().setString(v_DataRichText);
        }
        else if ( i_TemplateRichText instanceof XSSFRichTextString )
        {
            i_DataCell.getCellComment().setString(i_TemplateRichText);
        }
    }
    
}
