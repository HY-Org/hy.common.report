package org.hy.common.report.junit;

import java.util.List;





/**
 * 测试单元的接口 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-28
 * @version     v1.0
 */
public interface JUBase<E>
{
    
    public List<E> getDatas(int i_DataSize);
    
}
