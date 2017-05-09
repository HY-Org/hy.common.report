package org.hy.common.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.PartitionMap;
import org.hy.common.report.bean.RCell;
import org.hy.common.report.bean.RTemplate;





/**
 * Excel数据通过占位符的映射转为Java对象
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-05-08
 * @version     v1.0
 */
public class ExcelToJava
{
    
    /**
     * Excel数据通过占位符的映射转为Java对象
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-09
     * @version     v1.0
     *
     * @param i_RTemplate      模板对象
     * @param i_ExcelFileName  Excel文件的全路径
     * @return
     */
    public final static List<Object> read(RTemplate i_RTemplate ,String i_ExcelFileName)
    {
        return read(i_RTemplate ,i_ExcelFileName ,0);
    }
    
    
    
    /**
     * Excel数据通过占位符的映射转为Java对象
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-09
     * @version     v1.0
     *
     * @param i_RTemplate      模板对象
     * @param i_ExcelFileName  Excel文件的全路径
     * @param i_SheetNo        读取哪个工作表中的数据。下标从0开始。
     * @return
     */
    public final static List<Object> read(RTemplate i_RTemplate ,String i_ExcelFileName ,int i_SheetNo)
    {
        PartitionMap<String ,RCell> v_RCellDatas  = ExcelHelp.readDatas(i_RTemplate.getTemplateSheet());
        List<String>                v_DelKeys     = new ArrayList<String>();
        Map<String ,String>         v_RowColDatas = new HashMap<String ,String>();
        
        // 过滤出非占位符的普通信息，并删除。只保留占位符信息
        for (Map.Entry<String ,List<RCell>> v_Item : v_RCellDatas.entrySet())
        {
            if ( !v_Item.getKey().startsWith(i_RTemplate.getValueSign()) )
            {
                v_DelKeys.add(v_Item.getKey());
            }
        }
        if ( !Help.isNull(v_DelKeys) )
        {
            for (String v_Key : v_DelKeys)
            {
                v_RCellDatas.remove(v_Key);
            }
        }
        
        // 将报表模板中的占位符信息转为："单位格行号,单位格列号"为Map.key，Map.value为占位符的新的数据结构
        // 方便其后通过行号+列号定位到占位符
        for (Map.Entry<String, List<RCell>> v_Item : v_RCellDatas.entrySet())
        {
            if ( !Help.isNull(v_Item.getValue()) )
            {
                for (RCell v_RCell : v_Item.getValue())
                {
                    v_RowColDatas.put(v_RCell.getRowNo().intValue() + "," + v_RCell.getCellNo().intValue() ,v_Item.getKey());
                }
            }
        }
        
        List<Sheet> v_Sheets = ExcelHelp.read(i_ExcelFileName);
        if ( i_RTemplate.getDirection().intValue() == 1 )
        {
            return readHorizontal(i_RTemplate ,v_RowColDatas ,v_Sheets.get(i_SheetNo));
        }
        else
        {
            return readVertical(  i_RTemplate ,v_RowColDatas ,v_Sheets.get(i_SheetNo));
        }
    }
    
    
    
