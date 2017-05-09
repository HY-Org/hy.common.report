package org.hy.common.report.junit.versionCompare;

import org.hy.common.Date;
import org.hy.common.xml.SerializableDef;





/**
 * 测试用的样例类 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-17
 * @version     v1.0
 */
public class ExcelBean extends SerializableDef
{
    private static final long serialVersionUID = 2423903679071434270L;
    

    private String  name;
    
    private Date    date;
    
    private Integer age;
    
    private String  image;
    

    
    public String getName()
    {
        return name;
    }

    
    public void setName(String name)
    {
        this.name = name;
    }

    
    public Date getDate()
    {
        return date;
    }

    
    public void setDate(Date date)
    {
        this.date = date;
    }

    
    public Integer getAge()
    {
        return age;
    }

    
    public void setAge(Integer age)
    {
        this.age = age;
    }

    
    public String getImage()
    {
        return image;
    }

    
    public void setImage(String image)
    {
        this.image = image;
    }
    
}
