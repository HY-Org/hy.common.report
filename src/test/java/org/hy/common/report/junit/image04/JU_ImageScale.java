package org.hy.common.report.junit.image04;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：图片缩放
 *
 * @author      ZhengWei(HY)
 * @createDate  2023-09-06
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_ImageScale
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_ImageScale() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @Test
    public void test_ImageScale() throws RTemplateException
    {
        RTemplate         v_RTemplate = (RTemplate)XJava.getObject("Report_ImageScale");
        List<Image04Data> v_Datas     = this.getDatas(2);
        
        RWorkbook v_RWorkbook = ReportHelp.toExcel(v_Datas ,v_RTemplate);
        
        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"C:\\Users\\hyzhe\\Desktop\\JU_ImageScale_" + Date.getNowTime().getFull_ID() + ".xlsx");
    }
    
    
    
    /**
     * 生成测试数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-07-01
     * @version     v1.0
     *
     * @param i_Size
     * @return
     */
    public List<Image04Data> getDatas(int i_Size)
    {
        List<Image04Data> v_Datas = new ArrayList<Image04Data>();
        
        for (int i=1; i<=i_Size; i++)
        {
            Image04Data v_Data = new Image04Data();
            
            if ( i == 1 )
            {
                v_Data.setHeatTreatmentImage("D:\\WorkSpace\\hy.common.report\\images\\9.jpg");
            }
            else if ( i == 2 )
            {
                v_Data.setHeatTreatmentImage("D:\\WorkSpace\\hy.common.report\\src\\test\\java\\org\\hy\\common\\report\\junit\\image03\\1问题源.jpg");
            }
            else
            {
                v_Data.setHeatTreatmentImage("http://10.1.90.23/calc/windows/images/xsqlGroup.png");
            }
            
            v_Data.setImageUrl(v_Data.getHeatTreatmentImage());
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
}
