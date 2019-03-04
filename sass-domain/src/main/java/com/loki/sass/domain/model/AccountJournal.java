package com.loki.sass.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class AccountJournal {
    private Integer id;

    private Integer type;

    private Integer accountId;

    private String innerBusiNo;

    private BigDecimal amount;

    private String state;

    private Date opTime;

    private Integer thirdChannel;

    private String thirdTransNo;

    private Date thirdReceiptTime;

    private BigDecimal thirdReceiptAmount;

    private Integer needThirdConfirm;

    private String responseContent;

    private String outRequestNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getInnerBusiNo() {
        return innerBusiNo;
    }

    public void setInnerBusiNo(String innerBusiNo) {
        this.innerBusiNo = innerBusiNo == null ? null : innerBusiNo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public Integer getThirdChannel() {
        return thirdChannel;
    }

    public void setThirdChannel(Integer thirdChannel) {
        this.thirdChannel = thirdChannel;
    }

    public String getThirdTransNo() {
        return thirdTransNo;
    }

    public void setThirdTransNo(String thirdTransNo) {
        this.thirdTransNo = thirdTransNo == null ? null : thirdTransNo.trim();
    }

    public Date getThirdReceiptTime() {
        return thirdReceiptTime;
    }

    public void setThirdReceiptTime(Date thirdReceiptTime) {
        this.thirdReceiptTime = thirdReceiptTime;
    }

    public BigDecimal getThirdReceiptAmount() {
        return thirdReceiptAmount;
    }

    public void setThirdReceiptAmount(BigDecimal thirdReceiptAmount) {
        this.thirdReceiptAmount = thirdReceiptAmount;
    }

    public Integer getNeedThirdConfirm() {
        return needThirdConfirm;
    }

    public void setNeedThirdConfirm(Integer needThirdConfirm) {
        this.needThirdConfirm = needThirdConfirm;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent == null ? null : responseContent.trim();
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo == null ? null : outRequestNo.trim();
    }
}