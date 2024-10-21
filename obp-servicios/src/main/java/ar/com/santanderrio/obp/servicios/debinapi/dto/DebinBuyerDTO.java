package ar.com.santanderrio.obp.servicios.debinapi.dto;

public class DebinBuyerDTO {
    private String cuit;
    private String fancyName;
    private String terminal;
    private DebinAccountDTO debinAccountDTO;

    public String getCuit() {
        return cuit;
    }

    public String getFancyName() {
        return fancyName;
    }

    public String getTerminal() {
        return terminal;
    }

    public DebinAccountDTO getDebinAccountDTO() {
        return debinAccountDTO;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setFancyName(String fancyName) {
        this.fancyName = fancyName;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setDebinAccountDTO(DebinAccountDTO debinAccountDTO) {
        this.debinAccountDTO = debinAccountDTO;
    }
}
