/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import java.util.ArrayList;
import java.util.List;

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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AgendaDestinatarioBOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.CasuisticaAgendaDestinatariosImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConsultaAgendaDestinatarioOutEntityMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.DestinatarioEntityMock;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

/**
 * The Class AgendaDestinatarioBOTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendaDestinatarioBOTest {

    /** The agenda BO. */
    @InjectMocks
    private AgendaDestinatarioBOImpl agendaBO = new AgendaDestinatarioBOImpl();

    /** The casuistica. */
    @InjectMocks
    @Spy
    private CasuisticaAgendaDestinatariosImpl casuistica = new CasuisticaAgendaDestinatariosImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The agenda DAO. */
    @Mock
    private AgendaDestinatarioDAO agendaDAO;
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The consulta agenda destinatario out entity mock. */
    private ConsultaAgendaDestinatarioOutEntityMock consultaAgendaDestinatarioOutEntityMock = new ConsultaAgendaDestinatarioOutEntityMock();

    /**
     * Dado un cliente, cuando se invoca al metodo "obtenerAgendaDestinatarios",
     * obtengo la respuesta OK de agenda destinatarios DTO.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTO()
            throws DAOException {
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class)))
                .thenReturn(obtenerAgendaDestinatario());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(obtenerCliente(), true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(4, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(7, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CAP como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCapCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_PESOS));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(0, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CAD como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCadCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_DOLARES));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(0, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CU como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCuCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CUENTA_UNICA));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(0, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CUP como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCupCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CUENTA_UNICA_PESOS));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(0, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CUD como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCudCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CUENTA_UNICA_DOLARES));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(0, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CAP y CAD como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCapYCadCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_PESOS));
        cuentas.add(CuentaMock.completarInfoCuenta("000002345678", "0371", TipoCuenta.CAJA_AHORRO_DOLARES));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(0, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con 2 cta propia en pesos sin CU.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOCon2CtaPropiaEnPesosSinCu()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_PESOS));
        cuentas.add(CuentaMock.completarInfoCuenta("000002345678", "0371", TipoCuenta.CUENTA_CORRIENTE_PESOS));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con 2 cta propia en dolares sin CU.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOCon2CtaPropiaEnDolaresSinCu()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_DOLARES));
        cuentas.add(CuentaMock.completarInfoCuenta("000002345678", "0371", TipoCuenta.CUENTA_CORRIENTE_DOLARES));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CAP, CAP y CAD como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCapCcpYCadCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_PESOS));
        cuentas.add(CuentaMock.completarInfoCuenta("000002345678", "0371", TipoCuenta.CUENTA_CORRIENTE_PESOS));
        cuentas.add(CuentaMock.completarInfoCuenta("000003456789", "0372", TipoCuenta.CAJA_AHORRO_DOLARES));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CAP, CCD y CAD como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCapYCcdCadCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_PESOS));
        cuentas.add(CuentaMock.completarInfoCuenta("000002345678", "0371", TipoCuenta.CUENTA_CORRIENTE_DOLARES));
        cuentas.add(CuentaMock.completarInfoCuenta("000003456789", "0372", TipoCuenta.CAJA_AHORRO_DOLARES));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CAP, CCD y CU como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCapCcdYCuCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_PESOS));
        cuentas.add(CuentaMock.completarInfoCuenta("000002345678", "0371", TipoCuenta.CUENTA_CORRIENTE_DOLARES));
        cuentas.add(CuentaMock.completarInfoCuenta("000003456789", "0372", TipoCuenta.CUENTA_UNICA));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(3, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CAD, CCD y CU como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCapCcpYCuCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_DOLARES));
        cuentas.add(CuentaMock.completarInfoCuenta("000002345678", "0371", TipoCuenta.CUENTA_CORRIENTE_DOLARES));
        cuentas.add(CuentaMock.completarInfoCuenta("000003456789", "0372", TipoCuenta.CUENTA_UNICA));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());

        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(3, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Dado cliente cuando invoca obtener agenda destinatarios obtengo respuesta
     * OK agenda destinatarios DTO con CUD, CUP y CU como cta propia.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatariosDTOConCupYCudCtaPropia()
            throws DAOException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta("000002345678", "0371", TipoCuenta.CUENTA_UNICA_DOLARES));
        cuentas.add(CuentaMock.completarInfoCuenta("000003456789", "0372", TipoCuenta.CUENTA_UNICA_PESOS));
        cuentas.add(CuentaMock.completarInfoCuenta("000003456789", "0372", TipoCuenta.CUENTA_UNICA));
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
        List<DestinatarioEntity> destinatariosEntity = obtenerListaDestinatariosEntityNoRio();
        ConsultaAgendaDestinatarioOutEntity outEntity = consultaAgendaDestinatarioOutEntityMock
                .completarInfoConCtaPropiaEnPesos(destinatariosEntity);

        // When
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        // Then
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, true);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
        Assert.assertEquals(3, respDTO.getRespuesta().getCantCuentasPropias());
        Assert.assertEquals(2, respDTO.getRespuesta().getCantidadCuentasNoPropias());
    }

    /**
     * Obtener lista destinatarios entity no rio.
     *
     * @return the list
     */
    private List<DestinatarioEntity> obtenerListaDestinatariosEntityNoRio() {
        List<DestinatarioEntity> destinatariosEntity = new ArrayList<DestinatarioEntity>();
        DestinatarioEntity d1 = DestinatarioEntityMock.completarInfoDestinatarioEntity();
        DestinatarioEntity d2 = DestinatarioEntityMock.completarInfoDestinatarioEntity();
        d2.setBancoDestinatario("Banco Francés");
        d2.setTipoAgendaOcurrencia("OB ");
        destinatariosEntity.add(d1);
        destinatariosEntity.add(d2);
        return destinatariosEntity;
    }

    /**
     * Obtener agenda destinatarios uno test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerAgendaDestinatariosUnoTest() throws DAOException {
        Cliente cliente = ClienteMock.completarInfoCliente();
        cliente.setCuentas(obtenerCuenta());

        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class)))
                .thenReturn(obtenerAgendaDestinatario());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(cliente, false);

        Assert.assertEquals(EstadoRespuesta.OK, respDTO.getEstadoRespuesta());
    }

    /**
     * Obtener agenda destinatarios sin cuentas validas test.
     */
    @Test
    public void obtenerAgendaDestinatariosSinCuentasValidasTest() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1484",
                        "<p>Aún no tenés una cuenta activa.</p><p>Para realizar una nueva transferencia o envío efectivo necesitás poseer una cuenta activa.</p><br><br><p>Podes solicitarla rápido y fácil desde tu online banking.</p>"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        Cliente cliente = obtenerCliente();
        cliente.setCuentas(new ArrayList<Cuenta>());

        Respuesta<AgendaDestinatarioDTO> res = agendaBO.obtenerAgendaDestinatarios(cliente, false);

        Assert.assertNotNull(res);
    }

    /**
     * Dado un cliente, cuando se invoca al metodo "obtenerAgendaDestinatarios",
     * obtengo la respuesta OK de agenda destinatarios DTO.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void realizarConsultaAgendaDestinatarioDAOException() throws DAOException {
        Mockito.when(agendaDAO.consultar(Matchers.any(ConsultaAgendaDestinatarioInEntity.class)))
                .thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1390", "¡Utiliza tu agenda!"));
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());
        Respuesta<AgendaDestinatarioDTO> respDTO = agendaBO.obtenerAgendaDestinatarios(obtenerCliente(), false);

        Assert.assertEquals(EstadoRespuesta.WARNING, respDTO.getEstadoRespuesta());
    }

    /**
     * Agenda destinatario DTO hash equals string test.
     */
    @Test
    public void agendaDestinatarioDTOHashEqualsStringTest() {
        AgendaDestinatarioDTO agendaDestinatarioDTO = new AgendaDestinatarioDTO();
        agendaDestinatarioDTO.setTieneErrorRellamado(true);
        agendaDestinatarioDTO.setCantCuentasPropias(3);
        agendaDestinatarioDTO.setMensajeCabecera("mensaje");

        Assert.assertNotEquals(agendaDestinatarioDTO.hashCode(), 0);
        Assert.assertEquals(
                "AgendaDestinatarioDTO[mensajeCabecera=mensaje,listaDestinatarios=[],tieneErrorCuentasPropias=false]",
                agendaDestinatarioDTO.toString());
        AgendaDestinatarioDTO agendaDestinatarioDTO2 = new AgendaDestinatarioDTO();
        Assert.assertNotEquals(agendaDestinatarioDTO2, agendaDestinatarioDTO);
        Assert.assertEquals(agendaDestinatarioDTO, agendaDestinatarioDTO);
    }

    // TEST COBERTURA ENTITYES Y DTOs

    /**
     * Destinatario agenda DTO hash equals string test.
     */
    @Test
    public void destinatarioAgendaDTOHashEqualsStringTest() {
        DestinatarioAgendaDTO agendaDestinatarioDTO = new DestinatarioAgendaDTO();
        agendaDestinatarioDTO.setBanco(" ");
        agendaDestinatarioDTO.setBanco("banco");
        agendaDestinatarioDTO.setCuitCuil("cuitCuil");
        agendaDestinatarioDTO.setTipoDocumento(" ");
        agendaDestinatarioDTO.setTipoDocumento(" L");
        Assert.assertNotEquals(agendaDestinatarioDTO.hashCode(), 0);
        Assert.assertEquals(
                "DestinatarioAgendaDTO[id=<null>,referenciaApodo=<null>,titular=<null>,nroCuenta=<null>,tipoCuenta=<null>,banco=banco,cuitCuil=cuitCuil,email=<null>,tipoAgendaEnum=<null>,muestraReferenciaApodo=false,tipoDocumento= L,documento=<null>,esCuentaPropia=false,esPesos=false]",
                agendaDestinatarioDTO.toString());
        DestinatarioAgendaDTO agendaDestinatarioDTO2 = new DestinatarioAgendaDTO();
        Assert.assertNotEquals(agendaDestinatarioDTO2, agendaDestinatarioDTO);
        Assert.assertEquals(agendaDestinatarioDTO, agendaDestinatarioDTO);
    }

    /**
     * Validacion cuenta in entity getter setter test.
     */
    @Test
    public void validacionCuentaInEntityGetterSetterTest() {
        ValidacionCuentaInEntity cuentaInEntity = new ValidacionCuentaInEntity();
        cuentaInEntity.setCliente(null);
        cuentaInEntity.setInformarCuil("Si");
        cuentaInEntity.setNumeroCuenta("nroCuenta");
        cuentaInEntity.setSucursalCuenta("012");
        cuentaInEntity.setTipoCuenta("02");
        Assert.assertNull(cuentaInEntity.getCliente());
        Assert.assertEquals("Si", cuentaInEntity.getInformarCuil());
        Assert.assertEquals("nroCuenta", cuentaInEntity.getNumeroCuenta());
        Assert.assertEquals("012", cuentaInEntity.getSucursalCuenta());
        Assert.assertEquals("02", cuentaInEntity.getTipoCuenta());
    }

    /**
     * Destinatario entitygetter setter string equals test.
     */
    @Test
    public void destinatarioEntitygetterSetterStringEqualsTest() {
        DestinatarioEntity destinatarioEntity = new DestinatarioEntity();
        destinatarioEntity.setBancoDestinatario("BANELCO");
        destinatarioEntity.setTimestampActivacion("asd");
        destinatarioEntity.setCanalOperacion("RIO");
        destinatarioEntity.setSubCanalOperacion("as");
        destinatarioEntity.setTimestampAlta("asdw");
        Assert.assertEquals("asd", destinatarioEntity.getTimestampActivacion());
        Assert.assertEquals("RIO", destinatarioEntity.getCanalOperacion());
        Assert.assertEquals("as", destinatarioEntity.getSubCanalOperacion());
        Assert.assertEquals("asdw", destinatarioEntity.getTimestampAlta());

        Assert.assertEquals(
                "DestinatarioEntity[tipoCuentaDestinatario=<null>,sucursalCuentaDestinatario=<null>,numerroCuentaDestinatario=<null>,cbuDestinatario=<null>,id=<null>]",
                destinatarioEntity.toString());
        DestinatarioEntity destinatarioEntity2 = new DestinatarioEntity();
        Assert.assertNotEquals(destinatarioEntity, destinatarioEntity2);
        Assert.assertEquals(destinatarioEntity, destinatarioEntity);

    }

    /**
     * Obtener agenda destinatario.
     *
     * @return the consulta agenda destinatario out entity
     */
    private ConsultaAgendaDestinatarioOutEntity obtenerAgendaDestinatario() {
        ConsultaAgendaDestinatarioOutEntity entity = new ConsultaAgendaDestinatarioOutEntity();
        entity.setDestinatarios(obtenerDestinatarios());
        return entity;
    }

    /**
     * Obtener destinatarios.
     *
     * @return the list
     */
    private List<DestinatarioEntity> obtenerDestinatarios() {
        List<DestinatarioEntity> destinatariosEntity = new ArrayList<DestinatarioEntity>();
        destinatariosEntity.add(DestinatarioEntityMock.completarInfoDestinatarioEntity());
        destinatariosEntity.add(obtenerDestinatarioEntity("Banco Francés", "00113579246802468013579",
                "Transporte                    ", "Transporte Carga Pesada S. A.", "0122", "002468013579", "01",
                "002000068147", TipoAgendaEnum.AGENDA_RIO.getCampo()));
        destinatariosEntity.add(obtenerDestinatarioEntity("Banco Patagonia", "0070000000001234567890",
                "AAA                       ", "Gerardo S. A.", "0212", "001234567890", "00", "002000068148",
                TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo()));
        destinatariosEntity.add(
                obtenerDestinatarioEntity("Banco Nación", "0080000000002345678901", "", "ZAutomóvil Club Argentino",
                        "0345", "002345678901", "01", "002000068149", TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo()));
        destinatariosEntity
                .add(DestinatarioEntityMock.completarInfoDestinatarioEntity(TipoAgendaEnum.AGENDA_EXTRACCIONES));
        destinatariosEntity.add(obtenerDestinatarioEntity("Banco Nación", "0080000000002345678901", "", "", "0345",
                "002345678901", "00", "002000068149", TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo()));
        destinatariosEntity.add(obtenerDestinatarioEntity("Banco Nación", "0080000000002345678901", "Club Arg", "",
                "0345", "002345678901", "03", "", TipoAgendaEnum.AGENDA_RIO.getCampo()));
        destinatariosEntity.add(
                obtenerDestinatarioEntity("Banco Nación", "0080000000002345678901", "", "ZAutomóvil Club Argentino",
                        "0345", "002345678901", "04", "002000068149", TipoAgendaEnum.AGENDA_RIO.getCampo()));
        destinatariosEntity.add(
                obtenerDestinatarioEntity("Banco Nación", "0080000000002345678901", "", "ZAutomóvil Club Argentino",
                        "0345", "002345678901", "00", "002000068149", TipoAgendaEnum.AGENDA_RIO.getCampo()));
        return destinatariosEntity;
    }

    /**
     * Obtener destinatario entity.
     *
     * @param bancoDestinatario
     *            the banco destinatario
     * @param cbuDestinatario
     *            the cbu destinatario
     * @param descripcionCuentaDestinatario
     *            the descripcion cuenta destinatario
     * @param titular
     *            the titular
     * @param sucursalCuentaDestinatario
     *            the sucursal cuenta destinatario
     * @param numeroCuentaDestinatario
     *            the numero cuenta destinatario
     * @param tipoCuentaDestinatario
     *            the tipo cuenta destinatario
     * @param cuitCuil
     *            the cuit cuil
     * @param tipoAgenda
     *            the tipo agenda
     * @return the destinatario entity
     */
    private DestinatarioEntity obtenerDestinatarioEntity(String bancoDestinatario, String cbuDestinatario,
            String descripcionCuentaDestinatario, String titular, String sucursalCuentaDestinatario,
            String numeroCuentaDestinatario, String tipoCuentaDestinatario, String cuitCuil, String tipoAgenda) {
        DestinatarioEntity entity = new DestinatarioEntity();
        entity.setBancoDestinatario(bancoDestinatario);
        entity.setCbuDestinatario(cbuDestinatario);
        entity.setDescripcionCuentaDestinatario(descripcionCuentaDestinatario);
        entity.setTitular(titular);
        entity.setSucursalCuentaDestinatario(sucursalCuentaDestinatario);
        entity.setNumeroCuentaDestinatario(numeroCuentaDestinatario);
        entity.setTipoCuentaDestinatario(tipoCuentaDestinatario);
        entity.setDocumentoDestinatario(cuitCuil);
        entity.setTipoAgendaOcurrencia(tipoAgenda);
        return entity;
    }

    /**
     * Obtener cliente.
     *
     * @return the cliente
     */
    private Cliente obtenerCliente() {
        Cliente cliente = ClienteMock.completarInfoCliente();
        cliente.setCuentas(obtenerCuentas());
        return cliente;
    }

    /**
     * Obtener cuentas.
     *
     * @return the list
     */
    private List<Cuenta> obtenerCuentas() {
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(obtenerCuenta(TipoCuenta.CAJA_AHORRO_PESOS, "", "123", "001234567891", "12345678901234567890"));
        cuentas.add(
                obtenerCuenta(TipoCuenta.CUENTA_UNICA, "Fer Unica", "123", "0023456789012", "23456789012345678901"));
        cuentas.add(obtenerCuenta(TipoCuenta.BANELCO, "Fer BANELCO", "123", "0023456789013", "23456789012345678903"));
        cuentas.add(obtenerCuenta(TipoCuenta.CAJA_AHORRO_PESOS, "", "123", "0023456789014", "23456789012345678904"));
        cuentas.add(obtenerCuenta(TipoCuenta.CUENTA_UNICA_PESOS, "Fer Unica", "123", "0023456789012",
                "23456789012345678901"));
        return cuentas;
    }

    /**
     * Obtener cuenta.
     *
     * @return the list
     */
    private List<Cuenta> obtenerCuenta() {
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(obtenerCuenta(TipoCuenta.CAJA_AHORRO_PESOS, "", "123", "001234567891", "12345678901234567890"));
        return cuentas;
    }

    /**
     * Obtener cuenta.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @param apodo
     *            the apodo
     * @param nroSucursal
     *            the nro sucursal
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @param cbu
     *            the cbu
     * @return the cuenta
     */
    private Cuenta obtenerCuenta(TipoCuenta tipoCuenta, String apodo, String nroSucursal, String nroCuentaProducto,
            String cbu) {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(tipoCuenta);
        cuenta.setTipoCuenta(tipoCuenta.getDescripcion());
        cuenta.setCliente(obtenerClienteCuenta());
        cuenta.setAlias(apodo);
        cuenta.setNroSucursal(nroSucursal);
        cuenta.setNroCuentaProducto(nroCuentaProducto);
        cuenta.setCbu(cbu);
        return cuenta;
    }

    /**
     * Obtener cliente cuenta.
     *
     * @return the cliente
     */
    private Cliente obtenerClienteCuenta() {
        Cliente cliente = new Cliente();
        cliente.setApellido1("Lopez");
        cliente.setNombre("Fernando");
        cliente.setNumeroCUILCUIT("202345676792");
        return cliente;
    }

}