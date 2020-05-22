package org.hy.common.report.junit.formula;

import java.util.ArrayList;
import java.util.List;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.report.ExcelFormula;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RCell;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.junit.image.ImageReportBean;
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
    
    
    
    /**
     * 测试：带公式的报表生成
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-21
     * @version     v1.0
     *
     * @throws RTemplateException
     */
    @Test
    public void test_Formula() throws RTemplateException
    {
        RTemplate             v_RTemplate = (RTemplate)XJava.getObject("Report_Formula_xlsx");
        List<ImageReportBean> v_Datas     = this.getDatas(3);
        
        RWorkbook v_RWorkbook = ReportHelp.toExcel(v_Datas ,v_RTemplate);
        
        ExcelHelp.save(v_RWorkbook.getWorkbook() ,"C:\\Users\\ZhengWei\\Desktop\\Formula_" + Date.getNowTime().getFull_ID() + ".xlsx");
    }
    
    
    
    /**
     * 测试：Excel单元格ID的转换为数字表示的行号、列号
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-19
     * @version     v1.0
     *
     */
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
    
    
    
    /**
     * 测试：将数字表示的行号、列号转为Excel的单元格ID
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-20
     * @version     v1.0
     *
     */
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
    
    
    
    /**
     * 测试：Excel单元格ID的偏移量
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-20
     * @version     v1.0
     *
     */
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
    
    
    
    /**
     * 测试：解释Excel公式中的单元格ID
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-21
     * @version     v1.0
     *
     */
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
    
    
    
    /**
     * 测试：Excel公式的偏移计算
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-21
     * @version     v1.0
     *
     */
    @Test
    public void test_calcFormulaOffset()
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
            System.out.println("\n\n" + v_Formula + "\n" + ExcelFormula.calcFormulaOffset(v_Formula ,1 ,1));
        }
    }
    
    
    
    /**
     * 生成测试数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2019-05-30
     * @version     v1.0
     *
     * @param i_Size
     * @return
     */
    public List<ImageReportBean> getDatas(int i_Size)
    {
        List<ImageReportBean> v_Datas = new ArrayList<ImageReportBean>();
        
        for (int i=1; i<=i_Size; i++)
        {
            ImageReportBean v_Data   = new ImageReportBean();
            int             v_PSize  = v_Data.gatPropertySize();
            
            for (int v_PIndex=0; v_PIndex<v_PSize; v_PIndex++)
            {
                v_Data.setPropertyValue(v_PIndex ,v_PIndex + "");
            }
            
            // Java生成动态Excel公式
            if ( i % 5 == 1 )
            {
                v_Data.setDyForumla("=SUM(A1:A9)");
            }
            else if ( i % 5 == 2 )
            {
                v_Data.setDyForumla("=SUM(B1:B9)");
            }
            else  if ( i % 5 == 3 )
            {
                v_Data.setDyForumla("=SUM(C1:C9)");
            }
            else  if ( i % 5 == 4 )
            {
                v_Data.setDyForumla("=SUM(D1:D9)");
            }
            else
            {
                v_Data.setDyForumla("=SUM(E1:E9)");
            }
            
            v_Datas.add(v_Data);
        }
        
        return v_Datas;
    }
    
}