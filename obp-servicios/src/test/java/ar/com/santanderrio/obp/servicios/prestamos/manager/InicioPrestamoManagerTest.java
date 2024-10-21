/**
 *
 */
package ar.com.santanderrio.obp.servicios.prestamos.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosEncoladosEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosEncoladosView;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldoTasaSubsidiadaEntity;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoCanceladoMock;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoCanceladoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.CabeceraPrestamosView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosCanceladosView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.SimuladorPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.InicioPrestamoManagerImpl;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.ConfiguracionPrestamoView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class InicioPrestamoManagerTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
public class InicioPrestamoManagerTest {

    /** The inicio prestamo manager. */
    @InjectMocks
    private InicioPrestamoManagerImpl inicioPrestamoManager;

    /** The prestamo BO. */
    @Mock
    private PrestamoBO prestamoBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The modulo permiso BO. */
    @Mock
    private ModuloPermisoBOImpl moduloPermisoBOImpl;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;

    /** The mensaje manager. */
    @Mock
    private MensajeManager mensajeManager;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The prestamo manager. */
    @Mock
    private PrestamoManager prestamoManager;

    /** The simulador prestamo manager. */
    @Mock
    private SimuladorPrestamoManager simuladorPrestamoManager;

    /** The preformalizacion BO. */
    @Mock
    private PreFormalizacionPrestamoBO preformalizacionBO;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    /**
     * Obtener cabecera ok.
     */
    @Test
    public void obtenerCabeceraOk() throws DAOException {

        Cliente cliente = new Cliente();
        Cuenta cuentaUno = crearCuenta();
        cuentaUno.setJerarquiaCuenta("P");

        // Request
        CalificacionCrediticiaDTO calificacionCrediticiaDTO = new CalificacionCrediticiaDTO();
        calificacionCrediticiaDTO.setLineaTotalCrediticia(new BigDecimal(33));
        calificacionCrediticiaDTO.setLineaActualDisponible(new BigDecimal(21));
        calificacionCrediticiaDTO.setCuenta(cuentaUno);
        List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
        calificaciones.add(calificacionCrediticiaDTO);


        Respuesta<List<ConfiguracionPrestamoView>> datosSimulacion = new Respuesta<List<ConfiguracionPrestamoView>>();

        when(legalBO.obtenerLegalesPorCodigo(Matchers.anyString())).thenReturn("Mensaje legal");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(simuladorPrestamoManager.obtenerDatosSimulacion()).thenReturn(datosSimulacion);
        //    Respuesta<CalificacionCrediticiaDTO> respuestaBO = respuestaFactory
        //          .crearRespuestaOk(CalificacionCrediticiaDTO.class, calificacionCrediticiaDTO);


        Respuesta<List<CalificacionCrediticiaDTO>> respuestaBO = new Respuesta<List<CalificacionCrediticiaDTO>>();
        respuestaBO.setRespuesta(calificaciones);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);


