package org.hy.common.report.event;

import java.util.EventListener;

import org.apache.poi.ss.usermodel.Cell;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;





/**
 * 变量处理的监听事件接口 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 *              v2.0  2017-09-11  1.添加 接口新增 i_DataWorkbook 入参，方便动态设置样式、字体
 *                                      建议人：李浩
 */
public interface ValueListener extends EventListener
{
    
    /**
     * 变量名称
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @return
     */
    public String getValueName();
    
    
    
    /**
     * 对变量名称反射出来的值进行加工处理
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_RTemplate     模板
     * @param i_TemplateCell  模板单元格对象
     * @param i_DataCell      数据单元格对象
     * @param i_DataWorkbook  数据工作薄对象
     * @param i_RSystemValue  系统变量信息
     * @param i_Datas         本行对应的数据
     * @param i_Value         反射出来的变量名称对应的值
     * @return 
     */
    public String getValue(RTemplate i_RTemplate ,Cell i_TemplateCell ,Cell i_DataCell ,RWorkbook i_DataWorkbook ,RSystemValue i_RSystemValue ,Object i_Datas ,Object i_Value);
    
}
