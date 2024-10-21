/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ModificacionCambioDomicilioDTO;

/**
 * DatosComprobanteEntity.
 *
 * @author Silvina_Luque
 */
public class DatosComprobanteEntity {

	/** The nro comprobante. */
	private String nroComprobante;

	/** The domicilio completo actual. */
	private String domicilioCompletoActual;

	/** The domicilio completo anterior. */
	private String domicilioCompletoAnterior;

	/** The fecha. */
	private String fecha;

	/** The Constant EMPTY_FIELD. */
	private static final String EMPTY_FIELD = " ";

	/** The Constant TELEFONO. */
	private static final String TELEFONO = "Tel.";

	/** The Constant SEPARADOR. */
	private static final String SEPARADOR = "-";

	/**
	 * Instantiates a new datos comprobante entity.
	 */
	public DatosComprobanteEntity() {

	}

	/**
	 * Instantiates a new datos comprobante entity.
	 *
	 * @param original
	 *            the original
	 * @param modificado
	 *            the modificado
	 */
	public DatosComprobanteEntity(CambioDomicilioDTO original, ModificacionCambioDomicilioDTO modificado) {
		nroComprobante = modificado.getResultadoModificacion().getNroComprobante();
		fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		domicilioCompletoActual = buildDomicilio(modificado);
		domicilioCompletoAnterior = buildDomicilio(original);
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the domicilio completo actual.
	 *
	 * @return the domicilio completo actual
	 */
	public String getDomicilioCompletoActual() {
		return domicilioCompletoActual;
	}

	/**
	 * Sets the domicilio completo actual.
	 *
	 * @param domicilioCompletoActual
	 *            the new domicilio completo actual
	 */
	public void setDomicilioCompletoActual(String domicilioCompletoActual) {
		this.domicilioCompletoActual = domicilioCompletoActual;
	}

	/**
	 * Gets the domicilio completo anterior.
	 *
	 * @return the domicilio completo anterior
	 */
	public String getDomicilioCompletoAnterior() {
		return domicilioCompletoAnterior;
	}

	/**
	 * Sets the domicilio completo anterior.
	 *
	 * @param domicilioCompletoAnterior
	 *            the new domicilio completo anterior
	 */
	public void setDomicilioCompletoAnterior(String domicilioCompletoAnterior) {
		this.domicilioCompletoAnterior = domicilioCompletoAnterior;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Builds the domicilio.
	 *
	 * @param domicilio
	 *            the domicilio
	 * @return the string
	 */
	private String buildDomicilio(CambioDomicilioDTO domicilio) {
		return (domicilio.getCalle() == null ? "" : domicilio.getCalle().replaceAll("\\s+$", "")) + EMPTY_FIELD
				+ (domicilio.getApt() == null ? ""
						: domicilio.getApt().replaceAll("\\s", "").replaceFirst("^0+(?!$)", ""))
				+ EMPTY_FIELD + (domicilio.getPiso() == null ? "" : domicilio.getPiso()) + EMPTY_FIELD
				+ (domicilio.getDepartamento() == null ? "" : domicilio.getDepartamento()) + SEPARADOR
				+ (domicilio.getCodigoPostal() == null ? "" : domicilio.getCodigoPostal().replaceAll("\\s+$", ""))
				+ SEPARADOR + (domicilio.getComuna() == null ? "" : domicilio.getComuna().replaceAll("\\s+$", ""))
				+ EMPTY_FIELD
				+ (domicilio.getLocalidad() == null ? "" : domicilio.getLocalidad().replaceAll("\\s+$", "").trim())
				+ EMPTY_FIELD + SEPARADOR + (domicilio.getDescProvincia() == null ? "" : domicilio.getDescProvincia())
				+ "\r\n " + (TELEFONO + EMPTY_FIELD + domicilio.getPrefijo() + SEPARADOR + domicilio.getCaracteristica()
						+ SEPARADOR + domicilio.getNumeroTelefono());

	}

}
