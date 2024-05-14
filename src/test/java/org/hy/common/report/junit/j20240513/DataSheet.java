package org.hy.common.report.junit.j20240513;

import java.text.DecimalFormat;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.hy.common.Help;
import org.hy.common.xml.plugins.UnitConvert;


public class DataSheet
{
    public DataSheet(){}


    private String projectCode; //项目编号
    private String bwgcz;
    private String pageNo;
    //迷宫块材质
    private String dsMatenial;
    private String dsMatenialName;
    private String language;
    private String priceNumber;
    private String priceNumber1;
    private String projectModelId;
    private String productModelId;
    private String valveName;
    private String elecActuatorModelNo;
    private String airValveSetPressure;
    private String airSupplyRange;
    private String electricalConn;
    private String sepcialRequirement;
    private String airInputConnect;
    private String airSetQuantityNew;
    private String limitSwitchQuantity;
    private String seatMaterialNew;
    private String openTime;
    private String closeTime;
    private String tubing;
    private String airinstallDirt;
    private String remarks;
    private String gasPathNum;
    private String gasPathNum1;
    private String gasPath;
    private String xxRecordUpdateTime;
    private String xxRecordUpdateUser;
    private String deviationRecord;
    private String projectRecord;
    private String recordUpdateUser;
    private String recordUpdateTime;
    private String valveProductModel;
    private String gctjWxcc;
    private String sljgWxcc;
    private String fmxsWxcc;
    private String orderUnit;
    private String signingTime;
    private String unitPrice;
    private String totalPrice;
    private String projectName;
    private String deviceName;
    //小合同号
    private String contNo;
    //大合同号
    private String contNoBig;
    private String revisionNo;
    private String quotationNo;
    private String serialNo;
    private String seqNo;
    private String imgPath;
    //概述
    private String tagNumber;
    private String quantity;
    private String service;
    private String areaClassification;
    private String pipingNo;
    private String pipingClass;
    private String pipingMaterial;
    private String pidNo;
    private String lineSizeName;
    private String pipingSizeInlet;
    private String pipingSizeOutlet;
    private String pipingSchInlet;
    private String pipingSchOutlet;
    private String ambientTemperatureRange;
    private String ambientTemperatureRangeUnit;
    //工况条件
    private String fluidState;
    private String fluidStateExplain;
    private String fluidName;
    private String maxValvePressDrop;
    private String criticalPressureUnit;
    private String criticalPressure;
    private String criticalTemperUnits;
    private String criticalTemper;
    private String closeDifferentPressureUnits;
    private String closeDifferentPressure;
    private String designPressure;
    private String designPressureUnit;
    private String designTemperature;
    private String designTemperatureUnit;
    private String flowRateUnits;
    private String flowRateMax;
    private String flowRateNor;
    private String flowRateMin;
    private String flowCondition1;
    private String flowCondition2;
    private String flowCondition3;
    private String flowCondition4;
    private String pressureUnits;
    private String inletPressureMax;
    private String inletPressureNor;
    private String inletPressureMin;
    private String inletPressureCondition1;
    private String inletPressureCondition2;
    private String inletPressureCondition3;
    private String inletPressureCondition4;
    private String outletPressureMax;
    private String outletPressureNor;
    private String outletPressureMin;
    private String outletPressureCondition1;
    private String outletPressureCondition2;
    private String outletPressureCondition3;
    private String outletPressureCondition4;
    private String differentPressureUnits;
    private String maxDifferentPressure;
    private String norDifferentPressure;
    private String minDifferentPressure;
    private String differentPressureOne;
    private String differentPressureTwo;
    private String differentPressureThree;
    private String differentPressurefour;
    private String temperUnits;
    private String wdDanwei;
    private String outVelocity;
    private String inletTemperatureMax;
    private String inletTemperatureNor;
    private String inletTemperatureMin;
    private String inletTemperatureCondition1;
    private String inletTemperatureCondition2;
    private String inletTemperatureCondition3;
    private String inletTemperatureCondition4;
    private String specificGravityMax;
    private String specificGravityNor;
    private String specificGravityMin;
    private String specificGravityCondition1;
    private String specificGravityCondition2;
    private String specificGravityCondition3;
    private String specificGravityCondition4;
    private String operaDensityMax;
    private String operaDensityNor;
    private String operaDensityMin;
    private String operaDensityCondition1;
    private String operaDensityCondition2;
    private String operaDensityCondition3;
    private String operaDensityCondition4;
    private String viscosityUnits;
    private String inletViscosityMax;
    private String inletViscosityNor;
    private String inletViscosityMin;
    private String inletViscosityCondition1;
    private String inletViscosityCondition2;
    private String inletViscosityCondition3;
    private String inletViscosityCondition4;
    private String rkqhPressUint;
    private String inletVaporPressureMax;
    private String inletVaporPressureNor;
    private String inletVaporPressureMin;
    private String inletVaporPressureCondition1;
    private String inletVaporPressureCondition2;
    private String inletVaporPressureCondition3;
    private String inletVaporPressureCondition4;
    private String inletCompressibilityFactorMax;
    private String inletCompressibilityFactorNor;
    private String inletCompressibilityFactorMin;
    private String inletCompressibilityFactorCondition1;
    private String inletCompressibilityFactorCondition2;
    private String inletCompressibilityFactorCondition3;
    private String inletCompressibilityFactorCondition4;
    private String standardDensity;
    private String inletViscosity;
    private String inletVaporPressure;
    private String specificGravity;
    private String molecularWeight;
    private String inletSpecificHeatsRatio;
    private String inletCompressibilityFactor;
    private String pipeUnit;

    //计算
    private String flowConditionMax;
    private String flowConditionNor;
    private String flowConditionMin;
    private String flowConditionOne;
    private String flowConditionTwo;
    private String flowConditionThree;
    private String flowConditionFour;
    private String flowCoefficientCvMax;
    private String flowCoefficientCvNor;
    private String flowCoefficientCvMin;
    private String flowCoefficientCvOne;
    private String flowCoefficientCvTwo;
    private String flowCoefficientCvThree;
    private String flowCoefficientCvFour;
    private String estimateTravelUnits;
    private String estimateTravelMax;
    private String estimateTravelNor;
    private String estimateTravelMin;
    private String estimateTravelOne;
    private String estimateTravelTwo;
    private String estimateTravelThree;
    private String estimateTravelFour;
    private String estimatedNoiseMax;
    private String estimatedNoiseNor;
    private String estimatedNoiseMin;
    private String estimatedNoiseOne;
    private String estimatedNoiseTwo;
    private String estimatedNoiseThree;
    private String estimatedNoiseFour;
    private String specificGravityName;
    private String inletDensityName;
    private String inletVaporPressureName;
    private String specificGravityUnits;
    private String densityUnits;
    private String maxExportFlowRate;
    private String norExportFlowRate;
    private String minExportFlowRate;
    private String exportFlowRateOne;
    private String exportFlowRateTwo;
    private String exportFlowRateThree;
    private String maxMachFlowRate;
    private String norMachFlowRate;
    private String minMachFlowRate;
    private String machFlowRateOne;
    private String machFlowRateTwo;
    private String machFlowRateThree;
    //阀门
    private String valveBodyType;
    private String valveBodyModel;
    private String linerMaterialName;
    private String linerMaterialValue;
    private String valveBodyModel1;
    private String insideNominalDiameter;
    private String seatDiameter2016;
    private String flowCharacteristics;
    private String ratedCV;
    private String pressureGrade;
    private String flangedType;
    private String flangedType1;
    private String flowDirection;
    private String bonnetType;
    private String trimType;
    private String bodyMaterial;
    private String coreMaterial;
    private String seatMaterial;
    private String spindleMaterial;
    private String fill;
    private String fillType;
    private String leakageLevel;
    private String ratedTravel;
    private String bellowsForm;
    private String bellowsMaterial;
    private String pressureRating;
    private String cageMaterial;
    private String spoolBalance;
    private String flowDirectionName;
    private String eNNamegetCoreMaterial;
    private String eNNamegetSpindleMaterial;
    private String bellowsName;
    private String supplyUnit;

    //执行机构
    private String electricActuatorType;
    private String actuatorModel;
    private String gqylZxjg;//供气压力
    private String rangeSpring;
    private String powerSupply;
    private String AirFailurePosition;
    private String failurePosition;
    private String airtoAction;
    private String elecAirtoAction;
    private String controlUnit;
    private String permissiblePressure;//允许压差
    private String airChamberVolume;
    private String handWheel;
    private String mechanicalLimit;
    private String elecActuatorOpenTime;
    private String elecActuatorClosingTime;
    private String limitSwitch;
    private String inputSignal;
    private String outputSignal;
    private String elecProtection;
    private String electricEXPClass;
    private String elecHandwheelLocation;
    private String manufacturer;
    private String settingPressure;
    private String valtageRang;
    private String valtageRangUnit; //调压范围单位
    private String tapsType;
    private String fireSafeDesign;
    private String travelRange;
    private String shutDiffPressureMax;
    private String zxjgGDFX;
    private String zxjgZHQX;
    private String zxjgWYFS;
    private String zxjgGHFS;
    private String zxjgQDCGQD;
    private String zxjgYXYC;
    private String zxjgQSRJ;
    private String thfwUnit;
    private String yxycUnit;
    private String sdylUnit;
    //特殊要求
    private String tsyqName1;
    private String  tsyqName2;
    private String  tsyqName3;
    private String  tsyqName4;
    private String  tsyqName5;
    private String  tsyqName6;
    private String  tsyqName7;
    private String  tsyqName8;
    private String  tsyqName9;
    private String  tsyqName10;
    private String  tsyqName11;
    private String  tsyqName12;
    private String  canRangeability;
    //备注
    private String  remark2016One;
    private String  remark2016Two;
    //定位器
    private String  positionerType;
    private String  localizerType;
    private String  inputRange;
    private String  outPutRange;
    private String  positionerOutputCharacteristics;
    private String  localizerExplosiveProof;
    private String  dwqDQJK;
    private String  dwqQYJK;
    private String  dwqXH;
    private String  localizerManufacturer;
    private String  localizerAction;
    private String  localizerStyle;
    //电磁阀
    private String  solenoidType;
    private String  solenoidPower;
    private String  solenoidSupply;
    private String  solenoidMaterial;
    private String  solenoidQuantity;
    private String  solenoidValveType;
    private String  solenoidActionMode;
    private String  solenoidStructureType;
    private String  outage;
    private String  dcfDQJK;
    private String  solenoidExplosionProofGrade;
    private String  dcfQYJK;
    private String  dcfJGXS;
    private String  dcfZYXS;
    private String  solenoidManufacturer;
    //限位开关
    private String  limitSwitchModelNo;
    private String  limitSwitchType;
    private String  limitQuantity;
    private String  limitForm;
    private String  limitCapacity;
    private String  xwkgDQJK;
    private String  xwkgXS;
    private String  xwkgxh;
    private String  limitExplosionProofGrade;
    private String  limitManufacturer;
    //减压阀
    private String  pressureReliefModelNo;
    private String  pressureReliefType;
    private String  pressureReliefQuantity;
    private String  pressureReliefGasSupplyPressureUnits;
    private String  pressureReliefGasSupplyPressure;
    private String  jyfQYJK;
    private String  pressureReliefManufacturer;
    private String  attachment;
    private String  attachmentType1;
    private String  attachmentType2;
    private String  attachmentType3;
    private String  attachmentType4;
    private String  attachmentType5;
    private String  attachmentType6;
    private String  attachmentValue1;
    private String  attachmentValue2;
    private String  attachmentValue3;
    private String  attachmentValue4;
    private String  attachmentValue5;
    private String  attachmentValue6;
    private String  curUserName;
    private String  lectotypeUser;
    private String  lectotypeLead;
    private String  lectotypeDate;
    private String flowConditionUnit;
    private String bellowName;
    private String bellowsMaterialName;
    private String shutDiffPressureMaxUnit;
    private String flowRateName;
    private String viscosityName;
    private String inletCompressibilityFactorName;
    private String inletCompressibilityFactorUnit;
    private String pipingSizeName;
    private String intelTemperatureUnit;
    private String bellowsType;
    private String vaporPressureName;
    private String rkqhUint;
    private String vaporPressureMax;
    private String vaporPressureNor;
    private String vaporPressureMin;
    private String vapourOne;
    private String vapourTwo;
    private String vapourThree;
    private String vapourFour;
    private String logoPath;
    private String logoWord;
    private String ratedTravelUnit;
    private String gqylZdZxjg;
    private String actuatorType2016; //执行机构型式
    private String localtionNumber;
    private String valveModel;
    private String aperture;
    private String pressureClass;
    private String actuatorNo;
    private String actuatorNo1;
    /** 阀门推力 */
    private String thrustCalculate;
    /** 执行机构推力 */
    private String thrustSelect;
    /** 阀门扭矩 */
    private String momentCalculate;
    /** 执行机构扭矩 */
    private String momentSelect;
    /** 关闭压力 */
    private String pressureDifference;
    private String valveCalculate;
    private String valveSelect;
    /** 安全系数 */
    private String safetyFactor;
    private String hJLX;
    private String  fLCZ;
    private String  lSCZ;
    private String  lMCZ;
    private String  bJG;
    private String  dP;
    private String actuatorSeries;
    private String actuAirtoAction;
    private String pipingSize;
    private String actuModeofAction;
    private String velocityUnit;
    private String setGasPressUnit;
    private String setPressUnit;
    private String allTsyq;
    private String qtTsyq;
    private String ratedTravelName;
    private String qtfj;
    private String fLXTFDName;
    private String fLXTFDMax;
    private String fLXTFDNor;
    private String fLXTFDMin;
    private String fLXTFDOne;
    private String fLXTFDTwo;
    private String fLXTFDThree;
    private String FLXTFD;
    private String attachAccessory;
    private String outletVelocityUnit;
    private String noiseUnit;
    private String mechanicalPowerUnit;
    private String attachAcceleratorValue;
    private String driveMode;
    private String valveForm;
    private String valveSeries;
    private String sixCondition;
    private String bonnetMaterial;
    private String productSeries;
    private String actuHandWheel;
    private String figureValue;
    private String paramKeys;
    private String paramKey0;
    private String paramKey1;
    private String paramKey2;
    private String paramKey3;
    private String paramKey4;
    private String paramKey5;
    private String paramValue1;
    private String paramValue2;
    private String paramValue3;
    private String paramValue4;
    private String paramValue5;
    private String productName;
    private String outLinePathNum;
    private String orderNumber;
    /** 产品型号 */
    private String paProductNo;
    /** 小合同号 */
    private String forderNo;
    /** 位号 */
    private String locationNumber;
    /** 执行机构 */
    private String paActuNo;
    /** 公称通径 */
    private String paAperture;

