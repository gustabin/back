/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class DomicilioView.
 * 
 * @author Silvina_Luque
 */
@SuppressWarnings("serial")
@JsonSerialize(include = Inclusion.NON_NULL)
public class CambioDomicilioView extends RsaDTO {

    /** The domicilioID. */
    private String domicilioId;

    /** The domicilioID. */
    private String secuenciaDomicilio;

    /** The tipo. */
    private String tipoDomicilio;

    /** The nombre calle. */
    private String calle;

    /** The piso. */
    private String piso;

    /** The departamento. */
    private String departamento;

    /** The apt. */
    private String apt;

    /** The desc localidad. */
    private String localidad;

    /** The desc comuna. */
    private String comuna;

    /** The cod postal. */
    private String codigoPostal;

    /** The desc prov. */
    private String descProvincia;

    /** The desc pais. */
    private String descPais;

    /** The telefono. */
    private String telefono;

    /** The prefijo. */
    private String prefijo;

    /** The caracteristica. */
    private String caracteristica;

    /** The numeroTelefono. */
    private String numeroTelefono;

    /** The codigo provincia. */
    private String provincia;

    /** The titulo. */
    private String titulo;

    /** The domicilioCompleto. */
    private String domicilioCompleto;

    /** Mensaje resultado. */
    private String mensajeFeedback;

    /** The normalizar. */
    private Boolean normalizar;

    /** The desafio. */
    @JsonIgnore
    @JsonManagedReference
    private AutentificacionDTO desafio;

    /** The tiene celular my A. Se usa en RSA. */
    private boolean tieneCelularMyA = false;

    /** The permite reintentar. */
    private boolean permiteReintentar = true;

    /** The domicilio O producto privado. */
    private Boolean domicilioOProductoPrivado = Boolean.FALSE;

    /** The domicilio O producto prendario. */
    private Boolean domicilioOProductoPrendario = Boolean.FALSE;

    /** The descripciones productos. */
    private List<String> descripcionesProductos = new ArrayList<String>();

    /**
     * Instantiates a new cambio domicilio view.
     */
    public CambioDomicilioView() {
        super(OperacionesRSAEnum.CAMBIODOMICILIO);
    }

    /**
     * Gets the tipo domicilio.
     *
     * @return the tipo domicilio
     */
    public String getTipoDomicilio() {
        return tipoDomicilio;
    }

    /**
     * Sets the tipo domicilio.
     *
     * @param tipoDomicilio
     *            the new tipo domicilio
     */
    public void setTipoDomicilio(String tipoDomicilio) {
        this.tipoDomicilio = tipoDomicilio;
    }

    /**
     * Gets the calle.
     *
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the calle.
     *
     * @param calle
     *            the new calle
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Gets the piso.
     *
     * @return the piso
     */
    public String getPiso() {
        return piso;
    }

    /**
     * Sets the piso.
     *
     * @param piso
     *            the new piso
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * Gets the departamento.
     *
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the departamento.
     *
     * @param departamento
     *            the new departamento
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Gets the apt.
     *
     * @return the apt
     */
    public String getApt() {
        return apt;
    }

    /**
     * Sets the apt.
     *
     * @param apt
     *            the new apt
     */
    public void setApt(String apt) {
        this.apt = apt;
    }

    /**
     * Gets the localidad.
     *
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the localidad.
     *
     * @param localidad
     *            the new localidad
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Gets the comuna.
     *
     * @return the comuna
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * Sets the comuna.
     *
     * @param comuna
     *            the new comuna
     */
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    /**
     * Gets the codigo postal.
     *
     * @return the codigo postal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Sets the codigo postal.
     *
     * @param codigoPostal
     *            the new codigo postal
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Gets the telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the telefono.
     *
     * @param telefono
     *            the new telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets the desc provincia.
     *
     * @return the desc provincia
     */
    public String getDescProvincia() {
        return descProvincia;
    }

