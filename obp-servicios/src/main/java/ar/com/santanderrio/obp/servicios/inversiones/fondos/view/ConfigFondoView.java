/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Vista Configurar suscripcion.
 * 
 * @author pablo.d.gargaglione
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigFondoView {

	/** The cuentas. */
	private List<CuentasAdhesionDebitoView> cuentasDebito;

	/** The legales. */
	private String legales;

	/** The contratoAceptado. */
	private boolean contratoAceptado;

	/** The moneda. */
	private String moneda;

	/** The mensaje sin cuentas. */
	private String mensajeSinCuentas;

	/** The informacion gastos. */
	private String informacionGastos;

	/** The tiene gastos. */
	private String tieneGastos;

	/** The id mensaje legal. */
	private String idMensajeGastos;
	
	/** The codigo fondo */
	private String codigoFondo;
	
	/** The url reglamento */
	private String urlReglamento;

	private DatosCotizacionFondoView informacion;

	/**
	 * @return the urlReglamento
	 */
	public String getUrlReglamento() {
		return urlReglamento;
	}

	/**
	 * @param urlReglamento the urlReglamento to set
	 */
	public void setUrlReglamento(String urlReglamento) {
		this.urlReglamento = urlReglamento;
	}

	/**
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * @param codigoFondo the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cuentasDebito).append(legales).append(contratoAceptado).append(moneda)
				.append(mensajeSinCuentas).append(informacionGastos).append(tieneGastos).append(idMensajeGastos)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		ConfigFondoView other = (ConfigFondoView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(cuentasDebito, other.getCuentasDebito()).append(legales, other.getLegales())
				.append(contratoAceptado, other.isContratoAceptado())
				.append(mensajeSinCuentas, other.getMensajeSinCuentas()).append(moneda, other.getMoneda())
				.append(informacionGastos, other.getInformacionGastos()).append(tieneGastos, other.getTieneGastos())
				.append(idMensajeGastos, other.getIdMensajeGastos()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cuentasDebito", cuentasDebito).append("legales", legales)
				.append("contratoAceptado", contratoAceptado).append("moneda", moneda)
				.append("mensajeSinCuentas", mensajeSinCuentas).append("informacionGastos", informacionGastos)
				.append("tieneGastos", tieneGastos).append("idMensajeGastos", idMensajeGastos).toString();
	}

	/**
	 * Gets the cuentas debito.
	 *
	 * @return the cuentas debito
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDebito() {
		return cuentasDebito;
	}

	/**
	 * Sets the cuentas debito.
	 *
	 * @param cuentasDebito
	 *            the new cuentas debito
	 */
	public void setCuentasDebito(List<CuentasAdhesionDebitoView> cuentasDebito) {
		this.cuentasDebito = cuentasDebito;
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
	 * Checks if is contrato aceptado.
	 *
	 * @return true, if is contrato aceptado
	 */
	public boolean isContratoAceptado() {
		return contratoAceptado;
	}

	/**
	 * Sets the contrato aceptado.
	 *
	 * @param contratoAceptado
	 *            the new contrato aceptado
	 */
	public void setContratoAceptado(boolean contratoAceptado) {
		this.contratoAceptado = contratoAceptado;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the mensaje sin cuentas.
	 *
	 * @return the mensaje sin cuentas
	 */
	public String getMensajeSinCuentas() {
		return mensajeSinCuentas;
	}

	/**
	 * Sets the mensaje sin cuentas.
	 *
	 * @param mensajeSinCuentas
	 *            the new mensaje sin cuentas
	 */
	public void setMensajeSinCuentas(String mensajeSinCuentas) {
		this.mensajeSinCuentas = mensajeSinCuentas;
	}

	/**
	 * Gets the informacion gastos.
	 *
	 * @return the informacion gastos
	 */
	public String getInformacionGastos() {
		return informacionGastos;
	}

	/**
	 * Sets the informacion gastos.
	 *
	 * @param informacionGastos
	 *            the new informacion gastos
	 */
	public void setInformacionGastos(String informacionGastos) {
		this.informacionGastos = informacionGastos;
	}

	/**
	 * Gets the id mensaje gastos.
	 *
	 * @return the id mensaje gastos
	 */
	public String getIdMensajeGastos() {
		return idMensajeGastos;
	}

	/**
	 * Sets the id mensaje gastos.
	 *
	 * @param idMensajeGastos
	 *            the new id mensaje gastos
	 */
	public void setIdMensajeGastos(String idMensajeGastos) {
		this.idMensajeGastos = idMensajeGastos;
	}

	/**
	 * Gets the tiene gastos.
	 *
	 * @return the tiene gastos
	 */
	public String getTieneGastos() {
		return tieneGastos;
	}

	/**
	 * Sets the tiene gastos.
	 *
	 * @param tieneGastos
	 *            the new tiene gastos
	 */
	public void setTieneGastos(String tieneGastos) {
		this.tieneGastos = tieneGastos;
	}

	public DatosCotizacionFondoView getInformacion() {
		return informacion;
	}

	public void setInformacion(DatosCotizacionFondoView informacion) {
		this.informacion = informacion;
	}
}
