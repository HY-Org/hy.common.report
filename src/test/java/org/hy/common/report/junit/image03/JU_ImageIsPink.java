package org.hy.common.report.junit.image03;

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
 * 测试单元：图片背景变成粉红色的问题
 *
 * @author      ZhengWei(HY)
 * @createDate  2020-07-01
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_ImageIsPink
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_ImageIsPink() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @Test
    public void test_ImageIsPink() throws RTemplateException
    {
        RTemplate         v_RTemplate = (RTemplate)XJava.getObject("Report_ImageIsPink");
        List<Image03Data> v_Datas     = this.getDatas(4);
        
        RWorkbook v_RWorkbook = ReportHelp.toExcel(v_Datas ,v_RTemplate);
        
        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"C:\\Users\\hyzhe\\Desktop\\ImageIsPink_" + Date.getNowTime().getFull_ID() + ".xlsx");
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
    public List<Image03Data> getDatas(int i_Size)
    {
        List<String>      v_Images = new ArrayList<String>();
        List<Image03Data> v_Datas  = new ArrayList<Image03Data>();
        
        v_Images.add("D:\\WorkSpace\\hy.common.report\\src\\test\\java\\org\\hy\\common\\report\\junit\\image03\\1问题源.jpg");
        v_Images.add("D:\\WorkSpace\\hy.common.report\\src\\test\\java\\org\\hy\\common\\report\\junit\\image03\\2白色背景.png");
        v_Images.add("D:\\WorkSpace\\hy.common.report\\src\\test\\java\\org\\hy\\common\\report\\junit\\image03\\3透明图.png");
        v_Images.add("D:\\WorkSpace\\hy.common.report\\src\\test\\java\\org\\hy\\common\\report\\junit\\image04\\JU_ImageScale.png");
        
        for (int i=1; i<=i_Size; i++)
        {
            Image03Data v_Data = new Image03Data();
            
            v_Data.setImage(v_Images.get(i % v_Images.size()));
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
}
