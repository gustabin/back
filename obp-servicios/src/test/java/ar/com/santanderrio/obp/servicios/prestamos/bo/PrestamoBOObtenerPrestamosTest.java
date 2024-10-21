package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
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
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;

/**
 * The Class PrestamoBOObtenerPrestamosTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoBOObtenerPrestamosTest {

    /** The prestamo BO. */
    @InjectMocks
    private PrestamoBOImpl prestamoBO = new PrestamoBOImpl();

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

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
     * Validar prestamo todo cero test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void validarPrestamoTodoCeroTest() throws BusinessException {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ZZZZ");
        CalificacionCrediticiaDTO calificacionCrediticiaDTO = new CalificacionCrediticiaDTO();
        calificacionCrediticiaDTO.setLineaActualDisponible(BigDecimal.ZERO);
        calificacionCrediticiaDTO.setLineaTotalCrediticia(BigDecimal.ZERO);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CalificacionCrediticiaDTO> respuesta = prestamoBO.validarLineaCrediticia(calificacionCrediticiaDTO,
                10, new Cliente());
        assertNotNull(respuesta);
    }

    /**
     * Validar prestamo algunos cero test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void validarPrestamoAlgunosCeroTest() throws BusinessException {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ZZZZ");
        CalificacionCrediticiaDTO calificacionCrediticiaDTO = new CalificacionCrediticiaDTO();
        calificacionCrediticiaDTO.setLineaActualDisponible(BigDecimal.ZERO);
        calificacionCrediticiaDTO.setLineaTotalCrediticia(BigDecimal.TEN);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CalificacionCrediticiaDTO> respuesta = prestamoBO.validarLineaCrediticia(calificacionCrediticiaDTO,
                10, new Cliente());
        assertNotNull(respuesta);
    }

    /**
     * Validar prestamo algunos cero sin registros test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void validarPrestamoAlgunosCeroSinRegistrosTest() throws BusinessException {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ZZZZ");
        CalificacionCrediticiaDTO calificacionCrediticiaDTO = new CalificacionCrediticiaDTO();
        calificacionCrediticiaDTO.setLineaActualDisponible(BigDecimal.ZERO);
        calificacionCrediticiaDTO.setLineaTotalCrediticia(BigDecimal.ZERO);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CalificacionCrediticiaDTO> respuesta = prestamoBO.validarLineaCrediticia(calificacionCrediticiaDTO, 0,
                new Cliente());
        assertNotNull(respuesta);
    }

    /**
     * Validar prestamo con disponible sin registros test.
     */
    @Test
    public void validarPrestamoConDisponibleSinRegistrosTest() {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ZZZZ");
        CalificacionCrediticiaDTO calificacionCrediticiaDTO = new CalificacionCrediticiaDTO();
        calificacionCrediticiaDTO.setLineaActualDisponible(BigDecimal.TEN);
        calificacionCrediticiaDTO.setLineaTotalCrediticia(BigDecimal.ZERO);

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CalificacionCrediticiaDTO> respuestaCalificacion = prestamoBO
                .validarLineaCrediticia(calificacionCrediticiaDTO, 0, new Cliente());

        assertEquals(TipoError.ERROR_NO_REGISTROS_CON_LINEA_CREDITICIA.getDescripcion(),
                respuestaCalificacion.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Validar prestamo sin cuentas sin calificacion C test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void validarPrestamoSinCuentasSinCalificacionCTest() throws BusinessException {

        CalificacionCrediticiaDTO calificacionCrediticiaDTO = null;
        Mockito.when(cuentaBO.hasCuentasMonetariasActivas(Matchers.any(Cliente.class))).thenReturn(false);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CalificacionCrediticiaDTO> respuesta = prestamoBO.validarLineaCrediticia(calificacionCrediticiaDTO,
                10, new Cliente());
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);

    }

    /**
     * Validar prestamo con cuentas sin calificacion C test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void validarPrestamoConCuentasSinCalificacionCTest() throws BusinessException {

        CalificacionCrediticiaDTO calificacionCrediticiaDTO = null;
        Mockito.when(cuentaBO.hasCuentasMonetariasActivas(Matchers.any(Cliente.class))).thenReturn(true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CalificacionCrediticiaDTO> respuesta = prestamoBO.validarLineaCrediticia(calificacionCrediticiaDTO,
                10, new Cliente());
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

    }

}
