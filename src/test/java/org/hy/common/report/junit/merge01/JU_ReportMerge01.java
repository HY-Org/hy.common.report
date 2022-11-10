package org.hy.common.report.junit.merge01;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.StringHelp;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.junit.JUBase;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：常规列表加合计的报表演示
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-06-27
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_ReportMerge01 implements JUBase<Data>
{
    private static boolean $isInit = false;
    
    
    
    public JU_ReportMerge01() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    public List<Data> getDatas(int i_OrgSize)
    {
        List<Data> v_Datas = new ArrayList<Data>();
        
        for (int i=1; i<=i_OrgSize; i++)
        {
            Data v_Data = new Data();
            
            v_Data.setModelID(StringHelp.getUUID());
            v_Data.setSelfParts(new ArrayList<DataPart>());
            v_Data.setOutParts( new ArrayList<DataPart>());
            
            for (int x=1; x<=i*3; x++)
            {
                DataPart v_DataPart = new DataPart();
                
                v_DataPart.setLb("R1-" + "C" + x);
                v_DataPart.setSl(200 + x);
                v_DataPart.setHj(300 + x);
                
                v_Data.getSelfParts().add(v_DataPart);
            }
            
            for (int x=1; x<=i*3; x++)
            {
                DataPart v_DataPart = new DataPart();
                
                v_DataPart.setLb("R1-" + "C" + x);
                v_DataPart.setXh("R2-" + "C" + x);
                v_DataPart.setSl(300 + x);
                v_DataPart.setHj(400 + x);
                
                v_Data.getOutParts().add(v_DataPart);
            }
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
    
    
    @Test
    public void test_ReportMerge01() throws RTemplateException
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReportMerge01");
        
        RWorkbook v_RWorkbook = ReportHelp.toExcel("合并的报表演示" ,getDatas(1) ,v_RTemplate);

        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\ReportMerge.xlsx");
    }
    
}
