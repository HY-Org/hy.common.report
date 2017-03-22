package org.hy.common.report.junit.total;

import java.util.ArrayList;
import java.util.List;





/**
 * 测试用的样例类：部门 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-21
 * @version     v1.0
 */
public class OrgInfo
{
    
    private String           orgNo;
    
    private String           orgName;

    private List<StaffInfo>  staffs;
    
    
    
    public OrgInfo()
    {
        this.staffs = new ArrayList<StaffInfo>();
    }
    
    
    public String getOrgNo()
    {
        return orgNo;
    }

    
    public void setOrgNo(String orgNo)
    {
        this.orgNo = orgNo;
    }

    
    public String getOrgName()
    {
        return orgName;
    }

    
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    
    public List<StaffInfo> getStaffs()
    {
        return staffs;
    }

    
    public void setStaffs(List<StaffInfo> staffs)
    {
        this.staffs = staffs;
    }
    
}
