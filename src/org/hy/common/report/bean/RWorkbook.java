package org.hy.common.report.bean;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.hy.common.TablePartitionRID;





/**
 * 工作薄 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
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
     * 每个分区表中的主键是：原模板字体的位置索引
     */
    private TablePartitionRID<RTemplate ,Font> fonts;

    
    
    public RWorkbook()
    {
        this(null);
    }
    
    
    
    public RWorkbook(Workbook i_Workbook)
    {
        this.workbook = i_Workbook;
        this.fonts    = new TablePartitionRID<RTemplate ,Font>();
    }
    
    
    
    /**
     * 获取模板指定位置上的已转为本工作薄的字体
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate  模板对象
     * @param i_IDX        字体在模板中的索引位置
     * @return
     */
    public Font getFont(RTemplate i_RTemplate ,short i_IDX)
    {
        return this.fonts.getRow(i_RTemplate ,String.valueOf(i_IDX));
    }
    
    
    
    /**
     * 添加模板指定位置上的已转为本工作薄的字体
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate  模板对象
     * @param i_IDX        字体在模板中的位置索引
     * @param i_Font       已转为本工作薄的字体
     * @return
     */
    public Font putFont(RTemplate i_RTemplate ,short i_IDX ,Font i_Font)
    {
        return this.fonts.putRow(i_RTemplate ,String.valueOf(i_IDX) ,i_Font);
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
    
}
