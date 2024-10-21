/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.bo.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasDatos;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasIn;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasItem;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasItem2;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasLista;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasOut;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasPieza;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.dao.ConsultaTarjetasDAO;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.TarjetaDatos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.bo.TrackingTarjetasBO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dao.TrackingTarjetasDAO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dto.TrackingPiezaDTO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.ConsultaTarjetasMonederoOutEntity;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.TarjetaMonederoEntity;



/**
 * TrackingTarjetasBOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class TrackingTarjetasBOImpl implements TrackingTarjetasBO {
    
   
    /** TarjetasDAO. */
    @Autowired
    private ConsultaTarjetasDAO tarjetasDAO;
    
    /** TrackingTarjetasDAO. */
    @Autowired
    private TrackingTarjetasDAO trackingTarjetasDAO;
    
    
    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    
    /** The sign. */
    @Autowired
    private Sign sign;
    
    /** The app encoding. */
    private static final String APPENCODING = "UTF-8";
    
    /** The Constant JKS. */
    private static final String JKS = "ANDREANI";
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingTarjetasBOImpl.class);

    /** Tipo consulta tarjetas monedero. */
    private static final String TIPO_CONSULTA_MONEDERO = "T";
    

    /** The Constant NRO_ORDEN_FIRMANTE. */
    private static final String NRO_ORDEN_FIRMANTE = "001";

    /** The Constant CODIGO_APP_ABAE. */
    private static final String CODIGO_APP_ABAE = "ABAE";

    
    /** The Constant CANAL_ID. */
    private static final String CANAL_ID = "HTML";
    
    /** The Constant CANAL_TIPO. */
    private static final String CANAL_TIPO = "04";
    
    /** The Constant STATUS_OK. */
    private static final String STATUS_OK = "0";
    
    /** The Constant SUBCANAL_ID. */
    private static final String SUBCANAL_ID = "0001";
    
    /** The Constant SUBCANAL_TIPO. */
    private static final String SUBCANAL_TIPO = "99";
    
    /** CODIGO_APLICACION_MONEDERO. */
    public static final String CODIGO_APLICACION_MONEDERO    = "AMON";

    /** TIPO_CUENTA_MONEDERO. */
    private static final String TIPO_CUENTA_MONEDERO = "43";

    /** PIEZA_STATUS_OK. */
    private static final String PIEZA_STATUS_OK = "0";
    
    /** estadosAndreaniPermitidos. */
    private static final String[] estadosAndreaniPermitidos  =  { "12", "14", "15", "16", "17",
            "18", "19", "21", "22", "23", "24", "32", "35","SU","C6","DE","TT","TR" };

    
    /**
	 * obtenerTrackingTarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    @Override
    public Respuesta<List<TrackingPiezaDTO>> obtenerTrackingTarjetas(Cliente cliente)  {
        TrackingTarjetasIn requestWSTracking = new TrackingTarjetasIn();
        List<TrackingTarjetasItem> itemsRequestWSTracking = new ArrayList<TrackingTarjetasItem>();
        try {
            //MONEDERO
            itemsRequestWSTracking.addAll(obtenerDatosTarjetasMonedero(cliente)); 
            //CREDITO - RECARGABLES
            itemsRequestWSTracking.addAll(obtenerDatosTarjetasCreditoYRecargables(cliente));
            //DEBITO
            itemsRequestWSTracking.addAll(obtenerDatosTarjetasDebito(cliente));
           //ARMADO DEL REQUEST DEL WEB SERVICE
            requestWSTracking = obtenerRequestServicioTracking(cliente,itemsRequestWSTracking);
            
        } catch (DAOException  e) {
            LOGGER.error("Eror obteniendo informacion de tarjetas", e); 
            return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_TRACKING_TARJETAS, CodigoMensajeConstantes.ERROR_GENERICO_TRACKING_TARJETAS);  
        } catch (BusinessException e) {
            LOGGER.error("Error generando firma del WS de tracking de tarjetas");
            return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_TRACKING_TARJETAS, CodigoMensajeConstantes.ERROR_GENERICO_TRACKING_TARJETAS);
        } catch (Exception e) {
            LOGGER.error("Eror procesando informacion de tarjetas", e); 
            return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_TRACKING_TARJETAS, CodigoMensajeConstantes.ERROR_GENERICO_TRACKING_TARJETAS);  
        }
        //INVOCAMOS AL WS
        
        return obtenerTrackingServiceAndreani(requestWSTracking);
        
       
    }
    
    /**
	 * Obtener tracking service andreani.
	 *
	 * @param requestWSTracking
	 *            the request WS tracking
	 * @return the respuesta
	 */
    private Respuesta<List<TrackingPiezaDTO>> obtenerTrackingServiceAndreani(TrackingTarjetasIn requestWSTracking) {
        try {
            TrackingTarjetasOut outResponse = trackingTarjetasDAO.consultarTraza(requestWSTracking);
            if (!STATUS_OK.equals(outResponse.getCodRetorno())) {
                LOGGER.debug("Error llamando al WS de tracking de tarjetas WSDelTarjSOAP");
                return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_TRACKING_TARJETAS, CodigoMensajeConstantes.ERROR_GENERICO_TRACKING_TARJETAS);
            }
            if(!hayPiezasEnTracking(outResponse)) {
                return this.respuestaFactory.crearRespuestaWarning("", TipoError.WARNING_TRACKING_TARJETAS_SIN_DATOS, CodigoMensajeConstantes.WARNING_TRACKING_TARJETAS_SIN_DATOS);
            }
            List<TrackingPiezaDTO> piezasDTO = new ArrayList<TrackingPiezaDTO>();
            List<TrackingTarjetasPieza> piezaList = outResponse.getPiezas().getPieza();
            boolean hayPiezasParaMostrar = false;
            boolean hayPiezasStatusOK = false;
            for(TrackingTarjetasPieza pieza : piezaList) {
                if (PIEZA_STATUS_OK.equals(pieza.getStatusPieza())){
                    hayPiezasStatusOK = true;
                    if(pieza.getPiezaAbierta() != null &&  "".equals(pieza.getPiezaAbierta().trim()) && tieneEstadoValido(pieza)) {
                        hayPiezasParaMostrar = true;
                        piezasDTO.add(new TrackingPiezaDTO(pieza));
                    }
                }
            }
            if(!hayPiezasStatusOK) {
                LOGGER.debug("WS de tracking de tarjetas WSDelTarjSOAP - TODAS LAS PIEZAS CON ERROR");
                return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_TRACKING_TARJETAS, CodigoMensajeConstantes.ERROR_GENERICO_TRACKING_TARJETAS); 
            }
            if(!hayPiezasParaMostrar) {
                LOGGER.debug("Warning tracking tarjetas, hay piezas con status ok pero no se reconoce el estado");
                return this.respuestaFactory.crearRespuestaWarning("", TipoError.WARNING_TRACKING_TARJETAS_SIN_DATOS, CodigoMensajeConstantes.WARNING_TRACKING_TARJETAS_SIN_DATOS);
            }
            return respuestaFactory.crearRespuestaOk(piezasDTO);

        } catch (DAOException e) {
            LOGGER.error("Error llamando al WS de tracking de tarjetas WSDelTarjSOAP");
            return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_TRACKING_TARJETAS, CodigoMensajeConstantes.ERROR_GENERICO_TRACKING_TARJETAS);
        }
    }

    /**
	 * tieneEstadoValido.
	 *
	 * @param pieza
	 *            the pieza
	 * @return true, if successful
	 */
    private boolean tieneEstadoValido(TrackingTarjetasPieza pieza) {
        String estado = null;
        if (pieza.getEvento() != null) {
            estado = pieza.getEvento().getIdEstado(); 
        } else {
            estado = pieza.getCodEstadoActual(); 
        }
        return isEstadoAndreaniPermitido(estado);
    }
    
    /**
	 * isEstadoAndreaniPermitido.
	 *
	 * @param eventoEstado
	 *            the evento estado
	 * @return true, if is estado andreani permitido
	 */
    private boolean isEstadoAndreaniPermitido(String eventoEstado) {
        for (String est : estadosAndreaniPermitidos) {
            if (est.equals(eventoEstado)) {
                return true;
            }
        }
        return false;
    }

    /**
	 * Valida que el WS haya retornado piezas en tracking.
	 *
	 * @param outResponse
	 *            the out response
	 * @return true, if successful
	 */
    private boolean hayPiezasEnTracking(TrackingTarjetasOut outResponse) {
        return outResponse.getPiezas() != null && outResponse.getPiezas().getPieza() != null &&
                !outResponse.getPiezas().getPieza().isEmpty();

    }

    /**
	 * 
	 * obtenerRequestServicioTracking.
	 *
	 * @param cliente
	 *            the cliente
	 * @param itemRequestListWS
	 *            the item request list WS
	 * @return the tracking tarjetas in
	 * @throws BusinessException
	 *             the business exception
	 */
    private TrackingTarjetasIn obtenerRequestServicioTracking(Cliente cliente, List<TrackingTarjetasItem> itemRequestListWS) throws BusinessException {
        TrackingTarjetasIn inRequest = new TrackingTarjetasIn();
        inRequest.setCanalTipo(CANAL_TIPO);
        inRequest.setCanalID(CANAL_ID);
        inRequest.setSubcanalTipo(SUBCANAL_TIPO);
        inRequest.setSubcanalID(SUBCANAL_ID);
        inRequest.setNUP(cliente.getNup());
        inRequest.setTipoPersona(cliente.getTipoPersona());
        inRequest.setTipoID(cliente.getTipoDocumento());
        inRequest.setIDCliente(cliente.getDni());
        inRequest.setFechaNac(cliente.getFechaNacimiento());
        LOGGER.debug("Generando firma de tracking de tarjetas...");
        inRequest.setFirma(generarFirmaTrackingTarjetas(inRequest));
        LOGGER.debug("Se genero la firma de tracking de tarjetas OK");
        TrackingTarjetasDatos datoRequest = new TrackingTarjetasDatos();
        datoRequest.setItem(itemRequestListWS);
        inRequest.setDatos(datoRequest);
        return inRequest;
    }

    /**
	 * Arma los items request para el webservice de andreani con los datos de
	 * las tarjetas de debito.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 */
    private List<TrackingTarjetasItem> obtenerDatosTarjetasDebito(Cliente cliente) {
        List<TrackingTarjetasItem> trackItemRequestList = new ArrayList<TrackingTarjetasItem>();
        LOGGER.debug("Obteniendo datos de tarjetas de debito...");
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())   && NRO_ORDEN_FIRMANTE.equals(cuenta.getNroOrdenFirmante()) && CODIGO_APP_ABAE.equals(cuenta.getCodigoAplicacion())){
                TrackingTarjetasItem2 trackingTarjetasItem2 = new TrackingTarjetasItem2();
                if(cuenta.getNroSucursal().length() > 3) {
                    trackingTarjetasItem2.setSucursal(cuenta.getNroSucursal().substring(cuenta.getNroSucursal().length()-3));
                  }else {
                      trackingTarjetasItem2.setSucursal(ISBANStringUtils.fillNum(cuenta.getNroSucursal(), 3));   
                  }
                trackingTarjetasItem2.setCodEstado(ISBANStringUtils.fillStr("", 2));
                trackingTarjetasItem2.setCodProd(cuenta.getCodigoAplicacion());
                trackingTarjetasItem2.setNroIdComp(cuenta.getNroTarjetaCredito().substring(4));
                trackingTarjetasItem2.setTipoCuenta(cuenta.getTipoCuenta());
                trackItemRequestList.add(obtenerTrackingTarjetasItem(trackingTarjetasItem2));
            }
        }    
        return trackItemRequestList;
    }

    /**
	 * Arma los items request para el webservice de andreani con los datos de
	 * las tarjetas de credito.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
    private List<TrackingTarjetasItem>  obtenerDatosTarjetasCreditoYRecargables(Cliente cliente) throws DAOException {
        List<TrackingTarjetasItem> itemRequestList = new ArrayList<TrackingTarjetasItem>();
        for (Cuenta cuenta : cliente.getCuentas()) {
            if(  TipoCuenta.VISA.equals((cuenta.getTipoCuentaEnum())) ||  TipoCuenta.VISA_RECARGABLE.equals((cuenta.getTipoCuentaEnum())) 
                     || TipoCuenta.AMEX.equals(cuenta.getTipoCuentaEnum()) ) {
                
                //SI ES TITULAR LLAMO AL SERVICIO CNSTJCDATC
                if(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad())) {
                    itemRequestList.addAll(obtenerItemsTarjetaCreditoTitular(cliente, cuenta));
                //SI ES ADICIONAL TOMO LA INFORMACION DE LAS CUENTAS DEL CLIENTE    
                }else if (TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL.equals(cuenta.getCodigoTitularidad())) {
                    TrackingTarjetasItem2 item2Request = new TrackingTarjetasItem2();
                    if(cuenta.getNroSucursal().length() > 3) {
                      item2Request.setSucursal(cuenta.getNroSucursal().substring(cuenta.getNroSucursal().length()-3));
                    }else {
                      item2Request.setSucursal(ISBANStringUtils.fillNum(cuenta.getNroSucursal(), 3));   
                    }
                    item2Request.setCodProd(cuenta.getCodigoAplicacion());
                    item2Request.setNroIdComp(cuenta.getNroTarjetaCredito().substring(4));
                    item2Request.setTipoCuenta(cuenta.getTipoCuenta());
                    item2Request.setCodEstado(ISBANStringUtils.fillStr("", 2));
                    itemRequestList.add(obtenerTrackingTarjetasItem(item2Request));
                }
            }
       }
        return itemRequestList;
    }
    
    
    /**
	 * Obtiene informacion de las tarjetas llamado al servicio CNSTJCDATC.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
    private List<TrackingTarjetasItem> obtenerItemsTarjetaCreditoTitular(Cliente cliente, Cuenta cuenta) throws DAOException {
        List<TrackingTarjetasItem>  itemRequestList = new ArrayList<TrackingTarjetasItem>();
        ConsultaDatosTarjetasIn consultaTarjetaIn = new ConsultaDatosTarjetasIn();
        consultaTarjetaIn.setCliente(cliente);
        if(TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum()) || TipoCuenta.VISA_RECARGABLE.equals(cuenta.getTipoCuentaEnum())  ) {
            consultaTarjetaIn.setMarca("1");
            consultaTarjetaIn.setTipoCuenta("1");
        }
        if(TipoCuenta.AMEX.equals(cuenta.getTipoCuentaEnum())) {
            consultaTarjetaIn.setMarca("4");
            consultaTarjetaIn.setTipoCuenta("3");
        }
        consultaTarjetaIn.setNroCuenta(cuenta.getNroCuentaProducto());
        LOGGER.debug("TrackingBO - Consultado datos de tarjeta de credito CNSTJCDATC...");
        
        boolean reEnganche = true;
        String valorReEnganche = "0000000000000000";
        
        while(reEnganche) {
            consultaTarjetaIn.setClaveReEnganche(valorReEnganche);
            ConsultaDatosTarjetasOut tarjetasOut =  tarjetasDAO.consultaDatosTarjetas(consultaTarjetaIn);
            for (TarjetaDatos tarjeta : tarjetasOut.getTarjetas()) {
                TrackingTarjetasItem2 item2Request = new TrackingTarjetasItem2();
                item2Request.setSucursal(tarjeta.getSucursalCtaRelacionada());
                item2Request.setCodProd(tarjeta.getApliCtaRelacionada());
                item2Request.setNroIdComp(tarjeta.getNroTarjeta());
                item2Request.setTipoCuenta(cuenta.getTipoCuenta());
                item2Request.setCodEstado(ISBANStringUtils.fillStr("", 2));
                itemRequestList.add(obtenerTrackingTarjetasItem(item2Request));
            }
            if (tarjetasOut.getHayReEnganche() != null) {
                reEnganche = tarjetasOut.getHayReEnganche().equals("S") ?  true : false;
                valorReEnganche = tarjetasOut.getMarcaReLlamada();
            }else {
                reEnganche = false;
            }
                
            
        }
        

        LOGGER.debug("Consulta de datos de tarjeta de credito CNSTJCDATC OK");
        return itemRequestList;
    }

    
    /**
	 * TrackingTarjetasItem.
	 *
	 * @param item2Request
	 *            the item 2 request
	 * @return the tracking tarjetas item
	 */
    private TrackingTarjetasItem obtenerTrackingTarjetasItem(TrackingTarjetasItem2 item2Request) {
        TrackingTarjetasLista listaRequest = new TrackingTarjetasLista();
        listaRequest.setItem(item2Request);
        TrackingTarjetasItem itemRequest = new TrackingTarjetasItem();
        itemRequest.setNroIdComponente(ISBANStringUtils.fillStr("", 20)); 
        itemRequest.setNroPieza(ISBANStringUtils.llenaConCeros(7)); 
        itemRequest.setLista(listaRequest);
        return itemRequest;
    }

    /**
	 * Arma los items request para el webservice de andreani con los datos de
	 * las tarjetas monedero.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 */
    private List<TrackingTarjetasItem> obtenerDatosTarjetasMonedero(Cliente cliente)  {
        List<TrackingTarjetasItem> itemRequestList = new ArrayList<TrackingTarjetasItem>();
        LOGGER.debug("Obteniendo datos de tarjetas monedero...");
        try {
            ConsultaTarjetasMonederoOutEntity tarjetasMonederoEntity = trackingTarjetasDAO.getDatosTarjetaMonedero(cliente, TIPO_CONSULTA_MONEDERO);
            for (TarjetaMonederoEntity tarjetaMonedero : tarjetasMonederoEntity.getTarjetasMonedero()) {
                TrackingTarjetasItem2 item2Request = new TrackingTarjetasItem2();
                item2Request.setSucursal(tarjetaMonedero.getSucursal());
                item2Request.setCodProd(CODIGO_APLICACION_MONEDERO);
                item2Request.setNroIdComp(tarjetaMonedero.getNumeroTarjetaTag());
                item2Request.setTipoCuenta(TIPO_CUENTA_MONEDERO);
                item2Request.setCodEstado(ISBANStringUtils.fillStr("", 2));                
                itemRequestList.add(obtenerTrackingTarjetasItem(item2Request));
            }
            LOGGER.debug("Consulta de datos de tarjeta monedero OK");
        }catch(DAOException e) {
            LOGGER.debug("ERROR al consultar  datos de tarjeta monedero- se continua con el tracking de otras tarjetas");
        }
        return itemRequestList;
    }

    /**
	 * generarFirmaTrackingTarjetas.
	 *
	 * @param inRequest
	 *            the in request
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
    private String generarFirmaTrackingTarjetas(TrackingTarjetasIn inRequest) throws BusinessException {
        StringBuilder firmaBuild = new StringBuilder().append(inRequest.getCanalTipo()).append("|").append(inRequest.getCanalID()).append("|").append(inRequest.getSubcanalTipo())
                .append("|").append(inRequest.getSubcanalID()).append("|").append(inRequest.getNUP()).append("|").append(inRequest.getTipoPersona()).append("|")
                .append(inRequest.getTipoID()).append("|").append(inRequest.getIDCliente()).append("|").append(inRequest.getFechaNac());
        try {
            return new String(sign.buildB64Signature(firmaBuild.toString().getBytes(APPENCODING), JKS, true));
        } catch (DAOException e) {
            throw new BusinessException(e);
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(e);
        }
    }
}
