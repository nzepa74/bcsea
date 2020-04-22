package com.ngawang.zepa.bcsea.dto;

/**
 * Created by USER on 8/5/2019.
 */
public class ServiceChargeDTO {
    //region private variables
    private Integer amountCharge;
    private Integer serviceTypeId;
    //endregion

    //region setters and getters
    public Integer getAmountCharge() {
        return amountCharge;
    }

    public void setAmountCharge(Integer amountCharge) {
        this.amountCharge = amountCharge;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
    //endregion
}
