package org.hy.common.report.junit.image04;





/**
 * 报表的数据对象
 *
 * @author      ZhengWei(HY)
 * @createDate  2023-09-06
 * @version     v1.0
 */
public class Image04Data
{
    
    /** 图片路径 */
    private String heatTreatmentImage;
    
    /** 图片路径 */
    private String imageUrl;

    
    
    /**
     * 获取：图片路径
     */
    public String getHeatTreatmentImage()
    {
        return heatTreatmentImage;
    }

    
    /**
     * 设置：图片路径
     * 
     * @param i_HeatTreatmentImage 图片路径
     */
    public void setHeatTreatmentImage(String i_HeatTreatmentImage)
    {
        this.heatTreatmentImage = i_HeatTreatmentImage;
    }

    
    /**
     * 获取：图片路径
     */
    public String getImageUrl()
    {
        return imageUrl;
    }

    
    /**
     * 设置：图片路径
     * 
     * @param i_ImageUrl 图片路径
     */
    public void setImageUrl(String i_ImageUrl)
    {
        this.imageUrl = i_ImageUrl;
    }
    
}
