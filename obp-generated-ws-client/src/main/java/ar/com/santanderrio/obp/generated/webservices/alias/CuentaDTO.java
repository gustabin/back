/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class CuentaDTO.
 *
 * @author leonardo.medina
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class CuentaDTO {

	/** The descripcion. */
	private String descripcion;

	/** The disponible. */
	private Double disponible;

	/** The limite. */
	private Double limite;

	/** The moneda. */
	private MonedaDTO moneda;

	/** The numero. */
	private String numero;

	/** The numero CBU. */
	private String numeroCBU;

	/** The saldo. */
	private Double saldo;

	/** The tipo. */
	private TipoCuentaDTO tipo;

	/** The fiid origen link. */
	private String fiidOrigenLink;

	/**
	 * Instantiates a new cuenta DTO.
	 */
	public CuentaDTO() {
		super();
	}

	/**
	 * Instantiates a new cuenta DTO.
	 *
	 * @param codMoneda
	 *            the cod moneda
	 * @param numero
	 *            the numero
	 * @param cbu
	 *            the cbu
	 * @param codTipoCuenta
	 *            the cod tipo cuenta
	 */
	public CuentaDTO(String codMoneda, String numero, String cbu, String codTipoCuenta) {
		super();
		this.moneda = new MonedaDTO(codMoneda);
		this.numero = numero;
		this.numeroCBU = cbu;
		this.tipo = new TipoCuentaDTO(codTipoCuenta);
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the disponible.
	 *
	 * @return the disponible
	 */
	public Double getDisponible() {
		return disponible;
	}

	/**
	 * Sets the disponible.
	 *
	 * @param disponible
	 *            the disponible to set
	 */
	public void setDisponible(Double disponible) {
		this.disponible = disponible;
	}

	/**
	 * Gets the limite.
	 *
	 * @return the limite
	 */
	public Double getLimite() {
		return limite;
	}

	/**
	 * Sets the limite.
	 *
	 * @param limite
	 *            the limite to set
	 */
	public void setLimite(Double limite) {
		this.limite = limite;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public MonedaDTO getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(MonedaDTO moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the numero CBU.
	 *
	 * @return the numeroCBU
	 */
	public String getNumeroCBU() {
		return numeroCBU;
	}

	/**
	 * Sets the numero CBU.
	 *
	 * @param numeroCBU
	 *            the numeroCBU to set
	 */
	public void setNumeroCBU(String numeroCBU) {
		this.numeroCBU = numeroCBU;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public TipoCuentaDTO getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(TipoCuentaDTO tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the fiid origen link.
	 *
	 * @return the fiidOrigenLink
	 */
	public String getFiidOrigenLink() {
		return fiidOrigenLink;
	}

	/**
	 * Sets the fiid origen link.
	 *
	 * @param fiidOrigenLink
	 *            the fiidOrigenLink to set
	 */
	public void setFiidOrigenLink(String fiidOrigenLink) {
		this.fiidOrigenLink = fiidOrigenLink;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(moneda);
		hcb.append(numero);
		hcb.append(numeroCBU);
		hcb.append(tipo);
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
		CuentaDTO other = (CuentaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(moneda, other.getMoneda());
		eb.append(numero, other.getNumero());
		eb.append(numeroCBU, other.getNumeroCBU());
		eb.append(tipo, other.getTipo());
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
		return new ToStringBuilder(this).append("moneda", moneda).append("numero", numero)
				.append("numeroCBU", numeroCBU).append("tipo", tipo).toString();
	}

}
