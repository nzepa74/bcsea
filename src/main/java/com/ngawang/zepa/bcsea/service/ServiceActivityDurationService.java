package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.ServiceActivityDurationDao;
import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.entity.ServiceActivityDuration;
import com.ngawang.zepa.bcsea.entity.ServiceActivityDuration_A;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.BeanValidator;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.MailSender;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 8/26/2019.
 */
@Service
public class ServiceActivityDurationService {
    @Autowired
    protected BeanValidator beanValidator;
    @Autowired
    private ServiceActivityDurationDao serviceActivityDurationDao;

    public ResponseMessage getServiceActivityDurationList() {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ServiceActivityDurationDTO> serviceActivityDurationDTOList = serviceActivityDurationDao.
                getServiceActivityDurationList();
        if (serviceActivityDurationDTOList != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(serviceActivityDurationDTOList);
        }
        return responseMessage;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage saveServiceActivityDuration(CurrentUser currentUser, ServiceActivityDurationDTO
            serviceActivityDurationDTO) throws Exception {

        ResponseMessage responseMessage = new ResponseMessage();
        beanValidator.Validate(serviceActivityDurationDTO, responseMessage);
        if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value()) {
            return responseMessage;
        }

        Date fromDate = serviceActivityDurationDTO.getActiveFrom();
        Date toDate = serviceActivityDurationDTO.getActiveTo();
        responseMessage = validateForSave(fromDate, toDate);
        if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value()) {
            return responseMessage;
        }
        String serviceName = ServiceTypeEnum.CLERICAL_RECHECK.getServiceName();
        Integer classType = serviceActivityDurationDTO.getClassType();
        Integer examYear = serviceActivityDurationDTO.getExamYear();

        if (serviceActivityDurationDTO.getAutoIndex() == null) {
            Integer isExistSave = serviceActivityDurationDao.isExistSave(serviceActivityDurationDTO);
            if (isExistSave != null) {
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                responseMessage.setResponseText(serviceName + " for exam year " + examYear + " class " + classType + " is already saved.");
                return responseMessage;
            }
            Integer autoIndex = serviceActivityDurationDao.getAutoIndex();
            if (autoIndex == null) {
                autoIndex = 1;
            }
            serviceActivityDurationDTO.setAutoIndex(autoIndex + 1);
            ServiceActivityDuration serviceActivityDuration = convertDtoToEntity(currentUser, serviceActivityDurationDTO);
            ServiceActivityDuration_A serviceActivityDuration_a = convertDtoToEntity_A(currentUser, serviceActivityDuration, 'C');
            serviceActivityDurationDao.saveServiceActivityDuration(serviceActivityDuration, serviceActivityDuration_a);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseText("Saved successfully.");
        } else {
            Integer isExistUpdate = serviceActivityDurationDao.isExistUpdate(serviceActivityDurationDTO);
            if (isExistUpdate != null) {
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                responseMessage.setResponseText(serviceName + " for exam year " + examYear + " class " + classType + " is already saved.");
                return responseMessage;
            }
            ServiceActivityDuration serviceActivityDuration = convertDtoToEntity(currentUser, serviceActivityDurationDTO);
            ServiceActivityDuration_A serviceActivityDuration_a = convertDtoToEntity_A(currentUser, serviceActivityDuration, 'M');
            serviceActivityDurationDao.saveServiceActivityDuration(serviceActivityDuration, serviceActivityDuration_a);
//            sendMail();
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseText("Updated successfully.");
        }
        return responseMessage;
    }

    private void sendMail() throws Exception {
        String toAddress = "ngawangzepa91@gmail.com";
        String subject = "Testing Mail Sending";
        String mailContent = "Mail content here....";
        String fromAddress = "ngawang.zepa@gmail.com";
        MailSender.sendMailWithoutFile(mailContent, subject, toAddress, fromAddress);
    }

    private ResponseMessage validateForSave(Date fromDate, Date toDate) {
        ResponseMessage responseMessage = new ResponseMessage();
        if (toDate.before(fromDate)) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("From date cannot be before to date.");
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        }
        return responseMessage;
    }

    private ServiceActivityDuration convertDtoToEntity(CurrentUser currentUser, ServiceActivityDurationDTO
            serviceActivityDurationDTO) {
        ServiceActivityDuration serviceActivityDuration = new ServiceActivityDuration();
        serviceActivityDuration.setAutoIndex(serviceActivityDurationDTO.getAutoIndex());
        serviceActivityDuration.setServiceId(ServiceTypeEnum.CLERICAL_RECHECK.getServiceTypeId());
        serviceActivityDuration.setClassType(serviceActivityDurationDTO.getClassType());
        serviceActivityDuration.setExamYear(serviceActivityDurationDTO.getExamYear());
        serviceActivityDuration.setActiveFrom(serviceActivityDurationDTO.getActiveFrom());
        serviceActivityDuration.setActiveTo(serviceActivityDurationDTO.getActiveTo());
        serviceActivityDuration.setStatusTag(serviceActivityDurationDTO.getStatusTag());
        return serviceActivityDuration;
    }

    private ServiceActivityDuration_A convertDtoToEntity_A(CurrentUser currentUser, ServiceActivityDuration serviceActivityDuration
            , Character cmdFlag) {
        ServiceActivityDuration_A serviceActivityDuration_a = new ServiceActivityDuration_A();
        serviceActivityDuration_a.setAutoIndex(serviceActivityDuration.getAutoIndex());
        serviceActivityDuration_a.setServiceId(serviceActivityDuration.getServiceId());
        serviceActivityDuration_a.setClassType(serviceActivityDuration.getClassType());
        serviceActivityDuration_a.setExamYear(serviceActivityDuration.getExamYear());
        serviceActivityDuration_a.setActiveFrom(serviceActivityDuration.getActiveFrom());
        serviceActivityDuration_a.setActiveTo(serviceActivityDuration.getActiveTo());
        serviceActivityDuration_a.setStatusTag(serviceActivityDuration.getStatusTag());
        serviceActivityDuration_a.setUpdatedBy(currentUser.getUsername());
        serviceActivityDuration_a.setUpdatedDate(new Date());
        serviceActivityDuration_a.setCmdFlag(cmdFlag);
        return serviceActivityDuration_a;
    }

}
