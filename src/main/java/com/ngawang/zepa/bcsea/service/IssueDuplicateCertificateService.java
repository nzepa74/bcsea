package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.dto.AppliedDocumentDTO;
import com.ngawang.zepa.bcsea.entity.AppliedDocument;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.BeanValidator;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueDuplicateCertificateService {
    @Autowired
    private CommonService commonService;
    @Autowired
    protected BeanValidator beanValidator;

    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage saveIssueDuplicateCertificate(CurrentUser currentUser, ApplicationDetailDTO applicationDetailDTO) {
        ResponseMessage responseMessage = new ResponseMessage();

        Integer globalServiceTypeId = ServiceTypeEnum.ISSUE_DUPLICATE_CERTIFICATE.getServiceTypeId();
        String applicationType = ServiceTypeEnum.ISSUE_DUPLICATE_CERTIFICATE.getServiceCode();
        String applicationNo = commonService.generateApplicationNumber(globalServiceTypeId, applicationType);
        applicationDetailDTO.setApplicationNo(applicationNo);
        applicationDetailDTO.setServiceSlNo(globalServiceTypeId);
        String serviceName = ServiceTypeEnum.ISSUE_DUPLICATE_CERTIFICATE.getServiceName();

        //region to validate if the document is already applied
        for (AppliedDocumentDTO appliedDocumentDTO : applicationDetailDTO.getAppliedDocumentDTOs()) {
            if (appliedDocumentDTO.getDocumentTypeId() != null) {
                responseMessage = commonService.validateDuplicateDocument(applicationDetailDTO.getCidNo(), applicationDetailDTO.getIndexNo()
                        , globalServiceTypeId, appliedDocumentDTO.getDocumentTypeId());
                String documentName = commonService.getDocumentName(appliedDocumentDTO.getDocumentTypeId());
                if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value()) {
                    responseMessage.setResponseText("You have already applied for " + documentName + " under " + serviceName);
                    return responseMessage;
                }
            }
        }
        //endregion

        commonService.saveApplication(currentUser, applicationDetailDTO);

        Integer appliedDocumentId = commonService.getAppliedDocumentId();
        if (appliedDocumentId == null) {
            appliedDocumentId = 1;
        }
        Integer counter = 0;
        for (AppliedDocumentDTO appliedDocumentDTO : applicationDetailDTO.getAppliedDocumentDTOs()) {
            if (appliedDocumentDTO.getDocumentTypeId() != null) {
                counter = counter + 1;
                appliedDocumentDTO.setAppliedDocumentId(appliedDocumentId + counter);
                appliedDocumentDTO.setApplicationNo(applicationNo);
                AppliedDocument appliedDocument = convertAppliedDocumentDTOtoEntity(appliedDocumentDTO);
                commonService.saveAppliedDocument(appliedDocument);
            }
        }
        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        responseMessage.setApplicationNumber(applicationNo);
        responseMessage.setServiceName(ServiceTypeEnum.ISSUE_DUPLICATE_CERTIFICATE.getServiceName());
        responseMessage.setResponseText("Submitted successfully.");
        return responseMessage;
    }

    private AppliedDocument convertAppliedDocumentDTOtoEntity(AppliedDocumentDTO appliedDocumentDTO) {
        AppliedDocument appliedDocument = new AppliedDocument();
        appliedDocument.setAppliedDocumentId(appliedDocumentDTO.getAppliedDocumentId());
        appliedDocument.setApplicationNo(appliedDocumentDTO.getApplicationNo());
        appliedDocument.setDocumentTypeId(appliedDocumentDTO.getDocumentTypeId());
        appliedDocument.setPrinted('N');
        appliedDocument.setRejected('N');
        return appliedDocument;
    }
}
