package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;

/**
 * Created by USER on 9/13/2019.
 */
@Repository
public class PrintCertificateDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<ApplicationDetailDTO> getCertificateList(Integer serviceId) {
        String sqlQuery;
        if (serviceId == 313) {
            sqlQuery = properties.getProperty("PrintCertificateDao.getLanguageProList");
        } else {
            sqlQuery = properties.getProperty("PrintCertificateDao.getCertificateList");
        }
        Query hQuery = hibernateQuery(sqlQuery, ApplicationDetailDTO.class);
        hQuery.setParameter("serviceId", serviceId);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }
}
