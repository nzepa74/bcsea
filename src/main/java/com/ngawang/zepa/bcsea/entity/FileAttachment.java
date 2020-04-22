package com.ngawang.zepa.bcsea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by USER on 8/14/2019.
 */
@Entity
@Table(name = "t_document_dtls")
public class FileAttachment {
    //region private variables
    @Id
    @Column(name = "Document_Id")
    private Integer documentId;

    @Column(name = "Document_Type_Id")
    private String documentTypeId;

    @Column(name = "Application_Number")
    private String applicationNo;

    @Column(name = "Document_Type")
    private String documentType;

    @Column(name = "Document_Name")
    private String documentName;

    @Column(name = "Upload_URL")
    private String uploadUrl;

    @Column(name = "UUID")
    private String uuId;
    //endregion

    //region setters and getters

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(String documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    //endregion
}
