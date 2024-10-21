package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ArchivoDeRecursosDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.ConsultaPaquetesDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.impl.ConsultaPaquetesDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ConsultaPaquetesDAOTest.ConsultaPaquetesDAOTestConfiguration.class })
public class ConsultaPaquetesDAOTest extends IatxBaseDAOTest{

	@Autowired
	private ConsultaPaquetesDAO consultaPaquetesDAO;
	
	@InjectMocks
    ArchivoDeRecursosDAO recursos = new ArchivoDeRecursosDAOImpl();
    @Mock
    private TransaccionesControllerHomeBO transaccionesControllerHomeBO;
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
    }

    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.descuento.cheques.dao",
            "ar.com.santanderrio.obp.base.comun" })
    public static class ConsultaPaquetesDAOTestConfiguration {
        @Bean
        ConsultaPaquetesDAO consultaPaquetesDAO() {
            return new ConsultaPaquetesDAOImpl();
        }
        
        @Bean
        ArchivoDeRecursosDAO archivoDeRecursosDAO() {
            return new ArchivoDeRecursosDAOImpl();
        }

        @Bean
        PackagesApi packagesApi(){
            return Mockito.mock(PackagesApi.class);
        }
    }
    
    @Test
    public void obtenerConsultaPaquetesOkTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00202857000000032017110717250100000000IBF00CPR000000000000CNSPAQCNLS1000000            03682408    00        00 017238010201711071724490000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0010100000000õ001õ09õ000õ3604292õ0072õ0000õ07õ0001õ007003604292õARSõ1õ0072õ0000õ000090000259563õ90õ0351õAõ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);
        boolean noAplicaCesion = transaccionesControllerHomeBO.aplicaDescuentoCheques(new Cliente());
        ConsultaPaquetesInEntity consultaPaquetesInEntity = new ConsultaPaquetesInEntity();
        consultaPaquetesInEntity.setCliente(cliente);
        Assert.assertFalse(noAplicaCesion);
    }
}
