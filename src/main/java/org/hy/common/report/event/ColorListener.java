package org.hy.common.report.event;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.event.ValueListener;





/**
 * 单元格颜色的监听器 (奇偶行变色、隔行变色)
 *
 * @author      ZhengWei(HY)
 * @createDate  2018-06-22
 * @version     v1.0
 */
public class ColorListener implements ValueListener
{
    
    /** 建议是行的最后一列的占位符 */
    private String valueName;
    
    /** 奇数行颜色 */
    private short  oddNumberolor;
    
    /** 偶数行颜色 */
    private short  evenNumberColor;
    
    
    
    public ColorListener()
    {
        this.oddNumberolor   = IndexedColors.RED.index;
        this.evenNumberColor = IndexedColors.GREEN.index;
    }
    
    
    
    /**
     * 变量名称
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-29
     * @version     v1.0
     *
     * @return
     */
    public String getValueName()
    {
        return this.valueName;
    }
    
    
    
    public void setValueName(String i_ValueName)
    {
        this.valueName = i_ValueName;
    }
    
    
    
    /**
     * 获取：奇数行颜色
     */
    public int getOddNumberolor()
    {
        return oddNumberolor;
    }
    

    
    /**
     * 获取：偶数行颜色
     */
    public int getEvenNumberColor()
    {
        return evenNumberColor;
    }
    

    
    /**
     * 设置：奇数行颜色
     * 
     * @param oddNumberolor 
     */
    public void setOddNumberolor(short oddNumberolor)
    {
        this.oddNumberolor = oddNumberolor;
    }


    
    /**
     * 设置：偶数行颜色
     * 
     * @param evenNumberColor 
     */
    public void setEvenNumberColor(short evenNumberColor)
    {
        this.evenNumberColor = evenNumberColor;
    }
    


    /**
     * 对变量名称反射出来的值进行加工处理
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-29
     * @version     v1.0
     *
     * @param i_RTemplate     模板
     * @param i_TemplateCell  模板单元格对象
     * @param i_DataCell      数据单元格对象
     * @param i_DataWorkbook  数据工作薄对象
     * @param i_RSystemValue  系统变量信息
     * @param i_Datas         本行对应的数据
     * @param i_Value         反射出来的变量名称对应的值
     * @return 
     */
    public String getValue(RTemplate i_RTemplate ,Cell i_TemplateCell ,Cell i_DataCell ,RWorkbook i_DataWorkbook ,RSystemValue i_RSystemValue ,Object i_Datas ,Object i_Value)
    {
        CellStyle v_NewCellStyle = null;
        
        if ( i_DataCell.getRow().getRowNum() % 2 == 1 )
        {
            v_NewCellStyle = i_DataWorkbook.getCellStyleByCopy("奇数行" ,i_DataCell ,i_RTemplate);
            v_NewCellStyle.setFillForegroundColor(this.oddNumberolor);
        }
        else
        {
            v_NewCellStyle = i_DataWorkbook.getCellStyleByCopy("偶数行" ,i_DataCell ,i_RTemplate);
            v_NewCellStyle.setFillForegroundColor(this.evenNumberColor);
        }
        
        v_NewCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        Row v_Row       = i_DataCell.getRow();
        int v_CellCount = v_Row.getLastCellNum();
        for (int i=0; i<v_CellCount; i++)
        {
            v_Row.getCell(i).setCellStyle(v_NewCellStyle);
        }
        
        if ( i_Value == null )
        {
            return "";
        }
        
        return i_Value.toString();
    }
    
}
