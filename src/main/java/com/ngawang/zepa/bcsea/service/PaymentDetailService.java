package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.PaymentDetailDao;
import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.dto.PaymentDetailDTO;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by USER on 9/8/2019.
 */
@Service
public class PaymentDetailService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private PaymentDetailDao paymentDetailDao;

    public ResponseMessage getPaymentList() {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ApplicationDetailDTO> documentList = paymentDetailDao.getPaymentList();
        if (documentList != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(documentList);
        }
        return responseMessage;
    }

    public ResponseMessage updatePaymentDetail(CurrentUser currentUser, PaymentDetailDTO paymentDetailDTO) {
        ResponseMessage responseMessage;
        responseMessage = commonService.updatePaymentDetail(currentUser, paymentDetailDTO);
        if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value()) {
            paymentDetailDao.updatePaymentDetail(paymentDetailDTO);
            responseMessage.setResponseText("Updated Successfully.");
        }
        //TODO:Need to update application detail tale status Id to payment cleared
        return responseMessage;
    }
}
