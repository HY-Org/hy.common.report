package org.hy.common.report.junit.readInteger;

import java.math.BigDecimal;
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
public class JU_ReadInteger
{
    private static boolean $isInit = false;
    
    
    
    public JU_ReadInteger() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
        
        String v = "1.1";
        BigDecimal b = new BigDecimal(v);
        System.out.print(b.intValue());
    }
    
    
    
    @Test
    public void test_ReadInteger()
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReadInteger");
        List<?>   v_Datas     = ReportHelp.toJava(v_RTemplate ,JU_ReadInteger.class.getResource("JU_ReadInteger_Data.xlsx").toString());
        
        Help.print(v_Datas);
    }
    
}
