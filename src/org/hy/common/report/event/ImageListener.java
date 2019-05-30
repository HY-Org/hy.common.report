package org.hy.common.report.event;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.hy.common.Help;
import org.hy.common.file.FileHelp;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;





/**
 * 图片处理的监听器 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 *              v2.0  2019-01-04  添加：图片缩放功能。支持最大宽度、最大高度等功能。
 *                                     建议人：王涵宇
 *              v3.0  2019-01-10  添加：图片缩放功能。支持最小宽度、最小高度等功能。
 *                                     当最大高宽与最小高宽同时限定时，以最大高宽为最终的限定。
 *                                     建议人：杨东
 *              v4.0  2019-05-30  添加：图片的横向、纵向缩放比例
 */
public class ImageListener implements ValueListener
{
    
    /** 监听器的变量名称 */
    protected String  valueName;
    
    /** 图片显示的位置信息：开始行号 */
    protected Integer beginRow;
    
    /** 图片显示的位置信息：结束行号 */
    protected Integer endRow;
    
    /** 图片显示的位置信息：开始行号 */
    protected Short   beginColumn;
    
    /** 图片显示的位置信息：结束行号 */
    protected Short   endColumn;
    
    /** 图片最大宽度。默认为0值，表示不限制 */
    protected int     maxWidth;
    
    /** 图片最大高度。默认为0值，表示不限制 */
    protected int     maxHeight;
    
    /** 图片最小宽度。默认为0值，表示不限制 */
    protected int     minWidth;
    
    /** 图片最小宽度。默认为0值，表示不限制 */
    protected int     minHeight;
    
    /** 当图片被缩小时，是否保持高宽等比缩放（当maxWidth 或 maxHeight大于0时有效）。默认为：真 */
    protected boolean isScale;
    
    /** 可在最大高宽、最小高宽的基础上（当然，也可独立使用，不基于最大高宽、最小高宽），横向缩放比例。不设置，不缩放 */
    protected Double  scaleX;
    
    /** 可在最大高宽、最小高宽的基础上（当然，也可独立使用，不基于最大高宽、最小高宽），纵向缩放比例。不设置，不缩放 */
    protected Double  scaleY;
    
    /** 与单元格顶部的边距。先将图片大小设置好后导出报表看看，再微调此值 */
    protected Integer marginTop;
    
    /** 与单元格左侧的边距。先将图片大小设置好后导出报表看看，再微调此值 */
    protected Integer marginLeft;
    
    
    
    public ImageListener()
    {
        this.maxWidth  = 0;
        this.maxHeight = 0;
        this.minWidth  = 0;
        this.minHeight = 0;
        this.isScale   = true;
    }
    
    
    
    /**
     * 变量名称
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @return
     */
    public String getValueName()
    {
        return this.valueName;
    }
    
    
    
    public void setValueName(String i_ValueName)
    {
        this.valueName = i_ValueName;
    }
    
    
    
    /**
     * 获取：图片显示的位置信息：开始行号
     */
    public Integer getBeginRow()
    {
        return beginRow;
    }


    
    /**
     * 设置：图片显示的位置信息：开始行号
     * 
     * @param beginRow 
     */
    public void setBeginRow(Integer beginRow)
    {
        this.beginRow = beginRow;
    }


    
    /**
     * 获取：图片显示的位置信息：结束行号
     */
    public Integer getEndRow()
    {
        return endRow;
    }


    
    /**
     * 设置：图片显示的位置信息：结束行号
     * 
     * @param endRow 
     */
    public void setEndRow(Integer endRow)
    {
        this.endRow = endRow;
    }


    
    /**
     * 获取：图片显示的位置信息：开始行号
     */
    public Short getBeginColumn()
    {
        return beginColumn;
    }


    
    /**
     * 设置：图片显示的位置信息：开始行号
     * 
     * @param beginColumn 
     */
    public void setBeginColumn(Short beginColumn)
    {
        this.beginColumn = beginColumn;
    }


    
    /**
     * 获取：图片显示的位置信息：结束行号
     */
    public Short getEndColumn()
    {
        return endColumn;
    }


    
    /**
     * 设置：图片显示的位置信息：结束行号
     * 
     * @param endColumn 
     */
    public void setEndColumn(Short endColumn)
    {
        this.endColumn = endColumn;
    }
    
    
    
