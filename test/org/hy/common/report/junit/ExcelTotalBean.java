package org.hy.common.report.junit;

import java.util.List;





/**
 * 测试用的样例类。用于小计、合计的类型
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-21
 * @version     v1.0
 */
public class ExcelTotalBean
{
    
    private List<ExcelBean> datas;
    
    /** 小计信息 */
    private String subtotal;
    
    /** 合计信息 */
    private String total;

    
    
    public List<ExcelBean> getDatas()
    {
        return datas;
    }

    
    public void setDatas(List<ExcelBean> datas)
    {
        this.datas = datas;
    }

    
    /**
     * 获取：小计信息
     */
    public String getSubtotal()
    {
        return subtotal;
    }

    
    /**
     * 设置：小计信息
     * 
     * @param subtotal 
     */
    public void setSubtotal(String subtotal)
    {
        this.subtotal = subtotal;
    }

    
    /**
     * 获取：合计信息
     */
    public String getTotal()
    {
        return total;
    }

    
    /**
     * 设置：合计信息
     * 
     * @param total 
     */
    public void setTotal(String total)
    {
        this.total = total;
    }
    
}
