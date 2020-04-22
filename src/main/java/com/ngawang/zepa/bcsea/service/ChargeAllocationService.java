package com.ngawang.zepa.bcsea.service;

;
import com.ngawang.zepa.bcsea.dao.ChargeAllocationDao;
import com.ngawang.zepa.bcsea.dto.ChargeAllocationDTO;
import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.entity.ChargeAllocation;
import com.ngawang.zepa.bcsea.entity.ChargeAllocation_A;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.BeanValidator;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.DropdownDTO;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 9/5/2019.
 */
@Service
public class ChargeAllocationService {
    @Autowired
    protected BeanValidator beanValidator;
    @Autowired
    private ChargeAllocationDao chargeAllocationDao;

    public List<DropdownDTO> getServiceName() {
        return chargeAllocationDao.getServiceName();
    }

    public List<DropdownDTO> getDocumentList() {
        return chargeAllocationDao.getDocumentList();
    }

    public ResponseMessage getChargeList() {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ChargeAllocationDTO> chargeAllocationDTOs = chargeAllocationDao.getChargeList();
        if (chargeAllocationDTOs != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(chargeAllocationDTOs);
        }
        return responseMessage;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage saveChargeAllocation(CurrentUser currentUser, ChargeAllocationDTO chargeAllocationDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        beanValidator.Validate(chargeAllocationDTO, responseMessage);
        if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value()) {
            return responseMessage;
        }
        Integer serviceChargeId = chargeAllocationDTO.getServiceChargeId();
        if (serviceChargeId == null) {
            Integer isExistSave = chargeAllocationDao.isExistSave(chargeAllocationDTO);
            if (isExistSave != null) {
                String documentName = chargeAllocationDao.getDocumentName(chargeAllocationDTO.getDocumentId());
                String serviceName = chargeAllocationDao.getServiceName(chargeAllocationDTO.getServiceId());
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
                responseMessage.setResponseText("Charge for " + documentName + " under " + serviceName + " is already saved.");
                return responseMessage;
            }
            Integer LastServiceChargeId = chargeAllocationDao.getLastServiceChargeId();
            if (LastServiceChargeId == null) {
                LastServiceChargeId = 1;
            }
            chargeAllocationDTO.setServiceChargeId(LastServiceChargeId + 1);
            ChargeAllocation chargeAllocation = convertDtoToEntity(currentUser, chargeAllocationDTO);
            ChargeAllocation_A chargeAllocation_a = convertDtoToEntity_A(currentUser, chargeAllocation, 'C');
            chargeAllocationDao.saveChargeAllocation(chargeAllocation, chargeAllocation_a);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseText("Saved successfully.");
        } else {
            ChargeAllocationDTO chargeAllocationDetailDTO = chargeAllocationDao.getChargeDetail(serviceChargeId);
            chargeAllocationDTO.setServiceId(chargeAllocationDetailDTO.getServiceId());
            chargeAllocationDTO.setDocumentId(chargeAllocationDetailDTO.getDocumentId());

            ChargeAllocation chargeAllocation = convertDtoToEntity(currentUser, chargeAllocationDTO);
            ChargeAllocation_A chargeAllocation_a = convertDtoToEntity_A(currentUser, chargeAllocation, 'M');
            chargeAllocationDao.saveChargeAllocation(chargeAllocation, chargeAllocation_a);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseText("Updated successfully.");
        }
        return responseMessage;
    }

    private ChargeAllocation convertDtoToEntity(CurrentUser currentUser, ChargeAllocationDTO chargeAllocationDTO) {
        ChargeAllocation chargeAllocation = new ChargeAllocation();
        chargeAllocation.setServiceChargeId(chargeAllocationDTO.getServiceChargeId());
        chargeAllocation.setServiceId(chargeAllocationDTO.getServiceId());
        if (chargeAllocationDTO.getServiceId().equals(ServiceTypeEnum.ISSUE_DUPLICATE_CERTIFICATE.getServiceTypeId())
                || chargeAllocationDTO.getServiceId().equals(ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceTypeId())) {
            chargeAllocation.setDocumentId(chargeAllocationDTO.getDocumentId());
        } else {
            chargeAllocation.setDocumentId(null);
        }
        chargeAllocation.setChargeAmount(chargeAllocationDTO.getChargeAmount());
        chargeAllocation.setStatusTag(chargeAllocationDTO.getStatusTag());
        chargeAllocation.setUpdatedBy(currentUser.getUsername());
        chargeAllocation.setUpdatedDate(new Date());
        return chargeAllocation;
    }

    private ChargeAllocation_A convertDtoToEntity_A(CurrentUser currentUser, ChargeAllocation chargeAllocation,
                                                    Character cmdFlag) {
        ChargeAllocation_A chargeAllocation_a = new ChargeAllocation_A();
        chargeAllocation_a.setServiceChargeId(chargeAllocation.getServiceChargeId());
        chargeAllocation_a.setServiceId(chargeAllocation.getServiceId());
        chargeAllocation_a.setDocumentId(chargeAllocation.getDocumentId());
        chargeAllocation_a.setChargeAmount(chargeAllocation.getChargeAmount());
        chargeAllocation_a.setStatusTag(chargeAllocation.getStatusTag());
        chargeAllocation_a.setUpdatedBy(currentUser.getUsername());
        chargeAllocation_a.setUpdatedDate(new Date());
        chargeAllocation_a.setCmdFlag(cmdFlag);
        return chargeAllocation_a;
    }

}
