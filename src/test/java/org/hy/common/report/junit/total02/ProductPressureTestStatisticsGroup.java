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
public class ProductPressureTestStatisticsGroup
{
    /**
     * 产品数量
     */
    private int productCount;

    /**
     * 第一次试验次数
     */
    private String firstTestNum;

    /**
     * 委托试验次数
     */
    private String otherTestNum;

    /**
     * 工时合计
     */
    private String workhourTotal;

    /**
     * 委托工时合计
     */
    private String otherWorkhourTotal;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 导出时间
     */
    private String exportTime;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 产品编号
     */
    private String productNo;

    /**
     * 产品型号
     */
    private String productModel;

    private String pressureLevel;

    private String standard;

    private String testItem;

    /**
     * 试验次数
     */
    private String testTimes;

    /**
     * 试验总数
     */
    private String testTotalNum;


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
     * 公称通径
     */
    private String diameterNominal;

    /**
     * 试验方向
     */
    private String theDirection;

    public String getExportTime() {
        return exportTime;
    }

    public void setExportTime(String exportTime) {
        this.exportTime = exportTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private List<ProductPressureTestStatistics> list;

    public String getOtherWorkhourTotal() {
        return otherWorkhourTotal;
    }

    public void setOtherWorkhourTotal(String otherWorkhourTotal) {
        this.otherWorkhourTotal = otherWorkhourTotal;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getFirstTestNum() {
        return firstTestNum;
    }

    public void setFirstTestNum(String firstTestNum) {
        this.firstTestNum = firstTestNum;
    }

    public String getOtherTestNum() {
        return otherTestNum;
    }

    public void setOtherTestNum(String otherTestNum) {
        this.otherTestNum = otherTestNum;
    }

    public String getWorkhourTotal() {
        return workhourTotal;
    }

    public void setWorkhourTotal(String workhourTotal) {
        this.workhourTotal = workhourTotal;
    }

    public List<ProductPressureTestStatistics> getList() {
        return list;
    }

    public void setList(List<ProductPressureTestStatistics> list) {
        this.list = list;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getTestTimes() {
        return testTimes;
    }

    public void setTestTimes(String testTimes) {
        this.testTimes = testTimes;
    }

    public String getTheDirection() {
        return theDirection;
    }

    public void setTheDirection(String theDirection) {
        this.theDirection = theDirection;
    }

    public String getTimesOfTheFirst() {
        return timesOfTheFirst;
    }

    public void setTimesOfTheFirst(String timesOfTheFirst) {
        this.timesOfTheFirst = timesOfTheFirst;
    }

    public String getManHourOfTheFirst() {
        return manHourOfTheFirst;
    }

    public void setManHourOfTheFirst(String manHourOfTheFirst) {
        this.manHourOfTheFirst = manHourOfTheFirst;
    }

    public String getManHourTotalOfTheFirst() {
        return manHourTotalOfTheFirst;
    }

    public void setManHourTotalOfTheFirst(String manHourTotalOfTheFirst) {
        this.manHourTotalOfTheFirst = manHourTotalOfTheFirst;
    }

    public String getTimesOfTheSecond() {
        return timesOfTheSecond;
    }

    public void setTimesOfTheSecond(String timesOfTheSecond) {
        this.timesOfTheSecond = timesOfTheSecond;
    }

    public String getManHourOfTheSecond() {
        return manHourOfTheSecond;
    }

    public void setManHourOfTheSecond(String manHourOfTheSecond) {
        this.manHourOfTheSecond = manHourOfTheSecond;
    }

    public String getManHourTotalOfTheSecond() {
        return manHourTotalOfTheSecond;
    }

    public void setManHourTotalOfTheSecond(String manHourTotalOfTheSecond) {
        this.manHourTotalOfTheSecond = manHourTotalOfTheSecond;
    }

    public String getDiameterNominal() {
        return diameterNominal;
    }

    public void setDiameterNominal(String diameterNominal) {
        this.diameterNominal = diameterNominal;
    }

    public String getPressureLevel() {
        return pressureLevel;
    }

    public void setPressureLevel(String pressureLevel) {
        this.pressureLevel = pressureLevel;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getTestItem() {
        return testItem;
    }

    public void setTestItem(String testItem) {
        this.testItem = testItem;
    }

    public String getTestTotalNum() {
        return testTotalNum;
    }

    public void setTestTotalNum(String testTotalNum) {
        this.testTotalNum = testTotalNum;
    }
}
