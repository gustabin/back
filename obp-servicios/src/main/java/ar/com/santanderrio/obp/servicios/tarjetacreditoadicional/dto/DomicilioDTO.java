/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DudaEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinEntity;

/**
 * The Class DomicilioDTO.
 */
public class DomicilioDTO {

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

    /**
	 * Instantiates a new domicilio DTO.
	 */
    public DomicilioDTO() {
        super();
    }

    /**
	 * Instantiates a new domicilio DTO.
	 *
	 * @param resultadoMerlinEntity
	 *            the resultado merlin entity
	 */
    public DomicilioDTO(ResultadoMerlinEntity resultadoMerlinEntity) {
        this.nombreCalle = StringUtils.trim(resultadoMerlinEntity.getNombreCalle());
        this.numeroBloque = StringUtils.stripStart(StringUtils.trim(resultadoMerlinEntity.getNumeroBloque()), "0");
        this.piso = StringUtils.trim(resultadoMerlinEntity.getPiso());
        this.departamento = StringUtils.trim(resultadoMerlinEntity.getDepartamento());
        this.descLocalidad = StringUtils.trim(resultadoMerlinEntity.getLocalidad());
        this.codPostal = StringUtils.trim(resultadoMerlinEntity.getCpa());
        this.codProv = StringUtils.trim(resultadoMerlinEntity.getProvincia());
    }

    /**
	 * Instantiates a new domicilio DTO.
	 *
	 * @param duda
	 *            the duda
	 * @param resultadoMerlinEntity
	 *            the resultado merlin entity
	 */
    public DomicilioDTO(DudaEntity duda, ResultadoMerlinEntity resultadoMerlinEntity) {
        this.nombreCalle = StringUtils.trim(duda.getDudNomcal());
        this.numeroBloque = StringUtils.stripStart(StringUtils.trim(resultadoMerlinEntity.getNumeroBloque()), "0");
        this.piso = StringUtils.trim(resultadoMerlinEntity.getPiso());
        this.departamento = StringUtils.trim(resultadoMerlinEntity.getDepartamento());
        this.descLocalidad = StringUtils.trim(duda.getDudLocal());
        this.codPostal = StringUtils.trim(duda.getDudCPA());
        this.codProv = StringUtils.trim(duda.getDudProv());
    }

    /**
     * Gets the nombre calle.
     *
     * @return the nombreCalle
     */
    public String getNombreCalle() {
        return nombreCalle;
    }

    /**
     * Sets the nombre calle.
     *
     * @param nombreCalle
     *            the nombreCalle to set
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
     *            the piso to set
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
     *            the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Gets the desc localidad.
     *
     * @return the descLocalidad
     */
    public String getDescLocalidad() {
        return descLocalidad;
    }

    /**
     * Sets the desc localidad.
     *
     * @param descLocalidad
     *            the descLocalidad to set
     */
    public void setDescLocalidad(String descLocalidad) {
        this.descLocalidad = descLocalidad;
    }

    /**
     * Gets the cod postal.
     *
     * @return the codPostal
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Sets the cod postal.
     *
     * @param codPostal
     *            the codPostal to set
     */
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    /**
     * Gets the cod prov.
     *
     * @return the codProv
     */
    public String getCodProv() {
        return codProv;
    }

    /**
     * Sets the cod prov.
     *
     * @param codProv
     *            the codProv to set
     */
    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    /**
     * Gets the desc prov.
     *
     * @return the descProv
     */
    public String getDescProv() {
        return descProv;
    }

    /**
     * Sets the desc prov.
     *
     * @param descProv
     *            the descProv to set
     */
    public void setDescProv(String descProv) {
        this.descProv = descProv;
    }
}
