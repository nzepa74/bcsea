package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ReportResponseDto;
import com.ngawang.zepa.bcsea.dto.ResultResponseMessage;
import com.ngawang.zepa.bcsea.dto.ServiceActivityDurationDTO;
import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import com.ngawang.zepa.bcsea.service.ChargeAllocationService;
import com.ngawang.zepa.bcsea.service.PrintCertificateService;
import com.ngawang.zepa.bcsea.service.ViewResultService;
import com.ngawang.zepa.helper.CertificateUtility;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.DropdownDTO;
import com.ngawang.zepa.helper.ResponseMessage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * Created by USER on 9/13/2019.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/printCertificate")
public class PrintCertificateController {

    @Autowired
    private PrintCertificateService printCertificateService;
    @Autowired
    private ChargeAllocationService chargeAllocationService;

    @Autowired
    private ViewResultService viewResultService;

    ResponseMessage responseMessage;
    ResultResponseMessage resultResponseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        List<DropdownDTO> serviceList = chargeAllocationService.getServiceName();
        model.addAttribute("serviceList", serviceList);
        return "bcsea/printCertificate";
    }

    @ResponseBody
    @RequestMapping(value = "/getCertificateList", method = RequestMethod.GET)
    public ResponseMessage getCertificateList(HttpServletRequest request, HttpServletResponse response,
                                              Integer serviceId) {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = printCertificateService.getCertificateList(serviceId);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ResponseMessage print(HttpServletRequest request, String applicationNo, String indexNo, Integer documentId)
            throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = printCertificateService.print(request, currentUser, applicationNo, indexNo, documentId);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/printCertificate", method = RequestMethod.GET)
    public ResponseMessage generateReport(HttpServletRequest request, String applicationNo, String indexNo, Integer documentId)
            throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = printCertificateService.generateReport(request, currentUser, applicationNo, indexNo, documentId);
        return responseMessage;
    }
}
