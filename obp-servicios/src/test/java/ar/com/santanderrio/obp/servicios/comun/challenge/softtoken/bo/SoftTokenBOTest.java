package ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.DesafioDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.impl.SoftTokenBOImpl;
import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.dao.SoftTokenDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

//TODO realizar asserts de los tipos de respuestas (Respuesta.respuesta y Respuesta.ItemMensajeRespuesta)
/**
 * The Class SoftTokenBOTest.
 */
// TODO realizar las verificaciones de mensajes y estadisticas
@RunWith(MockitoJUnitRunner.class)
public class SoftTokenBOTest {

    /** The Constant TOKEN_OK. */
    private static final String TOKEN_OK = "123456";

    /** The Constant TOKEN_ERROR. */
    private static final String TOKEN_ERROR = "654321";

    /** The Constant TOKEN_EXCEPTION. */
    private static final String TOKEN_EXCEPTION = "999999";

    /** The soft token respuesta ok. */
    private Respuesta<DesafioDTO> softTokenRespuestaOk;

    /** The soft token respuesta error. */
    private Respuesta<DesafioDTO> softTokenRespuestaError;

    /** The soft token BO. */
    @InjectMocks
    private SoftTokenBOImpl softTokenBO;

    /** The soft token DAO. */
    @Mock
    SoftTokenDAO softTokenDAO;

    /** The sesion cliente. */
    @Mock
    SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Mock
    SesionParametros sesionParametros;

    /** The mensaje BO. */
    @Mock
    MensajeBO mensajeBO;

    /** The registro sesion mock. */
    @Mock
    RegistroSesion registroSesionMock;

    /** The cliente mock. */
    @Mock
    Cliente clienteMock;

    /**
     * Inits the.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Before
    public void init() throws DAOException {

        // Respuesta Generica OK
        softTokenRespuestaOk = new Respuesta<DesafioDTO>();
        softTokenRespuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);

        // Respuesta Generica Error
        softTokenRespuestaError = new Respuesta<DesafioDTO>();
        softTokenRespuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        softTokenRespuestaError.setRespuesta(null);

        // Mocks de otros servicios
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesionMock);
        Mockito.when(sesionCliente.getCliente()).thenReturn(clienteMock);
        Mockito.when(mensajeBO.obtenerMensajeDescripcion(Matchers.anyString())).thenReturn("Mensaje");

    }

    /**
     * Validar soft token.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void validarSoftToken() throws DAOException {

        DesafioDTO tokenDtoOk = new DesafioDTO(TipoDesafioEnum.TOKEN);
        tokenDtoOk.setToken(TOKEN_OK);
        DesafioDTO tokenDtoError = new DesafioDTO(TipoDesafioEnum.TOKEN);
        tokenDtoError.setToken(TOKEN_ERROR);
        DesafioDTO tokenDtoException = new DesafioDTO(TipoDesafioEnum.TOKEN);
        tokenDtoError.setToken(TOKEN_EXCEPTION);

        Mockito.when(softTokenDAO.ejecutarValidacionToken(Matchers.eq(TOKEN_OK), Matchers.anyString(),
                Matchers.any(Cliente.class))).thenReturn(true);
        Mockito.when(softTokenDAO.ejecutarValidacionToken(Matchers.eq(TOKEN_ERROR), Matchers.anyString(),
                Matchers.any(Cliente.class))).thenReturn(false);
        Mockito.when(softTokenDAO.ejecutarValidacionToken(Matchers.eq(TOKEN_EXCEPTION), Matchers.anyString(),
                Matchers.any(Cliente.class))).thenThrow(DAOException.class);

        // Prueba: caso validado
        // Respuesta<TokenDTO> validarSoftToken =
        // softTokenBO.validarSoftToken(tokenDtoOk);
        // Assert.assertEquals(softTokenRespuestaOk.getEstadoRespuesta(),
        // validarSoftToken.getEstadoRespuesta());
        //
        // //Prueba: caso no validado
        // validarSoftToken = softTokenBO.validarSoftToken(tokenDtoError);
        // Assert.assertEquals(softTokenRespuestaError.getEstadoRespuesta(),
        // validarSoftToken.getEstadoRespuesta());
        //
        // //Pueba: error en servicio
        // validarSoftToken = softTokenBO.validarSoftToken(tokenDtoException);
        // //Estado ERROR
        // Assert.assertEquals(softTokenRespuestaError.getEstadoRespuesta(),
        // validarSoftToken.getEstadoRespuesta());
        // //Tiene mensaje(s) de error
        // Assert.assertFalse(validarSoftToken.getItemsMensajeRespuesta().isEmpty());
    }

    /**
     * Tiene soft token habilitado.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void tieneSoftTokenHabilitado() throws DAOException {

        // Prueba: con soft token
        List<Cuenta> cuentasSoftTokenHabilitadoMock = new ArrayList<Cuenta>();
        Cuenta cuentaBanelcoHabilitada = new Cuenta();
        cuentaBanelcoHabilitada.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuentaBanelcoHabilitada.setGrupoAfinidad("000030");
        cuentasSoftTokenHabilitadoMock.add(cuentaBanelcoHabilitada);
        Mockito.when(clienteMock.getCuentas()).thenReturn(cuentasSoftTokenHabilitadoMock);
        //
        // Respuesta<TokenDTO> validarSoftToken =
        // softTokenBO.tieneSoftTokenHabilitado();
        // Assert.assertEquals(softTokenRespuestaOk.getEstadoRespuesta(),
        // validarSoftToken.getEstadoRespuesta());
        //

        // //Prueba: sin soft token
        // List<Cuenta> cuentasSoftTokenInhabilitadoMock = new
        // ArrayList<Cuenta>();
        // Cuenta cuentaBanelcoInhabilitada = new Cuenta();
        // cuentaBanelcoInhabilitada.setTipoCuentaEnum(TipoCuenta.BANELCO);
        // cuentaBanelcoInhabilitada.setGrupoAfinidad("999999");
        // cuentasSoftTokenInhabilitadoMock.add(cuentaBanelcoInhabilitada);
        // Mockito.when(clienteMock.getCuentas()).thenReturn(cuentasSoftTokenInhabilitadoMock);
        //
        // validarSoftToken = softTokenBO.tieneSoftTokenHabilitado();
        // Assert.assertEquals(softTokenRespuestaError.getEstadoRespuesta(),
        // validarSoftToken.getEstadoRespuesta());
        //
        // //Prueba: sin soft token (grupo de afinidad mal)
        // cuentaBanelcoInhabilitada.setGrupoAfinidad("ABC");
        // validarSoftToken = softTokenBO.tieneSoftTokenHabilitado();
        // Assert.assertEquals(softTokenRespuestaError.getEstadoRespuesta(),
        // validarSoftToken.getEstadoRespuesta());
        //
        //
        // //Pueba: sin cuenta banelco
        // List<Cuenta> cuentasNoBanelcoMock = new ArrayList<Cuenta>();
        // Cuenta cuentaNoBanelco = new Cuenta();
        // cuentaNoBanelco.setTipoCuentaEnum(TipoCuenta.PRESTAMO);
        // cuentasNoBanelcoMock.add(cuentaNoBanelco);
        // Mockito.when(clienteMock.getCuentas()).thenReturn(cuentasNoBanelcoMock);
        //
        // validarSoftToken = softTokenBO.tieneSoftTokenHabilitado();
        // Assert.assertEquals(softTokenRespuestaError.getEstadoRespuesta(),
        // validarSoftToken.getEstadoRespuesta());
    }

}
