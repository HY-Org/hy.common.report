package org.hy.common.report.junit.versionCompare;

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
 * 测试单元：对比2003版本与2007版本生成的报表样式
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-19
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_Report01 implements JUBase<ExcelBean>
{
    private static boolean $isInit = false;
    
    
    
    public JU_Report01() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    public List<ExcelBean> getDatas(int i_DataSize)
    {
        List<ExcelBean> v_Ret = new ArrayList<ExcelBean>();
        
        for (int i=0; i<i_DataSize; i++)
        {
            ExcelBean v_Bean = new ExcelBean();
            
            v_Bean.setAge(i);
            v_Bean.setName("Name " + i);
            v_Bean.setDate(new Date());
            v_Bean.setImage("/Volumes/HY_HD_06/WSS/WorkSpace_SearchDesktop/hy.common.report/images/" + i + ".jpg");
            
            v_Ret.add(v_Bean);
        }
        
        return v_Ret;
    }
    
    
    
    @Test
    public void test_Report01() throws RTemplateException
    {
        RTemplate v_RTemplate01 = (RTemplate)XJava.getObject("Report01");
        RTemplate v_RTemplate02 = (RTemplate)XJava.getObject("Report02");
        
        ExcelHelp.save(ReportHelp.toExcel("XLSX格式" ,getDatas(11) ,v_RTemplate02).getWorkbook() ,"/Volumes/HY_HD_06/Downloads/Report01");
        // ExcelHelp.save(ReportHelp.toExcel("XLS格式"  ,getDatas(11) ,v_RTemplate01).getWorkbook() ,"/Volumes/HY_HD_06/Downloads/Report01");
    }
    
}
