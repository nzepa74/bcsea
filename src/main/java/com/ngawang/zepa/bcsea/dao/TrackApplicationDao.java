package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by USER on 8/22/2019.
 */
@Repository
public class TrackApplicationDao extends BaseDao {

    @Transactional(readOnly = true)
    public ApplicationDetailDTO getTrackApplicationDetail(String applicationNo) {
        String query = properties.getProperty("TrackApplicationDao.getTrackApplicationDetail");
        org.hibernate.Query hQuery = hibernateQuery(query, ApplicationDetailDTO.class);
        hQuery.setParameter("applicationNo", applicationNo);
        return (ApplicationDetailDTO) hQuery.uniqueResult();
    }
}
