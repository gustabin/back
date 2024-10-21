/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.*;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype.FondosTenenciasPrototype;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.TipoPapel;
import ar.com.santanderrio.obp.servicios.inversiones.comun.EnumFondosDisponiblesTipoOperacion;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao.FondoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.RescateDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.dao.RescateFondosPorEspecieDAOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity.ConfirmarRescateEspecieIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity.ConfirmarRescateEspecieOut;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateFondoEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateDesdeGrilla;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ErrorRescateCitiFondoCompassException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ServicioCerradoExCitiException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ServicioDeshabilitadoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class RescateBOImpl.
 *
 * @author b039920
 */
@Component("rescateBO")
public class RescateBOImpl extends InversionesAbstractBO implements RescateBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RescateBOImpl.class);

    /** The Constant LEGAL_SIMULACION_RESCATE. */
    private static final String LEGAL_SIMULACION_RESCATE = "50016";

    /** Codigo de moneda pesos para servicio. */
    private static final String MONEDA_PESOS = "0";

    /** Codigo de moneda pesos para servicio. */
    private static final String MONEDA_DOLARES = "2";

    /** The Constant LARGO_COD_FONDO. */
    private static final int LARGO_COD_FONDO = 3;

    /** The Constant LARGO_COD_FONDO_FDC. */
    private static final int LARGO_COD_FONDO_FDC = 4;

    /** The Constant CUENTA_UNICA_TIPO. */
    private static final String CUENTA_UNICA_TIPO = "2";

    /** The Constant CATORCE. */
    private static final int CATORCE = 14;

    /** The Constant SUSCRIPCION_CORRECTA. */
    private static final String SUSCRIPCION_RESCATE_CORRECTA = "10432";

    /** The Constant FINALIZAR_SUSCRIPCION_FALLO_RESCATE_GENERICO. */
    public static final String FINALIZAR_RESCATE_FALLO_GENERICO = "10433";

    /** The Constant FINALIZAR_SUSCRIPCION_FALLO_RESCATE_GENERICO_BPRIV. */
    public static final String FINALIZAR_RESCATE_FALLO_GENERICO_BPRIV = "10464";

    /** The Constant MENSAJE_LEGALES_CUOTA_PARTES. */
    private static final String MENSAJE_LEGALES_CUOTA_PARTES = "10007";

    /** The Constant HABILITADO_PARA_RESCATAR. */
    private static final String HABILITADO_PARA_RESCATAR = "1";

    /** The Constant LONG_CTA_CRED. */
    private static final int LONG_CTA_CRED = 7;

    /** The Constant LONG_CTA_CRED_CITI. */
    private static final int LONG_CTA_CRED_CITI = 10;

    /** The Constant CERO_STRING. */
    private static final String CERO_STRING = "0";

    /** The Constant MSG_ERROR_SALDO_INSUFICIENTE. */
    private static final String MSG_ERROR_SALDO_INSUFICIENTE = "No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.  174- SALDO INSUFICIENTE. ";

    /** The Constant CODIGO_FONDO_CITI_COMPASS. */
    private static final String CODIGO_FONDO_CITI_COMPASS = "2510";

    /** The Constant OPERACION RESCATE. */
    private static final String RESCATE = "rescate";

    /** The fondo DAO. */
    @Autowired
    private FondoDAO fondoDAO;

    /** The mensaje bo. */
    @Autowired
    private FondoBO fondoBO;

    /** The mensaje bo. */
    @Autowired
    private MensajeBO mensajeBo;

    /** The session parametros. */
    @Autowired
    private SesionParametros sessionParametros;

    /** dao. */
    @Autowired
    private RescateDAO rescateDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The consulta FondoBPriv DAO. */
    @Autowired
    private FondoBPrivDAO fondoBPrivDAO;
    /**
     * BO de legales.
     */
    @Autowired
    private LegalBO legalBO;

    @Autowired
    private RescateFondosPorEspecieDAOImpl rescateFondosPorEspecieDAOImpl;

    /** The rescate Fondo Especie. */
    @Autowired
    private RescateFondoEspecie rescateFondoEspecie;
    
    @Autowired
    private InversionWSHelper inversionWSHelper;

    @Autowired
    private FondosTenenciasPrototype fondosTenenciasPrototype;

    @Autowired
    private FundsApi fundsApi;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO#
     * getTipo()
     */
    @Override
    protected String getTipo() {
        return EnumFondosDisponiblesTipoOperacion.RE.getCodigo();
    }

    /**
     * Genera el objeto FondoInEntity a partir del DTO pasado como parametro.
     *
     * @param dtoRequest
     *            the dto request
     * @param cliente
     *            the cliente
     * @return the fondo in entity
     */
    private FondoInEntity generateEntity(FinalizarRescateInDTO dtoRequest, Cliente cliente) {
        FondoInEntity fondoInEntity = new FondoInEntity();
        fondoInEntity.setCliente(cliente);
        fondoInEntity.setCodigoFondo(dtoRequest.getCodigoFondo());
        fondoInEntity.setCodigoCliente(dtoRequest.getCuentaTitulo().replaceAll("/", ""));
        fondoInEntity.setImporteBruto(ISBANStringUtils.formatearImporteRescate(dtoRequest.getImporte()));

        TipoCuenta tipoCuenta = TipoCuenta.fromAbreviatura(dtoRequest.getTipoCtaCred());
        fondoInEntity.setTipoCuenta(tipoCuenta.getCodigo().toString());
        fondoInEntity.setTipoCuenta(StringUtils.leftPad(fondoInEntity.getTipoCuenta(), 2, ISBANStringUtils.ZERO_STR));

        fondoInEntity.setSucCuenta(dtoRequest.getSucursalCtaCred());
        String nroCuenta = dtoRequest.getNumeroCtaCred().replaceAll("/", "");
        fondoInEntity.setNroCuenta(StringUtils.leftPad(nroCuenta, LONG_CTA_CRED, CERO_STRING));

        if (("02").equals(fondoInEntity.getTipoCuenta())) {
            if ("$".equals(dtoRequest.getMoneda())) {
                fondoInEntity.setTipoCuenta("09");
            } else {
                fondoInEntity.setTipoCuenta("10");
            }
        }

        if (dtoRequest.getMoneda() != null) {
            if ("$".equals(dtoRequest.getMoneda())) {
                fondoInEntity.setMoneda(MONEDA_PESOS);
            } else {
                fondoInEntity.setMoneda(MONEDA_DOLARES);
            }
        }
        fondoInEntity.setImporteRescateComision(dtoRequest.getImporteRescateComision());
        fondoInEntity.setImporteRescateNeto(dtoRequest.getImporteRescateNeto());
        return fondoInEntity;
    }

    /**
     * Genera el objeto FondoCitiInEntity a partir del DTO pasado como parametro.
     *
     * @param dtoRequest
     *            the dto request
     * @param cliente
     *            the cliente
     * @return the fondo in citi entity
     */
    private FondoCitiInEntity generateCitiEntity(FinalizarRescateInDTO dtoRequest, Cliente cliente) {
        FondoCitiInEntity fondoInEntity = new FondoCitiInEntity();
        fondoInEntity.setCliente(cliente);

        fondoInEntity.setCodigoFondo(StringUtils.leftPad(dtoRequest.getCodigoFondo(), 5, CERO_STRING));
        String nroCuentaTitulo = dtoRequest.getCuentaTitulo().replaceAll("/", "");
        fondoInEntity.setCuentaTitulo(StringUtils.leftPad(nroCuentaTitulo, 11, CERO_STRING));
        fondoInEntity.setImporteBruto(ISBANStringUtils.formatearImporteRescate(dtoRequest.getImporte()));

        TipoCuenta tipoCuenta = TipoCuenta.fromAbreviatura(dtoRequest.getTipoCtaCred());
        fondoInEntity.setTipoCuenta(tipoCuenta.getCodigo().toString());

        fondoInEntity.setSucCuenta(dtoRequest.getSucursalCtaCred());
        String nroCuenta = dtoRequest.getNumeroCtaCred().replaceAll("/", "");
        fondoInEntity.setNroCuenta(StringUtils.leftPad(nroCuenta, LONG_CTA_CRED_CITI, CERO_STRING));

        if ((CUENTA_UNICA_TIPO).equals(fondoInEntity.getTipoCuenta())) {
            if ("$".equals(dtoRequest.getMoneda())) {
                fondoInEntity.setTipoCuenta("09");
            } else {
                fondoInEntity.setTipoCuenta("10");
            }
        }

        if (dtoRequest.getMoneda() != null) {
            if ("$".equals(dtoRequest.getMoneda())) {
                fondoInEntity.setMoneda(MONEDA_PESOS);
            } else {
                fondoInEntity.setMoneda(MONEDA_DOLARES);
            }

            // PARA FONDO CITI COMPASS SE FUERZA LA MONEDA A PESOS
            if (dtoRequest.getCodigoFondo().equals(CODIGO_FONDO_CITI_COMPASS)) {
                fondoInEntity.setMoneda(MONEDA_PESOS);
            }
        }

        return fondoInEntity;
    }

    /**
     * metodo auxiliar para cargar los datos en DTO FinalizarRescateDTO.
     *
     * @param requestDTO
     *            the request DTO
     * @param respuestaDAO
     *            the respuesta DAO
     * @return the finalizar rescate DTO
     */

    public FinalizarRescateDTO cargarDatos(FinalizarRescateInDTO requestDTO, ComprobanteRescateEntity respuestaDAO) {
        FinalizarRescateDTO dtoResponse = new FinalizarRescateDTO();
        String moneda = requestDTO.getMoneda();
        String saldo = ISBANStringUtils.formatearConComaYDosDecimales(requestDTO.getImporte());

        dtoResponse.setTotalCuotasPartes(
                ISBANStringUtils.formatearSaldoCuatroDecimales(respuestaDAO.getTotalCuotasPartes()));
        dtoResponse.setImporteRescateNeto(" " + moneda + " " + saldo);
        String mensaje = mensajeBo.obtenerMensajePorCodigo(SUSCRIPCION_RESCATE_CORRECTA).getMensaje();
        String mensajeSuscripcion = MessageFormat.format(mensaje, requestDTO.getNombreFondo(),
                dtoResponse.getImporteRescateNeto(), dtoResponse.getTotalCuotasPartes());
        dtoResponse.setMensajeSuscripcion(mensajeSuscripcion);
        dtoResponse.setMoneda(requestDTO.getMoneda());
        dtoResponse.setEstadoActual(respuestaDAO.getEstadoActual());
        dtoResponse.setNombreFondo(respuestaDAO.getNombreFondo());
        dtoResponse.setNroRescate(respuestaDAO.getNroRescate());
        dtoResponse.setImporteRescateComision(respuestaDAO.getImporteRescateComision());
        dtoResponse.setImporteRescateBruto(respuestaDAO.getImporteRescateBruto());
        dtoResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
        return dtoResponse;

    }

    /**
     * Cargar datos citi.
     *
     * @param requestDTO
     *            the request DTO
     * @param respuestaDAO
     *            the respuesta DAO
     * @return the finalizar rescate DTO
     */
    public FinalizarRescateDTO cargarDatosCiti(FinalizarRescateInDTO requestDTO,
            ComprobanteRescateCitiEntity respuestaDAO) {
        FinalizarRescateDTO dtoResponse = new FinalizarRescateDTO();
        String moneda = requestDTO.getMoneda();
        String saldo = ISBANStringUtils.formatearConComaYDosDecimales(requestDTO.getImporte());

        dtoResponse.setTotalCuotasPartes(
                ISBANStringUtils.formatearSaldoCuatroDecimales(respuestaDAO.getTotalCuotasPartes()));
        dtoResponse.setImporteRescateNeto(" " + moneda + " " + saldo);
        String mensaje = mensajeBo.obtenerMensajePorCodigo(SUSCRIPCION_RESCATE_CORRECTA).getMensaje();
        String mensajeSuscripcion = MessageFormat.format(mensaje, requestDTO.getNombreFondo(),
                dtoResponse.getImporteRescateNeto(), dtoResponse.getTotalCuotasPartes());
        dtoResponse.setMensajeSuscripcion(mensajeSuscripcion);
        dtoResponse.setMoneda(requestDTO.getMoneda());
        dtoResponse.setEstadoActual("");
        dtoResponse.setNombreFondo(requestDTO.getNombreFondo());
        dtoResponse.setNroRescate(respuestaDAO.getNroSolicitudRescate());
        dtoResponse.setImporteRescateBruto(respuestaDAO.getImporteRescateBruto());
        dtoResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
        return dtoResponse;

    }

    /**
     * Manejar caso feliz rescate.
     *
     * @param requestDTO
     *            the request DTO
     * @param respuestaDAO
     *            the respuesta DAO
     * @return the respuesta
     */
    Respuesta<FinalizarRescateDTO> manejarCasoFelizRescate(FinalizarRescateInDTO requestDTO,
            ComprobanteRescateEntity respuestaDAO) {
        FinalizarRescateDTO dtoResponse = cargarDatos(requestDTO, respuestaDAO);
        return respuestaFactory.crearRespuestaOk(FinalizarRescateDTO.class, dtoResponse);
    }

    /**
     * Manejar caso feliz rescate citi.
     *
     * @param requestDTO
     *            the request DTO
     * @param respuestaDAO
     *            the respuesta DAO
     * @return the respuesta
     */
    Respuesta<FinalizarRescateDTO> manejarCasoFelizRescateCiti(FinalizarRescateInDTO requestDTO,
            ComprobanteRescateCitiEntity respuestaDAO) {
        FinalizarRescateDTO dtoResponse = cargarDatosCiti(requestDTO, respuestaDAO);
        return respuestaFactory.crearRespuestaOk(FinalizarRescateDTO.class, dtoResponse);
    }

    /**
     * Manejar reintento rescate.
     *
     * @param daoIn
     *            the dao in
     * @param permiteReintentar
     *            the permite reintentar
     * @param e
     *            the e
     * @return the respuesta
     */
    Respuesta<FinalizarRescateDTO> manejarReintentoRescate(FinalizarRescateInDTO daoIn, boolean permiteReintentar,
            DAOException e) {
        Respuesta<FinalizarRescateDTO> respuesta;
        LOGGER.error("Error en fondoDAO metodo finalizarSuscribirFondosBPriv. ", e);
        String mensaje = mensajeBo.obtenerMensajePorCodigo(FINALIZAR_RESCATE_FALLO_GENERICO).getMensaje();
        String mensajeError = MessageFormat.format(mensaje, daoIn.getNombreFondo(),
                daoIn.getMoneda() + " "
                        + ISBANStringUtils.formatearSaldosConCerosYSignos(daoIn.getImporteRescateNeto()),
                daoIn.getCuotaPartes());
        if (permiteReintentar) {
            respuesta = respuestaFactory.crearRespuestaError(FinalizarRescateDTO.class, mensajeError,
                    TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_CON_REINTENTO.toString());

        } else {
            respuesta = respuestaFactory.crearRespuestaError(FinalizarRescateDTO.class, mensajeError,
                    TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString());
        }
        return respuesta;
    }

    /**
     * Manejar reintento rescate B priv.
     *
     * @param daoIn
     *            the dao in
     * @param permiteReintentar
     *            the permite reintentar
     * @param e
     *            the e
     * @return the respuesta
     */
    Respuesta<FinalizarRescateBPrivDTO> manejarReintentoRescateBPriv(FinalizarRescateInDTO daoIn,
            boolean permiteReintentar, Exception e) {
        Respuesta<FinalizarRescateBPrivDTO> respuesta;
        LOGGER.error("Error en fondoDAO metodo finalizarSuscribirFondosBPriv. ", e);
        String mensaje = mensajeBo.obtenerMensajePorCodigo(FINALIZAR_RESCATE_FALLO_GENERICO_BPRIV).getMensaje();
        String mensajeError = MessageFormat.format(mensaje, daoIn.getNombreFondo(),
                daoIn.getMoneda() + " " + ISBANStringUtils.formatearConComaYDosDecimales(daoIn.getImporte()));
        if (permiteReintentar) {
            respuesta = respuestaFactory.crearRespuestaError(FinalizarRescateBPrivDTO.class, mensajeError,
                    TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_CON_REINTENTO.toString());

        } else {
            respuesta = respuestaFactory.crearRespuestaError(FinalizarRescateBPrivDTO.class, mensajeError,
                    TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString());
        }
        return respuesta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<FinalizarRescateDTO> finalizarRescate(FinalizarRescateInDTO dtoRequest, Cliente cliente) {
        boolean permiteReintentar;
        String segmento = Segmento.BANCA_PERSONAL.getCodigo();

        if (sessionParametros.getContador() != null) {
            permiteReintentar = sessionParametros.getContador().permiteReintentar();
        } else {
            LOGGER.debug("Error, Contador no inicializado!!");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }

        ComprobanteRescateEntity outDAO = null;

        try {
            fondosTenenciasPrototype.cleanCacheObtenerTenenciaValuadaDetalleFondoOnline(cliente, segmento);
            FondoInEntity daoEntity = generateEntity(dtoRequest, cliente);
            outDAO = rescateDAO.comprobanteRescate(daoEntity);
        } catch (FueraDeHorarioException e) {
        	LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new FinalizarRescateDTO(), "",
					TipoError.FUERADEHORARIO, CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
        } catch (ImporteMenorAlMinimoException e) {
            LOGGER.error("Error en BO iporte menor al minimo permitido. ", e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_LIMITE_MINIMO, "");
        } catch (SaldoInsuficienteException ex) {
            LOGGER.error(MSG_ERROR_SALDO_INSUFICIENTE, ex);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        } catch (TimeOutException exc) {
            LOGGER.error("Error en BO timeOut. ", exc);
            String mensaje = mensajeBo.obtenerMensajePorCodigo(FINALIZAR_RESCATE_FALLO_GENERICO).getMensaje();
            String mensajeError = MessageFormat.format(mensaje, dtoRequest.getNombreFondo(),
                    dtoRequest.getMoneda() + " "
                            + ISBANStringUtils.formatearConComaYDosDecimales(dtoRequest.getImporte()),
                    dtoRequest.getCuotaPartes());

            return respuestaFactory.crearRespuestaError(FinalizarRescateDTO.class, mensajeError,
                    TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString());
        } catch (CuentaSinOperarException e) {
            LOGGER.error("Error en BO 180 dias cuentasinOperar. ", e);
            return manejarReintentoRescate(dtoRequest, permiteReintentar, e);
        }

        catch (DAOException e) {
            LOGGER.error("Error al consultar DAO Finalizar Rescate", e);
            return manejarReintentoRescate(dtoRequest, permiteReintentar, e);
        } catch (Exception e) {
            LOGGER.error("Error al Finalizar Rescate", e);
        }

        cleanCacheHoldingBff(dtoRequest.getCuentaTitulo(), cliente, segmento);

        return manejarCasoFelizRescate(dtoRequest, outDAO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.RescateBO#
     * finalizarRescateFDC(ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.
     * FinalizarRescateInDTO,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<FinalizarRescateDTO> finalizarRescateFDC(FinalizarRescateInDTO dtoRequest, Cliente cliente) {
        boolean permiteReintentar;
        if (sessionParametros.getContador() != null) {
            permiteReintentar = sessionParametros.getContador().permiteReintentar();
        } else {
            LOGGER.debug("Error, Contador no inicializado!!");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }

        ComprobanteRescateCitiEntity outCitiDAO = null;

        try {
            FondoCitiInEntity daoEntity = generateCitiEntity(dtoRequest, cliente);
            outCitiDAO = rescateDAO.comprobanteRescateCiti(daoEntity);
        } catch (FueraDeHorarioException e) {
            LOGGER.error("Error en BO fuera de horario. ", e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.FUERADEHORARIO,
                    CodigoMensajeConstantes.FONDO_FUERA_HORARIO);
        } catch (TimeOutException exc) {
            LOGGER.error("Error en BO timeOut. ", exc);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString());
        } catch (ServicioCerradoExCitiException ex) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_CERRADO_EXCITI,
            		"");
        }catch (ErrorRescateCitiFondoCompassException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_RESCATE_FONDO_CITI_COMPASS, "");
		}catch (DAOException e) {
            LOGGER.error("Error al consultar DAO Finalizar Rescate", e);
            if (permiteReintentar) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_CON_REINTENTO.toString());
            } else {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString());
            }
        }

        return manejarCasoFelizRescateCiti(dtoRequest, outCitiDAO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.RescateBO#
     * simularRescateFondo(ar.com.santanderrio.obp.servicios.inversiones.fondos.
     * dto.SimularRescateInDTO,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<SimularRescateOutDTO> simularRescateFondo(SimularRescateInDTO dtoRequest, Cliente cliente) {
        Respuesta<SimularRescateOutDTO> respuesta = new Respuesta<SimularRescateOutDTO>();
        try {
            RescateFondoOutEntity simulacionOut = rescateDAO.simulacionRescate(cliente, generateEntity(dtoRequest));
            respuesta = respuestaFactory.crearRespuestaOk(SimularRescateOutDTO.class, generateDTO(simulacionOut));
        } catch (FueraDeHorarioException fhe) {
        	LOGGER.error("Error Fuera de horario. ", fhe);
			return respuestaFactory.crearRespuestaWarning(new SimularRescateOutDTO(), "",
					TipoError.FUERADEHORARIO, CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
        } catch (SaldoInsuficienteException e) {
            LOGGER.error("Error supera saldo disponible . ", e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SUPERA_SALDO_DISPONIBLE,
                    CodigoMensajeConstantes.ERRO_SUPERA_SALDO_DISPONIBLE);
        } catch (ServicioDeshabilitadoException e) {
            LOGGER.error("Error Servicio Deshabilitado. ", e);
            return respuestaFactory.crearRespuestaError("", TipoError.SERVICIO_FONDOS_DESHABILITADO,
                    CodigoMensajeConstantes.ERROR_SERVICIO_RESCATE_FONDOS_DESHABILITADO);

        } catch (DAOException e) {
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
            LOGGER.error("Error al consultar DAO con", dtoRequest, e);
        } catch (Exception e) {
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
            LOGGER.error("Error al consultar DAO con", dtoRequest, e);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.RescateBO#
     * simularRescateFondoBPriv(ar.com.santanderrio.obp.servicios.inversiones.
     * fondos.dto.SimulacionInDTO,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<SimularRescateOutDTO> simularRescateFondoBPriv(SimulacionInDTO requestDTO, Cliente cliente) {
        SimularRescateOutDTO respuesta = new SimularRescateOutDTO();
        try {
            SimulacionFondoBancaPrivadaOutEntity out = fondoBPrivDAO
                    .simularRescateBPriv(crearDAOin(requestDTO, cliente, getRACFCredential()));
            respuesta = createDTOout(out);
            Respuesta<String> respuestaLegales = legalBO.buscarLegal(LEGAL_SIMULACION_RESCATE);
            if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.COMUNICATE_ASESOR_BANCA_PRIVADA);
            }
            respuesta.setLegales(respuestaLegales.getRespuesta());
        } catch (FueraDeHorarioException e) {
        	LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new SimularRescateOutDTO(), "",
					TipoError.FUERADEHORARIO, CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
        } catch (SaldoInsuficienteException e) {
            LOGGER.error("Error supera saldo disponible . ", e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SUPERA_SALDO_DISPONIBLE,
                    CodigoMensajeConstantes.ERRO_SUPERA_SALDO_DISPONIBLE);
        } catch (BusinessException e) {
            LOGGER.error("Error llamando a simulacion de rescate", e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        } catch (DAOException e) {
            LOGGER.error("Error llamando a simulacion de rescate", e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
        return respuestaFactory.crearRespuestaOk(SimularRescateOutDTO.class, respuesta);
    }

    /**
     * Creates the DT oout.
     *
     * @param out
     *            the out
     * @return the simular rescate out DTO
     * @throws BusinessException
     *             the business exception
     */
    private SimularRescateOutDTO createDTOout(SimulacionFondoBancaPrivadaOutEntity out) throws BusinessException {
        SimularRescateOutDTO respuesta = new SimularRescateOutDTO();
        if (out != null) {
         //   respuesta.setDisclaimer(out.getDisclaimer());
            respuesta.setCuotaPartes(out.getCuotasPartes());
            respuesta.setDentroDelPerfil(out.getDentroDelPerfil());
            return respuesta;
        }
        throw new BusinessException();
    }

    /**
     * genera el Entity para llamar al DAO a partir del DTO.
     *
     * @param dtoRequest
     *            the dto request
     * @return the rescate fondo in entity
     * @throws SimulacionDAOException
     *             the simulacion DAO exception
     */
    private RescateFondoInEntity generateEntity(SimularRescateInDTO dtoRequest) throws SimulacionDAOException {
        RescateFondoInEntity entity = new RescateFondoInEntity();

        try {
            BeanUtils.copyProperties(entity, dtoRequest);
            entity.setTipoCuenta(StringUtils.leftPad(dtoRequest.getTipoCuenta(), 2, ISBANStringUtils.ZERO_STR));
            entity.setImporteNeto(StringUtils.leftPad(dtoRequest.getImporteNeto(), CATORCE, ISBANStringUtils.ZERO_STR));
            entity.setNumeroCuenta(StringUtils.leftPad(dtoRequest.getNumeroCuenta(), LONG_CTA_CRED, CERO_STRING));

            if (("02").equals(entity.getTipoCuenta())) {
                if ("$".equals(dtoRequest.getMoneda())) {
                    entity.setTipoCuenta("09");
                } else {
                    entity.setTipoCuenta("10");
                }
            }
            if ("$".equals(dtoRequest.getMoneda())) {
                entity.setMoneda("0");
            } else {
                entity.setMoneda("2");
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("Error generando entity para llamar a DAO", e);
            throw new SimulacionDAOException(e);
        } catch (InvocationTargetException e) {
            LOGGER.error("Error generando entity para llamar a DAO", e);
            throw new SimulacionDAOException(e);
        }
        return entity;
    }

    /**
     * Crea el DTO de respuesta a partir del entity de respuesta del DAO.
     *
     * @param simulacionOut
     *            the simulacion out
     * @return the simular rescate out DTO
     * @throws SimulacionDAOException
     *             the simulacion DAO exception
     */
    private SimularRescateOutDTO generateDTO(RescateFondoOutEntity simulacionOut) throws SimulacionDAOException {
        SimularRescateOutDTO dto = new SimularRescateOutDTO();
        String cuotaPartes = simulacionOut.getTotalCuotasPartes();
        dto.setCuotaPartes(ISBANStringUtils.formatearSaldoCuatroDecimales(cuotaPartes));
        dto.setImporteRescateComision(simulacionOut.getImporteRescateComision());
        dto.setImporteRescateNeto(simulacionOut.getImporteRescateNeto());
        Respuesta<String> rtaLegal = legalBO.buscarLegal(LEGAL_SIMULACION_RESCATE);
        if (!rtaLegal.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
            LOGGER.debug("Error recuperando legal : " + LEGAL_SIMULACION_RESCATE);
            throw new SimulacionDAOException("Error recuperando legales");
        }
        dto.setLegales(rtaLegal.getRespuesta());
        return dto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.RescateBO#
     * obtenerFondosSuscriptos(ar.com.santanderrio.obp.servicios.inversiones.
     * fondos.dto.CuentasConsultaFondoDTO,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptos(CuentasConsultaFondoDTO requestDTO,
            Cliente cliente) {

		Date fecha = new Date();
        LOGGER.info("CACHE_DIAS_NO_HABILES: Consulta al metodo desde Rescate");
		boolean esFeriado = inversionWSHelper.esFeriado(fecha);

    	if (!validarHorarioFondos() || esFinDeSemana() || esFeriado) {
			LOGGER.error("Los fondos no se encuentran dentro del horario de inicio ({} hs.) y fin ({} hs.) de operatoria.",
					horarioDesdeOperarFondos, horarioHastaOperarFondos);
			return respuestaFactory.crearRespuestaWarning(new CuentasConsultaFondoDTO(), "", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.CODIGO_RESCATE_FUERA_HORARIO_MAPS);
		}
    	requestDTO=inyectarCuentasTitRep(requestDTO,cliente);
        // SETEO TIPO BANCA
        CuentasConsultaFondoDTO returnDTO = new CuentasConsultaFondoDTO();
        returnDTO.setTipoBanca(requestDTO.getTipoBanca());
        Mensaje mensajeInformacion = mensajeBo
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_INFORMACION_GO_TO);
        returnDTO.setMensajeInformacion(mensajeInformacion.getMensaje());

        ConsultaTenenciaFCIInEntity requestEntity = genererRequestEntity(requestDTO, false);

        try {
            fondosTenenciasPrototype.setEstadisticaConsultaTenenciaFCI(cliente);
            ConsultaTenenciaFCIOutEntity retornoFCIDAO = fondoDAO.consultaTenenciaFCI(cliente, requestEntity);

            List<ConsultaTenenciaFCIEntity> listaTenencia = new ArrayList<ConsultaTenenciaFCIEntity>();
            listaTenencia.addAll(retornoFCIDAO.getListaTenencia());

            List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
            cuentasTitulo.addAll(entityToDTO(listaTenencia, requestDTO));

            returnDTO.setCuentasTitulo(cuentasTitulo);
        } catch (DAOException e) {
            LOGGER.error("Error DAOException. ", e);
            fondosTenenciasPrototype.cleanCacheConsultaTenenciaFCI(cliente);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }

        if (!returnDTO.getCuentasTitulo().isEmpty()) {
            return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoDTO.class, returnDTO);
        } else {
            return respuestaFactory.crearRespuestaWarning("", TipoError.SIN_FONDOS_A_RESCATAR,
                    CodigoMensajeConstantes.SIN_FONDOS_A_RESCATAR);
        }
    }

    private CuentasConsultaFondoDTO inyectarCuentasTitRep(CuentasConsultaFondoDTO requestDTO, Cliente cliente) {
		// TODO Auto-generated method stub
    	List<Cuenta> ctasTitRep=new ArrayList<Cuenta>();	
		if(requestDTO.getTipoBanca().equals("BR")) {
			ctasTitRep=cliente.getCuentasTitRtlRepatriacion();
		}else {
			ctasTitRep=cliente.getCuentasTitBPRepatriacion();
		}
		
		List<CuentaTituloDTO> ListaDTO=requestDTO.getCuentasTitulo();
		
		for(Cuenta ctaTitRep : ctasTitRep) {
			CuentaTituloDTO ctaDto=new CuentaTituloDTO();
			ctaDto.setIntervinientes(ctaTitRep.getIntervinientes());
			ctaDto.setNroCuenta(ctaTitRep.getNroCuentaProducto());
			String nroCuenta=Integer.parseInt(ctaTitRep.getNroCuentaProducto())+"";
			ctaDto.setNroCuentaFormateado(nroCuenta.substring(0, nroCuenta.length() - 1) + "/"
		            + nroCuenta.substring(nroCuenta.length() - 1, nroCuenta.length()));
			ctaDto.setRepatriacion(true);
			String nroCuentaOp=ctaTitRep.getCuentaOPRepatriacion()+"";
			String sucursalOp=ctaTitRep.getSucursalCtaOpRep();
			String cuentaOP=sucursalOp+"-"+nroCuentaOp.substring(0,nroCuentaOp.length()-1)+"/"+nroCuentaOp.substring(nroCuentaOp.length()-1,nroCuentaOp.length());
			ctaDto.setCuentaOperativa(cuentaOP);
			ctaDto.setSucursal("000");
			ListaDTO.add(ctaDto);
		}
		
		requestDTO.setCuentasTitulo(ListaDTO);
		
		return requestDTO;
	}

	/*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.RescateBO#
     * obtenerFondosSuscriptosBPriv(ar.com.santanderrio.obp.servicios.
     * inversiones.fondos.dto.CuentasConsultaFondoDTO,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosBPriv(CuentasConsultaFondoDTO requestDTO,
            Cliente cliente) {

		Date fecha = new Date();
        LOGGER.info("CACHE_DIAS_NO_HABILES: Consulta al metodo desde ObtenerFondosSuscriptosBPriv");
		boolean esFeriado = inversionWSHelper.esFeriado(fecha);
    	
    	if (!validarHorarioFondos() || esFinDeSemana() || esFeriado) {
			LOGGER.error("Los fondos no se encuentran dentro del horario de inicio ({} hs.) y fin ({} hs.) de operatoria.",
					horarioDesdeOperarFondos, horarioHastaOperarFondos);
			return respuestaFactory.crearRespuestaWarning(new CuentasConsultaFondoDTO(), "", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.CODIGO_RESCATE_FUERA_HORARIO_MAPS); //rescate_menu
    	}
    	
        // SETEO TIPO BANCA
        CuentasConsultaFondoDTO returnDTO = new CuentasConsultaFondoDTO();
        returnDTO.setTipoBanca(requestDTO.getTipoBanca());
        Mensaje mensajeInformacion = mensajeBo
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_INFORMACION_GO_TO);
        returnDTO.setMensajeInformacion(mensajeInformacion.getMensaje());

        // OBTENGO FONDOS SUSCRIPTOS
        try {
            returnDTO.setCuentasTitulo(obtenerRelacionOperativaTitulo(cliente));
            Predicate filtroRescate = new Predicate() {
                @Override
                public boolean evaluate(Object object) {
                    ConsultaFondoOutEntity fondo = (ConsultaFondoOutEntity) object;
                    return "1".equals(fondo.getHabilitarRescate());
                }
            };
            fondoBO.cargarTenencia(returnDTO.getCuentasTitulo(), filtroRescate);
            returnDTO.setCuentasTitulo(obtenerCuentasConTenencia(returnDTO.getCuentasTitulo()));
        } catch (BusinessException e) {
            LOGGER.error("Error BusinessException. ", e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }

        if (!returnDTO.getCuentasTitulo().isEmpty()) {
            return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoDTO.class, returnDTO);
        } else {
            return respuestaFactory.crearRespuestaWarning("", TipoError.SIN_FONDOS_A_RESCATAR,
                    CodigoMensajeConstantes.SIN_FONDOS_A_RESCATAR);
        }
    }

    /**
     * Obtener cuentas con tenencia.
     *
     * @param cuentasTitulo
     *            the cuentas titulo
     * @return the list
     */
    private List<CuentaTituloDTO> obtenerCuentasConTenencia(List<CuentaTituloDTO> cuentasTitulo) {
        List<CuentaTituloDTO> cuentasTituloReturn = new ArrayList<CuentaTituloDTO>();
        for (CuentaTituloDTO cuentaTituloDTO : cuentasTitulo) {
            if (!cuentaTituloDTO.getFondosSuscriptos().isEmpty()) {
                cuentasTituloReturn.add(cuentaTituloDTO);
            }
        }
        return cuentasTituloReturn;
    }

    /**
     * Metodo que devuelve un DTO con las cuentas titulos, extrayendolas del
     * requestDTO y las completa con los fondos asociados a cada una de ellas que
     * vinieron del dao de CNSTENVAL_. Ademas filtra a los fondos que estan
     * habilitadaos para rescatar
     * 
     * @param listaTenencia
     *            the respuesta DAO
     * @param requestDTO
     *            the request DTO
     * @return the cuentas consulta fondo DTO
     * @throws DAOException
     *             the DAO exception
     */
    private List<CuentaTituloDTO> entityToDTO(List<ConsultaTenenciaFCIEntity> listaTenencia,
            CuentasConsultaFondoDTO requestDTO) throws DAOException {

        CuentaTituloDTO cuentaTitDTO = null;
        // RECORRO TODOS LOS FONDOS DEVUELTOS
        List<CuentaTituloDTO> cuentasTitulosConFondos = new ArrayList<CuentaTituloDTO>();
        for (ConsultaTenenciaFCIEntity tenencia : listaTenencia) {

            // ASIGNO EL FONDO ACTUAL A LA "cuentaTitDTO" CORRESPONDIENTE
            cuentaTitDTO = requestDTO.getCuentaByNumero(tenencia.getNroCuenta());

            if (cuentaTitDTO != null) {
                if (cuentaTitDTO.getFondosSuscriptos() == null) {
                    cuentaTitDTO.setFondosSuscriptos(new ArrayList<FondoResumidoDTO>());
                }
                FondoResumidoDTO fondoResumido = obtenerFondoPorCodigoHabiliResc(StringUtils.right(
                        tenencia.getEspecieCodigo(),
                        TipoPapel.FCI.equals(tenencia.getTipoPapelEnum()) ? LARGO_COD_FONDO : LARGO_COD_FONDO_FDC));
                if (fondoResumido != null) {
                    if (fondoResumido.getHabilitarRescate().equals(HABILITADO_PARA_RESCATAR)) {
                        fondoResumido.setMoneda(TipoMonedaInversionEnum
                                .fromCodigoNumericoString(tenencia.getMonedaTipo()).getSimbolo());
                        fondoResumido.setSaldo(
                                ISBANStringUtils.formatearSaldosConCerosYSignos(tenencia.getTeneciaValuada()));

                        fondoResumido.setFondoNuevo(
                                rescateFondoEspecie.getFondosMap().containsKey(fondoResumido.getCodigoFondo()));
                        if (fondoResumido.isFondoNuevo()) {
                            FondoEspecie fondoEspecie = rescateFondoEspecie.getFondosMap()
                                    .get(fondoResumido.getCodigoFondo());
                            LOGGER.info("Se opera con el fondo obtenido del yaml {}", fondoEspecie);
                            fondoResumido.setSoloEspecie(fondoEspecie.getOperacion());
                        } else {
                            LOGGER.debug("El fondo consultado en el yaml {} no es nuevo",
                                    fondoResumido.getCodigoFondo());
                        }
                        cuentaTitDTO.getFondosSuscriptos().add(fondoResumido);
                        Collections.sort(cuentaTitDTO.getFondosSuscriptos());
                        if (!cuentasTitulosConFondos.contains(cuentaTitDTO)) {
                            cuentasTitulosConFondos.add(cuentaTitDTO);
                        }

                    }
                }
            }
        }
        requestDTO.setCuentasTitulo(cuentasTitulosConFondos);
        return requestDTO.getCuentasTitulo();
    }

    /**
     * {@inheritDoc}
     */
    public Respuesta<FinalizarRescateBPrivDTO> finalizarRescateBPriv(FinalizarRescateBPrivInDTO dtoRequest,
            Cliente cliente) {
        boolean permiteReintentar;
        String segmento = Segmento.BANCA_PRIVADA.getCodigo();

        if (sessionParametros.getContador() != null) {
            permiteReintentar = sessionParametros.getContador().permiteReintentar();
        } else {
            LOGGER.debug("Error, Contador no inicializado!!");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
        EjecucionFondoBancaPrivadaOutEntity ejecucionFondoBancaPrivadaOutEntity = null;
        try {
            fondosTenenciasPrototype.cleanCacheObtenerTenenciaValuadaDetalleFondoOnline(cliente, segmento);
            ejecucionFondoBancaPrivadaOutEntity = fondoBPrivDAO.rescatar(dtoToEntity(dtoRequest, cliente));
        } catch (FueraDeHorarioException e) {
        	LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new FinalizarRescateBPrivDTO(), "",
					TipoError.FUERADEHORARIO, CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
        } catch (ImporteMenorAlMinimoException e) {
            return manejarReintentoRescateBPriv(dtoRequest, permiteReintentar, e);
        } catch (SaldoInsuficienteException e) {
        	LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new FinalizarRescateBPrivDTO(), "",
					TipoError.ERROR_SUPERA_SALDO_DISPONIBLE, CodigoMensajeConstantes.ERRO_SUPERA_SALDO_DISPONIBLE);
//            return manejarReintentoRescateBPriv(dtoRequest, permiteReintentar, e);
        } catch (TimeOutException exc) {
            LOGGER.error("Error en BO timeOut. ", exc);
            String mensaje = mensajeBo.obtenerMensajePorCodigo(FINALIZAR_RESCATE_FALLO_GENERICO_BPRIV).getMensaje();
            String mensajeError = MessageFormat.format(mensaje, dtoRequest.getNombreFondo(), dtoRequest.getMoneda()
                    + " " + ISBANStringUtils.formatearConComaYDosDecimales(dtoRequest.getImporte()));
            return respuestaFactory.crearRespuestaError(FinalizarRescateBPrivDTO.class, mensajeError,
                    TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString());
        } catch (CuentaSinOperarException e) {
            return manejarReintentoRescateBPriv(dtoRequest, permiteReintentar, e);
        } catch (DAOException e) {
            LOGGER.error("Error al consultar DAO Finalizar Rescate", e);
            return manejarReintentoRescateBPriv(dtoRequest, permiteReintentar, e);
        } catch (BusinessException e) {
            return manejarReintentoRescateBPriv(dtoRequest, permiteReintentar, e);
        }

        cleanCacheHoldingBff(dtoRequest.getCuentaTitulo(), cliente, segmento);

        return armarRespuestaOKFinalizarRescateBpriv(dtoRequest, ejecucionFondoBancaPrivadaOutEntity);
    }

    /**
     * Armar respuesta OK finalizar rescate bpriv.
     *
     * @param finalizarRescateBPrivInDTO
     *            the finalizar rescate B priv in DTO
     * @param ejecucionFondoBancaPrivadaOutEntity
     *            the ejecucion fondo out entity
     * @return the respuesta
     */
    private Respuesta<FinalizarRescateBPrivDTO> armarRespuestaOKFinalizarRescateBpriv(
            FinalizarRescateBPrivInDTO finalizarRescateBPrivInDTO, EjecucionFondoBancaPrivadaOutEntity ejecucionFondoBancaPrivadaOutEntity) {

        FinalizarRescateBPrivDTO finalizarRescateBPrivDTO = crearDTOFinalizarRescateBPriv(finalizarRescateBPrivInDTO,
                ejecucionFondoBancaPrivadaOutEntity);
        return respuestaFactory.crearRespuestaOk(FinalizarRescateBPrivDTO.class, finalizarRescateBPrivDTO);
    }

    /**
     * Crear DTO finalizar rescate B priv.
     *
     * @param finalizarRescateBPrivInDTO
     *            the finalizar rescate B priv in DTO
     * @param ejecucionFondoBancaPrivadaOutEntity
     *            the ejecucion fondo out entity
     * @return the finalizar rescate B priv DTO
     */
    private FinalizarRescateBPrivDTO crearDTOFinalizarRescateBPriv(
            FinalizarRescateBPrivInDTO finalizarRescateBPrivInDTO, EjecucionFondoBancaPrivadaOutEntity ejecucionFondoBancaPrivadaOutEntity) {

        FinalizarRescateBPrivDTO finalizarRescateBPrivDTO = new FinalizarRescateBPrivDTO();
        String mensaje = mensajeBo.obtenerMensajePorCodigo(SUSCRIPCION_RESCATE_CORRECTA).getMensaje();
        if (ejecucionFondoBancaPrivadaOutEntity.getCuotaPartes() != null) {
            String cuotaPartes = ejecucionFondoBancaPrivadaOutEntity.getCuotaPartes();
            cuotaPartes = cuotaPartes.replaceAll(",", "");
            cuotaPartes = cuotaPartes.replaceAll("\\.", "");
            finalizarRescateBPrivDTO.setCuotaPartes(ISBANStringUtils.formatearSaldoCuatroDecimales(cuotaPartes));
        }
        finalizarRescateBPrivDTO.setDisclaimer(ejecucionFondoBancaPrivadaOutEntity.getDisclaimer());
        finalizarRescateBPrivDTO
                .setImporte(ISBANStringUtils.formatearConComaYDosDecimales(ejecucionFondoBancaPrivadaOutEntity.getCapital()));
        finalizarRescateBPrivDTO.setNroCertificado(ejecucionFondoBancaPrivadaOutEntity.getNroCertificado());
        finalizarRescateBPrivDTO.setNroOrden(ejecucionFondoBancaPrivadaOutEntity.getNroOrden());
        String mensajeSuscripcion = MessageFormat.format(mensaje, finalizarRescateBPrivInDTO.getNombreFondo(),
                finalizarRescateBPrivInDTO.getMoneda() + " " + finalizarRescateBPrivDTO.getImporte(),
                finalizarRescateBPrivDTO.getCuotaPartes());
        finalizarRescateBPrivDTO.setMensajeSuscripcion(mensajeSuscripcion);
        finalizarRescateBPrivDTO.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
        return finalizarRescateBPrivDTO;
    }

    /**
     * Dto to entity.
     *
     * @param dtoRequest
     *            the dto request
     * @return the ejecucion fondo in entity
     * @throws BusinessException
     *             the business exception
     */
    private EjecucionFondoBancaPrivadaInEntity dtoToEntity(FinalizarRescateBPrivInDTO dtoRequest, Cliente cliente) throws BusinessException {
        EjecucionFondoBancaPrivadaInEntity entity = new EjecucionFondoBancaPrivadaInEntity();
        entity.setCliente(cliente);
        Credential credential = getRACFCredential();
        entity.setTipo(getTipo());

        entity.setNroCuenta(PREFIJO_CUENTA_TITULO
                + llenarConCerosIzqOTruncar(dtoRequest.getNumeroCtaCred().replaceAll("/", ""), LONGITUD_CUENTA));

        entity.setCuotasPartes(dtoRequest.getCuotaPartes());
        entity.setEspecieDestino("");
        entity.setMoneda(dtoRequest.getMoneda());
        entity.setCapital(new BigDecimal(dtoRequest.getImporte()));

        //// no viene en esta entidad, lo necesito
        entity.setIsPerfilInversion(dtoRequest.getDentroDelPerfil());
        entity.setUsuarioRacf(credential.getUsuario());
        entity.setPasswordRacf(credential.getPassword());

        ConsultaFondoOutEntity fondo;
        try {
            fondo = consultaFondoDAO.obtenerPorCodigo(dtoRequest.getCodigoFondo());
            entity.setEspecie(fondo.getEspecie());
            if (DivisaEnum.PESO.getCodigo().equals(fondo.getMoneda())) {
                entity.setMoneda(MONEDA_PESO);
            } else {
                entity.setMoneda(DivisaEnum.DOLAR.getCodigo());
            }
        } catch (DAOException e) {
            LOGGER.error("Error recuperando fondo Entity ", e);
            throw new BusinessException(e);
        }

        return entity;
    }

    /**
     * Metodo que ejecutar el rescate desde la grilla para devolver el plazo
     * efectivo del fondo, la cuenta operativa sin formatear y limite minimo y
     * maximo del rescate.
     *
     * @param viewRequest
     *            the view request
     * @param cliente
     *            the cliente
     * @return Plazo efectivo y Cuenta operativa sin formatear
     */
    @Override
    public Respuesta<RescateDesdeGrilla> obtenerRescateDesdeGrilla(RescateDesdeGrillaInView viewRequest,
            Cliente cliente) {
    	 boolean puedeAgendarRescate;
         RescateDesdeGrilla respuesta = new RescateDesdeGrilla();
         
 		Date fecha = new Date();
         LOGGER.info("CACHE_DIAS_NO_HABILES: Consulta al metodo desde obtenerRescateDesdeGrilla");
 		boolean esFeriado = inversionWSHelper.esFeriado(fecha);
         
    	if (!validarHorarioFondos() || esFinDeSemana() || esFeriado) {
			LOGGER.error("Los fondos no se encuentran dentro del horario de inicio ({}) y fin ({})",
					horarioDesdeOperarFondos, horarioHastaOperarFondos);
			puedeAgendarRescate= rescateDAO.consultaFondosAgendamiento(viewRequest.getCodigoFondo());
			if(puedeAgendarRescate) {
				respuesta.setPuedeRescatar(true);
				return respuestaFactory.crearRespuestaWarning(respuesta, "", TipoError.FUERADEHORARIO,
						CodigoMensajeConstantes.CODIGO_RESCATE_FUERA_HORARIO_MAPS);
			}else {
				respuesta.setPuedeRescatar(false);
				return respuestaFactory.crearRespuestaWarning(respuesta, "", TipoError.FUERADEHORARIO,
						CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS_MENU);
			}
			
		}
    	
        ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();

        try {
            fondo = consultaFondoDAO.obtenerPorCodigo(viewRequest.getCodigoFondo());
            respuesta.setPlazoEfectivo(fondo.getPlazoEfectivo());
            respuesta.setLimiteMaximoRescate(fondo.getLimiteMaximoRescate());
            respuesta.setLimiteMinimoRescate(fondo.getLimiteMinimoRescate());

            respuesta.setFondoNuevo(rescateFondoEspecie.getFondosMap().containsKey(viewRequest.getCodigoFondo()));
            if (respuesta.isFondoNuevo()) {
                FondoEspecie fondoEspecie = rescateFondoEspecie.getFondosMap().get(viewRequest.getCodigoFondo());
                LOGGER.info("Se opera con el fondo obtenido del yaml {}", fondoEspecie);
                respuesta.setSoloEspecie(fondoEspecie.getOperacion());
            } else {
                LOGGER.debug("El fondo consultado en el yaml {} no es nuevo", viewRequest.getCodigoFondo());
            }
            String nroCuentaOperativaFormateado = "";

            for (CuentaBancaPrivada cuentasBP : cliente.getCuentasBancaPrivada()) {
                nroCuentaOperativaFormateado = ISBANStringUtils
                        .formatearNumeroCuenta(cuentasBP.getCuentaOperativa().getNroCuentaProducto());
                if (viewRequest.getNumeroCuentaOperativa().equals(nroCuentaOperativaFormateado)) {
                    respuesta.setCuentaOperativaSinFormatear(
                            ISBANStringUtils.eliminarCeros(cuentasBP.getCuentaOperativa().getContratoAltair()));
                    respuesta.setNumeroDeCuentaTitulo(
                            ISBANStringUtils.formatearNumeroCuenta(cuentasBP.getCuentaOperativa().getNroCuentaTit()));

                }

            }

        } catch (DAOException e) {
            LOGGER.error("Error DAO metodo ObtenerRescateDesdeGrilla", e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
        return respuestaFactory.crearRespuestaOk(RescateDesdeGrilla.class, respuesta);
    }

    @Override
    public Respuesta<FinalizarRescateView> finalizarRescateFondoPorEspecie(FinalizarRescateInView viewRequest,
            Cliente cliente, String banca) {
        ConfirmarRescateEspecieIn entity = generarEntityConfirmarRescate(viewRequest, cliente, banca);
        return confirmar(entity);
    }

    @Override
    public Respuesta<FinalizarRescateView> finalizarRescateFondoPorEspecieBPriv(FinalizarRescateBPrivInView viewRequest,
            Cliente cliente, String banca) {
        ConfirmarRescateEspecieIn entity = generarEntityConfirmarRescateBpriv(viewRequest, cliente, banca);
        return confirmar(entity);
    }

    private ConfirmarRescateEspecieIn generarEntityConfirmarRescateBpriv(FinalizarRescateBPrivInView viewRequest,
            Cliente cliente, String banca) {
        ConfirmarRescateEspecieIn confirmarRescateEspecieIn = new ConfirmarRescateEspecieIn();
        confirmarRescateEspecieIn.setBanca(banca);
        confirmarRescateEspecieIn.setCanal("OBP");
        confirmarRescateEspecieIn.setCodigoFondo(viewRequest.getCodigoFondo());
        confirmarRescateEspecieIn.setCuentaTituloDestinoFCI(viewRequest.getCuentaTitulo());
        confirmarRescateEspecieIn.setCuentaTituloOrigenFCI(viewRequest.getCuentaTitulo());
        confirmarRescateEspecieIn.setCuitcuil(cliente.getNumeroCUILCUIT());
        confirmarRescateEspecieIn.setDni(cliente.getDni());
        confirmarRescateEspecieIn.setMonedaFondo(viewRequest.getMoneda());
        confirmarRescateEspecieIn.setMontoRescate(viewRequest.getImporte());
        confirmarRescateEspecieIn.setNombreFondo(viewRequest.getNombreFondo());
        confirmarRescateEspecieIn.setNup(cliente.getNup());
        confirmarRescateEspecieIn.setTipoPersona(cliente.getTipoPersona());
        confirmarRescateEspecieIn.setCuotaPartes(viewRequest.getCuotaPartes());
        return confirmarRescateEspecieIn;
    }

    private Respuesta<FinalizarRescateView> confirmar(ConfirmarRescateEspecieIn entity) {
        ConfirmarRescateEspecieOut confirmarRescateEspecieOut = rescateFondosPorEspecieDAOImpl.confirmarRescate(entity);
        if (StringUtils.isNotBlank(confirmarRescateEspecieOut.getCodigoRespuesta())
                && "0".equals(confirmarRescateEspecieOut.getCodigoRespuesta())) {
            FinalizarRescateView finalizarRescateView = new FinalizarRescateView();
            finalizarRescateView.setNroOrden(confirmarRescateEspecieOut.getNroOrden());
            String mensaje = mensajeBo.obtenerMensajePorCodigo(CodigoMensajeConstantes.FINALIZAR_RESCATE_ESPECIE_MENSAJE_OK).getMensaje();
            String mensajeSuscripcion = MessageFormat.format(mensaje, entity.getNombreFondo(),
            		ISBANStringUtils.formatearConComaYVariosDecimales2(entity.getMontoRescate(), 2));
            finalizarRescateView.setMensajeSuscripcion(mensajeSuscripcion);
            LOGGER.info("Se arma respuesta rescate de fondos con nro de orden: {}", finalizarRescateView.getNroOrden());
            return respuestaFactory.crearRespuestaOk(FinalizarRescateView.class, finalizarRescateView);
        }
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
        		CodigoErrorRescateEspecieEnum.fromCodigoString(confirmarRescateEspecieOut.getCodigoRespuesta()));
    }

	private ConfirmarRescateEspecieIn generarEntityConfirmarRescate(FinalizarRescateInView viewRequest, Cliente cliente,
            String banca) {
        ConfirmarRescateEspecieIn confirmarRescateEspecieIn = new ConfirmarRescateEspecieIn();
        confirmarRescateEspecieIn.setBanca(banca);
        confirmarRescateEspecieIn.setCanal("OBP");
        confirmarRescateEspecieIn.setCodigoFondo(viewRequest.getCodigoFondo());
        confirmarRescateEspecieIn.setCuentaTituloDestinoFCI(viewRequest.getCuentaTitulo());
        confirmarRescateEspecieIn.setCuentaTituloOrigenFCI(viewRequest.getCuentaTitulo());
        confirmarRescateEspecieIn.setCuitcuil(cliente.getNumeroCUILCUIT());
        confirmarRescateEspecieIn.setDni(cliente.getDni());
        confirmarRescateEspecieIn.setMonedaFondo(viewRequest.getMoneda());
        confirmarRescateEspecieIn.setMontoRescate(viewRequest.getImporte());
        confirmarRescateEspecieIn.setNombreFondo(viewRequest.getNombreFondo());
        confirmarRescateEspecieIn.setNup(cliente.getNup());
        confirmarRescateEspecieIn.setTipoPersona(cliente.getTipoPersona());
        confirmarRescateEspecieIn.setCuotaPartes(viewRequest.getCuotaPartes());
        return confirmarRescateEspecieIn;
    }

    private void cleanCacheHoldingBff(String cuentaTitulo, Cliente cliente, String segmento) {
        Boolean holdingsByBff = fundsApi.verifyAccessToGetHolding(cliente);
        String tokenRescate = sessionParametros.getTransactionTokenRedemptionFund();

        if (holdingsByBff.equals(Boolean.TRUE)) {
            if (tokenRescate == null) {
                fundsApi.cleanCacheFunds(cuentaTitulo, cliente, RESCATE, segmento);
            }
            fondosTenenciasPrototype.cleanCacheObpNupHoldingFondosTenenciaBff(cliente);
            try {
                fundsApi.getHoldingBff(cliente);
            } catch (ApiException e) {
                LOGGER.error("Error al obtener las tenencias desde FONDOS-TENENCIA-BFF", e);
            } catch (Exception e) {
                LOGGER.error("Error al obtener las tenencias desde FONDOS-TENENCIA-BFF - Exeption");
            }
        }
    }

}
