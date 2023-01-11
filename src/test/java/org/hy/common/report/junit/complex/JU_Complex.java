package org.hy.common.report.junit.complex;

import java.util.List;

import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.junit.complex.config.InitConfig;
import org.hy.common.report.junit.complex.config.reports.工艺过程记录卡.bean.ComplexDataInfo;
import org.hy.common.xml.XJava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：复杂报表
 *
 * @author      ZhengWei(HY)
 * @createDate  2022-12-10
 * @version     v1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_Complex
{
    
    public JU_Complex()
    {
        new InitConfig();
    }
    
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void test_01() throws RTemplateException
    {
        RTemplate             v_Report      = (RTemplate)             XJava.getObject("Report_工艺过程记录卡");
        List<ComplexDataInfo> v_ReportDatas = (List<ComplexDataInfo>) XJava.getObject("ReportDatas_工艺过程记录卡");
        RWorkbook             v_RWorkbook   = null;
        
        // 最后一个参数为true时，即追加模式
        v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,"工艺过程记录卡" ,v_ReportDatas ,v_Report ,true);
        ExcelHelp.save(v_RWorkbook ,"C:\\Users\\hyzhe\\Desktop\\工艺过程记录卡.xlsx");
    }
    
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void test_02() throws RTemplateException
    {
        List<RTemplate>             v_Reports     = (List<RTemplate>)             XJava.getObject("ReportComplexs_工艺过程记录卡");
        List<List<ComplexDataInfo>> v_ReportDatas = (List<List<ComplexDataInfo>>) XJava.getObject("ReportDatas_工艺过程记录卡");
        RWorkbook                   v_RWorkbook   = null;
        
        for (int x=0; x<v_ReportDatas.size(); x++)
        {
            // 最后一个参数为true时，即追加模式
            v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,"工艺过程记录卡" ,v_ReportDatas.get(x)                        ,v_Reports.get(0) ,true);
            v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,"工艺过程记录卡" ,v_ReportDatas.get(x).get(0).getChildDatas() ,v_Reports.get(1) ,true);
            v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,"工艺过程记录卡" ,v_ReportDatas.get(x)                        ,v_Reports.get(2) ,true);
        }
        
        ExcelHelp.save(v_RWorkbook ,"C:\\Users\\hyzhe\\Desktop\\工艺过程记录卡.xlsx");
    }
    
}
