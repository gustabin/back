/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleOperacionLicitacionView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetalleOperacionLicitacionView extends OperacionTitulosView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The estado. */
	private String estado;
	
	/** The moneda licitacion. */
	private String monedaLicitacion;
	
	/** The tramo. */
	private String tramo;
	
	/** The importe debitar. */
	private String importeDebitar;
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The cuenta debito. */
	private String cuentaDebito;
	
	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;
	
	/** The cantidad adjudicada. */
	private String cantidadAdjudicada;
	
	/** The precio adjudicado. */
	private String precioAdjudicado;
	
	/** The fecha debito. */
	private String fechaDebito;
	
	/** The fecha hora cierre. */
	private String fechaHoraCierre;
	
	/** The fecha adjudicacion. */
	private String fechaAdjudicacion;
	
	/** The fecha liquidacion titulo. */
	private String fechaLiquidacionTitulo;
	
	/** The comisiones. */
	private String comisiones;
	
	/** The impuestos. */
	private String impuestos;
	
	/** The precio adjudicado label. */
	private String precioAdjudicadoLabel;
	
	/** The precio label. */
	private String precioLabel;
	
	/** The abreviatura caja valores. */
	private String abreviaturaCajaValores;
	
	private String monedaEspecieOrigen;
	
	private String monedaEspecieDestino;
	
	private String valorNominalACanjear;
	
	private String tipoPrecio;

	private String tipoPrecioDato;
	
	private String especieDestino;
	
	/** The consulta operacion licitacion. */
	protected final String consultaOperacionLicitacion = "consultaOperacionLicitacion.jasper";
	
	/** The tramo key. */
	protected final String tramoKey = "TRAMO";
	
	/** The cuenta debito numero key. */
	protected final String cuentaDebitoNumeroKey = "CUENTA_DEBITO_NUMERO";
	
	/** The cuenta debito tipo key. */
	protected final String cuentaDebitoTipoKey = "CUENTA_DEBITO_TIPO";
	
	/** The cantidad adjudicada key. */
	protected final String cantidadAdjudicadaKey = "CANTIDAD_ADJUDICADA";
	
	/** The precio label key. */
	protected final String precioLabelKey = "PRECIO_LABEL";
	
	/** The precio adjudicado label key. */
	protected final String precioAdjudicadoLabelKey = "PRECIO_ADJUDICADO_LABEL";
	
	/** The precio adjudicado key. */
	protected final String precioAdjudicadoKey = "PRECIO_ADJUDICADO";
	
	/** The fecha debito key. */
	protected final String fechaDebitoKey = "FECHA_DEBITO";
	
	/** The fecha hora cierre key. */
	protected final String fechaHoraCierreKey = "FECHA_HORA_CIERRE";
	
	/** The fecha adjudicacion key. */
	protected final String fechaAdjudicacionKey = "FECHA_ADJUDICACION";
	
	/** The fecha liquidacion key. */
	protected final String fechaLiquidacionKey = "FECHA_LIQUIDACION";
	
	/** The moneda licitacion key. */
	protected final String monedaLicitacionKey = "MONEDA_LICITACION";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#getEstado()
	 */
	public String getEstado() {
		return estado;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#setEstado(java.lang.String)
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the moneda licitacion.
	 *
	 * @return the moneda licitacion
	 */
	public String getMonedaLicitacion() {
		return monedaLicitacion;
	}

	/**
	 * Sets the moneda licitacion.
	 *
	 * @param monedaLicitacion
	 *            the new moneda licitacion
	 */
	public void setMonedaLicitacion(String monedaLicitacion) {
		this.monedaLicitacion = monedaLicitacion;
	}

	/**
	 * Gets the tramo.
	 *
	 * @return the tramo
	 */
	public String getTramo() {
		return tramo;
	}

	/**
	 * Sets the tramo.
	 *
	 * @param tramo
	 *            the new tramo
	 */
	public void setTramo(String tramo) {
		this.tramo = tramo;
	}

	/**
	 * Gets the importe debitar.
	 *
	 * @return the importe debitar
	 */
	public String getImporteDebitar() {
		return importeDebitar;
	}

	/**
	 * Sets the importe debitar.
	 *
	 * @param importeDebitar
	 *            the new importe debitar
	 */
	public void setImporteDebitar(String importeDebitar) {
		this.importeDebitar = importeDebitar;
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
	 * Gets the cuenta debito.
	 *
	 * @return the cuenta debito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the new cuenta debito
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the cantidad adjudicada.
	 *
	 * @return the cantidad adjudicada
	 */
	public String getCantidadAdjudicada() {
		return cantidadAdjudicada;
	}

	/**
	 * Sets the cantidad adjudicada.
	 *
	 * @param cantidadAdjudicada
	 *            the new cantidad adjudicada
	 */
	public void setCantidadAdjudicada(String cantidadAdjudicada) {
		this.cantidadAdjudicada = cantidadAdjudicada;
	}

	/**
	 * Gets the precio adjudicado.
	 *
	 * @return the precio adjudicado
	 */
	public String getPrecioAdjudicado() {
		return precioAdjudicado;
	}

	/**
	 * Sets the precio adjudicado.
	 *
	 * @param precioAdjudicado
	 *            the new precio adjudicado
	 */
	public void setPrecioAdjudicado(String precioAdjudicado) {
		this.precioAdjudicado = precioAdjudicado;
	}

	/**
	 * Gets the fecha debito.
	 *
	 * @return the fecha debito
	 */
	public String getFechaDebito() {
		return fechaDebito;
	}

	/**
	 * Sets the fecha debito.
	 *
	 * @param fechaDebito
	 *            the new fecha debito
	 */
	public void setFechaDebito(String fechaDebito) {
		this.fechaDebito = fechaDebito;
	}

	/**
	 * Gets the fecha hora cierre.
	 *
	 * @return the fecha hora cierre
	 */
	public String getFechaHoraCierre() {
		return fechaHoraCierre;
	}

	/**
	 * Sets the fecha hora cierre.
	 *
	 * @param fechaHoraCierre
	 *            the new fecha hora cierre
	 */
	public void setFechaHoraCierre(String fechaHoraCierre) {
		this.fechaHoraCierre = fechaHoraCierre;
	}

	/**
	 * Gets the fecha adjudicacion.
	 *
	 * @return the fecha adjudicacion
	 */
	public String getFechaAdjudicacion() {
		return fechaAdjudicacion;
	}

	/**
	 * Sets the fecha adjudicacion.
	 *
	 * @param fechaAdjudicacion
	 *            the new fecha adjudicacion
	 */
	public void setFechaAdjudicacion(String fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
	}

	/**
	 * Gets the fecha liquidacion titulo.
	 *
	 * @return the fecha liquidacion titulo
	 */
	public String getFechaLiquidacionTitulo() {
		return fechaLiquidacionTitulo;
	}

	/**
	 * Sets the fecha liquidacion titulo.
	 *
	 * @param fechaLiquidacionTitulo
	 *            the new fecha liquidacion titulo
	 */
	public void setFechaLiquidacionTitulo(String fechaLiquidacionTitulo) {
		this.fechaLiquidacionTitulo = fechaLiquidacionTitulo;
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
	 *            the new comisiones
	 */
	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the precio adjudicado label.
	 *
	 * @return the precio adjudicado label
	 */
	public String getPrecioAdjudicadoLabel() {
		return precioAdjudicadoLabel;
	}

	/**
	 * Sets the precio adjudicado label.
	 *
	 * @param precioAdjudicadoLabel
	 *            the new precio adjudicado label
	 */
	public void setPrecioAdjudicadoLabel(String precioAdjudicadoLabel) {
		this.precioAdjudicadoLabel = precioAdjudicadoLabel;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the precio label.
	 *
	 * @return the precio label
	 */
	public String getPrecioLabel() {
		return precioLabel;
	}

	/**
	 * Sets the precio label.
	 *
	 * @param precioLabel
	 *            the new precio label
	 */
	public void setPrecioLabel(String precioLabel) {
		this.precioLabel = precioLabel;
	}

	/**
	 * Gets the abreviatura caja valores.
	 *
	 * @return the abreviatura caja valores
	 */
	public String getAbreviaturaCajaValores() {
		return abreviaturaCajaValores;
	}

	/**
	 * Sets the abreviatura caja valores.
	 *
	 * @param abreviaturaCajaValores
	 *            the new abreviatura caja valores
	 */
	public void setAbreviaturaCajaValores(String abreviaturaCajaValores) {
		this.abreviaturaCajaValores = abreviaturaCajaValores;
	}

	
	public String getMonedaEspecieOrigen() {
		return monedaEspecieOrigen;
	}

	public void setMonedaEspecieOrigen(String monedaEspecieOrigen) {
		this.monedaEspecieOrigen = monedaEspecieOrigen;
	}

	public String getMonedaEspecieDestino() {
		return monedaEspecieDestino;
	}

	public void setMonedaEspecieDestino(String monedaEspecieDestino) {
		this.monedaEspecieDestino = monedaEspecieDestino;
	}
	
	public String getValorNominalACanjear() {
		return valorNominalACanjear;
	}

	public void setValorNominalACanjear(String valorNominalACanjear) {
		this.valorNominalACanjear = valorNominalACanjear;
	}
	
	public String getTipoPrecio() {
		return tipoPrecio;
	}

	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	public String getTipoPrecioDato() {
		return tipoPrecioDato;
	}

	public void setTipoPrecioDato(String tipoPrecioDato) {
		this.tipoPrecioDato = tipoPrecioDato;
	}
	
	public String getEspecieDestino() {
		return especieDestino;
	}

	public void setEspecieDestino(String especieDestino) {
		this.especieDestino = especieDestino;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerParametrosPDF()
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(descripcionKey, StringUtils.trim(getDescripcion()));
		parametros.put(fechaKey, getFecha());
		parametros.put(numeroOperacionKey, getNumero());
		parametros.put(estadoKey, getEstado());
		parametros.put(tipoEspecieKey, getTipo());
		parametros.put(monedaKey, getMoneda());
		parametros.put(monedaLicitacionKey, getMonedaLicitacion());
		parametros.put(cantidadNominalesKey, getCantidadNominales());
		parametros.put(tramoKey, getTramo());
		parametros.put(precioLabelKey, getPrecioLabel());
		parametros.put(precioKey, getPrecio());
		parametros.put(importeKey, getImporteDebitar());
		parametros.put(cuentaTitulosKey, getCuentaTitulos());
		parametros.put(cuentaDebitoNumeroKey, getCuentaDebito());
		parametros.put(cuentaDebitoTipoKey, getTipoCuentaDebito());
		parametros.put(cantidadAdjudicadaKey, getCantidadAdjudicada());
		parametros.put(precioAdjudicadoLabelKey, getPrecioAdjudicadoLabel());
		parametros.put(precioAdjudicadoKey, getPrecioAdjudicado());
		parametros.put(fechaDebitoKey, getFechaDebito());
		parametros.put(fechaHoraCierreKey, getFechaHoraCierre());
		parametros.put(fechaAdjudicacionKey, getFechaAdjudicacion());
		parametros.put(fechaLiquidacionKey, getFechaLiquidacionTitulo());
		parametros.put(comisionesKey, getComisiones());
		parametros.put(impuestosKey, getImpuestos());
		parametros.put(legalKey, getLegal());
		return parametros;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + pathInversiones + consultaOperacionLicitacion).getPath();
	}

}
