package com.ngawang.zepa.bcsea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * Created by USER on 8/6/2019.
 */
@Entity
@Table(name = "t_recheck_paper_details")
public class RecheckPaperDetail {
    //region private variables
    @Id
    @Column(name = "RECHECK_DETAIL_ID")
    private BigInteger recheckDetailId;

    @Column(name = "APPLICATION_NO")
    private String applicationNo;

    @Column(name = "INDEX_NO")
    private String indexNo;

    @Column(name = "PAPER_ID")
    private Integer paperId;

    @Column(name = "SUBJECT_ID")
    private Integer subjectId;

    @Column(name = "OLD_MARKS")
    private String oldMarks;

    @Column(name = "NEW_MARKS")
    private String newMarks;

    @Column(name = "CLASSTYPE")
    private Integer classType;
    //endregion

    //region setters and getters

    public BigInteger getRecheckDetailId() {
        return recheckDetailId;
    }

    public void setRecheckDetailId(BigInteger recheckDetailId) {
        this.recheckDetailId = recheckDetailId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
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

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }
//endregion
}
