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
 * The Class ComprobanteSuscripcionFondo.
 */
public class ComprobanteSuscripcionFondo extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteSuscripcionFondo.class);

	/** The titulo comprobante. */
	private String tituloComprobante;

	/** The fondo. */
	private String fondo;

	/** The cuenta titulo. */
	private String cuentaTitulo;

	/** The numero cuenta debito. */
	private String numeroCuentaDebito;

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The importe. */
	private String importe;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha actual. */
	private String fechaActual;

	/** The legales. */
	private String legales;

	/** The seuo. */
	private String seuo;

	/** The banco. */
	protected final String tituloComprobanteKey = "TITULO_COMPROBANTE";

	/** The banco. */
	protected final String fondoKey = "FONDO";

	/** The banco. */
	protected final String cuentaTituloKey = "CUENTA_TITULO";

	/** The banco. */
	protected final String numeroCuentaDebitoKey = "NUMERO_CUENTA_DEBITO";

	/** The tipo cuenta debito key. */
	protected final String tipoCuentaDebitoKey = "TIPO_CUENTA_DEBITO";

	/** The banco. */
	protected final String importeKey = "IMPORTE";

	/** The banco. */
	protected final String comprobanteKey = "NUMERO_COMPROBANTE";

	/** The banco. */
	protected final String fechaActualKey = "FECHA_ACTUAL";

	/** The banco. */
	protected final String legalesKey = "LEGALES";

	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";

	/** The seuo key. */
	protected final String seuoKey = "SEUO";

	/** The suscripcion jasper. */
	protected final String suscripcionJasper = "suscripcion-fondo.jasper";

	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";

	/** The path. */
	protected String pathInversiones = "inversiones/";

	/**
	 * Gets the titulo comprobante.
	 *
	 * @return the tituloComprobante
	 */
	public String getTituloComprobante() {
		return tituloComprobante;
	}

	/**
	 * Sets the titulo comprobante.
	 *
	 * @param tituloComprobante
	 *            the tituloComprobante to set
	 */
	public void setTituloComprobante(String tituloComprobante) {
		this.tituloComprobante = tituloComprobante;
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
	 * Gets the cuenta titulo.
	 *
	 * @return the cuentaTitulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the cuentaTitulo to set
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the numero cuenta debito.
	 *
	 * @return the numeroCuentaDebito
	 */
	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cuenta debito.
	 *
	 * @param numeroCuentaDebito
	 *            the numeroCuentaDebito to set
	 */
	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
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
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
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
	 * Gets the seuo.
	 *
	 * @return the seuo
	 */
	public String getSeuo() {
		return seuo;
	}

	/**
	 * Sets the seuo.
	 *
	 * @param seuo
	 *            the seuo to set
	 */
	public void setSeuo(String seuo) {
		this.seuo = seuo;
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
		parametros.put(tituloComprobanteKey, getTituloComprobante());
		parametros.put(fondoKey, getFondo());
		parametros.put(cuentaTituloKey, getCuentaTitulo());
		parametros.put(numeroCuentaDebitoKey, getNumeroCuentaDebito());
		parametros.put(tipoCuentaDebitoKey, getTipoCuentaDebito());
		parametros.put(importeKey, getImporte());
		parametros.put(comprobanteKey, getNumeroComprobante());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(legalesKey, getLegales());
		parametros.put(seuoKey, getSeuo());
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
		return ResourceUtils.getFile(path + pathInversiones + suscripcionJasper).getPath();
	}
}
