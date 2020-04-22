package com.ngawang.zepa.bcsea.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by USER on 11/3/2019.
 */
@Entity
@Table(name = "t_service_activity_duration_audit")
public class ServiceActivityDuration_A {
    //region private variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer auditSerial;

    @Column(name = "INDEX_NO")
    private Integer autoIndex;

    @Column(name = "SERVICE_SL_NO")
    private Integer serviceId;

    @Column(name = "CLASS_TYPE")
    private Integer classType;

    @Column(name = "EXAM_YEAR")
    private Integer examYear;

    @Column(name = "ACTIVE_FROM")
    private Date activeFrom;

    @Column(name = "ACTIVE_TO")
    private Date activeTo;

    @Column(name = "ACTIVE_TAG")
    private Character statusTag;

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

    public Integer getAutoIndex() {
        return autoIndex;
    }

    public void setAutoIndex(Integer autoIndex) {
        this.autoIndex = autoIndex;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public Integer getExamYear() {
        return examYear;
    }

    public void setExamYear(Integer examYear) {
        this.examYear = examYear;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Date getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(Date activeTo) {
        this.activeTo = activeTo;
    }

    public Character getStatusTag() {
        return statusTag;
    }

    public void setStatusTag(Character statusTag) {
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