    /**
     * Sets the desc provincia.
     *
     * @param descProvincia
     *            the new desc provincia
     */
    public void setDescProvincia(String descProvincia) {
        this.descProvincia = descProvincia;
    }

    /**
     * Gets the desc pais.
     *
     * @return the desc pais
     */
    public String getDescPais() {
        return descPais;
    }

    /**
     * Sets the desc pais.
     *
     * @param descPais
     *            the new desc pais
     */
    public void setDescPais(String descPais) {
        this.descPais = descPais;
    }

    /**
     * Gets the domicilio id.
     *
     * @return the domicilio id
     */
    public String getDomicilioId() {
        return domicilioId;
    }

    /**
     * Sets the domicilio id.
     *
     * @param domicilioId
     *            the new domicilio id
     */
    public void setDomicilioId(String domicilioId) {
        this.domicilioId = domicilioId;
    }

    /**
     * Gets the provincia.
     *
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the provincia.
     *
     * @param provincia
     *            the new provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Gets the prefijo.
     *
     * @return the prefijo
     */
    public String getPrefijo() {
        return prefijo;
    }

    /**
     * Sets the prefijo.
     *
     * @param prefijo
     *            the new prefijo
     */
    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    /**
     * Gets the caracteristica.
     *
     * @return the caracteristica
     */
    public String getCaracteristica() {
        return caracteristica;
    }

    /**
     * Sets the caracteristica.
     *
     * @param caracteristica
     *            the new caracteristica
     */
    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    /**
     * Gets the numero telefono.
     *
     * @return the numero telefono
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * Sets the numero telefono.
     *
     * @param numeroTelefono
     *            the new numero telefono
     */
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the titulo.
     *
     * @param titulo
     *            the new titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets the domicilio completo.
     *
     * @return the domicilio completo
     */
    public String getDomicilioCompleto() {
        return domicilioCompleto;
    }

    /**
     * Sets the domicilio completo.
     *
     * @param domicilioCompleto
     *            the new domicilio completo
     */
    public void setDomicilioCompleto(String domicilioCompleto) {
        this.domicilioCompleto = domicilioCompleto;
    }

    /**
     * @return the normalizar
     */
    public Boolean getNormalizar() {
        return normalizar;
    }

    /**
     * @param normalizar the normalizar to set
     */
    public void setNormalizar(Boolean normalizar) {
        this.normalizar = normalizar;
    }

    /**
     * Gets the desafio.
     *
     * @return the desafio
     */
    public AutentificacionDTO getDesafio() {
        return desafio;
    }

    /**
     * Sets the desafio.
     *
     * @param desafio
     *            the new desafio
     */
    public void setDesafio(AutentificacionDTO desafio) {
        this.desafio = desafio;
    }

    /**
     * Checks if is tiene celular my A.
     *
     * @return true, if is tiene celular my A
     */
    public boolean isTieneCelularMyA() {
        return tieneCelularMyA;
    }

    /**
     * Sets the tiene celular my A.
     *
     * @param tieneCelularMyA
     *            the new tiene celular my A
     */
    public void setTieneCelularMyA(boolean tieneCelularMyA) {
        this.tieneCelularMyA = tieneCelularMyA;
    }

    /**
     * Gets the mensaje feedback.
     *
     * @return the mensaje feedback
     */
    public String getMensajeFeedback() {
        return mensajeFeedback;
    }

    /**
     * Sets the mensaje feedback.
     *
     * @param mensajeFeedback
     *            the new mensaje feedback
     */
    public void setMensajeFeedback(String mensajeFeedback) {
        this.mensajeFeedback = mensajeFeedback;
    }

    /**
     * Checks if is permite reintentar.
     *
     * @return true, if is permite reintentar
     */
    public boolean isPermiteReintentar() {
        return permiteReintentar;
    }

