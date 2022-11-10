package org.hy.common.report.bean;

import java.util.ArrayList;





/**
 * 报表单元格标记解释信息的组。即单元格的内容支持：{:Key1}+{:Key2}+...+{Keyn}
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-07-10
 * @version     v1.0
 */
public class RCellGroup extends ArrayList<RCell>
{

    private static final long serialVersionUID = -566961776111828288L;
    
    
    /** 单元格原本的文本信息 */
    private String cellInfo;
    
    /** 是否替换模式。false表示为普通的填充模式 */
    private boolean replaceMode;
    
    
    
    public RCellGroup(String i_CellInfo ,boolean i_ReplaceMode)
    {
        super(i_ReplaceMode ? 10 : 1);
        
        this.cellInfo    = i_CellInfo;
        this.replaceMode = i_ReplaceMode;
    }
    
    
    
    /**
     * 只有一个变量信息的单元格解释信息的组
     *
     * @author      ZhengWei(HY)
     * @createDate  2017-07-10
     * @version     v1.0
     *
     * @param i_CellInfo
     * @param i_RCell
     */
    public RCellGroup(String i_CellInfo ,RCell i_RCell)
    {
        super(1);
        
        this.cellInfo    = i_CellInfo;
        this.replaceMode = false;
    }


    
    /**
     * 获取：单元格原本的文本信息
     */
    public String getCellInfo()
    {
        return cellInfo;
    }


    
    /**
     * 获取：是否替换模式。false表示为普通的填充模式
     */
    public boolean isReplaceMode()
    {
        return replaceMode;
    }
    
}
