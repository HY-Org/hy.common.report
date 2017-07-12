package org.hy.common.report.bean;

import java.util.Iterator;
import java.util.List;





/**
 * 通过单位格上的表达式反射出来的值信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-27
 * @version     v1.0
 */
public class RValue
{
    
    /** 反射出来的值 */
    private Object        value;
    
    /** 小计循环的迭代器对象 */
    private Iterator<?>   iterator;
    
    /** 小计循环的迭代器的元素总个数 */
    private int           iteratorSize;
    
    /** 小计循环的迭代器的次数。下标从 0 开始 */
    private int           iteratorIndex;
    
    /** 下一级的通过单位格上的表达式反射出来的值信息  */
    private RValue        nextRValue;
    
    /** 值的组信息。与RCellGroup配合使用 */
    private List<RValue>  valueGroup;
    
    
    
    public RValue()
    {
        this(null ,null);
    }
    
    
    public RValue(Object i_Value)
    {
        this(i_Value ,null);
    }
    
    
    public RValue(Object i_Value ,Iterator<?> i_Iterator)
    {
        this.value         = i_Value;
        this.iterator      = i_Iterator;
        this.iteratorSize  = 0;
        this.iteratorIndex = -1;
        this.nextRValue    = null;
        this.valueGroup    = null;
    }

    
    /**
     * 获取：反射出来的值
     */
    public Object getValue()
    {
        return value;
    }

    
    /**
     * 设置：反射出来的值
     * 
     * @param value 
     */
    public void setValue(Object value)
    {
        this.value = value;
    }

    
    /**
     * 获取：小计循环的迭代器对象
     */
    public Iterator<?> getIterator()
    {
        return iterator;
    }

    
    /**
     * 设置：小计循环的迭代器对象
     * 
     * @param iterator 
     */
    public void setIterator(Iterator<?> iterator)
    {
        this.iterator = iterator;
    }
    
    
    /**
     * 获取：小计循环的迭代器的元素总个数
     */
    public int getIteratorSize()
    {
        return iteratorSize;
    }

    
    /**
     * 设置：小计循环的迭代器的元素总个数
     * 
     * @param iteratorSize 
     */
    public void setIteratorSize(int iteratorSize)
    {
        this.iteratorSize = iteratorSize;
    }


    /**
     * 获取：小计循环的迭代器的次数。下标从 0 开始
     */
    public int getIteratorIndex()
    {
        return iteratorIndex;
    }

    
    /**
     * 设置：小计循环的迭代器的次数。下标从 0 开始
     * 
     * @param iteratorIndex 
     */
    public void setIteratorIndex(int iteratorIndex)
    {
        this.iteratorIndex = iteratorIndex;
    }
    
    
    /**
     * 获取：下一级的通过单位格上的表达式反射出来的值信息
     */
    public RValue getNextRValue()
    {
        return nextRValue;
    }

    
    /**
     * 设置：下一级的通过单位格上的表达式反射出来的值信息
     * 
     * @param nextRValue 
     */
    public void setNextRValue(RValue nextRValue)
    {
        this.nextRValue = nextRValue;
    }


    /**
     * 获取：值的组信息。与RCellGroup配合使用
     */
    public List<RValue> getValueGroup()
    {
        return valueGroup;
    }
    
    
    /**
     * 设置：值的组信息。与RCellGroup配合使用
     * 
     * @param valueGroup 
     */
    public void setValueGroup(List<RValue> valueGroup)
    {
        this.valueGroup = valueGroup;
    }
    
}
