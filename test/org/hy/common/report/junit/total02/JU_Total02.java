package org.hy.common.report.junit.total02;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
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
 * @createDate  2017-07-07
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_Total02 implements JUBase<ProductPressureTestStatisticsGroup>
{
    private static boolean $isInit = false;
    
    
    
    public JU_Total02() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(JU_Total02.class.getName());
        }
    }
    
    
    
    public List<ProductPressureTestStatisticsGroup> getDatas(int i_OrgSize)
    {
        List<ProductPressureTestStatisticsGroup> v_Orgs = new ArrayList<ProductPressureTestStatisticsGroup>();
        
        for (int i=1; i<=i_OrgSize; i++)
        {
            ProductPressureTestStatisticsGroup v_Org = new ProductPressureTestStatisticsGroup();
            
            v_Org.setStartDate( Date.getNowTime().getFull());
            v_Org.setEndDate(   Date.getNowTime().getFull());
            v_Org.setExportTime(Date.getNowTime().getFull());
            
            v_Org.setOperator("" + i);
            v_Org.setList(new ArrayList<ProductPressureTestStatistics>());
            v_Org.setFirstTestNum("" + i);
            v_Org.setProductCount(i);
            v_Org.setManHourTotalOfTheSecond("" + i);
            v_Org.setManHourOfTheSecond("" + i);
            v_Org.setTimesOfTheSecond("" + i);
            v_Org.setManHourTotalOfTheFirst("" + i);
            v_Org.setManHourOfTheFirst("" + i);
            v_Org.setTimesOfTheFirst("" + i);
            v_Org.setTestTimes("" + i);
            v_Org.setTheDirection("" + i);
            v_Org.setProductModel("" + i);
            v_Org.setDiameterNominal("" + i);
            v_Org.setProductNo("" + i);
            v_Org.setWorkhourTotal("" + i);
            v_Org.setOtherTestNum("" + i);
            v_Org.setOtherWorkhourTotal("" + i);
            
            for (int x=1; x<=i; x++)
            {
                ProductPressureTestStatistics v_Staff = new ProductPressureTestStatistics();
                
                v_Staff.setPressureLevel("" + x);
                v_Staff.setClampingType("" + x);
                v_Staff.setTestItem("" + x);
                v_Staff.setStandard("" + x);
                v_Staff.setTestObject("" + x);
                v_Staff.setTestConclusion("" + x);
                v_Staff.setTestMedium("" + x);
                v_Staff.setTestDate(new Date().getFullMilli_ID());
                
                v_Staff.setProPressTestList(new ArrayList<ProductPressureTestStatistics>());
                
                for (int y=1; y<=3; y++)
                {
                    v_Staff.getProPressTestList().add(v_Staff);
                }
                
                v_Org.getList().add(v_Staff);
            }
            
            v_Orgs.add(v_Org);
        }
        
        return v_Orgs;
    }
    
    
    
    @Test
    public void test_Subtotal() throws RTemplateException
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReportTotalSubtotal");
        
        
        List<ProductPressureTestStatisticsGroup> v_Datas = getDatas(100);
        
        Date v_BeginTime = new Date();
        ExcelHelp.save(ReportHelp.toExcel("小计、分组数据的报表演示" ,v_Datas ,v_RTemplate).getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\TotalSubtotal");
        Date v_EndTime  = new Date();
        
        System.out.println(v_EndTime.getTime() - v_BeginTime.getTime());
    }
    
}
