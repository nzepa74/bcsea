package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by USER on 8/21/2019.
 */
@Service
public class ApproveIssueEnglishLanProCertificateService {
    @Autowired
    private CommonService commonService;

    public ResponseMessage approveApplication(CurrentUser currentUser, String applicationNo, String remarks,
                                              Integer rejectReasonId, String rejectRemarks) {
        ResponseMessage responseMessage;
        responseMessage = commonService.approveApplication(currentUser, applicationNo, remarks, rejectReasonId,
                rejectRemarks);
        responseMessage.setApplicationNumber(applicationNo);
        responseMessage.setServiceName(ServiceTypeEnum.ISSUE_ENGLISH_LAN_PRO_CERTIFICATE.getServiceName());
        return responseMessage;
    }
}
