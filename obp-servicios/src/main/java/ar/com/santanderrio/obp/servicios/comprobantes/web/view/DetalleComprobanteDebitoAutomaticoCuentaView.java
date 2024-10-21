/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleComprobanteDebitoAutomaticoCuentaView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetalleComprobanteDebitoAutomaticoCuentaView extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fecha pago. */
	private String fechaPago;

	/** The motivo rechazo. */
	private String motivoRechazo;

	/** The tipo cuenta debito. */
	private final String tipoCuentaDebitoKey = "TIPO_CUENTA_DEBITO";

	/** The cuenta debito. */
	private final String cuentaDebitoKey = "CUENTA_DEBITO";

	/** The cuenta debito. */
	private final String motivoKey = "MOTIVO";

	/** The debito automatico en cuenta jasper. */
	private final String debitoCuentaJasper = "debito-cuenta.jasper";

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
	 * Gets the motivo rechazo.
	 *
	 * @return the motivo rechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * Sets the motivo rechazo.
	 *
	 * @param motivoRechazo
	 *            the new motivo rechazo
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
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
		parametros.put(estadoKey, getEstado());
		parametros.put(motivoKey, getMotivoRechazo());
		parametros.put(empresaKey, getEmpresa());
		parametros.put(tipoCuentaDebitoKey, getTipoCuentaOrigen());
		parametros.put(cuentaDebitoKey, getNroCuentaOrigen());
		parametros.put(tipoIdentificacionKey, "Identificación");
		parametros.put(identificacionKey, getIdentificacion());
		parametros.put(fechaVencimientoKey, getFecha());
		parametros.put(fechaPagoKey, getFechaPago());
		return parametros;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + debitoCuentaJasper).getPath();
	}

}
