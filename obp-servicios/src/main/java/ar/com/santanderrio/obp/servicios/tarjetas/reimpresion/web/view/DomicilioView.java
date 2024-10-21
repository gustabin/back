/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;

/**
 * The Class DomicilioView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class DomicilioView {

    /** The domicilioId *. */
    private String domicilioId;

    /** The tipo. */
    private String tipoDomicilio;

    /** secuencia domicilio *. */
    private String secuenciaDomicilio;

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

    /** The domicilio. */
    private String domicilio;

    private boolean tieneProductos;

    private boolean modificable;

    /** The puerta. */
    private String puerta;
    
    /** The es sucursal. */
    @JsonSerialize(include = Inclusion.ALWAYS)
    private Boolean esSucursal;
    
    public DomicilioView() {
    	super();
    }
    
    
    public DomicilioView(CambioDomicilioDTO cambioDomicilioDTO) {
        this.setDomicilioId(cambioDomicilioDTO.getDomicilioId().trim());
        this.setApt(ISBANStringUtils.sacarCerosYBlancosIzq(cambioDomicilioDTO.getApt()));
        this.setCalle(cambioDomicilioDTO.getCalle().trim());
        this.setCaracteristica(cambioDomicilioDTO.getCaracteristica().trim());
        this.setCodigoPostal(cambioDomicilioDTO.getCodigoPostal().trim());
        this.setComuna(cambioDomicilioDTO.getComuna().trim());
        this.setDepartamento(cambioDomicilioDTO.getDepartamento().trim());
        this.setLocalidad(cambioDomicilioDTO.getLocalidad().trim());
        this.setNumeroTelefono(cambioDomicilioDTO.getNumeroTelefono().trim());
        this.setPiso(cambioDomicilioDTO.getPiso().trim());
        this.setPrefijo(cambioDomicilioDTO.getPrefijo().trim());
        this.setProvincia(cambioDomicilioDTO.getProvincia().trim());
        this.setDescProvincia(cambioDomicilioDTO.getDescProvincia().trim());
        this.setTelefono(cambioDomicilioDTO.getTelefono().trim());
        this.setSecuenciaDomicilio(cambioDomicilioDTO.getSecuenciaDomicilio().trim());
        this.setDescPais(cambioDomicilioDTO.getDescPais().trim());
        this.setTieneProductos(!cambioDomicilioDTO.getListaProductos().isEmpty());
	}

	/**
     * Gets the domicilioId.
     *
     * @return the string
     */
    public String getDomicilioId() {
        return domicilioId;
    }

    /**
     * Sets the domicilioId.
     *
     * @param domicilioId
     *            the new domicilio id
     */
    public void setDomicilioId(String domicilioId) {
        this.domicilioId = domicilioId;
    }

    /**
     * Gets the domicilio.
     *
     * @return the domicilio
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the domicilio.
     *
     * @param domicilio
     *            the new domicilio
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

    public boolean isTieneProductos() {
        return tieneProductos;
    }

    public void setTieneProductos(boolean tieneProductos) {
        this.tieneProductos = tieneProductos;
    }

    public boolean isModificable() {
        return modificable;
    }

    public void setModificable(boolean modificable) {
        this.modificable = modificable;
    }

    /**
     * Gets the puerta.
     *
     * @return the puerta
     */
    public String getPuerta() {
        return puerta;
    }

    /**
     * Sets the puerta.
     *
     * @param puerta
     *            the new puerta
     */
    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }
    
    /**
     * Es sucursal.
     *
     * @return the es sucursal
     */
    public Boolean esSucursal() {
        return esSucursal;
    }

    /**
     * Sets the es sucursal.
     *
     * @param esSucursal
     *            the new es sucursal
     */
    public void setEsSucursal(Boolean esSucursal) {
        this.esSucursal = esSucursal;
    }
    
}
