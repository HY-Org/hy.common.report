package org.hy.common.report.junit.font;

import org.hy.common.xml.SerializableDef;





/**
 * 测试字体 
 *
 * @author      ZhengWei(HY)
 * @createDate  2020-05-14
 * @version     v1.0
 */
public class FontTest extends SerializableDef
{
    
    private static final long serialVersionUID = 6695653941475081867L;

    private String fontInfo01;
    
    private String fontInfo02;
    
    private String fontInfo03;
    
    private String world;
    
    
    
    /**
     * 调用指定顺序上对应的Setter方法，设置属性值
     * 
     * @param i_PropertyIndex
     * @param i_Value
     */
    public void setPropertyValue(int i_PropertyIndex ,Object i_Value)
    {
        super.setPropertyValue(i_PropertyIndex ,i_Value ,this);
    }

    
    public String getFontInfo01()
    {
        return fontInfo01;
    }

    
    public String getFontInfo02()
    {
        return fontInfo02;
    }

    
    public String getFontInfo03()
    {
        return fontInfo03;
    }

    
    public void setFontInfo01(String fontInfo01)
    {
        this.fontInfo01 = fontInfo01;
    }

    
    public void setFontInfo02(String fontInfo02)
    {
        this.fontInfo02 = fontInfo02;
    }

    
    public void setFontInfo03(String fontInfo03)
    {
        this.fontInfo03 = fontInfo03;
    }

    
    public String getWorld()
    {
        return world;
    }

    
    public void setWorld(String world)
    {
        this.world = world;
    }
    
}
