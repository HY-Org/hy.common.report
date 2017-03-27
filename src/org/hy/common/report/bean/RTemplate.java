package org.hy.common.report.bean;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.hy.common.Help;
import org.hy.common.MethodReflect;
import org.hy.common.Return;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.event.ValueListener;





/**
 * 报表模板信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-03-15
 * @version     v1.0
 */
public class RTemplate implements Comparable<RTemplate>
{
    
    /** 系统固定变量名称：数据行号的变量名称。下标从1开始 */
    public final static String         $ValueName_RowNo        = "RowNo__";
    
    /** 系统固定变量名称：数据索引号的变量名称。下标从0开始 */
    public final static String         $ValueName_RowIndex     = "RowIndex__";
    
    /** 系统固定变量名称：数据总量的变量名称 */
    public final static String         $ValueName_RowCount     = "RowCount__";
    
    
    
    /** 模板名称 */
    private String                     name;
    
    /** Excel文件版本(1.xls  2.xlsx) */
    private String                     excelVersion;
    
    /** 模板文件的名称(全路径+文件名称) */
    private String                     excelFileName;
    
    /** 报表模板对应的工作表索引位置（下标从零开始） */
    private Integer                    sheetIndex;
    
    /** 报表标题的开始行号（包括此行）。下标从零开始 */
    private Integer                    titleBeginRow;
    
    /** 报表标题的结束行号（包括此行）。下标从零开始 */
    private Integer                    titleEndRow;
    
    /** 报表数据的开始行号（包括此行）。下标从零开始 */
    private Integer                    dataBeginRow;
    
    /** 报表数据的结束行号（包括此行）。下标从零开始 */
    private Integer                    dataEndRow;
    
    /** 合计内容的开始行号（包括此行）。下标从零开始 */
    private Integer                    totalBeginRow;
    
    /** 合计内容的结束行号（包括此行）。下标从零开始 */
    private Integer                    totalEndRow;
    
    /** 报表数据的Java类型 */
    private String                     dataClass;
    
    /** 值的标记。默认为一个冒号：":" */
    private String                     valueSign;

    
    
    /** 
     * 变量自定义处理事件
     * Map.key  为变量名称 
     */
    private Map<String ,ValueListener> valueListeners;
    
    
    
    /** 报表模板信息对应的工作表对象(一般只初始加载一次) */
    private Sheet                      templateSheet; 
    
    /** 解释的值的反射方法集合(一般只初始加载一次) */
    private Map<String ,RCell>         valueMethods;
    
    /** 按 this.valueSign 生成的系统变量名称 */
    private Map<String ,String>        valueNames;
    
    
    
    public RTemplate()
    {
        this.sheetIndex     = 0;
        this.templateSheet  = null;
        this.excelVersion   = null;
        this.valueMethods   = new LinkedHashMap<String ,RCell>();
        this.valueNames     = new Hashtable<String ,String>();
        this.valueListeners = new Hashtable<String ,ValueListener>();
        this.setValueSign(":");
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
        }
        
        return this.templateSheet;
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
            Map<String ,Object> v_ExcelDatas = ExcelHelp.readDatas(this.getTemplateSheet());
            List<String>        v_TempDatas  = Help.toListKeys(v_ExcelDatas);
            Class<?>            v_JavaClass  = Help.forName(this.dataClass);
            
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
                
