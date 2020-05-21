package org.hy.common.report;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
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
    
    /** 解释Excel公式时，用的分隔符 */
    private static final String   $ParserFormulaSplit = "@@@";
    
    /** 列举Excel公式中的关键字 */
    public static        String [] $Formulas = {"=" ,"+" ,"-" ,"*" ,"/"
                                              ,"(" ,")" ,"," ,"'" ,"\""
                                              ,":"
                                              ,"ABS("
                                              ,"ACCRINT("
                                              ,"ACCRINTM("
                                              ,"ACOS("
                                              ,"ACOSH("
                                              ,"ACOT("
                                              ,"ACOTH("
                                              ,"AGGREGATE("
                                              ,"ADDRESS("
                                              ,"AMORDEGRC("
                                              ,"AMORLINC("
                                              ,"AND("
                                              ,"ARABIC("
                                              ,"AREAS("
                                              ,"ASC("
                                              ,"ASIN("
                                              ,"ASINH("
                                              ,"ATAN("
                                              ,"ATAN2("
                                              ,"ATANH("
                                              ,"AVEDEV("
                                              ,"AVERAGE("
                                              ,"AVERAGEA("
                                              ,"AVERAGEIF("
                                              ,"AVERAGEIFS("
                                              ,"BAHTTEXT("
                                              ,"BASE("
                                              ,"BESSELI("
                                              ,"BESSELJ("
                                              ,"BESSELK("
                                              ,"BESSELY("
                                              ,"BETADIST("
                                              ,"BETA.DIST("
                                              ,"BETAINV("
                                              ,"BETA.INV("
                                              ,"BIN2DEC("
                                              ,"BIN2HEX("
                                              ,"BIN2OCT("
                                              ,"BINOMDIST("
                                              ,"BINOM.DIST("
                                              ,"BINOM.DIST.RANGE("
                                              ,"BINOM.INV("
                                              ,"BITAND("
                                              ,"BITLSHIFT("
                                              ,"BITOR("
                                              ,"BITRSHIFT("
                                              ,"BITXOR("
                                              ,"CALL("
                                              ,"CEILING("
                                              ,"CEILING.MATH("
                                              ,"CEILING.PRECISE("
                                              ,"CELL("
                                              ,"CHAR("
                                              ,"CHIDIST("
                                              ,"CHIINV("
                                              ,"CHITEST("
                                              ,"CHISQ.DIST("
                                              ,"CHISQ.DIST.RT("
                                              ,"CHISQ.INV("
                                              ,"CHISQ.INV.RT("
                                              ,"CHISQ.TEST("
                                              ,"CHOOSE("
                                              ,"CLEAN("
                                              ,"CODE("
                                              ,"COLUMN("
                                              ,"COLUMNS("
                                              ,"COMBIN("
                                              ,"COMBINA("
                                              ,"COMPLEX("
                                              ,"CONCAT("
                                              ,"CONCATENATE("
                                              ,"CONFIDENCE("
                                              ,"CONFIDENCE.NORM("
                                              ,"CONFIDENCE.T("
                                              ,"CONVERT("
                                              ,"CORREL("
                                              ,"COS("
                                              ,"COSH("
                                              ,"COT("
                                              ,"COTH("
                                              ,"COUNT("
                                              ,"COUNTA("
                                              ,"COUNTBLANK("
                                              ,"COUNTIF("
                                              ,"COUNTIFS("
                                              ,"COUPDAYBS("
                                              ,"COUPDAYS("
                                              ,"COUPDAYSNC("
                                              ,"COUPNCD("
                                              ,"COUPNUM("
                                              ,"COUPPCD("
                                              ,"COVAR("
                                              ,"COVARIANCE.P("
                                              ,"COVARIANCE.S("
                                              ,"CRITBINOM("
                                              ,"CSC("
                                              ,"CSCH("
                                              ,"CUBEKPIMEMBER("
                                              ,"CUBEMEMBER("
                                              ,"CUBEMEMBERPROPERTY("
                                              ,"CUBERANKEDMEMBER("
                                              ,"CUBESET("
                                              ,"CUBESETCOUNT("
                                              ,"CUBEVALUE("
                                              ,"CUMIPMT("
                                              ,"CUMPRINC("
                                              ,"DATE("
                                              ,"DATEDIF("
                                              ,"DATEVALUE("
                                              ,"DAVERAGE("
                                              ,"DAY("
                                              ,"DAYS("
                                              ,"DAYS360("
                                              ,"DB("
                                              ,"DBCS("
                                              ,"DCOUNT("
                                              ,"DCOUNTA("
                                              ,"DDB("
                                              ,"DEC2BIN("
                                              ,"DEC2HEX("
                                              ,"DEC2OCT("
                                              ,"DECIMAL("
                                              ,"DEGREES("
                                              ,"DELTA("
                                              ,"DEVSQ("
                                              ,"DGET("
                                              ,"DISC("
                                              ,"DMAX("
                                              ,"DMIN("
                                              ,"DOLLAR("
                                              ,"DOLLARDE("
                                              ,"DOLLARFR("
                                              ,"DPRODUCT("
                                              ,"DSTDEV("
                                              ,"DSTDEVP("
                                              ,"DSUM("
                                              ,"DURATION("
                                              ,"DVAR("
                                              ,"DVARP("
                                              ,"EDATE("
                                              ,"EFFECT("
                                              ,"ENCODEURL("
                                              ,"EOMONTH("
                                              ,"ERF("
                                              ,"ERF.PRECISE("
                                              ,"ERFC("
                                              ,"ERFC.PRECISE("
                                              ,"ERROR.TYPE("
                                              ,"EUROCONVERT("
                                              ,"EVEN("
                                              ,"EXACT("
                                              ,"EXP("
                                              ,"EXPON.DIST("
                                              ,"EXPONDIST("
                                              ,"FACT("
                                              ,"FACTDOUBLE("
                                              ,"FALSE("
                                              ,"F.DIST("
                                              ,"FDIST("
                                              ,"F.DIST.RT("
                                              ,"FILTER("
                                              ,"FILTERXML("
                                              ,"FIND("
                                              ,"FINDB("
                                              ,"F.INV("
                                              ,"F.INV.RT("
                                              ,"FINV("
                                              ,"FISHER("
                                              ,"FISHERINV("
                                              ,"FIXED("
                                              ,"FLOOR("
                                              ,"FLOOR.MATH("
                                              ,"FLOOR.PRECISE("
                                              ,"FORECAST("
                                              ,"FORECAST.ETS("
                                              ,"FORECAST.ETS.CONFINT("
                                              ,"FORECAST.ETS.SEASONALITY("
                                              ,"FORECAST.ETS.STAT("
                                              ,"FORECAST.LINEAR("
                                              ,"FORMULATEXT("
                                              ,"FREQUENCY("
                                              ,"F.TEST("
                                              ,"FTEST("
                                              ,"FV("
                                              ,"FVSCHEDULE("
                                              ,"GAMMA("
                                              ,"GAMMA.DIST("
                                              ,"GAMMADIST("
                                              ,"GAMMA.INV("
                                              ,"GAMMAINV("
                                              ,"GAMMALN("
                                              ,"GAMMALN.PRECISE("
                                              ,"GAUSS("
                                              ,"GCD("
                                              ,"GEOMEAN("
                                              ,"GESTEP("
                                              ,"GETPIVOTDATA("
                                              ,"GROWTH("
                                              ,"HARMEAN("
                                              ,"HEX2BIN("
                                              ,"HEX2DEC("
                                              ,"HEX2OCT("
                                              ,"HLOOKUP("
                                              ,"HOUR("
                                              ,"HYPERLINK("
                                              ,"HYPGEOM.DIST("
                                              ,"HYPGEOMDIST("
                                              ,"IF("
                                              ,"IFERROR("
                                              ,"IFNA("
                                              ,"IFS("
                                              ,"IMABS("
                                              ,"IMAGINARY("
                                              ,"IMARGUMENT("
                                              ,"IMCONJUGATE("
                                              ,"IMCOS("
                                              ,"IMCOSH("
                                              ,"IMCOT("
                                              ,"IMCSC("
                                              ,"IMCSCH("
                                              ,"IMDIV("
                                              ,"IMEXP("
                                              ,"IMLN("
                                              ,"IMLOG10("
                                              ,"IMLOG2("
                                              ,"IMPOWER("
                                              ,"IMPRODUCT("
                                              ,"IMREAL("
                                              ,"IMSEC("
                                              ,"IMSECH("
                                              ,"IMSIN("
                                              ,"IMSINH("
                                              ,"IMSQRT("
                                              ,"IMSUB("
                                              ,"IMSUM("
                                              ,"IMTAN("
                                              ,"INDEX("
                                              ,"INDIRECT("
                                              ,"INFO("
                                              ,"INT("
                                              ,"INTERCEPT("
                                              ,"INTRATE("
                                              ,"IPMT("
                                              ,"IRR("
                                              ,"ISBLANK("
                                              ,"ISERR("
                                              ,"ISERROR("
                                              ,"ISEVEN("
                                              ,"ISFORMULA("
                                              ,"ISLOGICAL("
                                              ,"ISNA("
                                              ,"ISNONTEXT("
                                              ,"ISNUMBER("
                                              ,"ISODD("
                                              ,"ISREF("
                                              ,"ISTEXT("
                                              ,"ISO.CEILING("
                                              ,"ISOWEEKNUM("
                                              ,"ISPMT("
                                              ,"JIS("
                                              ,"KURT("
                                              ,"LARGE("
                                              ,"LCM("
                                              ,"LEFT、LEFTB("
                                              ,"LEN、LENB("
                                              ,"LINEST("
                                              ,"LN("
                                              ,"LOG("
                                              ,"LOG10("
                                              ,"LOGEST("
                                              ,"LOGINV("
                                              ,"LOGNORM.DIST("
                                              ,"LOGNORMDIST("
                                              ,"LOGNORM.INV("
                                              ,"LOOKUP("
                                              ,"LOWER("
                                              ,"MATCH("
                                              ,"MAX("
                                              ,"MAXA("
                                              ,"MAXIFS("
                                              ,"MDETERM("
                                              ,"MDURATION("
                                              ,"MEDIAN("
                                              ,"MID("
                                              ,"MIDB("
                                              ,"MIN("
                                              ,"MINIFS("
                                              ,"MINA("
                                              ,"MINUTE("
                                              ,"MINVERSE("
                                              ,"MIRR("
                                              ,"MMULT("
                                              ,"MOD("
                                              ,"MODE("
                                              ,"MODE.MULT("
                                              ,"MODE.SNGL("
                                              ,"MONTH("
                                              ,"MROUND("
                                              ,"MULTINOMIAL("
                                              ,"MUNIT("
                                              ,"N("
                                              ,"NA("
                                              ,"NEGBINOM.DIST("
                                              ,"NEGBINOMDIST("
                                              ,"NETWORKDAYS("
                                              ,"NETWORKDAYS.INTL("
                                              ,"NOMINAL("
                                              ,"NORM.DIST("
                                              ,"NORMDIST("
                                              ,"NORMINV("
                                              ,"NORM.INV("
                                              ,"NORM.S.DIST("
                                              ,"NORMSDIST("
                                              ,"NORM.S.INV("
                                              ,"NORMSINV("
                                              ,"NOT("
                                              ,"NOW("
                                              ,"NPER("
                                              ,"NPV("
                                              ,"NUMBERVALUE("
                                              ,"OCT2BIN("
                                              ,"OCT2DEC("
                                              ,"OCT2HEX("
                                              ,"ODD("
                                              ,"ODDFPRICE("
                                              ,"ODDFYIELD("
                                              ,"ODDLPRICE("
                                              ,"ODDLYIELD("
                                              ,"OFFSET("
                                              ,"OR("
                                              ,"PDURATION("
                                              ,"PEARSON("
                                              ,"PERCENTILE.EXC("
                                              ,"PERCENTILE.INC("
                                              ,"PERCENTILE("
                                              ,"PERCENTRANK.EXC("
                                              ,"PERCENTRANK.INC("
                                              ,"PERCENTRANK("
                                              ,"PERMUT("
                                              ,"PERMUTATIONA("
                                              ,"PHI("
                                              ,"PHONETIC("
                                              ,"PI("
                                              ,"PMT("
                                              ,"POISSON.DIST("
                                              ,"POISSON("
                                              ,"POWER("
                                              ,"PPMT("
                                              ,"PRICE("
                                              ,"PRICEDISC("
                                              ,"PRICEMAT("
                                              ,"PROB("
                                              ,"PRODUCT("
                                              ,"PROPER("
                                              ,"PV("
                                              ,"QUARTILE("
                                              ,"QUARTILE.EXC("
                                              ,"QUARTILE.INC("
                                              ,"QUOTIENT("
                                              ,"RADIANS("
                                              ,"RAND("
                                              ,"RANDARRAY("
                                              ,"RANDBETWEEN("
                                              ,"RANK.AVG("
                                              ,"RANK.EQ("
                                              ,"RANK("
                                              ,"RATE("
                                              ,"RECEIVED("
                                              ,"REGISTER.ID("
                                              ,"REPLACE、REPLACEB("
                                              ,"REPT("
                                              ,"RIGHT、RIGHTB("
                                              ,"ROMAN("
                                              ,"ROUND("
                                              ,"ROUNDDOWN("
                                              ,"ROUNDUP("
                                              ,"ROW("
                                              ,"ROWS("
                                              ,"RRI("
                                              ,"RSQ("
                                              ,"RTD("
                                              ,"SEARCH("
                                              ,"SEARCHB("
                                              ,"SEC("
                                              ,"SECH("
                                              ,"SECOND("
                                              ,"SEQUENCE("
                                              ,"SERIESSUM("
                                              ,"SHEET("
                                              ,"SHEETS("
                                              ,"SIGN("
                                              ,"SIN("
                                              ,"SINH("
                                              ,"SKEW("
                                              ,"SKEW.P("
                                              ,"SLN("
                                              ,"SLOPE("
                                              ,"SMALL("
                                              ,"SORT("
                                              ,"SORTBY("
                                              ,"SQRT("
                                              ,"SQRTPI("
                                              ,"STANDARDIZE("
                                              ,"STDEV("
                                              ,"STDEV.P("
                                              ,"STDEV.S("
                                              ,"STDEVA("
                                              ,"STDEVP("
                                              ,"STDEVPA("
                                              ,"STEYX("
                                              ,"SUBSTITUTE("
                                              ,"SUBTOTAL("
                                              ,"SUM("
                                              ,"SUMIF("
                                              ,"SUMIFS("
                                              ,"SUMPRODUCT("
                                              ,"SUMSQ("
                                              ,"SUMX2MY2("
                                              ,"SUMX2PY2("
                                              ,"SUMXMY2("
                                              ,"SWITCH("
                                              ,"SYD("
                                              ,"T("
                                              ,"TAN("
                                              ,"TANH("
                                              ,"TBILLEQ("
                                              ,"TBILLPRICE("
                                              ,"TBILLYIELD("
                                              ,"T.DIST("
                                              ,"T.DIST.2T("
                                              ,"T.DIST.RT("
                                              ,"TDIST("
                                              ,"TEXT("
                                              ,"TEXTJOIN("
                                              ,"TIME("
                                              ,"TIMEVALUE("
                                              ,"T.INV("
                                              ,"T.INV.2T("
                                              ,"TINV("
                                              ,"TODAY("
                                              ,"TRANSPOSE("
                                              ,"TREND("
                                              ,"TRIM("
                                              ,"TRIMMEAN("
                                              ,"TRUE("
                                              ,"TRUNC("
                                              ,"T.TEST("
                                              ,"TTEST("
                                              ,"TYPE("
                                              ,"UNICHAR("
                                              ,"UNICODE("
                                              ,"UNIQUE("
                                              ,"UPPER("
                                              ,"VALUE("
                                              ,"VAR("
                                              ,"VAR.P("
                                              ,"VAR.S("
                                              ,"VARA("
                                              ,"VARP("
                                              ,"VARPA("
                                              ,"VDB("
                                              ,"VLOOKUP("
                                              ,"WEBSERVICE("
                                              ,"WEEKDAY("
                                              ,"WEEKNUM("
                                              ,"WEIBULL("
                                              ,"WEIBULL.DIST("
                                              ,"WORKDAY("
                                              ,"WORKDAY.INTL("
                                              ,"XIRR("
                                              ,"XLOOKUP("
                                              ,"XMATCH("
                                              ,"XNPV("
                                              ,"XOR("
                                              ,"YEAR("
                                              ,"YEARFRAC("
                                              ,"YIELD("
                                              ,"YIELDDISC("
                                              ,"YIELDMAT("
                                              ,"Z.TEST("
                                              ,"ZTEST("};
    
    
    
    // 为防止误替换，对$Formulas重新排序：按长字符的排名在前，再按自然字符倒排。
    static 
    {
        for (int i=0; i<$Formulas.length; i++)
        {
            $Formulas[i] = (char)($Formulas[i].length() + 64) + $ParserFormulaSplit + $Formulas[i];
        }
        
        $Formulas = Help.toReverse($Formulas).toArray(new String[] {});
        
        for (int i=0; i<$Formulas.length; i++)
        {
            $Formulas[i] = $Formulas[i].split($ParserFormulaSplit)[1];
        }
    }
    
    
    
    /**
     * 计算Excel公式在偏移量（偏移多少行、偏移多少列）后新的Excel公式。
     * 
     * Excel公式中涉及到的所有单元格ID均将偏移。
     * 
     * 如，=  A1  + B1 偏移1行为：   =  A2  + B2
     * 如，= $A1  + B1 偏移1列为：   = $A1  + C1
     * 如，= $A$1 + B1 偏移1行1列为：= $A$1 + C2
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-21
     * @version     v1.0
     *
     * @param i_FromCell   从哪个单元格的公式
     * @param i_ToCell     计算出目标单元格的公式
     * @return
     */
    public static String calcFormulaOffset(Cell i_FromCell ,Cell i_ToCell)
    {
        return calcFormulaOffset(i_FromCell.getCellFormula() ,i_ToCell.getRowIndex() - i_FromCell.getRowIndex() ,i_ToCell.getColumnIndex() - i_FromCell.getColumnIndex());
    }
    
    
    
    /**
     * 计算Excel公式在偏移量（偏移多少行、偏移多少列）后新的Excel公式。
     * 
     * Excel公式中涉及到的所有单元格ID均将偏移。
     * 
     * 如，=  A1  + B1 偏移1行为：   =  A2  + B2
     * 如，= $A1  + B1 偏移1列为：   = $A1  + C1
     * 如，= $A$1 + B1 偏移1行1列为：= $A$1 + C2
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-21
     * @version     v1.0
     *
     * @param i_Formula      Excle公式。
     * @param i_OffsetRow    偏移多少行。零值，表示不偏移；负值向i_CellExcelID上方偏移；正值向i_CellExcelID下方偏移。
     * @param i_OffsetCol    偏移多少列。零值，表示不偏移；负值向i_CellExcelID左方偏移；正值向i_CellExcelID右方偏移。
     * @return
     */
    public static String calcFormulaOffset(String i_Formula ,int i_OffsetRow ,int i_OffsetCol)
    {
        if ( Help.isNull(i_Formula) )
        {
            return i_Formula;
        }
        
        String    v_Formula        = i_Formula.trim().toUpperCase();
        String [] v_CellExcelIDOld = parserFormula(v_Formula);
        if ( Help.isNull(v_CellExcelIDOld) )
        {
            return i_Formula;
        }
        
        String [] v_CellExcelIDPK  = new String[v_CellExcelIDOld.length];  // 防止多次替换时的误替换
        String [] v_CellExcelIDNew = new String[v_CellExcelIDOld.length];  // 偏移后的单元格ID
        
        for (int i=0; i<v_CellExcelIDOld.length; i++)
        {
            if ( v_CellExcelIDOld[i].startsWith("$") )
            {
                v_CellExcelIDPK[i]  = v_CellExcelIDOld[i].substring(0 ,2) + $ParserFormulaSplit + v_CellExcelIDOld[i].substring(2);
            }
            else
            {
                v_CellExcelIDPK[i]  = v_CellExcelIDOld[i].substring(0 ,1) + $ParserFormulaSplit + v_CellExcelIDOld[i].substring(1);
            }
            
            v_CellExcelIDNew[i] = calcCellOffset(v_CellExcelIDOld[i] ,i_OffsetRow ,i_OffsetCol);
        }
        
        v_Formula = StringHelp.replaceAll(v_Formula ,v_CellExcelIDOld ,v_CellExcelIDPK);
        v_Formula = StringHelp.replaceAll(v_Formula ,v_CellExcelIDPK  ,v_CellExcelIDNew);
        
        return v_Formula;
    }
    
    
    
    /**
     * 解释公式，将公式中的单元格ID独立提取出来。
     * 
     * 1. 会去除重复的单元格ID
     * 2. 会去除绝对行、绝对列定位的单元格ID
     * 3. 保留只绝对行 或 只绝对列定位的单格ID，去除绝对行及绝对列的单元格ID
     * 
     * @author      ZhengWei(HY)
     * @createDate  2020-05-20
     * @version     v1.0
     *
     * @param i_Formula   Excle公式
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
            return i_CellExcelID;
        }
        if ( StringHelp.getCount(i_CellExcelID ,"\\$") >= 2 )
        {
            // 绝对行、绝对列是不能偏移的。
            return i_CellExcelID;
        }
        
        RCell v_RCell = cellIDtoJava(i_CellExcelID);
        
        if ( v_RCell == null )
        {
            return i_CellExcelID;
        }
        
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
        
        String v_CellExcelID = StringHelp.replaceAll(i_CellExcelID.trim().toUpperCase() ,"$" ,"");
        String v_RowName     = StringHelp.replaceAll(v_CellExcelID ,$A_TO_Z ,new String [] {""});
        
        if ( Help.isNull(v_RowName) || !Help.isNumber(v_RowName) || StringHelp.isContains(v_RowName ,"." ,"-" ," ") )
        {
            return null;
        }
        
        int    v_RowNo   = Integer.parseInt(v_RowName);
        String v_ColName = StringHelp.replaceAll(v_CellExcelID ,v_RowNo + "" ,"");
        
        if ( v_ColName.length() <= 0 || v_ColName.length() > 3 )
        {
            return null;
        }
        
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
