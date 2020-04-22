package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.entity.FileAttachment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by USER on 8/15/2019.
 */
@Repository
public class IssueReplacementCertificateDao extends BaseDao {

    @Transactional(readOnly = true)
    public Integer getDocumentId() {
        String sqlQuery = properties.getProperty("IssueReplacementCertificateDao.getDocumentId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveAttachFile(FileAttachment fileAttachment) {
        getReportingSession().saveOrUpdate(fileAttachment);
    }
}
