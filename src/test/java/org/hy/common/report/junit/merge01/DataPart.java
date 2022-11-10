package org.hy.common.report.junit.merge01;



/**
 * @Description: TOOL
 * @Author: 程元丰
 * @CrateDate: 2020/3/31
 */
public class DataPart {
    private String xxjlbh;      //询价记录编号
    private String lb;          //部件类型
    private String xh;          //型号
    private int sl;             //数量
    private String dj;          //单价
    private double hj;          //合计
    private String gh;          //供货方式

    public String getXxjlbh() {
        return xxjlbh;
    }

    public void setXxjlbh(String xxjlbh) {
        this.xxjlbh = xxjlbh;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public double getHj() {
        return hj;
    }

    public void setHj(double hj) {
        this.hj = hj;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }
}
