package org.hy.common.report.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hy.common.Help;
import org.hy.common.MethodReflect;
import org.hy.common.PartitionMap;
import org.hy.common.StringHelp;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.report.event.SheetListener;
import org.hy.common.report.event.ValueListener;
import org.hy.common.xml.SerializableDef;





/**
 * 报表模板信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-15
 * @version     v1.0
 *              v2.0  2017-06-21  优化：通过isSafe参数控制，放弃一些非必要的效验来提高性能
 *                                优化：启用对SXSSFWorkbook工作薄的支持大数据量
 *              v3.0  2017-06-25  优化：通过check()方法，预先在生成报表前，对模板信息检查。
 *                                     就不用在生成报表时动态检查模板信息。
 *              v4.0  2017-06-28  添加：支持分页功能。比原Excel页眉、页脚更高级、内容更丰富的分页页眉、分页页脚功能。
 *              v4.1  2017-06-29  添加：工作表写入数据完成的自定义事件机制，方便用户做后续操作。
 *                                添加：支持首个分页页眉与其后分页页眉的差异化内容及样式的功能。通过RTemplate.titlePageHeaderFirstWriteByRow参数调节。
 *              v4.3  2017-07-11  发现：copyRow(...)方法中，当isBig=true、 rowAccessWindowSize<v_ForSize 时，v_DataForRow会出现空的情况。
 *                                     原因是：SXSSFWorkbook缓存在内存中的行数是有限的。发现人：李浩
 *                                     因此将rowAccessWindowSize的默认值扩大10倍，如果还不够大，请自行设置rowAccessWindowSize的大小。
 *              v4.4  2017-07-19  添加：是否将整数显示为小数的形式的选择开功能。需Excel模板配合设置单元格的格式为：小数格式(0.000 或 0.###)
 *              v4.5  2017-07-31  添加：Excel高级筛选，由报表配置文件参数(isExcelFilter)控制生成的功能
 *              v4.6  2017-09-11  添加：自动计算行高的功能。建议人：李浩
 *              v5.0  2018-06-12  添加：在 ":系统固定变量" 格式的基础上，添加对 "{:系统固定变量}" 格式的填充替换的支持。
 *              v5.1  2018-06-19  添加：实例比较、相等时，添加this.name属性的判定。
 *                                       防止同一Excel文件生成多个模板对象时，无法区别模板实例。建议人：邹德福
 *              v6.0  2020-05-11  添加：打印分页模式。确保同一Excel在不同电脑上打印时，均能保持相同的分页结果。
 *                                      发现人：雷伟松
 *              v7.0  2020-11-10  添加：支持分项统计的小计在明细数据之前或之后的功能。建议人：尚仁强
 */
public class RTemplate extends SerializableDef implements Comparable<RTemplate>
{
    
    private static final long serialVersionUID = 6269939315364526275L;
    

    /** 系统固定变量名称：数据行号的变量名称。下标从1开始 */
    public final static String         $ValueName_RowNo              = "RowNo__";
    
    /** 系统固定变量名称：数据索引号的变量名称。下标从0开始 */
    public final static String         $ValueName_RowIndex           = "RowIndex__";
    
    /** 系统固定变量名称：数据总量的变量名称 */
    public final static String         $ValueName_RowCount           = "RowCount__";
    
    /** 系统固定变量名称：数据小计总量的变量名称 */
    public final static String         $ValueName_RowSubtotalCount   = "RowSubtotalCount__";
    
    /** 系统固定变量名称：分页页号的变量名称。下标从1开始 */
    public final static String         $ValueName_PageNo             = "PageNo__";
    
    /** 系统固定变量名称：分页总页数的变量名称。暂时不支持，因为动态行 */
    public final static String         $ValueName_PageSize           = "PageSize__";
    
    
    
    /** 打印分页模式 1. auto     ：自动模式（默认的）。对于复杂报表，可能出现同一Excel在不同电脑上打印时，分页结果是有差异的。 */
    public final static String         $PageBreakMode_Auto           = "auto";
    
    /** 打印分页模式 2. rowBreak ：按模板打印区域及输出数据量大小（即分页页数），添加多个分隔符分页。 */
    public final static String         $PageBreakMode_Page           = "rowBreak";
    
    
    
    /** 报表小计的输出位置（top：在明细数据之前输出小计；bottom） */
    public final static String         $SubTotalPosition_Top         = "top";
    
    /** 报表小计的输出位置（top：在明细数据之前输出小计；bottom） */
    public final static String         $SubTotalPosition_Bottom      = "bottom";
    
    
    
    /** 纵深扩展--表示从左到右的方向，一行或多行为一个对象数据 */
    public final static Integer       $Direction_Vertical            = 0;
    
    /** 横向扩展--表示从上到下的方向，一列或多列为一个对象数据 */
    public final static Integer       $Direction_Horizontal          = 1;
    
    
    
    /** 变量名称的前置限定符 */
    public  final static String       $Value_LimitBefore             = "{";
    
    /** 变量名称的后置限定符 */
    public  final static String       $Value_LimitEnd                = "}";
    
    /** 通过正则表达式解释变量名的前半部分。整体格式如：{:Key} */
    private final static String       $Pattern_Values_Before         = "\\" + $Value_LimitBefore;
    
    /** 通过正则表达式解释变量名的后半部分。整体格式如：{:Key} */
    private final static String       $Pattern_Values_End            = "[\\w\\._\\[\\]$]+\\" + $Value_LimitEnd;
    
    
    
    /** 模板名称 */
    private String                     name;
    
    /** Excel文件版本(1.xls  2.xlsx) */
    private String                     excelVersion;
    
    /** 模板文件的名称(全路径+文件名称) */
    private String                     excelFileName;
    
    /** 报表模板对应的工作表索引位置（下标从零开始） */
    private Integer                    sheetIndex;
    
    /** 
     * Excel数据的方向。
     * 0：纵深扩展--表示从左到右的方向，一行或多行为一个对象数据。此为默认值
     * 1：横向扩展--表示从上到下的方向，一列或多列为一个对象数据
     */
    private Integer                    direction;
    
    /** 
     * 报表标题占用一整页。默认为：false。即第一页的数据量按标题行数是动态的，第二页及其后是固定的数据量。
     * 只用于：分页页眉、分页页脚的情况下
     */
    private boolean                    titleUseOnePage;
    
    /** 标题系数。当 titleUseOnePage=true 时，titleRatio=0 ，当 titleUseOnePage=false 时，titleRatio=1*/
    private int                        titleRatio;
    
    /** 报表标题的开始行号（包括此行）。下标从零开始 */
    private Integer                    titleBeginRow;
    
    /** 报表标题的结束行号（包括此行）。下标从零开始 */
    private Integer                    titleEndRow;
    
    /** 
     * 在什么位置(Excel的行号)上首次写入分页页眉。下标从零开始。默认为0 
     * 
     * 主要用于：首个分页页眉与第二行分页页眉样式不一样的情况。
     *          在此情况下，可将首个分页页眉与总标题合并后，当总标题写入。
     */
    private int                        titlePageHeaderFirstWriteByRow;
    
    /** 通过 titlePageHeaderFirstWriteByRow 计算出来的首个分页页眉前已写入了多少行的真实数据。默认为：0 */
    private int                        titlePageHeaderFirstWriteByRealDataCount;
    
    /** 通过 titlePageHeaderFirstWriteByRow 计算出来的分页页眉系数。默认为：0 */
    private int                        titlePageHeaderRate;
    
    /** 报表分页页眉的开始行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用 */
    private Integer                    titlePageHeaderBeginRow;
    
    /** 报表分页页眉的结束行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用 */
    private Integer                    titlePageHeaderEndRow;
    
    /** 报表分页页脚的开始行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用 */
    private Integer                    titlePageFooterBeginRow;
    
    /** 报表分页页脚的结束行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用 */
    private Integer                    titlePageFooterEndRow;
    
    /** 报表分页的每页显示的行记录数 */
    private Integer                    perPageRowSize;
    
