package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by USER on 8/5/2019.
 */
@Repository
public class IssueEnglishLanProCertificateDao extends BaseDao {

    @Transactional(readOnly = true)
    public StudentDetailDTO getEnglishMarkByIndexNo(String indexNo) {
        String query = properties.getProperty("IssueEnglishLanProCertificateDao.getEnglishMarkByIndexNo");
        org.hibernate.Query hQuery = hibernateQuery(query, StudentDetailDTO.class);
        hQuery.setParameter("indexNo", indexNo);
        return (StudentDetailDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String getDuplicateApplicationNo(String cidNo, String indexNo, Integer globalServiceTypeId) {
        String sqlQuery = properties.getProperty("IssueEnglishLanProCertificateDao.getDuplicateApplicationNo");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("cidNo", cidNo)
                .setParameter("indexNo", indexNo)
                .setParameter("globalServiceTypeId", globalServiceTypeId);
        return (String) hQuery.uniqueResult();
    }
}
