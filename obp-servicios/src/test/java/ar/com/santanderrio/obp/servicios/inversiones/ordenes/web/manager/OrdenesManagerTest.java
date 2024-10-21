
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenBaseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.impl.OrdenesManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;

/**
 * Test para {@link OrdenesManager}.
 *
 * @author emilio.watemberg
 * @since Feb 2, 2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrdenesManagerTest {

    /** The ordenes manager. */
    @InjectMocks
    OrdenesManagerImpl ordenesManager;

    /** The estadistica manager. */
    @Mock
    EstadisticaManager estadisticaManager;

    /** The sesion cliente. */
    @Mock
    SesionCliente sesionCliente;

    /** The ordenes BO. */
    @Mock
    OrdenesBO ordenesBO;

    /** The mensaje manager. */
    @Mock
    MensajeManager mensajeManager;
    
    /** The Mensaje dao. */
    @Mock
    MensajeBO mensajeBO;

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The ordenes DTO. */
    OrdenesDTO ordenesDTO = new OrdenesDTO();

    /** The cuentas. */
    List<Cuenta> cuentas = new ArrayList<Cuenta>();

    /** The ordenes. */
    List<OrdenBaseDTO> ordenes = new ArrayList<OrdenBaseDTO>();

    /**
     * Inits the.
     */
    @Before
    public void init() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000013806660");
        cuenta.setAlias("alias");
        cuenta.setCbu("0720201088000036699968");
        cuenta.setSucursalPaquete("0250");
        cuentas.add(cuenta);

        OrdenBaseDTO orden1 = new OrdenDTO("0129865", new Date(), "Ingresada", "SU", "SUPER AHORRO PLUS", "14800.50");
        OrdenBaseDTO orden2 = new OrdenDTO("0123465", new Date(), "Ingresada", "SU", "SUPER AHORRO PLUS", "100.50");
        OrdenBaseDTO orden3 = new OrdenDTO("0122365", new Date(), "Ingresada", "SU", "SUPER AHORRO PLUS", "10870.90");
        OrdenBaseDTO orden4 = new OrdenDTO("0234365", new Date(), "Ingresada", "SU", "SUPER AHORRO PLUS", "1012430.50");
        ordenes.add(orden1);
        ordenes.add(orden2);
        ordenes.add(orden3);
        ordenes.add(orden4);

        ordenesDTO.setCuentas(cuentas);
        ordenesDTO.setOrdenes(ordenes);
    }

    /**
     * Iniciar ordenes test.
     */
    @Test
    public void iniciarOrdenesTest() {

        Respuesta<OrdenesView> respuestaEsperada = null;
        Respuesta<OrdenesDTO> respuestaBO = respuestaFactory.crearRespuestaOk(OrdenesDTO.class);

        respuestaBO.setRespuesta(ordenesDTO);

        Cliente cliente = new Cliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(ordenesBO.iniciarOrdenesOperaciones(Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo
        		(Matchers.anyString())).thenReturn(new Mensaje());
        respuestaEsperada = ordenesManager.iniciarOrdenes();

        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Iniciar ordenes exception test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void iniciarOrdenesExceptionTest() {

        Respuesta<OrdenesView> respuestaEsperada = null;
        Cliente cliente = new Cliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(ordenesBO.iniciarOrdenesOperaciones(Matchers.any(Cliente.class))).thenThrow(Exception.class);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("10422");
        mensaje.setMensaje("Ocurrio un error en nuestros servicios");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        respuestaEsperada = ordenesManager.iniciarOrdenes();

        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuestaEsperada.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_GENERICO.getDescripcion());

    }

    /**
     * Iniciar ordenes error test.
     */
    @Test
    public void iniciarOrdenesErrorTest() {

        Respuesta<OrdenesView> respuestaEsperada = null;
        Cliente cliente = new Cliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("10422");
        mensaje.setMensaje("Ocurrio un error en nuestros servicios");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<OrdenesDTO> respuestaBoError = respuestaFactory.crearRespuestaError(OrdenesDTO.class,
                "Ocurrio un error en nuestros servicios", null);
        Mockito.when(ordenesBO.iniciarOrdenesOperaciones(Matchers.any(Cliente.class))).thenReturn(respuestaBoError);

        respuestaEsperada = ordenesManager.iniciarOrdenes();

        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuestaEsperada.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_GENERICO.getDescripcion());
    }

    /**
     * Buscar ordenes test.
     */
    @Test
    public void buscarOrdenesTest() {
        Respuesta<OrdenesView> respuestaEsperada = null;
        FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();
        Respuesta<OrdenesDTO> respuestaBO = respuestaFactory.crearRespuestaOk(OrdenesDTO.class);
        respuestaBO.setRespuesta(ordenesDTO);

        Cliente cliente = new Cliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(ordenesBO.buscarOrdenesOperaciones(Matchers.any(FiltrosOrdenesView.class), Matchers.anyString()))
                .thenReturn(respuestaBO);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo
        		(Matchers.anyString())).thenReturn(new Mensaje());

        respuestaEsperada = ordenesManager.buscarOrdenes(filtrosOrdenesView);

        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Buscar ordenes error test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void buscarOrdenesExceptionTest() {
        Respuesta<OrdenesView> respuestaEsperada = null;
        FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();
        Cliente cliente = new Cliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(ordenesBO.buscarOrdenesOperaciones(Matchers.any(FiltrosOrdenesView.class), Matchers.anyString()))
                .thenThrow(Exception.class);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("10422");
        mensaje.setMensaje("Ocurrio un error en nuestros servicios");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        respuestaEsperada = ordenesManager.buscarOrdenes(filtrosOrdenesView);

        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuestaEsperada.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_GENERICO.getDescripcion());
    }

    /**
     * Buscar ordenes error test.
     */
    @Test
    public void buscarOrdenesErrorTest() {
        Respuesta<OrdenesView> respuestaEsperada = null;
        FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();

        Cliente cliente = new Cliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("10422");
        mensaje.setMensaje("Ocurrio un error en nuestros servicios");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<OrdenesDTO> respuestaBO = respuestaFactory.crearRespuestaError(OrdenesDTO.class,
                "Ocurrio un error en nuestros servicios", null);
        respuestaBO.setRespuesta(ordenesDTO);
        Mockito.when(ordenesBO.buscarOrdenesOperaciones(Matchers.any(FiltrosOrdenesView.class), Matchers.anyString()))
                .thenReturn(respuestaBO);

        respuestaEsperada = ordenesManager.buscarOrdenes(filtrosOrdenesView);

        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuestaEsperada.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_GENERICO.getDescripcion());
    }

    /***
     * Exception al recuperar las ordenes
     */
    @Test
    public void buscarOrdenesPorCuentaException () {
        String nroCuenta ="";
        Respuesta<OrdenesView> respuestaEsperada = null;
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(new Mensaje());
        respuestaEsperada = ordenesManager.buscarOrdenesPorCuenta(nroCuenta);
        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    /***
     * Error al recuperar las ordenes
     */
    @Test
    public void buscarOrdenesPorCuentaError () {
        String nroCuenta ="";
        Respuesta<OrdenesView> respuestaEsperada = null;
        Respuesta<OrdenesDTO> rtaOrdenes = new Respuesta<OrdenesDTO>();
        rtaOrdenes.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(new Mensaje());
        Mockito.when(ordenesBO.buscarOrdenesOperacionesPorCuenta(Matchers.any(String.class), Matchers.anyString())).thenReturn(rtaOrdenes);
        respuestaEsperada = ordenesManager.buscarOrdenesPorCuenta(nroCuenta);
        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    
    @Test
    public void buscarOrdenesPorCuentaOK() {
        String nroCuenta ="";
        Respuesta<OrdenesView> respuestaEsperada = null;
        Respuesta<OrdenesDTO> rtaOrdenes = new Respuesta<OrdenesDTO>();
        OrdenesDTO ordenes = new OrdenesDTO();
		rtaOrdenes.setRespuesta(ordenes);
		ordenes.setCuentas(new ArrayList<Cuenta>());
		ordenes.setOrdenes(new ArrayList<OrdenBaseDTO>());
        rtaOrdenes.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(new Mensaje());
        Mockito.when(ordenesBO.buscarOrdenesOperacionesPorCuenta(Matchers.any(String.class), Matchers.anyString())).thenReturn(rtaOrdenes);
        Mensaje mensaje = new Mensaje();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        respuestaEsperada = ordenesManager.buscarOrdenesPorCuenta(nroCuenta);
        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaEsperada.getEstadoRespuesta());
    }
    
}
