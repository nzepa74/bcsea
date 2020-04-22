package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.dto.ServiceChargeDTO;
import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import com.ngawang.zepa.bcsea.dto.SubjectDTO;
import com.ngawang.zepa.bcsea.entity.RecheckPaperDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by N-Zepa on 27-Jul-19.
 */
@Repository
public class RecheckApplicationDao extends BaseDao {

    @Transactional(readOnly = true)
    public ServiceActivityDurationDTO getServiceActivityDuration(Integer serviceTypeId, Integer classType, Integer examYear) {
        String query = properties.getProperty("RecheckApplicationDao.getServiceActivityDuration");
        org.hibernate.Query hQuery = hibernateQuery(query, ServiceActivityDurationDTO.class);
        hQuery.setParameter("serviceTypeId", serviceTypeId)
                .setParameter("classType", classType)
                .setParameter("examYear", examYear);
        return (ServiceActivityDurationDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public ServiceChargeDTO getRecheckCharge(Integer serviceTypeId) {
        String query = properties.getProperty("RecheckApplicationDao.getRecheckCharge");
        org.hibernate.Query hQuery = hibernateQuery(query, ServiceChargeDTO.class);
        hQuery.setParameter("serviceTypeId", serviceTypeId);
        return (ServiceChargeDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public StudentDetailDTO getStudentInfoByIndexNo(String indexNo) {
        String query = properties.getProperty("RecheckApplicationDao.getStudentInfoByIndexNo");
        org.hibernate.Query hQuery = hibernateQuery(query, StudentDetailDTO.class);
        hQuery.setParameter("indexNo", indexNo);
        return (StudentDetailDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public List<SubjectDTO> getSubjectsByIndexNo(String indexNo) {
        String sqlQuery = properties.getProperty("RecheckApplicationDao.getSubjectsByIndexNo");
        Query hQuery = hibernateQuery(sqlQuery, SubjectDTO.class);
        hQuery.setParameter("indexNo", indexNo);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public BigInteger getRecheckDetailId() {
        String sqlQuery = properties.getProperty("RecheckApplicationDao.getRecheckDetailId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return (BigInteger) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public Integer getSubjectIdByPaperId(Integer paperId) {
        String sqlQuery = properties.getProperty("RecheckApplicationDao.getSubjectIdByPaperId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("paperId", paperId);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String getOldMarksByPaperId(Integer paperId, String indexNo) {
        String sqlQuery = properties.getProperty("RecheckApplicationDao.getOldMarksByPaperId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("paperId", paperId)
                .setParameter("indexNo", indexNo);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveRecheckPaperDetail(RecheckPaperDetail recheckPaperDetail) {
        getReportingSession().saveOrUpdate(recheckPaperDetail);
    }

    @Transactional(readOnly = true)
    public String getDuplicateRecheckPaper(String indexNo, Integer paperId) {
        String sqlQuery = properties.getProperty("RecheckApplicationDao.getDuplicateRecheckPaper");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("indexNo", indexNo)
                .setParameter("paperId", paperId);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String getPaperName(Integer paperId) {
        String sqlQuery = properties.getProperty("RecheckApplicationDao.getPaperName");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("paperId", paperId);
        return (String) hQuery.uniqueResult();
    }
}
