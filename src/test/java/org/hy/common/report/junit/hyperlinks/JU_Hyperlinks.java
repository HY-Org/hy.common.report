package org.hy.common.report.junit.hyperlinks;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.junit.image.ImageReportBean;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：Excel内链接
 *
 * @author      ZhengWei(HY)
 * @createDate  2022-01-18
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_Hyperlinks
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_Hyperlinks() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    public static void main(String [] i_Args) throws RTemplateException, Exception
    {
        new JU_Hyperlinks().test_Hyperlinks();
    }
    
    
    
    /**
     * 测试：带公式的报表生成
     * 
     * @author      ZhengWei(HY)
     * @createDate  2022-01-18
     * @version     v1.0
     *
     * @throws RTemplateException
     */
    @Test
    public void test_Hyperlinks() throws RTemplateException
    {
        RTemplate             v_RTemplate01 = (RTemplate)XJava.getObject("Report_Hyperlinks_01");
        RTemplate             v_RTemplate02 = (RTemplate)XJava.getObject("Report_Hyperlinks_02");
        List<ImageReportBean> v_Datas       = this.getDatas(3);
        
        RWorkbook v_RWorkbook = null;
        v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,v_Datas ,v_RTemplate01);
        v_RWorkbook = ReportHelp.toExcel(v_RWorkbook ,v_Datas ,v_RTemplate02);
        
        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"C:\\Users\\hyzhe\\Desktop\\Hyperlinks_" + Date.getNowTime().getFull_ID() + ".xlsx");
    }
    
    
    
    /**
     * 生成测试数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2022-01-18
     * @version     v1.0
     *
     * @param i_Size
     * @return
     */
    public List<ImageReportBean> getDatas(int i_Size)
    {
        List<ImageReportBean> v_Datas = new ArrayList<ImageReportBean>();
        
        for (int i=1; i<=i_Size; i++)
        {
            ImageReportBean v_Data   = new ImageReportBean();
            int             v_PSize  = v_Data.gatPropertySize();
            
            for (int v_PIndex=0; v_PIndex<v_PSize; v_PIndex++)
            {
                v_Data.setPropertyValue(v_PIndex ,v_PIndex + "");
            }
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
}