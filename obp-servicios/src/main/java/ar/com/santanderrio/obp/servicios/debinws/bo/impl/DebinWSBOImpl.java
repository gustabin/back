package ar.com.santanderrio.obp.servicios.debinws.bo.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.debin.CompradorDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.DatosUsuarioBreveDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.DetalleDebinBreveDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.DetalleDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebitoV3;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestContracargo;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseContracargo;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResumenDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.VendedorDebinDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debinapi.dao.DebinApiConfig;
import ar.com.santanderrio.obp.servicios.debinapi.dao.DebinApiDAO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.CurrencyType;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListRequest;
import ar.com.santanderrio.obp.servicios.debinapi.utils.DebinApiMapper;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSBO;
import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CompradorDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.DebinWSDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.DebinWSEliminarOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.EstadoDebinWSBusquedaDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.RechazarDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.SolicitarContracargoDebinOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.VendedorDTO;
import ar.com.santanderrio.obp.servicios.debinws.utils.DebinWSUtils;
import ar.com.santanderrio.obp.servicios.debinws.utils.TipoCuentaDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Class DebinWSBOImpl.
 */
@Component
@Qualifier("debinWSBOImpl")
public class DebinWSBOImpl implements DebinWSBO {

    /** The debin WSDAO. */
    @Autowired
    @Qualifier("debinWSDAOImpl")
    private DebinWSDAO debinWSDAO;

    /** The debin WSDAO. */
    @Autowired
    @Qualifier("debinApiDAOImpl")
    private DebinApiDAO debinApiDAO;

    /** The debinApiMapper. */
    @Autowired
    private DebinApiMapper debinApiMapper;
	
    /** The Constant LOGGER. */
    protected static final Logger LOGGER = LoggerFactory.getLogger(DebinWSBOImpl.class);
	
    /** The Constant CODIGO_DEBIN_OK. */
    protected static final String CODIGO_DEBIN_OK = "00";
	
    /** The Constant CODIGO_LISTA_DEBIN_SINDATOS. */
    private static final String CODIGO_LISTA_DEBIN_SINDATOS = "85";
	
    /** The codigos error generico.
     * 63:  Entidad de token incorrecta
     * 22: Faltan datos de CUIT (Es necesario enviar CUIT de comprador o vendedor
     * 10: cuit mal formulado
     * 6: CBU mal formulado
     * 56: Datos mal formulados CBU/Banco
     * 99: Error del sistema
     * 12: Error general de búsqueda/datos)*/
    public static String[] codigosErrorGenerico = { "63", "22", "10", "6", "56", "99", "12" };
	
    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @Autowired
    protected RespuestaFactory respuestaFactory;
	
    /** The sesion parametros. */
    @Autowired
    protected SesionParametros sesionParametros;
	
    /** The sesion cliente. */
    @Autowired
    protected SesionCliente sesionCliente;
	
    /** The estadistica manager. */
    @Autowired
    protected EstadisticaManager estadisticaManager;
	
    /** The Constant CODIGO_ERROR_TIME_OUT. */
    protected static final String CODIGO_ERROR_TIME_OUT = "099";
	
    /** The Constant CODIGO_ERROR_CUIT. */
    private static final String CODIGO_ERROR_CUIT = "098";
	
    /** The Constant CANAL. */
    protected static final String CANAL = "E";
    
    private static final String TIPO_CONSULTA_DEFAULT = "Debin";
	
    /** The Constant MONEDA_PESOS. */
    protected static final String MONEDA_PESOS = "032";
	
    /** The Constant MONEDA_DOLARES. */
    protected static final String MONEDA_DOLARES = "840";
	
    /** The Constant CUENTA_BIMONETARIA. */
    protected static final String CUENTA_BIMONETARIA = "02";
	
    /** The Constant ESTADO_RESPONSE_DEBINWS_OK. */
    private static final String ESTADO_RESPONSE_DEBINWS_OK = "OK";
	
    /** The Constant FORMATO_FECHA_COMPLETO. */
    public static final String FORMATO_FECHA_COMPLETO = "yyyy-MM-dd-HH.mm.ss.SSSSSS";
	
    /** The Constant FORMATO_NRO_COMPROBANTE. */
    public static final String FORMATO_NRO_COMPROBANTE = "yyMMddHHmmss";
	
    /** The Constant PAGAR_DEBIN. */
    private static final String PAGAR_DEBIN = "01";
	
    /** The Constant RECHAZAR_DEBIN. */
    private static final String RECHAZAR_DEBIN = "10";
	
    /** The Constant NO_ADHERIDO_DEBIN. */
    private static final String NO_ADHERIDO_DEBIN = "10";
	
    /** The Constant CODIGO_TITULARIDAD. */
    private static final String CODIGO_TITULARIDAD = "TI";
	
    /** The Constant PAGO. */
    private static final String PAGO = "ConfirmacionDebito";
	
    /** The Constant RECHAZO. */
    private static final String RECHAZO = "RechazoDebin";
	
    /** The Constant ELIMINACION. */
    private static final String ELIMINACION = "EliminacionDebin";
	
    /** The Constant MARCA_NO_HAY_MAS_REGISTROS. */
    private static final int MARCA_NO_HAY_MAS_REGISTROS = 99;

    /** The Constant TIPO_CONSULTA_RECURRENTE. */
    private static final String TIPO_CONSULTA_RECURRENTE = "recurrente";

	
    /** The nup DAO. */
    @Autowired
    private NUPDAO nupDAO;

    @Value("${"+ DebinApiConfig.ENABLED + ":true}")
    private Boolean debinApiEnabled;

