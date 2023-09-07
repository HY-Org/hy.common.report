package org.hy.common.report.junit.zxing;

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
 * 测试单元：二维码、条形码的报表演示
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-10-30
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_ZXing implements JUBase<ZXingData>
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_ZXing() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @Override
    public List<ZXingData> getDatas(int i_DataSize)
    {
        List<ZXingData> v_Ret = new ArrayList<ZXingData>();
        
        for (int i=0; i<i_DataSize; i++)
        {
            ZXingData v_Bean = new ZXingData();
            
            v_Bean.setZxing2D(StringHelp.getUUID());
            v_Bean.setZxing1D("" + (i * 314));
            
            v_Ret.add(v_Bean);
        }
        
        return v_Ret;
    }
    
    
    
    @Test
    public void test_ZXing() throws RTemplateException
    {
        RTemplate v_RTemplate = (RTemplate)XJava.getObject("ReportZXing");
        
        ExcelHelp.save(ReportHelp.toExcel(getDatas(10) ,v_RTemplate).getWorkbook() ,"C:\\Users\\hyzhe\\Desktop\\二维码、条形码的报表演示.xlsx");
    }
    
}
