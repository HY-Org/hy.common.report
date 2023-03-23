package org.hy.common.report.junit.writeHorizontal;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.StringHelp;
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
 * 测试单元：横向动态列的报表演示
 *
 * @author      ZhengWei(HY)
 * @createDate  2023-03-23
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_WriteHorizontal implements JUBase<WHObject>
{
    private static boolean $isInit = false;
    
    
    
    public JU_WriteHorizontal() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @Override
    public List<WHObject> getDatas(int i_RowSize)
    {
        List<WHObject> v_Datas = new ArrayList<WHObject>();
        
        for (int v_RowNo=1; v_RowNo<=i_RowSize; v_RowNo++)
        {
            WHObject v_RowData = new WHObject();
            
            // 仅首行动态生成所有标题
            if ( v_RowNo == 1 )
            {
                for (int x=1; x<=i_RowSize; x++)
                {
                    v_RowData.addTitle("数" + x);
                }
            }
            
            // 按数列递增生成每一行的动态数据
            for (int x=1; x<=v_RowNo; x++)
            {
                v_RowData.addData(x + " * " + v_RowNo + " = " + StringHelp.lpad(x * v_RowNo ,2 ," "));
            }
            
            v_Datas.add(v_RowData);
        }
        
        return v_Datas;
    }
    
    
    
    @Test
    public void test_WriteHorizontal() throws RTemplateException
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReportWriteHorizontal");
        
        ExcelHelp.save(ReportHelp.toExcel("横向动态列的报表演示" ,getDatas(9) ,v_RTemplate).getWorkbook() ,"C:\\Users\\hyzhe\\Desktop\\WriteHorizontal.xlsx");
    }
    
}
