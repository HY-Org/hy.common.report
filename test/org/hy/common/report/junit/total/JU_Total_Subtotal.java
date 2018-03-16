package org.hy.common.report.junit.total;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.StringHelp;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.junit.JUBase;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：小计、分组数据的报表演示
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-21
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_Total_Subtotal implements JUBase<OrgInfo>
{
    private static boolean $isInit = false;
    
    
    
    public JU_Total_Subtotal() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(JU_Total_Subtotal.class.getName());
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
    public void test_Subtotal() throws RTemplateException
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReportTotalSubtotal");
        
        ExcelHelp.save(ReportHelp.toExcel("小计、分组数据的报表演示" ,getDatas(1) ,v_RTemplate).getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\TotalSubtotal.xlsx");
    }
    
}
