package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.ApproveRecheckApplicationDao;
import com.ngawang.zepa.bcsea.dto.ApproveRecheckApplicationDTO;
import com.ngawang.zepa.bcsea.dto.SubjectDTO;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by USER on 8/20/2019.
 */
@Service
public class ApproveRecheckApplicationService {
    @Autowired
    private CommonService commonService;
    @Autowired
    ApproveRecheckApplicationDao approveRecheckApplicationDao;

    public ResponseMessage getRecheckPapers(String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<SubjectDTO> subjectDTOs = approveRecheckApplicationDao.getRecheckPapers(applicationNo);
        if (subjectDTOs != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(subjectDTOs);
        }
        return responseMessage;
    }

    public ResponseMessage saveApproveRecheckApplication(CurrentUser currentUser, ApproveRecheckApplicationDTO
            approveRecheckApplicationDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        String applicationNo = approveRecheckApplicationDTO.getApplicationNo();
        String remarks = approveRecheckApplicationDTO.getRemarks();
        Integer rejectReasonId = approveRecheckApplicationDTO.getRejectReasonId();
        String rejectRemarks = approveRecheckApplicationDTO.getRejectRemarks();
        if (rejectReasonId == null) {
            if (approveRecheckApplicationDTO.getSubjectDTOs() == null) {
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                responseMessage.setResponseText("Cannot approve without entering new marks.");
                return responseMessage;
            }
            for (SubjectDTO subjectDTO : approveRecheckApplicationDTO.getSubjectDTOs()) {
                BigInteger recheckDetailId = subjectDTO.getRecheckDetailId();
                String newMarks = subjectDTO.getNewMarks();
                if (newMarks == null) {
                    responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                    responseMessage.setResponseText("Please enter marks obtained.");
                    return responseMessage;
                }
                approveRecheckApplicationDao.updateNewMarks(recheckDetailId, applicationNo, newMarks);
            }
        }
        responseMessage = commonService.approveApplication(currentUser, applicationNo, remarks, rejectReasonId,
                rejectRemarks);
        responseMessage.setApplicationNumber(applicationNo);
        responseMessage.setServiceName(ServiceTypeEnum.CLERICAL_RECHECK.getServiceName());
        return responseMessage;
    }
}
