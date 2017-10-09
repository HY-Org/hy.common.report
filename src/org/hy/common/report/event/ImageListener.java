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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.hy.common.Help;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;





/**
 * 图片处理的监听器 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 */
public class ImageListener implements ValueListener
{
    
    private String  valueName;
    
    /** 图片显示的位置信息：开始行号 */
    private Integer beginRow;
    
    /** 图片显示的位置信息：结束行号 */
    private Integer endRow;
    
    /** 图片显示的位置信息：开始行号 */
    private Short   beginColumn;
    
    /** 图片显示的位置信息：结束行号 */
    private Short   endColumn;
    
    
    
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
        try
        {
            // 读取图片
            BufferedImage v_BufferImage = null;
            if ( v_ImageName.startsWith("file:") )
            {
                v_BufferImage = ImageIO.read(new URL(v_ImageName));
            }
            else
            {
                v_BufferImage = ImageIO.read(new File(v_ImageName));
            }
            
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
            
//            ((XSSFClientAnchor)v_ClientAnchor).getFrom().setCol(this.beginColumn);
//            ((XSSFClientAnchor)v_ClientAnchor).getFrom().setRow(this.beginRow + v_OffsetRow);
//            ((XSSFClientAnchor)v_ClientAnchor).getTo()  .setCol(this.endColumn);
//            ((XSSFClientAnchor)v_ClientAnchor).getTo()  .setRow(this.endRow   + v_OffsetRow);
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
        v_Drawing.createPicture(v_ClientAnchor ,v_PictureIndex);
        
        return "";
    }
    
}
