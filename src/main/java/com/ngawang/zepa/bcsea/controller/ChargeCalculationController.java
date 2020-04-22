package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.service.ChargeAllocationService;
import com.ngawang.zepa.bcsea.service.ChargeCalculationService;
import com.ngawang.zepa.bcsea.service.RecheckApplicationService;
import com.ngawang.zepa.helper.DropdownDTO;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by USER on 9/6/2019.
 */
@Controller
@RequestMapping("/chargeCalculation")
public class ChargeCalculationController {
    @Autowired
    private ChargeAllocationService chargeAllocationService;
    @Autowired
    private ChargeCalculationService chargeCalculationService;
    @Autowired
    private RecheckApplicationService recheckApplicationService;
    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        List<DropdownDTO> serviceList = chargeAllocationService.getServiceName();
        List<DropdownDTO> documentList = chargeAllocationService.getDocumentList();
        model.addAttribute("serviceList", serviceList);
        model.addAttribute("documentList", documentList);
        return "bcsea/chargeCalculation";
    }

    @ResponseBody
    @RequestMapping(value = "/getDuplicateOrReplacementDocCharge", method = RequestMethod.GET)
    public ResponseMessage getDuplicateOrReplacementDocCharge(Integer serviceId, String indexNo, Integer documentId) {
        responseMessage = chargeCalculationService.getDuplicateOrReplacementDocCharge(serviceId, indexNo, documentId);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getEnglishLanProCharge", method = RequestMethod.GET)
    public ResponseMessage getEnglishLanProCharge(Integer serviceId, String cidNo) {
        responseMessage = chargeCalculationService.getEnglishLanProCharge(serviceId, cidNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/isValidCid", method = RequestMethod.GET)
    public ResponseMessage isValidCid(String cidNo) {
        responseMessage = chargeCalculationService.isValidCid(cidNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getRecheckCharge", method = RequestMethod.GET)
    public ResponseMessage getRecheckCharge() {
        responseMessage = recheckApplicationService.getRecheckCharge();
        return responseMessage;
    }
}
