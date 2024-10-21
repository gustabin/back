package ar.com.santanderrio.obp.servicios.afip.dto;

public class TieneDeudaRequestDto {
    private String canal;
    private String user;
    private String password;
    private String cuit;

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String toXmlString() {
        return
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.cache.dxc.com/\">" +
              "<soapenv:Header/>" +
               "<soapenv:Body>" +
                 "<ws:tieneDeuda>"+
                     "<tieneDeudaRequest>" +
                       "<canal>" + canal + "</canal>" +
                       "<usuario>" + user + "</usuario>" +
                       "<password>" + password + "</password>" +
                       "<cuit>" + cuit + "</cuit>" +
                     "</tieneDeudaRequest>" +
                 "</ws:tieneDeuda>" +
               "</soapenv:Body>" +
            "</soapenv:Envelope>";
    }
}
