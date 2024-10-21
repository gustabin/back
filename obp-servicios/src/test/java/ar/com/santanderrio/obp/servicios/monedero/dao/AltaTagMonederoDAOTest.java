package ar.com.santanderrio.obp.servicios.monedero.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.SessionUsuarioData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.monedero.dao.impl.AltaTagMonederoDAOImpl;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		AltaTagMonederoDAOTest.AltaTagMonederoDAOTestConfiguration.class })
@RunWith(MockitoJUnitRunner.class)
public class AltaTagMonederoDAOTest {
	
	@Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.monedero.dao" })
    public static class AltaTagMonederoDAOTestConfiguration {
        @Bean
        AltaTagMonederoDAO altaTagMonederoDAO() {
            return new AltaTagMonederoDAOImpl();
        }
    }
	
	@InjectMocks
    private AltaTagMonederoDAO altaTagMonederoDAO = new AltaTagMonederoDAOImpl();
	
	@Mock
    private IatxComm iatxComm;
	
	@Test
	@Ignore
	public void ejecutarAltaTagMonedero() throws BusinessException, IatxException{
		
        ApplicationContext context = Mockito.mock(ApplicationContext.class);
    	SessionUsuarioData sesionUsuarioData = new SessionUsuarioData();
    	sesionUsuarioData.setIatxNroReqSessionName("5");
    	sesionUsuarioData.setIatxSessionUserName("371769");
    	ContextHolder.setContext(context);
    	
    	IatxResponse iatxResponse = new IatxResponse();
    	iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
    	iatxResponse.setErrorCode(00000000);
    	iatxResponse.setFechaHoraReq((new Date()).toString());

    	Mockito.when(context.getBean(SessionUsuarioData.class)).thenReturn(sesionUsuarioData);
		Mockito.when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);

		DatosAltaTagMonederoEntity datosAltaTagMonederoEntity = new DatosAltaTagMonederoEntity();
		datosAltaTagMonederoEntity.setApellidoEmbozado("Fernandez");
		datosAltaTagMonederoEntity.setNombreEmbozado("Julio");
		datosAltaTagMonederoEntity.setAdicional(true);
		datosAltaTagMonederoEntity.setCuentaPesosSeleccionada("1234567890");
		datosAltaTagMonederoEntity.setSucursal("665");
		datosAltaTagMonederoEntity.setLimiteMensualRecarga("123456789123456789");
		datosAltaTagMonederoEntity.setImporteARecargar("50");
		
		Cliente cliente = new Cliente();
		cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setNroSucursal("123");
        cuenta.setTipoCuenta("09");
        cuenta.setNroCuentaProducto("12345");
        cuenta.setNroOrdenFirmante("1");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
		
		ComprobanteAltaTagMonederoView respuesta = altaTagMonederoDAO.ejecutarAltaTagMonedero(datosAltaTagMonederoEntity, cliente);
		
		Assert.assertNotNull(respuesta);
		
	}
	
}
