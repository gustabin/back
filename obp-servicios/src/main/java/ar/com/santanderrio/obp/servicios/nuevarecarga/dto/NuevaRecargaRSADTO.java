/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

/**
 * The Class NuevaRecargaRSADTO.
 *
 * @author florencia.n.martinez
 */
public class NuevaRecargaRSADTO extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The operacion. */
	private int operacion;

	/** The monto. */
	private Long monto;

	/** Se guarda en sesion si hay un desafio en curso. */
	private boolean existeDesafioEnCurso = false;

	/** The metodo desafio. */
	private AutentificacionDTO desafio;

	/** The tipo desafio. */
	private TipoDesafioEnum tipoDesafio;

	/** The tiene celular my A. Se usa en RSA. */
	private Boolean tieneCelularMyA = Boolean.FALSE;

	/** The cuenta origen. */
	private CuentaOrigenRSADTO cuentaOrigen;

	/** The nombre destinatario. */
	private String nombreDestinatario;
	
	/** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioClave;
    
    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioToken;
    
    private String codigoPagoElectronico;
    
    private boolean isFromCalendario;
    

	/**
	 * Instantiates a new nueva recarga RSADTO.
	 */
	public NuevaRecargaRSADTO() {
		super(OperacionesRSAEnum.NUEVO_PAGO);
	}

	/**
	 * Instantiates a new nueva recarga RSADTO.
	 *
	 * @param operacion
	 *            the operacion
	 * @param monto
	 *            the monto
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param nombreDestinatario
	 *            the nombre destinatario
	 * @param tieneCelularMyA
	 *            the tiene celular my A
	 */
	public NuevaRecargaRSADTO(int operacion, Long monto, CuentaOrigenRSADTO cuentaOrigen, String nombreDestinatario,
			Boolean tieneCelularMyA, boolean isFromCalendario) {
		this();
		this.operacion = operacion;
		this.monto = monto;
		this.cuentaOrigen = cuentaOrigen;
		this.nombreDestinatario = nombreDestinatario;
		this.tieneCelularMyA = tieneCelularMyA;
		this.isFromCalendario = isFromCalendario;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public Long getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(Long monto) {
		this.monto = monto;
	}

	/**
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public int getOperacion() {
		return operacion;
	}

	/**
	 * Sets the operacion.
	 *
	 * @param operacion
	 *            the operacion to set
	 */
	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}

	/**
	 * Checks if is existe desafio en curso.
	 *
	 * @return the existeDesafioEnCurso
	 */
	public boolean isExisteDesafioEnCurso() {
		return existeDesafioEnCurso;
	}

	/**
	 * Sets the existe desafio en curso.
	 *
	 * @param existeDesafioEnCurso
	 *            the existeDesafioEnCurso to set
	 */
	public void setExisteDesafioEnCurso(boolean existeDesafioEnCurso) {
		this.existeDesafioEnCurso = existeDesafioEnCurso;
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
	 * Gets the tipo desafio.
	 *
	 * @return the tipoDesafio
	 */
	public TipoDesafioEnum getTipoDesafio() {
		return tipoDesafio;
	}

	/**
	 * Sets the tipo desafio.
	 *
	 * @param tipoDesafio
	 *            the tipoDesafio to set
	 */
	public void setTipoDesafio(TipoDesafioEnum tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

	/**
	 * Gets the tiene celular mya.
	 *
	 * @return the tiene celular mya
	 */
	public Boolean getTieneCelularMyA() {
		return tieneCelularMyA;
	}

	/**
	 * Sets the tiene celular mya.
	 *
	 * @param tieneCelularMyA
	 *            the new tiene celular mya
	 */
	public void setTieneCelularMyA(Boolean tieneCelularMyA) {
		this.tieneCelularMyA = tieneCelularMyA;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuentaOrigen
	 */
	public CuentaOrigenRSADTO getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the cuentaOrigen to set
	 */
	public void setCuentaOrigen(CuentaOrigenRSADTO cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the nombre destinatario.
	 *
	 * @return the nombreDestinatario
	 */
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	/**
	 * Sets the nombre destinatario.
	 *
	 * @param nombreDestinatario
	 *            the nombreDestinatario to set
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
	
	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}
	
	public String getCodigoPagoElectronico() {
		return codigoPagoElectronico;
	}

	public void setCodigoPagoElectronico(String codigoPagoElectronico) {
		this.codigoPagoElectronico = codigoPagoElectronico;
	}
	
	
	public boolean isFromCalendario() {
		return isFromCalendario;
	}

	public void setFromCalendario(boolean isFromCalendario) {
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
		hcb.append(cuentaOrigen);
		hcb.append(desafio);
		hcb.append(existeDesafioEnCurso);
		hcb.append(monto);
		hcb.append(nombreDestinatario);
		hcb.append(operacion);
		hcb.append(tipoDesafio);
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
		NuevaRecargaRSADTO other = (NuevaRecargaRSADTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuentaOrigen, other.getCuentaOrigen());
		eb.append(desafio, other.getDesafio());
		eb.append(existeDesafioEnCurso, other.isExisteDesafioEnCurso());
		eb.append(monto, other.getMonto());
		eb.append(nombreDestinatario, other.getNombreDestinatario());
		eb.append(operacion, other.getOperacion());
		eb.append(tipoDesafio, other.getTipoDesafio());
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
		return new ToStringBuilder(this).append("operacion", operacion).append("monto", monto)
				.append("existeDesafioEnCurso", existeDesafioEnCurso).append("desafio", desafio)
				.append("tipoDesafio", tipoDesafio).append("tieneCelularMyA", tieneCelularMyA)
				.append("cuentaOrigen", cuentaOrigen).append("nombreDestinatario", nombreDestinatario).toString();
	}

}
