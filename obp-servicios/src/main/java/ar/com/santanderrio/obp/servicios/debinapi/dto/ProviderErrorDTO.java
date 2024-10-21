package ar.com.santanderrio.obp.servicios.debinapi.dto;

public class ProviderErrorDTO {

    private String provider;

    private Integer rootCode;

    private DetailErrorDTO rootDetail;

    private String rootMessage;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getRootCode() {
        return rootCode;
    }

    public void setRootCode(Integer rootCode) {
        this.rootCode = rootCode;
    }

    public DetailErrorDTO getRootDetail() {
        return rootDetail;
    }

    public void setRootDetail(DetailErrorDTO rootDetail) {
        this.rootDetail = rootDetail;
    }

    public String getRootMessage() {
        return rootMessage;
    }

    public void setRootMessage(String rootMessage) {
        this.rootMessage = rootMessage;
    }



}

