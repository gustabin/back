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
 * The Class ComprobanteReversaOrdenCompraVenta.
 */
public class ComprobanteReversaOrdenCompraVenta extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteReversaOrdenCompraVenta.class);

	/** The tipo orden reversada. */
	private String tipoOrdenReversada;

	/** The nombre especie. */
	private String nombreEspecie;

	/** The fecha ingreso. */
	private String fechaIngreso;

	/** The numero orden. */
	private String numeroOrden;

	/** The accion. */
	private String accion;

	/** The tipo titulo. */
	private String tipoTitulo;

	/** The moneda operacion. */
	private String monedaOperacion;

	/** The plazo liquidacion. */
	private String plazoLiquidacion;

	/** The cantidad nominales. */
	private String cantidadNominales;

	/** The precio limite. */
	private String precioLimite;

	/** The precio referencia. */
	private String precioReferencia;

	/** The cuenta titulos. */
	private String cuentaTitulos;

	/** The cuenta destino. */
	private String cuentaDestino;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;

	/** The comprobante. */
	private String comprobante;

	/** The legales. */
	private String legales;

	/** The fecha actual. */
	private String fechaActual;
	
	/** The hora ingreso. */
	private String horaIngreso;
	
	/** The canal. */
	private String canal;
	
	/** The importe A reversar. */
	private String importeAReversar;

	/** The tipo orden reversada key. */
	protected final String tipoOrdenReversadaKey = "TIPO_ORDEN_REVERSADA";

	/** The nombre especie key. */
	protected final String nombreEspecieKey = "NOMBRE_ESPECIE";

	/** The fecha ingreso key. */
	protected final String fechaIngresoKey = "FECHA_INGRESO";

	/** The numero orden key. */
	protected final String numeroOrdenKey = "NUMERO_ORDEN";

	/** The accion key. */
	protected final String accionKey = "ACCION";

	/** The tipo titulo key. */
	protected final String tipoTituloKey = "TIPO_TITULO";

	/** The moneda operacion key. */
	protected final String monedaOperacionKey = "MONEDA_OPERACION";

	/** The plazo liquidacion key. */
	protected final String plazoLiquidacionKey = "PLAZO_LIQUIDACION";

	/** The cantidad nominales key. */
	protected final String cantidadNominalesKey = "CANTIDAD_NOMINALES";

	/** The precio limite key. */
	protected final String precioLimiteKey = "PRECIO_LIMITE";

	/** The precio referencia key. */
	protected final String precioReferenciaKey = "PRECIO_REFERENCIA";

	/** The cuenta titulos key. */
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";

	/** The cuenta destino key. */
	protected final String cuentaDestinoKey = "CUENTA_DESTINO";

	/** The tipo cuenta destino key. */
	protected final String tipoCuentaDestinoKey = "TIPO_CUENTA_DESTINO";

	/** The comprobante key. */
	protected final String comprobanteKey = "COMPROBANTE";

	/** The legales key. */
	protected final String legalesKey = "LEGALES";
	
	/** The hora ingreso key. */
	protected final String horaIngresoKey = "HORA_INGRESO";
	
	/** The canal key. */
	protected final String canalKey = "CANAL";
	
	/** The importe A reversar key. */
	protected final String importeAReversarKey = "IMPORTE_REVERSAR";

	/** The fecha actual key. */
	protected final String fechaActualKey = "FECHA_ACTUAL";

	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";

	/** The suscripcion jasper. */
	protected final String suscripcionJasper = "reversa-orden-compraventa.jasper";
	
	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	
	/** The path inversiones. */
	protected final String pathInversiones = "inversiones/";

	/**
	 * Gets the tipo orden reversada.
	 *
	 * @return the tipo orden reversada
	 */
	public String getTipoOrdenReversada() {
		return tipoOrdenReversada;
	}

	/**
	 * Sets the tipo orden reversada.
	 *
	 * @param tipoOrdenReversada
	 *            the new tipo orden reversada
	 */
	public void setTipoOrdenReversada(String tipoOrdenReversada) {
		this.tipoOrdenReversada = tipoOrdenReversada;
	}

	/**
	 * Gets the nombre especie.
	 *
	 * @return the nombre especie
	 */
	public String getNombreEspecie() {
		return nombreEspecie;
	}

	/**
	 * Sets the nombre especie.
	 *
	 * @param nombreEspecie
	 *            the new nombre especie
	 */
	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
	}

	/**
	 * Gets the fecha ingreso.
	 *
	 * @return the fecha ingreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * Sets the fecha ingreso.
	 *
	 * @param fechaIngreso
	 *            the new fecha ingreso
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Gets the tipo titulo.
	 *
	 * @return the tipo titulo
	 */
	public String getTipoTitulo() {
		return tipoTitulo;
	}

	/**
	 * Sets the tipo titulo.
	 *
	 * @param tipoTitulo
	 *            the new tipo titulo
	 */
	public void setTipoTitulo(String tipoTitulo) {
		this.tipoTitulo = tipoTitulo;
	}

	/**
	 * Gets the moneda operacion.
	 *
	 * @return the moneda operacion
	 */
	public String getMonedaOperacion() {
		return monedaOperacion;
	}

	/**
	 * Sets the moneda operacion.
	 *
	 * @param monedaOperacion
	 *            the new moneda operacion
	 */
	public void setMonedaOperacion(String monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}

	/**
	 * Gets the plazo liquidacion.
	 *
	 * @return the plazo liquidacion
	 */
	public String getPlazoLiquidacion() {
		return plazoLiquidacion;
	}

	/**
	 * Sets the plazo liquidacion.
	 *
	 * @param plazoLiquidacion
	 *            the new plazo liquidacion
	 */
	public void setPlazoLiquidacion(String plazoLiquidacion) {
		this.plazoLiquidacion = plazoLiquidacion;
	}

	/**
	 * Gets the cantidad nominales.
	 *
	 * @return the cantidad nominales
	 */
	public String getCantidadNominales() {
		return cantidadNominales;
	}

	/**
	 * Sets the cantidad nominales.
	 *
	 * @param cantidadNominales
	 *            the new cantidad nominales
	 */
	public void setCantidadNominales(String cantidadNominales) {
		this.cantidadNominales = cantidadNominales;
	}

	/**
	 * Gets the precio limite.
	 *
	 * @return the precio limite
	 */
	public String getPrecioLimite() {
		return precioLimite;
	}

	/**
	 * Sets the precio limite.
	 *
	 * @param precioLimite
	 *            the new precio limite
	 */
	public void setPrecioLimite(String precioLimite) {
		this.precioLimite = precioLimite;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precio referencia
	 */
	public String getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the new precio referencia
	 */
	public void setPrecioReferencia(String precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuenta titulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the new cuenta titulos
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuenta destino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the new cuenta destino
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
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
	 *            the new comprobante
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
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
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
	 * Gets the hora ingreso.
	 *
	 * @return the hora ingreso
	 */
	public String getHoraIngreso() {
		return horaIngreso;
	}

	/**
	 * Sets the hora ingreso.
	 *
	 * @param horaIngreso
	 *            the new hora ingreso
	 */
	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the importe A reversar.
	 *
	 * @return the importe A reversar
	 */
	public String getImporteAReversar() {
		return importeAReversar;
	}

	/**
	 * Sets the importe A reversar.
	 *
	 * @param importeaAReversar
	 *            the new importe A reversar
	 */
	public void setImporteAReversar(String importeaAReversar) {
		this.importeAReversar = importeaAReversar;
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
			parametros.put(tipoOrdenReversadaKey, getTipoOrdenReversada());
			parametros.put(nombreEspecieKey, getNombreEspecie());
			parametros.put(fechaIngresoKey, getFechaIngreso());
			parametros.put(numeroOrdenKey, getNumeroOrden());
			parametros.put(accionKey, getAccion());
			parametros.put(tipoTituloKey, getTipoTitulo());
			parametros.put(monedaOperacionKey, getMonedaOperacion());
			parametros.put(plazoLiquidacionKey, getPlazoLiquidacion());
			parametros.put(cantidadNominalesKey, getCantidadNominales());
			parametros.put(precioLimiteKey, getPrecioLimite());
			parametros.put(precioReferenciaKey, getPrecioReferencia());
			parametros.put(cuentaTitulosKey, getCuentaTitulos());
			parametros.put(cuentaDestinoKey, getCuentaDestino());
			parametros.put(tipoCuentaDestinoKey, getTipoCuentaDestino());
			parametros.put(comprobanteKey, getComprobante());
			parametros.put(legalesKey, getLegales());
			parametros.put(horaIngresoKey, getHoraIngreso());
			parametros.put(canalKey, getCanal());
			parametros.put(importeAReversarKey, getImporteAReversar());
			parametros.put(fechaActualKey, getFechaActual());
			this.setTituloComprobante("Comprobante de solicitud de reversa de orden de " + this.getTipoOrdenReversada());
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
