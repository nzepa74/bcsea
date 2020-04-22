package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.RecheckApplicationDao;
import com.ngawang.zepa.bcsea.dto.*;
import com.ngawang.zepa.bcsea.entity.RecheckPaperDetail;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 8/3/2019.
 */
@Service
public class RecheckApplicationService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private RecheckApplicationDao recheckApplicationDao;

    public ResponseMessage getServiceActivityDuration(String indexNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        StudentDetailDTO studentDetailDTO = recheckApplicationDao.getStudentInfoByIndexNo(indexNo);
        if (studentDetailDTO != null) {
            Integer classType = studentDetailDTO.getClassType();
            Integer examYear = studentDetailDTO.getExamYear();
            ServiceActivityDurationDTO serviceActivityDurationDTO = recheckApplicationDao.getServiceActivityDuration
                    (ServiceTypeEnum.CLERICAL_RECHECK.getServiceTypeId(), classType, examYear);
            if (serviceActivityDurationDTO != null) {
                Date currentDate = new Date();
                Date activeFrom = serviceActivityDurationDTO.getActiveFrom();
                Date activeTo = serviceActivityDurationDTO.getActiveTo();
                if (currentDate.before(activeFrom)) {
                    responseMessage.setResponseDTO(serviceActivityDurationDTO);
                    responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_WARNING.value());
                }
                if (currentDate.after(activeTo)) {
                    responseMessage.setResponseDTO(serviceActivityDurationDTO);
                    responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_APPROVAL.value());
                }
                if (currentDate.after(activeFrom) && currentDate.before(activeTo)) {
                    responseMessage.setResponseDTO(serviceActivityDurationDTO);
                    responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
                }
            } else {
                responseMessage.setResponseText("Service activity information not found");
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_REJECT.value());
            }
        }
        return responseMessage;
    }

    public ResponseMessage getRecheckCharge() {
        ResponseMessage responseMessage = new ResponseMessage();
        ServiceChargeDTO serviceChargeDTO = recheckApplicationDao.getRecheckCharge(ServiceTypeEnum.CLERICAL_RECHECK.getServiceTypeId());
        if (serviceChargeDTO != null) {
            responseMessage.setResponseDTO(serviceChargeDTO);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        }
        return responseMessage;
    }

    public ResponseMessage getStudentInfoByIndexNo(String indexNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        StudentDetailDTO studentDetailDTO = recheckApplicationDao.getStudentInfoByIndexNo(indexNo);
        if (studentDetailDTO != null) {
            responseMessage.setResponseDTO(studentDetailDTO);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        } else {
            responseMessage.setResponseText("Invalid Index Number");
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
        }
        return responseMessage;
    }

    public ResponseMessage getSubjectsByIndexNo(String indexNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<SubjectDTO> subjectDTOs = recheckApplicationDao.getSubjectsByIndexNo(indexNo);
        if (subjectDTOs != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(subjectDTOs);
        }
        return responseMessage;
    }

    public ResponseMessage saveRecheckApplication(CurrentUser currentUser, ApplicationDetailDTO applicationDetailDTO) {
        ResponseMessage responseMessage = new ResponseMessage();

        Integer globalServiceTypeId = ServiceTypeEnum.CLERICAL_RECHECK.getServiceTypeId();
        String applicationType = ServiceTypeEnum.CLERICAL_RECHECK.getServiceCode();
        StudentDetailDTO studentDetailDTO = recheckApplicationDao.getStudentInfoByIndexNo(applicationDetailDTO.getIndexNo());
        if (studentDetailDTO != null) {
            Integer classType = studentDetailDTO.getClassType();
            Integer examYear = studentDetailDTO.getExamYear();
            ServiceActivityDurationDTO serviceActivityDurationDTO = recheckApplicationDao.getServiceActivityDuration
                    (ServiceTypeEnum.CLERICAL_RECHECK.getServiceTypeId(), classType, examYear);
            if (serviceActivityDurationDTO != null) {
                Date currentDate = new Date();
                Date activeFrom = serviceActivityDurationDTO.getActiveFrom();
                Date activeTo = serviceActivityDurationDTO.getActiveTo();
                if (currentDate.after(activeFrom) && currentDate.before(activeTo)) {
                    String applicationNo = commonService.generateApplicationNumber(globalServiceTypeId, applicationType);
                    applicationDetailDTO.setApplicationNo(applicationNo);
                    applicationDetailDTO.setServiceSlNo(globalServiceTypeId);
                    responseMessage = validateDuplicatePaper(applicationDetailDTO);
                    if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value()) {
                        return responseMessage;
                    }
                    commonService.saveApplication(currentUser, applicationDetailDTO);
                    BigInteger recheckDetailId = recheckApplicationDao.getRecheckDetailId();
                    if (recheckDetailId == null) {
                        recheckDetailId = BigInteger.ZERO;
                    }
                    Integer counter = 0;
                    for (SubjectDTO subjectDTO : applicationDetailDTO.getSubjectDTOs()) {
                        if (subjectDTO.getPaperId() != null) {
                            counter = counter + 1;
                            subjectDTO.setRecheckDetailId(recheckDetailId.add(BigInteger.valueOf(counter)));
                            subjectDTO.setApplicationNo(applicationNo);
                            Integer subjectId = recheckApplicationDao.getSubjectIdByPaperId(subjectDTO.getPaperId());
                            String oldMarks = recheckApplicationDao.getOldMarksByPaperId(subjectDTO.getPaperId(), applicationDetailDTO.getIndexNo());
                            subjectDTO.setSubjectId(subjectId);
                            subjectDTO.setOldMarks(oldMarks);
                            subjectDTO.setIndexNo(applicationDetailDTO.getIndexNo());
                            subjectDTO.setClassType(classType);
                            RecheckPaperDetail recheckPaperDetail = convertDTOtoEntity(subjectDTO);
                            recheckApplicationDao.saveRecheckPaperDetail(recheckPaperDetail);
                        }
                    }
                    responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
                    responseMessage.setApplicationNumber(applicationNo);
                    responseMessage.setServiceName(ServiceTypeEnum.CLERICAL_RECHECK.getServiceName());
                    responseMessage.setResponseText("Submitted successfully.");
                }
            }
        }
        return responseMessage;
    }

    private ResponseMessage validateDuplicatePaper(ApplicationDetailDTO applicationDetailDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        String serviceName = ServiceTypeEnum.CLERICAL_RECHECK.getServiceName();
        for (SubjectDTO subjectDTO : applicationDetailDTO.getSubjectDTOs()) {
            if (subjectDTO.getPaperId() != null) {
                String paperName = recheckApplicationDao.getPaperName(subjectDTO.getPaperId());
                String applicationNo = recheckApplicationDao.getDuplicateRecheckPaper(applicationDetailDTO.getIndexNo()
                        , subjectDTO.getPaperId());
                if (applicationNo != null) {
                    responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                    responseMessage.setResponseText("You have already applied " + serviceName + " for " + paperName);
                    return responseMessage;
                }else {
                    responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
                }
            }
        }
        return responseMessage;
    }

    private RecheckPaperDetail convertDTOtoEntity(SubjectDTO subjectDTO) {
        RecheckPaperDetail recheckPaperDetail = new RecheckPaperDetail();
        recheckPaperDetail.setRecheckDetailId(subjectDTO.getRecheckDetailId());
        recheckPaperDetail.setApplicationNo(subjectDTO.getApplicationNo());
        recheckPaperDetail.setIndexNo(subjectDTO.getIndexNo());
        recheckPaperDetail.setPaperId(subjectDTO.getPaperId());
        recheckPaperDetail.setSubjectId(subjectDTO.getSubjectId());
        recheckPaperDetail.setOldMarks(subjectDTO.getOldMarks());
        recheckPaperDetail.setNewMarks(subjectDTO.getNewMarks());
        recheckPaperDetail.setClassType(subjectDTO.getClassType());
        return recheckPaperDetail;
    }
}
