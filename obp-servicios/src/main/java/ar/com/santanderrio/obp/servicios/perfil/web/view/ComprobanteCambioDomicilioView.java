/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ModificacionCambioDomicilioDTO;

/**
 * ComprobanteCambioDomicilioView.
 *
 * @author Silvina_Luque
 */
public class ComprobanteCambioDomicilioView {

	/** The nro comprobante. */
	private String nroComprobante;

	/** The fecha. */
	private String fecha;

	/** The calle actual. */
	private String calleActual;

	/** The apt actual. */
	private String aptActual;

	/** The piso actual. */
	private String pisoActual;

	/** The departamento actual. */
	private String departamentoActual;

	/** The codigo postal actual. */
	private String codigoPostalActual;

	/** The desc comuna actual. */
	private String descComunaActual;

	/** The localidad actual. */
	private String localidadActual;

	/** The desc provincia actual. */
	private String descProvinciaActual;

	/** The prefijo actual. */
	private String prefijoActual;

	/** The caracteristica actual. */
	private String caracteristicaActual;

	/** The numero telefono actual. */
	private String numeroTelefonoActual;

	/** The calle anterior. */
	private String calleAnterior;

	/** The apt anterior. */
	private String aptAnterior;

	/** The piso anterior. */
	private String pisoAnterior;

	/** The departamento anterior. */
	private String departamentoAnterior;

	/** The codigo postal anterior. */
	private String codigoPostalAnterior;

	/** The desc comuna anterior. */
	private String descComunaAnterior;

	/** The localidad anterior. */
	private String localidadAnterior;

	/** The desc provincia anterior. */
	private String descProvinciaAnterior;

	/** The prefijo anterior. */
	private String prefijoAnterior;

	/** The caracteristica anterior. */
	private String caracteristicaAnterior;

	/** The numero telefono anterior. */
	private String numeroTelefonoAnterior;

	/** The domicilio completo actual. */
	private String domicilioCompletoActual;

	/** The domicilio completo anterior. */
	private String domicilioCompletoAnterior;

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant TELEFONO. */
	private static final String TELEFONO = "Tel.";

	/** The Constant SEPARADOR. */
	private static final String SEPARADOR = "-";

	/**
	 * Instantiates a new comprobante cambio domicilio view.
	 */
	public ComprobanteCambioDomicilioView() {

	}

	/**
	 * Instantiates a new comprobante cambio domicilio view.
	 *
	 * @param original
	 *            the original
	 * @param modificado
	 *            the modificado
	 */
	public ComprobanteCambioDomicilioView(CambioDomicilioDTO original, ModificacionCambioDomicilioDTO modificado) {
		nroComprobante = modificado.getResultadoModificacion().getNroComprobante();
		fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		calleActual = modificado.getCalle().replaceAll("\\s+$", "");
		aptActual = modificado.getApt().replaceAll("\\s", "").replaceFirst("^0+(?!$)", "");
		pisoActual = modificado.getPiso() == null ? "" : modificado.getPiso().replaceAll("\\s", "");
		departamentoActual = modificado.getDepartamento() == null ? ""
				: modificado.getDepartamento().replaceAll("\\s", "");
		codigoPostalActual = modificado.getCodigoPostal() == null ? ""
				: modificado.getCodigoPostal().replaceAll("\\s", "");
		descComunaActual = modificado.getComuna();
		localidadActual = modificado.getLocalidad() == null ? "" : modificado.getLocalidad().replaceAll("\\s+$", "");
		descProvinciaActual = modificado.getDescProvincia();
		prefijoActual = modificado.getPrefijo() == null ? "" : modificado.getPrefijo();
		caracteristicaActual = modificado.getCaracteristica() == null ? "" : modificado.getCaracteristica();
		numeroTelefonoActual = modificado.getNumeroTelefono() == null ? "" : modificado.getNumeroTelefono();
		calleAnterior = original.getCalle().replaceAll("\\s+$", "");
		aptAnterior = original.getApt().replaceAll("\\s", "").replaceFirst("^0+(?!$)", "");
		pisoAnterior = original.getPiso() == null ? "" : original.getPiso().replaceAll("\\s", "");
		departamentoAnterior = original.getDepartamento() == null ? ""
				: original.getDepartamento().replaceAll("\\s", "");
		codigoPostalAnterior = original.getCodigoPostal() == null ? ""
				: original.getCodigoPostal().replaceAll("\\s", "");
		descComunaAnterior = original.getComuna();
		localidadAnterior = original.getLocalidad() == null ? "" : original.getLocalidad().replaceAll("\\s+$", "");
		descProvinciaAnterior = original.getDescProvincia();
		prefijoAnterior = original.getPrefijo() == null ? "" : original.getPrefijo();
		caracteristicaAnterior = original.getCaracteristica() == null ? "" : original.getCaracteristica();
		numeroTelefonoAnterior = original.getNumeroTelefono() == null ? "" : original.getNumeroTelefono();
		domicilioCompletoActual = buildDomicilio(modificado);
		domicilioCompletoAnterior = buildDomicilio(original);

	}

