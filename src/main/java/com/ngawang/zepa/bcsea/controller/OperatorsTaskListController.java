package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.service.OperatorsTaskListService;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by USER on 8/2/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/operatorsTaskList")
public class OperatorsTaskListController {
    @Autowired
    private OperatorsTaskListService operatorsTaskListService;
    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
            return "bcsea/operatorsTaskList";
    }

    @ResponseBody
    @RequestMapping(value = "/getOperatorsTaskList", method = RequestMethod.GET)
    public ResponseMessage getOperatorsTaskList() {
        responseMessage = operatorsTaskListService.getOperatorsTaskList();
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getClaimedTaskList", method = RequestMethod.GET)
    public ResponseMessage getClaimedTaskList(HttpServletRequest request, HttpServletResponse response) {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = operatorsTaskListService.getClaimedTaskList(currentUser);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/saveClaimTask", method = RequestMethod.POST)
    public ResponseMessage saveClaimTask(HttpServletRequest request, HttpServletResponse response, String applicationNo) {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = operatorsTaskListService.saveClaimTask(currentUser, applicationNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/saveUnClaimTask", method = RequestMethod.POST)
    public ResponseMessage saveUnClaimTask(HttpServletRequest request, HttpServletResponse response, String applicationNo) {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = operatorsTaskListService.saveUnClaimTask(currentUser, applicationNo);
        return responseMessage;
    }

    @RequestMapping(value = "/navigateToApproveIssueDuplicateCertificate", method = RequestMethod.GET)
    public String navigateToApproveIssueDuplicateCertificate(String applicationNo, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("applicationNo", applicationNo);
        redirectAttributes.addFlashAttribute("urlRedirectBack", "operatorsTaskList");
        return "redirect:/approveIssueDuplicateCertificate";
    }

    @RequestMapping(value = "/navigateToApproveIssueReplacementCertificate", method = RequestMethod.GET)
    public String navigateToApproveIssueReplacementCertificate(String applicationNo, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("applicationNo", applicationNo);
        redirectAttributes.addFlashAttribute("urlRedirectBack", "operatorsTaskList");
        return "redirect:/approveIssueReplacementCertificate";
    }

    @RequestMapping(value = "/navigateToApproveEnglishLanProCertificate", method = RequestMethod.GET)
    public String navigateToApproveEnglishLanProCertificate(String applicationNo, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("applicationNo", applicationNo);
        redirectAttributes.addFlashAttribute("urlRedirectBack", "operatorsTaskList");
        return "redirect:/approveIssueEnglishLanProCertificate";
    }

    @RequestMapping(value = "/navigateToApproveRecheckApplication", method = RequestMethod.GET)
    public String navigateToApproveRecheckApplication(String applicationNo, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("applicationNo", applicationNo);
        redirectAttributes.addFlashAttribute("urlRedirectBack", "operatorsTaskList");
        return "redirect:/approveRecheckApplication";
    }
}
