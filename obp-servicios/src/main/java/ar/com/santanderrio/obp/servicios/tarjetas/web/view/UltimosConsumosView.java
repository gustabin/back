/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;

/**
 * 
 * The Class UltimosConsumosView.
 * 
 *
 */
public class UltimosConsumosView {

	/** The consumos tarjetas. */
	private List<ConsumoTarjetaView> consumosTarjetas = new ArrayList<ConsumoTarjetaView>();

	/** The estado respuesta consumos. */
	private EstadoRespuesta estadoRespuestaConsumos;

	/** The estado respuesta consumos pendientes. */
	private EstadoRespuesta estadoRespuestaConsumosPendientes;

	/** The muestra tarjetas con cabecera. */
	private Boolean muestraTarjetasConCabecera = Boolean.FALSE;

	/**
	 * Instantiates a new ultimos consumos view.
	 *
	 * @param consumosTarjetasDTO
	 *            the consumos tarjetas DTO
	 */
	// FIXME Corregir parametros de estado de respuesta.
	public UltimosConsumosView(List<ConsumoTarjetaDTO> consumosTarjetasDTO
	// , EstadoRespuesta estadoRespuestaConsumos, EstadoRespuesta
	// estadoRespuestaConsumosPendientes
	) {
		super();
		for (ConsumoTarjetaDTO consumoTarjetaDTO : consumosTarjetasDTO) {
			ConsumoTarjetaView consumoTarjetaView = new ConsumoTarjetaView(consumoTarjetaDTO);
			consumosTarjetas.add(consumoTarjetaView);
		}
		// this.setEstadoRespuestaConsumos(estadoRespuestaConsumos);
		// this.setEstadoRespuestaConsumosPendientes(estadoRespuestaConsumosPendientes);
	}

	/**
	 * Gets the consumos tarjetas.
	 *
	 * @return the consumos tarjetas
	 */
	public List<ConsumoTarjetaView> getConsumosTarjetas() {
		return consumosTarjetas;
	}

	/**
	 * Sets the consumos tarjetas.
	 *
	 * @param consumosTarjetas
	 *            the new consumos tarjetas
	 */
	public void setConsumosTarjetas(List<ConsumoTarjetaView> consumosTarjetas) {
		this.consumosTarjetas = consumosTarjetas;
	}

	/**
	 * Gets the estado respuesta consumos.
	 *
	 * @return the estado respuesta consumos
	 */
	public EstadoRespuesta getEstadoRespuestaConsumos() {
		return estadoRespuestaConsumos;
	}

	/**
	 * Sets the estado respuesta consumos .
	 *
	 * @param estadoRespuestaConsumos
	 *            the new estado respuesta consumos
	 */
	public void setEstadoRespuestaConsumos(EstadoRespuesta estadoRespuestaConsumos) {
		this.estadoRespuestaConsumos = estadoRespuestaConsumos;
	}

	/**
	 * Gets the estado respuesta consumos pendientes.
	 *
	 * @return the estado respuesta consumos pendientes
	 */
	public EstadoRespuesta getEstadoRespuestaConsumosPendientes() {
		return estadoRespuestaConsumosPendientes;
	}

	/**
	 * Sets the estado respuesta consumos pendientes.
	 *
	 * @param estadoRespuestaConsumosPendientes
	 *            the new estado respuesta consumos pendientes
	 */
	public void setEstadoRespuestaConsumosPendientes(EstadoRespuesta estadoRespuestaConsumosPendientes) {
		this.estadoRespuestaConsumosPendientes = estadoRespuestaConsumosPendientes;
	}

	/**
	 * Gets the muestra tarjetas con cabecera.
	 *
	 * @return the muestraTarjetasConCabecera
	 */
	public Boolean getMuestraTarjetasConCabecera() {
		return muestraTarjetasConCabecera;
	}

	/**
	 * Sets the muestra tarjetas con cabecera.
	 *
	 * @param muestraTarjetasConCabecera
	 *            the muestraTarjetasConCabecera to set
	 */
	public void setMuestraTarjetasConCabecera(Boolean muestraTarjetasConCabecera) {
		this.muestraTarjetasConCabecera = muestraTarjetasConCabecera;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumosTarjetas);
		hcb.append(estadoRespuestaConsumos);
		hcb.append(estadoRespuestaConsumosPendientes);
		hcb.append(muestraTarjetasConCabecera);
		return hcb.hashCode();
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
		UltimosConsumosView other = (UltimosConsumosView) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(consumosTarjetas, other.getConsumosTarjetas());
		eb.append(estadoRespuestaConsumos, other.getEstadoRespuestaConsumos());
		eb.append(estadoRespuestaConsumosPendientes, other.getEstadoRespuestaConsumosPendientes());
		eb.append(muestraTarjetasConCabecera, other.getMuestraTarjetasConCabecera());
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
		return new ToStringBuilder(this).append("consumosTarjetas", consumosTarjetas)
				.append("estadoRespuestaConsumos", estadoRespuestaConsumos)
				.append("estadoRespuestaConsumosPendientes", estadoRespuestaConsumosPendientes)
				.append("muestraTarjetasConCabecera", muestraTarjetasConCabecera).toString();
	}
}