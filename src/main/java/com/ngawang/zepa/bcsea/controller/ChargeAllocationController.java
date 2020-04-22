package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ChargeAllocationDTO;
import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.service.ChargeAllocationService;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.DropdownDTO;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by USER on 9/4/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/chargeAllocation")
public class ChargeAllocationController {
    @Autowired
    private ChargeAllocationService chargeAllocationService;
    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        List<DropdownDTO> serviceList = chargeAllocationService.getServiceName();
        List<DropdownDTO> documentList = chargeAllocationService.getDocumentList();
        model.addAttribute("serviceList", serviceList);
        model.addAttribute("documentList", documentList);
        return "bcsea/chargeAllocation";
    }

    @ResponseBody
    @RequestMapping(value = "/getChargeList", method = RequestMethod.GET)
    public ResponseMessage getChargeList() throws Exception {
        responseMessage = chargeAllocationService.getChargeList();
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/saveChargeAllocation", method = RequestMethod.POST)
    public ResponseMessage saveChargeAllocation(HttpServletRequest request, HttpServletResponse response,
                                                ChargeAllocationDTO chargeAllocationDTO)
            throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = chargeAllocationService.saveChargeAllocation(currentUser, chargeAllocationDTO);
        return responseMessage;
    }
}
