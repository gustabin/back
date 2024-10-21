/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;

/**
 * The Class ConsumoTarjetaView.
 *
 * @author sabrina.cis
 */
public class ConsumoTarjetaView {

	/** The marca. */
	private String marca;

	/** The numero. */
	private String numero;

	/** The nombre adicional. */
	private String nombreAdicional;

	/** The consumo en pesos. */
	private String consumoPesos;

	/** The consumo en dolares. */
	private String consumoDolares;

	/** The consumo pesos pendientes. */
	private String consumoPesosPendientes;

	/** The consumo dolares pendientes. */
	private String consumoDolaresPendientes;

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

	/** The lineas. */
	private List<LineaDetalleConsumoTarjetaView> lineas = new ArrayList<LineaDetalleConsumoTarjetaView>();

	/**
	 * Instantiates a new consumo tarjeta view.
	 *
	 * @param consumoTarjetaDTO
	 *            the consumo tarjeta DTO
	 */
	public ConsumoTarjetaView(ConsumoTarjetaDTO consumoTarjetaDTO) {
		super();
		this.setMarca(consumoTarjetaDTO.getMarca());
		this.setNumero(consumoTarjetaDTO.getNumero());
		this.setNombreAdicional(consumoTarjetaDTO.getNombreAdicional());
		this.setConsumoPesos(consumoTarjetaDTO.getConsumoPesos());
		this.setConsumoDolares(consumoTarjetaDTO.getConsumoDolares());
		this.setConsumoPesosPendientes(consumoTarjetaDTO.getConsumoPesosPendientes());
		this.setConsumoDolaresPendientes(consumoTarjetaDTO.getConsumoDolaresPendientes());
		this.setIsTitular(consumoTarjetaDTO.getIsTitular());
		this.setIsAdicional(consumoTarjetaDTO.getIsAdicional());
		this.setHasConsumoPesosCero(consumoTarjetaDTO.getHasConsumoPesosCero());
		this.setHasConsumoDolaresCero(consumoTarjetaDTO.getHasConsumoDolaresCero());
		this.setHasError(consumoTarjetaDTO.getHasError());
		for (LineaDetalleConsumoTarjetaDTO lineaDetalleConsumoTarjetaDTO : consumoTarjetaDTO.getLineas()) {
			LineaDetalleConsumoTarjetaView linea = new LineaDetalleConsumoTarjetaView(lineaDetalleConsumoTarjetaDTO);
			lineas.add(linea);
		}
	}

