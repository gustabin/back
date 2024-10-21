package ar.com.santanderrio.obp.servicios.biocatch.dto;

import ar.com.santanderrio.obp.servicios.biocatch.model.Brand;
import ar.com.santanderrio.obp.servicios.biocatch.model.PlatformType;

public class BaseRequestDataDTO {
    private String customerSessionId;
    private String nup;
    private String ip;
    private String userAgent;
    private PlatformType platformType;
    private Brand brand;

    public BaseRequestDataDTO(String customerSessionId, String nup, String ip, String userAgent, PlatformType platformType, Brand brand) {
        this.customerSessionId = customerSessionId;
        this.nup = nup;
        this.ip = ip;
        this.userAgent = userAgent;
        this.platformType = platformType;
        this.brand = brand;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCustomerSessionId() {
        return customerSessionId;
    }

    public void setCustomerSessionId(String customerSessionId) {
        this.customerSessionId = customerSessionId;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public PlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }


    @Override
    public String toString() {
        return "BiocatchRequestData{" +
                ", customerSessionId='" + customerSessionId + '\'' +
                ", nup='" + nup + '\'' +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
