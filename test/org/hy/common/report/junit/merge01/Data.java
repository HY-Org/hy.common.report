package org.hy.common.report.junit.merge01;


import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 控制阀报价单的实体类
 * @Author: chengyuanfeng
 * @CrateDate: 2020/1/17
 */
public class Data  {

    private String modelID;                     //项目ID
    private String projectCode;                 //项目编号
    private String xmmc;                        //项目名称
    private String priceState;                  //报价结果
    private String quoteNumber;                 //报价单号
    private String quoteQuantity;               //报价数量
    private String quoteMoney;                  //报价金额
    private String quoteRemark;                 //报价说明
    private String quoteDutyUserName;           //报价人员
    private String quoteDate;                   //报价日期
    private String quoteDateTerm;               //报价有效期
    private String khmc;                        //询价单位
    private String askDate;
    private String askDepartment;               //询价网点
    private String isShowResult;                //是否查看过报价结果
    private String outPrice;                    //外购部件价格
    private String selfPrice;                   //自制部件价格
    private String sumPrice;                    //价格合计
    private String version;                     //版本
    private List<DataControlValve> controlValves = new ArrayList<>();   //控制阀报价单
    private List<DataPart> parts = new ArrayList<DataPart>();     //所有部件集合
    private List<DataPart> outParts = new ArrayList<DataPart>();  //外购件集合
    private List<DataPart> selfParts = new ArrayList<DataPart>(); //自制件集合

    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getPriceState() {
        return priceState;
    }

    public void setPriceState(String priceState) {
        this.priceState = priceState;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getQuoteQuantity() {
        return quoteQuantity;
    }

    public void setQuoteQuantity(String quoteQuantity) {
        this.quoteQuantity = quoteQuantity;
    }

    public String getQuoteMoney() {
        return quoteMoney;
    }

    public void setQuoteMoney(String quoteMoney) {
        this.quoteMoney = quoteMoney;
    }

    public String getQuoteRemark() {
        return quoteRemark;
    }

    public void setQuoteRemark(String quoteRemark) {
        this.quoteRemark = quoteRemark;
    }

    public String getQuoteDutyUserName() {
        return quoteDutyUserName;
    }

    public void setQuoteDutyUserName(String quoteDutyUserName) {
        this.quoteDutyUserName = quoteDutyUserName;
    }

    public String getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(String quoteDate) {
        this.quoteDate = quoteDate;
    }

    public String getQuoteDateTerm() {
        return quoteDateTerm;
    }

    public void setQuoteDateTerm(String quoteDateTerm) {
        this.quoteDateTerm = quoteDateTerm;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getAskDate() {
        return askDate;
    }

    public void setAskDate(String askDate) {
        this.askDate = askDate;
    }

    public String getAskDepartment() {
        return askDepartment;
    }

    public void setAskDepartment(String askDepartment) {
        this.askDepartment = askDepartment;
    }

    public String getIsShowResult() {
        return isShowResult;
    }

    public void setIsShowResult(String isShowResult) {
        this.isShowResult = isShowResult;
    }

    public String getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(String outPrice) {
        this.outPrice = outPrice;
    }

    public String getSelfPrice() {
        return selfPrice;
    }

    public void setSelfPrice(String selfPrice) {
        this.selfPrice = selfPrice;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<DataControlValve> getControlValves() {
        return controlValves;
    }

    public void setControlValves(List<DataControlValve> controlValves) {
        this.controlValves = controlValves;
    }

    public List<DataPart> getParts() {
        return parts;
    }

    public void setParts(List<DataPart> parts) {
        this.parts = parts;
    }

    public List<DataPart> getOutParts() {
        return outParts;
    }

    public void setOutParts(List<DataPart> outParts) {
        this.outParts = outParts;
    }

    public List<DataPart> getSelfParts() {
        return selfParts;
    }

    public void setSelfParts(List<DataPart> selfParts) {
        this.selfParts = selfParts;
    }
}