    /**
     * 获取：与单元格顶部的边距。先将图片大小(width、height)设置好后导出报表看看，再微调此值
     */
    public Integer getMarginTop()
    {
        return marginTop;
    }
    

    
    /**
     * 设置：与单元格顶部的边距。先将图片大小(width、height)设置好后导出报表看看，再微调此值
     * 
     * @param marginTop 
     */
    public void setMarginTop(Integer marginTop)
    {
        this.marginTop = marginTop;
    }


    
    /**
     * 获取：与单元格左侧的边距。先将图片大小(width、height)设置好后导出报表看看，再微调此值
     */
    public Integer getMarginLeft()
    {
        return marginLeft;
    }


    
    /**
     * 设置：与单元格左侧的边距。先将图片大小(width、height)设置好后导出报表看看，再微调此值
     * 
     * @param marginLeft 
     */
    public void setMarginLeft(Integer marginLeft)
    {
        this.marginLeft = marginLeft;
    }
    
    
    
    /**
     * 获取：图片最大宽度。默认为0值，表示不限制
     */
    public int getMaxWidth()
    {
        return maxWidth;
    }
    

    
    /**
     * 获取：图片最大高度。默认为0值，表示不限制
     */
    public int getMaxHeight()
    {
        return maxHeight;
    }
    

    
    /**
     * 获取：图片最小宽度。默认为0值，表示不限制
     */
    public int getMinWidth()
    {
        return minWidth;
    }
    

    
    /**
     * 获取：图片最小宽度。默认为0值，表示不限制
     */
    public int getMinHeight()
    {
        return minHeight;
    }
    

    
    /**
     * 设置：图片最大宽度。默认为0值，表示不限制
     * 
     * @param maxWidth 
     */
    public void setMaxWidth(int maxWidth)
    {
        this.maxWidth = maxWidth;
    }
    

    
    /**
     * 设置：图片最大高度。默认为0值，表示不限制
     * 
     * @param maxHeight 
     */
    public void setMaxHeight(int maxHeight)
    {
        this.maxHeight = maxHeight;
    }


    
    /**
     * 设置：图片最小宽度。默认为0值，表示不限制
     * 
     * @param minWidth 
     */
    public void setMinWidth(int minWidth)
    {
        this.minWidth = minWidth;
    }


    
    /**
     * 设置：图片最小宽度。默认为0值，表示不限制
     * 
     * @param minHeight 
     */
    public void setMinHeight(int minHeight)
    {
        this.minHeight = minHeight;
    }

    
    
    /**
     * 获取：当图片被缩小时，是否保持高宽等比缩放（当maxWidth 或 maxHeight大于0时有效）。默认为：真
     */
    public boolean isScale()
    {
        return isScale;
    }


    
    /**
     * 设置：当图片被缩小时，是否保持高宽等比缩放（当maxWidth 或 maxHeight大于0时有效）。默认为：真
     * 
     * @param isScale 
     */
    public void setScale(boolean isScale)
    {
        this.isScale = isScale;
    }



