/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import org.apache.commons.collections.CollectionUtils;
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
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaPrestamosEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PreFormalizacionPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.SimuladorPrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CalificacionCrediticiaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPermitidoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.InfoPrestamosDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ObtenerPrestamosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.OrdenPrestamos;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoCanceladoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.utils.CodigosPrestamos;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class PrestamoBOTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoBOTest {

    /** The prestamo BO. */
    @InjectMocks
    private PrestamoBO prestamoBO = new PrestamoBOImpl();

    /** The simulador prestamo bo. */
    @InjectMocks
    private SimuladorPrestamoBO simuladorPrestamoBo = new SimuladorPrestamoBOImpl();

    /** The prestamo DAO. */
    @Mock
    private PrestamoDAO prestamoDAO;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The deuda prestamo DAO. */
    @Mock
    private DeudaPrestamoDAO deudaPrestamoDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The destino prestamo DAO. */
    @Mock
    private DestinoPrestamoDAO destinoPrestamoDAO;

    /** The Mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The Prestamo Permitido DAO. */
    @Mock
    private PrestamoPermitidoDAO prestamoPermitidoDAO;

    /** The Calificacion Crediticia DAO. */
    @Mock
    private CalificacionCrediticiaDAO calificacionCrediticiaDAO;
    
    @Mock
    private PreFormalizacionPrestamoDAO preFormalizacionPrestamoDAO;

    @Mock
    private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO;

    @Mock
    private DestinoPrestamoBO destinoPrestamoBO;
    
    @Mock
    private CodigosPrestamos codigosPrestamos;

    /**
     * Obtener prestamos test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerPrestamosTest() throws DAOException {

        Cliente cliente = new Cliente();
        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Prestamo prestamo1 = new Prestamo();
        Prestamo prestamo2 = new Prestamo();
        ObtenerPrestamosInDTO obtenerPrestamosInDTO = new ObtenerPrestamosInDTO();
        Calendar fechaVencimiento1 = Calendar.getInstance();
        fechaVencimiento1.set(2017, 10, 23);
        Calendar fechaVencimiento2 = Calendar.getInstance();
        fechaVencimiento2.set(2017, 10, 22);

        prestamo1.setVencimientoCuota(fechaVencimiento1.getTime());
        prestamo2.setVencimientoCuota(fechaVencimiento2.getTime());
        obtenerPrestamosInDTO.setIsTodos(true);
        obtenerPrestamosInDTO.setIsHipotecario(Boolean.FALSE);
        obtenerPrestamosInDTO.setIsPrendario(Boolean.FALSE);
        obtenerPrestamosInDTO.setIsPersonal(Boolean.FALSE);
        obtenerPrestamosInDTO.setCliente(cliente);
        obtenerPrestamosInDTO.setOrdenPrestamos(OrdenPrestamos.ASCENSDENTE);
        cuenta1.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30.getCodigo());
        cuenta1.setClaseCuenta("8");
        cuenta1.setNroCuentaProducto("12345");
        cuenta1.setProductoAltair("0");
        cuenta1.setSubproductoAltair("0");
        cuenta2.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO31.getCodigo());
        cuenta2.setClaseCuenta("2");
        cuenta2.setNroCuentaProducto("67890");
        cuenta2.setProductoAltair("0");
        cuenta2.setSubproductoAltair("0");
        listCuentas.add(cuenta1);
        listCuentas.add(cuenta2);
        cliente.setCuentas(listCuentas);

        PreFormalizacion preformalizacion = new PreFormalizacion();
        preformalizacion.setPlazo("24");
        preformalizacion.setCodigoDestinoPrestamo("03335");
        preformalizacion.getPrestamoDebitoAdherido().setImpconce("500000");
        preformalizacion.getPrestamoDebitoAdherido().setNumeroPropuesta("0072312312");

        Respuesta<PreFormalizacion> respuestaPreformalizacion = respuestaFactory.crearRespuestaOk(preformalizacion);

        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class))).thenReturn(respuestaPreformalizacion);
        when(deudaPrestamoDAO.consultarDeudaDePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(prestamo2, prestamo1);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(codigosPrestamos.getData()).thenReturn(new HashMap<Integer, List<Integer>>());

        Respuesta<List<PrestamoDTO>> respuesta = prestamoBO.obtenerPrestamos(obtenerPrestamosInDTO);
        List<PrestamoDTO> prestamosDTO = respuesta.getRespuesta();

        assertNotNull(prestamosDTO);
        assertTrue(prestamosDTO.size() == 2);
        assertEquals(prestamosDTO.get(0).getPrestamo().getClaseCuenta(),
                TipoPrestamoEnum.HIPOTECARIOS.getDescripcion());
        assertEquals(prestamosDTO.get(0).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.HIPOTECARIOS);
        assertEquals(prestamosDTO.get(0).getPrestamo().getNumeroCuentaProducto(), "12345");
        assertEquals(prestamosDTO.get(1).getPrestamo().getClaseCuenta(), TipoPrestamoEnum.PRENDARIO.getDescripcion());
        assertEquals(prestamosDTO.get(1).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.PRENDARIO);
        assertEquals(prestamosDTO.get(1).getPrestamo().getNumeroCuentaProducto(), "67890");

    }

    /**
     * Obtener prestamos filtrados test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerPrestamosFiltradosTest() throws DAOException {

        Cliente cliente = new Cliente();
        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        Cuenta cuenta4 = new Cuenta();
        Prestamo prestamo1 = new Prestamo();
        Prestamo prestamo2 = new Prestamo();
        Prestamo prestamo3 = new Prestamo();
        Prestamo prestamo4 = new Prestamo();
        ObtenerPrestamosInDTO obtenerPrestamosInDTO = new ObtenerPrestamosInDTO();
        Calendar fechaVencimiento = Calendar.getInstance();
        Calendar fechaVencimiento2 = Calendar.getInstance();
        fechaVencimiento.add(Calendar.DATE, 10);
        fechaVencimiento2.set(Calendar.DATE, 30);

        obtenerPrestamosInDTO.setOrdenPrestamos(OrdenPrestamos.ASCENSDENTE);
        prestamo1.setVencimientoCuota(fechaVencimiento.getTime());
        prestamo2.setVencimientoCuota(fechaVencimiento.getTime());
        prestamo3.setVencimientoCuota(fechaVencimiento.getTime());
        prestamo4.setVencimientoCuota(fechaVencimiento2.getTime());
        obtenerPrestamosInDTO.setCliente(cliente);
        cuenta1.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30.getCodigo());
        cuenta1.setClaseCuenta("8");
        cuenta1.setNroCuentaProducto("12345");
        cuenta1.setProductoAltair("0");
        cuenta1.setSubproductoAltair("0");
        cuenta2.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO31.getCodigo());
        cuenta2.setClaseCuenta("4");
        cuenta2.setNroCuentaProducto("67890");
        cuenta2.setProductoAltair("0");
        cuenta2.setSubproductoAltair("0");
        cuenta3.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO31.getCodigo());
        cuenta3.setClaseCuenta("2");
        cuenta3.setNroCuentaProducto("68922");
        cuenta3.setProductoAltair("0");
        cuenta3.setSubproductoAltair("0");
        cuenta4.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO31.getCodigo());
        cuenta4.setClaseCuenta("8");
        cuenta4.setNroCuentaProducto("42466");
        cuenta4.setProductoAltair("0");
        cuenta4.setSubproductoAltair("0");
        listCuentas.add(cuenta1);
        listCuentas.add(cuenta2);
        listCuentas.add(cuenta3);
        listCuentas.add(cuenta4);
        cliente.setCuentas(listCuentas);

        PreFormalizacion preformalizacion = new PreFormalizacion();
        preformalizacion.setPlazo("24");
        preformalizacion.setCodigoDestinoPrestamo("03335");
        preformalizacion.getPrestamoDebitoAdherido().setImpconce("500000");
        preformalizacion.getPrestamoDebitoAdherido().setNumeroPropuesta("0072312312");

        Respuesta<PreFormalizacion> respuestaPreformalizacion = respuestaFactory.crearRespuestaOk(preformalizacion);

        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class))).thenReturn(respuestaPreformalizacion);
        when(deudaPrestamoDAO.consultarDeudaDePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(prestamo1, prestamo2, prestamo3, prestamo4)
                .thenReturn(prestamo1, prestamo2, prestamo3, prestamo4)
                .thenReturn(prestamo1, prestamo2, prestamo3, prestamo4);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(codigosPrestamos.getData()).thenReturn(new HashMap<Integer, List<Integer>>());
        
        // Chequeo que filtre solo el hipotecario
        obtenerPrestamosInDTO.setIsHipotecario(true);
        Respuesta<List<PrestamoDTO>> respuesta = prestamoBO.obtenerPrestamos(obtenerPrestamosInDTO);
        List<PrestamoDTO> prestamosDTO = respuesta.getRespuesta();
        assertNotNull(prestamosDTO);
        assertTrue(prestamosDTO.size() == 2);
        assertEquals(prestamosDTO.get(0).getPrestamo().getClaseCuenta(),
                TipoPrestamoEnum.HIPOTECARIOS.getDescripcion());
        assertEquals(prestamosDTO.get(0).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.HIPOTECARIOS);
        assertEquals(prestamosDTO.get(0).getPrestamo().getNumeroCuentaProducto(), "12345");
        assertEquals(prestamosDTO.get(1).getPrestamo().getClaseCuenta(),
                TipoPrestamoEnum.HIPOTECARIOS.getDescripcion());
        assertEquals(prestamosDTO.get(1).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.HIPOTECARIOS);
        assertEquals(prestamosDTO.get(1).getPrestamo().getNumeroCuentaProducto(), "42466");

        // Chequeo que filtre solo el personal
        obtenerPrestamosInDTO.setIsHipotecario(false);
        obtenerPrestamosInDTO.setIsPersonal(true);
        respuesta = prestamoBO.obtenerPrestamos(obtenerPrestamosInDTO);
        prestamosDTO = respuesta.getRespuesta();
        assertNotNull(prestamosDTO);
        assertTrue(prestamosDTO.size() == 1);
        assertEquals(prestamosDTO.get(0).getPrestamo().getClaseCuenta(), TipoPrestamoEnum.PERSONAL.getDescripcion());
        assertEquals(prestamosDTO.get(0).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.PERSONAL);
        assertEquals(prestamosDTO.get(0).getPrestamo().getNumeroCuentaProducto(), "67890");

        // Chequeo que filtre solo el prendario
        obtenerPrestamosInDTO.setIsPersonal(false);
        obtenerPrestamosInDTO.setIsPrendario(true);
        respuesta = prestamoBO.obtenerPrestamos(obtenerPrestamosInDTO);
        prestamosDTO = respuesta.getRespuesta();
        assertNotNull(prestamosDTO);
        assertTrue(prestamosDTO.size() == 1);
        assertEquals(prestamosDTO.get(0).getPrestamo().getClaseCuenta(), TipoPrestamoEnum.PRENDARIO.getDescripcion());
        assertEquals(prestamosDTO.get(0).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.PRENDARIO);
        assertEquals(prestamosDTO.get(0).getPrestamo().getNumeroCuentaProducto(), "68922");
    }

    /**
     * Obtener prestamos error test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerPrestamosErrorTest() throws DAOException {

        Cliente cliente = new Cliente();
        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Mensaje mensaje = new Mensaje();
        ObtenerPrestamosInDTO obtenerPrestamosInDTO = new ObtenerPrestamosInDTO();

        obtenerPrestamosInDTO.setCliente(cliente);
        mensaje.setMensaje("Mensaje de error");
        cuenta1.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30.getCodigo());
        cuenta1.setClaseCuenta("8");
        cuenta1.setNroCuentaProducto("12345");
        listCuentas.add(cuenta1);
        cliente.setCuentas(listCuentas);

        when(deudaPrestamoDAO.consultarDeudaDePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenThrow(DAOException.class);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<List<PrestamoDTO>> respuesta = prestamoBO.obtenerPrestamos(obtenerPrestamosInDTO);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_PARCIAL_BUSCAR_PRESTAMOS.getDescripcion());
    }

    /**
     * Obtener prestamos un error un OK test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerPrestamosUnErrorUnOKTest() throws DAOException {

        Cliente cliente = new Cliente();
        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Prestamo prestamo1 = new Prestamo();
        Mensaje mensaje = new Mensaje();
        ObtenerPrestamosInDTO obtenerPrestamosInDTO = new ObtenerPrestamosInDTO();
        Calendar fechaVencimiento = Calendar.getInstance();
        fechaVencimiento.set(2017, 10, 20);

        prestamo1.setVencimientoCuota(fechaVencimiento.getTime());
        obtenerPrestamosInDTO.setIsTodos(true);
        obtenerPrestamosInDTO.setCliente(cliente);
        mensaje.setMensaje("Mensaje de error");
        cuenta1.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30.getCodigo());
        cuenta1.setClaseCuenta("8");
        cuenta1.setProductoAltair("35");
        cuenta1.setSubproductoAltair("13");
        cuenta1.setNroCuentaProducto("12345");
        cuenta2.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO31.getCodigo());
        cuenta2.setClaseCuenta("2");
        cuenta2.setProductoAltair("37");
        cuenta2.setSubproductoAltair("77");
        cuenta2.setNroCuentaProducto("67890");
        listCuentas.add(cuenta1);
        listCuentas.add(cuenta2);
        cliente.setCuentas(listCuentas);

        PreFormalizacion preformalizacion = new PreFormalizacion();
        preformalizacion.setPlazo("24");
        preformalizacion.setCodigoDestinoPrestamo("03335");
        preformalizacion.getPrestamoDebitoAdherido().setImpconce("500000");
        preformalizacion.getPrestamoDebitoAdherido().setNumeroPropuesta("0072312312");

        Respuesta<PreFormalizacion> respuestaPreformalizacion = respuestaFactory.crearRespuestaOk(preformalizacion);

        when(deudaPrestamoDAO.consultarDeudaDePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(prestamo1).thenThrow(DAOException.class);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class))).thenReturn(respuestaPreformalizacion);

        Respuesta<List<PrestamoDTO>> respuesta = prestamoBO.obtenerPrestamos(obtenerPrestamosInDTO);
        List<PrestamoDTO> prestamosDTO = respuesta.getRespuesta();

        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");
        assertNotNull(prestamosDTO);
        assertEquals(1, prestamosDTO.size());
        assertEquals(prestamosDTO.get(0).getPrestamo().getClaseCuenta(),
                TipoPrestamoEnum.HIPOTECARIOS.getDescripcion());
        assertEquals(prestamosDTO.get(0).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.HIPOTECARIOS);
        assertEquals(prestamosDTO.get(0).getPrestamo().getNumeroCuentaProducto(), "12345");
    }

    /**
     * Obtener prestamos uno OK dos vencidos un error test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerPrestamosUnoOKDosVencidosUnErrorTest() throws DAOException {

        Cliente cliente = new Cliente();
        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        Cuenta cuenta4 = new Cuenta();
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setIndex("1");
        Prestamo prestamo2 = new Prestamo();
        prestamo2.setIndex("2");
        Prestamo prestamo3 = new Prestamo();
        prestamo3.setIndex("3");
        Prestamo prestamo4 = new Prestamo();
        prestamo4.setIndex("4");
        Mensaje mensaje1 = new Mensaje();
        Mensaje mensaje2 = new Mensaje();
        ObtenerPrestamosInDTO obtenerPrestamosInDTO = new ObtenerPrestamosInDTO();
        Calendar fechaVencimiento1 = Calendar.getInstance();
        Calendar fechaVencimiento2 = Calendar.getInstance();
        // hoy mas 10 dias
        //fechaVencimiento1.set(2017, 10, 30);
        fechaVencimiento1.add(Calendar.DATE, 10);
        // hoy menos 365*2 dias
        fechaVencimiento2.add(Calendar.DATE, -730);

        prestamo1.setVencimientoCuota(fechaVencimiento1.getTime());
        prestamo2.setVencimientoCuota(fechaVencimiento2.getTime());
        prestamo3.setVencimientoCuota(fechaVencimiento2.getTime());
        prestamo4.setVencimientoCuota(fechaVencimiento1.getTime());
        prestamo2.setNroPrestamo("1");
        prestamo3.setNroPrestamo("2");
        obtenerPrestamosInDTO.setIsTodos(true);
        obtenerPrestamosInDTO.setCliente(cliente);
        mensaje1.setMensaje("Mensaje de error");
        mensaje2.setMensaje("Mensaje de warning");
        cuenta1.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30.getCodigo());
        cuenta1.setClaseCuenta("8");
        cuenta1.setNroCuentaProducto("12345");
        cuenta1.setIndex(1);
        cuenta1.setProductoAltair("0");
        cuenta1.setSubproductoAltair("0");
        cuenta2.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO31.getCodigo());
        cuenta2.setClaseCuenta("2");
        cuenta2.setNroCuentaProducto("67890");
        cuenta2.setIndex(2);
        cuenta2.setProductoAltair("0");
        cuenta2.setSubproductoAltair("0");
        cuenta3.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30.getCodigo());
        cuenta3.setClaseCuenta("8");
        cuenta3.setNroCuentaProducto("42466");
        cuenta3.setIndex(3);
        cuenta3.setProductoAltair("0");
        cuenta3.setSubproductoAltair("0");
        cuenta4.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO31.getCodigo());
        cuenta4.setClaseCuenta("2");
        cuenta4.setNroCuentaProducto("68922");
        cuenta4.setIndex(4);
        cuenta4.setProductoAltair("0");
        cuenta4.setSubproductoAltair("0");
        listCuentas.add(cuenta1);
        listCuentas.add(cuenta2);
        listCuentas.add(cuenta3);
        listCuentas.add(cuenta4);
        cliente.setCuentas(listCuentas);

        PreFormalizacion preformalizacion = new PreFormalizacion();
        preformalizacion.setPlazo("24");
        preformalizacion.setCodigoDestinoPrestamo("03335");
        preformalizacion.getPrestamoDebitoAdherido().setImpconce("500000");
        preformalizacion.getPrestamoDebitoAdherido().setNumeroPropuesta("0072312312");

        Respuesta<PreFormalizacion> respuestaPreformalizacion = respuestaFactory.crearRespuestaOk(preformalizacion);

        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class))).thenReturn(respuestaPreformalizacion);
        when(deudaPrestamoDAO.consultarDeudaDePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(prestamo1, prestamo2, prestamo3).thenThrow(DAOException.class);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje1, mensaje2);

        Respuesta<List<PrestamoDTO>> respuesta = prestamoBO.obtenerPrestamos(obtenerPrestamosInDTO);
        List<PrestamoDTO> prestamosDTO = respuesta.getRespuesta();

        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        assertTrue(respuesta.getItemsMensajeRespuesta().size() == 2);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");
        assertEquals(respuesta.getItemsMensajeRespuesta().get(1).getMensaje(), "Mensaje de warning");
        assertEquals(respuesta.getItemsMensajeRespuesta().get(1).getTag(), "3");
        assertNotNull(prestamosDTO);
        assertTrue(prestamosDTO.size() == 3);
        assertEquals(prestamosDTO.get(0).getPrestamo().getClaseCuenta(),
                TipoPrestamoEnum.HIPOTECARIOS.getDescripcion());
        assertEquals(prestamosDTO.get(0).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.HIPOTECARIOS);
        assertEquals(prestamosDTO.get(0).getPrestamo().getNumeroCuentaProducto(), "12345");
        assertEquals(prestamosDTO.get(1).getPrestamo().getClaseCuenta(), TipoPrestamoEnum.PRENDARIO.getDescripcion());
        assertEquals(prestamosDTO.get(1).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.PRENDARIO);
        assertEquals(prestamosDTO.get(1).getPrestamo().getNumeroCuentaProducto(), "67890");
        assertEquals(prestamosDTO.get(2).getPrestamo().getClaseCuenta(),
                TipoPrestamoEnum.HIPOTECARIOS.getDescripcion());
        assertEquals(prestamosDTO.get(2).getPrestamo().getTipoPrestamoEnum(), TipoPrestamoEnum.HIPOTECARIOS);
        assertEquals(prestamosDTO.get(2).getPrestamo().getNumeroCuentaProducto(), "42466");

    }

    /**
     * Obtengo destinos de prestamos buscando por producto y subproducto.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtengoDestinoPrestamosFiltrando() throws BusinessException, DAOException {

        // when
        String id = "350009ARS35135";
        List<DestinoPrestamo> destinos = crearDestinos();

        // then
        when(destinoPrestamoDAO.obtener()).thenReturn(destinos);
        List<DestinoPrestamoSeleccionView> destinosFiltrados = simuladorPrestamoBo.obtenerDestinosPrestamo("35",
                "0009");

        // expect
        Assert.assertTrue("No se encontraron destinos para el producto con el siguiente id: " + id,
                CollectionUtils.isNotEmpty(destinosFiltrados));

        DestinoPrestamoSeleccionView unDestino = destinosFiltrados.get(0);
        Assert.assertTrue("Los datos no corresponden a la busqueda", id.equals(unDestino.getId()));

    }
    
    @Test
    public void cuandoObtengoPrestamosCanceladosReciboOk() throws DAOException, BusinessException {
    	// When
    	PrestamoCanceladoMock prestamoCanceladoMock = new PrestamoCanceladoMock();
    	PreFormalizacion preformalizacion = new PreFormalizacion();
    	preformalizacion.setPlazo("24");
    	preformalizacion.setCodigoDestinoPrestamo("03335");
    	preformalizacion.getPrestamoDebitoAdherido().setImpconce("500000");
    	Cliente cliente = ClienteMock.completarInfoCliente();
    	when (prestamoDAO.obtenerCancelados(cliente)).thenReturn(prestamoCanceladoMock.getPrestamosCanceladosEntity());
    	when (preFormalizacionPrestamoDAO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.anyString())).thenReturn(preformalizacion);
    	when (destinoPrestamoBO.buscarDescripcionPorCodigoDestinoFondo(preformalizacion.getCodigoDestinoPrestamo())).thenReturn("Compra Casa");
    	
    	// Then
		List<PrestamoCanceladoDTO> prestamosCancelados = prestamoBO.obtenerPrestamoCancelado(cliente);
		
		// Expected
		for (PrestamoCanceladoDTO prestamo : prestamosCancelados) {
			Assert.assertTrue(ValidationEntity.validate(prestamo));
		}
    }
    
    @Test(expected = BusinessException.class)
    public void cuandoObtengoOrestamosCanceladosReciboBusinessException() throws BusinessException, DAOException  {
    	// When
    	Cliente cliente = ClienteMock.completarInfoCliente();
    	when(prestamoDAO.obtenerCancelados(cliente)).thenThrow(new DAOException());
    
    	// Then
    	prestamoBO.obtenerPrestamoCancelado(cliente);
    }

    /**
     * Obtener configuracion simulacion prestamo test.
     *
     * @throws BusinessException
     *             the business exception
     */
    public void obtenerConfiguracionSimulacionPrestamoTest() throws BusinessException {
        Cliente cliente = ClienteMock.completarInfoCliente();

        simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(cliente, cliente.obtenerCuentaPrincipal());
    }
    
    @Test
    public void obtenerCantidadPrestamosIgnorarOpenCreditTest() {
    	
    	Cliente cliente = new Cliente();
    	List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
    	Cuenta cuenta1 = new Cuenta();
    	Cuenta cuenta2 = new Cuenta();
    	
    	cuenta1.setTipoCuenta("30");
    	cuenta1.setClaseCuenta("8");
    	cuenta2.setTipoCuenta("30");
    	cuenta2.setClaseCuenta("O");
		listaCuentas.add(cuenta1);
		listaCuentas.add(cuenta2);
		cliente.setCuentas(listaCuentas);
		InfoPrestamosDTO respuesta = prestamoBO.obtenerCantidadPrestamosPorClase(cliente);
		
		assertEquals(new Integer(1), respuesta.getPrestamosHipotecarios());
    }

    /**
     * Crear destinos.
     *
     * @return the list
     */
    private List<DestinoPrestamo> crearDestinos() {

        DestinoPrestamo destino1 = new DestinoPrestamo();
        destino1.setProductoUG("35");
        destino1.setSubproductoUG("0009");
        destino1.setDivisaProductoUG("ARS");
        destino1.setDestinoDeFondosUG("35135");
        destino1.setDescripcionUG("ADQUISICION MAQUINARIAS, EQUIP Y HERRAM");

        DestinoPrestamo destino2 = new DestinoPrestamo();
        destino2.setProductoUG("35");
        destino2.setSubproductoUG("0013");
        destino2.setDivisaProductoUG("ARS");
        destino2.setDestinoDeFondosUG("35001");
        destino2.setDescripcionUG("VIAJES / TURISMO");

        DestinoPrestamo destino3 = new DestinoPrestamo();
        destino3.setProductoUG("35");
        destino3.setSubproductoUG("0015");
        destino3.setDivisaProductoUG("ARS");
        destino3.setDestinoDeFondosUG("35002");
        destino3.setDescripcionUG("COMPRA DE AUTO");

        List<DestinoPrestamo> destinos = new ArrayList<DestinoPrestamo>();
        destinos.add(destino1);
        destinos.add(destino2);
        destinos.add(destino3);

        return destinos;
    }
    

}
