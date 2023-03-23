package org.hy.common.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
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
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hy.common.Help;
import org.hy.common.StringHelp;
import org.hy.common.report.bean.RPosition;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RTotal;
import org.hy.common.report.bean.RValue;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.event.ValueListener;





/**
 * Excel模板占位符为依据，Java对象映射转为Excel数据文件。
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 *              v2.0  2017-06-21  优化：通过isSafe参数控制，放弃一些非必要的效验来提高性能
 *                                优化：启用对SXSSFWorkbook工作薄的支持大数据量
 *                                添加：当模板的单元格为数字格式的，并且导出文本也为数字字符，将转为数字写入到单元格中。
 *                                     这种做的好处是：支持0.123456789数字的写入单元格的同时还将只表面显示n位小数(n也在模板是设置)
 *              v3.0  2017-06-25  优化：通过RTemplate.check()方法，预先在生成报表前，对模板信息检查。
 *                                     就不用在生成报表时动态检查模板信息。
 *              v4.0  2017-06-28  添加：支持分页功能。比原Excel页眉、页脚更高级、内容更丰富的分页页眉、分页页脚功能。
 *              v4.1  2017-06-29  添加：工作表写入数据完成的自定义事件机制，方便用户做后续操作。
 *                                添加：支持首个分页页眉与其后分页页眉的差异化内容及样式的功能。通过RTemplate.titlePageHeaderFirstWriteByRow参数调节。
 *              v4.2  2017-07-03  添加：在有分页页脚的情况下，通过填充空白行的方法，将最后一页填充完整，好将分页页脚放在页脚的位置上。
 *              v4.3  2017-07-11  发现：copyRow(...)方法中，当isBig=true、 rowAccessWindowSize<v_ForSize 时，v_DataForRow会出现空的情况。
 *                                     原因是：SXSSFWorkbook缓存在内存中的行数是有限的。发现人：李浩
 *              v4.4  2017-07-19  添加：是否将整数显示为小数的形式的选择开功能。需Excel模板配合设置单元格的格式为：小数格式(0.000 或 0.###)
 *              v4.5  2017-07-31  添加：Excel高级筛选，由报表配置文件参数(isExcelFilter)控制生成的功能
 *              v4.6  2017-09-20  修复：批注只能生成一行的问题。
 *              v4.7  2018-06-11  修复：模板空白行（无任何数据）时，可能返回NULL时，只添加一行空白行。
 *              v5.0  2018-06-22  添加：在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
 *              v5.1  2018-09-19  修复：复文本格式化调用方法applyFont(...)异常的问题。发现人：李秉坤
 *              v6.0  2020-05-11  添加：打印分页模式。确保同一Excel在不同电脑上打印时，均能保持相同的分页结果。发现人：雷伟松
 *              v7.0  2020-05-21  添加：支持Excel公式的偏移计算及赋值
 *              v8.0  2020-11-10  添加：支持分项统计的小计在明细数据之前或之后的功能。建议人：尚仁强
 *              v9.0  2021-03-03  修复：有小计分项数据时，未对第二行的数据有合并处理。发同人：程元丰
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
        RWorkbook v_Ret = null;
        
        if ( "xls".equalsIgnoreCase(i_RTemplate.getExcelVersion()) )
        {
            v_Ret = new RWorkbook(new HSSFWorkbook());
        }
        else if ( "xlsx".equalsIgnoreCase(i_RTemplate.getExcelVersion()) )
        {
            if ( i_RTemplate.getIsBig() )
            {
                v_Ret = new RWorkbook(new SXSSFWorkbook(i_RTemplate.getRowAccessWindowSize()));
            }
            else
            {
                v_Ret = new RWorkbook(new XSSFWorkbook());
            }
        }
        else
        {
            return v_Ret;
        }
        
        ExcelHelp.copyWorkbook(i_RTemplate.getTemplateSheet().getWorkbook() ,v_Ret.getWorkbook());
        
        return v_Ret;
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
     * @throws RTemplateException
     */
    public final static RWorkbook write(String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate) throws RTemplateException
    {
        return write(null ,i_SheetName ,i_Datas ,i_RTemplate ,false ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-26
     * @version     v1.0
     *
     * @param i_SheetName  Excel工作表的名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @param i_IsAppend   是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @return
     * @throws RTemplateException
     */
    public final static RWorkbook write(String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend) throws RTemplateException
    {
        return write(null ,i_SheetName ,i_Datas ,i_RTemplate ,i_IsAppend ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2018-06-22
     * @version     v1.0
     *
     * @param i_SheetName  Excel工作表的名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @param i_IsAppend   是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @param i_AddBlankRow   在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
     * @return
     * @throws RTemplateException
     */
    public final static RWorkbook write(String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend ,int i_AddBlankRow) throws RTemplateException
    {
        return write(null ,i_SheetName ,i_Datas ,i_RTemplate ,i_IsAppend ,i_AddBlankRow);
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
     * @throws RTemplateException
     */
    public final static RWorkbook write(List<?> i_Datas ,RTemplate i_RTemplate) throws RTemplateException
    {
        return write(null ,null ,i_Datas ,i_RTemplate ,false ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-26
     * @version     v1.0
     *
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @param i_IsAppend   是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @return
     * @throws RTemplateException
     */
    public final static RWorkbook write(List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend) throws RTemplateException
    {
        return write(null ,null ,i_Datas ,i_RTemplate ,i_IsAppend ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2018-06-22
     * @version     v1.0
     *
     * @param i_Datas         数据对象
     * @param i_RTemplate     模板信息对象
     * @param i_IsAppend      是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @param i_AddBlankRow   在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
     * @return
     * @throws RTemplateException
     */
    public final static RWorkbook write(List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend ,int i_AddBlankRow) throws RTemplateException
    {
        return write(null ,null ,i_Datas ,i_RTemplate ,i_IsAppend ,i_AddBlankRow);
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
     * @throws RTemplateException
     */
    public final static RWorkbook write(RWorkbook i_Workbook ,List<?> i_Datas ,RTemplate i_RTemplate) throws RTemplateException
    {
        return write(i_Workbook ,null ,i_Datas ,i_RTemplate ,false ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-26
     * @version     v1.0
     *
     * @param i_Workbook   工作薄对象
     * @param i_SheetName  工作表名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @param i_IsAppend   是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @return
     * @throws RTemplateException
     */
    public final static RWorkbook write(RWorkbook i_Workbook ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend) throws RTemplateException
    {
        return write(i_Workbook ,null ,i_Datas ,i_RTemplate ,i_IsAppend ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2018-06-22
     * @version     v1.0
     *
     * @param i_Workbook      工作薄对象
     * @param i_SheetName     工作表名称
     * @param i_Datas         数据对象
     * @param i_RTemplate     模板信息对象
     * @param i_IsAppend      是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @param i_AddBlankRow   在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
     * @return
     * @throws RTemplateException
     */
    public final static RWorkbook write(RWorkbook i_Workbook ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend ,int i_AddBlankRow) throws RTemplateException
    {
        return write(i_Workbook ,null ,i_Datas ,i_RTemplate ,i_IsAppend ,i_AddBlankRow);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_Workbook      工作薄对象
     * @param i_SheetName     工作表名称
     * @param i_Datas         数据对象
     * @param i_RTemplate     模板信息对象
     * @param i_IsAppend      是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @param i_AddBlankRow   在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
     * @return
     * @throws RTemplateException
     */
    public final static RWorkbook write(RWorkbook i_Workbook ,String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend ,int i_AddBlankRow) throws RTemplateException
    {
        RWorkbook v_DataWorkbook  = i_Workbook;
        Sheet     v_DataSheet     = null;
        Sheet     v_TemplateSheet = null;
        String    v_SheetName     = null;
        
        i_RTemplate.check();
        
        if ( null == v_DataWorkbook )
        {
            v_DataWorkbook = createWorkbook(i_RTemplate);
        }
        
        if ( i_IsAppend && v_DataWorkbook.getWorkbook().getNumberOfSheets() >= 1)
        {
            v_DataSheet = v_DataWorkbook.getWorkbook().getSheetAt(v_DataWorkbook.getWorkbook().getNumberOfSheets() - 1);
        }
        else
        {
            // 如果不在这里转码一下，新生成的Excel会有异常，无法正常显示工作表的名称
            v_SheetName = new String(i_RTemplate.getTemplateSheet().getSheetName().getBytes());
            v_DataSheet = ExcelHelp.createSheet(v_DataWorkbook.getWorkbook() ,Help.NVL(i_SheetName ,v_SheetName));
        }
        v_TemplateSheet = i_RTemplate.getTemplateSheet();
        
        ExcelHelp.copySheet(       v_TemplateSheet ,v_DataSheet);
        // 数据工作表的整体(所有)列的列宽，复制于模板
        ExcelHelp.copyColumnsWidth(v_TemplateSheet ,v_DataSheet);
        // 数据工作表的打印区域，复制于模板
        ExcelHelp.copyPrintSetup  (v_TemplateSheet ,v_DataSheet);
        
        if ( RTemplate.$PageBreakMode_Page.equals(i_RTemplate.getPageBreakMode()) )
        {
            // 数据工作表的打印区域，用分隔符的方式，精确设定每一页的打印区域
            ExcelHelp.setPrintRowBreaks(v_TemplateSheet ,v_DataSheet ,Help.max(v_DataSheet.getLastRowNum() ,0) ,i_Datas.size());
        }
        
        // 数据工作表的整体(所有)列的样式，复制于模板
        copyColumnsStyle(i_RTemplate ,v_TemplateSheet ,v_DataWorkbook ,v_DataSheet);
        
        RSystemValue v_RSystemValue  = new RSystemValue();
        RTotal       v_RTotal        = new RTotal(i_RTemplate);
        
        v_RSystemValue.setRowNo(           1);
        v_RSystemValue.setRowCount(        i_Datas.size());
        v_RSystemValue.setRowSubtotalCount(i_Datas.size());
        v_RSystemValue.setAppendStartRowIndex(Help.max(v_DataSheet.getLastRowNum() ,0));
        
        v_RTotal.addExcelRowIndex(Help.max(v_DataSheet.getLastRowNum() ,0));
        if ( v_DataSheet.getLastRowNum() > 0 )
        {
            // 工作表有数据时    getLastRowNum() 的下标从0开始，所以此处额外加1。
            v_RTotal.addExcelRowIndex(1);
        }
        
        // 在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。ZhengWei(HY) Add 2018-06-22
        if ( i_AddBlankRow >= 1 )
        {
            v_RTotal.addExcelRowIndex(i_AddBlankRow);
        }
        
        // 计算分页总数量
        if ( i_RTemplate.getRowCountSubtotal() >= 1 )
        {
            v_RSystemValue.setPageSize(i_Datas.size());
        }
        else if ( v_RTotal.getTitlePageHeaderCount() + v_RTotal.getTitlePageFooterCount() >= 1 )
        {
            if ( v_RTotal.getTitleCount() <= 0 || i_RTemplate.isTitleUseOnePage() )
            {
                v_RSystemValue.setPageSize(i_Datas.size());
            }
            else if ( i_RTemplate.getTitlePageHeaderFirstWriteByRow() >= 1 )
            {
                int v_FirstPageDataCount = i_RTemplate.getTitlePageHeaderFirstWriteByRow() - v_RTotal.getTitlePageFooterCount() - v_RTotal.getTitleCount();
                
                v_RSystemValue.setPageSize(i_Datas.size() - v_FirstPageDataCount);
            }
            else
            {
                v_RSystemValue.setPageSize(i_Datas.size() - (i_RTemplate.getPerPageRowSize() - v_RTotal.getTitleCount()));
            }
            
            // 计算补填最后一个分页的数量
            if ( v_RSystemValue.getPageSize() >=0  )
            {
                int v_FillInCount = v_RSystemValue.getPageSize() % i_RTemplate.getPerPageRowSize();
                if ( v_FillInCount >= 1 )
                {
                    v_RTotal.setFillInCount(i_RTemplate.getPerPageRowSize() - v_FillInCount);
                }
                
                v_RSystemValue.setPageSize((int)Help.division(v_RSystemValue.getPageSize() ,i_RTemplate.getPerPageRowSize()));
                if ( (i_Datas.size() % i_RTemplate.getPerPageRowSize()) != 0 )
                {
                    v_RSystemValue.setPageSize(v_RSystemValue.getPageSize() + 1);
                }
                
                if ( v_RTotal.getTitleCount() >= 1 )
                {
                    v_RSystemValue.setPageSize(v_RSystemValue.getPageSize() + 1);
                }
            }
            else
            {
                // 首页都填充不满一页数据的情况
                v_RTotal.setFillInCount(Math.abs(v_RSystemValue.getPageSize()));
                v_RSystemValue.setPageSize(1);
            }
        }
        else
        {
            v_RSystemValue.setPageSize(i_Datas.size());
        }
        
        
        // 写入标题
        if ( v_RTotal.getTitleCount() >= 1 )
        {
            if ( i_RTemplate.isTitleUseOnePage() )
            {
                v_RSystemValue.setPageNo(v_RSystemValue.getPageNo() + 1);
            }
            else if ( i_RTemplate.getTitlePageHeaderFirstWriteByRow() >= 1 )
            {
                v_RSystemValue.setPageNo(v_RSystemValue.getPageNo() + 1);
            }
            else
            {
                // 这里有一个预设条件：普通的总标题上没有分页数量信息
            }
                
            writeTitle(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.size() >= 1 ? i_Datas.get(0) : i_RTemplate.newObject() ,i_RTemplate);
        }
        
        if ( i_RTemplate.getRowCountData() >= 1 )
        {
            if ( i_RTemplate.getRowCountSubtotal() >= 1 )
            {
                // 模板中有小计的
                if ( RTemplate.$SubTotalPosition_Top.equals(i_RTemplate.getSubtotalPosition()) )
                {
                    // 小计在明细数据之前
                    for (; v_RSystemValue.getRowNo()<=v_RSystemValue.getRowCount(); )
                    {
                        writeSubtotal(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                        writeData(    v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                        
                        v_RSystemValue.setRowNo(v_RSystemValue.getRowNo() + 1);
                    }
                }
                else
                {
                    // 小计在明细数据之后
                    for (; v_RSystemValue.getRowNo()<=v_RSystemValue.getRowCount(); )
                    {
                        writeData(    v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                        writeSubtotal(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                        
                        v_RSystemValue.setRowNo(v_RSystemValue.getRowNo() + 1);
                    }
                }
            }
            else
            {
                // 为了减少IF语句的执行次数，分开写成多种情况下的IF大分支
                if ( v_RTotal.getTitlePageHeaderCount() >= 1
                  && v_RTotal.getTitlePageFooterCount() >= 1  )
                {
                    for (; v_RSystemValue.getRowNo()<=v_RSystemValue.getRowCount(); )
                    {
                        writeDataPage(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                        v_RSystemValue.setRowNo(v_RSystemValue.getRowNo() + 1);
                    }
                }
                else if ( v_RTotal.getTitlePageHeaderCount() >= 1 )
                {
                    for (; v_RSystemValue.getRowNo()<=v_RSystemValue.getRowCount(); )
                    {
                        writeDataPageHeader(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                        v_RSystemValue.setRowNo(v_RSystemValue.getRowNo() + 1);
                    }
                }
                else if ( v_RTotal.getTitlePageFooterCount() >= 1 )
                {
                    for (; v_RSystemValue.getRowNo()<=v_RSystemValue.getRowCount(); )
                    {
                        writeDataPageFooter(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                        v_RSystemValue.setRowNo(v_RSystemValue.getRowNo() + 1);
                    }
                }
                else
                {
                    for (; v_RSystemValue.getRowNo()<=v_RSystemValue.getRowCount(); )
                    {
                        writeData(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.get(v_RSystemValue.getRowIndex()) ,i_RTemplate);
                        v_RSystemValue.setRowNo(v_RSystemValue.getRowNo() + 1);
                    }
                }
                
                // 将一页填充完整，好将分页页脚放在页脚的位置上
                if ( v_RTotal.getFillInCount() >= 1 )
                {
                    for (int v_RowIndex=0; v_RowIndex<v_RTotal.getFillInCount(); v_RowIndex++)
                    {
                        writeDataByBlankSpace(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue, i_RTemplate);
                    }
                    
                    if ( v_RTotal.getTitlePageFooterCount() >= 1 )
                    {
                        writeTitlePageFooter(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.size() >= 1 ? i_Datas.get(i_Datas.size() - 1) : i_RTemplate.newObject() ,i_RTemplate);
                    }
                }
            }
        }
        
        if ( i_RTemplate.getRowCountTotal() >= 1 )
        {
            writeTotal(v_DataWorkbook ,v_DataSheet ,v_RTotal ,v_RSystemValue ,i_Datas.size() >= 1 ? i_Datas.get(i_Datas.size() - 1) : i_RTemplate.newObject() ,i_RTemplate);
        }
        
        
        // 是否添加Excel高级筛选功能
        if ( i_RTemplate.getIsExcelFilter() && !Help.isNull(i_Datas) )
        {
            String v_Range = "A" + (i_RTemplate.getDataBeginRow())
                           + ":"
                           + StringHelp.toABC26(v_DataSheet.getRow(0).getLastCellNum() - 1)
                           + v_DataSheet.getLastRowNum();
            v_DataSheet.setAutoFilter(CellRangeAddress.valueOf(v_Range));
        }
        
        
        // 触发所有工作表事件
        i_RTemplate.fireSheetListener(v_DataSheet ,i_Datas ,i_RTemplate ,v_RSystemValue);
        
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
     * @param io_RTotal       将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue 系统变量信息
     * @param i_Datas         数据
     * @param i_RTemplate     报表模板对象
     */
    public final static void writeTitle(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue ,Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = i_RTemplate.getRowCountTitle();
        int   v_ExcelRowIndex    = io_RTotal.getExcelRowIndex();

        copyMergedRegionsTitle(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesTitle(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getTitleBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
                
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
        }
    }
    
    
    
    /**
     * 按报表模板格式写分页页眉标题
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeTitlePageHeader(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue ,Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = io_RTotal.getTitlePageHeaderCount();
        int   v_ExcelRowIndex    = io_RTotal.getExcelRowIndex();

        copyMergedRegionsTitlePageHeader(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesTitlePageHeader(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        io_RSystemValue.setPageNo(io_RSystemValue.getPageNo() + 1);
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getTitlePageHeaderBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
        }
    }
    
    
    
    /**
     * 按报表模板格式写分页页脚标题
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeTitlePageFooter(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue ,Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = io_RTotal.getTitlePageFooterCount();
        int   v_ExcelRowIndex    = io_RTotal.getExcelRowIndex();

        copyMergedRegionsTitlePageFooter(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesTitlePageFooter(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getTitlePageFooterBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
        }
    }
    
    
    
    /**
     * 按报表模板格式写入“空白”数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-07-03
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeDataByBlankSpace(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue, RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = i_RTemplate.getRowCountData();
        int   v_ExcelRowIndex    = io_RTotal.getExcelRowIndex();
        
        copyMergedRegionsData(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesData(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getDataBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRowByBlankSpace(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow);
            }
        }
    }
    
    
    
    /**
     * 按报表模板格式写入数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeData(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue, Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = i_RTemplate.getRowCountData();
        int   v_ExcelRowIndex    = io_RTotal.getExcelRowIndex();
        
        copyMergedRegionsData(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesData(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getDataBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            io_RTotal.addRealDataCount(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
        }
    }
    
    
    
    /**
     * 按报表模板格式写入数据（支持分页页眉、分页页脚）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeDataPage(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue, Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = i_RTemplate.getRowCountData();
        int   v_ExcelRowIndex    = io_RTotal.getExcelRowIndex();
        int   v_PageIndex        = (io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
        
        // 创建分页页眉。模板的"行（可对应Excel中的多行）"按一个不可再被分割的整体对待，固没有写在下面的For语句中。
        if ( v_PageIndex == 0 || io_RTotal.getRealDataCount() == i_RTemplate.getTitlePageHeaderFirstWriteByRealDataCount() )
        {
            writeTitlePageHeader(i_DataWorkbook ,i_DataSheet ,io_RTotal ,io_RSystemValue ,i_Datas ,i_RTemplate);
            v_ExcelRowIndex += io_RTotal.getTitlePageHeaderCount();
        }
        
        copyMergedRegionsData(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesData(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getDataBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            io_RTotal.addRealDataCount(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRowPageFooter(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
        }
        
        // 创建分页页脚。模板的"行（可对应Excel中的多行）"按一个不可再被分割的整体对待，固没有写在下面的For语句中。
        v_PageIndex = (io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
        if ( (v_PageIndex == 0 && io_RTotal.getRealDataCount() >= 1) || io_RTotal.getRealDataCount() == i_RTemplate.getTitlePageHeaderFirstWriteByRealDataCount() )
        {
            writeTitlePageFooter(i_DataWorkbook ,i_DataSheet ,io_RTotal ,io_RSystemValue ,i_Datas ,i_RTemplate);
            v_ExcelRowIndex += io_RTotal.getTitlePageFooterCount();
        }
    }
    
    
    
    /**
     * 按报表模板格式写入数据（支持分页页眉）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeDataPageHeader(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue, Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = i_RTemplate.getRowCountData();
        int   v_ExcelRowIndex    = io_RTotal.getExcelRowIndex();
        int   v_PageIndex        = (io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
        
        // 创建分页页眉。模板的"行（可对应Excel中的多行）"按一个不可再被分割的整体对待，固没有写在下面的For语句中。
        if ( v_PageIndex == 0 || io_RTotal.getRealDataCount() == i_RTemplate.getTitlePageHeaderFirstWriteByRealDataCount() )
        {
            writeTitlePageHeader(i_DataWorkbook ,i_DataSheet ,io_RTotal ,io_RSystemValue ,i_Datas ,i_RTemplate);
            v_ExcelRowIndex += io_RTotal.getTitlePageHeaderCount();
        }
        
        copyMergedRegionsData(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesData(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getDataBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            io_RTotal.addRealDataCount(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRowPageHeader(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
        }
    }
    
    
    
    /**
     * 按报表模板格式写入数据（支持分页页脚）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeDataPageFooter(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue, Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet    = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCount = i_RTemplate.getRowCountData();
        int   v_ExcelRowIndex    = io_RTotal.getExcelRowIndex();
        
        copyMergedRegionsData(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesData(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCount; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getDataBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            io_RTotal.addRealDataCount(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRowPageFooter(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
        }
        
        // 创建分页页脚。模板的"行（可对应Excel中的多行）"按一个不可再被分割的整体对待，固没有写在下面的For语句中。
        int v_PageIndex = (io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
        if ( (v_PageIndex == 0 && io_RTotal.getRealDataCount() >= 1) || io_RTotal.getRealDataCount() == i_RTemplate.getTitlePageHeaderFirstWriteByRealDataCount() )
        {
            writeTitlePageFooter(i_DataWorkbook ,i_DataSheet ,io_RTotal ,io_RSystemValue ,i_Datas ,i_RTemplate);
            v_ExcelRowIndex += io_RTotal.getTitlePageFooterCount();
        }
    }
    
    
    
    /**
     * 按报表模板格式写入小计（暂时不支持分页功能）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-27
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeSubtotal(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue, Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet            = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCountSubtotal = i_RTemplate.getRowCountSubtotal();
        int   v_ExcelRowIndex            = io_RTotal.getExcelRowIndex();
        
        copyMergedRegionsSubtotal(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesSubtotal(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCountSubtotal; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getSubtotalBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            io_RTotal.addRealDataCount(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
        }
    }
    
    
    
    /**
     * 按报表模板格式写入合计（暂时不支持分页功能）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataSheet      数据工作表
     * @param io_RTotal        将数据写入Excel时的辅助统计信息
     * @param io_RSystemValue  系统变量信息
     * @param i_Datas          数据
     * @param i_RTemplate      报表模板对象
     */
    public final static void writeTotal(RWorkbook i_DataWorkbook ,Sheet i_DataSheet ,RTotal io_RTotal ,RSystemValue io_RSystemValue, Object i_Datas ,RTemplate i_RTemplate)
    {
        Sheet v_TemplateSheet         = i_RTemplate.getTemplateSheet();
        int   v_TemplateRowCountTotal = i_RTemplate.getRowCountTotal();
        int   v_ExcelRowIndex         = io_RTotal.getExcelRowIndex();
        
        copyMergedRegionsTotal(i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板合并单元格
        copyImagesTotal(       i_RTemplate ,i_DataSheet ,io_RTotal);  // 按模板复制图片
        
        for (int v_RowNo=0; v_RowNo<v_TemplateRowCountTotal; v_RowNo++)
        {
            int v_TemplateRowNo = i_RTemplate.getTotalBeginRow() + v_RowNo;
            Row v_TemplateRow   = v_TemplateSheet.getRow(v_TemplateRowNo);
            
            int v_DataRowNo = v_RowNo + v_ExcelRowIndex;
            Row v_DataRow   = i_DataSheet.createRow(v_DataRowNo);
            io_RTotal.addExcelRowIndex(1);
            io_RTotal.addRealDataCount(1);
            
            if ( v_TemplateRow != null ) // 模板空白行（无任何数据）时，可能返回NULL
            {
                copyRow(i_RTemplate ,v_TemplateRow ,i_DataWorkbook ,io_RTotal ,io_RSystemValue ,v_DataRow ,i_Datas);
            }
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
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyImagesTitle(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getTitleBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitleBeginRow() ,i_RTemplate.getTitleEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的分页页眉标题区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyImagesTitlePageHeader(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getTitlePageHeaderBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitlePageHeaderBeginRow() ,i_RTemplate.getTitlePageHeaderEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的分页页脚标题区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyImagesTitlePageFooter(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getTitlePageFooterBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitlePageFooterBeginRow() ,i_RTemplate.getTitlePageFooterEndRow() ,i_DataSheet ,v_OffsetRow);
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
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyImagesData(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getDataBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getDataBeginRow() ,i_RTemplate.getDataEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的小计区域中所有图片到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-27
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyImagesSubtotal(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getSubtotalBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getSubtotalBeginRow() ,i_RTemplate.getSubtotalEndRow() ,i_DataSheet ,v_OffsetRow);
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
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyImagesTotal(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getTotalBeginRow();
        
        ExcelHelp.copyImages(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTotalBeginRow() ,i_RTemplate.getTotalEndRow() ,i_DataSheet ,v_OffsetRow);
    }
    
    
    
    /**
     * 复制模板工作表的标题区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyMergedRegionsTitle(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getTitleBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitleBeginRow() ,i_RTemplate.getTitleEndRow() ,i_DataSheet ,v_OffsetRow ,i_RTemplate.getIsSafe());
    }
    
    
    
    /**
     * 复制模板工作表的分页页眉标题区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyMergedRegionsTitlePageHeader(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getTitlePageHeaderBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitlePageHeaderBeginRow() ,i_RTemplate.getTitlePageHeaderEndRow() ,i_DataSheet ,v_OffsetRow ,i_RTemplate.getIsSafe());
    }
    
    
    
    /**
     * 复制模板工作表的分页页脚标题区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyMergedRegionsTitlePageFooter(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getTitlePageFooterBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTitlePageFooterBeginRow() ,i_RTemplate.getTitlePageFooterEndRow() ,i_DataSheet ,v_OffsetRow ,i_RTemplate.getIsSafe());
    }
    
    
    
    /**
     * 复制模板工作表的数据区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyMergedRegionsData(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getDataBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getDataBeginRow() ,i_RTemplate.getDataEndRow() ,i_DataSheet ,v_OffsetRow ,i_RTemplate.getIsSafe());
    }
    
    
    
    /**
     * 复制模板工作表的小计区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-27
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyMergedRegionsSubtotal(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getSubtotalBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getSubtotalBeginRow() ,i_RTemplate.getSubtotalEndRow() ,i_DataSheet ,v_OffsetRow ,i_RTemplate.getIsSafe());
    }
    
    
    
    /**
     * 复制模板工作表的合计区域中合并单元格到数据工作表中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_RTemplate  模板信息对象
     * @param i_DataSheet  数据工作表
     * @param io_RTotal    将数据写入Excel时的辅助统计信息
     */
    public final static void copyMergedRegionsTotal(RTemplate i_RTemplate ,Sheet i_DataSheet, RTotal io_RTotal)
    {
        int v_OffsetRow = io_RTotal.getExcelRowIndex() - i_RTemplate.getTotalBeginRow();
        
        ExcelHelp.copyMergedRegions(i_RTemplate.getTemplateSheet() ,i_RTemplate.getTotalBeginRow() ,i_RTemplate.getTotalEndRow() ,i_DataSheet ,v_OffsetRow ,i_RTemplate.getIsSafe());
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
        
        if ( i_DataCell instanceof HSSFCell )
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
        else if ( i_DataCell instanceof SXSSFCell )
        {
            SXSSFDrawing v_Patriarch = (SXSSFDrawing) i_DataCell.getSheet().createDrawingPatriarch();
            v_DataComment = v_Patriarch.createCellComment(new XSSFClientAnchor(v_TemplateAnchor.getDx1()
                                                                              ,v_TemplateAnchor.getDy1()
                                                                              ,v_TemplateAnchor.getDx2()
                                                                              ,v_TemplateAnchor.getDy2()
                                                                              ,v_TemplateAnchor.getCol1()
                                                                              ,i_DataCell.getRowIndex()
                                                                              ,v_TemplateAnchor.getCol2()
                                                                              ,i_DataCell.getRowIndex() + v_TemplateAnchor.getRow2() - v_TemplateAnchor.getRow1()));
        }
        else if ( i_DataCell instanceof XSSFCell )
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
        else
        {
            throw new java.lang.ClassCastException(i_DataCell.getClass().getName() + " is not find Type.");
        }
        
        v_DataComment.setAuthor( v_TemplateComment.getAuthor());
        v_DataComment.setColumn( v_TemplateComment.getColumn());
        v_DataComment.setRow(    i_DataCell.getRowIndex());
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
        
        if ( i_DataSheet instanceof HSSFSheet )
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
        else if ( i_DataSheet instanceof SXSSFSheet )
        {
            SXSSFSheet v_DataSheet = (SXSSFSheet)i_DataSheet;
            
            for (int v_ColumnIndex = 0; v_ColumnIndex < v_ColumnCount; v_ColumnIndex++)
            {
                CellStyle v_ColumnStyle = i_TemplateSheet.getColumnStyle(v_ColumnIndex);
                if ( v_ColumnStyle != null )
                {
                    v_DataSheet.setDefaultColumnStyle(v_ColumnIndex ,i_DataWorkbook.getCellStyle(i_RTemplate ,v_ColumnStyle.getIndex()));
                }
            }
        }
        else if ( i_DataSheet instanceof XSSFSheet )
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
     * 行复制功能（空白行的复制，即只复制格式和固定文字，不填充数据）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-07-03
     * @version     v1.0
     *
     * @param i_RTemplate      模板
     * @param i_TemplateRow    模板中的行对象
     * @param i_DataWorkbook   数据工作薄
     * @param io_RTotal        将数据写入Excel时的辅助统计信息。
     * @param io_RSystemValue  系统变量信息
     * @param i_DataRow        数据中的行对象
     * @param i_Datas          本行对应的数据
     * 
     * @return                 返回本方法内一共生成多少新行。
     */
    public final static int copyRowByBlankSpace(RTemplate i_RTemplate ,Row i_TemplateRow ,RWorkbook i_DataWorkbook ,RTotal io_RTotal ,RSystemValue io_RSystemValue ,Row i_DataRow)
    {
        ExcelHelp.copyRowHeight(i_TemplateRow ,i_DataRow);
        
        int v_CellCount = i_TemplateRow.getLastCellNum();
        
        for (int v_CellIndex=0; v_CellIndex<v_CellCount; v_CellIndex++)
        {
            Cell v_TemplateCell = i_TemplateRow.getCell(v_CellIndex);
            if ( v_TemplateCell == null )
            {
                continue;
            }
            
            Cell v_DataCell = i_DataRow.getCell(v_CellIndex);
            if ( v_DataCell == null )
            {
                v_DataCell = i_DataRow.createCell(v_CellIndex);
            }
            
            copyCellByBlankSpace(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataCell ,io_RSystemValue);
        }
        
        return 0;
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
     * @param io_RTotal        将数据写入Excel时的辅助统计信息。
     * @param io_RSystemValue  系统变量信息
     * @param i_DataRow        数据中的行对象
     * @param i_Datas          本行对应的数据
     * 
     * @return                 返回本方法内一共生成多少新行。
     */
    public final static int copyRow(RTemplate i_RTemplate ,Row i_TemplateRow ,RWorkbook i_DataWorkbook ,RTotal io_RTotal ,RSystemValue io_RSystemValue ,Row i_DataRow ,Object i_Datas)
    {
        ExcelHelp.copyRowHeight(i_TemplateRow ,i_DataRow);
        
        int     v_RowNum    = i_DataRow.getRowNum();
        int     v_CellCount = i_TemplateRow.getLastCellNum();
        int     v_ForSize   = 1;
        boolean v_IsFor     = false;
        Sheet   v_DataSheet = i_DataRow.getSheet();
        
        for (int v_CellIndex=0; v_CellIndex<v_CellCount; v_CellIndex++)
        {
            Cell v_TemplateCell = i_TemplateRow.getCell(v_CellIndex);
            if ( v_TemplateCell == null )
            {
                continue;
            }
            
            Cell v_DataCell = i_DataRow.getCell(v_CellIndex);
            if ( v_DataCell == null )
            {
                v_DataCell = i_DataRow.createCell(v_CellIndex);
            }
            
            RValue v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataCell ,io_RSystemValue ,i_Datas ,null);
            
            if ( v_RValue.getIteratorSize() > 0 )
            {
                // 逐列依次合并前面列的单元格
                if ( !v_IsFor )
                {
                    v_ForSize = v_RValue.getIteratorSize();
                    v_IsFor   = true;
                    
                    if ( v_ForSize > i_RTemplate.getRowAccessWindowSize() )
                    {
                        if ( v_DataSheet instanceof SXSSFSheet )
                        {
                            // 当isBig=true、 rowAccessWindowSize<v_ForSize 时，v_DataForRow会出现空的情况
                            throw new RuntimeException("RTemplate.RowAccessWindowSize(" + i_RTemplate.getRowAccessWindowSize() + ") too small. Please expand it.");
                        }
                    }
                    
                    io_RSystemValue.setRowSubtotalCount(io_RSystemValue.getRowSubtotalCount() + v_ForSize - 1);
                    
                    if ( v_RValue.getNextRValue() != null )
                    {
                        v_ForSize = v_RValue.getNextRValue().getIteratorSize();
                        
                        // 创建待合并的新行
                        for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                        {
                            Row v_Row = v_DataSheet.getRow(v_RowNum + v_RowIndex);
                            
                            if ( v_Row == null )
                            {
                                // ZhengWei(HY) Add 2021-03-03
                                copyMergedRegionsData(i_RTemplate ,v_DataSheet ,io_RTotal);  // 按模板合并单元格
                                copyImagesData(       i_RTemplate ,v_DataSheet ,io_RTotal);  // 按模板复制图片
                                
                                v_Row = v_DataSheet.createRow(v_RowNum + v_RowIndex);
                                io_RTotal.addExcelRowIndex(1);
                            }
                        }
                        
                        // 合并
                        for (int v_MergedColIndex=0; v_MergedColIndex<v_CellIndex; v_MergedColIndex++)
                        {
                            // 创建待合并的新列，并设置单元格的格式
                            for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                            {
                                // 当isBig=true、 rowAccessWindowSize<v_ForSize 时，v_DataForRow会出现空的情况
                                Row  v_DataForRow  = v_DataSheet.getRow(v_RowNum + v_RowIndex);
                                Cell v_DataForCell = v_DataForRow.getCell(v_MergedColIndex);
                                
                                if ( v_DataForCell == null )
                                {
                                    v_DataForCell = v_DataForRow.createCell(v_MergedColIndex);
                                }
                                
                                v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                            }
                            
                            ExcelHelp.addMergedRegions(v_DataSheet
                                                      ,v_RowNum
                                                      ,v_RowNum + v_ForSize - 1
                                                      ,v_MergedColIndex
                                                      ,v_MergedColIndex
                                                      ,i_RTemplate.getIsSafe());
                        }
                    }
                    
                    v_ForSize = v_RValue.getIteratorSize();
                    
                    // 创建待合并的新行
                    for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                    {
                        Row v_Row = v_DataSheet.getRow(v_RowNum + v_RowIndex);
                        
                        if ( v_Row == null )
                        {
                            copyMergedRegionsData(i_RTemplate ,v_DataSheet ,io_RTotal);  // 按模板合并单元格
                            copyImagesData(       i_RTemplate ,v_DataSheet ,io_RTotal);  // 按模板复制图片
                            
                            v_Row = v_DataSheet.createRow(v_RowNum + v_RowIndex);
                            io_RTotal.addExcelRowIndex(1);
                        }
                    }
                    
                    // 合并
                    for (int v_MergedColIndex=0; v_MergedColIndex<v_CellIndex; v_MergedColIndex++)
                    {
                        // 创建待合并的新列，并设置单元格的格式
                        for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                        {
                            // 当isBig=true、 rowAccessWindowSize<v_ForSize 时，v_DataForRow会出现空的情况
                            Row  v_DataForRow  = v_DataSheet.getRow(v_RowNum + v_RowIndex);
                            Cell v_DataForCell = v_DataForRow.getCell(v_MergedColIndex);
                            
                            if ( v_DataForCell == null )
                            {
                                v_DataForCell = v_DataForRow.createCell(v_MergedColIndex);
                            }
                            
                            v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                        }
                        
                        ExcelHelp.addMergedRegions(v_DataSheet
                                                  ,v_RowNum
                                                  ,v_RowNum + v_ForSize - 1
                                                  ,v_MergedColIndex
                                                  ,v_MergedColIndex
                                                  ,i_RTemplate.getIsSafe());
                    }
                }
                
                // 填充小计分项数据
                for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                {
                    Row  v_DataForRow  = v_DataSheet.getRow(v_RowNum + v_RowIndex);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null )
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataForCell ,io_RSystemValue ,i_Datas ,v_RValue);
                }
            }
            else if ( v_IsFor )
            {
                // 创建待合并的新列，并设置单元格的格式
                for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                {
                    Row  v_DataForRow  = v_DataSheet.getRow(v_RowNum + v_RowIndex);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null )
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                }
                
                ExcelHelp.addMergedRegions(v_DataSheet
                                          ,v_RowNum
                                          ,v_RowNum + v_ForSize - 1
                                          ,v_CellIndex
                                          ,v_CellIndex
                                          ,i_RTemplate.getIsSafe());
            }
        }
        
        io_RTotal.addRealDataCount(v_ForSize-1);
        
        return v_ForSize;
    }
    
    
    
    /**
     * 行复制功能（支持分页页眉、分页页脚）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_RTemplate      模板
     * @param i_TemplateRow    模板中的行对象
     * @param i_DataWorkbook   数据工作薄
     * @param io_RTotal        将数据写入Excel时的辅助统计信息。
     * @param io_RSystemValue  系统变量信息
     * @param i_DataRow        数据中的行对象
     * @param i_Datas          本行对应的数据
     * 
     * @return                 返回本方法内一共生成多少新行。
     */
    public final static int copyRowPage(RTemplate i_RTemplate ,Row i_TemplateRow ,RWorkbook i_DataWorkbook ,RTotal io_RTotal ,RSystemValue io_RSystemValue ,Row i_DataRow ,Object i_Datas)
    {
        ExcelHelp.copyRowHeight(i_TemplateRow ,i_DataRow);
        
        int     v_RowNum    = i_DataRow.getRowNum();
        int     v_CellCount = i_TemplateRow.getLastCellNum();
        int     v_ForSize   = 1;
        boolean v_IsFor     = false;
        int     v_PageCount = 0;
        
        for (int v_CellIndex=0; v_CellIndex<v_CellCount; v_CellIndex++)
        {
            Cell v_TemplateCell = i_TemplateRow.getCell(v_CellIndex);
            if ( v_TemplateCell == null )
            {
                continue;
            }
            
            Cell v_DataCell = i_DataRow.getCell(v_CellIndex);
            if ( v_DataCell == null )
            {
                v_DataCell = i_DataRow.createCell(v_CellIndex);
            }
            
            RValue v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataCell ,io_RSystemValue ,i_Datas ,null);
            
            if ( v_RValue.getIteratorSize() > 0 )
            {
                // 逐列依次合并前面列的单元格
                if ( !v_IsFor )
                {
                    v_ForSize = v_RValue.getIteratorSize();
                    v_IsFor   = true;
                    
                    io_RSystemValue.setRowSubtotalCount(io_RSystemValue.getRowSubtotalCount() + v_ForSize - 1);
                    
                    // 创建待合并的新行
                    for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                    {
                        if ( i_DataRow.getSheet().getRow(v_RowIndex + v_RowNum + v_PageCount) == null )
                        {
                            // 创建分页页眉
                            int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                            if ( v_PageIndex == 0 )
                            {
                                writeTitlePageHeader(i_DataWorkbook ,i_DataRow.getSheet() ,io_RTotal ,io_RSystemValue ,i_Datas ,i_RTemplate);
                                v_PageCount += io_RTotal.getTitlePageHeaderCount();
                            }
                            
                            i_DataRow.getSheet().createRow(v_RowIndex + v_RowNum + v_PageCount);
                            io_RTotal.addExcelRowIndex(1);
                            
                            // 创建分页页脚
                            v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                            if ( v_PageIndex == 0 )
                            {
                                writeTitlePageFooter(i_DataWorkbook ,i_DataRow.getSheet() ,io_RTotal ,io_RSystemValue ,i_Datas ,i_RTemplate);
                                v_PageCount += io_RTotal.getTitlePageFooterCount();
                            }
                        }
                    }
                    
                    // 合并
                    for (int v_MergedColIndex=0; v_MergedColIndex<v_CellIndex; v_MergedColIndex++)
                    {
                        List<RPosition> v_RPositions = new ArrayList<RPosition>();
                        RPosition       v_RPosition  = new RPosition(v_RowNum ,0);
                        
                        // 创建待合并的新列，并设置单元格的格式
                        v_PageCount = 0;
                        for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                        {
                            // 跳过分页页眉，并记录前后位置
                            int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                            if ( v_PageIndex == 0 )
                            {
                                v_RPosition.setEndNo(v_RowNum + v_RowIndex + v_PageCount - 1);
                                v_RPositions.add(v_RPosition);
                                v_RPosition = new RPosition(v_RPosition.getEndNo() + io_RTotal.getTitlePageHeaderCount() + 1 ,0);
                                
                                v_PageCount += io_RTotal.getTitlePageHeaderCount();
                            }
                            
                            Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                            Cell v_DataForCell = v_DataForRow.getCell(v_MergedColIndex);
                            
                            if ( v_DataForCell == null )
                            {
                                v_DataForCell = v_DataForRow.createCell(v_MergedColIndex);
                            }
                            
                            v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                            
                            // 跳过分页页脚，并记录前后位置
                            v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                            if ( v_PageIndex == 0 )
                            {
                                v_RPosition.setEndNo(v_RowNum + v_RowIndex + v_PageCount);
                                v_RPositions.add(v_RPosition);
                                v_RPosition = new RPosition(v_RPosition.getEndNo() + io_RTotal.getTitlePageFooterCount() + 1 ,0);
                                
                                v_PageCount += io_RTotal.getTitlePageFooterCount();
                            }
                        }
                        
                        v_RPosition.setEndNo(v_RowNum + v_ForSize + v_PageCount - 1);
                        v_RPositions.add(v_RPosition);
                        
                        for (RPosition v_RP : v_RPositions)
                        {
                            if ( v_RP.getBeginNo() < v_RP.getEndNo() )
                            {
                                ExcelHelp.addMergedRegions(i_DataRow.getSheet()
                                                          ,v_RP.getBeginNo()
                                                          ,v_RP.getEndNo()
                                                          ,v_MergedColIndex
                                                          ,v_MergedColIndex
                                                          ,i_RTemplate.getIsSafe());
                            }
                        }
                    }
                }
                
                // 填充小计分项数据
                v_PageCount = 0;
                for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                {
                    // 跳过分页页眉
                    int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                    if ( v_PageIndex == 0 )
                    {
                        v_PageCount += io_RTotal.getTitlePageHeaderCount();
                    }
                    
                    Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null )
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataForCell ,io_RSystemValue ,i_Datas ,v_RValue);
                
                    // 跳过分页页脚
                    v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                    if ( v_PageIndex == 0 )
                    {
                        v_PageCount += io_RTotal.getTitlePageFooterCount();
                    }
                }
            }
            else if ( v_IsFor )
            {
                List<RPosition> v_RPositions = new ArrayList<RPosition>();
                RPosition       v_RPosition  = new RPosition(v_RowNum ,0);
                
                // 创建待合并的新列，并设置单元格的格式
                v_PageCount = 0;
                for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                {
                    // 跳过分页页眉，并记录前后位置
                    int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                    if ( v_PageIndex == 0 )
                    {
                        v_RPosition.setEndNo(v_RowNum + v_RowIndex + v_PageCount - 1);
                        v_RPositions.add(v_RPosition);
                        v_RPosition = new RPosition(v_RPosition.getEndNo() + io_RTotal.getTitlePageHeaderCount() + 1 ,0);
                        
                        v_PageCount += io_RTotal.getTitlePageHeaderCount();
                    }
                    
                    Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null )
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                    
                    // 跳过分页页脚，并记录前后位置
                    v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                    if ( v_PageIndex == 0 )
                    {
                        v_RPosition.setEndNo(v_RowNum + v_RowIndex + v_PageCount);
                        v_RPositions.add(v_RPosition);
                        v_RPosition = new RPosition(v_RPosition.getEndNo() + io_RTotal.getTitlePageFooterCount() + 1 ,0);
                        
                        v_PageCount += io_RTotal.getTitlePageFooterCount();
                    }
                }
                
                v_RPosition.setEndNo(v_RowNum + v_ForSize + v_PageCount - 1);
                v_RPositions.add(v_RPosition);
                
                for (RPosition v_RP : v_RPositions)
                {
                    if ( v_RP.getBeginNo() < v_RP.getEndNo() )
                    {
                        ExcelHelp.addMergedRegions(i_DataRow.getSheet()
                                                  ,v_RP.getBeginNo()
                                                  ,v_RP.getEndNo()
                                                  ,v_CellIndex
                                                  ,v_CellIndex
                                                  ,i_RTemplate.getIsSafe());
                    }
                }
            }
        }
        
        io_RTotal.addRealDataCount(v_ForSize-1);
        
        return v_ForSize;
    }
    
    
    
    /**
     * 行复制功能（支持分页页眉）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_RTemplate      模板
     * @param i_TemplateRow    模板中的行对象
     * @param i_DataWorkbook   数据工作薄
     * @param io_RTotal        将数据写入Excel时的辅助统计信息。
     * @param io_RSystemValue  系统变量信息
     * @param i_DataRow        数据中的行对象
     * @param i_Datas          本行对应的数据
     * 
     * @return                 返回本方法内一共生成多少新行。
     */
    public final static int copyRowPageHeader(RTemplate i_RTemplate ,Row i_TemplateRow ,RWorkbook i_DataWorkbook ,RTotal io_RTotal ,RSystemValue io_RSystemValue ,Row i_DataRow ,Object i_Datas)
    {
        ExcelHelp.copyRowHeight(i_TemplateRow ,i_DataRow);
        
        int     v_RowNum    = i_DataRow.getRowNum();
        int     v_CellCount = i_TemplateRow.getLastCellNum();
        int     v_ForSize   = 1;
        boolean v_IsFor     = false;
        int     v_PageCount = 0;
        
        for (int v_CellIndex=0; v_CellIndex<v_CellCount; v_CellIndex++)
        {
            Cell v_TemplateCell = i_TemplateRow.getCell(v_CellIndex);
            if ( v_TemplateCell == null )
            {
                continue;
            }
            
            Cell v_DataCell = i_DataRow.getCell(v_CellIndex);
            if ( v_DataCell == null )
            {
                v_DataCell = i_DataRow.createCell(v_CellIndex);
            }
            
            RValue v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataCell ,io_RSystemValue ,i_Datas ,null);
            
            if ( v_RValue.getIteratorSize() > 0 )
            {
                // 逐列依次合并前面列的单元格
                if ( !v_IsFor )
                {
                    v_ForSize = v_RValue.getIteratorSize();
                    v_IsFor   = true;
                    
                    io_RSystemValue.setRowSubtotalCount(io_RSystemValue.getRowSubtotalCount() + v_ForSize - 1);
                    
                    // 创建待合并的新行
                    for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                    {
                        if ( i_DataRow.getSheet().getRow(v_RowIndex + v_RowNum + v_PageCount) == null )
                        {
                            // 创建分页页眉
                            int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                            if ( v_PageIndex == 0 )
                            {
                                writeTitlePageHeader(i_DataWorkbook ,i_DataRow.getSheet() ,io_RTotal ,io_RSystemValue ,i_Datas ,i_RTemplate);
                                v_PageCount += io_RTotal.getTitlePageHeaderCount();
                            }
                            
                            i_DataRow.getSheet().createRow(v_RowIndex + v_RowNum + v_PageCount);
                            io_RTotal.addExcelRowIndex(1);
                        }
                    }
                    
                    // 合并
                    for (int v_MergedColIndex=0; v_MergedColIndex<v_CellIndex; v_MergedColIndex++)
                    {
                        List<RPosition> v_RPositions = new ArrayList<RPosition>();
                        RPosition       v_RPosition  = new RPosition(v_RowNum ,0);
                        
                        // 创建待合并的新列，并设置单元格的格式
                        v_PageCount = 0;
                        for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                        {
                            // 跳过分页页眉，并记录前后位置
                            int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                            if ( v_PageIndex == 0 )
                            {
                                v_RPosition.setEndNo(v_RowNum + v_RowIndex + v_PageCount - 1);
                                v_RPositions.add(v_RPosition);
                                v_RPosition = new RPosition(v_RPosition.getEndNo() + io_RTotal.getTitlePageHeaderCount() + 1 ,0);
                                
                                v_PageCount += io_RTotal.getTitlePageHeaderCount();
                            }
                            
                            Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                            Cell v_DataForCell = v_DataForRow.getCell(v_MergedColIndex);
                            
                            if ( v_DataForCell == null )
                            {
                                v_DataForCell = v_DataForRow.createCell(v_MergedColIndex);
                            }
                            
                            v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                        }
                        
                        v_RPosition.setEndNo(v_RowNum + v_ForSize + v_PageCount - 1);
                        v_RPositions.add(v_RPosition);
                        
                        for (RPosition v_RP : v_RPositions)
                        {
                            if ( v_RP.getBeginNo() < v_RP.getEndNo() )
                            {
                                ExcelHelp.addMergedRegions(i_DataRow.getSheet()
                                                          ,v_RP.getBeginNo()
                                                          ,v_RP.getEndNo()
                                                          ,v_MergedColIndex
                                                          ,v_MergedColIndex
                                                          ,i_RTemplate.getIsSafe());
                            }
                        }
                    }
                }
                
                // 填充小计分项数据
                v_PageCount = 0;
                for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                {
                    // 跳过分页页眉
                    int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                    if ( v_PageIndex == 0 )
                    {
                        v_PageCount += io_RTotal.getTitlePageHeaderCount();
                    }
                    
                    Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null )
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataForCell ,io_RSystemValue ,i_Datas ,v_RValue);
                }
            }
            else if ( v_IsFor )
            {
                List<RPosition> v_RPositions = new ArrayList<RPosition>();
                RPosition       v_RPosition  = new RPosition(v_RowNum ,0);
                
                // 创建待合并的新列，并设置单元格的格式
                v_PageCount = 0;
                for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                {
                    // 跳过分页页眉，并记录前后位置
                    int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                    if ( v_PageIndex == 0 )
                    {
                        v_RPosition.setEndNo(v_RowNum + v_RowIndex + v_PageCount - 1);
                        v_RPositions.add(v_RPosition);
                        v_RPosition = new RPosition(v_RPosition.getEndNo() + io_RTotal.getTitlePageHeaderCount() + 1 ,0);
                        
                        v_PageCount += io_RTotal.getTitlePageHeaderCount();
                    }
                    
                    Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null )
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                }
                
                v_RPosition.setEndNo(v_RowNum + v_ForSize + v_PageCount - 1);
                v_RPositions.add(v_RPosition);
                
                for (RPosition v_RP : v_RPositions)
                {
                    if ( v_RP.getBeginNo() < v_RP.getEndNo() )
                    {
                        ExcelHelp.addMergedRegions(i_DataRow.getSheet()
                                                  ,v_RP.getBeginNo()
                                                  ,v_RP.getEndNo()
                                                  ,v_CellIndex
                                                  ,v_CellIndex
                                                  ,i_RTemplate.getIsSafe());
                    }
                }
            }
        }
        
        io_RTotal.addRealDataCount(v_ForSize-1);
        
        return v_ForSize;
    }
    
    
    
    /**
     * 行复制功能（支持分页页脚）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @param i_RTemplate      模板
     * @param i_TemplateRow    模板中的行对象
     * @param i_DataWorkbook   数据工作薄
     * @param io_RTotal        将数据写入Excel时的辅助统计信息。
     * @param io_RSystemValue  系统变量信息
     * @param i_DataRow        数据中的行对象
     * @param i_Datas          本行对应的数据
     * 
     * @return                 返回本方法内一共生成多少新行。
     */
    public final static int copyRowPageFooter(RTemplate i_RTemplate ,Row i_TemplateRow ,RWorkbook i_DataWorkbook ,RTotal io_RTotal ,RSystemValue io_RSystemValue ,Row i_DataRow ,Object i_Datas)
    {
        ExcelHelp.copyRowHeight(i_TemplateRow ,i_DataRow);
        
        int     v_RowNum    = i_DataRow.getRowNum();
        int     v_CellCount = i_TemplateRow.getLastCellNum();
        int     v_ForSize   = 1;
        boolean v_IsFor     = false;
        int     v_PageCount = 0;
        
        for (int v_CellIndex=0; v_CellIndex<v_CellCount; v_CellIndex++)
        {
            Cell v_TemplateCell = i_TemplateRow.getCell(v_CellIndex);
            if ( v_TemplateCell == null )
            {
                continue;
            }
            
            Cell v_DataCell = i_DataRow.getCell(v_CellIndex);
            if ( v_DataCell == null )
            {
                v_DataCell = i_DataRow.createCell(v_CellIndex);
            }
            
            RValue v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataCell ,io_RSystemValue ,i_Datas ,null);
            
            if ( v_RValue.getIteratorSize() > 0 )
            {
                // 逐列依次合并前面列的单元格
                if ( !v_IsFor )
                {
                    v_ForSize = v_RValue.getIteratorSize();
                    v_IsFor   = true;
                    
                    io_RSystemValue.setRowSubtotalCount(io_RSystemValue.getRowSubtotalCount() + v_ForSize - 1);
                    
                    // 创建待合并的新行
                    for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                    {
                        if ( i_DataRow.getSheet().getRow(v_RowIndex + v_RowNum + v_PageCount) == null )
                        {
                            i_DataRow.getSheet().createRow(v_RowIndex + v_RowNum + v_PageCount);
                            io_RTotal.addExcelRowIndex(1);
                            
                            // 创建分页页脚
                            int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                            if ( v_PageIndex == 0 )
                            {
                                writeTitlePageFooter(i_DataWorkbook ,i_DataRow.getSheet() ,io_RTotal ,io_RSystemValue ,i_Datas ,i_RTemplate);
                                v_PageCount += io_RTotal.getTitlePageFooterCount();
                            }
                        }
                    }
                    
                    // 合并
                    for (int v_MergedColIndex=0; v_MergedColIndex<v_CellIndex; v_MergedColIndex++)
                    {
                        List<RPosition> v_RPositions = new ArrayList<RPosition>();
                        RPosition       v_RPosition  = new RPosition(v_RowNum ,0);
                        
                        // 创建待合并的新列，并设置单元格的格式
                        v_PageCount = 0;
                        for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                        {
                            Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                            Cell v_DataForCell = v_DataForRow.getCell(v_MergedColIndex);
                            
                            if ( v_DataForCell == null )
                            {
                                v_DataForCell = v_DataForRow.createCell(v_MergedColIndex);
                            }
                            
                            v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                            
                            // 跳过分页页脚，并记录前后位置
                            int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                            if ( v_PageIndex == 0 )
                            {
                                v_RPosition.setEndNo(v_RowNum + v_RowIndex + v_PageCount);
                                v_RPositions.add(v_RPosition);
                                v_RPosition = new RPosition(v_RPosition.getEndNo() + io_RTotal.getTitlePageFooterCount() + 1 ,0);
                                
                                v_PageCount += io_RTotal.getTitlePageFooterCount();
                            }
                        }
                        
                        v_RPosition.setEndNo(v_RowNum + v_ForSize + v_PageCount - 1);
                        v_RPositions.add(v_RPosition);
                        
                        for (RPosition v_RP : v_RPositions)
                        {
                            if ( v_RP.getBeginNo() < v_RP.getEndNo() )
                            {
                                ExcelHelp.addMergedRegions(i_DataRow.getSheet()
                                                          ,v_RP.getBeginNo()
                                                          ,v_RP.getEndNo()
                                                          ,v_MergedColIndex
                                                          ,v_MergedColIndex
                                                          ,i_RTemplate.getIsSafe());
                            }
                        }
                    }
                }
                
                // 填充小计分项数据
                v_PageCount = 0;
                for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                {
                    Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null )
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_RValue = copyCell(i_RTemplate ,v_TemplateCell ,i_DataWorkbook ,v_DataForCell ,io_RSystemValue ,i_Datas ,v_RValue);
                
                    // 跳过分页页脚
                    int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                    if ( v_PageIndex == 0 )
                    {
                        v_PageCount += io_RTotal.getTitlePageFooterCount();
                    }
                }
            }
            else if ( v_IsFor )
            {
                List<RPosition> v_RPositions = new ArrayList<RPosition>();
                RPosition       v_RPosition  = new RPosition(v_RowNum ,0);
                
                // 创建待合并的新列，并设置单元格的格式
                v_PageCount = 0;
                for (int v_RowIndex=1; v_RowIndex<v_ForSize; v_RowIndex++)
                {
                    Row  v_DataForRow  = i_DataRow.getSheet().getRow(v_RowNum + v_RowIndex + v_PageCount);
                    Cell v_DataForCell = v_DataForRow.getCell(v_CellIndex);
                    
                    if ( v_DataForCell == null )
                    {
                        v_DataForCell = v_DataForRow.createCell(v_CellIndex);
                    }
                    
                    v_DataForCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,v_TemplateCell.getCellStyle().getIndex()));
                    
                    // 跳过分页页脚，并记录前后位置
                    int v_PageIndex = (v_RowIndex + io_RTotal.getRealDataCount() + io_RTotal.getTitleCount() * i_RTemplate.getTitleRatio() - i_RTemplate.getTitlePageHeaderRate()) % i_RTemplate.getPerPageRowSize();
                    if ( v_PageIndex == 0 )
                    {
                        v_RPosition.setEndNo(v_RowNum + v_RowIndex + v_PageCount);
                        v_RPositions.add(v_RPosition);
                        v_RPosition = new RPosition(v_RPosition.getEndNo() + io_RTotal.getTitlePageFooterCount() + 1 ,0);
                        
                        v_PageCount += io_RTotal.getTitlePageFooterCount();
                    }
                }
                
                v_RPosition.setEndNo(v_RowNum + v_ForSize + v_PageCount - 1);
                v_RPositions.add(v_RPosition);
                
                for (RPosition v_RP : v_RPositions)
                {
                    if ( v_RP.getBeginNo() < v_RP.getEndNo() )
                    {
                        ExcelHelp.addMergedRegions(i_DataRow.getSheet()
                                                  ,v_RP.getBeginNo()
                                                  ,v_RP.getEndNo()
                                                  ,v_CellIndex
                                                  ,v_CellIndex
                                                  ,i_RTemplate.getIsSafe());
                    }
                }
            }
        }
        
        io_RTotal.addRealDataCount(v_ForSize-1);
        
        return v_ForSize;
    }
    
    
    
    /**
     * 复制单位格（空白行的复制，即只复制格式和固定文字，不填充数据）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-07-03
     * @version     v1.0
     *
     * @param i_RTemplate      模板对象
     * @param i_TemplateCell   模板中的单元格对象
     * @param i_DataWorkbook   数据工作薄
     * @param i_DataCell       数据中的单元格对象
     * @param io_RSystemValue 系统变量信息
     * @param i_Datas          本行对应的数据
     * @param io_RValue        小计循环的迭代器
     * @return
     */
    public final static void copyCellByBlankSpace(RTemplate i_RTemplate ,Cell i_TemplateCell ,RWorkbook i_DataWorkbook ,Cell i_DataCell ,RSystemValue io_RSystemValue)
    {
        // 复制样式
        i_DataCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,i_TemplateCell.getCellStyle().getIndex()));
        
        // 复制评论
        copyComment(i_RTemplate ,i_TemplateCell ,i_DataWorkbook ,i_DataCell);
        
        // 复制超链接
        ExcelHelp.copyHyperlinks(i_TemplateCell ,i_DataCell);
        
        // 复制数据类型
        CellType v_CellType = i_TemplateCell.getCellType();
        // i_DataCell.setCellType(v_CellType);  不能在此统一设置，原因是：下面代码对类型是有浮动的
        
        if ( v_CellType == CellType.NUMERIC )
        {
            // 2022-11-10 Del：POI 5.x的版本不建议使用
            // i_DataCell.setCellType(v_CellType);
            
            if ( DateUtil.isCellDateFormatted(i_TemplateCell) )
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
                // 2022-11-10 Del：POI 5.x的版本不建议使用
                // i_DataCell.setCellType(v_CellType);
                i_DataCell.setCellValue("");
            }
            else
            {
                // 2022-11-10 Del：POI 5.x的版本不建议使用
                // i_DataCell.setCellType(v_CellType);
                copyRichTextStyle(i_RTemplate ,v_TemplateRichText ,i_DataWorkbook ,i_DataCell);
            }
        }
        else if ( v_CellType == CellType.BOOLEAN )
        {
            // 2022-11-10 Del：POI 5.x的版本不建议使用
            // i_DataCell.setCellType(v_CellType);
            i_DataCell.setCellValue(i_TemplateCell.getBooleanCellValue());
        }
        else if ( v_CellType == CellType.FORMULA)
        {
            // 2022-11-10 Del：POI 5.x的版本不建议使用
            // i_DataCell.setCellType(v_CellType);
            i_DataCell.setCellFormula(ExcelFormula.calcFormulaOffset(i_TemplateCell ,i_DataCell));
        }
        else
        {
            // Nothing.
            i_DataCell.setCellType(v_CellType);
        }
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
     * @param io_RSystemValue 系统变量信息
     * @param i_Datas          本行对应的数据
     * @param io_RValue        小计循环的迭代器
     * @return
     */
    public final static RValue copyCell(RTemplate i_RTemplate ,Cell i_TemplateCell ,RWorkbook i_DataWorkbook ,Cell i_DataCell ,RSystemValue io_RSystemValue ,Object i_Datas ,RValue io_RValue)
    {
        // 复制样式
        i_DataCell.setCellStyle(i_DataWorkbook.getCellStyle(i_RTemplate ,i_TemplateCell.getCellStyle().getIndex()));
        
        // 复制评论
        copyComment(i_RTemplate ,i_TemplateCell ,i_DataWorkbook ,i_DataCell);
        
        // 复制超链接
        ExcelHelp.copyHyperlinks(i_TemplateCell ,i_DataCell);
        
        // 复制数据类型
        CellType v_CellType = i_TemplateCell.getCellType();
        
        // i_DataCell.setCellType(v_CellType);  不能在此统一设置，原因是：下面代码对类型是有浮动的
        
        if ( v_CellType == CellType.NUMERIC )
        {
            // 2022-11-10 Del：POI 5.x的版本不建议使用
            // i_DataCell.setCellType(v_CellType);
            
            if ( DateUtil.isCellDateFormatted(i_TemplateCell) )
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
                RValue        v_RValue   = i_RTemplate.getValue(v_ValueName ,i_Datas ,io_RSystemValue ,io_RValue ,i_TemplateCell.getColumnIndex());
                ValueListener v_Listener = i_RTemplate.getListener(v_ValueName);
                
                if ( v_Listener != null )
                {
                    v_RValue.setValue(Help.NVL(v_Listener.getValue(i_RTemplate ,i_TemplateCell ,i_DataCell ,i_DataWorkbook ,io_RSystemValue ,i_Datas ,v_RValue.getValue())));
                }
                
                if ( null != v_RValue.getValue() )
                {
                    // 单元格的数字格式
                    short v_DataFormat = i_TemplateCell.getCellStyle().getDataFormat();
                    
                    // 如果模板的数据格式是：数值
                    if ( v_DataFormat > 0 )
                    {
                        String v_DataFormatName = i_TemplateCell.getCellStyle().getDataFormatString();
                        
                        if ( v_DataFormatName.indexOf("0") >= 0 && Help.isNumber(v_RValue.getValue().toString()) )
                        {
                            // 整数显示为小数的形式的选择开功能。需Excel模板配合设置单元格的格式为：小数格式(0.000 或 0.###)
                            if ( i_RTemplate.getIsIntegerShowDecimal() )
                            {
                                i_DataCell.setCellType(CellType.NUMERIC);
                                i_DataCell.setCellValue(Double.parseDouble(v_RValue.getValue().toString()));
                            }
                            else if ( v_RValue.getValue().toString().indexOf(".") >= 0 )
                            {
                                i_DataCell.setCellType(CellType.NUMERIC);
                                i_DataCell.setCellValue(Double.parseDouble(v_RValue.getValue().toString()));
                            }
                            else
                            {
                                i_DataCell.setCellType(CellType.NUMERIC);
                                i_DataCell.setCellValue(Double.parseDouble(v_RValue.getValue().toString()));
                            }
                        }
                        // 如果填充值首字母为等号“=”，按Excel公式填充
                        else if ( v_RValue.getValue().toString().trim().startsWith("=") )
                        {
                            i_DataCell.setCellType(CellType.FORMULA);
                            i_DataCell.setCellFormula(v_RValue.getValue().toString().trim().substring(1));
                        }
                        else
                        {
                            i_DataCell.setCellType(v_CellType);
                            i_DataCell.setCellValue(v_RValue.getValue().toString());
                        }
                    }
                    // 如果填充值首字母为等号“=”，按Excel公式填充
                    else if ( v_RValue.getValue().toString().trim().startsWith("=") )
                    {
                        i_DataCell.setCellType(CellType.FORMULA);
                        i_DataCell.setCellFormula(v_RValue.getValue().toString().trim().substring(1));
                    }
                    else
                    {
                        i_DataCell.setCellType(v_CellType);
                        i_DataCell.setCellValue(v_RValue.getValue().toString());
                    }
                    
                    // 自动单元格高度
                    if ( v_RValue.isAutoHeight() )
                    {
                        float v_AutoHeight = ExcelHelp.calcCellAutoHeight(v_RValue.getValue().toString() ,i_TemplateCell);
                    
                        if ( v_AutoHeight > i_DataCell.getRow().getHeightInPoints() )
                        {
                            i_DataCell.getRow().setHeightInPoints(v_AutoHeight);
                        }
                    }
                    
                    if ( StringHelp.isContains(v_ValueName ,true ,i_RTemplate.getValueSign() ,RTemplate.$Value_LimitBefore ,RTemplate.$Value_LimitEnd) )
                    {
                        copyRichTextStyle(i_RTemplate ,v_TemplateRichText ,i_DataWorkbook ,i_DataCell ,v_ValueName ,v_RValue.getValue().toString());
                    }
                }
                else
                {
                    i_DataCell.setCellType(v_CellType);
                    i_DataCell.setCellValue("");
                }
                
                return v_RValue;
            }
            else
            {
                i_DataCell.setCellType(v_CellType);
                copyRichTextStyle(i_RTemplate ,v_TemplateRichText ,i_DataWorkbook ,i_DataCell);
            }
        }
        else if ( v_CellType == CellType.BOOLEAN )
        {
            i_DataCell.setCellType(v_CellType);
            i_DataCell.setCellValue(i_TemplateCell.getBooleanCellValue());
        }
        else if ( v_CellType == CellType.FORMULA)
        {
            i_DataCell.setCellType(v_CellType);
            i_DataCell.setCellFormula(ExcelFormula.calcFormulaOffset(i_TemplateCell ,i_DataCell));
        }
        else
        {
            // Nothing.
            i_DataCell.setCellType(v_CellType);
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
                HSSFRichTextString v_TemplateRichText = (HSSFRichTextString)i_TemplateRichText;
                v_DataRichText = new HSSFRichTextString(v_Text);
                
                for (int v_FontIndex=v_FontCount-1; v_FontIndex >= 0; v_FontIndex--)
                {
                    int   v_FirstIndex = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
                    short v_IDX        = v_TemplateRichText.getFontOfFormattingRun(v_FontIndex);
                    Font  v_DataFont   = i_DataWorkbook.getFont(i_RTemplate ,v_IDX);
                    
                    if ( v_TextLen > 0 )
                    {
                        v_DataRichText.applyFont(v_FirstIndex, v_TextLen, v_DataFont);
                    }
                }
                
                i_DataCell.setCellValue(v_DataRichText);
            }
            else if ( i_TemplateRichText instanceof XSSFRichTextString )
            {
                XSSFRichTextString v_TemplateRichText = (XSSFRichTextString)i_TemplateRichText;
                v_DataRichText = new XSSFRichTextString(v_Text);//i_DataCell.getRow().getSheet().getWorkbook().getCreationHelper().createRichTextString(v_Text);
                
                for (int v_FontIndex=0; v_FontIndex<v_FontCount; v_FontIndex++)
                {
                    int      v_FirstIndex   = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
                    XSSFFont v_TemplateFont = v_TemplateRichText.getFontOfFormattingRun(v_FontIndex);
                    
                    if ( v_TemplateFont != null )
                    {
                        v_TemplateFont  = ExcelHelp.findFont((XSSFWorkbook)i_RTemplate.getTemplateSheet().getWorkbook() ,v_TemplateFont);
                        int  v_IDX      = v_TemplateFont.getIndex();
                        Font v_DataFont = i_DataWorkbook.getFont(i_RTemplate ,v_IDX);
                        
                        if ( v_TextLen > 0 )
                        {
                            v_DataRichText.applyFont(v_FirstIndex, v_TextLen, v_DataFont);
                        }
                    }
                }
                
                i_DataCell.setCellValue(v_DataRichText);
            }
        }
        else
        {
            i_DataCell.setCellValue(v_Text);
        }
    }
    
    
    
    /**
     * 复制高级文本及格式
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-09-04
     * @version     v1.0
     *
     * @param i_RTemplate         模板对象
     * @param i_TemplateRichText  高级文本对象
     * @param i_DataWorkbook      数据工作薄
     * @param i_DataCell          数据单元格
     * @param i_Name              占位符的名称
     * @param i_Value             占位符的数值
     */
    public final static void copyRichTextStyle(RTemplate i_RTemplate ,RichTextString i_TemplateRichText ,RWorkbook i_DataWorkbook ,Cell i_DataCell ,String i_Name ,String i_Value)
    {
       int    v_FontCount       = i_TemplateRichText.numFormattingRuns();
        String v_Text            = i_TemplateRichText.toString();
        int    v_TextLen         = v_Text.length();
        int    v_TextStartIndex  = v_Text.indexOf(RTemplate.$Value_LimitBefore + i_RTemplate.getValueSign());
        int    v_ValueLen        = i_Value.length();
        int    v_DiffLen         = v_ValueLen - v_TextLen;
        
        if ( v_FontCount >= 1 )
        {
            RichTextString v_DataRichText = null;
            
            if ( i_TemplateRichText instanceof HSSFRichTextString )
            {
                HSSFRichTextString v_TemplateRichText = (HSSFRichTextString)i_TemplateRichText;
                v_DataRichText = new HSSFRichTextString(i_Value);
                
                for (int v_FontIndex=v_FontCount-1; v_FontIndex >= 0; v_FontIndex--)
                {
                    int   v_FirstIndex = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
                    short v_IDX        = v_TemplateRichText.getFontOfFormattingRun(v_FontIndex);
                    Font  v_DataFont   = i_DataWorkbook.getFont(i_RTemplate ,v_IDX);
                    
                    if ( v_TextLen > 0 )
                    {
                        if ( v_FirstIndex > v_TextStartIndex )
                        {
                            v_FirstIndex += v_DiffLen;
                        }
                        
                        v_DataRichText.applyFont(v_FirstIndex, v_ValueLen, v_DataFont);
                    }
                }
                
                i_DataCell.setCellValue(v_DataRichText);
            }
            else if ( i_TemplateRichText instanceof XSSFRichTextString )
            {
                XSSFRichTextString v_TemplateRichText = (XSSFRichTextString)i_TemplateRichText;
                v_DataRichText = new XSSFRichTextString(i_Value);//i_DataCell.getRow().getSheet().getWorkbook().getCreationHelper().createRichTextString(v_Text);
                
                for (int v_FontIndex=0; v_FontIndex<v_FontCount; v_FontIndex++)
                {
                    int      v_FirstIndex   = i_TemplateRichText.getIndexOfFormattingRun(v_FontIndex);
                    XSSFFont v_TemplateFont = v_TemplateRichText.getFontOfFormattingRun(v_FontIndex);
                    
                    if ( v_TemplateFont != null )
                    {
                        v_TemplateFont  = ExcelHelp.findFont((XSSFWorkbook)i_RTemplate.getTemplateSheet().getWorkbook() ,v_TemplateFont);
                        int  v_IDX      = v_TemplateFont.getIndex();
                        Font v_DataFont = i_DataWorkbook.getFont(i_RTemplate ,v_IDX);
                        
                        if ( v_TextLen > 0 )
                        {
                            if ( v_FirstIndex > v_TextStartIndex )
                            {
                                v_FirstIndex += v_DiffLen;
                            }
                            
                            v_DataRichText.applyFont(v_FirstIndex, v_ValueLen, v_DataFont);
                        }
                    }
                }
                
                i_DataCell.setCellValue(v_DataRichText);
            }
        }
        else
        {
            i_DataCell.setCellValue(i_Value);
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
                
                if ( v_TextLen > 0 )
                {
                    v_DataRichText.applyFont(v_FirstIndex, v_TextLen, v_DataFont);
                }
            }
            
            i_DataCell.getCellComment().setString(v_DataRichText);
        }
        else if ( i_TemplateRichText instanceof XSSFRichTextString )
        {
            i_DataCell.getCellComment().setString(i_TemplateRichText);
        }
    }
    
}