    //合同附表临时用的字符
    private String str;

    //工况1数据
    private String data1;
    //工况2数据
    private String data2;
    //工况3数据
    private String data3;
    //工况4数据
    private String data4;
    //工况5数据
    private String data5;
    //工况6数据
    private String data6;
    //关联项目
    private Product product;
    //合同编号
    private String contractNum;
    //版次
    private String version;

    //页码
    private String page;

    //最小流量
    private String minFlow;
    //供气压力
    private String supPress;
    //执行机构气室容积
    private String chamberVolume;
    //作用方式
    private String effectForm;

    private String states;

    private String YYYYMMDD;

    private String pagePumber;

    private String valveProductName;

    private String connectWay;

    private String maxDPforShutoff;
    private String attachAirControlValve;
    private String airSetModelNo;
    private String flowCondition;
    private String slxh;
    private String airSetQuantity;
    private String remark;
    private String attach;
    private String localNumber;
    private String nominalDiameter;
    private String flangeType;
    private String pressClass;
    private String productModel;
    private String weight;
    private String remark0;
    private String remark1;
    private String remark2;
    private String remark3;
    private String contractsRemark;
    private String attachType1;
    private String attachType2;
    private String attachType3;
    private String attachType4;
    private String attachType5;
    private String attachType6;
    private String standard;
    private String mold;
    private String customerConnetway; //用户要求中法兰关联方式

    private String zxjgAZFX;//执行机构安装方向
    private String zxjgjxxw; //执行机构机械限位

    public String getZxjgjxxw() { return zxjgjxxw; }

    public void setZxjgjxxw(String zxjgjxxw) { this.zxjgjxxw = zxjgjxxw; }

    public String getZxjgAZFX() {
        return zxjgAZFX;
    }

    public void setZxjgAZFX(String zxjgAZFX) { this.zxjgAZFX = zxjgAZFX; }

    public String getCustomerConnetway() { return customerConnetway; }

    public void setCustomerConnetway(String customerConnetway) { this.customerConnetway = customerConnetway; }

    public String getThfwUnit() {
        return thfwUnit;
    }

    public void setThfwUnit(String thfwUnit) {
        this.thfwUnit = thfwUnit;
    }

    public String getYxycUnit() {
        return yxycUnit;
    }

    public void setYxycUnit(String yxycUnit) {
        this.yxycUnit = yxycUnit;
    }

    public String getSdylUnit() {
        return sdylUnit;
    }

    public void setSdylUnit(String sdylUnit) {
        this.sdylUnit = sdylUnit;
    }

    public String getFlangedType1() {
        return flangedType1;
    }

    public void setFlangedType1(String flangedType1) {
        this.flangedType1 = flangedType1;
    }

    public String getLinerMaterialName() {
        return linerMaterialName;
    }

    public void setLinerMaterialName(String linerMaterialName) {
        this.linerMaterialName = linerMaterialName;
    }

    public String getLinerMaterialValue() {
        return linerMaterialValue;
    }

    public void setLinerMaterialValue(String linerMaterialValue) {
        this.linerMaterialValue = linerMaterialValue;
    }

    public String getAttachType1() {
        return attachType1;
    }

    public void setAttachType1(String attachType1) {
        this.attachType1 = attachType1;
    }

    public String getAttachType2() {
        return attachType2;
    }

    public void setAttachType2(String attachType2) {
        this.attachType2 = attachType2;
    }

    public String getAttachType3() {
        return attachType3;
    }

    public void setAttachType3(String attachType3) {
        this.attachType3 = attachType3;
    }

    public String getAttachType4() {
        return attachType4;
    }

    public void setAttachType4(String attachType4) {
        this.attachType4 = attachType4;
    }

    public String getAttachType5() {
        return attachType5;
    }

    public void setAttachType5(String attachType5) {
        this.attachType5 = attachType5;
    }

    public String getAttachType6() {
        return attachType6;
    }

    public void setAttachType6(String attachType6) {
        this.attachType6 = attachType6;
    }

    public String getContractsRemark() {
        return contractsRemark;
    }

    public void setContractsRemark(String contractsRemark) {
        this.contractsRemark = contractsRemark;
    }

    public String getRemark0() {
        return remark0;
    }

