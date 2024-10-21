/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;

/**
 * The Class DetalleCompraVentaDolarView.
 */
public class DetalleCompraVentaDolarView extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nro cuenta destino. */
	private String nroCuentaDestino;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;

	/** The cotizacion aplicada. */
	private String cotizacionAplicada;

	/** The label debito. */
	private String labelDebito;

	/** The label credito. */
	private String labelCredito;

	/** The nro operacion. */
	private String nroOperacion;

	/** The saldo debitado. */
	private String saldoDebitado;

	/** The saldo acreditado. */
	private String saldoAcreditado;

	/** The hora. */
	private String hora;

	/** The legales. */
	private String legales;
	
	private String importeImpuesto;

	private String importeImpuesto2;

	private String importeBienes;

	/** The credito. */
	private final String credito = "credito";

	/** The debito. */
	private final String debito = "debito";

	/** The fecha vencimiento top. */
	protected final String numeroDestinoKey = "NUMERO_DESTINO";

	/** The fecha vencimiento top. */
	protected final String tipoDestinoKey = "TIPO_DESTINO";

	/** The fecha vencimiento top. */
	protected final String cotizacionKey = "COTIZACION";

	/** The fecha vencimiento top. */
	protected final String importeDebitadoKey = "IMPORTE_DEBITADO";

	/** The fecha vencimiento top. */
	protected final String importeAcreditadoKey = "IMPORTE_ACREDITADO";

	/** The fecha vencimiento top. */
	protected final String labelCreditoKey = "LABEL_ACREDITADO";

	/** The fecha vencimiento top. */
	protected final String labelDebitoKey = "LABEL_DEBITADO";

	/** The fecha vencimiento top. */
	protected final String numeroOperacionKey = "OPERACION";

	/** The fecha vencimiento top. */
	protected final String fechaOperacionKey = "FECHA_EJECUCION";

	/** The fecha vencimiento top. */
	protected final String nroComprobanteKey = "NUMERO_COMPROBANTE";

	/** The fecha vencimiento top. */
	protected final String legalesKey = "LEGALES";
	
	/** The importeImpuestoKey. */
	protected final String importeImpuestoKey = "IMPORTE_IMPUESTO";

	/** The pmc servicio. */
	private final String compraVentaJasper = "compra-venta-dolares.jasper";

	/** The pmc servicio. */
	private final String titulo = "Importe acreditado";
	
	   /** The importeImpuesto2Key. */
    protected final String importeImpuesto2Key = "IMPORTE_IMPUESTO2";

	/** The importeBienesKey. */
	protected static String importeBienesKey = "IMPORTE_BIENES";

	/**
	 * Gets the nro cuenta destino.
	 *
	 * @return the nro cuenta destino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * Sets the nro cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the new nro cuenta destino
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#
	 * getTipoCuentaDestino()
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#
	 * setTipoCuentaDestino(java.lang.String)
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#getCotizacionAplicada()
	 */
	public String getCotizacionAplicada() {
		return cotizacionAplicada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#setCotizacionAplicada(java.lang.String)
	 */
	public void setCotizacionAplicada(String cotizacionAplicada) {
		this.cotizacionAplicada = cotizacionAplicada;
	}

	/**
	 * Gets the label debito.
	 *
	 * @return the label debito
	 */
	public String getLabelDebito() {
		return labelDebito;
	}

	/**
	 * Sets the label debito.
	 *
	 * @param labelDebito
	 *            the new label debito
	 */
	public void setLabelDebito(String labelDebito) {
		this.labelDebito = labelDebito;
	}

	/**
	 * Gets the label credito.
	 *
	 * @return the label credito
	 */
	public String getLabelCredito() {
		return labelCredito;
	}

	/**
	 * Sets the label credito.
	 *
	 * @param labelCredito
	 *            the new label credito
	 */
	public void setLabelCredito(String labelCredito) {
		this.labelCredito = labelCredito;
	}

	/**
	 * Gets the saldo debitado.
	 *
	 * @return the saldo debitado
	 */
	public String getSaldoDebitado() {
		return saldoDebitado;
	}

	/**
	 * Sets the saldo debitado.
	 *
	 * @param saldoDebitado
	 *            the new saldo debitado
	 */
	public void setSaldoDebitado(String saldoDebitado) {
		this.saldoDebitado = saldoDebitado;
	}

	/**
	 * Gets the saldo acreditado.
	 *
	 * @return the saldo acreditado
	 */
	public String getSaldoAcreditado() {
		return saldoAcreditado;
	}

	/**
	 * Sets the saldo acreditado.
	 *
	 * @param saldoAcreditado
	 *            the new saldo acreditado
	 */
	public void setSaldoAcreditado(String saldoAcreditado) {
		this.saldoAcreditado = saldoAcreditado;
	}

	/**
	 * Gets the nro operacion.
	 *
	 * @return the nro operacion
	 */
	public String getNroOperacion() {
		return nroOperacion;
	}

	/**
	 * Sets the nro operacion.
	 *
	 * @param nroOperacion
	 *            the new nro operacion
	 */
	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}

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
	 * Obtener fecha hora.
	 *
	 * @return the string
	 */
	protected String obtenerFechaHora() {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(getHora())) {
			return sb.append(getFecha()).append(" - ").append(getHora()).append(" hs").toString();
		}
		return getFecha();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + compraVentaJasper).getPath();
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
		parametros.put(tituloKey, titulo);
		parametros.put(importeKey, setearMonedas(credito));
		parametros.put(numeroOrigenKey, getNroCuentaOrigen());
		parametros.put(numeroDestinoKey, nroCuentaDestino);
		parametros.put(tipoOrigenKey, getTipoCuentaOrigen());
		parametros.put(tipoDestinoKey, getTipoCuentaDestino());
		parametros.put(cotizacionKey, getCotizacionAplicada());
		parametros.put(importeDebitadoKey, setearMonedas(debito));
 		parametros.put(importeAcreditadoKey, setearMonedas(credito));
		parametros.put(labelCreditoKey, getLabelCredito());
		parametros.put(labelDebitoKey, getLabelDebito());
		parametros.put(numeroOperacionKey, getNroOperacion());
		parametros.put(fechaOperacionKey, obtenerFechaHora());
		parametros.put(nroComprobanteKey, getNroComprobante());
		parametros.put(fechaActualKey, getFechaOperacion());
		parametros.put(legalesKey, getLegales());
		parametros.put(importeImpuestoKey, obtenerImporteImpuesto(getImporteImpuesto()));
		parametros.put(importeImpuesto2Key, obtenerImporteImpuesto(getImporteImpuesto2()));
		parametros.put(importeBienesKey, obtenerImporteImpuesto(getImporteBienes()));
		return parametros;

	}

	/**
	 * Setear monedas.
	 *
	 * @param operacion
	 *            the operacion
	 * @return the string
	 */
	private String setearMonedas(String operacion) {
		if (operacion.equals(credito)) {
			if (CabeceraComprobantesEnum.COMPRA_DOLARES.getDetalle().equals(getTituloComprobante())) {
				return "u$s " + getSaldoAcreditado().replace( "u$s ",  "");
			} else {
				return "$ " + getSaldoAcreditado().replace( "$ ",  "");
			}
		} else {
			if (CabeceraComprobantesEnum.COMPRA_DOLARES.getDetalle().equals(getTituloComprobante())) {
				return "$ " + getSaldoDebitado().replace( "$ ",  "");
			} else {
				return "u$s " + getSaldoDebitado().replace( "u$s ",  "");
			}
		}
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
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * @return the importeImpuesto
	 */
	public String getImporteImpuesto() {
		return importeImpuesto;
	}

	/**
	 * @param importeImpuesto the importeImpuesto to set
	 */
	public void setImporteImpuesto(String importeImpuesto) {
		this.importeImpuesto = importeImpuesto;
	}
	
    public String getImporteImpuesto2() {
        return importeImpuesto2;
    }


    public void setImporteImpuesto2(String importeImpuesto2) {
        this.importeImpuesto2 = importeImpuesto2;
    }
	
	private String obtenerImporteImpuesto(String importe) {
		if (StringUtils.isEmpty(importe) || StringUtils.equals("0,00", importe)) {
			return null;
		}
		return "$ " + importe;
	}

	public String getImporteBienes() {
		return importeBienes;
	}


	public void setImporteBienes(String importeBienes) {
		this.importeBienes = importeBienes;
	}

}
