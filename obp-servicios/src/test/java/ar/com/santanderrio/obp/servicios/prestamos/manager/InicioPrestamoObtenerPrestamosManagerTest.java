/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.manager;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import org.apache.commons.lang3.reflect.FieldUtils;
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
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPrestamoDebitoAdherido;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ObtenerPrestamosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamosDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.ObtenerPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoOpenCreditManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.InicioPrestamoManagerImpl;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;

/**
 * The Class InicioPrestamoObtenerPrestamosManagerTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
public class InicioPrestamoObtenerPrestamosManagerTest {

    /** The prestamo manager. */
    @InjectMocks
    private InicioPrestamoManagerImpl prestamoManager;

    /** The prestamo BO. */
    @Mock
    private PrestamoBO prestamoBO;

    /** The pre formalizacion prestamo BO. */
    @Mock
    private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private DestinoPrestamoBO destinoPrestamoBO;
    
    @Mock
    private PrestamoOpenCreditManager prestamoOpenCreditManager;
    
    @Mock
    private LegalBO legalBO;
    

    /**
     * Obtener prestamos.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws DAOException 
     */
    @Test
    public void obtenerPrestamos() throws BusinessException, IllegalAccessException, DAOException {
        Respuesta<List<PrestamoDTO>> prestamosDTOs = new Respuesta<List<PrestamoDTO>>();
        Respuesta<CalificacionCrediticiaDTO> respuestaCalificacionDTO = new Respuesta<CalificacionCrediticiaDTO>();
        CalificacionCrediticiaDTO calificacionDTO = new CalificacionCrediticiaDTO();
        ObtenerPrestamoInView consultaPrestamo = new ObtenerPrestamoInView();
        RegistroSesion registroSession = new RegistroSesion();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        Cliente cliente = new Cliente();
        List<Prestamo> prestamos = new ArrayList<Prestamo>();
        List<PrestamoDTO> prestamosDTO = new ArrayList<PrestamoDTO>();
        Cuenta cuenta = new Cuenta();
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();

        calificacionDTO.setLineaActualDisponible(BigDecimal.TEN);
        calificacionDTO.setLineaTotalCrediticia(BigDecimal.TEN);

        cuenta.setNroSucursal("033");

        consultaPrestamo.setIsPersonal(Boolean.TRUE);
        consultaPrestamo.setIsHipotecario(Boolean.FALSE);
        consultaPrestamo.setIsPrendario(Boolean.FALSE);

        cuentaPrestamoDebitoAdherido.setFechaInicio("2014-09-17");
        cuentaPrestamoDebitoAdherido.setMontoAPagar("00000000010000000");
        preFormalizacion.setPlazo("12");
        preFormalizacion.setPrestamoDebitoAdherido(cuentaPrestamoDebitoAdherido);
        
        respuestaCalificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCalificacionDTO.setRespuesta(calificacionDTO);

        Prestamo prestamo = new Prestamo();
        prestamo.setNroPrestamo("022-345645");
        prestamo.setNumeroCuentaProducto("022334566");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("12");
        prestamo.setClaseCuenta("SUPER");
        prestamo.setVencimientoCuota(new Date());
        prestamo.setImporteTotalCuota(new BigDecimal(100));
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.HIPOTECARIOS);
        prestamo.setIsRefinanciacion(false);
        prestamo.setNroExp("");
        prestamos.add(prestamo);

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, preFormalizacion);
        prestamosDTO.add(prestamoDTO);

        prestamosDTOs.setEstadoRespuesta(EstadoRespuesta.OK);
        prestamosDTOs.setRespuesta(prestamosDTO);
        prestamosDTOs.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        FieldUtils.writeField(registroSession, "dispositivo", "phone", true);
        
        
        List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuenta);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
        
        
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
      /////////////////////  when(sesionParametros.getCalificacionCrediticiaDTO()).thenReturn(calificacionDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preFormalizacion);
        when(destinoPrestamoBO.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString()))
                .thenReturn("Viajes y turismo");
        when(prestamoBO.obtenerPrestamos(Matchers.any(ObtenerPrestamosInDTO.class))).thenReturn(prestamosDTOs);
        when(prestamoBO.validarLineaCrediticia(calificacionDTO, prestamosDTOs.getRespuesta().size(),
                cliente)).thenReturn(respuestaCalificacionDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        String legal = "legal";
        when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(legal);

        //OLYMPUS OpenCredit
        Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit = new Respuesta<PrestamosOpenCreditView>();
        respuestaPrestamosOpenCredit.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoOpenCreditManager.obtenerPrestamosOpenCredit()).thenReturn(respuestaPrestamosOpenCredit);
        when(prestamoBO.getPrestamoCom12123(Matchers.any(Prestamo.class))).thenReturn(Boolean.FALSE);
        
        Respuesta<PrestamosView> respuesta = prestamoManager.obtenerPrestamos(consultaPrestamo);

        PrestamosView prestamosView = respuesta.getRespuesta();
        Assert.assertNotNull(respuesta);
        Assert.assertNotNull(prestamosView);
        Assert.assertEquals(prestamosView.getPrestamos().get(0).getCuota(), "12");
        Assert.assertEquals(prestamosView.getPrestamos().get(0).getPlazo(), "12");
        Assert.assertEquals(prestamosView.getPrestamos().get(0).getImpoteMaximaCuota(), "100,00");
        Assert.assertEquals(prestamosView.getPrestamos().get(0).getTipoPrestamo(), "Súper Préstamo Hipotecario");
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener prestamos with error en plazo.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws DAOException 
     */
    @Test
    public void obtenerPrestamosWithErrorEnPlazo() throws BusinessException, IllegalAccessException, DAOException {
        Respuesta<List<PrestamoDTO>> prestamosDTOs = new Respuesta<List<PrestamoDTO>>();
        Respuesta<CalificacionCrediticiaDTO> respuestaCalificacionDTO = new Respuesta<CalificacionCrediticiaDTO>();
        CalificacionCrediticiaDTO calificacionDTO = new CalificacionCrediticiaDTO();
        ObtenerPrestamoInView consultaPrestamo = new ObtenerPrestamoInView();
        Respuesta<PreFormalizacion> respPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        RegistroSesion registroSession = new RegistroSesion();

        Cliente cliente = new Cliente();
        List<PrestamoDTO> prestamosDTO = new ArrayList<PrestamoDTO>();
        Cuenta cuenta = new Cuenta();

        calificacionDTO.setLineaActualDisponible(BigDecimal.TEN);
        calificacionDTO.setLineaTotalCrediticia(BigDecimal.TEN);

        respPreformalizacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respPreformalizacion.setRespuesta(preFormalizacion);
        respuestaCalificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCalificacionDTO.setRespuesta(calificacionDTO);

        consultaPrestamo.setIsPersonal(Boolean.TRUE);
        consultaPrestamo.setIsHipotecario(Boolean.FALSE);
        consultaPrestamo.setIsPrendario(Boolean.FALSE);

        cuenta.setNroSucursal("033");

        Prestamo prestamo = new Prestamo();
        prestamo.setNroPrestamo("022-345645");
        prestamo.setNumeroCuentaProducto("022334566");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("12");
        prestamo.setClaseCuenta("SUPER");
        prestamo.setVencimientoCuota(new Date());
        prestamo.setImporteTotalCuota(new BigDecimal(100));
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setIsRefinanciacion(false);
        prestamo.setNroExp("");

        PrestamoDTO prestamoDTO = new PrestamoDTO();
        prestamoDTO.setPrestamo(prestamo);
        prestamoDTO.setPreFormalizacion(null);
        prestamosDTO.add(prestamoDTO);

        prestamosDTOs.setEstadoRespuesta(EstadoRespuesta.WARNING);
        prestamosDTOs.setRespuesta(prestamosDTO);
        prestamosDTOs.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        FieldUtils.writeField(registroSession, "dispositivo", "phone", true);
        
        List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuenta);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
    	
    	
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respPreformalizacion);
        when(prestamoBO.obtenerPrestamos(Matchers.any(ObtenerPrestamosInDTO.class))).thenReturn(prestamosDTOs);
        when(prestamoBO.validarLineaCrediticia(calificacionDTO, prestamosDTOs.getRespuesta().size(),
                cliente)).thenReturn(respuestaCalificacionDTO);
        when(prestamoBO.getPrestamoCom12123(Matchers.any(Prestamo.class))).thenReturn(Boolean.FALSE);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        String legal = "legal";
        when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(legal);

        //OLYMPUS OpenCredit
        Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit = new Respuesta<PrestamosOpenCreditView>();
        respuestaPrestamosOpenCredit.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoOpenCreditManager.obtenerPrestamosOpenCredit()).thenReturn(respuestaPrestamosOpenCredit);
        
        
        Respuesta<PrestamosView> respuesta = prestamoManager.obtenerPrestamos(consultaPrestamo);

        PrestamosView prestamosView = respuesta.getRespuesta();
        Assert.assertNotNull(respuesta);
        Assert.assertNotNull(prestamosView);
        Assert.assertEquals(prestamosView.getPrestamos().get(0).getCuota(), "12");
        Assert.assertEquals(prestamosView.getPrestamos().get(0).getPlazo(), "-");
        Assert.assertEquals(prestamosView.getPrestamos().get(0).getImpoteMaximaCuota(), "100,00");
        Assert.assertEquals(prestamosView.getPrestamos().get(0).getTipoPrestamo(), "Súper Préstamo Personal");
        Assert.assertEquals(TipoError.ERROR_PLAZO_PRESTAMOS.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
    }

    /**
     * Obtener prestamos with calificacion crediticia no registros.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerPrestamosWithCalificacionCrediticiaNoRegistros()
            throws BusinessException, IllegalAccessException {
        Respuesta<List<PrestamoDTO>> prestamosDTOs = new Respuesta<List<PrestamoDTO>>();
        Respuesta<CalificacionCrediticiaDTO> respuestaCalificacionDTO = new Respuesta<CalificacionCrediticiaDTO>();
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        CalificacionCrediticiaDTO calificacionDTO = new CalificacionCrediticiaDTO();
        ObtenerPrestamoInView consultaPrestamo = new ObtenerPrestamoInView();
        Respuesta<PreFormalizacion> respPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        RegistroSesion registroSession = new RegistroSesion();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        Cliente cliente = new Cliente();
        List<PrestamoDTO> prestamos = new ArrayList<PrestamoDTO>();
        PrestamoDTO prestamosDTO = new PrestamoDTO();
        Cuenta cuenta = new Cuenta();

        calificacionDTO.setLineaActualDisponible(BigDecimal.ZERO);
        calificacionDTO.setLineaTotalCrediticia(BigDecimal.ZERO);

        itemMensajeRespuesta
                .setTipoError(TipoError.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL_NO_REGISTROS.getDescripcion());
        itemMensajeRespuesta.setMensaje("XXX");

        respPreformalizacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respPreformalizacion.setRespuesta(preFormalizacion);

        itemsMensajeRespuesta.add(itemMensajeRespuesta);
        respuestaCalificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaCalificacionDTO.setItemMensajeRespuesta(itemsMensajeRespuesta);
        respuestaCalificacionDTO.setRespuesta(calificacionDTO);

        consultaPrestamo.setIsPersonal(Boolean.TRUE);
        consultaPrestamo.setIsHipotecario(Boolean.FALSE);
        consultaPrestamo.setIsPrendario(Boolean.FALSE);

        cuenta.setNroSucursal("033");

        prestamosDTOs.setEstadoRespuesta(EstadoRespuesta.OK);
        prestamosDTOs.setRespuesta(prestamos);
        prestamosDTOs.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        FieldUtils.writeField(registroSession, "dispositivo", "phone", true);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
      /////////////////////////  when(sesionParametros.getCalificacionCrediticiaDTO()).thenReturn(calificacionDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respPreformalizacion);
        when(prestamoBO.obtenerPrestamos(Matchers.any(ObtenerPrestamosInDTO.class))).thenReturn(prestamosDTOs);
        when(prestamoBO.validarLineaCrediticia(calificacionDTO, prestamosDTOs.getRespuesta().size(),
                cliente)).thenReturn(respuestaCalificacionDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        //OLYMPUS OpenCredit
        Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit = new Respuesta<PrestamosOpenCreditView>();
        respuestaPrestamosOpenCredit.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoOpenCreditManager.obtenerPrestamosOpenCredit()).thenReturn(respuestaPrestamosOpenCredit);
        
        Respuesta<PrestamosView> respuesta = prestamoManager.obtenerPrestamos(consultaPrestamo);

        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.getItemsMensajeRespuesta().size() > 0);
        Assert.assertEquals(TipoError.ERROR_VALIDACION_NO_REGISTROS.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener prestamos calificacion crediticia zero ambos.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws DAOException 
     */
    @Test
    public void obtenerPrestamosCalificacionCrediticiaZeroAmbos() throws BusinessException, IllegalAccessException, DAOException {
        Respuesta<List<PrestamoDTO>> prestamosDTOs = new Respuesta<List<PrestamoDTO>>();
        Respuesta<CalificacionCrediticiaDTO> respuestaCalificacionDTO = new Respuesta<CalificacionCrediticiaDTO>();
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        CalificacionCrediticiaDTO calificacionDTO = new CalificacionCrediticiaDTO();
        ObtenerPrestamoInView consultaPrestamo = new ObtenerPrestamoInView();
        Respuesta<PreFormalizacion> respPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        RegistroSesion registroSession = new RegistroSesion();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        Cliente cliente = new Cliente();
        List<PrestamoDTO> prestamos = new ArrayList<PrestamoDTO>();
        Cuenta cuenta = new Cuenta();

        calificacionDTO.setLineaActualDisponible(BigDecimal.ZERO);
        calificacionDTO.setLineaTotalCrediticia(BigDecimal.ZERO);

        itemMensajeRespuesta.setTipoError(TipoError.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL.getDescripcion());
        itemMensajeRespuesta.setMensaje("XXX");

        respPreformalizacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respPreformalizacion.setRespuesta(preFormalizacion);

        itemsMensajeRespuesta.add(itemMensajeRespuesta);
        respuestaCalificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaCalificacionDTO.setItemMensajeRespuesta(itemsMensajeRespuesta);
        respuestaCalificacionDTO.setRespuesta(calificacionDTO);

        consultaPrestamo.setIsPersonal(Boolean.TRUE);
        consultaPrestamo.setIsHipotecario(Boolean.FALSE);
        consultaPrestamo.setIsPrendario(Boolean.FALSE);

        cuenta.setNroSucursal("033");

        Prestamo prestamo = new Prestamo();
        prestamo.setNroPrestamo("022-345645");
        prestamo.setNumeroCuentaProducto("022334566");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("12");
        prestamo.setClaseCuenta("SUPER");
        prestamo.setVencimientoCuota(new Date());
        prestamo.setImporteTotalCuota(new BigDecimal(100));
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.HIPOTECARIOS);
        prestamo.setIsRefinanciacion(false);
        prestamo.setNroExp("");

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, null);
        prestamos.add(prestamoDTO);

        prestamosDTOs.setEstadoRespuesta(EstadoRespuesta.OK);
        prestamosDTOs.setRespuesta(prestamos);
        prestamosDTOs.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        FieldUtils.writeField(registroSession, "dispositivo", "phone", true);
        List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuenta);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
    	
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
//////////////////////        when(sesionParametros.getCalificacionCrediticiaDTO()).thenReturn(calificacionDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respPreformalizacion);
        when(prestamoBO.obtenerPrestamos(Matchers.any(ObtenerPrestamosInDTO.class))).thenReturn(prestamosDTOs);
        when(prestamoBO.validarLineaCrediticia(calificacionDTO, prestamosDTOs.getRespuesta().size(),
                cliente)).thenReturn(respuestaCalificacionDTO);
        when(prestamoBO.getPrestamoCom12123(Matchers.any(Prestamo.class))).thenReturn(Boolean.FALSE);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        
        String legal = "legal";
        when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(legal);

        //OLYMPUS OpenCredit
        Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit = new Respuesta<PrestamosOpenCreditView>();
        respuestaPrestamosOpenCredit.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoOpenCreditManager.obtenerPrestamosOpenCredit()).thenReturn(respuestaPrestamosOpenCredit);
        
        Respuesta<PrestamosView> respuesta = prestamoManager.obtenerPrestamos(consultaPrestamo);

        PrestamosView prestamosView = respuesta.getRespuesta();
        Assert.assertNotNull(respuesta);
        Assert.assertNotNull(prestamosView);
        Assert.assertTrue(respuesta.getItemsMensajeRespuesta().size() >= 1);
        Assert.assertEquals(TipoError.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener prestamos calificacion crediticia linea disponible mayor zero.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerPrestamosCalificacionCrediticiaLineaDisponibleMayorZero()
            throws BusinessException, IllegalAccessException {
        Respuesta<List<PrestamoDTO>> prestamosDTOs = new Respuesta<List<PrestamoDTO>>();
        Respuesta<CalificacionCrediticiaDTO> respuestaCalificacionDTO = new Respuesta<CalificacionCrediticiaDTO>();
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        CalificacionCrediticiaDTO calificacionDTO = new CalificacionCrediticiaDTO();
        ObtenerPrestamoInView consultaPrestamo = new ObtenerPrestamoInView();
        Respuesta<PreFormalizacion> respPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        RegistroSesion registroSession = new RegistroSesion();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        Cliente cliente = new Cliente();
        List<PrestamoDTO> prestamos = new ArrayList<PrestamoDTO>();
        Cuenta cuenta = new Cuenta();

        calificacionDTO.setLineaActualDisponible(BigDecimal.TEN);
        calificacionDTO.setLineaTotalCrediticia(BigDecimal.ZERO);

        itemMensajeRespuesta.setTipoError(TipoError.ERROR_VALIDACION_NO_DISPONIBLE.getDescripcion());
        itemMensajeRespuesta.setMensaje("XXX");

        respPreformalizacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respPreformalizacion.setRespuesta(preFormalizacion);

        itemsMensajeRespuesta.add(itemMensajeRespuesta);
        respuestaCalificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaCalificacionDTO.setItemMensajeRespuesta(itemsMensajeRespuesta);
        respuestaCalificacionDTO.setRespuesta(calificacionDTO);

        consultaPrestamo.setIsPersonal(Boolean.TRUE);
        consultaPrestamo.setIsHipotecario(Boolean.FALSE);
        consultaPrestamo.setIsPrendario(Boolean.FALSE);

        cuenta.setNroSucursal("033");

        Prestamo prestamo = new Prestamo();
        prestamo.setNroPrestamo("022-345645");
        prestamo.setNumeroCuentaProducto("022334566");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("12");
        prestamo.setClaseCuenta("SUPER");
        prestamo.setVencimientoCuota(new Date());
        prestamo.setImporteTotalCuota(new BigDecimal(100));
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.HIPOTECARIOS);
        prestamo.setIsRefinanciacion(false);
        prestamo.setNroExp("");
        
        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, null);
        prestamos.add(prestamoDTO);

        prestamosDTOs.setEstadoRespuesta(EstadoRespuesta.OK);
        prestamosDTOs.setRespuesta(prestamos);
        prestamosDTOs.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        FieldUtils.writeField(registroSession, "dispositivo", "phone", true);
        
        List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuenta);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
    ////////////////////////    when(sesionParametros.getCalificacionCrediticiaDTO()).thenReturn(calificacionDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respPreformalizacion);
        when(prestamoBO.obtenerPrestamos(Matchers.any(ObtenerPrestamosInDTO.class))).thenReturn(prestamosDTOs);
        when(prestamoBO.validarLineaCrediticia(calificacionDTO, prestamosDTOs.getRespuesta().size(),
                cliente)).thenReturn(respuestaCalificacionDTO);
        when(prestamoBO.getPrestamoCom12123(Matchers.any(Prestamo.class))).thenReturn(Boolean.FALSE);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        
        //OLYMPUS OpenCredit
        Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit = new Respuesta<PrestamosOpenCreditView>();
        respuestaPrestamosOpenCredit.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoOpenCreditManager.obtenerPrestamosOpenCredit()).thenReturn(respuestaPrestamosOpenCredit);
        
        Respuesta<PrestamosView> respuesta = prestamoManager.obtenerPrestamos(consultaPrestamo);

        PrestamosView prestamosView = respuesta.getRespuesta();
        Assert.assertNotNull(respuesta);
        Assert.assertNotNull(prestamosView);
        Assert.assertTrue(respuesta.getItemsMensajeRespuesta().size() >= 1);
        Assert.assertEquals(TipoError.ERROR_VALIDACION_NO_DISPONIBLE.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener prestamos calificacion crediticia linea hipotecarios.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerPrestamosCalificacionCrediticiaLineaHipotecarios()
            throws BusinessException, IllegalAccessException {
        Respuesta<List<PrestamoDTO>> prestamosDTOs = new Respuesta<List<PrestamoDTO>>();
        Respuesta<CalificacionCrediticiaDTO> respuestaCalificacionDTO = new Respuesta<CalificacionCrediticiaDTO>();
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        CalificacionCrediticiaDTO calificacionDTO = new CalificacionCrediticiaDTO();
        ObtenerPrestamoInView consultaPrestamo = new ObtenerPrestamoInView();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        RegistroSesion registroSession = new RegistroSesion();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();

        Cliente cliente = new Cliente();
        List<PrestamoDTO> prestamos = new ArrayList<PrestamoDTO>();
        Cuenta cuenta = new Cuenta();

        calificacionDTO.setLineaActualDisponible(BigDecimal.TEN);
        calificacionDTO.setLineaTotalCrediticia(BigDecimal.ZERO);

        itemMensajeRespuesta.setTipoError(TipoError.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL.getDescripcion());
        itemMensajeRespuesta.setMensaje("XXX");

        cuentaPrestamoDebitoAdherido.setMontoAPagar("1000");
        cuentaPrestamoDebitoAdherido.setFechaInicio("01/01/2020");
        preFormalizacion.setPlazo("12");
        preFormalizacion.setPrestamoDebitoAdherido(cuentaPrestamoDebitoAdherido);

        itemsMensajeRespuesta.add(itemMensajeRespuesta);
        respuestaCalificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaCalificacionDTO.setItemMensajeRespuesta(itemsMensajeRespuesta);
        respuestaCalificacionDTO.setRespuesta(calificacionDTO);

        consultaPrestamo.setIsPersonal(Boolean.FALSE);
        consultaPrestamo.setIsHipotecario(Boolean.TRUE);
        consultaPrestamo.setIsPrendario(Boolean.FALSE);

        cuenta.setNroSucursal("033");

        Prestamo prestamo = new Prestamo();
        prestamo.setNroPrestamo("022-345645");
        prestamo.setNumeroCuentaProducto("022334566");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("12");
        prestamo.setClaseCuenta("SUPER");
        prestamo.setNroExp("");
        prestamo.setVencimientoCuota(new Date());
        prestamo.setImporteTotalCuota(new BigDecimal(100));
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.HIPOTECARIOS);
        prestamo.setIsRefinanciacion(false);

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, preFormalizacion);
        prestamos.add(prestamoDTO);

        prestamosDTOs.setEstadoRespuesta(EstadoRespuesta.OK);
        prestamosDTOs.setRespuesta(prestamos);
        prestamosDTOs.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        FieldUtils.writeField(registroSession, "dispositivo", "phone", true);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
   //////////////     when(sesionParametros.getCalificacionCrediticiaDTO()).thenReturn(calificacionDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preFormalizacion);
        when(prestamoBO.obtenerPrestamos(Matchers.any(ObtenerPrestamosInDTO.class))).thenReturn(prestamosDTOs);
        when(prestamoBO.validarLineaCrediticia(calificacionDTO, prestamosDTOs.getRespuesta().size(),
                cliente)).thenReturn(respuestaCalificacionDTO);
        when(prestamoBO.validarLineaCrediticia(calificacionDTO, prestamosDTOs.getRespuesta().size(),
                cliente)).thenReturn(respuestaCalificacionDTO);
        when(prestamoBO.getPrestamoCom12123(Matchers.any(Prestamo.class))).thenReturn(Boolean.FALSE);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        
        //OLYMPUS OpenCredit
        Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit = new Respuesta<PrestamosOpenCreditView>();
        respuestaPrestamosOpenCredit.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoOpenCreditManager.obtenerPrestamosOpenCredit()).thenReturn(respuestaPrestamosOpenCredit);
        
        Respuesta<PrestamosView> respuesta = prestamoManager.obtenerPrestamos(consultaPrestamo);

        Assert.assertNotNull(respuesta);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS_HIPOTECARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener prestamos calificacion crediticia linea prendarios.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerPrestamosCalificacionCrediticiaLineaPrendarios()
            throws BusinessException, IllegalAccessException {
        Respuesta<List<PrestamoDTO>> prestamosDTOs = new Respuesta<List<PrestamoDTO>>();
        Respuesta<CalificacionCrediticiaDTO> respuestaCalificacionDTO = new Respuesta<CalificacionCrediticiaDTO>();
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        CalificacionCrediticiaDTO calificacionDTO = new CalificacionCrediticiaDTO();
        ObtenerPrestamoInView consultaPrestamo = new ObtenerPrestamoInView();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        RegistroSesion registroSession = new RegistroSesion();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();

        Cliente cliente = new Cliente();
        List<PrestamoDTO> prestamos = new ArrayList<PrestamoDTO>();
        Cuenta cuenta = new Cuenta();

        calificacionDTO.setLineaActualDisponible(BigDecimal.TEN);
        calificacionDTO.setLineaTotalCrediticia(BigDecimal.ZERO);

        itemMensajeRespuesta.setTipoError(TipoError.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL.getDescripcion());
        itemMensajeRespuesta.setMensaje("XXX");

        cuentaPrestamoDebitoAdherido.setMontoAPagar("1000");
        cuentaPrestamoDebitoAdherido.setFechaInicio("01/01/2020");
        preFormalizacion.setPlazo("12");
        preFormalizacion.setPrestamoDebitoAdherido(cuentaPrestamoDebitoAdherido);

        itemsMensajeRespuesta.add(itemMensajeRespuesta);
        respuestaCalificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaCalificacionDTO.setItemMensajeRespuesta(itemsMensajeRespuesta);
        respuestaCalificacionDTO.setRespuesta(calificacionDTO);

        consultaPrestamo.setIsPersonal(Boolean.FALSE);
        consultaPrestamo.setIsHipotecario(Boolean.FALSE);
        consultaPrestamo.setIsPrendario(Boolean.TRUE);

        cuenta.setNroSucursal("033");

        Prestamo prestamo = new Prestamo();
        prestamo.setNroPrestamo("022-345645");
        prestamo.setNumeroCuentaProducto("022334566");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("12");
        prestamo.setClaseCuenta("SUPER");
        prestamo.setVencimientoCuota(new Date());
        prestamo.setImporteTotalCuota(new BigDecimal(100));
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.HIPOTECARIOS);
        prestamo.setIsRefinanciacion(false);
        prestamo.setNroExp("");

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, preFormalizacion);
        prestamos.add(prestamoDTO);

        prestamosDTOs.setEstadoRespuesta(EstadoRespuesta.OK);
        prestamosDTOs.setRespuesta(prestamos);
        prestamosDTOs.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        FieldUtils.writeField(registroSession, "dispositivo", "phone", true);
        List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuenta);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
  ////////////////      when(sesionParametros.getCalificacionCrediticiaDTO()).thenReturn(calificacionDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preFormalizacion);

        when(prestamoBO.obtenerPrestamos(Matchers.any(ObtenerPrestamosInDTO.class))).thenReturn(prestamosDTOs);
        when(prestamoBO.validarLineaCrediticia(calificacionDTO, prestamosDTOs.getRespuesta().size(),
                cliente)).thenReturn(respuestaCalificacionDTO);
        when(prestamoBO.getPrestamoCom12123(Matchers.any(Prestamo.class))).thenReturn(Boolean.FALSE);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        
        //OLYMPUS OpenCredit
        Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit = new Respuesta<PrestamosOpenCreditView>();
        respuestaPrestamosOpenCredit.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoOpenCreditManager.obtenerPrestamosOpenCredit()).thenReturn(respuestaPrestamosOpenCredit);
        
        Respuesta<PrestamosView> respuesta = prestamoManager.obtenerPrestamos(consultaPrestamo);

        PrestamosView prestamosView = respuesta.getRespuesta();
        Assert.assertNotNull(respuesta);
        Assert.assertNotNull(prestamosView);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS_PRENDARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

}