    public void setRemark0(String remark0) {
        this.remark0 = remark0;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void calOpenTime(){
        if(Help.isNull(chamberVolume) || Help.isNull(supPress)
                || Help.isNull(minFlow) ){
            return;
        }
        if("单作用".equals(this.effectForm)){
            Float V    =   Float.parseFloat(this.chamberVolume);
            double P1   =   Float.parseFloat(this.supPress);
            if (!"MPa.g".equals(this.supplyUnit)) {
                P1 = (double) UnitConvert.convert(this.supplyUnit,
                        "MPa.g",P1);
            }
            Float Q    =   Float.parseFloat(this.minFlow);
            DecimalFormat decimalFormat=new DecimalFormat("#.###");
            String  OpenTime = decimalFormat.format((V *(1-P1)*60)/(0.1*Q)) +"";
            this.openTime = OpenTime;
        }else if("双作用".equals(this.effectForm)){
            calDoubleOpenTime();
        }
    }

    public void calCloseTime(){
        if(Help.isNull(chamberVolume) || Help.isNull(supPress)
                || Help.isNull(minFlow) ){
            return;
        }
        Float V    =   Float.parseFloat(this.chamberVolume);
        double P1   =   Float.parseFloat(this.supPress);
        if (!"MPa.g".equals(this.supplyUnit)) {
            P1 = (double) UnitConvert.convert(this.supplyUnit,
                    "MPa.g",P1);
        }
        Float Q    =   Float.parseFloat(this.minFlow);
        DecimalFormat decimalFormat=new DecimalFormat("#.###");
        String closeTime = decimalFormat.format((V*P1*60)/(0.1*Q)) + "";
        this.closeTime = closeTime;
    }

    public void calDoubleOpenTime(){
        Float V    =   Float.parseFloat(this.chamberVolume);
        double P1   =   Float.parseFloat(this.supPress);
        if (!"MPa.g".equals(this.supplyUnit)) {
            P1 = (double) UnitConvert.convert(this.supplyUnit,
                    "MPa.g",P1);
        }
        Float Q    =   Float.parseFloat(this.minFlow);
        DecimalFormat decimalFormat=new DecimalFormat("#.###");
        String openTime = decimalFormat.format((V*P1*60)/(0.1*Q)) + "";
        this.closeTime = openTime;
        this.openTime = openTime;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getMaxDPforShutoff() {
        return maxDPforShutoff;
    }

    public void setMaxDPforShutoff(String maxDPforShutoff) {
        this.maxDPforShutoff = maxDPforShutoff;
    }

    public String getAttachAirControlValve() {
        return attachAirControlValve;
    }

    public void setAttachAirControlValve(String attachAirControlValve) {
        this.attachAirControlValve = attachAirControlValve;
    }

    public String getAirSetModelNo() {
        return airSetModelNo;
    }

    public void setAirSetModelNo(String airSetModelNo) {
        this.airSetModelNo = airSetModelNo;
    }

    public String getFlowCondition() {
        return flowCondition;
    }

    public void setFlowCondition(String flowCondition) {
        this.flowCondition = flowCondition;
    }

    public String getSlxh() {
        return slxh;
    }

    public void setSlxh(String slxh) {
        this.slxh = slxh;
    }

    public String getAirSetQuantity() {
        return airSetQuantity;
    }

    public void setAirSetQuantity(String airSetQuantity) {
        this.airSetQuantity = airSetQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public String getNominalDiameter() {
        return nominalDiameter;
    }

    public void setNominalDiameter(String nominalDiameter) {
        this.nominalDiameter = nominalDiameter;
    }

    public String getFlangeType() {
        return flangeType;
    }

    public void setFlangeType(String flangeType) {
        this.flangeType = flangeType;
    }

    public String getPressClass() {
        return pressClass;
    }

    public void setPressClass(String pressClass) {
        this.pressClass = pressClass;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getConnectWay() {
        return connectWay;
    }

    public void setConnectWay(String connectWay) {
        this.connectWay = connectWay;
    }

    public String getValveProductName() {
        return valveProductName;
    }

    public void setValveProductName(String valveProductName) {
        this.valveProductName = valveProductName;
    }

    public String getYYYYMMDD() {
        return YYYYMMDD;
    }

    public void setYYYYMMDD(String YYYYMMDD) {
        this.YYYYMMDD = YYYYMMDD;
    }

    public String getPagePumber() {
        return pagePumber;
    }

    public void setPagePumber(String pagePumber) {
        this.pagePumber = pagePumber;
    }
    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getEffectForm() {
        return effectForm;
    }

    public void setEffectForm(String effectForm) {
        this.effectForm = effectForm;
    }

    public String getChamberVolume() {
        return chamberVolume;
    }

    public void setChamberVolume(String chamberVolume) {
        this.chamberVolume = chamberVolume;
    }

    public String getMinFlow() {
        return minFlow;
    }

    public void setMinFlow(String minFlow) {
        this.minFlow = minFlow;
    }

    public String getSupPress() {
        return supPress;
    }

    public void setSupPress(String supPress) {
        this.supPress = supPress;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public String getData5() {
        return data5;
    }

    public void setData5(String data5) {
        this.data5 = data5;
    }

    public String getData6() {
        return data6;
    }

    public void setData6(String data6) {
        this.data6 = data6;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) { this.str = str; }

    public String getPaAperture() {
        return paAperture;
    }

    public void setPaAperture(String paAperture) {
        this.paAperture = paAperture;
    }

    public String getPaActuNo() {
        return paActuNo;
    }

    public void setPaActuNo(String paActuNo) {
        this.paActuNo = paActuNo;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getForderNo() {
        return forderNo;
    }

    public void setForderNo(String forderNo) {
        this.forderNo = forderNo;
    }

    public String getPaProductNo() {
        return paProductNo;
    }

    public void setPaProductNo(String paProductNo) {
        this.paProductNo = paProductNo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOutLinePathNum() {
        return outLinePathNum;
    }

    public void setOutLinePathNum(String outLinePathNum) {
        this.outLinePathNum = outLinePathNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getGasPath() {
        return gasPath;
    }

    public void setGasPath(String gasPath) {
        this.gasPath = gasPath;
    }

    public String getParamKey5() {
        return paramKey5;
    }

    public void setParamKey5(String paramKey5) {
        this.paramKey5 = paramKey5;
    }

    public String getParamKey0() {
        return paramKey0;
    }

    public void setParamKey0(String paramKey0) {
        this.paramKey0 = paramKey0;
    }

    public String getParamKey1() {
        return paramKey1;
    }

    public void setParamKey1(String paramKey1) {
        this.paramKey1 = paramKey1;
    }

    public String getParamKey2() {
        return paramKey2;
    }

    public void setParamKey2(String paramKey2) {
        this.paramKey2 = paramKey2;
    }

    public String getParamKey3() {
        return paramKey3;
    }

    public void setParamKey3(String paramKey3) {
        this.paramKey3 = paramKey3;
    }

    public String getParamKey4() {
        return paramKey4;
    }

    public void setParamKey4(String paramKey4) {
        this.paramKey4 = paramKey4;
    }

    public String getParamValue1() {
        return paramValue1;
    }

    public void setParamValue1(String paramValue1) {
        this.paramValue1 = paramValue1;
    }

    public String getParamValue2() {
        return paramValue2;
    }

    public void setParamValue2(String paramValue2) {
        this.paramValue2 = paramValue2;
    }

    public String getParamValue3() {
        return paramValue3;
    }

    public void setParamValue3(String paramValue3) {
        this.paramValue3 = paramValue3;
    }

    public String getParamValue4() {
        return paramValue4;
    }

    public void setParamValue4(String paramValue4) {
        this.paramValue4 = paramValue4;
    }

    public String getParamValue5() {
        return paramValue5;
    }

    public void setParamValue5(String paramValue5) {
        this.paramValue5 = paramValue5;
    }

    public String getFigureValue() {
        return figureValue;
    }

    public void setFigureValue(String figureValue) {
        this.figureValue = figureValue;
    }

    public String getParamKeys() {
        return paramKeys;
    }

    public void setParamKeys(String paramKeys) {
        this.paramKeys = paramKeys;
    }

    public String getActuHandWheel() {
        return actuHandWheel;
    }

    public void setActuHandWheel(String actuHandWheel) {
        this.actuHandWheel = actuHandWheel;
    }

    public String getProductSeries() {
        return productSeries;
    }

    public void setProductSeries(String productSeries) {
        this.productSeries = productSeries;
    }

    public String getProjectModelId() {
        return projectModelId;
    }

    public void setProjectModelId(String projectModelId) {
        this.projectModelId = projectModelId;
    }

    public String getGasPathNum1() {
        return gasPathNum1;
    }

    public void setGasPathNum1(String gasPathNum1) {
        this.gasPathNum1 = gasPathNum1;
    }

    public String getBonnetMaterial() {
        return bonnetMaterial;
    }

    public void setBonnetMaterial(String bonnetMaterial) {
        this.bonnetMaterial = bonnetMaterial;
    }

    public String getValveBodyModel1() {
        return valveBodyModel1;
    }

    public void setValveBodyModel1(String valveBodyModel1) {
        this.valveBodyModel1 = valveBodyModel1;
    }

    public String getSixCondition() {
        return sixCondition;
    }

    public void setSixCondition(String sixCondition) {
        this.sixCondition = sixCondition;
    }

    public String getValveSeries() {
        return valveSeries;
    }

    public void setValveSeries(String valveSeries) {
        this.valveSeries = valveSeries;
    }

    public String getValveForm() {
        return valveForm;
    }

    public void setValveForm(String valveForm) {
        this.valveForm = valveForm;
    }

    public String getDriveMode() {
        return driveMode;
    }

    public void setDriveMode(String driveMode) {
        this.driveMode = driveMode;
    }

    public String getAttachAcceleratorValue() {
        return attachAcceleratorValue;
    }

    public void setAttachAcceleratorValue(String attachAcceleratorValue) {
        this.attachAcceleratorValue = attachAcceleratorValue;
    }

    public String getOutletVelocityUnit() {
        return outletVelocityUnit;
    }

    public void setOutletVelocityUnit(String outletVelocityUnit) {
        this.outletVelocityUnit = outletVelocityUnit;
    }

    public String getNoiseUnit() {
        return noiseUnit;
    }

    public void setNoiseUnit(String noiseUnit) {
        this.noiseUnit = noiseUnit;
    }

    public String getMechanicalPowerUnit() {
        return mechanicalPowerUnit;
    }

    public void setMechanicalPowerUnit(String mechanicalPowerUnit) {
        this.mechanicalPowerUnit = mechanicalPowerUnit;
    }

    public String getAttachAccessory() {
        return attachAccessory;
    }

    public void setAttachAccessory(String attachAccessory) {
        this.attachAccessory = attachAccessory;
    }

    public String getCanRangeability() {
        return canRangeability;
    }

    public void setCanRangeability(String canRangeability) {
        this.canRangeability = canRangeability;
    }

    public String getPriceNumber1() {
        return priceNumber1;
    }

    public void setPriceNumber1(String priceNumber1) {
        this.priceNumber1 = priceNumber1;
    }

    public String getFLXTFD() {
        return FLXTFD;
    }

    public void setFLXTFD(String FLXTFD) {
        this.FLXTFD = FLXTFD;
    }

    public String getfLXTFDName() {
        return fLXTFDName;
    }

    public void setfLXTFDName(String fLXTFDName) {
        this.fLXTFDName = fLXTFDName;
    }

    public String getfLXTFDMax() {
        return fLXTFDMax;
    }

    public void setfLXTFDMax(String fLXTFDMax) {
        this.fLXTFDMax = fLXTFDMax;
    }

    public String getfLXTFDNor() {
        return fLXTFDNor;
    }

    public void setfLXTFDNor(String fLXTFDNor) {
        this.fLXTFDNor = fLXTFDNor;
    }

    public String getfLXTFDMin() {
        return fLXTFDMin;
    }

    public void setfLXTFDMin(String fLXTFDMin) {
        this.fLXTFDMin = fLXTFDMin;
    }

    public String getfLXTFDOne() {
        return fLXTFDOne;
    }

    public void setfLXTFDOne(String fLXTFDOne) {
        this.fLXTFDOne = fLXTFDOne;
    }

    public String getfLXTFDTwo() {
        return fLXTFDTwo;
    }

    public void setfLXTFDTwo(String fLXTFDTwo) {
        this.fLXTFDTwo = fLXTFDTwo;
    }

    public String getfLXTFDThree() {
        return fLXTFDThree;
    }

    public void setfLXTFDThree(String fLXTFDThree) {
        this.fLXTFDThree = fLXTFDThree;
    }

    public String getQtfj() {
        return qtfj;
    }

    public void setQtfj(String qtfj) {
        this.qtfj = qtfj;
    }

    public String getRatedTravelName() {
        return ratedTravelName;
    }

    public void setRatedTravelName(String ratedTravelName) {
        this.ratedTravelName = ratedTravelName;
    }

    public String getQtTsyq() {
        return qtTsyq;
    }

    public void setQtTsyq(String qtTsyq) {
        this.qtTsyq = qtTsyq;
    }

    public String getAllTsyq() {
        return allTsyq;
    }

    public void setAllTsyq(String allTsyq) {
        this.allTsyq = allTsyq;
    }

    public String getSetPressUnit() {
        return setPressUnit;
    }

    public void setSetPressUnit(String setPressUnit) {
        this.setPressUnit = setPressUnit;
    }

    public String getSetGasPressUnit() {
        return setGasPressUnit;
    }

    public void setSetGasPressUnit(String setGasPressUnit) {
        this.setGasPressUnit = setGasPressUnit;
    }

    public String getVelocityUnit() {
        return velocityUnit;
    }

    public void setVelocityUnit(String velocityUnit) {
        this.velocityUnit = velocityUnit;
    }

    public String getActuModeofAction() {
        return actuModeofAction;
    }

    public void setActuModeofAction(String actuModeofAction) {
        this.actuModeofAction = actuModeofAction;
    }

    public String getPipingSize() {
        return pipingSize;
    }

    public void setPipingSize(String pipingSize) {
        this.pipingSize = pipingSize;
    }

    public String getActuAirtoAction() {
        return actuAirtoAction;
    }

    public void setActuAirtoAction(String actuAirtoAction) {
        this.actuAirtoAction = actuAirtoAction;
    }

    public String getActuatorSeries() {
        return actuatorSeries;
    }

    public void setActuatorSeries(String actuatorSeries) {
        this.actuatorSeries = actuatorSeries;
    }

    public String gethJLX() {
        return hJLX;
    }

    public void sethJLX(String hJLX) {
        this.hJLX = hJLX;
    }

    public String getfLCZ() {
        return fLCZ;
    }

    public void setfLCZ(String fLCZ) {
        this.fLCZ = fLCZ;
    }

    public String getlSCZ() {
        return lSCZ;
    }

    public void setlSCZ(String lSCZ) {
        this.lSCZ = lSCZ;
    }

    public String getlMCZ() {
        return lMCZ;
    }

    public void setlMCZ(String lMCZ) {
        this.lMCZ = lMCZ;
    }

    public String getbJG() {
        return bJG;
    }

    public void setbJG(String bJG) {
        this.bJG = bJG;
    }

    public String getdP() {
        return dP;
    }

    public void setdP(String dP) {
        this.dP = dP;
    }

    public String getSafetyFactor() {
        return safetyFactor;
    }

    public void setSafetyFactor(String safetyFactor) {
        this.safetyFactor = safetyFactor;
    }

    public String getValveSelect() {
        return valveSelect;
    }

    public void setValveSelect(String valveSelect) {
        this.valveSelect = valveSelect;
    }

    public String getValveCalculate() {
        return valveCalculate;
    }

    public void setValveCalculate(String valveCalculate) {
        this.valveCalculate = valveCalculate;
    }

    public String getPressureDifference() {
        return pressureDifference;
    }

    public void setPressureDifference(String pressureDifference) {
        this.pressureDifference = pressureDifference;
    }

    public String getMomentSelect() {
        return momentSelect;
    }

    public void setMomentSelect(String momentSelect) {
        this.momentSelect = momentSelect;
    }

    public String getMomentCalculate() {
        return momentCalculate;
    }

    public void setMomentCalculate(String momentCalculate) {
        this.momentCalculate = momentCalculate;
    }

    public String getThrustSelect() {
        return thrustSelect;
    }

    public void setThrustSelect(String thrustSelect) {
        this.thrustSelect = thrustSelect;
    }

    public String getThrustCalculate() {
        return thrustCalculate;
    }

    public void setThrustCalculate(String thrustCalculate) {
        this.thrustCalculate = thrustCalculate;
    }

    public String getActuatorNo1() {
        return actuatorNo1;
    }

    public void setActuatorNo1(String actuatorNo1) {
        this.actuatorNo1 = actuatorNo1;
    }

    public String getActuatorNo() {
        return actuatorNo;
    }

    public void setActuatorNo(String actuatorNo) {
        this.actuatorNo = actuatorNo;
    }

    public String getPressureClass() {
        return pressureClass;
    }

    public void setPressureClass(String pressureClass) {
        this.pressureClass = pressureClass;
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    public String getValveModel() {
        return valveModel;
    }

    public void setValveModel(String valveModel) {
        this.valveModel = valveModel;
    }

    public String getLocaltionNumber() {
        return localtionNumber;
    }

    public void setLocaltionNumber(String localtionNumber) {
        this.localtionNumber = localtionNumber;
    }

    public String getActuatorType2016() {
        return actuatorType2016;
    }

    public void setActuatorType2016(String actuatorType2016) {
        this.actuatorType2016 = actuatorType2016;
    }

    public String getOutVelocity() {
        return outVelocity;
    }

    public void setOutVelocity(String outVelocity) {
        this.outVelocity = outVelocity;
    }

    public String getXwkgxh() {
        return xwkgxh;
    }

    public void setXwkgxh(String xwkgxh) {
        this.xwkgxh = xwkgxh;
    }

    public String getDwqXH() {
        return dwqXH;
    }

    public void setDwqXH(String dwqXH) {
        this.dwqXH = dwqXH;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSigningTime() {
        return signingTime;
    }

    public void setSigningTime(String signingTime) {
        this.signingTime = signingTime;
    }

    public String getOrderUnit() {
        return orderUnit;
    }

    public void setOrderUnit(String orderUnit) {
        this.orderUnit = orderUnit;
    }

    public String getFmxsWxcc() {
        return fmxsWxcc;
    }

    public void setFmxsWxcc(String fmxsWxcc) {
        this.fmxsWxcc = fmxsWxcc;
    }

    public String getSljgWxcc() {
        return sljgWxcc;
    }

    public void setSljgWxcc(String sljgWxcc) {
        this.sljgWxcc = sljgWxcc;
    }

    public String getGctjWxcc() {
        return gctjWxcc;
    }

    public void setGctjWxcc(String gctjWxcc) {
        this.gctjWxcc = gctjWxcc;
    }

    public String getValveProductModel() {
        return valveProductModel;
    }

    public void setValveProductModel(String valveProductModel) {
        this.valveProductModel = valveProductModel;
    }

    public String getProjectRecord() {
        return projectRecord;
    }

    public void setProjectRecord(String projectRecord) {
        this.projectRecord = projectRecord;
    }

    public String getRecordUpdateUser() {
        return recordUpdateUser;
    }

    public void setRecordUpdateUser(String recordUpdateUser) {
        this.recordUpdateUser = recordUpdateUser;
    }

    public String getRecordUpdateTime() {
        return recordUpdateTime;
    }

    public void setRecordUpdateTime(String recordUpdateTime) {
        this.recordUpdateTime = recordUpdateTime;
    }

    public String getDeviationRecord() {
        return deviationRecord;
    }

    public void setDeviationRecord(String deviationRecord) {
        this.deviationRecord = deviationRecord;
    }

    public String getXxRecordUpdateUser() {
        return xxRecordUpdateUser;
    }

    public void setXxRecordUpdateUser(String xxRecordUpdateUser) {
        this.xxRecordUpdateUser = xxRecordUpdateUser;
    }

    public String getXxRecordUpdateTime() {
        return xxRecordUpdateTime;
    }

    public void setXxRecordUpdateTime(String xxRecordUpdateTime) {
        this.xxRecordUpdateTime = xxRecordUpdateTime;
    }

    public String getGasPathNum() {
        return gasPathNum;
    }

    public void setGasPathNum(String gasPathNum) {
        this.gasPathNum = gasPathNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAirinstallDirt() {
        return airinstallDirt;
    }

    public void setAirinstallDirt(String airinstallDirt) {
        this.airinstallDirt = airinstallDirt;
    }

    public String getMaxMachFlowRate() {
        return maxMachFlowRate;
    }

    public void setMaxMachFlowRate(String maxMachFlowRate) {
        this.maxMachFlowRate = maxMachFlowRate;
    }

    public String getNorMachFlowRate() {
        return norMachFlowRate;
    }

    public void setNorMachFlowRate(String norMachFlowRate) {
        this.norMachFlowRate = norMachFlowRate;
    }

    public String getMinMachFlowRate() {
        return minMachFlowRate;
    }

    public void setMinMachFlowRate(String minMachFlowRate) {
        this.minMachFlowRate = minMachFlowRate;
    }

    public String getMachFlowRateOne() {
        return machFlowRateOne;
    }

    public void setMachFlowRateOne(String machFlowRateOne) {
        this.machFlowRateOne = machFlowRateOne;
    }

    public String getMachFlowRateTwo() {
        return machFlowRateTwo;
    }

    public void setMachFlowRateTwo(String machFlowRateTwo) {
        this.machFlowRateTwo = machFlowRateTwo;
    }

    public String getMachFlowRateThree() {
        return machFlowRateThree;
    }

    public void setMachFlowRateThree(String machFlowRateThree) {
        this.machFlowRateThree = machFlowRateThree;
    }

    public String getMaxExportFlowRate() {
        return maxExportFlowRate;
    }

    public void setMaxExportFlowRate(String maxExportFlowRate) {
        this.maxExportFlowRate = maxExportFlowRate;
    }

    public String getNorExportFlowRate() {
        return norExportFlowRate;
    }

    public void setNorExportFlowRate(String norExportFlowRate) {
        this.norExportFlowRate = norExportFlowRate;
    }

    public String getMinExportFlowRate() {
        return minExportFlowRate;
    }

    public void setMinExportFlowRate(String minExportFlowRate) {
        this.minExportFlowRate = minExportFlowRate;
    }

    public String getExportFlowRateOne() {
        return exportFlowRateOne;
    }

    public void setExportFlowRateOne(String exportFlowRateOne) {
        this.exportFlowRateOne = exportFlowRateOne;
    }

    public String getExportFlowRateTwo() {
        return exportFlowRateTwo;
    }

    public void setExportFlowRateTwo(String exportFlowRateTwo) {
        this.exportFlowRateTwo = exportFlowRateTwo;
    }

    public String getExportFlowRateThree() {
        return exportFlowRateThree;
    }

    public void setExportFlowRateThree(String exportFlowRateThree) {
        this.exportFlowRateThree = exportFlowRateThree;
    }

    public String getTubing() {
        return tubing;
    }

    public void setTubing(String tubing) {
        this.tubing = tubing;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getSeatMaterialNew() {
        return seatMaterialNew;
    }

    public void setSeatMaterialNew(String seatMaterialNew) {
        this.seatMaterialNew = seatMaterialNew;
    }

    public String getLimitSwitchQuantity() {
        return limitSwitchQuantity;
    }

    public void setLimitSwitchQuantity(String limitSwitchQuantity) {
        this.limitSwitchQuantity = limitSwitchQuantity;
    }

    public String getAirSetQuantityNew() {
        return airSetQuantityNew;
    }

    public void setAirSetQuantityNew(String airSetQuantityNew) {
        this.airSetQuantityNew = airSetQuantityNew;
    }

    public String getAirInputConnect() {
        return airInputConnect;
    }

    public void setAirInputConnect(String airInputConnect) {
        this.airInputConnect = airInputConnect;
    }

    public String getSepcialRequirement() {
        return sepcialRequirement;
    }

    public void setSepcialRequirement(String sepcialRequirement) {
        this.sepcialRequirement = sepcialRequirement;
    }

    public String getAirSupplyRange() {
        return airSupplyRange;
    }

    public void setAirSupplyRange(String airSupplyRange) {
        this.airSupplyRange = airSupplyRange;
    }

    public String getElectricalConn() {
        return electricalConn;
    }

    public void setElectricalConn(String electricalConn) {
        this.electricalConn = electricalConn;
    }

    public String getAirValveSetPressure() {
        return airValveSetPressure;
    }

    public void setAirValveSetPressure(String airValveSetPressure) {
        this.airValveSetPressure = airValveSetPressure;
    }

    public String getElecActuatorModelNo() {
        return elecActuatorModelNo;
    }

    public void setElecActuatorModelNo(String elecActuatorModelNo) {
        this.elecActuatorModelNo = elecActuatorModelNo;
    }

    public String getValveName() {
        return valveName;
    }

    public void setValveName(String valveName) {
        this.valveName = valveName;
    }

    public String getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(String productModelId) {
        this.productModelId = productModelId;
    }

    public String getPriceNumber() {
        return priceNumber;
    }

    public void setPriceNumber(String priceNumber) {
        this.priceNumber = priceNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGqylZdZxjg() {
        return gqylZdZxjg;
    }

    public void setGqylZdZxjg(String gqylZdZxjg) {
        this.gqylZdZxjg = gqylZdZxjg;
    }

    public String getRatedTravelUnit() {
        return ratedTravelUnit;
    }

    public void setRatedTravelUnit(String ratedTravelUnit) {
        this.ratedTravelUnit = ratedTravelUnit;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoWord() {
        return logoWord;
    }

    public void setLogoWord(String logoWord) {
        this.logoWord = logoWord;
    }

    public String getVapourFour() {
        return vapourFour;
    }

    public void setVapourFour(String vapourFour) {
        this.vapourFour = vapourFour;
    }

    public String getVaporPressureMax() {
        return vaporPressureMax;
    }

    public void setVaporPressureMax(String vaporPressureMax) {
        this.vaporPressureMax = vaporPressureMax;
    }

    public String getVaporPressureNor() {
        return vaporPressureNor;
    }

    public void setVaporPressureNor(String vaporPressureNor) {
        this.vaporPressureNor = vaporPressureNor;
    }

    public String getVaporPressureMin() {
        return vaporPressureMin;
    }

    public void setVaporPressureMin(String vaporPressureMin) {
        this.vaporPressureMin = vaporPressureMin;
    }

    public String getVaporPressureName() {
        return vaporPressureName;
    }

    public void setVaporPressureName(String vaporPressureName) {
        this.vaporPressureName = vaporPressureName;
    }

    public String getRkqhUint() {
        return rkqhUint;
    }

    public void setRkqhUint(String rkqhUint) {
        this.rkqhUint = rkqhUint;
    }



    public String getVapourOne() {
        return vapourOne;
    }

    public void setVapourOne(String vapourOne) {
        this.vapourOne = vapourOne;
    }

    public String getVapourTwo() {
        return vapourTwo;
    }

    public void setVapourTwo(String vapourTwo) {
        this.vapourTwo = vapourTwo;
    }

    public String getVapourThree() {
        return vapourThree;
    }

    public void setVapourThree(String vapourThree) {
        this.vapourThree = vapourThree;
    }

    public String getBellowsType() {
        return bellowsType;
    }

    public void setBellowsType(String bellowsType) {
        this.bellowsType = bellowsType;
    }

    public String getPipingSizeName() {
        return pipingSizeName;
    }

    public void setPipingSizeName(String pipingSizeName) {
        this.pipingSizeName = pipingSizeName;
    }

    public String getIntelTemperatureUnit() {
        return intelTemperatureUnit;
    }

    public void setIntelTemperatureUnit(String intelTemperatureUnit) {
        this.intelTemperatureUnit = intelTemperatureUnit;
    }

    public String getInletCompressibilityFactorName() {
        return inletCompressibilityFactorName;
    }

    public void setInletCompressibilityFactorName(String inletCompressibilityFactorName) {
        this.inletCompressibilityFactorName = inletCompressibilityFactorName;
    }

    public String getInletCompressibilityFactorUnit() {
        return inletCompressibilityFactorUnit;
    }

    public void setInletCompressibilityFactorUnit(String inletCompressibilityFactorUnit) {
        this.inletCompressibilityFactorUnit = inletCompressibilityFactorUnit;
    }

    public String getViscosityName() {
        return viscosityName;
    }

    public void setViscosityName(String viscosityName) {
        this.viscosityName = viscosityName;
    }

    public String getFlowRateName() {
        return flowRateName;
    }

    public void setFlowRateName(String flowRateName) {
        this.flowRateName = flowRateName;
    }

    public String getShutDiffPressureMaxUnit() {
        return shutDiffPressureMaxUnit;
    }

    public void setShutDiffPressureMaxUnit(String shutDiffPressureMaxUnit) {
        this.shutDiffPressureMaxUnit = shutDiffPressureMaxUnit;
    }

    public String getZxjgYXYC() {
        return zxjgYXYC;
    }

    public void setZxjgYXYC(String zxjgYXYC) {
        this.zxjgYXYC = zxjgYXYC;
    }

    public String getZxjgQSRJ() {
        return zxjgQSRJ;
    }

    public void setZxjgQSRJ(String zxjgQSRJ) {
        this.zxjgQSRJ = zxjgQSRJ;
    }

    public String getWdDanwei() {
        return wdDanwei;
    }

    public void setWdDanwei(String wdDanwei) {
        this.wdDanwei = wdDanwei;
    }


    public String getZxjgGDFX() {
        return zxjgGDFX;
    }

    public void setZxjgGDFX(String zxjgGDFX) {
        this.zxjgGDFX = zxjgGDFX;
    }

    public String getZxjgZHQX() {
        return zxjgZHQX;
    }

    public void setZxjgZHQX(String zxjgZHQX) {
        this.zxjgZHQX = zxjgZHQX;
    }

    public String getZxjgWYFS() {
        return zxjgWYFS;
    }

    public void setZxjgWYFS(String zxjgWYFS) {
        this.zxjgWYFS = zxjgWYFS;
    }

    public String getZxjgGHFS() {
        return zxjgGHFS;
    }

    public void setZxjgGHFS(String zxjgGHFS) {
        this.zxjgGHFS = zxjgGHFS;
    }

    public String getZxjgQDCGQD() {
        return zxjgQDCGQD;
    }

    public void setZxjgQDCGQD(String zxjgQDCGQD) {
        this.zxjgQDCGQD = zxjgQDCGQD;
    }

    public String getXwkgXS() {
        return xwkgXS;
    }

    public void setXwkgXS(String xwkgXS) {
        this.xwkgXS = xwkgXS;
    }

    public String getDcfZYXS() {
        return dcfZYXS;
    }

    public void setDcfZYXS(String dcfZYXS) {
        this.dcfZYXS = dcfZYXS;
    }

    public String getDcfJGXS() {
        return dcfJGXS;
    }

    public void setDcfJGXS(String dcfJGXS) {
        this.dcfJGXS = dcfJGXS;
    }

    public String getBellowName() {
        return bellowName;
    }

    public void setBellowName(String bellowName) {
        this.bellowName = bellowName;
    }

    public String getBellowsMaterialName() {
        return bellowsMaterialName;
    }

    public void setBellowsMaterialName(String bellowsMaterialName) {
        this.bellowsMaterialName = bellowsMaterialName;
    }

    public String getSupplyUnit() {
        return supplyUnit;
    }

    public void setSupplyUnit(String supplyUnit) {
        this.supplyUnit = supplyUnit;
    }

    public String getPipeUnit() {
        return pipeUnit;
    }

    public void setPipeUnit(String pipeUnit) {
        this.pipeUnit = pipeUnit;
    }

    public String getShutDiffPressureMax() {
        return shutDiffPressureMax;
    }

    public void setShutDiffPressureMax(String shutDiffPressureMax) {
        this.shutDiffPressureMax = shutDiffPressureMax;
    }

    public String getAmbientTemperatureRangeUnit() {
        return ambientTemperatureRangeUnit;
    }

    public void setAmbientTemperatureRangeUnit(String ambientTemperatureRangeUnit) {
        this.ambientTemperatureRangeUnit = ambientTemperatureRangeUnit;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDensityUnits() {
        return densityUnits;
    }

    public void setDensityUnits(String densityUnits) {
        this.densityUnits = densityUnits;
    }

    public String getLineSizeName() {
        return lineSizeName;
    }

    public void setLineSizeName(String lineSizeName) {
        this.lineSizeName = lineSizeName;
    }

    public String getSpecificGravityUnits() {
        return specificGravityUnits;
    }

    public void setSpecificGravityUnits(String specificGravityUnits) {
        this.specificGravityUnits = specificGravityUnits;
    }

    public String getSpecificGravityName() {
        return specificGravityName;
    }

    public void setSpecificGravityName(String specificGravityName) {
        this.specificGravityName = specificGravityName;
    }

    public String getInletDensityName() {
        return inletDensityName;
    }

    public void setInletDensityName(String inletDensityName) {
        this.inletDensityName = inletDensityName;
    }

    public String getInletVaporPressureName() {
        return inletVaporPressureName;
    }

    public void setInletVaporPressureName(String inletVaporPressureName) {
        this.inletVaporPressureName = inletVaporPressureName;
    }

    public String geteNNamegetCoreMaterial() {
        return eNNamegetCoreMaterial;
    }

    public void seteNNamegetCoreMaterial(String eNNamegetCoreMaterial) {
        this.eNNamegetCoreMaterial = eNNamegetCoreMaterial;
    }

    public String geteNNamegetSpindleMaterial() {
        return eNNamegetSpindleMaterial;
    }

    public void seteNNamegetSpindleMaterial(String eNNamegetSpindleMaterial) {
        this.eNNamegetSpindleMaterial = eNNamegetSpindleMaterial;
    }

    public String getBellowsName() {
        return bellowsName;
    }

    public void setBellowsName(String bellowsName) {
        this.bellowsName = bellowsName;
    }

    public String getFlowDirectionName() {
        return flowDirectionName;
    }

    public void setFlowDirectionName(String flowDirectionName) {
        this.flowDirectionName = flowDirectionName;
    }

    public String getLocalizerStyle() {
        return localizerStyle;
    }

    public void setLocalizerStyle(String localizerStyle) {
        this.localizerStyle = localizerStyle;
    }

    public String getLocalizerAction() {
        return localizerAction;
    }

    public void setLocalizerAction(String localizerAction) {
        this.localizerAction = localizerAction;
    }

    public String getTravelRange() {
        return travelRange;
    }

    public void setTravelRange(String travelRange) {
        this.travelRange = travelRange;
    }

    public String getFluidName() {
        return fluidName;
    }

    public void setFluidName(String fluidName) {
        this.fluidName = fluidName;
    }

    public String getMaxValvePressDrop() {
        return maxValvePressDrop;
    }

    public void setMaxValvePressDrop(String maxValvePressDrop) {
        this.maxValvePressDrop = maxValvePressDrop;
    }


    public String getFlowRateUnits() { return flowRateUnits; }

    public void setFlowRateUnits(String flowRateUnits) { this.flowRateUnits = flowRateUnits; }

    public String getPressureUnits() { return pressureUnits; }

    public void setPressureUnits(String pressureUnits) { this.pressureUnits = pressureUnits; }

    public String getDifferentPressureUnits() { return differentPressureUnits; }

    public void setDifferentPressureUnits(String differentPressureUnits) { this.differentPressureUnits = differentPressureUnits; }

    public String getTemperUnits() { return temperUnits; }

    public void setTemperUnits(String temperUnits) { this.temperUnits = temperUnits; }

    public String getViscosityUnits() { return viscosityUnits; }

    public void setViscosityUnits(String viscosityUnits) { this.viscosityUnits = viscosityUnits; }

    public String getRkqhPressUint() { return rkqhPressUint; }

    public void setRkqhPressUint(String rkqhPressUint) { this.rkqhPressUint = rkqhPressUint; }

    public String getStandardDensity() { return standardDensity; }

    public void setStandardDensity(String standardDensity) { this.standardDensity = standardDensity; }

    public String getInletViscosity() { return inletViscosity; }

    public void setInletViscosity(String inletViscosity) { this.inletViscosity = inletViscosity; }

    public String getInletVaporPressure() { return inletVaporPressure; }

    public void setInletVaporPressure(String inletVaporPressure) { this.inletVaporPressure = inletVaporPressure; }

    public String getSpecificGravity() { return specificGravity; }

    public void setSpecificGravity(String specificGravity) { this.specificGravity = specificGravity; }

    public String getMolecularWeight() { return molecularWeight; }

    public void setMolecularWeight(String molecularWeight) { this.molecularWeight = molecularWeight; }

    public String getInletSpecificHeatsRatio() { return inletSpecificHeatsRatio; }

    public void setInletSpecificHeatsRatio(String inletSpecificHeatsRatio) { this.inletSpecificHeatsRatio = inletSpecificHeatsRatio; }

    public String getInletCompressibilityFactor() { return inletCompressibilityFactor; }

    public void setInletCompressibilityFactor(String inletCompressibilityFactor) { this.inletCompressibilityFactor = inletCompressibilityFactor; }

    public String getEstimateTravelUnits() { return estimateTravelUnits; }

    public void setEstimateTravelUnits(String estimateTravelUnits) { this.estimateTravelUnits = estimateTravelUnits; }

    public String getPressureRating() { return pressureRating; }

    public void setPressureRating(String pressureRating) { this.pressureRating = pressureRating; }

    public String getCageMaterial() { return cageMaterial; }

    public void setCageMaterial(String cageMaterial) { this.cageMaterial = cageMaterial; }

    public String getSpoolBalance() { return spoolBalance; }

    public void setSpoolBalance(String spoolBalance) { this.spoolBalance = spoolBalance; }

    public String getSettingPressure() { return settingPressure; }

    public void setSettingPressure(String settingPressure) { this.settingPressure = settingPressure; }

    public String getValtageRang() { return valtageRang; }

    public void setValtageRang(String valtageRang) { this.valtageRang = valtageRang; }

    public String getValtageRangUnit() { return valtageRangUnit; }

    public void setValtageRangUnit(String valtageRangUnit) { this.valtageRangUnit = valtageRangUnit; }

    public String getTapsType() { return tapsType; }

    public void setTapsType(String tapsType) { this.tapsType = tapsType; }

    public String getFireSafeDesign() { return fireSafeDesign; }

    public void setFireSafeDesign(String fireSafeDesign) { this.fireSafeDesign = fireSafeDesign; }

    public String getElecAirtoAction() { return elecAirtoAction; }

    public void setElecAirtoAction(String elecAirtoAction) { this.elecAirtoAction = elecAirtoAction; }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public String getRevisionNo() {
        return revisionNo;
    }

    public void setRevisionNo(String revisionNo) {
        this.revisionNo = revisionNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAreaClassification() {
        return areaClassification;
    }

    public void setAreaClassification(String areaClassification) {
        this.areaClassification = areaClassification;
    }

    public String getPipingNo() {
        return pipingNo;
    }

    public void setPipingNo(String pipingNo) {
        this.pipingNo = pipingNo;
    }

    public String getPipingClass() {
        return pipingClass;
    }

    public void setPipingClass(String pipingClass) {
        this.pipingClass = pipingClass;
    }

    public String getPipingMaterial() {
        return pipingMaterial;
    }

    public void setPipingMaterial(String pipingMaterial) {
        this.pipingMaterial = pipingMaterial;
    }

    public String getPidNo() {
        return pidNo;
    }

    public void setPidNo(String pidNo) {
        this.pidNo = pidNo;
    }

    public String getPipingSizeInlet() {
        return pipingSizeInlet;
    }

    public void setPipingSizeInlet(String pipingSizeInlet) {
        this.pipingSizeInlet = pipingSizeInlet;
    }

    public String getPipingSizeOutlet() {
        return pipingSizeOutlet;
    }

    public void setPipingSizeOutlet(String pipingSizeOutlet) {
        this.pipingSizeOutlet = pipingSizeOutlet;
    }

    public String getPipingSchInlet() {
        return pipingSchInlet;
    }

    public void setPipingSchInlet(String pipingSchInlet) {
        this.pipingSchInlet = pipingSchInlet;
    }

    public String getPipingSchOutlet() {
        return pipingSchOutlet;
    }

    public void setPipingSchOutlet(String pipingSchOutlet) {
        this.pipingSchOutlet = pipingSchOutlet;
    }

    public String getAmbientTemperatureRange() {
        return ambientTemperatureRange;
    }

    public void setAmbientTemperatureRange(String ambientTemperatureRange) {
        this.ambientTemperatureRange = ambientTemperatureRange;
    }

    public String getFluidState() {
        return fluidState;
    }

    public void setFluidState(String fluidState) {
        this.fluidState = fluidState;
    }

    public String getFluidStateExplain() {
        return fluidStateExplain;
    }

    public void setFluidStateExplain(String fluidStateExplain) {
        this.fluidStateExplain = fluidStateExplain;
    }

    public String getCriticalPressureUnit() {
        return criticalPressureUnit;
    }

    public void setCriticalPressureUnit(String criticalPressureUnit) {
        this.criticalPressureUnit = criticalPressureUnit;
    }

    public String getCriticalPressure() {
        return criticalPressure;
    }

    public void setCriticalPressure(String criticalPressure) {
        this.criticalPressure = criticalPressure;
    }

    public String getCriticalTemperUnits() {
        return criticalTemperUnits;
    }

    public void setCriticalTemperUnits(String criticalTemperUnits) {
        this.criticalTemperUnits = criticalTemperUnits;
    }

    public String getCriticalTemper() {
        return criticalTemper;
    }

    public void setCriticalTemper(String criticalTemper) {
        this.criticalTemper = criticalTemper;
    }

    public String getCloseDifferentPressureUnits() {
        return closeDifferentPressureUnits;
    }

    public void setCloseDifferentPressureUnits(String closeDifferentPressureUnits) {
        this.closeDifferentPressureUnits = closeDifferentPressureUnits;
    }

    public String getCloseDifferentPressure() {
        return closeDifferentPressure;
    }

    public void setCloseDifferentPressure(String closeDifferentPressure) {
        this.closeDifferentPressure = closeDifferentPressure;
    }

    public String getDesignPressure() {
        return designPressure;
    }

    public void setDesignPressure(String designPressure) {
        this.designPressure = designPressure;
    }

    public String getDesignPressureUnit() {
        return designPressureUnit;
    }

    public void setDesignPressureUnit(String designPressureUnit) {
        this.designPressureUnit = designPressureUnit;
    }

    public String getDesignTemperature() {
        return designTemperature;
    }

    public void setDesignTemperature(String designTemperature) {
        this.designTemperature = designTemperature;
    }

    public String getDesignTemperatureUnit() {
        return designTemperatureUnit;
    }

    public void setDesignTemperatureUnit(String designTemperatureUnit) {
        this.designTemperatureUnit = designTemperatureUnit;
    }

    public String getFlowRateMax() {
        return flowRateMax;
    }

    public void setFlowRateMax(String flowRateMax) {
        this.flowRateMax = flowRateMax;
    }

    public String getFlowRateNor() {
        return flowRateNor;
    }

    public void setFlowRateNor(String flowRateNor) {
        this.flowRateNor = flowRateNor;
    }

    public String getFlowRateMin() {
        return flowRateMin;
    }

    public void setFlowRateMin(String flowRateMin) {
        this.flowRateMin = flowRateMin;
    }

    public String getFlowCondition1() {
        return flowCondition1;
    }

    public void setFlowCondition1(String flowCondition1) {
        this.flowCondition1 = flowCondition1;
    }

    public String getFlowCondition2() {
        return flowCondition2;
    }

    public void setFlowCondition2(String flowCondition2) {
        this.flowCondition2 = flowCondition2;
    }

    public String getFlowCondition3() {
        return flowCondition3;
    }

    public void setFlowCondition3(String flowCondition3) {
        this.flowCondition3 = flowCondition3;
    }

    public String getFlowCondition4() {
        return flowCondition4;
    }

    public void setFlowCondition4(String flowCondition4) {
        this.flowCondition4 = flowCondition4;
    }

    public String getInletPressureMax() {
        return inletPressureMax;
    }

    public void setInletPressureMax(String inletPressureMax) {
        this.inletPressureMax = inletPressureMax;
    }

    public String getInletPressureNor() {
        return inletPressureNor;
    }

    public void setInletPressureNor(String inletPressureNor) {
        this.inletPressureNor = inletPressureNor;
    }

    public String getInletPressureMin() {
        return inletPressureMin;
    }

    public void setInletPressureMin(String inletPressureMin) {
        this.inletPressureMin = inletPressureMin;
    }

    public String getInletPressureCondition1() {
        return inletPressureCondition1;
    }

    public void setInletPressureCondition1(String inletPressureCondition1) {
        this.inletPressureCondition1 = inletPressureCondition1;
    }

    public String getInletPressureCondition2() {
        return inletPressureCondition2;
    }

    public void setInletPressureCondition2(String inletPressureCondition2) {
        this.inletPressureCondition2 = inletPressureCondition2;
    }

    public String getInletPressureCondition3() {
        return inletPressureCondition3;
    }

    public void setInletPressureCondition3(String inletPressureCondition3) {
        this.inletPressureCondition3 = inletPressureCondition3;
    }

    public String getInletPressureCondition4() {
        return inletPressureCondition4;
    }

    public void setInletPressureCondition4(String inletPressureCondition4) {
        this.inletPressureCondition4 = inletPressureCondition4;
    }

    public String getOutletPressureMax() {
        return outletPressureMax;
    }

    public void setOutletPressureMax(String outletPressureMax) {
        this.outletPressureMax = outletPressureMax;
    }

    public String getOutletPressureNor() {
        return outletPressureNor;
    }

    public void setOutletPressureNor(String outletPressureNor) {
        this.outletPressureNor = outletPressureNor;
    }

    public String getOutletPressureMin() {
        return outletPressureMin;
    }

    public void setOutletPressureMin(String outletPressureMin) {
        this.outletPressureMin = outletPressureMin;
    }

    public String getOutletPressureCondition1() {
        return outletPressureCondition1;
    }

    public void setOutletPressureCondition1(String outletPressureCondition1) {
        this.outletPressureCondition1 = outletPressureCondition1;
    }

    public String getOutletPressureCondition2() {
        return outletPressureCondition2;
    }

    public void setOutletPressureCondition2(String outletPressureCondition2) {
        this.outletPressureCondition2 = outletPressureCondition2;
    }

    public String getOutletPressureCondition3() {
        return outletPressureCondition3;
    }

    public void setOutletPressureCondition3(String outletPressureCondition3) {
        this.outletPressureCondition3 = outletPressureCondition3;
    }

    public String getOutletPressureCondition4() {
        return outletPressureCondition4;
    }

    public void setOutletPressureCondition4(String outletPressureCondition4) {
        this.outletPressureCondition4 = outletPressureCondition4;
    }

    public String getMaxDifferentPressure() {
        return maxDifferentPressure;
    }

    public void setMaxDifferentPressure(String maxDifferentPressure) {
        this.maxDifferentPressure = maxDifferentPressure;
    }

    public String getNorDifferentPressure() {
        return norDifferentPressure;
    }

    public void setNorDifferentPressure(String norDifferentPressure) {
        this.norDifferentPressure = norDifferentPressure;
    }

    public String getMinDifferentPressure() {
        return minDifferentPressure;
    }

    public void setMinDifferentPressure(String minDifferentPressure) {
        this.minDifferentPressure = minDifferentPressure;
    }

    public String getDifferentPressureOne() {
        return differentPressureOne;
    }

    public void setDifferentPressureOne(String differentPressureOne) {
        this.differentPressureOne = differentPressureOne;
    }

    public String getDifferentPressureTwo() {
        return differentPressureTwo;
    }

    public void setDifferentPressureTwo(String differentPressureTwo) {
        this.differentPressureTwo = differentPressureTwo;
    }

    public String getDifferentPressureThree() {
        return differentPressureThree;
    }

    public void setDifferentPressureThree(String differentPressureThree) {
        this.differentPressureThree = differentPressureThree;
    }

    public String getDifferentPressurefour() {
        return differentPressurefour;
    }

    public void setDifferentPressurefour(String differentPressurefour) {
        this.differentPressurefour = differentPressurefour;
    }

    public String getInletTemperatureMax() {
        return inletTemperatureMax;
    }

    public void setInletTemperatureMax(String inletTemperatureMax) {
        this.inletTemperatureMax = inletTemperatureMax;
    }

    public String getInletTemperatureNor() {
        return inletTemperatureNor;
    }

    public void setInletTemperatureNor(String inletTemperatureNor) {
        this.inletTemperatureNor = inletTemperatureNor;
    }

    public String getInletTemperatureMin() {
        return inletTemperatureMin;
    }

    public void setInletTemperatureMin(String inletTemperatureMin) {
        this.inletTemperatureMin = inletTemperatureMin;
    }

    public String getInletTemperatureCondition1() {
        return inletTemperatureCondition1;
    }

    public void setInletTemperatureCondition1(String inletTemperatureCondition1) {
        this.inletTemperatureCondition1 = inletTemperatureCondition1;
    }

    public String getInletTemperatureCondition2() {
        return inletTemperatureCondition2;
    }

    public void setInletTemperatureCondition2(String inletTemperatureCondition2) {
        this.inletTemperatureCondition2 = inletTemperatureCondition2;
    }

    public String getInletTemperatureCondition3() {
        return inletTemperatureCondition3;
    }

    public void setInletTemperatureCondition3(String inletTemperatureCondition3) {
        this.inletTemperatureCondition3 = inletTemperatureCondition3;
    }

    public String getInletTemperatureCondition4() {
        return inletTemperatureCondition4;
    }

    public void setInletTemperatureCondition4(String inletTemperatureCondition4) {
        this.inletTemperatureCondition4 = inletTemperatureCondition4;
    }

    public String getSpecificGravityMax() {
        return specificGravityMax;
    }

    public void setSpecificGravityMax(String specificGravityMax) {
        this.specificGravityMax = specificGravityMax;
    }

    public String getSpecificGravityNor() {
        return specificGravityNor;
    }

    public void setSpecificGravityNor(String specificGravityNor) {
        this.specificGravityNor = specificGravityNor;
    }

    public String getSpecificGravityMin() {
        return specificGravityMin;
    }

    public void setSpecificGravityMin(String specificGravityMin) {
        this.specificGravityMin = specificGravityMin;
    }

    public String getSpecificGravityCondition1() {
        return specificGravityCondition1;
    }

    public void setSpecificGravityCondition1(String specificGravityCondition1) {
        this.specificGravityCondition1 = specificGravityCondition1;
    }

    public String getSpecificGravityCondition2() {
        return specificGravityCondition2;
    }

    public void setSpecificGravityCondition2(String specificGravityCondition2) {
        this.specificGravityCondition2 = specificGravityCondition2;
    }

    public String getSpecificGravityCondition3() {
        return specificGravityCondition3;
    }

    public void setSpecificGravityCondition3(String specificGravityCondition3) {
        this.specificGravityCondition3 = specificGravityCondition3;
    }

    public String getSpecificGravityCondition4() {
        return specificGravityCondition4;
    }

    public void setSpecificGravityCondition4(String specificGravityCondition4) {
        this.specificGravityCondition4 = specificGravityCondition4;
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

    public String getOperaDensityCondition1() {
        return operaDensityCondition1;
    }

    public void setOperaDensityCondition1(String operaDensityCondition1) {
        this.operaDensityCondition1 = operaDensityCondition1;
    }

    public String getOperaDensityCondition2() {
        return operaDensityCondition2;
    }

    public void setOperaDensityCondition2(String operaDensityCondition2) {
        this.operaDensityCondition2 = operaDensityCondition2;
    }

    public String getOperaDensityCondition3() {
        return operaDensityCondition3;
    }

    public void setOperaDensityCondition3(String operaDensityCondition3) {
        this.operaDensityCondition3 = operaDensityCondition3;
    }

    public String getOperaDensityCondition4() {
        return operaDensityCondition4;
    }

    public void setOperaDensityCondition4(String operaDensityCondition4) {
        this.operaDensityCondition4 = operaDensityCondition4;
    }

    public String getInletViscosityMax() {
        return inletViscosityMax;
    }

    public void setInletViscosityMax(String inletViscosityMax) {
        this.inletViscosityMax = inletViscosityMax;
    }

    public String getInletViscosityNor() {
        return inletViscosityNor;
    }

    public void setInletViscosityNor(String inletViscosityNor) {
        this.inletViscosityNor = inletViscosityNor;
    }

    public String getInletViscosityMin() {
        return inletViscosityMin;
    }

    public void setInletViscosityMin(String inletViscosityMin) {
        this.inletViscosityMin = inletViscosityMin;
    }

    public String getInletViscosityCondition1() {
        return inletViscosityCondition1;
    }

    public void setInletViscosityCondition1(String inletViscosityCondition1) {
        this.inletViscosityCondition1 = inletViscosityCondition1;
    }

    public String getInletViscosityCondition2() {
        return inletViscosityCondition2;
    }

    public void setInletViscosityCondition2(String inletViscosityCondition2) {
        this.inletViscosityCondition2 = inletViscosityCondition2;
    }

    public String getInletViscosityCondition3() {
        return inletViscosityCondition3;
    }

    public void setInletViscosityCondition3(String inletViscosityCondition3) {
        this.inletViscosityCondition3 = inletViscosityCondition3;
    }

    public String getInletViscosityCondition4() {
        return inletViscosityCondition4;
    }

    public void setInletViscosityCondition4(String inletViscosityCondition4) {
        this.inletViscosityCondition4 = inletViscosityCondition4;
    }

    public String getInletVaporPressureMax() {
        return inletVaporPressureMax;
    }

    public void setInletVaporPressureMax(String inletVaporPressureMax) {
        this.inletVaporPressureMax = inletVaporPressureMax;
    }

    public String getInletVaporPressureNor() {
        return inletVaporPressureNor;
    }

    public void setInletVaporPressureNor(String inletVaporPressureNor) {
        this.inletVaporPressureNor = inletVaporPressureNor;
    }

    public String getInletVaporPressureMin() {
        return inletVaporPressureMin;
    }

    public void setInletVaporPressureMin(String inletVaporPressureMin) {
        this.inletVaporPressureMin = inletVaporPressureMin;
    }

    public String getInletVaporPressureCondition1() {
        return inletVaporPressureCondition1;
    }

    public void setInletVaporPressureCondition1(String inletVaporPressureCondition1) {
        this.inletVaporPressureCondition1 = inletVaporPressureCondition1;
    }

    public String getInletVaporPressureCondition2() {
        return inletVaporPressureCondition2;
    }

    public void setInletVaporPressureCondition2(String inletVaporPressureCondition2) {
        this.inletVaporPressureCondition2 = inletVaporPressureCondition2;
    }

    public String getInletVaporPressureCondition3() {
        return inletVaporPressureCondition3;
    }

    public void setInletVaporPressureCondition3(String inletVaporPressureCondition3) {
        this.inletVaporPressureCondition3 = inletVaporPressureCondition3;
    }

    public String getInletVaporPressureCondition4() {
        return inletVaporPressureCondition4;
    }

    public void setInletVaporPressureCondition4(String inletVaporPressureCondition4) {
        this.inletVaporPressureCondition4 = inletVaporPressureCondition4;
    }

    public String getInletCompressibilityFactorMax() {
        return inletCompressibilityFactorMax;
    }

    public void setInletCompressibilityFactorMax(String inletCompressibilityFactorMax) {
        this.inletCompressibilityFactorMax = inletCompressibilityFactorMax;
    }

    public String getInletCompressibilityFactorNor() {
        return inletCompressibilityFactorNor;
    }

    public void setInletCompressibilityFactorNor(String inletCompressibilityFactorNor) {
        this.inletCompressibilityFactorNor = inletCompressibilityFactorNor;
    }

    public String getInletCompressibilityFactorMin() {
        return inletCompressibilityFactorMin;
    }

    public void setInletCompressibilityFactorMin(String inletCompressibilityFactorMin) {
        this.inletCompressibilityFactorMin = inletCompressibilityFactorMin;
    }

    public String getInletCompressibilityFactorCondition1() {
        return inletCompressibilityFactorCondition1;
    }

    public void setInletCompressibilityFactorCondition1(String inletCompressibilityFactorCondition1) {
        this.inletCompressibilityFactorCondition1 = inletCompressibilityFactorCondition1;
    }

    public String getInletCompressibilityFactorCondition2() {
        return inletCompressibilityFactorCondition2;
    }

    public void setInletCompressibilityFactorCondition2(String inletCompressibilityFactorCondition2) {
        this.inletCompressibilityFactorCondition2 = inletCompressibilityFactorCondition2;
    }

    public String getInletCompressibilityFactorCondition3() {
        return inletCompressibilityFactorCondition3;
    }

    public void setInletCompressibilityFactorCondition3(String inletCompressibilityFactorCondition3) {
        this.inletCompressibilityFactorCondition3 = inletCompressibilityFactorCondition3;
    }

    public String getInletCompressibilityFactorCondition4() {
        return inletCompressibilityFactorCondition4;
    }

    public void setInletCompressibilityFactorCondition4(String inletCompressibilityFactorCondition4) {
        this.inletCompressibilityFactorCondition4 = inletCompressibilityFactorCondition4;
    }

    public String getFlowConditionMax() {
        return flowConditionMax;
    }

    public void setFlowConditionMax(String flowConditionMax) {
        this.flowConditionMax = flowConditionMax;
    }

    public String getFlowConditionNor() {
        return flowConditionNor;
    }

    public void setFlowConditionNor(String flowConditionNor) {
        this.flowConditionNor = flowConditionNor;
    }

    public String getFlowConditionMin() {
        return flowConditionMin;
    }

    public void setFlowConditionMin(String flowConditionMin) {
        this.flowConditionMin = flowConditionMin;
    }

    public String getFlowConditionOne() {
        return flowConditionOne;
    }

    public void setFlowConditionOne(String flowConditionOne) {
        this.flowConditionOne = flowConditionOne;
    }

    public String getFlowConditionTwo() {
        return flowConditionTwo;
    }

    public void setFlowConditionTwo(String flowConditionTwo) {
        this.flowConditionTwo = flowConditionTwo;
    }

    public String getFlowConditionThree() {
        return flowConditionThree;
    }

    public void setFlowConditionThree(String flowConditionThree) {
        this.flowConditionThree = flowConditionThree;
    }

    public String getFlowConditionFour() {
        return flowConditionFour;
    }

    public void setFlowConditionFour(String flowConditionFour) {
        this.flowConditionFour = flowConditionFour;
    }

    public String getFlowCoefficientCvMax() {
        return flowCoefficientCvMax;
    }

    public void setFlowCoefficientCvMax(String flowCoefficientCvMax) {
        this.flowCoefficientCvMax = flowCoefficientCvMax;
    }

    public String getFlowCoefficientCvNor() {
        return flowCoefficientCvNor;
    }

    public void setFlowCoefficientCvNor(String flowCoefficientCvNor) {
        this.flowCoefficientCvNor = flowCoefficientCvNor;
    }

    public String getFlowCoefficientCvMin() {
        return flowCoefficientCvMin;
    }

    public void setFlowCoefficientCvMin(String flowCoefficientCvMin) {
        this.flowCoefficientCvMin = flowCoefficientCvMin;
    }

    public String getFlowCoefficientCvOne() {
        return flowCoefficientCvOne;
    }

    public void setFlowCoefficientCvOne(String flowCoefficientCvOne) {
        this.flowCoefficientCvOne = flowCoefficientCvOne;
    }

    public String getFlowCoefficientCvTwo() {
        return flowCoefficientCvTwo;
    }

    public void setFlowCoefficientCvTwo(String flowCoefficientCvTwo) {
        this.flowCoefficientCvTwo = flowCoefficientCvTwo;
    }

    public String getFlowCoefficientCvThree() {
        return flowCoefficientCvThree;
    }

    public void setFlowCoefficientCvThree(String flowCoefficientCvThree) {
        this.flowCoefficientCvThree = flowCoefficientCvThree;
    }

    public String getFlowCoefficientCvFour() {
        return flowCoefficientCvFour;
    }

    public void setFlowCoefficientCvFour(String flowCoefficientCvFour) {
        this.flowCoefficientCvFour = flowCoefficientCvFour;
    }

    public String getEstimateTravelMax() {
        return estimateTravelMax;
    }

    public void setEstimateTravelMax(String estimateTravelMax) {
        this.estimateTravelMax = estimateTravelMax;
    }

    public String getEstimateTravelNor() {
        return estimateTravelNor;
    }

    public void setEstimateTravelNor(String estimateTravelNor) {
        this.estimateTravelNor = estimateTravelNor;
    }

    public String getEstimateTravelMin() {
        return estimateTravelMin;
    }

    public void setEstimateTravelMin(String estimateTravelMin) {
        this.estimateTravelMin = estimateTravelMin;
    }

    public String getEstimateTravelOne() {
        return estimateTravelOne;
    }

    public void setEstimateTravelOne(String estimateTravelOne) {
        this.estimateTravelOne = estimateTravelOne;
    }

    public String getEstimateTravelTwo() {
        return estimateTravelTwo;
    }

    public void setEstimateTravelTwo(String estimateTravelTwo) {
        this.estimateTravelTwo = estimateTravelTwo;
    }

    public String getEstimateTravelThree() {
        return estimateTravelThree;
    }

    public void setEstimateTravelThree(String estimateTravelThree) {
        this.estimateTravelThree = estimateTravelThree;
    }

    public String getEstimateTravelFour() {
        return estimateTravelFour;
    }

    public void setEstimateTravelFour(String estimateTravelFour) {
        this.estimateTravelFour = estimateTravelFour;
    }

    public String getEstimatedNoiseMax() {
        return estimatedNoiseMax;
    }

    public void setEstimatedNoiseMax(String estimatedNoiseMax) {
        this.estimatedNoiseMax = estimatedNoiseMax;
    }

    public String getEstimatedNoiseNor() {
        return estimatedNoiseNor;
    }

    public void setEstimatedNoiseNor(String estimatedNoiseNor) {
        this.estimatedNoiseNor = estimatedNoiseNor;
    }

    public String getEstimatedNoiseMin() {
        return estimatedNoiseMin;
    }

    public void setEstimatedNoiseMin(String estimatedNoiseMin) {
        this.estimatedNoiseMin = estimatedNoiseMin;
    }

    public String getEstimatedNoiseOne() {
        return estimatedNoiseOne;
    }

    public void setEstimatedNoiseOne(String estimatedNoiseOne) {
        this.estimatedNoiseOne = estimatedNoiseOne;
    }

    public String getEstimatedNoiseTwo() {
        return estimatedNoiseTwo;
    }

    public void setEstimatedNoiseTwo(String estimatedNoiseTwo) {
        this.estimatedNoiseTwo = estimatedNoiseTwo;
    }

    public String getEstimatedNoiseThree() {
        return estimatedNoiseThree;
    }

    public void setEstimatedNoiseThree(String estimatedNoiseThree) {
        this.estimatedNoiseThree = estimatedNoiseThree;
    }

    public String getEstimatedNoiseFour() {
        return estimatedNoiseFour;
    }

    public void setEstimatedNoiseFour(String estimatedNoiseFour) {
        this.estimatedNoiseFour = estimatedNoiseFour;
    }

    public String getValveBodyType() {
        return valveBodyType;
    }

    public void setValveBodyType(String valveBodyType) {
        this.valveBodyType = valveBodyType;
    }

    public String getValveBodyModel() {
        return valveBodyModel;
    }

    public void setValveBodyModel(String valveBodyModel) {
        this.valveBodyModel = valveBodyModel;
    }

    public String getInsideNominalDiameter() {
        return insideNominalDiameter;
    }

    public void setInsideNominalDiameter(String insideNominalDiameter) {
        this.insideNominalDiameter = insideNominalDiameter;
    }

    public String getSeatDiameter2016() {
        return seatDiameter2016;
    }

    public void setSeatDiameter2016(String seatDiameter2016) {
        this.seatDiameter2016 = seatDiameter2016;
    }

    public String getFlowCharacteristics() {
        return flowCharacteristics;
    }

    public void setFlowCharacteristics(String flowCharacteristics) {
        this.flowCharacteristics = flowCharacteristics;
    }

    public String getRatedCV() {
        return ratedCV;
    }

    public void setRatedCV(String ratedCV) {
        this.ratedCV = ratedCV;
    }

    public String getPressureGrade() {
        return pressureGrade;
    }

    public void setPressureGrade(String pressureGrade) {
        this.pressureGrade = pressureGrade;
    }

    public String getFlangedType() {
        return flangedType;
    }

    public void setFlangedType(String flangedType) {
        this.flangedType = flangedType;
    }

    public String getFlowDirection() {
        return flowDirection;
    }

    public void setFlowDirection(String flowDirection) {
        this.flowDirection = flowDirection;
    }

    public String getBonnetType() {
        return bonnetType;
    }

    public void setBonnetType(String bonnetType) {
        this.bonnetType = bonnetType;
    }

    public String getTrimType() {
        return trimType;
    }

    public void setTrimType(String trimType) {
        this.trimType = trimType;
    }

    public String getBodyMaterial() {
        return bodyMaterial;
    }

    public void setBodyMaterial(String bodyMaterial) {
        this.bodyMaterial = bodyMaterial;
    }

    public String getCoreMaterial() {
        return coreMaterial;
    }

    public void setCoreMaterial(String coreMaterial) {
        this.coreMaterial = coreMaterial;
    }

    public String getSeatMaterial() {
        return seatMaterial;
    }

    public void setSeatMaterial(String seatMaterial) {
        this.seatMaterial = seatMaterial;
    }

    public String getSpindleMaterial() {
        return spindleMaterial;
    }

    public void setSpindleMaterial(String spindleMaterial) {
        this.spindleMaterial = spindleMaterial;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getFillType() {
        return fillType;
    }

    public void setFillType(String fillType) {
        this.fillType = fillType;
    }

    public String getLeakageLevel() {
        return leakageLevel;
    }

    public void setLeakageLevel(String leakageLevel) {
        this.leakageLevel = leakageLevel;
    }

    public String getRatedTravel() {
        return ratedTravel;
    }

    public void setRatedTravel(String ratedTravel) {
        this.ratedTravel = ratedTravel;
    }

    public String getElectricActuatorType() {
        return electricActuatorType;
    }

    public void setElectricActuatorType(String electricActuatorType) {
        this.electricActuatorType = electricActuatorType;
    }



    public String getPowerSupply() {
        return powerSupply;
    }

    public String getActuatorModel() {
        return actuatorModel;
    }

    public void setActuatorModel(String actuatorModel) {
        this.actuatorModel = actuatorModel;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public String getControlUnit() {
        return controlUnit;
    }

    public void setControlUnit(String controlUnit) {
        this.controlUnit = controlUnit;
    }

    public String getLimitSwitch() {
        return limitSwitch;
    }

    public void setLimitSwitch(String limitSwitch) {
        this.limitSwitch = limitSwitch;
    }

    public String getInputSignal() {
        return inputSignal;
    }

    public void setInputSignal(String inputSignal) {
        this.inputSignal = inputSignal;
    }

    public String getOutputSignal() {
        return outputSignal;
    }

    public void setOutputSignal(String outputSignal) {
        this.outputSignal = outputSignal;
    }

    public String getElecProtection() {
        return elecProtection;
    }

    public void setElecProtection(String elecProtection) {
        this.elecProtection = elecProtection;
    }

    public String getElectricEXPClass() {
        return electricEXPClass;
    }

    public void setElectricEXPClass(String electricEXPClass) {
        this.electricEXPClass = electricEXPClass;
    }

    public String getElecHandwheelLocation() {
        return elecHandwheelLocation;
    }

    public void setElecHandwheelLocation(String elecHandwheelLocation) {
        this.elecHandwheelLocation = elecHandwheelLocation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTsyqName1() {
        return tsyqName1;
    }

    public void setTsyqName1(String tsyqName1) {
        this.tsyqName1 = tsyqName1;
    }

    public String getTsyqName2() {
        return tsyqName2;
    }

    public void setTsyqName2(String tsyqName2) {
        this.tsyqName2 = tsyqName2;
    }

    public String getTsyqName3() {
        return tsyqName3;
    }

    public void setTsyqName3(String tsyqName3) {
        this.tsyqName3 = tsyqName3;
    }

    public String getTsyqName4() {
        return tsyqName4;
    }

    public void setTsyqName4(String tsyqName4) {
        this.tsyqName4 = tsyqName4;
    }

    public String getTsyqName5() {
        return tsyqName5;
    }

    public void setTsyqName5(String tsyqName5) {
        this.tsyqName5 = tsyqName5;
    }

    public String getTsyqName6() {
        return tsyqName6;
    }

    public void setTsyqName6(String tsyqName6) {
        this.tsyqName6 = tsyqName6;
    }

    public String getTsyqName7() {
        return tsyqName7;
    }

    public void setTsyqName7(String tsyqName7) {
        this.tsyqName7 = tsyqName7;
    }

    public String getTsyqName8() {
        return tsyqName8;
    }

    public void setTsyqName8(String tsyqName8) {
        this.tsyqName8 = tsyqName8;
    }

    public String getTsyqName9() {
        return tsyqName9;
    }

    public void setTsyqName9(String tsyqName9) {
        this.tsyqName9 = tsyqName9;
    }

    public String getTsyqName10() {
        return tsyqName10;
    }

    public void setTsyqName10(String tsyqName10) {
        this.tsyqName10 = tsyqName10;
    }

    public String getTsyqName11() {
        return tsyqName11;
    }

    public void setTsyqName11(String tsyqName11) {
        this.tsyqName11 = tsyqName11;
    }

    public String getTsyqName12() {
        return tsyqName12;
    }

    public void setTsyqName12(String tsyqName12) {
        this.tsyqName12 = tsyqName12;
    }

    public String getRemark2016One() {
        return remark2016One;
    }

    public void setRemark2016One(String remark2016One) {
        this.remark2016One = remark2016One;
    }

    public String getRemark2016Two() {
        return remark2016Two;
    }

    public void setRemark2016Two(String remark2016Two) {
        this.remark2016Two = remark2016Two;
    }

    public String getPositionerType() {
        return positionerType;
    }

    public void setPositionerType(String positionerType) {
        this.positionerType = positionerType;
    }

    public String getLocalizerType() {
        return localizerType;
    }

    public void setLocalizerType(String localizerType) {
        this.localizerType = localizerType;
    }

    public String getInputRange() {
        return inputRange;
    }

    public void setInputRange(String inputRange) {
        this.inputRange = inputRange;
    }


    public String getPositionerOutputCharacteristics() {
        return positionerOutputCharacteristics;
    }

    public void setPositionerOutputCharacteristics(String positionerOutputCharacteristics) {
        this.positionerOutputCharacteristics = positionerOutputCharacteristics;
    }

    public String getLocalizerExplosiveProof() {
        return localizerExplosiveProof;
    }

    public void setLocalizerExplosiveProof(String localizerExplosiveProof) {
        this.localizerExplosiveProof = localizerExplosiveProof;
    }

    public String getDwqDQJK() {
        return dwqDQJK;
    }

    public void setDwqDQJK(String dwqDQJK) {
        this.dwqDQJK = dwqDQJK;
    }

    public String getDwqQYJK() {
        return dwqQYJK;
    }

    public void setDwqQYJK(String dwqQYJK) {
        this.dwqQYJK = dwqQYJK;
    }

    public String getLocalizerManufacturer() {
        return localizerManufacturer;
    }

    public void setLocalizerManufacturer(String localizerManufacturer) {
        this.localizerManufacturer = localizerManufacturer;
    }

    public String getSolenoidType() {
        return solenoidType;
    }

    public void setSolenoidType(String solenoidType) {
        this.solenoidType = solenoidType;
    }

    public String getSolenoidPower() {
        return solenoidPower;
    }

    public void setSolenoidPower(String solenoidPower) {
        this.solenoidPower = solenoidPower;
    }

    public String getSolenoidSupply() {
        return solenoidSupply;
    }

    public void setSolenoidSupply(String solenoidSupply) {
        this.solenoidSupply = solenoidSupply;
    }

    public String getSolenoidMaterial() {
        return solenoidMaterial;
    }

    public void setSolenoidMaterial(String solenoidMaterial) {
        this.solenoidMaterial = solenoidMaterial;
    }

    public String getSolenoidQuantity() {
        return solenoidQuantity;
    }

    public void setSolenoidQuantity(String solenoidQuantity) {
        this.solenoidQuantity = solenoidQuantity;
    }

    public String getSolenoidValveType() {
        return solenoidValveType;
    }

    public void setSolenoidValveType(String solenoidValveType) {
        this.solenoidValveType = solenoidValveType;
    }

    public String getSolenoidActionMode() {
        return solenoidActionMode;
    }

    public void setSolenoidActionMode(String solenoidActionMode) {
        this.solenoidActionMode = solenoidActionMode;
    }

    public String getSolenoidStructureType() {
        return solenoidStructureType;
    }

    public void setSolenoidStructureType(String solenoidStructureType) {
        this.solenoidStructureType = solenoidStructureType;
    }

    public String getOutage() {
        return outage;
    }

    public void setOutage(String outage) {
        this.outage = outage;
    }

    public String getDcfDQJK() {
        return dcfDQJK;
    }

    public void setDcfDQJK(String dcfDQJK) {
        this.dcfDQJK = dcfDQJK;
    }

    public String getSolenoidExplosionProofGrade() {
        return solenoidExplosionProofGrade;
    }

    public void setSolenoidExplosionProofGrade(String solenoidExplosionProofGrade) {
        this.solenoidExplosionProofGrade = solenoidExplosionProofGrade;
    }

    public String getDcfQYJK() {
        return dcfQYJK;
    }

    public void setDcfQYJK(String dcfQYJK) {
        this.dcfQYJK = dcfQYJK;
    }

    public String getSolenoidManufacturer() {
        return solenoidManufacturer;
    }

    public void setSolenoidManufacturer(String solenoidManufacturer) {
        this.solenoidManufacturer = solenoidManufacturer;
    }

    public String getLimitSwitchModelNo() {
        return limitSwitchModelNo;
    }

    public void setLimitSwitchModelNo(String limitSwitchModelNo) {
        this.limitSwitchModelNo = limitSwitchModelNo;
    }

    public String getLimitSwitchType() {
        return limitSwitchType;
    }

    public void setLimitSwitchType(String limitSwitchType) {
        this.limitSwitchType = limitSwitchType;
    }

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public String getLimitForm() {
        return limitForm;
    }

    public void setLimitForm(String limitForm) {
        this.limitForm = limitForm;
    }

    public String getLimitCapacity() {
        return limitCapacity;
    }

    public void setLimitCapacity(String limitCapacity) {
        this.limitCapacity = limitCapacity;
    }

    public String getXwkgDQJK() {
        return xwkgDQJK;
    }

    public void setXwkgDQJK(String xwkgDQJK) {
        this.xwkgDQJK = xwkgDQJK;
    }

    public String getLimitExplosionProofGrade() {
        return limitExplosionProofGrade;
    }

    public void setLimitExplosionProofGrade(String limitExplosionProofGrade) {
        this.limitExplosionProofGrade = limitExplosionProofGrade;
    }

    public String getLimitManufacturer() {
        return limitManufacturer;
    }

    public void setLimitManufacturer(String limitManufacturer) {
        this.limitManufacturer = limitManufacturer;
    }

    public String getPressureReliefModelNo() {
        return pressureReliefModelNo;
    }

    public void setPressureReliefModelNo(String pressureReliefModelNo) {
        this.pressureReliefModelNo = pressureReliefModelNo;
    }

    public String getPressureReliefType() {
        return pressureReliefType;
    }

    public void setPressureReliefType(String pressureReliefType) {
        this.pressureReliefType = pressureReliefType;
    }

    public String getPressureReliefQuantity() {
        return pressureReliefQuantity;
    }

    public void setPressureReliefQuantity(String pressureReliefQuantity) {
        this.pressureReliefQuantity = pressureReliefQuantity;
    }

    public String getPressureReliefGasSupplyPressureUnits() {
        return pressureReliefGasSupplyPressureUnits;
    }

    public void setPressureReliefGasSupplyPressureUnits(String pressureReliefGasSupplyPressureUnits) {
        this.pressureReliefGasSupplyPressureUnits = pressureReliefGasSupplyPressureUnits;
    }

    public String getPressureReliefGasSupplyPressure() {
        return pressureReliefGasSupplyPressure;
    }

    public void setPressureReliefGasSupplyPressure(String pressureReliefGasSupplyPressure) {
        this.pressureReliefGasSupplyPressure = pressureReliefGasSupplyPressure;
    }

    public String getJyfQYJK() {
        return jyfQYJK;
    }

    public void setJyfQYJK(String jyfQYJK) {
        this.jyfQYJK = jyfQYJK;
    }

    public String getPressureReliefManufacturer() {
        return pressureReliefManufacturer;
    }

    public void setPressureReliefManufacturer(String pressureReliefManufacturer) {
        this.pressureReliefManufacturer = pressureReliefManufacturer;
    }

    public String getGqylZxjg() {
        return gqylZxjg;
    }

    public void setGqylZxjg(String gqylZxjg) {
        this.gqylZxjg = gqylZxjg;
    }

    public String getRangeSpring() {
        return rangeSpring;
    }

    public void setRangeSpring(String rangeSpring) {
        this.rangeSpring = rangeSpring;
    }
    public String getAirFailurePosition() {
        return AirFailurePosition;
    }

    public void setAirFailurePosition(String airFailurePosition) {
        AirFailurePosition = airFailurePosition;
    }


    public String getAirtoAction() {
        return airtoAction;
    }

    public void setAirtoAction(String airtoAction) {
        this.airtoAction = airtoAction;
    }

    public String getPermissiblePressure() {
        return permissiblePressure;
    }

    public void setPermissiblePressure(String permissiblePressure) {
        this.permissiblePressure = permissiblePressure;
    }

    public String getAirChamberVolume() {
        return airChamberVolume;
    }

    public void setAirChamberVolume(String airChamberVolume) {
        this.airChamberVolume = airChamberVolume;
    }

    public String getHandWheel() {
        return handWheel;
    }

    public void setHandWheel(String handWheel) {
        this.handWheel = handWheel;
    }

    public String getMechanicalLimit() {
        return mechanicalLimit;
    }

    public void setMechanicalLimit(String mechanicalLimit) {
        this.mechanicalLimit = mechanicalLimit;
    }

    public String getElecActuatorOpenTime() {
        return elecActuatorOpenTime;
    }

    public void setElecActuatorOpenTime(String elecActuatorOpenTime) {
        this.elecActuatorOpenTime = elecActuatorOpenTime;
    }

    public String getElecActuatorClosingTime() {
        return elecActuatorClosingTime;
    }

    public void setElecActuatorClosingTime(String elecActuatorClosingTime) {
        this.elecActuatorClosingTime = elecActuatorClosingTime;
    }

    public String getAttachmentType1() {
        return attachmentType1;
    }

    public void setAttachmentType1(String attachmentType1) {
        this.attachmentType1 = attachmentType1;
    }

    public String getAttachmentType2() {
        return attachmentType2;
    }

    public void setAttachmentType2(String attachmentType2) {
        this.attachmentType2 = attachmentType2;
    }

    public String getAttachmentType3() {
        return attachmentType3;
    }

    public void setAttachmentType3(String attachmentType3) {
        this.attachmentType3 = attachmentType3;
    }

    public String getAttachmentType4() {
        return attachmentType4;
    }

    public void setAttachmentType4(String attachmentType4) {
        this.attachmentType4 = attachmentType4;
    }

    public String getAttachmentType5() {
        return attachmentType5;
    }

    public void setAttachmentType5(String attachmentType5) {
        this.attachmentType5 = attachmentType5;
    }

    public String getAttachmentType6() {
        return attachmentType6;
    }

    public void setAttachmentType6(String attachmentType6) {
        this.attachmentType6 = attachmentType6;
    }

    public String getAttachmentValue1() {
        return attachmentValue1;
    }

    public void setAttachmentValue1(String attachmentValue1) {
        this.attachmentValue1 = attachmentValue1;
    }

    public String getAttachmentValue2() {
        return attachmentValue2;
    }

    public void setAttachmentValue2(String attachmentValue2) {
        this.attachmentValue2 = attachmentValue2;
    }

    public String getAttachmentValue3() {
        return attachmentValue3;
    }

    public void setAttachmentValue3(String attachmentValue3) {
        this.attachmentValue3 = attachmentValue3;
    }

    public String getAttachmentValue4() {
        return attachmentValue4;
    }

    public void setAttachmentValue4(String attachmentValue4) {
        this.attachmentValue4 = attachmentValue4;
    }

    public String getAttachmentValue5() {
        return attachmentValue5;
    }

    public void setAttachmentValue5(String attachmentValue5) {
        this.attachmentValue5 = attachmentValue5;
    }

    public String getAttachmentValue6() {
        return attachmentValue6;
    }

    public void setAttachmentValue6(String attachmentValue6) {
        this.attachmentValue6 = attachmentValue6;
    }

    public String getCurUserName() {
        return curUserName;
    }

    public void setCurUserName(String curUserName) {
        this.curUserName = curUserName;
    }

    public String getLectotypeUser() {
        return lectotypeUser;
    }

    public void setLectotypeUser(String lectotypeUser) {
        this.lectotypeUser = lectotypeUser;
    }

    public String getLectotypeLead() {
        return lectotypeLead;
    }

    public void setLectotypeLead(String lectotypeLead) {
        this.lectotypeLead = lectotypeLead;
    }

    public String getLectotypeDate() {
        return lectotypeDate;
    }

    public void setLectotypeDate(String lectotypeDate) {
        this.lectotypeDate = lectotypeDate;
    }
    public String getOutPutRange() {
        return outPutRange;
    }

    public void setOutPutRange(String outPutRange) {
        this.outPutRange = outPutRange;
    }
    public String getBellowsForm() { return bellowsForm; }

    public void setBellowsForm(String bellowsForm) { this.bellowsForm = bellowsForm; }

    public String getBellowsMaterial() { return bellowsMaterial; }

    public void setBellowsMaterial(String bellowsMaterial) { this.bellowsMaterial = bellowsMaterial; }
    public String getQuotationNo() {
        return quotationNo;
    }

    public void setQuotationNo(String quotationNo) {
        this.quotationNo = quotationNo;
    }
    public String getFailurePosition() {
        return failurePosition;
    }

    public String getStandard() { return standard; }

    public void setStandard(String standard) { this.standard = standard; }

    public String getMold() { return mold; }

    public void setMold(String mold) { this.mold = mold; }

    public void setFailurePosition(String failurePosition) {
        this.failurePosition = failurePosition;
    }

    public String getFlowConditionUnit() {
        return flowConditionUnit;
    }

    public void setFlowConditionUnit(String flowConditionUnit) {
        this.flowConditionUnit = flowConditionUnit;
    }

    public String getDsMatenialName() {
        return dsMatenialName;
    }

    public void setDsMatenialName(String dsMatenialName) {
        this.dsMatenialName = dsMatenialName;
    }

    public String getDsMatenial() {
        return dsMatenial;
    }

    public void setDsMatenial(String dsMatenial) {
        this.dsMatenial = dsMatenial;
    }

    public String getBwgcz() {
        return bwgcz;
    }

    public void setBwgcz(String bwgcz) {
        this.bwgcz = bwgcz;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getContNoBig() {
        return contNoBig;
    }

    public void setContNoBig(String contNoBig) {
        this.contNoBig = contNoBig;
    }

    public String getProjectCode() { return projectCode; }

    public void setProjectCode(String projectCode) { this.projectCode = projectCode; }
}
