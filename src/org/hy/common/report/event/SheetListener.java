package org.hy.common.report.event;

import java.util.EventListener;

import org.apache.poi.ss.usermodel.Sheet;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;





/**
 * 工作表的监听事件接口 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-06-29
 * @version     v1.0
 */
public interface SheetListener extends EventListener
{
    
    /**
     * 工作表数据写入完成后触发的动作
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-29
     * @version     v1.0
     *
     * @param i_DataSheet     数据的工作表
     * @param i_Datas         本行对应的数据
     * @param i_RTemplate     模板
     * @param i_TemplateCell  模板单元格对象
     * @param i_RSystemValue  系统变量信息
     * @return 
     */
    public void writeDatafinish(Sheet i_DataSheet ,Object i_Datas ,RTemplate i_RTemplate ,RSystemValue i_RSystemValue);
    
}
