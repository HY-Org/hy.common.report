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
    
    /** 反射单元格值的方法 */
    private MethodReflect valueMethod;
    
    /** 获取循环迭代器的方法 */
    private MethodReflect iteratorMethod;
    
    /** 获取循环迭代器的元素总个数 */
    private MethodReflect iteratorSizeMethod;
    
    
    
    public RCell()
    {
        this(null ,null ,null);
    }
    
    
    public RCell(MethodReflect i_ValueMethod)
    {
        this(i_ValueMethod ,null ,null);
    }
    
    
    public RCell(MethodReflect i_ValueMethod ,MethodReflect i_IteratorMethod ,MethodReflect i_IteratorSizeMethod)
    {
        this.valueMethod        = i_ValueMethod;
        this.iteratorMethod     = i_IteratorMethod;
        this.iteratorSizeMethod = i_IteratorSizeMethod;
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

}
