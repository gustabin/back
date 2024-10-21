/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.combos.service;

import java.util.List;

import ar.com.santanderrio.obp.base.service.Service;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;

/**
 * The Interface ConsultarTagsMaximoVezService.
 */
public interface DatosSelectoresService extends Service {

	/**
	 * Obtener tipos de documento.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerTiposDeDocumento();

	/**
	 * Obtener importes A recargar.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerImportesARecargar();

	/**
	 * Obtener limites de recarga mensual.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerLimitesDeRecargaMensual();

	/**
	 * Obtener paises de nacimiento.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerPaisesDeNacimiento();

	/**
	 * Obtener provincias.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerProvincias();

	/**
	 * Obtener sexo.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerSexo();

	/**
	 * Obtener estado civil.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerEstadoCivil();

	/**
	 * Obtener nacionalidad.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerNacionalidad();

	/**
	 * Obtener tipo de movimiento.
	 *
	 * @param string
	 *            the string
	 * @return the string
	 */
	String obtenerTipoDeMovimientoDescripcion(String string);

	/**
	 * Obtener Preguntas de Seguridad.
	 *
	 * @return the list
	 */
	List<Opcion> obtenerPreguntasSeguridad();

	/**
	 * Obtener cantidad cheques comun. T
	 * 
	 * @return the list
	 */
	List<Opcion> obtenerCantidadChequesComun();

	/**
	 * Obtener cantidad cheques pago diferido. T
	 * 
	 * @return the list
	 */
	List<Opcion> obtenerCantidadChequesPagoDiferido();

	/**
	 * Obtener cantidad chequera comun. T
	 * 
	 * @return the list
	 */
	List<Opcion> obtenerCantidadChequeraComun();

	/**
	 * Obtener cantidad chequera pago diferido. T
	 * 
	 * @return the list
	 */
	List<Opcion> obtenerCantidadChequeraPagoDiferido();

}
