package org.hy.common.report.bean;





/**
 * 系统变量信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-28
 * @version     v1.0
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
    
}
