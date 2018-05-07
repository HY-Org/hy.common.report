package org.hy.common.report.junit.brace;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：大括号测试
 *
 * @author      ZhengWei(HY)
 * @createDate  2018-05-04
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_Brace
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_Brace() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    public List<Brace> getDatas(int i_Size)
    {
        List<Brace> v_Dates = new ArrayList<Brace>();
        
        for (int i=0; i<i_Size; i++)
        {
            Brace v_Data = new Brace();
            
            v_Data.setDepartName("部门名称" + i);
            
            v_Dates.add(v_Data);
        }
        
        
        v_Dates.get(0).setBeginDate("2018-01-01");
        v_Dates.get(0).setEndDate(  "2018-05-04");
        v_Dates.get(0).setExportTime(Date.getNowTime().getYMD());
        
        return v_Dates;
    }
    
    
    
    @Test
    public void test_Normal() throws RTemplateException
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("Report_Brace");
        
        ExcelHelp.save(ReportHelp.toExcel(getDatas(10) ,v_RTemplate).getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\大括号测试");
    }
    
}
