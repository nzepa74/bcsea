package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.CommonDao;
import com.ngawang.zepa.bcsea.dto.*;
import com.ngawang.zepa.bcsea.entity.*;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.DropdownDTO;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 7/28/2019.
 */
@Service
public class CommonService {
    @Autowired
    private CommonDao commonDao;

    Calendar calendar = Calendar.getInstance();
    Integer currentYear = calendar.get(Calendar.YEAR);
    Integer currentMonth = calendar.get(Calendar.MONTH) + 1;
    Integer currentDay = calendar.get(Calendar.DATE);

    public ResponseMessage getDocumentList() {
        ResponseMessage responseMessage = new ResponseMessage();
        List<DropdownDTO> documentList = commonDao.getDocumentList();
        if (documentList != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(documentList);
        }
        return responseMessage;
    }

    public ResponseMessage getAppliedDocumentList(String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<DropdownDTO> documentList = commonDao.getAppliedDocumentList(applicationNo);
        if (documentList != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(documentList);
        }
        return responseMessage;
    }

    public String generateApplicationNumber(Integer globalServiceTypeId, String applicationType) {
        String applicationDay = currentDay.toString().length() == 1 ? "0" + currentDay.toString() : currentDay.toString();
        String applicationMonth = currentMonth.toString().length() == 1 ? "0" + currentMonth.toString() : currentMonth.toString();
        String applicationYear = currentYear.toString().substring(2, 4);
        String serialId = commonDao.getApplicationSerial(globalServiceTypeId, applicationYear,
                applicationMonth, applicationDay, applicationType);
        serialId = serialId == null ? "0" : serialId;
        Integer applicationSerial = Integer.parseInt(serialId);
        applicationSerial = applicationSerial + 1;
        String serialNumber = null;
        String applicationNumber;

        if (applicationSerial.toString().length() == 1) {
            serialNumber = "0000" + applicationSerial.toString();
        }
        if (applicationSerial.toString().length() == 2) {
            serialNumber = "000" + applicationSerial.toString();
        }
        if (applicationSerial.toString().length() == 3) {
            serialNumber = "00" + applicationSerial.toString();
        }
        if (applicationSerial.toString().length() == 4) {
            serialNumber = "0" + applicationSerial.toString();
        }
        if (applicationSerial.toString().length() == 5) {
            serialNumber = applicationSerial.toString();
        }
        applicationNumber = globalServiceTypeId.toString() + "_" + applicationYear + applicationMonth + applicationDay + applicationType + serialNumber;
        return applicationNumber;
    }

    public ResponseMessage getCitizenName(String cidNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        StudentDetailDTO studentDetailDTO;
        studentDetailDTO = commonDao.getCitizenName(cidNo);
        if (studentDetailDTO != null) {
            responseMessage.setResponseDTO(studentDetailDTO);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("No information found matching CID No " + cidNo);
        }
        return responseMessage;
    }

