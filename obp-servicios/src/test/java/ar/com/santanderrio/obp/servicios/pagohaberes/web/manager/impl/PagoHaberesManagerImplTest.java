package ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.impl;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.api.customers.CustomersApi;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.ScoringApi;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.feature.AumentoLimiteTransferenciaInOutViewFeature;
import ar.com.santanderrio.obp.servicios.biocatch.BiocatchManager;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesPagoSimpleMultipleEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.fixture.PagoHaberesFixture;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;
import java.lang.reflect.Method;


@RunWith(MockitoJUnitRunner.class)
public class PagoHaberesManagerImplTest{
    @Mock
    private PagoHaberesBO pagoHaberesBO;
    @Mock
    private SesionParametros sesionParametros;
    @Mock
    private SesionCliente sesionCliente;
    @InjectMocks
    private PagoHaberesManagerImpl pagoHaberesManagerImpl;
    @Mock
    private RespuestaFactory respuestaFactory ;
    @Mock
    CuentaManager cuentaManager;
    @Mock
    private EstadisticaManager estadisticaManager;

    @Mock
    private ClienteBO clienteBO;

    @Mock
    private AutentificacionManager autentificacionManager;

    @Mock
    private LegalDAO legalDao;

    @Mock
    private RsaManager rsaManager;

    @Mock
    private BiocatchManager mockBiocatchManager;

    @Mock
    private ScoringApi scoringApi;

    @Mock
    private CustomersApi customersApi;

    @Mock
    private Logger logger;

    @Mock
    private DesafioOperacionRSA<ComprobantePagoHaberesPagoSimpleMultipleEntity> desafioOperacionRSAMultiple;

    @Mock
    private MensajeBO mensajeBO;

