/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author
 *
 */
public class SimulacionFondoBancaPrivadaInEntity {

	/** The codigo operacion. */
	private String codigoOperacion;

	/** The cuenta titulo. */
	private String cuentaTitulo;

	/** The moneda. */
	private String moneda;

	/** The cuotas partes. */
	private BigDecimal cuotasPartes = BigDecimal.valueOf(0);

	/** The especie destino. */
	private String especieDestino = "";

	/** The capital. */
	private BigDecimal capital;

	/** The especie. */
	private String especie;
	private String codigoFondo;

	/** The uss racf. */
	private String ussRacf;

	/** The pass racf. */
	private String passRacf;

	/** The cliente. */
	private Cliente cliente;

	/**
	 * Gets the especie destino.
	 *
	 * @return the especie destino
	 */
	public String getEspecieDestino() {
		return especieDestino;
	}

	/**
	 * Sets the especie destino.
	 *
	 * @param especieDestino
	 *            the new especie destino
	 */
	public void setEspecieDestino(String especieDestino) {
		this.especieDestino = especieDestino;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(codigoOperacion).append(cuentaTitulo).append(moneda).append(cuotasPartes)
				.append(especieDestino).append(capital).append(especie).append(ussRacf).append(passRacf).append(cliente)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
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

		SimulacionFondoBancaPrivadaInEntity other = (SimulacionFondoBancaPrivadaInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append(codigoOperacion, other.codigoOperacion)
				.append(cuentaTitulo, other.cuentaTitulo).append(moneda, other.moneda)
				.append(cuotasPartes, other.cuotasPartes).append(especieDestino, other.especieDestino)
				.append(capital, other.capital).append(especie, other.especie).append(ussRacf, other.ussRacf)
				.append(passRacf, other.passRacf).append(cliente, other.cliente);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("codigoOperacion", codigoOperacion).append("cuentaTitulo", cuentaTitulo)
				.append("moneda", moneda).append("cuotasPartes", cuotasPartes).append("especieDestino", especieDestino)
				.append("capital", capital).append("especie", especie).append("cliente", cliente).toString();
	}

	/**
	 * Gets the codigo operacion.
	 *
	 * @return the codigo operacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * Sets the codigo operacion.
	 *
	 * @param codigoOperacion
	 *            the new codigo operacion
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
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
	 * Gets the cuotas partes.
	 *
	 * @return the cuotas partes
	 */
	public BigDecimal getCuotasPartes() {
		return cuotasPartes;
	}

	/**
	 * Sets the cuotas partes.
	 *
	 * @param cuotasPartes
	 *            the new cuotas partes
	 */
	public void setCuotasPartes(BigDecimal cuotasPartes) {
		this.cuotasPartes = cuotasPartes;
	}

	/**
	 * Gets the uss racf.
	 *
	 * @return the uss racf
	 */
	public String getUssRacf() {
		return ussRacf;
	}

	/**
	 * Sets the uss racf.
	 *
	 * @param ussRacf
	 *            the new uss racf
	 */
	public void setUssRacf(String ussRacf) {
		this.ussRacf = ussRacf;
	}

	/**
	 * Gets the pass racf.
	 *
	 * @return the pass racf
	 */
	public String getPassRacf() {
		return passRacf;
	}

	/**
	 * Sets the pass racf.
	 *
	 * @param passRacf
	 *            the new pass racf
	 */
	public void setPassRacf(String passRacf) {
		this.passRacf = passRacf;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public String getCodigoFondo() {
		return codigoFondo;
	}

	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}
}
