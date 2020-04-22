package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.entity.ServiceActivityDuration;
import com.ngawang.zepa.bcsea.entity.ServiceActivityDuration_A;
import org.hibernate.Query;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;

/**
 * Created by USER on 8/26/2019.
 */
@Repository
public class ServiceActivityDurationDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<ServiceActivityDurationDTO> getServiceActivityDurationList() {
        String sqlQuery = properties.getProperty("ServiceActivityDurationDao.getServiceActivityDurationList");
        Query hQuery = hibernateQuery(sqlQuery, ServiceActivityDurationDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public Integer isExistSave(ServiceActivityDurationDTO serviceActivityDurationDTO) {
        String sqlQuery = properties.getProperty("ServiceActivityDurationDao.isExistSave");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("classType", serviceActivityDurationDTO.getClassType())
                .setParameter("examYear", serviceActivityDurationDTO.getExamYear());
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public Integer isExistUpdate(ServiceActivityDurationDTO serviceActivityDurationDTO) {
        String sqlQuery = properties.getProperty("ServiceActivityDurationDao.isExistUpdate");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("classType", serviceActivityDurationDTO.getClassType())
                .setParameter("examYear", serviceActivityDurationDTO.getExamYear())
                .setParameter("autoIndex", serviceActivityDurationDTO.getAutoIndex());
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public Integer getAutoIndex() {
        String sqlQuery = properties.getProperty("ServiceActivityDurationDao.getAutoIndex");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveServiceActivityDuration(ServiceActivityDuration serviceActivityDuration,
                                            ServiceActivityDuration_A serviceActivityDuration_a) {
        getReportingSession().saveOrUpdate(serviceActivityDuration);
        getReportingSession().save(serviceActivityDuration_a);
    }

}
