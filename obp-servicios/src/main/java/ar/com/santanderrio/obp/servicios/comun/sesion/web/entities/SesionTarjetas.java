/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetasDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosPendientesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;

/**
 * The Class SesionTarjetas.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionTarjetas {

	/** The limites Y disponibles. */
	private List<LimitesYDisponiblesDTO> limitesYDisponibles;

	/** The consumos tarjeta. */
	private List<ConsumoTarjetaDTO> consumosTarjeta;

	/** The consumo pendiente. */
	private ConsumosPendientesView consumoPendiente;

	/** The items mensaje consumo pendiente. */
	private List<ItemMensajeRespuesta> itemsMensajeConsumoPendiente;

	/** The ultimo resumen. */
	private UltimoResumenDTO ultimoResumen;

	/** The reportes seleccionados. */
	private List<ReporteSeleccionado> reportesSeleccionados;

	/** The listaVistos. */
	private HashMap<String, Set<Integer>> listaVistos;

	/** The contador para estadistica. */
	private int contadorParaEstadistica;

	/** The cantidad errores. */
	private int cantidadErrores;

	/**
	 * Lista de tarjetas titulares del cliente. Se usa en financiacion de
	 * tarjetas - combo selector -
	 */
	private List<TarjetaView> tarjetas;

	/**
	 * Obtener consumo tarjetas.
	 *
	 * @return the consumo tarjetas
	 */
	public ConsumoTarjetasDTO obtenerConsumoTarjetas() {
		ConsumoTarjetasDTO tarjetas = new ConsumoTarjetasDTO();
		tarjetas.setConsumosTarjetas(this.getConsumosTarjeta());
		return tarjetas;
	}

	/**
	 * Gets the limites Y disponibles.
	 *
	 * @return the limites Y disponibles
	 */
	public List<LimitesYDisponiblesDTO> getLimitesYDisponibles() {
		return limitesYDisponibles;
	}

	/**
	 * Sets the limites Y disponibles.
	 *
	 * @param limitesYDisponibles
	 *            the new limites Y disponibles
	 */
	public void setLimitesYDisponibles(List<LimitesYDisponiblesDTO> limitesYDisponibles) {
		this.limitesYDisponibles = limitesYDisponibles;
	}

	/**
	 * Gets the reportes seleccionados.
	 *
	 * @return the reportes seleccionados
	 */
	public List<ReporteSeleccionado> getReportesSeleccionados() {
		return reportesSeleccionados;
	}

	/**
	 * Sets the reportes seleccionados.
	 *
	 * @param reportesSeleccionados
	 *            the new reportes seleccionados
	 */
	public void setReportesSeleccionados(List<ReporteSeleccionado> reportesSeleccionados) {
		this.reportesSeleccionados = reportesSeleccionados;
	}

	/**
	 * Gets the consumos tarjeta.
	 *
	 * @return the consumos tarjeta
	 */
	public List<ConsumoTarjetaDTO> getConsumosTarjeta() {
		return consumosTarjeta;
	}

	/**
	 * Sets the consumos tarjeta.
	 *
	 * @param consumosTarjeta
	 *            the new consumos tarjeta
	 */
	public void setConsumosTarjeta(List<ConsumoTarjetaDTO> consumosTarjeta) {
		this.consumosTarjeta = consumosTarjeta;
	}

	/**
	 * Gets the consumo pendiente.
	 *
	 * @return the consumo pendiente
	 */
	public ConsumosPendientesView getConsumoPendiente() {
		return consumoPendiente;
	}

	/**
	 * Sets the consumo pendiente.
	 *
	 * @param consumoPendiente
	 *            the new consumo pendiente
	 */
	public void setConsumoPendiente(ConsumosPendientesView consumoPendiente) {
		this.consumoPendiente = consumoPendiente;
	}

	/**
	 * Gets the ultimo resumen.
	 *
	 * @return the ultimo resumen
	 */
	public UltimoResumenDTO getUltimoResumen() {
		return ultimoResumen;
	}

	/**
	 * Sets the ultimo resumen.
	 *
	 * @param ultimoResumen
	 *            the new ultimo resumen
	 */
	public void setUltimoResumen(UltimoResumenDTO ultimoResumen) {
		this.ultimoResumen = ultimoResumen;
	}

	/**
	 * Gets the items mensaje consumo pendiente.
	 *
	 * @return the items mensaje consumo pendiente
	 */
	public List<ItemMensajeRespuesta> getItemsMensajeConsumoPendiente() {
		return itemsMensajeConsumoPendiente;
	}

	/**
	 * Sets the items mensaje consumo pendiente.
	 *
	 * @param itemsMensajeConsumoPendiente
	 *            the new items mensaje consumo pendiente
	 */
	public void setItemsMensajeConsumoPendiente(List<ItemMensajeRespuesta> itemsMensajeConsumoPendiente) {
		this.itemsMensajeConsumoPendiente = itemsMensajeConsumoPendiente;
	}

	/**
	 * Gets the reportes selccionados.
	 *
	 * @return the reportes selccionados
	 */
	public List<ReporteSeleccionado> getReportesSelccionados() {
		return reportesSeleccionados;
	}

	/**
	 * Sets the reportes selccionados.
	 *
	 * @param reportesSelccionados
	 *            the new reportes selccionados
	 */
	public void setReportesSelccionados(List<ReporteSeleccionado> reportesSelccionados) {
		this.reportesSeleccionados = reportesSelccionados;
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
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SesionTarjetas [limitesYDisponibles=" + limitesYDisponibles + ", consumosTarjeta=" + consumosTarjeta
				+ ", consumoPendiente=" + consumoPendiente + ", itemsMensajeConsumoPendiente="
				+ itemsMensajeConsumoPendiente + ", ultimoResumen=" + ultimoResumen + ", reportesSelccionados="
				+ reportesSeleccionados + "]";
	}

	/**
	 * Gets the contador para estadistica.
	 *
	 * @return the contador para estadistica
	 */
	public int getContadorParaEstadistica() {
		return contadorParaEstadistica;
	}

	/**
	 * Sets the contador para estadistica.
	 *
	 * @param contadorParaEstadistica
	 *            the new contador para estadistica
	 */
	public void setContadorParaEstadistica(int contadorParaEstadistica) {
		this.contadorParaEstadistica = contadorParaEstadistica;
	}

	/**
	 * Gets the cantidad errores.
	 *
	 * @return the cantidad errores
	 */
	public int getCantidadErrores() {
		return cantidadErrores;
	}

	/**
	 * Sets the cantidad errores.
	 *
	 * @param cantidadErrores
	 *            the new cantidad errores
	 */
	public void setCantidadErrores(int cantidadErrores) {
		this.cantidadErrores = cantidadErrores;
	}

	/**
	 * Gets the lista vistos.
	 *
	 * @return the lista vistos
	 */
	public HashMap<String, Set<Integer>> getListaVistos() {
		return listaVistos;
	}

	/**
	 * Sets the lista vistos.
	 *
	 * @param listaVistos
	 *            the lista vistos
	 */
	public void setListaVistos(HashMap<String, Set<Integer>> listaVistos) {
		this.listaVistos = listaVistos;
	}

}