    /** 报表数据的开始行号（包括此行）。下标从零开始 */
    private Integer                    dataBeginRow;
    
    /** 报表数据的结束行号（包括此行）。下标从零开始 */
    private Integer                    dataEndRow;
    
    /** 报表数据的开始列号（包括此列）。下标从零开始。用于Excel转为Java对象，并且this.direction=1的情况 */
    private Integer                    dataBeginCol;
    
    /** 报表数据的结束列号（包括此列）。下标从零开始。用于Excel转为Java对象，并且this.direction=1的情况 */
    private Integer                    dataEndCol;
    
    /** 报表小计的开始行号（包括此行）。下标从零开始 */
    private Integer                    subtotalBeginRow;
    
    /** 报表小计的结束行号（包括此行）。下标从零开始 */
    private Integer                    subtotalEndRow;
    
    /** 报表小计的输出位置（top：在明细数据之前输出小计；bottom）。默认是：bottom */
    private String                     subtotalPosition;
    
    /** 合计内容的开始行号（包括此行）。下标从零开始 */
    private Integer                    totalBeginRow;
    
    /** 合计内容的结束行号（包括此行）。下标从零开始 */
    private Integer                    totalEndRow;
    
    /** 
     * 打印分页模式
     *   1. auto     ：自动模式（默认的）。对于复杂报表，可能出现同一Excel在不同电脑上打印时，分页结果是有差异的。
     *   2. rowBreak ：按模板打印区域及输出数据量大小（即分页页数），添加多个分隔符分页。
     *   
     * 要求：须配合Excel模板上设置的打印区域一共使用
     */
    private String                     pageBreakMode;
    
    
    
    /** 报表数据的Java类型 */
    private String                     dataClass;
    
    /** 值的标记。默认为一个冒号：":" */
    private String                     valueSign;
    
    /** 
     * 是否将整数显示为小数的形式。需Excel模板配合设置单元格的格式为：小数格式(0.000 或 0.###)。
     * 
     *  Excel模板配合设置单元格的格式为：显示3位小数时
     *    1. 当为true 时，10会显示为 10.000
     *    2. 当为false时，10还是显示为 10
     * 
     * 默认为：false
     */
    private boolean                    isIntegerShowDecimal;
    
    /** 是否添加Excel高级筛选功能。默认为：false */
    private boolean                    isExcelFilter; 
    
    /** 单元格信息。Map.key为: "行号,列号" */
    private Map<String ,RCell>         cells;
    
    /** 是要安全？还是要性能（默认为：性能） */
    private boolean                    isSafe;
    
    /** 
     * 当为大数据量导出时，建议使用 SXSSFWorkbook
     * POI对excel的导出操作，一般只使用HSSFWorkbook以及SXSSFWorkbook，
     * HSSFWorkbook用来处理较少的数据量，
     * SXSSFWorkbook用来处理大数据量以及超大数据量的导出
     * 
     * 默认为：true
     */
    private boolean                    isBig;

    /** 
     * 当使用 SXSSFWorkbook 时（this.isBig=true），创建对象new SXSSFWorkbook(rowAccessWindowSize)入参。
     * 保持在内存中的行数，直到刷新为止
     */
    private Integer                    rowAccessWindowSize;
    
    /**
     * 自动行高
     * Map.key    为变量名称
     * Map.value  暂时也保存变量名称
     */
    private Map<String ,Object>        autoHeights;
    
    
    
    /** 
     * 变量自定义处理事件
     * Map.key  为变量名称 
     */
    private Map<String ,ValueListener> valueListeners;
    
    /** 工作表整体的自定义处理事件 */
    private List<SheetListener>        sheetListeners;
    
    
    
    /** 报表模板信息对应的工作表对象(一般只初始加载一次) */
    private Sheet                      templateSheet; 
    
    /** 解释的值的反射方法集合(一般只初始加载一次) */
    private Map<String ,RCellGroup>    valueMethods;
    
    /** 按 this.valueSign 生成的系统变量名称 */
    private Map<String ,String>        valueNames;
    
    /** 是否需要检查 */
    private boolean                    isCheck;
    
    
    
    public RTemplate()
    {
        this.sheetIndex           = 0;
        this.direction            = 0;
        this.templateSheet        = null;
        this.excelVersion         = null;
        this.valueMethods         = new LinkedHashMap<String ,RCellGroup>();
        this.valueNames           = new Hashtable<String ,String>();
        this.autoHeights          = new Hashtable<String ,Object>();
        this.valueListeners       = new Hashtable<String ,ValueListener>();
        this.sheetListeners       = new ArrayList<SheetListener>();
        this.isIntegerShowDecimal = false;
        this.isExcelFilter        = false;
        this.cells                = new HashMap<String ,RCell>();
        this.isSafe               = false;
        this.isBig                = true;
        this.isCheck              = true;
        this.pageBreakMode        = "auto";
        this.subtotalPosition     = $SubTotalPosition_Bottom;
        this.rowAccessWindowSize  = SXSSFWorkbook.DEFAULT_WINDOW_SIZE * 10;
        this.titlePageHeaderFirstWriteByRow           = 0;
        this.titlePageHeaderFirstWriteByRealDataCount = 0;
        this.titlePageHeaderRate                      = 0;
        this.setValueSign(":");
        this.setTitleUseOnePage(false);
    }
    
    
    
    public RTemplate(RTemplate i_Other)
    {
        this();
        this.init(i_Other);
    }
    
    
    
    /**
     * 创建报表数据的Java对象实例
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-08
     * @version     v1.0
     *
     * @return
     */
    public Object newObject()
    {
        try
        {
            return Class.forName(this.dataClass).newInstance();
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
        }
        
        return null;
    }
    
    
    
    /**
     * 获取报表模板对应的工作表
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-16
     * @version     v1.0
     *
     * @return
     */
    public synchronized Sheet getTemplateSheet()
    {
        if ( null == this.templateSheet )
        {
            this.isCheck = true;
            
            List<Sheet> v_Sheets = ExcelHelp.read(this.excelFileName);
            
            if ( Help.isNull(v_Sheets) )
            {
                this.templateSheet = null;
            }
            else
            {
                this.templateSheet = v_Sheets.get(this.sheetIndex);
            }
            
            this.init();
            
            this.getExcelVersion();
            
            if ( this.getTitlePageHeaderFirstWriteByRow() >= 1 )
            {
                this.titlePageHeaderFirstWriteByRealDataCount = this.getTitlePageHeaderFirstWriteByRow() - this.getRowCountTitlePageFooter() - this.getRowCountTitle();
                this.titlePageHeaderRate                      = this.getRowCountTitlePageHeader();
            }
            else
            {
                this.titlePageHeaderFirstWriteByRealDataCount = 0;
                this.titlePageHeaderRate                      = 0;
            }
        }
        
        return this.templateSheet;
    }
    
    
    
