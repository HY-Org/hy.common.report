package org.hy.common.report.junit.normal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.event.ValueListener;





/**
 * 单元格颜色的监听器 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-09-20
 * @version     v1.0
 */
public class ColorListener implements ValueListener
{
    
    private String  valueName;
    
    
    
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
            v_NewCellStyle = i_DataWorkbook.getCellStyleByCopy("单行" ,i_DataCell ,i_RTemplate);
            v_NewCellStyle.setFillForegroundColor(IndexedColors.ORANGE.index);
        }
        else
        {
            v_NewCellStyle = i_DataWorkbook.getCellStyleByCopy("双行" ,i_DataCell ,i_RTemplate);
            v_NewCellStyle.setFillForegroundColor(IndexedColors.RED.index);
        }
        
        v_NewCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        i_DataCell.setCellStyle(v_NewCellStyle);
        
        if ( i_Value == null )
        {
            return "";
        }
        
        return i_Value.toString();
    }
    
}