    public ResponseMessage indexNoValidation(String cidNo, String indexNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        StudentDetailDTO studentDetailDTO = commonDao.getStudentDetailsByIndexNo(indexNo);
        if (studentDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Invalid Index Number");
            return responseMessage;
        }

        if (!studentDetailDTO.getCidNo().equals(cidNo)) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Index Number doesn't belong to a person bearing CID number " + cidNo);
            return responseMessage;
        }
        if (studentDetailDTO.getCidNo().equals(cidNo)) {
            responseMessage.setResponseDTO(studentDetailDTO);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            return responseMessage;
        }
        return responseMessage;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveApplication(CurrentUser currentUser, ApplicationDetailDTO applicationDetailDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        StudentDetailDTO studentDetailDTO = commonDao.getStudentDetailsByIndexNo(applicationDetailDTO.getIndexNo());
        //region save to t_application_details
        applicationDetailDTO.setSubmissionDate(new Date());
        applicationDetailDTO.setSubmittedBy("CITIZEN(CITIZEN)");
        applicationDetailDTO.setIsPrinted('N');
        applicationDetailDTO.setPaymentMade("N");
        applicationDetailDTO.setStatusId(1);
        ApplicationDetail applicationDetail = convertDTOtoEntity(currentUser, applicationDetailDTO, studentDetailDTO);
        commonDao.saveApplication(applicationDetail);
        //endregion

        //region save to t_workflow_dtls table
        WorkFlowDetailDTO workFlowDetailDTO = new WorkFlowDetailDTO();
        String workInstanceId = getWorkInstanceId();
        workFlowDetailDTO.setWorkFlowInstanceId(workInstanceId);
        workFlowDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        workFlowDetailDTO.setApplicationStatusId(1);
        workFlowDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        workFlowDetailDTO.setCreatedDate(applicationDetailDTO.getSubmissionDate());
        WorkFlowDetail workFlowDetail = convertWorkFlowDetailDTOtoEntity(workFlowDetailDTO);
        commonDao.saveWorkFlowDetail(workFlowDetail);
        //endregion

        //region save to t_task_dtls table
        TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
        String taskInstanceId = getTaskInstanceId();
        taskDetailDTO.setTaskInstanceId(taskInstanceId);
        taskDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        taskDetailDTO.setApplicationStatus(1);
        taskDetailDTO.setWorkflowInstanceId(workInstanceId);
        taskDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        taskDetailDTO.setCreatedOn(applicationDetailDTO.getSubmissionDate());
        TaskDetail taskDetail = convertTaskDetailDTOtoEntity(taskDetailDTO);
        commonDao.saveTaskDetail(taskDetail);
        //endregion

        //region to save into t_app_payment_details table
        PaymentDetailDTO paymentDetailDTO = new PaymentDetailDTO();
        Integer paymentDetailId = getPaymentDetailId();
        Integer amountCharge = getAmountCharge(applicationDetailDTO);
        paymentDetailDTO.setPaymentDetailId(paymentDetailId + 1);
        paymentDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        paymentDetailDTO.setServiceNo(applicationDetailDTO.getServiceSlNo());
        paymentDetailDTO.setPaymentType(null);
        paymentDetailDTO.setAmountCharge(amountCharge);
        paymentDetailDTO.setDepositDate(null);
        paymentDetailDTO.setIsPaid('N');
        PaymentDetail paymentDetail = convertPaymentDetailDtoToEntity(paymentDetailDTO);
        commonDao.savePaymentDetail(paymentDetail);
        //endregion
    }

    public ResponseMessage saveClaimTask(CurrentUser currentUser, String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationDetailDTO applicationDetailDTO = commonDao.getApplicationDetail(applicationNo);
        if (applicationDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Application information not found.");
            return responseMessage;
        } else {
            if (applicationDetailDTO.getStatusId() != 1) {
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                responseMessage.setResponseText("Task already claimed/approved/rejected/unclaimed by someone. Please refresh the page to see the updated task.");
                return responseMessage;
            } else {
                applicationDetailDTO.setActionTakenBy(currentUser.getUsername());
                applicationDetailDTO.setActionTakenDate(new Date());
                applicationDetailDTO.setStatusId(2);// claimed status Id
            }
        }
        StudentDetailDTO studentDetailDTO = commonDao.getStudentDetailsByIndexNo(applicationDetailDTO.getIndexNo());
        //TODO:user should claim or un claim task without checking student information and should able to reject or verify accordingly
//        if (studentDetailDTO == null) {
//            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
//            responseMessage.setResponseText("Student information not found.");
//            return responseMessage;
//        }
        //region update t_application_details
        ApplicationDetail applicationDetail = convertDTOtoEntity(currentUser, applicationDetailDTO, studentDetailDTO);
        commonDao.saveApplication(applicationDetail);
        //endregion
        // region update t_workflow_dtls
        WorkFlowDetailDTO workFlowDetailDTO = new WorkFlowDetailDTO();
        String workInstanceId = getWorkInstanceId();
        workFlowDetailDTO.setWorkFlowInstanceId(workInstanceId);
        workFlowDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        workFlowDetailDTO.setApplicationStatusId(2); // claimed status Id
        workFlowDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        workFlowDetailDTO.setCreatedDate(applicationDetailDTO.getSubmissionDate());
        workFlowDetailDTO.setUpdateBy(currentUser.getUsername());
        workFlowDetailDTO.setUpdateDate(new Date());
        WorkFlowDetail workFlowDetail = convertWorkFlowDetailDTOtoEntity(workFlowDetailDTO);
        commonDao.saveWorkFlowDetail(workFlowDetail);
        //endregion

        //region update t_task_dtls table
        TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
        String taskInstanceId = getTaskInstanceId();
        taskDetailDTO.setTaskInstanceId(taskInstanceId);
        taskDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        taskDetailDTO.setApplicationStatus(2);
        taskDetailDTO.setOwner(currentUser.getUsername());
        taskDetailDTO.setWorkflowInstanceId(workInstanceId);
        taskDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        taskDetailDTO.setCreatedOn(applicationDetailDTO.getSubmissionDate());
        taskDetailDTO.setModifiedBy(currentUser.getUsername());
        taskDetailDTO.setModifiedOn(new Date());
        TaskDetail taskDetail = convertTaskDetailDTOtoEntity(taskDetailDTO);
        commonDao.saveTaskDetail(taskDetail);
        //endregion
        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        responseMessage.setResponseText("Claimed Successfully.");
        return responseMessage;
    }

