package org.hy.common.report.junit.readInteger;


public class Data  {
    //项目编号
    private String projectID;
    //项目名称
    private String projectName;
    //选型编号
    private String modelID;
    //唯一code
    private String onlyCode;
    //管道参数ID
    private String tranmissionID;
    //介质参数ID
    private String mediumID;
    //工艺参数ID
    private String technologicalID;
    //阀门推荐id
    private String valveRecommendID;
    //用户要求ID
    private String customerRequireID;
    //合同modelID
    private String contractdetailID;
    //产品code
    private String productCode;
    //当前操作人姓名
    private String curUserName;
    //当前操作人ID
    private String curUserID;
    //最后操作时间
    private String curOperTime;
    //最后操作时间
    private String finalOperationtime;
    //位号
    private String tagNo;
    //数量
    private String quantity;
    //用途
    private String server;
    //管道编号
    private String lineNo;
    //管道材质
    private String lineMaterial;
    //上游管道口径
    private String upTranmissionSize;
    //下游管道口径
    private String downTranmissionSize;
    //上游管道外径
    private String upPipeSize;
    //下游管道外径
    private String downPipeSize;
    //上游管道壁厚
    private String upPipeThickness;
    //下游管道壁厚
    private String downPipeThickness;
    //上游管道内径
    private String upTranmissionDiameter;
    //下游管道内径
    private String downTranmissionDiameter;
    //设计温度
    private String designTemp;
    //设计压力
    private String designP;
    //流体名称
    private String fluidName;
    //流体状态
    private String fluidState;
    //温度单位
    private String TEMP_Unit1;
    //入口最大温度
    private String condition1T1;
    //入口正常温度
    private String condition2T1;
    //入口最小温度
    private String condition3T1;
    //出口最大温度
    private String condition1T2;
    //出口正常温度
    private String condition2T2;
    //出口最小温度
    private String condition3T2;
    //流量单位
    private String flowRateUnit;
    //最大流量
    private String flowRate1;
    //正常流量
    private String flowRate2;
    //最小流量
    private String flowRate3;
    //最大阀前压
    private String condition1P1;
    //正常阀前压
    private String condition2P1;
    //最小阀前压
    private String condition3P1;
    //最大阀后压
    private String condition1P2;
    //正常阀后压
    private String condition2P2;
    //最小阀后压
    private String condition3P2;
    //最大压差;
    private String condition1P3;
    //正常压差
    private String condition2P3;
    //最小压差
    private String condition3P3;
    //关闭压差
    private String shutOff_P;
    //压力单位
    private String preUnit;
    //比重
    private String specificGravity;
    //密度单位
    private String standardDensityUnit;
    //标准密度
    private String standardDensity;
    //最大操作密度
    private String operaDensityMax;
    //正常操作密度
    private String operaDensityNor;
    //最小操作密度
    private String operaDensityMin;
    //操作密度单位
    private String operaDensity_Unit;
    //气体分子量
    private String mol_WT;

    //动力粘度
    private String dynamicViscosity;
    //临界压力
    private String criticalP;
    //汽化压力
    private String vaporP;
    //临界温度
    private String criticaTemp;
    //临界温度单位
    private String criticalTemperatureUnit;

    //管道口径与阀门口径相等  1 相等；2 不相等
    private String pipeOrValveIsEqulas;

    //管道单位标识inch（英制）metric(米制)
    private String unitSelect;

    //阀门型式
    private String valveType;

