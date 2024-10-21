/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contrato.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.TipoContratoEnum;

/**
 * The Interface ContratosBO.
 * 
 * @author pablo.d.gargaglione
 *
 */
public interface ContratoBO {

	/**
	 * Ejecuta el procedimiento almacenado que se fija si el contrato esta
	 * aceptado o no.
	 *
	 * @param fechaNac
	 *            La fecha de nacimiento del cliente
	 * @param dni
	 *            El dni del cliente
	 * @param campoEnum
	 *            the campo enum
	 * @param sinonimoEnum
	 *            the sinonimo enum
	 * @return 1 (OK) o 0 (ERROR) de acuerdo al resultado de la ejecucion.
	 */
	Respuesta<String> buscarContratoAceptado(String fechaNac, String dni, CampoEnum campoEnum,
			SinonimoEnum sinonimoEnum);

	/**
	 * Ejecuta el procedimiento almacenado que se fija si el contrato esta
	 * aceptado o no.
	 *
	 * @param fechaNac
	 *            La fecha de nacimiento del cliente
	 * @param dni
	 *            El dni del cliente
	 * @param campoEnum
	 *            the campo enum
	 * @param sinonimoEnum
	 *            the sinonimo enum
	 * @return 1 (OK) o 0 (ERROR) de acuerdo al resultado de la ejecucion.
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Deprecated // TODO: usar el que retorna una Respuesta
	String buscarContratoAceptadoOld(String fechaNac, String dni, CampoEnum campoEnum, SinonimoEnum sinonimoEnum)
			throws DAOException;

	/**
	 * Ejecuta el procedimiento almacenado que acepta el contrato.
	 *
	 * @param fechaNac
	 *            La fecha de nacimiento del cliente
	 * @param dni
	 *            El dni del cliente
	 * @param campoEnum
	 *            the campo enum
	 * @param sinonimoEnum
	 *            the sinonimo enum
	 * @return 1 (OK) o 0 (ERROR) de acuerdo al resultado de la ejecucion.
	 */
	Respuesta<String> confirmarAceptacionContrato(String fechaNac, String dni, CampoEnum campoEnum,
			SinonimoEnum sinonimoEnum);

	/**
	 * Ejecuta el procedimiento almacenado que acepta el contrato.
	 *
	 * @param fechaNac
	 *            La fecha de nacimiento del cliente
	 * @param dni
	 *            El dni del cliente
	 * @param campoEnum
	 *            the campo enum
	 * @param sinonimoEnum
	 *            the sinonimo enum
	 * @return 1 (OK) o 0 (ERROR) de acuerdo al resultado de la ejecucion.
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Deprecated // TODO: usar el que retorna una Respuesta
	String confirmarAceptacionContratoOld(String fechaNac, String dni, CampoEnum campoEnum, SinonimoEnum sinonimoEnum)
			throws DAOException;

	/**
	 * Este metodo verifica si el cliente tiene adhesion al contrato pasado como
	 * parametro.
	 *
	 * @author maximiliano.sanchez
	 * @author emilio.watemberg
	 * @param tipoContrato
	 *            the tipo contrato
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Boolean tieneContrato(TipoContratoEnum tipoContrato, Cliente cliente);

	/**
	 * Este metodo agrega el contrato a la sesion.
	 *
	 * @author maximiliano.sanchez
	 * @author emilio.watemberg
	 * @param tipoContrato
	 *            the tipo contrato
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	void agregarContrato(TipoContratoEnum tipoContrato, Cliente cliente);

	/**
	 * Este metodo verifica si existe agrega.
	 *
	 * @author maximiliano.sanchez
	 * @author emilio.watemberg
	 * @param tipoContrato
	 *            the tipo contrato
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	void eliminarContrato(TipoContratoEnum tipoContrato, Cliente cliente);

}
