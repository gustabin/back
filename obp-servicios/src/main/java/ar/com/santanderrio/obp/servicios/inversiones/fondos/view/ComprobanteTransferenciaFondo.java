/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

	/**
 * The Class ComprobanteTransferenciaFondo.
	 */
public class ComprobanteTransferenciaFondo extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteTransferenciaFondo.class);
	
	/** The importe. */
	private String importe;
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The fondo origen. */
	private String fondoOrigen;
	
	/** The fondo destino. */
	private String fondoDestino;
	
	/** The comprobante. */
	private String comprobante;
	
	/** The fecha actual. */
	private String fechaActual;
	
	/** The cuenta label. */
	private String cuentaLabel;
	
	/** The legales. */
	private String legales;
	
	/** The disclaimer. */
	private String disclaimer;
	
	/** The codigo fondo. */
	private String codigoFondo;
	
	/** The codigo fondo dest. */
	private String codigoFondoDest;
	
	/** The banco. */
	protected final String importeKey = "IMPORTE";
	
	/** The banco. */
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";
	
	/** The banco. */
	protected final String fondoOrigenKey = "FONDO_ORIGEN";
	
	/** The banco. */
	protected final String fondoDestinoKey = "FONDO_DESTINO";
	
	/** The banco. */
	protected final String comprobanteKey = "COMPROBANTE";
	
	/** The banco. */
	protected final String fechaActualKey = "FECHA_ACTUAL";
	
	/** The banco. */
	protected final String legalesKey = "LEGALES";
	
	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";
	
	/** The disclaimer key. */
	protected final String disclaimerKey = "DISCLAIMER";
	
	/** The cuenta label key. */
	protected final String cuentaLabelKey = "CUENTA_LABEL";
	
	/** The rescate jasper. */
	protected final String transferenciaJasper = "transferencia-fondo.jasper";
	
	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	
	/** The path. */
	protected String pathInversiones = "inversiones/";

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuentaTitulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the cuentaTitulos to set
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the fondo origen.
	 *
	 * @return the fondoOrigen
	 */
	public String getFondoOrigen() {
		return fondoOrigen;
	}

	/**
	 * Sets the fondo origen.
	 *
	 * @param fondoOrigen
	 *            the fondoOrigen to set
	 */
	public void setFondoOrigen(String fondoOrigen) {
		this.fondoOrigen = fondoOrigen;
	}

	/**
	 * Gets the fondo destino.
	 *
	 * @return the fondoDestino
	 */
	public String getFondoDestino() {
		return fondoDestino;
	}

	/**
	 * Sets the fondo destino.
	 *
	 * @param fondoDestino
	 *            the fondoDestino to set
	 */
	public void setFondoDestino(String fondoDestino) {
		this.fondoDestino = fondoDestino;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
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

	
	/**
	 * Gets the disclaimer.
	 *
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return disclaimer;
	}

	/**
	 * Sets the disclaimer.
	 *
	 * @param disclaimer
	 *            the disclaimer to set
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	
	/**
	 * Gets the cuenta label.
	 *
	 * @return the cuentaLabel
	 */
	public String getCuentaLabel() {
		return cuentaLabel;
	}

	/**
	 * Sets the cuenta label.
	 *
	 * @param cuentaLabel
	 *            the cuentaLabel to set
	 */
	public void setCuentaLabel(String cuentaLabel) {
		this.cuentaLabel = cuentaLabel;
	}
	

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the codigo fondo dest.
	 *
	 * @return the codigoFondoDest
	 */
	public String getCodigoFondoDest() {
		return codigoFondoDest;
	}

	/**
	 * Sets the codigo fondo dest.
	 *
	 * @param codigoFondoDest
	 *            the codigoFondoDest to set
	 */
	public void setCodigoFondoDest(String codigoFondoDest) {
		this.codigoFondoDest = codigoFondoDest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerParametrosPDF()
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
		parametros.put(importeKey, getImporte());
		parametros.put(fondoDestinoKey, getFondoDestino());
		parametros.put(cuentaTitulosKey, getCuentaTitulos());
		parametros.put(fondoOrigenKey, getFondoOrigen());
		parametros.put(comprobanteKey, getComprobante());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(legalesKey, getLegales());
		parametros.put(disclaimerKey, " ");
		parametros.put(cuentaLabelKey, getCuentaLabel());
		this.setTituloComprobante("Comprobante de Transferencia entre Fondos Comunes de Inversión");
		try {
			parametros.put(logoPieKey, ResourceUtils.getFile(path + pathLogoPie).getPath());
		} catch (FileNotFoundException e) {
			LOGGER.error("No se encontro el archivo jasper", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + pathInversiones + transferenciaJasper).getPath();
	}
}
