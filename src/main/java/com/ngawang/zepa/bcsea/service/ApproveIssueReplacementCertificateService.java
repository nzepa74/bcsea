package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.ApproveIssueReplacementCertificateDao;
import com.ngawang.zepa.bcsea.dto.FileAttachmentDTO;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.FileUploadToExternalLocation;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 8/15/2019.
 */
@Service
public class ApproveIssueReplacementCertificateService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private ApproveIssueReplacementCertificateDao approveIssueReplacementCertificateDao;

    public ResponseMessage getAttachedFiles(String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<FileAttachmentDTO> fileAttachmentDTOs = approveIssueReplacementCertificateDao.getAttachedFiles(applicationNo);
        if (fileAttachmentDTOs != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(fileAttachmentDTOs);
        }
        return responseMessage;
    }

    public ResponseMessage downloadFile(Integer documentId, HttpServletResponse response) throws IOException {
        ResponseMessage responseMessage;
        FileAttachmentDTO fileAttachmentDTO = approveIssueReplacementCertificateDao.downloadFile(documentId);
        String uploadFilePath = fileAttachmentDTO.getUploadUrl();
        String fileName = fileAttachmentDTO.getDocumentName();
        responseMessage = FileUploadToExternalLocation.fileDownloader(fileName, uploadFilePath, response);
        if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value()) {
            return responseMessage;
        }
        responseMessage.setResponseDTO(fileAttachmentDTO);
        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        return responseMessage;
    }

    public ResponseMessage saveApproveIssueReplacementCertificate(CurrentUser currentUser, String applicationNo,
                                                                  String remarks, Integer rejectReasonId,
                                                                  String rejectRemarks, String nameChange,
                                                                  Date dobChange) throws ParseException {
        ResponseMessage responseMessage;
        if (rejectReasonId == null) {
            updateStudentInfo(applicationNo, nameChange, dobChange);
        }
        responseMessage = commonService.approveApplication(currentUser, applicationNo, remarks, rejectReasonId,
                rejectRemarks);
        responseMessage.setApplicationNumber(applicationNo);
        responseMessage.setServiceName(ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceName());
        return responseMessage;
    }

    private ResponseMessage updateStudentInfo(String applicationNo, String nameChange, Date dobChange) throws ParseException {
        ResponseMessage responseMessage = new ResponseMessage();
        String indexNo = approveIssueReplacementCertificateDao.getIndexNo(applicationNo);
        if (indexNo == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Index no not found matching application no " + applicationNo);
            return responseMessage;
        }
        if (!nameChange.equals("") && dobChange != null) {
            approveIssueReplacementCertificateDao.updateNameAndDob(indexNo, nameChange, formatDate(dobChange));
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        }
        if (!nameChange.equals("") && dobChange == null) {
            approveIssueReplacementCertificateDao.updateStudentName(indexNo, nameChange);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        }
        if (nameChange.equals("") && dobChange != null) {
            approveIssueReplacementCertificateDao.updateStudentDob(indexNo, formatDate(dobChange));
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        }
        return responseMessage;
    }

    private String formatDate(Date dobChange) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(dobChange);
        return date;
    }
}
