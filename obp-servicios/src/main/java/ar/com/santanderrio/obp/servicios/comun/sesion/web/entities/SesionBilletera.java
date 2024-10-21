/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarMedioPagoDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.ConsultaCuentaDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.MedioDePagoBilleteraDTO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;

/**
 * The Class SesionBilletera.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionBilletera {

	/** The administrar medio pago dto. */
	private AdministrarMedioPagoDTO administrarMedioPagoDto;

	/** The consulta cuenta. */
	private ConsultaCuentaDTO consultaCuentaDto;

	/** The ctas map. */
	private Map<String, List<MedioDePagoBilleteraDTO>> ctasMap;

	/** The cuenta acreditacion. */
	private String cuentaAcreditacion;

	/** The datos solicitante. */
	private DatosSolicitanteEntity datosSolicitante;

	/** The email. */
	private String email;

	/** The empresa celular. */
	private String empresaCelular;

	/** The err cta. */
	private boolean errCta;

	/** The id cuenta. */
	private String idCuenta;

	/** The id cuenta alta cuenta. */
	private String idCuentaAltaCuenta;

	/** The impact trjs. */
	private Boolean impactTrjs;

	/** The mode. */
	private String mode;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The numero numero celular. */
	private String numeroCelular;

	/** The tarjetas ok. */
	private List<MedioDePagoBilleteraDTO> tarjetasOk;

	/**
	 * Gets the administrar medio pago dto.
	 *
	 * @return the administrarMedioPagoDto
	 */
	public AdministrarMedioPagoDTO getAdministrarMedioPagoDto() {
		return administrarMedioPagoDto;
	}

	/**
	 * Gets the consulta cuenta.
	 *
	 * @return the consultaCuenta
	 */
	public ConsultaCuentaDTO getConsultaCuentaDto() {
		return consultaCuentaDto;
	}

	/**
	 * Gets the ctas map.
	 *
	 * @return the ctasMap
	 */
	public Map<String, List<MedioDePagoBilleteraDTO>> getCtasMap() {
		return ctasMap;
	}

	/**
	 * Gets the cuenta acreditacion.
	 *
	 * @return the cuentaAcreditacion
	 */
	public String getCuentaAcreditacion() {
		return cuentaAcreditacion;
	}

	/**
	 * Gets the datos solicitante.
	 *
	 * @return the datosSolicitante
	 */
	public DatosSolicitanteEntity getDatosSolicitante() {
		return datosSolicitante;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the empresa celular.
	 *
	 * @return the empresaCelular
	 */
	public String getEmpresaCelular() {
		return empresaCelular;
	}

	/**
	 * Gets the err cta.
	 *
	 * @return the errCta
	 */
	public boolean getErrCta() {
		return errCta;
	}

	/**
	 * Gets the id cuenta.
	 *
	 * @return the idCuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Gets the id cuenta alta cuenta.
	 *
	 * @return the idCuentaAltaCuenta
	 */
	public String getIdCuentaAltaCuenta() {
		return idCuentaAltaCuenta;
	}

	/**
	 * Gets the impact trjs.
	 *
	 * @return the impactTrjs
	 */
	public Boolean getImpactTrjs() {
		return impactTrjs;
	}

	/**
	 * Obtiene la lista de cuentas a partir de Map almacenado en session.
	 *
	 * @param tipoLista
	 *            Tipo de lista a buscar
	 * @return Lista de medios de pago obtenida desde Map almacenado en session
	 */
	public List<MedioDePagoBilleteraDTO> getList(String tipoLista) {
		return this.ctasMap.get(tipoLista);
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Gets the numero celular.
	 *
	 * @return the numeroCelular
	 */
	public String getNumeroCelular() {
		return numeroCelular;
	}

	/**
	 * Gets the tarjetas ok.
	 *
	 * @return the tarjetasOk
	 */
	public List<MedioDePagoBilleteraDTO> getTarjetasOk() {
		return tarjetasOk;
	}

	/**
	 * Sets the administrar medio pago dto.
	 *
	 * @param administrarMedioPagoDto
	 *            the administrarMedioPagoDto to set
	 */
	public void setAdministrarMedioPagoDto(AdministrarMedioPagoDTO administrarMedioPagoDto) {
		this.administrarMedioPagoDto = administrarMedioPagoDto;
	}

	/**
	 * Sets the consulta cuenta.
	 *
	 * @param consultaCuenta
	 *            the consultaCuenta to set
	 */
	public void setConsultaCuentaDto(ConsultaCuentaDTO consultaCuenta) {
		this.consultaCuentaDto = consultaCuenta;
	}

	/**
	 * Sets the ctas map.
	 *
	 * @param ctasMap
	 *            the ctasMap to set
	 */
	public void setCtasMap(Map<String, List<MedioDePagoBilleteraDTO>> ctasMap) {
		this.ctasMap = ctasMap;
	}

	/**
	 * Sets the cuenta acreditacion.
	 *
	 * @param cuentaAcreditacion
	 *            the cuentaAcreditacion to set
	 */
	public void setCuentaAcreditacion(String cuentaAcreditacion) {
		this.cuentaAcreditacion = cuentaAcreditacion;
	}

	/**
	 * Sets the datos solicitante.
	 *
	 * @param datosSolicitante
	 *            the datosSolicitante to set
	 */
	public void setDatosSolicitante(DatosSolicitanteEntity datosSolicitante) {
		this.datosSolicitante = datosSolicitante;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the empresa celular.
	 *
	 * @param empresaCelular
	 *            the empresaCelular to set
	 */
	public void setEmpresaCelular(String empresaCelular) {
		this.empresaCelular = empresaCelular;
	}

	/**
	 * Sets the err cta.
	 *
	 * @param errCta
	 *            the errCta to set
	 */
	public void setErrCta(boolean errCta) {
		this.errCta = errCta;
	}

	/**
	 * Sets the id cuenta.
	 *
	 * @param idCuenta
	 *            the idCuenta to set
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * Sets the id cuenta alta cuenta.
	 *
	 * @param idCuentaAltaCuenta
	 *            the idCuentaAltaCuenta to set
	 */
	public void setIdCuentaAltaCuenta(String idCuentaAltaCuenta) {
		this.idCuentaAltaCuenta = idCuentaAltaCuenta;
	}

	/**
	 * Sets the impact trjs.
	 *
	 * @param impactTrjs
	 *            the impactTrjs to set
	 */
	public void setImpactTrjs(Boolean impactTrjs) {
		this.impactTrjs = impactTrjs;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Sets the numero celular.
	 *
	 * @param numeroCelular
	 *            the numeroCelular to set
	 */
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	/**
	 * Sets the tarjetas ok.
	 *
	 * @param tarjetasOk
	 *            the tarjetasOk to set
	 */
	public void setTarjetasOk(List<MedioDePagoBilleteraDTO> tarjetasOk) {
		this.tarjetasOk = tarjetasOk;
	}

}