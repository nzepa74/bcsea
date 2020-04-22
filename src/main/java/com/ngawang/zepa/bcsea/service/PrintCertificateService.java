package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.PrintCertificateDao;
import com.ngawang.zepa.bcsea.dao.ViewResultDao;
import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.bcsea.dto.ReportRequestDto;
import com.ngawang.zepa.bcsea.dto.ReportResponseDto;
import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import com.ngawang.zepa.enumeration.ReportGenerationStatus;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.CurrentUser;
import com.ngawang.zepa.helper.ReportService;
import com.ngawang.zepa.helper.ResponseMessage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 9/13/2019.
 */
@Service
public class PrintCertificateService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private PrintCertificateDao certificateDao;

    @Autowired
    private ViewResultDao viewResultDao;

    //region connection manager
    protected EntityManager em;

    @PersistenceContext(unitName = "default")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
    //endregion

    public ResponseMessage getCertificateList(Integer serviceId) {
        ResponseMessage responseMessage = new ResponseMessage();

        List<ApplicationDetailDTO> applicationDetailDTOs = certificateDao.getCertificateList(serviceId);

        if (applicationDetailDTOs != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(applicationDetailDTOs);
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Result not found.");
        }
        return responseMessage;
    }

    @Transactional
    public ResponseMessage print(HttpServletRequest request, CurrentUser currentUser, String applicationNo, String indexNo,
                                 Integer documentId) throws JRException, ClassNotFoundException, SQLException {
        ResponseMessage responseMessage = new ResponseMessage();
        Connection connection = ((SessionImpl) getCurrentSession()).connection();
        String path = request.getSession().getServletContext().getRealPath("/jasperReport/");
        String reportJRXML = "";
        String examType = "";

        StudentDetailDTO studentDetailDTO = viewResultDao.getStudentDetail(indexNo);
        Map<String, Object> params = new HashMap<String, Object>();
        if (documentId == 1) {
            reportJRXML = "admitCard.jasper";
            params.put("STUDENT_NAME", studentDetailDTO.getStudentName());
            params.put("INDEX_NO", studentDetailDTO.getIndexNo());
            params.put("SCHOOL_NAME", studentDetailDTO.getSchoolName());
            examType = studentDetailDTO.getClassType() == 10 ? "BCSC" : "BHSEC";
            params.put("EXAM_TYPE_AND_YEAR", examType + ' ' + studentDetailDTO.getExamYear().toString());
        } else if (documentId == 2) {
            reportJRXML = "passCertificate.jasper";
            params.put("STUDENT_NAME", studentDetailDTO.getStudentName());
            params.put("INDEX_NO", studentDetailDTO.getIndexNo());
            params.put("EXAM_YEAR", studentDetailDTO.getExamYear().toString());
            params.put("SCHOOL_NAME", studentDetailDTO.getSchoolName());
            params.put("SUPW_GRADE", studentDetailDTO.getSupwGrade());
            params.put("DATE_OF_BIRTH", studentDetailDTO.getDob().toString());
            params.put("RESULT_REMARKS", studentDetailDTO.getResultRemarks());
            params.put("CLASS_TYPE", studentDetailDTO.getClassType());
        } else if (documentId == 3) {
            reportJRXML = "markSheet.jasper";
            params.put("STUDENT_NAME", studentDetailDTO.getStudentName());
            params.put("INDEX_NO", studentDetailDTO.getIndexNo());
            params.put("EXAM_YEAR", studentDetailDTO.getExamYear().toString());
            params.put("SCHOOL_NAME", studentDetailDTO.getSchoolName());
            params.put("SUPW_GRADE", studentDetailDTO.getSupwGrade());
            params.put("DATE_OF_BIRTH", studentDetailDTO.getDob().toString());
            params.put("RESULT_REMARKS", studentDetailDTO.getResultRemarks());
            params.put("CLASS_TYPE", studentDetailDTO.getClassType());
        } else if (documentId == 4) {
            reportJRXML = "engLanProCertificate.jasper";
            params.put("STUDENT_NAME", studentDetailDTO.getStudentName());
            params.put("CID_NO", studentDetailDTO.getCidNo());
            params.put("APPLICATION_NO", applicationNo);
        }
        String filePath = path.replace("\\", "/").concat(reportJRXML);
        String jasperReport = JasperFillManager.fillReportToFile(filePath, params, connection);
        JasperPrintManager.printReport(jasperReport, Boolean.TRUE);
        responseMessage.setResponseText("Printed Successfully.");
//        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public ResponseMessage generateReport(HttpServletRequest request, CurrentUser currentUser,
                                          String applicationNo, String indexNo, Integer documentId)
            throws JRException, ClassNotFoundException, SQLException, ParseException {
        ResponseMessage responseMessage = new ResponseMessage();

        Connection connection = ((SessionImpl) getCurrentSession()).connection();
        String reportPath = request.getSession().getServletContext().getRealPath("/jasperReport/");
        String outputPath = request.getSession().getServletContext().getRealPath("/resources/reports");

        StudentDetailDTO studentDetailDTO = viewResultDao.getStudentDetail(indexNo);
        if (studentDetailDTO == null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Failed to print. Student information not found.");
            return responseMessage;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        reportPath = reportPath.replace("\\", "/");
        String reportJRXML = "";
        String reportName = "Report";
        String examType = "";
        if (documentId == 1) {
            reportJRXML = "admitCard.jrxml";
            params.put("STUDENT_NAME", studentDetailDTO.getStudentName());
            params.put("INDEX_NO", studentDetailDTO.getIndexNo());
            params.put("SCHOOL_NAME", studentDetailDTO.getSchoolName());
            if (studentDetailDTO.getClassType() == 10) {
                examType = studentDetailDTO.getClassType() == 10 ? "BCSC" : "BHSEC";
            }
            params.put("EXAM_TYPE_AND_YEAR", examType + ' ' + studentDetailDTO.getExamYear().toString());
        } else if (documentId == 2) {
            reportJRXML = "passCertificate.jrxml";
            params.put("STUDENT_NAME", studentDetailDTO.getStudentName());
            params.put("INDEX_NO", studentDetailDTO.getIndexNo());
            params.put("EXAM_YEAR", studentDetailDTO.getExamYear().toString());
            params.put("SCHOOL_NAME", studentDetailDTO.getSchoolName());
            params.put("SUPW_GRADE", studentDetailDTO.getSupwGrade());
            params.put("DATE_OF_BIRTH", formatDate(studentDetailDTO.getDob()).toString());
            params.put("RESULT_REMARKS", studentDetailDTO.getResultRemarks());
            params.put("CLASS_TYPE", studentDetailDTO.getClassType());
        } else if (documentId == 3) {
            reportJRXML = "markSheet.jrxml";
            params.put("STUDENT_NAME", studentDetailDTO.getStudentName());
            params.put("INDEX_NO", studentDetailDTO.getIndexNo());
            params.put("EXAM_YEAR", studentDetailDTO.getExamYear().toString());
            params.put("SCHOOL_NAME", studentDetailDTO.getSchoolName());
            params.put("SUPW_GRADE", studentDetailDTO.getSupwGrade());
            params.put("DATE_OF_BIRTH", formatDate(studentDetailDTO.getDob()).toString());
            params.put("RESULT_REMARKS", studentDetailDTO.getResultRemarks());
            params.put("CLASS_TYPE", studentDetailDTO.getClassType());
        } else if (documentId == 4) {
            reportJRXML = "engLanProCertificate.jrxml";
            params.put("STUDENT_NAME", studentDetailDTO.getStudentName());
            params.put("CID_NO", studentDetailDTO.getCidNo());
            params.put("APPLICATION_NO", applicationNo);
        }

        ReportRequestDto reportRequestDto = new ReportRequestDto(outputPath, reportName, "html", reportPath + reportJRXML,
                reportPath, params, connection, currentUser.getUsername());

        ReportResponseDto reportResponseDto = ReportService.createReport(reportRequestDto);

        if (reportResponseDto.getReportStatus().value().equals(ReportGenerationStatus.GENERATION_SUCCESS.value())) {
            responseMessage = commonService.updatePrintStatus(currentUser, applicationNo, documentId);
            if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value()) {
                responseMessage.setResponseDTO(reportResponseDto);
                responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
                responseMessage.setResponseText("Printed Successfully.");
            } else {
                return responseMessage;
            }
        } else {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            responseMessage.setResponseText("Failed to print.");
        }
        return responseMessage;
    }

    private String formatDate(Date dob) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(dob);
        return date;
    }
}
