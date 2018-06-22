package org.hy.common.report;

import java.util.List;

import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;





/**
 * 报表的辅助类 
 *   1. Java对象转为Excel文件
 *   2. Excel文件转为Java对象
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-18
 * @version     v1.0
 *              v2.0  2017-05-09  添加：Excel文件转为Java对象
 *              v3.0  2018-06-22  添加：在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
 */
public class ReportHelp
{
    
    /**
     * Excel文件转为Java对象。
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
    public final static List<?> toJava(RTemplate i_RTemplate ,String i_ExcelFileName)
    {
        return ExcelToJava.read(i_RTemplate ,i_ExcelFileName ,0);
    }
    
    
    
    /**
     * Excel文件转为Java对象
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
    public final static List<?> toJava(RTemplate i_RTemplate ,String i_ExcelFileName ,int i_SheetNo)
    {
        return ExcelToJava.read(i_RTemplate ,i_ExcelFileName ,i_SheetNo);
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
    public final static List<?> toJava(RTemplate i_RTemplate ,String i_ExcelFileName ,boolean i_IsAddNull)
    {
        return ExcelToJava.read(i_RTemplate ,i_ExcelFileName ,i_IsAddNull);
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
    public final static List<?> toJava(RTemplate i_RTemplate ,String i_ExcelFileName ,int i_SheetNo ,boolean i_IsAddNull)
    {
        return ExcelToJava.read(i_RTemplate ,i_ExcelFileName ,i_SheetNo ,i_IsAddNull);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_SheetName  Excel工作表的名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate) throws RTemplateException
    {
        return JavaToExcel.write(null ,i_SheetName ,i_Datas ,i_RTemplate ,false ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-26
     * @version     v1.0
     *
     * @param i_SheetName  Excel工作表的名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @param i_IsAppend   是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend) throws RTemplateException
    {
        return JavaToExcel.write(null ,i_SheetName ,i_Datas ,i_RTemplate ,i_IsAppend ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2018-06-22
     * @version     v1.0
     *
     * @param i_SheetName     Excel工作表的名称
     * @param i_Datas         数据对象
     * @param i_RTemplate     模板信息对象
     * @param i_IsAppend      是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @param i_AddBlankRow   在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend ,int i_AddBlankRow) throws RTemplateException
    {
        return JavaToExcel.write(null ,i_SheetName ,i_Datas ,i_RTemplate ,i_IsAppend ,i_AddBlankRow);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-28
     * @version     v1.0
     *
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(List<?> i_Datas ,RTemplate i_RTemplate) throws RTemplateException
    {
        return JavaToExcel.write(null ,null ,i_Datas ,i_RTemplate ,false  ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-26
     * @version     v1.0
     *
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @param i_IsAppend   是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend) throws RTemplateException
    {
        return JavaToExcel.write(null ,null ,i_Datas ,i_RTemplate ,i_IsAppend ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2018-06-22
     * @version     v1.0
     *
     * @param i_Datas         数据对象
     * @param i_RTemplate     模板信息对象
     * @param i_IsAppend      是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @param i_AddBlankRow   在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend ,int i_AddBlankRow) throws RTemplateException
    {
        return JavaToExcel.write(null ,null ,i_Datas ,i_RTemplate ,i_IsAppend ,i_AddBlankRow);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_Workbook   工作薄对象
     * @param i_SheetName  工作表名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(RWorkbook i_Workbook ,List<?> i_Datas ,RTemplate i_RTemplate) throws RTemplateException
    {
        return JavaToExcel.write(i_Workbook ,null ,i_Datas ,i_RTemplate ,false ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-26
     * @version     v1.0
     *
     * @param i_Workbook   工作薄对象
     * @param i_SheetName  工作表名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @param i_IsAppend   是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(RWorkbook i_Workbook ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend) throws RTemplateException
    {
        return JavaToExcel.write(i_Workbook ,null ,i_Datas ,i_RTemplate ,i_IsAppend ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-10-26
     * @version     v1.0
     *
     * @param i_Workbook      工作薄对象
     * @param i_SheetName     工作表名称
     * @param i_Datas         数据对象
     * @param i_RTemplate     模板信息对象
     * @param i_IsAppend      是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @param i_AddBlankRow   在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(RWorkbook i_Workbook ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend ,int i_AddBlankRow) throws RTemplateException
    {
        return JavaToExcel.write(i_Workbook ,null ,i_Datas ,i_RTemplate ,i_IsAppend ,i_AddBlankRow);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_Workbook   工作薄对象
     * @param i_SheetName  工作表名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(RWorkbook i_Workbook ,String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate) throws RTemplateException
    {
        return JavaToExcel.write(i_Workbook ,i_SheetName ,i_Datas ,i_RTemplate ,false ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @param i_Workbook   工作薄对象
     * @param i_SheetName  工作表名称
     * @param i_Datas      数据对象
     * @param i_RTemplate  模板信息对象
     * @param i_IsAppend   是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(RWorkbook i_Workbook ,String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend) throws RTemplateException
    {
        return JavaToExcel.write(i_Workbook ,i_SheetName ,i_Datas ,i_RTemplate ,i_IsAppend ,0);
    }
    
    
    
    /**
     * 向Excel文件中写数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2018-06-22
     * @version     v1.0
     *
     * @param i_Workbook      工作薄对象
     * @param i_SheetName     工作表名称
     * @param i_Datas         数据对象
     * @param i_RTemplate     模板信息对象
     * @param i_IsAppend      是否为追加模式。当为追加模式为true时，向已有的工作表中写数据。未创建任何工作表时，会自动创建。
     * @param i_AddBlankRow   在报表标题前生成几行空行，起到分隔作用，一般用于追加模式。
     * @return
     * @throws RTemplateException 
     */
    public final static RWorkbook toExcel(RWorkbook i_Workbook ,String i_SheetName ,List<?> i_Datas ,RTemplate i_RTemplate ,boolean i_IsAppend ,int i_AddBlankRow) throws RTemplateException
    {
        return JavaToExcel.write(i_Workbook ,i_SheetName ,i_Datas ,i_RTemplate ,i_IsAppend ,i_AddBlankRow);
    }
    
}
