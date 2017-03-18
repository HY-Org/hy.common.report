package org.hy.common.report;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hy.common.Date;
import org.hy.common.Help;





/**
 * Excel操作的辅助类 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-16
 * @version     v1.0
 */
public class ExcelHelp
{
    
    
    /**
     * 私有构建器
     */
    protected ExcelHelp()
    {
        
    }
    
    
    
    /**
     * 读取Excel中所有的工作表对象
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_ExcelFileName  文件全路径+文件名称
     * @return
     */
    public final static List<HSSFSheet> read(String i_ExcelFileName)
    {
        List<HSSFSheet> v_Ret   = new ArrayList<HSSFSheet>();
        InputStream     v_Input = null;
        
        try
        {
            if ( i_ExcelFileName.startsWith("file:") )
            {
                URL v_URL = new URL(i_ExcelFileName);
                v_Input = new FileInputStream(v_URL.getFile());
            }
            else
            {
                v_Input = new FileInputStream(i_ExcelFileName);
            }
            
            HSSFWorkbook v_Workbook   = (HSSFWorkbook)WorkbookFactory.create(v_Input);
            int          v_SheetCount = v_Workbook.getNumberOfSheets();
            
            for (int v_Index=0; v_Index<v_SheetCount; v_Index++)
            {
                v_Ret.add(v_Workbook.getSheetAt(v_Index));
            }
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
        }
        finally
        {
            if ( null != v_Input )
            {
                try
                {
                    v_Input.close();
                }
                catch (Exception exce)
                {
                    // Nothing.
                }
            }
        }
        
        return v_Ret;
    }
    
    
    
    public final static Map<String ,Object> readDatas(String i_ExcelFileName ,int i_SheetIndex)
    {
        return readDatas(i_ExcelFileName ,i_SheetIndex ,null ,null);
    }
    
    
    
    public final static Map<String ,Object> readDatas(String i_ExcelFileName ,int i_SheetIndex ,Integer i_BeginRow)
    {
        return readDatas(i_ExcelFileName ,i_SheetIndex ,i_BeginRow ,null);
    }
    
    
    
    public final static Map<String ,Object> readDatas(String i_ExcelFileName ,int i_SheetIndex ,Integer i_BeginRow ,Integer i_EndRow)
    {
        return readDatas(read(i_ExcelFileName).get(i_SheetIndex) ,i_BeginRow ,i_EndRow);
    }
    
    
    
    public final static Map<String ,Object> readDatas(HSSFSheet i_Sheet)
    {
        return readDatas(i_Sheet ,null ,null);
    }
    
    
    
    public final static Map<String ,Object> readDatas(HSSFSheet i_Sheet ,Integer i_BeginRow)
    {
        return readDatas(i_Sheet ,i_BeginRow ,null);
    }
    
    
    
    public final static Map<String ,Object> readDatas(HSSFSheet i_Sheet ,Integer i_BeginRow ,Integer i_EndRow)
    {
        Map<String ,Object> v_Ret      = new LinkedHashMap<String ,Object>();
        HSSFSheet           v_Sheet    = i_Sheet;
        int                 v_BeginRow = 0;
        int                 v_EndRow   = 0;
        
        if ( i_BeginRow != null )
        {
            v_BeginRow = i_BeginRow.intValue();
            
            if ( v_BeginRow < 0 )
            {
                v_BeginRow = 0;
            }
        }
        
        if ( i_EndRow != null )
        {
            v_EndRow = i_EndRow.intValue();
        }
        else
        {
            v_EndRow = v_Sheet.getPhysicalNumberOfRows();
        }
        
        for (int v_RowNo=v_BeginRow; v_RowNo<=v_EndRow; v_RowNo++)
        {
            HSSFRow v_Row = v_Sheet.getRow(v_RowNo);
            if ( v_Row == null )
            {
                continue;
            }
            
            short v_CellCount = v_Row.getLastCellNum();
            
            for (int v_ColumnNo=0; v_ColumnNo<v_CellCount; v_ColumnNo++)
            {
                HSSFCell v_Cell = v_Row.getCell(v_ColumnNo);
                if ( v_Cell == null )
                {
                    continue;
                }
                
                if ( v_Cell.getCellTypeEnum() == CellType.STRING )
                {
                    String v_Value = v_Cell.getStringCellValue();
                    
                    if ( !Help.isNull(v_Value) )
                    {
                        v_Ret.put(v_Value.trim() ,null);
                    }
                }
                else if ( v_Cell.getCellTypeEnum() == CellType.NUMERIC )
                {
                    if ( HSSFDateUtil.isCellDateFormatted(v_Cell) ) 
                    {
                        if ( v_Cell.getDateCellValue() != null )
                        {
                            v_Ret.put((new Date(v_Cell.getDateCellValue())).getFull() ,null);
                        }
                    } 
                    else 
                    {
                        v_Ret.put(String.valueOf(v_Cell.getNumericCellValue()) ,null);
                    }
                }
            }
        }
        
        return v_Ret;
    }
    
    
    
