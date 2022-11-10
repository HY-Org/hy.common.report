package org.hy.common.report.junit.append;

import java.util.List;

import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.junit.append.config.InitConfig;
import org.hy.common.xml.XJava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：追加模式的复合报表
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-10-26
 * @version     v1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_AppendComplex
{
    
    public JU_AppendComplex()
    {
        new InitConfig();
    }
    
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void test_01() throws RTemplateException
    {
        List<RTemplate>    v_Reports     = (List<RTemplate>)   XJava.getObject("ReportAppendComplexs");
        List<List<Object>> v_ReportDatas = (List<List<Object>>)XJava.getObject("ReportDatas");
        RWorkbook          v_RWorkbook   = null;
        
        for (int v_RIndex=0; v_RIndex<v_Reports.size(); v_RIndex++)
        {
            RTemplate    v_RTemplate = v_Reports    .get(v_RIndex);
            List<Object> v_RData     = v_ReportDatas.get(v_RIndex);
            
            // 最后一个参数为true时，即追加模式
            v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,"工作表的标题" ,v_RData ,v_RTemplate ,true);
        }
        
        ExcelHelp.save(v_RWorkbook ,"C:\\Users\\ZhengWei\\Desktop\\追加模式的复合报表.xlsx");
    }
    
}
