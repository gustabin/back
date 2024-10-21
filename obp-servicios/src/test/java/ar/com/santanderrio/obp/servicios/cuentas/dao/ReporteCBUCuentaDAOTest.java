package ar.com.santanderrio.obp.servicios.cuentas.dao;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.impl.ConsultarSucursalesBOImpl;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.dao.impl.ReporteCBUCuentaDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class ReporteCBUCuentaDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporteCBUCuentaDAOTest {

    /** The reporte CBU cuenta DAO. */
    @InjectMocks
    private ReporteCBUCuentaDAO reporteCBUCuentaDAO = new ReporteCBUCuentaDAOImpl();

    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
    
    @Mock
	private ConsultarSucursalesBO consultarSucursalesBO = new ConsultarSucursalesBOImpl();
    
    

    /**
     * Inits the.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
    }

    
    /**
     * Test reporte.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void testReporte() throws IOException, IllegalAccessException {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jose");
        cliente.setApellido1("Valdez");
        cliente.setTipoDocumento("N");
        cliente.setNumeroCUILCUIT("34-93901222-2");
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("9438294983249324324");
        cuenta.setAlias("Alias");
        cuenta.setNroSucursal("033");
        cuenta.setNroCuentaProducto("123456789");
        cuenta.setTipoCuenta(TipoCuenta.CUENTA_UNICA.getCodigo().toString());
        
        Respuesta<Sucursal> respuestaSuc =  new Respuesta<Sucursal>();
        respuestaSuc.setEstadoRespuesta(EstadoRespuesta.OK);
        Sucursal sucursal = new Sucursal();
        respuestaSuc.setRespuesta(sucursal);
        sucursal.setDescripcion("TRIBUNALES");
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respuestaSuc);
        
        FieldUtils.writeField(reporteCBUCuentaDAO, "logo_pie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
        		true);
        FieldUtils.writeField(reporteCBUCuentaDAO, "logo_cabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
        		true);
        FieldUtils.writeField(reporteCBUCuentaDAO, "fileJasper",
                appContext.getResource("classpath:/report/cuentas/detallecbucuenta.jasper"), true);
        Reporte reporte = reporteCBUCuentaDAO.obtenerReporteCBUCuenta(cliente, cuenta, "aliffas");
        Assert.assertNotNull(reporte.getBytes());
//         FileUtils.writeByteArrayToFile(new File("c:/file10.pdf"),
//         reporte.getBytes());
    }

}
