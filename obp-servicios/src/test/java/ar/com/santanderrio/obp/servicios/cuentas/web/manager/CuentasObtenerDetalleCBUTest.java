
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.sucursales.service.ConsultarSucursalesService;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.AliasCuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CuentasObtenerDetalleCBUTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasObtenerDetalleCBUTest {

    /** The cuenta manager. */
    @InjectMocks
    private CuentaManagerImpl cuentaManager = new CuentaManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO;

    /** The session detalle cuentas. */
    @Mock
    private SessionDetalleCuentas sessionDetalleCuentas;

    /** The consultar sucursales service. */
    @Mock
    private ConsultarSucursalesService consultarSucursalesService;

    /** The respuesta service. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    AliasCuentaBO aliasCuentaBO = new AliasCuentaBOImpl();
    
    
    @Mock
    private AliasCuentaManager aliasCuenta;
    
    @Mock
    private SesionParametros sesionParametros;

    /**
     * Gets the cuentas service test.
     *
     * @return the cuentas service test
     * @throws ServiceException
     *             the service exception
     * @throws DAOException 
     */
    @Test
    public void obtenerDetalleCBUTest() throws ServiceException, DAOException {

        ConsultaCuentaView cuenta = new ConsultaCuentaView();
        Cliente cliente = new Cliente();
        AbstractCuenta abstractCuenta = new ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta();
        Respuesta<Sucursal> respuestaSucursal = new Respuesta<Sucursal>();
        Sucursal sucursal = new Sucursal();
        Respuesta<DetalleCBUView> respuestaAlias = new Respuesta<DetalleCBUView>();
        DetalleCBUView detalleCBUVAliasiew = new DetalleCBUView();
        
        detalleCBUVAliasiew.setAliasAnterior("PRUEBA ANTERIOR");
        respuestaAlias.setRespuesta(detalleCBUVAliasiew);
        respuestaAlias.setEstadoRespuesta(EstadoRespuesta.OK);
        sucursal.setDescripcion("Paris");
        respuestaSucursal.setRespuesta(sucursal);
        cuenta.setNumeroCuenta("123-123456/7");
        cliente.setNombre("ricardo");
        cliente.setApellido1("iorio");
        cliente.setDni("99999999");
        cliente.setTipoDocumento("N");
        cliente.setNumeroCUILCUIT("21-21212121-2");
        abstractCuenta.setCbu("1234567890123");
        abstractCuenta.setTipoCuenta("0");
        abstractCuenta.setNroSucursal("123");
        DetalleDocumentoDTO doc = new DetalleDocumentoDTO();
        doc.setNroDocumento("21212121212");
        
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDni("99999999");
        
        Respuesta<DetalleCBUView> detalleAliasCBU = new Respuesta<DetalleCBUView>();
        
        when(aliasCuentaBO.obtenerDocumentoValidoDTO(cliente)).thenReturn(doc);
        when(aliasCuentaBO.obtenerCuitPorDetalle(doc)).thenReturn("21-21212121-2");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(abstractCuenta);
        when(consultarSucursalesService.consultarSucursalPorId(Matchers.anyString())).thenReturn(respuestaSucursal);
        when(sesionParametros.getDetalleCBUAlias()).thenReturn(detalleCBUVAliasiew);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(aliasCuentaBO.obtenerAliasCBU(Matchers.anyString(),Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(detalleAliasCBU);
        
        Respuesta<DetalleCBUView> respuesta = cuentaManager.obtenerDetalleCBU(cuenta, "");
        DetalleCBUView detalleCBUView = respuesta.getRespuesta();

        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(detalleCBUView.getCbu(), "1234567890123");
        assertEquals(detalleCBUView.getIdentificacionCliente(), "21-21212121-2");
        assertEquals(detalleCBUView.getTipoIdentificacion(), "CUIT/CUIL");
        assertEquals(detalleCBUView.getNombreBanco(), BancoEnum.SANTANDER_RIO.getDescripcion());
        assertEquals(detalleCBUView.getNombreCliente(), "Ricardo Iorio");
        assertEquals(detalleCBUView.getNumeroCuenta(), "123-123456/7");
        assertEquals(detalleCBUView.getNumeroSucursal(), "123 - Paris");
        assertEquals(detalleCBUView.getTipoCuenta(), "Cuenta corriente en $");

    }

}
