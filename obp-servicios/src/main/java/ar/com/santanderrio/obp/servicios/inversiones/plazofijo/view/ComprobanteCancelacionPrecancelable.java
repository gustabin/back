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
 * The Class ComprobanteCancelacionPrecancelable.
 */
public class ComprobanteCancelacionPrecancelable extends DetalleComprobanteView{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteCancelacionPrecancelable.class);

	/** The capital invertido. */
	private String capitalInvertido;
	
	/** The total cobrado. */
	private String totalCobrado;
	
	/** The moneda. */
	private String moneda;
	
	/** The fecha vencimiento. */
	private String fechaVencimiento;
	
	/** The plazo. */
	private String plazo;
	
	/** The fecha precancelacion. */
	private String fechaPrecancelacion;
	
	/** The dias transcurridos. */
	private String diasTranscurridos;
	
	/** The porcentaje penalizacion. */
	private String porcentajePenalizacion;
	
	/** The interes. */
	private String interes;
	
	/** The intereses A cobrar. */
	private String interesesACobrar;
	
	/** The cuenta destino. */
	private String cuentaDestino;
	
	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;
	
	/** The fecha actual. */
	private String fechaActual;
	
	/** The legales. */
	private String legales;
	
	/** The capital invertido key. */
	protected final String capitalInvertidoKey = "CAPITAL_INVERTIDO";
	
	/** The total cobrado key. */
	protected final String totalCobradoKey = "TOTAL_COBRADO";
	
	/** The moneda key. */
	protected final String monedaKey = "MONEDA";
	
	/** The fecha vencimiento key. */
	protected final String fechaVencimientoKey = "FECHA_VENCIMIENTO";
	
	/** The plazo key. */
	protected final String plazoKey = "PLAZO";
	
	/** The fecha precancelacion key. */
	protected final String fechaPrecancelacionKey = "FECHA_PRECANCELACION";
	
	/** The dias transcurridos key. */
	protected final String diasTranscurridosKey = "DIAS_TRANSCURRIDOS";
	
	/** The porcentaje penalizacion key. */
	protected final String porcentajePenalizacionKey = "PORCENTAJE_PENALIZACION";
	
	/** The interes key. */
	protected final String interesKey = "INTERES";
	
	/** The intereses A cobrar key. */
	protected final String interesesACobrarKey = "INTERESES_A_COBRAR";
	
	/** The cuenta destino key. */
	protected final String cuentaDestinoKey = "CUENTA_DESTINO";
	
	/** The tipo cuenta destino key. */
	protected final String tipoCuentaDestinoKey = "TIPO_CUENTA_DESTINO";
	
	/** The fecha actual key. */
	protected final String fechaActualKey = "FECHA_ACTUAL";
	
	/** The legales key. */
	protected final String legalesKey = "LEGALES";
	
	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";
	
	/** The cancelar precancelable jasper. */
	protected final String cancelarPrecancelableJasper = "cancelar-precancelable.jasper";
	
	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	
	/** The path inversiones. */
	protected final String pathInversiones = "inversiones/";

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
	 * Gets the total cobrado.
	 *
	 * @return the totalCobrado
	 */
	public String getTotalCobrado() {
		return totalCobrado;
	}

	/**
	 * Sets the total cobrado.
	 *
	 * @param totalCobrado
	 *            the totalCobrado to set
	 */
	public void setTotalCobrado(String totalCobrado) {
		this.totalCobrado = totalCobrado;
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
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the fecha precancelacion.
	 *
	 * @return the fechaPrecancelacion
	 */
	public String getFechaPrecancelacion() {
		return fechaPrecancelacion;
	}

	/**
	 * Sets the fecha precancelacion.
	 *
	 * @param fechaPrecancelacion
	 *            the fechaPrecancelacion to set
	 */
	public void setFechaPrecancelacion(String fechaPrecancelacion) {
		this.fechaPrecancelacion = fechaPrecancelacion;
	}

	/**
	 * Gets the dias transcurridos.
	 *
	 * @return the diasTranscurridos
	 */
	public String getDiasTranscurridos() {
		return diasTranscurridos;
	}

	/**
	 * Sets the dias transcurridos.
	 *
	 * @param diasTranscurridos
	 *            the diasTranscurridos to set
	 */
	public void setDiasTranscurridos(String diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentajePenalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the porcentajePenalizacion to set
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the interes.
	 *
	 * @return the interes
	 */
	public String getInteres() {
		return interes;
	}

	/**
	 * Sets the interes.
	 *
	 * @param interes
	 *            the interes to set
	 */
	public void setInteres(String interes) {
		this.interes = interes;
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
	 * Gets the intereses A cobrar.
	 *
	 * @return the intereses A cobrar
	 */
	public String getInteresesACobrar() {
		return interesesACobrar;
	}

	/**
	 * Sets the intereses A cobrar.
	 *
	 * @param interesesACobrar
	 *            the new intereses A cobrar
	 */
	public void setInteresesACobrar(String interesesACobrar) {
		this.interesesACobrar = interesesACobrar;
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
		parametros.put(capitalInvertidoKey, getCapitalInvertido());
		parametros.put(totalCobradoKey, getTotalCobrado());
		parametros.put(monedaKey, getMoneda());
		parametros.put(fechaVencimientoKey, getFechaVencimiento());
		parametros.put(plazoKey, getPlazo());
		parametros.put(fechaPrecancelacionKey, getFechaPrecancelacion());
		parametros.put(diasTranscurridosKey, getDiasTranscurridos());
		parametros.put(capitalInvertidoKey, getCapitalInvertido());
		parametros.put(porcentajePenalizacionKey, getPorcentajePenalizacion());
		parametros.put(interesKey, getInteres());
		parametros.put(interesesACobrarKey, getInteresesACobrar());
		parametros.put(cuentaDestinoKey, getCuentaDestino());
		parametros.put(tipoCuentaDestinoKey, getTipoCuentaDestino());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(legalesKey, getLegales());
		this.setTituloComprobante("Comprobante de cancelación de Plazo Fijo Precancelable");
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
		return ResourceUtils.getFile(path + pathInversiones + cancelarPrecancelableJasper).getPath();
	}

}
