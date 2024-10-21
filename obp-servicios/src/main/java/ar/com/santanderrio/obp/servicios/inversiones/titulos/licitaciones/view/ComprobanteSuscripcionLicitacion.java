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
 * The Class ComprobanteSuscripcionLicitacion.
	 */
public class ComprobanteSuscripcionLicitacion extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteSuscripcionLicitacion.class);
	
	/** The nombre licitacion. */
	private String nombreLicitacion;
	
	/** The tramo. */
	private String tramo;
	
	/** The moneda especie. */
	private String monedaEspecie;
	
	/** The moneda licitacion. */
	private String monedaLicitacion;
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The cuenta debito. */
	private String cuentaDebito;
	
	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;
	
	/** The cantidad nominales. */
	private String cantidadNominales;
	
	/** The precio tasa. */
	private String precioTasa;
	
	/** The importe label. */
	private String importeLabel;
	
	/** The importe. */
	private String importe;
	
	/** The fecha label. */
	private String fechaLabel;
	
	/** The fecha debito. */
	private String fechaDebito;
	
	/** The fecha cierre. */
	private String fechaCierre;
	
	/** The fecha adjudicacion. */
	private String fechaAdjudicacion;
	
	/** The fecha liquidacion titulos. */
	private String fechaLiquidacionTitulos;
	
	/** The numero orden. */
	private String numeroOrden;
	
	/** The email. */
	private String email;
	
	/** The comisiones. */
	private String comisiones;
	
	/** The impuestos. */
	private String impuestos;
	
	/** The legales. */
	private String legales;
	
	/** The fecha actual. */
	private String fechaActual;
	
	/** The reinvertir. */
	private String renovacion;
	
	/** The cantidad nominales label. */
	private String cantidadNominalesLabel;
	
	/** The tipo de cambio. */
	private String tipoDeCambio;
	
	/** The precio label. */
	private String precioLabel;
	
	/** The mostrar importe label. */
	private boolean mostrarImporteLabel;
	
	/** The banca. */
	private String banca = "";
	
	/** The legal canal. */
	private String legalCanal;
	
	/** The nombre licitacion key. */
	protected final String nombreLicitacionKey = "NOMBRE_LICITACION";
	
	/** The tramo key. */
	protected final String tramoKey = "TRAMO";
	
	/** The moneda especie key. */
	protected final String monedaEspecieKey = "MONEDA_ESPECIE";
	
	/** The moneda licitacion key. */
	protected final String monedaLicitacionKey = "MONEDA_LICITACION";
	
	/** The cuenta titulos key. */
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";
	
	/** The cuenta debito key. */
	protected final String cuentaDebitoKey = "CUENTA_DEBITO";
	
	/** The tipo cuenta debito key. */
	protected final String tipoCuentaDebitoKey = "TIPO_CUENTA_DEBITO";
	
	/** The cantidad nominales key. */
	protected final String cantidadNominalesKey = "CANTIDAD_NOMINALES";
	
	/** The precio tasa key. */
	protected final String precioTasaKey = "PRECIO_TASA";
	
	/** The importe label key. */
	protected final String importeLabelKey = "IMPORTE_LABEL";
	
	/** The importe key. */
	protected final String importeKey = "IMPORTE";
	
	/** The fecha label key. */
	protected final String fechaLabelKey = "FECHA_LABEL";
	
	/** The fecha debito key. */
	protected final String fechaDebitoKey = "FECHA_DEBITO";
	
	/** The fecha cierre key. */
	protected final String fechaCierreKey = "FECHA_CIERRE";
	
	/** The fecha adjudicacion key. */
	protected final String fechaAdjudicacionKey = "FECHA_ADJUDICACION";
	
	/** The fecha liquidacion titulos key. */
	protected final String fechaLiquidacionTitulosKey = "FECHA_LIQUIDACION_TITULOS";
	
	/** The numero orden key. */
	protected final String numeroOrdenKey = "NUMERO_ORDEN";
	
	/** The email key. */
	protected final String emailKey = "EMAIL";
	
	/** The comisiones key. */
	protected final String comisionesKey = "COMISIONES";
	
	/** The impuestos key. */
	protected final String impuestosKey = "IMPUESTOS";
	
	/** The legales key. */
	protected final String legalesKey = "LEGALES";
	
	/** The tipo de cambio key. */
	protected final String tipoDeCambioKey = "TIPO_CAMBIO";
	
	/** The fecha actual key. */
	protected final String fechaActualKey = "FECHA_ACTUAL";
	
	/** The precio label key. */
	protected final String precioLabelKey = "PRECIO_LABEL";
	
	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";
	
	/** The legal canal key. */
	protected final String legalCanalKey = "LEGALES_CANAL";
	
	/** The cantidad nominales label key. */
	protected final String cantidadNominalesLabelKey = "CANTIDAD_NOMINALES_LABEL";
	
	/** The suscripcion jasper. */
	protected final String suscripcionJasper = "suscripcion-licitacion.jasper";
	
	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	
	/** The path. */
	protected final String pathInversiones = "inversiones/";
	
	/** The fecha label reinvertir. */
	protected final String fechaLabelReinvertir = "Fecha de reinversión";
	
	/** The fecha label no reinvertir. */
	protected final String fechaLabelNoReinvertir = "Fecha de débito en cuenta";
	
	/** The importe label reinvertir. */
	protected final String importeLabelReinvertir = "Importe reinvertido";
	
	/** The importe label no reinvertir. */
	protected final String importeLabelNoReinvertir = "Importe a debitar";

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
	 * Gets the moneda especie.
	 *
	 * @return the monedaEspecie
	 */
	public String getMonedaEspecie() {
		return monedaEspecie;
	}

	/**
	 * Sets the moneda especie.
	 *
	 * @param monedaEspecie
	 *            the monedaEspecie to set
	 */
	public void setMonedaEspecie(String monedaEspecie) {
		this.monedaEspecie = monedaEspecie;
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
	 * Gets the fecha label.
	 *
	 * @return the fechaLabel
	 */
	public String getFechaLabel() {
		return fechaLabel;
	}

	/**
	 * Sets the fecha label.
	 *
	 * @param fechaLabel
	 *            the fechaLabel to set
	 */
	public void setFechaLabel(String fechaLabel) {
		this.fechaLabel = fechaLabel;
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
	 * Gets the fecha cierre.
	 *
	 * @return the fechaCierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Sets the fecha cierre.
	 *
	 * @param fechaCierre
	 *            the fechaCierre to set
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
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
	 * Gets the fecha liquidacion titulos.
	 *
	 * @return the fechaLiquidacionTitulos
	 */
	public String getFechaLiquidacionTitulos() {
		return fechaLiquidacionTitulos;
	}

	/**
	 * Sets the fecha liquidacion titulos.
	 *
	 * @param fechaLiquidacionTitulos
	 *            the fechaLiquidacionTitulos to set
	 */
	public void setFechaLiquidacionTitulos(String fechaLiquidacionTitulos) {
		this.fechaLiquidacionTitulos = fechaLiquidacionTitulos;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Gets the reinvertir.
	 *
	 * @return the reinvertir
	 */
	public String getRenovacion() {
		return renovacion;
	}

	/**
	 * Sets the reinvertir.
	 *
	 * @param renovacion
	 *            the new renovacion
	 */
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
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
	 * Checks if is mostrar importe label.
	 *
	 * @return the mostrarImporteLabel
	 */
	public boolean isMostrarImporteLabel() {
		return mostrarImporteLabel;
	}

	/**
	 * Sets the mostrar importe label.
	 *
	 * @param mostrarImporteLabel
	 *            the mostrarImporteLabel to set
	 */
	public void setMostrarImporteLabel(boolean mostrarImporteLabel) {
		this.mostrarImporteLabel = mostrarImporteLabel;
	}
	
	

	/**
	 * Gets the cantidad nominales label.
	 *
	 * @return the cantidadNominalesLabel
	 */
	public String getCantidadNominalesLabel() {
		return cantidadNominalesLabel;
	}

	/**
	 * Sets the cantidad nominales label.
	 *
	 * @param cantidadNominalesLabel
	 *            the cantidadNominalesLabel to set
	 */
	public void setCantidadNominalesLabel(String cantidadNominalesLabel) {
		this.cantidadNominalesLabel = cantidadNominalesLabel;
	}
	
	/**
	 * Gets the tipo de cambio.
	 *
	 * @return the tipoDeCambio
	 */
	public String getTipoDeCambio() {
		return tipoDeCambio;
	}

	/**
	 * Sets the tipo de cambio.
	 *
	 * @param tipoDeCambio
	 *            the tipoDeCambio to set
	 */
	public void setTipoDeCambio(String tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
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
	 * Gets the banca.
	 *
	 * @return the banca
	 */
	public String getBanca() {
		return banca;
	}

	/**
	 * Sets the banca.
	 *
	 * @param banca
	 *            the banca to set
	 */
	public void setBanca(String banca) {
		this.banca = banca;
	}

	/**
	 * Gets the legal canal.
	 *
	 * @return the legal canal
	 */
	public String getLegalCanal() {
		return legalCanal;
	}

	/**
	 * Sets the legal canal.
	 *
	 * @param legalCanal
	 *            the new legal canal
	 */
	public void setLegalCanal(String legalCanal) {
		this.legalCanal = legalCanal;
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
		parametros.put(nombreLicitacionKey, getNombreLicitacion());
		parametros.put(tramoKey, getTramo());
		parametros.put(monedaEspecieKey, getMonedaEspecie());
		parametros.put(monedaLicitacionKey, getMonedaLicitacion());
		parametros.put(cuentaTitulosKey, getCuentaTitulos());
		parametros.put(cuentaDebitoKey, getCuentaDebito());
		parametros.put(tipoCuentaDebitoKey, getTipoCuentaDebito());
		parametros.put(cantidadNominalesKey, getCantidadNominales());
		parametros.put(precioTasaKey, getPrecioTasa());
		parametros.put(importeKey, getImporte());
		parametros.put(fechaDebitoKey, getFechaDebito());
		parametros.put(fechaCierreKey, getFechaCierre());
		parametros.put(fechaAdjudicacionKey, getFechaAdjudicacion());
		parametros.put(fechaLiquidacionTitulosKey, getFechaLiquidacionTitulos());
		parametros.put(numeroOrdenKey, getNumeroOrden());
		parametros.put(emailKey, getEmail());
		parametros.put(comisionesKey, getComisiones());
		parametros.put(impuestosKey, getImpuestos());
		parametros.put(legalesKey, getLegales() == null ? "" : getLegales());
		parametros.put(legalCanalKey, getLegalCanal() == null ? "" : getLegalCanal());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(tipoDeCambioKey, getTipoDeCambio());
		parametros.put(precioLabelKey, getPrecioLabel());
		this.setTituloComprobante("Comprobante de suscripción a licitaciones de Titulos Valores");
		if("S".equals(getRenovacion())){
			parametros.put(importeLabelKey, importeLabelReinvertir);
			parametros.put(fechaLabelKey, fechaLabelReinvertir);
		} else {
			parametros.put(importeLabelKey, importeLabelNoReinvertir);
			parametros.put(fechaLabelKey, fechaLabelNoReinvertir);
		}
		
		if(isMostrarImporteLabel() == true){
			parametros.put(cantidadNominalesLabelKey, "Importe");
		}else{
			parametros.put(cantidadNominalesLabelKey, "Cantidad de nominales");
		}
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
