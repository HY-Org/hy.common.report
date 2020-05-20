package org.hy.common.report;

import java.util.List;

import org.hy.common.Help;
import org.hy.common.StringHelp;
import org.hy.common.report.bean.RCell;





/**
 * Excel公式相关的共用类
 *
 * @author      ZhengWei(HY)
 * @createDate  2020-05-20
 * @version     v1.0
 */
public class ExcelFormula
{
    
    public static final String [] $A_TO_Z = {"A" ,"B" ,"C" ,"D" ,"E" ,"F" ,"G"
                                            ,"H" ,"I" ,"J" ,"K" ,"L" ,"M" ,"N"
                                            ,"O" ,"P" ,"Q" ,"R" ,"S" ,"T"
                                            ,"U" ,"V" ,"W" ,"X" ,"Y" ,"Z"};
    
    /** 列举Excel公式中的关键字 */
    public static final String [] $Formulas = {"=" ,"+" ,"-" ,"*" ,"/"
                                              ,"(" ,")"
                                              ,":"};
    
    /** 解释Excel公式时，用的分隔符 */
    private static final String   $ParserFormulaSplit = "@@@";
    
    
    
    /**
     * 解释公式，将公式中的单元格ID独立提取出来。
     * 
     * 1. 会去除重复的单元格ID
     * 2. 会去除绝对行、绝对列定位的单元格ID
     * 3. 保留只绝对行 或 只绝对列定位的单格ID
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-20
     * @version     v1.0
     *
     * @param i_Formula
     * @return
     */
    public static String [] parserFormula(String i_Formula)
    {
        if ( Help.isNull(i_Formula) )
        {
            return new String[0];
        }
        
        String v_CellIDs = "";
        v_CellIDs = StringHelp.trim(i_Formula.trim().toUpperCase());
        v_CellIDs = StringHelp.replaceAll(v_CellIDs ,$Formulas ,new String[] {$ParserFormulaSplit});
        v_CellIDs = StringHelp.trimToDistinct(v_CellIDs ,$ParserFormulaSplit);
        
        String [] v_CellIDArr = v_CellIDs.split($ParserFormulaSplit);
        if ( Help.isNull(v_CellIDArr) )
        {
            return new String[0];
        }
        
        List<String> v_CellIDList = Help.toDistinct(v_CellIDArr);
        for (int i=v_CellIDList.size()-1; i>=0; i--)
        {
            if ( Help.isNull(v_CellIDList.get(i)) )
            {
                // 去除空
                v_CellIDList.remove(i);
            }
            else if ( StringHelp.getCount(v_CellIDList.get(i) ,"\\$") >= 2 )
            {
                // 去除绝对行、绝对列定位的单元格ID
                v_CellIDList.remove(i);
            }
        }
        
        if ( Help.isNull(v_CellIDList) )
        {
            return new String[0];
        }
        return v_CellIDList.toArray(new String[] {});
    }
    
    
    
    /**
     * 计算单元格在偏移量（偏移多少行、偏移多少列）后新的单元格ID
     * 
     * 如，A1偏移1行为：A2
     * 如，A1偏移1列为：B1
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-20
     * @version     v1.0
     *
     * @param i_CellExcelID  单元格的ID。为Excel与人交互的ID，如，A1表示第1列的第1行坐标位置上单元格。
     * @param i_OffsetRow    偏移多少行。零值，表示不偏移；负值向i_CellExcelID上方偏移；正值向i_CellExcelID下方偏移。
     * @param i_OffsetCol    偏移多少列。零值，表示不偏移；负值向i_CellExcelID左方偏移；正值向i_CellExcelID右方偏移。
     * @return
     */
    public static String calcCellOffset(String i_CellExcelID ,int i_OffsetRow ,int i_OffsetCol)
    {
        if ( Help.isNull(i_CellExcelID) )
        {
            return null;
        }
        if ( StringHelp.getCount(i_CellExcelID ,"\\$") >= 2 )
        {
            // 绝对行、绝对列是不能偏移的。
            return i_CellExcelID;
        }
        
        RCell v_RCell = cellIDtoJava(i_CellExcelID);
        
        if ( !v_RCell.isFixedRow() )
        {
            v_RCell.setRowNo(Help.max(v_RCell.getRowNo() + i_OffsetRow ,0));
        }
        
        if ( !v_RCell.isFixedCol() )
        {
            v_RCell.setColNo(Help.max(v_RCell.getColNo() + i_OffsetCol ,0));
        }
        
        return cellIDtoExcel(v_RCell);
    }
    
    
    
