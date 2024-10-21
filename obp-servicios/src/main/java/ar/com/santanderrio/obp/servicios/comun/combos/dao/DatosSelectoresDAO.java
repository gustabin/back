/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.combos.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;

/**
 * The Interface ConsultarTagsMaximoVezDAO.
 */
public interface DatosSelectoresDAO {

	/**
	 * Busca una sucursal por numero de identificacion.
	 *
	 * @return TagMaximoVez, o null si no existe
	 * @throws DAOException
	 *             si hubo error
	 * 
	 *             ImporteARecargar consultarImporteARecargarPorId(String id)
	 *             throws DAOException;
	 */

	/**
	 * Obtener tipos de documento.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerTiposDeDocumento() throws DAOException;

	/**
	 * Obtener importes maximos mensuales.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerImportesMaximosMensuales() throws DAOException;

	/**
	 * Obtener limites de recarga mensual.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerLimitesDeRecargaMensual() throws DAOException;

	/**
	 * Obtener provincias.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerProvincias() throws DAOException;

	/**
	 * Obtener opcion descripcion.
	 *
	 * @param comboTag
	 *            the combo tag
	 * @param id
	 *            the id
	 * @return the string
	 */
	String obtenerOpcionDescripcion(String comboTag, String id);

	/**
	 * Obtener id opcion por descripcion.
	 *
	 * @param comboID
	 *            the combo ID
	 * @param desc
	 *            the desc
	 * @return the string
	 */
	String obtenerIdOpcionPorDescripcion(String comboID, String desc);

	/**
	 * Obtener paises de nacimiento.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerPaisesDeNacimiento() throws DAOException;

	/**
	 * Obtener sexo.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerSexo() throws DAOException;

	/**
     * Obtener sexo completo.
     *
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    List<Opcion> obtenerSexoCompleto() throws DAOException;

	/**
	 * Obtener estado civil.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerEstadoCivil() throws DAOException;

	/**
	 * Obtener nacionalidad.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerNacionalidad() throws DAOException;

	/**
	 * Obtener Preguntas de Seguridad.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerPreguntasSeguridad() throws DAOException;

	/**
	 * Obtener Cantidad cheques comun.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerCantidadChequesComun() throws DAOException;

	/**
	 * Obtener Cantidad cheques pago diferido.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerCantidadChequesPagoDiferido() throws DAOException;

	/**
	 * Obtener Cantidad chequera comun.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerCantidadChequeraComun() throws DAOException;

	/**
	 * Obtener Cantidad chequera pago diferido.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerCantidadChequeraPagoDiferido() throws DAOException;
	
	/**
	 * Obtener vinculos.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerVinculos() throws DAOException;
	
	/**
	 * Obtener gasto a cargo.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerGastoACargo() throws DAOException;
	
	/**
	 * Obtener codigos bancarios.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerCodigoBancario() throws DAOException;

	/**
	 *  Obtener condicion IVA.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerCondicionIVA() throws DAOException;

	/**
     * Obtener condicion IIBB.
     *
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    List<Opcion> obtenerCondicionIIBB() throws DAOException;

	/**
     * Obtener modalidades ECheq.
     *
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    List<Opcion> obtenerModalidadesECheq() throws DAOException;

	/**
     * Obtener tipos endoso ECheq
     * segun modalidad del Echeq
     *
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    List<Opcion> obtenerTiposEndosoECheq(String idModalidadEcheq) throws DAOException;
    
    /**
     * Obtener motivos email.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Opcion> obtenerMotivosEmail() throws DAOException;

    /**
     * Obtener actividades getnet.
     *
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    List<Opcion> obtenerActividadesGetnet() throws DAOException;

	/**
     * Obtener ingresos getnet.
     *
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    List<Opcion> obtenerIngresosGetnet() throws DAOException;
}
