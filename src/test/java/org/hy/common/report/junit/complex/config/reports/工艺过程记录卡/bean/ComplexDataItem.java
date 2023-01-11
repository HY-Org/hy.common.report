package org.hy.common.report.junit.complex.config.reports.工艺过程记录卡.bean;





/**
 * 复杂报表的子集合列表对象
 *
 * @author      ZhengWei(HY)
 * @createDate  2022-12-10
 * @version     v1.0
 */
public class ComplexDataItem
{
    
    /** 工序序号 */
    private String processNo;
    
    /** 工序名称 */
    private String processName;
    
    /** 检查项目及要求 */
    private String drawingsRequired;

    
    
    /**
     * 获取：工序序号
     */
    public String getProcessNo()
    {
        return processNo;
    }

    
    /**
     * 设置：工序序号
     * 
     * @param i_ProcessNo 工序序号
     */
    public void setProcessNo(String i_ProcessNo)
    {
        this.processNo = i_ProcessNo;
    }

    
    /**
     * 获取：工序名称
     */
    public String getProcessName()
    {
        return processName;
    }

    
    /**
     * 设置：工序名称
     * 
     * @param i_ProcessName 工序名称
     */
    public void setProcessName(String i_ProcessName)
    {
        this.processName = i_ProcessName;
    }

    
    /**
     * 获取：检查项目及要求
     */
    public String getDrawingsRequired()
    {
        return drawingsRequired;
    }

    
    /**
     * 设置：检查项目及要求
     * 
     * @param i_DrawingsRequired 检查项目及要求
     */
    public void setDrawingsRequired(String i_DrawingsRequired)
    {
        this.drawingsRequired = i_DrawingsRequired;
    }
    
}
