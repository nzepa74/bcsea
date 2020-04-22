package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.service.ApproveIssueEnglishLanProCertificateService;
import com.ngawang.zepa.bcsea.service.CommonService;
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
 * Created by USER on 8/15/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/approveIssueEnglishLanProCertificate")
public class ApproveIssueEnglishLanProCertificateController {
    @Autowired
    private ApproveIssueEnglishLanProCertificateService approveIssueEnglishLanProCertificateService;
    @Autowired
    private CommonService commonService;

    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        if (model.size() != 0) {
            List<DropdownDTO> rejectReasons = commonService.getRejectReasons();
            model.addAttribute("rejectReasons", rejectReasons);
            return "bcsea/approveIssueEnglishLanProCertificate";
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
    @RequestMapping(value = "/saveApproveIssueEnglishLanProCertificate", method = RequestMethod.POST)
    public ResponseMessage saveApproveIssueEnglishLanProCertificate(HttpServletRequest request, HttpServletResponse response,
                                                                    String applicationNo, String remarks,
                                                                    Integer rejectReasonId, String rejectRemarks)
            throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = approveIssueEnglishLanProCertificateService.approveApplication(currentUser,
                applicationNo, remarks, rejectReasonId, rejectRemarks);
        return responseMessage;
    }
}