    @Test
    public void givenCBUWhenValidaImporteThenValidacionesPagoPorCBUReturnEstadoRespuestaOK() throws NoSuchMethodException {
        //se utilizo reflection porque no se pudo mockear un método privado
        Method privateResetearDesafioEnCurso = SesionParametros.class.getMethod("resetearDesafioEnCurso", null);
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(pagoHaberesBO.validarImporte(Mockito.any(Cliente.class),Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(PagoHaberesFixture.getRespuestaDatosDestinatarioViewCBU());
        Mockito.when(pagoHaberesBO.validarCBU(Mockito.any(Cliente.class), Mockito.any(ValidacionesPagoPorCBUView.class))).thenReturn(PagoHaberesFixture.getRespuestaDatosDestinatarioViewCBU());
        Respuesta<DatosDestinatarioView> rta = pagoHaberesManagerImpl.validacionesPagoPorCBU(PagoHaberesFixture.getValidacionesPagoPorCBUViewOK());
        Mockito.verify(sesionCliente, Mockito.times(2)).getCliente();
        Mockito.verify(pagoHaberesBO, Mockito.times(1)).validarImporte(Mockito.any(Cliente.class),Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(pagoHaberesBO, Mockito.times(1)).validarCBU(Mockito.any(Cliente.class), Mockito.any(ValidacionesPagoPorCBUView.class));
        Assert.assertEquals(EstadoRespuesta.OK, rta.getEstadoRespuesta());
    }

    @Test
    public void givenCBUWhenValidaImporteThenValidacionesPagoPorCBUReturnEstadoRespuestaError() throws NoSuchMethodException {
        //se utilizo reflection porque no se pudo mockear un método privado
        Method privateResetearDesafioEnCurso = SesionParametros.class.getMethod("resetearDesafioEnCurso", null);
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(pagoHaberesBO.validarImporte(Mockito.any(Cliente.class),Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(PagoHaberesFixture.getRespuestaDatosDestinatarioViewCBU2());
        Respuesta<DatosDestinatarioView> rta = pagoHaberesManagerImpl.validacionesPagoPorCBU(PagoHaberesFixture.getValidacionesPagoPorCBUViewOK());
        Mockito.verify(sesionCliente, Mockito.times(1)).getCliente();
        Mockito.verify(pagoHaberesBO, Mockito.times(1)).validarImporte(Mockito.any(Cliente.class),Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Assert.assertEquals(EstadoRespuesta.ERROR, rta.getEstadoRespuesta());
    }

    @Test
    public void givenCVUWhenValidaImporteThenValidacionesPagoPorCBUReturnEstadoRespuestaError() throws NoSuchMethodException {
        //se utilizo reflection porque no se pudo mockear un método privado
        Method privateResetearDesafioEnCurso = SesionParametros.class.getMethod("resetearDesafioEnCurso", null);
        Mockito.when(pagoHaberesBO.esTipoDeClaveCVU(Mockito.<ValidacionesPagoPorCBUView>any(ValidacionesPagoPorCBUView.class))).thenReturn(Boolean.TRUE);
        Respuesta<DatosDestinatarioView> resValidacion = new Respuesta<DatosDestinatarioView>();
        Mockito.doReturn(resValidacion).when(respuestaFactory).validate(Matchers.any(DatosDestinatarioView.class));
        Respuesta res = new Respuesta();
        res.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.eq(TipoError.ERROR_USUARIO_CBU_INVALIDO),
        Matchers.eq(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_TIPO_CUENTA_CVU_DESHABILITADO))).thenReturn(res);
        Respuesta<DatosDestinatarioView> rta = pagoHaberesManagerImpl.validacionesPagoPorCBU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCVU());
        Assert.assertEquals(EstadoRespuesta.ERROR, rta.getEstadoRespuesta());
    }

    @Test
    public void givenCBUSantanderWhenValidaImporteThenValidacionesPagoPorCBUReturnEstadoRespuestaError() throws NoSuchMethodException {
        Mockito.when(pagoHaberesBO.esCBUSantander(Mockito.<ValidacionesPagoPorCBUView>any(ValidacionesPagoPorCBUView.class))).thenReturn(Boolean.TRUE);
        Respuesta<DatosDestinatarioView> resValidacion = new Respuesta<DatosDestinatarioView>();
        Mockito.doReturn(resValidacion).when(respuestaFactory).validate(Matchers.any(DatosDestinatarioView.class));
        Respuesta res = new Respuesta();
        res.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.eq(TipoError.ERROR_USUARIO_CBU_INVALIDO),
                Matchers.eq(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_OTROS_BANCOS_CBU_SANTANDER))).thenReturn(res);
        Respuesta<DatosDestinatarioView> rta = pagoHaberesManagerImpl.validacionesPagoPorCBU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCBU());
        Assert.assertEquals(EstadoRespuesta.ERROR, rta.getEstadoRespuesta());
    }

    @Test
    public void given2PagoInformadoViewThenObtenerPagoHaberesReturn2PagoInformadoViewFirsElementIsChallengeEqualsTrue(){
        Mockito.when(pagoHaberesBO.obtenerConsultaAgendamiento7x24((Cliente) Mockito.any())).thenReturn(PagoHaberesFixture.getRespuestaPagoHaberesEntityOK());
        Mockito.when(cuentaManager.obtenerCuentaById(Mockito.anyString())).thenReturn(PagoHaberesFixture.getAbstractCuenta());
        Mockito.when(estadisticaManager.add(Mockito.any(Estadistica.class))).thenReturn(Boolean.TRUE);
        Respuesta<PagoHaberesView> rta = pagoHaberesManagerImpl.obtenerPagoHaberes();
        Mockito.verify(pagoHaberesBO, Mockito.times(1)).obtenerConsultaAgendamiento7x24((Cliente) Mockito.any());
        Mockito.verify(cuentaManager, Mockito.times(4)).obtenerCuentaById(Mockito.anyString());
        Mockito.verify(estadisticaManager, Mockito.times(1)).add("13159", "1");
        Assert.assertEquals( EstadoRespuesta.OK, rta.getEstadoRespuesta());
        Assert.assertEquals(Boolean.TRUE, rta.getRespuesta().getPagoHaberesEmpleadosView().get(0).getChallenge());
        Assert.assertEquals(Boolean.TRUE, rta.getRespuesta().getPagoHaberesEmpleadosView().get(1).getChallenge());
    }

