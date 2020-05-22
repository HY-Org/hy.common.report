package org.hy.common.report.junit.image;

import org.hy.common.xml.SerializableDef;





/**
 * 报表数据的封装类。与报表Excel模板对应
 *
 * @author      ZhengWei(HY)
 * @createDate  2019-05-30
 * @version     v1.0
 */
public class ImageReportBean extends SerializableDef
{

    private static final long serialVersionUID = -7577305494914976472L;

    /** 产品名称 */
	private String productName;
	
	/** 产品型号 */
	private String productModel;
	
	/** 合同号 */
	private String forderNo;
	
	/** 口径 */
	private String nominalDiameter;
	
	/** 压力等级 */
	private String pressClass;
	
	/** 法兰形式 */
	private String flangeType;
	
	/** 阀盖型式 */
	private String bonnetType;
	
	/** 执行机构型号 */
	private String paActuNo;
	
	/** 位号 */
	private String localNumber;
	
	/** 手轮机构 */
	private String handWheel;
	
	/** 数量 */
	private String quantity;
	
	/** 外形尺寸图路径 */
	private String outLinePathNum;
	
	/** 外形尺寸图路径 */
	private String notOutLinePathNum;
	
	/** 参数1 */
	private String paramKey1;
	
	/** 参数2 */
	private String paramKey2;
	
	/** 参数3 */
	private String paramKey3;
	
	/** 参数4 */
	private String paramKey4;
	
	/** 参数5 */
	private String paramKey5;
	
	/** 参数数值1 */
	private String paramValue1;
	
	/** 参数数值2 */
	private String paramValue2;
	
	/** 参数数值3 */
	private String paramValue3;
	
	/** 参数数值4 */
	private String paramValue4;
	
	/** 参数数值5 */
	private String paramValue5;
	
	/** 二维码图片 */
	private String imgPath;
	
	/** 动态公式 */
	private String dyForumla;

	
	
