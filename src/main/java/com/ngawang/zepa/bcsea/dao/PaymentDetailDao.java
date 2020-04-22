package com.ngawang.zepa.bcsea.dao;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.dto.PaymentDetailDTO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by USER on 9/8/2019.
 */
@Repository
public class PaymentDetailDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<ApplicationDetailDTO> getPaymentList() {
        String sqlQuery = properties.getProperty("PaymentDetailDao.getPaymentList");
        Query hQuery = hibernateQuery(sqlQuery, ApplicationDetailDTO.class);
        return hQuery.list().isEmpty() ? null : hQuery.list();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePaymentDetail(PaymentDetailDTO paymentDetailDTO) {
        String sqlQuery = properties.getProperty("PaymentDetailDao.updatePaymentDetail");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("paymentDetailId", paymentDetailDTO.getPaymentDetailId())
                .setParameter("applicationNo", paymentDetailDTO.getApplicationNo())
                .setParameter("paymentType", paymentDetailDTO.getPaymentType())
                .setParameter("receiptNo", paymentDetailDTO.getReceiptNo())
                .setParameter("depositDate", paymentDetailDTO.getDepositDate())
                .setParameter("isPaidStatus", 'P'
                );
        hQuery.executeUpdate();
    }
}
