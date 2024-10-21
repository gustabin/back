package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ComprobanteAdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.ResumenCuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.AdhesionResumenView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CuentaManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentaManagerMarcaResumenOnlineTest {

    /** The cuenta manager. */
    @Mock
    private CuentaManagerImpl cuentaManager = new CuentaManagerImpl();

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The session detalle cuentas. */
    @Mock
    private SessionDetalleCuentas sessionDetalleCuentas;

    /** The sesion resumen cuenta. */
    @Mock
    private SessionResumenCuenta sesionResumenCuenta;

    /** The sesion resumen cuenta. */
    @Mock
    private AliasCuentaManager aliasCuenta;

    /** The resumen mensual cuenta BO. */
    @Mock
    private ResumenMensualCuentaBO resumenMensualCuentaBO;

    /** The sesion resumen cuenta. */
    @Mock
    private SesionParametros sesionParametros;

    /** The respuesta Factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @InjectMocks
    private ResumenCuentaManager resumenCuentaManager = new ResumenCuentaManagerImpl();
    
    /**
     * Test confirmar adhesion resumen OK.
     */
    @Test
    public void testConfirmarAdhesionResumenOK() {
        Respuesta<Void> respuestaVoid = crearRespuestaVoid(new Boolean(true));
        AbstractCuenta cuentaAbstract = crearCuenta();
        AdhesionResumenView adhesionResumenView = crearAdhesionResumenView();
        RegistroSesion registroSesion = crearRegistroSesion();
        sesionParametros.setRegistroSession(registroSesion);
        ComprobanteAdhesionResumenDTO comprobanteAdhesionResumenDTO = new ComprobanteAdhesionResumenDTO();
        Mensaje mensajeOK = crearMensaje();

        when(cuentaManager.obtenerCuentaById(adhesionResumenView.getNumeroCuenta())).thenReturn(cuentaAbstract);
        when(resumenMensualCuentaBO.cambiarMarcaImpresion(Matchers.any(AdhesionResumenDTO.class)))
                .thenReturn(respuestaVoid);
        when(resumenMensualCuentaBO.crearComprobanteAdhesionResumen(Matchers.anyString()))
                .thenReturn(comprobanteAdhesionResumenDTO);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeOK);
        Respuesta<AdhesionResumenView> respuesta;
        respuesta = resumenCuentaManager.confirmarAdhesionResumen(adhesionResumenView);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Test confirmar adhesion resumen error.
     */
    @Test
    public void testConfirmarAdhesionResumenError() {
        Respuesta<Void> respuestaVoid = crearRespuestaVoid(new Boolean(false));
        AbstractCuenta cuentaAbstract = crearCuenta();
        AdhesionResumenView adhesionResumenView = crearAdhesionResumenView();
        RegistroSesion registroSesion = crearRegistroSesion();
        sesionParametros.setRegistroSession(registroSesion);
        ComprobanteAdhesionResumenDTO comprobanteAdhesionResumenDTO = new ComprobanteAdhesionResumenDTO();
        Mensaje mensajeOK = crearMensaje();

        when(cuentaManager.obtenerCuentaById(adhesionResumenView.getNumeroCuenta())).thenReturn(cuentaAbstract);
        when(resumenMensualCuentaBO.cambiarMarcaImpresion(Matchers.any(AdhesionResumenDTO.class)))
                .thenReturn(respuestaVoid);
        when(resumenMensualCuentaBO.crearComprobanteAdhesionResumen(Matchers.anyString()))
                .thenReturn(comprobanteAdhesionResumenDTO);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeOK);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos());
        Respuesta<AdhesionResumenView> respuesta;
        respuesta = resumenCuentaManager.confirmarAdhesionResumen(adhesionResumenView);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * Test comprobante adhesion resumen.
     */
    @Test
    public void testComprobanteAdhesionResumen() {
        AdhesionResumenView adhesionResumenView = new AdhesionResumenView();

        adhesionResumenView.setResumenOnline(true);
        resumenCuentaManager.comprobanteAdhesionResumen(adhesionResumenView);
        adhesionResumenView.setResumenOnline(false);
        resumenCuentaManager.comprobanteAdhesionResumen(adhesionResumenView);

        Mockito.verify(estadisticaManager, Mockito.times(1)).add(
                EstadisticasConstants.COMPROBANTE_ADHESION_RESUMEN_ONLINE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(
                EstadisticasConstants.COMPROBANTE_ADHESION_RESUMEN_FISICO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Crear adhesion resumen view.
     *
     * @return the adhesion resumen view
     */
    private AdhesionResumenView crearAdhesionResumenView() {
        AdhesionResumenView adhesionResumenView = new AdhesionResumenView();
        adhesionResumenView.setResumenOnline(true);
        adhesionResumenView.setNumeroCuenta("033-1232344/8");
        adhesionResumenView.setOpinionUsuario("Esta es la opinion.");
        return adhesionResumenView;
    }

    /**
     * Crear cuenta.
     *
     * @return the abstract cuenta
     */
    private AbstractCuenta crearCuenta() {
        AbstractCuenta cuentaAbstract = new Cuenta();
        cuentaAbstract.setAlias("Cuenta Prueba");
        cuentaAbstract.setNroCuentaProducto("033-1232344/8");
        return cuentaAbstract;
    }

    /**
     * Crear registro sesion.
     *
     * @return the registro sesion
     */
    private RegistroSesion crearRegistroSesion() {
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setPid("1234567890");
        return registroSesion;
    }

    /**
     * Crear mensaje.
     *
     * @return the mensaje
     */
    private Mensaje crearMensaje() {
        Mensaje mensajeOK = new Mensaje();
        mensajeOK.setMensaje("MensajeOK");
        return mensajeOK;
    }

    /**
     * Crear respuesta void.
     *
     * @param casoOK
     *            the caso OK
     * @return the respuesta
     */
    private Respuesta<Void> crearRespuestaVoid(Boolean casoOK) {
        Respuesta<Void> respuestaVoid = new Respuesta<Void>();
        respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
        if (!casoOK) {
            respuestaVoid.setEstadoRespuesta(EstadoRespuesta.ERROR);
        }
        return respuestaVoid;
    }

}
