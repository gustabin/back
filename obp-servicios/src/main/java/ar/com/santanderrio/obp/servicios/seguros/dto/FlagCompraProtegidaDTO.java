package ar.com.santanderrio.obp.servicios.seguros.dto;

public class FlagCompraProtegidaDTO {

    private Boolean respuesta;

    private String tokenJwt;

    public Boolean getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Boolean respuesta) {
        this.respuesta = respuesta;
    }

    public String getTokenJwt() {
        return tokenJwt;
    }

    public void setTokenJwt(String tokenJwt) {
        this.tokenJwt = tokenJwt;
    }
}
