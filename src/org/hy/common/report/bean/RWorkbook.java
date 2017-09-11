package org.hy.common.report.bean;

import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.hy.common.TablePartitionRID;
import org.hy.common.report.ExcelHelp;





/**
 * 工作薄 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 *              v2.0  2017-09-11  1. 添加外界(第三方)创建样式、字体，并通过标记ID保存，方便二次引用的功能。
 *                                   建议人：李浩
 */
public class RWorkbook
{
    
    /** 工作薄 */
    private Workbook workbook;
    
    /** 
     * 工作薄所用到的模板中的字体信息。
     * 
     * 此字体为已在工作薄中创建过的字体，是本工作薄的字体对象，不是模板的。
     * 但字体的索引位置与模板保质一样
     * 
     * 每个分区表中的主键是：模板字体的位置索引
     */
    private TablePartitionRID<RTemplate ,Font> fonts;
    
    /** 
     * 工作薄所用到的模板中的单元格样式信息。
     * 
     * 此单元格样式为已在工作薄中创建过的单元格样式，是本工作薄的单元格样式对象，不是模板的。
     * 但单元格样式的索引位置与模板保质一样
     * 
     * 每个分区表中的主键是：模板单元格样式的位置索引
     */
    private TablePartitionRID<RTemplate ,CellStyle> cellStyles;
    
    /**
     * 第三方用户动态通过克隆创建出来的字体。
     * 
     * Map.key    为第三方自行定义的标识
     * Map.value  为 this.fonts 中的分区主键
     */
    private Map<String ,Short> fontsByCopy;
    
    /**
     * 第三方用户动态通过克隆创建出来的样式。
     * 
     * Map.key    为第三方自行定义的标识
     * Map.value  为 this.cellStyles 中的分区主键
     */
    private Map<String ,Short> cellStylesByCopy;

    
    
    public RWorkbook()
    {
        this(null);
    }
    
    
    
    public RWorkbook(Workbook i_Workbook)
    {
        this.workbook   = i_Workbook;
        this.fonts      = new TablePartitionRID<RTemplate ,Font>();
        this.cellStyles = new TablePartitionRID<RTemplate ,CellStyle>();
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
    public synchronized Font getFont(RTemplate i_RTemplate ,short i_IDX)
    {
        Font v_DataFont = this.fonts.getRow(i_RTemplate ,String.valueOf(i_IDX));
        
        if ( v_DataFont == null )
        {
            v_DataFont = this.workbook.createFont();
            ExcelHelp.copyFont(i_RTemplate.getTemplateSheet().getWorkbook().getFontAt(i_IDX) ,v_DataFont);
            
            this.fonts.putRow(i_RTemplate ,String.valueOf(i_IDX) ,v_DataFont);
        }
        
        return v_DataFont;
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
    public synchronized CellStyle getCellStyle(RTemplate i_RTemplate ,short i_IDX)
    {
        CellStyle v_DataCellStyle = this.cellStyles.getRow(i_RTemplate ,String.valueOf(i_IDX));
        
        if ( v_DataCellStyle == null )
        {
            v_DataCellStyle = this.workbook.createCellStyle();
            
            CellStyle v_TemplateCellStyle = i_RTemplate.getTemplateSheet().getWorkbook().getCellStyleAt(i_IDX);
            ExcelHelp.copyCellStyle(v_TemplateCellStyle ,v_DataCellStyle);
            
            v_DataCellStyle.setFont(this.getFont(i_RTemplate ,v_TemplateCellStyle.getFontIndex()));
            
            this.cellStyles.putRow(i_RTemplate ,String.valueOf(i_IDX) ,v_DataCellStyle);
        }
        
        return v_DataCellStyle;
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
        Short     v_CellStyleID = this.cellStylesByCopy.get(i_ID);
        CellStyle v_CellStyle   = null;
        
        if ( v_CellStyleID == null )
        {
            v_CellStyle = this.workbook.createCellStyle();
            
            ExcelHelp.copyCellStyle(i_DataCell.getCellStyle(), v_CellStyle);
            
            this.cellStyles.putRow(i_RTemplate ,String.valueOf(v_CellStyle.getIndex()) ,v_CellStyle);
            this.cellStylesByCopy.put(i_ID ,v_CellStyle.getIndex());
        }
        else
        {
            v_CellStyle = this.getCellStyle(i_RTemplate ,v_CellStyleID);
        }
        
        return v_CellStyle;
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
        Short v_FontID = this.fontsByCopy.get(i_ID);
        Font  v_Font   = null;
        
        if ( v_FontID == null )
        {
            v_Font = this.workbook.createFont();
            
            ExcelHelp.copyFont(this.workbook.getFontAt(i_DataCell.getCellStyle().getFontIndex()) ,v_Font);
            
            this.fonts.putRow(i_RTemplate ,String.valueOf(v_Font.getIndex()) ,v_Font);
            this.fontsByCopy.put(i_ID ,v_Font.getIndex());
        }
        else
        {
            v_Font = this.getFont(i_RTemplate ,v_FontID);
        }
        
        return v_Font;
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
     * 获取：工作薄所用到的模板中的字体信息。
     * 
     * 此字体为已在工作薄中创建过的字体，是本工作薄的字体对象，不是模板的。
     * 但字体的索引位置与模板保质一样
     * 
     * 每个分区表中的主键是：原模板字体的位置索引
     */
    public TablePartitionRID<RTemplate ,Font> getFonts()
    {
        return fonts;
    }

    
    
    /**
     * 设置：工作薄所用到的模板中的字体信息。
     * 
     * 此字体为已在工作薄中创建过的字体，是本工作薄的字体对象，不是模板的。
     * 但字体的索引位置与模板保质一样
     * 
     * 每个分区表中的主键是：原模板字体的位置索引
     * 
     * @param fonts 
     */
    public void setFonts(TablePartitionRID<RTemplate ,Font> fonts)
    {
        this.fonts = fonts;
    }


    
    /**
     * 获取：工作薄所用到的模板中的单元格样式信息。
     * 
     * 此单元格样式为已在工作薄中创建过的单元格样式，是本工作薄的单元格样式对象，不是模板的。
     * 但单元格样式的索引位置与模板保质一样
     * 
     * 每个分区表中的主键是：模板单元格样式的位置索引
     */
    public TablePartitionRID<RTemplate ,CellStyle> getCellStyles()
    {
        return cellStyles;
    }

    

    /**
     * 设置：工作薄所用到的模板中的单元格样式信息。
     * 
     * 此单元格样式为已在工作薄中创建过的单元格样式，是本工作薄的单元格样式对象，不是模板的。
     * 但单元格样式的索引位置与模板保质一样
     * 
     * 每个分区表中的主键是：模板单元格样式的位置索引
     * 
     * @param cellStyles 
     */
    public void setCellStyles(TablePartitionRID<RTemplate ,CellStyle> cellStyles)
    {
        this.cellStyles = cellStyles;
    }
    
}
