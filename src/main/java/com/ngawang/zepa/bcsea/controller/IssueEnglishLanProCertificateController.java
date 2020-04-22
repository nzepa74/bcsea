package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.service.CommonService;
import com.ngawang.zepa.bcsea.service.IssueEnglishLanProCertificateService;
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
 * Created by N-Zepa on 26-Jul-19.
 */
@Controller
@RequestMapping("/issueEnglishLanProCertificate")
public class IssueEnglishLanProCertificateController {
    @Autowired
    private IssueEnglishLanProCertificateService issueEnglishLanProCertificateService;

    @Autowired
    private CommonService commonService;
    private ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        return "bcsea/issueEnglishLanProCertificate";
    }

    /**
     * to get citizen name by cid no.
     *
     * @param cidNo --String
     * @return --ResponseMessage
     */
    @ResponseBody
    @RequestMapping(value = "/getCitizenName", method = RequestMethod.GET)
    public ResponseMessage getCitizenName(String cidNo) {
        responseMessage = commonService.getCitizenName(cidNo);
        return responseMessage;
    }

    /**
     * to validate index number
     *
     * @param cidNo   -- String
     * @param indexNo -- String
     * @return --ResponseMessage
     */
    @ResponseBody
    @RequestMapping(value = "/indexNoValidation", method = RequestMethod.GET)
    public ResponseMessage indexNoValidation(String cidNo, String indexNo) {
        responseMessage = commonService.indexNoValidation(cidNo, indexNo);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getEnglishMarkByIndexNo", method = RequestMethod.GET)
    public ResponseMessage getEnglishMarkByIndexNo(String indexNo) {
        responseMessage = issueEnglishLanProCertificateService.getEnglishMarkByIndexNo(indexNo);
        return responseMessage;
    }

    /**
     * save method
     *
     * @param applicationDetailDTO -- ApplicationDetailDTO
     * @return -- ResponseMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/saveIssueEnglishLanProCertificate", method = RequestMethod.POST)
    public ResponseMessage saveIssueEnglishLanProCertificate(HttpServletRequest request, HttpServletResponse response,
                                                         ApplicationDetailDTO applicationDetailDTO) throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = issueEnglishLanProCertificateService.saveIssueEnglishLanProCertificate(currentUser, applicationDetailDTO);
        return responseMessage;
    }
}
