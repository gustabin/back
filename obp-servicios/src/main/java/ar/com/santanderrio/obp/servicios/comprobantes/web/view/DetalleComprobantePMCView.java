/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleComprobantePMCView.
 */
public class DetalleComprobantePMCView extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The hora. */
	private String hora;

	/** The nro transaccion. */
	private String nroTransaccion;

	/** The label dinamico. */
	private String labelDinamico;

	/** The leyenda factura. */
	private String leyendaFactura;

	/** The leyenda empresa. */
	private String leyendaEmpresa;

	/** The nro control. */
	private String nroControl;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The factura. */
	private String factura;

	/** The fecha pago. */
	private String fechaPago;

	/** The fecha actual. */
	private String fechaActual;

	/** The pmc servicio. */
	private final String pmcServicioJasper = "pmc-servicio.jasper";

	/** The salto linea. */
	protected final String saltoLinea = "<br>";

	/** The logo pmc. */
	protected final String logoPMC = "logo-pago-mis-cuentas.png";

	/** The tipo medio pago. */
	protected final String tipoMedioPagoKey = "TIPO_MEDIO_PAGO";

	/** The fecha y hora. */
	protected final String fechaYHoraKey = "FECHA_Y_HORA";

	/** The numero control. */
	protected final String numeroControlKey = "NUMERO_CONTROL";

	/** The numero control. */
	protected final String fechaPagoKey = "FECHA_PAGO";

	/** The informacion adicional. */
	protected final String informacionAdicionalKey = "INFORMACION_ADICIONAL";

	/** The logo pmc. */
	protected final String logoPMCKey = "LOGO_PMC";

	/** The medio pago. */
	private final String leyendaFacturaKey = "LEYENDA_FACTURA";

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the nro transaccion.
	 *
	 * @return the nro transaccion
	 */
	public String getNroTransaccion() {
		return nroTransaccion;
	}

	/**
	 * Sets the nro transaccion.
	 *
	 * @param nroTransaccion
	 *            the new nro transaccion
	 */
	public void setNroTransaccion(String nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}

	/**
	 * Gets the label dinamico.
	 *
	 * @return the label dinamico
	 */
	public String getLabelDinamico() {
		return labelDinamico;
	}

	/**
	 * Sets the label dinamico.
	 *
	 * @param labelDinamico
	 *            the new label dinamico
	 */
	public void setLabelDinamico(String labelDinamico) {
		this.labelDinamico = labelDinamico;
	}

	/**
	 * Gets the leyenda factura.
	 *
	 * @return the leyenda factura
	 */
	public String getLeyendaFactura() {
		return leyendaFactura;
	}

	/**
	 * Sets the leyenda factura.
	 *
	 * @param leyendaFactura
	 *            the new leyenda factura
	 */
	public void setLeyendaFactura(String leyendaFactura) {
		this.leyendaFactura = leyendaFactura;
	}

	/**
	 * Gets the leyenda empresa.
	 *
	 * @return the leyenda empresa
	 */
	public String getLeyendaEmpresa() {
		return leyendaEmpresa;
	}

	/**
	 * Sets the leyenda empresa.
	 *
	 * @param leyendaEmpresa
	 *            the new leyenda empresa
	 */
	public void setLeyendaEmpresa(String leyendaEmpresa) {
		this.leyendaEmpresa = leyendaEmpresa;
	}

	/**
	 * Gets the nro control.
	 *
	 * @return the nro control
	 */
	public String getNroControl() {
		return nroControl;
	}

	/**
	 * Sets the nro control.
	 *
	 * @param nroControl
	 *            the new nro control
	 */
	public void setNroControl(String nroControl) {
		this.nroControl = nroControl;
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
	 * Gets the factura.
	 *
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * Sets the factura.
	 *
	 * @param factura
	 *            the new factura
	 */
	public void setFactura(String factura) {
		this.factura = factura;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + pmcServicioJasper).getPath();
	}

	/**
	 * Obtener fecha hora.
	 *
	 * @return the string
	 */
	protected String obtenerFechaHora() {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(getHora())) {
			return sb.append(getFechaOperacion()).append(" - ").append(getHora()).append(" hs").toString();
		}
		return getFechaOperacion();
	}

	/**
	 * Obtener informacion adicional.
	 *
	 * @return the string
	 */
	protected String obtenerInformacionAdicional() {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(getLeyendaFactura())) {
			concatenarLeyenda(getLeyendaFactura(), sb);
		}
		if (StringUtils.isNotBlank(getLeyendaEmpresa())) {
			concatenarLeyenda(getLeyendaEmpresa(), sb);
		}
		return sb.toString();
	}

	/**
	 * Gets the fecha pago.
	 *
	 * @return the fecha pago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fechaPago
	 *            the new fecha pago
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
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
	 * Concatenar leyenda.
	 *
	 * @param leyenda
	 *            the leyenda
	 * @param sb
	 *            the sb
	 */
	protected void concatenarLeyenda(String leyenda, StringBuilder sb) {
		if (StringUtils.isNotBlank(sb)) {
			sb.append(saltoLinea);
		}
		sb.append(leyenda);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerParametrosPDF()
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(tituloKey, getEmpresa());
		parametros.put(importeKey, obtenerImporte());
		parametros.put(empresaKey, getEmpresa());
		parametros.put(fechaVencimientoKey, getFechaVencimiento());
		parametros.put(tipoIdentificacionKey, getLabelDinamico());
		parametros.put(identificacionKey, getIdentificacion());
		parametros.put(leyendaFacturaKey, getFactura());
		parametros.put(medioPagoKey, getNroCuentaOrigen());
		parametros.put(tipoMedioPagoKey, getTipoCuentaOrigen());
		parametros.put(fechaYHoraKey, obtenerFechaHora());
		parametros.put(numeroControlKey, getNroControl());
		parametros.put(informacionAdicionalKey, obtenerInformacionAdicional());
		parametros.put(numeroComprobanteKey, getNroTransaccion());
		parametros.put(logoPMCKey, ResourceUtils.getFile(path + logoPMC).getPath());
		parametros.put(fechaPagoKey, getFechaPago());
		parametros.put(fechaActualKey, getFechaActual());
		return parametros;
	}

}
