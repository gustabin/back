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
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.impl.ConsultaTarjetaTitularDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaOutEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ConsultaTarjetaTitularDAOTest.ConsultaTarjetaTitularDAOTestConfiguration.class })
public class ConsultaTarjetaTitularDAOTest extends IatxBaseDAOTest{
    
    @Autowired
    private ConsultaTarjetaTitularDAO consultaTarjetaTitularDAO;
    
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
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao" })
    public static class ConsultaTarjetaTitularDAOTestConfiguration {
        @Bean
        ConsultaTarjetaTitularDAO consultaTarjetaTitularDAO() {
            return new ConsultaTarjetaTitularDAOImpl();
        }
    }
    
    @Test
    public void obtenerDetalleDatosTarjetaTest() throws IatxException, DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException {
        
        String servicio = "CNSTJCDET_";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00615037000000062017081815061900000000IBF009WJ000000000000CNSTJCDET_1000000            00276937    00        00 015031310201708181506090000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0047800000000õ3õ0058188800õLõPLATINUM            õ1õPERSONAL  õ10õ033õMILANDO  CONSTANCIO PERCY     õ01õ36õS õ000000000007032õ100õ0000000006328õ100õ03õ2õNO MANTIENEõ03õACTEõ033õ0õ3663936õ01õCALLE 128               õ00124õ  õ   õ    õ 7107   õ     õ02õLOCALIDAD FALSEõ õ    18231723õ            õCTE SDO.ACõOPNMKT  õ00õ1õSUCURSAL        õ000113õSUPERCLUB PQ PLATõ20131127õA125844 õ00000000õ                    õ00000000õ000000õ        õ000õ0000õ10õACTIVA         õ01õCICLO-30õ000õUSDõ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaDetalleDatosTarjetaInEntity consultaDetalleDatosTarjetaInEntity = new ConsultaDetalleDatosTarjetaInEntity();
        consultaDetalleDatosTarjetaInEntity.setCliente(cliente);
        consultaDetalleDatosTarjetaInEntity.setTipoTarjeta("1");
        consultaDetalleDatosTarjetaInEntity.setNroCuentaTarjeta("12345678");
        ConsultaDetalleDatosTarjetaOutEntity respuesta = consultaTarjetaTitularDAO.obtenerDetalleDatosTarjeta(consultaDetalleDatosTarjetaInEntity);
        Assert.assertEquals("000000000007032", respuesta.getLimiteDeCompra());
    }

}