    /**
     * Sets the permite reintentar.
     *
     * @param permiteReintentar
     *            the new permite reintentar
     */
    public void setPermiteReintentar(boolean permiteReintentar) {
        this.permiteReintentar = permiteReintentar;
    }

    /**
     * Gets the secuencia domicilio.
     *
     * @return the secuencia domicilio
     */
    public String getSecuenciaDomicilio() {
        return secuenciaDomicilio;
    }

    /**
     * Sets the secuencia domicilio.
     *
     * @param secuenciaDomicilio
     *            the new secuencia domicilio
     */
    public void setSecuenciaDomicilio(String secuenciaDomicilio) {
        this.secuenciaDomicilio = secuenciaDomicilio;
    }

    /**
     * Gets the descripciones productos.
     *
     * @return the descripciones productos
     */
    public List<String> getDescripcionesProductos() {
        return descripcionesProductos;
    }

    /**
     * Sets the descripciones productos.
     *
     * @param descripcionesProductos
     *            the new descripciones productos
     */
    public void setDescripcionesProductos(List<String> descripcionesProductos) {
        this.descripcionesProductos = descripcionesProductos;
    }

    /**
     * Gets the domicilio O producto privado.
     *
     * @return the domicilio O producto privado
     */
    public Boolean getDomicilioOProductoPrivado() {
        return domicilioOProductoPrivado;
    }

    /**
     * Sets the domicilio O producto privado.
     *
     * @param domicilioOProductoPrivado
     *            the new domicilio O producto privado
     */
    public void setDomicilioOProductoPrivado(Boolean domicilioOProductoPrivado) {
        this.domicilioOProductoPrivado = domicilioOProductoPrivado;
    }

    /**
     * Gets the domicilio O producto prendario.
     *
     * @return the domicilio O producto prendario
     */
    public Boolean getDomicilioOProductoPrendario() {
        return domicilioOProductoPrendario;
    }

    /**
     * Sets the domicilio O producto prendario.
     *
     * @param domicilioOProductoPrendario
     *            the new domicilio O producto prendario
     */
    public void setDomicilioOProductoPrendario(Boolean domicilioOProductoPrendario) {
        this.domicilioOProductoPrendario = domicilioOProductoPrendario;
    }

    /**
     * Gets the prioridad.
     *
     * @return the prioridad
     */
    public Integer consultarPrioridad() {
        if ("PRI".equals(this.tipoDomicilio)) {
            return 0;
        } else if ("LAB".equals(this.tipoDomicilio)) {
            return 1;
        } else if ("ALT".equals(this.tipoDomicilio)) {
            return 2;
        } else if ("BRP".equals(this.tipoDomicilio)) {
            return 3;
        } else {
            return 4;
        }
    }

//    @Override
//    public String toString() {
//        return "CambioDomicilioView [domicilioId=" + domicilioId + ", secuenciaDomicilio=" + secuenciaDomicilio
//                + ", tipoDomicilio=" + tipoDomicilio + ", calle=" + calle + ", piso=" + piso + ", departamento="
//                + departamento + ", apt=" + apt + ", localidad=" + localidad + ", comuna=" + comuna + ", codigoPostal="
//                + codigoPostal + ", descProvincia=" + descProvincia + ", descPais=" + descPais + ", telefono="
//                + telefono + ", prefijo=" + prefijo + ", caracteristica=" + caracteristica + ", numeroTelefono="
//                + numeroTelefono + ", provincia=" + provincia + ", titulo=" + titulo + ", domicilioCompleto="
//                + domicilioCompleto + ", mensajeFeedback=" + mensajeFeedback + ", desafio=" + desafio
//                + ", tieneCelularMyA=" + tieneCelularMyA + ", permiteReintentar=" + permiteReintentar
//                + ", domicilioOProductoPrivado=" + domicilioOProductoPrivado + ", domicilioOProductoPrendario="
//                + domicilioOProductoPrendario + ", descripcionesProductos=" + descripcionesProductos + "]";
//    }

}
