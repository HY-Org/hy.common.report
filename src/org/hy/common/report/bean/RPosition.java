package org.hy.common.report.bean;





/**
 * 位置信息
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-06-25
 * @version     v1.0
 */
public class RPosition
{
    /** 开始位置 */
    private int beginNo;
    
    /** 结束位置 */
    private int endNo;
    
    
    
    public RPosition()
    {
        this(0 ,0);
    }
    
    
    
    public RPosition(int i_BeginNo ,int i_EndNo)
    {
        this.beginNo = i_BeginNo;
        this.endNo   = i_EndNo;
    }
    
    
    
    /**
     * 获取：开始位置
     */
    public int getBeginNo()
    {
        return beginNo;
    }
    

    
    /**
     * 设置：开始位置
     * 
     * @param beginNo 
     */
    public void setBeginNo(int beginNo)
    {
        this.beginNo = beginNo;
    }
    

    
    /**
     * 获取：结束位置
     */
    public int getEndNo()
    {
        return endNo;
    }
    

    
    /**
     * 设置：结束位置
     * 
     * @param endNo 
     */
    public void setEndNo(int endNo)
    {
        this.endNo = endNo;
    }
    
}
