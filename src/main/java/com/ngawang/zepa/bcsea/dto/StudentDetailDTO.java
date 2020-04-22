package com.ngawang.zepa.bcsea.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by USER on 7/30/2019.
 */
public class StudentDetailDTO {
    //region private variables
    private String cidNo;
    private String indexNo;
    private String studentName;
    private Integer schoolId;
    private String schoolName;
    private Integer examYear;
    private Integer examMonth;
    private Integer streamId;
    private String isActive;
    private BigDecimal englishMark;
    private Integer classType;
    private String streamName;
    private String supwGrade;
    private String resultRemarks;
    private Date dob;
    private String filePath;
    //endregion

    //region setters and getters

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getExamYear() {
        return examYear;
    }

    public void setExamYear(Integer examYear) {
        this.examYear = examYear;
    }

    public Integer getExamMonth() {
        return examMonth;
    }

    public void setExamMonth(Integer examMonth) {
        this.examMonth = examMonth;
    }

    public Integer getStreamId() {
        return streamId;
    }

    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public BigDecimal getEnglishMark() {
        return englishMark;
    }

    public void setEnglishMark(BigDecimal englishMark) {
        this.englishMark = englishMark;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
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

    //endregion
}
