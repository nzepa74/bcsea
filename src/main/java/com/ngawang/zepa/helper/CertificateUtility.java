package com.ngawang.zepa.helper;

import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CertificateUtility {

    String url = null;

    public CertificateUtility(final String url) {
        this.url = url;
    }

    public final JasperPrint getJasperPrintToPDF(StudentDetailDTO studentDetailDTO, Connection connection,
                                                 String indexNo, Integer documentId) throws JRException,
            MalformedURLException, SQLException {

        String filePath = "";
        String passCertificate = "PassCertificate.jasper";
        String markSheet = "printMarksheet.jasper";
        String admitCard = "admitCard.jasper";
        String LanProCertificate = "ELP_Certificate.jasper";

        if (documentId == 1) {
            filePath = url + passCertificate;
        } else if (documentId == 2) {
            filePath = markSheet;
        } else if (documentId == 3) {
            filePath = url + admitCard;
        } else if (documentId == 4) {
            filePath = url + LanProCertificate;
        }
        Map parameters = new HashMap();

        parameters.put("INDEX_NO", indexNo);
        parameters.put("STUDENT_NAME", "Ngawang Zepa");
//      JasperReport jr = (JasperReport) JRLoader.loadObject(filePath);
        JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile("E:/admitCard.jasper");
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
        JasperPrint jasperprint = JasperFillManager.fillReport(jr, parameters, connection);

        JRExporter exporter = new JRPrintServiceExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperprint);
        //exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, service.getAttributes());
        //exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, service.getAttributes());
        //exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.TRUE);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
        connection.close();
        return jasperprint;
    }
}
