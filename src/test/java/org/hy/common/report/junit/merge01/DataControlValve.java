package org.hy.common.report.junit.merge01;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 控制阀报价结果明细的实体类
 * @Author: chengyuanfeng
 * @CrateDate: 2020/1/17
 */
public class DataControlValve {

    private String xmbh;            //项目编号
    private String xxjlbh;          //选型编号
    private String wh;              //位号
    private String cpxh;            //产品型号
    private String sl;              //数量
    private String price;           //价格
    private String flxs;            //法兰型式
    private String gctj;            //公称通径
    private String yldj;            //压力等级
    private String sfg;
    private String ftcz;            //阀体材质
    private String fgxs;            //阀盖形式
    private String fxcz;            //阀芯/阀板/球芯材质
    private String fzcz;            //阀座/密封环材质
    private String zxjg;            //执行机构
    private String sljg;            //手轮机构
    private String dcf;             //电磁阀
    private String dcfsl;           //电磁阀数量
    private String jyf;             //减压阀
    private String dwq;             //定位器
    private String xckg;            //行程开关
    private String xckgsl;          //行程开关数量
    private String qtfj;            //其他附件
    private String flsmdp;          //法兰栓母垫片
    private String bjg;             //变径管
    private String bz;              //备注

    private String quoteRetFtzj;   // 价格 阀体组件
    private String quoteRetZxjg;   // 价格 执行机构
    private String quoteRetDwq;    // 价格 定位器
    private String quoteRetJyf;    // 价格 减压阀
    private String quoteRetDcf;    // 价格 电磁阀
    private String quoteRetQtfj;   // 价格 其它附件
    private String quoteRetFlsmdp; // 价格 法兰栓母垫片
    private String quoteRetBjg;    // 价格 变径管
    private String quoteRetXckg;   // 价格 行程开关
    private String quoteRetFyf;    // 价格 发运费
    private String quoteRetDj;     // 价格 单价
    private String quoteRetHj;     // 价格 合计
    private double priceHj;        // 在导出excel时放入浮点型，可以用公式计算价格合计
    private Map<String, String> fs;//部件供货方式
    private List<DataPart> bjgc = new ArrayList<>(); //部件构成
    private List<DataPart> qtfjs = new ArrayList<>();//其他附件

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getXxjlbh() {
        return xxjlbh;
    }

    public void setXxjlbh(String xxjlbh) {
        this.xxjlbh = xxjlbh;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh;
    }

    public String getCpxh() {
        return cpxh;
    }