    /**
     * 保存工作薄
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_Workbook  工作薄对象
     * @param i_SaveFile  保存的全路径+文件名称。当没有写扩展名称或类型不匹配时，自动识别添加
     * @return            保存成功返回：文件全路径+文件名称；异常返回：null
     */
    public final static String save(Workbook i_Workbook ,String i_SaveFile)
    {
        String v_SaveFile = i_SaveFile.trim();
        if ( i_Workbook instanceof HSSFWorkbook )
        {
            if ( !v_SaveFile.toLowerCase().endsWith(".xls") )
            {
                v_SaveFile += ".xls";
            }
        }
        else if ( i_Workbook instanceof XSSFWorkbook )
        {
            if ( !v_SaveFile.toLowerCase().endsWith(".xlsx") )
            {
                v_SaveFile += ".xlsx";
            }
        }
        
        FileOutputStream v_Output   = null;
        
        try
        {            
            
            v_Output = new FileOutputStream(v_SaveFile);
            i_Workbook.write(v_Output);
        }
        catch (Exception exce)
        {
            v_SaveFile = null;
            exce.printStackTrace();
        }
        finally
        {
            if ( v_Output != null )
            {
                try
                {
                    v_Output.flush();
                }
                catch (Exception exce)
                {
                    // Nothing.
                }
                
                try
                {
                    v_Output.close();
                }
                catch (Exception exce)
                {
                    // Nothing.
                }
                
                v_Output = null;
            }
        }
        
        return v_SaveFile;
    }
    
    
    
    /**
     * 创建一个工作表
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_Workbook   工作薄对象
     * @param i_SheetName  工作表名称(当为空时，自动生成)
     * @return
     */
    public final static HSSFSheet createSheet(Workbook i_Workbook ,String i_SheetName) 
    {
        int    v_SheetCount = i_Workbook.getNumberOfSheets();
        String v_SheetName  = i_SheetName;
        
        if ( Help.isNull(v_SheetName) ) 
        {
            v_SheetName = "sheet" + (v_SheetCount + 1);
        }
        
        return (HSSFSheet)i_Workbook.createSheet(v_SheetName);
    }
    
    
    
    /**
     * 复制模板工作表的整体(所有)列的列宽到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_FormSheet  源工作表
     * @param i_ToSheet    目标工作表
     */
    public final static void copyColumnsWidth(HSSFSheet i_FormSheet ,HSSFSheet i_ToSheet) 
    {
        HSSFRow v_Row = i_FormSheet.getRow(0);
        if ( null == v_Row ) 
        {
            return;
        }
        
        short v_ColumnCount = v_Row.getLastCellNum();

        for (int v_ColumnIndex = 0; v_ColumnIndex < v_ColumnCount; v_ColumnIndex++) 
        {
            int v_Width = i_FormSheet.getColumnWidth(v_ColumnIndex);
            i_ToSheet.setColumnWidth(v_ColumnIndex ,v_Width);
        }
    }
    
    
    
