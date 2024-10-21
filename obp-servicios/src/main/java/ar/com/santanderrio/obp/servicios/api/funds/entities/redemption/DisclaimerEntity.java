package ar.com.santanderrio.obp.servicios.api.funds.entities.redemption;

public class DisclaimerEntity {
    private Integer type;
    private String text;
    private Boolean checked;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
