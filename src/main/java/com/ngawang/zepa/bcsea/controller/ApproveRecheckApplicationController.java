package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ApproveRecheckApplicationDTO;
import com.ngawang.zepa.bcsea.service.ApproveRecheckApplicationService;
import com.ngawang.zepa.bcsea.service.CommonService;
import com.ngawang.zepa.bcsea.service.RecheckApplicationService;
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
 * Created by USER on 8/19/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/approveRecheckApplication")
public class ApproveRecheckApplicationController {
    @Autowired
    private ApproveRecheckApplicationService approveRecheckApplicationService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private RecheckApplicationService recheckApplicationService;

    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        if (model.size() != 0) {
            List<DropdownDTO> rejectReasons = commonService.getRejectReasons();
            model.addAttribute("rejectReasons", rejectReasons);
            return "bcsea/approveRecheckApplication";
        } else {
            return "redirect:/operatorsTaskList";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getApplicationDetail", method = RequestMethod.GET)
    public ResponseMessage getApplicationDetail(String applicationNo) {
        responseMessage = commonService.getApplicationDetail(applicationNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getRecheckPapers", method = RequestMethod.GET)
    public ResponseMessage getRecheckPapers(String applicationNo) {
        responseMessage = approveRecheckApplicationService.getRecheckPapers(applicationNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getRecheckCharge", method = RequestMethod.GET)
    public ResponseMessage getRecheckCharge() {
        responseMessage = recheckApplicationService.getRecheckCharge();
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/saveApproveRecheckApplication", method = RequestMethod.POST)
    public ResponseMessage saveApproveRecheckApplication(HttpServletRequest request, HttpServletResponse response,
                                                         ApproveRecheckApplicationDTO approveRecheckApplicationDTO)
            throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = approveRecheckApplicationService.saveApproveRecheckApplication(currentUser,
                approveRecheckApplicationDTO);
        return responseMessage;
    }
}
