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
 * The Class DetalleComprobantePMCVEPView.
 */
public class DetalleComprobantePMCVEPView extends DetalleComprobantePMCView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The leyenda factura 2. */
	private String leyendaFactura2;

	/** The leyenda factura 3. */
	private String leyendaFactura3;

	/** The cuit 2. */
	private String cuit2;

	/** The nro VEP. */
	private String nroVEP;

	/** The periodo. */
	private String periodo;

	/** The anticipo. */
	private String anticipo;

	/** The pmc vep. */
	private final String pmcVepJasper = "pmc-vep.jasper";

	/** The cuit generador del VEP *. */
	private final String cuitGeneradorVEPKey = "CUIT_VEP";

	/** The numero VEP. */
	private final String numeroVEPKey = "NUMERO_VEP";

	/** The periodo. */
	private final String periodoVEPKey = "PERIODO_VEP";

	/** The anticipo cuota. */
	private final String anticipoCuotaKey = "CUOTA_VEP";

	/**
	 * Gets the cuit 2.
	 *
	 * @return the cuit 2
	 */
	public String getCuit2() {
		return cuit2;
	}

	/**
	 * Sets the cuit 2.
	 *
	 * @param cuit2
	 *            the new cuit 2
	 */
	public void setCuit2(String cuit2) {
		this.cuit2 = cuit2;
	}

	/**
	 * Gets the leyenda factura 2.
	 *
	 * @return the leyenda factura 2
	 */
	public String getLeyendaFactura2() {
		return leyendaFactura2;
	}

	/**
	 * Sets the leyenda factura 2.
	 *
	 * @param leyendaFactura2
	 *            the new leyenda factura 2
	 */
	public void setLeyendaFactura2(String leyendaFactura2) {
		this.leyendaFactura2 = leyendaFactura2;
	}

	/**
	 * Gets the leyenda factura 3.
	 *
	 * @return the leyenda factura 3
	 */
	public String getLeyendaFactura3() {
		return leyendaFactura3;
	}

	/**
	 * Sets the leyenda factura 3.
	 *
	 * @param leyendaFactura3
	 *            the new leyenda factura 3
	 */
	public void setLeyendaFactura3(String leyendaFactura3) {
		this.leyendaFactura3 = leyendaFactura3;
	}

	/**
	 * Gets the nro VEP.
	 *
	 * @return the nro VEP
	 */
	public String getNroVEP() {
		return nroVEP;
	}

	/**
	 * Sets the nro VEP.
	 *
	 * @param nroVEP
	 *            the new nro VEP
	 */
	public void setNroVEP(String nroVEP) {
		this.nroVEP = nroVEP;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo
	 *            the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Gets the anticipo.
	 *
	 * @return the anticipo
	 */
	public String getAnticipo() {
		return anticipo;
	}

	/**
	 * Sets the anticipo.
	 *
	 * @param anticipo
	 *            the new anticipo
	 */
	public void setAnticipo(String anticipo) {
		this.anticipo = anticipo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobantePMCView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + pmcVepJasper).getPath();
	}

	/**
	 * Obtener informacion adicional.
	 *
	 * @return the string
	 */
	@Override
	protected String obtenerInformacionAdicional() {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(getLeyendaFactura())) {
			concatenarLeyenda(getLeyendaFactura(), sb);
		}
		if (StringUtils.isNotBlank(getLeyendaFactura2())) {
			concatenarLeyenda(getLeyendaFactura2(), sb);
		}
		if (StringUtils.isNotBlank(getLeyendaFactura3())) {
			concatenarLeyenda(getLeyendaFactura3(), sb);
		}
		if (StringUtils.isNotBlank(getLeyendaEmpresa())) {
			concatenarLeyenda(getLeyendaEmpresa(), sb);
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobantePMCView#obtenerParametrosPDF()
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
		parametros.put(cuitKey, getCuit());
		parametros.put(tipoCuitKey, getTipoCuit());
		parametros.put(cuitGeneradorVEPKey, getCuit2());
		parametros.put(numeroVEPKey, getNroVEP());
		parametros.put(periodoVEPKey, getPeriodo());
		parametros.put(anticipoCuotaKey, getAnticipo());
		parametros.put(medioPagoKey, getNroCuentaOrigen());
		parametros.put(tipoMedioPagoKey, getTipoCuentaOrigen());
		parametros.put(fechaYHoraKey, obtenerFechaHora());
		parametros.put(numeroControlKey, getNroControl());
		parametros.put(informacionAdicionalKey, obtenerInformacionAdicional());
		parametros.put(numeroComprobanteKey, getNroTransaccion());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(logoPMCKey, ResourceUtils.getFile(path + logoPMC).getPath());
		return parametros;
	}

}
