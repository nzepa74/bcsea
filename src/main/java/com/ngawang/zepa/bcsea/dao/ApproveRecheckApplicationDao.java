package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.SubjectDTO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by USER on 8/20/2019.
 */
@Repository
public class ApproveRecheckApplicationDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<SubjectDTO> getRecheckPapers(String applicationNo) {
        String sqlQuery = properties.getProperty("ApproveRecheckApplicationDao.getRecheckPapers");
        Query hQuery = hibernateQuery(sqlQuery, SubjectDTO.class);
        hQuery.setParameter("applicationNo", applicationNo);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateNewMarks(BigInteger recheckDetailId, String applicationNo, String newMarks) {
        String sqlQuery = properties.getProperty("ApproveRecheckApplicationDao.updateNewMarks");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("recheckDetailId", recheckDetailId);
        hQuery.setParameter("applicationNo", applicationNo);
        hQuery.setParameter("newMarks", newMarks);
        hQuery.executeUpdate();
    }
}