                if ( v_Value.length() >= this.valueSign.length() + 1 && v_Value.startsWith(this.valueSign) )
                {
                    RCell     v_RCell     = new RCell();
                    String    v_ValueName = v_Value.substring(this.valueSign.length());
                    String [] v_Fors      = v_ValueName.split("\\[\\]");
                    
                    if ( v_Fors.length >= 2 )
                    {
                        MethodReflect v_ForMR       = new MethodReflect(v_JavaClass ,v_Fors[0] ,true ,MethodReflect.$NormType_Getter);
                        String        v_ForSizeName = "";
                        
                        if ( MethodReflect.isExtendImplement(v_ForMR.getReturnType() ,List.class) )
                        {
                            v_ForSizeName = v_Fors[0] + ".$size";
                            v_ValueName   = v_Fors[0] + ".$get(index)" + v_Fors[1];
                        }
                        else if ( MethodReflect.isExtendImplement(v_ForMR.getReturnType() ,Map.class) )
                        {
                            v_ForSizeName = v_Fors[0] + ".$size";
                            v_ValueName   = v_Fors[0] + ".$get(index)" + v_Fors[1];
                        }
                        else
                        {
                            v_ForSizeName = v_Fors[0] + ".$length";
                        }
                        
                        v_RCell.setForSizeMethod(new MethodReflect(v_JavaClass ,v_ForSizeName ,true ,MethodReflect.$NormType_Getter));
                    }
                    
                    v_RCell.setValueMethod(new MethodReflect(v_JavaClass ,v_ValueName ,true ,MethodReflect.$NormType_Getter));
                    this.valueMethods.put(v_Value ,v_RCell);
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
        return this.valueMethods.containsKey(i_ValueName) || this.valueNames.containsKey(i_ValueName);
    }
    
    
    
    /**
     * 通过变量名称反射出对应的数值
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-03-18
     * @version     v1.0
     *
     * @param i_ValueName  变量名称
     * @param i_Datas      数据
     * @param i_RowNo      数据行号。下标从 1 开始
     * @param i_RowCount   数据总量
     * @param i_ForIndex   小计循环索引。下标从 0 开始，即List.get()
     * @return             Return.paramObj  反射出来的数值
     *                     Return.paramInt  小计For循环的次数
     */
    public Return<Object> getValue(String i_ValueName ,Object i_Datas ,int i_RowNo ,int i_RowCount ,int i_ForIndex)
    {
        RCell          v_RCell = this.valueMethods.get(i_ValueName);
        Return<Object> v_Ret   = new Return<Object>(true).paramInt(0);
        
        if ( v_RCell != null )
        {
            try
            {
                if ( v_RCell.isFor() )
                {
                    v_Ret.paramInt = (int)v_RCell.getForSizeMethod().invokeForInstance(i_Datas);
                    
                    if ( v_Ret.paramInt >= 1 )
                    {
                        Map<String ,Object> v_Params = new HashMap<String ,Object>(1);
                        
                        v_Params.put(RCell.$Index ,i_ForIndex);

                        v_Ret.paramObj = v_RCell.getValueMethod().invokeForInstance(i_Datas ,v_Params);
                    }
                }
                else
                {
                    v_Ret.paramObj = v_RCell.getValueMethod().invokeForInstance(i_Datas);
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
                v_Ret.paramObj = String.valueOf(i_RowNo);
            }
            else if ( $ValueName_RowIndex.equalsIgnoreCase(v_ValueName) )
            {
                v_Ret.paramObj = String.valueOf(i_RowNo - 1);
            }
            else if ( $ValueName_RowCount.equalsIgnoreCase(v_ValueName) )
            {
                v_Ret.paramObj = String.valueOf(i_RowCount);
            }
        }
        
        return v_Ret;
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
        
        this.valueListeners.put(i_Listener.getValueName() ,i_Listener);
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
    public String getValueSign()
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
        
        this.valueNames.put(this.valueSign + $ValueName_RowNo    ,$ValueName_RowNo);
        this.valueNames.put(this.valueSign + $ValueName_RowIndex ,$ValueName_RowIndex);
        this.valueNames.put(this.valueSign + $ValueName_RowCount ,$ValueName_RowCount);
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
            if ( this.excelFileName == null )
            {
                return -1;
            }
            
            return this.excelFileName.compareTo(i_Other.getExcelFileName());
        }
    }



    @Override
    public int hashCode()
    {
        if ( this.excelFileName != null )
        {
            return this.excelFileName.hashCode();
        }
        
        return super.hashCode();
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
            if ( this.excelFileName == null )
            {
                return false;
            }
            
            return this.excelFileName.equals(((RTemplate)i_Other).getExcelFileName());
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
