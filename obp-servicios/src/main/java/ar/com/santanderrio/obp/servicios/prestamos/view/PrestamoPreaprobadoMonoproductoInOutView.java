package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoInEntity;

@JsonSerialize(include = Inclusion.NON_NULL)
public class PrestamoPreaprobadoMonoproductoInOutView {

	private static final String TASA_VARIABLE = "Variable";

	private static final String TASA_FIJA = "Fija";

	private static final String TIPO_TASA = "F";

	private PrestamoPreaprobadoMonoproductoInEntity requestSimulacion;
	
	private Long idOferta;

	private String importeSeleccionado;

	private String importeNeto;

	private String gastosOtorgamiento;

	private String impuesto;

	private CuentaView cuentaSeleccionada;
	
	private Cuenta cuenta;

	private String nroCuotas;

	private DestinoPrestamoSeleccionView motivoSeleccionado;

	private String fechaSeleccionada;

	private String cuotaConstante;

	private String tipoTasa;

	private String tna;

	private String tea;

	private String legales;

	private String tyc;

	private String mensajeFeedback;

	private String nroDeComprobante;

	private static final String PATH_REPO = "classpath:/report/prestamos/comprobante/";

	private static final String NOMBRE_ARCHIVO_JASPER = "comprobantePrestamoPreaprobado.jasper";

	/**
	 * Obtiene jasper report
	 * @return
	 * @throws FileNotFoundException
	 */
	public String obtenerJasper() throws FileNotFoundException {		
		return ResourceUtils.getFile(PATH_REPO + NOMBRE_ARCHIVO_JASPER).getPath();
	}

	/**
	 * Obtiene datos para comprobante PDF
	 * @return
	 */
	public HashMap<String, Object> obtenerParametros() {
		
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("importeAcreditado", DivisaEnum.PESO.getSimbolo() + ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(getImporteSeleccionado(),4));
		parameters.put("monto", DivisaEnum.PESO.getSimbolo() + ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(getImporteNeto(),4));
		Double valorNumerico = new Double(getGastosOtorgamiento());
		if(valorNumerico.compareTo(Double.valueOf(0)) > 0) {			
			parameters.put("comision", DivisaEnum.PESO.getSimbolo() + ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(getGastosOtorgamiento(),4));
		}
		valorNumerico = new Double(getImpuesto());
		if(valorNumerico.compareTo(Double.valueOf(0)) > 0) {
			parameters.put("impuestos", DivisaEnum.PESO.getSimbolo() + ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(getImpuesto(),4));
		}
		parameters.put("cuentaDestino", getCuenta().obtenerNroCuentaFormateado());
		parameters.put("tipoCuenta", getCuentaSeleccionada().getDescripcionTipoCuenta());
		parameters.put("nroCuotas", getNroCuotas());
		parameters.put("motivo", getMotivoSeleccionado().getDescripcion());
		parameters.put("fechaPrimeraCuota", FechaUtils.modificarFormatoFechas(getFechaSeleccionada(), "yyyy-MM-dd", "dd/MM/yyyy"));
		parameters.put("cuotaConstante", DivisaEnum.PESO.getSimbolo() + ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(getCuotaConstante(),4));
		parameters.put("tipoTasa", TIPO_TASA.equalsIgnoreCase(getTipoTasa())? TASA_FIJA : TASA_VARIABLE);
		parameters.put("tasa", ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(getTna(), 6) + " %");
		parameters.put("tea", ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(getTea(), 6) + " %");
		parameters.put("nroComprobante", getNroDeComprobante());
		parameters.put("legales", getLegales());
		return parameters;
	}

	public PrestamoPreaprobadoMonoproductoInEntity getRequestSimulacion() {
		return requestSimulacion;
	}

	public void setRequestSimulacion(PrestamoPreaprobadoMonoproductoInEntity requestSimulacion) {
		this.requestSimulacion = requestSimulacion;
	}

	public Long getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}

	public String getImporteSeleccionado() {
		return importeSeleccionado;
	}

	public void setImporteSeleccionado(String importeSeleccionado) {
		this.importeSeleccionado = importeSeleccionado;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getImporteNeto() {
		return importeNeto;
	}

	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	public String getGastosOtorgamiento() {
		return gastosOtorgamiento;
	}

	public void setGastosOtorgamiento(String gastosOtorgamiento) {
		this.gastosOtorgamiento = gastosOtorgamiento;
	}

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public CuentaView getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(CuentaView cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public String getNroCuotas() {
		return nroCuotas;
	}

	public void setNroCuotas(String nroCuotas) {
		this.nroCuotas = nroCuotas;
	}

	public DestinoPrestamoSeleccionView getMotivoSeleccionado() {
		return motivoSeleccionado;
	}

	public void setMotivoSeleccionado(DestinoPrestamoSeleccionView motivoSeleccionado) {
		this.motivoSeleccionado = motivoSeleccionado;
	}

	public String getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(String fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	public String getCuotaConstante() {
		return cuotaConstante;
	}

	public void setCuotaConstante(String cuotaConstante) {
		this.cuotaConstante = cuotaConstante;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public String getTna() {
		return tna;
	}

	public void setTna(String tna) {
		this.tna = tna;
	}

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getLegales() {
		return legales;
	}

	public void setLegales(String legales) {
		this.legales = legales;
	}

	public String getTyc() {
		return tyc;
	}

	public void setTyc(String tyc) {
		this.tyc = tyc;
	}

	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	public void setNroDeComprobante(String nroComprobante) {
		this.nroDeComprobante = nroComprobante;
	}
}
