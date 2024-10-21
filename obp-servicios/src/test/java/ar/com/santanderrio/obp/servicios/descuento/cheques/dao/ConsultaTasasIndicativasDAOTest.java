package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
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
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl.ConsultaTasasIndicativasDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ConsultaTasasIndicativasDAOTest.ConsultaTasasIndicativasDAOTestConfiguration.class })
public class ConsultaTasasIndicativasDAOTest extends IatxBaseDAOTest {

	@Autowired
	ConsultaTasasIndicativasDAO consultaTasasIndicativasDAO;
	
    @InjectMocks
    ArchivoDeRecursosDAO recursos = new ArchivoDeRecursosDAOImpl();
    
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
    public static class ConsultaTasasIndicativasDAOTestConfiguration {
        @Bean
        ConsultaTasasIndicativasDAO consultaTasasIndicativasDAO() {
            return new ConsultaTasasIndicativasDAOImpl();
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
    public void obtenerConsultaTasasIndicativasOkTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00995115000000112017112109013700000000IBF0005L000000000000CNSTASIND_1000000            03682408    00        00 000000000201711210901240000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036100000000õ006õ030õ0000264200õ0000090000õ000000000000000õ000000000000000õ060õ0000266100õ0000091500õ000000000000000õ000000000000000õ090õ0000265000õ0000090000õ000000000000000õ000000000000000õ120õ0000267500õ0000091500õ000000000000000õ000000000000000õ150õ0000269500õ0000092500õ000000000000000õ000000000000000õ180õ0000270400õ0000092500õ000000000000000õ000000000000000õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);
        
        TasasIndicativasEntity  datosCesion = consultaTasasIndicativasDAO.obtenerTasasIndicativas("", cliente);
        
        Assert.assertTrue(datosCesion!=null);
    }
    
    
    @Test(expected= DAOException.class)
    public void obtenerConsultaTasasIndicativasExceptionTest() throws IatxException, DAOException {
        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenThrow(new IatxException());
        consultaTasasIndicativasDAO.obtenerTasasIndicativas("", cliente);
    }
    
    
}
