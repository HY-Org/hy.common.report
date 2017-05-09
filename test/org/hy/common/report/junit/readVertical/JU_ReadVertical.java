package org.hy.common.report.junit.readVertical;

import java.util.List;

import org.hy.common.Help;
import org.hy.common.report.ExcelToJava;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：Excel转为Java对象，并且Excel文件中的数据是纵深扩展的
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-05-08
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_ReadVertical
{
    private static boolean $isInit = false;
    
    
    
    public JU_ReadVertical() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(JU_ReadVertical.class.getName());
        }
    }
    
    
    
    @Test
    public void test_ReadHorizontal()
    {
        RTemplate    v_RTemplate = (RTemplate)XJava.getObject("ReadVertical");
        List<Object> v_Datas     = ReportHelp.toJava(v_RTemplate ,JU_ReadVertical.class.getResource("JU_ReadVertical_Datas.xlsx").toString());
        
        Help.print(v_Datas);
    }
    
}
