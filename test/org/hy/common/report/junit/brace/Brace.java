package org.hy.common.report.junit.brace;

import org.hy.common.xml.SerializableDef;





/**
 * 大括号测试的值类
 *
 * @author      ZhengWei(HY)
 * @createDate  2018-05-04
 * @version     v1.0
 */
public class Brace extends SerializableDef
{
    
    private static final long serialVersionUID = 4763394254839919042L;

    private String departName;
    
    private String beginDate;
    
    private String endDate;
    
    private String exportTime;

    
    
    public String getDepartName()
    {
        return departName;
    }
    

    
    public String getBeginDate()
    {
        return beginDate;
    }
    

    
    public String getEndDate()
    {
        return endDate;
    }
    

    
    public String getExportTime()
    {
        return exportTime;
    }
    

    
    public void setDepartName(String departName)
    {
        this.departName = departName;
    }
    

    
    public void setBeginDate(String beginDate)
    {
        this.beginDate = beginDate;
    }
    

    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    

    
    public void setExportTime(String exportTime)
    {
        this.exportTime = exportTime;
    }
    
}
