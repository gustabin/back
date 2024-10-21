package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao.CuentaTituloDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.dao.CustodiaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao.FondoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.RescateDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteRescateEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEIImpl;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateDesdeGrilla;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class RescateBOTest.
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class RescateBOTest {

    /** The rescate BO. */
    @InjectMocks
    private RescateBOImpl rescateBO;

    @Mock
    private FundsApi fundsApi;

    /** The rescate manager. */
    @InjectMocks
    private RescateManagerImpl rescateManager;

    /** The rescate SEI. */
    @Spy
    private RescateSEIImpl rescateSEI;

    @Mock
    private Validator validator;

    /** The fondo BO real. */
    @InjectMocks
    private FondoBOImpl fondoBOReal;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje bo. */
    @Mock
    private MensajeBO mensajeBo;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The estadistica BO. */
    @Mock
    private EstadisticaBO estadisticaBO;

    /** The rescate dao. */
    @Mock
    private RescateDAO rescateDao;

    /** The fondo dao. */
    @Mock
    private FondoDAO fondoDao;

    /** The fondo BO. */
    @Mock
    private FondoBO fondoBO;

    /** The consulta fondo DAO. */
    @Mock
    private ConsultaFondoDAO consultaFondoDAO;

    /** The custodia DAO. */
    @Mock
    private CustodiaDAO custodiaDAO;

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The sesion parametros real. */
    @Spy
    private SesionParametros sesionParametrosReal;

    /** The session cliente. */
    @Mock
    private SesionCliente sessionCliente;

    @Mock
    private LegalBO legalBO;
    
    @Mock
    private CredentialSecurityFactory credentialSecurity;
    
    @Mock
    private FondoBPrivDAO fondoBPrivDAO;
    @Mock
    private CuentaTituloDAO cuentaTituloDAO;
    
    /**
     * rescate OK test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarRescateOKTest() throws Exception {
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class)))
                .thenReturn(comprobanteRescateEntity);

        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = new Respuesta<FinalizarRescateDTO>();
        respuestaFinalizarRescate.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(
                respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FinalizarRescateDTO.class)))
                .thenReturn(respuestaFinalizarRescate);
        respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Finalizar Rescate FALLO DAOException con Reintento test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void finalizarRescateFalloDAOExceptionConReintentoTest() throws Exception {

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class))).thenThrow(new DAOException());

        Respuesta<FinalizarRescateDTO> responseFactoryError = new Respuesta<FinalizarRescateDTO>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaError(FinalizarRescateDTO.class, "mensaje",
                TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_CON_REINTENTO.toString()))
                .thenReturn(responseFactoryError);

        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate, responseFactoryError);

    }

    /**
     * Finalizar Rescate FALLO DAOException sin Reintento test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void finalizarRescateFalloDAOExceptionSinReintentoTest() throws Exception {

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        contadorIntentos.permiteReintentar();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class))).thenThrow(new DAOException());

        Respuesta<FinalizarRescateDTO> responseFactoryError = new Respuesta<FinalizarRescateDTO>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaError(FinalizarRescateDTO.class, "mensaje",
                TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString()))
                .thenReturn(responseFactoryError);

        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate, responseFactoryError);

    }

    /**
     * Finalizar Rescate FALLO TimeOutException Sin Reintento test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void finalizarRescateFalloTimeOutExceptionTest() throws Exception {

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class)))
                .thenThrow(new TimeOutException(""));

        Respuesta<FinalizarRescateDTO> responseFactoryError = new Respuesta<FinalizarRescateDTO>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaError(FinalizarRescateDTO.class, "mensaje",
                TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString()))
                .thenReturn(responseFactoryError);

        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate, responseFactoryError);

    }

    /**
     * Finalizar Rescate FALLO FueraDeHorarioException test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void finalizarRescateFalloFueraDeHorarioExceptionTest() throws Exception {

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class)))
                .thenThrow(new FueraDeHorarioException());

        Respuesta<Object> responseFactoryError = new Respuesta<Object>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.FUERADEHORARIO,
                CodigoMensajeConstantes.FONDO_FUERA_HORARIO)).thenReturn(responseFactoryError);

        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate, responseFactoryError);

    }

    /**
     * Finalizar Rescate FALLO ImporteMenorAlMinimoException test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void finalizarRescateFalloImporteMenorAlMinimoExceptionTest() throws Exception {

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class)))
                .thenThrow(new ImporteMenorAlMinimoException());

        Respuesta<Object> responseFactoryError = new Respuesta<Object>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.eq(TipoError.ERROR_LIMITE_MINIMO),
        		Matchers.anyString())).thenReturn(responseFactoryError);

        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate, responseFactoryError);

    }

    /**
     * Finalizar Rescate FALLO SaldoInsuficienteException test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void finalizarRescateFalloSaldoInsuficienteExceptionTest() throws Exception {

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class)))
                .thenThrow(new SaldoInsuficienteException());

        Respuesta<Object> responseFactoryError = new Respuesta<Object>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS))
                .thenReturn(responseFactoryError);
        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate, responseFactoryError);

    }

    /**
     * Finalizar Rescate FALLO Cuenta Sin Operar Exception Con Reintento test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void finalizarRescateCuentaSinOperarExceptionConReintentoTest() throws Exception {

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class)))
                .thenThrow(new CuentaSinOperarException());

        Respuesta<FinalizarRescateDTO> responseFactoryError = new Respuesta<FinalizarRescateDTO>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaError(FinalizarRescateDTO.class, "mensaje",
                TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_CON_REINTENTO.toString()))
                .thenReturn(responseFactoryError);

        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate, responseFactoryError);

    }

    /**
     * Finalizar Rescate FALLO Cuenta Sin Operar Exception Sin Reintento test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void finalizarRescateCuentaSinOperarExceptionSinReintentoTest() throws Exception {

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        contadorIntentos.permiteReintentar();
        contadorIntentos.permiteReintentar();
        FinalizarRescateInDTO dtoRequest = new FinalizarRescateInDTO();
        dtoRequest.setCodigoFondo("011");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setCuotaPartes("20");
        dtoRequest.setImporte("16700");
        dtoRequest.setImporteRescateNeto("00000000016700");
        dtoRequest.setImporteRescateComision("00000000000000");
        dtoRequest.setMoneda("u$s");
        dtoRequest.setNombreFondo("SUPER ACCIONES BRASI");
        dtoRequest.setNumeroCtaCred("638801");
        dtoRequest.setSucursalCtaCred("000");
        dtoRequest.setTipoCtaCred("CU");
        Cliente cliente = mock(Cliente.class);

        ComprobanteRescateEntity comprobanteRescateEntity = new ComprobanteRescateEntity();
        comprobanteRescateEntity.setCotacaoPact("00000000000000");
        comprobanteRescateEntity.setDescripcionMoneda("DOLAR");
        comprobanteRescateEntity.setEstadoActual("        ");
        comprobanteRescateEntity.setHeaderTrama(
                "200000000000P04HTML0009900010301PRQP31  ********00156683000000022017020614355600000000IBF21646000000000000RESFCI____1600000            01576531    00        00 000000000201702061440300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        comprobanteRescateEntity.setImporteRescateBruto("00000000016700");
        comprobanteRescateEntity.setImporteRescateComision("00000000000000");
        comprobanteRescateEntity.setImporteRescateNeto("00000000016700");
        comprobanteRescateEntity.setMarcaKU("0");
        comprobanteRescateEntity.setMontoCambio("00000000000000");
        comprobanteRescateEntity.setMotivoActual("        ");
        comprobanteRescateEntity.setNombreFondo("SUPER ACCIONES BRASI");
        comprobanteRescateEntity.setNroRescate("0000002530");
        comprobanteRescateEntity.setStatusResultadoExtendido("00000000");
        comprobanteRescateEntity.setTotalCuotasPartes("00000001501289");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(rescateDao.comprobanteRescate(Matchers.any(FondoInEntity.class)))
                .thenThrow(new CuentaSinOperarException());

        Respuesta<FinalizarRescateDTO> responseFactoryError = new Respuesta<FinalizarRescateDTO>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaError(FinalizarRescateDTO.class, "mensaje",
                TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString()))
                .thenReturn(responseFactoryError);

        Respuesta<FinalizarRescateDTO> respuestaFinalizarRescate = rescateBO.finalizarRescate(dtoRequest, cliente);
        Assert.assertNotNull(respuestaFinalizarRescate);
        Assert.assertEquals(respuestaFinalizarRescate, responseFactoryError);

    }

    /**
     * Configurar Rescate OK test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void configurarRescateOKTest() throws DAOException {

        List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
        CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
        cuentaTituloDTO.setNroCuenta("0000000015631228");
        cuentaTituloDTO.setNroCuentaFormateado("1563122/8");
        cuentaTituloDTO.setSucursal("0000");
        cuentasTitulo.add(cuentaTituloDTO);

        CuentasConsultaFondoDTO dtoRequest = new CuentasConsultaFondoDTO();
        dtoRequest.setCabeceraStack("cabeceraStack");
        dtoRequest.setTipoBanca("BR");
        dtoRequest.setCuentasTitulo(cuentasTitulo);
        Cliente cliente = mock(Cliente.class);

        ConsultaTenenciaFCIOutEntity consultaTenenciaFCIOutEntity = new ConsultaTenenciaFCIOutEntity();
        consultaTenenciaFCIOutEntity.setCantidadTenecias(2000L);
        consultaTenenciaFCIOutEntity.setCodigoRetornoExtendido("");
        consultaTenenciaFCIOutEntity.setHeaderTrama("");
        List<ConsultaTenenciaFCIEntity> listaTenencia = new ArrayList<ConsultaTenenciaFCIEntity>();
        ConsultaTenenciaFCIEntity consultaTenencia = new ConsultaTenenciaFCIEntity();
        String especieCodigo = "027";
        consultaTenencia.setEspecieCodigo(especieCodigo);
        String nroCuenta = "0000000015631228";
        consultaTenencia.setNroCuenta(nroCuenta);
        String monedaTipo = "00";
        consultaTenencia.setMonedaTipo(monedaTipo);
        String teneciaValuada = "48200";
        consultaTenencia.setTeneciaValuada(teneciaValuada);
        listaTenencia.add(consultaTenencia);
        consultaTenenciaFCIOutEntity.setListaTenencia(listaTenencia);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setHabilitarSuscripcion("1");
        consultaFondoOutEntity.setHabilitarRescate("1");
        consultaFondoOutEntity.setTipoBanca("BR");
        consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
        consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
        consultaFondoOutEntity.setCodigoFondo("027");
        consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");


        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Mockito.when(fondoDao.consultaTenenciaFCI(Matchers.any(Cliente.class),
                Matchers.any(ConsultaTenenciaFCIInEntity.class))).thenReturn(consultaTenenciaFCIOutEntity);

        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);

        Respuesta<CuentasConsultaFondoDTO> respuestaConfigurarRescate = new Respuesta<CuentasConsultaFondoDTO>();
        respuestaConfigurarRescate.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(CuentaTituloDTO.class)))
                .thenReturn(respuestaConfigurarRescate);

        respuestaConfigurarRescate = rescateBO.obtenerFondosSuscriptos(dtoRequest, cliente);
        Assert.assertNotNull(respuestaConfigurarRescate);
        Assert.assertEquals(respuestaConfigurarRescate.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Configurar Rescate Fallo ObtenerFondos disponibles DAOException test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void configurarRescateFalloFondosDisponibles_DAOExceptionTest() throws DAOException {

        List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
        CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
        cuentaTituloDTO.setNroCuenta("0000000015631228");
        cuentaTituloDTO.setNroCuentaFormateado("1563122/8");
        cuentaTituloDTO.setSucursal("0000");
        cuentasTitulo.add(cuentaTituloDTO);

        CuentasConsultaFondoDTO dtoRequest = new CuentasConsultaFondoDTO();
        dtoRequest.setCabeceraStack("cabeceraStack");
        dtoRequest.setTipoBanca("BR");
        dtoRequest.setCuentasTitulo(cuentasTitulo);
        Cliente cliente = mock(Cliente.class);

        ConsultaTenenciaFCIOutEntity consultaTenenciaFCIOutEntity = new ConsultaTenenciaFCIOutEntity();
        consultaTenenciaFCIOutEntity.setCantidadTenecias(2000L);
        consultaTenenciaFCIOutEntity.setCodigoRetornoExtendido("");
        consultaTenenciaFCIOutEntity.setHeaderTrama("");
        List<ConsultaTenenciaFCIEntity> listaTenencia = new ArrayList<ConsultaTenenciaFCIEntity>();
        ConsultaTenenciaFCIEntity consultaTenencia = new ConsultaTenenciaFCIEntity();
        String especieCodigo = "027";
        consultaTenencia.setEspecieCodigo(especieCodigo);
        String nroCuenta = "0000000015631228";
        consultaTenencia.setNroCuenta(nroCuenta);
        String monedaTipo = "00";
        consultaTenencia.setMonedaTipo(monedaTipo);
        String teneciaValuada = "48200";
        consultaTenencia.setTeneciaValuada(teneciaValuada);
        listaTenencia.add(consultaTenencia);
        consultaTenenciaFCIOutEntity.setListaTenencia(listaTenencia);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setHabilitarSuscripcion("1");
        consultaFondoOutEntity.setTipoBanca("BR");
        consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
        consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
        consultaFondoOutEntity.setCodigoFondo("027");
        consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");

        List<ConsultaFondoOutEntity> coleccionConsultaFondoOutEntity = new ArrayList<ConsultaFondoOutEntity>();
        coleccionConsultaFondoOutEntity.add(consultaFondoOutEntity);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(fondoDao.consultaTenenciaFCI(Matchers.any(Cliente.class),
                Matchers.any(ConsultaTenenciaFCIInEntity.class))).thenReturn(consultaTenenciaFCIOutEntity);

      
        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenThrow(new DAOException());
        Respuesta<Object> responseFactoryError = new Respuesta<Object>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS))
                .thenReturn(responseFactoryError);

        Respuesta<CuentasConsultaFondoDTO> respuestaConfigurarRescate = rescateBO.obtenerFondosSuscriptos(dtoRequest,
                cliente);
        Assert.assertNotNull(respuestaConfigurarRescate);
        Assert.assertEquals(respuestaConfigurarRescate, responseFactoryError);

    }

    /**
     * Configurar Rescate Fallo consultaTenenciaFCI DAOException test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void configurarRescateFalloConsultaTenenciaFCI_DAOExceptionTest() throws DAOException {

        List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
        CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
        cuentaTituloDTO.setNroCuenta("0000000015631228");
        cuentaTituloDTO.setNroCuentaFormateado("1563122/8");
        cuentaTituloDTO.setSucursal("0000");
        cuentasTitulo.add(cuentaTituloDTO);

        CuentasConsultaFondoDTO dtoRequest = new CuentasConsultaFondoDTO();
        dtoRequest.setCabeceraStack("cabeceraStack");
        dtoRequest.setTipoBanca("BR");
        dtoRequest.setCuentasTitulo(cuentasTitulo);
        Cliente cliente = mock(Cliente.class);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setHabilitarSuscripcion("1");
        consultaFondoOutEntity.setTipoBanca("BR");
        consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
        consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
        consultaFondoOutEntity.setCodigoFondo("027");
        consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");


        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Mockito.when(fondoDao.consultaTenenciaFCI(Matchers.any(Cliente.class),
                Matchers.any(ConsultaTenenciaFCIInEntity.class))).thenThrow(new DAOException());

        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);

        Respuesta<Object> responseFactoryError = new Respuesta<Object>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

        Respuesta<CuentasConsultaFondoDTO> respuestaConfigurarRescate = rescateBO.obtenerFondosSuscriptos(dtoRequest,
                cliente);
        Assert.assertNotNull(respuestaConfigurarRescate);
        Assert.assertEquals(respuestaConfigurarRescate, responseFactoryError);

    }

    /**
     * Configurar Rescate Fallo Sin Fondo a Rescatar test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void configurarRescateFalloSinFondoARescatarTest() throws DAOException {

        List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
        CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
        cuentaTituloDTO.setNroCuenta("0000000015631228");
        cuentaTituloDTO.setNroCuentaFormateado("1563122/8");
        cuentaTituloDTO.setSucursal("0000");
        cuentasTitulo.add(cuentaTituloDTO);

        CuentasConsultaFondoDTO dtoRequest = new CuentasConsultaFondoDTO();
        dtoRequest.setCabeceraStack("cabeceraStack");
        dtoRequest.setTipoBanca("BR");
        dtoRequest.setCuentasTitulo(cuentasTitulo);
        Cliente cliente = mock(Cliente.class);

        ConsultaTenenciaFCIOutEntity consultaTenenciaFCIOutEntity = new ConsultaTenenciaFCIOutEntity();

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setHabilitarSuscripcion("1");
        consultaFondoOutEntity.setTipoBanca("BR");
        consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
        consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
        consultaFondoOutEntity.setCodigoFondo("027");
        consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");


        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(fondoDao.consultaTenenciaFCI(Matchers.any(Cliente.class),
                Matchers.any(ConsultaTenenciaFCIInEntity.class))).thenReturn(consultaTenenciaFCIOutEntity);

        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);

        Respuesta<Object> responseFactoryError = new Respuesta<Object>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.SIN_FONDOS_A_RESCATAR,
                CodigoMensajeConstantes.SIN_FONDOS_A_RESCATAR)).thenReturn(responseFactoryError);

        Respuesta<CuentasConsultaFondoDTO> respuestaConfigurarRescate = rescateBO.obtenerFondosSuscriptos(dtoRequest,
                cliente);
        Assert.assertNotNull(respuestaConfigurarRescate);
        Assert.assertEquals(respuestaConfigurarRescate, responseFactoryError);

    }

    /**
     * Obtener fondos suscriptos B priv test ok.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerFondosSuscriptosBPrivTestOk() {
        
        
    }

    @SuppressWarnings("unchecked")
	@Test
    public void simularRescateFondoTestOK() throws Exception {

    	RescateFondoOutEntity mockSimulacion = new RescateFondoOutEntity();
    	String totalCuotasPartes = "1500";
		mockSimulacion.setTotalCuotasPartes(totalCuotasPartes);
    	String importeRescateComision = "3000";
		mockSimulacion.setImporteRescateComision(importeRescateComision);
    	String importeRescateNeto = "2000";
		mockSimulacion.setImporteRescateNeto(importeRescateNeto);
		Mockito.when(rescateDao.simulacionRescate(Matchers.any(Cliente.class), Matchers.any(RescateFondoInEntity.class))).thenReturn(mockSimulacion);
		
		Respuesta<String> mockLegal = new Respuesta<String>();
		mockLegal.setRespuesta("Respuesta legal mockeada");
		mockLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(mockLegal);

		Respuesta<SimularRescateOutDTO> respuestaSimularRescate = new Respuesta<SimularRescateOutDTO>();
		respuestaSimularRescate.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularRescateOutDTO.class))).thenReturn(respuestaSimularRescate);
		
    	SimularRescateInDTO dtoRequest = new SimularRescateInDTO();
    	String tipoCuenta = "02";
		dtoRequest.setTipoCuenta(tipoCuenta);
    	String importeNeto = "30500";
		dtoRequest.setImporteNeto(importeNeto);
    	String numeroCuenta = "12345678";
		dtoRequest.setNumeroCuenta(numeroCuenta);
		String moneda = "$";
		dtoRequest.setMoneda(moneda);
		Cliente cliente = new Cliente();
		
		Respuesta<SimularRescateOutDTO> respuestaSimularRescateBO = rescateBO.simularRescateFondo(dtoRequest, cliente);
		
		Assert.assertNotNull(respuestaSimularRescateBO);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaSimularRescateBO.getEstadoRespuesta());
    }
    
    @Test
    public void simularRescateFondoTestWarning() throws Exception {

    	RescateFondoOutEntity mockSimulacion = new RescateFondoOutEntity();
    	String totalCuotasPartes = "1500";
		mockSimulacion.setTotalCuotasPartes(totalCuotasPartes);
    	String importeRescateComision = "3000";
		mockSimulacion.setImporteRescateComision(importeRescateComision);
    	String importeRescateNeto = "2000";
		mockSimulacion.setImporteRescateNeto(importeRescateNeto);
		Mockito.when(rescateDao.simulacionRescate(Matchers.any(Cliente.class), Matchers.any(RescateFondoInEntity.class))).thenThrow(new FueraDeHorarioException());
		
		Respuesta<String> mockLegal = new Respuesta<String>();
		mockLegal.setRespuesta("Respuesta legal mockeada");
		mockLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(mockLegal);

		Respuesta<SimularRescateOutDTO> respuestaSimularRescate = new Respuesta<SimularRescateOutDTO>();
		respuestaSimularRescate.setEstadoRespuesta(EstadoRespuesta.OK);
		Respuesta<Object> respuestaWarning = new Respuesta<Object>();
//		respuestaSimularRescate.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(String.class), Matchers.any(TipoError.class), Matchers.any(String.class))).thenReturn(respuestaWarning);
		
    	SimularRescateInDTO dtoRequest = new SimularRescateInDTO();
    	String tipoCuenta = "CU";
		dtoRequest.setTipoCuenta(tipoCuenta);
    	String importeNeto = "30500";
		dtoRequest.setImporteNeto(importeNeto);
    	String numeroCuenta = "12345678";
		dtoRequest.setNumeroCuenta(numeroCuenta);
		String moneda = "$";
		dtoRequest.setMoneda(moneda);
		Cliente cliente = new Cliente();
		
		Respuesta<SimularRescateOutDTO> respuestaSimularRescateBO = rescateBO.simularRescateFondo(dtoRequest, cliente);
		
		Assert.assertNotNull(respuestaSimularRescateBO);
//        Assert.assertEquals(EstadoRespuesta.WARNING, respuestaSimularRescateBO.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void simularRescateFondoTestError() throws Exception {

		Mockito.when(rescateDao.simulacionRescate(Matchers.any(Cliente.class), Matchers.any(RescateFondoInEntity.class))).thenThrow(new DAOException());

		Respuesta<Object> respuestaError = new Respuesta<Object>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList())).thenReturn(respuestaError);
    	SimularRescateInDTO dtoRequest = new SimularRescateInDTO();
    	String tipoCuenta = "CU";
		dtoRequest.setTipoCuenta(tipoCuenta);
    	String importeNeto = "30500";
		dtoRequest.setImporteNeto(importeNeto);
    	String numeroCuenta = "12345678";
		dtoRequest.setNumeroCuenta(numeroCuenta);
		String moneda = "$";
		dtoRequest.setMoneda(moneda);
		Cliente cliente = new Cliente();
		
		Respuesta<SimularRescateOutDTO> respuestaSimularRescateBO = rescateBO.simularRescateFondo(dtoRequest, cliente);
		
		Assert.assertNotNull(respuestaSimularRescateBO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaSimularRescateBO.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void simularRescateFondoBPrivTestOK() throws DAOException, SQLException{

		SimulacionInDTO dtoRequest = new SimulacionInDTO();
		String nroCuentaBancaPrivada = "12345678";
		dtoRequest.setNroCuentaBancaPrivada(nroCuentaBancaPrivada);
		BigDecimal importe = new BigDecimal("45000");
		dtoRequest.setImporte(importe);
		
		Cliente cliente = new Cliente();
		
		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password );
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);
		
		SimulacionFondoBancaPrivadaOutEntity respuestaDaoSimular = new SimulacionFondoBancaPrivadaOutEntity();
		String disclaimer = "disclaimer";
		respuestaDaoSimular.setDisclaimer(disclaimer);
		String cuotasPartes = "cuotas partes";
		respuestaDaoSimular.setCuotasPartes(cuotasPartes);
		String dentroDelPerfil = "dentro del perfil";
		respuestaDaoSimular.setDentroDelPerfil(dentroDelPerfil);
		Mockito.when(fondoBPrivDAO.simularSuscripcionBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class))).thenReturn(respuestaDaoSimular);
		
		Respuesta<String> mockLegal = new Respuesta<String>();
		mockLegal.setRespuesta("Respuesta legal mockeada");
		mockLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(mockLegal);
		
		Respuesta<SimularRescateOutDTO> respuestaSimularRescate = new Respuesta<SimularRescateOutDTO>();
		respuestaSimularRescate.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularRescateOutDTO.class))).thenReturn(respuestaSimularRescate);
		
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);
        
		Respuesta<SimularRescateOutDTO> respuestaSimularRescateBO = rescateBO.simularRescateFondoBPriv(dtoRequest, cliente);
		
		Assert.assertNotNull(respuestaSimularRescateBO);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaSimularRescateBO.getEstadoRespuesta());
    }
    
    @Test
    public void finalizarRescateBprivOkTest() throws DAOException, SQLException{
    	
    	ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        
        EjecucionFondoBancaPrivadaOutEntity respuestaDaoSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
        String cuotaPartes = "123456.78";
		respuestaDaoSuscribir.setCuotaPartes(cuotaPartes);
		String disclaimer = "Disclaimer";
		respuestaDaoSuscribir.setDisclaimer(disclaimer);
		String capital = "123456";
		respuestaDaoSuscribir.setCapital(capital);
		String nroCertificado = "987654";
		respuestaDaoSuscribir.setNroCertificado(nroCertificado);
		String nroOrden = "7654";
		respuestaDaoSuscribir.setNroOrden(nroOrden);
		Mockito.when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class))).thenReturn(respuestaDaoSuscribir);
		
		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password );
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setHabilitarSuscripcion("1");
        consultaFondoOutEntity.setTipoBanca("BR");
        consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
        consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
        consultaFondoOutEntity.setCodigoFondo("027");
        consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
    	FinalizarRescateBPrivInDTO dtoRequest = new FinalizarRescateBPrivInDTO();
    	String nombreFondo = "Nombre fondo";
		dtoRequest.setNombreFondo(nombreFondo);
    	String moneda = "$";
		dtoRequest.setMoneda(moneda);
		String numeroCtaCred = "2345677";
		dtoRequest.setNumeroCtaCred(numeroCtaCred);
		String cuotasPartes = "345678";
		dtoRequest.setCuotaPartes(cuotasPartes);
		String importe = "123456";
		dtoRequest.setImporte(importe);
		String dentroPerfil = "DF";
		dtoRequest.setDentroDelPerfil(dentroPerfil);
		Cliente cliente = new Cliente();
		rescateBO.finalizarRescateBPriv(dtoRequest, cliente);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void ObtenerRescateDesdeGrilla() throws DAOException {

        ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        fondo.setPlazoEfectivo("24");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);

        Cliente cliente = new Cliente();
        cliente.setNup("001");
        List<CuentaBancaPrivada> listcuentasBP = new ArrayList<CuentaBancaPrivada>();
        CuentaBancaPrivada cuentaBP = new CuentaBancaPrivada();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("000123456");
        cuenta.setNroCuentaTit("01");
        cuentaBP.setCuentaOperativa(cuenta);
        listcuentasBP.add(cuentaBP);
        cliente.setCuentasBancaPrivada(listcuentasBP);

        CuentaTituloInEntity inEntity = new CuentaTituloInEntity();
        inEntity.setCliente(cliente);

        CuentaTituloOutEntity outEntity = new CuentaTituloOutEntity();
        List<CuentaBP> aVer = new ArrayList<CuentaBP>();
        CuentaBP otraVez = new CuentaBP("01", "000123456");
        aVer.add(otraVez);
        outEntity.relacionesOperativaTitulo(aVer);

        Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
                .thenReturn(outEntity);

        RescateDesdeGrillaInView viewRequest = new RescateDesdeGrillaInView();
        viewRequest.setCodigoFondo("007");
        viewRequest.setNumeroCuentaOperativa("000123456");
        Respuesta<RescateDesdeGrilla> respuesta = new Respuesta<RescateDesdeGrilla>();

        Mockito.when(
                respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(RescateDesdeGrilla.class)))
                .thenReturn(respuesta);

        Respuesta<RescateDesdeGrilla> respuestafinal = rescateBO.obtenerRescateDesdeGrilla(viewRequest, cliente);
        Assert.assertNotNull(respuestafinal);
    }
    
    @Test
    public void ObtenerRescateDesdeGrillaErrorDAO() throws DAOException {

        ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        fondo.setPlazoEfectivo("24");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenThrow(new DAOException());


        RescateDesdeGrillaInView viewRequest = new RescateDesdeGrillaInView();
        viewRequest.setCodigoFondo("007");
        viewRequest.setNumeroCuentaOperativa("000123456");
        
        Cliente cliente = mock(Cliente.class);

        Respuesta<Object> respuestaConfiguracion = new Respuesta<Object>();
        respuestaConfiguracion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class),
                Matchers.any(String.class))).thenReturn(respuestaConfiguracion);
        
        Respuesta<RescateDesdeGrilla> respuestafinal = rescateBO.obtenerRescateDesdeGrilla(viewRequest, cliente);
        Assert.assertNotNull(respuestafinal);
    }
        
    @Test
    public void finalizarRescateBprivErrorBusinessTest() throws DAOException, SQLException{
        
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        
        EjecucionFondoBancaPrivadaOutEntity respuestaDaoSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
        String cuotaPartes = "123456.78";
        respuestaDaoSuscribir.setCuotaPartes(cuotaPartes);
        String disclaimer = "Disclaimer";
        respuestaDaoSuscribir.setDisclaimer(disclaimer);
        String capital = "123456";
        respuestaDaoSuscribir.setCapital(capital);
        String nroCertificado = "987654";
        respuestaDaoSuscribir.setNroCertificado(nroCertificado);
        String nroOrden = "7654";
        respuestaDaoSuscribir.setNroOrden(nroOrden);
        Mockito.when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class))).thenReturn(respuestaDaoSuscribir);
        
        Credential credenciales = new Credential();
        String usuario = "usuario";
        credenciales.setUsuario(usuario);
        String password = "password";
        credenciales.setPassword(password );
        Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setHabilitarSuscripcion("1");
        consultaFondoOutEntity.setTipoBanca("BR");
        consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
        consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
        consultaFondoOutEntity.setCodigoFondo("027");
        consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenThrow(new DAOException());

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        FinalizarRescateBPrivInDTO dtoRequest = new FinalizarRescateBPrivInDTO();
        String nombreFondo = "Nombre fondo";
        dtoRequest.setNombreFondo(nombreFondo);
        String moneda = "$";
        dtoRequest.setMoneda(moneda);
        String numeroCtaCred = "2345677";
        dtoRequest.setNumeroCtaCred(numeroCtaCred);
        String cuotasPartes = "345678";
        dtoRequest.setCuotaPartes(cuotasPartes);
        String importe = "123456";
        dtoRequest.setImporte(importe);
        String dentroPerfil = "DF";
        dtoRequest.setDentroDelPerfil(dentroPerfil);
        Cliente cliente = new Cliente();
        rescateBO.finalizarRescateBPriv(dtoRequest, cliente);
    }
    
    @Test
    public void finalizarRescateBprivErrorDAOTest() throws DAOException, SQLException{
        
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        
        EjecucionFondoBancaPrivadaOutEntity respuestaDaoSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
        String cuotaPartes = "123456.78";
        respuestaDaoSuscribir.setCuotaPartes(cuotaPartes);
        String disclaimer = "Disclaimer";
        respuestaDaoSuscribir.setDisclaimer(disclaimer);
        String capital = "123456";
        respuestaDaoSuscribir.setCapital(capital);
        String nroCertificado = "987654";
        respuestaDaoSuscribir.setNroCertificado(nroCertificado);
        String nroOrden = "7654";
        respuestaDaoSuscribir.setNroOrden(nroOrden);
        Mockito.when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class))).thenThrow(new DAOException());
        
        Credential credenciales = new Credential();
        String usuario = "usuario";
        credenciales.setUsuario(usuario);
        String password = "password";
        credenciales.setPassword(password );
        Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setHabilitarSuscripcion("1");
        consultaFondoOutEntity.setTipoBanca("BR");
        consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
        consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
        consultaFondoOutEntity.setCodigoFondo("027");
        consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenThrow(new DAOException());

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        FinalizarRescateBPrivInDTO dtoRequest = new FinalizarRescateBPrivInDTO();
        String nombreFondo = "Nombre fondo";
        dtoRequest.setNombreFondo(nombreFondo);
        String moneda = "$";
        dtoRequest.setMoneda(moneda);
        String numeroCtaCred = "2345677";
        dtoRequest.setNumeroCtaCred(numeroCtaCred);
        String cuotasPartes = "345678";
        dtoRequest.setCuotaPartes(cuotasPartes);
        String importe = "123456";
        dtoRequest.setImporte(importe);
        String dentroPerfil = "DF";
        dtoRequest.setDentroDelPerfil(dentroPerfil);
        Cliente cliente = new Cliente();
        rescateBO.finalizarRescateBPriv(dtoRequest, cliente);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularRescateFondoBPrivErrorDAO() throws DAOException, SQLException{

        SimulacionInDTO dtoRequest = new SimulacionInDTO();
        String nroCuentaBancaPrivada = "12345678";
        dtoRequest.setNroCuentaBancaPrivada(nroCuentaBancaPrivada);
        BigDecimal importe = new BigDecimal("45000");
        dtoRequest.setImporte(importe);
        
        Cliente cliente = new Cliente();
        
        Credential credenciales = new Credential();
        String usuario = "usuario";
        credenciales.setUsuario(usuario);
        String password = "password";
        credenciales.setPassword(password );
        Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);
        
        SimulacionFondoBancaPrivadaOutEntity respuestaDaoSimular = new SimulacionFondoBancaPrivadaOutEntity();
        String disclaimer = "disclaimer";
        respuestaDaoSimular.setDisclaimer(disclaimer);
        String cuotasPartes = "cuotas partes";
        respuestaDaoSimular.setCuotasPartes(cuotasPartes);
        String dentroDelPerfil = "dentro del perfil";
        respuestaDaoSimular.setDentroDelPerfil(dentroDelPerfil);
        Mockito.when(fondoBPrivDAO.simularSuscripcionBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class))).thenThrow(new DAOException());
               
        Respuesta<SimularRescateOutDTO> respuestaSimularRescate = new Respuesta<SimularRescateOutDTO>();
        respuestaSimularRescate.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(
                respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularRescateOutDTO.class)))
                .thenReturn(respuestaSimularRescate);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
                .thenReturn(consultaFondoOutEntity);

        Respuesta<Object> respuestaConfiguracion = new Respuesta<Object>();
        respuestaConfiguracion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class),
                Matchers.any(String.class))).thenReturn(respuestaConfiguracion);

        Respuesta<SimularRescateOutDTO> respuestaSimularRescateBO = rescateBO.simularRescateFondoBPriv(dtoRequest,
                cliente);

        Assert.assertNotNull(respuestaSimularRescateBO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaSimularRescateBO.getEstadoRespuesta());
    }
    @SuppressWarnings("unchecked")
    @Test
    public void simularRescateFondoBPrivErrorBusinnes() throws DAOException, SQLException{

        SimulacionInDTO dtoRequest = new SimulacionInDTO();
        String nroCuentaBancaPrivada = "12345678";
        dtoRequest.setNroCuentaBancaPrivada(nroCuentaBancaPrivada);
        BigDecimal importe = new BigDecimal("45000");
        dtoRequest.setImporte(importe);
        
        Cliente cliente = new Cliente();
        
        Credential credenciales = new Credential();
        String usuario = "usuario";
        credenciales.setUsuario(usuario);
        String password = "password";
        credenciales.setPassword(password );
        Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);
        
        SimulacionFondoBancaPrivadaOutEntity respuestaDaoSimular = new SimulacionFondoBancaPrivadaOutEntity();
        String disclaimer = "disclaimer";
        respuestaDaoSimular.setDisclaimer(disclaimer);
        String cuotasPartes = "cuotas partes";
        respuestaDaoSimular.setCuotasPartes(cuotasPartes);
        String dentroDelPerfil = "dentro del perfil";
        respuestaDaoSimular.setDentroDelPerfil(dentroDelPerfil);
        Mockito.when(fondoBPrivDAO.simularSuscripcionBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class))).thenReturn(respuestaDaoSimular);
               
        Respuesta<SimularRescateOutDTO> respuestaSimularRescate = new Respuesta<SimularRescateOutDTO>();
        respuestaSimularRescate.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(
                respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularRescateOutDTO.class)))
                .thenReturn(respuestaSimularRescate);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
                .thenThrow(new DAOException());

        Respuesta<Object> respuestaConfiguracion = new Respuesta<Object>();
        respuestaConfiguracion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class),
                Matchers.any(String.class))).thenReturn(respuestaConfiguracion);

        Respuesta<SimularRescateOutDTO> respuestaSimularRescateBO = rescateBO.simularRescateFondoBPriv(dtoRequest,
                cliente);

        Assert.assertNotNull(respuestaSimularRescateBO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaSimularRescateBO.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularRescateFondoBPrivErrorLegales() throws DAOException, SQLException{

        SimulacionInDTO dtoRequest = new SimulacionInDTO();
        String nroCuentaBancaPrivada = "12345678";
        dtoRequest.setNroCuentaBancaPrivada(nroCuentaBancaPrivada);
        BigDecimal importe = new BigDecimal("45000");
        dtoRequest.setImporte(importe);
        
        Cliente cliente = new Cliente();
        
        Credential credenciales = new Credential();
        String usuario = "usuario";
        credenciales.setUsuario(usuario);
        String password = "password";
        credenciales.setPassword(password );
        Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);
        
        SimulacionFondoBancaPrivadaOutEntity respuestaDaoSimular = new SimulacionFondoBancaPrivadaOutEntity();
        String disclaimer = "disclaimer";
        respuestaDaoSimular.setDisclaimer(disclaimer);
        String cuotasPartes = "cuotas partes";
        respuestaDaoSimular.setCuotasPartes(cuotasPartes);
        String dentroDelPerfil = "dentro del perfil";
        respuestaDaoSimular.setDentroDelPerfil(dentroDelPerfil);
        Mockito.when(fondoBPrivDAO.simularSuscripcionBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class))).thenReturn(respuestaDaoSimular);
        
        Respuesta<String> mockLegal = new Respuesta<String>();
        mockLegal.setRespuesta("Respuesta legal mockeada");
        mockLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(mockLegal);
        
        Respuesta<SimularRescateOutDTO> respuestaSimularRescate = new Respuesta<SimularRescateOutDTO>();
        respuestaSimularRescate.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(
                respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularRescateOutDTO.class)))
                .thenReturn(respuestaSimularRescate);

        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setEspecie("especie");
        consultaFondoOutEntity.setMoneda("ARS");
        Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
                .thenReturn(consultaFondoOutEntity);

        Respuesta<Object> respuestaConfiguracion = new Respuesta<Object>();
        respuestaConfiguracion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class),
                Matchers.any(String.class))).thenReturn(respuestaConfiguracion);

        Respuesta<SimularRescateOutDTO> respuestaSimularRescateBO = rescateBO.simularRescateFondoBPriv(dtoRequest,
                cliente);

        Assert.assertNotNull(respuestaSimularRescateBO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaSimularRescateBO.getEstadoRespuesta());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void simularRescateFondoErrorLegal() throws Exception {

        RescateFondoOutEntity mockSimulacion = new RescateFondoOutEntity();
        String totalCuotasPartes = "1500";
        mockSimulacion.setTotalCuotasPartes(totalCuotasPartes);
        String importeRescateComision = "3000";
        mockSimulacion.setImporteRescateComision(importeRescateComision);
        String importeRescateNeto = "2000";
        mockSimulacion.setImporteRescateNeto(importeRescateNeto);
        Mockito.when(
                rescateDao.simulacionRescate(Matchers.any(Cliente.class), Matchers.any(RescateFondoInEntity.class)))
                .thenReturn(mockSimulacion);

        Respuesta<Object> respuestaError = new Respuesta<Object>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList()))
                .thenReturn(respuestaError);
        SimularRescateInDTO dtoRequest = new SimularRescateInDTO();
        String tipoCuenta = "CU";
        dtoRequest.setTipoCuenta(tipoCuenta);
        String importeNeto = "30500";
        dtoRequest.setImporteNeto(importeNeto);
        String numeroCuenta = "12345678";
        dtoRequest.setNumeroCuenta(numeroCuenta);
        String moneda = "$";
        dtoRequest.setMoneda(moneda);
        Cliente cliente = new Cliente();

        Respuesta<String> mockLegal = new Respuesta<String>();
        mockLegal.setRespuesta("Respuesta legal mockeada");
        mockLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(mockLegal);

        Respuesta<SimularRescateOutDTO> respuestaSimularRescateBO = rescateBO.simularRescateFondo(dtoRequest, cliente);

        Assert.assertNotNull(respuestaSimularRescateBO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaSimularRescateBO.getEstadoRespuesta());

    }
}
