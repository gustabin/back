/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomicilioDTO;

/**
 * The Class DomicilioView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DomicilioView {

    /** The nombre calle. */
    private String nombreCalle;

    /** The numero bloque. */
    private String numeroBloque;

    /** The piso. */
    private String piso;

    /** The departamento. */
    private String departamento;

    /** The desc localidad. */
    private String descLocalidad;

    /** The cod postal. */
    private String codPostal;

    /** The cod prov. */
    private String codProv;

    /** The desc prov. */
    private String descProv;
    
    /** The cpa. */
    private String cpa;

    /**
	 * Instantiates a new domicilio view.
	 */
    public DomicilioView() {
        super();
    }

    /**
	 * Instantiates a new domicilio view.
	 *
	 * @param domicilioDTO
	 *            the domicilio DTO
	 */
    public DomicilioView(DomicilioDTO domicilioDTO) {
        this.nombreCalle = domicilioDTO.getNombreCalle();
        this.numeroBloque = domicilioDTO.getNumeroBloque();
        this.piso = domicilioDTO.getPiso();
        this.departamento = domicilioDTO.getDepartamento();
        this.descLocalidad = domicilioDTO.getDescLocalidad();
        this.codPostal = StringUtils.substring(domicilioDTO.getCodPostal(), 1, 5);
        this.codProv = domicilioDTO.getCodProv();
        this.descProv = domicilioDTO.getDescProv();
        this.cpa = domicilioDTO.getCodPostal();
    }

    /**
	 * Gets the nombre calle.
	 *
	 * @return the nombre calle
	 */
    public String getNombreCalle() {
        return nombreCalle;
    }

    /**
	 * Sets the nombre calle.
	 *
	 * @param nombreCalle
	 *            the new nombre calle
	 */
    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    /**
	 * Gets the numero bloque.
	 *
	 * @return the numero bloque
	 */
    public String getNumeroBloque() {
        return numeroBloque;
    }

    /**
	 * Sets the numero bloque.
	 *
	 * @param numeroBloque
	 *            the new numero bloque
	 */
    public void setNumeroBloque(String numeroBloque) {
        this.numeroBloque = numeroBloque;
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
	 * Gets the desc localidad.
	 *
	 * @return the desc localidad
	 */
    public String getDescLocalidad() {
        return descLocalidad;
    }

    /**
	 * Sets the desc localidad.
	 *
	 * @param descLocalidad
	 *            the new desc localidad
	 */
    public void setDescLocalidad(String descLocalidad) {
        this.descLocalidad = descLocalidad;
    }

    /**
	 * Gets the cod postal.
	 *
	 * @return the cod postal
	 */
    public String getCodPostal() {
        return codPostal;
    }

    /**
	 * Sets the cod postal.
	 *
	 * @param codPostal
	 *            the new cod postal
	 */
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    /**
	 * Gets the cod prov.
	 *
	 * @return the cod prov
	 */
    public String getCodProv() {
        return codProv;
    }

    /**
	 * Sets the cod prov.
	 *
	 * @param codProv
	 *            the new cod prov
	 */
    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    /**
	 * Gets the desc prov.
	 *
	 * @return the desc prov
	 */
    public String getDescProv() {
        return descProv;
    }

    /**
	 * Sets the desc prov.
	 *
	 * @param descProv
	 *            the new desc prov
	 */
    public void setDescProv(String descProv) {
        this.descProv = descProv;
    }

    /**
	 * Gets the cpa.
	 *
	 * @return the cpa
	 */
    public String getCpa() {
        return cpa;
    }

    /**
	 * Sets the cpa.
	 *
	 * @param cpa
	 *            the new cpa
	 */
    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

}
