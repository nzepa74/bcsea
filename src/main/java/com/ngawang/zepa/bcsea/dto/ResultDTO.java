package com.ngawang.zepa.bcsea.dto;

/**
 * Created by USER on 8/23/2019.
 */
public class ResultDTO {
    //region private variables
    private Integer subjectId;
    private String subjectName;
    private String marksObtained;
    private String marksInWords;
    private String marksInGrade;
    private String subjectCountInWords;
    //endregion

    //region setters and getters

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(String marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getMarksInWords() {
        return marksInWords;
    }

    public void setMarksInWords(String marksInWords) {
        this.marksInWords = marksInWords;
    }

    public String getMarksInGrade() {
        return marksInGrade;
    }

    public void setMarksInGrade(String marksInGrade) {
        this.marksInGrade = marksInGrade;
    }

    public String getSubjectCountInWords() {
        return subjectCountInWords;
    }

    public void setSubjectCountInWords(String subjectCountInWords) {
        this.subjectCountInWords = subjectCountInWords;
    }
    //endregion
}
