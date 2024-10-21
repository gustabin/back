/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
/**
 * The Class AdhesionDebitoAutomaticoView.
 *
 * @author marcelo.ruiz
 */
public class AdhesionDebitoAutomaticoEnCuentaView {

	/** The fiid. */
	private String fiid;

	/** The importe limite. */
	@NotNull
	@Pattern(regexp = "[0-9]*", message = "{validation.debitoautomatico.importeLimite}")
	private String importeLimite;

	/** The codigo pago electronico. */
	@NotNull
	@Size(min = 1, max = 22, message = "{validation.debitoautomatico.codigoPagoElectronico}")
	private String codigoPagoElectronico;

	/** The cuenta seleccionada. */
	@NotNull
	private String cuentaSeleccionada;

	/** The cuit. */
	private String cuit;

	/** The nombre servicio empresa. */
	private String nombreEmpresa;

	/** The nro partida servicio empresa. */
	private String nroPartidaServicioEmpresa;

	/** The limite adhesion. */
	private String limiteAdhesion;

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The sucursal cuenta debito. */
	private String sucursalCuentaDebito;

	/** The nro cuenta producto debito. */
	private String nroCuentaProductoDebito;

	/** The nro orden firmante. */
	private String nroOrdenFirmante;

	/** The nro de comprobante. */
	private String nroDeComprobante;

	/** The fecha hora. */
	private String fechaHora;

	/** The mensaje feedback. */
	private String mensajeFeedback;

	/** The legales SEUO. */
	private String legalesSEUO;

	/** The reintentar. */
	private String reintentar;
	
	private String nombreMedioDePago;
	
	private String medioPago;
	
	private boolean isFromAdhesionDebitoAutomatico;

	private EmisionOfertaIntegrada emisionGastosProtegido;

	private String nroPolizaGastosProtegido;

	public boolean getisFromAdhesionDebitoAutomatico() {
		return isFromAdhesionDebitoAutomatico;
	}

	public void setFromAdhesionDebitoAutomatico(boolean isFromAdhesionDebitoAutomatico) {
		this.isFromAdhesionDebitoAutomatico = isFromAdhesionDebitoAutomatico;
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
		this.fiid = fiid;
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
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the nombre servicio empresa.
	 *
	 * @return the nombre servicio empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre servicio empresa.
	 *
	 * @param nombreEmpresa
	 *            the new nombre servicio empresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Gets the nro partida servicio empresa.
	 *
	 * @return the nro partida servicio empresa
	 */
	public String getNroPartidaServicioEmpresa() {
		return nroPartidaServicioEmpresa;
	}

	/**
	 * Sets the nro partida servicio empresa.
	 *
	 * @param nroPartidaServicioEmpresa
	 *            the new nro partida servicio empresa
	 */
	public void setNroPartidaServicioEmpresa(String nroPartidaServicioEmpresa) {
		this.nroPartidaServicioEmpresa = nroPartidaServicioEmpresa;
	}

	/**
	 * Gets the limite adhesion.
	 *
	 * @return the limite adhesion
	 */
	public String getLimiteAdhesion() {
		return limiteAdhesion;
	}

	/**
	 * Sets the limite adhesion.
	 *
	 * @param limiteAdhesion
	 *            the new limite adhesion
	 */
	public void setLimiteAdhesion(String limiteAdhesion) {
		this.limiteAdhesion = limiteAdhesion;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursal cuenta debito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the new sucursal cuenta debito
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the nro cuenta producto debito.
	 *
	 * @return the nro cuenta producto debito
	 */
	public String getNroCuentaProductoDebito() {
		return nroCuentaProductoDebito;
	}

	/**
	 * Sets the nro cuenta producto debito.
	 *
	 * @param nroCuentaProductoDebito
	 *            the new nro cuenta producto debito
	 */
	public void setNroCuentaProductoDebito(String nroCuentaProductoDebito) {
		this.nroCuentaProductoDebito = nroCuentaProductoDebito;
	}

	/**
	 * Gets the nro orden firmante.
	 *
	 * @return the nro orden firmante
	 */
	public String getNroOrdenFirmante() {
		return nroOrdenFirmante;
	}

	/**
	 * Sets the nro orden firmante.
	 *
	 * @param nroOrdenFirmante
	 *            the new nro orden firmante
	 */
	public void setNroOrdenFirmante(String nroOrdenFirmante) {
		this.nroOrdenFirmante = nroOrdenFirmante;
	}

	/**
	 * Gets the nro de comprobante.
	 *
	 * @return the nro de comprobante
	 */
	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	/**
	 * Sets the nro de comprobante.
	 *
	 * @param nroDeComprobante
	 *            the new nro de comprobante
	 */
	public void setNroDeComprobante(String nroDeComprobante) {
		this.nroDeComprobante = nroDeComprobante;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the mensajeFeedback.
	 *
	 * @return the mensajeFeedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param mensajeFeedback
	 *            the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the legales SEUO.
	 *
	 * @return the legales SEUO
	 */
	public String getLegalesSEUO() {
		return legalesSEUO;
	}

	/**
	 * Sets the legales SEUO.
	 *
	 * @param legalesSEUO
	 *            the new legales SEUO
	 */
	public void setLegalesSEUO(String legalesSEUO) {
		this.legalesSEUO = legalesSEUO;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}

	public String getNombreMedioDePago() {
		return nombreMedioDePago;
	}

	public void setNombreMedioDePago(String nombreMedioDePago) {
		this.nombreMedioDePago = nombreMedioDePago;
	}

	public String getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
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

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fiid);
		hcb.append(importeLimite);
		hcb.append(codigoPagoElectronico);
		hcb.append(cuentaSeleccionada);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AdhesionDebitoAutomaticoEnCuentaView other = (AdhesionDebitoAutomaticoEnCuentaView) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(fiid, other.getFiid());
		eb.append(importeLimite, other.getImporteLimite());
		eb.append(codigoPagoElectronico, other.getCodigoPagoElectronico());
		eb.append(cuentaSeleccionada, other.getCuentaSeleccionada());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "fiid=" + fiid + ", importeLimite=" + importeLimite + ", codigoPagoElectronico=" + codigoPagoElectronico
				+ ", cuentaSeleccionada=" + cuentaSeleccionada;
	}
}
