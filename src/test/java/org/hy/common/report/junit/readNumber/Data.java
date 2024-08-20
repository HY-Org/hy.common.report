package org.hy.common.report.junit.readNumber;

import org.hy.common.xml.SerializableDef;





/**
 * Excel中的行数据
 *
 * @author      ZhengWei(HY)
 * @createDate  2024-07-24
 * @version     v1.0
 */
public class Data extends SerializableDef
{
    
    private static final long serialVersionUID = 7028172837721272616L;

    /** 序号 */
    private Integer dataIndex;
    
    /** 数量 */
    private Integer  dataCount;

    
    /**
     * 获取：序号
     */
    public Integer getDataIndex()
    {
        return dataIndex;
    }

    
    /**
     * 设置：序号
     * 
     * @param i_DataIndex 序号
     */
    public void setDataIndex(Integer i_DataIndex)
    {
        this.dataIndex = i_DataIndex;
    }

    
    /**
     * 获取：数量
     */
    public Integer getDataCount()
    {
        return dataCount;
    }

    
    /**
     * 设置：数量
     * 
     * @param i_DataCount 数量
     */
    public void setDataCount(Integer i_DataCount)
    {
        this.dataCount = i_DataCount;
    }
    
    
    /**
     * 获取：数量
     */
    public Integer getDataCountString()
    {
        return dataCount;
    }

    
    /**
     * 设置：数量
     * 
     * @param i_DataCount 数量
     */
    public void setDataCountString(String i_DataCount)
    {
        this.dataCount = Integer.parseInt(i_DataCount);
    }
    
}
