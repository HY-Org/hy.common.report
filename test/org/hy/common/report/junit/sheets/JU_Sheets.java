package org.hy.common.report.junit.sheets;

import java.util.List;

import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.junit.JUBase;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：多模板、多个工作表 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-28
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_Sheets
{
    private static boolean $isInit = false;
    
    
    
    public JU_Sheets() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            
            XJava.parserAnnotation(JU_Sheets.class.getName());
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void test_Sheets() throws Exception
    {
        List<JUBase<?>> v_Junits          = (List<JUBase<?>>)XJava.getObject("Junits");
        List<RTemplate> v_ReportTemplates = (List<RTemplate>)XJava.getObject("ReportTemplates");
        RWorkbook       v_RWorkbook       = null;
        RTemplate       v_RTemplate       = null;
        
        for (int i_Index=0; i_Index<v_ReportTemplates.size(); i_Index++)
        {
            v_RTemplate = v_ReportTemplates.get(i_Index);
            v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,v_Junits.get(i_Index).getDatas(10) ,v_RTemplate);
        }
        
        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"/Users/hy/Downloads/Sheets");
    }
    
}
