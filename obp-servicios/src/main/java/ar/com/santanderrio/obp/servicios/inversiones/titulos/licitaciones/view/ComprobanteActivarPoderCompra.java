/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Class ComprobanteActivarPoderCompra.
 */
public class ComprobanteActivarPoderCompra extends DetalleComprobanteView {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteOrdenCompra.class);
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The cuenta debito. */
	private String cuentaDebito;
	
	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;
	
	/** The moneda. */
	private String moneda;
	
	/** The fecha solicitud. */
	private String fechaSolicitud;
	
	/** The fecha vigencia. */
	private String fechaVigencia;
	
	/** The comprobante. */
	private String comprobante;
	
	/** The legales. */
	private String legales;
	
	/** The fecha actual. */
	private String fechaActual;
	
	/** The cuenta titulos key. */
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";
	
	/** The cuenta debito key. */
	protected final String cuentaDebitoKey = "CUENTA_DEBITO";
	
	/** The tipo cuenta debito key. */
	protected final String tipoCuentaDebitoKey = "TIPO_CUENTA_DEBITO";
	
	/** The moneda key. */
	protected final String monedaKey = "MONEDA";
	
	/** The fecha solicitud key. */
	protected final String fechaSolicitudKey = "FECHA_DE_SOLICITUD";
	
	/** The fecha vigencia key. */
	protected final String fechaVigenciaKey= "FECHA_DE_VIGENCIA";
	
	/** The comprobante key. */
	protected final String comprobanteKey = "COMPROBANTE";
	
	/** The legales key. */
	protected final String legalesKey = "LEGALES";
	
	/** The fecha actual key. */
	protected final String fechaActualKey = "FECHA_ACTUAL";
	
	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";
	
	/** The suscripcion jasper. */
	protected final String suscripcionJasper = "activar-poder-compra.jasper";
	
	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	
	/** The path inversiones. */
	protected final String pathInversiones = "inversiones/";
	

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
	 * Gets the cuenta debito.
	 *
	 * @return the cuentaDebito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the cuentaDebito to set
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipoCuentaDebito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the tipoCuentaDebito to set
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
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
	 * Gets the logo pie key.
	 *
	 * @return the logoPieKey
	 */
	public String getLogoPieKey() {
		return logoPieKey;
	}

	/**
	 * Gets the suscripcion jasper.
	 *
	 * @return the suscripcionJasper
	 */
	public String getSuscripcionJasper() {
		return suscripcionJasper;
	}

	/**
	 * Gets the path logo pie.
	 *
	 * @return the pathLogoPie
	 */
	public String getPathLogoPie() {
		return pathLogoPie;
	}

	/**
	 * Gets the path inversiones.
	 *
	 * @return the pathInversiones
	 */
	public String getPathInversiones() {
		return pathInversiones;
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the fecha solicitud.
	 *
	 * @return the fecha solicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud
	 *            the new fecha solicitud
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Gets the fecha vigencia.
	 *
	 * @return the fecha vigencia
	 */
	public String getFechaVigencia() {
		return fechaVigencia;
	}

	/**
	 * Sets the fecha vigencia.
	 *
	 * @param fechaVigencia
	 *            the new fecha vigencia
	 */
	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
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
		try {
			parametros.put(cuentaTitulosKey, getCuentaTitulos());
			parametros.put(cuentaDebitoKey, getCuentaDebito());
			parametros.put(tipoCuentaDebitoKey, getTipoCuentaDebito());
			parametros.put(monedaKey, getMoneda());
			parametros.put(fechaSolicitudKey, getFechaSolicitud());
			parametros.put(fechaVigenciaKey, getFechaVigencia());
			parametros.put(comprobanteKey, getComprobante());
			parametros.put(legalesKey, getLegales());
			parametros.put(fechaActualKey, getFechaActual());
			this.setTituloComprobante("Activar poder de compra");
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
		return ResourceUtils.getFile(path + pathInversiones + suscripcionJasper).getPath();
	}


}
