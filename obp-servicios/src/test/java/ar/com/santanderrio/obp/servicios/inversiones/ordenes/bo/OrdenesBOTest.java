package ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo;

import java.math.BigDecimal;
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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.impl.OrdenesBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.dao.OrdenDAOBpriv;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.Orden;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.TipoView;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesOutEntity;

/**
 * Test para {@link OrdenesBO}.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
@RunWith(MockitoJUnitRunner.class)
public class OrdenesBOTest {

    /** The ordenes BO. */
    @InjectMocks
    private OrdenesBOImpl ordenesBO;

    /** The orden DAO. */
    @Mock
    OrdenDAOBpriv ordenDAO;
    
    @Mock
    IntervinientesDAO intervinientesDAO;
    
    @Mock
    MensajeBO mensajeBO;
    
    /** The orden DAO. */
    @Mock
    ConsultaFondoDAO consultaFondosDAO;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The orden out entity. */
    OrdenOutEntity ordenOutEntity = new OrdenOutEntity();

    /**
     * Inits the.
     */
    @Before
    public void init() {

        Orden orden1 = new Orden();
        orden1.setEstado("Ingresada ");
        orden1.setNroOrden("465758930");
        orden1.setFechaOrden(new Date());
        orden1.setCapital(new BigDecimal("57487.23"));
        orden1.setTipoOrden("RE");
        orden1.setNroFondo(new BigDecimal("2333333"));

        Orden orden2 = new Orden();
        orden2.setEstado("Ingresada ");
        orden2.setNroOrden("43234677830");
        orden2.setFechaOrden(new Date());
        orden2.setCapital(new BigDecimal("287.23"));
        orden2.setTipoOrden("SU");
        orden2.setNroFondo(new BigDecimal("23233333"));

        List<Orden> ordenes = new ArrayList<Orden>();
        ordenes.add(orden1);
        ordenes.add(orden2);

        ordenOutEntity.setOrdenes(ordenes);
    }

    /**
     * Iniciar ordenes operaciones test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void iniciarOrdenesOperacionesTest() throws DAOException {
        Respuesta<OrdenesDTO> respuestaEsperada = respuestaFactory.crearRespuestaOk(OrdenesDTO.class);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setNombreFondo("PEPITO");

        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000003669996");
        cuenta.setCbu("0720201088000036699968");
        cuenta.setAlias("");
        cuenta.setCodigoPaquete("0000");
        cuentas.add(cuenta);
        cliente.setCuentasPrivadas(cuentas);
        
        Mockito.when(ordenDAO.cargarOrdenes(Matchers.any(OrdenInEntity.class))).thenReturn(ordenOutEntity);
        Mockito.when(consultaFondosDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);
        Mockito.when(intervinientesDAO.obtenerIntervinientes(Matchers.any(Cuenta.class))).thenReturn(new IntervinientesOutEntity());
        respuestaEsperada = ordenesBO.iniciarOrdenesOperaciones(cliente);
        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    
    @Test
    public void iniciarOrdenesOperacionesDAOException() throws DAOException  {
        Respuesta<OrdenesDTO> respuestaEsperada = respuestaFactory.crearRespuestaOk(OrdenesDTO.class);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setNombreFondo("PEPITO");

        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000003669996");
        cuenta.setCbu("0720201088000036699968");
        cuenta.setAlias("");
        cuenta.setCodigoPaquete("0000");
        cuentas.add(cuenta);
        cliente.setCuentasPrivadas(cuentas);
        
        Mockito.when(ordenDAO.cargarOrdenes(Matchers.any(OrdenInEntity.class))).thenThrow(new DAOException ());
        Mockito.when(consultaFondosDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);
        Mockito.when(intervinientesDAO.obtenerIntervinientes(Matchers.any(Cuenta.class))).thenReturn(new IntervinientesOutEntity());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(new Mensaje());
        respuestaEsperada = ordenesBO.iniciarOrdenesOperaciones(cliente);
        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    
    
    
    /**
     * Buscar ordenes operaciones test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void buscarOrdenesOperacionesTest() throws DAOException {
        Respuesta<OrdenesDTO> respuestaEsperada = respuestaFactory.crearRespuestaOk(OrdenesDTO.class);

        FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();
        filtrosOrdenesView.setCuentaSeleccionada("0000000003669996");
        filtrosOrdenesView.setFechaDesde("01-01-2017");
        filtrosOrdenesView.setFechaHasta("01-02-2017");
        filtrosOrdenesView.setTipoSeleccionado("");
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setNombreFondo("PEPITO");

        Mockito.when(ordenDAO.cargarOrdenes(Matchers.any(OrdenInEntity.class))).thenReturn(ordenOutEntity);
        
        Mockito.when(consultaFondosDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);

        String codigoSist = "FC";
        respuestaEsperada = ordenesBO.buscarOrdenesOperaciones(filtrosOrdenesView, codigoSist);

        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    @Test
    public void buscarOrdenesOperacionesPorCuentaOK() throws DAOException {
        String nroCuenta = "0000000003669996";
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setNombreFondo("PEPITO");

        Mockito.when(ordenDAO.cargarOrdenes(Matchers.any(OrdenInEntity.class))).thenReturn(ordenOutEntity);
        Mockito.when(consultaFondosDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);
        
        String codigoSist = "FC";
		Respuesta<OrdenesDTO> rta =  ordenesBO.buscarOrdenesOperacionesPorCuenta(nroCuenta, codigoSist);
        
        Assert.assertNotNull(rta);
        Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    @Test
    public void buscarOrdenesOperacionesOK() throws DAOException {
        FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();
        filtrosOrdenesView.setCuentaSeleccionada("0000000003669996");
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setNombreFondo("PEPITO");
        List<TipoView> tipos = new ArrayList<TipoView>();
        tipos.add(new TipoView("AA", "AA"));
        filtrosOrdenesView.setTipos(tipos);
        Mockito.when(ordenDAO.cargarOrdenes(Matchers.any(OrdenInEntity.class))).thenReturn(ordenOutEntity);
        Mockito.when(consultaFondosDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);
        String codigoSist = "FC";
        Respuesta<OrdenesDTO> rta =  ordenesBO.buscarOrdenesOperaciones(filtrosOrdenesView, codigoSist);
        
        Assert.assertNotNull(rta);
        Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    @Test
    public void buscarOrdenesOperacionesDAOException () throws DAOException {
        FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();
        filtrosOrdenesView.setCuentaSeleccionada("0000000003669996");
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setNombreFondo("PEPITO");
        List<TipoView> tipos = new ArrayList<TipoView>();
        tipos.add(new TipoView("AA", "AA"));
        filtrosOrdenesView.setTipos(tipos);
        Mockito.when(ordenDAO.cargarOrdenes(Matchers.any(OrdenInEntity.class))).thenThrow(new DAOException());
        Mockito.when(consultaFondosDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(new Mensaje());
        String codigoSist = "FC";
		Respuesta<OrdenesDTO> rta =  ordenesBO.buscarOrdenesOperaciones(filtrosOrdenesView, codigoSist);
        
        
        Assert.assertNotNull(rta);
        Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    
    @Test
    public void  iniciarOrdenesOperacionesListaVacia() throws DAOException{
        Respuesta<OrdenesDTO> respuestaEsperada = respuestaFactory.crearRespuestaOk(OrdenesDTO.class);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setNombreFondo("PEPITO");

        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000003669996");
        cuenta.setCbu("0720201088000036699968");
        cuenta.setAlias("");
        cuenta.setCodigoPaquete("0000");
        cuentas.add(cuenta);
        cliente.setCuentasPrivadas(cuentas);
        
        OrdenOutEntity ordenOutEntityEmpty = new OrdenOutEntity();
        
        Mockito.when(ordenDAO.cargarOrdenes(Matchers.any(OrdenInEntity.class))).thenReturn(ordenOutEntityEmpty);
        Mockito.when(consultaFondosDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);
        Mockito.when(intervinientesDAO.obtenerIntervinientes(Matchers.any(Cuenta.class))).thenReturn(new IntervinientesOutEntity());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(new Mensaje());
        respuestaEsperada = ordenesBO.iniciarOrdenesOperaciones(cliente);
        Assert.assertNotNull(respuestaEsperada);
        Assert.assertEquals(respuestaEsperada.getEstadoRespuesta(), EstadoRespuesta.WARNING);
    }
    
    
    
    
    @Test
    public void buscarOrdenesOperacionesPeriodoNotNull() throws DAOException {
        FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();
        filtrosOrdenesView.setCuentaSeleccionada("0000000003669996");
        
        filtrosOrdenesView.setPeriodoSeleccionado("1");
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
        consultaFondoOutEntity.setNombreFondo("PEPITO");
        List<TipoView> tipos = new ArrayList<TipoView>();
        tipos.add(new TipoView("AA", "AA"));
        filtrosOrdenesView.setTipos(tipos);
        Mockito.when(ordenDAO.cargarOrdenes(Matchers.any(OrdenInEntity.class))).thenReturn(ordenOutEntity);
        Mockito.when(consultaFondosDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(consultaFondoOutEntity);
        String codigoSist = "FC";
        Respuesta<OrdenesDTO> rta =  ordenesBO.buscarOrdenesOperaciones(filtrosOrdenesView, codigoSist);
        
        Assert.assertNotNull(rta);
        Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
}
