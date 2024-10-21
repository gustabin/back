/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Class ComprobanteNuevaTransferencia.
 */
public class ComprobanteNuevaTransferencia extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteNuevaTransferencia.class);
	
	/** The destinatario. */
	private String destinatario;

	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;
	
	/** The numero cuenta origen. */
	private String numeroCuentaOrigen;
	
	/** The tipo cuenta origen. */
	private String tipoCuentaOrigen;

	/** The importe. */
	private String importe;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha actual. */
	private String fechaActual;

	/** The tituloComprobanteKey. */
	protected final String tituloComprobanteKey = "TITULO_COMPROBANTE";

	/** The destinatarioKey. */
	protected final String destinatarioKey = "DESTINATARIO";

	/** The numeroCuentaDestinoKey. */
	protected final String numeroCuentaDestinoKey = "NUMERO_CUENTA_DESTINO";

	/** The tipo cuenta destino key. */
	protected final String tipoCuentaDestinoKey = "TIPO_CUENTA_DESTINO";

	/** The numeroCuentaOrigenKey. */
	protected final String numeroCuentaOrigenKey = "NUMERO_CUENTA_ORIGEN";

	/** The tipo cuenta origen key. */
	protected final String tipoCuentaOrigenKey = "TIPO_CUENTA_ORIGEN";

	/** The importeKey. */
	protected final String importeKey = "IMPORTE";

	/** The comprobanteKey. */
	protected final String comprobanteKey = "NUMERO_COMPROBANTE";

	/** The fechaActualKey. */
	protected final String fechaActualKey = "FECHA_ACTUAL";

	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";

	/** The suscripcion jasper. */
	protected final String suscripcionJasper = "nueva-transferencia.jasper";

	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";

	/** The path. */
	protected String pathInversiones = "inversiones/";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#getDestinatario()
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#setDestinatario(java.lang.String)
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numero cuenta destino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the new numero cuenta destino
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#getTipoCuentaDestino()
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#setTipoCuentaDestino(java.lang.String)
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the numero cuenta origen.
	 *
	 * @return the numero cuenta origen
	 */
	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	/**
	 * Sets the numero cuenta origen.
	 *
	 * @param numeroCuentaOrigen
	 *            the new numero cuenta origen
	 */
	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#getTipoCuentaOrigen()
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#setTipoCuentaOrigen(java.lang.String)
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
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
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the fecha actual.
	 *
	 * @return the fecha actual
	 */
	public String getFechaActual() {
		return fechaActual;
	}

	/**
	 * Sets the fecha actual.
	 *
	 * @param fechaActual
	 *            the new fecha actual
	 */
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * Gets the path inversiones.
	 *
	 * @return the path inversiones
	 */
	public String getPathInversiones() {
		return pathInversiones;
	}

	/**
	 * Sets the path inversiones.
	 *
	 * @param pathInversiones
	 *            the new path inversiones
	 */
	public void setPathInversiones(String pathInversiones) {
		this.pathInversiones = pathInversiones;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Gets the titulo comprobante key.
	 *
	 * @return the titulo comprobante key
	 */
	public String getTituloComprobanteKey() {
		return tituloComprobanteKey;
	}

	/**
	 * Gets the destinatario key.
	 *
	 * @return the destinatario key
	 */
	public String getDestinatarioKey() {
		return destinatarioKey;
	}

	/**
	 * Gets the numero cuenta destino key.
	 *
	 * @return the numero cuenta destino key
	 */
	public String getNumeroCuentaDestinoKey() {
		return numeroCuentaDestinoKey;
	}

	/**
	 * Gets the tipo cuenta destino key.
	 *
	 * @return the tipo cuenta destino key
	 */
	public String getTipoCuentaDestinoKey() {
		return tipoCuentaDestinoKey;
	}

	/**
	 * Gets the numero cuenta origen key.
	 *
	 * @return the numero cuenta origen key
	 */
	public String getNumeroCuentaOrigenKey() {
		return numeroCuentaOrigenKey;
	}

	/**
	 * Gets the tipo cuenta origen key.
	 *
	 * @return the tipo cuenta origen key
	 */
	public String getTipoCuentaOrigenKey() {
		return tipoCuentaOrigenKey;
	}

	/**
	 * Gets the importe key.
	 *
	 * @return the importe key
	 */
	public String getImporteKey() {
		return importeKey;
	}

	/**
	 * Gets the comprobante key.
	 *
	 * @return the comprobante key
	 */
	public String getComprobanteKey() {
		return comprobanteKey;
	}

	/**
	 * Gets the fecha actual key.
	 *
	 * @return the fecha actual key
	 */
	public String getFechaActualKey() {
		return fechaActualKey;
	}

	/**
	 * Gets the logo pie key.
	 *
	 * @return the logo pie key
	 */
	public String getLogoPieKey() {
		return logoPieKey;
	}

	/**
	 * Gets the suscripcion jasper.
	 *
	 * @return the suscripcion jasper
	 */
	public String getSuscripcionJasper() {
		return suscripcionJasper;
	}

	/**
	 * Gets the path logo pie.
	 *
	 * @return the path logo pie
	 */
	public String getPathLogoPie() {
		return pathLogoPie;
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
	 * @param parametros
	 *            the parametros
	 */
	protected void ponerParametros(HashMap<String, Object> parametros) {
		parametros.put(tituloComprobanteKey, getTituloComprobante());
		parametros.put(tituloComprobanteKey, getTituloComprobante());
		parametros.put(destinatarioKey, getDestinatario());
		parametros.put(numeroCuentaDestinoKey, getNumeroCuentaDestino());
		parametros.put(tipoCuentaDestinoKey, getTipoCuentaDestino());
		parametros.put(numeroCuentaOrigenKey, getNumeroCuentaOrigen());
		parametros.put(tipoCuentaOrigenKey, getTipoCuentaOrigen());
		parametros.put(importeKey, getImporte());
		parametros.put(comprobanteKey, getNumeroComprobante());
		parametros.put(fechaActualKey, getFechaActual());
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
		return ResourceUtils.getFile(path + pathInversiones + suscripcionJasper).getPath();
	}
	
}
