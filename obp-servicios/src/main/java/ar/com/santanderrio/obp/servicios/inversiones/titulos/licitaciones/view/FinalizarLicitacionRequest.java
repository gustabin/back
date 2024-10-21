/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class FinalizarLicitacionRequest.
 *
 * @author B039920 Request para finalizar la suscripcion a una licitacion
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FinalizarLicitacionRequest {

	/** Monto a debitar. */
	@NotNull
	private String montoDebito;

	/** Numero de orden. */
	@NotNull
	private String numeroDeOrden;

	/** Cuenta operativa. */
	@NotNull
	private String cuentaOperativa;

	/** Tipo cuenta operativa. */
	@NotNull
	private String tipoCuentaOperativa;

	/** Moneda. */
	@NotNull
	private String moneda;

	/** Descripcion Especie. */
	@NotNull
	private String descripcionEspecie;

	/**
	 * Gets the monto debito.
	 *
	 * @return the monto debito
	 */
	public String getMontoDebito() {
		return montoDebito;
	}

	/**
	 * Gets the numero de orden.
	 *
	 * @return the numero de orden
	 */
	public String getNumeroDeOrden() {
		return numeroDeOrden;
	}

	/**
	 * Gets the cuenta operativa.
	 *
	 * @return the cuenta operativa
	 */
	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * Gets the tipo cuenta operativa.
	 *
	 * @return the tipo cuenta operativa
	 */
	public String getTipoCuentaOperativa() {
		return tipoCuentaOperativa;
	}

	/**
	 * Sets the monto debito.
	 *
	 * @param montoDebito
	 *            the new monto debito
	 */
	public void setMontoDebito(String montoDebito) {
		this.montoDebito = montoDebito;
	}

	/**
	 * Sets the numero de orden.
	 *
	 * @param numeroDeOrden
	 *            the new numero de orden
	 */
	public void setNumeroDeOrden(String numeroDeOrden) {
		this.numeroDeOrden = numeroDeOrden;
	}

	/**
	 * Sets the cuenta operativa.
	 *
	 * @param cuentaOperativa
	 *            the new cuenta operativa
	 */
	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	/**
	 * Sets the tipo cuenta operativa.
	 *
	 * @param tipoCuentaOperativa
	 *            the new tipo cuenta operativa
	 */
	public void setTipoCuentaOperativa(String tipoCuentaOperativa) {
		this.tipoCuentaOperativa = tipoCuentaOperativa;
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
	 * Gets the descripcion especie.
	 *
	 * @return the descripcion especie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the new descripcion especie
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

}