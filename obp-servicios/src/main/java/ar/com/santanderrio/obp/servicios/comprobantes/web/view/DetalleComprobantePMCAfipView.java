/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleComprobantePMCAfipView.
 */
public class DetalleComprobantePMCAfipView extends DetalleComprobantePMCView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo identificacion. */
	private String tipoIdentificacion;

	/** The elemento adicional. */
	private String elementoAdicional;

	/** The tipo elemento adicional. */
	private String tipoElementoAdicional;

	/** The pmc vep. */
	private final String pmcAfipJasper = "pmc-afip.jasper";

	/**
	 * Gets the tipo identificacion.
	 *
	 * @return the tipo identificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * Sets the tipo identificacion.
	 *
	 * @param tipoIdentificacion
	 *            the new tipo identificacion
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * Gets the elemento adicional.
	 *
	 * @return the elemento adicional
	 */
	public String getElementoAdicional() {
		return elementoAdicional;
	}

	/**
	 * Sets the elemento adicional.
	 *
	 * @param elementoAdicional
	 *            the new elemento adicional
	 */
	public void setElementoAdicional(String elementoAdicional) {
		this.elementoAdicional = elementoAdicional;
	}

	/**
	 * Gets the tipo elemento adicional.
	 *
	 * @return the tipo elemento adicional
	 */
	public String getTipoElementoAdicional() {
		return tipoElementoAdicional;
	}

	/**
	 * Sets the tipo elemento adicional.
	 *
	 * @param tipoElementoAdicional
	 *            the new tipo elemento adicional
	 */
	public void setTipoElementoAdicional(String tipoElementoAdicional) {
		this.tipoElementoAdicional = tipoElementoAdicional;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobantePMCView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + pmcAfipJasper).getPath();
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
		parametros.put(elementoAdicionalKey, elementoAdicional);
		parametros.put(tipoElementoAdicionalKey, tipoElementoAdicional);
		parametros.put(medioPagoKey, getNroCuentaOrigen());
		parametros.put(tipoMedioPagoKey, getTipoCuentaOrigen());
		parametros.put(fechaYHoraKey, obtenerFechaHora());
		parametros.put(numeroControlKey, getNroControl());
		parametros.put(informacionAdicionalKey, obtenerInformacionAdicional());
		parametros.put(numeroComprobanteKey, getNroTransaccion());
		parametros.put(logoPMCKey, ResourceUtils.getFile(path + logoPMC).getPath());
	    parametros.put(fechaActualKey, getFechaActual());
		return parametros;
	}

}
