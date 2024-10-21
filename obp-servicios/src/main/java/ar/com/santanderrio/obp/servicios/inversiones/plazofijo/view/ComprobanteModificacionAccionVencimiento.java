/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Class ComprobanteModificacionAccionVencimiento.
 */
public class ComprobanteModificacionAccionVencimiento extends DetalleComprobanteView{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteModificacionAccionVencimiento.class);
	
	/** The tipo plazo fijo. */
	private String tipoPlazoFijo;
	
	/** The moneda. */
	private String moneda;
	
	/** The fecha vencimiento. */
	private String fechaVencimiento;
	
	/** The capital invertido. */
	private String capitalInvertido;
	
	/** The intereses netos. */
	private String interesesNetos;
	
	/** The importe total. */
	private String importeTotal;
	
	/** The accion al vencimiento. */
	private String accionAlVencimiento;
	
	/** The fecha actual. */
	private String fechaActual;
	
	/** The legales. */
	private String legales;
	
	/** The tipo plazo fijo key. */
	protected final String tipoPlazoFijoKey = "TIPO_PLAZO_FIJO";
	
	/** The moneda key. */
	protected final String monedaKey = "MONEDA";
	
	/** The fecha vencimiento key. */
	protected final String fechaVencimientoKey = "FECHA_VENCIMIENTO";
	
	/** The capital invertido key. */
	protected final String capitalInvertidoKey = "CAPITAL_INVERTIDO";

	/** The intereses netos key. */
	protected final String interesesNetosKey = "INTERESES_NETOS";
	
	/** The importe total key. */
	protected final String importeTotalKey = "IMPORTE_TOTAL";
	
	/** The accion al vencimiento key. */
	protected final String accionAlVencimientoKey = "ACCION_AL_VENCIMIENTO";
	
	/** The fecha actual key. */
	protected final String fechaActualKey = "FECHA_ACTUAL";
	
	/** The legales key. */
	protected final String  legalesKey = "LEGALES";
	
	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";
	
	/** The modificacion accion jasper. */
	protected final String modificacionAccionJasper = "modificacion-accion-vencimiento.jasper";
	
	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	
	/** The path. */
	protected final String pathInversiones = "inversiones/";

	/**
	 * Gets the tipo plazo fijo.
	 *
	 * @return the tipoPlazoFijo
	 */
	public String getTipoPlazoFijo() {
		return tipoPlazoFijo;
	}

	/**
	 * Sets the tipo plazo fijo.
	 *
	 * @param tipoPlazoFijo
	 *            the tipoPlazoFijo to set
	 */
	public void setTipoPlazoFijo(String tipoPlazoFijo) {
		this.tipoPlazoFijo = tipoPlazoFijo;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the capital invertido.
	 *
	 * @return the capitalInvertido
	 */
	public String getCapitalInvertido() {
		return capitalInvertido;
	}

	/**
	 * Sets the capital invertido.
	 *
	 * @param capitalInvertido
	 *            the capitalInvertido to set
	 */
	public void setCapitalInvertido(String capitalInvertido) {
		this.capitalInvertido = capitalInvertido;
	}

	/**
	 * Gets the intereses netos.
	 *
	 * @return the interesesNetos
	 */
	public String getInteresesNetos() {
		return interesesNetos;
	}

	/**
	 * Sets the intereses netos.
	 *
	 * @param interesesNetos
	 *            the interesesNetos to set
	 */
	public void setInteresesNetos(String interesesNetos) {
		this.interesesNetos = interesesNetos;
	}

	/**
	 * Gets the importe total.
	 *
	 * @return the importeTotal
	 */
	public String getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 *
	 * @param importeTotal
	 *            the importeTotal to set
	 */
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Gets the accion al vencimiento.
	 *
	 * @return the accionAlVencimiento
	 */
	public String getAccionAlVencimiento() {
		return accionAlVencimiento;
	}

	/**
	 * Sets the accion al vencimiento.
	 *
	 * @param accionAlVencimiento
	 *            the accionAlVencimiento to set
	 */
	public void setAccionAlVencimiento(String accionAlVencimiento) {
		this.accionAlVencimiento = accionAlVencimiento;
	}

	/**
	 * Gets the fecha actual.
	 *
	 * @return the fechaActual
	 */
	public String getFechaActual() {
		return fechaActual;
	}

	/**
	 * Sets the fecha actual.
	 *
	 * @param fechaActual
	 *            the fechaActual to set
	 */
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerParametrosPDF()
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		ponerParametros(parametros);
		return parametros;
	}

	/**
	 * Poner parametros.
	 *
	 * @param parametros
	 *            the parametros
	 */
	protected void ponerParametros(HashMap<String, Object> parametros) {
		parametros.put(tipoPlazoFijoKey, getTipoPlazoFijo());
		parametros.put(monedaKey, getMoneda());
		parametros.put(fechaVencimientoKey, getFechaVencimiento());
		parametros.put(capitalInvertidoKey, getCapitalInvertido());
		parametros.put(interesesNetosKey, getInteresesNetos());
		parametros.put(importeTotalKey, getImporteTotal());
		parametros.put(accionAlVencimientoKey, getAccionAlVencimiento());
		parametros.put(legalesKey, getLegales());
		parametros.put(fechaActualKey, getFechaActual());
		this.setTituloComprobante("Comprobante de modificación de acción al vencimiento");

		try {
			parametros.put(logoPieKey, ResourceUtils.getFile(path + pathLogoPie).getPath());
		} catch (FileNotFoundException e) {
			LOGGER.error("No se encontro el archivo jasper", e);
		}

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + pathInversiones + modificacionAccionJasper).getPath();
	}
}
