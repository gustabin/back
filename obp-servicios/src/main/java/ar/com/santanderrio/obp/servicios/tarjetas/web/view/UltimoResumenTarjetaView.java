/*
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
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaUltimoResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenTarjetaDTO;

/**
 * View que representa cada tarjeta de la pantalla de ultimo resumen.
 *
 * @author federico.n.flores
 */
public class UltimoResumenTarjetaView {

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The total pesos. */
	private String totalPesos;

	/** The total dolares. */
	private String totalDolares;

	/** The lineas. */
	private List<LineaUltimoResumenTarjetaView> lineas;

	/**
	 * Instantiates a new UltimoResumenTarjetaView con argumento
	 * UltimoResumenTarjetaDTO.
	 *
	 * @param dto
	 *            the dto
	 */
	public UltimoResumenTarjetaView(UltimoResumenTarjetaDTO dto) {
		super();
		this.setNumeroTarjeta(dto.getNumeroTarjeta());
		this.setTotalPesos(dto.getTotalPesos());
		this.setTotalDolares(dto.getTotalDolares());
		List<LineaUltimoResumenTarjetaView> lineasTarjeta = new ArrayList<LineaUltimoResumenTarjetaView>();
		for (LineaUltimoResumenTarjetaDTO lineaDTO : dto.getLineas()) {
			LineaUltimoResumenTarjetaView lineaView = new LineaUltimoResumenTarjetaView(lineaDTO);
			lineasTarjeta.add(lineaView);
		}
		this.setLineas(lineasTarjeta);
	}

	/**
	 * Instantiates a new UltimoResumenTarjetaView.
	 */
	public UltimoResumenTarjetaView() {
		super();
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
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
	 * Gets the lineas.
	 *
	 * @return the lineas
	 */
	public List<LineaUltimoResumenTarjetaView> getLineas() {
		return lineas;
	}

	/**
	 * Sets the lineas.
	 *
	 * @param lineas
	 *            the new lineas
	 */
	public void setLineas(List<LineaUltimoResumenTarjetaView> lineas) {
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
		hcb.append(numeroTarjeta);
		hcb.append(totalPesos);
		hcb.append(totalDolares);
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
		UltimoResumenTarjetaView other = (UltimoResumenTarjetaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(numeroTarjeta, other.numeroTarjeta);
		eb.append(totalPesos, other.totalPesos);
		eb.append(totalDolares, other.totalDolares);
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
		return new ToStringBuilder(this).append("numeroTarjeta", numeroTarjeta).append("totalPesos", totalPesos)
				.append("totalDolares", totalDolares).append("lineas", lineas).toString();
	}

}