package org.hy.common.report.bean;

import java.util.Hashtable;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.hy.common.Help;
import org.hy.common.report.ExcelHelp;





/**
 * 工作薄
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 *              v2.0  2017-09-11  1. 添加外界(第三方)创建样式、字体，并通过标记ID保存，方便二次引用的功能。
 *                                   建议人：李浩
 *              v2.1  2017-09-20  1. 修复：getCellStyleByCopy() 在动态改变单元格颜色时，可能出现颜色ID相同后，相互覆盖的问题。
 *                                2. 修复：getFontByCopy()      在动态改变单元格字体时，可能出现字体ID相同后，相互覆盖的问题。
 *              v3.0  2020-05-29  1. 添加：字体ID、样式ID生成，即唯一性的维护按新规则，不再按字体索引、样式索引管理。
 */
public class RWorkbook
{
    
    /** 工作薄 */
    private Workbook workbook;
    
    /**
     * 工作薄所用到的模板中的字体信息。
     * 
     * 此字体为已在工作薄中创建过的字体，是本工作薄的字体对象，不是模板的。
     * 
     * Map.key 为字体ID
     */
    private Map<String ,Font> fonts;
    
    /**
     * 工作薄所用到的模板中的单元格样式信息。
     * 
     * 此单元格样式为已在工作薄中创建过的单元格样式，是本工作薄的单元格样式对象，不是模板的。
     * 
     * Map.key 为样式ID
     */
    private Map<String ,CellStyle> cellStyles;
    
    /**
     * 第三方用户动态通过克隆创建出来的样式。
     * 
     * Map.key    为第三方自行定义的标识
     */
    private Map<String ,CellStyle> cellStylesByCopy;
    
    /**
     * 第三方用户动态通过克隆创建出来的字体。
     * 
     * Map.key    为第三方自行定义的标识
     */
    private Map<String ,Font> fontsByCopy;

    
    
    public RWorkbook()
    {
        this(null);
    }
    
    
    
    public RWorkbook(Workbook i_Workbook)
    {
        this.workbook         = i_Workbook;
        this.fonts            = new Hashtable<String ,Font>();
        this.cellStyles       = new Hashtable<String ,CellStyle>();
        this.cellStylesByCopy = new Hashtable<String ,CellStyle>();
    }
    
    
    
    /**
     * 保存工作薄
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-07-27
     * @version     v1.0
     *
     * @param i_SaveFile  保存的全路径+文件名称。当没有写扩展名称或类型不匹配时，自动识别添加
     * @return            保存成功返回：文件全路径+文件名称；异常返回：null
     */
    public final String save(String i_SaveFile)
    {
        return ExcelHelp.save(this ,i_SaveFile);
    }
    
    
    
