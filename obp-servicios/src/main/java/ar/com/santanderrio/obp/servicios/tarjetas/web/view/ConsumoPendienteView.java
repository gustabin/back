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
 * The Class ConsumoPendienteView.
 */
public class ConsumoPendienteView {

	/** The marca. */
	private String marca;

	/** The numero. */
	private String numero;

	/** The nombre adicional. */
	private String nombreAdicional;

	/** The total pesos. */
	private String totalPesos;

	/** The total dolares. */
	private String totalDolares;

	/** The es Titular. */
	private Boolean isTitular;

	/** The has total pesos cero. */
	private Boolean hasTotalPesosCero;

	/** The has total dolares cero. */
	private Boolean hasTotalDolaresCero;

	/** The has error. */
	private Boolean hasError;

	/** The lineas. */
	private List<LineaDetalleConsumoTarjetaView> lineas = new ArrayList<LineaDetalleConsumoTarjetaView>();

	/**
	 * Instantiates a new consumo pendiente view.
	 */
	public ConsumoPendienteView() {
		super();
	}

	/**
	 * Instantiates a new consumo pendiente view.
	 *
	 * @param dto
	 *            the dto
	 */
	public ConsumoPendienteView(ConsumoTarjetaDTO dto) {
		this.setMarca(dto.getMarca());
		this.setNumero(dto.getNumero());
		this.setNombreAdicional(dto.getNombreAdicional());
		this.setTotalPesos(dto.getConsumoPesos());
		this.setTotalDolares(dto.getConsumoDolares());
		this.setIsTitular(dto.getIsTitular());
		this.setHasTotalPesosCero(dto.getHasConsumoPesosCero());
		this.setHasTotalDolaresCero(dto.getHasConsumoDolaresCero());
		this.setHasError(dto.getHasError());
		List<LineaDetalleConsumoTarjetaView> lineaDetalleConsumoPendienteViewList = new ArrayList<LineaDetalleConsumoTarjetaView>();
		for (LineaDetalleConsumoTarjetaDTO lineaDetalleConsumoPendienteDTO : dto.getLineas()) {
			LineaDetalleConsumoTarjetaView lineaDetalleConsumoPendienteView = new LineaDetalleConsumoTarjetaView(
					lineaDetalleConsumoPendienteDTO);
			lineaDetalleConsumoPendienteViewList.add(lineaDetalleConsumoPendienteView);
		}
		this.setLineas(lineaDetalleConsumoPendienteViewList);
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
	 * Gets the checks for total pesos cero.
	 *
	 * @return the checks for total pesos cero
	 */
	public Boolean getHasTotalPesosCero() {
		return hasTotalPesosCero;
	}

	/**
	 * Sets the checks for total pesos cero.
	 *
	 * @param hasTotalPesosCero
	 *            the new checks for total pesos cero
	 */
	public void setHasTotalPesosCero(Boolean hasTotalPesosCero) {
		this.hasTotalPesosCero = hasTotalPesosCero;
	}

	/**
	 * Gets the checks for total dolares cero.
	 *
	 * @return the checks for total dolares cero
	 */
	public Boolean getHasTotalDolaresCero() {
		return hasTotalDolaresCero;
	}

	/**
	 * Sets the checks for total dolares cero.
	 *
	 * @param hasTotalDolaresCero
	 *            the new checks for total dolares cero
	 */
	public void setHasTotalDolaresCero(Boolean hasTotalDolaresCero) {
		this.hasTotalDolaresCero = hasTotalDolaresCero;
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
	 * Gets the total pesos.
	 *
	 * @return the total pesos
	 */
	public String getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the new total pesos
	 */
	public void setTotalPesos(BigDecimal totalPesos) {
		if (totalPesos != null) {
			this.totalPesos = ISBANStringUtils.formatearSaldoConSigno(totalPesos);
		}
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the total dolares
	 */
	public String getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the new total dolares
	 */
	public void setTotalDolares(BigDecimal totalDolares) {
		if (totalDolares != null) {
			this.totalDolares = ISBANStringUtils.formatearSaldoConSigno(totalDolares);
		}
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(hasError);
		hcb.append(hasTotalDolaresCero);
		hcb.append(hasTotalPesosCero);
		hcb.append(isTitular);
		hcb.append(lineas);
		hcb.append(marca);
		hcb.append(nombreAdicional);
		hcb.append(numero);
		hcb.append(totalDolares);
		hcb.append(totalPesos);
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
		ConsumoPendienteView other = (ConsumoPendienteView) obj;
		return new EqualsBuilder().append(hasError, other.hasError)
				.append(hasTotalDolaresCero, other.hasTotalDolaresCero)
				.append(hasTotalPesosCero, other.hasTotalPesosCero).append(isTitular, other.isTitular)
				.append(lineas, other.lineas).append(marca, other.marca).append(nombreAdicional, other.nombreAdicional)
				.append(numero, other.numero).append(totalDolares, other.totalDolares)
				.append(totalPesos, other.totalPesos).isEquals();
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
				.append("nombreAdicional", nombreAdicional).append("totalPesos", totalPesos)
				.append("totalDolares", totalDolares).append("isTitular", isTitular)
				.append("hasTotalPesosCero", hasTotalPesosCero).append("hasTotalDolaresCero", hasTotalDolaresCero)
				.append("hasError", hasError).append("lineas").toString();
	}

}