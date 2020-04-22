package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.OperatorsTaskListDao;
import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by USER on 8/2/2019.
 */
@Service
public class OperatorsTaskListService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private OperatorsTaskListDao operatorsTaskListDao;

    public ResponseMessage getOperatorsTaskList() {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ApplicationDetailDTO> documentList = operatorsTaskListDao.getOperatorsTaskList();
        if (documentList != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(documentList);
        }
        return responseMessage;
    }

    public ResponseMessage getClaimedTaskList(CurrentUser currentUser) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ApplicationDetailDTO> documentList = operatorsTaskListDao.getClaimedTaskList(currentUser);
        if (documentList != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(documentList);
        }
        return responseMessage;
    }

    public ResponseMessage saveClaimTask(CurrentUser currentUser, String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage = commonService.saveClaimTask(currentUser, applicationNo);
        return responseMessage;
    }

    public ResponseMessage saveUnClaimTask(CurrentUser currentUser, String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage = commonService.saveUnClaimTask(currentUser, applicationNo);
        return responseMessage;
    }
}
