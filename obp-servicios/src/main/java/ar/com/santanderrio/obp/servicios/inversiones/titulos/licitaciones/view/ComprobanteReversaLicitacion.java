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
 * The Class ComprobanteReversaLicitacion.
 */
public class ComprobanteReversaLicitacion extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteReversaLicitacion.class);
	
	/** The moneda. */
	private String moneda;
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The tramo. */
	private String tramo;
	
	/** The moneda licitacion. */
	private String monedaLicitacion;
	
	/** The cuenta debito. */
	private String cuentaDebito;
	
	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;
	
	/** The valor nominal. */
	private String valorNominal;
	
	/** The precio label. */
	private String precioLabel;
	
	/** The precio tasa. */
	private String precioTasa;
	
	/** The importe A reversar. */
	private String importeAReversar;
	
	/** The fecha hora. */
	private String fechaHora;
	
	/** The fecha adjudicacion. */
	private String fechaAdjudicacion;
	
	/** The fecha liquidacion. */
	private String fechaLiquidacion;
	
	/** The numero orden. */
	private String numeroOrden;
	
	/** The comisiones. */
	private String comisiones;
	
	/** The impuestos. */
	private String impuestos;
	
	/** The legal canal. */
	private String legalCanal;
	
	/** The legal pliegue. */
	private String legalPliegue;
	
	/** The fecha actual. */
	private String fechaActual;
	
	/** The nombre licitacion. */
	private String nombreLicitacion;
	
	/** The importe label. */
	private String importeLabel;
	
	/** The moneda key. */
	protected final String monedaKey = "MONEDA";
	
	/** The cuenta titulos key. */
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";
	
	/** The tramo key. */
	protected final String tramoKey = "TRAMO";
	
	/** The moneda licitacion key. */
	protected final String monedaLicitacionKey = "MONEDA_LICITACION";
	
	/** The cuenta debito key. */
	protected final String cuentaDebitoKey = "CUENTA_DEBITO";
	
	/** The tipo cuenta debito key. */
	protected final String tipoCuentaDebitoKey = "TIPO_CUENTA_DEBITO";
	
	/** The valor nominal key. */
	protected final String valorNominalKey = "VALOR_NOMINAL";
	
	/** The precio label key. */
	protected final String precioLabelKey = "PRECIO_LABEL";
	
	/** The precio tasa key. */
	protected final String precioTasaKey = "PRECIO_TASA";
	
	/** The importe A reversar key. */
	protected final String importeAReversarKey = "IMPORTE_REVERSAR";
	
	/** The fecha hora key. */
	protected final String fechaHoraKey = "FECHA_HORA";
	
	/** The fecha adjudicacion key. */
	protected final String fechaAdjudicacionKey = "FECHA_ADJUDICACION";
	
	/** The fecha liquidacion key. */
	protected final String fechaLiquidacionKey = "FECHA_LIQUIDACION";
	
	/** The numero orden key. */
	protected final String numeroOrdenKey = "NUMERO_ORDEN";
	
	/** The comisiones key. */
	protected final String comisionesKey = "COMISIONES";
	
	/** The impuestos key. */
	protected final String impuestosKey ="IMPUESTOS";
	
	/** The nombre licitacion key. */
	protected final String nombreLicitacionKey = "NOMBRE_LICITACION";
	
	/** The legal canal key. */
	protected final String legalCanalKey = "LEGAL_CANAL";
	
	/** The legal pliegue key. */
	protected final String legalPliegueKey = "LEGAL_PLIEGUE";
	
	/** The importe label key. */
	protected final String importeLabelKey = "IMPORTE_LABLE";
	
	/** The fecha actual key. */
	protected final String fechaActualKey = "FECHA_ACTUAL";
	
	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";
	
	/** The suscripcion jasper. */
	protected final String reversaJasper = "reversa-licitacion.jasper";
	
	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	
	/** The path. */
	protected final String pathInversiones = "inversiones/";
	
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
	 *            the tramo to set
	 */
	public void setTramo(String tramo) {
		this.tramo = tramo;
	}

	/**
	 * Gets the moneda licitacion.
	 *
	 * @return the monedaLicitacion
	 */
	public String getMonedaLicitacion() {
		return monedaLicitacion;
	}

	/**
	 * Sets the moneda licitacion.
	 *
	 * @param monedaLicitacion
	 *            the monedaLicitacion to set
	 */
	public void setMonedaLicitacion(String monedaLicitacion) {
		this.monedaLicitacion = monedaLicitacion;
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
	 * Gets the valor nominal.
	 *
	 * @return the valorNominal
	 */
	public String getValorNominal() {
		return valorNominal;
	}

	/**
	 * Sets the valor nominal.
	 *
	 * @param valorNominal
	 *            the valorNominal to set
	 */
	public void setValorNominal(String valorNominal) {
		this.valorNominal = valorNominal;
	}

	/**
	 * Gets the precio label.
	 *
	 * @return the precioLabel
	 */
	public String getPrecioLabel() {
		return precioLabel;
	}

	/**
	 * Sets the precio label.
	 *
	 * @param precioLabel
	 *            the precioLabel to set
	 */
	public void setPrecioLabel(String precioLabel) {
		this.precioLabel = precioLabel;
	}

	/**
	 * Gets the precio tasa.
	 *
	 * @return the precioTasa
	 */
	public String getPrecioTasa() {
		return precioTasa;
	}

	/**
	 * Sets the precio tasa.
	 *
	 * @param precioTasa
	 *            the precioTasa to set
	 */
	public void setPrecioTasa(String precioTasa) {
		this.precioTasa = precioTasa;
	}

	/**
	 * Gets the importe A reversar.
	 *
	 * @return the importeAReversar
	 */
	public String getImporteAReversar() {
		return importeAReversar;
	}

	/**
	 * Sets the importe A reversar.
	 *
	 * @param importeAReversar
	 *            the importeAReversar to set
	 */
	public void setImporteAReversar(String importeAReversar) {
		this.importeAReversar = importeAReversar;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the fecha adjudicacion.
	 *
	 * @return the fechaAdjudicacion
	 */
	public String getFechaAdjudicacion() {
		return fechaAdjudicacion;
	}

	/**
	 * Sets the fecha adjudicacion.
	 *
	 * @param fechaAdjudicacion
	 *            the fechaAdjudicacion to set
	 */
	public void setFechaAdjudicacion(String fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
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
	 *            the impuestos to set
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the legal canal.
	 *
	 * @return the legalCanal
	 */
	public String getLegalCanal() {
		return legalCanal;
	}

	/**
	 * Sets the legal canal.
	 *
	 * @param legalCanal
	 *            the legalCanal to set
	 */
	public void setLegalCanal(String legalCanal) {
		this.legalCanal = legalCanal;
	}

	/**
	 * Gets the legal pliegue.
	 *
	 * @return the legalPliegue
	 */
	public String getLegalPliegue() {
		return legalPliegue;
	}

	/**
	 * Sets the legal pliegue.
	 *
	 * @param legalPliegue
	 *            the legalPliegue to set
	 */
	public void setLegalPliegue(String legalPliegue) {
		this.legalPliegue = legalPliegue;
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
	 * Gets the importe label.
	 *
	 * @return the importeLabel
	 */
	public String getImporteLabel() {
		return importeLabel;
	}

	/**
	 * Sets the importe label.
	 *
	 * @param importeLabel
	 *            the importeLabel to set
	 */
	public void setImporteLabel(String importeLabel) {
		this.importeLabel = importeLabel;
	}

	/**
	 * Gets the nombre licitacion.
	 *
	 * @return the nombreLicitacion
	 */
	public String getNombreLicitacion() {
		return nombreLicitacion;
	}

	/**
	 * Sets the nombre licitacion.
	 *
	 * @param nombreLicitacion
	 *            the nombreLicitacion to set
	 */
	public void setNombreLicitacion(String nombreLicitacion) {
		this.nombreLicitacion = nombreLicitacion;
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
		parametros.put(monedaKey, getMoneda());
		parametros.put(cuentaTitulosKey, getCuentaTitulos());
		parametros.put(tramoKey, getTramo());
		parametros.put(monedaLicitacionKey, getMonedaLicitacion());
		parametros.put(cuentaDebitoKey, getCuentaDebito());
		parametros.put(tipoCuentaDebitoKey, getTipoCuentaDebito());
		parametros.put(valorNominalKey, getValorNominal());
		parametros.put(precioTasaKey, getPrecioTasa());
		parametros.put(precioLabelKey, getPrecioLabel());
		parametros.put(importeAReversarKey, getImporteAReversar());
		parametros.put(fechaHoraKey, getFechaHora());
		parametros.put(fechaAdjudicacionKey, getFechaAdjudicacion());
		parametros.put(fechaLiquidacionKey, getFechaLiquidacion());
		parametros.put(numeroOrdenKey, getNumeroOrden());
		parametros.put(comisionesKey, getComisiones());
		parametros.put(impuestosKey, getImpuestos());
		parametros.put(legalCanalKey, getLegalCanal());
		parametros.put(legalPliegueKey, getLegalPliegue());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(nombreLicitacionKey, getNombreLicitacion());
		parametros.put(importeLabelKey, getImporteLabel());
		this.setTituloComprobante("Comprobante de solicitud de reversa de licitación");
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
