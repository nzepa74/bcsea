package com.ngawang.zepa.bcsea.dto;

/**
 * Created by USER on 9/7/2019.
 */
public class ChargeCalculationDTO {
    //region private variables
    private Integer noOfTimesDocIssued;
    private Integer chargeForDocument;
    private Integer documentTotalCharge;

    private Integer noOfTimesLanCertificateIssued;
    private Integer chargeForLanCertificate;
    private Integer lanCertificateTotalCharge;

    private Integer noOfRecheckPaper;
    private Integer chargePerPaper;
    private Integer recheckPaperTotalCharge;
    //endregion

    //region setters and getters
    public Integer getNoOfTimesDocIssued() {
        return noOfTimesDocIssued;
    }

    public void setNoOfTimesDocIssued(Integer noOfTimesDocIssued) {
        this.noOfTimesDocIssued = noOfTimesDocIssued;
    }

    public Integer getChargeForDocument() {
        return chargeForDocument;
    }

    public void setChargeForDocument(Integer chargeForDocument) {
        this.chargeForDocument = chargeForDocument;
    }

    public Integer getDocumentTotalCharge() {
        return documentTotalCharge;
    }

    public void setDocumentTotalCharge(Integer documentTotalCharge) {
        this.documentTotalCharge = documentTotalCharge;
    }

    public Integer getNoOfTimesLanCertificateIssued() {
        return noOfTimesLanCertificateIssued;
    }

    public void setNoOfTimesLanCertificateIssued(Integer noOfTimesLanCertificateIssued) {
        this.noOfTimesLanCertificateIssued = noOfTimesLanCertificateIssued;
    }

    public Integer getChargeForLanCertificate() {
        return chargeForLanCertificate;
    }

    public void setChargeForLanCertificate(Integer chargeForLanCertificate) {
        this.chargeForLanCertificate = chargeForLanCertificate;
    }

    public Integer getLanCertificateTotalCharge() {
        return lanCertificateTotalCharge;
    }

    public void setLanCertificateTotalCharge(Integer lanCertificateTotalCharge) {
        this.lanCertificateTotalCharge = lanCertificateTotalCharge;
    }

    public Integer getNoOfRecheckPaper() {
        return noOfRecheckPaper;
    }

    public void setNoOfRecheckPaper(Integer noOfRecheckPaper) {
        this.noOfRecheckPaper = noOfRecheckPaper;
    }

    public Integer getChargePerPaper() {
        return chargePerPaper;
    }

    public void setChargePerPaper(Integer chargePerPaper) {
        this.chargePerPaper = chargePerPaper;
    }

    public Integer getRecheckPaperTotalCharge() {
        return recheckPaperTotalCharge;
    }

    public void setRecheckPaperTotalCharge(Integer recheckPaperTotalCharge) {
        this.recheckPaperTotalCharge = recheckPaperTotalCharge;
    }
    //endregion
}
