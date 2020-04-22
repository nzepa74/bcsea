package com.ngawang.zepa.bcsea.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by USER on 8/6/2019.
 */
public class ServiceActivityDurationDTO {
    //region private variables
    private Integer autoIndex;
    private Integer serviceId;

    @NotNull(message = "Class type is required")
    private Integer classType;

    @NotNull(message = "Exam year is required")
//        @Size(min = 4, max = 4, message = "Exam year should be contain 4 numeric characters")
    private Integer examYear;

    @NotNull(message = "From date is required")
    private Date activeFrom;

    @NotNull(message = "To date is required")
    private Date activeTo;

    private Character statusTag;
    private String statusTagName;
    private String serviceName;
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

    public String getStatusTagName() {
        return statusTagName;
    }

    public void setStatusTagName(String statusTagName) {
        this.statusTagName = statusTagName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
//endregion
}