    /**
	 * consultaDebin.
	 *
	 * @param consultaDebinInDTO
	 *            the consulta debin in DTO
	 * @return the respuesta
	 */
    @Override
    public Respuesta<ConsultaDebinWSOutDTO> consultaDebin(ConsultaDebinWSInDTO consultaDebinInDTO) {
        LOGGER.info("DebinWSBOImpl DebinWSBO inicuando consulta de debin");
        List<DebinWSDTO> respuestaListaDebines = new ArrayList<DebinWSDTO>();
        ConsultaDebinWSOutDTO consultaDebinWSOut = new ConsultaDebinWSOutDTO();
        consultaDebinWSOut.setDebinesDTO(respuestaListaDebines);
        List<DebinWSDTO> listaParcialOutDTO = new ArrayList<DebinWSDTO>();
        String codigoError = "";
        String codigoEstadistica = consultaDebinInDTO.getConsultaComprador() ? EstadisticasConstants.CONSULTA_LISTA_DEBINWS_RECIBIDOS : EstadisticasConstants.CONSULTA_LISTA_DEBINWS_ENVIADOS;
        try {
            //SE TIENE QUE LLAMAR DOS VECES A LA CONSULTA DE LISTA DEBIN
            //PRIMER LLAMADO
            if (consultaDebinInDTO.getPrimerEstadoBusqueda().getNroPagina() != MARCA_NO_HAY_MAS_REGISTROS) {
                LOGGER.info("DebinWSBOImpl Generando request para consulta de debines DAO");
                ResponseListaDebin responsePrimerLlamadoListaDebin = listaDebinNew(consultaDebinInDTO, Boolean.TRUE);
                if (responsePrimerLlamadoListaDebin.getError() != null) {
                    codigoError = responsePrimerLlamadoListaDebin.getError().getCodigo();
                    if (!CODIGO_DEBIN_OK.equals(codigoError) && !(CODIGO_LISTA_DEBIN_SINDATOS.equals(codigoError))) { 
                        return manejarPrimerErrorListaDebin(codigoError, codigoEstadistica);
                    } else if (CODIGO_LISTA_DEBIN_SINDATOS.equals(codigoError) && consultaDebinInDTO.getSegundoEstadoBusqueda() != null &&
                            consultaDebinInDTO.getPrimerEstadoBusqueda().getEstado().equals(consultaDebinInDTO.getSegundoEstadoBusqueda().getEstado())) {
                        estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
                		itemMensajeRespuesta.setTipoError(TipoError.DEBINWS_WARNING_SIN_DATOS.getDescripcion());
                		itemMensajeRespuesta.setTag(StringUtils.EMPTY);
                		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_WARNING_SIN_DATOS).getMensaje();
						itemMensajeRespuesta.setMensaje(DebinWSUtils.obtenerMensajeConsulta(mensaje, consultaDebinInDTO.getTipo()));
						return respuestaFactory.crearRespuestaWarning(ConsultaDebinWSOutDTO.class, null, Arrays.asList(itemMensajeRespuesta)); 
                    }
                }
                if (responsePrimerLlamadoListaDebin.getSiguientePagina() != null) {
                    guardarEnSesionNroPagina(consultaDebinInDTO.getPrimerEstadoBusqueda().getEstado(), responsePrimerLlamadoListaDebin.getSiguientePagina(), consultaDebinInDTO.getAplicaFiltro());
                }
                listaParcialOutDTO = generarListaDebinOut(responsePrimerLlamadoListaDebin, consultaDebinInDTO.getPrimerEstadoBusqueda());
                respuestaListaDebines.addAll(listaParcialOutDTO);   
            }
        } catch (DatatypeConfigurationException e1) {
            LOGGER.error("DebinWSBOImpl Consulta debinWS : ERROR formateando fechas");
            return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
        } catch (DAOException e) {
        	LOGGER.error("Error consultando DAO Lista Debin New");
            return manejarErrorDaoListaDebin(e, consultaDebinWSOut, codigoEstadistica);
        }  

