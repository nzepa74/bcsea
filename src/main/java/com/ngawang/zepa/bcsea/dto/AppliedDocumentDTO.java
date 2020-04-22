package com.ngawang.zepa.bcsea.dto;

/**
 * Created by USER on 7/29/2019.
 */
public class AppliedDocumentDTO {
    //region private variables
    private Integer appliedDocumentId;
    private String applicationNo;
    private Integer documentTypeId;
    private Character printed;
    private Character rejected;
    //endregion

    //region setters and getters
    public Integer getAppliedDocumentId() {
        return appliedDocumentId;
    }

    public void setAppliedDocumentId(Integer appliedDocumentId) {
        this.appliedDocumentId = appliedDocumentId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Character getPrinted() {
        return printed;
    }

    public void setPrinted(Character printed) {
        this.printed = printed;
    }

    public Character getRejected() {
        return rejected;
    }

    public void setRejected(Character rejected) {
        this.rejected = rejected;
    }
    //endregion
}
