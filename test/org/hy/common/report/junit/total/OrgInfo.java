package org.hy.common.report.junit.total;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;





/**
 * 测试用的样例类：部门 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-21
 * @version     v1.0
 */
public class OrgInfo
{
    
    private String                 orgNo;
    
    private String                 orgName;
    
    private String                 reportTime;

    private List<StaffInfo>        staffs;
    
    private Set<StaffInfo>         staffSet;
    
    private Map<String ,StaffInfo> staffMap;
    
    
    
    public OrgInfo()
    {
        this.staffs   = new ArrayList<StaffInfo>();
        this.staffSet = new HashSet<StaffInfo>();
        this.staffMap = new HashMap<String ,StaffInfo>(); 
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

    
    public Set<StaffInfo> getStaffSet()
    {
        return staffSet;
    }

    
    public void setStaffSet(Set<StaffInfo> staffSet)
    {
        this.staffSet = staffSet;
    }

    
    public Map<String ,StaffInfo> getStaffMap()
    {
        return staffMap;
    }

    
    public void setStaffMap(Map<String ,StaffInfo> staffMap)
    {
        this.staffMap = staffMap;
    }


    public String getReportTime()
    {
        return reportTime;
    }

    
    public void setReportTime(String reportTime)
    {
        this.reportTime = reportTime;
    }

}