	/**
     * 调用指定顺序上对应的Setter方法，设置属性值
     * 
     * @param i_PropertyIndex
     * @param i_Value
     */
    public void setPropertyValue(int i_PropertyIndex ,Object i_Value)
    {
        super.setPropertyValue(i_PropertyIndex ,i_Value ,this);
    }
	
    
    /**
     * 获取：产品名称
     */
    public String getProductName()
    {
        return productName;
    }

    
    /**
     * 获取：产品型号
     */
    public String getProductModel()
    {
        return productModel;
    }

    
    /**
     * 获取：合同号
     */
    public String getForderNo()
    {
        return forderNo;
    }

    
    /**
     * 获取：口径
     */
    public String getNominalDiameter()
    {
        return nominalDiameter;
    }

    
    /**
     * 获取：压力等级
     */
    public String getPressClass()
    {
        return pressClass;
    }

    
    /**
     * 获取：法兰形式
     */
    public String getFlangeType()
    {
        return flangeType;
    }

    
    /**
     * 获取：阀盖型式
     */
    public String getBonnetType()
    {
        return bonnetType;
    }

    
    /**
     * 获取：执行机构型号
     */
    public String getPaActuNo()
    {
        return paActuNo;
    }

    
    /**
     * 获取：位号
     */
    public String getLocalNumber()
    {
        return localNumber;
    }

    
    /**
     * 获取：手轮机构
     */
    public String getHandWheel()
    {
        return handWheel;
    }

    
    /**
     * 获取：数量
     */
    public String getQuantity()
    {
        return quantity;
    }

    
    /**
     * 获取：外形尺寸图路径
     */
    public String getOutLinePathNum()
    {
        return outLinePathNum;
    }

    
    /**
     * 获取：外形尺寸图路径
     */
    public String getNotOutLinePathNum()
    {
        return notOutLinePathNum;
    }

    
    /**
     * 获取：参数1
     */
    public String getParamKey1()
    {
        return paramKey1;
    }

    
    /**
     * 获取：参数2
     */
    public String getParamKey2()
    {
        return paramKey2;
    }

    
    /**
     * 获取：参数3
     */
    public String getParamKey3()
    {
        return paramKey3;
    }

    
    /**
     * 获取：参数4
     */
    public String getParamKey4()
    {
        return paramKey4;
    }

    
    /**
     * 获取：参数数值1
     */
    public String getParamValue1()
    {
        return paramValue1;
    }

    
    /**
     * 获取：参数数值2
     */
    public String getParamValue2()
    {
        return paramValue2;
    }

    
    /**
     * 获取：参数数值3
     */
    public String getParamValue3()
    {
        return paramValue3;
    }

    
    /**
     * 获取：参数数值4
     */
    public String getParamValue4()
    {
        return paramValue4;
    }

    
    /**
     * 获取：参数数值5
     */
    public String getParamValue5()
    {
        return paramValue5;
    }

    
    /**
     * 设置：产品名称
     * 
     * @param productName 
     */
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    
    /**
     * 设置：产品型号
     * 
     * @param productModel 
     */
    public void setProductModel(String productModel)
    {
        this.productModel = productModel;
    }

    
    /**
     * 设置：合同号
     * 
     * @param forderNo 
     */
    public void setForderNo(String forderNo)
    {
        this.forderNo = forderNo;
    }

    
    /**
     * 设置：口径
     * 
     * @param nominalDiameter 
     */
    public void setNominalDiameter(String nominalDiameter)
    {
        this.nominalDiameter = nominalDiameter;
    }

    
    /**
     * 设置：压力等级
     * 
     * @param pressClass 
     */
    public void setPressClass(String pressClass)
    {
        this.pressClass = pressClass;
    }

    
    /**
     * 设置：法兰形式
     * 
     * @param flangeType 
     */
    public void setFlangeType(String flangeType)
    {
        this.flangeType = flangeType;
    }

    
    /**
     * 设置：阀盖型式
     * 
     * @param bonnetType 
     */
    public void setBonnetType(String bonnetType)
    {
        this.bonnetType = bonnetType;
    }

    
    /**
     * 设置：执行机构型号
     * 
     * @param paActuNo 
     */
    public void setPaActuNo(String paActuNo)
    {
        this.paActuNo = paActuNo;
    }

    
    /**
     * 设置：位号
     * 
     * @param localNumber 
     */
    public void setLocalNumber(String localNumber)
    {
        this.localNumber = localNumber;
    }

    
    /**
     * 设置：手轮机构
     * 
     * @param handWheel 
     */
    public void setHandWheel(String handWheel)
    {
        this.handWheel = handWheel;
    }

    
    /**
     * 设置：数量
     * 
     * @param quantity 
     */
    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    
    /**
     * 设置：外形尺寸图路径
     * 
     * @param outLinePathNum 
     */
    public void setOutLinePathNum(String outLinePathNum)
    {
        this.outLinePathNum = outLinePathNum;
    }

    
    /**
     * 设置：外形尺寸图路径
     * 
     * @param notOutLinePathNum 
     */
    public void setNotOutLinePathNum(String notOutLinePathNum)
    {
        this.notOutLinePathNum = notOutLinePathNum;
    }

    
    /**
     * 设置：参数1
     * 
     * @param paramKey1 
     */
    public void setParamKey1(String paramKey1)
    {
        this.paramKey1 = paramKey1;
    }

    
    /**
     * 设置：参数2
     * 
     * @param paramKey2 
     */
    public void setParamKey2(String paramKey2)
    {
        this.paramKey2 = paramKey2;
    }

    
    /**
     * 设置：参数3
     * 
     * @param paramKey3 
     */
    public void setParamKey3(String paramKey3)
    {
        this.paramKey3 = paramKey3;
    }

    
    /**
     * 设置：参数4
     * 
     * @param paramKey4 
     */
    public void setParamKey4(String paramKey4)
    {
        this.paramKey4 = paramKey4;
    }

    
    /**
     * 设置：参数数值1
     * 
     * @param paramValue1 
     */
    public void setParamValue1(String paramValue1)
    {
        this.paramValue1 = paramValue1;
    }

    
    /**
     * 设置：参数数值2
     * 
     * @param paramValue2 
     */
    public void setParamValue2(String paramValue2)
    {
        this.paramValue2 = paramValue2;
    }

    
    /**
     * 设置：参数数值3
     * 
     * @param paramValue3 
     */
    public void setParamValue3(String paramValue3)
    {
        this.paramValue3 = paramValue3;
    }

    
    /**
     * 设置：参数数值4
     * 
     * @param paramValue4 
     */
    public void setParamValue4(String paramValue4)
    {
        this.paramValue4 = paramValue4;
    }

    
    /**
     * 设置：参数数值5
     * 
     * @param paramValue5 
     */
    public void setParamValue5(String paramValue5)
    {
        this.paramValue5 = paramValue5;
    }


    /**
     * 获取：二维码图片
     */
    public String getImgPath()
    {
        return imgPath;
    }

    
    /**
     * 设置：二维码图片
     * 
     * @param imgPath 
     */
    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    
    /**
     * 获取：参数5
     */
    public String getParamKey5()
    {
        return paramKey5;
    }

    
    /**
     * 设置：参数5
     * 
     * @param paramKey5 
     */
    public void setParamKey5(String paramKey5)
    {
        this.paramKey5 = paramKey5;
    }

    
    /**
     * 获取：动态公式
     */
    public String getDyForumla()
    {
        return dyForumla;
    }


    /**
     * 设置：动态公式
     * 
     * @param dyForumla 
     */
    public void setDyForumla(String dyForumla)
    {
        this.dyForumla = dyForumla;
    }
	
}