    /**
     * 按RTemplate.direction=0的方向垂直读取数据（一行或多行是一个Java对象）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-08
     * @version     v1.0
     *
     * @param i_RTemplate    模板对象
     * @param i_RowColDatas  模板中的占位符信息。Map.key为"单位格行号,单位格列号"，Map.value为占位符
     * @param i_Sheet        要读取的工作表
     * @return
     */
    private final static List<Object> readVertical(RTemplate i_RTemplate ,Map<String ,String> i_RowColDatas ,Sheet i_Sheet)
    {
        int          v_RowCount     = i_Sheet.getPhysicalNumberOfRows();
        int          v_RowCountData = i_RTemplate.getRowCountData();
        boolean      v_IsHaveData   = false;
        List<Object> v_Ret          = new ArrayList<Object>();   
        
        // 纵深扩展
        for (int v_RowNo=i_RTemplate.getDataBeginRow(); v_RowNo<v_RowCount; v_RowNo+=v_RowCountData)
        {
            Object v_RowObj = i_RTemplate.newObject();
            
            // 一行或多行表示一个对象数据
            for (int v_RowDataNo=0; v_RowDataNo<v_RowCountData; v_RowDataNo++)
            {
                Row v_Row = i_Sheet.getRow(v_RowNo + v_RowDataNo);
                if ( v_Row == null )
                {
                    continue;
                }
                
                for (int v_ColumnNo=0; v_ColumnNo<=v_Row.getPhysicalNumberOfCells(); v_ColumnNo++)
                {
                    Cell v_Cell = v_Row.getCell(v_ColumnNo);
                    if ( v_Cell == null )
                    {
                        continue;
                    }
                    
                    Object v_Value = null;
                    if ( v_Cell.getCellTypeEnum() == CellType.STRING )
                    {
                        v_Value = v_Cell.getStringCellValue();
                    }
                    else if ( v_Cell.getCellTypeEnum() == CellType.NUMERIC )
                    {
                        if ( HSSFDateUtil.isCellDateFormatted(v_Cell) ) 
                        {
                            if ( v_Cell.getDateCellValue() != null )
                            {
                                v_Value = new Date(v_Cell.getDateCellValue());
                            }
                        } 
                        else 
                        {
                            v_Value = String.valueOf(v_Cell.getNumericCellValue());
                        }
                    }
                    
                    if ( null != v_Value )
                    {
                        if ( i_RTemplate.setValue(i_RowColDatas.get((i_RTemplate.getDataBeginRow() + v_RowDataNo) + "," + v_ColumnNo) ,v_Value ,v_RowObj) )
                        {
                            v_IsHaveData = true;
                        }
                    }
                }
            }
            
            if ( v_IsHaveData ) { v_Ret.add(v_RowObj); }
            v_IsHaveData = false;
        }
        
        return v_Ret;
    }
    
    
    
    /**
     * 按RTemplate.direction=1的方向水平读取数据（一列或多列是一个Java对象）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-08
     * @version     v1.0
     *
     * @param i_RTemplate    模板对象
     * @param i_RowColDatas  模板中的占位符信息。Map.key为"单位格行号,单位格列号"，Map.value为占位符
     * @param i_Sheet        要读取的工作表
     * @return
     */
    private final static List<Object> readHorizontal(RTemplate i_RTemplate ,Map<String ,String> i_RowColDatas ,Sheet i_Sheet)
    {
        int          v_CellCount    = i_Sheet.getRow(i_RTemplate.getDataBeginRow()).getPhysicalNumberOfCells();
        int          v_ColCountData = i_RTemplate.getColCountData();
        boolean      v_IsHaveData   = false;
        List<Object> v_Ret          = new ArrayList<Object>();   
        
        // 横向扩展
        for (int v_ColumnNo=i_RTemplate.getDataBeginCol(); v_ColumnNo<v_CellCount; v_ColumnNo+=v_ColCountData)
        {
            Object v_RowObj = i_RTemplate.newObject();
            
            // 一列或多列表示一个对象数据
            for (int v_ColDataNo=0; v_ColDataNo<v_ColCountData; v_ColDataNo++)
            {
                for (int v_RowNo=i_RTemplate.getDataBeginRow(); v_RowNo<=i_RTemplate.getDataEndRow(); v_RowNo++)
                {
                    Row v_Row = i_Sheet.getRow(v_RowNo);
                    if ( v_Row == null )
                    {
                        continue;
                    }
                    
                    Cell v_Cell = v_Row.getCell(v_ColumnNo + v_ColDataNo);
                    if ( v_Cell == null )
                    {
                        continue;
                    }
                    
                    Object v_Value = null;
                    if ( v_Cell.getCellTypeEnum() == CellType.STRING )
                    {
                        v_Value = v_Cell.getStringCellValue();
                    }
                    else if ( v_Cell.getCellTypeEnum() == CellType.NUMERIC )
                    {
                        if ( HSSFDateUtil.isCellDateFormatted(v_Cell) ) 
                        {
                            if ( v_Cell.getDateCellValue() != null )
                            {
                                v_Value = new Date(v_Cell.getDateCellValue());
                            }
                        } 
                        else 
                        {
                            v_Value = String.valueOf(v_Cell.getNumericCellValue());
                        }
                    }
                    
                    if ( null != v_Value )
                    {
                        if ( i_RTemplate.setValue(i_RowColDatas.get(v_RowNo + "," + (i_RTemplate.getDataBeginCol() + v_ColDataNo)) ,v_Value ,v_RowObj) )
                        {
                            v_IsHaveData = true;
                        }
                    }
                }
            }
            
            if ( v_IsHaveData ) { v_Ret.add(v_RowObj); }
            v_IsHaveData = false;
        }
        
        return v_Ret;
    }
    
}
