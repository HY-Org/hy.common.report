package org.hy.common.report.junit.listmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.StringHelp;
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
 * 测试单元：List集合元素对象的属性是Map
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-12-02
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JU_ListMap
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_ListMap() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @Test
    public void test_ListMap() throws RTemplateException
    {
        RTemplate      v_RTemplate = (RTemplate)XJava.getObject("Report_ListMap");
        List<DataInfo> v_Datas     = this.getDatas(3);
        
        RWorkbook v_RWorkbook = ReportHelp.toExcel(v_Datas ,v_RTemplate);
        
        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"D:\\ListMap_" + Date.getNowTime().getFull_ID() + ".xlsx");
    }
    
    
    
    /**
     * 生成测试数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2021-12-02
     * @version     v1.0
     *
     * @param i_Size
     * @return
     */
    public List<DataInfo> getDatas(int i_Size)
    {
        List<DataInfo> v_Datas = new ArrayList<DataInfo>();
        
        for (int i=1; i<=i_Size; i++)
        {
            DataInfo v_Data = new DataInfo();
            
            v_Data.setName(Help.random(5 ,false));
            v_Data.setMap(new HashMap<String ,Object>());
            v_Data.getMap().put("key1" ,StringHelp.lpad(i ,3 ,"0"));
            v_Data.getMap().put("key2" ,Help.random(2));
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
}
