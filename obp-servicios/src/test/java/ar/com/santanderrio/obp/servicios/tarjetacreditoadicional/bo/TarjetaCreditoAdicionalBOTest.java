/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.merlin.dao.MerlinDAO;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DudaEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinWarningException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl.EdadIncorrectaException;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl.TarjetaCreditoAdicionalBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaOutDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomiciliosDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.mock.DatosSolicitanteCriterioDTOMock;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.mock.DatosSolicitanteEntityMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class TarjetaCreditoAdicionalBOTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class TarjetaCreditoAdicionalBOTest {

    /** The tarjeta credito adicional BO. */
    @InjectMocks
    private TarjetaCreditoAdicionalBO tarjetaCreditoAdicionalBO = new TarjetaCreditoAdicionalBOImpl();

    /** The datos solicitante DAO. */
    @Mock
    private DatosSolicitanteDAO datosSolicitanteDAO;

    /** The datos selectores BO. */
    @Mock
    private DatosSelectoresBO datosSelectoresBO;

    @Mock
    private EstadoYLimitesTarjetaCreditoBO estadoYLimitesTarjetaCreditoBO;

    @Mock
    private MerlinDAO merlinDAO;

    @Mock
    private DatosSelectoresDAO datosSelectoresDAO;

    @Mock
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    // Given
    // When
    // Then
    // Expected

    /**
     * Gets the datos del solicitante altair error sin fecha nac test.
     *
     * @return the datos del solicitante altair error sin fecha nac test
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     * @throws EdadIncorrectaException
     */
    @SuppressWarnings("unused")
    @Test(expected = EdadIncorrectaException.class)
    public void getDatosDelSolicitanteAltairErrorSinFechaNacTest() throws DAOException,
            SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, EdadIncorrectaException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO = DatosSolicitanteCriterioDTOMock
                .completarDatosSolicitanteCriterioDTO();
        DatosSolicitanteEntity datosSolicitanteEntity = DatosSolicitanteEntityMock.completarDatosSolicitanteEntity();
        datosSolicitanteEntity.setFechaNacimiento(null);

        // When
        when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class),
                Matchers.any(Cliente.class))).thenReturn(datosSolicitanteEntity);
        when(datosSelectoresBO.obtenerDescripcionPorOptionId(Matchers.matches("Nacionalidades"), Matchers.anyString()))
                .thenReturn("Argentino");
        when(datosSelectoresBO.obtenerDescripcionPorOptionId(Matchers.matches("paisNacimiento"), Matchers.anyString()))
                .thenReturn("Argentino");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(
                MensajeMock.completarInfoMensaje("1677", "El cliente no tiene la edad permitida para esta operacion."));

        // Then
        Respuesta<DatosSolicitanteDTO> respuesta = tarjetaCreditoAdicionalBO
                .getDatosDelSolicitanteAltair(datosSolicitanteCriterioDTO, cliente);
    }

    /**
     * Gets the datos del solicitante altair error fecha nac error parse test.
     *
     * @return the datos del solicitante altair error fecha nac error parse test
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     * @throws EdadIncorrectaException
     */
    @SuppressWarnings("unused")
    @Test(expected = EdadIncorrectaException.class)
    public void getDatosDelSolicitanteAltairErrorFechaNacErrorParseTest() throws DAOException,
            SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, EdadIncorrectaException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO = DatosSolicitanteCriterioDTOMock
                .completarDatosSolicitanteCriterioDTO();
        DatosSolicitanteEntity datosSolicitanteEntity = DatosSolicitanteEntityMock.completarDatosSolicitanteEntity();
        datosSolicitanteCriterioDTO.setFechaNacimiento("12#/1#2/19##58");

        // When
        when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class),
                Matchers.any(Cliente.class))).thenReturn(datosSolicitanteEntity);
        when(datosSelectoresBO.obtenerDescripcionPorOptionId(Matchers.matches("Nacionalidades"), Matchers.anyString()))
                .thenReturn("Argentino");
        when(datosSelectoresBO.obtenerDescripcionPorOptionId(Matchers.matches("paisNacimiento"), Matchers.anyString()))
                .thenReturn("Argentino");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(
                MensajeMock.completarInfoMensaje("1677", "El cliente no tiene la edad permitida para esta operacion."));

        // Then
        Respuesta<DatosSolicitanteDTO> respuesta = tarjetaCreditoAdicionalBO
                .getDatosDelSolicitanteAltair(datosSolicitanteCriterioDTO, cliente);
    }

    /**
     * Gets the datos del solicitante altair error fecha nac menor 14 test.
     *
     * @return the datos del solicitante altair error fecha nac menor 14 test
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     * @throws EdadIncorrectaException
     *             the edad incorrecta exception
     */
    @SuppressWarnings("unused")
    @Test(expected = EdadIncorrectaException.class)
    public void getDatosDelSolicitanteAltairErrorFechaNacMenor14Test() throws DAOException,
            SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, EdadIncorrectaException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO = DatosSolicitanteCriterioDTOMock
                .completarDatosSolicitanteCriterioDTO();
        DatosSolicitanteEntity datosSolicitanteEntity = DatosSolicitanteEntityMock.completarDatosSolicitanteEntity();
        datosSolicitanteCriterioDTO.setFechaNacimiento("11/11/2011");

        // When
        when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class),
                Matchers.any(Cliente.class))).thenReturn(datosSolicitanteEntity);
        when(datosSelectoresBO.obtenerDescripcionPorOptionId(Matchers.matches("Nacionalidades"), Matchers.anyString()))
                .thenReturn("Argentino");
        when(datosSelectoresBO.obtenerDescripcionPorOptionId(Matchers.matches("paisNacimiento"), Matchers.anyString()))
                .thenReturn("Argentino");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(
                MensajeMock.completarInfoMensaje("1677", "El cliente no tiene la edad permitida para esta operacion."));

        // Then
        Respuesta<DatosSolicitanteDTO> respuesta = tarjetaCreditoAdicionalBO
                .getDatosDelSolicitanteAltair(datosSolicitanteCriterioDTO, cliente);
    }

    /**
     * Gets the datos del solicitante altair OK.
     *
     * @return the datos del solicitante altair OK
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     * @throws EdadIncorrectaException
     */
    @Test
    public void getDatosDelSolicitanteAltairOK() throws DAOException, SinAccesoALaInformacionException,
            OperacionFueraHorarioSucursalException, EdadIncorrectaException {
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO = DatosSolicitanteCriterioDTOMock
                .completarDatosSolicitanteCriterioDTO();
        datosSolicitanteCriterioDTO.setFechaNacimiento("11/11/1987");
        DatosSolicitanteEntity datosSolicitanteEntity = DatosSolicitanteEntityMock.completarDatosSolicitanteEntity();

        when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class),
                Matchers.any(Cliente.class))).thenReturn(datosSolicitanteEntity);
        when(datosSelectoresBO.obtenerDescripcionPorOptionId(Matchers.matches("Nacionalidades"), Matchers.anyString()))
                .thenReturn("Argentino");
        when(datosSelectoresBO.obtenerDescripcionPorOptionId(Matchers.matches("paisNacimiento"), Matchers.anyString()))
                .thenReturn("Argentino");

        Respuesta<DatosSolicitanteDTO> respuesta = tarjetaCreditoAdicionalBO
                .getDatosDelSolicitanteAltair(datosSolicitanteCriterioDTO, cliente);

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void getTarjetasCandidatasOk() throws DAOException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        ConsultaDetalleDatosTarjetaOutDTO consultaDetalleDatosTarjetaOutDTO = new ConsultaDetalleDatosTarjetaOutDTO();
        consultaDetalleDatosTarjetaOutDTO.setLimiteDeCompra("1000");
        consultaDetalleDatosTarjetaOutDTO.setEstado("10");
        // When
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(estadoYLimitesTarjetaCreditoBO.obtenerDetalleDatosTarjeta(
                Matchers.any(ConsultaDetalleDatosTarjetaInDTO.class), Matchers.any(Cliente.class)))
                        .thenReturn(consultaDetalleDatosTarjetaOutDTO);
        // Then
        Respuesta<List<TarjetaCandidataDTO>> respuesta = tarjetaCreditoAdicionalBO.getTarjetasCandidatas();
        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void getResultadoMerlinOk()
            throws MerlinWarningException, BusinessException, DAOException, MerlinError1Exception {
        // Given
        Cliente cliente = new Cliente();
        cliente.setNup("556625");
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
        datosMerlinInEntity.setNombreCalle("PROF DR PEDRO CHUTRO");
        datosMerlinInEntity.setNumeroBloque("3135");
        datosMerlinInEntity.setCodigoPostal("1437");
        datosMerlinInEntity.setProvincia("01");
        datosMerlinInEntity.setLocalidad("Parque Patricios");
        datosMerlinInEntity.setPiso("1");
        datosMerlinInEntity.setDepartamento("1");
        datosMerlinInEntity.setTelediscado("11");
        datosMerlinInEntity.setTelefono("12345678");
        ResultadoMerlinEntity resultadoMerlinEntity = new ResultadoMerlinEntity();
        resultadoMerlinEntity.setCodigoRetornoExtendido("00000000");
        resultadoMerlinEntity.setCpa("C1437IYK");
        resultadoMerlinEntity.setCp4("1437");
        resultadoMerlinEntity.setDepartamento("1    ");
        resultadoMerlinEntity.setLocalidad("CAPITAL                       ");
        resultadoMerlinEntity.setNombreCalle("PROF DR PEDRO CHUTRO          ");
        resultadoMerlinEntity.setNumeroBloque("0000003135");
        resultadoMerlinEntity.setPiso("1   ");
        resultadoMerlinEntity.setProvincia("01");
        resultadoMerlinEntity.setBarrio("                              ");
        resultadoMerlinEntity.setMotivo("OK");
        resultadoMerlinEntity.setCantidadDeDudas(0);
        resultadoMerlinEntity.setDudas(new ArrayList<DudaEntity>());
        // When
        when(merlinDAO.busquedaMerlin(Matchers.any(DatosMerlinInEntity.class))).thenReturn(resultadoMerlinEntity);
        // Then
        DomiciliosDTO respuesta = tarjetaCreditoAdicionalBO.getResultadoMerlin(cliente, datosMerlinInEntity);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(false, respuesta.isEsPopUp());
        Assert.assertEquals(1, respuesta.getDomiciliosDTO().size());
    }

    @Test
    public void getResultadoMerlinOkCambioDeDomicilio()
            throws MerlinWarningException, BusinessException, DAOException, MerlinError1Exception {
        // Given
        Cliente cliente = new Cliente();
        cliente.setNup("556625");
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
        datosMerlinInEntity.setNombreCalle("SAN JOSE");
        datosMerlinInEntity.setNumeroBloque("1452");
        datosMerlinInEntity.setCodigoPostal("1136");
        datosMerlinInEntity.setProvincia("01");
        datosMerlinInEntity.setLocalidad("CAPITAL");
        datosMerlinInEntity.setPiso("5");
        datosMerlinInEntity.setDepartamento("A");
        datosMerlinInEntity.setTelediscado("11");
        datosMerlinInEntity.setTelefono("12345678");
        ResultadoMerlinEntity resultadoMerlinEntity = new ResultadoMerlinEntity();
        resultadoMerlinEntity.setCodigoRetornoExtendido("20000004");
        resultadoMerlinEntity.setCpa("C1136AAF");
        resultadoMerlinEntity.setCp4("1136");
        resultadoMerlinEntity.setDepartamento("A    ");
        resultadoMerlinEntity.setLocalidad("CAPITAL                       ");
        resultadoMerlinEntity.setNombreCalle("SAN JOSE                      ");
        resultadoMerlinEntity.setNumeroBloque("0000001452");
        resultadoMerlinEntity.setPiso("5   ");
        resultadoMerlinEntity.setProvincia("01");
        resultadoMerlinEntity.setBarrio("                              ");
        resultadoMerlinEntity.setMotivo("CO");
        resultadoMerlinEntity.setCantidadDeDudas(0);
        resultadoMerlinEntity.setDudas(new ArrayList<DudaEntity>());
        // When
        when(merlinDAO.busquedaMerlin(Matchers.any(DatosMerlinInEntity.class))).thenReturn(resultadoMerlinEntity);
        // Then
        DomiciliosDTO respuesta = tarjetaCreditoAdicionalBO.getResultadoMerlin(cliente, datosMerlinInEntity);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(false, respuesta.isEsPopUp());
        Assert.assertEquals(1, respuesta.getDomiciliosDTO().size());
    }

    @Test
    public void getResultadoMerlinOkDudas()
            throws MerlinWarningException, BusinessException, DAOException, MerlinError1Exception {
        // Given
        Cliente cliente = new Cliente();
        cliente.setNup("556625");
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
        datosMerlinInEntity.setNombreCalle("nose");
        datosMerlinInEntity.setNumeroBloque("1452");
        datosMerlinInEntity.setCodigoPostal("1587");
        datosMerlinInEntity.setProvincia("01");
        datosMerlinInEntity.setLocalidad("Palermo");
        datosMerlinInEntity.setPiso("5");
        datosMerlinInEntity.setDepartamento("A");
        datosMerlinInEntity.setTelediscado("11");
        datosMerlinInEntity.setTelefono("12345678");
        ResultadoMerlinEntity resultadoMerlinEntity = new ResultadoMerlinEntity();
        resultadoMerlinEntity.setCodigoRetornoExtendido("20000006");
        resultadoMerlinEntity.setCpa("        ");
        resultadoMerlinEntity.setCp4("    ");
        resultadoMerlinEntity.setDepartamento("A    ");
        resultadoMerlinEntity.setLocalidad("                              ");
        resultadoMerlinEntity.setNombreCalle("                              ");
        resultadoMerlinEntity.setNumeroBloque("0000001452");
        resultadoMerlinEntity.setPiso("5   ");
        resultadoMerlinEntity.setProvincia("01");
        resultadoMerlinEntity.setBarrio("                              ");
        resultadoMerlinEntity.setMotivo("DU");
        resultadoMerlinEntity.setCantidadDeDudas(2);
        List<DudaEntity> dudas = new ArrayList<DudaEntity>();
        resultadoMerlinEntity.setDudas(dudas);
        DudaEntity dudaEntity1 = new DudaEntity();
        dudaEntity1.setDudBarrio("");
        dudaEntity1.setDudCPA("C1136AAF");
        dudaEntity1.setDudDesde("0000000001");
        dudaEntity1.setDudHasta("0000002100");
        dudaEntity1.setDudLocal("CAPITAL");
        dudaEntity1.setDudNomcal("SAN JOSE");
        dudaEntity1.setDudProv("01");
        dudaEntity1.setDudSec("01");
        dudas.add(dudaEntity1);
        DudaEntity dudaEntity2 = new DudaEntity();
        dudaEntity2.setDudBarrio("");
        dudaEntity2.setDudCPA("C1424ABO");
        dudaEntity2.setDudDesde("0000000001");
        dudaEntity2.setDudHasta("0000002000");
        dudaEntity2.setDudLocal("CAPITAL");
        dudaEntity2.setDudNomcal("AV JOSE M MORENO");
        dudaEntity2.setDudProv("01");
        dudaEntity2.setDudSec("02");
        dudas.add(dudaEntity2);
        // When
        when(merlinDAO.busquedaMerlin(Matchers.any(DatosMerlinInEntity.class))).thenReturn(resultadoMerlinEntity);
        // Then
        DomiciliosDTO respuesta = tarjetaCreditoAdicionalBO.getResultadoMerlin(cliente, datosMerlinInEntity);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(true, respuesta.isEsPopUp());
        Assert.assertEquals(2, respuesta.getDomiciliosDTO().size());
    }

    @Test(expected = MerlinWarningException.class)
    public void getResultadoMerlinWarningDatoInvalido()
            throws MerlinWarningException, BusinessException, DAOException, MerlinError1Exception {
        // Given
        Cliente cliente = new Cliente();
        cliente.setNup("556625");
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
        datosMerlinInEntity.setNombreCalle("SAN JOSE");
        datosMerlinInEntity.setNumeroBloque("99999");
        datosMerlinInEntity.setCodigoPostal("1136");
        datosMerlinInEntity.setProvincia("01");
        datosMerlinInEntity.setLocalidad("CAPITAL");
        datosMerlinInEntity.setPiso("5");
        datosMerlinInEntity.setDepartamento("A");
        datosMerlinInEntity.setTelediscado("11");
        datosMerlinInEntity.setTelefono("12345678");
        ResultadoMerlinEntity resultadoMerlinEntity = new ResultadoMerlinEntity();
        resultadoMerlinEntity.setCodigoRetornoExtendido("20000017");
        resultadoMerlinEntity.setCpa("        ");
        resultadoMerlinEntity.setCp4("    ");
        resultadoMerlinEntity.setDepartamento("A    ");
        resultadoMerlinEntity.setLocalidad("                              ");
        resultadoMerlinEntity.setNombreCalle("                              ");
        resultadoMerlinEntity.setNumeroBloque("0000099999");
        resultadoMerlinEntity.setPiso("5   ");
        resultadoMerlinEntity.setProvincia("01");
        resultadoMerlinEntity.setBarrio("                              ");
        resultadoMerlinEntity.setMotivo("NO");
        resultadoMerlinEntity.setCantidadDeDudas(1);
        List<DudaEntity> dudas = new ArrayList<DudaEntity>();
        resultadoMerlinEntity.setDudas(dudas);
        DudaEntity dudaEntity1 = new DudaEntity();
        dudaEntity1.setDudBarrio("");
        dudaEntity1.setDudCPA("AI");
        dudaEntity1.setDudDesde("0000000001");
        dudaEntity1.setDudHasta("0000002100");
        dudaEntity1.setDudLocal("CAPITAL");
        dudaEntity1.setDudNomcal("SAN JOSE");
        dudaEntity1.setDudProv("01");
        dudaEntity1.setDudSec("01");
        dudas.add(dudaEntity1);
        // When
        when(merlinDAO.busquedaMerlin(Matchers.any(DatosMerlinInEntity.class))).thenReturn(resultadoMerlinEntity);
        // Then
        tarjetaCreditoAdicionalBO.getResultadoMerlin(cliente, datosMerlinInEntity);
    }
}
