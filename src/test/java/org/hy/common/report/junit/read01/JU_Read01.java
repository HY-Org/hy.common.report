package org.hy.common.report.junit.read01;

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
 * 测试单元：Excel转为Java对象
 *
 * @author      ZhengWei(HY)
 * @createDate  2022-03-09
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_Read01
{
    private static boolean $isInit = false;
    
    
    
    public JU_Read01() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void test_Read01()
    {
        RTemplate      v_RTemplate = (RTemplate)XJava.getObject("Report_Read01");
        List<DataInfo> v_Datas     = (List<DataInfo>)ReportHelp.toJava(v_RTemplate ,JU_Read01.class.getResource("Datas_9J04-1.xls").toString());
        
        Help.print(v_Datas);
    }
    
}
