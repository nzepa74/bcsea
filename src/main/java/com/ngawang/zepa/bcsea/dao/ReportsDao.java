package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.helper.DropdownDTO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by USER on 9/12/2019.
 */
@Repository
public class ReportsDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<DropdownDTO> getStatusList() {
        String sqlQuery = properties.getProperty("ReportsDao.getStatusList");
        Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<ApplicationDetailDTO> getDuplicateOrReplacement(Integer serviceId, Integer documentId, Integer classType, Date fromDate,
                                                                Date toDate, Integer statusId) {
        String sqlQuery = properties.getProperty("ReportsDao.getDuplicateOrReplacement");
        Query hQuery = hibernateQuery(sqlQuery, ApplicationDetailDTO.class);
        hQuery.setParameter("serviceId", serviceId)
                .setParameter("documentId", documentId)
                .setParameter("classType", classType)
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .setParameter("statusId", statusId);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<ApplicationDetailDTO> getEnglishLanPro(Integer serviceId, Date fromDate, Date toDate, Integer statusId) {
        String sqlQuery = properties.getProperty("ReportsDao.getEnglishLanPro");
        Query hQuery = hibernateQuery(sqlQuery, ApplicationDetailDTO.class);
        hQuery.setParameter("serviceId", serviceId)
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .setParameter("statusId", statusId);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<ApplicationDetailDTO> getRecheck(Integer serviceId, Integer classType, Date fromDate, Date toDate, Integer statusId) {
        String sqlQuery = properties.getProperty("ReportsDao.getRecheck");
        Query hQuery = hibernateQuery(sqlQuery, ApplicationDetailDTO.class);
        hQuery.setParameter("serviceId", serviceId)
                .setParameter("classType", classType)
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .setParameter("statusId", statusId);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }
}
