/*
 * 文 件 名:  ProductPressureTestStatistics.java
 * 版    权:  Sunny Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  LHao
 * 修改时间:  2016年1月22日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package org.hy.common.report.junit.total02;

import java.util.List;


/**
 * 产品压力实验绩效统计
 * <功能详细描述>
 * 
 * @author  LHao
 * @version  [版本号, 2016年1月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductPressureTestStatistics
{

    /**
     * 操作者
     */
    private String operator;
    
    /**
     * 产品编号
     */
    private String productID;
    
    /**
     * 产品型号
     */
    private String productType;
    
    /**
     * 产品型号
     */
    private String standard;
    
    /**
     * 公称通径
     */
    private String diameterNominal;
    
    /**
     * 压力等级
     */
    private String pressureLevel;
    
    /**
     * 试验项目
     */
    private String testItem;
    
    /**
     * 试验次数
     */
    private String testTimes;
    
    /**
     * 试验方向
     */
    private String theDirection;
    
    /**
     * 一次试验次数
     */
    private String timesOfTheFirst; 
    
    /**
     * 一次试验工时
     */
    private String manHourOfTheFirst;
    
    /**
     * 一次试验工时合计
     */
    private String manHourTotalOfTheFirst;
    
    /**
     * 二次试验次数
     */
    private String timesOfTheSecond; 
    
    /**
     * 二次试验工时
     */
    private String manHourOfTheSecond;
    
    /**
     * 二次试验工时合计
     */
    private String manHourTotalOfTheSecond;
    
    /**
     * 装夹方式
     */
    private String clampingType;
    
    /**
     * 试验介质
     */
    private String testMedium;
    
    /**
     * 试验结果
     */
    private String testConclusion;
    
    /**
     * 交检类型
     */
    private String testObject;
    
    /**
     * 试验日期
     */
    private String testDate;
    
    /**
     * 试验结束日期
     */
    private String testEndDate;
    
    /**
     * 压力工时类型：分为单向、双向
     */
    private String manHoursType;
    
    /**
     *标准工时-单向
     */
    private float oneWayManHour;
    
    /**
     *标准工时-双向
     */
    private float twoWayManHour;
    
    private List<ProductPressureTestStatistics> proPressTestList;

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getProductID()
    {
        return productID;
    }

    public void setProductID(String productID)
    {
        this.productID = productID;
    }

    public String getDiameterNominal()
    {
        return diameterNominal;
    }

    public void setDiameterNominal(String diameterNominal)
    {
        this.diameterNominal = diameterNominal;
    }

    public String getPressureLevel()
    {
        return pressureLevel;
    }

    public void setPressureLevel(String pressureLevel)
    {
        this.pressureLevel = pressureLevel;
    }

    public String getTestItem()
    {
        return testItem;
    }

    public void setTestItem(String testItem)
    {
        this.testItem = testItem;
    }

    public String getTestTimes()
    {
        return testTimes;
    }

    public void setTestTimes(String testTimes)
    {
        this.testTimes = testTimes;
    }

    public String getTheDirection()
    {
        return theDirection;
    }

    public void setTheDirection(String theDirection)
    {
        this.theDirection = theDirection;
    }

    public String getTimesOfTheFirst()
    {
        return timesOfTheFirst;
    }

    public void setTimesOfTheFirst(String timesOfTheFirst)
    {
        this.timesOfTheFirst = timesOfTheFirst;
    }

    public String getManHourOfTheFirst()
    {
        return manHourOfTheFirst;
    }

    public void setManHourOfTheFirst(String manHourOfTheFirst)
    {
        this.manHourOfTheFirst = manHourOfTheFirst;
    }

    public String getManHourTotalOfTheFirst()
    {
        return manHourTotalOfTheFirst;
    }

    public void setManHourTotalOfTheFirst(String manHourTotalOfTheFirst)
    {
        this.manHourTotalOfTheFirst = manHourTotalOfTheFirst;
    }

    public String getTimesOfTheSecond()
    {
        return timesOfTheSecond;
    }

    public void setTimesOfTheSecond(String timesOfTheSecond)
    {
        this.timesOfTheSecond = timesOfTheSecond;
    }

    public String getManHourOfTheSecond()
    {
        return manHourOfTheSecond;
    }

    public void setManHourOfTheSecond(String manHourOfTheSecond)
    {
        this.manHourOfTheSecond = manHourOfTheSecond;
    }

    public String getManHourTotalOfTheSecond()
    {
        return manHourTotalOfTheSecond;
    }

    public void setManHourTotalOfTheSecond(String manHourTotalOfTheSecond)
    {
        this.manHourTotalOfTheSecond = manHourTotalOfTheSecond;
    }

    public String getClampingType()
    {
        return clampingType;
    }

    public void setClampingType(String clampingType)
    {
        this.clampingType = clampingType;
    }

    public String getTestMedium()
    {
        return testMedium;
    }

    public void setTestMedium(String testMedium)
    {
        this.testMedium = testMedium;
    }

    public String getTestConclusion()
    {
        return testConclusion;
    }

    public void setTestConclusion(String testConclusion)
    {
        this.testConclusion = testConclusion;
    }

    public String getTestObject()
    {
        return testObject;
    }

    public void setTestObject(String testObject)
    {
        this.testObject = testObject;
    }

    public String getTestDate()
    {
        return testDate;
    }

    public void setTestDate(String testDate)
    {
        this.testDate = testDate;
    }

    public String getTestEndDate()
    {
        return testEndDate;
    }

    public void setTestEndDate(String testEndDate)
    {
        this.testEndDate = testEndDate;
    }

    public List<ProductPressureTestStatistics> getProPressTestList()
    {
        return proPressTestList;
    }

    public void setProPressTestList(
            List<ProductPressureTestStatistics> proPressTestList)
    {
        this.proPressTestList = proPressTestList;
    }

    public String getManHoursType()
    {
        return manHoursType;
    }

    public void setManHoursType(String manHoursType)
    {
        this.manHoursType = manHoursType;
    }

    public float getOneWayManHour()
    {
        return oneWayManHour;
    }

    public void setOneWayManHour(float oneWayManHour)
    {
        this.oneWayManHour = oneWayManHour;
    }

    public float getTwoWayManHour()
    {
        return twoWayManHour;
    }

    public void setTwoWayManHour(float twoWayManHour)
    {
        this.twoWayManHour = twoWayManHour;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public String getStandard()
    {
        return standard;
    }

    public void setStandard(String standard)
    {
        this.standard = standard;
    }
}