    /**
     * 将Excel单元格ID，转换为Java语言标识的行号、列号。
     * 
     * 如，将A1翻译为，第1列第1行，实际返回 0,0  ，下标均从0开始
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-19
     * @version     v1.0
     *
     * @param i_CellExcelID  Excel单元格ID，如1
     * @return
     */
    public static RCell cellIDtoJava(String i_CellExcelID)
    {
        if ( Help.isNull(i_CellExcelID) )
        {
            return null;
        }
        
        String [] v_RepalceSpace = {""};
        String    v_CellExcelID  = StringHelp.replaceAll(i_CellExcelID.trim().toUpperCase() ,"$" ,"");
        int       v_RowNo        = Integer.parseInt(StringHelp.replaceAll(v_CellExcelID ,$A_TO_Z ,v_RepalceSpace));
        String    v_ColName      = StringHelp.replaceAll(v_CellExcelID ,v_RowNo + "" ,"");
        
        RCell v_Ret = new RCell();
        
        v_Ret.setRowNo( v_RowNo - 1);
        v_Ret.setColNo(StringHelp.reABC26(v_ColName));
        v_Ret.setFixedRow(i_CellExcelID.trim().indexOf("$") > 0);
        v_Ret.setFixedCol(i_CellExcelID.trim().startsWith("$"));
        
        return v_Ret;
    }
    
    
    
    /**
     * 将Java语言标识的单元格坐标（行号、列号），转换为Excel的单元格ID，如A1
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-20
     * @version     v1.0
     *
     * @param i_RCell       单元格的行、列信息
     * @param i_IsFixedRow  绝对定位的行，即固定行号
     * @param i_IsFixedCol  绝对定位的列，即固定列号
     * @return
     */
    public static String cellIDtoExcel(RCell i_RCell)
    {
        if ( i_RCell == null || i_RCell.getRowNo() == null || i_RCell.getColNo() == null )
        {
            return null;
        }
        
        return cellIDtoExcel(i_RCell.getRowNo() ,i_RCell.getColNo() ,i_RCell.isFixedRow() ,i_RCell.isFixedCol());
    }
    
    
    
    /**
     * 将Java语言标识的单元格坐标（行号、列号），转换为Excel的单元格ID，如A1
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-20
     * @version     v1.0
     *
     * @param i_RowNo       行号（下标从0开始）
     * @param i_ColNo       列号（下标从0开始）
     * @return
     */
    public static String cellIDtoExcel(int i_RowNo ,int i_ColNo)
    {
        return cellIDtoExcel(i_RowNo ,i_ColNo ,false ,false);
    }
    
    
    
    /**
     * 将Java语言标识的单元格坐标（行号、列号），转换为Excel的单元格ID，如A1
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-20
     * @version     v1.0
     *
     * @param i_RowNo       行号（下标从0开始）
     * @param i_ColNo       列号（下标从0开始）
     * @param i_IsFixedRow  绝对定位的行，即固定行号
     * @param i_IsFixedCol  绝对定位的列，即固定列号
     * @return
     */
    public static String cellIDtoExcel(int i_RowNo ,int i_ColNo ,boolean i_IsFixedRow ,boolean i_IsFixedCol)
    {
        if ( i_RowNo < 0 || i_ColNo < 0 )
        {
            return null;
        }
        
        return (i_IsFixedCol ? "$" : "") + StringHelp.toABC26(i_ColNo) + (i_IsFixedRow ? "$" : "") + (i_RowNo + 1);
    }
    
}