    //用户要求部分数据(不存在Excel中)
    private String valveForm;
    private String flowCharacteristic;
    private String leakLevel;
    private String PLevel;
    private String velocityValue;
    private String openingValue;
    private String valveTypeSign;
    private String actuType;
    private String actuFucntion;
    private String actuExpiredchain;
    private String ratedTravel;
    private String hasPositioner;
    private String hasElectroValve;
    private String hasTravelSwitch;
    private String sealingStructure;
    private String hasPositionerCustom;
    private String hasJunction;
    private String orderNum;
    //----------------------------------选型详细参数
    //产品系列
    private String CPXL;
    //产品型号
    private String CPXH;
    //公称通径;
    private String DN;
    //公称压力
    private String YLDJ;
    //法兰形式
    private String FLXS;
    //泄漏等级
    private String XLDJ;
    //流量特性
    private String LLTX;
    //额定cv;
    private String EDCV;
    //额定行程;
    private String EDXC;
    //阀体材质
    private String FTCZ;
    //阀芯材质
    private String FXCZ;
    //阀座材质
    private String FZCZ;
    //阀杆材质
    private String FGCZ;
    //波纹管材质
    private String BWGCZ;
    //上盖型式
    private String FGXS;
    //填料
    private String TL;
    //执行机构驱动方式(电动、气动)
    private String driveMode;
    //故障阀位
    private String ZXJGQYGZFW;
    //作用型式（区分电动气动）
    private String ZXJGZYXS;
    //机构类型（区分电动气动）
    private String ZXJGXS;
    //机构型号（区分电动气动）
    private String ZXJGXH;
    //供气压力
    private String ZXJGGQYL;
    //弹簧范围
    private String ZXJGTHFW;
    //手轮机构（区分电动气动)
    private String ZXJGSLJG;
    //定位器类型
    private String DWQLX;
    //定位器型号
    private String DWQXH;
    //输入信号
    private String DWQSRXH;
    //定位器供气压力
    private String DWQGQYL;
    //定位器防护等级
    private String DWQFHDJ;
    //定位器防爆等级
    private String DWQFBDJ;
    //定位器气源接口
    private String DWQQYJKCC;
    //定位器电气接口
    private String DWQDQJKCC;
    //减压阀
    private String JYFXH;
    //电磁阀型号
    private String DCFXH;
    //电磁阀电压
    private String DCFDY;
    //电磁阀防爆
    private String DCFFBDJ;
    //电磁阀气源接口
    private String DCFQYJKCC;
    //电磁阀电气接口
    private String DCFDQJKCC;
    //电磁阀结构形式
    private String DCFJGXS;
    //电磁阀数量
    private String DCFSL;
    //开关型号
    private String XWKGXH;
    //开关数量
    private String XWKGSL;
    //开关电气接口
    private String XWKGDQJKCC;
    //开关防爆
    private String XWKGFBDJ;
    //三通气控
    private String STQK;
    //五通气控
    private String WTQK;
    //加速器
    private String JSQ;
    //保位阀
    private String BWF;
    //备注
    private String remark;
    //禁铜
    private String JT;
    //禁水
    private String JS;
    //禁油
    private String JY;
    //禁石墨
    private String JSM;
    //禁橡胶
    private String JXJ;
    //双重填料
    private String SCTL;
    //NACE0103
    private String NACE1;
    //NACE0175
    private String NACE2;
    //开启时间
    private String KQSJ;
    //关闭时间
    private String GBSJ;
    //法兰栓母垫片
    private String FLSMDP;
    //变径管
    private String BJG;

    //法兰类型
    private String FLLX;
    //法兰材质
    private String FLCZ;
    //螺栓材质
    private String LSCZ;
    //螺母材质
    private String LMCZ;
    //管道材质
    private String materialQualityAlias;

    private String pipeDataSign;

    //浪涌保护器1
    private String LYBHQ1;

    private String LYBHQ1SL;
    //浪涌保护器2
    private String LYBHQ2;

    private String LYBHQ2SL;
    //电缆密封接头
    private String DLMFJT;

    private String DLMFJTSL;

    private String productName;
    //入口气化压力
    private String vapourPressure;
    //入口气化压力单位
    private String rkqhPressUint;
    //入口粘度
    private String viscosity;
    //入口粘度单位
    private String viscosityUnits;


    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public String getOnlyCode() {
        return onlyCode;
    }

    public void setOnlyCode(String onlyCode) {
        this.onlyCode = onlyCode;
    }

    public String getTranmissionID() {
        return tranmissionID;
    }

    public void setTranmissionID(String tranmissionID) {
        this.tranmissionID = tranmissionID;
    }

    public String getMediumID() {
        return mediumID;
    }

    public void setMediumID(String mediumID) {
        this.mediumID = mediumID;
    }

    public String getTechnologicalID() {
        return technologicalID;
    }

    public void setTechnologicalID(String technologicalID) {
        this.technologicalID = technologicalID;
    }

    public String getValveRecommendID() {
        return valveRecommendID;
    }

    public void setValveRecommendID(String valveRecommendID) {
        this.valveRecommendID = valveRecommendID;
    }