    /**
     * 获取：可在最大高宽、最小高宽的基础上（当然，也可独立使用，不基于最大高宽、最小高宽），横向缩放比例。不设置，不缩放
     */
    public Double getScaleX()
    {
        return scaleX;
    }


    
    /**
     * 获取：可在最大高宽、最小高宽的基础上（当然，也可独立使用，不基于最大高宽、最小高宽），纵向缩放比例。不设置，不缩放
     */
    public Double getScaleY()
    {
        return scaleY;
    }


    
    /**
     * 设置：可在最大高宽、最小高宽的基础上（当然，也可独立使用，不基于最大高宽、最小高宽），横向缩放比例。不设置，不缩放
     * 
     * @param scaleX 
     */
    public void setScaleX(Double scaleX)
    {
        this.scaleX = scaleX;
    }


    
    /**
     * 设置：可在最大高宽、最小高宽的基础上（当然，也可独立使用，不基于最大高宽、最小高宽），纵向缩放比例。不设置，不缩放
     * 
     * @param scaleY 
     */
    public void setScaleY(Double scaleY)
    {
        this.scaleY = scaleY;
    }



    /**
     * 缩放图片。按预先设定的最大宽度和最大高度
     * 
     * @author      ZhengWei(HY)
     * @createDate  2019-01-04
     * @version     v1.0
     *
     * @param i_Image
     * @return
     */
    protected BufferedImage resizeImage(BufferedImage i_Image)
    {
        FileHelp      v_FileHelp = new FileHelp();
        BufferedImage v_Image    = v_FileHelp.resizeImageByMin(i_Image ,this.isScale ,this.minWidth ,this.minHeight);
        
        return v_FileHelp.resizeImageByMax(v_Image ,this.isScale ,this.maxWidth ,this.maxHeight);
    }
    
    
    
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
     * @param i_Value         反射出来的变量名称对应的值（图片文件的全路径）
     * @return 
     */
    public String getValue(RTemplate i_RTemplate ,Cell i_TemplateCell ,Cell i_DataCell ,RWorkbook i_DataWorkbook ,RSystemValue i_RSystemValue ,Object i_Datas ,Object i_Value)
    {
        if ( i_Value == null )
        {
            return "";
        }
        
        String v_ImageName = i_Value.toString().trim();
        String v_ImageType = v_ImageName.substring(v_ImageName.lastIndexOf(".") + 1).toLowerCase();
        if ( Help.isNull(v_ImageName) )
        {
            return "";
        }
        
        ByteArrayOutputStream v_ByteArrayOut = null;
        BufferedImage         v_BufferImage  = null;
        try
        {
            // 读取图片
            if ( v_ImageName.startsWith("file:") )
            {
                v_BufferImage = ImageIO.read(new URL(v_ImageName));
            }
            else
            {
                v_BufferImage = ImageIO.read(new File(v_ImageName));
            }
            
            // 缩放图片
            v_BufferImage = resizeImage(v_BufferImage);
            
            v_ByteArrayOut = new ByteArrayOutputStream();
            ImageIO.write(v_BufferImage ,v_ImageType ,v_ByteArrayOut);
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
            return "";
        }
        
        Drawing<?>   v_Drawing      = i_DataCell.getSheet().createDrawingPatriarch();
        ClientAnchor v_ClientAnchor = null;
        int          v_PictureType  = 0;
        int          v_OffsetRow    = i_RTemplate.getRowCountData() * (i_RSystemValue.getRowNo() - 1);
        
        if ( v_Drawing instanceof HSSFPatriarch )
        {
            v_ClientAnchor = new HSSFClientAnchor(0 ,0 ,0 ,0
                                                 ,this.beginColumn
                                                 ,this.beginRow + v_OffsetRow
                                                 ,this.endColumn
                                                 ,this.endRow   + v_OffsetRow);
        }
        else if ( v_Drawing instanceof XSSFDrawing )
        {
            v_ClientAnchor = new XSSFClientAnchor(0 ,0 ,0 ,0
                                                 ,this.beginColumn
                                                 ,this.beginRow + v_OffsetRow
                                                 ,this.endColumn
                                                 ,this.endRow   + v_OffsetRow);
        }
        else if ( v_Drawing instanceof SXSSFDrawing )
        {
            v_ClientAnchor = new XSSFClientAnchor(0 ,0 ,0 ,0
                                                 ,this.beginColumn
                                                 ,this.beginRow + v_OffsetRow
                                                 ,this.endColumn
                                                 ,this.endRow   + v_OffsetRow);
        }
        else
        {
            throw new java.lang.ClassCastException(v_Drawing.getClass().getName() + " is not find Type.");
        }
        
        v_ClientAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
        
        if ( "png".equals(v_ImageType) )
        {
            v_PictureType = Workbook.PICTURE_TYPE_PNG; 
        }
        else if ( "jpg".equals(v_ImageType) )
        {
            v_PictureType = Workbook.PICTURE_TYPE_JPEG;
        }
        else
        {
            v_PictureType = Workbook.PICTURE_TYPE_JPEG;
        }
        
        int v_PictureIndex = i_DataCell.getSheet().getWorkbook().addPicture(v_ByteArrayOut.toByteArray() ,v_PictureType);
        Picture v_Picture  = v_Drawing.createPicture(v_ClientAnchor ,v_PictureIndex);
        
        this.resizeMarginLeftTop(v_Picture ,v_BufferImage ,this.scaleX ,this.scaleY);
        
        return "";
    }
    
    
    