    /**
     * 复制模板工作表的打印区域到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     * 
     * @param i_FormSheet  源工作表
     * @param i_ToSheet    目标工作表
     */
    public final static void copyPrintSetup(HSSFSheet i_FormSheet ,HSSFSheet i_ToSheet) 
    {
        HSSFPrintSetup v_TemplatePrintSetup = i_FormSheet.getPrintSetup();
        HSSFPrintSetup v_DataPrintSetup     = i_ToSheet    .getPrintSetup();
        
        v_DataPrintSetup.setCopies(       v_TemplatePrintSetup.getCopies());
        v_DataPrintSetup.setDraft(        v_TemplatePrintSetup.getDraft());
        v_DataPrintSetup.setFitHeight(    v_TemplatePrintSetup.getFitHeight());
        v_DataPrintSetup.setFitWidth(     v_TemplatePrintSetup.getFitWidth());
        v_DataPrintSetup.setFooterMargin( v_TemplatePrintSetup.getFooterMargin());
        v_DataPrintSetup.setHeaderMargin( v_TemplatePrintSetup.getHeaderMargin());
        v_DataPrintSetup.setHResolution(  v_TemplatePrintSetup.getHResolution());
        v_DataPrintSetup.setLandscape(    v_TemplatePrintSetup.getLandscape());
        v_DataPrintSetup.setLeftToRight(  v_TemplatePrintSetup.getLeftToRight());
        v_DataPrintSetup.setNoColor(      v_TemplatePrintSetup.getNoColor());
        v_DataPrintSetup.setNoOrientation(v_TemplatePrintSetup.getNoOrientation());
        v_DataPrintSetup.setNotes(        v_TemplatePrintSetup.getNotes());
        v_DataPrintSetup.setPageStart(    v_TemplatePrintSetup.getPageStart());
        v_DataPrintSetup.setPaperSize(    v_TemplatePrintSetup.getPaperSize());
        v_DataPrintSetup.setScale(        v_TemplatePrintSetup.getScale());
        v_DataPrintSetup.setUsePage(      v_TemplatePrintSetup.getUsePage());
        v_DataPrintSetup.setValidSettings(v_TemplatePrintSetup.getValidSettings());
        v_DataPrintSetup.setVResolution(  v_TemplatePrintSetup.getVResolution());
        v_DataPrintSetup.setPaperSize(    v_TemplatePrintSetup.getPaperSize());  // 纸张类型 A4纸 HSSFPrintSetup.A4_PAPERSIZE
        
        // 设置打印参数
        i_ToSheet.setDisplayGridlines(false);
        i_ToSheet.setPrintGridlines(  false);
        i_ToSheet.setMargin(HSSFSheet.TopMargin    ,i_ToSheet.getMargin(HSSFSheet.TopMargin));    // 页边距（上）
        i_ToSheet.setMargin(HSSFSheet.BottomMargin ,i_ToSheet.getMargin(HSSFSheet.BottomMargin)); // 页边距（下）
        i_ToSheet.setMargin(HSSFSheet.LeftMargin   ,i_ToSheet.getMargin(HSSFSheet.LeftMargin));   // 页边距（左）
        i_ToSheet.setMargin(HSSFSheet.RightMargin  ,i_ToSheet.getMargin(HSSFSheet.RightMargin));  // 页边距（右）
    }
    
    
    
    /**
     * 复制模板工作表的合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     * 
     * @param i_TemplateSheet  模板工作表
     * @param i_AreaBeginRow   定指区域内的开始行号。包含此行。
     * @param i_AreaEndRow     定指区域内的结束行号。包含此行。
     * @param i_DataSheet      数据工作表
     * @param i_OffsetRow      偏移行号
     */
    public final static void copyMergedRegions(HSSFSheet i_TemplateSheet ,int i_AreaBeginRow ,int i_AreaEndRow ,HSSFSheet i_DataSheet ,int i_OffsetRow) 
    {
        int v_MergedRegionsCount = i_TemplateSheet.getNumMergedRegions();
        
        for (int i=0; i<v_MergedRegionsCount; i++) 
        {
            CellRangeAddress v_CellRangeAddress = i_TemplateSheet.getMergedRegion(i);
            
            int v_FirstRow    = v_CellRangeAddress.getFirstRow();
            int v_LastRow     = v_CellRangeAddress.getLastRow();
            int v_FirstColumn = v_CellRangeAddress.getFirstColumn();
            int v_LastColumn  = v_CellRangeAddress.getLastColumn();
            
            if ( i_AreaBeginRow <= v_FirstRow 
              && i_AreaEndRow   >= v_LastRow )
            {
                // Nothing. 在区域内的
            }
            else
            {
                continue;
            }

            v_FirstRow += i_OffsetRow;
            v_LastRow  += i_OffsetRow;
            
            i_DataSheet.addMergedRegion(new CellRangeAddress(v_FirstRow 
                                                            ,v_LastRow 
                                                            ,v_FirstColumn
                                                            ,v_LastColumn));
        }
    }
    
    
    
