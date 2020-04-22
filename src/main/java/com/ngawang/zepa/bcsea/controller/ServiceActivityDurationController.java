package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.service.ServiceActivityDurationService;
import com.ngawang.zepa.helper.CurrentUser;
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

/**
 * Created by USER on 8/26/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/serviceActivityDuration")
public class ServiceActivityDurationController {

    @Autowired
    private ServiceActivityDurationService serviceActivityDurationService;
    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        return "bcsea/serviceActivityDuration";
    }

    @ResponseBody
    @RequestMapping(value = "/getServiceActivityDurationList", method = RequestMethod.GET)
    public ResponseMessage getServiceActivityDurationList() throws Exception {
        responseMessage = serviceActivityDurationService.getServiceActivityDurationList();
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/saveServiceActivityDuration", method = RequestMethod.POST)
    public ResponseMessage saveServiceActivityDuration(HttpServletRequest request, HttpServletResponse response,
                                                       ServiceActivityDurationDTO serviceActivityDurationDTO) throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = serviceActivityDurationService.saveServiceActivityDuration(currentUser, serviceActivityDurationDTO);
        return responseMessage;
    }
}
