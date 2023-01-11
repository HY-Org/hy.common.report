package org.hy.common.report.junit.complex.config.reports.工艺过程记录卡.bean;

import java.util.ArrayList;
import java.util.List;





/**
 * 复杂报表的主对象
 *
 * @author      ZhengWei(HY)
 * @createDate  2022-12-10
 * @version     v1.0
 */
public class ComplexDataInfo
{
    
    /** 零件名称 */
    private String itemName;
    
    /** 零件材质 */
    private String material;
    
    /** 零件图号及版本 */
    private String itemCode;
    
    /** 工艺卡号 */
    private String processCardNo;
    
    /** 项目号 */
    private String projectCode;
    
    /** 产品名称 */
    private String productName;
    
    /** 产品型号 */
    private String productModel;
    
    /** 版次 */
    private String versionNo;
    
    /** 客户名称 */
    private String clientName;
    
    /** 项目名称 */
    private String projectName;
    
    /** 工艺编制 */
    private String organization;
    
    /** 工艺编制日期 */
    private String organizationTime;
    
    /** 工艺审核 */
    private String reviewer;
    
    /** 工艺审核日期 */
    private String reviewTime;
    
    /** 工艺批准 */
    private String approver;
    
    /** 工艺批准日期 */
    private String approveTime;
    
    /** 子集合数据 */
    private List<ComplexDataItem> childDatas;
    
    
    
    public ComplexDataInfo()
    {
        this.childDatas = new ArrayList<ComplexDataItem>();
    }
    
    
    /**
     * 获取：零件名称
     */
    public String getItemName()
    {
        return itemName;
    }

    
    /**
     * 设置：零件名称
     * 
     * @param i_ItemName 零件名称
     */
    public void setItemName(String i_ItemName)
    {
        this.itemName = i_ItemName;
    }

    
    /**
     * 获取：零件材质
     */
    public String getMaterial()
    {
        return material;
    }

    
    /**
     * 设置：零件材质
     * 
     * @param i_Material 零件材质
     */
    public void setMaterial(String i_Material)
    {
        this.material = i_Material;
    }

    
    /**
     * 获取：零件图号及版本
     */
    public String getItemCode()
    {
        return itemCode;
    }

    
    /**
     * 设置：零件图号及版本
     * 
     * @param i_ItemCode 零件图号及版本
     */
    public void setItemCode(String i_ItemCode)
    {
        this.itemCode = i_ItemCode;
    }

    
    /**
     * 获取：工艺卡号
     */
    public String getProcessCardNo()
    {
        return processCardNo;
    }

    
    /**
     * 设置：工艺卡号
     * 
     * @param i_ProcessCardNo 工艺卡号
     */
    public void setProcessCardNo(String i_ProcessCardNo)
    {
        this.processCardNo = i_ProcessCardNo;
    }

    
    /**
     * 获取：项目号
     */
    public String getProjectCode()
    {
        return projectCode;
    }

    
    /**
     * 设置：项目号
     * 
     * @param i_ProjectCode 项目号
     */
    public void setProjectCode(String i_ProjectCode)
    {
        this.projectCode = i_ProjectCode;
    }

    
    /**
     * 获取：产品名称
     */
    public String getProductName()
    {
        return productName;
    }

    
    /**
     * 设置：产品名称
     * 
     * @param i_ProductName 产品名称
     */
    public void setProductName(String i_ProductName)
    {
        this.productName = i_ProductName;
    }

    
    /**
     * 获取：产品型号
     */
    public String getProductModel()
    {
        return productModel;
    }

    
    /**
     * 设置：产品型号
     * 
     * @param i_ProductModel 产品型号
     */
    public void setProductModel(String i_ProductModel)
    {
        this.productModel = i_ProductModel;
    }

    
    /**
     * 获取：版次
     */
    public String getVersionNo()
    {
        return versionNo;
    }

    
    /**
     * 设置：版次
     * 
     * @param i_VersionNo 版次
     */
    public void setVersionNo(String i_VersionNo)
    {
        this.versionNo = i_VersionNo;
    }

    
    /**
     * 获取：客户名称
     */
    public String getClientName()
    {
        return clientName;
    }

    
    /**
     * 设置：客户名称
     * 
     * @param i_ClientName 客户名称
     */
    public void setClientName(String i_ClientName)
    {
        this.clientName = i_ClientName;
    }

    
    /**
     * 获取：项目名称
     */
    public String getProjectName()
    {
        return projectName;
    }

    
    /**
     * 设置：项目名称
     * 
     * @param i_ProjectName 项目名称
     */
    public void setProjectName(String i_ProjectName)
    {
        this.projectName = i_ProjectName;
    }

    
    /**
     * 获取：工艺编制
     */
    public String getOrganization()
    {
        return organization;
    }

    
    /**
     * 设置：工艺编制
     * 
     * @param i_Organization 工艺编制
     */
    public void setOrganization(String i_Organization)
    {
        this.organization = i_Organization;
    }

    
    /**
     * 获取：工艺编制日期
     */
    public String getOrganizationTime()
    {
        return organizationTime;
    }

    
    /**
     * 设置：工艺编制日期
     * 
     * @param i_OrganizationTime 工艺编制日期
     */
    public void setOrganizationTime(String i_OrganizationTime)
    {
        this.organizationTime = i_OrganizationTime;
    }

    
    /**
     * 获取：工艺审核
     */
    public String getReviewer()
    {
        return reviewer;
    }

    
    /**
     * 设置：工艺审核
     * 
     * @param i_Reviewer 工艺审核
     */
    public void setReviewer(String i_Reviewer)
    {
        this.reviewer = i_Reviewer;
    }

    
    /**
     * 获取：工艺审核日期
     */
    public String getReviewTime()
    {
        return reviewTime;
    }

    
    /**
     * 设置：工艺审核日期
     * 
     * @param i_ReviewTime 工艺审核日期
     */
    public void setReviewTime(String i_ReviewTime)
    {
        this.reviewTime = i_ReviewTime;
    }

    
    /**
     * 获取：工艺批准
     */
    public String getApprover()
    {
        return approver;
    }

    
    /**
     * 设置：工艺批准
     * 
     * @param i_Approver 工艺批准
     */
    public void setApprover(String i_Approver)
    {
        this.approver = i_Approver;
    }

    
    /**
     * 获取：工艺批准日期
     */
    public String getApproveTime()
    {
        return approveTime;
    }

    
    /**
     * 设置：工艺批准日期
     * 
     * @param i_ApproveTime 工艺批准日期
     */
    public void setApproveTime(String i_ApproveTime)
    {
        this.approveTime = i_ApproveTime;
    }

    
    /**
     * 获取：子集合数据
     */
    public List<ComplexDataItem> getChildDatas()
    {
        return childDatas;
    }

    
    /**
     * 设置：子集合数据
     * 
     * @param i_ChildDatas 子集合数据
     */
    public void setChildDatas(List<ComplexDataItem> i_ChildDatas)
    {
        this.childDatas = i_ChildDatas;
    }
    
}
