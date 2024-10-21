/*
 * 
 * @author marcelo.ruiz
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.entities;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;

/**
 * The Class AdhesionPagoAutomatico.
 *
 * @author marcelo.ruiz
 */

public class AdhesionPagoAutomatico extends ComprobanteFeedbackView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8066900123547866985L;

	/** The fiid. */
	private String fiid;

	/** El CBU de a cuenta seleccionada. */
	private String cuentaSeleccionada;

	/** The importe limite. */
	private String importeLimite;

	/** The desafio. */
	private AutentificacionDTO desafio;

	/** The codigo pago electronico. */
	private String codigoPagoElectronico;

	/** The contador intentos. */
	private ContadorIntentos contadorIntentos;

	/** The isFromCalendario. */
	private Boolean isFromCalendario;

	/** The nombre empresa. */
	private String nombreEmpresa;

	/** The aux importe. */
	private String auxImporte;

	private EmisionOfertaIntegrada emisionGastosProtegido;

	private String nroPolizaGastosProtegido;

	private String alias;

	private String numero;

	private String descripcionTipoCuenta;
	
	/**
	 * Gets the contador intentos.
	 *
	 * @return the contadorIntentos
	 */
	public ContadorIntentos getContadorIntentos() {
		return contadorIntentos;
	}

	/**
	 * Gets the checks if is from calendario.
	 *
	 * @return the checks if is from calendario
	 */
	public Boolean getIsFromCalendario() {
		return isFromCalendario;
	}

	/**
	 * Sets the checks if is from calendario.
	 *
	 * @param isFromCalendario
	 *            the new checks if is from calendario
	 */
	public void setIsFromCalendario(Boolean isFromCalendario) {
		this.isFromCalendario = isFromCalendario;
	}

	/**
	 * Sets the contador intentos.
	 *
	 * @param contadorIntentos
	 *            the contadorIntentos to set
	 */
	public void setContadorIntentos(ContadorIntentos contadorIntentos) {
		this.contadorIntentos = contadorIntentos;
	}

	/**
	 * Gets the codigo pago electronico.
	 *
	 * @return the codigo pago electronico
	 */
	public String getCodigoPagoElectronico() {
		return codigoPagoElectronico;
	}

	/**
	 * Sets the codigo pago electronico.
	 *
	 * @param codigoPagoElectronico
	 *            the new codigo pago electronico
	 */
	public void setCodigoPagoElectronico(String codigoPagoElectronico) {
		this.codigoPagoElectronico = codigoPagoElectronico;
	}

	/**
	 * Gets the fiid.
	 *
	 * @return the fiid
	 */
	public String getFiid() {
		return fiid;
	}

	/**
	 * Sets the fiid.
	 *
	 * @param fiid
	 *            the new fiid
	 */
	public void setFiid(String fiid) {
		this.fiid = StringUtils.rightPad(fiid, 4, StringUtils.EMPTY);
	}

	/**
	 * Gets the cuenta seleccionada.
	 *
	 * @return the cuenta seleccionada
	 */
	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * Sets the cuenta seleccionada.
	 *
	 * @param cuentaSeleccionada
	 *            the new cuenta seleccionada
	 */
	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	/**
	 * Gets the importe limite.
	 *
	 * @return the importe limite
	 */
	public String getImporteLimite() {
		return importeLimite;
	}

	/**
	 * Sets the importe limite.
	 *
	 * @param importeLimite
	 *            the new importe limite
	 */
	public void setImporteLimite(String importeLimite) {
		this.importeLimite = importeLimite;
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}
	
	/**
	 * Gets the aux importe.
	 *
	 * @return the aux importe
	 */
	public String getAuxImporte() {
		return auxImporte;
	}

	/**
	 * Sets the aux importe.
	 *
	 * @param auxImporte
	 *            the new aux importe
	 */
	public void setAuxImporte(String auxImporte) {
		this.auxImporte = auxImporte;
	}

	/**
	 * Gets the nombre empresa.
	 *
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre empresa.
	 *
	 * @param nombreEmpresa
	 *            the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public EmisionOfertaIntegrada getEmisionGastosProtegido() {
		return emisionGastosProtegido;
	}

	public void setEmisionGastosProtegido(EmisionOfertaIntegrada emisionGastosProtegido) {
		this.emisionGastosProtegido = emisionGastosProtegido;
	}

	public String getNroPolizaGastosProtegido() {
		return nroPolizaGastosProtegido;
	}

	public void setNroPolizaGastosProtegido(String nroPolizaGastosProtegido) {
		this.nroPolizaGastosProtegido = nroPolizaGastosProtegido;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoPagoElectronico);
		hcb.append(contadorIntentos);
		hcb.append(cuentaSeleccionada);
		hcb.append(desafio);
		hcb.append(fiid);
		hcb.append(importeLimite);
		hcb.append(isFromCalendario);
		hcb.append(nombreEmpresa);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdhesionPagoAutomatico other = (AdhesionPagoAutomatico) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigoPagoElectronico, other.getCodigoPagoElectronico());
		eb.append(contadorIntentos, other.getContadorIntentos());
		eb.append(cuentaSeleccionada, other.getCuentaSeleccionada());
		eb.append(desafio, other.getDesafio());
		eb.append(fiid, other.getFiid());
		eb.append(importeLimite, other.getImporteLimite());
		eb.append(isFromCalendario, other.getIsFromCalendario());
		eb.append(nombreEmpresa, other.getNombreEmpresa());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("fiid", fiid).append("cuentaSeleccionada", cuentaSeleccionada)
				.append("importeLimite", importeLimite).append("desafio", desafio)
				.append("codigoPagoElectronico", codigoPagoElectronico).append("contadorIntentos", contadorIntentos)
				.append("isFromCalendario", isFromCalendario).append("nombreEmpresa", nombreEmpresa).toString();
	}

}
