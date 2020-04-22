package com.ngawang.zepa.bcsea.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by USER on 11/3/2019.
 */
@Entity
@Table(name = "t_service_charge_audit")
public class ChargeAllocation_A {

    //region private variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer auditSerial;

    @Column(name = "SERVICE_CHARGE_ID")
    private Integer serviceChargeId;

    @Column(name = "SERVICE_SL_NO")
    private Integer serviceId;

    @Column(name = "DOCUMENT_ID")
    private Integer documentId;

    @Column(name = "CHARGE_AMOUNT")
    private Integer chargeAmount;

    @Column(name = "IS_ACTIVE")
    private String statusTag;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "CMD_FLAG")
    private Character cmdFlag;

    //endregion

    //region setters and getters
    public Integer getAuditSerial() {
        return auditSerial;
    }

    public void setAuditSerial(Integer auditSerial) {
        this.auditSerial = auditSerial;
    }

    public Integer getServiceChargeId() {
        return serviceChargeId;
    }

    public void setServiceChargeId(Integer serviceChargeId) {
        this.serviceChargeId = serviceChargeId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Integer chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getStatusTag() {
        return statusTag;
    }

    public void setStatusTag(String statusTag) {
        this.statusTag = statusTag;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Character getCmdFlag() {
        return cmdFlag;
    }

    public void setCmdFlag(Character cmdFlag) {
        this.cmdFlag = cmdFlag;
    }
    //endregion
}
