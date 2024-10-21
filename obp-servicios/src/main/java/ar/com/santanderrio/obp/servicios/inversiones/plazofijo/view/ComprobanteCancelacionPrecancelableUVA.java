package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

public class ComprobanteCancelacionPrecancelableUVA extends DetalleComprobanteView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9092838088500543213L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteCancelacionPrecancelableUVA.class);

	private String banca;
	private String capitalInicial;
	private String moneda;
	private String fechaSolicitud;
	private String fechaAcreditacion;
	private String diasTranscurridos;
	private String tasaNominalAnual;
	private String intereses;
	private String cuentaDestino;
	private String tipoCuentaDestino;
	private String totalACobrar;
	private String fechaHora;
	private String legal;

	protected final String capitalInicialKey = "CAPITAL_INICIAL";
	protected final String monedaKey = "MONEDA";
	protected final String fechaSolicitudKey = "FECHA_SOLICITUD";
	protected final String fechaAcreditacionKey = "FECHA_ACREDITACION";
	protected final String diasTranscurridosKey = "DIAS_TRANSCURRIDOS";
	protected final String tasaNominalAnualKey = "TASA_NOMINAL_ANUAL";
	protected final String interesesKey = "INTERESES";
	protected final String cuentaDestinoKey = "CUENTA_DESTINO";
	protected final String tipoCuentaDestinoKey = "TIPO_CUENTA_DESTINO";
	protected final String totalACobrarKey = "TOTAL_A_COBRAR";
	protected final String fechaHoraKey = "FECHA_HORA";
	protected final String legalKey = "LEGAL";
	protected final String logoPieKey = "LOGO_PIE";
	protected final String solicitudPrecancelableUVA = "solicitud-precancelable-uva.jasper";
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	protected final String pathInversiones = "inversiones/";

	public String getCapitalInicial() {
		return capitalInicial;
	}

	public void setCapitalInicial(String capitalInicial) {
		this.capitalInicial = capitalInicial;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getFechaAcreditacion() {
		return fechaAcreditacion;
	}

	public void setFechaAcreditacion(String fechaAcreditacion) {
		this.fechaAcreditacion = fechaAcreditacion;
	}

	public String getDiasTranscurridos() {
		return diasTranscurridos;
	}

	public void setDiasTranscurridos(String diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}

	public String getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	public void setTasaNominalAnual(String tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
	}

	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	public String getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	public String getTotalACobrar() {
		return totalACobrar;
	}

	public void setTotalACobrar(String totalACobrar) {
		this.totalACobrar = totalACobrar;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
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
		parametros.put(capitalInicialKey, getCapitalInicial());
		parametros.put(monedaKey, getMoneda());
		parametros.put(fechaSolicitudKey, getFechaSolicitud());
		parametros.put(fechaAcreditacionKey, getFechaAcreditacion());
		parametros.put(diasTranscurridosKey, getDiasTranscurridos());
		parametros.put(tasaNominalAnualKey, getTasaNominalAnual());
		parametros.put(diasTranscurridosKey, getDiasTranscurridos());
		parametros.put(interesesKey, getIntereses());
		parametros.put(cuentaDestinoKey, getCuentaDestino());
		parametros.put(tipoCuentaDestinoKey, getTipoCuentaDestino());
		parametros.put(totalACobrarKey, getTotalACobrar());
		parametros.put(fechaHoraKey, getFechaHora());
		parametros.put(legalKey, getLegal());
		this.setTituloComprobante("Comprobante de solicitud de la Precancelación");
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
		return ResourceUtils.getFile(path + pathInversiones + solicitudPrecancelableUVA).getPath();
	}
}
