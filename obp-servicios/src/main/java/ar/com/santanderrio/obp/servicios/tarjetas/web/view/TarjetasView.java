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
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;

/**
 * 
 * The Class TarjetasView.
 * 
 * @author florencia.n.martinez
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class TarjetasView {

	/** The tarjetas. */
	private List<TarjetaView> tarjetas;

	/** The tarjeta seleccionada. */
	private Integer tarjetaSeleccionada;

	/** The consumo tarjeta default. */
	private List<ConsumoTarjetaView> consumoTarjetaDefault;

	/** The estado respuesta consumo. */
	private EstadoRespuesta estadoRespuestaConsumo;

	/** The estado respuesta consumos pendientes. */
	private EstadoRespuesta estadoRespuestaConsumosPendientes;

	/** The muestra tarjetas con cabecera. */
	private Boolean muestraTarjetasConCabecera = Boolean.FALSE;

	/** The tooltip tarjeta favorita. */
	private String tooltipTarjetaFavorita;

	/** The tooltip tarjeta no favorita. */
	private String tooltipTarjetaNoFavorita;

	/** The legales ultimos consumos. */
	private String legalesUltimosConsumos;

	/** The mostrar opcion pago tarjeta credito. */
	private Boolean mostrarOpcionPagoTarjetaCredito;
	
	private Boolean mostrarOpcionContracargos;

	/** The mensaje opcion pago tarjeta credito. */
	private String mensajeOpcionPagoTarjetaCredito;

	/** The variable 1 char. */
	private String variable1Char;
	
	/** The nombre titular. */
	private String nombreTitular;

	/** The direccion sucursal */
	private String direccionSucursal;
	
	private String grupoAfinidad;
	
	/**
	 * Instantiates a new tarjetas view.
	 */
	public TarjetasView() {
		super();
		this.tarjetas = new ArrayList<TarjetaView>();
		this.tarjetaSeleccionada = 0;
		this.consumoTarjetaDefault = new ArrayList<ConsumoTarjetaView>();
		this.muestraTarjetasConCabecera = false;
	}
	/**
	 * Gets the estado respuesta consumo.
	 *
	 * @return the estado respuesta consumo
	 */
	public EstadoRespuesta getEstadoRespuestaConsumo() {
		return estadoRespuestaConsumo;
	}

	/**
	 * Sets the estado respuesta consumo.
	 *
	 * @param estadoRespuestaConsumo
	 *            the new estado respuesta consumo
	 */
	public void setEstadoRespuestaConsumo(EstadoRespuesta estadoRespuestaConsumo) {
		this.estadoRespuestaConsumo = estadoRespuestaConsumo;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetaView> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the tarjeta seleccionada.
	 *
	 * @return the tarjeta seleccionada
	 */
	public Integer getTarjetaSeleccionada() {
		return tarjetaSeleccionada;
	}

	/**
	 * Sets the tarjeta seleccionada.
	 *
	 * @param tarjetaSeleccionada
	 *            the new tarjeta seleccionada
	 */
	public void setTarjetaSeleccionada(Integer tarjetaSeleccionada) {
		this.tarjetaSeleccionada = tarjetaSeleccionada;
	}

	/**
	 * Gets the consumo tarjeta default.
	 *
	 * @return the consumo tarjeta default
	 */
	public List<ConsumoTarjetaView> getConsumoTarjetaDefault() {
		return consumoTarjetaDefault;
	}

	/**
	 * Sets the consumo tarjeta default.
	 *
	 * @param consumoTarjetaDefault
	 *            the new consumo tarjeta default
	 */
	public void setConsumoTarjetaDefault(List<ConsumoTarjetaView> consumoTarjetaDefault) {
		this.consumoTarjetaDefault = consumoTarjetaDefault;
	}

	/**
	 * Gets the tooltip tarjeta favorita.
	 *
	 * @return the tooltip tarjeta favorita
	 */
	public String getTooltipTarjetaFavorita() {
		return tooltipTarjetaFavorita;
	}

	/**
	 * Sets the tooltip tarjeta favorita.
	 *
	 * @param tooltipTarjetaFavorita
	 *            the new tooltip tarjeta favorita
	 */
	public void setTooltipTarjetaFavorita(String tooltipTarjetaFavorita) {
		this.tooltipTarjetaFavorita = tooltipTarjetaFavorita;
	}

	/**
	 * Gets the tooltip tarjeta no favorita.
	 *
	 * @return the tooltip tarjeta no favorita
	 */
	public String getTooltipTarjetaNoFavorita() {
		return tooltipTarjetaNoFavorita;
	}

	/**
	 * Sets the tooltip tarjeta no favorita.
	 *
	 * @param tooltipTarjetaNoFavorita
	 *            the new tooltip tarjeta no favorita
	 */
	public void setTooltipTarjetaNoFavorita(String tooltipTarjetaNoFavorita) {
		this.tooltipTarjetaNoFavorita = tooltipTarjetaNoFavorita;
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
	 * Gets the mostrar opcion pago tarjeta credito.
	 *
	 * @return the mostrar opcion pago tarjeta credito
	 */
	public Boolean getMostrarOpcionPagoTarjetaCredito() {
		return mostrarOpcionPagoTarjetaCredito;
	}

	/**
	 * Sets the mostrar opcion pago tarjeta credito.
	 *
	 * @param mostrarOpcionPagoTarjetaCredito
	 *            the new mostrar opcion pago tarjeta credito
	 */
	public void setMostrarOpcionPagoTarjetaCredito(Boolean mostrarOpcionPagoTarjetaCredito) {
		this.mostrarOpcionPagoTarjetaCredito = mostrarOpcionPagoTarjetaCredito;
	}
	

	public Boolean getMostrarOpcionContracargos() {
		return mostrarOpcionContracargos;
	}
	public void setMostrarOpcionContracargos(Boolean mostrarOpcionContracargos) {
		this.mostrarOpcionContracargos = mostrarOpcionContracargos;
	}
	/**
	 * Gets the legales ultimos consumos.
	 *
	 * @return the legalesUltimosConsumos
	 */
	public String getLegalesUltimosConsumos() {
		return legalesUltimosConsumos;
	}

	/**
	 * Sets the legales ultimos consumos.
	 *
	 * @param legalesUltimosConsumos
	 *            the legalesUltimosConsumos to set
	 */
	public void setLegalesUltimosConsumos(String legalesUltimosConsumos) {
		this.legalesUltimosConsumos = legalesUltimosConsumos;
	}
	
	/**
	 * Gets the variable1Char.
	 *
	 * @return the variable1Char
	 */
	public String getVariable1Char() {
		return variable1Char;
	}
	
	/**
	 * Sets the variable1Char.
	 *
	 * @param variable1Char
	 *            the variable1Char to set
	 */
	public void setVariable1Char(String variable1Char) {
		this.variable1Char = variable1Char;
	}
	
	/**
	 * Gets the nombreTitular.
	 *
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}
	
	/**
	 * Sets the nombre titular.
	 *
	 * @param nombreTitular
	 *            the nombre titular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	
	/**
	 * Gets the direccionSucursal.
	 *
	 * @return the direccionSucursal
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}
	
	/**
	 * Sets the direccion sucursal.
	 *
	 * @param direccionSucursal
	 *            the direccion sucursal to set
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	
	public String getGrupoAfinidad() {
		return grupoAfinidad;
	}
	public void setGrupoAfinidad(String grupoAfinidad) {
		this.grupoAfinidad = grupoAfinidad;
	}

	/**
	 * Gets the mensaje opcion pago tarjeta credito.
	 *
	 * @return the mensaje opcion pago tarjeta credito
	 */
	public String getMensajeOpcionPagoTarjetaCredito() {
		return mensajeOpcionPagoTarjetaCredito;
	}

	/**
	 * Sets the mensaje opcion pago tarjeta credito.
	 *
	 * @param opcionPagoTarjetaAviso the new mensaje opcion pago tarjeta credito
	 */
	public void setMensajeOpcionPagoTarjetaCredito(String opcionPagoTarjetaAviso) {
		this.mensajeOpcionPagoTarjetaCredito = opcionPagoTarjetaAviso;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumoTarjetaDefault);
		hcb.append(estadoRespuestaConsumo);
		hcb.append(estadoRespuestaConsumosPendientes);
		hcb.append(muestraTarjetasConCabecera);
		hcb.append(tarjetaSeleccionada);
		hcb.append(tooltipTarjetaFavorita);
		hcb.append(tooltipTarjetaNoFavorita);
		hcb.append(grupoAfinidad);
		hcb.append(mensajeOpcionPagoTarjetaCredito);
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
		TarjetasView other = (TarjetasView) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(consumoTarjetaDefault, other.getConsumoTarjetaDefault());
		eb.append(estadoRespuestaConsumo, other.getEstadoRespuestaConsumo());
		eb.append(tarjetaSeleccionada, other.getTarjetaSeleccionada());
		eb.append(tooltipTarjetaFavorita, other.getTooltipTarjetaFavorita());
		eb.append(tooltipTarjetaNoFavorita, other.getTooltipTarjetaNoFavorita());
		eb.append(estadoRespuestaConsumosPendientes, other.getEstadoRespuestaConsumosPendientes());
		eb.append(grupoAfinidad, other.getGrupoAfinidad());
		eb.append(mensajeOpcionPagoTarjetaCredito, other.getMensajeOpcionPagoTarjetaCredito());
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
		return new ToStringBuilder(this).append("tarjetas", tarjetas).append("tarjetaSeleccionada", tarjetaSeleccionada)
				.append("consumoTarjetaDefault", consumoTarjetaDefault)
				.append("estadoRespuestaConsumo", estadoRespuestaConsumo)
				.append("estadoRespuestaConsumosPendientes", estadoRespuestaConsumosPendientes)
				.append("tooltipTarjetaFavorita", tooltipTarjetaFavorita)
				.append("tooltipTarjetaNoFavorita", tooltipTarjetaNoFavorita)
				.append("grupoAfinidad", grupoAfinidad).append("mensajeOpcionPagoTarjetaCredito", mensajeOpcionPagoTarjetaCredito).toString();
	}

}