package org.hy.common.report.junit.writeHorizontal;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Help;





/**
 * 动态标题与横向扩展数据的一行信息的对象
 *
 * @author      ZhengWei(HY)
 * @createDate  2023-03-23
 * @version     v1.0
 */
public class WHObject
{
    /** 动态标题的数据集合 */
    private List<String> titleList;
    
    /** 动态横向数据的集合 */
    private List<Object> dataList;
    
    
    
    public WHObject()
    {
        this.titleList = new ArrayList<String>();
        this.dataList  = new ArrayList<Object>();
    }
    
    
    
    /**
     * 获取某一列的标题
     * 
     * @author      ZhengWei(HY)
     * @createDate  2023-03-23
     * @version     v1.0
     *
     * @param i_ColNo  Excel模板中的列号，下标从1开始
     * @return
     */
    public String getTitle(int i_ColNo)
    {
        int v_ColNo = i_ColNo - 1; // 因为第一列用于显示副标题了，所以要-1
                                   // 如果第一列就是数据，可以不用此步翻译功能
        
        if ( v_ColNo < 1 )
        {
            return "";
        }
        else if ( v_ColNo > this.titleList.size() )
        {
            return "";
        }
        else
        {
            return Help.NVL(this.titleList.get(v_ColNo - 1));
        }
    }
    
    
    
    /**
     * 获取某一列的数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2023-03-23
     * @version     v1.0
     *
     * @param i_ColIndex  Excel模板中的列索引号，下标从0开始
     * @return
     */
    public Object getData(int i_ColIndex)
    {
        int v_ColIndex = i_ColIndex - 1; // 因为第一列用于显示副标题了，所以要-1
                                         // 如果第一列就是数据，可以不用此步翻译功能
        
        if ( v_ColIndex < 0 )
        {
            return "";
        }
        else if ( v_ColIndex >= this.dataList.size() )
        {
            return "";
        }
        else
        {
            return Help.NVL(this.dataList.get(v_ColIndex));
        }
    }
    
    
    
    /**
     * 添加标题
     * 
     * @author      ZhengWei(HY)
     * @createDate  2023-03-23
     * @version     v1.0
     *
     * @param i_Title
     */
    public void addTitle(String i_Title)
    {
        this.titleList.add(i_Title);
    }
    
    
    
    /**
     * 添加列数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2023-03-23
     * @version     v1.0
     *
     * @param i_Data
     */
    public void addData(Object i_Data)
    {
        this.dataList.add(i_Data);
    }
    
}
