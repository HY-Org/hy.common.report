package org.hy.common.report.junit.formula;

import org.hy.common.Help;
import org.hy.common.report.ExcelFormula;
import org.hy.common.report.bean.RCell;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.XType;
import org.hy.common.xml.annotation.Xjava;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;





/**
 * 测试单元：Excel公式的演示
 *
 * @author      ZhengWei(HY)
 * @createDate  2020-05-19
 * @version     v1.0
 */
@Xjava(value=XType.XML)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_Formula
{
    
    private static boolean $isInit = false;
    
    
    
    public JU_Formula() throws Exception
    {
        if ( !$isInit )
        {
            $isInit = true;
            XJava.parserAnnotation(this.getClass().getName());
        }
    }
    
    
    
    @Test
    public void test_A1_to_Java()
    {
        String v_ExcelCellID = "";
        RCell  v_RCell       = null;
        
        
        v_ExcelCellID = "AAA1";  // 703列
        v_RCell = ExcelFormula.cellIDtoJava(v_ExcelCellID);
        System.out.println(v_ExcelCellID + "\t = 第" + (v_RCell.getRowNo() + 1) + "行，\t第" + (v_RCell.getColNo() + 1) + "列");
        
        
        v_ExcelCellID = "BAA1";  // 1379列
        v_RCell = ExcelFormula.cellIDtoJava(v_ExcelCellID);
        System.out.println(v_ExcelCellID + "\t = 第" + (v_RCell.getRowNo() + 1) + "行，\t第" + (v_RCell.getColNo() + 1) + "列");
        
        v_ExcelCellID = "XFD1";  // 16384列
        v_RCell = ExcelFormula.cellIDtoJava(v_ExcelCellID);
        System.out.println(v_ExcelCellID + "\t = 第" + (v_RCell.getRowNo() + 1) + "行，\t第" + (v_RCell.getColNo() + 1) + "列");
        
        
        
        for (String v_Level2ID : ExcelFormula.$A_TO_Z)
        {
            for (String v_ColName : ExcelFormula.$A_TO_Z)
            {
                for (int v_RowNo=1; v_RowNo<=10; v_RowNo++)
                {
                    v_ExcelCellID = v_Level2ID + v_ColName + v_RowNo;
                    v_RCell = ExcelFormula.cellIDtoJava(v_ExcelCellID);
                    System.out.println(v_ExcelCellID + "\t = 第" + (v_RCell.getRowNo() + 1) + "行，\t第" + (v_RCell.getColNo() + 1) + "列");
                }
                
                System.out.println();
            }
        }
    }
    
    
    
    @Test
    public void test_Java_to_A1()
    {
        for (int v_RowIndex=0; v_RowIndex<2; v_RowIndex++)
        {
            for (int v_ColIndex=0; v_ColIndex<16384; v_ColIndex++)
            {
                System.out.println("第" + (v_RowIndex + 1) + "行，\t第" + (v_ColIndex + 1) + "列 \t = " + ExcelFormula.cellIDtoExcel(v_RowIndex ,v_ColIndex));
            }
        }
    }
    
    
    
    @Test
    public void test_calcCellOffset()
    {
        String [] v_CellExcelIDs = {"A1"
                                   ,"A2"
                                   ,"B1"
                                   ,"B2"
                                   ,"$B2"
                                   ,"B$2"
                                   ,"$B$2"
                                   ,"Z2"
                                   ,"BAA2"
        };
        
        for (String v_CellExcelID : v_CellExcelIDs)
        {
            System.out.println(v_CellExcelID + "\t 偏移 0 , 0 = " + ExcelFormula.calcCellOffset(v_CellExcelID , 0 , 0));
            System.out.println(v_CellExcelID + "\t 偏移 1 , 0 = " + ExcelFormula.calcCellOffset(v_CellExcelID , 1 , 0));
            System.out.println(v_CellExcelID + "\t 偏移 1 , 1 = " + ExcelFormula.calcCellOffset(v_CellExcelID , 1 , 1));
            System.out.println(v_CellExcelID + "\t 偏移 0 , 1 = " + ExcelFormula.calcCellOffset(v_CellExcelID , 0 , 1));
            System.out.println(v_CellExcelID + "\t 偏移-1 , 0 = " + ExcelFormula.calcCellOffset(v_CellExcelID ,-1 , 0));
            System.out.println(v_CellExcelID + "\t 偏移-1 ,-1 = " + ExcelFormula.calcCellOffset(v_CellExcelID ,-1 ,-1));
            System.out.println(v_CellExcelID + "\t 偏移 0 ,-1 = " + ExcelFormula.calcCellOffset(v_CellExcelID , 0 ,-1));
            System.out.println();
        }
    }
    
    
    
    @Test
    public void test_parserFormula()
    {
        String [] v_Formulas = {"=A1 + B1"
                               ,"=A1 - B1"
                               ,"=A1 * B1"
                               ,"=A1 / B1"
                               ,"=A1 / B1 + C1 - D1 * E1"
                               ,"=$A1 / B$1 + $C$1 - D1 * E1"
                               ,"=$A1 / B$1 + A1 + B1 + $C$1 - D1 * E1"
        };
        
        for (String v_Formula : v_Formulas)
        {
            System.out.println("\n\n" + v_Formula);
            
            Help.print(ExcelFormula.parserFormula(v_Formula));
        }
    }
    
}
