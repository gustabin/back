/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.entities;

import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinoVinculado;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMsgMultiple;

/**
 * The Class UpdateMensajesMyaSuscripcion.
 */
public class UpdateMensajesMyaSuscripcion {

	/** The mya cod operacion enum. */
	private MyaCodOperacionEnum myaCodOperacionEnum;

	/** The numero mensaje suscripcion. */
	private String numeroMensajeSuscripcion;

	/** The numero suscripcion. */
	private String numeroSuscripcion;

	/** The mya destino vinculado. */
	private MyaDestinoVinculado myaDestinoVinculado;

	/** The codigo frecuencia suscripcion. */
	private String codigoFrecuenciaSuscripcion;

	/** The codigo dap suscripcion. */
	private String codigoDapSuscripcion;

	/** The atributo vinculado clave. */
	private String atributoVinculadoClave;

	/** The atributo vinculado valor. */
	private String atributoVinculadoValor;

	/** The lista msg multiples. */
	private List<MyaMsgMultiple> listaMsgMultiples;

	/**
	 * Gets the mya cod operacion enum.
	 *
	 * @return the mya cod operacion enum
	 */
	public MyaCodOperacionEnum getMyaCodOperacionEnum() {
		return myaCodOperacionEnum;
	}

	/**
	 * Sets the mya cod operacion enum.
	 *
	 * @param myaCodOperacionEnum
	 *            the new mya cod operacion enum
	 */
	public void setMyaCodOperacionEnum(MyaCodOperacionEnum myaCodOperacionEnum) {
		this.myaCodOperacionEnum = myaCodOperacionEnum;
	}

	/**
	 * Gets the numero mensaje suscripcion.
	 *
	 * @return the numero mensaje suscripcion
	 */
	public String getNumeroMensajeSuscripcion() {
		return numeroMensajeSuscripcion;
	}

	/**
	 * Sets the numero mensaje suscripcion.
	 *
	 * @param numeroMensajeSuscripcion
	 *            the new numero mensaje suscripcion
	 */
	public void setNumeroMensajeSuscripcion(String numeroMensajeSuscripcion) {
		this.numeroMensajeSuscripcion = numeroMensajeSuscripcion;
	}

	/**
	 * Gets the numero suscripcion.
	 *
	 * @return the numero suscripcion
	 */
	public String getNumeroSuscripcion() {
		return numeroSuscripcion;
	}

	/**
	 * Sets the numero suscripcion.
	 *
	 * @param numeroSuscripcion
	 *            the new numero suscripcion
	 */
	public void setNumeroSuscripcion(String numeroSuscripcion) {
		this.numeroSuscripcion = numeroSuscripcion;
	}

	/**
	 * Gets the mya destino vinculado.
	 *
	 * @return the mya destino vinculado
	 */
	public MyaDestinoVinculado getMyaDestinoVinculado() {
		return myaDestinoVinculado;
	}

	/**
	 * Sets the mya destino vinculado.
	 *
	 * @param myaDestinoVinculado
	 *            the new mya destino vinculado
	 */
	public void setMyaDestinoVinculado(MyaDestinoVinculado myaDestinoVinculado) {
		this.myaDestinoVinculado = myaDestinoVinculado;
	}

	/**
	 * Gets the codigo frecuencia suscripcion.
	 *
	 * @return the codigo frecuencia suscripcion
	 */
	public String getCodigoFrecuenciaSuscripcion() {
		return codigoFrecuenciaSuscripcion;
	}

	/**
	 * Sets the codigo frecuencia suscripcion.
	 *
	 * @param codigoFrecuenciaSuscripcion
	 *            the new codigo frecuencia suscripcion
	 */
	public void setCodigoFrecuenciaSuscripcion(String codigoFrecuenciaSuscripcion) {
		this.codigoFrecuenciaSuscripcion = codigoFrecuenciaSuscripcion;
	}

	/**
	 * Gets the codigo dap suscripcion.
	 *
	 * @return the codigo dap suscripcion
	 */
	public String getCodigoDapSuscripcion() {
		return codigoDapSuscripcion;
	}

	/**
	 * Sets the codigo dap suscripcion.
	 *
	 * @param codigoDapSuscripcion
	 *            the new codigo dap suscripcion
	 */
	public void setCodigoDapSuscripcion(String codigoDapSuscripcion) {
		this.codigoDapSuscripcion = codigoDapSuscripcion;
	}

	/**
	 * Gets the atributo vinculado clave.
	 *
	 * @return the atributo vinculado clave
	 */
	public String getAtributoVinculadoClave() {
		return atributoVinculadoClave;
	}

	/**
	 * Sets the atributo vinculado clave.
	 *
	 * @param atributoVinculadoClave
	 *            the new atributo vinculado clave
	 */
	public void setAtributoVinculadoClave(String atributoVinculadoClave) {
		this.atributoVinculadoClave = atributoVinculadoClave;
	}

	/**
	 * Gets the atributo vinculado valor.
	 *
	 * @return the atributo vinculado valor
	 */
	public String getAtributoVinculadoValor() {
		return atributoVinculadoValor;
	}

	/**
	 * Sets the atributo vinculado valor.
	 *
	 * @param atributoVinculadoValor
	 *            the new atributo vinculado valor
	 */
	public void setAtributoVinculadoValor(String atributoVinculadoValor) {
		this.atributoVinculadoValor = atributoVinculadoValor;
	}

	/**
	 * Gets the lista msg multiples.
	 *
	 * @return the lista msg multiples
	 */
	public List<MyaMsgMultiple> getListaMsgMultiples() {
		return listaMsgMultiples;
	}

	/**
	 * Sets the lista msg multiples.
	 *
	 * @param listaMsgMultiples
	 *            the new lista msg multiples
	 */
	public void setListaMsgMultiples(List<MyaMsgMultiple> listaMsgMultiples) {
		this.listaMsgMultiples = listaMsgMultiples;
	}

}
