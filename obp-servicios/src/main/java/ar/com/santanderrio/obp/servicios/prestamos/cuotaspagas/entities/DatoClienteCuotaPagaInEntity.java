/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class DatoClienteCuotaPagaInEntity.
 *
 * @author florencia.n.martinez
 */
public class DatoClienteCuotaPagaInEntity {

	/** The Constant APTM. */
	private static final String APTM = "APTM";

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant DOS_ENTERO. */
	private static final int DOS_ENTERO = 2;

	/** The Constant CUATRO_ENTERO. */
	private static final int CUATRO_ENTERO = 4;

	/** The Constant TI. */
	private static final String TI = "TI";

	/** The Constant UNO_ENTERO. */
	private static final int UNO_ENTERO = 1;

	/** The aplicacion. */
	@Pattern(regexp = "APTM")
	private String aplicacion;

	/** The sucursal. */
	@Pattern(regexp = "[0-9]{4}")
	private String sucursal;

	/** The codigo producto. */
	@Pattern(regexp = "0{2}|[a-zA-Z0-9]{2}")
	private String codigoProducto;

	/** The codigo subproducto. */
	@Pattern(regexp = "0{4}|[a-zA-Z0-9]{4}")
	private String codigoSubproducto;

	/** The numero contrato. */
	@Pattern(regexp = "[a-zA-Z0-9]{12}")
	private String numeroContrato;

	/** The calidad participacion. */
	@Pattern(regexp = "TI|[a-zA-Z0-9]{2}")
	private String calidadParticipacion;

	/** The cantidad participantes. */
	@Pattern(regexp = "0{1}|[0-9]{1}")
	private String cantidadParticipantes;

	/**
	 * Instancia un nuevo objeto DatoClienteCuotaPagaInEntity con los valores
	 * para la llamada al servicio "CNINTERVI 120".
	 *
	 * @param sucursal
	 *            the sucursal
	 * @param numeroContrato
	 *            the numero contrato
	 */
	public DatoClienteCuotaPagaInEntity(String sucursal, String numeroContrato) {
		this.aplicacion = APTM;
		this.sucursal = sucursal;
		this.codigoProducto = StringUtils.repeat(CERO_STRING, DOS_ENTERO);
		this.codigoSubproducto = StringUtils.repeat(CERO_STRING, CUATRO_ENTERO);
		this.numeroContrato = numeroContrato;
		this.calidadParticipacion = TI;
		this.cantidadParticipantes = StringUtils.repeat(CERO_STRING, UNO_ENTERO);
	}

	/**
	 * Instancia un nuevo objeto DatoClienteCuotaPagaInEntity.
	 */
	public DatoClienteCuotaPagaInEntity() {
		super();
	}

	/**
	 * Gets the aplicacion.
	 *
	 * @return the aplicacion
	 */
	public String getAplicacion() {
		return aplicacion;
	}

	/**
	 * Sets the aplicacion.
	 *
	 * @param aplicacion
	 *            the aplicacion to set
	 */
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the codigo subproducto.
	 *
	 * @return the codigoSubproducto
	 */
	public String getCodigoSubproducto() {
		return codigoSubproducto;
	}

	/**
	 * Sets the codigo subproducto.
	 *
	 * @param codigoSubproducto
	 *            the codigoSubproducto to set
	 */
	public void setCodigoSubproducto(String codigoSubproducto) {
		this.codigoSubproducto = codigoSubproducto;
	}

	/**
	 * Gets the numero contrato.
	 *
	 * @return the numeroContrato
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * Sets the numero contrato.
	 *
	 * @param numeroContrato
	 *            the numeroContrato to set
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/**
	 * Gets the calidad participacion.
	 *
	 * @return the calidadParticipacion
	 */
	public String getCalidadParticipacion() {
		return calidadParticipacion;
	}

	/**
	 * Sets the calidad participacion.
	 *
	 * @param calidadParticipacion
	 *            the calidadParticipacion to set
	 */
	public void setCalidadParticipacion(String calidadParticipacion) {
		this.calidadParticipacion = calidadParticipacion;
	}

	/**
	 * Gets the cantidad participantes.
	 *
	 * @return the cantidadParticipantes
	 */
	public String getCantidadParticipantes() {
		return cantidadParticipantes;
	}

	/**
	 * Sets the cantidad participantes.
	 *
	 * @param cantidadParticipantes
	 *            the cantidadParticipantes to set
	 */
	public void setCantidadParticipantes(String cantidadParticipantes) {
		this.cantidadParticipantes = cantidadParticipantes;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(aplicacion);
		hcb.append(calidadParticipacion);
		hcb.append(cantidadParticipantes);
		hcb.append(codigoProducto);
		hcb.append(codigoSubproducto);
		hcb.append(numeroContrato);
		hcb.append(sucursal);
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
		DatoClienteCuotaPagaInEntity other = (DatoClienteCuotaPagaInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(aplicacion, other.getAplicacion());
		eb.append(calidadParticipacion, other.getCalidadParticipacion());
		eb.append(cantidadParticipantes, other.getCantidadParticipantes());
		eb.append(codigoProducto, other.getCodigoProducto());
		eb.append(codigoSubproducto, other.getCodigoSubproducto());
		eb.append(numeroContrato, other.getNumeroContrato());
		eb.append(sucursal, other.getSucursal());
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
		return new ToStringBuilder(this).append("aplicacion", aplicacion).append("sucursal", sucursal)
				.append("codigoProducto", codigoProducto).append("codigoSubproducto", codigoSubproducto)
				.append("numeroContrato", numeroContrato).append("calidadParticipacion", calidadParticipacion)
				.append("cantidadParticipantes", cantidadParticipantes).toString();
	}
}