    /**
     * 自动缩放
     * 
     * @author      ZhengWei(HY)
     * @createDate  2019-05-30
     * @version     v1.0
     *
     * @param i_Picture
     * @param i_Image
     */
    protected void autoScale(Picture i_Picture ,BufferedImage i_Image)
    {
        double v_Scale = 1D - 0.00001D;
        
        if ( i_Image.getWidth() > i_Image.getHeight() )
        {
            // double v_ScaleY = Help.division(i_Image.getHeight() ,i_Image.getWidth());
            // i_Picture.resize(v_Scale ,v_ScaleY);
            i_Picture.resize(v_Scale ,v_Scale);
        }
        else if ( i_Image.getWidth() < i_Image.getHeight() )
        {
            // double v_ScaleX = Help.division(i_Image.getWidth() ,i_Image.getHeight());
            // i_Picture.resize(v_ScaleX ,v_Scale);
            i_Picture.resize(0.5D ,v_Scale);
        }
        else
        {
            i_Picture.resize(v_Scale ,v_Scale);
        }
    }
    
    
    
    /**
     * 重置图片大小，设置顶部、左侧边距
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-31
     * @version     v1.0
     *
     * @param i_Picture
     */
    protected void resizeMarginLeftTop(Picture i_Picture)
    {
        this.resizeMarginLeftTop(i_Picture ,null ,null ,null);
    }
    
    
    
    /**
     * 重置图片大小，设置顶部、左侧边距
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-31
     * @version     v1.0
     *
     * @param i_Picture
     */
    protected void resizeMarginLeftTop(Picture i_Picture ,BufferedImage i_Image ,Double i_ScaleX ,Double i_ScaleY)
    {
        if ( i_ScaleX != null && i_ScaleY != null )
        {
            i_Picture.resize(i_ScaleX ,i_ScaleY);
        }
        else if ( i_ScaleX != null )
        {
            i_Picture.resize(i_ScaleX ,1);
        }
        else if ( i_ScaleY != null )
        {
            i_Picture.resize(1 ,i_ScaleY);
        }
        else if ( i_Image != null )
        {
            autoScale(i_Picture ,i_Image);
        }
        
        i_Picture.getAnchor().setDx1(i_Picture.getAnchor().getDx1() + Help.NVL(this.marginLeft ,0));
        i_Picture.getAnchor().setDx2(i_Picture.getAnchor().getDx2() + Help.NVL(this.marginLeft ,0));
        i_Picture.getAnchor().setDy1(i_Picture.getAnchor().getDy1() + Help.NVL(this.marginTop  ,0));
        i_Picture.getAnchor().setDy2(i_Picture.getAnchor().getDy2() + Help.NVL(this.marginTop  ,0));
    }
    
}
