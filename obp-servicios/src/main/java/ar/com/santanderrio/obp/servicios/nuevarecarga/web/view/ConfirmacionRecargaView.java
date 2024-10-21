/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.web.view;

import java.io.Serializable;
import java.math.BigDecimal;

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

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPuntualDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Class ConfirmacionRecargaView.
 *
 * @author florencia.n.martinez
 */
@XmlRootElement(name = "comprobanteFeedback", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConfirmacionRecargaView extends ComprobanteFeedbackView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo empresa. */
	private String codigoEmpresa;

	/** The nombre empresa. */
	private String nombreEmpresa;

	/** The identificacion cliente. */
	private String identificacionCliente;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The cbu cuenta. */
	private String cbuCuenta;

	/** The saldo cuenta. */
	private String saldoCuenta;

	/** The monto id. */
	private String montoId;

	/** The monto. */
	private String monto;

	/** The desafio. */
	private AutentificacionDTO desafio;
	
	/** The is from calendario. */
	private Boolean isFromCalendario;
	
	private String aliasCelular;
	
	private Boolean vieneDeAgenda = Boolean.FALSE;

	/**
	 * Gets the codigo empresa.
	 *
	 * @return the codigo empresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * Sets the codigo empresa.
	 *
	 * @param codigoEmpresa
	 *            the new codigo empresa
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * Gets the identificacion cliente.
	 *
	 * @return the identificacion cliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 * Sets the identificacion cliente.
	 *
	 * @param identificacionCliente
	 *            the new identificacion cliente
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the monto id.
	 *
	 * @return the monto id
	 */
	public String getMontoId() {
		return montoId;
	}

	/**
	 * Sets the monto id.
	 *
	 * @param montoId
	 *            the new monto id
	 */
	public void setMontoId(String montoId) {
		this.montoId = montoId;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
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
	 *            the desafio to set
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Gets the nombre empresa.
	 *
	 * @return the nombre empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre empresa.
	 *
	 * @param nombreEmpresa
	 *            the new nombre empresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Gets the cbu cuenta.
	 *
	 * @return the cbuCuenta
	 */
	public String getCbuCuenta() {
		return cbuCuenta;
	}

	/**
	 * Sets the cbu cuenta.
	 *
	 * @param cbuCuenta
	 *            the cbuCuenta to set
	 */
	public void setCbuCuenta(String cbuCuenta) {
		this.cbuCuenta = cbuCuenta;
	}

	/**
	 * Gets the saldo cuenta.
	 *
	 * @return the saldoCuenta
	 */
	public String getSaldoCuenta() {
		return saldoCuenta;
	}

	/**
	 * Sets the saldo cuenta.
	 *
	 * @param saldoCuenta
	 *            the saldoCuenta to set
	 */
	public void setSaldoCuenta(String saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	/**
	 * Genera el comprobante resultante.
	 *
	 * @param nroControl
	 *            the nro control
	 * @param nroComprobante
	 *            the nro comprobante
	 */
	public void generarComprobante(String nroControl, String nroComprobante) {
		super.setNroControl(nroControl);
		super.setNroDeComprobante(nroComprobante);
		super.generarFechaHoraComprobante();
	}

	/**
	 * transforma a DTO.
	 *
	 * @return the pago puntual DTO
	 */
	public PagoPuntualDTO obtenerPagoPuntualDTO() {
		PagoPuntualDTO pagoPuntualDTO = new PagoPuntualDTO();
		pagoPuntualDTO.setCodigoEmpresa(this.codigoEmpresa);
		pagoPuntualDTO.setIdentificacion(this.identificacionCliente);
		pagoPuntualDTO.setNumeroCuenta(this.cbuCuenta);
		pagoPuntualDTO.setMontoId(this.montoId);
		pagoPuntualDTO.setMonto(this.getMonto());
		return pagoPuntualDTO;
	}

    /**
	 * Obtener monto feedback.
	 *
	 * @return the string
	 */
    public String obtenerMontoFeedback() {
        return ISBANStringUtils.formatearSaldoSinAbsConDivisa(new BigDecimal(this.montoId), DivisaEnum.PESO);
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
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cbuCuenta);
		hcb.append(codigoEmpresa);
		hcb.append(identificacionCliente);
		hcb.append(numeroCuenta);
		hcb.append(desafio);
		hcb.append(montoId);
		hcb.append(monto);
		hcb.append(nombreEmpresa);
		hcb.append(saldoCuenta);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfirmacionRecargaView other = (ConfirmacionRecargaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cbuCuenta, other.getCbuCuenta());
		eb.append(codigoEmpresa, other.getCodigoEmpresa());
		eb.append(identificacionCliente, other.getIdentificacionCliente());
		eb.append(numeroCuenta, other.getNumeroCuenta());
		eb.append(desafio, other.getDesafio());
		eb.append(montoId, other.getMonto());
		eb.append(monto, other.getMontoId());
		eb.append(nombreEmpresa, other.getNombreEmpresa());
		eb.append(saldoCuenta, other.getSaldoCuenta());
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
		return new ToStringBuilder(this).append("monto", monto).append("codigoEmpresa", codigoEmpresa)
				.append("identificacionCliente", identificacionCliente).append("montoId", montoId)
				.append("numeroCuenta", numeroCuenta).append("nombreEmpresa", nombreEmpresa)
				.append("cbuCuenta", cbuCuenta).append("saldoCuenta", saldoCuenta).toString();
	}

	public String getAliasCelular() {
		return aliasCelular;
	}

	public void setAliasCelular(String aliasCelular) {
		this.aliasCelular = aliasCelular;
	}

	public Boolean getVieneDeAgenda() {
		return vieneDeAgenda;
	}

	public void setVieneDeAgenda(Boolean vieneDeAgenda) {
		this.vieneDeAgenda = vieneDeAgenda;
	}
		
}
