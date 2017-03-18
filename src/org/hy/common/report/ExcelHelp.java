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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hy.common.Help;
import org.hy.common.report.bean.RTemplate;





/**
 * TODO(请详细描述类型的作用。描述后请删除todo标签) 
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
            if ( i_ExcelFileName.startsWith(Help.getSysPathSeparator()) )
            {
                v_Input = new FileInputStream(i_ExcelFileName);
            }
            else
            {
                URL v_URL = new URL(i_ExcelFileName);
                v_Input = new FileInputStream(v_URL.getFile());
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
     * 创建一个工作薄
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @return
     */
    public final static HSSFWorkbook createWorkbook()
    {
        return new HSSFWorkbook();
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
    public final static HSSFSheet createSheet(HSSFWorkbook i_Workbook ,String i_SheetName) 
    {
        int    v_SheetCount = i_Workbook.getNumberOfSheets();
        String v_SheetName  = i_SheetName;
        
        if ( Help.isNull(v_SheetName) ) 
        {
            v_SheetName = "sheet" + (v_SheetCount + 1);
        }
        
        return i_Workbook.createSheet(v_SheetName);
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
    public final static HSSFWorkbook write(String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate)
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
    public final static HSSFWorkbook write(HSSFWorkbook i_Workbook ,String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate)
    {
        HSSFWorkbook v_Workbook      = i_Workbook;
        HSSFSheet    v_DataSheet     = null;
        HSSFSheet    v_TemplateSheet = null;
        
        if ( null == v_Workbook )
        {
            v_Workbook = createWorkbook();
        }
        
        v_DataSheet     = createSheet(v_Workbook ,i_SheetName);
        v_TemplateSheet = i_RTemplate.getTemplateSheet();
        
        copyFonts(v_TemplateSheet.getWorkbook() ,v_Workbook);
        // 数据工作表的整体(所有)列的列宽，复制于模板
        copyColumnsWidth(v_TemplateSheet ,v_DataSheet);
        // 数据工作表的打印区域，复制于模板
        copyPrintSetup  (v_TemplateSheet ,v_DataSheet);
        
        writeTitle(v_DataSheet ,i_Datas ,i_RTemplate);
        
        for (int v_DataIndex=1; v_DataIndex<=i_Datas.size(); v_DataIndex++)
        {
            writeData(v_DataSheet ,v_DataIndex ,i_Datas.get(v_DataIndex - 1) ,i_RTemplate);
        }
        
        return v_Workbook;
    }
    
    
    
    /**
     * 复制模板工作表的整体(所有)列的列宽到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_TemplateSheet  模板工作表
     * @param i_DataSheet      数据工作表
     */
    public final static void copyColumnsWidth(HSSFSheet i_TemplateSheet ,HSSFSheet i_DataSheet) 
    {
        HSSFRow v_Row = i_TemplateSheet.getRow(0);
        if ( null == v_Row ) 
        {
            return;
        }
        
        short v_ColumnCount = v_Row.getLastCellNum();

        for (int v_ColumnIndex = 0; v_ColumnIndex < v_ColumnCount; v_ColumnIndex++) 
        {
            int v_Width = i_TemplateSheet.getColumnWidth(v_ColumnIndex);
            i_DataSheet.setColumnWidth(v_ColumnIndex ,v_Width);
        }
    }
    
    
    
    /**
     * 复制模板工作表的打印区域到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     * 
     * @param i_TemplateSheet  模板工作表
     * @param i_DataSheet      数据工作表
     */
    public final static void copyPrintSetup(HSSFSheet i_TemplateSheet ,HSSFSheet i_DataSheet) 
    {
        HSSFPrintSetup v_TemplatePrintSetup = i_TemplateSheet.getPrintSetup();
        HSSFPrintSetup v_DataPrintSetup     = i_DataSheet    .getPrintSetup();
        
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
        i_TemplateSheet.setDisplayGridlines(false);
        i_TemplateSheet.setPrintGridlines(  false);
        i_TemplateSheet.setMargin(HSSFSheet.TopMargin    ,i_DataSheet.getMargin(HSSFSheet.TopMargin));    // 页边距（上）
        i_TemplateSheet.setMargin(HSSFSheet.BottomMargin ,i_DataSheet.getMargin(HSSFSheet.BottomMargin)); // 页边距（下）
        i_TemplateSheet.setMargin(HSSFSheet.LeftMargin   ,i_DataSheet.getMargin(HSSFSheet.LeftMargin));   // 页边距（左）
        i_TemplateSheet.setMargin(HSSFSheet.RightMargin  ,i_DataSheet.getMargin(HSSFSheet.RightMargin));  // 页边距（右）
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
        
        copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitleBeginRow() ,i_RTemplate.getTitleEndRow() ,i_DataSheet ,v_OffsetRow);
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
        
        copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getDataBeginRow() ,i_RTemplate.getDataEndRow() ,i_DataSheet ,v_OffsetRow);
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
        
        copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTotalBeginRow() ,i_RTemplate.getTotalEndRow() ,i_DataSheet ,v_OffsetRow);
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
        
        copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitleBeginRow() ,i_RTemplate.getTitleEndRow() ,i_DataSheet ,v_OffsetRow);
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
        
        copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getDataBeginRow() ,i_RTemplate.getDataEndRow() ,i_DataSheet ,v_OffsetRow);
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
        
        copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTotalBeginRow() ,i_RTemplate.getTotalEndRow() ,i_DataSheet ,v_OffsetRow);
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
     * 行复制功能
     * 
     * @param fromRow
     * @param toRow
     */
    public final static void copyRow(RTemplate i_RTemplate ,HSSFRow i_TemplateRow ,HSSFRow i_DataRow ,Object i_Datas) 
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
            
            copyCell(i_RTemplate ,v_TemplateCell ,v_DataCell ,i_Datas);
        }
    }
    
    
    
    /**
     * 复制单元格
     * 
     * @param srcCell
     * @param distCell
     * @param copyValueFlag
     *            true则连同cell的内容一起复制
     */
    public final static void copyCell(RTemplate i_RTemplate ,HSSFCell i_TemplateCell ,HSSFCell i_DataCell ,Object i_Datas)
    {
        // 复制样式
        HSSFCellStyle v_CellStyle = (HSSFCellStyle)i_DataCell.getSheet().getWorkbook().createCellStyle();
        copyCellStyle(i_RTemplate ,i_TemplateCell.getCellStyle() ,v_CellStyle);
        i_DataCell.setCellStyle(v_CellStyle);
        
        if ( i_TemplateCell.getCellComment() != null ) 
        {
            // 复制评论
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
                copyRichTextStyle(i_RTemplate ,v_TemplateRichText ,i_DataCell);
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
    
    
    
    public final static void copyRichTextStyle(RTemplate i_RTemplate ,HSSFRichTextString i_TemplateRichText ,HSSFCell i_DataCell) 
    {
        HSSFWorkbook v_TemplateWorkbook = i_RTemplate.getTemplateSheet().getWorkbook();
        int          v_FontCount        = i_TemplateRichText.numFormattingRuns();
        String       v_Text             = i_TemplateRichText.toString();
        int          v_TextLen          = v_Text.length();
        
        if ( v_FontCount >= 1 )
        {
            HSSFRichTextString v_DataRichTextString = new HSSFRichTextString(v_Text);
            
            for (int v_FontIndex=v_FontCount-1; v_FontIndex >= 0; v_FontIndex--) 
            {
                int      v_FirstIndex   = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
                short    v_IDX          = i_TemplateRichText.getFontOfFormattingRun( v_FontIndex);
                HSSFFont v_TemplateFont = v_TemplateWorkbook.getFontAt(v_IDX);
                HSSFFont v_DataFont     = i_DataCell.getSheet().getWorkbook().getFontAt(v_IDX);
                
                if ( v_DataFont == null )
                {
                    v_DataFont = i_DataCell.getSheet().getWorkbook().createFont();
                    copyFont(v_TemplateFont ,v_DataFont);
                }
                
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
    
    
    
    /**
     * 复制工作薄中的所有字体
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_TemplateWorkbook  模板工作薄
     * @param i_DataWorkbook      数据工作薄
     */
    public final static void copyFonts(Workbook i_TemplateWorkbook ,Workbook i_DataWorkbook)
    {
        short v_FontCount = i_TemplateWorkbook.getNumberOfFonts();
        
        if ( i_DataWorkbook.getNumberOfFonts() >= v_FontCount ) 
        {
            return;
        }
        
        for (short v_FontIndex=0; v_FontIndex<v_FontCount; v_FontIndex++)
        {
            Font v_TemplateFont = i_TemplateWorkbook.getFontAt(v_FontIndex);
            Font v_DataFont     = i_DataWorkbook.createFont();
            
            copyFont(v_TemplateFont ,v_DataFont);
        }
    }
    
    
    
    /**
     * 复制字体
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_TemplateFont  模板字体
     * @param i_DataFont      数据字体
     */
    public final static void copyFont(Font i_TemplateFont ,Font i_DataFont)
    {
        i_DataFont.setBold(              i_TemplateFont.getBold());
        i_DataFont.setCharSet(           i_TemplateFont.getCharSet());
        i_DataFont.setColor(             i_TemplateFont.getColor());
        i_DataFont.setFontHeight(        i_TemplateFont.getFontHeight());
        i_DataFont.setFontHeightInPoints(i_TemplateFont.getFontHeightInPoints());
        i_DataFont.setFontName(          i_TemplateFont.getFontName());
        i_DataFont.setItalic(            i_TemplateFont.getItalic());
        i_DataFont.setStrikeout(         i_TemplateFont.getStrikeout());
        i_DataFont.setTypeOffset(        i_TemplateFont.getTypeOffset());
        i_DataFont.setUnderline(         i_TemplateFont.getUnderline());
    }
    
    
    
    /**
     * 复制一个单元格样式到目的单元格样式
     * 
     * @param fromStyle
     * @param toStyle
     */
    public final static void copyCellStyle(RTemplate i_RTemplate ,HSSFCellStyle i_TemplateCellStyle ,HSSFCellStyle i_DataCellStyle)
    {
        i_DataCellStyle.setAlignment(i_TemplateCellStyle.getAlignmentEnum());
        
        //边框和边框颜色
        i_DataCellStyle.setBorderBottom(i_TemplateCellStyle.getBorderBottomEnum());
        i_DataCellStyle.setBorderLeft(i_TemplateCellStyle.getBorderLeftEnum());
        i_DataCellStyle.setBorderRight(i_TemplateCellStyle.getBorderRightEnum());
        i_DataCellStyle.setBorderTop(i_TemplateCellStyle.getBorderTopEnum());
        i_DataCellStyle.setBottomBorderColor(i_TemplateCellStyle.getBottomBorderColor());
        i_DataCellStyle.setDataFormat(i_TemplateCellStyle.getDataFormat());
        
        //背景和前景
        i_DataCellStyle.setFillBackgroundColor(i_TemplateCellStyle.getFillBackgroundColor());
        i_DataCellStyle.setFillForegroundColor(i_TemplateCellStyle.getFillForegroundColor());
        i_DataCellStyle.setFillPattern(i_TemplateCellStyle.getFillPatternEnum());
        i_DataCellStyle.setFont(i_TemplateCellStyle.getFont(i_RTemplate.getTemplateSheet().getWorkbook()));
        i_DataCellStyle.setHidden(i_TemplateCellStyle.getHidden());
        
        //首行缩进
        i_DataCellStyle.setIndention(i_TemplateCellStyle.getIndention());
        i_DataCellStyle.setLeftBorderColor(i_TemplateCellStyle.getLeftBorderColor());
        i_DataCellStyle.setLocked(i_TemplateCellStyle.getLocked());
        i_DataCellStyle.setRightBorderColor(i_TemplateCellStyle.getRightBorderColor());
        
        //旋转
        i_DataCellStyle.setShrinkToFit(i_TemplateCellStyle.getShrinkToFit());
        i_DataCellStyle.setRotation(i_TemplateCellStyle.getRotation());
        i_DataCellStyle.setTopBorderColor(i_TemplateCellStyle.getTopBorderColor());
        i_DataCellStyle.setVerticalAlignment(i_TemplateCellStyle.getVerticalAlignmentEnum());
        i_DataCellStyle.setWrapText(i_TemplateCellStyle.getWrapText());
    }
    
    
    
    /**
     * 按报表模板格式写标题
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_DataSheet  数据工作表
     * @param i_DataIndex  数据索引号。下标从 1 开始
     * @param i_Datas      数据
     * @param i_RTemplate  报表模板对象
     */
    public final static void writeTitle(HSSFSheet i_DataSheet ,Object i_Datas ,RTemplate i_RTemplate) 
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
            
            copyRow(i_RTemplate ,v_TemplateRow ,v_DataRow ,i_Datas);
        }
    } 
    
    
    
    /**
     * 按报表模板格式写入一行数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_DataSheet  数据工作表
     * @param i_DataIndex  数据索引号。下标从 1 开始
     * @param i_Datas      数据
     * @param i_RTemplate  报表模板对象
     */
    public final static void writeData(HSSFSheet i_DataSheet ,int i_DataIndex, Object i_Datas ,RTemplate i_RTemplate) 
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
            
            copyRow(i_RTemplate ,v_TemplateRow ,v_DataRow ,i_Datas);
        }
    }
    
}