/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleComprobanteTransferenciaProgramadaView.
 */
public class DetalleComprobanteTransferenciaProgramadaView extends DetalleComprobanteTransferenciaView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The esPagoDeHaberes. */
	private Boolean esPagoDeHaberes = Boolean.FALSE;

	/**
	 * Gets the es pago de haberes.
	 *
	 * @return the esPagoDeHaberes
	 */
	public Boolean getEsPagoDeHaberes() {
		return esPagoDeHaberes;
	}

	/**
	 * Sets the es pago de haberes.
	 *
	 * @param esPagoDeHaberes
	 *            the esPagoDeHaberes to set
	 */
	public void setEsPagoDeHaberes(Boolean esPagoDeHaberes) {
		this.esPagoDeHaberes = esPagoDeHaberes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteTransferenciaView#obtenerParametrosPDF()
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		ponerParametrosTransferencia(parametros);
		parametros.put(estadoKey, getEstado());
		return parametros;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteTransferenciaView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + transferenciasJasper).getPath();
	}

}
