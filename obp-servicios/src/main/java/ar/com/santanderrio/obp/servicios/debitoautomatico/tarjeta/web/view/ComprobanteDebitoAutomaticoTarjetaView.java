/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DebitoAutomaticoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Class ComprobanteDebitoAutomaticoTarjetaView.
 *
 * @author florencia.n.martinez
 */
@XmlRootElement(name = "comprobanteDebitoTarjeta", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ComprobanteDebitoAutomaticoTarjetaView extends ComprobanteFeedbackView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "dd/MM/yyyy - HH:MM";

	/** The empresa. */
	private String empresa;

	/** The medio pago. */
	private String medioPago;

	/** The nro cuenta cliente. */
	private String nroCuentaCliente;

	/** The cuit empresa. */
	private String cuitEmpresa;

	/**
	 * Instantiates a new comprobante debito automatico tarjeta view.
	 */
	public ComprobanteDebitoAutomaticoTarjetaView() {
		super();
	}

	/**
	 * Instantiates a new comprobante debito automatico tarjeta view.
	 *
	 * @param inView
	 *            the in view
	 * @param dto
	 *            the dto
	 */
	public ComprobanteDebitoAutomaticoTarjetaView(HashDebitoAutomaticoTarjetaView inView,
			DebitoAutomaticoTarjetaDTO dto) {
		this.empresa = inView.getNombreFantasia();
		this.medioPago = inView.getNumeroTarjetaEnmascarado();
		this.nroCuentaCliente = inView.getNroIdentificacion();
		this.cuitEmpresa = inView.getCuit();
		this.setNroDeComprobante(dto.getComprobante());
		this.setFechaHora(this.obtenerFechaHora(dto.getFecha()));
		this.setMensajeFeedback(dto.getMensajeFeedback());
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the medio pago.
	 *
	 * @return the medioPago
	 */
	public String getMedioPago() {
		return medioPago;
	}

	/**
	 * Sets the medio pago.
	 *
	 * @param medioPago
	 *            the medioPago to set
	 */
	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	/**
	 * Gets the nro cuenta cliente.
	 *
	 * @return the nroCuentaCliente
	 */
	public String getNroCuentaCliente() {
		return nroCuentaCliente;
	}

	/**
	 * Sets the nro cuenta cliente.
	 *
	 * @param nroCuentaCliente
	 *            the nroCuentaCliente to set
	 */
	public void setNroCuentaCliente(String nroCuentaCliente) {
		this.nroCuentaCliente = nroCuentaCliente;
	}

	/**
	 * Gets the cuit empresa.
	 *
	 * @return the cuitEmpresa
	 */
	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	/**
	 * Sets the cuit empresa.
	 *
	 * @param cuitEmpresa
	 *            the cuitEmpresa to set
	 */
	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cuitEmpresa);
		hcb.append(empresa);
		hcb.append(medioPago);
		hcb.append(nroCuentaCliente);
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
		ComprobanteDebitoAutomaticoTarjetaView other = (ComprobanteDebitoAutomaticoTarjetaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuitEmpresa, other.getCuitEmpresa());
		eb.append(empresa, other.getEmpresa());
		eb.append(medioPago, other.getMedioPago());
		eb.append(nroCuentaCliente, other.getNroCuentaCliente());
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
		return new ToStringBuilder(this).append("empresa", empresa).append("medioPago", medioPago)
				.append("nroCuentaCliente", nroCuentaCliente).append("cuitEmpresa", cuitEmpresa).toString();
	}

	/**
	 * Obtiene la fecha y la hora.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	private String obtenerFechaHora(Date fecha) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return format.format(fecha);
	}

}