    /**
     * 生成字体ID
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-29
     * @version     v1.0
     *
     * @param i_Font
     * @return
     */
    public static String makeFontID(Font i_Font)
    {
        StringBuilder v_Buffer = new StringBuilder();
        
        if ( i_Font instanceof HSSFFont )
        {
            HSSFFont v_Font = (HSSFFont)i_Font;
            
            v_Buffer.append(Help.NVL(v_Font.getFontName() ,"FN"));
            v_Buffer.append("_");
            v_Buffer.append(v_Font.getFontHeight());
            v_Buffer.append("_");
            v_Buffer.append(v_Font.getFontHeightInPoints());
            v_Buffer.append("_B");
            v_Buffer.append(v_Font.getBold() ? "1" : "0");
            v_Buffer.append("_I");
            v_Buffer.append(v_Font.getItalic() ? "1" : "0");
            v_Buffer.append("_S");
            v_Buffer.append(v_Font.getStrikeout() ? "1" : "0");
            v_Buffer.append("_U");
            v_Buffer.append(v_Font.getUnderline());
            v_Buffer.append("_T");
            v_Buffer.append(v_Font.getTypeOffset());
            v_Buffer.append("_C");
            v_Buffer.append(v_Font.getColor());
            v_Buffer.append("_");
            v_Buffer.append(v_Font.getCharSet());
        }
        else if ( i_Font instanceof XSSFFont )
        {
            XSSFFont v_Font = (XSSFFont)i_Font;
            
            v_Buffer.append(Help.NVL(v_Font.getFontName() ,"FN"));
            v_Buffer.append("_");
            v_Buffer.append(v_Font.getFontHeight());
            v_Buffer.append("_");
            v_Buffer.append(v_Font.getFontHeightInPoints());
            v_Buffer.append("_B");
            v_Buffer.append(v_Font.getBold() ? "1" : "0");
            v_Buffer.append("_I");
            v_Buffer.append(v_Font.getItalic() ? "1" : "0");
            v_Buffer.append("_S");
            v_Buffer.append(v_Font.getStrikeout() ? "1" : "0");
            v_Buffer.append("_U");
            v_Buffer.append(v_Font.getUnderline());
            v_Buffer.append("_T");
            v_Buffer.append(v_Font.getTypeOffset());
            v_Buffer.append("_TC");
            v_Buffer.append(v_Font.getThemeColor());
            v_Buffer.append("_");
            v_Buffer.append(v_Font.getCharSet());
            v_Buffer.append("_S");
            v_Buffer.append(v_Font.getScheme());
            
            v_Buffer.append("_C");
            if ( v_Font.getXSSFColor() != null )
            {
                v_Buffer.append(Help.NVL(v_Font.getXSSFColor().getARGBHex() ,v_Font.getColor()));
            }
            else
            {
                v_Buffer.append(v_Font.getColor());
            }
        }
        
        return v_Buffer.toString();
    }
    
    
    
