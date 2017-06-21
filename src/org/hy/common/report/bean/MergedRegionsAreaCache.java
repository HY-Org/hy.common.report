package org.hy.common.report.bean;


import org.apache.poi.ss.usermodel.Sheet;





/**
 * 单元格合并的缓存信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-06-21
 * @version     v1.0
 */
public class MergedRegionsAreaCache implements Comparable<MergedRegionsAreaCache>
{
    /** 工作表 */
    private Sheet sheet;
    
    /** 定指区域内的开始行号。包含此行 */
    private int   areaBeginRow;
    
    /** 定指区域内的结束行号。包含此行 */
    private int   areaEndRow;
    
    
    
    public MergedRegionsAreaCache(Sheet i_Sheet ,int i_AreaBeginRow ,int i_AreaEndRow)
    {
        this.sheet        = i_Sheet;
        this.areaBeginRow = i_AreaBeginRow;
        this.areaEndRow   = i_AreaEndRow;
    }
    
    
    
    /**
     * 获取：工作表
     */
    public Sheet getSheet()
    {
        return sheet;
    }

    
    /**
     * 设置：工作表
     * 
     * @param sheet 
     */
    public void setSheet(Sheet sheet)
    {
        this.sheet = sheet;
    }

    
    /**
     * 获取：定指区域内的开始行号。包含此行
     */
    public int getAreaBeginRow()
    {
        return areaBeginRow;
    }

    
    /**
     * 设置：定指区域内的开始行号。包含此行
     * 
     * @param areaBeginRow 
     */
    public void setAreaBeginRow(int areaBeginRow)
    {
        this.areaBeginRow = areaBeginRow;
    }

    
    /**
     * 获取：定指区域内的结束行号。包含此行
     */
    public int getAreaEndRow()
    {
        return areaEndRow;
    }

    
    /**
     * 设置：定指区域内的结束行号。包含此行
     * 
     * @param areaEndRow 
     */
    public void setAreaEndRow(int areaEndRow)
    {
        this.areaEndRow = areaEndRow;
    }

    
    @Override
    public int compareTo(MergedRegionsAreaCache i_Other)
    {
        if ( this == i_Other )
        {
            return 0;
        }
        else if ( i_Other == null )
        {
            return -1;
        }
        else
        {
            if ( this.sheet        == i_Other.getSheet()
              && this.areaBeginRow == i_Other.getAreaBeginRow() 
              && this.areaEndRow   == i_Other.getAreaEndRow() )
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
    }


    @Override
    public int hashCode()
    {
        return this.sheet.hashCode() + this.areaBeginRow + this.areaEndRow;
    }


    @Override
    public boolean equals(Object i_Other)
    {
        if ( this == i_Other )
        {
            return true;
        }
        else if ( i_Other == null )
        {
            return false;
        }
        else if ( i_Other instanceof MergedRegionsAreaCache )
        {
            return this.compareTo((MergedRegionsAreaCache)i_Other) == 0;
        }
        else
        {
            return false;
        }
    }
    
}
