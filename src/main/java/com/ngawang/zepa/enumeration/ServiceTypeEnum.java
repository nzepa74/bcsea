package com.ngawang.zepa.enumeration;

/**
 * Created by USER on 7/29/2019.
 */
public enum ServiceTypeEnum {
    ISSUE_DUPLICATE_CERTIFICATE(311, "DP","Issue of duplicate examination documents"),
    ISSUE_REPLACEMENT_DOCUMENT(312, "RP","Issue of re-placement documents"),
    ISSUE_ENGLISH_LAN_PRO_CERTIFICATE(313,"EP", "Issue of English Language Proficiency Certificate"),
    CLERICAL_RECHECK(314,"RC", "Clerical Re-check of Papers"),
    CHARGE_CALCULATION(315,"CC", "Charge Calculator");

    private final Integer serviceTypeId;
    private final String serviceCode;
    private final String serviceName;

    ServiceTypeEnum(Integer serviceTypeId, String serviceCode, String serviceName) {
        this.serviceTypeId = serviceTypeId;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }
}
