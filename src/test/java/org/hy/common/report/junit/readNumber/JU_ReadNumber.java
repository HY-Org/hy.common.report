package org.hy.common.report.junit.readNumber;

import java.util.List;

import org.hy.common.Help;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：Excel转为Java对象，对整数的处理
 *
 * @author      ZhengWei(HY)
 * @createDate  2024-05-09
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_ReadNumber
{
    private static boolean $isInit = false;
    
    
    
    public JU_ReadNumber() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @Test
    public void test_ReadInteger()
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReadNumber");
        List<?>   v_Datas     = ReportHelp.toJava(v_RTemplate ,JU_ReadNumber.class.getResource("JU_ReadNumber_Data.xlsx").toString());
        
        Help.print(v_Datas);
    }
    
}
