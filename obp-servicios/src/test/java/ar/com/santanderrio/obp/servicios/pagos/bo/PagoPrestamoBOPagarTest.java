package ar.com.santanderrio.obp.servicios.pagos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.Calendar;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.RespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.PagoPrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.ImporteCuotaHipotecarioUVaException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.NoTieneFondosSuficientesException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoConAnterioridadException;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO;

/**
 * The Class PagoPrestamoBOPagarTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoPrestamoBOPagarTest {

    /** The pago prestamo BO. */
    @InjectMocks
    private PagoPrestamoBOImpl pagoPrestamoBO = new PagoPrestamoBOImpl();

    /** The intervinientes DAO. */
    @Mock
    private IntervinientesDAO intervinientesDAO;

    /** The pago prestamo DAO. */
    @Mock
    private PagoPrestamoDAO pagoPrestamoDAO;

    /** The respuesta BO. */
    @InjectMocks
    @Spy
    private RespuestaBO respuestaBO = new RespuestaBOImpl();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The Mensaje BO. */
    @InjectMocks
    @Spy
    private MensajeBO MensajeBO = new MensajeBOImpl();
    
    @Mock
    private SimuladorPrestamoBO simuladorPrestamoBo;

    /**
     * Pagar test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test
    public void pagarOK() throws DAOException, IllegalAccessException, PagoConAnterioridadException,
            NoTieneFondosSuficientesException, ImporteCuotaHipotecarioUVaException {

    	//When
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        Interviniente interviniente = new Interviniente();
        ComprobantePrestamo comprobantePrestamo = new ComprobantePrestamo();

        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);
        prestamo.setCuenta(cuenta);
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR HORARIO DE OPERACIONES");
        
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPrestamo.setContadorIntentos(contadorIntentos);
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setCuentaSeleccionada(cuenta);

        when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
        when(intervinientesDAO.obtenerIntervinienteTitular(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
        .thenReturn(interviniente);
        when(pagoPrestamoDAO.pagar(Matchers.any(Prestamo.class), Matchers.any(Cuenta.class)))
        .thenReturn(comprobantePrestamo);
        when(mensajeDAO.obtenerMensajePorCodigo("1145")).thenReturn(mensaje);

        //Then
        Respuesta<ComprobantePrestamo> respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 1);

        //Expected
        assertNotNull(respuesta);
        assertNotNull(respuesta.getRespuesta());
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().getInterviniente(), interviniente);
        assertEquals(respuesta.getRespuesta().getCuenta(), cuenta);

    }

    /**
     * Pagar excede reintentos test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test
    public void pagarErrorExcedeReintentos() throws DAOException, IllegalAccessException, PagoConAnterioridadException,
            NoTieneFondosSuficientesException, ImporteCuotaHipotecarioUVaException {

    	//When
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        contadorIntentos.permiteReintentar();
        contadorIntentos.permiteReintentar();
        Interviniente interviniente = new Interviniente();
        ComprobantePrestamo comprobantePrestamo = new ComprobantePrestamo();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");
        
        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);
        prestamo.setCuenta(cuenta);
        
        respuestaMensaje.setRespuesta(mensaje);
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPrestamo.setContadorIntentos(contadorIntentos);
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setCuentaSeleccionada(cuenta);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        Integer horaInicioServer = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        Integer horaFinServer = calendar.get(Calendar.HOUR_OF_DAY);
        FieldUtils.writeField(pagoPrestamoBO, "horaInicioOperaciones", horaInicioServer.toString(), true);
        FieldUtils.writeField(pagoPrestamoBO, "horaFinOperaciones", horaFinServer.toString(), true);

        when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
        when(intervinientesDAO.obtenerIntervinienteTitular(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(interviniente);
        when(pagoPrestamoDAO.pagar(Matchers.any(Prestamo.class), Matchers.any(Cuenta.class)))
                .thenReturn(comprobantePrestamo);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        //Then
        Respuesta<ComprobantePrestamo> respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 1);

        //Expected
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertSame(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_PAGO_PRESTAMO.getDescripcion());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");
        verify(mensajeDAO).obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_PAGO_PRESTAMO);

    }

    /**
     * Pagar fuera horario test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test
    public void pagarErrorFueraHorario() throws DAOException, IllegalAccessException, PagoConAnterioridadException,
            NoTieneFondosSuficientesException, ImporteCuotaHipotecarioUVaException {

    	//When
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        Interviniente interviniente = new Interviniente();
        ComprobantePrestamo comprobantePrestamo = new ComprobantePrestamo();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();

        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);
        prestamo.setCuenta(cuenta);
        
        mensaje.setMensaje("Mensaje de error El horario es de {0} a {1}.");
        respuestaMensaje.setRespuesta(mensaje);
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPrestamo.setContadorIntentos(contadorIntentos);
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setCuentaSeleccionada(cuenta);
        String horaInicioServer = "01:00";
        String horaFinServer = "08:00";
        FieldUtils.writeField(pagoPrestamoBO, "horaInicioOperaciones", horaInicioServer, true);
        FieldUtils.writeField(pagoPrestamoBO, "horaFinOperaciones", horaFinServer, true);

        when(intervinientesDAO.obtenerIntervinienteTitular(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(interviniente);
        when(pagoPrestamoDAO.pagar(Matchers.any(Prestamo.class), Matchers.any(Cuenta.class)))
                .thenReturn(comprobantePrestamo);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(false);
        
        //Then
        Respuesta<ComprobantePrestamo> respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 1);

        //Expected
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_HORARIO_DE_OPERACIONES.getDescripcion());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(),
                "Mensaje de error El horario es de " + horaInicioServer + " a " + horaFinServer + ".");
        verify(mensajeDAO).obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_HORARIO_DE_OPERACIONES);

    }

    /**
     * Pagar interviniente null test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test
    public void pagarErrorIntervinienteNull() throws DAOException, IllegalAccessException, PagoConAnterioridadException,
            NoTieneFondosSuficientesException, ImporteCuotaHipotecarioUVaException {

    	//When
    	PagoPrestamo pagoPrestamo = new PagoPrestamo(); 
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        ComprobantePrestamo comprobantePrestamo = new ComprobantePrestamo();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        
        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);
        prestamo.setCuenta(cuenta);
        
        mensaje.setMensaje("Mensaje de error");
        respuestaMensaje.setRespuesta(mensaje);
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPrestamo.setContadorIntentos(contadorIntentos);
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setCuentaSeleccionada(cuenta);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        Integer horaInicioServer = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        Integer horaFinServer = calendar.get(Calendar.HOUR_OF_DAY);
        FieldUtils.writeField(pagoPrestamoBO, "horaInicioOperaciones", horaInicioServer.toString(), true);
        FieldUtils.writeField(pagoPrestamoBO, "horaFinOperaciones", horaFinServer.toString(), true);

        when(pagoPrestamoDAO.pagar(Matchers.any(Prestamo.class), Matchers.any(Cuenta.class)))
                .thenReturn(comprobantePrestamo);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        //Then
        Respuesta<ComprobantePrestamo> respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 1);

        //Expected
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertSame(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_PAGO_PRESTAMO.getDescripcion());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");

    }

    /**
     * Pagar pago anterioridad exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @SuppressWarnings("unchecked")
	@Test
    public void pagarPagoAnterioridadExceptionTest() throws DAOException, IllegalAccessException,
            PagoConAnterioridadException, NoTieneFondosSuficientesException, ImporteCuotaHipotecarioUVaException {

    	//When
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        Interviniente interviniente = new Interviniente();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");
        
        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);
        prestamo.setCuenta(cuenta);
        
        respuestaMensaje.setRespuesta(mensaje);
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPrestamo.setContadorIntentos(contadorIntentos);
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setCuentaSeleccionada(cuenta);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        Integer horaInicioServer = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        Integer horaFinServer = calendar.get(Calendar.HOUR_OF_DAY);
        FieldUtils.writeField(pagoPrestamoBO, "horaInicioOperaciones", horaInicioServer.toString(), true);
        FieldUtils.writeField(pagoPrestamoBO, "horaFinOperaciones", horaFinServer.toString(), true);

        when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
        when(intervinientesDAO.obtenerIntervinienteTitular(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(interviniente);
        when(pagoPrestamoDAO.pagar(Matchers.any(Prestamo.class), Matchers.any(Cuenta.class)))
                .thenThrow(PagoConAnterioridadException.class);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        //Then
        Respuesta<ComprobantePrestamo> respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 1);

        //Expected
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertSame(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.ERROR_PAGO_CON_ANTERIORIDAD.getDescripcion());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");

        verify(mensajeDAO).obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_PAGO_CON_ANTERIORIDAD);

    }

    /**
     * Pagar fondos insuficientes exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @SuppressWarnings("unchecked")
	@Test
    public void pagarFondosInsuficientesExceptionTest() throws DAOException, IllegalAccessException,
            PagoConAnterioridadException, NoTieneFondosSuficientesException, ImporteCuotaHipotecarioUVaException {

    	//When
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        Interviniente interviniente = new Interviniente();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        
        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);
        prestamo.setCuenta(cuenta);
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");
        
        respuestaMensaje.setRespuesta(mensaje);
        cuenta.setNroSucursal("123");
        cuenta.setNroCuentaProducto("1234567");
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPrestamo.setContadorIntentos(contadorIntentos);
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setCuentaSeleccionada(cuenta);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        Integer horaInicioServer = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        Integer horaFinServer = calendar.get(Calendar.HOUR_OF_DAY);
        FieldUtils.writeField(pagoPrestamoBO, "horaInicioOperaciones", horaInicioServer.toString(), true);
        FieldUtils.writeField(pagoPrestamoBO, "horaFinOperaciones", horaFinServer.toString(), true);

        when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
        when(intervinientesDAO.obtenerIntervinienteTitular(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(interviniente);
        when(pagoPrestamoDAO.pagar(Matchers.any(Prestamo.class), Matchers.any(Cuenta.class)))
                .thenThrow(NoTieneFondosSuficientesException.class);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        //Then
        Respuesta<ComprobantePrestamo> respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 1);

        //Expected
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertSame(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_SALDOS_INSUFICIENTES.getDescripcion());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "123-123456/7");

        //Then
        respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 2);

        //Expected
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertSame(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_SALDOS_INSUFICIENTES_VARIAS_CUENTAS.getDescripcion());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");

        verify(mensajeDAO).obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_SALDOS_INSUFICIENTES);
        verify(mensajeDAO).obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_SALDOS_INSUFICIENTES_VARIAS_CUENTAS);

    }

    /**
     * Pagar DAO exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @SuppressWarnings("unchecked")
	@Test
    public void pagarDAOExceptionTest() throws DAOException, IllegalAccessException, PagoConAnterioridadException,
            NoTieneFondosSuficientesException, ImporteCuotaHipotecarioUVaException {

    	//When
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        Interviniente interviniente = new Interviniente();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");
        
        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);
        prestamo.setCuenta(cuenta);
        
        respuestaMensaje.setRespuesta(mensaje);
        cuenta.setNroSucursal("123");
        cuenta.setNroCuentaProducto("1234567");
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPrestamo.setContadorIntentos(contadorIntentos);
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setCuentaSeleccionada(cuenta);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        Integer horaInicioServer = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        Integer horaFinServer = calendar.get(Calendar.HOUR_OF_DAY);
        FieldUtils.writeField(pagoPrestamoBO, "horaInicioOperaciones", horaInicioServer.toString(), true);
        FieldUtils.writeField(pagoPrestamoBO, "horaFinOperaciones", horaFinServer.toString(), true);

        when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
        when(intervinientesDAO.obtenerIntervinienteTitular(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(interviniente);
        when(pagoPrestamoDAO.pagar(Matchers.any(Prestamo.class), Matchers.any(Cuenta.class)))
                .thenThrow(DAOException.class);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        //Then
        Respuesta<ComprobantePrestamo> respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 1);

        //Expected
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertSame(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), 
        		TipoError.ERROR_PAGO_PRESTAMO_CON_REINTENTO.getDescripcion());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");
        
        //Then
        respuesta = pagoPrestamoBO.pagar(pagoPrestamo, 1);

        //Expected
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertSame(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.ERROR_PAGO_PRESTAMO.getDescripcion());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");

        // Verifica que se haya llamado 2 veces, una por cada prueba que se hizo en este método
        verify(mensajeDAO, times(2)).obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_PAGO_PRESTAMO);
    }

}