        try {
            //segundo llamado
            if (consultaDebinInDTO.getSegundoEstadoBusqueda() != null && validacionHayMasMismoEstado(consultaDebinInDTO)) {
                LOGGER.info("DebinWSBOImpl Generando request para consulta de debines segundo llamado DAO");
                ResponseListaDebin responseSegundoLlamadoListaDebin = listaDebinNew(consultaDebinInDTO, Boolean.FALSE);

                if (responseSegundoLlamadoListaDebin.getError() != null) {
                    codigoError = responseSegundoLlamadoListaDebin.getError().getCodigo();
                    if (!CODIGO_DEBIN_OK.equals(codigoError)) { 
                        return manejarSegundoErrorListaDebin(codigoError, consultaDebinWSOut, codigoEstadistica, consultaDebinInDTO.getTipo());
                    }
                }
                List<DebinWSDTO> listaParcialSegundoOutDTO  = generarListaDebinOut(responseSegundoLlamadoListaDebin, consultaDebinInDTO.getSegundoEstadoBusqueda());
                respuestaListaDebines.addAll(listaParcialSegundoOutDTO);
                if (responseSegundoLlamadoListaDebin.getSiguientePagina() != null) {
                    guardarEnSesionNroPagina(consultaDebinInDTO.getSegundoEstadoBusqueda().getEstado(), responseSegundoLlamadoListaDebin.getSiguientePagina(), consultaDebinInDTO.getAplicaFiltro());
                }
            }
            LOGGER.info("DebinWSBOImpl Consulta debin respuesta OK");
            estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            return respuestaFactory.crearRespuestaOk(consultaDebinWSOut);
        } catch (DatatypeConfigurationException e1) {
            LOGGER.error("DebinWSBOImpl Consulta debinWS : ERROR formateando fechas");
            return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
        } catch (DAOException e) {
             LOGGER.error("DebinWSBOImpl Consulta debinWS : ERROR consulta DAO");
             return manejarErrorDaoListaDebin(e, consultaDebinWSOut, codigoEstadistica);
        }   
    }

    private ResponseListaDebin listaDebinNew(ConsultaDebinWSInDTO consultaDebinInDTO, boolean esPrimeraBusqueda) throws DatatypeConfigurationException, DAOException  {
        //TODO think about using an design pattern for solve the switch between calling debinWSDAO and debinWSDAO
        ResponseListaDebin responseListaDebin = null;
        EstadoDebinWSBusquedaDTO estadoDebinWSBusquedaDTO =esPrimeraBusqueda?consultaDebinInDTO.getPrimerEstadoBusqueda() : consultaDebinInDTO.getSegundoEstadoBusqueda();
        if (debinApiEnabled){
            responseListaDebin = listaDebinNewCallingDebinApi(consultaDebinInDTO, estadoDebinWSBusquedaDTO);
        }

        if (responseListaDebin == null && !TIPO_CONSULTA_RECURRENTE.equals(consultaDebinInDTO.getTipo())){
            responseListaDebin = listaDebinNewCallingDebinWS(consultaDebinInDTO, estadoDebinWSBusquedaDTO);
        }
        return responseListaDebin;

    }


    private ResponseListaDebin listaDebinNewCallingDebinApi(ConsultaDebinWSInDTO consultaDebinInDTO,  EstadoDebinWSBusquedaDTO estadoDebinWSBusquedaDTO ) throws DatatypeConfigurationException, DAOException{
            LOGGER.info("DebinWSBOImpl Iniciando llamada consulta debin a DebinAPI");
            DebinListRequest debinListRequest = debinApiMapper.toDebinListRequest(consultaDebinInDTO,
                    estadoDebinWSBusquedaDTO,
                    obtenerCuitCliente(),
                    sesionCliente.getIpCliente());
            try {
                DebinListDTO debinListDTO = debinApiDAO.getDebinList(debinListRequest);
                return debinApiMapper.toDTO(debinListDTO, Integer.parseInt(debinListRequest.getPageNumber()));
            }catch(DAOException daoException){
                LOGGER.error("Falló la llamada a DebinAPI", daoException);
                LOGGER.info("Se va a reintentar llamando a otro Prisma");
            }
            return null;
    }

    private ResponseListaDebin listaDebinNewCallingDebinWS(ConsultaDebinWSInDTO consultaDebinInDTO,  EstadoDebinWSBusquedaDTO estadoDebinWSBusquedaDTO) throws DatatypeConfigurationException, DAOException{
            RequestListaDebin requestWSPrimerLlamadoLista = generarRequestListaDebin(consultaDebinInDTO, estadoDebinWSBusquedaDTO);
            LOGGER.info("DebinWSBOImpl Iniciando llamada consulta debin DAO a Prisma ");
            return debinWSDAO.listaDebinNew(requestWSPrimerLlamadoLista);
    }


    /**
     * Validacion hay mas mismo estado.
     *
     * @param consultaDebinInDTO the consulta debin in DTO
     * @return true, if successful
     */
    private boolean validacionHayMasMismoEstado(ConsultaDebinWSInDTO consultaDebinInDTO) {
        if (consultaDebinInDTO.getSegundoEstadoBusqueda() != null && !(consultaDebinInDTO.getPrimerEstadoBusqueda().getEstado().equals(consultaDebinInDTO.getSegundoEstadoBusqueda().getEstado()))) {
            return true;
        } else {
        	if (consultaDebinInDTO.getAplicaFiltro()) {
                return sesionParametros.getNroPaginaDebinEstadoUsuario() != MARCA_NO_HAY_MAS_REGISTROS;        		
        	} else {
                if (EstadoDebinEnum.INICIADO.equals(consultaDebinInDTO.getPrimerEstadoBusqueda().getEstado())) {
                    return sesionParametros.getNroPaginaDebinIniciado() != MARCA_NO_HAY_MAS_REGISTROS;
                } else if (EstadoDebinEnum.ACREDITADO.equals(consultaDebinInDTO.getPrimerEstadoBusqueda().getEstado())) {
                    return sesionParametros.getNroPaginaDebinAcreditado() != MARCA_NO_HAY_MAS_REGISTROS;
                }        		
        	}
        }
        return false;
    }

    /**
     * Manejar segundo error lista debin.
     *
     * @param codigoError the codigo error
     * @param consultaDebinWSOut the consulta debin WS out
     * @param codigoEstadistica the codigo estadistica
     * @param tipoConsulta the tipo consulta
     * @return the respuesta
     */
    private Respuesta<ConsultaDebinWSOutDTO> manejarSegundoErrorListaDebin(String codigoError, ConsultaDebinWSOutDTO consultaDebinWSOut, String codigoEstadistica, String tipoConsulta) {
        LOGGER.info("Error en segundo llamado a lista debin");
        //si no hay resultados de la primera busqueda
        if (consultaDebinWSOut.getDebinesDTO().isEmpty()) {
            if (ArrayUtils.contains(codigosErrorGenerico, codigoError)) { 
                estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                		CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO); 
            } else if (CODIGO_LISTA_DEBIN_SINDATOS.equals(codigoError)) {
                estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        		itemMensajeRespuesta.setTipoError(TipoError.DEBINWS_WARNING_SIN_DATOS.getDescripcion());
        		itemMensajeRespuesta.setTag(StringUtils.EMPTY);
        		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_WARNING_SIN_DATOS).getMensaje();
				itemMensajeRespuesta.setMensaje(DebinWSUtils.obtenerMensajeConsulta(mensaje, tipoConsulta));
				return respuestaFactory.crearRespuestaWarning(ConsultaDebinWSOutDTO.class, null, Arrays.asList(itemMensajeRespuesta));
            } else {
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                		CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            }
        //si hay datos del primer llamado
        } else {
            LOGGER.info("Error en segundo llamado a lista debin pero hay datos del primer llamado a mostrar");
            estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            if (ArrayUtils.contains(codigosErrorGenerico, codigoError)) {
                return respuestaFactory.crearRespuestaWarning(consultaDebinWSOut, StringUtils.EMPTY,
                		TipoError.DEBINWS_WARNING_LISTA_ERROR_PARCIAL, CodigoMensajeConstantes.DEBINWS_WARNING_LISTA_ERROR_PARCIAL); 
            } else if (CODIGO_LISTA_DEBIN_SINDATOS.equals(codigoError)) {
                return respuestaFactory.crearRespuestaOk(consultaDebinWSOut);
            } else {
                return respuestaFactory.crearRespuestaWarning(consultaDebinWSOut, StringUtils.EMPTY,
                		TipoError.DEBINWS_WARNING_LISTA_ERROR_PARCIAL, CodigoMensajeConstantes.DEBINWS_WARNING_LISTA_ERROR_PARCIAL);
            }
        }
    }

    /**
     *  error en el primer llamado  a lista debin.
     *
     * @param codigoError the codigo error
     * @param codigoEstadistica the codigo estadistica
     * @return the respuesta
     */
    private Respuesta<ConsultaDebinWSOutDTO> manejarPrimerErrorListaDebin(String codigoError, String codigoEstadistica) {
        LOGGER.info("DebinWS Error en primer llamado a lista debin");
        estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        if (ArrayUtils.contains(codigosErrorGenerico, codigoError)) { 
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO, 
            		CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO); 
        }
        return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO, 
        		CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
    }

    /**
     * manejarErrorDaoListaDebin.
     *
     * @param e the e
     * @param consultaDebinWSOut the consulta debin WS out
     * @param codigoEstadistica the codigo estadistica
     * @return the respuesta
     */
    private Respuesta<ConsultaDebinWSOutDTO> manejarErrorDaoListaDebin(DAOException e, ConsultaDebinWSOutDTO consultaDebinWSOut, String codigoEstadistica) {
       //si no hay resultados de la primera busqueda
       if (consultaDebinWSOut.getDebinesDTO().isEmpty()) {
           LOGGER.error("DebinWSBOImpl Consulta debinWS : ERROR consulta DAO");
           if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
               estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
               return this.respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
            		   TipoError.DEBINWS_ERROR_TIMEOUT,CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
           } else if (CODIGO_ERROR_CUIT.equals(e.getErrorCode())) {
                return this.respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_CUIT,
                		CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);   
           } else {
                estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return this.respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                		CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO); 
           }
       } else { //si hay resultado de la primer llamada
           LOGGER.error("DebinWSBOImpl Consulta debinWS : ERROR consulta DAO pero con datos del primer llamado");
           if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
               estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
           } else if (!(CODIGO_ERROR_CUIT.equals(e.getErrorCode()))) {
               estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);   
           }   
           return respuestaFactory.crearRespuestaWarning(consultaDebinWSOut, StringUtils.EMPTY,
        		   TipoError.DEBINWS_WARNING_LISTA_ERROR_PARCIAL, CodigoMensajeConstantes.DEBINWS_WARNING_LISTA_ERROR_PARCIAL);
       }
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSBO#consultaDetalleDebin(ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSInDTO)
     */
    @Override
    public Respuesta<ConsultaDetalleDebinWSOutDTO> consultaDetalleDebin(ConsultaDetalleDebinWSInDTO cnsDetalleDebinInDTO) {
        LOGGER.info("DebinWSBOImpl iniciando consulta detalle debin");
        try{
            RequestDebin  requestDetalleDebin = generarRequestDetalleDebin(cnsDetalleDebinInDTO);
            ResponseDebin responseDetalleDebin =  debinWSDAO.consultaDebinNew(requestDetalleDebin);
            if (responseDetalleDebin.getError() != null && !CODIGO_DEBIN_OK.equals(responseDetalleDebin.getError().getCodigo())) {
                    estadisticaManager.add(EstadisticasConstants.CONSULTA_DETALLE_DEBINWS,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                    return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO); 
            }
            ConsultaDetalleDebinWSOutDTO cnsDetalleOutDTO = generarCnsDetalleOutDTO(responseDetalleDebin,cnsDetalleDebinInDTO.isConsultaDesdeRecibidos());
            LOGGER.info("DebinWSBOImpl Consulta debin respuesta OK");
            estadisticaManager.add(EstadisticasConstants.CONSULTA_DETALLE_DEBINWS,EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            return respuestaFactory.crearRespuestaOk(cnsDetalleOutDTO);
        } catch (DAOException e) {
            LOGGER.error("Error consultado detalle debin DAO Exception");
            if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
                estadisticaManager.add(EstadisticasConstants.CONSULTA_DETALLE_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
                return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_TIMEOUT, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            } else if (CODIGO_ERROR_CUIT.equals(e.getErrorCode())) {
                return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_CUIT,  CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
                    
            }
            estadisticaManager.add(EstadisticasConstants.CONSULTA_DETALLE_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
        }
    }

    /**
     * Generar cns detalle out DTO.
     * 
     * Se pasa el atributo isConsultaDesdeRecibidos, este atributo viene desde el front
     * para poder identificar si viene desde de recibidos o enviados
     * en el caso de ser de recibidos el tipoCuenta de comprador se obtiene desde las cuentas de sesion
     * en el caso de ser de enviados el tipoCuenta de vendedor se obtiene desde recibidos
     * se intercambian los roles
     *
     * @param responseDetalleDebin            the response detalle debin
     * @param isConsultaDesdeRecibidos the is consulta desde recibidos
     * @return the consulta detalle debin WS out DTO
     */
    private ConsultaDetalleDebinWSOutDTO generarCnsDetalleOutDTO(ResponseDebin responseDetalleDebin, boolean isConsultaDesdeRecibidos) {

        ConsultaDetalleDebinWSOutDTO cnsDetOutDTO = new ConsultaDetalleDebinWSOutDTO();
        DetalleDebinDTO detalle = responseDetalleDebin.getDetalleDebin();
        cnsDetOutDTO.setConcepto(obtenerConcepto(detalle.getConcepto()));
        cnsDetOutDTO.setDescripcion(detalle.getDescripcion());
        cnsDetOutDTO.setEstado(detalle.getEstadoDebin().getDescripcion());
        if (detalle.getFechaCreacion() != null) {
            cnsDetOutDTO.setFechaSolicitud(detalle.getFechaCreacion().toGregorianCalendar().getTime()); 
        }
        if (detalle.getFechaExpiracion() != null) {
            cnsDetOutDTO.setFechaVencimiento(detalle.getFechaExpiracion().toGregorianCalendar().getTime()); 
        }
        cnsDetOutDTO.setIdDebin(detalle.getIdDebin());
        cnsDetOutDTO.setImporteSolicitado(detalle.getImporte());
        cnsDetOutDTO.setMoneda(detalle.getMoneda());
        cnsDetOutDTO.setOperacionEnProceso(detalle.getIdOperacionOriginal() != null && !StringUtils.EMPTY.equals(detalle.getIdOperacionOriginal()));
        cnsDetOutDTO.setPreautorizado(responseDetalleDebin.isPreautorizado());
        
        if (null != detalle.getEvaluacion()) {
        	cnsDetOutDTO.setPuntaje(detalle.getEvaluacion().getPuntaje());
        }
        cnsDetOutDTO.setComprador(obtenerComprador(responseDetalleDebin.getComprador(),detalle.getMoneda(),isConsultaDesdeRecibidos));
        cnsDetOutDTO.setVendedor(obtenerVendedor(responseDetalleDebin.getVendedor(),detalle.getMoneda(),isConsultaDesdeRecibidos));
    
        return cnsDetOutDTO;
    }

    /**
     * Obtener concepto.
    *
    * @param concepto the concepto
    * @return the string
    */
    private String obtenerConcepto(String concepto) {
        if (concepto != null && concepto.length() == 1 && StringUtils.isAlpha(concepto)) {
            return  "0" + concepto;
        }
        return concepto;
    }

    /**
     * Obtiene el vendedor.
     *
     * @param vendedor the vendedor
     * @param moneda the moneda
     * @param isConsultaDesdeRecibidos the is consulta desde recibidos
     * @return the vendedor DTO
     */
    private VendedorDTO obtenerVendedor(VendedorDebinDTO vendedor, String moneda, boolean isConsultaDesdeRecibidos) {
        VendedorDTO retorno = new VendedorDTO();
        retorno.setCuit(vendedor.getCuit());
        if (vendedor.getTitular() != null) {
        	retorno.setNombreTitular(vendedor.getTitular().trim());
            	
        }
        if (vendedor.getCuenta() != null) {
        	if (vendedor.getCuenta().getAlias() != null) {
            	retorno.setAlias(vendedor.getCuenta().getAlias().trim());
        	}
            retorno.setNumero(vendedor.getCuenta().getNumero());
            retorno.setCbu(vendedor.getCuenta().getCbu());
            retorno.setMoneda(vendedor.getCuenta().getMoneda());
            retorno.setTipo(vendedor.getCuenta().getTipo());
            if (!isConsultaDesdeRecibidos) {
            	retorno.setTipo(obtenerTipoCuentaDebin(vendedor.getCuenta().getCbu(),moneda));
            }
        }
        return retorno;
	}

    /**
     * Obtiene el comprador.
     *
     * @param comprador the comprador
     * @param moneda the moneda
     * @param isConsultaDesdeRecibidos the is consulta desde recibidos
     * @return the comprador DTO
     */
	private CompradorDTO obtenerComprador(CompradorDebinDTO comprador, String moneda, boolean isConsultaDesdeRecibidos) {
        CompradorDTO retorno = new CompradorDTO();
        retorno.setCuit(comprador.getCuit());
        if (null != comprador.getTitular()) {
        	retorno.setNombreTitular(comprador.getTitular().trim());
        }
        if (comprador.getCuenta() != null) {
        	if (comprador.getCuenta().getAlias() != null) {
        		retorno.setAlias(comprador.getCuenta().getAlias().trim());	
            }
        	retorno.setTipo(comprador.getCuenta().getTipo());
        	if (isConsultaDesdeRecibidos) {
                retorno.setTipo(obtenerTipoCuentaDebin(comprador.getCuenta().getCbu(),moneda));
            }
            retorno.setNumero(comprador.getCuenta().getNumero());
            retorno.setCbu(comprador.getCuenta().getCbu());
            retorno.setMoneda(comprador.getCuenta().getMoneda());        	
        }
		return retorno;
	}

    /**
	 * Obtener tipo cuenta debin.
	 *
	 * @param cbu the cbu
	 * @param monedaDebin the moneda debin
	 * @return the string
	 */
	private String obtenerTipoCuentaDebin(String cbu, String monedaDebin) {
    	String retorno = "";
    	List<Cuenta> cuentas = sesionCliente.getCliente().getCuentas();
    	for(Cuenta c : cuentas) {
    		if (c.getCbu().equals(cbu)) {
    			if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().equals(c.getTipoCuentaEnum().getCodigo())) {
    				retorno = TipoCuentaDebinEnum.TIPO_CC_PESOS.getCodigo();
    			} else if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().equals(c.getTipoCuentaEnum().getCodigo())) {
    				retorno = TipoCuentaDebinEnum.TIPO_CC_DOLAR.getCodigo();
    			} else if (TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().equals(c.getTipoCuentaEnum().getCodigo())) {
    				retorno = TipoCuentaDebinEnum.TIPO_CA_PESOS.getCodigo();
    			} else if (TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo().equals(c.getTipoCuentaEnum().getCodigo())) {
    				retorno = TipoCuentaDebinEnum.TIPO_CA_DOLARES.getCodigo();
    			} else if (TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().equals(c.getTipoCuentaEnum().getCodigo())) {
    				if (MONEDA_PESOS.equals(monedaDebin)) {
    					retorno = TipoCuentaDebinEnum.TIPO_CU_PESOS.getCodigo();
    				} else if (MONEDA_DOLARES.equals(monedaDebin)) {
    					retorno = TipoCuentaDebinEnum.TIPO_CU_DOLARES.getCodigo();
    				}	
    			}
        		break;
    		}
    	}
		return retorno;
	}
    
	/**
	 * Guardar en sesion nro pagina.
	 *
	 * @param estado the estado
	 * @param pagina the pagina
	 * @param aplicaFiltro the aplica filtro
	 */
    private void guardarEnSesionNroPagina(EstadoDebinEnum estado, int pagina, Boolean aplicaFiltro) {
    	if (aplicaFiltro) {
            sesionParametros.setNroPaginaDebinEstadoUsuario(pagina);    		
    	} else {
            if (EstadoDebinEnum.ACREDITADO.equals(estado)) {
                sesionParametros.setNroPaginaDebinAcreditado(pagina);
            } else if (EstadoDebinEnum.INICIADO.equals(estado)) {
                sesionParametros.setNroPaginaDebinIniciado(pagina);
            }    		
    	}
    }

    /**
	 * generarConsultaDebinOut.
	 *
	 * @param responseWSListaDebinNew
	 *            the response WS lista debin new
	 * @param estadoBusquedaDTO
	 *            the estado busqueda DTO
	 * @return the list
	 */
    private List<DebinWSDTO> generarListaDebinOut(ResponseListaDebin responseWSListaDebinNew, EstadoDebinWSBusquedaDTO estadoBusquedaDTO) {
        LOGGER.info("DebinWSBOImpl generarConsultaDebinOut");
        List<ResumenDebinDTO> debinesResponse = responseWSListaDebinNew.getDebines();
        List<DebinWSDTO> listaDTOOut = new ArrayList<DebinWSDTO>();
        for(ResumenDebinDTO debinResponseDTO : debinesResponse) {
            DebinWSDTO debinDTO = new DebinWSDTO();
            if (debinResponseDTO.getVencimiento() != null) {
            	debinDTO.setFechaVencimiento(debinResponseDTO.getVencimiento().toGregorianCalendar().getTime());	
            }
            if (debinResponseDTO.getFechaCreacion() != null) {
                debinDTO.setFechaCreacion(debinResponseDTO.getFechaCreacion().toGregorianCalendar().getTime());
            }
            debinDTO.setImporte(debinResponseDTO.getImporte());

            debinDTO.setMoneda(CurrencyType.fromString(debinResponseDTO.getMoneda()));

            debinDTO.setCuitSolicitante(debinResponseDTO.getVendedor().getCuit());
            debinDTO.setNombreSolicitante(debinResponseDTO.getVendedor().getTitular());
            debinDTO.setNombreDestinatario(debinResponseDTO.getComprador().getTitular());
            debinDTO.setDebinId(debinResponseDTO.getId());
            debinDTO.setEstado(estadoBusquedaDTO.getEstado());
            debinDTO.setConcepto(obtenerConcepto(debinResponseDTO.getConcepto()));
            listaDTOOut.add(debinDTO);
        }
        LOGGER.info("DebinWSBOImpl generarConsultaDebinOut OK");
        return listaDTOOut;
    } 

    /**
	 * generarRequestListaDebin.
	 *
	 * @param consultaDebinInDTO
	 *            the consulta debin in DTO
	 * @param estadoBusqueda
	 *            the estado busqueda
	 * @return the request lista debin
	 * @throws DatatypeConfigurationException
	 *             the datatype configuration exception
	 * @throws DAOException
	 *             the DAO exception
	 */
    private RequestListaDebin generarRequestListaDebin(ConsultaDebinWSInDTO consultaDebinInDTO, EstadoDebinWSBusquedaDTO estadoBusqueda) throws DatatypeConfigurationException, DAOException  {
        RequestListaDebin request = new RequestListaDebin();
        LOGGER.info("DebinWSBOImpl generarRequestListaDebin");
        request.setCanal(consultaDebinInDTO.getCanal());
        request.setCuit(obtenerCuitCliente());
        request.setIp(sesionCliente.getIpCliente());
        request.setTipo(consultaDebinInDTO.getTipo());
        request.setNroDocumento(ISBANStringUtils.eliminarCeros(consultaDebinInDTO.getNroDocumento()));
        request.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(consultaDebinInDTO.getTipoDocumento()).getCodigoNumero());
        request.setEstado(estadoBusqueda.getEstado().getDescripcion());
        request.setNroPagina(estadoBusqueda.getNroPagina());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(consultaDebinInDTO.getFechaDesde());
        XMLGregorianCalendar fechaDesde = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        request.setFechaCreacionDesde(fechaDesde);
        c.setTime(consultaDebinInDTO.getFechaHasta());
        XMLGregorianCalendar fechaHasta = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        request.setFechaCreacionHasta(fechaHasta);
        
        if (consultaDebinInDTO.getConsultaComprador()) {
            CompradorDebinDTO comprador = new CompradorDebinDTO();
            comprador.setCuit(request.getCuit());
            request.setComprador(comprador);
        } else {
            VendedorDebinDTO vendedor = new VendedorDebinDTO();
            vendedor.setCuit(request.getCuit());
            request.setVendedor(vendedor);
        }
        LOGGER.info("DebinWSBOImpl generarRequestListaDebin OK");
        return request;
    }

    /**
     * generarRequestListaDebinAPI.
     *
     * @param consultaDebinInDTO
     *            the consulta debin in DTO
     * @param estadoBusqueda
     *            the estado busqueda
     * @return the request lista debin
     * @throws DatatypeConfigurationException
     *             the datatype configuration exception
     * @throws DAOException
     *             the DAO exception
     */




    /**
	 * Obtener cuit cliente.
	 *
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
    protected String obtenerCuitCliente() throws DAOException {
        Cliente cliente = sesionCliente.getCliente();
        try {
            if (cliente.getNumeroCUILCUIT() == null || cliente.getNumeroCUILCUIT().isEmpty()) {
                NupDTO nupDTO = nupDAO.obtenerNUP(cliente);
                estadisticaManager.add(EstadisticasConstants.CONSULTA_CUIL_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK); 
                sesionCliente.getCliente().setNumeroCUILCUIT(nupDTO.getCuit(NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI));
                return nupDTO.getCuit(NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI);
            } else {
            	return cliente.getNumeroCUILCUIT().replaceAll("-", "");
            }
        } catch (DAOException e) {
            LOGGER.error("No se puede obtener el cuit para el cliente {}.", cliente.getNup(), e);
            estadisticaManager.add(EstadisticasConstants.CONSULTA_CUIL_DEBINWS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR); 
            throw new DAOException(e.getMessage(), CODIGO_ERROR_CUIT);
        }
    }

    /**
	 * Generar request detalle debin.
	 *
	 * @param cnsDetalleDebinInDTO
	 *            the cns detalle debin in DTO
	 * @return the request debin
	 * @throws DAOException
	 *             the DAO exception
	 */
    private RequestDebin generarRequestDetalleDebin(ConsultaDetalleDebinWSInDTO cnsDetalleDebinInDTO) throws DAOException {
        LOGGER.info("DebinWSBOImpl generando request consulta detalle debin");
        RequestDebin  requestDetalleDebin = new RequestDebin();
        requestDetalleDebin.setCanal(cnsDetalleDebinInDTO.getCanal());
        requestDetalleDebin.setCuit(obtenerCuitCliente());
        requestDetalleDebin.setIdDebin(cnsDetalleDebinInDTO.getIdDebin());
        requestDetalleDebin.setIp(sesionCliente.getIpCliente());
        requestDetalleDebin.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(cnsDetalleDebinInDTO.getTipoDocumento()).getCodigoNumero());
        requestDetalleDebin.setNroDocumento(ISBANStringUtils.eliminarCeros(cnsDetalleDebinInDTO.getNroDocumento()));
        return requestDetalleDebin;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSBO#descargarComprobante(ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteContracargoInDTO)
     */
    @Override
    public Respuesta<Reporte> descargarComprobante(ComprobanteInDTO comprobanteDTO) {
        LOGGER.info("Debin" + comprobanteDTO.getTipoComprobante() + " _ iniciando descargarComprobante");
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        try {
            Reporte reporte = debinWSDAO.descargarComprobante(comprobanteDTO);
            registrarEstadisticasDescargarComprobante(comprobanteDTO.getTipoComprobante());
            respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaReporte.setRespuesta(reporte);
        } catch (Exception e) {
            return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
        }
        LOGGER.info("Debin" + comprobanteDTO.getTipoComprobante() + " _ finalizando descargarComprobante");
        return respuestaReporte;        
    }

    /**
     * Registra estadisticas dependiendo el tipo de comprobante.
     *
     * @param tipoComprobante the tipo comprobante
     */
    private void registrarEstadisticasDescargarComprobante(String tipoComprobante) {
        if (PAGO.equals(tipoComprobante)) {
            estadisticaManager.add(EstadisticasConstants.COMPROBANTE_PAGAR_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (RECHAZO.equals(tipoComprobante)) {
            estadisticaManager.add(EstadisticasConstants.COMPROBANTE_RECHAZAR_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (ELIMINACION.equals(tipoComprobante)) {
            estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ELIMINAR_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
    }

    /**
     * eliminarDebin.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<DebinWSEliminarOutDTO> eliminarDebin() {
        LOGGER.info("DebinEliminarDebin _ iniciando eliminar debin");
        DebinWSEliminarOutDTO debinWSEliminarOutDTO = new DebinWSEliminarOutDTO();
        Respuesta<DebinWSEliminarOutDTO> rtaDebinWSEliminar = new Respuesta<DebinWSEliminarOutDTO>();
        try {
            if (sesionParametros.getContador() == null) {
                sesionParametros.setContador(new ContadorIntentos(2));
            }
            RequestDebin requestEliminarDebin = generarRequestEliminarDebinWS();
            Response responseEliminar = debinWSDAO.eliminarDebin(requestEliminarDebin);

            if (responseEliminar.getError() != null && !CODIGO_DEBIN_OK.equals(responseEliminar.getError().getCodigo())) {
                estadisticaManager.add(EstadisticasConstants.ELIMINAR_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                if (!sesionParametros.getContador().permiteReintentar()) {
                    sesionParametros.setContador(null);
                    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                            CodigoMensajeConstantes.DEBINWS_ERROR_ELIMINAR, sesionParametros.getDetalleDebin().getComprador().getNombreTitular());
                }
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_ELIMINAR,
                        CodigoMensajeConstantes.DEBINWS_ERROR_ELIMINAR, sesionParametros.getDetalleDebin().getComprador().getNombreTitular());
            }
            LOGGER.info("DebinWSBOImpl Eliminar debin respuesta OK");
            Date fechaComprobante = new Date();
            String fechaComprobanteFormat = ISBANStringUtils.formatearFechaConHoraParaComprobante(fechaComprobante);
            debinWSEliminarOutDTO.setNumeroComprobante(obtenerNumeroComprobante(fechaComprobante));
            debinWSEliminarOutDTO.setFechaComprobante(fechaComprobanteFormat);
            sesionParametros.getDetalleDebin().setNumeroComprobante(debinWSEliminarOutDTO.getNumeroComprobante());
            sesionParametros.getDetalleDebin().setFechaComprobante(fechaComprobanteFormat);
            estadisticaManager.add(EstadisticasConstants.ELIMINAR_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            return respuestaFactory.crearRespuestaOk(debinWSEliminarOutDTO);
        } catch (DAOException e) {
            if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
                estadisticaManager.add(EstadisticasConstants.ELIMINAR_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
                rtaDebinWSEliminar = this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_TIMEOUT, 
                        CodigoMensajeConstantes.DEBINWS_ERROR_ELIMINAR, sesionParametros.getDetalleDebin().getComprador().getNombreTitular());
            } else {
                estadisticaManager.add(EstadisticasConstants.ELIMINAR_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                rtaDebinWSEliminar =  respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                        CodigoMensajeConstantes.DEBINWS_ERROR_ELIMINAR, sesionParametros.getDetalleDebin().getComprador().getNombreTitular());
            }
            if (!sesionParametros.getContador().permiteReintentar()) {
                sesionParametros.setContador(null);
                rtaDebinWSEliminar =  respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                        CodigoMensajeConstantes.DEBINWS_ERROR_ELIMINAR, sesionParametros.getDetalleDebin().getComprador().getNombreTitular());
            }
            return rtaDebinWSEliminar;
        }
    }

    /**
     * Generar request eliminar debin WS.
     *
     * @return the request debin
     * @throws DAOException the DAO exception
     */
    private RequestDebin generarRequestEliminarDebinWS() throws DAOException {
        RequestDebin requestDebin = new RequestDebin();
        requestDebin.setCanal(CANAL);
        requestDebin.setCuit(obtenerCuitCliente());
        requestDebin.setIp(sesionCliente.getIpCliente());
        requestDebin.setNroDocumento(ISBANStringUtils.eliminarCeros(sesionCliente.getCliente().getDni()));
        requestDebin.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(sesionCliente.getCliente().getTipoDocumento()).getCodigoNumero());
        requestDebin.setIdDebin(sesionParametros.getDetalleDebin().getIdDebin());
        return requestDebin;
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSBO#pagarDebin(ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView)
	 */
    @Override
	public Respuesta<PagarDebinWSView> pagarDebin(PagarDebinWSView pagarDebinWSView) {
		LOGGER.info("DebinWSBOImpl DebinWSBO iniciando pagar debin");

		if (sesionParametros.getContador() == null) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

		if (NO_ADHERIDO_DEBIN.equals(sesionParametros.getDetalleDebin().getEstado())) {
			try {
				LOGGER.info("DebinWSBOImpl Generando request para adhesion comprador DAO");
				RequestAdhesion request = generarRequestWSAdhesionComprador();
				LOGGER.info("Request adhesion comprador: {}" , request.toString());
				Response responseWSAdhesion = debinWSDAO.adhesionComprador(request);
				if (!ESTADO_RESPONSE_DEBINWS_OK.equals(responseWSAdhesion.getEstado())) {
					estadisticaManager.add(EstadisticasConstants.PAGAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					if (!sesionParametros.getContador().permiteReintentar()) {
	                    sesionParametros.setContador(null);
	                    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
	                            CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
	                }
					return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_ADHESION_COMPRADOR,
							CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
				}
				estadisticaManager.add(EstadisticasConstants.PAGAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} catch (DAOException e) {
				if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
					estadisticaManager.add(EstadisticasConstants.PAGAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
					return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_TIMEOUT, 
							CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
				} else {
					estadisticaManager.add(EstadisticasConstants.PAGAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					if (!sesionParametros.getContador().permiteReintentar()) {
	                    sesionParametros.setContador(null);
	                    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
	                            CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
	                }
					return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
							CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
				}
			}
		}

		try {
			LOGGER.info("DebinWSBOImpl Generando request para confirmar debito DAO");
			RequestConfirmacionDebitoV3 request = generarRequestWSConfirmacionDebito(PAGAR_DEBIN);
			LOGGER.info("Request confirmar debito: {}" , request.toString());
			Response responseWSConfirmacionDebito = debinWSDAO.confirmacionDebitoV3(request);
			if (!ESTADO_RESPONSE_DEBINWS_OK.equals(responseWSConfirmacionDebito.getEstado())) {
			    estadisticaManager.add(EstadisticasConstants.PAGAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			    if (!sesionParametros.getContador().permiteReintentar()) {
                    sesionParametros.setContador(null);
                    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                            CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
                }
			    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                        CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO); //En Manager envia el mensaje error_pagar
			}
			//genero nro de comprobante con timestamp y coloco la fecha actual para fecha de comprobante
			Date fechaComprobante = new Date();
			String fechaComprobanteFormat = ISBANStringUtils.formatearFechaConHoraParaComprobante(fechaComprobante);
            pagarDebinWSView.setNumeroComprobante(obtenerNumeroComprobante(fechaComprobante));
            pagarDebinWSView.setFechaComprobante(fechaComprobanteFormat);
            sesionParametros.getDetalleDebin().setNumeroComprobante(pagarDebinWSView.getNumeroComprobante());
            sesionParametros.getDetalleDebin().setFechaComprobante(fechaComprobanteFormat);
            estadisticaManager.add(EstadisticasConstants.PAGAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} catch (DAOException e) {
		    if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
                estadisticaManager.add(EstadisticasConstants.PAGAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
                return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_TIMEOUT, 
                        CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            } else {
                estadisticaManager.add(EstadisticasConstants.PAGAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                if (!sesionParametros.getContador().permiteReintentar()) {
                    sesionParametros.setContador(null);
                    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                            CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
                }
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                        CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            }
		}
        return respuestaFactory.crearRespuestaOk(pagarDebinWSView);
	}

	/**
	 * generarRequestWSAdhesionComprador.
	 *
	 * @return the request adhesion
	 */
	private RequestAdhesion generarRequestWSAdhesionComprador() {
		RequestAdhesion request = new RequestAdhesion();
		request.setCanal(CANAL);
		request.setCuit(sesionCliente.getCliente().getNumeroCUILCUIT());
		request.setIp(sesionCliente.getIpCliente());
		request.setNroDocumento(sesionCliente.getCliente().getDni());
		request.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(sesionCliente.getCliente().getTipoDocumento()).getCodigoNumero());
		request.setCuenta(new CuentaDebinDTO());
		String cbuCliente = sesionParametros.getDetalleDebin().getComprador().getCbu();
		request.getCuenta().setCbu(cbuCliente);
		Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(cbuCliente);
		String numeroCuenta = StringUtils.substring(cbuCliente, 4, 7) + StringUtils.substring(cbuCliente, 14, 21);
		request.getCuenta().setNumero(numeroCuenta);
		boolean esTitular = CODIGO_TITULARIDAD.equals(cuentaCliente.getCodigoTitularidad()) ? true : false;
		request.getCuenta().setEsTitular(esTitular);
		return request;
	}

	/**
	 * generarRequestWSConfirmacionDebito.
	 *
	 * @param operacion the operacion
	 * @return the request confirmacion debito V3
	 */
	private RequestConfirmacionDebitoV3 generarRequestWSConfirmacionDebito(String operacion) {
		RequestConfirmacionDebitoV3 request = new RequestConfirmacionDebitoV3();
		ConsultaDetalleDebinWSOutDTO detalleDebin = sesionParametros.getDetalleDebin();
		request.setCanal(CANAL);
		request.setCuit(sesionCliente.getCliente().getNumeroCUILCUIT().replaceAll("-", ""));
		request.setIp(sesionCliente.getIpCliente());
		request.setNroDocumento(ISBANStringUtils.eliminarCeros(sesionCliente.getCliente().getDni()));
		request.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(sesionCliente.getCliente().getTipoDocumento()).getCodigoNumero());
		request.setComprador(new CompradorDebinDTO());
		request.getComprador().setTitular(sesionCliente.getCliente().getNombre()); 
		request.getComprador().setCuit(sesionCliente.getCliente().getNumeroCUILCUIT().replaceAll("-", ""));
		request.getComprador().setCuenta(new CuentaDebinDTO());
		String cbuCliente = sesionParametros.getDetalleDebin().getComprador().getCbu();
		request.getComprador().getCuenta().setCbu(cbuCliente);
		request.getComprador().getCuenta().setTipo(detalleDebin.getComprador().getTipo());	
		request.setCodigo(operacion);
		request.setDescripcion(sesionParametros.getDetalleDebin().getDescripcion());
		request.setIdDebin(sesionParametros.getDetalleDebin().getIdDebin());
		request.setImporte(Double.valueOf(sesionParametros.getDetalleDebin().getImporteSolicitado()));//verificar formato 0.70 198.88
		request.setMoneda(sesionParametros.getDetalleDebin().getMoneda());//verificar ARS 032
		request.setVendedor(new VendedorDebinDTO());
		request.getVendedor().setCuit(sesionParametros.getDetalleDebin().getVendedor().getCuit());
		request.getVendedor().setCuenta(new CuentaDebinDTO());
		request.getVendedor().getCuenta().setCbu(sesionParametros.getDetalleDebin().getVendedor().getCbu());
		request.setPuntaje(sesionParametros.getDetalleDebin().getPuntaje());
		request.setConcepto(sesionParametros.getDetalleDebin().getConcepto());
		return request;
	}


    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSBO#rechazarDebin()
     */
    @Override
    public Respuesta<RechazarDebinWSOutDTO> rechazarDebin() {
        LOGGER.info("DebinWSBOImpl DebinWSBO iniciando rechazar debin");
        RechazarDebinWSOutDTO rechazarDebinWSOutDTO = new RechazarDebinWSOutDTO();

        if (sesionParametros.getContador() == null) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }
        try {
            LOGGER.info("DebinWSBOImpl Generando request para confirmar debito DAO");
            RequestConfirmacionDebitoV3 request = generarRequestWSConfirmacionDebito(RECHAZAR_DEBIN);
            LOGGER.info("Request confirmar debito: {}" , request.toString());
            Response responseWSConfirmacionDebito = debinWSDAO.confirmacionDebitoV3(request);
            if (!ESTADO_RESPONSE_DEBINWS_OK.equals(responseWSConfirmacionDebito.getEstado())) {
                estadisticaManager.add(EstadisticasConstants.RECHAZAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                if (!sesionParametros.getContador().permiteReintentar()) {
                    sesionParametros.setContador(null);
                    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                            CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
                }
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                        CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            }
            Date fechaComprobante = new Date();
            rechazarDebinWSOutDTO.setNumeroComprobante(obtenerNumeroComprobante(fechaComprobante));
            sesionParametros.getDetalleDebin().setNumeroComprobante(rechazarDebinWSOutDTO.getNumeroComprobante());
            sesionParametros.getDetalleDebin().setFechaComprobante(ISBANStringUtils.formatearFechaConHoraParaComprobante(fechaComprobante));
            estadisticaManager.add(EstadisticasConstants.RECHAZAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } catch (DAOException e) {
            if (!sesionParametros.getContador().permiteReintentar()) {
                sesionParametros.setContador(null);
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                        CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            }
            if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
                estadisticaManager.add(EstadisticasConstants.RECHAZAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
                return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_TIMEOUT, 
                        CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            } else {
                estadisticaManager.add(EstadisticasConstants.RECHAZAR_DEBIN_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                        CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            }
        }

        return respuestaFactory.crearRespuestaOk(rechazarDebinWSOutDTO);
    }

	/**
     *  
     * obtener nro de comprobante formateado yyMMddHHmmss.
     *
     * @param fechaComprobante the fecha comprobante
     * @return the string
     */
    protected String obtenerNumeroComprobante(Date fechaComprobante) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_NRO_COMPROBANTE);
        return sdf.format(fechaComprobante);
    }

	@Override
	public Respuesta<SolicitarContracargoDebinOutDTO> solicitarContracargo(Cliente cliente, String idDebin) {
		try {
			RequestContracargo requestContracargo = generarRequestContracargo(cliente, idDebin);
			ResponseContracargo responseContracargo = debinWSDAO.solicitarContracargo(requestContracargo);

			if(responseContracargo.getEstado().equals(ESTADO_RESPONSE_DEBINWS_OK)) {
				SolicitarContracargoDebinOutDTO response = new SolicitarContracargoDebinOutDTO();
				response.setFechaOperacion(responseContracargo.getFechaNegocio().toGregorianCalendar().getTime());
				response.setIdContracargo(responseContracargo.getId());
				return respuestaFactory.crearRespuestaOk(response);
			}else {
				// TODO aqui podriamos tratar el error de forma mas feliz
				LOGGER.error("Respuesta ERROR del servicio solicitarContracargo de Prisma");
				return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
	                    CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
			}
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO,
                    CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
		}
	}

	private RequestContracargo generarRequestContracargo(Cliente cliente, String idDebin) throws DAOException {
		RequestContracargo request = new RequestContracargo();
	    LOGGER.info("DebinWSBOImpl generarRequestContracargo");
        request.setCanal(CANAL);
        request.setCuit(obtenerCuitCliente());
        request.setIp(sesionCliente.getIpCliente());
        request.setTipo(TIPO_CONSULTA_DEFAULT);
        request.setCuit(sesionCliente.getCliente().getNumeroCUILCUIT().replaceAll("-", ""));
        request.setNroDocumento(ISBANStringUtils.eliminarCeros(cliente.getDni()));
        request.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(cliente.getTipoDocumento()).getCodigoNumero());

        request.setId(idDebin);
        ConsultaDetalleDebinWSOutDTO detalleDebin = sesionParametros.getDetalleDebin();
        
        if(detalleDebin != null) {
	        DetalleDebinBreveDTO detalle = new DetalleDebinBreveDTO();
			detalle.setImporte(detalleDebin.getMoneda());
			detalle.setMoneda(detalleDebin.getImporteSolicitado());
			detalle.setMotivo(detalleDebin.getConcepto());
	        request.setDetalle(detalle);
	        
	        DatosUsuarioBreveDTO usuario = new DatosUsuarioBreveDTO();
			usuario.setCuit(sesionCliente.getCliente().getNumeroCUILCUIT().replaceAll("-", ""));
	        request.setComprador(usuario);
	        CuentaDebinDTO cuentaCompradorDebin = new CuentaDebinDTO();
	        cuentaCompradorDebin.setCbu(detalleDebin.getComprador().getCbu());
	        cuentaCompradorDebin.setTipo(detalleDebin.getComprador().getTipo());
	        usuario.setCuenta(cuentaCompradorDebin);
	        
	        DatosUsuarioBreveDTO vendedor = new DatosUsuarioBreveDTO();
	        vendedor.setCuit(detalleDebin.getVendedor().getCuit());
	        CuentaDebinDTO cuentaVendedorDebin = new CuentaDebinDTO();
			cuentaVendedorDebin.setCbu(detalleDebin.getVendedor().getCbu());
	        vendedor.setCuenta(cuentaVendedorDebin);
	        request.setVendedor(vendedor);
	        return request;
        } else {
        	LOGGER.error("No tenemos detalle debin guardado {}", idDebin);
        	throw new DAOException("No tenemos detalle debin guardado ", idDebin);
        }
	}

}