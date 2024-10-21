package ar.com.santanderrio.obp.servicios.debinapi.dto;

public class DebinStatusDTO {
    private String code;
    private String description;
    private boolean success;
    private boolean finalStatus;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFinalStatus() {
        return finalStatus;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setFinalStatus(boolean finalStatus) {
        this.finalStatus = finalStatus;
    }
}
