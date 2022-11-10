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
    
    /** 
     * 已写入真实数据(Excel中的一行)的数据个数（动态行的情况下，最外层的一行会动态的变成多行）到Excel工作表中。
     * 下标从 0 开始。 
     */
    private int realDataCount;
    
    /** 标题的总行数（一次解释多次使用，只为了性能） */
    private int titleCount;
    
    /** 分页页眉标题的总行数（一次解释多次使用，只为了性能） */
    private int titlePageHeaderCount;
    
    /** 分页页脚标题的总行数（一次解释多次使用，只为了性能） */
    private int titlePageFooterCount;
    
    /** 有分页页脚时，最后一页数据量没有填充够一页数据时的填充数据 */
    private int fillInCount;

    
    
    public RTotal(RTemplate i_RTemplate)
    {
        this.excelRowIndex        = 0;
        this.realDataCount        = 0;
        this.titleCount           = i_RTemplate.getRowCountTitle();
        this.titlePageHeaderCount = i_RTemplate.getRowCountTitlePageHeader();
        this.titlePageFooterCount = i_RTemplate.getRowCountTitlePageFooter();
        this.fillInCount          = 0;
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
     * 获取：已写入真实数据(Excel中的一行)的数据个数（动态行的情况下，最外层的一行会动态的变成多行）到Excel工作表中。下标从 0 开始
     */
    public int getRealDataCount()
    {
        return realDataCount;
    }
    
    
    /**
     * 设置：已写入真实数据(Excel中的一行)的数据个数（动态行的情况下，最外层的一行会动态的变成多行）到Excel工作表中。下标从 0 开始
     * 
     * @param realDataCount 
     */
    protected void setRealDataCount(int realDataCount)
    {
        this.realDataCount = realDataCount;
    }


    /**
     * 累计设置：已写入真实数据(Excel中的一行)的数据个数（动态行的情况下，最外层的一行会动态的变成多行）到Excel工作表中。下标从 0 开始
     * 
     * @param realDataCount
     */
    public void addRealDataCount(int realDataCount)
    {
        this.realDataCount += realDataCount;
    }

    
    /**
     * 获取：标题的总行数（一次解释多次使用，只为了性能）
     */
    public int getTitleCount()
    {
        return titleCount;
    }
    
    
    /**
     * 获取：分页页眉标题的总行数（一次解释多次使用，只为了性能）
     */
    public int getTitlePageHeaderCount()
    {
        return titlePageHeaderCount;
    }

    
    /**
     * 获取：分页页脚标题的总行数（一次解释多次使用，只为了性能）
     */
    public int getTitlePageFooterCount()
    {
        return titlePageFooterCount;
    }

    
    /**
     * 获取：有分页页脚时，最后一页数据量没有填充够一页数据时的填充数据
     */
    public int getFillInCount()
    {
        return fillInCount;
    }
    

    /**
     * 设置：有分页页脚时，最后一页数据量没有填充够一页数据时的填充数据
     * 
     * @param fillInCount 
     */
    public void setFillInCount(int fillInCount)
    {
        this.fillInCount = fillInCount;
    }
    
}
