package com.ngawang.zepa.bcsea.dto;

import java.util.Date;

/**
 * Created by USER on 8/6/2019.
 */
public class WorkFlowDetailDTO {
    //region private variables
    private String workFlowInstanceId;
    private String applicationNo;
    private Integer applicationStatusId;
    private String createdBy;
    private Date createdDate;
    private String updateBy;
    private Date updateDate;
    //endregion

    //region setters and getters

    public String getWorkFlowInstanceId() {
        return workFlowInstanceId;
    }

    public void setWorkFlowInstanceId(String workFlowInstanceId) {
        this.workFlowInstanceId = workFlowInstanceId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getApplicationStatusId() {
        return applicationStatusId;
    }

    public void setApplicationStatusId(Integer applicationStatusId) {
        this.applicationStatusId = applicationStatusId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    //endregion
}