    public ResponseMessage saveUnClaimTask(CurrentUser currentUser, String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationDetailDTO applicationDetailDTO = commonDao.getApplicationDetail(applicationNo);
        if (applicationDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Application information not found.");
            return responseMessage;
        } else {
            if (applicationDetailDTO.getStatusId() != 2) {
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                responseMessage.setResponseText("Task already claimed/approved/rejected/unclaimed by someone. Please refresh the page to see the updated task.");
                return responseMessage;
            } else {
                applicationDetailDTO.setActionTakenBy(currentUser.getUsername());
                applicationDetailDTO.setActionTakenDate(new Date());
                applicationDetailDTO.setStatusId(1);// un claimed status Id
            }
        }
        StudentDetailDTO studentDetailDTO = commonDao.getStudentDetailsByIndexNo(applicationDetailDTO.getIndexNo());
//        if (studentDetailDTO == null) {
//            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
//            responseMessage.setResponseText("Student information not found.");
//            return responseMessage;
//        }
        //region update t_application_details
        ApplicationDetail applicationDetail = convertDTOtoEntity(currentUser, applicationDetailDTO, studentDetailDTO);
        commonDao.saveApplication(applicationDetail);
        //endregion
        // region update t_workflow_dtls
        WorkFlowDetailDTO workFlowDetailDTO = new WorkFlowDetailDTO();
        String workInstanceId = getWorkInstanceId();
        workFlowDetailDTO.setWorkFlowInstanceId(workInstanceId);
        workFlowDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        workFlowDetailDTO.setApplicationStatusId(1); // un claimed status Id
        workFlowDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        workFlowDetailDTO.setCreatedDate(applicationDetailDTO.getSubmissionDate());
        workFlowDetailDTO.setUpdateBy(currentUser.getUsername());
        workFlowDetailDTO.setUpdateDate(new Date());
        WorkFlowDetail workFlowDetail = convertWorkFlowDetailDTOtoEntity(workFlowDetailDTO);
        commonDao.saveWorkFlowDetail(workFlowDetail);
        //endregion

        //region update t_task_dtls table
        TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
        String taskInstanceId = getTaskInstanceId();
        taskDetailDTO.setTaskInstanceId(taskInstanceId);
        taskDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        taskDetailDTO.setApplicationStatus(1);
        taskDetailDTO.setOwner(currentUser.getUsername());
        taskDetailDTO.setWorkflowInstanceId(workInstanceId);
        taskDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        taskDetailDTO.setCreatedOn(applicationDetailDTO.getSubmissionDate());
        taskDetailDTO.setModifiedBy(currentUser.getUsername());
        taskDetailDTO.setModifiedOn(new Date());
        TaskDetail taskDetail = convertTaskDetailDTOtoEntity(taskDetailDTO);
        commonDao.saveTaskDetail(taskDetail);
        //endregion
        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        responseMessage.setResponseText("Claimed Successfully.");
        return responseMessage;
    }

    public ResponseMessage approveApplication(CurrentUser currentUser, String applicationNo,
                                              String remarks, Integer rejectReasonId, String rejectRemarks) {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationDetailDTO applicationDetailDTO = commonDao.getApplicationDetail(applicationNo);
        if (applicationDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Application information not found.");
            return responseMessage;
        } else if (applicationDetailDTO.getStatusId() != 2) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Task already claimed/approved/rejected/unclaimed by someone. Please refresh the page to see the updated task.");
            return responseMessage;
        } else {
            applicationDetailDTO.setApplicationNo(applicationNo);
            applicationDetailDTO.setActionTakenBy(currentUser.getUsername());
            applicationDetailDTO.setActionTakenDate(new Date());
            applicationDetailDTO.setRemarks(remarks);
            if (rejectReasonId == null) {
                applicationDetailDTO.setStatusId(3);// verified status Id
            } else {
                applicationDetailDTO.setStatusId(4);// reject status Id
                applicationDetailDTO.setRejectReasonId(rejectReasonId);
                applicationDetailDTO.setRejectRemarks(rejectRemarks);
            }
        }
        StudentDetailDTO studentDetailDTO = commonDao.getStudentDetailsByIndexNo(applicationDetailDTO.getIndexNo());
//        if (studentDetailDTO == null) {
//            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
//            responseMessage.setResponseText("Student information not found.");
//            return responseMessage;
//        }
        //region update t_application_details
        ApplicationDetail applicationDetail = convertDTOtoEntity(currentUser, applicationDetailDTO, studentDetailDTO);
        commonDao.saveApplication(applicationDetail);
        //endregion
        // region update t_workflow_dtls
        WorkFlowDetailDTO workFlowDetailDTO = new WorkFlowDetailDTO();
        String workInstanceId = getWorkInstanceId();
        workFlowDetailDTO.setWorkFlowInstanceId(workInstanceId);
        workFlowDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        if (rejectReasonId == null) {
            workFlowDetailDTO.setApplicationStatusId(3); // verified status Id
        } else {
            workFlowDetailDTO.setApplicationStatusId(4); // reject status Id
        }
        workFlowDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        workFlowDetailDTO.setCreatedDate(applicationDetailDTO.getSubmissionDate());
        workFlowDetailDTO.setUpdateBy(currentUser.getUsername());
        workFlowDetailDTO.setUpdateDate(new Date());
        WorkFlowDetail workFlowDetail = convertWorkFlowDetailDTOtoEntity(workFlowDetailDTO);
        commonDao.saveWorkFlowDetail(workFlowDetail);
        //endregion

