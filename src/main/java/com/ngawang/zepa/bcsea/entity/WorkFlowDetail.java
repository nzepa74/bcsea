package com.ngawang.zepa.bcsea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by USER on 8/6/2019.
 */
@Entity
@Table(name = "t_workflow_dtls")
public class WorkFlowDetail {
    //region private variables
    @Id
    @Column(name = "Workflow_instance_Id")
    private String workFlowInstanceId;

    @Column(name = "Application_Id")
    private String applicationNo;

    @Column(name = "Application_Status")
    private Integer applicationStatusId;

    @Column(name = "Created_By")
    private String createdBy;

    @Column(name = "Created_Date")
    private Date createdDate;

    @Column(name = "Update_By")
    private String updateBy;

    @Column(name = "Update_Date")
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
