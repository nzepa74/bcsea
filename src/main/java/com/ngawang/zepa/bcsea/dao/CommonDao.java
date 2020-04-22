package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import com.ngawang.zepa.bcsea.entity.*;
import com.ngawang.zepa.helper.DropdownDTO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by USER on 7/28/2019.
 */
@Repository
public class CommonDao extends BaseDao {

    /**
     * to get document list
     *
     * @return -- List<DropdownDTO>
     */
    @Transactional(readOnly = true)
    public List<DropdownDTO> getDocumentList() {
        String sqlQuery = properties.getProperty("CommonDao.getDocumentList");
        Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getAppliedDocumentList(String applicationNo) {
        String sqlQuery = properties.getProperty("CommonDao.getAppliedDocumentList");
        Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        hQuery.setParameter("applicationNo", applicationNo);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public String getApplicationSerial(Integer globalServiceTypeId, String applicationYear,
                                       String applicationMonth, String applicationDay, String applicationType) {
        String sqlQuery = properties.getProperty("CommonDao.getApplicationSerial");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("globalServiceTypeId", globalServiceTypeId)
                .setParameter("applicationYear", applicationYear)
                .setParameter("applicationMonth", applicationMonth)
                .setParameter("applicationDay", applicationDay)
                .setParameter("applicationType", applicationType);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public StudentDetailDTO getStudentDetailsByIndexNo(String indexNo) {
        String query = properties.getProperty("CommonDao.getStudentDetailsByIndexNo");
        org.hibernate.Query hQuery = hibernateQuery(query, StudentDetailDTO.class);
        hQuery.setParameter("indexNo", indexNo);
        return (StudentDetailDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public StudentDetailDTO getCitizenName(String cidNo) {
        String query = properties.getProperty("CommonDao.getCitizenName");
        org.hibernate.Query hQuery = hibernateQuery(query, StudentDetailDTO.class);
        hQuery.setParameter("cidNo", cidNo);
        return (StudentDetailDTO) hQuery.uniqueResult();
    }

    /**
     * save application
     *
     * @param applicationDetail -- ApplicationDetail
     */
    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveApplication(ApplicationDetail applicationDetail) {
        getReportingSession().saveOrUpdate(applicationDetail);
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveWorkFlowDetail(WorkFlowDetail workFlowDetail) {
        getReportingSession().saveOrUpdate(workFlowDetail);
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveTaskDetail(TaskDetail taskDetail) {
        getReportingSession().saveOrUpdate(taskDetail);
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void savePaymentDetail(PaymentDetail paymentDetail) {
        getReportingSession().saveOrUpdate(paymentDetail);
    }

    /**
     * to get last generated document Id
     *
     * @return --Integer
     */
    @Transactional(readOnly = true)
    public Integer getAppliedDocumentId() {
        String sqlQuery = properties.getProperty("CommonDao.getAppliedDocumentId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return (Integer) hQuery.uniqueResult();
    }

    /**
     * to save applied document
     *
     * @param appliedDocument --AppliedDocument
     */
    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveAppliedDocument(AppliedDocument appliedDocument) {
        getReportingSession().saveOrUpdate(appliedDocument);
    }

    @Transactional(readOnly = true)
    public Integer getChargeByDocumentId(Integer documentId, Integer serviceId) {
        String sqlQuery = properties.getProperty("CommonDao.getChargeByDocumentId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("documentId", documentId)
                .setParameter("serviceId", serviceId);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public Integer getWorkInstanceSerial() {
        String sqlQuery = properties.getProperty("CommonDao.getWorkInstanceSerial");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public Integer getTaskInstanceSerial() {
        String sqlQuery = properties.getProperty("CommonDao.getTaskInstanceSerial");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public Integer getLastPaymentDetailId() {
        String sqlQuery = properties.getProperty("CommonDao.getLastPaymentDetailId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public ApplicationDetailDTO getApplicationDetail(String applicationNo) {
        String query = properties.getProperty("CommonDao.getApplicationDetail");
        org.hibernate.Query hQuery = hibernateQuery(query, ApplicationDetailDTO.class);
        hQuery.setParameter("applicationNo", applicationNo);
        return (ApplicationDetailDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public BigDecimal getChargedApplied(String applicationNo) {
        String sqlQuery = properties.getProperty("CommonDao.getChargedApplied");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("applicationNo", applicationNo);
        return (BigDecimal) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getRejectReasons() {
        String sqlQuery = properties.getProperty("CommonDao.getRejectReasons");
        Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public String getRejectReasonByRejectReasonId(Integer rejectReasonId) {
        String sqlQuery = properties.getProperty("CommonDao.getRejectReasonByRejectReasonId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("rejectReasonId", rejectReasonId);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String validateDuplicateDocument(String cidNo, String indexNo, Integer serviceTypeId
            , Integer documentTypeId) {
        String sqlQuery = properties.getProperty("CommonDao.validateDuplicateDocument");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("cidNo", cidNo)
                .setParameter("indexNo", indexNo)
                .setParameter("serviceTypeId", serviceTypeId)
                .setParameter("documentTypeId", documentTypeId);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String getDocumentName(Integer documentTypeId) {
        String sqlQuery = properties.getProperty("CommonDao.getDocumentName");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("documentTypeId", documentTypeId);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePrintStatus(String applicationNo, Integer documentId) {
        String sqlQuery = properties.getProperty("CommonDao.updatePrintStatus");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("applicationNo", applicationNo)
                .setParameter("documentId", documentId);
        hQuery.executeUpdate();
    }
}