	/**
	 * Builds the domicilio.
	 *
	 * @param domicilio
	 *            the domicilio
	 * @return the string
	 */
	private String buildDomicilio(CambioDomicilioDTO domicilio) {
		return (domicilio.getCalle() == null ? "" : domicilio.getCalle().replaceAll("\\s+$", "")) + SPACE
				+ (domicilio.getApt() == null ? ""
						: domicilio.getApt().replaceAll("\\s", "").replaceFirst("^0+(?!$)", ""))
				+ SPACE + (domicilio.getPiso() == null ? "" : domicilio.getPiso()) + SPACE
				+ (domicilio.getDepartamento() == null ? "" : domicilio.getDepartamento()) + SEPARADOR
				+ (domicilio.getCodigoPostal() == null ? "" : domicilio.getCodigoPostal().replaceAll("\\s+$", ""))
				+ SEPARADOR + (domicilio.getComuna() == null ? "" : domicilio.getComuna().replaceAll("\\s+$", ""))
				+ SPACE
				+ (domicilio.getLocalidad() == null ? "" : domicilio.getLocalidad().replaceAll("\\s+$", "").trim())
				+ SPACE + SEPARADOR + (domicilio.getDescProvincia() == null ? "" : domicilio.getDescProvincia())
				+ (TELEFONO + SPACE + domicilio.getPrefijo() + SEPARADOR + domicilio.getCaracteristica() + SEPARADOR
						+ domicilio.getNumeroTelefono());

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
	 * Gets the calle actual.
	 *
	 * @return the calle actual
	 */
	public String getCalleActual() {
		return calleActual;
	}

	/**
	 * Sets the calle actual.
	 *
	 * @param calleActual
	 *            the new calle actual
	 */
	public void setCalleActual(String calleActual) {
		this.calleActual = calleActual;
	}

	/**
	 * Gets the apt actual.
	 *
	 * @return the apt actual
	 */
	public String getAptActual() {
		return aptActual;
	}

	/**
	 * Sets the apt actual.
	 *
	 * @param aptActual
	 *            the new apt actual
	 */
	public void setAptActual(String aptActual) {
		this.aptActual = aptActual;
	}

	/**
	 * Gets the piso actual.
	 *
	 * @return the piso actual
	 */
	public String getPisoActual() {
		return pisoActual;
	}

	/**
	 * Sets the piso actual.
	 *
	 * @param pisoActual
	 *            the new piso actual
	 */
	public void setPisoActual(String pisoActual) {
		this.pisoActual = pisoActual;
	}

	/**
	 * Gets the departamento actual.
	 *
	 * @return the departamento actual
	 */
	public String getDepartamentoActual() {
		return departamentoActual;
	}

	/**
	 * Sets the departamento actual.
	 *
	 * @param departamentoActual
	 *            the new departamento actual
	 */
	public void setDepartamentoActual(String departamentoActual) {
		this.departamentoActual = departamentoActual;
	}

	/**
	 * Gets the codigo postal actual.
	 *
	 * @return the codigo postal actual
	 */
	public String getCodigoPostalActual() {
		return codigoPostalActual;
	}

	/**
	 * Sets the codigo postal actual.
	 *
	 * @param codigoPostalActual
	 *            the new codigo postal actual
	 */
	public void setCodigoPostalActual(String codigoPostalActual) {
		this.codigoPostalActual = codigoPostalActual;
	}

	/**
	 * Gets the desc comuna actual.
	 *
	 * @return the desc comuna actual
	 */
	public String getDescComunaActual() {
		return descComunaActual;
	}

	/**
	 * Sets the desc comuna actual.
	 *
	 * @param descComunaActual
	 *            the new desc comuna actual
	 */
	public void setDescComunaActual(String descComunaActual) {
		this.descComunaActual = descComunaActual;
	}

	/**
	 * Gets the localidad actual.
	 *
	 * @return the localidad actual
	 */
	public String getLocalidadActual() {
		return localidadActual;
	}

	/**
	 * Sets the localidad actual.
	 *
	 * @param localidadActual
	 *            the new localidad actual
	 */
	public void setLocalidadActual(String localidadActual) {
		this.localidadActual = localidadActual;
	}

	/**
	 * Gets the desc provincia actual.
	 *
	 * @return the desc provincia actual
	 */
	public String getDescProvinciaActual() {
		return descProvinciaActual;
	}

	/**
	 * Sets the desc provincia actual.
	 *
	 * @param descProvinciaActual
	 *            the new desc provincia actual
	 */
	public void setDescProvinciaActual(String descProvinciaActual) {
		this.descProvinciaActual = descProvinciaActual;
	}

	/**
	 * Gets the prefijo actual.
	 *
	 * @return the prefijo actual
	 */
	public String getPrefijoActual() {
		return prefijoActual;
	}

	/**
	 * Sets the prefijo actual.
	 *
	 * @param prefijoActual
	 *            the new prefijo actual
	 */
	public void setPrefijoActual(String prefijoActual) {
		this.prefijoActual = prefijoActual;
	}

	/**
	 * Gets the caracteristica actual.
	 *
	 * @return the caracteristica actual
	 */
	public String getCaracteristicaActual() {
		return caracteristicaActual;
	}

	/**
	 * Sets the caracteristica actual.
	 *
	 * @param caracteristicaActual
	 *            the new caracteristica actual
	 */
	public void setCaracteristicaActual(String caracteristicaActual) {
		this.caracteristicaActual = caracteristicaActual;
	}

	/**
	 * Gets the numero telefono actual.
	 *
	 * @return the numero telefono actual
	 */
	public String getNumeroTelefonoActual() {
		return numeroTelefonoActual;
	}

	/**
	 * Sets the numero telefono actual.
	 *
	 * @param numeroTelefonoActual
	 *            the new numero telefono actual
	 */
	public void setNumeroTelefonoActual(String numeroTelefonoActual) {
		this.numeroTelefonoActual = numeroTelefonoActual;
	}

	/**
	 * Gets the calle anterior.
	 *
	 * @return the calle anterior
	 */
	public String getCalleAnterior() {
		return calleAnterior;
	}

	/**
	 * Sets the calle anterior.
	 *
	 * @param calleAnterior
	 *            the new calle anterior
	 */
	public void setCalleAnterior(String calleAnterior) {
		this.calleAnterior = calleAnterior;
	}

	/**
	 * Gets the apt anterior.
	 *
	 * @return the apt anterior
	 */
	public String getAptAnterior() {
		return aptAnterior;
	}

	/**
	 * Sets the apt anterior.
	 *
	 * @param aptAnterior
	 *            the new apt anterior
	 */
	public void setAptAnterior(String aptAnterior) {
		this.aptAnterior = aptAnterior;
	}

	/**
	 * Gets the piso anterior.
	 *
	 * @return the piso anterior
	 */
	public String getPisoAnterior() {
		return pisoAnterior;
	}

	/**
	 * Sets the piso anterior.
	 *
	 * @param pisoAnterior
	 *            the new piso anterior
	 */
	public void setPisoAnterior(String pisoAnterior) {
		this.pisoAnterior = pisoAnterior;
	}

	/**
	 * Gets the departamento anterior.
	 *
	 * @return the departamento anterior
	 */
	public String getDepartamentoAnterior() {
		return departamentoAnterior;
	}

	/**
	 * Sets the departamento anterior.
	 *
	 * @param departamentoAnterior
	 *            the new departamento anterior
	 */
	public void setDepartamentoAnterior(String departamentoAnterior) {
		this.departamentoAnterior = departamentoAnterior;
	}

	/**
	 * Gets the codigo postal anterior.
	 *
	 * @return the codigo postal anterior
	 */
	public String getCodigoPostalAnterior() {
		return codigoPostalAnterior;
	}

	/**
	 * Sets the codigo postal anterior.
	 *
	 * @param codigoPostalAnterior
	 *            the new codigo postal anterior
	 */
	public void setCodigoPostalAnterior(String codigoPostalAnterior) {
		this.codigoPostalAnterior = codigoPostalAnterior;
	}

	/**
	 * Gets the desc comuna anterior.
	 *
	 * @return the desc comuna anterior
	 */
	public String getDescComunaAnterior() {
		return descComunaAnterior;
	}

	/**
	 * Sets the desc comuna anterior.
	 *
	 * @param descComunaAnterior
	 *            the new desc comuna anterior
	 */
	public void setDescComunaAnterior(String descComunaAnterior) {
		this.descComunaAnterior = descComunaAnterior;
	}

	/**
	 * Gets the localidad anterior.
	 *
	 * @return the localidad anterior
	 */
	public String getLocalidadAnterior() {
		return localidadAnterior;
	}

	/**
	 * Sets the localidad anterior.
	 *
	 * @param localidadAnterior
	 *            the new localidad anterior
	 */
	public void setLocalidadAnterior(String localidadAnterior) {
		this.localidadAnterior = localidadAnterior;
	}

	/**
	 * Gets the desc provincia anterior.
	 *
	 * @return the desc provincia anterior
	 */
	public String getDescProvinciaAnterior() {
		return descProvinciaAnterior;
	}

	/**
	 * Sets the desc provincia anterior.
	 *
	 * @param descProvinciaAnterior
	 *            the new desc provincia anterior
	 */
	public void setDescProvinciaAnterior(String descProvinciaAnterior) {
		this.descProvinciaAnterior = descProvinciaAnterior;
	}

	/**
	 * Gets the prefijo anterior.
	 *
	 * @return the prefijo anterior
	 */
	public String getPrefijoAnterior() {
		return prefijoAnterior;
	}

	/**
	 * Sets the prefijo anterior.
	 *
	 * @param prefijoAnterior
	 *            the new prefijo anterior
	 */
	public void setPrefijoAnterior(String prefijoAnterior) {
		this.prefijoAnterior = prefijoAnterior;
	}

	/**
	 * Gets the caracteristica anterior.
	 *
	 * @return the caracteristica anterior
	 */
	public String getCaracteristicaAnterior() {
		return caracteristicaAnterior;
	}

	/**
	 * Sets the caracteristica anterior.
	 *
	 * @param caracteristicaAnterior
	 *            the new caracteristica anterior
	 */
	public void setCaracteristicaAnterior(String caracteristicaAnterior) {
		this.caracteristicaAnterior = caracteristicaAnterior;
	}

	/**
	 * Gets the numero telefono anterior.
	 *
	 * @return the numero telefono anterior
	 */
	public String getNumeroTelefonoAnterior() {
		return numeroTelefonoAnterior;
	}

	/**
	 * Sets the numero telefono anterior.
	 *
	 * @param numeroTelefonoAnterior
	 *            the new numero telefono anterior
	 */
	public void setNumeroTelefonoAnterior(String numeroTelefonoAnterior) {
		this.numeroTelefonoAnterior = numeroTelefonoAnterior;
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

}
