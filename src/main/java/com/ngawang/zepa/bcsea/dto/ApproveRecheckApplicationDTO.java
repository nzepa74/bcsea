package com.ngawang.zepa.bcsea.dto;

import java.util.List;

/**
 * Created by USER on 8/21/2019.
 */
public class ApproveRecheckApplicationDTO {
    //region private variables
    private String applicationNo;
    private String remarks;
    private Integer rejectReasonId;
    private String rejectRemarks;
    private List<SubjectDTO> subjectDTOs;
    //endregion

    //region setters and getters
    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getRejectReasonId() {
        return rejectReasonId;
    }

    public void setRejectReasonId(Integer rejectReasonId) {
        this.rejectReasonId = rejectReasonId;
    }

    public String getRejectRemarks() {
        return rejectRemarks;
    }

    public void setRejectRemarks(String rejectRemarks) {
        this.rejectRemarks = rejectRemarks;
    }

    public List<SubjectDTO> getSubjectDTOs() {
        return subjectDTOs;
    }

    public void setSubjectDTOs(List<SubjectDTO> subjectDTOs) {
        this.subjectDTOs = subjectDTOs;
    }
    //endregion
}
