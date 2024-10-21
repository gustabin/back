package ar.com.santanderrio.obp.servicios.loggedinusercache.entities;

import java.io.Serializable;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;

public class UserCacheEntity  implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String segmento;
    private String nombre;
    private String token;
    private Integer expireToken;
    private String refreshToken;
    private String expireRefreshToken;
    private ResumenCliente resumenCliente;

    /**
     * @return the segmento
     */
    public String getSegmento() {
        return segmento;
    }

    /**
     * @param segmento the segmento to set
     */
    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the expireToken
     */
    public Integer getExpireToken() {
        return expireToken;
    }

    /**
     * @param expireToken the expireToken to set
     */
    public void setExpireToken(Integer expireToken) {
        this.expireToken = expireToken;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken the refreshToken to set
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @return the expireRefreshToken
     */
    public String getExpireRefreshToken() {
        return expireRefreshToken;
    }

    /**
     * @param expireRefreshToken the expireRefreshToken to set
     */
    public void setExpireRefreshToken(String expireRefreshToken) {
        this.expireRefreshToken = expireRefreshToken;
    }

    /**
     * @return the resumenCliente
     */
    public ResumenCliente getResumenCliente() {
        return resumenCliente;
    }

    /**
     * @param resumenCliente the resumenCliente to set
     */
    public void setResumenCliente(ResumenCliente resumenCliente) {
        this.resumenCliente = resumenCliente;
    }
}
