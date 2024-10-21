package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.impl.AltaTarjetaCreditoAdicionalDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.AltaTarjetaCreditoAdicionalInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.AltaTarjetaCreditoAdicionalOutResponseEntity;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		AltaTarjetaCreditoAdicionalDAOTest.AltaTarjetaCreditoAdicionalDAOTestConfiguration.class })
public class AltaTarjetaCreditoAdicionalDAOTest extends IatxBaseDAOTest{
	
    /** The fondo DAO. */
    @Autowired
    private AltaTarjetaCreditoAdicionalDAO altaTarjetaCreditoAdicionalDAO;

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
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao" })
    public static class AltaTarjetaCreditoAdicionalDAOTestConfiguration {
        @Bean
        AltaTarjetaCreditoAdicionalDAO altaTarjetaCreditoAdicionalDAO() {
            return new AltaTarjetaCreditoAdicionalDAOImpl();
        }
    }
	
    
    @Test
    public void altaExitosaSolicitudTarjCredAdicionalTest() throws DAOException, IatxException {
    	
    	String servicio = "ALTTARJETA";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300FPQG25  ********00220006000000272017071214113500000000IBF00L3Q000000000000ALTTARJETA1000000            00556625    00        00 014161504201707121411260000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0001600000000õ000369õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        
        AltaTarjetaCreditoAdicionalInEntity altaTarjetaCreditoAdicionalInEntity = new AltaTarjetaCreditoAdicionalInEntity();
        altaTarjetaCreditoAdicionalInEntity.setApellidoYNombre("WELLY/PANDORA CUNEGUNDA ");
        altaTarjetaCreditoAdicionalInEntity.setNroCuentaTarjeta("0412095313");
        altaTarjetaCreditoAdicionalInEntity.setSucursalUsuario("226");
        altaTarjetaCreditoAdicionalInEntity.setCentroDeCosto("0226");
        altaTarjetaCreditoAdicionalInEntity.setGrupoAfinidad("005348");
        altaTarjetaCreditoAdicionalInEntity.setMarca("1");
        altaTarjetaCreditoAdicionalInEntity.setNup("");
        altaTarjetaCreditoAdicionalInEntity.setPorcentajeLimiteCompra("01000"); 
        altaTarjetaCreditoAdicionalInEntity.setCliente(cliente);
        AltaTarjetaCreditoAdicionalOutResponseEntity respuesta = altaTarjetaCreditoAdicionalDAO.altaTarjetaCreditoAdicional(altaTarjetaCreditoAdicionalInEntity);
        Assert.assertNotNull(respuesta);
    }

    
    
    @Test(expected = DAOException.class)
    public void altaSolicitudTarjCredAdicionalIatxExceptionTest() throws DAOException, IatxException {
        
        String servicio = "ALTTARJETA";
        String version = "100";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        
        AltaTarjetaCreditoAdicionalInEntity altaTarjetaCreditoAdicionalInEntity = new AltaTarjetaCreditoAdicionalInEntity();
        altaTarjetaCreditoAdicionalInEntity.setApellidoYNombre("WELLY/PANDORA CUNEGUNDA ");
        altaTarjetaCreditoAdicionalInEntity.setNroCuentaTarjeta("0412095313");
        altaTarjetaCreditoAdicionalInEntity.setSucursalUsuario("226");
        altaTarjetaCreditoAdicionalInEntity.setCentroDeCosto("0226");
        altaTarjetaCreditoAdicionalInEntity.setGrupoAfinidad("005348");
        altaTarjetaCreditoAdicionalInEntity.setMarca("1");
        altaTarjetaCreditoAdicionalInEntity.setNup("");
        altaTarjetaCreditoAdicionalInEntity.setPorcentajeLimiteCompra("01000"); 
        altaTarjetaCreditoAdicionalInEntity.setCliente(cliente);
        AltaTarjetaCreditoAdicionalOutResponseEntity respuesta = altaTarjetaCreditoAdicionalDAO.altaTarjetaCreditoAdicional(altaTarjetaCreditoAdicionalInEntity);
    }
}