        // Mockear el bo.
        when(prestamoBO.obtenerSituacionCrediticia(cliente)).thenReturn(respuestaBO);
        when(prestamoBO.obtenerPrestamosEncolados()).thenReturn(new PrestamosEncoladosEntity());
        PrestamoSueldoTasaSubsidiadaEntity datosPrestamo = new PrestamoSueldoTasaSubsidiadaEntity();
        datosPrestamo.setEstado("TOMADO");
        when(prestamoBO.consultarPrestamoATPVigente(cliente)).thenReturn(datosPrestamo);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        // consumir el manager.
        Respuesta<CabeceraPrestamosView> respuesta = inicioPrestamoManager.obtenerCabecera();
        // asserts o validate mocks
        Assert.assertNotNull(respuesta);
        Mockito.verify(prestamoBO).obtenerSituacionCrediticia(Matchers.any(Cliente.class));

        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().getLineaDisponible(), "21,00");
        assertEquals(respuesta.getRespuesta().getLineaTotal(), "33,00");
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_AGENDA_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener cabecera warning.
     */
    @Test
    public void obtenerCabeceraWarning() throws DAOException {

        Cliente cliente = new Cliente();
        Cuenta cuentaUno = crearCuenta();
        cuentaUno.setJerarquiaCuenta("P");
        // Request
        CalificacionCrediticiaDTO calificacionCrediticiaDTO = new CalificacionCrediticiaDTO();
        calificacionCrediticiaDTO.setLineaTotalCrediticia(new BigDecimal(33));
        calificacionCrediticiaDTO.setLineaActualDisponible(new BigDecimal(21));
        calificacionCrediticiaDTO.setCuenta(cuentaUno);

        List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
        calificaciones.add(calificacionCrediticiaDTO);

        Respuesta<List<ConfiguracionPrestamoView>> datosSimulacion = new Respuesta<List<ConfiguracionPrestamoView>>();
        when(legalBO.obtenerLegalesPorCodigo(Matchers.anyString())).thenReturn("Mensaje legal");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(simuladorPrestamoManager.obtenerDatosSimulacion()).thenReturn(datosSimulacion);


        Respuesta<List<CalificacionCrediticiaDTO>> respuestaBO = new Respuesta<List<CalificacionCrediticiaDTO>>();
        respuestaBO.setRespuesta(calificaciones);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);


        // Mockear el bo.
        when(prestamoBO.obtenerSituacionCrediticia(cliente)).thenReturn(respuestaBO);
        when(prestamoBO.obtenerPrestamosEncolados()).thenReturn(new PrestamosEncoladosEntity());
        PrestamoSueldoTasaSubsidiadaEntity datosPrestamo = new PrestamoSueldoTasaSubsidiadaEntity();
        datosPrestamo.setEstado("TOMADO");
        when(prestamoBO.consultarPrestamoATPVigente(cliente)).thenReturn(datosPrestamo);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());

        // consumir el manager.
        Respuesta<CabeceraPrestamosView> respuesta = inicioPrestamoManager.obtenerCabecera();
        // asserts o validate mocks
        Assert.assertNotNull(respuesta);
        Mockito.verify(prestamoBO).obtenerSituacionCrediticia(Matchers.any(Cliente.class));

        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().getLineaDisponible(), "21,00");
        assertEquals(respuesta.getRespuesta().getLineaTotal(), "33,00");
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_AGENDA_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener cabecera error.
     */
    @Test
    public void obtenerCabeceraError() {

        Cliente cliente = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(cliente);


        Respuesta<List<CalificacionCrediticiaDTO>> respuestaBO = new Respuesta<List<CalificacionCrediticiaDTO>>();
        getRespuestaMock(respuestaBO, EstadoRespuesta.ERROR, true, "Mensaje Error", TipoError.ERROR_INICIO_PRESTAMOS);
        when(cuentaManager.hasCuentasMonetariasActivasEnPesos()).thenReturn(Boolean.TRUE);
        when(prestamoBO.obtenerSituacionCrediticia(cliente)).thenReturn(respuestaBO);
        when(prestamoBO.obtenerPrestamosEncolados()).thenReturn(new PrestamosEncoladosEntity());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(legalBO.obtenerLegalesPorCodigo(Matchers.anyString())).thenReturn("msg de legales");
        PrestamoSueldoTasaSubsidiadaEntity datosPrestamo = new PrestamoSueldoTasaSubsidiadaEntity();
        datosPrestamo.setEstado("TOMADO");
        when(prestamoBO.consultarPrestamoATPVigente(cliente)).thenReturn(datosPrestamo);

        // consumir el manager.
        Respuesta<CabeceraPrestamosView> respuesta = inicioPrestamoManager.obtenerCabecera();
        // asserts o validate mocks
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        Assert.assertEquals(respuesta.getRespuesta().getLineaTotal(), "-");
        Assert.assertEquals(respuesta.getRespuesta().getLineaDisponible(), "-");
        Mockito.verify(prestamoBO).obtenerSituacionCrediticia(Matchers.any(Cliente.class));
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_AGENDA_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Gets the detalle prestamos test.
     *
     * @return the detalle prestamos test
     */
    public void getDetallePrestamosTest() {

        Respuesta<DetallePrestamoView> respuestaDetallePrestamoView = new Respuesta<DetallePrestamoView>();
        DetallePrestamoView detallePrestamoView = new DetallePrestamoView();

        respuestaDetallePrestamoView.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDetallePrestamoView.setRespuesta(detallePrestamoView);

        when(prestamoManager.getDetallePrestamos(Matchers.any(ConsultaPrestamo.class), Matchers.anyBoolean()))
                .thenReturn(respuestaDetallePrestamoView);
        ConsultaPrestamo consulta = new ConsultaPrestamo();
        consulta.setNumeroPrestamo("123");

        Respuesta<DetallePrestamoView> respuesta = inicioPrestamoManager.getDetallePrestamos(consulta, true);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener prestamos cancelados.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerPrestamosCancelados() throws BusinessException {
        // When
        Cliente cliente = ClienteMock.completarInfoCliente();

        when(prestamoBO.obtenerPrestamoCancelado(cliente))
                .thenReturn((new PrestamoCanceladoMock()).getPrestamosCanceladosDTO());
        ////    respuestaFactory.crearRespuestaError(PrestamosCanceladosView.class, null, null, TipoError.ERROR_GENERICO, "1137");


        when(sesionCliente.getCliente()).thenReturn(cliente);

        // Then
        Respuesta<PrestamosCanceladosView> respuesta = inicioPrestamoManager.obtenerPrestamosCancelados();
        // Expect
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Cuando no obtengo prestamos cancelados retorna warning.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void cuandoNoObtengoPrestamosCanceladosRetornaWarning() throws BusinessException {
        // When
        Cliente cliente = ClienteMock.completarInfoCliente();

        when(prestamoBO.obtenerPrestamoCancelado(cliente)).thenReturn(new ArrayList<PrestamoCanceladoDTO>());
        when(mensajeBO.obtenerMensajePorCodigo(InicioPrestamoManagerImpl.SIN_PRESTAMOS_CANCELADOS))
                .thenReturn(new Mensaje());
        when(sesionCliente.getCliente()).thenReturn(cliente);

        // Then
        Respuesta<PrestamosCanceladosView> respuesta = inicioPrestamoManager.obtenerPrestamosCancelados();

        // Expect
        Assert.assertTrue(EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta()));
        Assert.assertTrue(
                TipoError.WARNING.getDescripcion().equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError()));
        Assert.assertNull(respuesta.getRespuesta());
    }

    /**
     * Cuando obtengo prestamos cancelados retorna error generico.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void cuandoObtengoPrestamosCanceladosRetornaErrorGenerico() throws BusinessException {
        // When
        Cliente cliente = ClienteMock.completarInfoCliente();

        when(prestamoBO.obtenerPrestamoCancelado(cliente)).thenReturn(new ArrayList<PrestamoCanceladoDTO>());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(InicioPrestamoManagerImpl.ERROR_GENERICO_PRESTAMOS_CANCELADOS))
                .thenReturn(new Mensaje());
        when(prestamoBO.obtenerPrestamoCancelado(cliente)).thenThrow(new BusinessException());

        // Then
        Respuesta<PrestamosCanceladosView> respuesta = inicioPrestamoManager.obtenerPrestamosCancelados();

        // Expect
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta()));
        Assert.assertTrue(TipoError.ERROR_GENERICO.getDescripcion()
                .equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError()));
        Assert.assertNull(respuesta.getRespuesta());
    }

    /**
     * Respuesta mockeada segun estado y mensaje esperado.
     *
     * @param <E>
     *            the element type
     * @param respuesta
     *            the respuesta
     * @param estado
     *            the estado
     * @param isVacia
     *            the is vacia
     * @param mensajeEsperado
     *            the mensaje esperado
     * @param tipoError
     *            the tipo error
     * @return the respuesta mock
     */
    public <E> void getRespuestaMock(Respuesta<E> respuesta, EstadoRespuesta estado, boolean isVacia,
                                     String mensajeEsperado, TipoError tipoError) {

        respuesta.setEstadoRespuesta(estado);
        respuesta.setRespuestaVacia(isVacia);
        if (EstadoRespuesta.ERROR.equals(estado) || EstadoRespuesta.WARNING.equals(estado)) {
            List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();

            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setTipoError(tipoError.getDescripcion());
            itemMensajeRespuesta.setMensaje(estado.toString() + ": " + mensajeEsperado);

            itemsMensajeRespuesta.add(itemMensajeRespuesta);

            respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
        }

    }

    private Cuenta crearCuenta() {

        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("Cuentita personal");
        cuenta.setCbu("57574849393");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta.setNroCuentaProducto("0000000000639170");
        cuenta.setNroSucursal("0000");
        cuenta.setSaldoCUPesos("000000011265717");

        return cuenta;
    }


    @Test
    public void obtenerPrestamosEncoladosOk() throws DAOException {

        Cliente cliente = new Cliente();
        cliente.setNup("12341234");
        when(sesionCliente.getCliente()).thenReturn(cliente);

        PrestamosEncoladosEntity prestamosEncoladosEntity = new PrestamosEncoladosEntity();
        PrestamoEncoladoEntity prestamoEncoladoEntity = new PrestamoEncoladoEntity();
        prestamoEncoladoEntity.setId("123");

        prestamosEncoladosEntity.setPrestamosEncolados(Collections.singletonList(prestamoEncoladoEntity));
        when(prestamoBO.obtenerPrestamosEncolados()).thenReturn(prestamosEncoladosEntity);

        Respuesta<PrestamosEncoladosView> respuesta = inicioPrestamoManager.obtenerPrestamosEncolados();
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().getPrestamos().get(0).getId(),"123");
    }
}