    @Test
    public void given1PagoInformadoViewThenObtenerPagoHaberesReturnPagoInformadoViewIsChallengeEqualsTrue(){
        Mockito.when(pagoHaberesBO.obtenerConsultaAgendamiento7x24((Cliente) Mockito.any())).thenReturn(PagoHaberesFixture.getRespuestaPagoHaberesEntityOK2());
        Mockito.when(cuentaManager.obtenerCuentaById(Mockito.anyString())).thenReturn(PagoHaberesFixture.getAbstractCuenta());
        Mockito.when(estadisticaManager.add(Mockito.any(Estadistica.class))).thenReturn(Boolean.TRUE);
        Respuesta<PagoHaberesView> rta = pagoHaberesManagerImpl.obtenerPagoHaberes();
        Mockito.verify(pagoHaberesBO, Mockito.times(1)).obtenerConsultaAgendamiento7x24((Cliente) Mockito.any());
        Mockito.verify(cuentaManager, Mockito.times(2)).obtenerCuentaById(Mockito.anyString());
        Mockito.verify(estadisticaManager, Mockito.times(1)).add("13159", "1");
        Assert.assertEquals(EstadoRespuesta.OK, rta.getEstadoRespuesta());
        Assert.assertEquals(Boolean.TRUE, rta.getRespuesta().getPagoHaberesEmpleadosView().get(0).getChallenge());
    }

    @Test
    public void given3PagoInformadoViewThenObtenerPagoHaberesReturnPagoInformadoViewIsChallengeEqualsTrue(){
        Mockito.when(pagoHaberesBO.obtenerConsultaAgendamiento7x24((Cliente) Mockito.any())).thenReturn(PagoHaberesFixture.getRespuestaPagoHaberesEntityOK3());
        Mockito.when(cuentaManager.obtenerCuentaById(Mockito.anyString())).thenReturn(PagoHaberesFixture.getAbstractCuenta());
        Mockito.when(estadisticaManager.add(Mockito.any(Estadistica.class))).thenReturn(Boolean.TRUE);
        Respuesta<PagoHaberesView> rta = pagoHaberesManagerImpl.obtenerPagoHaberes();
        Mockito.verify(pagoHaberesBO, Mockito.times(1)).obtenerConsultaAgendamiento7x24((Cliente) Mockito.any());
        Mockito.verify(cuentaManager, Mockito.times(6)).obtenerCuentaById(Mockito.anyString());
        Mockito.verify(estadisticaManager, Mockito.times(1)).add("13159", "1");
        Assert.assertEquals(EstadoRespuesta.OK, rta.getEstadoRespuesta());
        Assert.assertEquals(Boolean.TRUE, rta.getRespuesta().getPagoHaberesEmpleadosView().get(0).getChallenge());
        Assert.assertEquals(Boolean.TRUE, rta.getRespuesta().getPagoHaberesEmpleadosView().get(1).getChallenge());
        Assert.assertEquals(Boolean.TRUE, rta.getRespuesta().getPagoHaberesEmpleadosView().get(1).getChallenge());
    }


    @Test
    public void whenObtenerPagoHaberesFailReturnError(){
        Mockito.when(pagoHaberesBO.obtenerConsultaAgendamiento7x24((Cliente) Mockito.any())).thenReturn(PagoHaberesFixture.getRespuestaPagoHaberesEntityError());
        Mockito.when(cuentaManager.obtenerCuentaById(Mockito.anyString())).thenReturn(PagoHaberesFixture.getAbstractCuenta());
        Mockito.when(estadisticaManager.add(Mockito.any(Estadistica.class))).thenReturn(Boolean.TRUE);
        Respuesta<PagoHaberesView> rta = pagoHaberesManagerImpl.obtenerPagoHaberes();
        Mockito.verify(pagoHaberesBO, Mockito.times(1)).obtenerConsultaAgendamiento7x24((Cliente) Mockito.any());
        Mockito.verify(cuentaManager, Mockito.times(0)).obtenerCuentaById(Mockito.anyString());
        Mockito.verify(estadisticaManager, Mockito.times(0)).add("13159", "1");
        Assert.assertEquals(EstadoRespuesta.ERROR, rta.getEstadoRespuesta());
    }

