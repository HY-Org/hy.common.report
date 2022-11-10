package org.hy.common.report.junit.appendPage;

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
 * 测试单元：多模板追加及打印分页
 *
 * @author      ZhengWei(HY)
 * @createDate  2020-05-12
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_AppendPage
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_AppendPage() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    /**
     * 生成测试数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2019-05-30
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
                v_Data.setPropertyValue(v_PIndex ,i + "-" + v_PIndex);
            }
            
            v_Data.setImgPath("D:\\WorkSpace_SearchDesktop\\hy.common.report\\test\\org\\hy\\common\\report\\junit\\image02\\1.png");
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
    
    
    @Test
    public void test_AppendPage() throws RTemplateException
    {
        RTemplate             v_RTemplate01 = (RTemplate)XJava.getObject("Report_AppendPage_1_xlsx");
        RTemplate             v_RTemplate02 = (RTemplate)XJava.getObject("Report_AppendPage_2_xlsx");
        List<ImageReportBean> v_Datas       = this.getDatas(2);
        
        RWorkbook v_RWorkbook = ReportHelp.toExcel(v_Datas ,v_RTemplate01); // 按第一个模板生成数据
        
        ReportHelp.toExcel(v_RWorkbook ,v_Datas ,v_RTemplate02 ,true);      // 追加第二个模板的数据
        
        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\AppendPage_" + Date.getNowTime().getFull_ID() + ".xlsx");
    }
    
}
