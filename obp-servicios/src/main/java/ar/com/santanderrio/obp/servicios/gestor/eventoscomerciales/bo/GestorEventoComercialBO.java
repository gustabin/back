/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PeriodoActualDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PoliticasPrivacidadDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.FinalizarPromesaEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertaFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.SolicitarFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceHistorialView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.FinalizarPromesaPagoInView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.RespuestaEncuestaView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosDeDomicilioDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface GestorEventoComercialBO.
 */
public interface GestorEventoComercialBO {

	/**
	 * Obtiene la cantidad de notificaciones sin leer y totales.
	 * 
	 * @param cliente
	 *            the cliente
	 * @return the gestion evento comercial out entity
	 * @throws BusinessException
	 *             the business exception
	 */
	GestionEventoComercialOutEntity obtenerCantidadNotificaciones(Cliente cliente) throws BusinessException;

	/**
	 * Obtiene notificaciones.
	 * 
	 * @param cliente
	 *            the cliente
	 * @return the gestion evento comercial out entity
	 * @throws BusinessException
	 *             the business exception
	 */
	GestionEventoComercialOutEntity obtenerNotificaciones(Cliente cliente) throws BusinessException;
	
	
	/**
	 * Borrar notificacion.
	 *
	 * @param cliente the cliente
	 * @return Boolean
	 * @throws BusinessException the business exception
	 */
	Boolean borrarNotificacion(Cliente cliente, String id) throws BusinessException;
	

	/**
	 * Actualizar notificaciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 */
	boolean actualizarNotificaciones(Cliente cliente);

	/**
	 * Informa si el cliente tiene interes en una notificacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param idNotificacion
	 *            the id notificacion
	 * @return the boolean
	 */
	Boolean informarInteresEnNotificacion(Cliente cliente, String idNotificacion);

	/**
	 * Informar interes oferta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param seccion
	 *            the section
	 * @param ofertaComercialDTO
	 *            the oferta comercial DTO
	 *  @param action
	 *            the type of feedback
	 * @return the boolean
	 */
	Boolean informarFeedbackOferta(Cliente cliente, String seccion, OfertaComercialDTO ofertaComercialDTO,String action);

	
	/**
	 * Obtener ofertas comerciales.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dispositivo
	 *            the dispositivo
	 * @return the oferta comercial DTO
	 */
	EventosComercialesDTO obtenerOfertasComerciales(Cliente cliente, String dispositivo);

	/**
	 * Registrar encuesta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param eventosComercialesDTO
	 *            the eventos comerciales DTO
	 * @return the boolean
	 */
    Boolean registrarEncuesta(Cliente cliente, EventosComercialesDTO eventosComercialesDTO);
    
    /**
	 * Obtener respuestas encuesta.
	 *
	 * @param respuestasEncuesta
	 *            the respuestas encuesta
	 * @return the eventos comerciales DTO
	 */
    EventosComercialesDTO obtenerRespuestasEncuesta(List<RespuestaEncuestaView> respuestasEncuesta);
    
    /**
     * Informar Gestion AC.
     *
     * @param cliente the cliente
     * @param domicilioDeReemplazo            the domicilio de reemplazo
     * @return the respuesta
     */
    Boolean informarGestionAC(Cliente cliente, DatosDeDomicilioDTO domicilioDeReemplazo);
    
    /**
     * Obtener notificaciones perfil.
     *
     * @param cliente the cliente
     * @return the gestion evento comercial out entity
     * @throws BusinessException the business exception
     */
    GestionEventoComercialOutEntity obtenerNotificacionesPerfil(Cliente cliente) throws BusinessException;
    
    /**
     * obtener premiaciones.
     *
     * @param dispositivo the dispositivo
     * @param cliente 		the cliente
     * @param fechaCorte the fecha corte
     * @return Respuesta Chances DTO
     */
    Respuesta<ChancesDTO> obtenerPremiaciones(String dispositivo, Cliente cliente, String fechaCorte);
    
    /**
   	 * Premiaciones periodo actual.
   	 *
   	 * @param cliente 
   	 * 		the cliente
   	 * @return Respuesta Chances DTO
   	 */
    Respuesta<PeriodoActualDTO> premiacionesPeriodoActual(Cliente cliente);

    /**
     * obtener Cotas Maxima Y Minina de anio y mes.
     *
     * @param dispositivo the dispositivo
     * @param cliente 		the cliente
     * @return Respuesta Chance Historial View
     */
	Respuesta<ChanceHistorialView> obtenerCotasMaximaYMinina(String dispositivo, Cliente cliente);

	
	/**
	 * finalizar Promesa de Pago.
	 *
	 * @param cliente the cliente
	 * @param inView the in view
	 * @return the respuesta
	 */
	Respuesta<FinalizarPromesaEntity> finalizarPromesaPago(Cliente cliente, FinalizarPromesaPagoInView inView);

	/**
	 * Obtener ofertas financiacion.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	Respuesta<List<OfertaFinanciacionEntity>> obtenerOfertasFinanciacion(Cliente cliente);

	/**
	 * Solicitar financiacion.
	 *
	 * @param cliente the cliente
	 * @param solicitarFinanciacionEntity the solicitar financiacion entity
	 * @return the respuesta
	 */
	Respuesta<Boolean> solicitarFinanciacion(Cliente cliente, SolicitarFinanciacionEntity solicitarFinanciacionEntity);
	
	String registrarRespuestaPoliticasPrivacidad(PoliticasPrivacidadDTO politicasPrivacidadDTO) throws DAOException;

}
