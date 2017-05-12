package org.hy.common.report.junit.readHorizontal;

import org.hy.common.Date;
import org.hy.common.xml.SerializableDef;





/**
 * 财务信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-05-08
 * @version     v1.0
 */
public class Finance extends SerializableDef
{
    
    private static final long serialVersionUID = 1788013639015556011L;

    
    /** 收益 */
    private Double income;
    
    /** 支出 */
    private Double pay;
    
    /** 流动比 */
    private String rate;
    
    /** 时间 */
    private Date   time;

    
    
    /**
     * 获取：收益
     */
    public Double getIncome()
    {
        return income;
    }

    
    /**
     * 设置：收益
     * 
     * @param income 
     */
    public void setIncome(Double income)
    {
        this.income = income;
    }

    
    /**
     * 获取：支出
     */
    public Double getPay()
    {
        return pay;
    }

    
    /**
     * 设置：支出
     * 
     * @param pay 
     */
    public void setPay(Double pay)
    {
        this.pay = pay;
    }


    
    /**
     * 获取：流动比
     */
    public String getRate()
    {
        return rate;
    }


    
    /**
     * 设置：流动比
     * 
     * @param rate 
     */
    public void setRate(String rate)
    {
        this.rate = rate;
    }


    
    /**
     * 获取：时间
     */
    public Date getTime()
    {
        return time;
    }


    
    /**
     * 设置：时间
     * 
     * @param time 
     */
    public void setTime(Date time)
    {
        this.time = time;
    }
    
}
