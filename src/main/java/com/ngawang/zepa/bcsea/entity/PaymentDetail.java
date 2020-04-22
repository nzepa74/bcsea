package com.ngawang.zepa.bcsea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by USER on 9/8/2019.
 */
@Entity
@Table(name = "t_app_payment_details")
public class PaymentDetail {
    //region private variables
    @Id
    @Column(name = "PAYMENT_DETAIL_ID")
    private Integer paymentDetailId;

    @Column(name = "APPLICATION_NO")
    private String applicationNo;

    @Column(name = "SERVICE_ID")
    private Integer serviceNo;

    @Column(name = "PAYMENT_TYPE")
    private Character paymentType;

    @Column(name = "AMOUNT_CHARGE")
    private Integer amountCharge;

    @Column(name = "BANK_ID")
    private Integer bankId;

    @Column(name = "RECEIPT_NO")
    private String receiptNo;

    @Column(name = "VOUCHER_NO")
    private Integer voucherNo;

    @Column(name = "DEPOSITE_DATE")
    private Date depositDate;

    @Column(name = "IS_PAID")
    private Character isPaid;
    //endregion

    //region setters and getters

    public Integer getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(Integer paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(Integer serviceNo) {
        this.serviceNo = serviceNo;
    }

    public Character getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Character paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getAmountCharge() {
        return amountCharge;
    }

    public void setAmountCharge(Integer amountCharge) {
        this.amountCharge = amountCharge;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Integer getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(Integer voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public Character getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Character isPaid) {
        this.isPaid = isPaid;
    }

    //endregion
}
