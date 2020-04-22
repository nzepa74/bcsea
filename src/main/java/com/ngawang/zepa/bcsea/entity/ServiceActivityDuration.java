package com.ngawang.zepa.bcsea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by USER on 8/26/2019.
 */
@Entity
@Table(name = "t_service_activity_duration")
public class ServiceActivityDuration {

    //region private variables
    @Id
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
    //endregion

    //region setters and getters


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
    //endregion
}
