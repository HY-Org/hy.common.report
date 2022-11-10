package org.hy.common.report.junit.readHorizontal;

import org.hy.common.xml.SerializableDef;





/**
 * 工厂 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-05-12
 * @version     v1.0
 */
public class Factory extends SerializableDef
{
    
    private static final long serialVersionUID = 7686939889530368720L;
    
    
    /** 月份 */
    private String month;
    
    /** 财务信息  */
    private Finance finance;


    
    /**
     * 获取：月份
     */
    public String getMonth()
    {
        return month;
    }


    
    /**
     * 设置：月份
     * 
     * @param month 
     */
    public void setMonth(String month)
    {
        this.month = month;
    }

    

    /**
     * 获取：财务信息
     */
    public Finance getFinance()
    {
        return finance;
    }


    
    /**
     * 设置：财务信息
     * 
     * @param finance 
     */
    public void setFinance(Finance finance)
    {
        this.finance = finance;
    }
    
}
