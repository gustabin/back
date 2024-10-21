package ar.com.santanderrio.obp.servicios.debinapi.dto;

public class DebinSellerDTO {
    private String fancyName;
    private String cuit;
    private String owner;
    private String terminal;
    private DebinAccountDTO debinAccountDTO;

    public String getFancyName() {
        return fancyName;
    }

    public String getCuit() {
        return cuit;
    }

    public String getOwner() {
        return owner;
    }

    public String getTerminal() {
        return terminal;
    }

    public DebinAccountDTO getDebinAccountDTO() {
        return debinAccountDTO;
    }

    public void setFancyName(String fancyName) {
        this.fancyName = fancyName;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setDebinAccountDTO(DebinAccountDTO debinAccountDTO) {
        this.debinAccountDTO = debinAccountDTO;
    }
}
