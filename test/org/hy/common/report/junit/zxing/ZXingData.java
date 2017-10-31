package org.hy.common.report.junit.zxing;





/**
 * 测试用的样例类 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-10-30
 * @version     v1.0
 */
public class ZXingData
{
    
    /** 二维码文本 */
    private String zxing2D;
    
    /** 条形码文本 */
    private String zxing1D;

    
    
    /**
     * 获取：二维码文本
     */
    public String getZxing2D()
    {
        return zxing2D;
    }
    

    
    /**
     * 设置：二维码文本
     * 
     * @param zxing2d 
     */
    public void setZxing2D(String zxing2d)
    {
        zxing2D = zxing2d;
    }
    

    
    /**
     * 获取：条形码文本
     */
    public String getZxing1D()
    {
        return zxing1D;
    }
    

    
    /**
     * 设置：条形码文本
     * 
     * @param zxing1d 
     */
    public void setZxing1D(String zxing1d)
    {
        zxing1D = zxing1d;
    }
    
}
