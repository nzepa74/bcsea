package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ChargeCalculationDTO;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by USER on 9/7/2019.
 */
@Repository
public class ChargeCalculationDao extends BaseDao {

    @Transactional(readOnly = true)
    public ChargeCalculationDTO getDuplicateOrReplacementDocCharge(Integer serviceId, String indexNo,
                                                                   Integer documentId) {
        String query = properties.getProperty("ChargeCalculationDao.getDuplicateOrReplacementDocCharge");
        org.hibernate.Query hQuery = hibernateQuery(query, ChargeCalculationDTO.class);
        hQuery.setParameter("serviceId", serviceId)
                .setParameter("indexNo", indexNo)
                .setParameter("documentId", documentId);
        return (ChargeCalculationDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public ChargeCalculationDTO getEnglishLanProCharge(Integer serviceId, String cidNo) {
        String query = properties.getProperty("ChargeCalculationDao.getEnglishLanProCharge");
        org.hibernate.Query hQuery = hibernateQuery(query, ChargeCalculationDTO.class);
        hQuery.setParameter("serviceId", serviceId)
                .setParameter("cidNo", cidNo);
        return (ChargeCalculationDTO) hQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    public String isValidCid(String cidNo) {
        String sqlQuery = properties.getProperty("ChargeCalculationDao.isValidCid");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("cidNo", cidNo);
        return (String) hQuery.uniqueResult();
    }
}
