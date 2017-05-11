package org.hy.common.report.junit.readVerticalGrade;

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
 * 测试单元：Excel转为Java对象，并且Excel文件中的数据是纵深扩展的（高级版本的Excel文件）
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-05-09
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_ReadVerticalGrade
{
    private static boolean $isInit = false;
    
    
    public JU_ReadVerticalGrade() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(JU_ReadVerticalGrade.class.getName());
        }
    }
    
    
    
    @Test
    public void test_JU_ReadVerticalGrade()
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReadVerticalGrade");
        List<?>   v_Datas     = ReportHelp.toJava(v_RTemplate ,JU_ReadVerticalGrade.class.getResource("JU_ReadVerticalGrade_Datas.xlsx").toString());
        
        Help.print(v_Datas);
    }
    
}