    /**
     * 生成样式ID
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-29
     * @version     v1.0
     *
     * @param i_CellStyle
     * @param i_Font
     * @return
     */
    public static String makeCellStyleID(CellStyle i_CellStyle ,Font i_Font)
    {
        StringBuilder v_Buffer = new StringBuilder();
        
        if ( i_CellStyle instanceof HSSFCellStyle )
        {
            HSSFCellStyle v_CellStyle = (HSSFCellStyle)i_CellStyle;
            
            v_Buffer.append(v_CellStyle.getAlignment());
            v_Buffer.append("_DF");
            v_Buffer.append(v_CellStyle.getDataFormat());
            
            // 边框和边框颜色
            v_Buffer.append("_BB");
            v_Buffer.append(v_CellStyle.getBorderBottom());
            v_Buffer.append("_BL");
            v_Buffer.append(v_CellStyle.getBorderLeft());
            v_Buffer.append("_BR");
            v_Buffer.append(v_CellStyle.getBorderRight());
            v_Buffer.append("_BT");
            v_Buffer.append(v_CellStyle.getBorderTop());
            v_Buffer.append("_CBL");
            v_Buffer.append(v_CellStyle.getLeftBorderColor());
            v_Buffer.append("_CBR");
            v_Buffer.append(v_CellStyle.getRightBorderColor());
            v_Buffer.append("_CBT");
            v_Buffer.append(v_CellStyle.getTopBorderColor());
            v_Buffer.append("_CBB");
            v_Buffer.append(v_CellStyle.getBottomBorderColor());
            
            // 背景和前景
            v_Buffer.append("_CFB");
            v_Buffer.append(v_CellStyle.getFillBackgroundColor());
            v_Buffer.append("_CFF");
            v_Buffer.append(v_CellStyle.getFillForegroundColor());
            v_Buffer.append("_CFP");
            v_Buffer.append(v_CellStyle.getFillPattern());
            v_Buffer.append("_H");
            v_Buffer.append(v_CellStyle.getHidden());
            
            // 首行缩进
            v_Buffer.append("_I");
            v_Buffer.append(v_CellStyle.getIndention());
            v_Buffer.append("_L");
            v_Buffer.append(v_CellStyle.getLocked());

            // 旋转
            v_Buffer.append("_SF");
            v_Buffer.append(v_CellStyle.getShrinkToFit());
            v_Buffer.append("_R");
            v_Buffer.append(v_CellStyle.getRotation());
            v_Buffer.append("_VA");
            v_Buffer.append(v_CellStyle.getVerticalAlignment());
            v_Buffer.append("_WT");
            v_Buffer.append(v_CellStyle.getWrapText());
            
            v_Buffer.append("_QP");
            v_Buffer.append(v_CellStyle.getQuotePrefixed());
            v_Buffer.append("_RO");
            v_Buffer.append(v_CellStyle.getReadingOrder());
            v_Buffer.append("_USN");
            v_Buffer.append(v_CellStyle.getUserStyleName());
        }
        else if ( i_CellStyle instanceof XSSFCellStyle )
        {
            XSSFCellStyle v_CellStyle = (XSSFCellStyle)i_CellStyle;
            
            v_Buffer.append(v_CellStyle.getAlignment());
            v_Buffer.append("_DF");
            v_Buffer.append(v_CellStyle.getDataFormat());
            
            // 边框和边框颜色
            v_Buffer.append("_BB");
            v_Buffer.append(v_CellStyle.getBorderBottom());
            v_Buffer.append("_BL");
            v_Buffer.append(v_CellStyle.getBorderLeft());
            v_Buffer.append("_BR");
            v_Buffer.append(v_CellStyle.getBorderRight());
            v_Buffer.append("_BT");
            v_Buffer.append(v_CellStyle.getBorderTop());
            
            v_Buffer.append("_CBL");
            if ( v_CellStyle.getLeftBorderXSSFColor() != null )
            {
                v_Buffer.append(Help.NVL(v_CellStyle.getLeftBorderXSSFColor().getARGBHex() ,v_CellStyle.getLeftBorderColor()));
            }
            else
            {
                v_Buffer.append(v_CellStyle.getLeftBorderColor());
            }
            
            v_Buffer.append("_CBR");
            if ( v_CellStyle.getRightBorderXSSFColor() != null )
            {
                v_Buffer.append(Help.NVL(v_CellStyle.getRightBorderXSSFColor().getARGBHex() ,v_CellStyle.getRightBorderColor()));
            }
            else
            {
                v_Buffer.append(v_CellStyle.getRightBorderColor());
            }
            
            v_Buffer.append("_CBT");
            if ( v_CellStyle.getTopBorderXSSFColor() != null )
            {
                v_Buffer.append(Help.NVL(v_CellStyle.getTopBorderXSSFColor().getARGBHex() ,v_CellStyle.getTopBorderColor()));
            }
            else
            {
                v_Buffer.append(v_CellStyle.getTopBorderColor());
            }
            
            v_Buffer.append("_CBB");
            if ( v_CellStyle.getBottomBorderXSSFColor() != null )
            {
                v_Buffer.append(Help.NVL(v_CellStyle.getBottomBorderXSSFColor().getARGBHex() ,v_CellStyle.getBottomBorderColor()));
            }
            else
            {
                v_Buffer.append(v_CellStyle.getBottomBorderColor());
            }
            
            // 背景和前景
            v_Buffer.append("_CFB");
            if ( v_CellStyle.getFillBackgroundXSSFColor() != null )
            {
                v_Buffer.append(Help.NVL(v_CellStyle.getFillBackgroundXSSFColor().getARGBHex() ,v_CellStyle.getFillBackgroundColor()));
            }
            else if ( v_CellStyle.getFillBackgroundColorColor() != null )
            {
                v_Buffer.append(Help.NVL(v_CellStyle.getFillBackgroundColorColor().getARGBHex() ,v_CellStyle.getFillBackgroundColor()));
            }
            else
            {
                v_Buffer.append(v_CellStyle.getFillBackgroundColor());
            }
            
            v_Buffer.append("_CFF");
            if ( v_CellStyle.getFillForegroundXSSFColor() != null )
            {
                v_Buffer.append(Help.NVL(v_CellStyle.getFillForegroundXSSFColor().getARGBHex() ,v_CellStyle.getFillForegroundColor()));
            }
            else if ( v_CellStyle.getFillForegroundColorColor() != null )
            {
                v_Buffer.append(Help.NVL(v_CellStyle.getFillForegroundColorColor().getARGBHex() ,v_CellStyle.getFillForegroundColor()));
            }
            else
            {
                v_Buffer.append(v_CellStyle.getFillForegroundColor());
            }
            
            v_Buffer.append("_CFP");
            v_Buffer.append(v_CellStyle.getFillPattern());
            v_Buffer.append("_H");
            v_Buffer.append(v_CellStyle.getHidden());
            
            // 首行缩进
            v_Buffer.append("_I");
            v_Buffer.append(v_CellStyle.getIndention());
            v_Buffer.append("_L");
            v_Buffer.append(v_CellStyle.getLocked());

            // 旋转
            v_Buffer.append("_SF");
            v_Buffer.append(v_CellStyle.getShrinkToFit());
            v_Buffer.append("_R");
            v_Buffer.append(v_CellStyle.getRotation());
            v_Buffer.append("_VA");
            v_Buffer.append(v_CellStyle.getVerticalAlignment());
            v_Buffer.append("_WT");
            v_Buffer.append(v_CellStyle.getWrapText());
            
            v_Buffer.append("_QP");
            v_Buffer.append(v_CellStyle.getQuotePrefixed());
        }
        
        v_Buffer.append("_CSF_");
        v_Buffer.append(makeFontID(i_Font));
        
        return v_Buffer.toString();
    }
    
    
    
