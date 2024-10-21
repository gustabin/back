/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class OrdenesView. Representa el dto que transporta los datos al a grilla
 * de busqueda de ordenes y operaciones.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 * 
 */
@XmlRootElement(name = "ordenesView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltrosOrdenesView extends View {

		/** The Constant TREINTA. */
	private static final String CUARENTA_Y_CINCO = "Últimos 45 días";

	/** The Constant TREINTA. */
	private static final String SESENTA = "Últimos 60 días";

	/** The Constant ORDENES. */
	private static final String ORDENES = "Órdenes";

	/** The Constant OPERACIONES. */
	private static final String OPERACIONES = "Operaciones";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5989915634272260628L;

	/** The tipo. */
	List<TipoView> tipos = new ArrayList<TipoView>();

	/** The periodos. */
	List<PeriodoView> periodos = new ArrayList<PeriodoView>();

	/** The periodo seleccionado. */
	String periodoSeleccionado;

	/** The cuenta seleccionada. */
	String cuentaSeleccionada;

	/** The tipo seleccionado. */
	String tipoSeleccionado;

	/** The fecha desde. */
	String fechaDesde;

	/** The fecha hasta. */
	String fechaHasta;

	/**
	 * Instantiates a new filtros ordenes view.
	 */
	public FiltrosOrdenesView() {
		super();
	}

	/**
	 * Instantiates a new filtros ordenes view. con la lista de tipos y periodos
	 * cargada.
	 *
	 * @param filtroPrecargado
	 *            the filtro precargado
	 */
	public FiltrosOrdenesView(boolean filtroPrecargado) {
		super();
		if (filtroPrecargado) {
			cargarPeriodos();
			cargarTipos();
		}
	}

	/**
	 * Instantiates a new filtros ordenes view.
	 *
	 * @param id
	 *            the id
	 */
	public FiltrosOrdenesView(String id) {
		super(id);
		cargarPeriodos();
		cargarTipos();
	}

	/**
	 * Gets the tipos.
	 *
	 * @return the tipos
	 */
	public List<TipoView> getTipos() {
		return tipos;
	}

	/**
	 * Sets the tipos.
	 *
	 * @param tipos
	 *            the new tipos
	 */
	public void setTipos(List<TipoView> tipos) {
		this.tipos = tipos;
	}

	/**
	 * Gets the periodos.
	 *
	 * @return the periodos
	 */
	public List<PeriodoView> getPeriodos() {
		return periodos;
	}

	/**
	 * Sets the periodos.
	 *
	 * @param periodos
	 *            the new periodos
	 */
	public void setPeriodos(List<PeriodoView> periodos) {
		this.periodos = periodos;
	}

	/**
	 * Gets the periodo seleccionado.
	 *
	 * @return the periodo seleccionado
	 */
	public String getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	/**
	 * Sets the periodo seleccionado.
	 *
	 * @param periodoSeleccionado
	 *            the new periodo seleccionado
	 */
	public void setPeriodoSeleccionado(String periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the cuenta seleccionada.
	 *
	 * @return the cuenta seleccionada
	 */
	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * Sets the cuenta seleccionada.
	 *
	 * @param cuentaSeleccionada
	 *            the new cuenta seleccionada
	 */
	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	/**
	 * Gets the tipo seleccionado.
	 *
	 * @return the tipo seleccionado
	 */
	public String getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	/**
	 * Sets the tipo seleccionado.
	 *
	 * @param tipoSeleccionado
	 *            the new tipo seleccionado
	 */
	public void setTipoSeleccionado(String tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	/**
	 * Cargar periodos.
	 */
	private void cargarPeriodos() {
		PeriodoView cuarentaYCinco = new PeriodoView(CUARENTA_Y_CINCO, "45");
		PeriodoView sesenta = new PeriodoView(SESENTA, "60");
		periodos.add(cuarentaYCinco);
		periodos.add(sesenta);
	}

	/**
	 * Cargar tipos.
	 */
	private void cargarTipos() {
		TipoView operacionesEjecutadas = new TipoView(OPERACIONES, "E");
		TipoView ordenes = new TipoView(ORDENES, "");
		tipos.add(ordenes);
		tipos.add(operacionesEjecutadas);
	}

}
