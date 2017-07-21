/*
 * 文 件 名:  PartSprayRecordBean.java
 * 版    权:  Sunny Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  LHao
 * 修改时间:  2017-01-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package org.hy.common.report.junit.total03;

import java.util.List;


/**
 * 
 * 零件喷涂记录-Bean <功能详细描述>
 * 
 * @author LHao
 * @version [版本号, 2017-01-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PartSprayRecord implements Comparable<PartSprayRecord> {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = -5166202294198634244L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 路线单号
	 */
	private String batchNo;

	/**
	 * 零件代号
	 */
	private String itemCode;

	/**
	 * 零件名称
	 */
	private String partName;

	/**
	 * 设备代码
	 */
	private String deviceCode;

	/**
	 * 工序号
	 */
	private Integer processNo;

	/**
	 * 工序名称
	 */
	private String processName;

	/**
	 * 准备工时
	 */
	private String preparationTime;

	/**
	 * 加工工时
	 */
	private String workingHours;

	/**
	 * 焊接数量
	 */
	private Integer partCount;

	/**
	 * 焊工
	 */
	private String operator;

	/**
	 * 焊接时间
	 */
	private String operateTime;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 放弃人
	 */
	private String giveupPerson;

	/**
	 * 放弃时间
	 */
	private String giveupTime;

	/**
	 * 放弃标识,1=有效，0=无效
	 */
	private String giveupFlag;

	/**
	 * 扩展字段1
	 */
	private String extend1;

	/**
	 * 扩展字段2
	 */
	private String extend2;

	/**
	 * 扩展字段3
	 */
	private String extend3;


	/**
	 * 车间编号
	 */
	private String departCode;

	/**
	 * @Fields workIncome : 工时费用小计
	 */
	private Double workIncome;

	/**
	 * @Fields rate : 设备费率
	 */
	private Double rate;

	/**
	 * @Fields bs : 转换系数
	 */
	private Double bs;

	/**
	 * 工艺分类
	 */
	private String processClassify;

	/**
	 * 零件材质
	 */
	private String material;

	/**
	 * 工时小计
	 */
	private Double workIn;

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	/**
	 * @return 主键
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param 对id进行赋值
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return 路线单号
	 */
	public String getBatchNo() {
		return this.batchNo;
	}

	/**
	 * @param 对batchNo进行赋值
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return 零件代号
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * @param 对itemCode进行赋值
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return 零件名称
	 */
	public String getPartName() {
		return this.partName;
	}

	/**
	 * @param 对partName进行赋值
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}

	/**
	 * @return 设备代码
	 */
	public String getDeviceCode() {
		return this.deviceCode;
	}

	/**
	 * @param 对deviceCode进行赋值
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	/**
	 * @return 工序号
	 */
	public Integer getProcessNo() {
		return this.processNo;
	}

	/**
	 * @param 对processNo进行赋值
	 */
	public void setProcessNo(Integer processNo) {
		this.processNo = processNo;
	}

	/**
	 * @return 工序名称
	 */
	public String getProcessName() {
		return this.processName;
	}

	/**
	 * @param 对processName进行赋值
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * @return 准备工时
	 */
	public String getPreparationTime() {
		return this.preparationTime;
	}

	/**
	 * @param 对preparationTime进行赋值
	 */
	public void setPreparationTime(String preparationTime) {
		this.preparationTime = preparationTime;
	}

	/**
	 * @return 加工工时
	 */
	public String getWorkingHours() {
		return this.workingHours;
	}

	/**
	 * @param 对workingHours进行赋值
	 */
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	/**
	 * @return 焊接数量
	 */
	public Integer getPartCount() {
		return this.partCount;
	}

	/**
	 * @param 对partCount进行赋值
	 */
	public void setPartCount(Integer partCount) {
		this.partCount = partCount;
	}

	/**
	 * @return 焊工
	 */
	public String getOperator() {
		return this.operator;
	}

	/**
	 * @param 对operator进行赋值
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return 焊接时间
	 */
	public String getOperateTime() {
		return this.operateTime;
	}

	/**
	 * @param 对operateTime进行赋值
	 */
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * @return 备注
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * @param 对note进行赋值
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return 放弃人
	 */
	public String getGiveupPerson() {
		return this.giveupPerson;
	}

	/**
	 * @param 对giveupPerson进行赋值
	 */
	public void setGiveupPerson(String giveupPerson) {
		this.giveupPerson = giveupPerson;
	}

	/**
	 * @return 放弃时间
	 */
	public String getGiveupTime() {
		return this.giveupTime;
	}

	/**
	 * @param 对giveupTime进行赋值
	 */
	public void setGiveupTime(String giveupTime) {
		this.giveupTime = giveupTime;
	}

	/**
	 * @return 放弃标识,1=有效，0=无效
	 */
	public String getGiveupFlag() {
		return this.giveupFlag;
	}

	/**
	 * @param 对giveupFlag进行赋值
	 */
	public void setGiveupFlag(String giveupFlag) {
		this.giveupFlag = giveupFlag;
	}

	/**
	 * @return 扩展字段1
	 */
	public String getExtend1() {
		return this.extend1;
	}

	/**
	 * @param 对extend1进行赋值
	 */
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	/**
	 * @return 扩展字段2
	 */
	public String getExtend2() {
		return this.extend2;
	}

	/**
	 * @param 对extend2进行赋值
	 */
	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	/**
	 * @return 扩展字段3
	 */
	public String getExtend3() {
		return this.extend3;
	}

	/**
	 * @param 对extend3进行赋值
	 */
	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}



	public Double getWorkIncome() {
		return workIncome;
	}

	public void setWorkIncome(Double workIncome) {
		this.workIncome = workIncome;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getBs() {
		return bs;
	}

	public void setBs(Double bs) {
		this.bs = bs;
	}

	public String getProcessClassify() {
		return processClassify;
	}

	public void setProcessClassify(String processClassify) {
		this.processClassify = processClassify;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public int compareTo(PartSprayRecord o) {
		if (this.departCode.compareTo(o.getDepartCode()) > 0) {
			return 1;
		}
		if (this.departCode.compareTo(o.getDepartCode()) < 0) {
			return -1;
		}
		return 0;
	}

	public void setWorkIn(Double workIn) {
		this.workIn = workIn;
	}

	public Double getWorkIn() {

		return workIn;
	}

	@Override
	public String toString() {
		return "PartSprayRecord{" +
				"id=" + id +
				", batchNo='" + batchNo + '\'' +
				", itemCode='" + itemCode + '\'' +
				", partName='" + partName + '\'' +
				", deviceCode='" + deviceCode + '\'' +
				", processNo=" + processNo +
				", processName='" + processName + '\'' +
				", preparationTime='" + preparationTime + '\'' +
				", workingHours='" + workingHours + '\'' +
				", partCount=" + partCount +
				", operator='" + operator + '\'' +
				", operateTime='" + operateTime + '\'' +
				", note='" + note + '\'' +
				", giveupPerson='" + giveupPerson + '\'' +
				", giveupTime='" + giveupTime + '\'' +
				", giveupFlag='" + giveupFlag + '\'' +
				", extend1='" + extend1 + '\'' +
				", extend2='" + extend2 + '\'' +
				", extend3='" + extend3 + '\'' +
				", departCode='" + departCode + '\'' +
				", workIncome=" + workIncome +
				", rate=" + rate +
				", bs=" + bs +
				", processClassify='" + processClassify + '\'' +
				", material='" + material + '\'' +
				", workIn=" + workIn +
				'}';
	}
}
