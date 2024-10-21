package ar.com.santanderrio.obp.servicios.marcaviajero.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.impl.LegalBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBO;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Destino;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Tarjeta;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajeHabilitado;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajesHabilitados;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.MarcaViajeroView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.ViajeHabilitadoView;

/**
 * The Class CuentaManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MarcaViajeroTest {

    /** The Constant FECHA_VIAJE. */
    private static final String FECHA_VIAJE = "18 de mayo";

    /** The Constant FECHA_DESDE. */
    private static final String FECHA_DESDE = "01/06/2018";

    /** The Constant FECHA_HASTA. */
    private static final String FECHA_HASTA = "30/06/2018";

    /** The cuenta manager. */
    @InjectMocks
    private MarcaViajeroManagerImpl marcaViajeroManager = new MarcaViajeroManagerImpl();

    /** The marca viajero BO. */
    @Mock
    private MarcaViajeroBO marcaViajeroBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO = new LegalBOImpl();

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /**
     * Obtener viajes OK test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerViajesOKTest() throws BusinessException, DAOException {
        EstadoRespuesta estadoRespuesta = EstadoRespuesta.OK;
        mockCliente();
        mockRespuestaViajesHabilitados(estadoRespuesta);
        mockMensajeBO();
        mockearCredencialesMya();


        Respuesta<MarcaViajeroView> respuestaMarcaViajeroView = marcaViajeroManager
                .obtenerViajes();
        ViajeHabilitadoView viajeHabilitadoView = respuestaMarcaViajeroView
                .getRespuesta().getViajesHabilitados().get(0);
        assertNotNull(respuestaMarcaViajeroView);
        assertEquals(FECHA_VIAJE, viajeHabilitadoView.getFechaViaje());
        assertEquals(FECHA_DESDE, viajeHabilitadoView.getFechaDesde());
        assertEquals(FECHA_HASTA, viajeHabilitadoView.getFechaHasta());
    }

    /**
     * Obtener viajes warning test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerViajesWarningTest()
            throws BusinessException, DAOException {
        EstadoRespuesta estadoRespuesta = EstadoRespuesta.WARNING;
        mockCliente();
        mockRespuestaViajesHabilitados(estadoRespuesta);
        mockMensajeBO();
        mockearCredencialesMya();

        Respuesta<MarcaViajeroView> respuestaMarcaViajeroView = marcaViajeroManager
                .obtenerViajes();
        assertNotNull(respuestaMarcaViajeroView);
        assertEquals(estadoRespuesta,
                respuestaMarcaViajeroView.getEstadoRespuesta());
    }

    /**
     * Obtener viajes error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerViajesErrorTest()
            throws BusinessException, DAOException {
        EstadoRespuesta estadoRespuesta = EstadoRespuesta.ERROR;
        mockCliente();
        mockRespuestaViajesHabilitados(estadoRespuesta);
        mockMensajeBO();
        mockearCredencialesMya();

        Respuesta<MarcaViajeroView> respuestaMarcaViajeroView = marcaViajeroManager
                .obtenerViajes();
        assertNotNull(respuestaMarcaViajeroView);
        assertEquals(estadoRespuesta,
                respuestaMarcaViajeroView.getEstadoRespuesta());
    }

    /**
     * Mock sesion cliente.
     */
    private void mockCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Pedro");
        cliente.setApellido1("Caseres");
        cliente.setApellido2("Guiterrez");
        cliente.setDni("1234");
        cliente.setTipoDocumento("N");
        when(sesionCliente.getCliente()).thenReturn(cliente);
    }

    /**
     * Mock marca viajero BO.
     *
     * @param estadoRespuesta the estado respuesta
     */
    private void mockRespuestaViajesHabilitados(EstadoRespuesta estadoRespuesta) {
        ViajesHabilitados viajesHabilitados = new ViajesHabilitados();
        ViajeHabilitado viajeHabilitado = new ViajeHabilitado();
        Calendar calendarioViaje = new GregorianCalendar(2018, 4, 18);
        Calendar calendarioDesde = new GregorianCalendar(2018, 5, 1);
        Calendar calendarioHasta = new GregorianCalendar(2018, 5, 30);
        viajeHabilitado.setFechaViaje(calendarioViaje.getTime());
        viajeHabilitado.setFechaDesde(calendarioDesde.getTime());
        viajeHabilitado.setFechaHasta(calendarioHasta.getTime());

        List<Destino> destinos = new ArrayList<Destino>();
        destinos.add(new Destino());
        viajeHabilitado.setDestinos(destinos);

        List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNombre("Maria Martinez");
        tarjeta.setNumero("123456789");
        tarjeta.setMarca("AMEX");
        tarjetas.add(tarjeta);
        viajeHabilitado.setTarjetas(tarjetas);

        viajesHabilitados
                .setViajesHabilitados(new ArrayList<ViajeHabilitado>());
        viajesHabilitados.setTieneBlack(Boolean.TRUE);
        viajesHabilitados.getViajesHabilitados().add(viajeHabilitado);
        Respuesta<ViajesHabilitados> respuestaViajesHabilitados = new Respuesta<ViajesHabilitados>();
        respuestaViajesHabilitados.setRespuesta(viajesHabilitados);
        respuestaViajesHabilitados.setEstadoRespuesta(estadoRespuesta);
        when(marcaViajeroBO.obtenerViajes(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenReturn(respuestaViajesHabilitados);
    }

    /**
     * Mock mensaje BO.
     */
    private void mockMensajeBO() {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de ayuda marca viajero");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
        .thenReturn(mensaje);
    }

    /**
     * Mockear credenciales mya.
     */
    private void mockearCredencialesMya() {
        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("juan@prueba.org");
        when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
    }
    
}
