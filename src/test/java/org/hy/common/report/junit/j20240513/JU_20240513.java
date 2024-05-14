package org.hy.common.report.junit.j20240513;

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
 * 测试单元：小计、分组数据的报表演示
 *
 * @author      ZhengWei(HY)
 * @createDate  2024-05-13
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_20240513 implements JUBase<DataSheet>
{
    private static boolean $isInit = false;
    
    
    
    public JU_20240513() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @Override
    public List<DataSheet> getDatas(int i_OrgSize)
    {
        List<DataSheet> v_Datas = new ArrayList<DataSheet>();
        
        for (int i=1; i<=i_OrgSize; i++)
        {
            DataSheet v_Data = new DataSheet();
            
            v_Data.setTagNumber("TagNumber-" + i);
            v_Data.setImgPath("D:\\WorkSpace\\hy.common.report\\src\\test\\java\\org\\hy\\common\\report\\junit\\j20240513\\Image.png");
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
    
    
    @Test
    public void test_性能() throws RTemplateException
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("Report_20240513");
        
        List<DataSheet> v_Datas = getDatas(200);
        
        Date v_BeginTime = new Date();
        ExcelHelp.save(ReportHelp.toExcel("性能测试" ,v_Datas ,v_RTemplate).getWorkbook() ,"D:\\报表性能测试");
        Date v_EndTime  = new Date();
        
        System.out.println("导出数量：" + v_Datas.size());
        System.out.println("导出用时：" + Date.toTimeLen(v_EndTime.getTime() - v_BeginTime.getTime()));
    }
    
}
