/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConsumoGeneralEntity.
 */
public class ConsumoGeneralEntity {
	/** The marca. */
	private String marca;

	/** The numero. */
	private String numero;

	/** The nombre adicional. */
	private String nombreAdicional;

	/** The es Titular. */
	private Boolean isTitular;

	/** The es Adicional. */
	private Boolean isAdicional;

	/** The has consumo pesos cero. */
	private Boolean hasConsumoPesosCero;

	/** The has consumo dolares cero. */
	private Boolean hasConsumoDolaresCero;

	/** The has error. */
	private Boolean hasError;

	/** The fecha desde. */
	private Date fechaDesde;

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the nombre adicional.
	 *
	 * @return the nombre adicional
	 */
	public String getNombreAdicional() {
		return nombreAdicional;
	}

	/**
	 * Sets the nombre adicional.
	 *
	 * @param nombreAdicional
	 *            the new nombre adicional
	 */
	public void setNombreAdicional(String nombreAdicional) {
		this.nombreAdicional = nombreAdicional;
	}

	/**
	 * Gets the checks if is titular.
	 *
	 * @return the checks if is titular
	 */
	public Boolean getIsTitular() {
		return isTitular;
	}

	/**
	 * Sets the checks if is titular.
	 *
	 * @param isTitular
	 *            the new checks if is titular
	 */
	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
	}

	/**
	 * Gets the checks if is adicional.
	 *
	 * @return the checks if is adicional
	 */
	public Boolean getIsAdicional() {
		return isAdicional;
	}

	/**
	 * Sets the checks if is adicional.
	 *
	 * @param isAdicional
	 *            the new checks if is adicional
	 */
	public void setIsAdicional(Boolean isAdicional) {
		this.isAdicional = isAdicional;
	}

	/**
	 * Gets the checks for consumo pesos cero.
	 *
	 * @return the checks for consumo pesos cero
	 */
	public Boolean getHasConsumoPesosCero() {
		return hasConsumoPesosCero;
	}

	/**
	 * Sets the checks for consumo pesos cero.
	 *
	 * @param hasConsumoPesosCero
	 *            the new checks for consumo pesos cero
	 */
	public void setHasConsumoPesosCero(Boolean hasConsumoPesosCero) {
		this.hasConsumoPesosCero = hasConsumoPesosCero;
	}

	/**
	 * Gets the checks for consumo dolares cero.
	 *
	 * @return the checks for consumo dolares cero
	 */
	public Boolean getHasConsumoDolaresCero() {
		return hasConsumoDolaresCero;
	}

	/**
	 * Sets the checks for consumo dolares cero.
	 *
	 * @param hasConsumoDolaresCero
	 *            the new checks for consumo dolares cero
	 */
	public void setHasConsumoDolaresCero(Boolean hasConsumoDolaresCero) {
		this.hasConsumoDolaresCero = hasConsumoDolaresCero;
	}

	/**
	 * Gets the checks for error.
	 *
	 * @return the checks for error
	 */
	public Boolean getHasError() {
		return hasError;
	}

	/**
	 * Sets the checks for error.
	 *
	 * @param hasError
	 *            the new checks for error
	 */
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public Date getFechaDesde() {
		return fechaDesde != null ? new Date(fechaDesde.getTime()) : null;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde != null ? new Date(fechaDesde.getTime()) : null;
	}

	/**
	 * Hash filler.
	 *
	 * @param hcb
	 *            the hcb
	 * @param isFirstPart
	 *            the is first part
	 */
	public void hashFiller(HashCodeBuilder hcb, boolean isFirstPart) {
		if (isFirstPart) {
			hcb.append(fechaDesde);
			hcb.append(hasConsumoDolaresCero);
			hcb.append(hasConsumoPesosCero);
			hcb.append(hasError);
			hcb.append(isAdicional);
			hcb.append(isTitular);
		} else {
			hcb.append(marca);
			hcb.append(nombreAdicional);
			hcb.append(numero);
		}
	}

	/**
	 * Equals filler.
	 *
	 * @param eb
	 *            the eb
	 * @param ct
	 *            the ct
	 * @param ctDTO
	 *            the ct DTO
	 * @param isFirst
	 *            the is first
	 */
	public void equalsFiller(EqualsBuilder eb, ConsumoTarjetaEntity ct, ConsumoTarjetaDTO ctDTO, boolean isFirst) {
		if (isFirst) {
			if (ct != null) {
				eb.append(fechaDesde, ct.getFechaDesde());
				eb.append(hasConsumoDolaresCero, ct.getHasConsumoDolaresCero());
				eb.append(hasConsumoPesosCero, ct.getHasConsumoPesosCero());
				eb.append(hasError, ct.getHasError());
				eb.append(isAdicional, ct.getIsAdicional());
				eb.append(isTitular, ct.getIsTitular());
			} else {
				eb.append(fechaDesde, ctDTO.getFechaDesde());
				eb.append(hasConsumoDolaresCero, ctDTO.getHasConsumoDolaresCero());
				eb.append(hasConsumoPesosCero, ctDTO.getHasConsumoPesosCero());
				eb.append(hasError, ctDTO.getHasError());
				eb.append(isAdicional, ctDTO.getIsAdicional());
				eb.append(isTitular, ctDTO.getIsTitular());
			}
		} else {
			if (ct != null) {
				eb.append(marca, ct.getMarca());
				eb.append(nombreAdicional, ct.getNombreAdicional());
				eb.append(numero, ct.getNumero());
			} else {
				eb.append(marca, ctDTO.getMarca());
				eb.append(nombreAdicional, ctDTO.getNombreAdicional());
				eb.append(numero, ctDTO.getNumero());
			}
		}
	}

}
