package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.ChargeCalculationDao;
import com.ngawang.zepa.bcsea.dto.ChargeCalculationDTO;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by USER on 9/7/2019.
 */
@Service
public class ChargeCalculationService {
    @Autowired
    private ChargeCalculationDao chargeCalculationDao;

    public ResponseMessage getDuplicateOrReplacementDocCharge(Integer serviceId, String indexNo, Integer documentId) {
        ResponseMessage responseMessage = new ResponseMessage();
        ChargeCalculationDTO chargeCalculationDTO = chargeCalculationDao.getDuplicateOrReplacementDocCharge
                (serviceId, indexNo, documentId);
        if (chargeCalculationDTO != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(chargeCalculationDTO);
        }
        return responseMessage;
    }

    public ResponseMessage getEnglishLanProCharge(Integer serviceId, String cidNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        ChargeCalculationDTO chargeCalculationDTO = chargeCalculationDao.getEnglishLanProCharge(serviceId, cidNo);
        if (chargeCalculationDTO != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(chargeCalculationDTO);
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("No information found matching " + cidNo);
        }
        return responseMessage;
    }

    public ResponseMessage isValidCid(String cidNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        String cidNumber = chargeCalculationDao.isValidCid(cidNo);
        if (cidNumber != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("No information found matching " + cidNo);
        }
        return responseMessage;
    }
}
