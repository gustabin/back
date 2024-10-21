package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.CasuisticaAgendaDestinatariosImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CasuisticaAgendaDestinatarioTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class CasuisticaAgendaDestinatarioTest {

    /** The casuistica agenda destinatarios. */
    @InjectMocks
    private CasuisticaAgendaDestinatarios casuisticaAgendaDestinatarios = new CasuisticaAgendaDestinatariosImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Crear respuesta ok error test.
     */
    @Test
    public void crearRespuestaOkErrorTest() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());

        // Arrancamos testeando casos OK
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setCantCuentasPropias(4);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(4);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals(agendaDestinatarioDTO, res.getRespuesta());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        // Arrancamos testeando casos Error
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setCantCuentasPropias(1);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(4);
        Respuesta<AgendaDestinatarioDTO> resOk2 = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, resOk2.getEstadoRespuesta());

    }

    /**
     * Crear respuesta casuistica 1.
     */
    @Test
    public void crearRespuestaCasuistica1() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(true);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setCantCuentasPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios
                .crearRespuestaError(ErrorAgendaDestinatariosEnum.TIMEOUT);
        res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.TIMEOUT.getDescripcion(), res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 2.
     */
    @Test
    public void crearRespuestaCasuistica2() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setCantCuentasPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(0);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_SIN_AGENDADOS.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 3.
     */
    @Test
    public void crearRespuestaCasuistica3() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(true);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setCantCuentasPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(0);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 4.
     */
    @Test
    public void crearRespuestaCasuistica4() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasPropias(false);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(true);
        agendaDestinatarioDTO.setCantCuentasPropias(3);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.WARNING_SERVICIO_AGENDAMIENTO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 5.
     */
    @Test
    public void crearRespuestaCasuistica5() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(true);
        agendaDestinatarioDTO.setTieneErrorRellamado(true);
        agendaDestinatarioDTO.setCantCuentasPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(4);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.WARNING_ERROR_RELLAMADO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 6.
     */
    @Test
    public void crearRespuestaCasuistica6() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(true);
        agendaDestinatarioDTO.setTieneErrorRellamado(true);
        agendaDestinatarioDTO.setCantCuentasPropias(4);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(4);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.WARNING_ERROR_RELLAMADO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 7.
     */
    @Test
    public void crearRespuestaCasuistica7() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasPropias(true);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(4);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.WARNING_ERROR_RELLAMADO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 8.
     */
    @Test
    public void crearRespuestaCasuistica8() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasPropias(true);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(0);
        agendaDestinatarioDTO.setCantCuentasPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_SIN_AGENDADOS.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 9.
     */
    @Test
    public void crearRespuestaCasuistica9() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasPropias(true);
        agendaDestinatarioDTO.setTieneErrorRellamado(true);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(4);
        agendaDestinatarioDTO.setCantCuentasPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.WARNING_ERROR_RELLAMADO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 10.
     */
    @Test
    public void crearRespuestaCasuistica10() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setTieneErrorCuentasPropias(true);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(true);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(4);
        agendaDestinatarioDTO.setCantCuentasPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Crear respuesta casuistica 11.
     */
    @Test
    public void crearRespuestaCasuistica11() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorCuentasPropias(false);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setTieneErrorTimeOut(false);
        agendaDestinatarioDTO.setCantCuentasPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(false);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta casuistica 12.
     */
    @Test
    public void crearRespuestaCasuistica12() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
        agendaDestinatarioDTO.setTieneErrorCuentasPropias(false);
        agendaDestinatarioDTO.setTieneErrorRellamado(false);
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(false);
        agendaDestinatarioDTO.setTieneErrorTimeOut(true);
        agendaDestinatarioDTO.setCantCuentasPropias(3);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(0);
        agendaDestinatarioDTO.setTieneCuentaPropias(true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> res = casuisticaAgendaDestinatarios.crearRespuesta(agendaDestinatarioDTO);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.WARNING_SERVICIO_AGENDAMIENTO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Gets the getter setter DTO test.
     *
     * @return the getter setter DTO test
     */
    @Test
    public void getterSetterDTOTest() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setCantCuentasPropias(13);
        agendaDestinatarioDTO.setCantidadCuentasNoPropias(15);
        agendaDestinatarioDTO.setMensajeCabecera("mensaje cabecera");
        agendaDestinatarioDTO.setTieneErrorCuentasNoPropias(true);
        agendaDestinatarioDTO.setTieneErrorCuentasPropias(false);
        agendaDestinatarioDTO.setListaDestinatarios(new ArrayList<DestinatarioAgendaDTO>());
        agendaDestinatarioDTO.getListaDestinatarios().add(new DestinatarioAgendaDTO());
    }

    /**
     * Crear respuesta error time out.
     */
    @Test
    public void crearRespuestaErrorTimeOut() {
        AgendaDestinatarioDTO dto = new AgendaDestinatarioDTO();
        dto.setCantCuentasPropias(0);
        dto.setTieneErrorCuentasPropias(false);
        dto.setTieneErrorTimeOut(true);
        dto.setTieneErrorCuentasNoPropias(false);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> respuesta = casuisticaAgendaDestinatarios.crearRespuesta(dto);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(),
                ErrorAgendaDestinatariosEnum.TIMEOUT.getTag());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.TIMEOUT.getDescripcion());
    }

    /**
     * Crear respuesta error sin cuenta valida.
     */
    @Test
    public void crearRespuestaErrorSinCuentaValida() {
        AgendaDestinatarioDTO dto = new AgendaDestinatarioDTO();
        dto.setCantCuentasPropias(0);
        dto.setTieneErrorCuentasPropias(false);
        dto.setTieneCuentaPropias(false);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> respuesta = casuisticaAgendaDestinatarios.crearRespuesta(dto);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(),
                ErrorAgendaDestinatariosEnum.SINCUENTAVALIDA.getTag());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_SERVICIO.getDescripcion());
    }

    /**
     * Crear respuesta error servicio una cuenta.
     */
    @Test
    public void crearRespuestaErrorServicioUnaCuenta() {
        AgendaDestinatarioDTO dto = new AgendaDestinatarioDTO();
        dto.setCantCuentasPropias(1);
        dto.setTieneErrorCuentasPropias(false);
        dto.setTieneErrorCuentasNoPropias(true);
        dto.setTieneErrorRellamado(false);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<AgendaDestinatarioDTO> respuesta = casuisticaAgendaDestinatarios.crearRespuesta(dto);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(),
                ErrorAgendaDestinatariosEnum.UNACUENTAERRORAGENDAMIENTOS.getTag());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_SERVICIO.getDescripcion());
    }

    /**
     * Crear respuesta error configuracion cuenta invalida.
     */
    @Test
    public void crearRespuestaErrorConfiguracionCuentaInvalida() {
        DatosCliente datosCliente = new DatosCliente();
        datosCliente.setCodigoError(10000054);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ConfiguracionAltaDestinatarioDTO> respuesta = casuisticaAgendaDestinatarios
                .crearRespuestaConfiguracion(datosCliente);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(),
                ErrorAgendaDestinatariosEnum.CUENTAINEXISTENTE.getTag());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_CUENTA_INEXISTENTE.getDescripcion());
    }

}
