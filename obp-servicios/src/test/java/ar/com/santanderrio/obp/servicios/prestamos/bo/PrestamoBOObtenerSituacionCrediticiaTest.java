/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamoPermitidoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CalificacionCrediticiaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoPermitidoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;

/**
 * The Class PrestamoBOObtenerSituacionCrediticiaTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoBOObtenerSituacionCrediticiaTest {

    /** The Constant TIPO_CUENTA_VISA. */
    private static final String TIPO_CUENTA_VISA = "07";

    /** The Constant TIPO_CUENTA_AMEX. */
    private static final String TIPO_CUENTA_AMEX = "42";

    /** The Constant JERARQUIA_PRINCIPAL. */
    private static final String JERARQUIA_PRINCIPAL = "P";

    /** The prestamo BO. */
    @InjectMocks
    private PrestamoBOImpl prestamoBO = new PrestamoBOImpl();

    /** The prestamo permitido BO. */
    @Mock
    private PrestamoPermitidoBOImpl prestamoPermitidoBO;

    /** The prestamo DAO. */
    @Mock
    private CalificacionCrediticiaDAO prestamoDAO;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The calificacion crediticia DAO. */
    @Mock
    private CalificacionCrediticiaDAO calificacionCrediticiaDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

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
     * Obtener situacion crediticia OK test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerSituacionCrediticiaOKTest() throws DAOException {

        Cliente cliente = new Cliente();

        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        Cuenta cuenta4 = new Cuenta();

        cuenta1.setTipoCuenta("02");
        cuenta1.setJerarquiaCuenta("");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        listCuentas.add(cuenta1);

        cuenta2.setTipoCuenta(TIPO_CUENTA_VISA);
        cuenta2.setJerarquiaCuenta("");
        listCuentas.add(cuenta2);

        cuenta3.setTipoCuenta(TIPO_CUENTA_VISA);
        cuenta3.setJerarquiaCuenta("");
        listCuentas.add(cuenta3);

        cuenta4.setTipoCuenta(TIPO_CUENTA_AMEX);
        cuenta4.setJerarquiaCuenta("");
        listCuentas.add(cuenta4);

        cliente.setCuentas(listCuentas);
        CalificacionCrediticiaOutEntity calificacionCrediticiaOutEntity = new CalificacionCrediticiaOutEntity();
        calificacionCrediticiaOutEntity.setImporteDisponiblePrestamo("00220");
        calificacionCrediticiaOutEntity.setPorcentajeLimitePrestamo("22220000");
        calificacionCrediticiaOutEntity.setCodigoInhabilitado("001");
        when(calificacionCrediticiaDAO.obtenerSituacionCrediticia(cuenta1, "C"))
                .thenReturn(calificacionCrediticiaOutEntity);

        PrestamoPermitidoDTO prestamoPermitidoDTO = new PrestamoPermitidoDTO();
        prestamoPermitidoDTO.setMontoPrestamo(new BigDecimal(22));
        Respuesta<PrestamoPermitidoDTO> prestamoPermitidoRespuesta = new Respuesta<PrestamoPermitidoDTO>();
        prestamoPermitidoRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        prestamoPermitidoRespuesta.setRespuesta(prestamoPermitidoDTO);
        prestamoPermitidoRespuesta.setRespuestaVacia(false);

        when(prestamoPermitidoBO.obtenerPrestamoConSaldoMayor(cliente)).thenReturn(prestamoPermitidoRespuesta);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de Ayuda");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

     /**  Respuesta<CalificacionCrediticiaDTO> respuesta = prestamoBO.obtenerSituacionCrediticia(cliente);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertNotNull(respuesta.getRespuesta());
 
       CalificacionCrediticiaDTO calificacionCrediticiaDTO = respuesta.getRespuesta();

        assertEquals(calificacionCrediticiaDTO.getLineaActualDisponible(), new BigDecimal("2.20"));
        assertEquals(calificacionCrediticiaDTO.getLineaTotalCrediticia(), new BigDecimal("222200.00"));**/
    }

    /**
     * Obtener situacion crediticia error test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerSituacionCrediticiaErrorTest() throws DAOException {

        Cliente cliente = new Cliente();

        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();

        cuenta1.setTipoCuenta("");
        cuenta1.setJerarquiaCuenta(JERARQUIA_PRINCIPAL);
        listCuentas.add(cuenta1);

        cliente.setCuentas(listCuentas);
        CalificacionCrediticiaOutEntity calificacionCrediticiaOutEntity = new CalificacionCrediticiaOutEntity();
        calificacionCrediticiaOutEntity.setImporteDisponiblePrestamo("00220");
        calificacionCrediticiaOutEntity.setPorcentajeLimitePrestamo("22220000");
        when(calificacionCrediticiaDAO.obtenerSituacionCrediticia(cuenta1, "C")).thenThrow(DAOException.class);

        Respuesta<PrestamoPermitidoDTO> prestamoPermitidoRespuesta = new Respuesta<PrestamoPermitidoDTO>();
        prestamoPermitidoRespuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        prestamoPermitidoRespuesta.setRespuestaVacia(true);

        when(prestamoPermitidoBO.obtenerPrestamoConSaldoMayor(cliente)).thenReturn(prestamoPermitidoRespuesta);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de Ayuda");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

       /* Respuesta<CalificacionCrediticiaDTO> respuesta = prestamoBO.obtenerSituacionCrediticia(cliente);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertNotNull(
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError().equals(TipoError.ERROR_INICIO_PRESTAMOS));*/
    }
}