	/**
	 * Instantiates a new consumo tarjeta view.
	 */
	public ConsumoTarjetaView() {
		super();
	}

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
	 * Gets the consumo pesos.
	 *
	 * @return the consumo pesos
	 */
	public String getConsumoPesos() {
		return consumoPesos;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumo
	 *            the new consumo pesos
	 */
	public void setConsumoPesos(BigDecimal consumo) {
		if (consumo != null) {
			this.consumoPesos = ISBANStringUtils.formatearSaldoConSigno(consumo);
		} else {
			this.consumoPesos = ISBANStringUtils.GUION_STRING;
		}
	}

	/**
	 * Sets the consumo pesos pendientes.
	 *
	 * @param consumo
	 *            the new consumo pesos pendientes
	 */
	public void setConsumoPesosPendientes(BigDecimal consumo) {
		if (consumo != null) {
			this.consumoPesosPendientes = ISBANStringUtils.formatearSaldoConSigno(consumo);
		} else {
			this.consumoPesosPendientes = ISBANStringUtils.GUION_STRING;
		}
	}

	/**
	 * Gets the consumo dolares.
	 *
	 * @return the consumo dolares
	 */
	public String getConsumoDolares() {
		return consumoDolares;
	}

	/**
	 * Sets the consumo dolares.
	 *
	 * @param consumo
	 *            the new consumo dolares
	 */
	public void setConsumoDolares(BigDecimal consumo) {
		if (consumo != null) {
			this.consumoDolares = ISBANStringUtils.formatearSaldoConSigno(consumo);
		} else {
			this.consumoDolares = ISBANStringUtils.GUION_STRING;
		}
	}

	/**
	 * Sets the consumo dolares pendientes.
	 *
	 * @param consumo
	 *            the new consumo dolares pendientes
	 */
	public void setConsumoDolaresPendientes(BigDecimal consumo) {
		if (consumo != null) {
			this.consumoDolaresPendientes = ISBANStringUtils.formatearSaldoConSigno(consumo);
		} else {
			this.consumoDolaresPendientes = ISBANStringUtils.GUION_STRING;
		}
	}

	/**
	 * Gets the consumo pesos pendientes.
	 *
	 * @return the consumoPesosPendientes
	 */
	public String getConsumoPesosPendientes() {
		return consumoPesosPendientes;
	}

	/**
	 * Sets the consumo pesos pendientes.
	 *
	 * @param consumoPesosPendientes
	 *            the consumoPesosPendientes to set
	 */
	public void setConsumoPesosPendientes(String consumoPesosPendientes) {
		this.consumoPesosPendientes = consumoPesosPendientes;
	}

	/**
	 * Gets the consumo dolares pendientes.
	 *
	 * @return the consumoDolaresPendientes
	 */
	public String getConsumoDolaresPendientes() {
		return consumoDolaresPendientes;
	}

	/**
	 * Sets the consumo dolares pendientes.
	 *
	 * @param consumoDolaresPendientes
	 *            the consumoDolaresPendientes to set
	 */
	public void setConsumoDolaresPendientes(String consumoDolaresPendientes) {
		this.consumoDolaresPendientes = consumoDolaresPendientes;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumoPesos
	 *            the consumoPesos to set
	 */
	public void setConsumoPesos(String consumoPesos) {
		this.consumoPesos = consumoPesos;
	}

	/**
	 * Sets the consumo dolares.
	 *
	 * @param consumoDolares
	 *            the consumoDolares to set
	 */
	public void setConsumoDolares(String consumoDolares) {
		this.consumoDolares = consumoDolares;
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
	 * Gets the lineas.
	 *
	 * @return the lineas
	 */
	public List<LineaDetalleConsumoTarjetaView> getLineas() {
		return lineas;
	}

	/**
	 * Sets the lineas.
	 *
	 * @param lineas
	 *            the new lineas
	 */
	public void setLineas(List<LineaDetalleConsumoTarjetaView> lineas) {
		this.lineas = lineas;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumoDolares);
		hcb.append(consumoPesos);
		hcb.append(consumoDolaresPendientes);
		hcb.append(consumoPesosPendientes);
		hcb.append(hasConsumoDolaresCero);
		hcb.append(hasConsumoPesosCero);
		hcb.append(hasError);
		hcb.append(isAdicional);
		hcb.append(isTitular);
		hcb.append(lineas);
		hcb.append(marca);
		hcb.append(nombreAdicional);
		hcb.append(numero);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConsumoTarjetaView other = (ConsumoTarjetaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(consumoDolares, other.consumoDolares);
		eb.append(consumoPesos, other.consumoPesos);
		eb.append(consumoDolaresPendientes, other.consumoDolaresPendientes);
		eb.append(consumoPesosPendientes, other.consumoPesosPendientes);
		eb.append(hasConsumoDolaresCero, other.hasConsumoDolaresCero);
		eb.append(hasConsumoPesosCero, other.hasConsumoPesosCero);
		eb.append(hasError, other.hasError);
		eb.append(isAdicional, other.isAdicional);
		eb.append(isTitular, other.isTitular);
		eb.append(lineas, other.lineas);
		eb.append(marca, other.marca);
		eb.append(nombreAdicional, other.nombreAdicional);
		eb.append(numero, other.numero);
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("marca", marca).append("numero", numero)
				.append("nombreAdicional", nombreAdicional).append("consumoPesos", consumoPesos)
				.append("consumoDolares", consumoDolares).append("consumoPesosPendientes", consumoPesosPendientes)
				.append("consumoDolaresPendientes", consumoDolaresPendientes).append("isTitular", isTitular)
				.append("isAdicional", isAdicional).append("hasConsumoPesosCero", hasConsumoPesosCero)
				.append("hasConsumoDolaresCero", hasConsumoDolaresCero).append("hasError", hasError)
				.append("lineas", lineas).toString();
	}
}