    /**
     * 获取模板指定位置上的已转为本工作薄的字体
     * 
     * 目前看，只用于2003的版本(*.xls)，2007的版本是可以直接 setFont() 方法设置字体的。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate  模板对象
     * @param i_IDX        字体在模板中的索引位置
     * @return
     */
    public synchronized Font getFont(RTemplate i_RTemplate ,int i_IDX)
    {
        Font   v_FromFont = i_RTemplate.getTemplateSheet().getWorkbook().getFontAt((short)i_IDX);
        String v_FontID   = makeFontID(v_FromFont);
        Font   v_ToFont   = this.fonts.get(v_FontID);
        
        if ( v_ToFont == null )
        {
            v_ToFont = this.workbook.createFont();
            ExcelHelp.copyFont(v_FromFont ,v_ToFont);
            
            this.fonts.put(v_FontID ,v_ToFont);
        }
        
        return v_ToFont;
    }
    
    
    
    /**
     * 获取模板指定位置上的已转为本工作薄的单元格样式
     * 
     * 目前看，只用于2003的版本(*.xls)，2007的版本是可以直接 setCellStyle() 方法设置字体的。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate  模板对象
     * @param i_IDX        单元格样式在模板中的索引位置
     * @return
     */
    public synchronized CellStyle getCellStyle(RTemplate i_RTemplate ,int i_IDX)
    {
        CellStyle v_FromCellStyle = i_RTemplate.getTemplateSheet().getWorkbook().getCellStyleAt(i_IDX);
        Font      v_FromFont      = i_RTemplate.getTemplateSheet().getWorkbook().getFontAt(v_FromCellStyle.getFontIndex());
        String    v_CellStyleID   = makeCellStyleID(v_FromCellStyle ,v_FromFont);
        CellStyle v_ToCellStyle   = this.cellStyles.get(v_CellStyleID);
        
        if ( v_ToCellStyle == null )
        {
            v_ToCellStyle = this.workbook.createCellStyle();
            
            ExcelHelp.copyCellStyle(v_FromCellStyle ,v_ToCellStyle);
            Font v_ToFont = this.getFont(i_RTemplate ,v_FromFont.getIndex());
            
            v_ToCellStyle.setFont(v_ToFont);
            
            this.cellStyles.put(v_CellStyleID ,v_ToCellStyle);
        }
        
        return v_ToCellStyle;
    }
    
    
    
