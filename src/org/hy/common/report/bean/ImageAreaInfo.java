package org.hy.common.report.bean;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.PictureData;





public class ImageAreaInfo
{
    
    private ClientAnchor anchor;
    
    private PictureData  pictureData;

    
    
    public ImageAreaInfo(ClientAnchor i_Anchor ,PictureData i_PictureData)
    {
        this.anchor      = i_Anchor;
        this.pictureData = i_PictureData;
    }
    
    
    public ClientAnchor getAnchor()
    {
        return anchor;
    }

    
    public void setAnchor(ClientAnchor anchor)
    {
        this.anchor = anchor;
    }

    
    public PictureData getPictureData()
    {
        return pictureData;
    }

    
    public void setPictureData(PictureData pictureData)
    {
        this.pictureData = pictureData;
    }
    
}
