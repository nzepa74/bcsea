package com.ngawang.zepa.bcsea.dto;

import java.util.Date;

/**
 * Created by USER on 8/22/2019.
 */
public class ResultResponseMessage {
    private Integer responseStatus;
    private String responseText;
    private String studentName;
    private String indexNo;
    private Date dob;
    private Integer classType;
    private Integer examYear;
    private String schoolName;
    private String streamName;
    private String supwGrade;
    private String resultRemarks;
    private Object responseDTO;

    //endregion
    public ResultResponseMessage() {

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getSupwGrade() {
        return supwGrade;
    }

    public void setSupwGrade(String supwGrade) {
        this.supwGrade = supwGrade;
    }

    public String getResultRemarks() {
        return resultRemarks;
    }

    public void setResultRemarks(String resultRemarks) {
        this.resultRemarks = resultRemarks;
    }

    public Object getResponseDTO() {
        return responseDTO;
    }

    public void setResponseDTO(Object responseDTO) {
        this.responseDTO = responseDTO;
    }
}
