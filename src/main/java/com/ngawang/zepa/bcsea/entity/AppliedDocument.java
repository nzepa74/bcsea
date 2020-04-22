package com.ngawang.zepa.bcsea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by USER on 7/29/2019.
 */
@Entity
@Table(name = "t_applied_document")
public class AppliedDocument {
    //region private variables
    @Id
    @Column(name = "ID")
    private Integer appliedDocumentId;

    @Column(name = "APPLICATION_NO")
    private String applicationNo;

    @Column(name = "DOCUMENT_TYPE_ID")
    private Integer documentTypeId;

    @Column(name = "printed")
    private Character printed;

    @Column(name = "rejected")
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