    public String getCustomerRequireID() {
        return customerRequireID;
    }

    public void setCustomerRequireID(String customerRequireID) {
        this.customerRequireID = customerRequireID;
    }

    public String getContractdetailID() {
        return contractdetailID;
    }

    public void setContractdetailID(String contractdetailID) {
        this.contractdetailID = contractdetailID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCurUserName() {
        return curUserName;
    }

    public void setCurUserName(String curUserName) {
        this.curUserName = curUserName;
    }

    public String getCurUserID() {
        return curUserID;
    }

    public void setCurUserID(String curUserID) {
        this.curUserID = curUserID;
    }

    public String getCurOperTime() {
        return curOperTime;
    }

    public void setCurOperTime(String curOperTime) {
        this.curOperTime = curOperTime;
    }

    public String getFinalOperationtime() {
        return finalOperationtime;
    }

    public void setFinalOperationtime(String finalOperationtime) {
        this.finalOperationtime = finalOperationtime;
    }

    public String getTagNo() {
        return tagNo;
    }

    public void setTagNo(String tagNo) {
        this.tagNo = tagNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getLineMaterial() {
        return lineMaterial;
    }

    public void setLineMaterial(String lineMaterial) {
        this.lineMaterial = lineMaterial;
    }

    public String getUpTranmissionSize() {
        return upTranmissionSize;
    }

    public void setUpTranmissionSize(String upTranmissionSize) {
        this.upTranmissionSize = upTranmissionSize;
    }

    public String getDownTranmissionSize() {
        return downTranmissionSize;
    }

    public void setDownTranmissionSize(String downTranmissionSize) {
        this.downTranmissionSize = downTranmissionSize;
    }

    public String getUpPipeSize() {
        return upPipeSize;
    }

    public void setUpPipeSize(String upPipeSize) {
        this.upPipeSize = upPipeSize;
    }

    public String getDownPipeSize() {
        return downPipeSize;
    }

    public void setDownPipeSize(String downPipeSize) {
        this.downPipeSize = downPipeSize;
    }

    public String getUpPipeThickness() {
        return upPipeThickness;
    }

    public void setUpPipeThickness(String upPipeThickness) {
        this.upPipeThickness = upPipeThickness;
    }

    public String getDownPipeThickness() {
        return downPipeThickness;
    }

    public void setDownPipeThickness(String downPipeThickness) {
        this.downPipeThickness = downPipeThickness;
    }

    public String getUpTranmissionDiameter() {
        return upTranmissionDiameter;
    }

    public void setUpTranmissionDiameter(String upTranmissionDiameter) {
        this.upTranmissionDiameter = upTranmissionDiameter;
    }

    public String getDownTranmissionDiameter() {
        return downTranmissionDiameter;
    }

    public void setDownTranmissionDiameter(String downTranmissionDiameter) {
        this.downTranmissionDiameter = downTranmissionDiameter;
    }

    public String getDesignTemp() {
        return designTemp;
    }

    public void setDesignTemp(String designTemp) {
        this.designTemp = designTemp;
    }

    public String getDesignP() {
        return designP;
    }

    public void setDesignP(String designP) {
        this.designP = designP;
    }

    public String getFluidName() {
        return fluidName;
    }

    public void setFluidName(String fluidName) {
        this.fluidName = fluidName;
    }

    public String getFluidState() {
        return fluidState;
    }

    public void setFluidState(String fluidState) {
        this.fluidState = fluidState;
    }

    public String getTEMP_Unit1() {
        return TEMP_Unit1;
    }

    public void setTEMP_Unit1(String TEMP_Unit1) {
        this.TEMP_Unit1 = TEMP_Unit1;
    }

    public String getCondition1T1() {
        return condition1T1;
    }

    public void setCondition1T1(String condition1T1) {
        this.condition1T1 = condition1T1;
    }

    public String getCondition2T1() {
        return condition2T1;
    }

    public void setCondition2T1(String condition2T1) {
        this.condition2T1 = condition2T1;
    }

    public String getCondition3T1() {
        return condition3T1;
    }

    public void setCondition3T1(String condition3T1) {
        this.condition3T1 = condition3T1;
    }

    public String getCondition1T2() {
        return condition1T2;
    }

    public void setCondition1T2(String condition1T2) {
        this.condition1T2 = condition1T2;
    }

    public String getCondition2T2() {
        return condition2T2;
    }

    public void setCondition2T2(String condition2T2) {
        this.condition2T2 = condition2T2;
    }

    public String getCondition3T2() {
        return condition3T2;
    }

    public void setCondition3T2(String condition3T2) {
        this.condition3T2 = condition3T2;
    }

    public String getFlowRateUnit() {
        return flowRateUnit;
    }

    public void setFlowRateUnit(String flowRateUnit) {
        this.flowRateUnit = flowRateUnit;
    }

    public String getFlowRate1() {
        return flowRate1;
    }

    public void setFlowRate1(String flowRate1) {
        this.flowRate1 = flowRate1;
    }

    public String getFlowRate2() {
        return flowRate2;
    }

    public void setFlowRate2(String flowRate2) {
        this.flowRate2 = flowRate2;
    }

    public String getFlowRate3() {
        return flowRate3;
    }

    public void setFlowRate3(String flowRate3) {
        this.flowRate3 = flowRate3;
    }

    public String getCondition1P1() {
        return condition1P1;
    }

    public void setCondition1P1(String condition1P1) {
        this.condition1P1 = condition1P1;
    }

    public String getCondition2P1() {
        return condition2P1;
    }

    public void setCondition2P1(String condition2P1) {
        this.condition2P1 = condition2P1;
    }

    public String getCondition3P1() {
        return condition3P1;
    }

    public void setCondition3P1(String condition3P1) {
        this.condition3P1 = condition3P1;
    }

    public String getCondition1P2() {
        return condition1P2;
    }

    public void setCondition1P2(String condition1P2) {
        this.condition1P2 = condition1P2;
    }

    public String getCondition2P2() {
        return condition2P2;
    }

    public void setCondition2P2(String condition2P2) {
        this.condition2P2 = condition2P2;
    }

    public String getCondition3P2() {
        return condition3P2;
    }

    public void setCondition3P2(String condition3P2) {
        this.condition3P2 = condition3P2;
    }

    public String getCondition1P3() {
        return condition1P3;
    }

    public void setCondition1P3(String condition1P3) {
        this.condition1P3 = condition1P3;
    }

    public String getCondition2P3() {
        return condition2P3;
    }

    public void setCondition2P3(String condition2P3) {
        this.condition2P3 = condition2P3;
    }

    public String getCondition3P3() {
        return condition3P3;
    }

    public void setCondition3P3(String condition3P3) {
        this.condition3P3 = condition3P3;
    }

    public String getShutOff_P() {
        return shutOff_P;
    }

    public void setShutOff_P(String shutOff_P) {
        this.shutOff_P = shutOff_P;
    }

    public String getPreUnit() {
        return preUnit;
    }

    public void setPreUnit(String preUnit) {
        this.preUnit = preUnit;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(String specificGravity) {
        this.specificGravity = specificGravity;
    }

    public String getStandardDensity() {
        return standardDensity;
    }

    public void setStandardDensity(String standardDensity) {
        this.standardDensity = standardDensity;
    }

    public String getOperaDensityMax() {
        return operaDensityMax;
    }

    public void setOperaDensityMax(String operaDensityMax) {
        this.operaDensityMax = operaDensityMax;
    }

    public String getOperaDensityNor() {
        return operaDensityNor;
    }

    public void setOperaDensityNor(String operaDensityNor) {
        this.operaDensityNor = operaDensityNor;
    }

    public String getOperaDensityMin() {
        return operaDensityMin;
    }

    public void setOperaDensityMin(String operaDensityMin) {
        this.operaDensityMin = operaDensityMin;
    }

    public String getOperaDensity_Unit() {
        return operaDensity_Unit;
    }

    public void setOperaDensity_Unit(String operaDensity_Unit) {
        this.operaDensity_Unit = operaDensity_Unit;
    }

    public String getMol_WT() {
        return mol_WT;
    }

    public void setMol_WT(String mol_WT) {
        this.mol_WT = mol_WT;
    }

    public String getDynamicViscosity() {
        return dynamicViscosity;
    }

    public void setDynamicViscosity(String dynamicViscosity) {
        this.dynamicViscosity = dynamicViscosity;
    }

    public String getCriticalP() {
        return criticalP;
    }

    public void setCriticalP(String criticalP) {
        this.criticalP = criticalP;
    }

    public String getVaporP() {
        return vaporP;
    }

    public void setVaporP(String vaporP) {
        this.vaporP = vaporP;
    }

    public String getCriticaTemp() {
        return criticaTemp;
    }

    public void setCriticaTemp(String criticaTemp) {
        this.criticaTemp = criticaTemp;
    }

    public String getCriticalTemperatureUnit() {
        return criticalTemperatureUnit;
    }

    public void setCriticalTemperatureUnit(String criticalTemperatureUnit) {
        this.criticalTemperatureUnit = criticalTemperatureUnit;
    }

    public String getPipeOrValveIsEqulas() {
        return pipeOrValveIsEqulas;
    }

    public void setPipeOrValveIsEqulas(String pipeOrValveIsEqulas) {
        this.pipeOrValveIsEqulas = pipeOrValveIsEqulas;
    }

    public String getUnitSelect() {
        return unitSelect;
    }

    public void setUnitSelect(String unitSelect) {
        this.unitSelect = unitSelect;
    }

    public String getValveType() {
        return valveType;
    }

    public void setValveType(String valveType) {
        this.valveType = valveType;
    }

    public String getValveForm() {
        return valveForm;
    }

    public void setValveForm(String valveForm) {
        this.valveForm = valveForm;
    }

    public String getFlowCharacteristic() {
        return flowCharacteristic;
    }

    public void setFlowCharacteristic(String flowCharacteristic) {
        this.flowCharacteristic = flowCharacteristic;
    }

    public String getLeakLevel() {
        return leakLevel;
    }

    public void setLeakLevel(String leakLevel) {
        this.leakLevel = leakLevel;
    }

    public String getPLevel() {
        return PLevel;
    }

    public void setPLevel(String PLevel) {
        this.PLevel = PLevel;
    }

    public String getVelocityValue() {
        return velocityValue;
    }

    public void setVelocityValue(String velocityValue) {
        this.velocityValue = velocityValue;
    }

    public String getOpeningValue() {
        return openingValue;
    }

    public void setOpeningValue(String openingValue) {
        this.openingValue = openingValue;
    }

    public String getValveTypeSign() {
        return valveTypeSign;
    }

    public void setValveTypeSign(String valveTypeSign) {
        this.valveTypeSign = valveTypeSign;
    }

    public String getActuType() {
        return actuType;
    }

    public void setActuType(String actuType) {
        this.actuType = actuType;
    }

    public String getActuFucntion() {
        return actuFucntion;
    }

    public void setActuFucntion(String actuFucntion) {
        this.actuFucntion = actuFucntion;
    }

    public String getActuExpiredchain() {
        return actuExpiredchain;
    }

    public void setActuExpiredchain(String actuExpiredchain) {
        this.actuExpiredchain = actuExpiredchain;
    }

    public String getRatedTravel() {
        return ratedTravel;
    }

    public void setRatedTravel(String ratedTravel) {
        this.ratedTravel = ratedTravel;
    }

    public String getHasPositioner() {
        return hasPositioner;
    }

    public void setHasPositioner(String hasPositioner) {
        this.hasPositioner = hasPositioner;
    }

    public String getHasElectroValve() {
        return hasElectroValve;
    }

    public void setHasElectroValve(String hasElectroValve) {
        this.hasElectroValve = hasElectroValve;
    }

    public String getHasTravelSwitch() {
        return hasTravelSwitch;
    }

    public void setHasTravelSwitch(String hasTravelSwitch) {
        this.hasTravelSwitch = hasTravelSwitch;
    }

    public String getSealingStructure() {
        return sealingStructure;
    }

    public void setSealingStructure(String sealingStructure) {
        this.sealingStructure = sealingStructure;
    }

    public String getHasPositionerCustom() {
        return hasPositionerCustom;
    }

    public void setHasPositionerCustom(String hasPositionerCustom) {
        this.hasPositionerCustom = hasPositionerCustom;
    }

    public String getHasJunction() {
        return hasJunction;
    }

    public void setHasJunction(String hasJunction) {
        this.hasJunction = hasJunction;
    }

    public String getCPXL() {
        return CPXL;
    }

    public void setCPXL(String CPXL) {
        this.CPXL = CPXL;
    }

    public String getCPXH() {
        return CPXH;
    }

    public void setCPXH(String CPXH) {
        this.CPXH = CPXH;
    }

    public String getDN() {
        return DN;
    }

    public void setDN(String DN) {
        this.DN = DN;
    }

    public String getYLDJ() {
        return YLDJ;
    }

    public void setYLDJ(String YLDJ) {
        this.YLDJ = YLDJ;
    }

    public String getFLXS() {
        return FLXS;
    }

    public void setFLXS(String FLXS) {
        this.FLXS = FLXS;
    }

    public String getXLDJ() {
        return XLDJ;
    }

    public void setXLDJ(String XLDJ) {
        this.XLDJ = XLDJ;
    }

    public String getLLTX() {
        return LLTX;
    }

    public void setLLTX(String LLTX) {
        this.LLTX = LLTX;
    }

    public String getEDCV() {
        return EDCV;
    }

    public void setEDCV(String EDCV) {
        this.EDCV = EDCV;
    }

    public String getEDXC() { return EDXC; }

    public void setEDXC(String EDXC) {
        this.EDXC = EDXC;
    }

    public String getFTCZ() {
        return FTCZ;
    }

    public void setFTCZ(String FTCZ) {
        this.FTCZ = FTCZ;
    }

    public String getFXCZ() {
        return FXCZ;
    }

    public void setFXCZ(String FXCZ) {
        this.FXCZ = FXCZ;
    }

    public String getFZCZ() {
        return FZCZ;
    }

    public void setFZCZ(String FZCZ) {
        this.FZCZ = FZCZ;
    }

    public String getFGCZ() {
        return FGCZ;
    }

    public void setFGCZ(String FGCZ) {
        this.FGCZ = FGCZ;
    }

    public String getBWGCZ() {
        return BWGCZ;
    }

    public void setBWGCZ(String BWGCZ) {
        this.BWGCZ = BWGCZ;
    }

    public String getFGXS() {
        return FGXS;
    }

    public void setFGXS(String FGXS) {
        this.FGXS = FGXS;
    }

    public String getTL() {
        return TL;
    }

    public void setTL(String TL) {
        this.TL = TL;
    }

    public String getDriveMode() {
        return driveMode;
    }

    public void setDriveMode(String driveMode) {
        this.driveMode = driveMode;
    }

    public String getZXJGQYGZFW() {
        return ZXJGQYGZFW;
    }

    public void setZXJGQYGZFW(String ZXJGQYGZFW) {
        this.ZXJGQYGZFW = ZXJGQYGZFW;
    }

    public String getZXJGZYXS() {
        return ZXJGZYXS;
    }

    public void setZXJGZYXS(String ZXJGZYXS) {
        this.ZXJGZYXS = ZXJGZYXS;
    }

    public String getZXJGXS() {
        return ZXJGXS;
    }

    public void setZXJGXS(String ZXJGXS) {
        this.ZXJGXS = ZXJGXS;
    }

    public String getZXJGXH() {
        return ZXJGXH;
    }

    public void setZXJGXH(String ZXJGXH) {
        this.ZXJGXH = ZXJGXH;
    }

    public String getZXJGGQYL() {
        return ZXJGGQYL;
    }

    public void setZXJGGQYL(String ZXJGGQYL) {
        this.ZXJGGQYL = ZXJGGQYL;
    }

    public String getZXJGTHFW() {
        return ZXJGTHFW;
    }

    public void setZXJGTHFW(String ZXJGTHFW) {
        this.ZXJGTHFW = ZXJGTHFW;
    }

    public String getZXJGSLJG() {
        return ZXJGSLJG;
    }

    public void setZXJGSLJG(String ZXJGSLJG) {
        this.ZXJGSLJG = ZXJGSLJG;
    }

    public String getDWQLX() {
        return DWQLX;
    }

    public void setDWQLX(String DWQLX) {
        this.DWQLX = DWQLX;
    }

    public String getDWQXH() {
        return DWQXH;
    }

    public void setDWQXH(String DWQXH) {
        this.DWQXH = DWQXH;
    }

    public String getDWQSRXH() {
        return DWQSRXH;
    }

    public void setDWQSRXH(String DWQSRXH) {
        this.DWQSRXH = DWQSRXH;
    }

    public String getDWQGQYL() {
        return DWQGQYL;
    }

    public void setDWQGQYL(String DWQGQYL) {
        this.DWQGQYL = DWQGQYL;
    }

    public String getDWQFHDJ() {
        return DWQFHDJ;
    }

    public void setDWQFHDJ(String DWQFHDJ) {
        this.DWQFHDJ = DWQFHDJ;
    }

    public String getDWQFBDJ() {
        return DWQFBDJ;
    }

    public void setDWQFBDJ(String DWQFBDJ) {
        this.DWQFBDJ = DWQFBDJ;
    }

    public String getDWQQYJKCC() {
        return DWQQYJKCC;
    }

    public void setDWQQYJKCC(String DWQQYJKCC) {
        this.DWQQYJKCC = DWQQYJKCC;
    }

    public String getDWQDQJKCC() {
        return DWQDQJKCC;
    }

    public void setDWQDQJKCC(String DWQDQJKCC) {
        this.DWQDQJKCC = DWQDQJKCC;
    }

    public String getJYFXH() {
        return JYFXH;
    }

    public void setJYFXH(String JYFXH) {
        this.JYFXH = JYFXH;
    }

    public String getDCFXH() {
        return DCFXH;
    }

    public void setDCFXH(String DCFXH) {
        this.DCFXH = DCFXH;
    }

    public String getDCFDY() {
        return DCFDY;
    }

    public void setDCFDY(String DCFDY) {
        this.DCFDY = DCFDY;
    }

    public String getDCFFBDJ() {
        return DCFFBDJ;
    }

    public void setDCFFBDJ(String DCFFBDJ) {
        this.DCFFBDJ = DCFFBDJ;
    }

    public String getDCFQYJKCC() {
        return DCFQYJKCC;
    }

    public void setDCFQYJKCC(String DCFQYJKCC) {
        this.DCFQYJKCC = DCFQYJKCC;
    }

    public String getDCFDQJKCC() {
        return DCFDQJKCC;
    }

    public void setDCFDQJKCC(String DCFDQJKCC) {
        this.DCFDQJKCC = DCFDQJKCC;
    }

    public String getDCFJGXS() {
        return DCFJGXS;
    }

    public void setDCFJGXS(String DCFJGXS) {
        this.DCFJGXS = DCFJGXS;
    }

    public String getDCFSL() {
        return DCFSL;
    }

    public void setDCFSL(String DCFSL) {
        this.DCFSL = DCFSL;
    }

    public String getXWKGXH() {
        return XWKGXH;
    }

    public void setXWKGXH(String XWKGXH) {
        this.XWKGXH = XWKGXH;
    }

    public String getXWKGSL() {
        return XWKGSL;
    }

    public void setXWKGSL(String XWKGSL) {
        this.XWKGSL = XWKGSL;
    }

    public String getXWKGDQJKCC() {
        return XWKGDQJKCC;
    }

    public void setXWKGDQJKCC(String XWKGDQJKCC) {
        this.XWKGDQJKCC = XWKGDQJKCC;
    }

    public String getXWKGFBDJ() {
        return XWKGFBDJ;
    }

    public void setXWKGFBDJ(String XWKGFBDJ) {
        this.XWKGFBDJ = XWKGFBDJ;
    }

    public String getSTQK() {
        return STQK;
    }

    public void setSTQK(String STQK) {
        this.STQK = STQK;
    }

    public String getWTQK() {
        return WTQK;
    }

    public void setWTQK(String WTQK) {
        this.WTQK = WTQK;
    }

    public String getJSQ() {
        return JSQ;
    }

    public void setJSQ(String JSQ) {
        this.JSQ = JSQ;
    }

    public String getBWF() {
        return BWF;
    }

    public void setBWF(String BWF) {
        this.BWF = BWF;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getJT() {
        return JT;
    }

    public void setJT(String JT) {
        this.JT = JT;
    }

    public String getJS() {
        return JS;
    }

    public void setJS(String JS) {
        this.JS = JS;
    }

    public String getJY() {
        return JY;
    }

    public void setJY(String JY) {
        this.JY = JY;
    }

    public String getJSM() {
        return JSM;
    }

    public void setJSM(String JSM) {
        this.JSM = JSM;
    }

    public String getJXJ() {
        return JXJ;
    }

    public void setJXJ(String JXJ) {
        this.JXJ = JXJ;
    }

    public String getSCTL() {
        return SCTL;
    }

    public void setSCTL(String SCTL) {
        this.SCTL = SCTL;
    }

    public String getNACE1() {
        return NACE1;
    }

    public void setNACE1(String NACE1) {
        this.NACE1 = NACE1;
    }

    public String getNACE2() {
        return NACE2;
    }

    public void setNACE2(String NACE2) {
        this.NACE2 = NACE2;
    }

    public String getKQSJ() {
        return KQSJ;
    }

    public void setKQSJ(String KQSJ) {
        this.KQSJ = KQSJ;
    }

    public String getGBSJ() {
        return GBSJ;
    }

    public void setGBSJ(String GBSJ) {
        this.GBSJ = GBSJ;
    }

    public String getFLSMDP() {
        return FLSMDP;
    }

    public void setFLSMDP(String FLSMDP) {
        this.FLSMDP = FLSMDP;
    }

    public String getBJG() {
        return BJG;
    }

    public void setBJG(String BJG) {
        this.BJG = BJG;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getFLLX() {
        return FLLX;
    }

    public void setFLLX(String FLLX) {
        this.FLLX = FLLX;
    }

    public String getFLCZ() {
        return FLCZ;
    }

    public void setFLCZ(String FLCZ) {
        this.FLCZ = FLCZ;
    }

    public String getLSCZ() {
        return LSCZ;
    }

    public void setLSCZ(String LSCZ) {
        this.LSCZ = LSCZ;
    }

    public String getLMCZ() {
        return LMCZ;
    }

    public void setLMCZ(String LMCZ) {
        this.LMCZ = LMCZ;
    }

    public String getPipeDataSign() {
        return pipeDataSign;
    }

    public void setPipeDataSign(String pipeDataSign) {
        this.pipeDataSign = pipeDataSign;
    }

    public String getStandardDensityUnit() {
        return standardDensityUnit;
    }

    public void setStandardDensityUnit(String standardDensityUnit) {
        this.standardDensityUnit = standardDensityUnit;
    }

    public String getMaterialQualityAlias() {
        return materialQualityAlias;
    }

    public void setMaterialQualityAlias(String materialQualityAlias) {
        this.materialQualityAlias = materialQualityAlias;
    }

    public String getLYBHQ1() {
        return LYBHQ1;
    }

    public void setLYBHQ1(String LYBHQ1) {
        this.LYBHQ1 = LYBHQ1;
    }

    public String getLYBHQ1SL() {
        return LYBHQ1SL;
    }

    public void setLYBHQ1SL(String LYBHQ1SL) {
        this.LYBHQ1SL = LYBHQ1SL;
    }

    public String getLYBHQ2() {
        return LYBHQ2;
    }

    public void setLYBHQ2(String LYBHQ2) {
        this.LYBHQ2 = LYBHQ2;
    }

    public String getLYBHQ2SL() {
        return LYBHQ2SL;
    }

    public void setLYBHQ2SL(String LYBHQ2SL) {
        this.LYBHQ2SL = LYBHQ2SL;
    }

    public String getDLMFJT() {
        return DLMFJT;
    }

    public void setDLMFJT(String DLMFJT) {
        this.DLMFJT = DLMFJT;
    }

    public String getDLMFJTSL() {
        return DLMFJTSL;
    }

    public void setDLMFJTSL(String DLMFJTSL) {
        this.DLMFJTSL = DLMFJTSL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVapourPressure() {
        return vapourPressure;
    }

    public void setVapourPressure(String vapourPressure) {
        this.vapourPressure = vapourPressure;
    }

    public String getRkqhPressUint() {
        return rkqhPressUint;
    }

    public void setRkqhPressUint(String rkqhPressUint) {
        this.rkqhPressUint = rkqhPressUint;
    }

    public String getViscosity() {
        return viscosity;
    }

    public void setViscosity(String viscosity) {
        this.viscosity = viscosity;
    }

    public String getViscosityUnits() {
        return viscosityUnits;
    }

    public void setViscosityUnits(String viscosityUnits) {
        this.viscosityUnits = viscosityUnits;
    }
}