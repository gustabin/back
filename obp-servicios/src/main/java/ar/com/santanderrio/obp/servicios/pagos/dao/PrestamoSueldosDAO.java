/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.prestamos.BCRAResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldoTasaSubsidiadaEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldosAgregarCBUEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldosConfigEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoSueldosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaSubsidiadaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoSueldoTasaSubsidiadaView;

// TODO: Auto-generated Javadoc
/**
 * The Interface DeudaPrestamoDAO.
 */
public interface PrestamoSueldosDAO {

	/**
	 * /** Consulta las deudas de prestamos informados para un cliente.
	 *
	 * @param cliente            the cliente
	 * @return detalles del prestamo
	 * @throws DAOException             the DAO exception
	 */
	PrestamoSueldosConfigEntity consultarPrestamoSueldos(Cliente cliente) throws DAOException;
	
	/**
	 * Insertar prestamo sueldos.
	 *
	 * @param cliente the cliente
	 * @param confirmarPrestamoSueldosInDTO the confirmar prestamo sueldos in DTO
	 * @return the prestamo sueldos config entity
	 * @throws DAOException the DAO exception
	 */
	PrestamoSueldosConfigEntity insertarPrestamoSueldos(Cliente cliente, ConfirmarPrestamoSueldosInDTO confirmarPrestamoSueldosInDTO) throws DAOException;
	
	/**
	 * Verificar virus archivo fs.
	 *
	 * @param adjuntarArchivosInEntity the adjuntar archivos in entity
	 * @return the respuesta
	 */
	Respuesta<Boolean> verificarVirusArchivoFs(AdjuntarArchivosInEntity adjuntarArchivosInEntity);
	
		
	/**
	 * Borrar doc.
	 *
	 * @param adjuntarArchivosInEntity the adjuntar archivos in entity
	 * @return the respuesta
	 */
	Respuesta<Boolean> borrarDoc(AdjuntarArchivosInEntity adjuntarArchivosInEntity) ;

	/**
	 * Consultar WSFOREXBCRA.
	 *
	 * @param cliente the cliente
	 * @return the BCRA response
	 * @throws DAOException the DAO exception
	 */
	BCRAResponse consultarWSFOREXBCRA(Cliente cliente) throws DAOException;

	/**
	 * Consulta si hay prestamo a tasa subsidiada para el cliente
	 * @param cliente
	 * @return
	 * @throws DAOException
	 */
	PrestamoSueldoTasaSubsidiadaEntity consultarPrestamoATPVigente(Cliente cliente) throws DAOException;
	
	/**
	 * Realiza el alta de un prestamo sueldo a tasa subsidiada
	 * @param prestamoSueldoView
	 * @param cliente
	 * @return
	 */
	String altaPrestamoSueldoTasaSubsidiada(PrestamoSueldoTasaSubsidiadaView prestamoSueldoView, ComprobantePrestamoTasaSubsidiadaView comprobante, Cliente cliente, Cuenta cuentaSeleccionada) throws DAOException;

	String agregarCBU(PrestamoSueldosAgregarCBUEntity agregarCBUEntity) throws DAOException;
}
