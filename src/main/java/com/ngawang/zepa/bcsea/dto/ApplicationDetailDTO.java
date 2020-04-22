package com.ngawang.zepa.bcsea.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by N-Zepa on 27-Jul-19.
 */
public class ApplicationDetailDTO {
    //region private variables
    private String applicationNo;
    private Integer serviceSlNo;
    private Date submissionDate;
    private String submittedBy;
    private String actionTakenBy;
    private Date actionTakenDate;
    private String cidNo;
    private String birthDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private String guardianName;
    private String fatherName;
    private String address;
    private Integer mobileNo;
    private String email;
    private String reasonWithOriginalDoc;
    private String changesRequired;
    private String isOldDocReturned;
    private Character documentCollection;
    private String collectedBy;
    private String isEmployed;
    private String agencyName;
    private String designation;
    private Integer lastExamMonth;
    private Integer lastExamYear;
    private String lastSchoolName;
    private String purpose;
    private String indexNo;
    private Integer examMonth;
    private Integer examYear;
    private Integer schoolId;
    private String remarks;
    private Character isPrinted;
    private String paymentMade;
    private Integer statusId;
    private Integer rejectReasonId;
    private List<AppliedDocumentDTO> appliedDocumentDTOs;
    private List<SubjectDTO> subjectDTOs;
    private List<FileAttachmentDTO> fileAttachmentDTOs;
    private String serviceName;
    private String statusName;
    private String fullName;
    private String rejectRemarks;
    private String schoolName;
    private Integer paymentDetailId;
    private Integer amountCharge;
    private String documentName;
    private Integer documentId;
     //endregion

    //region setters and getters

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getServiceSlNo() {
        return serviceSlNo;
    }

    public void setServiceSlNo(Integer serviceSlNo) {
        this.serviceSlNo = serviceSlNo;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getActionTakenBy() {
        return actionTakenBy;
    }

    public void setActionTakenBy(String actionTakenBy) {
        this.actionTakenBy = actionTakenBy;
    }

    public Date getActionTakenDate() {
        return actionTakenDate;
    }

    public void setActionTakenDate(Date actionTakenDate) {
        this.actionTakenDate = actionTakenDate;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Integer mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReasonWithOriginalDoc() {
        return reasonWithOriginalDoc;
    }

    public void setReasonWithOriginalDoc(String reasonWithOriginalDoc) {
        this.reasonWithOriginalDoc = reasonWithOriginalDoc;
    }

    public String getChangesRequired() {
        return changesRequired;
    }

    public void setChangesRequired(String changesRequired) {
        this.changesRequired = changesRequired;
    }

    public String getIsOldDocReturned() {
        return isOldDocReturned;
    }

    public void setIsOldDocReturned(String isOldDocReturned) {
        this.isOldDocReturned = isOldDocReturned;
    }

    public Character getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Character documentCollection) {
        this.documentCollection = documentCollection;
    }

    public String getCollectedBy() {
        return collectedBy;
    }

    public void setCollectedBy(String collectedBy) {
        this.collectedBy = collectedBy;
    }

    public String getIsEmployed() {
        return isEmployed;
    }

    public void setIsEmployed(String isEmployed) {
        this.isEmployed = isEmployed;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getLastExamMonth() {
        return lastExamMonth;
    }

    public void setLastExamMonth(Integer lastExamMonth) {
        this.lastExamMonth = lastExamMonth;
    }

    public Integer getLastExamYear() {
        return lastExamYear;
    }

    public void setLastExamYear(Integer lastExamYear) {
        this.lastExamYear = lastExamYear;
    }

    public String getLastSchoolName() {
        return lastSchoolName;
    }

    public void setLastSchoolName(String lastSchoolName) {
        this.lastSchoolName = lastSchoolName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getExamMonth() {
        return examMonth;
    }

    public void setExamMonth(Integer examMonth) {
        this.examMonth = examMonth;
    }

    public Integer getExamYear() {
        return examYear;
    }

    public void setExamYear(Integer examYear) {
        this.examYear = examYear;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Character getIsPrinted() {
        return isPrinted;
    }

    public void setIsPrinted(Character isPrinted) {
        this.isPrinted = isPrinted;
    }

    public String getPaymentMade() {
        return paymentMade;
    }

    public void setPaymentMade(String paymentMade) {
        this.paymentMade = paymentMade;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getRejectReasonId() {
        return rejectReasonId;
    }

    public void setRejectReasonId(Integer rejectReasonId) {
        this.rejectReasonId = rejectReasonId;
    }

    public List<AppliedDocumentDTO> getAppliedDocumentDTOs() {
        return appliedDocumentDTOs;
    }

    public void setAppliedDocumentDTOs(List<AppliedDocumentDTO> appliedDocumentDTOs) {
        this.appliedDocumentDTOs = appliedDocumentDTOs;
    }

    public List<SubjectDTO> getSubjectDTOs() {
        return subjectDTOs;
    }

    public void setSubjectDTOs(List<SubjectDTO> subjectDTOs) {
        this.subjectDTOs = subjectDTOs;
    }

    public List<FileAttachmentDTO> getFileAttachmentDTOs() {
        return fileAttachmentDTOs;
    }

    public void setFileAttachmentDTOs(List<FileAttachmentDTO> fileAttachmentDTOs) {
        this.fileAttachmentDTOs = fileAttachmentDTOs;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRejectRemarks() {
        return rejectRemarks;
    }

    public void setRejectRemarks(String rejectRemarks) {
        this.rejectRemarks = rejectRemarks;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(Integer paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public Integer getAmountCharge() {
        return amountCharge;
    }

    public void setAmountCharge(Integer amountCharge) {
        this.amountCharge = amountCharge;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    //endregion
}
