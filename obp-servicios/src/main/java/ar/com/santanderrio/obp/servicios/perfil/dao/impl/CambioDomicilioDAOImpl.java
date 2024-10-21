/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.perfil.dao.CambioDomicilioDAO;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DomicilioEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ProductoEntity;
import ar.com.santanderrio.obp.servicios.perfil.utils.PerfilUtils;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Clase CambioDomicilioDAOImpl.
 *
 * @author Silvina_Luque
 */

@Component
public class CambioDomicilioDAOImpl extends IatxBaseDAO implements CambioDomicilioDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CambioDomicilioDAOImpl.class);

    /** The servicio CNSDOMPROD. */
    private static final String SERVICIO_CNSDOMPROD = "CNSDOMPROD";

    /** The version CNSDOMPROD. */
    private static final String VERSION_CNSDOMPROD = "100";

    /** The servicio CMBDOMICRM. */
    private static final String SERVICIO_CMBDOMICRM = "CMBDOMICRM";

    /** The version CMBDOMICRM. */
    private static final String VERSION_CMBDOMICRM = "100";

    /** The servicio CMBTELCRM. */
    private static final String SERVICIO_CMBTELCRM = "CMBTELCRM_";

    /** The version CMBTELCRM. */
    private static final String VERSION_CMBTELCRM = "100";

    /** The servicio CMBDOMYTEL. */
    private static final String SERVICIO_CMBDOMYTEL = "CMBDOMYTEL";

    /** The version CMBTELCRM. */
    private static final String VERSION_CMBDOMYTEL = "100";

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    
    @Autowired
    private SesionParametros sesionParametros;

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The file jasper. */
    @Value("classpath:/report/perfil/cambioDomicilio/ComprobanteCambioDomicilio.jasper")
    private Resource fileJasperComprobanteCambioDomicilio;

    /** The logo cierre. */
    @Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
    private Resource logoCierre;

    /** The logo cabecera. */
    @Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
    private Resource logoCabecera;

    /** The Constant LOGO_CABECERA. */
    private static final String LOGO_CABECERA = "LOGO_CABECERA";

    /** The Constant FECHA. */
    private static final String FECHA = "FECHA";

    /** The Constant DOMICILIO_ANTERIOR. */
    private static final String DOMICILIO_ANTERIOR = "DOMICILIO_ANTERIOR";

    /** The Constant DOMICILIO_ACTUAL. */
    private static final String DOMICILIO_ACTUAL = "DOMICILIO_ACTUAL";

    /** The Constant NRO_COMPROBANTE. */
    private static final String NRO_COMPROBANTE = "NRO_COMPROBANTE";

    /** The Constant LOGO_CIERRE. */
    private static final String LOGO_CIERRE = "LOGO_CIERRE";

    /** The Constant CODIGO_RETORNO. */
    private static final int CODIGO_RETORNO = 1;

    /** The Constant HAY_MAS_DATOS. */
    private static final int HAY_MAS_DATOS = 2;

    /** The Constant CANTIDADAD_TOTAL_DOMICILIOS. */
    private static final int CANTIDADAD_TOTAL_DOMICILIOS = 3;

    /** The Constant DOMICILIOS_INFORMADOS. */
    private static final int DOMICILIOS_INFORMADOS = 4;

    /** The start domicilios. */
    private static int START_DOMICILIOS = 5;

    /**
     * Este servicio devuelve los domicilios registrados del cliente utilizando el
     * servicio iatx CNSDOMPROD.
     *
     * @param consultaDomiciliosInEntity
     *            the consulta domicilios in entity
     * @return the consulta domicilios out entity
     * @throws DAOException
     *             the DAO exception
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.dao.CambioDomicilioDAO#
     * consultaDomiciliosRegistrados(ar.com.santanderrio.obp.servicios.perfil.
     * entities.ConsultaDomiciliosInEntity)
     */
    @Override
    public Respuesta<ConsultaDomiciliosOutEntity> consultaDomiciliosRegistrados(
            ConsultaDomiciliosInEntity consultaDomiciliosInEntity) throws DAOException {
        Respuesta<ConsultaDomiciliosOutEntity> respuestaConsulta = new Respuesta<ConsultaDomiciliosOutEntity>();
        ConsultaDomiciliosOutEntity consultaDomiciliosOutEntity = new ConsultaDomiciliosOutEntity();
        boolean primeraLlamada = true;
        try {
            do {
                ejecutarCNSDOMPROD(consultaDomiciliosInEntity, consultaDomiciliosOutEntity);
                primeraLlamada = false;
                consultaDomiciliosInEntity
                        .setSecuenciaDomicilio(consultaDomiciliosOutEntity.getUltimaSecuenciaDomicilio());
            } while ("S".equals(consultaDomiciliosOutEntity.getHayMasDatos())
                    || "I".equals(consultaDomiciliosOutEntity.getHayMasDatos())
                    || "M".equals(consultaDomiciliosOutEntity.getHayMasDatos()));

            respuestaConsulta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaConsulta.setRespuesta(consultaDomiciliosOutEntity);
            respuestaConsulta.setItemMensajeRespuesta(respuestaFactory.obtenerListItemMensaje(
                    CodigoMensajeConstantes.CODIGO_DATOS_DE_CONTACTO_CAMBIO_DOMICILIO,
                    TipoError.CAMBIO_DOMICILIO_DATOS_CONTACTO.getDescripcion()));
            return respuestaConsulta;
        } catch (IatxException e) {
            LOGGER.info("Validar comportamiento consulta Domicilio", e);
            if (primeraLlamada) {
                respuestaConsulta.setEstadoRespuesta(EstadoRespuesta.ERROR);
                respuestaConsulta.setRespuestaVacia(true);
                respuestaConsulta.setItemMensajeRespuesta(respuestaFactory.obtenerListItemMensaje(
                        CodigoMensajeConstantes.CODIGO_ERROR_FALLO_TOTAL_CAMBIO_DOMICILIO,
                        TipoError.ERROR_TOTAL_CAMBIO_DOMICILIO.getDescripcion()));
                return respuestaConsulta;
            } else {
                respuestaConsulta.setEstadoRespuesta(EstadoRespuesta.WARNING);
                respuestaConsulta.setRespuesta(consultaDomiciliosOutEntity);
                respuestaConsulta.setItemMensajeRespuesta(respuestaFactory.obtenerListItemMensaje(
                        CodigoMensajeConstantes.CODIGO_ERROR_FALLO_PARCIAL_CAMBIO_DOMICILIO,
                        TipoError.ERROR_PARCIAL_CAMBIO_DOMICILIO.getDescripcion()));
                respuestaConsulta.getItemsMensajeRespuesta()
                        .addAll(respuestaFactory.obtenerListItemMensaje(
                                CodigoMensajeConstantes.CODIGO_DATOS_DE_CONTACTO_CAMBIO_DOMICILIO,
                                TipoError.CAMBIO_DOMICILIO_DATOS_CONTACTO.getDescripcion()));
                return respuestaConsulta;
            }
        }
    }

    /**
     * Ejecutar CNSDOMPROD.
     *
     * @param consultaDomiciliosInEntity
     *            the consulta domicilios in entity
     * @param consultaDomiciliosOutEntity
     *            the consulta domicilios out entity
     * @return the consulta domicilios out entity
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             cod Retorno != 0
     */
    private ConsultaDomiciliosOutEntity ejecutarCNSDOMPROD(ConsultaDomiciliosInEntity consultaDomiciliosInEntity,
            ConsultaDomiciliosOutEntity consultaDomiciliosOutEntity) throws IatxException, DAOException {
        IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSDOMPROD, VERSION_CNSDOMPROD);

        IatxRequestData iatxRequestData = this.generateRequestDataCnsDomProd(consultaDomiciliosInEntity);
        iatxRequest.setData(iatxRequestData);
        IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
        LOGGER.debug("CambioDomicilioDAO _ Finalizando llamada iatx CNSDOMPROD");
        int errorCode = iatxResponse.getErrorCode();

        String[] domicilioArray = iatxResponse.getTrama().trim().split("õ");
        START_DOMICILIOS = 5;
        long cantDomicilios = 0;

        if (OK_CODIGO_RETORNO == errorCode) {
            procesarPrimerosDatos(iatxResponse, consultaDomiciliosOutEntity);
            cantDomicilios = consultaDomiciliosOutEntity.getCantidadDomiciliosInformados();
            llenarDomicilios(domicilioArray, cantDomicilios, consultaDomiciliosOutEntity);

        } else {
            this.manejarErrorCambioDomicilio(errorCode, iatxResponse.getErrorSystem());
        }

        int ultPos = saltarDomicilios(START_DOMICILIOS, 10 - cantDomicilios);
        llenarProductos(domicilioArray, ultPos, consultaDomiciliosOutEntity);
        return consultaDomiciliosOutEntity;
    }

    /**
	 * Llenar domicilios.
	 *
	 * @param domicilioArray
	 *            the domicilio array
	 * @param cantDomicilios
	 *            the cant domicilios
	 * @param consultaDomiciliosOutEntity
	 *            the consulta domicilios out entity
	 */
    private void llenarDomicilios(String[] domicilioArray, long cantDomicilios,
            ConsultaDomiciliosOutEntity consultaDomiciliosOutEntity) {
        if (cantDomicilios > 0) {
            for (int i = 0; i < cantDomicilios; i++) {
                DomicilioEntity domicilioEntity = new DomicilioEntity();

                domicilioEntity.setSecuenciaDomicilio(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setTipoDomicilio(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setCalle(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setObservaciones(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setSucursal(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setApt(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setLocalidad(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setComuna(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setCodigoPostal(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setProvincia(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setPais(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setMarcaDomErroneo(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                domicilioEntity.setTelefono(domicilioArray[START_DOMICILIOS]);
                START_DOMICILIOS++;
                consultaDomiciliosOutEntity.getListaDomicilios().add(domicilioEntity);
            }
        }
    }

    /**
	 * Llenar productos.
	 *
	 * @param domicilioArray
	 *            the domicilio array
	 * @param posicion
	 *            the posicion
	 * @param consultaDomiciliosOutEntity
	 *            the consulta domicilios out entity
	 */
    private void llenarProductos(String[] domicilioArray, int posicion,
            ConsultaDomiciliosOutEntity consultaDomiciliosOutEntity) {

        int cantidadroductos = Integer.valueOf(domicilioArray[posicion]);

        int iterante = 135;
        if (cantidadroductos < 135) {
            iterante = cantidadroductos;
        }

        for (int i = 0; i < iterante; i++) {
            ProductoEntity productoEntity = new ProductoEntity();
            posicion++;
            productoEntity = new ProductoEntity();
            productoEntity.setSecuenciaDomicilio(domicilioArray[posicion]);
            posicion++;
            productoEntity.setCuenta(domicilioArray[posicion]);
            posicion++;
            productoEntity.setOficina(domicilioArray[posicion]);
            posicion++;
            productoEntity.setDescripcionOficina(domicilioArray[posicion]);
            posicion++;
            productoEntity.setProducto(domicilioArray[posicion]);
            posicion++;
            productoEntity.setDescripcionProducto(domicilioArray[posicion]);
            posicion++;
            productoEntity.setSubproducto(domicilioArray[posicion]);
            consultaDomiciliosOutEntity.getListaproductos().add(productoEntity);
        }
    }

    /**
     * Este servicio devuelve datos adicionales de un domicilio utilizando el
     * servicio CMBDOMICRM.
     *
     * @param consultaDomicilioInEntity
     *            the consulta domicilio in entity
     * @return the consulta datos domicilio out entity
     * @throws DAOException
     *             the DAO exception
     */
    public ConsultaDatosDomicilioOutEntity consultaDatosDomicilio(
            ConsultaDatosDomicilioInEntity consultaDomicilioInEntity) throws DAOException {
        LOGGER.info("CambioDomicilioDAO _ Iniciando metodo consultaDatosDomicilio");
        IatxRequest iatxRequest = new IatxRequest(SERVICIO_CMBDOMICRM, VERSION_CMBDOMICRM);
        ConsultaDatosDomicilioOutEntity consultaDatosDomicilioOutEntity = new ConsultaDatosDomicilioOutEntity();

        try {
            LOGGER.debug("CambioDomicilioDAO _ Iniciando llamada iatx CMBDOMICRM");
            IatxRequestData iatxRequestData = this.generateRequestDataCmbDomiCRM(consultaDomicilioInEntity);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            LOGGER.debug("CambioDomicilioDAO _ Finalizando llamada iatx CMBDOMICRM");
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                consultaDatosDomicilioOutEntity = processTrama(iatxResponse.getTrama(),
                        ConsultaDatosDomicilioOutEntity.class);
            } else {
                this.manejarErrorConsultaDatosDomicilio(errorCode, iatxResponse.getErrorSystem());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        LOGGER.debug("CambioDomicilioDAO _ Finalizando llamada consultaDatosDomicilio");
        return consultaDatosDomicilioOutEntity;

    }

    /**
     * Este servicio devuelve datos adicionales de un telefono utilizando el
     * servicio CMBTELCRM.
     *
     * @param consultaDatosTelefonoInEntity
     *            the consulta datos telefono in entity
     * @return the consulta datos telefono out entity
     * @throws DAOException
     *             the DAO exception
     */
    public ConsultaDatosTelefonoOutEntity consultaDatosTelefono(
            ConsultaDatosTelefonoInEntity consultaDatosTelefonoInEntity) throws DAOException {
        LOGGER.info("CambioDomicilioDAO _ Iniciando metodo consultaDatosTelefono");
        IatxRequest iatxRequest = new IatxRequest(SERVICIO_CMBTELCRM, VERSION_CMBTELCRM);
        ConsultaDatosTelefonoOutEntity consultaDatosTelefonoOutEntity = new ConsultaDatosTelefonoOutEntity();
        try {
            LOGGER.debug("CambioDomicilioDAO _ Iniciando llamada iatx CMBTELCRM");
            IatxRequestData iatxRequestData = this.generateRequestDataCmbTelCRM(consultaDatosTelefonoInEntity);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            LOGGER.debug("CambioDomicilioDAO _ Finalizando llamada iatx CMBTELCRM");
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                consultaDatosTelefonoOutEntity = processTrama(iatxResponse.getTrama(),
                        ConsultaDatosTelefonoOutEntity.class);
            } else {
                this.manejarErrorConsultaDatosTelefono(errorCode, iatxResponse.getErrorSystem());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        LOGGER.debug("CambioDomicilioDAO _ Finalizando llamada consultaDatosTelefono");
        return consultaDatosTelefonoOutEntity;

    }

    /**
     * Este servicio guarda la informacion de un cambio de domicilio/telefono
     * utilizando el servicio CMBDOMYTEL.
     *
     * @param cambioDomicilioInEntity
     *            the cambio domicilio in entity
     * @return the cambio domicilio out entity
     * @throws DAOException
     *             the DAO exception
     */
    public CambioDomicilioOutEntity guardarCambioDomicilio(CambioDomicilioInEntity cambioDomicilioInEntity)
            throws DAOException {
        LOGGER.info("CambioDomicilioDAO _ Iniciando metodo guardarCambioDomicilio");
        IatxRequest iatxRequest = new IatxRequest(SERVICIO_CMBDOMYTEL, VERSION_CMBDOMYTEL);
        CambioDomicilioOutEntity cambioDomicilioOutEntity = new CambioDomicilioOutEntity();
        try {
            LOGGER.debug("CambioDomicilioDAO _ Iniciando llamada iatx CMBDOMYTEL");
            IatxRequestData iatxRequestData = this.generateRequestDataCmbDomYTel(cambioDomicilioInEntity);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            LOGGER.debug("CambioDomicilioDAO _ Finalizando llamada iatx CMBDOMYTEL");
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                cambioDomicilioOutEntity.setNroComprobante(iatxResponse.getNroComprobante());
                cambioDomicilioOutEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
                cambioDomicilioOutEntity.setTrama(iatxResponse.getTrama());
                cambioDomicilioOutEntity.setFechaTransaccion(iatxResponse.getFecha());
            } else {
                this.manejarErrorCambioDomicilio(errorCode, iatxResponse.getErrorSystem());
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        LOGGER.debug("CambioDomicilioDAO _ Finalizando llamada guardarCambioDomicilio");
        return cambioDomicilioOutEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.dao.CambioDomicilioDAO#
     * descargarComprobante(ar.com.santanderrio.obp.servicios.perfil.entities.
     * DatosComprobanteEntity)
     */
    @Override
    public Reporte descargarComprobante(DatosComprobanteEntity datos) {
        LOGGER.info("CambioDomicilioDAO iniciando descargar comprobante");
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try {
            // se carga el reporte
            jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteCambioDomicilio.getInputStream());
            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
            // se procesa el archivo jasper

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            LOGGER.debug("Completando parametros de reporte CambioDomicilio");
            parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
            parameters.put(NRO_COMPROBANTE, datos.getNroComprobante());
            parameters.put(DOMICILIO_ACTUAL, datos.getDomicilioCompletoActual());
            parameters.put(DOMICILIO_ANTERIOR, datos.getDomicilioCompletoAnterior());
            parameters.put(FECHA, datos.getFecha());
            parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            byte[] byteArray = outStream.toByteArray();
            Reporte reporteCambioDomicilio = new Reporte();
            reporteCambioDomicilio.setTipoArchivo(TipoArchivoEnum.PDF);
            reporteCambioDomicilio.setBytes(byteArray);
            reporteCambioDomicilio.setNombre("Comprobante_CambioDomicilio.pdf");
            LOGGER.info("CambioDomicilioDAO finalizando descargar comprobante");
            return reporteCambioDomicilio;
        } catch (JRException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ISBANRuntimeException(ex);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ISBANRuntimeException(e);
        }
    }

    /**
     * Generate request data cns dom prod.
     *
     * @param entity
     *            the entity
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataCnsDomProd(ConsultaDomiciliosInEntity entity) {
        IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
        iatxRequestData.addBodyValue(entity.getSecuenciaDomicilio());
        return iatxRequestData;
    }

    /**
     * Manejar error cambio domicilio.
     *
     * @param errorCode
     *            the error code
     * @param errorSystem
     *            the error system
     * @throws DAOException
     *             the DAO exception
     */
    private void manejarErrorCambioDomicilio(int errorCode, String errorSystem) throws DAOException {
        LOGGER.debug("Codigo de error no esperado de iatx en servicio CNSDOMPROD. %s  -  %s ", errorCode, errorSystem);
        throw new DAOException();
    }

    /**
     * Manejar error consulta datos telefono.
     *
     * @param errorCode
     *            the error code
     * @param errorSystem
     *            the error system
     * @throws DAOException
     *             the DAO exception
     */
    private void manejarErrorConsultaDatosTelefono(int errorCode, String errorSystem) throws DAOException {
        LOGGER.debug("Codigo de error no esperado de iatx en servicio CMBTELCRM. %s  -  %s ", errorCode, errorSystem);
        throw new DAOException();

    }

    /**
     * Generate request data cmb dom Y tel.
     *
     * @param entity
     *            the entity
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataCmbDomYTel(CambioDomicilioInEntity entity) {
        IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
        iatxRequestData.addBodyValue(entity.getAccion());// 1
        iatxRequestData.addBodyValue(entity.getSecuencia());// 2
        iatxRequestData.addBodyValue(entity.getTipoDomicilio());// 3
        iatxRequestData.addBodyValue(entity.getTipoVia());// 4
        iatxRequestData.addBodyValue(entity.getCalle() == null ? PerfilUtils.llenaConBlancos(50)
                : ISBANStringUtils.fillStr(PerfilUtils.str2StrDomicilios(entity.getCalle()), 50));// 5
        iatxRequestData.addBodyValue(entity.getTipoConstruccion());// 6
        iatxRequestData.addBodyValue(entity.getTipoNucleoUrbano());// 7
        iatxRequestData.addBodyValue(entity.getObservaciones1());// 8
        iatxRequestData.addBodyValue(entity.getObservaciones2().substring(0, 24) + "000000000}000000000}"+PerfilUtils.llenaConBlancos(56)); // 9
        iatxRequestData.addBodyValue(entity.getSucursalCasilla());// 10
        iatxRequestData.addBodyValue(entity.getApt() == null ? PerfilUtils.llenaConBlancos(10)
                : ISBANStringUtils.fillNum(entity.getApt(), 10)); // 11
        iatxRequestData
                .addBodyValue(entity.getLocalidad() == null ? PerfilUtils.llenaConBlancos(7) : entity.getLocalidad());// 12
        iatxRequestData.addBodyValue(entity.getDescLocalidad() == null ? PerfilUtils.llenaConBlancos(30)
                : ISBANStringUtils.fillStr(entity.getDescLocalidad(), 30)); // 13
        iatxRequestData.addBodyValue(entity.getComuna()); // 14
        iatxRequestData.addBodyValue(sesionParametros.getBarrioNormalizacionDomicilio());// 15
        if (entity.getCodigoPostal() == null) { // 16
            iatxRequestData.addBodyValue(PerfilUtils.llenaConBlancos(8));
        } else {
            iatxRequestData.addBodyValue(entity.getCodigoPostal().trim().length() == 4
                    ? " " + ISBANStringUtils.fillStr(entity.getCodigoPostal(), 7)
                    : entity.getCodigoPostal());
        }
        iatxRequestData.addBodyValue(entity.getRutaCartero()); // 17
        iatxRequestData.addBodyValue(
                entity.getCodigoProvincia() == null ? PerfilUtils.llenaConBlancos(2) : entity.getCodigoProvincia());// 18
        iatxRequestData.addBodyValue(entity.getCodigoPais());// 19
        iatxRequestData.addBodyValue(entity.getTituDomicilio());// 20
        iatxRequestData.addBodyValue(entity.getFechaVerificacion());// 21
        iatxRequestData.addBodyValue(entity.getMarcaNormalizacion());// 22
        iatxRequestData.addBodyValue(entity.getMarcaDomErroneo());// 23
        iatxRequestData.addBodyValue(entity.getMarcaCorrespDevuel());// 24
        iatxRequestData.addBodyValue(entity.getMotivoDevolucion());// 25
        iatxRequestData.addBodyValue(entity.getUsuarioAlta()); // 26
        iatxRequestData.addBodyValue(entity.getFechaAltaRegistro()); // 27
        iatxRequestData.addBodyValue(entity.getUsuarioUltimaMod());// 28
        iatxRequestData.addBodyValue(entity.getNroTerminalUltMod());// 29
        iatxRequestData.addBodyValue(entity.getSucursalUltMod());// 30
        iatxRequestData.addBodyValue(entity.getTimestampUltMod()); // 31
        iatxRequestData.addBodyValue(PerfilUtils.llenaConBlancos(8)); // 32
                                                                      // Nombre
                                                                      // programa
        iatxRequestData.addBodyValue(entity.getSecuenciaTelefono());// 33
        iatxRequestData.addBodyValue(entity.getTipoTelefono());// 34
        iatxRequestData.addBodyValue(entity.getClaseTelefono()); // 35
        iatxRequestData.addBodyValue(entity.getPrefijo() == null ? PerfilUtils.llenaConBlancos(7)
                : ISBANStringUtils.fillStr(entity.getPrefijo(), 7));// 36
        iatxRequestData.addBodyValue(entity.getCaracteristica() == null ? PerfilUtils.llenaConBlancos(4)
                : ISBANStringUtils.fillStr(entity.getCaracteristica(), 4));// 37
        iatxRequestData.addBodyValue(entity.getNroTelefono() == null ? PerfilUtils.llenaConBlancos(8)
                : ISBANStringUtils.fillStr(entity.getNroTelefono(), 8)); // 38
        iatxRequestData.addBodyValue(entity.getNroInterno());// 39
        iatxRequestData.addBodyValue(entity.getObservacionesSemaforo()); // 40
        iatxRequestData.addBodyValue(entity.getUsuarioAltaTel()); // 41
        iatxRequestData.addBodyValue(entity.getFechaAltaRegistroTel());// 42
        iatxRequestData.addBodyValue(entity.getUsuarioUltimaModTel()); // 43
        iatxRequestData.addBodyValue(entity.getNroTerminalUltModTel()); // 44
        iatxRequestData.addBodyValue(entity.getSucursalUltModTel());// 45
        iatxRequestData.addBodyValue(entity.getTimestampUltModTel());// 46
        iatxRequestData.addBodyValue(entity.getDescripcionProvincia() == null ? PerfilUtils.llenaConBlancos(30)
                : ISBANStringUtils.fillStr(entity.getDescripcionProvincia(), 30));// 47
        iatxRequestData.addBodyValue(entity.getCalleAnterior());// 48
        iatxRequestData.addBodyValue(entity.getObservaciones1Anterior());// 49
        iatxRequestData.addBodyValue(entity.getAptAnterior());// 50
        iatxRequestData.addBodyValue(entity.getDescLocalidadAnterior());// 51
        iatxRequestData.addBodyValue(entity.getDescComunaAnterior()); // 52
        iatxRequestData.addBodyValue(entity.getCodigoPostalAnterior());// 53
        iatxRequestData.addBodyValue(entity.getCodigoProvinciaAnterior());// 54
        iatxRequestData.addBodyValue(entity.getDescripcionProvinciaAnterior() == null ? PerfilUtils.llenaConBlancos(30)
                : ISBANStringUtils.fillStr(entity.getDescripcionProvinciaAnterior(), 30));// 55
        iatxRequestData.addBodyValue(entity.getPrefijoAnterior() == null ? PerfilUtils.llenaConBlancos(7)
                : ISBANStringUtils.fillStr(entity.getPrefijoAnterior(), 7));// 56
        iatxRequestData.addBodyValue(entity.getCaracteristicaAnterior() == null ? PerfilUtils.llenaConBlancos(4)
                : ISBANStringUtils.fillStr(entity.getCaracteristicaAnterior(), 4));// 57
        iatxRequestData.addBodyValue(entity.getNroTelefonoAnterior() == null ? PerfilUtils.llenaConBlancos(8)
                : ISBANStringUtils.fillStr(entity.getNroTelefonoAnterior(), 8)); // 58
        iatxRequestData.addBodyValue(entity.getCantidadProductosDom() == null ? ISBANStringUtils.fillNum("", 3)
                : ISBANStringUtils.fillNum(entity.getCantidadProductosDom(), 3));// 59
        // Productos
        generarRequesDataProoductos(iatxRequestData, entity);
        return iatxRequestData;
    }

    /**
     * Generar reques data prooductos.
     *
     * @param iatxRequestData
     *            the iatx request data
     * @param entity
     *            the entity
     */
    private void generarRequesDataProoductos(IatxRequestData iatxRequestData, CambioDomicilioInEntity entity) {
        List<ProductoEntity> listProd = entity.getListaproductos();
        if (listProd != null && !listProd.isEmpty()) {
            for (Iterator<ProductoEntity> iterator = listProd.iterator(); iterator.hasNext();) {
                ProductoEntity pEntity = iterator.next();
                // 61
                iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(pEntity.getCuenta(), 16));
                // 62
                iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(pEntity.getOficina(), 4));
                // 63
                iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(pEntity.getProducto(), 2));
                // 64
                iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(pEntity.getSubproducto(), 4));
                // 65
                iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(pEntity.getDescripcionProducto(), 40));
            }
        } else {
            // 61
            iatxRequestData.addBodyValue(PerfilUtils.llenaConBlancos(16));
            // 62
            iatxRequestData.addBodyValue(PerfilUtils.llenaConBlancos(4));
            // 63
            iatxRequestData.addBodyValue(PerfilUtils.llenaConBlancos(2));
            // 64
            iatxRequestData.addBodyValue(PerfilUtils.llenaConBlancos(4));
            // 65
            iatxRequestData.addBodyValue(PerfilUtils.llenaConBlancos(40));
        }
    }

    /**
     * Generate request data cmb tel CRM.
     *
     * @param entity
     *            the entity
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataCmbTelCRM(ConsultaDatosTelefonoInEntity entity) {
        IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
        iatxRequestData.addBodyValue(entity.getOpcion());
        iatxRequestData.addBodyValue(entity.getCliente().getNup());
        iatxRequestData.addBodyValue(entity.getSecuenciaTel());
        iatxRequestData.addBodyValue(entity.getSecuenciaDom());
        iatxRequestData.addBodyValue(entity.getTelTipo());
        iatxRequestData.addBodyValue(entity.getTelClase());
        return iatxRequestData;
    }

    /**
     * Manejar error consulta datos domicilio.
     *
     * @param errorCode
     *            the error code
     * @param errorSystem
     *            the error system
     * @throws DAOException
     *             the DAO exception
     */
    private void manejarErrorConsultaDatosDomicilio(int errorCode, String errorSystem) throws DAOException {
        LOGGER.debug("Codigo de error no esperado de iatx en servicio CMBDOMICRM. %s  -  %s ", errorCode, errorSystem);
        throw new DAOException();

    }

    /**
     * Generate request data cmb domi CRM.
     *
     * @param entity
     *            the entity
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataCmbDomiCRM(ConsultaDatosDomicilioInEntity entity) {
        IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
        iatxRequestData.addBodyValue(entity.getOpcion());
        iatxRequestData.addBodyValue(entity.getCliente().getNup());
        iatxRequestData.addBodyValue(entity.getSecuenciaDomicilio());
        iatxRequestData.addBodyValue(entity.getTipoDomicilio());
        iatxRequestData.addBodyValue(entity.getTipoVia());
        iatxRequestData.addBodyValue(entity.getNombreCalle());
        iatxRequestData.addBodyValue(entity.getTipoConstruccion());
        iatxRequestData.addBodyValue(entity.getTipoNucleoUrbano());
        iatxRequestData.addBodyValue(entity.getObservaciones1());
        iatxRequestData.addBodyValue(entity.getObservaciones2());
        iatxRequestData.addBodyValue(entity.getSucursalCasilla());
        iatxRequestData.addBodyValue(entity.getApt());
        iatxRequestData.addBodyValue(entity.getLocalidadCiudad());
        iatxRequestData.addBodyValue(entity.getDescLocalidad());
        iatxRequestData.addBodyValue(entity.getComuna());
        iatxRequestData.addBodyValue(entity.getDescComuna());
        iatxRequestData.addBodyValue(entity.getCodigoPostal());
        iatxRequestData.addBodyValue(entity.getRutaCartero());
        iatxRequestData.addBodyValue(entity.getCodigoProvincia());
        iatxRequestData.addBodyValue(entity.getCodigoPais());
        iatxRequestData.addBodyValue(entity.getTipoDomicilio());
        iatxRequestData.addBodyValue(entity.getFechaVerificacion());
        iatxRequestData.addBodyValue(entity.getMarcaNormalizacion());
        iatxRequestData.addBodyValue(entity.getMarcaDomErroneo());
        iatxRequestData.addBodyValue(entity.getMarcaCorrespDevuel());
        iatxRequestData.addBodyValue(entity.getMarcaDevolucion());
        iatxRequestData.addBodyValue(entity.getUsuarioAlta());
        iatxRequestData.addBodyValue(entity.getFechaAltaRegistro());
        iatxRequestData.addBodyValue(entity.getUsuarioUltimaMod());
        iatxRequestData.addBodyValue(entity.getNroTerminalUltModif());
        iatxRequestData.addBodyValue(entity.getSucursalUltModReg());
        iatxRequestData.addBodyValue(entity.getTimestampUltModReg());
        return iatxRequestData;

    }

    /**
	 * Procesar primeros datos.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @param consultaDomiciliosOutEntity
	 *            the consulta domicilios out entity
	 */
    private void procesarPrimerosDatos(IatxResponse iatxResponse,
            ConsultaDomiciliosOutEntity consultaDomiciliosOutEntity) {

        String[] tramaEnArray = iatxResponse.getTrama().trim().split("õ");

        consultaDomiciliosOutEntity.setCodigoRetornoExtendido(
                tramaEnArray[CODIGO_RETORNO].substring(5, tramaEnArray[CODIGO_RETORNO].length()));
        consultaDomiciliosOutEntity.setHayMasDatos(tramaEnArray[HAY_MAS_DATOS]);
        consultaDomiciliosOutEntity.setCantidadTotalDomicilios(tramaEnArray[CANTIDADAD_TOTAL_DOMICILIOS]);
        consultaDomiciliosOutEntity
                .setCantidadDomiciliosInformados(Long.parseLong(tramaEnArray[DOMICILIOS_INFORMADOS]));
    }

    /**
	 * Saltar domicilios.
	 *
	 * @param ultimaPosicion
	 *            the ultima posicion
	 * @param cantDomiciliosVacios
	 *            the cant domicilios vacios
	 * @return the int
	 */
    private static int saltarDomicilios(int ultimaPosicion, long cantDomiciliosVacios) {
        return (int) (ultimaPosicion + (cantDomiciliosVacios * 13));
    }

    /**
	 * Proesar domicilios.
	 *
	 * @param lista
	 *            the lista
	 * @param posicion
	 *            the posicion
	 * @param consultaDomiciliosOutEntity
	 *            the consulta domicilios out entity
	 * @return the consulta domicilios out entity
	 */
    public ConsultaDomiciliosOutEntity proesarDomicilios(String[] lista, int posicion,
            ConsultaDomiciliosOutEntity consultaDomiciliosOutEntity) {
        DomicilioEntity domicilioEntity = null;

        List<DomicilioEntity> domicilios = new ArrayList<DomicilioEntity>();

        for (int i = 0; i < 10; i++) {

            domicilioEntity = new DomicilioEntity();

            domicilioEntity.setSecuenciaDomicilio(lista[posicion]);
            posicion++;
            domicilioEntity.setTipoDomicilio(lista[posicion]);
            posicion++;
            domicilioEntity.setCalle(lista[posicion]);
            posicion++;
            domicilioEntity.setObservaciones(lista[posicion]);
            posicion++;
            domicilioEntity.setSucursal(lista[posicion]);
            posicion++;
            domicilioEntity.setApt(lista[posicion]);
            posicion++;
            domicilioEntity.setLocalidad(lista[posicion]);
            posicion++;
            domicilioEntity.setComuna(lista[posicion]);
            posicion++;
            domicilioEntity.setCodigoPostal(lista[posicion]);
            posicion++;
            domicilioEntity.setProvincia(lista[posicion]);
            posicion++;
            domicilioEntity.setPais(lista[posicion]);
            posicion++;
            domicilioEntity.setMarcaDomErroneo(lista[posicion]);
            posicion++;
            domicilioEntity.setTelefono(lista[posicion]);
            posicion++;
            domicilios.add(domicilioEntity);
        }
        consultaDomiciliosOutEntity.setListaDomicilios(domicilios);
        return consultaDomiciliosOutEntity;
    }    
}
