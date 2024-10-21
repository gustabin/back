package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

public class ComprobanteReversaLicitacionCanje extends DetalleComprobanteView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteReversaLicitacionCanje.class);

	private String nombreLicitacion;
	private String monedaEspecieOrigen;
	private String cuentaTitulos;
	private String tramo;
	private String cuentaOperativa;
	private String tipoCuentaOperativa;
	private String especieDestino;
	private String monedaEspecieDestino;
	private String valorNominalCanje;
	private String tipoPrecioLabel;
	private String tipoPrecioDato;
	private String importeADebitar;
	private String fechaHoraCierre;
	private String fechaAdjudicacion;
	private String fechaLiquidacion;
	private String numeroOrden;
	private String comisiones;
	private String impuestos;
	private String fechaActual;
	private String legalCanal;
	private String legalPliego;

	protected final String nombreLicitacionKey = "NOMBRE_LICITACION";
	protected final String monedaEspecieOrigenKey = "MONEDA_ESPECIE_ORIGEN";
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";
	protected final String tramoKey = "TRAMO";
	protected final String cuentaOperativaKey = "CUENTA_OPERATIVA";
	protected final String tipoCuentaOperativaKey = "TIPO_CUENTA_OPERATIVA";
	protected final String especieDestinoKey = "ESPECIE_DESTINO";
	protected final String monedaEspecieDestinoKey = "MONEDA_ESPECIE_DESTINO";
	protected final String valorNominalCanjeKey = "VALOR_NOMINAL_CANJEAR";
	protected final String tipoPrecioLabelKey = "TIPO_PRECIO_LABEL";
	protected final String tipoPrecioDatoKey = "TIPO_PRECIO_DATO";
	protected final String importeADebitarKey = "IMPORTE_A_DEBITAR";
	protected final String fechaHoraCierreKey = "FECHA_HORA_CIERRE";
	protected final String fechaAdjudicacionKey = "FECHA_ADJUDICACION";
	protected final String fechaLiquidacionKey = "FECHA_LIQUIDACION";
	protected final String numeroOrdenKey = "NUMERO_ORDEN";
	protected final String comisionesKey = "COMISIONES";
	protected final String impuestosKey = "IMPUESTOS";
	protected final String fechaActualKey = "FECHA_ACTUAL";
	protected final String legalCanalKey = "LEGAL_CANAL";
	protected final String legalPliegoKey = "LEGAL_PLIEGO";
	protected final String logoPieKey = "LOGO_PIE";
	protected final String reversaJasper = "reversa-orden-licitacion-canje.jasper";
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	protected final String pathInversiones = "inversiones/";

	public String getNombreLicitacion() {
		return nombreLicitacion;
	}

	public void setNombreLicitacion(String nombreLicitacion) {
		this.nombreLicitacion = nombreLicitacion;
	}

	public String getMonedaEspecieOrigen() {
		return monedaEspecieOrigen;
	}

	public void setMonedaEspecieOrigen(String monedaEspecieOrigen) {
		this.monedaEspecieOrigen = monedaEspecieOrigen;
	}

	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	public String getTramo() {
		return tramo;
	}

	public void setTramo(String tramo) {
		this.tramo = tramo;
	}

	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	public String getTipoCuentaOperativa() {
		return tipoCuentaOperativa;
	}

	public void setTipoCuentaOperativa(String tipoCuentaOperativa) {
		this.tipoCuentaOperativa = tipoCuentaOperativa;
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

	public String getValorNominalCanje() {
		return valorNominalCanje;
	}

	public void setValorNominalCanje(String valorNominalCanje) {
		this.valorNominalCanje = valorNominalCanje;
	}

	public String getTipoPrecioLabel() {
		return tipoPrecioLabel;
	}

	public void setTipoPrecioLabel(String tipoPrecioLabel) {
		this.tipoPrecioLabel = tipoPrecioLabel;
	}

	public String getTipoPrecioDato() {
		return tipoPrecioDato;
	}

	public void setTipoPrecioDato(String tipoPrecioDato) {
		this.tipoPrecioDato = tipoPrecioDato;
	}

	public String getImporteADebitar() {
		return importeADebitar;
	}

	public void setImporteADebitar(String importeADebitar) {
		this.importeADebitar = importeADebitar;
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

	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
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

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getLegalCanal() {
		return legalCanal;
	}

	public void setLegalCanal(String legalCanal) {
		this.legalCanal = legalCanal;
	}

	public String getLegalPliego() {
		return legalPliego;
	}

	public void setLegalPliego(String legalPliego) {
		this.legalPliego = legalPliego;
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
	 * @param parametros the parametros
	 */
	protected void ponerParametros(HashMap<String, Object> parametros) {
		parametros.put(nombreLicitacionKey, getNombreLicitacion());
		parametros.put(monedaEspecieOrigenKey, getMonedaEspecieOrigen());
		parametros.put(cuentaTitulosKey, getCuentaTitulos());
		parametros.put(tramoKey, getTramo());
		parametros.put(cuentaOperativaKey, getCuentaOperativa());
		parametros.put(tipoCuentaOperativaKey, getTipoCuentaOperativa());
		parametros.put(especieDestinoKey, getEspecieDestino());
		parametros.put(monedaEspecieDestinoKey, getMonedaEspecieDestino());
		parametros.put(valorNominalCanjeKey, getValorNominalCanje());
		parametros.put(tipoPrecioLabelKey, getTipoPrecioLabel());
		parametros.put(tipoPrecioDatoKey, getTipoPrecioDato());
		parametros.put(importeADebitarKey, getImporteADebitar());
		parametros.put(fechaHoraCierreKey, getFechaHoraCierre());
		parametros.put(fechaAdjudicacionKey, getFechaAdjudicacion());
		parametros.put(fechaLiquidacionKey, getFechaLiquidacion());
		parametros.put(numeroOrdenKey, getNumeroOrden());
		parametros.put(comisionesKey, getComisiones());
		parametros.put(impuestosKey, getImpuestos());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(legalCanalKey, getLegalCanal());
		parametros.put(legalPliegoKey, getLegalPliego());
		this.setTituloComprobante("Comprobante de pedido de reversa");
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
		return ResourceUtils.getFile(path + pathInversiones + reversaJasper).getPath();
	}

}
