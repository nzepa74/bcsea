package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.IssueEnglishLanProCertificateDao;
import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by USER on 8/1/2019.
 */
@Service
public class IssueEnglishLanProCertificateService {
    @Autowired
    private IssueEnglishLanProCertificateDao issueEnglishLanProCertificateDao;
    @Autowired
    private CommonService commonService;

    public ResponseMessage getEnglishMarkByIndexNo(String indexNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        StudentDetailDTO studentDetailDTO = issueEnglishLanProCertificateDao.getEnglishMarkByIndexNo(indexNo);
        if (studentDetailDTO != null) {
            if (studentDetailDTO.getEnglishMark() != null) {
                responseMessage.setResponseDTO(studentDetailDTO);
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            } else {
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                responseMessage.setResponseText("English marks not available.");
            }
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("English marks not available.");
        }
        return responseMessage;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage saveIssueEnglishLanProCertificate(CurrentUser currentUser,
                                                             ApplicationDetailDTO applicationDetailDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        Integer globalServiceTypeId = ServiceTypeEnum.ISSUE_ENGLISH_LAN_PRO_CERTIFICATE.getServiceTypeId();
        String applicationType = ServiceTypeEnum.ISSUE_ENGLISH_LAN_PRO_CERTIFICATE.getServiceCode();
        String applicationNo = commonService.generateApplicationNumber(globalServiceTypeId, applicationType);
        applicationDetailDTO.setApplicationNo(applicationNo);
        applicationDetailDTO.setServiceSlNo(globalServiceTypeId);

        String serviceName = ServiceTypeEnum.ISSUE_ENGLISH_LAN_PRO_CERTIFICATE.getServiceName();

        //region to validate if already applied or not
        String duplicateApplicationNo = issueEnglishLanProCertificateDao.getDuplicateApplicationNo(applicationDetailDTO.getCidNo(),
                applicationDetailDTO.getIndexNo(), globalServiceTypeId);
        if (duplicateApplicationNo != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("You have already applied for " + serviceName + " and your application is under process.");
            return responseMessage;
        }
        //endregion

        commonService.saveApplication(currentUser, applicationDetailDTO);

        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        responseMessage.setApplicationNumber(applicationNo);
        responseMessage.setServiceName(ServiceTypeEnum.ISSUE_ENGLISH_LAN_PRO_CERTIFICATE.getServiceName());
        responseMessage.setResponseText("Submitted successfully.");
        return responseMessage;
    }
}
