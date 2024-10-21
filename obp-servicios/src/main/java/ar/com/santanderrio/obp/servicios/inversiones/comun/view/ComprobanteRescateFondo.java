/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Class ComprobanteRescateFondo.
 */
public class ComprobanteRescateFondo extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteRescateFondo.class);

	/** The importe. */
	private String importe;

	/** The fondo. */
	private String fondo;

	/** The cuenta titulos. */
	private String cuentaTitulos;

	/** The cuenta destino. */
	private String cuentaDestino;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;

	/** The plazo pago. */
	private String plazoPago;

	/** The comprobante. */
	private String comprobante;

	/** The legales. */
	private String legales;

	/** The fecha actual. */
	private String fechaActual;

	/** The cuotapartes. */
	private String cuotapartes;
	
	/** The disclaimer. */
	private String disclaimer;
	
	private String destino;
	
	private String tituloRescate;

	private String fondoNuevo;

	/** The banco. */
	protected final String importeKey = "IMPORTE";

	/** The banco. */
	protected final String fondoKey = "FONDO";

	/** The banco. */
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";

	/** The banco. */
	protected final String cuentaDestinoKey = "CUENTA_DESTINO";

	/** The banco. */
	protected final String tipoCuentaDestinoKey = "TIPO_CUENTA_DESTINO";

	/** The banco. */
	protected final String plazoPagoKey = "PLAZO_PAGO";

	/** The banco. */
	protected final String comprobanteKey = "COMPROBANTE";

	/** The banco. */
	protected final String legalesKey = "LEGALES";

	/** The banco. */
	protected final String fechaActualKey = "FECHA_ACTUAL";

	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";

	/** The cuotapartes key. */
	protected final String cuotapartesKey = "CUOTAPARTES";
	
	/** The disclaimer key. */
	protected final String disclaimerKey = "DISCLAIMER";
	
	protected final String destinoKey = "DESTINO";
	
	protected final String tituloRescateKey = "TITULO_RESCATE";
	
	protected final String fondoNuevoKey = "FONDO_NUEVO";

	/** The rescate jasper. */
	protected final String rescateJasper = "rescate-fondo.jasper";

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
	 * Gets the fondo.
	 *
	 * @return the fondo
	 */
	public String getFondo() {
		return fondo;
	}

	/**
	 * Sets the fondo.
	 *
	 * @param fondo
	 *            the fondo to set
	 */
	public void setFondo(String fondo) {
		this.fondo = fondo;
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
	 * Gets the cuenta destino.
	 *
	 * @return the cuentaDestino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the cuentaDestino to set
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipoCuentaDestino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the tipoCuentaDestino to set
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the plazo pago.
	 *
	 * @return the plazoPago
	 */
	public String getPlazoPago() {
		return plazoPago;
	}

	/**
	 * Sets the plazo pago.
	 *
	 * @param plazoPago
	 *            the plazoPago to set
	 */
	public void setPlazoPago(String plazoPago) {
		this.plazoPago = plazoPago;
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
	 * Gets the cuotapartes.
	 *
	 * @return the cuotapartes
	 */
	public String getCuotapartes() {
		return cuotapartes;
	}

	/**
	 * Sets the cuotapartes.
	 *
	 * @param cuotapartes
	 *            the cuotapartes to set
	 */
	public void setCuotapartes(String cuotapartes) {
		this.cuotapartes = cuotapartes;
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

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getTituloRescate() {
		return tituloRescate;
	}

	public void setTituloRescate(String tituloRescate) {
		this.tituloRescate = tituloRescate;
	}

	public String getFondoNuevo() {
		return fondoNuevo;
	}

	public void setFondoNuevo(String fondoNuevo) {
		this.fondoNuevo = fondoNuevo;
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
		parametros.put(fondoKey, getFondo());
		parametros.put(cuentaTitulosKey, getCuentaTitulos());
		parametros.put(cuentaDestinoKey, getCuentaDestino());
		parametros.put(tipoCuentaDestinoKey, getTipoCuentaDestino());
		parametros.put(importeKey, getImporte());
		parametros.put(comprobanteKey, getComprobante());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(legalesKey, getLegales());
		parametros.put(plazoPagoKey, getPlazoPago());
		parametros.put(cuotapartesKey, getCuotapartes());
		parametros.put(disclaimerKey, getDisclaimer());
		parametros.put(destinoKey, getDestino());
		parametros.put(tituloRescateKey, getTituloRescate()); 
		parametros.put(fondoNuevoKey, getFondoNuevo());
		this.setTituloComprobante("Comprobante de Rescate a Fondos Comunes de Inversión");
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
		return ResourceUtils.getFile(path + pathInversiones + rescateJasper).getPath();
	}
}
