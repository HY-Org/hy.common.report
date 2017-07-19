package org.hy.common.report.bean;

import org.hy.common.MethodReflect;





/**
 * 报表单元格标记解释信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-23
 * @version     v1.0
 */
public class RCell
{
    
    /** 变量名称 */
    private String        valueName;
    
    /** 反射单元格值的方法 */
    private MethodReflect valueMethod;
    
    /** 获取循环迭代器的方法 */
    private MethodReflect iteratorMethod;
    
    /** 获取循环迭代器的元素总个数 */
    private MethodReflect iteratorSizeMethod;
    
    /** 行号。下标从0开始 */
    private Integer rowNo;
    
    /** 列号。下标从0开始 */
    private Integer cellNo;
    
    /** 小数位数 */
    private Integer decimal;
    
    /** 反射单元格值的Settter方法。用于Excel转为Java对象时 */
    private MethodReflect valueSetMethod;
    
    /** 下一级的标记的解释信息 */
    private RCell nextRCell;
    
    
    
    public RCell()
    {
        this(null ,null ,null);
    }
    
    
    public RCell(String i_ValueName)
    {
        this(null ,null ,null);
        
        this.valueName = i_ValueName;
    }
    
    
    public RCell(MethodReflect i_ValueMethod)
    {
        this(i_ValueMethod ,null ,null);
    }
    
    
    public RCell(Integer i_RowNo ,Integer i_CellNo)
    {
        this.valueMethod        = null;
        this.iteratorMethod     = null;
        this.iteratorSizeMethod = null;
        this.rowNo              = i_RowNo;
        this.cellNo             = i_CellNo;
        this.decimal            = null;
        this.valueSetMethod     = null;
        this.nextRCell          = null;
    }
    
    
    public RCell(MethodReflect i_ValueMethod ,MethodReflect i_IteratorMethod ,MethodReflect i_IteratorSizeMethod)
    {
        this.valueMethod        = i_ValueMethod;
        this.iteratorMethod     = i_IteratorMethod;
        this.iteratorSizeMethod = i_IteratorSizeMethod;
        this.rowNo              = null;
        this.cellNo             = null;
        this.decimal            = null;
        this.valueSetMethod     = null;
    }
    
    
    
    /**
     * 获取：变量名称
     */
    public String getValueName()
    {
        return valueName;
    }

    
    /**
     * 设置：变量名称
     * 
     * @param valueName 
     */
    public void setValueName(String valueName)
    {
        this.valueName = valueName;
    }


    /**
     * 是否为For循环
     */
    public boolean isFor()
    {
        return this.iteratorMethod != null;
    }
    
    
    /**
     * 获取：反射单元格值的方法
     */
    public MethodReflect getValueMethod()
    {
        return valueMethod;
    }

    
    /**
     * 设置：反射单元格值的方法
     * 
     * @param valueMethod 
     */
    public void setValueMethod(MethodReflect valueMethod)
    {
        this.valueMethod = valueMethod;
    }

    
    /**
     * 获取：获取循环迭代器的方法
     */
    public MethodReflect getIteratorMethod()
    {
        return iteratorMethod;
    }

    
    /**
     * 设置：获取循环迭代器的方法
     * 
     * @param iteratorMethod 
     */
    public void setIteratorMethod(MethodReflect iteratorMethod)
    {
        this.iteratorMethod = iteratorMethod;
    }


    /**
     * 获取：获取循环迭代器的元素总个数
     */
    public MethodReflect getIteratorSizeMethod()
    {
        return iteratorSizeMethod;
    }

    
    /**
     * 设置：获取循环迭代器的元素总个数
     * 
     * @param iteratorSizeMethod 
     */
    public void setIteratorSizeMethod(MethodReflect iteratorSizeMethod)
    {
        this.iteratorSizeMethod = iteratorSizeMethod;
    }

    
    /**
     * 获取：行号。下标从0开始
     */
    public Integer getRowNo()
    {
        return rowNo;
    }

    
    /**
     * 设置：行号。下标从0开始
     * 
     * @param rowNo 
     */
    public void setRowNo(Integer rowNo)
    {
        this.rowNo = rowNo;
    }


    
    /**
     * 获取：列号。下标从0开始
     */
    public Integer getCellNo()
    {
        return cellNo;
    }

    
    /**
     * 设置：列号。下标从0开始
     * 
     * @param cellNo 
     */
    public void setCellNo(Integer cellNo)
    {
        this.cellNo = cellNo;
    }
    
    
    /**
     * 获取：小数位数
     */
    public Integer getDecimal()
    {
        return decimal;
    }

    
    /**
     * 设置：小数位数
     * 
     * @param decimal 
     */
    public void setDecimal(Integer decimal)
    {
        this.decimal = decimal;
    }
    

    /**
     * 获取：反射单元格值的Settter方法。用于Excel转为Java对象时
     */
    public MethodReflect getValueSetMethod()
    {
        return valueSetMethod;
    }

    
    /**
     * 设置：反射单元格值的Settter方法。用于Excel转为Java对象时
     * 
     * @param valueSetMethod 
     */
    public void setValueSetMethod(MethodReflect valueSetMethod)
    {
        this.valueSetMethod = valueSetMethod;
    }

    
    /**
     * 获取：下一级的标记的解释信息
     */
    public RCell getNextRCell()
    {
        return nextRCell;
    }

    
    /**
     * 设置：下一级的标记的解释信息
     * 
     * @param nextRCell 
     */
    public void setNextRCell(RCell nextRCell)
    {
        this.nextRCell = nextRCell;
    }

}
