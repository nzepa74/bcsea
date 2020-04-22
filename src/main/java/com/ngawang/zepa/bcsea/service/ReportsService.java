package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.ReportsDao;
import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.DropdownDTO;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by USER on 9/12/2019.
 */
@Service
public class ReportsService {
    @Autowired
    private ReportsDao reportsDao;

    public List<DropdownDTO> getStatusList() {
        return reportsDao.getStatusList();
    }

    public ResponseMessage getReport(Integer serviceId, Integer documentId, Integer classType, Date fromDate,
                                     Date toDate, Integer statusId) {

        ResponseMessage responseMessage = new ResponseMessage();

        List<ApplicationDetailDTO> applicationDetailDTOs = null;

        if (statusId == 0) {//if status is ALL
            statusId = null;
        }
        if (serviceId == 311 || serviceId == 312) {
            applicationDetailDTOs = reportsDao.getDuplicateOrReplacement(serviceId, documentId, classType, fromDate, toDate, statusId);
        } else if (serviceId == 313) {
            applicationDetailDTOs = reportsDao.getEnglishLanPro(serviceId, fromDate, toDate, statusId);
        } else if (serviceId == 314) {
            applicationDetailDTOs = reportsDao.getRecheck(serviceId, classType, fromDate, toDate, statusId);
        }
        if (applicationDetailDTOs != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(applicationDetailDTOs);
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Result not found");
        }
        return responseMessage;
    }
}
