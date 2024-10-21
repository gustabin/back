/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by pablo.martin.gore on 8/5/2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TarjetasView {

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The alias. */
	private String alias;

	/** The marca. */
	private String marca;

	/** The tarjeta activa. */
	String tarjetaActiva;

	/** The fechas. */
	List<Integer> fechas;

	/** The id. */
	Integer id;

	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public List<Integer> getFechas() {
		return fechas;
	}

	/**
	 * Sets the fechas.
	 *
	 * @param fechas
	 *            the new fechas
	 */
	public void setFechas(List<Integer> fechas) {
		this.fechas = fechas;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the tarjeta activa.
	 *
	 * @return the tarjetaActiva
	 */
	public String getTarjetaActiva() {
		return tarjetaActiva;
	}

	/**
	 * Sets the tarjeta activa.
	 *
	 * @param tarjetaActiva
	 *            the tarjetaActiva to set
	 */
	public void setTarjetaActiva(String tarjetaActiva) {
		this.tarjetaActiva = tarjetaActiva;
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
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TarjetasView [numeroCuenta=" + numeroCuenta + ", alias=" + alias + ", marca=" + marca
				+ ", tarjetaActiva=" + tarjetaActiva + ", fechas=" + fechas + ", id=" + id + "]";
	}
}
