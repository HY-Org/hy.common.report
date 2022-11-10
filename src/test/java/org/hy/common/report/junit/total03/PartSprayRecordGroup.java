/*
 * 文 件 名:  PartSprayRecordGroup.java
 * 版    权:  Sunny Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Zq
 * 修改时间:  2017-07-20
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package org.hy.common.report.junit.total03;


import java.util.List;

/**
 * 
 * 零件喷涂记录导出excel-Bean <功能详细描述>
 * 
 * @author Zq
 * @version [版本号, 2017-07-20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PartSprayRecordGroup{

	private String monthDate;

	private String beginDate;

	private String endDate;

	private String exportTime;

	private String departName;

	private String operator;

	private String unit;

	private List<PartSprayRecord> list;

	private Integer order;

	private String batchNoSum;

	private String itemCodeSum;

	private String partCountSum;

	private String preSum;

	private String workSum;

	private String workIn;

	private String workIncome;

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExportTime() {
		return exportTime;
	}

	public void setExportTime(String exportTime) {
		this.exportTime = exportTime;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<PartSprayRecord> getList() {
		return list;
	}

	public void setList(List<PartSprayRecord> list) {
		this.list = list;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getBatchNoSum() {
		return batchNoSum;
	}

	public void setBatchNoSum(String batchNoSum) {
		this.batchNoSum = batchNoSum;
	}

	public String getItemCodeSum() {
		return itemCodeSum;
	}

	public void setItemCodeSum(String itemCodeSum) {
		this.itemCodeSum = itemCodeSum;
	}

	public String getPartCountSum() {
		return partCountSum;
	}

	public void setPartCountSum(String partCountSum) {
		this.partCountSum = partCountSum;
	}

	public String getPreSum() {
		return preSum;
	}

	public void setPreSum(String preSum) {
		this.preSum = preSum;
	}

	public String getWorkSum() {
		return workSum;
	}

	public void setWorkSum(String workSum) {
		this.workSum = workSum;
	}

	public String getWorkIn() {
		return workIn;
	}

	public void setWorkIn(String workIn) {
		this.workIn = workIn;
	}

	public String getWorkIncome() {
		return workIncome;
	}

	public void setWorkIncome(String workIncome) {
		this.workIncome = workIncome;
	}

	@Override
	public String toString() {
		return "PartSprayRecordGroup{" +
				"monthDate='" + monthDate + '\'' +
				", beginDate='" + beginDate + '\'' +
				", endDate='" + endDate + '\'' +
				", exportTime='" + exportTime + '\'' +
				", departName='" + departName + '\'' +
				", operator='" + operator + '\'' +
				", unit='" + unit + '\'' +
				", list=" + list +
				", order=" + order +
				", batchNoSum='" + batchNoSum + '\'' +
				", itemCodeSum='" + itemCodeSum + '\'' +
				", partCountSum='" + partCountSum + '\'' +
				", preSum='" + preSum + '\'' +
				", workSum='" + workSum + '\'' +
				", workIn='" + workIn + '\'' +
				", workIncome='" + workIncome + '\'' +
				'}';
	}
}