    /**
     * 创建一个新的样式，样式从i_DataCell中克隆出来。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-11
     * @version     v1.0
     *
     * @param i_ID         标记ID。由调用者设定
     * @param i_DataCell   被克隆的单元格样式
     * @return
     */
    public synchronized CellStyle getCellStyleByCopy(String i_ID ,Cell i_DataCell ,RTemplate i_RTemplate)
    {
        CellStyle v_NewCellStyle = this.cellStylesByCopy.get(i_ID);
        
        if ( v_NewCellStyle == null )
        {
            v_NewCellStyle = this.workbook.createCellStyle();
            
            ExcelHelp.copyCellStyle(i_DataCell.getCellStyle(), v_NewCellStyle);
            
            Font v_FromFont = this.workbook.getFontAt(i_DataCell.getCellStyle().getFontIndex());
            Font v_NewFont  = this.workbook.createFont();
            ExcelHelp.copyFont(v_FromFont ,v_NewFont);
            
            v_NewCellStyle.setFont(v_NewFont);
            
            this.cellStylesByCopy.put(i_ID ,v_NewCellStyle);
        }
        
        return v_NewCellStyle;
    }
    
    
    
    /**
     * 创建一个新的字体，字体从i_DataCell中克隆出来。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-11
     * @version     v1.0
     *
     * @param i_ID         标记ID。由调用者设定
     * @param i_DataCell   被克隆的单元格样式
     * @return
     */
    public synchronized Font getFontByCopy(String i_ID ,Cell i_DataCell ,RTemplate i_RTemplate)
    {
        Font v_NewFont = this.fontsByCopy.get(i_ID);
        
        if ( v_NewFont == null )
        {
            v_NewFont = this.workbook.createFont();
            
            ExcelHelp.copyFont(this.workbook.getFontAt(i_DataCell.getCellStyle().getFontIndex()) ,v_NewFont);
            
            this.fontsByCopy.put(i_ID ,v_NewFont);
        }
        
        return v_NewFont;
    }
    
    
    
    /**
     * 获取：工作薄
     */
    public Workbook getWorkbook()
    {
        return workbook;
    }

    
    
    /**
     * 设置：工作薄
     * 
     * @param workbook
     */
    public void setWorkbook(Workbook workbook)
    {
        this.workbook = workbook;
    }

    
    
    /**
     * 工作薄所用到的模板中的字体信息。
     * 
     * 此字体为已在工作薄中创建过的字体，是本工作薄的字体对象，不是模板的。
     * 
     * Map.key 为字体ID
     */
    public Map<String ,Font> getFonts()
    {
        return fonts;
    }

    
    
    /**
     * 工作薄所用到的模板中的字体信息。
     * 
     * 此字体为已在工作薄中创建过的字体，是本工作薄的字体对象，不是模板的。
     * 
     * Map.key 为字体ID
     * 
     * @param fonts
     */
    public void setFonts(Map<String ,Font> fonts)
    {
        this.fonts = fonts;
    }


    
    /**
     * 工作薄所用到的模板中的单元格样式信息。
     * 
     * 此单元格样式为已在工作薄中创建过的单元格样式，是本工作薄的单元格样式对象，不是模板的。
     * 
     * Map.key 为样式ID
     */
    public Map<String ,CellStyle> getCellStyles()
    {
        return cellStyles;
    }

    

    /**
     * 工作薄所用到的模板中的单元格样式信息。
     * 
     * 此单元格样式为已在工作薄中创建过的单元格样式，是本工作薄的单元格样式对象，不是模板的。
     * 
     * Map.key 为样式ID
     * 
     * @param cellStyles
     */
    public void setCellStyles(Map<String ,CellStyle> cellStyles)
    {
        this.cellStyles = cellStyles;
    }
    
}
