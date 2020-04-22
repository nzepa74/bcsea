package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.service.CommonService;
import com.ngawang.zepa.bcsea.service.IssueDuplicateCertificateService;
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

/**
 * Created by N-Zepa on 23-Jul-19.
 */
@Controller
@RequestMapping("/issueDuplicateCertificate")
public class IssueDuplicateCertificateController {
    //region private variables
    @Autowired
    private IssueDuplicateCertificateService issueDuplicateCertificateService;
    @Autowired
    private CommonService commonService;
    private ResponseMessage responseMessage;
    //endregion

    //region public methods

    /**
     * index method for Issuance Of Duplicate Certificate Registration Form
     *
     * @param model -- ModelMap
     * @return -- String
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
//        if (model.size() != 0) {
//            return "redirect:/operatorsTaskList";
//        } else {
        return "bcsea/issueDuplicateCertificate";
//        }
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
        Integer serviceId = ServiceTypeEnum.ISSUE_DUPLICATE_CERTIFICATE.getServiceTypeId();
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
    @RequestMapping(value = "/saveIssueDuplicateCertificate", method = RequestMethod.POST)
    public ResponseMessage saveIssueDuplicateCertificate(HttpServletRequest request, HttpServletResponse response,
                                                         ApplicationDetailDTO applicationDetailDTO) throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = issueDuplicateCertificateService.saveIssueDuplicateCertificate(currentUser, applicationDetailDTO);
        return responseMessage;
    }
    //endregion
}
