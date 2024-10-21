/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao;

import java.io.IOException;
import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ProcessResultResponseDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.FinalizarPromesaEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertaFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertasComercialesEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.PeriodoActualOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.SolicitarFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.FinalizarPromesaPagoInView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosDeDomicilioDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface GestorEventoComercialDAO.
 *
 * @author florencia.n.martinez
 */
public interface GestorEventoComercialDAO {

	/**
	 * Obtener ofertas comerciales.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dispositivo
	 *            the dispositivo
	 * @return the ofertas comerciales entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	OfertasComercialesEntity obtenerOfertasComerciales(Cliente cliente, String dispositivo) throws DAOException;

	/**
	 * Obtiene cantidad de notificaciones leidas y totales.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the gestion evento comercial out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	GestionEventoComercialOutEntity obtenerCantidadNotificaciones(Cliente cliente) throws DAOException;

	/**
	 * Obtiene todas las notificaciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the gestion evento comercial out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	GestionEventoComercialOutEntity obtenerNotificaciones(Cliente cliente) throws DAOException;
	
	
	/**
	 * Borrar notificacion.
	 *
	 * @param cliente the cliente
	 * @return the gestion evento comercial out entity
	 * @throws DAOException the DAO exception
	 */
	GestionEventoComercialOutEntity borrarNotificacion(Cliente cliente, String id) throws DAOException;

	/**
	 * Actualiza el estado de las notificaciones en caso de ser leidas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the gestion evento comercial out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	GestionEventoComercialOutEntity actualizarNotificaciones(Cliente cliente) throws DAOException;

	/**
	 * Informa el interes que tiene un cliente en una notificacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param idNotificacion
	 *            the id notificacion
	 * @return the notificaciones out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	GestionEventoComercialOutEntity informarInteresEnNotificacion(Cliente cliente, String idNotificacion)
			throws DAOException;

	/**
	 * Informar feedback oferta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param seccion
	 *            the section
	 * @param ofertaComercialDTO
	 *            the oferta comercial DTO
	 * @param action
	 *            the type of feedback
	 * @return the boolean
	 * @throws DAOException
	 *             the DAO exception
	 */
	Boolean informarFeedbackOferta(Cliente cliente, String seccion, OfertaComercialDTO ofertaComercialDTO, String action)
			throws DAOException;

	/**
	 * Registrar encuesta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param eventosComercialesDTO
	 *            the eventos comerciales DTO
	 * @return true si es COD OK
	 * @throws DAOException
	 *             the DAO exception
	 */
    public Boolean registrarEncuesta(Cliente cliente, EventosComercialesDTO eventosComercialesDTO)
    		throws DAOException;
    

	/**
	 * Obtener notificaciones perfil.
	 *
	 * @param cliente the cliente
	 * @return the gestion evento comercial out entity
	 * @throws DAOException the DAO exception
	 */
	GestionEventoComercialOutEntity obtenerNotificacionesPerfil(Cliente cliente) throws DAOException;
	
    /**
     * Informar gestion AC.
     *
     * @param cliente            the cliente
     * @param domicilioDeReemplazo            the domicilio de reemplazo
     * @param ofertaRecambioChip the oferta recambio chip
     * @return true si es COD OK
     * @throws DAOException             the DAO exception
     */
    public Boolean informarGestionAC(Cliente cliente, DatosDeDomicilioDTO domicilioDeReemplazo, OfertaComercialDTO ofertaRecambioChip)
    		throws DAOException;

    /**
     * Informar gestion AC.
     *
     * @param cliente            the cliente
     * @param ofertaRecambioChip the oferta recambio chip
     * @return true si es COD OK
     * @throws DAOException             the DAO exception
     */
	Boolean informarGestionAC(Cliente cliente, OfertaComercialDTO ofertaRecambioChip) throws DAOException;
	
	/**
	 * obtener Premiaciones.
	 *
	 * @param dispositivo the dispositivo
	 * @param cliente            the cliente
	 * @return Chance Out Entity
	 * @throws DAOException             the DAO exception
	 */
	public ChanceOutEntity obtenerPremiaciones(String dispositivo, Cliente cliente) throws DAOException;
	
	/**
	 * Informar gestion AC.
	 * 
	 * @param cliente
	 *            the cliente
	 * @return true si es COD OK
	 * @throws DAOException
	 *             the DAO exception
	 */
	
	public PeriodoActualOutEntity premiacionesPeriodoActual(Cliente cliente) throws DAOException;

	
	
	
	/**
	 * Finalizar Promesa de Pago.
	 *
	 * @param cliente the cliente
	 * @param inView the in view
	 * @return the finalizar promesa entity
	 * @throws DAOException the DAO exception
	 */
	public FinalizarPromesaEntity finalizarPromesaPago(Cliente cliente, FinalizarPromesaPagoInView inView) throws DAOException;

	/**
	 * Obtener ofertas financiacion.
	 *
	 * @param nup the nup
	 * @return the list
	 */
	List<OfertaFinanciacionEntity> obtenerOfertasFinanciacion(String nup) throws DAOException;

	/**
	 * Solicitar financiacion.
	 *
	 * @param cliente the cliente
	 * @param solicitarFinanciacionEntity the solicitar financiacion entity
	 * @return the boolean
	 * @throws DAOException the DAO exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	Boolean solicitarFinanciacion(Cliente cliente, SolicitarFinanciacionEntity solicitarFinanciacionEntity) throws DAOException, IOException;

	ProcessResultResponseDto getProcessesCreatedToday(String nup) throws DAOException;
}
