package org.hy.common.report.junit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元： 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-17
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_ExcelHelp
{
    private static boolean $isInit = false;
    
    
    
    public JU_ExcelHelp() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(JU_ExcelHelp.class.getName());
        }
    }
    
    
    
    public List<ExcelBean> getDatas(int i_DataSize)
    {
        List<ExcelBean> v_Ret = new ArrayList<>();
        
        for (int i=0; i<i_DataSize; i++)
        {
            ExcelBean v_Bean = new ExcelBean();
            
            v_Bean.setAge(i);
            v_Bean.setName("Name " + i);
            v_Bean.setDate(new Date());
            v_Bean.setImage("/Users/hy/WSS/WorkSpace_SearchDesktop/hy.common.report/images/" + i + ".jpg");
            
            v_Ret.add(v_Bean);
        }
        
        return v_Ret;
    }
    
    
    
    @Test
    public void test_readDatas()
    {
        String v_ExcelFileName = "/Users/hy/WSS/WorkSpace_SearchDesktop/hy.common.report/test/org/hy/common/report/junit/JU_ExcelHelp.xls";
        Map<String ,Object> v_Datas = ExcelHelp.readDatas(v_ExcelFileName ,0);
        
        Help.print(v_Datas);
    }
    
    
    
    @Test
    public void test_RTemplate()
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReportTemplate");
        
        v_RTemplate.getTemplateSheet();
    }
    
    
    
    @Test
    public void test_write()
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReportTemplate");
        
        ExcelHelp.save(ReportHelp.write("测试" ,this.getDatas(10) ,v_RTemplate).getWorkbook() ,"/Users/hy/Downloads/测试.xls");
    }
    
}
