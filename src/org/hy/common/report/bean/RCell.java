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
    
    public final static String $Index = "index";
    
    
 
    /** 反射单元格值的方法 */
    private MethodReflect valueMethod;
    
    /** 获取循环次数的方法 */
    private MethodReflect forSizeMethod;
    
    
    
    public RCell()
    {
        this(null ,null);
    }
    
    
    public RCell(MethodReflect i_ValueMethod)
    {
        this(i_ValueMethod ,null);
    }
    
    
    public RCell(MethodReflect i_ValueMethod ,MethodReflect i_ForSizeMethod)
    {
        this.valueMethod   = i_ValueMethod;
        this.forSizeMethod = i_ForSizeMethod;
    }
    
    
    /**
     * 是否为For循环
     */
    public boolean isFor()
    {
        return this.forSizeMethod != null;
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
     * 获取：获取循环次数的方法
     */
    public MethodReflect getForSizeMethod()
    {
        return forSizeMethod;
    }

    
    /**
     * 设置：获取循环次数的方法
     * 
     * @param forSizeMethod 
     */
    public void setForSizeMethod(MethodReflect forSizeMethod)
    {
        this.forSizeMethod = forSizeMethod;
    }
    
}
