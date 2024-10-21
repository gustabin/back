package ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.PackagesEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.PackagesResponseEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.PaqueteEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;
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
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.impl.ConsultaPaquetesDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.ConsultaPaquetesDAOTest.ConsultaPaquetesDAOTestConfiguration.class })
public class ConsultaPaquetesDAOTest extends IatxBaseDAOTest {
    
    /** The fondo DAO. */
    @Autowired
    @InjectMocks
    private ConsultaPaquetesDAO consultaPaquetesDAO;

    @Autowired
    @InjectMocks
    private PackagesApi accountsApi;
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
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
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao" })
    public static class ConsultaPaquetesDAOTestConfiguration {
        @Bean
        ConsultaPaquetesDAO consultaPaquetesDAO() {
            return new ConsultaPaquetesDAOImpl();
        }
        @Bean
        PackagesApi packagesApi(){
            return Mockito.mock(PackagesApi.class);
        }
    }
    
    @Test
    public void consultarPaquetesTest() throws IatxException, DAOException {


        String servicio = "CNSPAQCNLS";
        String version = "100";
        String tramaResponse ="200000000000P04HTML0009900010304LPPM26  ********00621744000000072016122616330000000000IBF22544000000000000CNSPAQCNLS1000000            04155226    00        00 016366502201612261632540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0010100000000õ001õ09õ000õ1510142õ0072õ0000õ07õ0001õ007001510142õARSõ1õ0072õ0000õ000090000285175õ90õ0350õAõ";
        ConsultaPaquetesInEntity consultaPaquetesInEntity = new ConsultaPaquetesInEntity();
        PaqueteEntity packageUnit=new PaqueteEntity();
        consultaPaquetesInEntity.setCliente(cliente);
        consultaPaquetesInEntity.setCantidadDeCuentas("001");
        consultaPaquetesInEntity.setNumeroCuenta("1234567");
        consultaPaquetesInEntity.setSucursalCuenta("123");
        consultaPaquetesInEntity.setTipoCuenta("12");
        ConsultaPaquetesOutEntity consultaPaquetesOutEntity = generateResponseApiPackagesTest();
        Assert.assertNotNull(consultaPaquetesOutEntity);

    }
    private ConsultaPaquetesOutEntity generateResponseApiPackagesTest(){
        //salida iatx
        ConsultaPaquetesOutEntity consultaPaquetesOutEntity = new ConsultaPaquetesOutEntity();
        // lista de paquetes iatx
        List<PaqueteEntity> packageIatx = new ArrayList<PaqueteEntity>();
        PaqueteEntity packageUnit = new PaqueteEntity();
        //tipo de cuenta
        packageUnit.setTipoCuenta("07");
        packageUnit.setEntidad("0072");
        packageUnit.setSucursalCuenta("0000");
        packageUnit.setNumeroCuenta("1234567");
        packageUnit.setDivisa("ARS");
        packageUnit.setProducto("090");
        packageUnit.setSubProducto("0001");
        packageUnit.setEntidadPaquete("0072");
        packageUnit.setCentroAltairPaquete("0000");
        packageUnit.setNumeroCuentaAltair("1234567");
        packageUnit.setPaquete("1234");
        packageUnit.setProductoPaquete("090");
        packageUnit.setSubProductoPaquete("00");
        packageUnit.setIndicadorPaquete("1");
        packageUnit.setIndicadorEstadoPaquete("A");
        packageUnit.setSucursal("0000");
        packageIatx.add(packageUnit);

        //retorna la lista de paquetes parseada
        consultaPaquetesOutEntity.setPaquetes(packageIatx);
        consultaPaquetesOutEntity.setCantidadCuentas(1L);
        consultaPaquetesOutEntity.setCodigoRetornoExtendido("0");

        return consultaPaquetesOutEntity;
    }

}