package org.hy.common.report.junit.complex.config.reports.工艺过程记录卡.datas;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.StringHelp;
import org.hy.common.report.junit.complex.config.reports.工艺过程记录卡.bean.ComplexDataInfo;
import org.hy.common.report.junit.complex.config.reports.工艺过程记录卡.bean.ComplexDataItem;





/**
 * 测试数据的生成器
 *
 * @author      ZhengWei(HY)
 * @createDate  2022-12-10
 * @version     v1.0
 */
public class DatasMaker
{
    
    public List<ComplexDataInfo> makeDatas()
    {
        List<ComplexDataInfo> v_Datas = new ArrayList<ComplexDataInfo>();
        
        for (int i=1; i<=2; i++)
        {
            String          v_No   = StringHelp.lpad(i ,3 ,"0");
            ComplexDataInfo v_Data = new ComplexDataInfo();
            
            v_Data.setProjectName("中国" + v_No);
            v_Data.setItemCode("零件编号" + v_No);
            
            for (int x=1; x<=2; x++)
            {
                ComplexDataItem v_DataItem = new ComplexDataItem();
                
                v_DataItem.setProcessNo(v_No + "_" + x);
                v_DataItem.setProcessName("工序名称" + v_No + "_" + x);
                v_DataItem.setDrawingsRequired("检查要求" + v_No + "_" + x);
                
                v_Data.getChildDatas().add(v_DataItem);
            }
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
    
    
    public List<List<ComplexDataInfo>> makeDatas_02()
    {
        List<List<ComplexDataInfo>> v_Datas = new ArrayList<List<ComplexDataInfo>>();
        
        for (int i=1; i<=2; i++)
        {
            List<ComplexDataInfo> v_DataList = new ArrayList<ComplexDataInfo>();
            String                v_No       = StringHelp.lpad(i ,3 ,"0");
            ComplexDataInfo       v_Data     = new ComplexDataInfo();
            
            v_Data.setProjectName("中国" + v_No);
            v_Data.setItemCode("零件编号" + v_No);
            v_Data.setOrganization("编制" + v_No);
            v_Data.setOrganizationTime(Date.getNowTime().getYMDHM());
            v_Data.setReviewer("审核" + v_No);
            v_Data.setReviewTime(Date.getNowTime().getYMDHM());
            v_Data.setApprover("批准" + v_No);
            v_Data.setApproveTime(Date.getNowTime().getYMDHM());
            
            int v_CountAll  = 15;
            int v_CountData = 2;
            for (int x=1; x<=v_CountData; x++)
            {
                ComplexDataItem v_DataItem = new ComplexDataItem();
                
                v_DataItem.setProcessNo(v_No + "_" + x);
                v_DataItem.setProcessName("工序名称" + v_No + "_" + x);
                v_DataItem.setDrawingsRequired("检查要求" + v_No + "_" + x);
                
                v_Data.getChildDatas().add(v_DataItem);
            }
            
            for (int x=1; x<=v_CountAll-v_CountData; x++)
            {
                ComplexDataItem v_DataItem = new ComplexDataItem();
                
                v_DataItem.setProcessNo(" ");
                
                v_Data.getChildDatas().add(v_DataItem);
            }
            
            v_DataList.add(v_Data);
            v_Datas.add(v_DataList);
        }
        
        return v_Datas;
    }
    
}
