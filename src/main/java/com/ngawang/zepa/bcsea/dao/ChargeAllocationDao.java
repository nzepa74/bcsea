package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ChargeAllocationDTO;
import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.entity.ChargeAllocation;
import com.ngawang.zepa.bcsea.entity.ChargeAllocation_A;
import com.ngawang.zepa.helper.DropdownDTO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by USER on 9/5/2019.
 */
@Repository
public class ChargeAllocationDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<DropdownDTO> getServiceName() {
        String sqlQuery = properties.getProperty("ChargeAllocationDao.getServiceName");
        Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getDocumentList() {
        String sqlQuery = properties.getProperty("ChargeAllocationDao.getDocumentList");
        Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<ChargeAllocationDTO> getChargeList() {
        String sqlQuery = properties.getProperty("ChargeAllocationDao.getChargeList");
        Query hQuery = hibernateQuery(sqlQuery, ChargeAllocationDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public Integer isExistSave(ChargeAllocationDTO chargeAllocationDTO) {
        String sqlQuery = properties.getProperty("ChargeAllocationDao.isExistSave");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("serviceId", chargeAllocationDTO.getServiceId())
                .setParameter("documentId", chargeAllocationDTO.getDocumentId());
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public Integer getLastServiceChargeId() {
        String sqlQuery = properties.getProperty("ChargeAllocationDao.getLastServiceChargeId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public ChargeAllocationDTO getChargeDetail(Integer serviceChargeId) {
        String query = properties.getProperty("ChargeAllocationDao.getChargeDetail");
        org.hibernate.Query hQuery = hibernateQuery(query, ChargeAllocationDTO.class);
        hQuery.setParameter("serviceChargeId", serviceChargeId);
        return (ChargeAllocationDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String getDocumentName(Integer documentId) {
        String sqlQuery = properties.getProperty("ChargeAllocationDao.getDocumentName");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("documentId", documentId);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String getServiceName(Integer serviceId) {
        String sqlQuery = properties.getProperty("ChargeAllocationDao.getServiceNameByServiceId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("serviceId", serviceId);
        return (String) hQuery.uniqueResult();
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveChargeAllocation(ChargeAllocation chargeAllocation, ChargeAllocation_A chargeAllocation_a) {
        getReportingSession().saveOrUpdate(chargeAllocation);
        getReportingSession().save(chargeAllocation_a);
    }
}