    /**
     * 检查报表模板中的行数据信息是否存在
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @throws RTemplateException
     */
    public synchronized void check() throws RTemplateException
    {
        this.getTemplateSheet();
        
        if ( !this.isCheck )
        {
            return;
        }
        
        if ( this.getRowCountTitle() >= 1 )
        {
            if ( null == this.templateSheet.getRow(this.titleBeginRow) )
            {
                throw new RTemplateException("报表标题的开始行号[" + this.titleBeginRow + "]，在模板文件中不存在");
            }
            
            if ( null == this.templateSheet.getRow(this.titleEndRow) )
            {
                throw new RTemplateException("报表标题的结束行号[" + this.titleEndRow + "]，在模板文件中不存在");
            }
        }
        
        if ( this.getRowCountTitlePageHeader() >= 1 )
        {
            if ( null == this.templateSheet.getRow(this.titlePageHeaderBeginRow) )
            {
                throw new RTemplateException("报表分页页眉的开始行号[" + this.titlePageHeaderBeginRow + "]，在模板文件中不存在");
            }
            
            if ( null == this.templateSheet.getRow(this.titlePageHeaderEndRow) )
            {
                throw new RTemplateException("报表分页页眉的结束行号[" + this.titlePageHeaderEndRow + "]，在模板文件中不存在");
            }
        }
        
        if ( this.getRowCountTitlePageFooter() >= 1 )
        {
            if ( null == this.templateSheet.getRow(this.titlePageFooterBeginRow) )
            {
                throw new RTemplateException("报表分页页脚的开始行号[" + this.titlePageFooterBeginRow + "]，在模板文件中不存在");
            }
            
            if ( null == this.templateSheet.getRow(this.titlePageFooterEndRow) )
            {
                throw new RTemplateException("报表分页页脚的结束行号[" + this.titlePageFooterEndRow + "]，在模板文件中不存在");
            }
        }
        
        if ( this.getRowCountData() >= 1 )
        {
            if ( null == this.templateSheet.getRow(this.dataBeginRow) )
            {
                throw new RTemplateException("报表数据的开始行号[" + this.dataBeginRow + "]，在模板文件中不存在");
            }
            
            if ( null == this.templateSheet.getRow(this.dataEndRow) )
            {
                throw new RTemplateException("报表数据的结束行号[" + this.dataEndRow + "]，在模板文件中不存在");
            }
        }
        
        if ( this.getRowCountSubtotal() >= 1 )
        {
            if ( null == this.templateSheet.getRow(this.subtotalBeginRow) )
            {
                throw new RTemplateException("报表小计的开始行号[" + this.subtotalBeginRow + "]，在模板文件中不存在");
            }
            
            if ( null == this.templateSheet.getRow(this.subtotalEndRow) )
            {
                throw new RTemplateException("报表小计的结束行号[" + this.subtotalEndRow + "]，在模板文件中不存在");
            }
        }
        
        if ( this.getRowCountTotal() >= 1 )
        {
            if ( null == this.templateSheet.getRow(this.totalBeginRow) )
            {
                throw new RTemplateException("报表合计的开始行号[" + this.totalBeginRow + "]，在模板文件中不存在");
            }
            
            if ( null == this.templateSheet.getRow(this.totalEndRow) )
            {
                throw new RTemplateException("报表合计的结束行号[" + this.totalEndRow + "]，在模板文件中不存在");
            }
        }
        
        this.isCheck = false;
    }
    
    
    
    /**
     * 初始化
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     */
    private void init()
    {
        try
        {
            int                         v_MinRow     = Help.min(titleBeginRow ,dataBeginRow ,titlePageHeaderBeginRow ,titlePageFooterBeginRow ,subtotalBeginRow ,totalBeginRow);
            int                         v_MaxRow     = Help.max(titleEndRow   ,dataEndRow   ,titlePageHeaderEndRow   ,titlePageFooterEndRow   ,subtotalEndRow   ,totalEndRow);
            PartitionMap<String ,RCell> v_ExcelDatas = ExcelHelp.readDatas(this.getTemplateSheet() ,v_MinRow ,v_MaxRow);
            List<String>                v_TempDatas  = Help.toListKeys(v_ExcelDatas);
            Class<?>                    v_JavaClass  = Help.forName(this.dataClass);
            String                      v_PatternKey = $Pattern_Values_Before + this.valueSign + $Pattern_Values_End;
            Pattern                     v_Pattern    = Pattern.compile(v_PatternKey);
            
            this.cells = new HashMap<String ,RCell>();
            for (List<RCell> v_Cells : v_ExcelDatas.values())
            {
                for (RCell v_Cell : v_Cells)
                {
                    this.cells.put(v_Cell.getRowNo() + "," + v_Cell.getColNo() ,v_Cell);
                }
            }
            
            for (int i=v_TempDatas.size()-1; i>=0; i--)
            {
                final String v_Value = v_TempDatas.get(i);
                
                if ( this.valueNames.containsKey(v_Value) )
                {
                    // 系统变量名称不解释
                    continue;
                }
                
                if ( this.valueMethods.containsKey(v_Value) )
                {
                    // 防止重复解释
                    continue;
                }
                
                
                List<String> v_Values = new ArrayList<String>();
                RCellGroup   v_RCellG = null;
                if ( v_Value.length() >= this.valueSign.length() + 1 && v_Value.startsWith(this.valueSign) )
                {
                    v_Values.add(v_Value);
                    v_RCellG = new RCellGroup(v_Value ,false);
                }
                else
                {
                    Matcher v_Matcher = v_Pattern.matcher(v_Value);
                    while( v_Matcher.find() )
                    {  
                        v_Values.add(v_Value.substring(v_Matcher.start() ,v_Matcher.end()));
                    }
                    
                    v_RCellG = new RCellGroup(v_Value ,true);
                }
                
                for (String v_ItemValue : v_Values)
                {
                    String v_ValueName = "";
                    if ( v_RCellG.isReplaceMode() )
                    {
                        v_ValueName = v_ItemValue.substring($Value_LimitBefore.length() + this.valueSign.length() ,v_ItemValue.length() - $Value_LimitEnd.length());
                    }
                    else
                    {
                        v_ValueName = v_ItemValue.substring(this.valueSign.length());
                    }
                    
                    if ( this.valueNames.containsKey(this.getValueSign() + v_ValueName) )
                    {
                        v_RCellG.add(new RCell(v_ItemValue));
                        continue;
                    }
                    
                    String [] v_Fors      = v_ValueName.split("\\[\\]");
                    boolean   v_IsPull    = false;
                    RCell     v_RCell     = new RCell(v_ItemValue);
                    
                    if ( v_Fors.length >= 2 )
                    {
                        MethodReflect v_ForMR         = new MethodReflect(v_JavaClass ,v_Fors[0] ,true ,MethodReflect.$NormType_Getter);
                        String        v_Iterator      = "";
                        String        v_IteratorSize  = "";
                        int           v_GenericsIndex = 0;
                        
                        if (      MethodReflect.isExtendImplement(v_ForMR.getReturnType() ,List.class) )
                        {
                            v_Iterator      = v_Fors[0] + ".$iterator";
                            v_IteratorSize  = v_Fors[0] + ".$size";
                            v_ValueName     = v_Fors[1].substring(1);
                            v_GenericsIndex = 0;
                        }
                        else if ( MethodReflect.isExtendImplement(v_ForMR.getReturnType() ,Set.class) )
                        {
                            v_Iterator      = v_Fors[0] + ".$iterator";
                            v_IteratorSize  = v_Fors[0] + ".$size";
                            v_ValueName     = v_Fors[1].substring(1);
                            v_GenericsIndex = 0;
                        }
                        else if ( MethodReflect.isExtendImplement(v_ForMR.getReturnType() ,Map.class) )
                        {
                            v_Iterator      = v_Fors[0] + ".$values.$iterator";
                            v_IteratorSize  = v_Fors[0] + ".$size";
                            v_ValueName     = v_Fors[1].substring(1);
                            v_GenericsIndex = 1;
                        }
                        
                        v_RCell.setIteratorSizeMethod(new MethodReflect(v_JavaClass ,v_IteratorSize ,true ,MethodReflect.$NormType_Getter));
                        v_RCell.setIteratorMethod(    new MethodReflect(v_JavaClass ,v_Iterator     ,true ,MethodReflect.$NormType_Getter));
                        
                        Class<?> v_ForElementJavaClass = MethodReflect.getGenericsReturn(v_ForMR.getReturnMethod() ,v_GenericsIndex).getGenericType();
                        
                        try
                        {
                            v_RCell.setValueMethod(new MethodReflect(v_ForElementJavaClass ,v_ValueName ,true ,MethodReflect.$NormType_Getter));
                            v_IsPull = true;
                        }
                        catch (Exception exce)
                        {
                            // 有可能没有Getter方法
                        }
                        
                        try
                        {
                            v_RCell.setValueSetMethod(new MethodReflect(v_ForElementJavaClass ,v_ValueName ,true ,MethodReflect.$NormType_Setter));
                            v_IsPull = true;
                        }
                        catch (Exception exce)
                        {
                            // 有可能没有Setter方法
                        }
                        
                        
                        // 第三级集合的解释
                        if ( v_Fors.length >= 3 )
                        {
                            v_RCell.setNextRCell(new RCell());
                            v_ForMR         = new MethodReflect(v_ForElementJavaClass ,v_Fors[1].substring(1) ,true ,MethodReflect.$NormType_Getter);
                            v_Iterator      = "";
                            v_IteratorSize  = "";
                            v_GenericsIndex = 0;
                            
                            if (      MethodReflect.isExtendImplement(v_ForMR.getReturnType() ,List.class) )
                            {
                                v_Iterator      = v_Fors[1].substring(1) + ".$iterator";
                                v_IteratorSize  = v_Fors[1].substring(1) + ".$size";
                                v_ValueName     = v_Fors[2].substring(1);
                                v_GenericsIndex = 0;
                            }
                            else if ( MethodReflect.isExtendImplement(v_ForMR.getReturnType() ,Set.class) )
                            {
                                v_Iterator      = v_Fors[1].substring(1) + ".$iterator";
                                v_IteratorSize  = v_Fors[1].substring(1) + ".$size";
                                v_ValueName     = v_Fors[2].substring(1);
                                v_GenericsIndex = 0;
                            }
                            else if ( MethodReflect.isExtendImplement(v_ForMR.getReturnType() ,Map.class) )
                            {
                                v_Iterator      = v_Fors[1].substring(1) + ".$values.$iterator";
                                v_IteratorSize  = v_Fors[1].substring(1) + ".$size";
                                v_ValueName     = v_Fors[2].substring(1);
                                v_GenericsIndex = 1;
                            }
                            
                            v_RCell.getNextRCell().setIteratorSizeMethod(new MethodReflect(v_ForElementJavaClass ,v_IteratorSize ,true ,MethodReflect.$NormType_Getter));
                            v_RCell.getNextRCell().setIteratorMethod(    new MethodReflect(v_ForElementJavaClass ,v_Iterator     ,true ,MethodReflect.$NormType_Getter));
                            
                            v_ForElementJavaClass = MethodReflect.getGenericsReturn(v_ForMR.getReturnMethod() ,v_GenericsIndex).getGenericType();
                            
                            try
                            {
                                v_RCell.getNextRCell().setValueMethod(new MethodReflect(v_ForElementJavaClass ,v_ValueName ,true ,MethodReflect.$NormType_Getter));
                                v_IsPull = true;
                            }
                            catch (Exception exce)
                            {
                                // 有可能没有Getter方法
                            }
                            
                            try
                            {
                                v_RCell.getNextRCell().setValueSetMethod(new MethodReflect(v_ForElementJavaClass ,v_ValueName ,true ,MethodReflect.$NormType_Setter));
                                v_IsPull = true;
                            }
                            catch (Exception exce)
                            {
                                // 有可能没有Setter方法
                            }
                        }
                    }
                    else
                    {
                        try
                        {
                            v_RCell.setValueMethod(new MethodReflect(v_JavaClass ,v_ValueName ,true ,MethodReflect.$NormType_Getter));
                            v_IsPull = true;
                        }
                        catch (Exception exce)
                        {
                            // 有可能没有Getter方法
                        }
                        
                        try
                        {
                            v_RCell.setValueSetMethod(new MethodReflect(v_JavaClass ,v_ValueName ,true ,MethodReflect.$NormType_Setter));
                            v_IsPull = true;
                        }
                        catch (Exception exce)
                        {
                            // 有可能没有Setter方法
                        }
                    }
                    
                    if ( v_IsPull )
                    {
                        v_RCellG.add(v_RCell);
                    }
                    else
                    {
                        if ( !this.valueListeners.containsKey(this.getValueSign() + v_ValueName) )
                        {
                            System.err.println(Help.NVL(this.getName() ,this.getExcelFileName()) + "：变量名称或占位符[" + v_Value + "]未匹配到对应数据结构中的属性值。");
                        }
                    }
                }
                
                if ( v_RCellG.size() >= 1 )
                {
                    this.valueMethods.put(v_Value ,v_RCellG);
                }
            }
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
        }
    }
    
    
    
