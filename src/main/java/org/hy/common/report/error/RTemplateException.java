package org.hy.common.report.error;





/**
 * 报表模板的异常信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-06-25
 * @version     v1.0
 */
public class RTemplateException extends Exception
{

    private static final long serialVersionUID = -9092865996140470496L;
    
    

    public RTemplateException(String i_Message) 
    {
        super(i_Message);
    }



    public RTemplateException(String i_Message, Throwable i_Cause) 
    {
        super(i_Message ,i_Cause);
    }
    
}