    public void setCpxh(String cpxh) {
        this.cpxh = cpxh;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFlxs() {
        return flxs;
    }

    public void setFlxs(String flxs) {
        this.flxs = flxs;
    }

    public String getGctj() {
        return gctj;
    }

    public void setGctj(String gctj) {
        this.gctj = gctj;
    }

    public String getYldj() {
        return yldj;
    }

    public void setYldj(String yldj) {
        this.yldj = yldj;
    }

    public String getSfg() {
        return sfg;
    }

    public void setSfg(String sfg) {
        this.sfg = sfg;
    }

    public String getFtcz() {
        return ftcz;
    }

    public void setFtcz(String ftcz) {
        this.ftcz = ftcz;
    }

    public String getFgxs() {
        return fgxs;
    }

    public void setFgxs(String fgxs) {
        this.fgxs = fgxs;
    }

    public String getFxcz() {
        return fxcz;
    }

    public void setFxcz(String fxcz) {
        this.fxcz = fxcz;
    }

    public String getFzcz() {
        return fzcz;
    }

    public void setFzcz(String fzcz) {
        this.fzcz = fzcz;
    }

    public String getZxjg() {
        return zxjg;
    }

    public void setZxjg(String zxjg) {
        this.zxjg = zxjg;
    }


    public String getSljg() {
        return sljg;
    }

    public void setSljg(String sljg) {
        this.sljg = sljg;
    }

    public String getDcf() {
        return dcf;
    }

    public void setDcf(String dcf) {
        this.dcf = dcf;
    }

    public String getDcfsl() {
        return dcfsl;
    }

    public void setDcfsl(String dcfsl) {
        this.dcfsl = dcfsl;
    }

    public String getJyf() {
        return jyf;
    }

    public void setJyf(String jyf) {
        this.jyf = jyf;
    }

    public String getDwq() {
        return dwq;
    }

    public void setDwq(String dwq) {
        this.dwq = dwq;
    }

    public String getXckg() {
        return xckg;
    }

    public void setXckg(String xckg) {
        this.xckg = xckg;
    }

    public String getXckgsl() {
        return xckgsl;
    }

    public void setXckgsl(String xckgsl) {
        this.xckgsl = xckgsl;
    }

    public String getQtfj() {
        return qtfj;
    }

    public void setQtfj(String qtfj) {
        this.qtfj = qtfj;
    }

    public String getFlsmdp() {
        return flsmdp;
    }

    public void setFlsmdp(String flsmdp) {
        this.flsmdp = flsmdp;
    }

    public String getBjg() {
        return bjg;
    }

    public void setBjg(String bjg) {
        this.bjg = bjg;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getQuoteRetFtzj() {
        return quoteRetFtzj;
    }

    public void setQuoteRetFtzj(String quoteRetFtzj) {
        this.quoteRetFtzj = quoteRetFtzj;
    }

    public String getQuoteRetZxjg() {
        return quoteRetZxjg;
    }

    public void setQuoteRetZxjg(String quoteRetZxjg) {
        this.quoteRetZxjg = quoteRetZxjg;
    }

    public String getQuoteRetDwq() {
        return quoteRetDwq;
    }

    public void setQuoteRetDwq(String quoteRetDwq) {
        this.quoteRetDwq = quoteRetDwq;
    }

    public String getQuoteRetJyf() {
        return quoteRetJyf;
    }

    public void setQuoteRetJyf(String quoteRetJyf) {
        this.quoteRetJyf = quoteRetJyf;
    }

    public String getQuoteRetDcf() {
        return quoteRetDcf;
    }

    public void setQuoteRetDcf(String quoteRetDcf) {
        this.quoteRetDcf = quoteRetDcf;
    }

    public String getQuoteRetQtfj() {
        return quoteRetQtfj;
    }

    public void setQuoteRetQtfj(String quoteRetQtfj) {
        this.quoteRetQtfj = quoteRetQtfj;
    }

    public String getQuoteRetFlsmdp() {
        return quoteRetFlsmdp;
    }

    public void setQuoteRetFlsmdp(String quoteRetFlsmdp) {
        this.quoteRetFlsmdp = quoteRetFlsmdp;
    }

    public String getQuoteRetBjg() {
        return quoteRetBjg;
    }

    public void setQuoteRetBjg(String quoteRetBjg) {
        this.quoteRetBjg = quoteRetBjg;
    }

    public String getQuoteRetXckg() {
        return quoteRetXckg;
    }

    public void setQuoteRetXckg(String quoteRetXckg) {
        this.quoteRetXckg = quoteRetXckg;
    }

    public String getQuoteRetFyf() {
        return quoteRetFyf;
    }

    public void setQuoteRetFyf(String quoteRetFyf) {
        this.quoteRetFyf = quoteRetFyf;
    }

    public String getQuoteRetDj() {
        return quoteRetDj;
    }

    public void setQuoteRetDj(String quoteRetDj) {
        this.quoteRetDj = quoteRetDj;
    }

    public String getQuoteRetHj() {
        return quoteRetHj;
    }

    public void setQuoteRetHj(String quoteRetHj) {
        this.quoteRetHj = quoteRetHj;
    }

    public double getPriceHj() {
        return priceHj;
    }

    public void setPriceHj(double priceHj) {
        this.priceHj = priceHj;
    }

    public Map<String, String> getFs() {
        return fs;
    }

    public void setFs(Map<String, String> fs) {
        this.fs = fs;
    }


    public List<DataPart> getBjgc() {
        return bjgc;
    }

    public void setBjgc(List<DataPart> bjgc) {
        this.bjgc = bjgc;
    }


    public List<DataPart> getQtfjs() {
        return qtfjs;
    }

    public void setQtfjs(List<DataPart> qtfjs) {
        this.qtfjs = qtfjs;
    }

    @Override
    public boolean equals(Object i_Other)
    {
        return super.equals(i_Other);
    }
}
