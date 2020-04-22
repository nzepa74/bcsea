package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.service.RecheckApplicationService;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by N-Zepa on 27-Jul-19.
 */
@Controller
@RequestMapping("/recheckApplication")
public class RecheckApplicationController {
    @Autowired
    private RecheckApplicationService recheckApplicationService;
    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        return "bcsea/recheckApplication";
    }

    @ResponseBody
    @RequestMapping(value = "/getServiceActivityDuration", method = RequestMethod.GET)
    public ResponseMessage getServiceActivityDuration(String indexNo) {
        responseMessage = recheckApplicationService.getServiceActivityDuration(indexNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getRecheckCharge", method = RequestMethod.GET)
    public ResponseMessage getRecheckCharge() {
        responseMessage = recheckApplicationService.getRecheckCharge();
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getStudentInfoByIndexNo", method = RequestMethod.GET)
    public ResponseMessage getStudentInfoByIndexNo(String indexNo) {
        responseMessage = recheckApplicationService.getStudentInfoByIndexNo(indexNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getSubjectsByIndexNo", method = RequestMethod.GET)
    public ResponseMessage getSubjectsByIndexNo(String indexNo) {
        responseMessage = recheckApplicationService.getSubjectsByIndexNo(indexNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/saveRecheckApplication", method = RequestMethod.POST)
    public ResponseMessage saveRecheckApplication(HttpServletRequest request, HttpServletResponse response,
                                                  ApplicationDetailDTO applicationDetailDTO) throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = recheckApplicationService.saveRecheckApplication(currentUser, applicationDetailDTO);
        return responseMessage;
    }
}
