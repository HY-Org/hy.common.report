package org.hy.common.report.junit.total03;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
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
 * @createDate  2017-07-07
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_Total03 implements JUBase<PartSprayRecordGroup>
{
    private static boolean $isInit = false;
    
    
    
    public JU_Total03() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    public List<PartSprayRecordGroup> getDatas(int i_OrgSize)
    {
        List<PartSprayRecordGroup> v_Orgs = new ArrayList<PartSprayRecordGroup>();
        
        for (int i=1; i<=i_OrgSize; i++)
        {
            PartSprayRecordGroup v_Org = new PartSprayRecordGroup();
            
            v_Org.setDepartName("" + i);
            v_Org.setBeginDate( Date.getNowTime().getFull());
            v_Org.setEndDate(   Date.getNowTime().getFull());
            v_Org.setExportTime(Date.getNowTime().getFull());
            
            v_Org.setOperator("" + i);
            v_Org.setList(new ArrayList<PartSprayRecord>());
            
            for (int x=1; x<=i; x++)
            {
                PartSprayRecord v_Staff = new PartSprayRecord();
                
                v_Staff.setBatchNo("" + x);
                v_Staff.setItemCode("" + x);
                v_Staff.setPartName("" + x);
                v_Staff.setMaterial("" + x);
                v_Staff.setProcessClassify("" + x);
                v_Staff.setBs((double)x);
                
                
                v_Org.getList().add(v_Staff);
            }
            
            v_Orgs.add(v_Org);
        }
        
        return v_Orgs;
    }
    
    
    
    @Test
    public void test_Subtotal() throws RTemplateException
    {
        RTemplate v_RTemplate01 = (RTemplate)XJava.getObject("PartSprayRecordStatistics");
        RTemplate v_RTemplate02 = (RTemplate)XJava.getObject("PartSprayRecordStatistics_t");
        
        
        List<PartSprayRecordGroup> v_Datas = getDatas(10);
        
        Date v_BeginTime = new Date();
        RWorkbook v_RWorkbook = null;
        
        v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,v_Datas ,v_RTemplate01);
        v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,v_Datas ,v_RTemplate02);
        
        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\PartSprayRecordStatistics");
        Date v_EndTime  = new Date();
        
        System.out.println(v_EndTime.getTime() - v_BeginTime.getTime());
    }
    
}