    /**
     * 判定变量名称是否存在
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_ValueName  变量名称
     * @return
     */
    public boolean isExists(String i_ValueName)
    {
        if ( i_ValueName.startsWith(this.valueSign) )
        {
            return true;
        }
        else if ( this.valueMethods.containsKey(i_ValueName) || this.valueNames.containsKey(i_ValueName) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    
    /**
     * 获取单元格保留小数的位数（Excel设置单元格的格式）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-07-19
     * @version     v1.0
     *
     * @param i_RowNo
     * @param i_CellNo
     * @return
     */
    public Integer getDecimal(int i_RowNo ,int i_CellNo)
    {
        RCell v_RCell = this.cells.get(i_RowNo + "," + i_CellNo);
        
        if ( v_RCell != null )
        {
            return v_RCell.getDecimal();
        }
        
        return null;
    }
    
    
    
    /**
     * 通过变量名称反射出对应的数值
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_ValueName     变量名称
     * @param i_Datas         数据
     * @param i_RSystemValue  系统变量信息
     * @param io_RValue       小计循环迭代器
     * @return             
     */
    public RValue getValue(String i_ValueName ,Object i_Datas ,RSystemValue i_RSystemValue ,RValue io_RValue)
    {
        RCellGroup  v_RCellG = this.valueMethods.get(i_ValueName);
        RValue      v_RValue = io_RValue != null ? io_RValue : new RValue();
        
        if ( !Help.isNull(v_RCellG) )
        {
            if ( this.autoHeights.containsKey(i_ValueName) )
            {
                v_RValue.setAutoHeight(true);
            }
            
            try
            {
                // 替换模式
                if ( v_RCellG.isReplaceMode() )
                {
                    Map<String ,Object> v_Replaces  = new HashMap<String ,Object>();
                    
                    if ( Help.isNull(v_RValue.getValueGroup()) )
                    {
                        v_RValue.setValueGroup(new ArrayList<RValue>(v_RCellG.size()));
                        
                        for (int v_Index=0; v_Index<v_RCellG.size(); v_Index++)
                        {
                            RValue v_RValueChild = new RValue();
                            v_RValue.getValueGroup().add(v_RValueChild);
                        }
                    }
                    
                    for (int v_Index=0; v_Index<v_RCellG.size(); v_Index++)
                    {
                        RCell  v_RCell       = v_RCellG.get(v_Index);
                        RValue v_RValueChild = v_RValue.getValueGroup().get(v_Index);
                        String v_ValueName   = StringHelp.replaceAll(v_RCell.getValueName() 
                                                                    ,new String[]{$Value_LimitBefore ,$Value_LimitEnd ,this.valueSign} 
                                                                    ,new String[]{""});
                        
                        // 添加对 "{:系统固定变量}" 格式的填充替换的支持  2018-06-12
                        if ( $ValueName_RowNo.equalsIgnoreCase(v_ValueName) )
                        {
                            v_Replaces.put(v_RCell.getValueName() ,String.valueOf(i_RSystemValue.getRowNo()));
                        }
                        else if ( $ValueName_RowIndex.equalsIgnoreCase(v_ValueName) )
                        {
                            v_Replaces.put(v_RCell.getValueName() ,String.valueOf(i_RSystemValue.getRowIndex()));
                        }
                        else if ( $ValueName_RowCount.equalsIgnoreCase(v_ValueName) )
                        {
                            v_Replaces.put(v_RCell.getValueName() ,String.valueOf(i_RSystemValue.getRowCount()));
                        }
                        else if ( $ValueName_RowSubtotalCount.equalsIgnoreCase(v_ValueName) )
                        {
                            v_Replaces.put(v_RCell.getValueName() ,String.valueOf(i_RSystemValue.getRowSubtotalCount()));
                        }
                        else if ( $ValueName_PageNo.equalsIgnoreCase(v_ValueName) )
                        {
                            v_Replaces.put(v_RCell.getValueName() ,String.valueOf(i_RSystemValue.getPageNo()));
                        }
                        else if ( $ValueName_PageSize.equalsIgnoreCase(v_ValueName) )
                        {
                            v_Replaces.put(v_RCell.getValueName() ,String.valueOf(i_RSystemValue.getPageSize()));
                        }
                        else if ( v_RCell.isFor() )
                        {
                            if ( v_RValueChild.getIterator() == null )
                            {
                                v_RValueChild.setIterator((Iterator<?>)v_RCell.getIteratorMethod()    .invokeForInstance(i_Datas));
                                v_RValueChild.setIteratorSize((Integer)v_RCell.getIteratorSizeMethod().invokeForInstance(i_Datas));
                            }
                            
                            if ( v_RValueChild.getIterator().hasNext() )
                            {
                                Object v_ForElement = v_RValueChild.getIterator().next();
                                v_Replaces.put(v_RCell.getValueName() ,v_RCell.getValueMethod().invokeForInstance(v_ForElement));
                                v_RValueChild.setIteratorIndex(v_RValueChild.getIteratorIndex() + 1);
                            }
                        }
                        else
                        {
                            v_Replaces.put(v_RCell.getValueName() ,v_RCell.getValueMethod().invokeForInstance(i_Datas));
                        }
                    }
                    
                    v_RValue.setValue(StringHelp.replaceAll(v_RCellG.getCellInfo() ,v_Replaces));
                }
                // 填充模式
                else
                {
                    RCell v_RCell = v_RCellG.get(0);
                    
                    if ( v_RCell.isFor() )
                    {
                        if ( v_RValue.getIterator() == null )
                        {
                            v_RValue.setIterator((Iterator<?>)v_RCell.getIteratorMethod()    .invokeForInstance(i_Datas));
                            v_RValue.setIteratorSize((Integer)v_RCell.getIteratorSizeMethod().invokeForInstance(i_Datas));
                        }
                        
                        if ( v_RValue.getIterator().hasNext() )
                        {
                            Object v_ForElement = v_RValue.getIterator().next();
                            if ( v_RCell.getNextRCell() != null )
                            {
                                v_RValue.setValue(v_ForElement);
                            }
                            else
                            {
                                v_RValue.setValue(v_RCell.getValueMethod().invokeForInstance(v_ForElement));
                            }
                            v_RValue.setIteratorIndex(v_RValue.getIteratorIndex() + 1);
                        }
                    }
                    else
                    {
                        v_RValue.setValue(v_RCell.getValueMethod().invokeForInstance(i_Datas));
                    }
                    
                    if ( v_RCell.getNextRCell() != null && v_RValue.getValue() != null )
                    {
                        if ( v_RValue.getNextRValue() == null )
                        {
                            v_RValue.setNextRValue(new RValue());
                        }
                        
                        if ( v_RCell.getNextRCell().isFor() )
                        {
                            if ( v_RValue.getNextRValue().getIterator() == null )
                            {
                                v_RValue.getNextRValue().setIterator((Iterator<?>)v_RCell.getNextRCell().getIteratorMethod()    .invokeForInstance(v_RValue.getValue()));
                                v_RValue.getNextRValue().setIteratorSize((Integer)v_RCell.getNextRCell().getIteratorSizeMethod().invokeForInstance(v_RValue.getValue()));
                            }
                            
                            if ( v_RValue.getNextRValue().getIterator().hasNext() )
                            {
                                Object v_ForElement = v_RValue.getNextRValue().getIterator().next();
                                v_RValue.getNextRValue().setValue(v_RCell.getNextRCell().getValueMethod().invokeForInstance(v_ForElement));
                                v_RValue.setValue(v_RValue.getNextRValue().getValue());
                                v_RValue.getNextRValue().setIteratorIndex(v_RValue.getNextRValue().getIteratorIndex() + 1);
                            }
                        }
                        else
                        {
                            v_RValue.getNextRValue().setValue(v_RCell.getNextRCell().getValueMethod().invokeForInstance(v_RValue.getValue()));
                        }
                    }
                }
            }
            catch (Exception exce)
            {
                exce.printStackTrace();
            }
        }
        else
        {
            String v_ValueName = this.valueNames.get(i_ValueName);
            
            if ( $ValueName_RowNo.equalsIgnoreCase(v_ValueName) )
            {
                v_RValue.setValue(String.valueOf(i_RSystemValue.getRowNo()));
            }
            else if ( $ValueName_RowIndex.equalsIgnoreCase(v_ValueName) )
            {
                v_RValue.setValue(String.valueOf(i_RSystemValue.getRowIndex()));
            }
            else if ( $ValueName_RowCount.equalsIgnoreCase(v_ValueName) )
            {
                v_RValue.setValue(String.valueOf(i_RSystemValue.getRowCount()));
            }
            else if ( $ValueName_RowSubtotalCount.equalsIgnoreCase(v_ValueName) )
            {
                v_RValue.setValue(String.valueOf(i_RSystemValue.getRowSubtotalCount()));
            }
            else if ( $ValueName_PageNo.equalsIgnoreCase(v_ValueName) )
            {
                v_RValue.setValue(String.valueOf(i_RSystemValue.getPageNo()));
            }
            else if ( $ValueName_PageSize.equalsIgnoreCase(v_ValueName) )
            {
                v_RValue.setValue(String.valueOf(i_RSystemValue.getPageSize()));
            }
        }
        
        return v_RValue;
    }
    
    
    
    /**
     * Excel转Java时，将Excel单位格中的数据映射并赋值给Java对象的属性
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-09
     * @version     v1.0
     *
     * @param i_ValueName  占位符名称
     * @param i_Value      Excel单位格中的数据
     * @param io_RowObj    Java对象
     * @return             映射赋值成功时返回true
     */
    public boolean setValue(String i_ValueName ,Object i_Value ,Object io_RowObj)
    {
        if ( Help.isNull(i_ValueName) )
        {
            return false;
        }
        
        RCellGroup v_RCellG = this.valueMethods.get(i_ValueName);
        if ( v_RCellG == null )
        {
            return false;
        }
        RCell v_RCell = v_RCellG.get(0);
        
        if ( null != v_RCell && null != v_RCell.getValueSetMethod() )
        {
            try
            {
                v_RCell.getValueSetMethod().invokeSetForInstance(io_RowObj ,i_Value);
                
                return true;
            }
            catch (Exception exce)
            {
                exce.printStackTrace();
            }
        }
        
        return false;
    }
    
    
    
    /**
     * 获取标题的总行数
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @return
     */
    public int getRowCountTitle()
    {
        return this.getRowCount(this.titleBeginRow ,this.titleEndRow);
    }
    
    
    
    /**
     * 获取分页页眉标题的总行数
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @return
     */
    public int getRowCountTitlePageHeader()
    {
        if ( this.perPageRowSize == null || this.perPageRowSize <= 0 )
        {
            return 0;
        }
        
        return this.getRowCount(this.titlePageHeaderBeginRow ,this.titlePageHeaderEndRow);
    }
    
    
    
    /**
     * 获取分页页脚标题的总行数
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-25
     * @version     v1.0
     *
     * @return
     */
    public int getRowCountTitlePageFooter()
    {
        if ( this.perPageRowSize == null || this.perPageRowSize <= 0 )
        {
            return 0;
        }
        
        return this.getRowCount(this.titlePageFooterBeginRow ,this.titlePageFooterEndRow);
    }
    
    
    
    /**
     * 获取数据的总行数
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @return
     */
    public int getRowCountData()
    {
        return this.getRowCount(this.dataBeginRow ,this.dataEndRow);
    }
    
    
    
    /**
     * 获取数据的总列数。用于Excel转为Java对象，并且this.direction=1的情况
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-05-08
     * @version     v1.0
     *
     * @return
     */
    public int getColCountData()
    {
        return this.getRowCount(this.dataBeginCol ,this.dataEndCol);
    }
    
    
    
    /**
     * 获取小计的总行数
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-27
     * @version     v1.0
     *
     * @return
     */
    public int getRowCountSubtotal()
    {
        return this.getRowCount(this.subtotalBeginRow ,this.subtotalEndRow);
    }
    
    
    
    /**
     * 获取合计的总行数
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @return
     */
    public int getRowCountTotal()
    {
        return this.getRowCount(this.totalBeginRow ,this.totalEndRow);
    }
    
    
    
    /**
     * 获取的总行数
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-17
     * @version     v1.0
     *
     * @param i_BeginRow  开始行号。下标从零开始
     * @param i_EndRow    结束行号。下标从零开始
     * @return            0表示不存在
     */
    public int getRowCount(Integer i_BeginRow ,Integer i_EndRow)
    {
        if ( null == i_BeginRow 
          || null == i_EndRow )
        {
            return 0;
        }
        
        if ( i_BeginRow.intValue() == i_EndRow.intValue() )
        {
            return 1;
        }
        
        return i_EndRow.intValue() - i_BeginRow.intValue() + 1;
    }
    
    
    
    /**
     * 添加自动行高的变量名称
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-11
     * @version     v1.0
     *
     * @param i_ValueName  变量名称
     */
    public void setAddAutoHeight(String i_ValueName)
    {
        if ( !Help.isNull(i_ValueName) )
        {
            this.autoHeights.put(this.valueSign + i_ValueName ,i_ValueName);
        }
    }
    
    
    
    /**
     * 添加自定义变量处理事件的监听者
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_Listener
     */
    public void addListener(ValueListener i_Listener)
    {
        if ( i_Listener == null )
        {
            throw new NullPointerException("ValueListener is null.");
        }
        
        if ( Help.isNull(i_Listener.getValueName()) )
        {
            throw new NullPointerException("ValueListener.getValueName() is null.");
        }
        
        this.valueListeners.put(this.getValueSign() + i_Listener.getValueName() ,i_Listener);
    }
    
    
    
    /**
     * 获取自定义变量处理事件的监听者
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_ValueName  变量名称
     * @return
     */
    public ValueListener getListener(String i_ValueName)
    {
        return this.valueListeners.get(i_ValueName);
    }
    
    
    
    /**
     * 触发所有工作表事件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-29
     * @version     v1.0
     * 
     * @param i_DataSheet     数据的工作表
     * @param i_Datas         本行对应的数据
     * @param i_RTemplate     模板
     * @param i_TemplateCell  模板单元格对象
     * @param i_RSystemValue  系统变量信息
     */
    public void fireSheetListener(Sheet i_DataSheet ,List<?> i_Datas ,RTemplate i_RTemplate ,RSystemValue i_RSystemValue)
    {
        for (SheetListener v_SheetListener : this.sheetListeners)
        {
            try
            {
                v_SheetListener.writeDatafinish(i_DataSheet ,i_Datas ,i_RTemplate ,i_RSystemValue);
            }
            catch (Exception exce)
            {
                exce.printStackTrace();
            }
        }
    }
    
    
    
    /**
     * 添加自定义工作表的监听事件的监听者
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-29
     * @version     v1.0
     *
     * @param i_Listener
     */
    public void addSheetListener(SheetListener i_Listener)
    {
        if ( i_Listener == null )
        {
            throw new NullPointerException("SheetListener is null.");
        }
        
        this.sheetListeners.add(i_Listener);
    }
    
    
    
    /**
     * 获取自定义工作表的监听事件的监听者
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-06-29
     * @version     v1.0
     *
     * @return
     */
    public List<SheetListener> getSheetListeners()
    {
        return this.sheetListeners;
    }
    
    
    
    /**
     * 获取：模板名称
     */
    public String getName()
    {
        return name;
    }

    
    /**
     * 设置：模板名称
     * 
     * @param name 
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    
    /**
     * 获取：Excel文件版本(1.xls  2.xlsx)
     */
    public synchronized String getExcelVersion()
    {
        if ( Help.isNull(this.excelVersion) )
        {
            this.excelVersion = this.excelFileName.substring(this.excelFileName.lastIndexOf(".") + 1);
        }
        
        return excelVersion;
    }

    
    /**
     * 设置：Excel文件版本(1.xls  2.xlsx)
     * 
     * @param excelVersion 
     */
    public synchronized void setExcelVersion(String excelVersion)
    {
        this.excelVersion = excelVersion;
    }


    /**
     * 获取：模板文件的名称(全路径+文件名称)
     */
    public String getExcelFileName()
    {
        return excelFileName;
    }

    
    /**
     * 设置：模板文件的名称(全路径+文件名称)
     * 
     * @param excelFileName 
     */
    public void setExcelFileName(String excelFileName)
    {
        this.excelFileName = excelFileName;
    }

    
    /**
     * 获取：报表模板对应的工作表索引位置（下标从零开始）
     */
    public Integer getSheetIndex()
    {
        return sheetIndex;
    }

    
    /**
     * 设置：报表模板对应的工作表索引位置（下标从零开始）
     * 
     * @param sheetIndex 
     */
    public void setSheetIndex(Integer sheetIndex)
    {
        this.sheetIndex = sheetIndex;
    }
    
    
    /**
     * 获取：Excel数据的方向。
     * 0：表示从左到右的方向，一行或多行为一个对象数据。此为默认值
     * 1：表示从上到下的方向，一列或多列为一个对象数据
     */
    public Integer getDirection()
    {
        return direction;
    }
    
    
    /**
     * 设置：Excel数据的方向。
     * 0：表示从左到右的方向，一行或多行为一个对象数据。此为默认值
     * 1：表示从上到下的方向，一列或多列为一个对象数据
     * 
     * @param direction 
     */
    public void setDirection(Integer direction)
    {
        this.direction = direction;
    }
    
    
    /**
     * 获取：报表标题占用一整页。默认为：false。即第一页的数据量按标题行数是动态的，第二页及其后是固定的数据量
     */
    public boolean isTitleUseOnePage()
    {
        return titleUseOnePage;
    }

    
    /**
     * 设置：报表标题占用一整页。默认为：false。即第一页的数据量按标题行数是动态的，第二页及其后是固定的数据量
     * 
     * @param titleUseOnePage 
     */
    public void setTitleUseOnePage(boolean titleUseOnePage)
    {
        this.titleUseOnePage = titleUseOnePage;
        
        if ( this.titleUseOnePage )
        {
            this.titleRatio = 0;
        }
        else
        {
            this.titleRatio = 1;
        }
    }

    
    /**
     * 获取：标题系数。当 titleUseOnePage=true 时，titleRatio=0 ，当 titleUseOnePage=false 时，titleRatio=1
     */
    public int getTitleRatio()
    {
        return titleRatio;
    }
    

    /**
     * 获取：报表标题的开始行号（包括此行）。下标从零开始
     */
    public Integer getTitleBeginRow()
    {
        return titleBeginRow;
    }

    
    /**
     * 设置：报表标题的开始行号（包括此行）。下标从零开始
     * 
     * @param i_TitleBeginRow 
     */
    public void setTitleBeginRow(Integer i_TitleBeginRow)
    {
        this.titleBeginRow = i_TitleBeginRow;
        this.titleEndRow   = i_TitleBeginRow;
    }

    
    /**
     * 获取：报表标题的结束行号（包括此行）。下标从零开始
     */
    public Integer getTitleEndRow()
    {
        return titleEndRow;
    }

    
    /**
     * 设置：报表标题的结束行号（包括此行）。下标从零开始
     * 
     * @param titleEndRow 
     */
    public void setTitleEndRow(Integer titleEndRow)
    {
        this.titleEndRow = titleEndRow;
    }

    
    /**
     * 获取：* 在什么位置(Excel的行号)上首次写入分页页眉。下标从零开始。默认为0 
     * 
     * 主要用于：首个分页页眉与第二行分页页眉样式不一样的情况。
     *          在此情况下，可将首个分页页眉与总标题合并后，当总标题写入。
     */
    public int getTitlePageHeaderFirstWriteByRow()
    {
        return titlePageHeaderFirstWriteByRow;
    }
    
    
    /**
     * 设置：* 在什么位置(Excel的行号)上首次写入分页页眉。下标从零开始。默认为0 
     * 
     * 主要用于：首个分页页眉与第二行分页页眉样式不一样的情况。
     *          在此情况下，可将首个分页页眉与总标题合并后，当总标题写入。
     * 
     * @param titlePageHeaderFirstWriteByRow 
     */
    public void setTitlePageHeaderFirstWriteByRow(int titlePageHeaderFirstWriteByRow)
    {
        this.titlePageHeaderFirstWriteByRow = titlePageHeaderFirstWriteByRow;
    }

    
    /**
     * 获取：通过 titlePageHeaderFirstWriteByRow 计算出来的首个分页页眉前已写入了多少行的真实数据
     */
    public int getTitlePageHeaderFirstWriteByRealDataCount()
    {
        return titlePageHeaderFirstWriteByRealDataCount;
    }

    
    /**
     * 获取：通过 titlePageHeaderFirstWriteByRow 计算出来的分页页眉系数。默认为：0
     */
    public int getTitlePageHeaderRate()
    {
        return titlePageHeaderRate;
    }
    

    /**
     * 获取：报表分页页眉的开始行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用
     */
    public Integer getTitlePageHeaderBeginRow()
    {
        return titlePageHeaderBeginRow;
    }

    
    /**
     * 设置：报表分页页眉的开始行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用
     * 
     * @param titlePageHeaderBeginRow 
     */
    public void setTitlePageHeaderBeginRow(Integer titlePageHeaderBeginRow)
    {
        this.titlePageHeaderBeginRow = titlePageHeaderBeginRow;
        this.titlePageHeaderEndRow   = titlePageHeaderBeginRow;
    }

    
    /**
     * 获取：报表分页页眉的结束行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用
     */
    public Integer getTitlePageHeaderEndRow()
    {
        return titlePageHeaderEndRow;
    }

    
    /**
     * 设置：报表分页页眉的结束行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用
     * 
     * @param titlePageHeaderEndRow 
     */
    public void setTitlePageHeaderEndRow(Integer titlePageHeaderEndRow)
    {
        this.titlePageHeaderEndRow = titlePageHeaderEndRow;
    }

    
    /**
     * 获取：报表分页页脚的开始行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用
     */
    public Integer getTitlePageFooterBeginRow()
    {
        return titlePageFooterBeginRow;
    }
    
    
    /**
     * 设置：报表分页页脚的开始行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用
     * 
     * @param titlePageFooterBeginRow 
     */
    public void setTitlePageFooterBeginRow(Integer titlePageFooterBeginRow)
    {
        this.titlePageFooterBeginRow = titlePageFooterBeginRow;
        this.titlePageFooterEndRow   = titlePageFooterBeginRow;
    }

    
    /**
     * 获取：报表分页页脚的结束行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用
     */
    public Integer getTitlePageFooterEndRow()
    {
        return titlePageFooterEndRow;
    }

    
    /**
     * 设置：报表分页页脚的结束行号（包括此行）。下标从零开始。配合perPageRowSize属性一同使用
     * 
     * @param titlePageFooterEndRow 
     */
    public void setTitlePageFooterEndRow(Integer titlePageFooterEndRow)
    {
        this.titlePageFooterEndRow = titlePageFooterEndRow;
    }


    /**
     * 获取：报表分页的每页显示的行记录数
     */
    public Integer getPerPageRowSize()
    {
        return perPageRowSize;
    }

    
    /**
     * 设置：报表分页的每页显示的行记录数
     * 
     * @param perPageRowSize 
     */
    public void setPerPageRowSize(Integer perPageRowSize)
    {
        if ( perPageRowSize != null )
        {
            this.perPageRowSize = Math.abs(perPageRowSize.intValue());
        }
        else
        {
            this.perPageRowSize = null;
        }
    }


    /**
     * 获取：报表数据的开始行号（包括此行）。下标从零开始
     */
    public Integer getDataBeginRow()
    {
        return dataBeginRow;
    }

    
    /**
     * 设置：报表数据的开始行号（包括此行）。下标从零开始
     * 
     * @param i_DataBeginRow 
     */
    public void setDataBeginRow(Integer i_DataBeginRow)
    {
        this.dataBeginRow = i_DataBeginRow;
        this.dataEndRow   = i_DataBeginRow;
    }

    
    /**
     * 获取：报表数据的结束行号（包括此行）。下标从零开始
     */
    public Integer getDataEndRow()
    {
        return dataEndRow;
    }

    
    /**
     * 设置：报表数据的结束行号（包括此行）。下标从零开始
     * 
     * @param dataEndRow 
     */
    public void setDataEndRow(Integer dataEndRow)
    {
        this.dataEndRow = dataEndRow;
    }
    
    
    /**
     * 获取：报表数据的开始列号（包括此列）。下标从零开始。用于Excel转为Java对象，并且this.direction=1的情况
     */
    public Integer getDataBeginCol()
    {
        return dataBeginCol;
    }


    /**
     * 设置：报表数据的开始列号（包括此列）。下标从零开始。用于Excel转为Java对象，并且this.direction=1的情况
     * 
     * @param dataBeginCol 
     */
    public void setDataBeginCol(Integer dataBeginCol)
    {
        this.dataBeginCol = dataBeginCol;
        this.dataEndCol   = dataBeginCol;
    }

    
    /**
     * 获取：报表数据的结束列号（包括此列）。下标从零开始。用于Excel转为Java对象，并且this.direction=1的情况
     */
    public Integer getDataEndCol()
    {
        return dataEndCol;
    }

    
    /**
     * 设置：报表数据的结束列号（包括此列）。下标从零开始。用于Excel转为Java对象，并且this.direction=1的情况
     * 
     * @param dataEndCol 
     */
    public void setDataEndCol(Integer dataEndCol)
    {
        this.dataEndCol = dataEndCol;
    }


    /**
     * 获取：报表小计的开始行号（包括此行）。下标从零开始
     */
    public Integer getSubtotalBeginRow()
    {
        return subtotalBeginRow;
    }

    
    /**
     * 设置：报表小计的开始行号（包括此行）。下标从零开始
     * 
     * @param subtotalBeginRow 
     */
    public void setSubtotalBeginRow(Integer i_SubtotalBeginRow)
    {
        this.subtotalBeginRow = i_SubtotalBeginRow;
        this.subtotalEndRow   = i_SubtotalBeginRow; 
    }

    
    /**
     * 获取：报表小计的结束行号（包括此行）。下标从零开始
     */
    public Integer getSubtotalEndRow()
    {
        return subtotalEndRow;
    }

    
    /**
     * 设置：报表小计的结束行号（包括此行）。下标从零开始
     * 
     * @param subtotalEndRow 
     */
    public void setSubtotalEndRow(Integer subtotalEndRow)
    {
        this.subtotalEndRow = subtotalEndRow;
    }
    
    
    /**
     * 获取：报表小计的输出位置（top：在明细数据之前输出小计；bottom）。默认是：bottom
     */
    public String getSubtotalPosition()
    {
        return subtotalPosition;
    }

    
    /**
     * 设置：报表小计的输出位置（top：在明细数据之前输出小计；bottom）。默认是：bottom
     * 
     * @param subtotalPosition 
     */
    public void setSubtotalPosition(String i_SubtotalPosition)
    {
        this.subtotalPosition = Help.NVL(i_SubtotalPosition ,$SubTotalPosition_Bottom);
        
        if ( !$SubTotalPosition_Top.equals(this.subtotalPosition) && !$SubTotalPosition_Bottom.equals(this.subtotalPosition) )
        {
            this.subtotalPosition = $SubTotalPosition_Bottom;
        }
    }


    /**
     * 获取：合计内容的开始行号（包括此行）。下标从零开始
     */
    public Integer getTotalBeginRow()
    {
        return totalBeginRow;
    }

    
    /**
     * 设置：合计内容的开始行号（包括此行）。下标从零开始
     * 
     * @param i_TotalBeginRow 
     */
    public void setTotalBeginRow(Integer i_TotalBeginRow)
    {
        this.totalBeginRow = i_TotalBeginRow;
        this.totalEndRow   = i_TotalBeginRow;
    }

    
    /**
     * 获取：合计内容的结束行号（包括此行）。下标从零开始
     */
    public Integer getTotalEndRow()
    {
        return totalEndRow;
    }

    
    /**
     * 设置：合计内容的结束行号（包括此行）。下标从零开始
     * 
     * @param totalEndRow 
     */
    public void setTotalEndRow(Integer totalEndRow)
    {
        this.totalEndRow = totalEndRow;
    }

    
    /**
     * 获取：报表数据的Java类型
     */
    public String getDataClass()
    {
        return dataClass;
    }

    
    /**
     * 设置：报表数据的Java类型
     * 
     * @param dataClass 
     */
    public void setDataClass(String dataClass)
    {
        this.dataClass = dataClass;
    }


    /**
     * 获取：值的标记。默认为一个冒号：":"
     */
    public synchronized String getValueSign()
    {
        return valueSign;
    }

    
    /**
     * 设置：值的标记。默认为一个冒号：":"
     * 
     * @param valueSign 
     */
    public synchronized void setValueSign(String valueSign)
    {
        this.valueSign = valueSign;
        
        this.valueNames.put(this.valueSign + $ValueName_RowNo            ,$ValueName_RowNo);
        this.valueNames.put(this.valueSign + $ValueName_RowIndex         ,$ValueName_RowIndex);
        this.valueNames.put(this.valueSign + $ValueName_RowCount         ,$ValueName_RowCount);
        this.valueNames.put(this.valueSign + $ValueName_RowSubtotalCount ,$ValueName_RowSubtotalCount);
        this.valueNames.put(this.valueSign + $ValueName_PageNo           ,$ValueName_PageNo);
        this.valueNames.put(this.valueSign + $ValueName_PageSize         ,$ValueName_PageSize);
    }
    
    
    
    /**
     * 获取：是否将整数显示为小数的形式。需Excel模板配合设置单元格的格式为：小数格式。
     * 
     *  Excel模板配合设置单元格的格式为：显示3位小数时
     *    1. 当为true 时，10会显示为 10.000
     *    2. 当为false时，10还是显示为 10
     * 
     * 默认为：false
     */
    public boolean getIsIntegerShowDecimal()
    {
        return isIntegerShowDecimal;
    }


    
    /**
     * 设置：是否将整数显示为小数的形式。需Excel模板配合设置单元格的格式为：小数格式。
     * 
     *  Excel模板配合设置单元格的格式为：显示3位小数时
     *    1. 当为true 时，10会显示为 10.000
     *    2. 当为false时，10还是显示为 10
     * 
     * 默认为：false
     * 
     * @param isIntegerShowDecimal 
     */
    public void setIsIntegerShowDecimal(boolean isIntegerShowDecimal)
    {
        this.isIntegerShowDecimal = isIntegerShowDecimal;
    }
    

    
    /**
     * 获取：是否添加Excel高级筛选功能。默认为：false
     */
    public boolean getIsExcelFilter()
    {
        return isExcelFilter;
    }
    

    
    /**
     * 设置：是否添加Excel高级筛选功能。默认为：false
     * 
     * @param isExcelFilter 
     */
    public void setIsExcelFilter(boolean isExcelFilter)
    {
        this.isExcelFilter = isExcelFilter;
    }



    /**
     * 获取：是要安全？还是要性能（默认为：性能）
     */
    public boolean getIsSafe()
    {
        return isSafe;
    }


    /**
     * 设置：是要安全？还是要性能（默认为：性能）
     * 
     * @param isSafe 
     */
    public void setIsSafe(boolean isSafe)
    {
        this.isSafe = isSafe;
    }

    
    /**
     * 获取：当为大数据量导出时，建议使用 SXSSFWorkbook
     * POI对excel的导出操作，一般只使用HSSFWorkbook以及SXSSFWorkbook，
     * HSSFWorkbook用来处理较少的数据量，
     * SXSSFWorkbook用来处理大数据量以及超大数据量的导出
     * 
     * 默认为：false
     */
    public boolean getIsBig()
    {
        return isBig;
    }

    
    /**
     * 设置：当为大数据量导出时，建议使用 SXSSFWorkbook
     * POI对excel的导出操作，一般只使用HSSFWorkbook以及SXSSFWorkbook，
     * HSSFWorkbook用来处理较少的数据量，
     * SXSSFWorkbook用来处理大数据量以及超大数据量的导出
     * 
     * 默认为：false
     * 
     * @param isBig 
     */
    public void setIsBig(boolean isBig)
    {
        this.isBig = isBig;
    }
    
    
    /**
     * 获取：当使用 SXSSFWorkbook 时，创建对象new SXSSFWorkbook(rowAccessWindowSize)入参。
     * 保持在内存中的行数，直到刷新为止
     */
    public Integer getRowAccessWindowSize()
    {
        return rowAccessWindowSize;
    }

    
    /**
     * 设置：当使用 SXSSFWorkbook 时，创建对象new SXSSFWorkbook(rowAccessWindowSize)入参。
     * 保持在内存中的行数，直到刷新为止
     * 
     * @param rowAccessWindowSize 
     */
    public void setRowAccessWindowSize(Integer rowAccessWindowSize)
    {
        this.rowAccessWindowSize = rowAccessWindowSize;
    }
    
    
    /**
     * 获取：打印分页模式
     *   1. auto     ：自动模式（默认的）。对于复杂报表，可能出现同一Excel在不同电脑上打印时，分页结果是有差异的。
     *   2. rowBreak ：按模板打印区域及输出数据量大小（即分页页数），添加多个分隔符分页。
     */
    public String getPageBreakMode()
    {
        return pageBreakMode;
    }

    
    /**
     * 设置：打印分页模式
     *   1. auto     ：自动模式（默认的）。对于复杂报表，可能出现同一Excel在不同电脑上打印时，分页结果是有差异的。
     *   2. rowBreak ：按模板打印区域及输出数据量大小（即分页页数），添加多个分隔符分页。
     * 
     * @param pageBreakMode 
     */
    public void setPageBreakMode(String pageBreakMode)
    {
        this.pageBreakMode = pageBreakMode;
    }


    @Override
    public int compareTo(RTemplate i_Other)
    {
        if ( i_Other == null )
        {
            return 1;
        }
        else if ( this == i_Other )
        {
            return 0;
        }
        else
        {
            if ( Help.isNull(this.name) || Help.isNull(this.excelFileName) )
            {
                return -1;
            }
            
            int v_Ret = this.name.compareTo(i_Other.getName());
            if ( v_Ret == 0 )
            {
                v_Ret = this.excelFileName.compareTo(i_Other.getExcelFileName());
            }
            return v_Ret;
        }
    }



    @Override
    public int hashCode()
    {
        int v_Ret = 0;
        
        if ( !Help.isNull(this.name) )
        {
            v_Ret = this.name.hashCode();
        }
        if ( !Help.isNull(this.excelFileName) )
        {
            v_Ret += this.excelFileName.hashCode() * 1000;
        }
        if ( v_Ret == 0 )
        {
            return super.hashCode();
        }
        
        return v_Ret;
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
        else if ( i_Other instanceof RTemplate )
        {
            boolean v_Ret = false;
            
            if ( !Help.isNull(this.name) )
            {
                v_Ret = this.name.equals(((RTemplate)i_Other).getName());
            }
            
            if ( !v_Ret )
            {
                return false;
            }
            
            if ( !Help.isNull(this.excelFileName) )
            {
                v_Ret = this.excelFileName.equals(((RTemplate)i_Other).getExcelFileName());
            }
            
            return v_Ret;
        }
        else
        {
            return false;
        }
    }



    @Override
    public String toString()
    {
        return Help.NVL(this.name ,Help.NVL(this.excelFileName));
    }
    
}
