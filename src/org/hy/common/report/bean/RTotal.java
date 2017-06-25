package org.hy.common.report.bean;





/**
 * 将数据写入Excel时的辅助统计信息。
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-06-25
 * @version     v1.0
 */
public class RTotal
{
    /** Excel工作表中已写到哪一行的行号（Excel的行号）。下标从 0 开始 */
    private int excelRowIndex;
    
    /** 当前正在写入真实数据(Excel中的一行)的数据个数（动态行的情况下，最外层的一行会动态的变成多行）到Excel工作表中。有效下标从 1 开始 */
    private int realDataCount;

    
    
    public RTotal()
    {
        this.excelRowIndex = 0;
        this.realDataCount = 0;
    }
    
    
    /**
     * 获取：Excel工作表中已写到哪一行的行号（Excel的行号）。下标从 0 开始
     */
    public int getExcelRowIndex()
    {
        return excelRowIndex;
    }
    
    
    /**
     * 设置：Excel工作表中已写到哪一行的行号（Excel的行号）。下标从 0 开始
     * 
     * @param excelRowIndex 
     */
    protected void setExcelRowIndex(int excelRowIndex)
    {
        this.excelRowIndex = excelRowIndex;
    }
    

    /**
     * 累计设置：Excel工作表中已写到哪一行的行号（Excel的行号）。下标从 0 开始
     * 
     * @param excelRowIndex 
     */
    public void addExcelRowIndex(int excelRowIndex)
    {
        this.excelRowIndex += excelRowIndex;
    }
    
    
    /**
     * 获取：当前正在写入真实数据(Excel中的一行)的数据个数（动态行的情况下，最外层的一行会动态的变成多行）到Excel工作表中。有效下标从 1 开始
     */
    public int getRealDataCount()
    {
        return realDataCount;
    }
    
    
    /**
     * 设置：当前正在写入真实数据(Excel中的一行)的数据个数（动态行的情况下，最外层的一行会动态的变成多行）到Excel工作表中。有效下标从 1 开始
     * 
     * @param realDataCount 
     */
    protected void setRealDataCount(int realDataCount)
    {
        this.realDataCount = realDataCount;
    }


    /**
     * 累计设置：当前正在写入真实数据(Excel中的一行)的数据个数（动态行的情况下，最外层的一行会动态的变成多行）到Excel工作表中。有效下标从 1 开始
     * 
     * @param realDataCount
     */
    public void addRealDataCount(int realDataCount)
    {
        this.realDataCount += realDataCount;
    }
    
}