        //region update t_task_dtls table
        TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
        String taskInstanceId = getTaskInstanceId();
        taskDetailDTO.setTaskInstanceId(taskInstanceId);
        taskDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        if (rejectReasonId == null) {
            taskDetailDTO.setApplicationStatus(3);
        } else {
            taskDetailDTO.setApplicationStatus(4);
        }
        taskDetailDTO.setOwner(currentUser.getUsername());
        taskDetailDTO.setWorkflowInstanceId(workInstanceId);
        taskDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        taskDetailDTO.setCreatedOn(applicationDetailDTO.getSubmissionDate());
        taskDetailDTO.setModifiedBy(currentUser.getUsername());
        taskDetailDTO.setModifiedOn(new Date());
        TaskDetail taskDetail = convertTaskDetailDTOtoEntity(taskDetailDTO);
        commonDao.saveTaskDetail(taskDetail);
        //endregion
        if (rejectReasonId == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_APPROVAL.value());
            responseMessage.setResponseText("Verified Successfully.");
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_REJECT.value());
            if (rejectReasonId == 4) {      // if  Reject Reason is Others, Number is subjected to change
                responseMessage.setRejectReason(rejectRemarks);
            } else {
                responseMessage.setRejectReason(getRejectReasonByRejectReasonId(rejectReasonId));
            }
            responseMessage.setResponseText("Rejected Successfully.");
        }
        return responseMessage;
    }

    public Integer getAppliedDocumentId() {
        return commonDao.getAppliedDocumentId();
    }

    public Integer getChargeByDocumentId(Integer documentId, Integer serviceId) {
        return commonDao.getChargeByDocumentId(documentId, serviceId);
    }

    public void saveAppliedDocument(AppliedDocument appliedDocument) {
        commonDao.saveAppliedDocument(appliedDocument);
    }

    private ApplicationDetail convertDTOtoEntity(CurrentUser currentUser, ApplicationDetailDTO applicationDetailDTO,
                                                 StudentDetailDTO studentDetailDTO) {
        ApplicationDetail applicationDetail = new ApplicationDetail();

        applicationDetail.setApplicationNo(applicationDetailDTO.getApplicationNo());
        applicationDetail.setServiceSlNo(applicationDetailDTO.getServiceSlNo());
        applicationDetail.setSubmissionDate(applicationDetailDTO.getSubmissionDate());
        applicationDetail.setSubmittedBy(applicationDetailDTO.getSubmittedBy());
        applicationDetail.setActionTakenBy(applicationDetailDTO.getActionTakenBy());
        applicationDetail.setActionTakenDate(applicationDetailDTO.getActionTakenDate());
        applicationDetail.setCidNo(applicationDetailDTO.getCidNo());

        //TODO:fetch following detail from census data
        applicationDetail.setBirthDate("DoB");
        applicationDetail.setFirstName("F Name");
        applicationDetail.setMiddleName("M Name");
        applicationDetail.setLastName("L Name");
        applicationDetail.setGuardianName("Guardian");

        applicationDetail.setFatherName(applicationDetailDTO.getFatherName());
        applicationDetail.setAddress(applicationDetailDTO.getAddress());
        applicationDetail.setMobileNo(applicationDetailDTO.getMobileNo());
        applicationDetail.setEmail(applicationDetailDTO.getEmail());
        applicationDetail.setReasonWithOriginalDoc(applicationDetailDTO.getReasonWithOriginalDoc());
        applicationDetail.setChangesRequired(applicationDetailDTO.getChangesRequired());
        applicationDetail.setIsOldDocReturned(applicationDetailDTO.getIsOldDocReturned());

        applicationDetail.setDocumentCollection(applicationDetailDTO.getDocumentCollection());
        applicationDetail.setCollectedBy(applicationDetailDTO.getCollectedBy());

        applicationDetail.setIsEmployed(applicationDetailDTO.getIsEmployed());
        applicationDetail.setAgencyName(applicationDetailDTO.getAgencyName());
        applicationDetail.setDesignation(applicationDetailDTO.getDesignation());
        applicationDetail.setPurpose(applicationDetailDTO.getPurpose());
        applicationDetail.setIndexNo(applicationDetailDTO.getIndexNo());
        if (studentDetailDTO == null) {
            applicationDetail.setLastExamMonth(null);
            applicationDetail.setLastExamYear(null);
            applicationDetail.setLastSchoolName(null);
            applicationDetail.setExamMonth(null);
            applicationDetail.setExamYear(null);
            applicationDetail.setSchoolId(null);
        } else {
            applicationDetail.setLastExamMonth(studentDetailDTO.getExamMonth());
            applicationDetail.setLastExamYear(studentDetailDTO.getExamYear());
            applicationDetail.setLastSchoolName(studentDetailDTO.getSchoolName());
            applicationDetail.setExamMonth(studentDetailDTO.getExamMonth());
            applicationDetail.setExamYear(studentDetailDTO.getExamYear());
            applicationDetail.setSchoolId(studentDetailDTO.getSchoolId());
        }
        applicationDetail.setRemarks(applicationDetailDTO.getRemarks());
        applicationDetail.setIsPrinted(applicationDetailDTO.getIsPrinted());
        applicationDetail.setPaymentMade(applicationDetailDTO.getPaymentMade());
        applicationDetail.setStatusId(applicationDetailDTO.getStatusId());
        applicationDetail.setRejectReasonId(applicationDetailDTO.getRejectReasonId());
        applicationDetail.setRejectRemarks(applicationDetailDTO.getRejectRemarks());
        return applicationDetail;
    }

    public ResponseMessage updatePaymentDetail(CurrentUser currentUser, PaymentDetailDTO paymentDetailDTO) {

        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationDetailDTO applicationDetailDTO = commonDao.getApplicationDetail(paymentDetailDTO.getApplicationNo());
        if (applicationDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Application information not found.");
            return responseMessage;
        } else {
            if (applicationDetailDTO.getStatusId() == 5) {
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                responseMessage.setResponseText("Payment detail already updated for application no " + applicationDetailDTO.getApplicationNo());
                return responseMessage;
            } else {
                applicationDetailDTO.setActionTakenBy(currentUser.getUsername());
                applicationDetailDTO.setActionTakenDate(new Date());
                applicationDetailDTO.setPaymentMade("Y");
                applicationDetailDTO.setStatusId(5);// payment cleared status Id
            }
        }
        StudentDetailDTO studentDetailDTO = commonDao.getStudentDetailsByIndexNo(applicationDetailDTO.getIndexNo());
        if (studentDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Applicant information not found by application no " + applicationDetailDTO.getApplicationNo());
            return responseMessage;
        }

        //region update t_application_details
        ApplicationDetail applicationDetail = convertDTOtoEntity(currentUser, applicationDetailDTO, studentDetailDTO);
        commonDao.saveApplication(applicationDetail);
        //endregion
        // region update t_workflow_dtls
        WorkFlowDetailDTO workFlowDetailDTO = new WorkFlowDetailDTO();
        String workInstanceId = getWorkInstanceId();
        workFlowDetailDTO.setWorkFlowInstanceId(workInstanceId);
        workFlowDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        workFlowDetailDTO.setApplicationStatusId(5); // payment cleared status Id
        workFlowDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        workFlowDetailDTO.setCreatedDate(applicationDetailDTO.getSubmissionDate());
        workFlowDetailDTO.setUpdateBy(currentUser.getUsername());
        workFlowDetailDTO.setUpdateDate(new Date());
        WorkFlowDetail workFlowDetail = convertWorkFlowDetailDTOtoEntity(workFlowDetailDTO);
        commonDao.saveWorkFlowDetail(workFlowDetail);
        //endregion

        //region update t_task_dtls table
        TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
        String taskInstanceId = getTaskInstanceId();
        taskDetailDTO.setTaskInstanceId(taskInstanceId);
        taskDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        taskDetailDTO.setApplicationStatus(5);// payment cleared status Id
        taskDetailDTO.setOwner(currentUser.getUsername());
        taskDetailDTO.setWorkflowInstanceId(workInstanceId);
        taskDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        taskDetailDTO.setCreatedOn(applicationDetailDTO.getSubmissionDate());
        taskDetailDTO.setModifiedBy(currentUser.getUsername());
        taskDetailDTO.setModifiedOn(new Date());
        TaskDetail taskDetail = convertTaskDetailDTOtoEntity(taskDetailDTO);
        commonDao.saveTaskDetail(taskDetail);
        //endregion
        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        return responseMessage;
    }

    public ResponseMessage updatePrintStatus(CurrentUser currentUser, String applicationNo, Integer documentId) {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationDetailDTO applicationDetailDTO = commonDao.getApplicationDetail(applicationNo);
        if (applicationDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Application information not found.");
            return responseMessage;
        } else {
//            if (applicationDetailDTO.getStatusId() == 6) {
//                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
//                responseMessage.setResponseText("Certificate already printed for application no " + applicationNo);
//                return responseMessage;
//            } else {
            //TODO check if it is already printed or not. get printed value from t_applied_document table
            applicationDetailDTO.setActionTakenBy(currentUser.getUsername());
            applicationDetailDTO.setActionTakenDate(new Date());
            if (documentId == 4) {//if document type is english lan pro certificate
                applicationDetailDTO.setStatusId(6);// task completed status Id
                applicationDetailDTO.setIsPrinted('Y');
            }
//            }
        }
        StudentDetailDTO studentDetailDTO = commonDao.getStudentDetailsByIndexNo(applicationDetailDTO.getIndexNo());
        if (studentDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Applicant information not found by application no " + applicationNo);
            return responseMessage;
        }
        //region update t_application_details
        ApplicationDetail applicationDetail = convertDTOtoEntity(currentUser, applicationDetailDTO, studentDetailDTO);
        commonDao.saveApplication(applicationDetail);
       //update t_applied_document table
        commonDao.updatePrintStatus(applicationNo, documentId);
        //endregion
        // region update t_workflow_dtls
        WorkFlowDetailDTO workFlowDetailDTO = new WorkFlowDetailDTO();
        String workInstanceId = getWorkInstanceId();
        workFlowDetailDTO.setWorkFlowInstanceId(workInstanceId);
        workFlowDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        if (documentId == 4) {//if document type is english lan pro certificate
        workFlowDetailDTO.setApplicationStatusId(6); //task completed status Id
        }else {
            workFlowDetailDTO.setApplicationStatusId(5); //task completed status Id
        }
        workFlowDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        workFlowDetailDTO.setCreatedDate(applicationDetailDTO.getSubmissionDate());
        workFlowDetailDTO.setUpdateBy(currentUser.getUsername());
        workFlowDetailDTO.setUpdateDate(new Date());
        WorkFlowDetail workFlowDetail = convertWorkFlowDetailDTOtoEntity(workFlowDetailDTO);
        commonDao.saveWorkFlowDetail(workFlowDetail);
        //endregion

        //region update t_task_dtls table
        TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
        String taskInstanceId = getTaskInstanceId();
        taskDetailDTO.setTaskInstanceId(taskInstanceId);
        taskDetailDTO.setApplicationNo(applicationDetailDTO.getApplicationNo());
        if (documentId == 4) {//if document type is english lan pro certificate
            taskDetailDTO.setApplicationStatus(6);// task completed status Id
        }else {
            taskDetailDTO.setApplicationStatus(6);// task completed status Id
        }
        taskDetailDTO.setOwner(currentUser.getUsername());
        taskDetailDTO.setWorkflowInstanceId(workInstanceId);
        taskDetailDTO.setCreatedBy(applicationDetailDTO.getSubmittedBy());
        taskDetailDTO.setCreatedOn(applicationDetailDTO.getSubmissionDate());
        taskDetailDTO.setModifiedBy(currentUser.getUsername());
        taskDetailDTO.setModifiedOn(new Date());
        TaskDetail taskDetail = convertTaskDetailDTOtoEntity(taskDetailDTO);
        commonDao.saveTaskDetail(taskDetail);
        //endregion
        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        return responseMessage;
    }

    public ResponseMessage getApplicationDetail(String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationDetailDTO applicationDetailDTO = commonDao.getApplicationDetail(applicationNo);
        if (applicationDetailDTO != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(applicationDetailDTO);
        }
        return responseMessage;
    }

    public BigDecimal getChargedApplied(String applicationNo) {
        return commonDao.getChargedApplied(applicationNo);
    }

    public List<DropdownDTO> getRejectReasons() {
        return commonDao.getRejectReasons();
    }

    public ResponseMessage validateDuplicateDocument(String cidNo, String indexNo, Integer serviceTypeId, Integer documentTypeId) {
        ResponseMessage responseMessage = new ResponseMessage();
        String applicationNo = commonDao.validateDuplicateDocument(cidNo, indexNo, serviceTypeId, documentTypeId);
        if (applicationNo != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        }
        return responseMessage;
    }

    public String getDocumentName(Integer documentTypeId) {
        return commonDao.getDocumentName(documentTypeId);
    }

    private WorkFlowDetail convertWorkFlowDetailDTOtoEntity(WorkFlowDetailDTO workFlowDetailDTO) {
        WorkFlowDetail workFlowDetail = new WorkFlowDetail();
        workFlowDetail.setWorkFlowInstanceId(workFlowDetailDTO.getWorkFlowInstanceId());
        workFlowDetail.setApplicationNo(workFlowDetailDTO.getApplicationNo());
        workFlowDetail.setApplicationStatusId(workFlowDetailDTO.getApplicationStatusId());
        workFlowDetail.setCreatedBy(workFlowDetailDTO.getCreatedBy());
        workFlowDetail.setCreatedDate(workFlowDetailDTO.getCreatedDate());
        workFlowDetail.setUpdateBy(workFlowDetailDTO.getUpdateBy());
        workFlowDetail.setUpdateDate(workFlowDetailDTO.getUpdateDate());
        return workFlowDetail;
    }

    private TaskDetail convertTaskDetailDTOtoEntity(TaskDetailDTO taskDetailDTO) {
        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setTaskInstanceId(taskDetailDTO.getTaskInstanceId());
        taskDetail.setApplicationNo(taskDetailDTO.getApplicationNo());
        taskDetail.setOwner(taskDetailDTO.getOwner());
        taskDetail.setCreatedBy(taskDetailDTO.getCreatedBy());
        taskDetail.setCreatedOn(taskDetailDTO.getCreatedOn());
        taskDetail.setModifiedBy(taskDetailDTO.getModifiedBy());
        taskDetail.setModifiedOn(taskDetailDTO.getModifiedOn());
        taskDetail.setApplicationStatus(taskDetailDTO.getApplicationStatus());
        taskDetail.setWorkflowInstanceId(taskDetailDTO.getWorkflowInstanceId());
        return taskDetail;
    }

    private PaymentDetail convertPaymentDetailDtoToEntity(PaymentDetailDTO paymentDetailDTO) {
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setPaymentDetailId(paymentDetailDTO.getPaymentDetailId());
        paymentDetail.setApplicationNo(paymentDetailDTO.getApplicationNo());
        paymentDetail.setServiceNo(paymentDetailDTO.getServiceNo());
        paymentDetail.setPaymentType(paymentDetailDTO.getPaymentType());
        paymentDetail.setAmountCharge(paymentDetailDTO.getAmountCharge());
        paymentDetail.setBankId(paymentDetailDTO.getBankId());
        paymentDetail.setReceiptNo(paymentDetailDTO.getReceiptNo());
        paymentDetail.setVoucherNo(paymentDetailDTO.getVoucherNo());
        paymentDetail.setDepositDate(paymentDetailDTO.getDepositDate());
        paymentDetail.setIsPaid(paymentDetailDTO.getIsPaid());
        return paymentDetail;
    }

    private String getWorkInstanceId() {
        String workInstanceShortCode = "WI";
        String workInstanceId = null;
        Integer workInstanceSerial = commonDao.getWorkInstanceSerial();
        workInstanceSerial = workInstanceSerial == null ? 0 : workInstanceSerial;
        workInstanceSerial = workInstanceSerial + 1;
        if (workInstanceSerial.toString().length() == 1) {
            workInstanceId = workInstanceShortCode + "0000000" + workInstanceSerial;
        }
        if (workInstanceSerial.toString().length() == 2) {
            workInstanceId = workInstanceShortCode + "000000" + workInstanceSerial;
        }
        if (workInstanceSerial.toString().length() == 3) {
            workInstanceId = workInstanceShortCode + "00000" + workInstanceSerial;
        }
        if (workInstanceSerial.toString().length() == 4) {
            workInstanceId = workInstanceShortCode + "0000" + workInstanceSerial;
        }
        if (workInstanceSerial.toString().length() == 5) {
            workInstanceId = workInstanceShortCode + "000" + workInstanceSerial;
        }
        if (workInstanceSerial.toString().length() == 6) {
            workInstanceId = workInstanceShortCode + "00" + workInstanceSerial;
        }
        if (workInstanceSerial.toString().length() == 7) {
            workInstanceId = workInstanceShortCode + "0" + workInstanceSerial;
        }
        if (workInstanceSerial.toString().length() == 8) {
            workInstanceId = workInstanceSerial.toString();
        }
        return workInstanceId;
    }

    private String getTaskInstanceId() {
        String taskInstanceShortCode = "TI";
        String taskInstanceId = null;
        Integer taskInstanceSerial = commonDao.getTaskInstanceSerial();
        taskInstanceSerial = taskInstanceSerial == null ? 0 : taskInstanceSerial;
        taskInstanceSerial = taskInstanceSerial + 1;
        if (taskInstanceSerial.toString().length() == 1) {
            taskInstanceId = taskInstanceShortCode + "0000000" + taskInstanceSerial;
        }
        if (taskInstanceSerial.toString().length() == 2) {
            taskInstanceId = taskInstanceShortCode + "000000" + taskInstanceSerial;
        }
        if (taskInstanceSerial.toString().length() == 3) {
            taskInstanceId = taskInstanceShortCode + "00000" + taskInstanceSerial;
        }
        if (taskInstanceSerial.toString().length() == 4) {
            taskInstanceId = taskInstanceShortCode + "0000" + taskInstanceSerial;
        }
        if (taskInstanceSerial.toString().length() == 5) {
            taskInstanceId = taskInstanceShortCode + "000" + taskInstanceSerial;
        }
        if (taskInstanceSerial.toString().length() == 6) {
            taskInstanceId = taskInstanceShortCode + "00" + taskInstanceSerial;
        }
        if (taskInstanceSerial.toString().length() == 7) {
            taskInstanceId = taskInstanceShortCode + "0" + taskInstanceSerial;
        }
        if (taskInstanceSerial.toString().length() == 8) {
            taskInstanceId = taskInstanceSerial.toString();
        }
        return taskInstanceId;
    }

    private Integer getPaymentDetailId() {
        Integer lastPaymentDetailId = commonDao.getLastPaymentDetailId();
        return lastPaymentDetailId == null ? 0 : lastPaymentDetailId;
    }

    private Integer getAmountCharge(ApplicationDetailDTO applicationDetailDTO) {
        Integer amountPaid = 0;
        if (applicationDetailDTO.getServiceSlNo().equals(ServiceTypeEnum.ISSUE_DUPLICATE_CERTIFICATE.getServiceTypeId())) {
            for (AppliedDocumentDTO appliedDocumentDTO : applicationDetailDTO.getAppliedDocumentDTOs()) {
                if (appliedDocumentDTO.getDocumentTypeId() != null) {
                    amountPaid = amountPaid + getChargeByDocumentId(appliedDocumentDTO.getDocumentTypeId()
                            , ServiceTypeEnum.ISSUE_DUPLICATE_CERTIFICATE.getServiceTypeId());
                }
            }
        }
        if (applicationDetailDTO.getServiceSlNo().equals(ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceTypeId())) {
            for (AppliedDocumentDTO appliedDocumentDTO : applicationDetailDTO.getAppliedDocumentDTOs()) {
                if (appliedDocumentDTO.getDocumentTypeId() != null) {
                    amountPaid = amountPaid + getChargeByDocumentId(appliedDocumentDTO.getDocumentTypeId()
                            , ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceTypeId());
                }
            }
        }
        if (applicationDetailDTO.getServiceSlNo().equals(ServiceTypeEnum.ISSUE_ENGLISH_LAN_PRO_CERTIFICATE.getServiceTypeId())) {
            amountPaid = getChargeByDocumentId(null, ServiceTypeEnum.ISSUE_ENGLISH_LAN_PRO_CERTIFICATE.getServiceTypeId());
        }
        if (applicationDetailDTO.getServiceSlNo().equals(ServiceTypeEnum.CLERICAL_RECHECK.getServiceTypeId())) {
            for (SubjectDTO subjectDTO : applicationDetailDTO.getSubjectDTOs()) {
                if (subjectDTO.getPaperId() != null) {
                    amountPaid = amountPaid + getChargeByDocumentId(null, ServiceTypeEnum.CLERICAL_RECHECK.getServiceTypeId());
                }
            }
        }
        return amountPaid;
    }

    private String getRejectReasonByRejectReasonId(Integer rejectReasonId) {
        return commonDao.getRejectReasonByRejectReasonId(rejectReasonId);
    }
}
