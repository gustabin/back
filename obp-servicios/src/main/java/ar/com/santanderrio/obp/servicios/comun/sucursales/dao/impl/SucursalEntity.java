/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl;

import org.beanio.annotation.Field;

/**
 * @author sergio.e.goldentair
 *
 */
public class SucursalEntity {
    @Field
    private String codigoEntidad;
    @Field
    private String codigoOficina;
    @Field
    private String descCompleta;
    @Field
    private String descCorta;
    @Field
    private String direccion;
    @Field
    private String codPostal;
    @Field
    private String codPais;
    @Field
    private String codLocalidad;
    @Field
    private String codOficinaCabecera;
    @Field
    private String codOficinaVentanilla;
    @Field
    private String codOficinaDestinoCorreo;
    @Field
    private String tipoOficina;
    @Field
    private String tipoPerfil;
    @Field
    private String indRealizaCamara;
    @Field
    private String indOficinaBaja;
    @Field
    private String indCodigoPostal;
    @Field
    private String prefijoTelefonico;
    @Field
    private String telax;
    @Field
    private String fax;
    @Field
    private String telefono1;
    @Field
    private String telefono2;
    @Field
    private String codCamara;
    /**
     * @return the codigoOficina
     */
    public String getCodigoOficina() {
        return codigoOficina;
    }
    /**
     * @param codigoOficina the codigoOficina to set
     */
    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }
    /**
     * @return the descCompleta
     */
    public String getDescCompleta() {
        return descCompleta;
    }
    /**
     * @param descCompleta the descCompleta to set
     */
    public void setDescCompleta(String descCompleta) {
        this.descCompleta = descCompleta;
    }
    /**
     * @return the descCorta
     */
    public String getDescCorta() {
        return descCorta;
    }
    /**
     * @param descCorta the descCorta to set
     */
    public void setDescCorta(String descCorta) {
        this.descCorta = descCorta;
    }
    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * @return the codPostal
     */
    public String getCodPostal() {
        return codPostal;
    }
    /**
     * @param codPostal the codPostal to set
     */
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }
    /**
     * @return the codPais
     */
    public String getCodPais() {
        return codPais;
    }
    /**
     * @param codPais the codPais to set
     */
    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }
    /**
     * @return the codLocalidad
     */
    public String getCodLocalidad() {
        return codLocalidad;
    }
    /**
     * @param codLocalidad the codLocalidad to set
     */
    public void setCodLocalidad(String codLocalidad) {
        this.codLocalidad = codLocalidad;
    }
    /**
     * @return the codOficinaCabecera
     */
    public String getCodOficinaCabecera() {
        return codOficinaCabecera;
    }
    /**
     * @param codOficinaCabecera the codOficinaCabecera to set
     */
    public void setCodOficinaCabecera(String codOficinaCabecera) {
        this.codOficinaCabecera = codOficinaCabecera;
    }
    /**
     * @return the codOficinaVentanilla
     */
    public String getCodOficinaVentanilla() {
        return codOficinaVentanilla;
    }
    /**
     * @param codOficinaVentanilla the codOficinaVentanilla to set
     */
    public void setCodOficinaVentanilla(String codOficinaVentanilla) {
        this.codOficinaVentanilla = codOficinaVentanilla;
    }
    /**
     * @return the codOficinaDestinoCorreo
     */
    public String getCodOficinaDestinoCorreo() {
        return codOficinaDestinoCorreo;
    }
    /**
     * @param codOficinaDestinoCorreo the codOficinaDestinoCorreo to set
     */
    public void setCodOficinaDestinoCorreo(String codOficinaDestinoCorreo) {
        this.codOficinaDestinoCorreo = codOficinaDestinoCorreo;
    }
    /**
     * @return the tipoOficina
     */
    public String getTipoOficina() {
        return tipoOficina;
    }
    /**
     * @param tipoOficina the tipoOficina to set
     */
    public void setTipoOficina(String tipoOficina) {
        this.tipoOficina = tipoOficina;
    }
    /**
     * @return the tipoPerfil
     */
    public String getTipoPerfil() {
        return tipoPerfil;
    }
    /**
     * @param tipoPerfil the tipoPerfil to set
     */
    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }
    /**
     * @return the indRealizaCamara
     */
    public String getIndRealizaCamara() {
        return indRealizaCamara;
    }
    /**
     * @param indRealizaCamara the indRealizaCamara to set
     */
    public void setIndRealizaCamara(String indRealizaCamara) {
        this.indRealizaCamara = indRealizaCamara;
    }
    /**
     * @return the indOficinaBaja
     */
    public String getIndOficinaBaja() {
        return indOficinaBaja;
    }
    /**
     * @param indOficinaBaja the indOficinaBaja to set
     */
    public void setIndOficinaBaja(String indOficinaBaja) {
        this.indOficinaBaja = indOficinaBaja;
    }
    /**
     * @return the indCodigoPostal
     */
    public String getIndCodigoPostal() {
        return indCodigoPostal;
    }
    /**
     * @param indCodigoPostal the indCodigoPostal to set
     */
    public void setIndCodigoPostal(String indCodigoPostal) {
        this.indCodigoPostal = indCodigoPostal;
    }
    /**
     * @return the prefijoTelefonico
     */
    public String getPrefijoTelefonico() {
        return prefijoTelefonico;
    }
    /**
     * @param prefijoTelefonico the prefijoTelefonico to set
     */
    public void setPrefijoTelefonico(String prefijoTelefonico) {
        this.prefijoTelefonico = prefijoTelefonico;
    }
    /**
     * @return the telax
     */
    public String getTelax() {
        return telax;
    }
    /**
     * @param telax the telax to set
     */
    public void setTelax(String telax) {
        this.telax = telax;
    }
    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }
    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }
    /**
     * @return the telefono1
     */
    public String getTelefono1() {
        return telefono1;
    }
    /**
     * @param telefono1 the telefono1 to set
     */
    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }
    /**
     * @return the telefono2
     */
    public String getTelefono2() {
        return telefono2;
    }
    /**
     * @param telefono2 the telefono2 to set
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }
    /**
     * @return the codCamara
     */
    public String getCodCamara() {
        return codCamara;
    }
    /**
     * @param codCamara the codCamara to set
     */
    public void setCodCamara(String codCamara) {
        this.codCamara = codCamara;
    }
    /**
     * @return the codigoEntidad
     */
    public String getCodigoEntidad() {
        return codigoEntidad;
    }
    /**
     * @param codigoEntidad the codigoEntidad to set
     */
    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

}
