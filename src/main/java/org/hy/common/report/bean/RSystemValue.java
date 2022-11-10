package org.hy.common.report.bean;





/**
 * 系统变量信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-28
 * @version     v1.0
 *              v2.0  2020-04-10  添加：在多个不同模板，以追加模式写入时，对模板中图片作偏移量的修正。
 */
public class RSystemValue
{
    
    /** 数据行号的变量名称。下标从1开始 */
    private int rowNo;
    
    /** 数据总量的变量名称 */
    private int rowCount;
    
    /** 数据小计总量的变量名称 */
    private int rowSubtotalCount;
    
    /** 分页页号的变量名称。下标从1开始 */
    private int pageNo;
    
    /** 分页总页数的变量名称 */
    private int pageSize;
    
    /** 追加模式下的，记录追加前已有数据的最大行号。下标从0开始。默认为：0 */
    private int appendStartRowIndex;
    
    
    
    public RSystemValue()
    {
        this.appendStartRowIndex = 0;
    }   
    
    
    /**
     * 获取：数据行号的变量名称。下标从1开始
     */
    public int getRowNo()
    {
        return rowNo;
    }

    
    /**
     * 设置：数据行号的变量名称。下标从1开始
     * 
     * @param rowNo 
     */
    public void setRowNo(int rowNo)
    {
        this.rowNo = rowNo;
    }

    
    /**
     * 获取：数据索引号的变量名称。下标从0开始
     */
    public int getRowIndex()
    {
        return this.rowNo - 1;
    }

    
    /**
     * 获取：数据总量的变量名称
     */
    public int getRowCount()
    {
        return rowCount;
    }

    
    /**
     * 设置：数据总量的变量名称
     * 
     * @param rowCount 
     */
    public void setRowCount(int rowCount)
    {
        this.rowCount = rowCount;
    }

    
    /**
     * 获取：数据小计总量的变量名称
     */
    public int getRowSubtotalCount()
    {
        return rowSubtotalCount;
    }

    
    /**
     * 设置：数据小计总量的变量名称
     * 
     * @param rowSubtotalCount 
     */
    public void setRowSubtotalCount(int rowSubtotalCount)
    {
        this.rowSubtotalCount = rowSubtotalCount;
    }

    
    /**
     * 获取：分页页号的变量名称。下标从1开始
     */
    public int getPageNo()
    {
        return pageNo;
    }
    
    
    /**
     * 设置：分页页号的变量名称。下标从1开始
     * 
     * @param pageNo 
     */
    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    
    /**
     * 获取：分页总页数的变量名称
     */
    public int getPageSize()
    {
        return pageSize;
    }
    
    
    /**
     * 设置：分页总页数的变量名称
     * 
     * @param pageSize 
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    
    /**
     * 获取：追加模式下的，记录追加前已有数据的最大行号。下标从0开始。默认为：0
     */
    public int getAppendStartRowIndex()
    {
        return appendStartRowIndex;
    }

    
    /**
     * 设置：追加模式下的，记录追加前已有数据的最大行号。下标从0开始。默认为：0
     * 
     * @param appendStartRowIndex 
     */
    public void setAppendStartRowIndex(int appendStartRowIndex)
    {
        this.appendStartRowIndex = appendStartRowIndex;
    }
    
}
