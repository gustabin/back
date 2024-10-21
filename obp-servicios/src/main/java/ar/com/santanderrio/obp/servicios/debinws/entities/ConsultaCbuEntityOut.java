/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.entities;

import org.beanio.annotation.Field;

/**
 * The Class ConsultaCbuEntityOut.
 */
public class ConsultaCbuEntityOut {
    
    /** The header trama. */
    @Field
    private String headerTrama;

    /** The codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;
        
    /** titular. */
    @Field
    private String titular;
    
    /** cuit 1. */
    @Field
    private String cuit1;
    
    /** cuit 2. */
    @Field
    private String cuit2;
    
    /** cuit 3. */
    @Field
    private String cuit3;
    
    /** descripcion banco. */
    @Field
    private String bandes;
    
    /** longitud de cuenta destino. */
    @Field
    private String longCtaDestino;
    
    /** cuenta destino. */
    @Field
    private String ctaDestino;
    
    /** tipo cuenta to banelco. */
    @Field
    private String tipoCuentaToBanelco;
    
    /** tipo cuenta from banelco. */
    @Field
    private String tipoCuentaFromBanelco;
    
    /** banco receptor. */
    @Field
    private String bancoReceptor;
    
    /** fiid. */
    @Field
    private String fiid;
    
    /** user. */
    @Field
    private String user;

    /**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
    public String getHeaderTrama() {
        return headerTrama;
    }

    /**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
    public void setHeaderTrama(String headerTrama) {
        this.headerTrama = headerTrama;
    }

    /**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
    public String getCodigoRetornoExtendido() {
        return codigoRetornoExtendido;
    }

    /**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
    public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
        this.codigoRetornoExtendido = codigoRetornoExtendido;
    }

    /**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
    public String getTitular() {
        return titular;
    }

    /**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the titular to set
	 */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
	 * Gets the cuit 1.
	 *
	 * @return the cuit1
	 */
    public String getCuit1() {
        return cuit1;
    }

    /**
	 * Sets the cuit 1.
	 *
	 * @param cuit1
	 *            the cuit1 to set
	 */
    public void setCuit1(String cuit1) {
        this.cuit1 = cuit1;
    }

    /**
	 * Gets the cuit 2.
	 *
	 * @return the cuit2
	 */
    public String getCuit2() {
        return cuit2;
    }

    /**
	 * Sets the cuit 2.
	 *
	 * @param cuit2
	 *            the cuit2 to set
	 */
    public void setCuit2(String cuit2) {
        this.cuit2 = cuit2;
    }

    /**
	 * Gets the cuit 3.
	 *
	 * @return the cuit3
	 */
    public String getCuit3() {
        return cuit3;
    }

    /**
	 * Sets the cuit 3.
	 *
	 * @param cuit3
	 *            the cuit3 to set
	 */
    public void setCuit3(String cuit3) {
        this.cuit3 = cuit3;
    }

    /**
	 * Gets the bandes.
	 *
	 * @return the bandes
	 */
    public String getBandes() {
        return bandes;
    }

    /**
	 * Sets the bandes.
	 *
	 * @param bandes
	 *            the bandes to set
	 */
    public void setBandes(String bandes) {
        this.bandes = bandes;
    }

    /**
	 * Gets the long cta destino.
	 *
	 * @return the longCtaDestino
	 */
    public String getLongCtaDestino() {
        return longCtaDestino;
    }

    /**
	 * Sets the long cta destino.
	 *
	 * @param longCtaDestino
	 *            the longCtaDestino to set
	 */
    public void setLongCtaDestino(String longCtaDestino) {
        this.longCtaDestino = longCtaDestino;
    }

    /**
	 * Gets the cta destino.
	 *
	 * @return the ctaDestino
	 */
    public String getCtaDestino() {
        return ctaDestino;
    }

    /**
	 * Sets the cta destino.
	 *
	 * @param ctaDestino
	 *            the ctaDestino to set
	 */
    public void setCtaDestino(String ctaDestino) {
        this.ctaDestino = ctaDestino;
    }

    /**
	 * Gets the tipo cuenta to banelco.
	 *
	 * @return the tipoCuentaToBanelco
	 */
    public String getTipoCuentaToBanelco() {
        return tipoCuentaToBanelco;
    }

    /**
	 * Sets the tipo cuenta to banelco.
	 *
	 * @param tipoCuentaToBanelco
	 *            the tipoCuentaToBanelco to set
	 */
    public void setTipoCuentaToBanelco(String tipoCuentaToBanelco) {
        this.tipoCuentaToBanelco = tipoCuentaToBanelco;
    }

    /**
	 * Gets the tipo cuenta from banelco.
	 *
	 * @return the tipoCuentaFromBanelco
	 */
    public String getTipoCuentaFromBanelco() {
        return tipoCuentaFromBanelco;
    }

    /**
	 * Sets the tipo cuenta from banelco.
	 *
	 * @param tipoCuentaFromBanelco
	 *            the tipoCuentaFromBanelco to set
	 */
    public void setTipoCuentaFromBanelco(String tipoCuentaFromBanelco) {
        this.tipoCuentaFromBanelco = tipoCuentaFromBanelco;
    }

    /**
	 * Gets the banco receptor.
	 *
	 * @return the bancoReceptor
	 */
    public String getBancoReceptor() {
        return bancoReceptor;
    }

    /**
	 * Sets the banco receptor.
	 *
	 * @param bancoReceptor
	 *            the bancoReceptor to set
	 */
    public void setBancoReceptor(String bancoReceptor) {
        this.bancoReceptor = bancoReceptor;
    }

    /**
	 * Gets the fiid.
	 *
	 * @return the fiid
	 */
    public String getFiid() {
        return fiid;
    }

    /**
	 * Sets the fiid.
	 *
	 * @param fiid
	 *            the fiid to set
	 */
    public void setFiid(String fiid) {
        this.fiid = fiid;
    }

    /**
	 * Gets the user.
	 *
	 * @return the user
	 */
    public String getUser() {
        return user;
    }

    /**
	 * Sets the user.
	 *
	 * @param user
	 *            the user to set
	 */
    public void setUser(String user) {
        this.user = user;
    }
    

    
    
    
    

}
