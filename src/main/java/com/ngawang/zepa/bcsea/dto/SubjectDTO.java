package com.ngawang.zepa.bcsea.dto;

import java.math.BigInteger;

/**
 * Created by USER on 8/3/2019.
 */
public class SubjectDTO {
    //region private variables
    private BigInteger recheckDetailId;
    private Integer paperId;
    private Integer subjectId;
    private String paperName;
    private String subjectName;
    private String applicationNo;
    private String oldMarks;
    private String newMarks;
    private String indexNo;
    private Integer classType;
    //endregion

    //region setters and getters

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public BigInteger getRecheckDetailId() {
        return recheckDetailId;
    }

    public void setRecheckDetailId(BigInteger recheckDetailId) {
        this.recheckDetailId = recheckDetailId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getOldMarks() {
        return oldMarks;
    }

    public void setOldMarks(String oldMarks) {
        this.oldMarks = oldMarks;
    }

    public String getNewMarks() {
        return newMarks;
    }

    public void setNewMarks(String newMarks) {
        this.newMarks = newMarks;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    //endregion
}
