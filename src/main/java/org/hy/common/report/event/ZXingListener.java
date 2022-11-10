package org.hy.common.report.event;

import java.io.ByteArrayOutputStream;

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
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.zxing.ZXingHelp;

import com.google.zxing.BarcodeFormat;





/**
 * 二维码的监听器 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-10-30
 * @version     v1.0
 */
public class ZXingListener extends ImageListener implements ValueListener
{
    
    /** 二维码、条形码的类型 */
    private BarcodeFormat  barcodeFormat;
    
    /** 二维码的宽度 */
    private Integer        width;
    
    /** 二维码的高度 */
    private Integer        height;
    
    
    
    /**
     * 获取：二维码、条形码的类型
     */
    public BarcodeFormat getBarcodeFormat()
    {
        return barcodeFormat;
    }


    
    /**
     * 设置：二维码、条形码的类型
     * 
     * @param barcodeFormat 
     */
    public void setBarcodeFormat(BarcodeFormat barcodeFormat)
    {
        this.barcodeFormat = barcodeFormat;
    }


    
    /**
     * 获取：二维码的宽度
     */
    public Integer getWidth()
    {
        return width;
    }


    
    /**
     * 设置：二维码的宽度
     * 
     * @param width 
     */
    public void setWidth(Integer width)
    {
        this.width = width;
    }


    
    /**
     * 获取：二维码的高度
     */
    public Integer getHeight()
    {
        return height;
    }
    

    
    /**
     * 设置：二维码的高度
     * 
     * @param height 
     */
    public void setHeight(Integer height)
    {
        this.height = height;
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
     * @param i_Value         反射出来的变量名称对应的值（二维码原文）
     * @return 
     */
    public String getValue(RTemplate i_RTemplate ,Cell i_TemplateCell ,Cell i_DataCell ,RWorkbook i_DataWorkbook ,RSystemValue i_RSystemValue ,Object i_Datas ,Object i_Value)
    {
        if ( i_Value == null )
        {
            return "";
        }
        
        String v_ZXingValue = i_Value.toString().trim();
        if ( Help.isNull(v_ZXingValue) )
        {
            return "";
        }
        
        ByteArrayOutputStream v_ByteArrayOut = new ByteArrayOutputStream();
        try
        {
            ZXingHelp.encodeToStream(this.barcodeFormat  ,v_ByteArrayOut ,v_ZXingValue ,this.width ,this.height);
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
        
        v_PictureType = Workbook.PICTURE_TYPE_PNG;
        
        int v_PictureIndex = i_DataCell.getSheet().getWorkbook().addPicture(v_ByteArrayOut.toByteArray() ,v_PictureType);
        Picture v_Picture  = v_Drawing.createPicture(v_ClientAnchor ,v_PictureIndex);
        
        this.resizeMarginLeftTop(v_Picture ,null ,this.isScale ,Help.NVL(this.scaleX ,1D) ,Help.NVL(this.scaleY ,1D));
        
        return "";
    }
    
}
