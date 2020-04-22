package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.IssueReplacementCertificateDao;
import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.dto.AppliedDocumentDTO;
import com.ngawang.zepa.bcsea.dto.FileAttachmentDTO;
import com.ngawang.zepa.bcsea.entity.AppliedDocument;
import com.ngawang.zepa.bcsea.entity.FileAttachment;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.FileUploadDTO;
import com.ngawang.zepa.helper.FileUploadToExternalLocation;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by USER on 7/31/2019.
 */
@Service
public class IssueReplacementCertificateService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private IssueReplacementCertificateDao issueReplacementCertificateDao;

    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage saveIssueReplacementCertificate(HttpServletRequest request, CurrentUser currentUser, ApplicationDetailDTO applicationDetailDTO) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage();

        Integer globalServiceTypeId = ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceTypeId();
        String applicationType = ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceCode();
        String applicationNo = commonService.generateApplicationNumber(globalServiceTypeId, applicationType);
        applicationDetailDTO.setApplicationNo(applicationNo);
        applicationDetailDTO.setServiceSlNo(globalServiceTypeId);
        String serviceName = ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceName();

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

        //region to save applied document
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
        //endregion

        //region upload attached files in folder
        for (FileAttachmentDTO fileAttachmentDTO : applicationDetailDTO.getFileAttachmentDTOs()) {
            MultipartFile attachedFile = fileAttachmentDTO.getAttachedFile();
            if (attachedFile != null) {
                String attachedFileName = attachedFile.getOriginalFilename();
                String attachedFileExt = attachedFileName.substring(attachedFileName.lastIndexOf("."));
                String fileName = attachedFileName;
                responseMessage = FileUploadToExternalLocation.fileUploader(attachedFile, fileName, "attachFile.properties", request);
                if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value()) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return responseMessage;
                }
            }
        }
        //endregion
        // region attached file information
        Integer documentId = issueReplacementCertificateDao.getDocumentId();
        if (documentId == null) {
            documentId = 1;
        }
        Integer iterator = 0;
        for (FileAttachmentDTO fileAttachmentDTO : applicationDetailDTO.getFileAttachmentDTOs()) {
            MultipartFile attachedFile = fileAttachmentDTO.getAttachedFile();
            if (attachedFile != null) {
                String attachedFileName = attachedFile.getOriginalFilename();
                String attachedFileExt = attachedFileName.substring(attachedFileName.lastIndexOf("."));
                String fileName = attachedFileName;

                iterator = iterator + 1;
                FileUploadDTO fileUploadDTO = FileUploadToExternalLocation.fileUploadPathRetriever(request);
                String uploadedDirectory = fileUploadDTO.getUploadFilePath().concat(attachedFileName);
                fileAttachmentDTO.setDocumentId(documentId + iterator);
                fileAttachmentDTO.setUploadUrl(uploadedDirectory);
                fileAttachmentDTO.setApplicationNo(applicationNo);
                fileAttachmentDTO.setDocumentName(attachedFileName);
                FileAttachment fileAttachment = convertFileAttachmentEntToDTO(fileAttachmentDTO);
                issueReplacementCertificateDao.saveAttachFile(fileAttachment);
            }
        }
        //endregion

        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        responseMessage.setApplicationNumber(applicationNo);
        responseMessage.setServiceName(ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceName());
        responseMessage.setResponseText("Submitted successfully.");
        return responseMessage;
    }

    private FileAttachment convertFileAttachmentEntToDTO(FileAttachmentDTO fileAttachmentDTO) {
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setDocumentId(fileAttachmentDTO.getDocumentId());
        fileAttachment.setDocumentTypeId(fileAttachmentDTO.getDocumentTypeId());
        fileAttachment.setApplicationNo(fileAttachmentDTO.getApplicationNo());
        fileAttachment.setDocumentType(fileAttachmentDTO.getDocumentType());
        fileAttachment.setDocumentName(fileAttachmentDTO.getDocumentName());
        fileAttachment.setUploadUrl(fileAttachmentDTO.getUploadUrl());
        fileAttachment.setUuId(fileAttachmentDTO.getUuId());
        return fileAttachment;
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
