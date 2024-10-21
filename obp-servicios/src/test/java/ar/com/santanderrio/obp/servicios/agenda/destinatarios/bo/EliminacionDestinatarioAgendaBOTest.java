package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.EliminacionDestinatarioAgendaBOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class EliminacionDestinatarioAgendaBOTest {

    /** The agenda destinatario DAO. */
    @Mock
    private AgendaDestinatarioDAO agendaDestinatarioDAO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The alta destinatario BO. */
    @InjectMocks
    private EliminacionDestinatarioAgendaBO eliminacionDestinatarioAgendaBO = new EliminacionDestinatarioAgendaBOImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Test
    public void eliminacionOKTest() throws DAOException {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("00000000");
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(outEntity);
        Respuesta<Void> respuestaEsperada = respuestaFactory.crearRespuestaOk(null);
        Respuesta<Void> respuestaBO = eliminacionDestinatarioAgendaBO.eliminacionDestinatario(crearDestinatarioEntity(),
                "123.123.123", new Cliente());
        Assert.assertEquals(respuestaEsperada, respuestaBO);
    }

    @Test
    public void eliminacionErrorGeneralDAOException() throws DAOException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenThrow(new DAOException());
        ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_GENERAL_ELIMINACION_DESTINATARIO;
        Respuesta<Void> respuestaEsperada = respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(),
                error.getCodigoMensaje());
        Respuesta<Void> respuestaBO = eliminacionDestinatarioAgendaBO.eliminacionDestinatario(crearDestinatarioEntity(),
                "123.123.123", new Cliente());
        Assert.assertEquals(respuestaEsperada, respuestaBO);
    }

    @Test
    public void eliminacionErrorNoEspecifico() throws DAOException {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("11111111");
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(outEntity);
        ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_GENERAL_ELIMINACION_DESTINATARIO;
        Respuesta<Void> respuestaEsperada = respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(),
                error.getCodigoMensaje());
        Respuesta<Void> respuestaBO = eliminacionDestinatarioAgendaBO.eliminacionDestinatario(crearDestinatarioEntity(),
                "123.123.123.", new Cliente());
        Assert.assertEquals(respuestaEsperada, respuestaBO);
    }

    @Test
    public void eliminacionErrorDestinatarioYaEliminadoAnteriormente() throws DAOException {

        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("10014012");
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(outEntity);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("El destinatario ya se encuentra eliminado");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_BAJA_DESTINATARIO_YA_ELIMINADO;
        Respuesta<Void> respuestaEsperada = respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(),
                error.getCodigoMensaje());
        Respuesta<Void> respuestaBO = eliminacionDestinatarioAgendaBO.eliminacionDestinatario(crearDestinatarioEntity(),
                "123.123.123.", new Cliente());
        Assert.assertEquals(respuestaEsperada, respuestaBO);
    }

    private DestinatarioEntity crearDestinatarioEntity() {
        DestinatarioEntity destinatario = new DestinatarioEntity();
        destinatario.setTipoAgendaOcurrencia("RIO");
        destinatario.setTipoCuentaDestinatario("03");
        destinatario.setSucursalCuentaDestinatario("0123");
        destinatario.setNumeroCuentaDestinatario("1234567");
        destinatario.setCbuDestinatario("     ");
        destinatario.setBancoDestinatario(BancoEnum.SANTANDER_RIO.getDescripcion());
        destinatario.setTipoDocumentoDestinatario("T");
        destinatario.setDocumentoDestinatario("123456789");
        destinatario.setDescripcionCuentaDestinatario("nombre referencia");
        destinatario.setCaracteristicasCuentaDestinatario("        ");
        destinatario.setTitular("Juan Gomez");
        destinatario.setDireccionCorreo("dongoogle@gmail.com");
        destinatario.setTelefonoDestinatario("41234321");
        return destinatario;
    }

}
