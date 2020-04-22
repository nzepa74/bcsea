package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.service.ChargeAllocationService;
import com.ngawang.zepa.bcsea.service.ReportsService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 9/12/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportsService;
    @Autowired
    private ChargeAllocationService chargeAllocationService;

    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        List<DropdownDTO> serviceList = chargeAllocationService.getServiceName();
        List<DropdownDTO> documentList = chargeAllocationService.getDocumentList();
        List<DropdownDTO> statusList = reportsService.getStatusList();
        model.addAttribute("serviceList", serviceList);
        model.addAttribute("documentList", documentList);
        model.addAttribute("statusList", statusList);
        return "bcsea/reports";
    }

    @ResponseBody
    @RequestMapping(value = "/getReport", method = RequestMethod.GET)
    public ResponseMessage getReport(HttpServletRequest request, HttpServletResponse response, Integer serviceId,
                                     Integer documentId, Integer classType, Date fromDate,
                                     Date toDate, Integer statusId) {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = reportsService.getReport(serviceId, documentId, classType, fromDate, toDate, statusId);
        return responseMessage;
    }
}
