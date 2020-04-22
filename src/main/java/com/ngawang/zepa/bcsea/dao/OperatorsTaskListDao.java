package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.helper.CurrentUser;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by USER on 8/2/2019.
 */
@Repository
public class OperatorsTaskListDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<ApplicationDetailDTO> getOperatorsTaskList() {
        String sqlQuery = properties.getProperty("OperatorsTaskListDao.getOperatorsTaskList");
        Query hQuery = hibernateQuery(sqlQuery, ApplicationDetailDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<ApplicationDetailDTO> getClaimedTaskList(CurrentUser currentUser) {
        String sqlQuery = properties.getProperty("OperatorsTaskListDao.getClaimedTaskList");
        Query hQuery = hibernateQuery(sqlQuery, ApplicationDetailDTO.class);
        hQuery.setParameter("owner",  currentUser.getUsername());
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }
}