    /**
     * 复制模板工作表的定指区域内的所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_TemplateSheet  模板工作表
     * @param i_AreaBeginRow   定指区域内的开始行号。包含此行。
     * @param i_AreaEndRow     定指区域内的结束行号。包含此行。
     * @param i_DataSheet      数据工作表
     * @param i_OffsetRow      偏移行号
     */
    public final static void copyImages(HSSFSheet i_TemplateSheet ,int i_AreaBeginRow ,int i_AreaEndRow ,HSSFSheet i_DataSheet, int i_OffsetRow)
    {
        List<HSSFPictureData> v_Pictures = i_TemplateSheet.getWorkbook().getAllPictures();
        
        if ( i_TemplateSheet.getDrawingPatriarch() != null ) 
        {
            for (HSSFShape v_Shape : i_TemplateSheet.getDrawingPatriarch().getChildren()) 
            {
                if ( v_Shape instanceof HSSFPicture) 
                {
                    HSSFPicture      v_Picture       = (HSSFPicture)      v_Shape;
                    HSSFClientAnchor v_Anchor        = (HSSFClientAnchor) v_Shape.getAnchor();
                    HSSFPictureData  v_PictureData   = v_Pictures.get(v_Picture.getPictureIndex() - 1);
                    
                    if ( i_AreaBeginRow <= v_Anchor.getRow1() 
                      && i_AreaEndRow   >= v_Anchor.getRow2() )
                    {
                        // Nothing. 在数据区域内的图片
                    }
                    else
                    {
                        continue;
                    }
                    
                    HSSFPatriarch    v_DataPatriarch = i_DataSheet.createDrawingPatriarch();
                    HSSFClientAnchor v_DataAnchor    = new HSSFClientAnchor(v_Anchor.getDx1()
                                                                           ,Math.min(v_Anchor.getDy1() ,255)
                                                                           ,v_Anchor.getDx2()
                                                                           ,Math.min(v_Anchor.getDy2() ,255)
                                                                           ,v_Anchor.getCol1()
                                                                           ,v_Anchor.getRow1() + i_OffsetRow
                                                                           ,v_Anchor.getCol2()
                                                                           ,v_Anchor.getRow2() + i_OffsetRow);
                    
                    v_DataAnchor.setAnchorType(v_Anchor.getAnchorType());
                    
                    v_DataPatriarch.createPicture(v_DataAnchor
                                                 ,i_DataSheet.getWorkbook().addPicture(v_PictureData.getData() ,v_PictureData.getPictureType()));
                }
            }
        }
    }
    
    
    
    /**
     * 复制字体
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_FormFont  源字体
     * @param i_ToFont    目标字体
     */
    public final static void copyFont(Font i_FormFont ,Font i_ToFont)
    {
        i_ToFont.setBold(              i_FormFont.getBold());
        i_ToFont.setCharSet(           i_FormFont.getCharSet());
        i_ToFont.setColor(             i_FormFont.getColor());
        i_ToFont.setFontHeight(        i_FormFont.getFontHeight());
        i_ToFont.setFontHeightInPoints(i_FormFont.getFontHeightInPoints());
        i_ToFont.setFontName(          i_FormFont.getFontName());
        i_ToFont.setItalic(            i_FormFont.getItalic());
        i_ToFont.setStrikeout(         i_FormFont.getStrikeout());
        i_ToFont.setTypeOffset(        i_FormFont.getTypeOffset());
        i_ToFont.setUnderline(         i_FormFont.getUnderline());
    }
    
    
    
    /**
     * 复制单元格样式
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_FormCellStyle  源单元格样式
     * @param i_ToCellStyle    目标单元格样式
     */
    public final static void copyCellStyle(CellStyle i_FormCellStyle ,CellStyle i_ToCellStyle)
    {
        i_ToCellStyle.setAlignment(          i_FormCellStyle.getAlignmentEnum());
        
        //边框和边框颜色
        i_ToCellStyle.setBorderBottom(       i_FormCellStyle.getBorderBottomEnum());
        i_ToCellStyle.setBorderLeft(         i_FormCellStyle.getBorderLeftEnum());
        i_ToCellStyle.setBorderRight(        i_FormCellStyle.getBorderRightEnum());
        i_ToCellStyle.setBorderTop(          i_FormCellStyle.getBorderTopEnum());
        i_ToCellStyle.setBottomBorderColor(  i_FormCellStyle.getBottomBorderColor());
        i_ToCellStyle.setDataFormat(         i_FormCellStyle.getDataFormat());
        
        //背景和前景
        i_ToCellStyle.setFillBackgroundColor(i_FormCellStyle.getFillBackgroundColor());
        i_ToCellStyle.setFillForegroundColor(i_FormCellStyle.getFillForegroundColor());
        i_ToCellStyle.setFillPattern(        i_FormCellStyle.getFillPatternEnum());
        i_ToCellStyle.setHidden(             i_FormCellStyle.getHidden());
        
        //首行缩进
        i_ToCellStyle.setIndention(          i_FormCellStyle.getIndention());
        i_ToCellStyle.setLeftBorderColor(    i_FormCellStyle.getLeftBorderColor());
        i_ToCellStyle.setLocked(             i_FormCellStyle.getLocked());
        i_ToCellStyle.setRightBorderColor(   i_FormCellStyle.getRightBorderColor());
        
        //旋转
        i_ToCellStyle.setShrinkToFit(        i_FormCellStyle.getShrinkToFit());
        i_ToCellStyle.setRotation(           i_FormCellStyle.getRotation());
        i_ToCellStyle.setTopBorderColor(     i_FormCellStyle.getTopBorderColor());
        i_ToCellStyle.setVerticalAlignment(  i_FormCellStyle.getVerticalAlignmentEnum());
        i_ToCellStyle.setWrapText(           i_FormCellStyle.getWrapText());
    }
    
}