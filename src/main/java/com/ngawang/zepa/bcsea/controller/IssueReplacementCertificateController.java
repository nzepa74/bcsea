package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.service.CommonService;
import com.ngawang.zepa.bcsea.service.IssueReplacementCertificateService;
import com.ngawang.zepa.enumeration.ServiceTypeEnum;
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
import java.io.IOException;

/**
 * Created by N-Zepa on 25-Jul-19.
 */
@Controller
@RequestMapping("/issueReplacementCertificate")
public class IssueReplacementCertificateController {

    //region private variables
    @Autowired
    private IssueReplacementCertificateService issueReplacementCertificateService;

    @Autowired
    private CommonService commonService;
    private ResponseMessage responseMessage;
    //endregion

    //region public methods

    /**
     * index method
     *
     * @param model -- ModelMap
     * @return --String
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        return "bcsea/issueReplacementCertificate";
    }

    /**
     * to get document List
     *
     * @return -- ResponseMessage
     */
    @ResponseBody
    @RequestMapping(value = "/getDocumentList", method = RequestMethod.GET)
    public ResponseMessage getDocumentList() {
        responseMessage = commonService.getDocumentList();
        return responseMessage;
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
    @RequestMapping(value = "/getChargeByDocumentId", method = RequestMethod.GET)
    public Integer getChargeByDocumentId(Integer documentId) {
        Integer serviceId = ServiceTypeEnum.ISSUE_REPLACEMENT_DOCUMENT.getServiceTypeId();
        return commonService.getChargeByDocumentId(documentId, serviceId);
    }

    /**
     * save method
     *
     * @param applicationDetailDTO -- ApplicationDetailDTO
     * @return -- ResponseMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/saveIssueReplacementCertificate", method = RequestMethod.POST)
    public ResponseMessage saveIssueDuplicateCertificate(HttpServletRequest request, HttpServletResponse response,
                                                         ApplicationDetailDTO applicationDetailDTO) throws IOException {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = issueReplacementCertificateService.saveIssueReplacementCertificate(request, currentUser,
                applicationDetailDTO);
        return responseMessage;
    }

    //endregion
}
