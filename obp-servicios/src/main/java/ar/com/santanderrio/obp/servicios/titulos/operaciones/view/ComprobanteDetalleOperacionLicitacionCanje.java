package ar.com.santanderrio.obp.servicios.titulos.operaciones.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

public class ComprobanteDetalleOperacionLicitacionCanje extends DetalleComprobanteView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteDetalleOperacionLicitacionCanje.class);

	private String descripcion;
	private String fecha;
	private String numero;
	private String estado;
	private String tipo;
	private String monedaEspecieOrigen;
	private String especieDestino;
	private String monedaEspecieDestino;
	private String valorNominalACanjear;
	private String tramo;
	private String tipoPrecioDato;
	private String tipoPrecio;
	private String cuentaTitulos;
	private String importeDebitar;
	private String cuentaDebito;
	private String tipoCuentaDebito;
	private String cantidadAdjudicada;
	private String precioAdjudicado;
	private String fechaDebito;
	private String fechaHoraCierre;
	private String fechaAdjudicacion;
	private String fechaLiquidacionTitulo;
	private String comisiones;
	private String impuestos;
	private String legal;

	protected final String descripcionKey = "DESCRIPCION";
	protected final String fechaKey = "FECHA";
	protected final String numeroKey = "NUMERO_OPERACION";
	protected final String estadoKey = "ESTADO";
	protected final String tipoKey = "TIPO";
	protected final String monedaEspecieOrigenKey = "MONEDA_ESPECIE_ORIGEN";
	protected final String especieDestinoKey = "ESPECIE_DESTINO";
	protected final String monedaEspecieDestinoKey = "MONEDA_ESPECIE_DESTINO";
	protected final String valorNominalACanjearKey = "VALOR_NOMINAL_A_CANJEAR";
	protected final String tramoKey = "TRAMO";
	protected final String tipoPrecioDatoKey = "TIPO_PRECIO_DATO";
	protected final String tipoPrecioKey = "TIPO_PRECIO";
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";
	protected final String cuentaDebitoKey = "CUENTA_OPERATIVA";
	protected final String tipoCuentaDebitoKey = "TIPO_CUENTA_OPERATIVA";
	protected final String cantidadAdjudicadaKey = "CANTIDAD_ADJUDICADA";
	protected final String precioAdjudicadoKey = "PRECIO_ADJUDICADO";
	protected final String importeDebitarKey = "IMPORTE_A_DEBITAR";
	protected final String fechaDebitoKey = "FECHA_DEBITO";
	protected final String fechaHoraCierreKey = "FECHA_HORA_CIERRE";
	protected final String fechaAdjudicacionKey = "FECHA_ADJUDICACION";
	protected final String fechaLiquidacionTituloKey = "FECHA_LIQUIDACION_TITULOS";
	protected final String comisionesKey = "COMISIONES";
	protected final String impuestosKey = "IMPUESTOS";
	protected final String legalKey = "LEGAL";
	protected final String logoPieKey = "LOGO_PIE";
	protected final String suscripcionJasper = "detalle-operacion-licitacion-canje.jasper";
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	protected final String pathInversiones = "inversiones/";

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMonedaEspecieOrigen() {
		return monedaEspecieOrigen;
	}

	public void setMonedaEspecieOrigen(String monedaEspecieOrigen) {
		this.monedaEspecieOrigen = monedaEspecieOrigen;
	}

	public String getEspecieDestino() {
		return especieDestino;
	}

	public void setEspecieDestino(String especieDestino) {
		this.especieDestino = especieDestino;
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

	public String getTramo() {
		return tramo;
	}

	public void setTramo(String tramo) {
		this.tramo = tramo;
	}

	public String getTipoPrecio() {
		return tipoPrecio;
	}

	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	public String getCuentaDebito() {
		return cuentaDebito;
	}

	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	public String getCantidadAdjudicada() {
		return cantidadAdjudicada;
	}

	public void setCantidadAdjudicada(String cantidadAdjudicada) {
		this.cantidadAdjudicada = cantidadAdjudicada;
	}

	public String getPrecioAdjudicado() {
		return precioAdjudicado;
	}

	public void setPrecioAdjudicado(String precioAdjudicado) {
		this.precioAdjudicado = precioAdjudicado;
	}

	public String getFechaHoraCierre() {
		return fechaHoraCierre;
	}

	public void setFechaHoraCierre(String fechaHoraCierre) {
		this.fechaHoraCierre = fechaHoraCierre;
	}

	public String getFechaAdjudicacion() {
		return fechaAdjudicacion;
	}

	public void setFechaAdjudicacion(String fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
	}

	public String getFechaLiquidacionTitulo() {
		return fechaLiquidacionTitulo;
	}

	public void setFechaLiquidacionTitulo(String fechaLiquidacionTitulo) {
		this.fechaLiquidacionTitulo = fechaLiquidacionTitulo;
	}

	public String getComisiones() {
		return comisiones;
	}

	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	public String getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getTipoPrecioDato() {
		return tipoPrecioDato;
	}

	public void setTipoPrecioDato(String tipoPrecioDato) {
		this.tipoPrecioDato = tipoPrecioDato;
	}

	public String getImporteDebitar() {
		return importeDebitar;
	}

	public void setImporteADebitar(String importeDebitar) {
		this.importeDebitar = importeDebitar;
	}

	public String getFechaDebito() {
		return fechaDebito;
	}

	public void setFechaDebito(String fechaDebito) {
		this.fechaDebito = fechaDebito;
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
		parametros.put(descripcionKey, getDescripcion());
		parametros.put(fechaKey, getFecha());
		parametros.put(numeroKey, getNumero());
		parametros.put(estadoKey, getEstado());
		parametros.put(tipoKey, getTipo());
		parametros.put(monedaEspecieOrigenKey, getMonedaEspecieOrigen());
		parametros.put(especieDestinoKey, getEspecieDestino());
		parametros.put(monedaEspecieDestinoKey, getMonedaEspecieDestino());
		parametros.put(valorNominalACanjearKey, getValorNominalACanjear());
		parametros.put(tramoKey, getTramo());
		parametros.put(tipoPrecioDatoKey, getTipoPrecioDato());
		parametros.put(tipoPrecioKey, getTipoPrecio());
		parametros.put(cuentaTitulosKey, getCuentaTitulos());
		parametros.put(cuentaDebitoKey, getCuentaDebito());
		parametros.put(tipoCuentaDebitoKey, getTipoCuentaDebito());
		parametros.put(cantidadAdjudicadaKey, getCantidadAdjudicada());
		parametros.put(precioAdjudicadoKey, getPrecioAdjudicado());
		parametros.put(importeDebitarKey, getImporteDebitar());
		parametros.put(fechaDebitoKey, getFechaDebito());
		parametros.put(fechaHoraCierreKey, getFechaHoraCierre());
		parametros.put(fechaAdjudicacionKey, getFechaAdjudicacion());
		parametros.put(fechaLiquidacionTituloKey, getFechaLiquidacionTitulo());
		parametros.put(comisionesKey, getComisiones());
		parametros.put(impuestosKey, getImpuestos());
		parametros.put(legalKey, getLegal());
		parametros.put(legalKey, getLegal());
		this.setTituloComprobante("Comprobante de operación de canje");
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
