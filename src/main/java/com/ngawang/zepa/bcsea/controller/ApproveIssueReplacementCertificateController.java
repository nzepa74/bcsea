package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.service.ApproveIssueReplacementCertificateService;
import com.ngawang.zepa.bcsea.service.CommonService;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.DropdownDTO;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 8/12/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/approveIssueReplacementCertificate")
public class ApproveIssueReplacementCertificateController {

    @Autowired
    private ApproveIssueReplacementCertificateService approveIssueReplacementCertificateService;

    @Autowired
    private CommonService commonService;

    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        if (model.size() != 0) {
            List<DropdownDTO> rejectReasons = commonService.getRejectReasons();
            model.addAttribute("rejectReasons", rejectReasons);
            return "bcsea/approveIssueReplacementCertificate";
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
    @RequestMapping(value = "/getAppliedDocumentList", method = RequestMethod.GET)
    public ResponseMessage getAppliedDocumentList(String applicationNo) {
        responseMessage = commonService.getAppliedDocumentList(applicationNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getChargedApplied", method = RequestMethod.GET)
    public BigDecimal getChargedApplied(String applicationNo) {
        BigDecimal chargedApplied = commonService.getChargedApplied(applicationNo);
        return chargedApplied;
    }

    @ResponseBody
    @RequestMapping(value = "/getAttachedFiles", method = RequestMethod.GET)
    public ResponseMessage getAttachedFiles(String applicationNo) {
        responseMessage = approveIssueReplacementCertificateService.getAttachedFiles(applicationNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/downloadFile/{documentId}", method = RequestMethod.GET)
    public ResponseMessage downloadFile(@PathVariable Integer documentId, HttpServletResponse response) throws IOException {
        responseMessage = approveIssueReplacementCertificateService.downloadFile(documentId, response);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/saveApproveIssueReplacementCertificate", method = RequestMethod.POST)
    public ResponseMessage saveApproveIssueReplacementCertificate(HttpServletRequest request, HttpServletResponse response,
                                                                  String applicationNo, String remarks,
                                                                  Integer rejectReasonId, String rejectRemarks,
                                                                  String nameChange, Date dobChange) throws Exception {

        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = approveIssueReplacementCertificateService.saveApproveIssueReplacementCertificate(currentUser,
                applicationNo, remarks, rejectReasonId, rejectRemarks, nameChange, dobChange);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/saveApproveIssueReplacementCertificateWithoutDate", method = RequestMethod.POST)
    public ResponseMessage saveWithoutDate(HttpServletRequest request, HttpServletResponse response, String applicationNo,
                                           String remarks, Integer rejectReasonId,
                                           String rejectRemarks, String nameChange) throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = approveIssueReplacementCertificateService.saveApproveIssueReplacementCertificate(currentUser,
                applicationNo, remarks, rejectReasonId, rejectRemarks, nameChange, null);
        return responseMessage;
    }
}
