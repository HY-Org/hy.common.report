package org.hy.common.report.junit.append;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Help;
import org.hy.common.StringHelp;
import org.hy.common.report.junit.append.bean.AppendComplex01;
import org.hy.common.report.junit.append.bean.AppendComplex02;
import org.hy.common.report.junit.append.bean.AppendComplex03;
import org.hy.common.xml.XJava;





/**
 * 测试数据的生成器
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-10-26
 * @version     v1.0
 */
public class DatasMaker
{
    
    public List<AppendComplex01> makeAppendComplex01()
    {
        List<AppendComplex01> v_Datas = new ArrayList<AppendComplex01>();
        
        for (int i=1; i<=1; i++)
        {
            String          v_No   = StringHelp.lpad(i ,3 ,"0");
            AppendComplex01 v_Data = new AppendComplex01();
            
            v_Data.setNumber(     "N" + v_No);
            v_Data.setType(       "T" + v_No);
            v_Data.setChineseName("C" + v_No);
            v_Data.setName(       "E" + v_No);
            v_Data.setSize(       i);
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
    
    
    public List<AppendComplex02> makeAppendComplex02()
    {
        List<AppendComplex02> v_Datas = new ArrayList<AppendComplex02>();
        
        for (int i=1; i<=3; i++)
        {
            String          v_No   = StringHelp.lpad(i ,3 ,"0");
            AppendComplex02 v_Data = new AppendComplex02();
            
            v_Data.setNumber(     "N" + v_No);
            v_Data.setEnglishName("E" + v_No);
            v_Data.setChineseName("C" + v_No);
            v_Data.setRetailNo(   "R" + v_No);
            v_Data.setImageNo(    "I" + v_No);
            v_Data.setSize(       i);
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
    
    
    public List<AppendComplex03> makeAppendComplex03()
    {
        List<AppendComplex03> v_Datas = new ArrayList<AppendComplex03>();
        
        for (int i=1; i<=3; i++)
        {
            String          v_No   = StringHelp.lpad(i ,3 ,"0");
            AppendComplex03 v_Data = new AppendComplex03();
            
            v_Data.setQty(     "Q" + v_No);
            v_Data.setTypeName("T" + v_No);
            v_Data.setRemark(  "R" + v_No);
            v_Data.setLength(  i);
            v_Data.setWidth(   Help.multiply(i ,100));
            v_Data.setHeight(  Help.multiply(i ,60));
            v_Data.setNet(     Help.multiply(i ,10));
            v_Data.setRt(      Help.multiply(i ,30));
            v_Data.setGross(   Help.multiply(i ,Math.PI));  // 在模板上可设置显示的小数位数
            v_Data.setQcode(XJava.getParam("QCodeImagePath").getValue() + i + ".png");
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
}
