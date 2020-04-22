package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.FileAttachmentDTO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by USER on 8/15/2019.
 */
@Repository
public class ApproveIssueReplacementCertificateDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<FileAttachmentDTO> getAttachedFiles(String applicationNo) {
        String sqlQuery = properties.getProperty("ApproveIssueReplacementCertificateDao.getAttachedFiles");
        Query hQuery = hibernateQuery(sqlQuery, FileAttachmentDTO.class);
        hQuery.setParameter("applicationNo", applicationNo);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public FileAttachmentDTO downloadFile(Integer documentId) {
        String sql = properties.getProperty("ApproveIssueReplacementCertificateDao.downloadFile");
        org.hibernate.Query hQuery = hibernateQuery(sql, FileAttachmentDTO.class);
        hQuery.setParameter("documentId", documentId);
        return (FileAttachmentDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String getIndexNo(String applicationNo) {
        String sqlQuery = properties.getProperty("ApproveIssueReplacementCertificateDao.getIndexNo");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("applicationNo", applicationNo);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateNameAndDob(String indexNo, String nameChange, String dobChange) {
        String sqlQuery = properties.getProperty("ApproveIssueReplacementCertificateDao.updateNameAndDob");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("indexNo", indexNo);
        hQuery.setParameter("nameChange",nameChange);
        hQuery.setParameter("dobChange",dobChange);
        hQuery.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStudentName(String indexNo, String nameChange) {
        String sqlQuery = properties.getProperty("ApproveIssueReplacementCertificateDao.updateStudentName");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("indexNo", indexNo);
        hQuery.setParameter("nameChange",nameChange);
        hQuery.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStudentDob(String indexNo, String dobChange) {
        String sqlQuery = properties.getProperty("ApproveIssueReplacementCertificateDao.updateStudentDob");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("indexNo", indexNo);
        hQuery.setParameter("dobChange",dobChange);
        hQuery.executeUpdate();
    }
}
