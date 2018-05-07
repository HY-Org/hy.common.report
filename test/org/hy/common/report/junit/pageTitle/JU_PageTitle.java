package org.hy.common.report.junit.pageTitle;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.StringHelp;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.junit.JUBase;
import org.hy.common.report.junit.total.OrgInfo;
import org.hy.common.report.junit.total.StaffInfo;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：分页标题的报表演示
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-06-25
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_PageTitle implements JUBase<OrgInfo>
{
    private static boolean $isInit = false;
    
    
    
    public JU_PageTitle() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    public List<OrgInfo> getDatas(int i_OrgSize)
    {
        List<OrgInfo> v_Orgs = new ArrayList<OrgInfo>();
        
        for (int i=1; i<=i_OrgSize; i++)
        {
            OrgInfo v_Org = new OrgInfo();
            
            v_Org.setOrgNo(StringHelp.lpad(i ,3 ,"0"));
            v_Org.setOrgName("部门名称 " + i);
            
            for (int x=1; x<=i*2; x++)
            {
                StaffInfo v_Staff = new StaffInfo();
                
                v_Staff.setStaffNo(v_Org.getOrgNo() + "-" + StringHelp.lpad(x ,3 ,"0"));
                v_Staff.setStaffName("员工名称 " + x);
                v_Staff.setTime(new Date());
                
                v_Org.getStaffs()  .add(v_Staff);
                v_Org.getStaffSet().add(v_Staff);
                v_Org.getStaffMap().put(v_Staff.getStaffNo() ,v_Staff);
            }
            
            v_Orgs.add(v_Org);
        }
        
        return v_Orgs;
    }
    
    
    
    @Test
    public void test_PageTitle() throws RTemplateException
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReportPageTitle");
        RTemplate v_Clone     = new RTemplate(v_RTemplate);
        
        // 分页页眉、分页页脚的演示
        ExcelHelp.save(ReportHelp.toExcel(getDatas(10) ,v_RTemplate).getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\ReportPageTitle.Page.xlsx");
        
        // 仅分页页眉的演示
        v_RTemplate.setTitlePageFooterBeginRow(null);
        ExcelHelp.save(ReportHelp.toExcel(getDatas(10) ,v_RTemplate).getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\ReportPageTitle.Header.xlsx");
        
        // 仅分页页脚的演示
        v_RTemplate.init(v_Clone);
        v_RTemplate.setTitlePageHeaderBeginRow(null);
        ExcelHelp.save(ReportHelp.toExcel(getDatas(10) ,v_RTemplate).getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\ReportPageTitle.Footer.xlsx");
        
        // 无分页的演示
        v_RTemplate.setTitlePageHeaderBeginRow(null);
        v_RTemplate.setTitlePageFooterBeginRow(null);
        ExcelHelp.save(ReportHelp.toExcel(getDatas(10) ,v_RTemplate).getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\ReportPageTitle.Normal.xlsx");
    }
    
}
