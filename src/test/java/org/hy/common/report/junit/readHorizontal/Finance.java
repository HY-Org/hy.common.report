package org.hy.common.report.junit.readHorizontal;

import java.sql.Timestamp;

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
    private Double     income;
    
    /** 支出 */
    private Double    pay;
    
    /** 流动比 */
    private String    rate;
    
    /** 时间 */
    private Date      time;
    
    /** 数据库时间 */
    private Timestamp time2;
    
    /** yyyy-MM格式的时间 */
    private Date      time3;
    
    /* 纳税（千分位） */
    private Double    tax;

    
    
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


    
    /**
     * 获取：数据库时间
     */
    public Timestamp getTime2()
    {
        return time2;
    }
    

    
    /**
     * 设置：数据库时间
     * 
     * @param time2
     */
    public void setTime2(Timestamp time2)
    {
        this.time2 = time2;
    }


    
    /**
     * 获取：yyyy-MM格式的时间
     */
    public Date getTime3()
    {
        return time3;
    }
    

    
    /**
     * 设置：yyyy-MM格式的时间
     * 
     * @param time3
     */
    public void setTime3(Date time3)
    {
        this.time3 = time3;
    }

    

    /**
     * 获取：纳税（千分位）
     */
    public Double getTax()
    {
        return tax;
    }


    
    /**
     * 设置：纳税（千分位）
     * 
     * @param tax
     */
    public void setTax(Double tax)
    {
        this.tax = tax;
    }
    
}
