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
 * The Class DetalleComprobanteDebitoAutomaticoTarjetaView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetalleComprobanteDebitoAutomaticoTarjetaView extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The descripcion. */
	private String descripcion;

	/** The titular. */
	private String titular;

	/** The medio de pago. */
	private String medioDePago;

	/** The titular medio pago key. */
	private final String titularKey = "TITULAR";

	/** The debito tarjeta jasper. */
	private final String debitoTarjetaJasper = "debito-tarjeta.jasper";

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the medio de pago.
	 *
	 * @return the medioDePago
	 */
	public String getMedioDePago() {
		return medioDePago;
	}

	/**
	 * Sets the medio de pago.
	 *
	 * @param medioDePago
	 *            the medioDePago to set
	 */
	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
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
		parametros.put(logoTituloKey, ResourceUtils.getFile(path + logoGenerico).getPath());
		parametros.put(importeKey, obtenerImporte());
		parametros.put(empresaKey, getEmpresa());
		parametros.put(descripcionKey, getDescripcion());
		parametros.put(medioPagoKey, getMedioDePago());
		parametros.put(titularKey, getTitular());
		parametros.put(fechaPagoKey, getFechaOperacion());
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
		return ResourceUtils.getFile(path + debitoTarjetaJasper).getPath();
	}

}
