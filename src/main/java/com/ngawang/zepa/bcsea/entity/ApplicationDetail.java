package com.ngawang.zepa.bcsea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by N-Zepa on 27-Jul-19.
 */
@Entity
@Table(name = "t_application_details")
public class ApplicationDetail {

    //region private variables
    @Id
    @Column(name = "APPLICATION_NO")
    private String applicationNo;

    @Column(name = "SERVICE_SL_NO")
    private Integer serviceSlNo;

    @Column(name = "SUBMISSION_DATE")
    private Date submissionDate;

    @Column(name = "SUBMITTED_BY")
    private String submittedBy;

    @Column(name = "ACTION_TAKEN_BY")
    private String actionTakenBy;

    @Column(name = "ACTION_TAKEN_DATE")
    private Date actionTakenDate;

    @Column(name = "CID")
    private String cidNo;

    @Column(name = "BIRTH_DATE")
    private String birthDate;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "GUARDIAN_NAME")
    private String guardianName;

    @Column(name = "CONTACT_GUARDIAN_NAME")
    private String fatherName;

    @Column(name = "CONTACT_ADDRESS")
    private String address;

    @Column(name = "MOB_NO")
    private Integer mobileNo;

    @Column(name = "EMAIL_ID")
    private String email;

    @Column(name = "OLD_DOC_PROB")
    private String reasonWithOriginalDoc;

    @Column(name = "CHANGES_REQUIRED")
    private String changesRequired;

    @Column(name = "OLD_IS_RETURNED")
    private String isOldDocReturned;

    @Column(name = "DOC_IS_COLLECTED")
    private Character documentCollection;

    @Column(name = "COLLECTED_BY")
    private String collectedBy;

    @Column(name = "IS_EMPLOYED")
    private String isEmployed;

    @Column(name = "AGENCY_NAME")
    private String agencyName;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "LAST_EXAM_MONTH")
    private Integer lastExamMonth;

    @Column(name = "LAST_EXAM_YEAR")
    private Integer lastExamYear;

    @Column(name = "LAST_SCHOOL_NAME")
    private String lastSchoolName;

    @Column(name = "PURPOSE")
    private String purpose;

    @Column(name = "INDEX_NUMBER")
    private String indexNo;

    @Column(name = "EXAM_MONTH")
    private Integer examMonth;

    @Column(name = "EXAM_YEAR")
    private Integer examYear;

    @Column(name = "SCHOOL_ID")
    private Integer schoolId;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "PRINTED")
    private Character isPrinted;

    @Column(name = "PAYMENT_MADE")
    private String paymentMade;

    @Column(name = "STATUS_ID")
    private Integer statusId;

    @Column(name = "REJECT_REASON_ID")
    private Integer rejectReasonId;

    @Column(name = "REJECT_REMARKS")
    private String rejectRemarks;
    //endregion

    //region setters and getters

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

    public String getRejectRemarks() {
        return rejectRemarks;
    }

    public void setRejectRemarks(String rejectRemarks) {
        this.rejectRemarks = rejectRemarks;
    }

    //endregion
}
