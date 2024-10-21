package ar.com.santanderrio.obp.servicios.prestamos.manager;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoManagerBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoPreaprobadoBO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.CuentaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoPreaprobadoMonoproductoInOutView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ResultadoAltaSimulacionPreaprobadoView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PrestamoPreaprobadoManagerImpl;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class PrestamoPreaprobadoMonoproductoManagerTest {

    @InjectMocks
    private PrestamoPreaprobadoManagerImpl prestamoPreaprobadoManager;

    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private SesionCliente sesionCliente;

    @Mock
    private MyaBO myaBO;

    @Mock
    private ClienteBO clienteBO;

    @Mock
    private EstadisticaManager estadisticaManager;

    @Mock
    private PrestamoManagerBO prestamoManagerBO;

    @Mock
    private PrestamoPreaprobadoBO prestamoPreaprobadoBO;

    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private AutentificacionManager autentificacionManager;

    @Mock
    private RsaManager rsaManager;

    @Mock
    private PrestamoService prestamoService;


    @Before
    public void init() throws BusinessException {
        PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoMonoproductoInOutView = new PrestamoPreaprobadoMonoproductoInOutView();
        prestamoPreaprobadoMonoproductoInOutView.setImporteSeleccionado("1000");
        prestamoPreaprobadoMonoproductoInOutView.setImpuesto("1000");
        prestamoPreaprobadoMonoproductoInOutView.setImporteNeto("1000");
        prestamoPreaprobadoMonoproductoInOutView.setGastosOtorgamiento("1000");
        prestamoPreaprobadoMonoproductoInOutView.setCuotaConstante("1000");
        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("0000");
        cuenta.setNroCuentaProducto("0123456789");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        prestamoPreaprobadoMonoproductoInOutView.setCuenta(cuenta);
        prestamoPreaprobadoMonoproductoInOutView.setNroCuotas("10");
        prestamoPreaprobadoMonoproductoInOutView.setFechaSeleccionada("2020-01-01");
        prestamoPreaprobadoMonoproductoInOutView.setMotivoSeleccionado(new DestinoPrestamoSeleccionView());
        prestamoPreaprobadoMonoproductoInOutView.setTipoTasa("F");
        prestamoPreaprobadoMonoproductoInOutView.setTna("1");
        prestamoPreaprobadoMonoproductoInOutView.setTea("1");
        CuentaView cuentaView = new CuentaView();
        cuentaView.setNumero("123-456");
        prestamoPreaprobadoMonoproductoInOutView.setCuentaSeleccionada(cuentaView);
        PrestamoPreaprobadoMonoproductoInEntity prestamoPreaprobadoMonoproductoInEntity = new PrestamoPreaprobadoMonoproductoInEntity("1", "2", "", "", "", "", new BigDecimal(0), "", "", "", "", "", "", "", "", "", new BigDecimal(0), "", "", "", "");
        Cliente cliente = new Cliente();
        cliente.setNup("12345678");
        cliente.setCuentas(Collections.singletonList(cuenta));
        cliente.setFechaNacimiento("19900101");

        Respuesta<List<BigDecimal>> antiguedades = new Respuesta<List<BigDecimal>>();
        antiguedades.setEstadoRespuesta(EstadoRespuesta.OK);


        PrestamoPreaprobadoMonoproductoOutEntity prestamoPreaprobadoMonoproductoOut = new PrestamoPreaprobadoMonoproductoOutEntity();
        prestamoPreaprobadoMonoproductoOut.setImpconc("10");
        prestamoPreaprobadoMonoproductoOut.setImpcarg("10");
        prestamoPreaprobadoMonoproductoOut.setTotcomi("10");
        prestamoPreaprobadoMonoproductoOut.setTotivat("10");
        prestamoPreaprobadoMonoproductoOut.setTotrimp("10");
        prestamoPreaprobadoMonoproductoOut.setCuopur("10");
        prestamoPreaprobadoMonoproductoOut.setTna("10");
        prestamoPreaprobadoMonoproductoOut.setTea("10");
        prestamoPreaprobadoMonoproductoOut.setFeprivt("2020-01-01");
        prestamoPreaprobadoMonoproductoOut.setCuenta("0123456789");
        Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> respuestaEntity = new Respuesta<PrestamoPreaprobadoMonoproductoOutEntity>();
        respuestaEntity.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaEntity.setRespuesta(prestamoPreaprobadoMonoproductoOut);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("");

        Respuesta<Object> respuestaExitosa = new Respuesta<Object>();
        respuestaExitosa.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<AutentificacionDTO> respuestaAutentificacionExitosa = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacionExitosa.setEstadoRespuesta(EstadoRespuesta.OK);

        prestamoPreaprobadoMonoproductoInOutView.setRequestSimulacion(prestamoPreaprobadoMonoproductoInEntity);
        when(sesionParametros.getPrestamoPreaprobadoMonoproducto()).thenReturn(prestamoPreaprobadoMonoproductoInOutView);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionCliente.getTieneTokenRSA()).thenReturn(true);
        when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(anyLong())).thenReturn(antiguedades);
        when(prestamoManagerBO.isValidToken(anyString())).thenReturn(true);
        when(prestamoPreaprobadoBO.altaSimulacionPrestamoPreabrobadoMonoproducto(Matchers.<PrestamoPreaprobadoMonoproductoInEntity>anyObject(), Matchers.<Cliente>anyObject())).thenReturn(respuestaEntity);
        when(mensajeBO.obtenerMensajePorCodigo(anyString())).thenReturn(mensaje);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.<AutentificacionDTO>anyObject(), anyInt())).thenReturn(respuestaExitosa);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.<AutentificacionDTO>anyObject())).thenReturn(respuestaAutentificacionExitosa);
        when(prestamoService.validarHorario()).thenReturn(true);


        ReflectionTestUtils.setField(prestamoPreaprobadoManager, "valorDesafioPrestamo", "123456");
        ReflectionTestUtils.setField(prestamoPreaprobadoManager, "respuestaFactory", respuestaFactory);
    }


    @Test
    public void confirmarCompleto() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.confirmarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void confirmarErrorTokenVacio() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();
        when(prestamoManagerBO.isValidToken(anyString())).thenReturn(false);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.confirmarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.SOLICITUD_TOKEN_PRESTAMO.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void confirmarErrorTokenIvalido() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();
        resultado.setToken("TOKEN_INVALIDO");
        when(prestamoManagerBO.isValidToken(anyString())).thenReturn(false);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.confirmarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.TOKEN_PRESTAMO_ERROR.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void confirmarErrorAltaPrestamo() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> respuestaEntity = new Respuesta<PrestamoPreaprobadoMonoproductoOutEntity>();
        respuestaEntity.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaEntity.setRespuesta(null);
        when(prestamoPreaprobadoBO.altaSimulacionPrestamoPreabrobadoMonoproducto(Matchers.<PrestamoPreaprobadoMonoproductoInEntity>anyObject(), Matchers.<Cliente>anyObject())).thenReturn(respuestaEntity);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.confirmarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.ERROR_ALTA_PRESTAMO_PREAPROBADO.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void confirmarErrorAltaGenerico() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        when(sesionParametros.getPrestamoPreaprobadoMonoproducto()).thenReturn(null);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.confirmarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.ERROR_ALTA_PRESTAMO_PREAPROBADO.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void encolarCompleto() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void encolarCompletoCargaAutentificacion() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        Respuesta<Object> respuestaError = new Respuesta<Object>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.<AutentificacionDTO>anyObject(), anyInt())).thenReturn(respuestaError);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void encolarErrorSinToken() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        when(sesionCliente.getTieneTokenRSA()).thenReturn(false);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.RSA_OFFLINE.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void encolarErrorSinMetodoDesafio() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        Respuesta<AutentificacionDTO> respuestaAutentificacionError = new Respuesta<AutentificacionDTO>();
        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();
        itemRespuesta.setTipoError(TipoError.SIN_METODO_DESAFIO.getDescripcion());
        respuestaAutentificacionError.add(itemRespuesta);
        respuestaAutentificacionError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(autentificacionManager.ejecutarValidacionRSA(Matchers.<AutentificacionDTO>anyObject())).thenReturn(respuestaAutentificacionError);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.SIN_METODO_DESAFIO.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void encolarErrorBloqueoUsuario() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        Respuesta<AutentificacionDTO> respuestaAutentificacionError = new Respuesta<AutentificacionDTO>();
        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();
        itemRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        respuestaAutentificacionError.add(itemRespuesta);
        respuestaAutentificacionError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setBloquearUsuario(true);
        respuestaAutentificacionError.setRespuesta(autentificacionDTO);

        when(autentificacionManager.ejecutarValidacionRSA(Matchers.<AutentificacionDTO>anyObject())).thenReturn(respuestaAutentificacionError);


        Respuesta<RsaUpdateUserResponseData> respuestaError = new Respuesta<RsaUpdateUserResponseData>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.OK);
        when(rsaManager.updateUser(Matchers.<RsaUpdateUserRequestData>anyObject())).thenReturn(respuestaError);


        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.RSA_BLOQUEAR_USUARIO.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void encolarErrorBloqueoUsuarioFallido() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        Respuesta<AutentificacionDTO> respuestaAutentificacionError = new Respuesta<AutentificacionDTO>();
        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();
        itemRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        respuestaAutentificacionError.add(itemRespuesta);
        respuestaAutentificacionError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setBloquearUsuario(true);
        respuestaAutentificacionError.setRespuesta(autentificacionDTO);

        when(autentificacionManager.ejecutarValidacionRSA(Matchers.<AutentificacionDTO>anyObject())).thenReturn(respuestaAutentificacionError);


        Respuesta<RsaUpdateUserResponseData> respuestaError = new Respuesta<RsaUpdateUserResponseData>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(rsaManager.updateUser(Matchers.<RsaUpdateUserRequestData>anyObject())).thenReturn(respuestaError);


        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.RSA_OFFLINE.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void encolarErrorRSAGenerico() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        Respuesta<AutentificacionDTO> respuestaAutentificacionError = new Respuesta<AutentificacionDTO>();
        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();
        itemRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        respuestaAutentificacionError.add(itemRespuesta);
        respuestaAutentificacionError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setBloquearUsuario(false);
        respuestaAutentificacionError.setRespuesta(autentificacionDTO);

        when(autentificacionManager.ejecutarValidacionRSA(Matchers.<AutentificacionDTO>anyObject())).thenReturn(respuestaAutentificacionError);


        Respuesta<RsaUpdateUserResponseData> respuestaError = new Respuesta<RsaUpdateUserResponseData>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.OK);
        when(rsaManager.updateUser(Matchers.<RsaUpdateUserRequestData>anyObject())).thenReturn(respuestaError);


        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void encolarErrorRSAWarning() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        Respuesta<AutentificacionDTO> respuestaAutentificacionError = new Respuesta<AutentificacionDTO>();
        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();
        itemRespuesta.setTipoError(TipoError.WARNING.getDescripcion());
        respuestaAutentificacionError.add(itemRespuesta);
        respuestaAutentificacionError.setEstadoRespuesta(EstadoRespuesta.WARNING);

        when(autentificacionManager.ejecutarValidacionRSA(Matchers.<AutentificacionDTO>anyObject())).thenReturn(respuestaAutentificacionError);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.WARNING.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }


    @Test
    public void encolarErrorAltaPrestamo() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();

        Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> respuestaEntity = new Respuesta<PrestamoPreaprobadoMonoproductoOutEntity>();
        respuestaEntity.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaEntity.setRespuesta(null);
        when(prestamoPreaprobadoBO.altaSimulacionPrestamoPreabrobadoMonoproducto(Matchers.<PrestamoPreaprobadoMonoproductoInEntity>anyObject(), Matchers.<Cliente>anyObject())).thenReturn(respuestaEntity);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.ERROR_ENCOLAR_PRESTAMO_PREAPROBADO.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void encolarErrorAltaGenerico() {

        //When
        ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder().build();


        Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> respuestaEntity = new Respuesta<PrestamoPreaprobadoMonoproductoOutEntity>();
        respuestaEntity.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(prestamoPreaprobadoBO.altaSimulacionPrestamoPreabrobadoMonoproducto(Matchers.<PrestamoPreaprobadoMonoproductoInEntity>anyObject(), Matchers.<Cliente>anyObject())).thenReturn(respuestaEntity);

        //Then
        Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuesta = prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(resultado);

        //Expected
        Assert.assertEquals(TipoError.ERROR_ENCOLAR_PRESTAMO_PREAPROBADO.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

}