    @Test
    public void whenRealizarPagoDeHaberesThenIsChallengeFirstElementIsTrueAndThenEstadoRespuestaIsOK() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListOK()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOOK());
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(PagoHaberesFixture.getDesafioEnCursoNull());
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(rsaManager.analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any())).thenReturn(PagoHaberesFixture.getRespuestaActionCodeAllow());
        Mockito.when(sesionParametros.getPagoHaberesInformadosView()).thenReturn(PagoHaberesFixture.getPagosInformadosViewList());
        Mockito.when(pagoHaberesBO.realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold()), Mockito
                        .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(PagoHaberesFixture.getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity2());

        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntity());

        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(clienteBO, Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionParametros, Mockito.times(1)).getDesafioEnCurso();
        Mockito.verify(sesionCliente, Mockito.times(13)).getCliente();
        Mockito.verify(rsaManager, Mockito.times(2)).analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any());
        Mockito.verify(sesionParametros, Mockito.times(1)).getPagoHaberesInformadosView();
        Mockito.verify(pagoHaberesBO, Mockito.times(3)).realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold()), Mockito
                .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void whenRealizarPagoDeHaberesThenIsChallengeAndContPermiteReintentoAndThenEstadoRespuestaIsError() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListOK()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOOK());
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(PagoHaberesFixture.getDesafioEnCursoNull());
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(rsaManager.analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any())).thenReturn(PagoHaberesFixture.getRespuestaActionCodeAllow());
        Mockito.when(sesionParametros.getPagoHaberesInformadosView()).thenReturn(PagoHaberesFixture.getPagosInformadosViewList());
        Mockito.when(pagoHaberesBO.realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold()), Mockito
                        .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(PagoHaberesFixture.getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity2());
        Mockito.when(pagoHaberesBO.realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold2()), Mockito
                        .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(PagoHaberesFixture.getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity1Error());
        Mockito.when(sesionParametros.getContador()).thenReturn(PagoHaberesFixture.getContadorDeIntentos());

        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.eq(TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_PERMITE_REINTENTAR),
                Matchers.eq(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_PAGO_MULTIPLE))).thenReturn(PagoHaberesFixture.getEstadoRespuestaError());

        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntity());

        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(clienteBO, Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionParametros, Mockito.times(1)).getDesafioEnCurso();
        Mockito.verify(sesionCliente, Mockito.times(13)).getCliente();
        Mockito.verify(rsaManager, Mockito.times(2)).analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any());
        Mockito.verify(sesionParametros, Mockito.times(1)).getPagoHaberesInformadosView();
        Mockito.verify(pagoHaberesBO, Mockito.times(3)).realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold()), Mockito
                .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(sesionParametros, Mockito.times(1)).getContador();
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void whenRealizarPagoDeHaberesThenIsChallengeAndContNoPermiteReintentoAndThenEstadoRespuestaIsError() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListOK()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOOK());
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(PagoHaberesFixture.getDesafioEnCursoNull());
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(rsaManager.analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any())).thenReturn(PagoHaberesFixture.getRespuestaActionCodeAllow());
        Mockito.when(sesionParametros.getPagoHaberesInformadosView()).thenReturn(PagoHaberesFixture.getPagosInformadosViewList());
        Mockito.when(pagoHaberesBO.realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold()), Mockito
                        .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(PagoHaberesFixture.getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity2());
        Mockito.when(pagoHaberesBO.realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold2()), Mockito
                        .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(PagoHaberesFixture.getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity1Error());
        Mockito.when(sesionParametros.getContador()).thenReturn(PagoHaberesFixture.getContadorNoPermiteReintentos());

        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.eq(TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR),
                Matchers.eq(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_PAGO_MULTIPLE))).thenReturn(PagoHaberesFixture.getEstadoRespuestaError());

        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntity());

        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(clienteBO, Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionParametros, Mockito.times(1)).getDesafioEnCurso();
        Mockito.verify(sesionCliente, Mockito.times(13)).getCliente();
        Mockito.verify(rsaManager, Mockito.times(2)).analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any());
        Mockito.verify(sesionParametros, Mockito.times(1)).getPagoHaberesInformadosView();
        Mockito.verify(pagoHaberesBO, Mockito.times(3)).realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold()), Mockito
                .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(sesionParametros, Mockito.times(1)).getContador();
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void whenRealizarPagoDeHaberesThenIsChallengeFirstElementIsFalseAndThenEstadoRespuestaIsWarningAndModoEjecucionFalse() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListWarning()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOWarning());
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(null);
        Mockito.when(desafioOperacionRSAMultiple.validarOperacionRSA(Mockito.any(ComprobantePagoHaberesPagoSimpleMultipleEntity.class), Mockito.anyInt(),Mockito.any(AutentificacionCodEstDTO.class))).
                thenReturn(PagoHaberesFixture.getRespuestaComprobantePagoHaberesPagoSimpleMultipleEntityWarningMEFalse());
        Mockito.when(rsaManager.analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any())).thenReturn(PagoHaberesFixture.getRespuestaActionCodeAllow());
        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntityModoEjecucionFalse());


        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionCliente, Mockito.times(10)).getCliente();
        Mockito.verify(autentificacionManager,Mockito.times(1)).analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(rsaManager,Mockito.times(2)).analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any());
        Mockito.verify(sesionParametros,Mockito.times(1)).getDesafioEnCurso();
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }


    @Test
    public void whenRealizarPagoDeHaberesThenIsChallengeFirstElementIsTrueAndThenEstadoRespuestaIsErrorAndModoEjecucionTRUE() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListError()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOError());
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(PagoHaberesFixture.getDesafioEnCursoNull());
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntityMETrue());

        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(clienteBO, Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Mockito.verify(autentificacionManager, Mockito.times(1)).analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionParametros, Mockito.times(1)).getDesafioEnCurso();
        Mockito.verify(sesionCliente, Mockito.times(6)).getCliente();

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void whenRealizarPagoDeHaberesThenIsChallengeFirstElementIsTrueAndThenEstadoRespuestaIsOKAndModoEjecucionFalse() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListOK()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOOK());
        Mockito.when(sesionParametros.getPagoHaberesInformadosView()).thenReturn(PagoHaberesFixture.getPagosInformadosViewList());
        Mockito.when(pagoHaberesBO.realizarPagoSimple(Mockito.eq(PagoHaberesFixture.getClienteGold()), Mockito
                        .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(PagoHaberesFixture.getRespuestaDatosEmpleadoPagoHaberesSimpleMultipleEntity2());
        Mockito.when(legalDao.obtenerLegal(Mockito.anyString())).thenReturn("1233366");
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(PagoHaberesFixture.getDesafioEnCursoNull());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(PagoHaberesFixture.getMensaje());
        Mockito.when(rsaManager.analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any())).thenReturn(PagoHaberesFixture.getRespuestaActionCodeAllow());

        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntityMETrue());

        Mockito.verify(sesionCliente, Mockito.times(13)).getCliente();
        Mockito.verify(sesionParametros, Mockito.times(1)).getPagoHaberesInformadosView();
        Mockito.verify(pagoHaberesBO, Mockito.times(3)).realizarPagoSimple(Mockito.any(Cliente.class), Mockito
                .any(PagoInformadoView.class), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(legalDao, Mockito.times(1)).obtenerLegal(Mockito.anyString());

        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(clienteBO, Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Mockito.verify(autentificacionManager, Mockito.times(1)).analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionParametros, Mockito.times(1)).getDesafioEnCurso();
        Mockito.verify(rsaManager, Mockito.times(2)).analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any());
        Mockito.verify(mensajeBO, Mockito.times(3)).obtenerMensajePorCodigo(Mockito.anyString());


        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }


    @Test
    public void whenRealizarPagoDeHaberesThenEstadoRespuestaIsWARNING() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListWarning()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOWarning());
        Mockito.when(rsaManager.analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any())).thenReturn(PagoHaberesFixture.getRespuestaActionCode());

        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntity());

        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionCliente, Mockito.times(10)).getCliente();
        Mockito.verify(autentificacionManager,Mockito.times(1)).analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(rsaManager,Mockito.times(2)).analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any());
        Mockito.verify(clienteBO,Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    @Test
    public void whenRealizarPagoDeHaberesThenEstadoRespuestaIsWARNINGAndDesafioIsNotNull() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(PagoHaberesFixture.getDesafioEnCursoNull());
       Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListOK()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOWarning());
        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());
        Mockito.when(rsaManager.analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any())).thenReturn(PagoHaberesFixture.getRespuestaActionCodeAllow());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntity());

        Mockito.verify(sesionParametros,Mockito.times(1)).getDesafioEnCurso();
        Mockito.verify(sesionCliente,Mockito.times(10)).getCliente();
        Mockito.verify(clienteBO,Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Mockito.verify(autentificacionManager,Mockito.times(1)).analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(rsaManager,Mockito.times(2)).analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any());
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    @Test
    public void whenRealizarPagoDeHaberesThenEstadoRespuestaIsError() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListOK()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOError());

        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntity());

        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionCliente, Mockito.times(6)).getCliente();
        Mockito.verify(autentificacionManager,Mockito.times(1)).analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(clienteBO,Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void whenRealizarPagoDeHaberesThenEstadoRespuestaIsWARNINGAndDesafioNotNull() throws NoSuchFieldException, IllegalAccessException, BusinessException, DAOException {
        ReflectionTestUtils.setField(pagoHaberesManagerImpl, "valorDesafioPagoHaberes", 1);
        Mockito.when(autentificacionManager.obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(sesionCliente.getCliente()).thenReturn(PagoHaberesFixture.getClienteGold());
        Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class))).thenReturn((PagoHaberesFixture.getBigDecimalRespuestaListWarning()));
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class))).thenReturn(PagoHaberesFixture.getRespuestaAutentificacionDTOWarning());
        Mockito.when(rsaManager.analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any())).thenReturn(PagoHaberesFixture.getRespuestaActionCode());
        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(PagoHaberesFixture.getRsaGenericRequestDataNotNull());
        Mockito.when(sesionParametros.getRsaGenericResponseData()).thenReturn(PagoHaberesFixture.getRsaGenericResponseDataNotNull());
        Mockito.when(customersApi.getCustomerById(sesionCliente.getCliente().getNup())).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta =  pagoHaberesManagerImpl.realizarPagoHaberes(PagoHaberesFixture.getComprobantePagoHaberesPagoSimpleMultipleEntityDesafioNotNull());

        Mockito.verify(autentificacionManager, Mockito.times(1)).obtenerDesafioHabilitadoOperacion(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(sesionCliente, Mockito.times(8)).getCliente();
        Mockito.verify(autentificacionManager,Mockito.times(1)).analizarRSAyObtenerAutenticacionValida(Mockito.any(AutentificacionDTO.class));
        Mockito.verify(rsaManager,Mockito.times(2)).analizar(Mockito.any(RsaDTO.class), Mockito.<TipoDesafioEnum>any());
        Mockito.verify(clienteBO,Mockito.times(1)).obtenerAntiguedadDiasUltCambioClaveToken(Mockito.any(Long.class));
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }


}
