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
 *              v1.1              修复：在读取单位格时，支持对公式表达式的计算结果的读取
 *              v2.0  2017-06-26  添加：对与 readVertical(...) 方法，如果有标题信息的话，将保存在首个对象中
 *              v2.1  2018-05-30  修复：特殊情况：Excel的实际列数量不准确，为了预防此问题，列数量从标题行取。发现人：向以前
 *                                     当Excel多个连续列是空值时，Excel实际列数量将不准确。此可能是 POI API的Bug。
 */
public class ExcelToJava
{
    
    /**
     * Excel数据通过占位符的映射转为Java对象
     * 
     * 注意：默认从Excel文件的首个工作表读取数据。而不是从模板定义的工作表位置RTemplate.getSheetIndex()读取数据。
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
        return read(i_RTemplate ,i_ExcelFileName ,0 ,false);
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
        return read(i_RTemplate ,i_ExcelFileName ,i_SheetNo ,false);
    }
    
    
    
    /**
     * Excel数据通过占位符的映射转为Java对象
     * 
     * 注意：默认从Excel文件的首个工作表读取数据。而不是从模板定义的工作表位置RTemplate.getSheetIndex()读取数据。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-24
     * @version     v1.0
     *
     * @param i_RTemplate      模板对象
     * @param i_ExcelFileName  Excel文件的全路径
     * @param i_IsAddNull      当一行数据为空时，是否添加到返回集合中。
     * @return
     */
    public final static List<Object> read(RTemplate i_RTemplate ,String i_ExcelFileName ,boolean i_IsAddNull)
    {
        return read(i_RTemplate ,i_ExcelFileName ,0 ,i_IsAddNull);
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
     * @param i_IsAddNull      当一行数据为空时，是否添加到返回集合中。
     * @return
     */
    public final static List<Object> read(RTemplate i_RTemplate ,String i_ExcelFileName ,int i_SheetNo ,boolean i_IsAddNull)
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
            return readHorizontal(i_RTemplate ,v_RowColDatas ,v_Sheets.get(i_SheetNo) ,i_IsAddNull);
        }
        else
        {
            return readVertical(  i_RTemplate ,v_RowColDatas ,v_Sheets.get(i_SheetNo) ,i_IsAddNull);
        }
    }
    
    
    
    /**
     * 按RTemplate.direction=0的方向垂直读取数据（一行或多行是一个Java对象）
     * 
     * 注：如果有标题信息的话，将保存在首个对象中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-08
     * @version     v1.0
     *              v2.0  2018-05-30  修复： Excel的实际列数量不准确，为了预防此问题，列数量从标题行取
     *
     * @param i_RTemplate    模板对象
     * @param i_RowColDatas  模板中的占位符信息。Map.key为"单位格行号,单位格列号"，Map.value为占位符
     * @param i_Sheet        要读取的工作表
     * @param i_IsAddNull    当一行数据为空时，是否添加到返回集合中。
     * @return
     */
    private final static List<Object> readVertical(RTemplate i_RTemplate ,Map<String ,String> i_RowColDatas ,Sheet i_Sheet ,boolean i_IsAddNull)
    {
        int          v_RowCount      = i_Sheet.getLastRowNum() + 1;
        int          v_RowCountTitle = i_RTemplate.getRowCountTitle();
        int          v_RowCountData  = i_RTemplate.getRowCountData();
        boolean      v_IsHaveData    = false;
        Object       v_TitleObj      = null;
        List<Object> v_Ret           = new ArrayList<Object>();  
        int          v_MaxColSize    = 0;
        
        // 读取标题信息
        if ( i_RTemplate.getRowCountTitle() >= 1 )
        {
            v_TitleObj = i_RTemplate.newObject();
            
            for (int v_RowNo=i_RTemplate.getTitleBeginRow(); v_RowNo<v_RowCountTitle; v_RowNo++)
            {
                Row v_Row = i_Sheet.getRow(v_RowNo);
                
                // 特殊情况：Excel的实际列数量不准确，为了预防此问题，列数量从标题行取 ZhengWei(HY) Add 2018-05-30
                v_MaxColSize = Math.max(v_Row.getLastCellNum() ,v_MaxColSize);
                
                for (int v_ColumnNo=0; v_ColumnNo<=v_Row.getLastCellNum(); v_ColumnNo++)
                {
                    Cell v_Cell = v_Row.getCell(v_ColumnNo);
                    if ( v_Cell == null )
                    {
                        continue;
                    }
                    
                    Object v_Value = readCellValue(v_Cell);
                    if ( null != v_Value )
                    {
                        i_RTemplate.setValue(i_RowColDatas.get((i_RTemplate.getTitleBeginRow() + v_RowNo) + "," + v_ColumnNo) ,v_Value ,v_TitleObj);
                    }
                }
            }
        }
        
        // 纵深扩展
        for (int v_RowNo=i_RTemplate.getDataBeginRow(); v_RowNo<v_RowCount; v_RowNo+=v_RowCountData)
        {
            Object v_RowObj = null;
            if ( v_TitleObj == null )
            {
                v_RowObj = i_RTemplate.newObject();
            }
            else
            {
                // 将标题信息与第一行对象合并在一起
                v_RowObj     = v_TitleObj;
                v_TitleObj   = null;
                v_IsHaveData = true;
            }
            
            // 一行或多行表示一个对象数据
            for (int v_RowDataNo=0; v_RowDataNo<v_RowCountData; v_RowDataNo++)
            {
                Row v_Row = i_Sheet.getRow(v_RowNo + v_RowDataNo);
                if ( v_Row == null )
                {
                    continue;
                }
                
                v_MaxColSize = Math.max(v_Row.getLastCellNum() ,v_MaxColSize);
                
                for (int v_ColumnNo=0; v_ColumnNo<=v_MaxColSize; v_ColumnNo++)
                {
                    Cell v_Cell = v_Row.getCell(v_ColumnNo);
                    if ( v_Cell == null )
                    {
                        continue;
                    }
                    
                    Object v_Value = readCellValue(v_Cell);
                    if ( null != v_Value )
                    {
                        if ( i_RTemplate.setValue(i_RowColDatas.get((i_RTemplate.getDataBeginRow() + v_RowDataNo) + "," + v_ColumnNo) ,v_Value ,v_RowObj) )
                        {
                            v_IsHaveData = true;
                        }
                    }
                }
            }
            
            if ( v_IsHaveData || i_IsAddNull ) 
            { 
                v_Ret.add(v_RowObj); 
            }
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
     * @param i_IsAddNull    当一行数据为空时，是否添加到返回集合中。
     * @return
     */
    private final static List<Object> readHorizontal(RTemplate i_RTemplate ,Map<String ,String> i_RowColDatas ,Sheet i_Sheet ,boolean i_IsAddNull)
    {
        int          v_CellCount    = i_Sheet.getRow(i_RTemplate.getDataBeginRow()).getLastCellNum();
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
                    
                    Object v_Value = readCellValue(v_Cell);
                    if ( null != v_Value )
                    {
                        if ( i_RTemplate.setValue(i_RowColDatas.get(v_RowNo + "," + (i_RTemplate.getDataBeginCol() + v_ColDataNo)) ,v_Value ,v_RowObj) )
                        {
                            v_IsHaveData = true;
                        }
                    }
                }
            }
            
            if ( v_IsHaveData || i_IsAddNull ) 
            { 
                v_Ret.add(v_RowObj); 
            }
            v_IsHaveData = false;
        }
        
        return v_Ret;
    }
    
    
    
    /**
     * 读取单元格的数值
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-08
     * @version     v1.0
     *
     * @param i_Cell
     * @return
     */
    public final static Object readCellValue(Cell i_Cell)
    {
        Object v_Value = null;
        if ( i_Cell.getCellTypeEnum() == CellType.STRING )
        {
            v_Value = i_Cell.getStringCellValue();
        }
        else if ( i_Cell.getCellTypeEnum() == CellType.NUMERIC )
        {
            if ( HSSFDateUtil.isCellDateFormatted(i_Cell) ) 
            {
                if ( i_Cell.getDateCellValue() != null )
                {
                    v_Value = new Date(i_Cell.getDateCellValue());
                }
            } 
            else 
            {
                v_Value = String.valueOf(i_Cell.getNumericCellValue());
                
                // 2017-05-22 Add ZhengWei(HY)
                // Excel中单元格中的以文本保存数字时，防止产生与Excel单元格显示不一致的数据。
                // 如Excel中为100，如果用 v_Cell.getNumericCellValue() 读取会变成 "100.0"，
                // 即使设置了Excel单元格的格式为文本后，也是不行的。
                try
                {
                    if ( null != v_Value )
                    {
                        i_Cell.setCellType(CellType.STRING);
                        Object v_ValueTemp = i_Cell.getStringCellValue();
                        
                        // 有可能将0.0001识别为1.000E-3。为预防此情况，添加如下判定 2017-05-23 Add ZhengWei(HY)
                        if ( v_ValueTemp.toString().length() < v_Value.toString().length() )
                        {
                            v_Value = v_ValueTemp;
                        }
                        else
                        {
                            // 将 0.28999999999999992 转为 0.28 
                            v_ValueTemp = "" + Help.round(v_Value.toString() ,10);
                            
                            if ( v_ValueTemp.toString().length() < v_Value.toString().length() )
                            {
                                v_Value = v_ValueTemp;
                            }
                        }
                    }
                }
                catch (Exception exce)
                {
                    // Nothing.
                }
            }
        }
        else if ( i_Cell.getCellTypeEnum() == CellType.BOOLEAN )
        {
            v_Value = Boolean.valueOf(i_Cell.getBooleanCellValue());
        }
        else if ( i_Cell.getCellTypeEnum() == CellType.FORMULA )
        {
            try
            {
                v_Value = i_Cell.getStringCellValue();
            }
            catch (Exception exce)
            {
                try
                {
                    if ( HSSFDateUtil.isCellDateFormatted(i_Cell) ) 
                    {
                        if ( i_Cell.getDateCellValue() != null )
                        {
                            v_Value = new Date(i_Cell.getDateCellValue());
                        }
                    } 
                    else 
                    {
                        v_Value = String.valueOf(i_Cell.getNumericCellValue());
                        
                        // 2017-05-22 Add ZhengWei(HY)
                        // Excel中单元格中的以文本保存数字时，防止产生与Excel单元格显示不一致的数据。
                        // 如Excel中为100，如果用 v_Cell.getNumericCellValue() 读取会变成 "100.0"，
                        // 即使设置了Excel单元格的格式为文本后，也是不行的。
                        try
                        {
                            if ( null != v_Value )
                            {
                                i_Cell.setCellType(CellType.STRING);
                                Object v_ValueTemp = i_Cell.getStringCellValue();
                                
                                // 有可能将0.0001识别为1.000E-3。为预防此情况，添加如下判定 2017-05-23 Add ZhengWei(HY)
                                if ( v_ValueTemp.toString().length() < v_Value.toString().length() )
                                {
                                    v_Value = v_ValueTemp;
                                }
                            }
                        }
                        catch (Exception exce1)
                        {
                            // Nothing.
                        }
                    }
                }
                catch (Exception exce2)
                {
                    v_Value = Boolean.valueOf(i_Cell.getBooleanCellValue());
                }
            }
        }
        
        return v_Value;
    }
    
}
