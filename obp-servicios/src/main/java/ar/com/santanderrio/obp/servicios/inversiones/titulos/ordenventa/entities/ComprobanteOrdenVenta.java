/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Class ComprobanteOrdenVenta.
 */
public class ComprobanteOrdenVenta extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteOrdenVenta.class);

	/** The nombre especie. */
	private String nombreEspecie;

	/** The cuenta titulos. */
	private String cuentaTitulos;

	/** The tipo. */
	private String tipo;

	/** The plazo liquidacion. */
	private String plazoLiquidacion;

	/** The precio referencia. */
	private String precioReferencia;

	/** The fecha referencia. */
	private String fechaReferencia;

	/** The cantidad nominales. */
	private String cantidadNominales;

	/** The moneda liquidacion. */
	private String monedaLiquidacion;

	/** The fecha liquidacion. */
	private String fechaLiquidacion;

	/** The precio limite. */
	private String precioLimite;

	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The tipo de cuenta destino. */
	private String tipoDeCuentaDestino;

	/** The importe total. */
	private String importeTotal;

	/** The cuenta debito. */
	private String cuentaDebito;

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The importe debito. */
	private String importeDebito;

	/** The fecha debito. */
	private String fechaDebito;

	/** The importe poder compra. */
	private String importePoderCompra;

	/** The adhesion poder compra. */
	private String adhesionPoderCompra;

	/** The comisiones. */
	private String comisiones;

	/** The iva comision. */
	private String ivaComision;

	/** The derecho mercado. */
	private String derechoMercado;

	/** The iva derecho mercado. */
	private String ivaDerechoMercado;

	/** The numero orden. */
	private String numeroOrden;

	/** The comprobante. */
	private String comprobante;

	/** The legales. */
	private String legales;

	/** The fecha actual. */
	private String fechaActual;

	/** The nombre especie key. */
	protected final String nombreEspecieKey = "NOMBRE_ESPECIE";

	/** The codigo especie key. */
	protected final String codigoEspecieKey = "CODIGO_ESPECIE";

	/** The cuenta titulos key. */
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";

	/** The tipo key. */
	protected final String tipoKey = "TIPO";

	/** The plazo liquidacion key. */
	protected final String plazoLiquidacionKey = "PLAZO_LIQUIDACION";

	/** The precio referencia key. */
	protected final String precioReferenciaKey = "PRECIO_REFERENCIA";

	/** The fecha referencia key. */
	protected final String fechaReferenciaKey = "FECHA_REFERENCIA";

	/** The cantidad nominales key. */
	protected final String cantidadNominalesKey = "CANTIDAD_NOMINALES";

	/** The moneda liquidacion key. */
	protected final String monedaLiquidacionKey = "MONEDA_LIQUIDACION";

	/** The fecha liquidacion key. */
	protected final String fechaLiquidacionKey = "FECHA_LIQUIDACION";

	/** The precio limite key. */
	protected final String precioLimiteKey = "PRECIO_LIMITE";

	/** The numero cuenta destino key. */
	protected final String numeroCuentaDestinoKey = "NUMERO_CUENTA_DESTINO";

	/** The tipo de cuenta destino key. */
	protected final String tipoDeCuentaDestinoKey = "TIPO_CUENTA_DESTINO";

	/** The importe total key. */
	protected final String importeTotalKey = "IMPORTE_TOTAL";

	/** The cuenta debito key. */
	protected final String cuentaDebitoKey = "CUENTA_DEBITO";

	/** The tipo cuenta debito key. */
	protected final String tipoCuentaDebitoKey = "TIPO_CUENTA_DEBITO";

	/** The importe debito key. */
	protected final String importeDebitoKey = "IMPORTE_DEBITO";

	/** The fecha debito key. */
	protected final String fechaDebitoKey = "FECHA_DEBITO";

	/** The importe poder compra key. */
	protected final String importePoderCompraKey = "IMPORTE_PODER_COMPRA";

	/** The adhesion poder compra key. */
	protected final String adhesionPoderCompraKey = "ADHESION_PODER_COMPRA";

	/** The comisiones key. */
	protected final String comisionesKey = "COMISIONES";

	/** The iva comision key. */
	protected final String ivaComisionKey = "IVA_COMISION";

	/** The derecho mercado key. */
	protected final String derechoMercadoKey = "DERECHO_MERCADO";

	/** The iva derecho mercado key. */
	protected final String ivaDerechoMercadoKey = "IVA_DERECHO_MERCADO";

	/** The numero orden key. */
	protected final String numeroOrdenKey = "NUMERO_ORDEN";

	/** The comprobante key. */
	protected final String comprobanteKey = "COMPROBANTE";

	/** The legales key. */
	protected final String legalesKey = "LEGALES";

	/** The fecha actual key. */
	protected final String fechaActualKey = "FECHA_ACTUAL";

	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";

	/** The suscripcion jasper. */
	protected final String suscripcionJasper = "orden-venta.jasper";

	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";

	/** The path inversiones. */
	protected final String pathInversiones = "inversiones/";

	/**
	 * Gets the nombre especie.
	 *
	 * @return the nombreEspecie
	 */
	public String getNombreEspecie() {
		return nombreEspecie;
	}

	/**
	 * Sets the nombre especie.
	 *
	 * @param nombreEspecie
	 *            the nombreEspecie to set
	 */
	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
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
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the plazo liquidacion.
	 *
	 * @return the plazoLiquidacion
	 */
	public String getPlazoLiquidacion() {
		return plazoLiquidacion;
	}

	/**
	 * Sets the plazo liquidacion.
	 *
	 * @param plazoLiquidacion
	 *            the plazoLiquidacion to set
	 */
	public void setPlazoLiquidacion(String plazoLiquidacion) {
		this.plazoLiquidacion = plazoLiquidacion;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precioReferencia
	 */
	public String getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the precioReferencia to set
	 */
	public void setPrecioReferencia(String precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Gets the fecha referencia.
	 *
	 * @return the fechaReferencia
	 */
	public String getFechaReferencia() {
		return fechaReferencia;
	}

	/**
	 * Sets the fecha referencia.
	 *
	 * @param fechaReferencia
	 *            the fechaReferencia to set
	 */
	public void setFechaReferencia(String fechaReferencia) {
		this.fechaReferencia = fechaReferencia;
	}

	/**
	 * Gets the cantidad nominales.
	 *
	 * @return the cantidadNominales
	 */
	public String getCantidadNominales() {
		return cantidadNominales;
	}

	/**
	 * Sets the cantidad nominales.
	 *
	 * @param cantidadNominales
	 *            the cantidadNominales to set
	 */
	public void setCantidadNominales(String cantidadNominales) {
		this.cantidadNominales = cantidadNominales;
	}

	/**
	 * Gets the moneda liquidacion.
	 *
	 * @return the monedaLiquidacion
	 */
	public String getMonedaLiquidacion() {
		return monedaLiquidacion;
	}

	/**
	 * Sets the moneda liquidacion.
	 *
	 * @param monedaLiquidacion
	 *            the monedaLiquidacion to set
	 */
	public void setMonedaLiquidacion(String monedaLiquidacion) {
		this.monedaLiquidacion = monedaLiquidacion;
	}

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fechaLiquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the fechaLiquidacion to set
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the precio limite.
	 *
	 * @return the precioLimite
	 */
	public String getPrecioLimite() {
		return precioLimite;
	}

	/**
	 * Sets the precio limite.
	 *
	 * @param precioLimite
	 *            the precioLimite to set
	 */
	public void setPrecioLimite(String precioLimite) {
		this.precioLimite = precioLimite;
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
	 * Gets the importe debito.
	 *
	 * @return the importeDebito
	 */
	public String getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the importeDebito to set
	 */
	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
	}

	/**
	 * Gets the fecha debito.
	 *
	 * @return the fechaDebito
	 */
	public String getFechaDebito() {
		return fechaDebito;
	}

	/**
	 * Sets the fecha debito.
	 *
	 * @param fechaDebito
	 *            the fechaDebito to set
	 */
	public void setFechaDebito(String fechaDebito) {
		this.fechaDebito = fechaDebito;
	}

	/**
	 * Gets the importe poder compra.
	 *
	 * @return the importePoderCompra
	 */
	public String getImportePoderCompra() {
		return importePoderCompra;
	}

	/**
	 * Sets the importe poder compra.
	 *
	 * @param importePoderCompra
	 *            the importePoderCompra to set
	 */
	public void setImportePoderCompra(String importePoderCompra) {
		this.importePoderCompra = importePoderCompra;
	}

	/**
	 * Gets the comisiones.
	 *
	 * @return the comisiones
	 */
	public String getComisiones() {
		return comisiones;
	}

	/**
	 * Sets the comisiones.
	 *
	 * @param comisiones
	 *            the comisiones to set
	 */
	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	/**
	 * Gets the iva comision.
	 *
	 * @return the ivaComision
	 */
	public String getIvaComision() {
		return ivaComision;
	}

	/**
	 * Sets the iva comision.
	 *
	 * @param ivaComision
	 *            the ivaComision to set
	 */
	public void setIvaComision(String ivaComision) {
		this.ivaComision = ivaComision;
	}

	/**
	 * Gets the derecho mercado.
	 *
	 * @return the derechoMercado
	 */
	public String getDerechoMercado() {
		return derechoMercado;
	}

	/**
	 * Sets the derecho mercado.
	 *
	 * @param derechoMercado
	 *            the derechoMercado to set
	 */
	public void setDerechoMercado(String derechoMercado) {
		this.derechoMercado = derechoMercado;
	}

	/**
	 * Gets the iva derecho mercado.
	 *
	 * @return the ivaDerechoMercado
	 */
	public String getIvaDerechoMercado() {
		return ivaDerechoMercado;
	}

	/**
	 * Sets the iva derecho mercado.
	 *
	 * @param ivaDerechoMercado
	 *            the ivaDerechoMercado to set
	 */
	public void setIvaDerechoMercado(String ivaDerechoMercado) {
		this.ivaDerechoMercado = ivaDerechoMercado;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numeroOrden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the numeroOrden to set
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
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

	/**
	 * Gets the tipo de cuenta destino.
	 *
	 * @return the tipo de cuenta destino
	 */
	public String getTipoDeCuentaDestino() {
		return tipoDeCuentaDestino;
	}

	/**
	 * Sets the tipo de cuenta destino.
	 *
	 * @param tipoDeCuentaDestino
	 *            the new tipo de cuenta destino
	 */
	public void setTipoDeCuentaDestino(String tipoDeCuentaDestino) {
		this.tipoDeCuentaDestino = tipoDeCuentaDestino;
	}

	/**
	 * Gets the adhesion poder compra.
	 *
	 * @return the adhesion poder compra
	 */
	public String getAdhesionPoderCompra() {
		return adhesionPoderCompra;
	}

	/**
	 * Sets the adhesion poder compra.
	 *
	 * @param adhesionPoderCompra
	 *            the new adhesion poder compra
	 */
	public void setAdhesionPoderCompra(String adhesionPoderCompra) {
		this.adhesionPoderCompra = adhesionPoderCompra;
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
		// parametros.put(nombreLicitacionKey, getNombreLicitacion());
		try {
			parametros.put(nombreEspecieKey, getNombreEspecie());
			parametros.put(cuentaTitulosKey, getCuentaTitulos());
			parametros.put(tipoKey, getTipo());
			parametros.put(plazoLiquidacionKey, getPlazoLiquidacion());
			parametros.put(precioReferenciaKey, getPrecioReferencia());
			parametros.put(fechaReferenciaKey, getFechaReferencia());
			parametros.put(cantidadNominalesKey, getCantidadNominales());
			parametros.put(monedaLiquidacionKey, getMonedaLiquidacion());
			parametros.put(fechaLiquidacionKey, getFechaLiquidacion());
			parametros.put(precioLimiteKey, getPrecioLimite());
			parametros.put(numeroCuentaDestinoKey, getNumeroCuentaDestino());
			parametros.put(tipoDeCuentaDestinoKey, getTipoDeCuentaDestino());
			parametros.put(importeTotalKey, getImporteTotal());
			parametros.put(cuentaDebitoKey, getCuentaDebito());
			parametros.put(tipoCuentaDebitoKey, getTipoCuentaDebito());
			parametros.put(importeDebitoKey, getImporteDebito());
			parametros.put(fechaDebitoKey, getFechaDebito());
			parametros.put(importePoderCompraKey, getImportePoderCompra());
			parametros.put(adhesionPoderCompraKey, getAdhesionPoderCompra());
			parametros.put(comisionesKey, getComisiones());
			parametros.put(ivaComisionKey, getIvaComision());
			parametros.put(derechoMercadoKey, getDerechoMercado());
			parametros.put(ivaDerechoMercadoKey, getIvaDerechoMercado());
			parametros.put(numeroOrdenKey, getNumeroOrden());
			parametros.put(comprobanteKey, getComprobante());
			parametros.put(legalesKey, getLegales());
			parametros.put(fechaActualKey, getFechaActual());

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
