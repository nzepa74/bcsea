package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ResultDTO;
import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by USER on 8/22/2019.
 */
@Repository
public class ViewResultDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<ResultDTO> getResult(String indexNo, Integer classType) {
        String sqlQuery = properties.getProperty("ViewResultDao.getResult");
        Query hQuery = hibernateQuery(sqlQuery, ResultDTO.class);
        hQuery.setParameter("indexNo", indexNo)
        .setParameter("classType", classType);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public StudentDetailDTO getStudentDetail(String indexNo) {
        String query = properties.getProperty("ViewResultDao.getStudentDetail");
        org.hibernate.Query hQuery = hibernateQuery(query, StudentDetailDTO.class);
        hQuery.setParameter("indexNo", indexNo);
        return (StudentDetailDTO) hQuery.uniqueResult();
    }
}
