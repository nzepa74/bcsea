package com.ngawang.zepa.enumeration;

public enum ReportGenerationStatus {

    GENERATION_FAILED("F"),
    GENERATION_SUCCESS("S");

    private final String value;

    private ReportGenerationStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}