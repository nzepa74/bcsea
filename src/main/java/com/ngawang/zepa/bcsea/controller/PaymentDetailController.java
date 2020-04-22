package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.PaymentDetailDTO;
import com.ngawang.zepa.bcsea.service.PaymentDetailService;
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
 * Created by USER on 9/8/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/paymentDetail")
public class PaymentDetailController {

    @Autowired
    private PaymentDetailService paymentDetailService;
    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        return "bcsea/paymentDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/getPaymentList", method = RequestMethod.GET)
    public ResponseMessage getOperatorsTaskList() {
        responseMessage = paymentDetailService.getPaymentList();
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePaymentDetail", method = RequestMethod.POST)
    public ResponseMessage updatePaymentDetail(HttpServletRequest request, HttpServletResponse response,
                                               PaymentDetailDTO paymentDetailDTO) throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = paymentDetailService.updatePaymentDetail(currentUser, paymentDetailDTO);
        return responseMessage;
    }
}
