package org.hy.common.report.junit.total;

import org.hy.common.Date;





/**
 * 测试用的样例类：员工 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-21
 * @version     v1.0
 */
public class StaffInfo
{
    
    private String staffNo;
    
    private String staffName;
    
    private Date   time;

    
    
    public String getStaffNo()
    {
        return staffNo;
    }

    
    public void setStaffNo(String staffNo)
    {
        this.staffNo = staffNo;
    }

    
    public String getStaffName()
    {
        return staffName;
    }

    
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
    
    
    public Date getTime()
    {
        return time;
    }

    
    public void setTime(Date time)
    {
        this.time = time;
    }
    
}
