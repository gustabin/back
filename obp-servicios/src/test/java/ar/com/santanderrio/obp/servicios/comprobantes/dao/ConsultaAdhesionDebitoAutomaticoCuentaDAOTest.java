package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ConsultaAdhesionDebitoAutomaticoCuentaDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ConsultaAdhesionDebitoAutomaticoCuentaDAOTest.CNSDDIADHGDAOITConfiguration.class })
public class ConsultaAdhesionDebitoAutomaticoCuentaDAOTest extends IatxBaseDAOTest {

    @Autowired
    @InjectMocks
    private ConsultaAdhesionDebitoAutomaticoCuentaDAO serviceDAO;

    @Configuration
    public static class CNSDDIADHGDAOITConfiguration {

        @Bean
        public ConsultaAdhesionDebitoAutomaticoCuentaDAO serviceDAO() {
            return new ConsultaAdhesionDebitoAutomaticoCuentaDAOImpl();
        }

    }

    @Test
    public void consultaTest() throws DAOException, IatxException {
        EmpresasAdheridasDebitoAutoInEntity entity = new EmpresasAdheridasDebitoAutoInEntity();
        entity.setCliente(obtenerCliente());
        entity.setNombreApellido("ZAES ZIMNY            M COVADO");
        entity.setBloqueRellamada("30710408323LAKAUT SA 18073IC04171751E      00106601330546                                           ");

        String cnsddiadhgResponse = "200000000000P04HTML0009900010302JIBE95  XXXXXXXX00933886000000142020060110472200000000IBF3036S000000000000CNSDDIADHG1200000            02981495    00        00 000000000202006011046420000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0118300000000õSõ                                                                                                    õ011õ30710408323õLAKAUT SA õ18073IC04173029E      õ00000000000000õ2õ20181031õACTEõ02õ066õ01330546õ001õ30710408323õLAKAUT SA õ18073IC06000825W      õ00000000000000õ2õ20180424õACTEõ02õ066õ01330546õ001õ30710408323õLAKAUT SA õ18073IC06000826A      õ00000000000000õ2õ20180425õACTEõ02õ066õ01330546õ001õ30710408323õLAKAUT SA õ18073IC24000213N      õ00000000000000õ2õ20180315õACTEõ02õ066õ01330546õ001õ30710408323õLAKAUT SA õ18073IC24000480T      õ00000000000000õ2õ20180515õACTEõ02õ066õ01330546õ001õ30710408323õLAKAUT SA õ18073IDA4000446Y      õ00000000000000õ2õ20180423õACTEõ02õ066õ01330546õ001õ30710408323õLAKAUT SA õ19001IC04125976E      õ00000000000000õ2õ20190801õACTEõ02õ066õ01330546õ001õ33693675486õPLANRG4057õ30661744665           õ00000000000000õ2õ20181214õACTEõ02õ066õ01330546õ001õ33693675486õPLANRG4268õ30661744665           õ00000000000000õ2õ20180815õACTEõ02õ066õ01330546õ001õ33693675486õR1966R196Hõ30661744665           õ00000000000000õ2õ20081015õACTEõ02õ066õ01330546õ001õ33693675486õR1966R1967õ30661744665           õ00000000000000õ2õ20080515õACTEõ02õ066õ01330546õ001õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSDDIADHG", "120"))))
                .thenReturn(cnsddiadhgResponse);

        EmpresasAdheridasDebitoAutoOutEntity res = serviceDAO.consultar(entity);
        Assert.assertNotNull(res);
    }
    
    @Test
    public void consultaErrorTest() throws DAOException, IatxException {
        EmpresasAdheridasDebitoAutoInEntity entity = new EmpresasAdheridasDebitoAutoInEntity();
        entity.setCliente(obtenerCliente());
        entity.setNombreApellido("ZAES ZIMNY            M COVADO");
        entity.setBloqueRellamada(ISBANStringUtils.fillStr(ISBANStringUtils.ESPACIO_STRING, 100));
        String cnsddiadhgResponse = "200000000000P04HTML0009900010302JIBE95  XXXXXXXX00933886000000142020060110472200000000IBF3036S000000000000CNSDDIADHG1200000            00276937    00        00 014158778201705031414240000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0668900000010õ000õ01õ7593061701          7593061701          7593061701          7593061701          õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSDDIADHG", "120"))))
                .thenReturn(cnsddiadhgResponse);

        EmpresasAdheridasDebitoAutoOutEntity res = serviceDAO.consultar(entity);
        Assert.assertNotNull(res);
        Assert.assertEquals("10", res.getCodigoRetornoExtendido());
    }

    @Test(expected=DAOException.class)
    public void consultaExceptionTest() throws DAOException, IatxException {
        EmpresasAdheridasDebitoAutoInEntity entity = new EmpresasAdheridasDebitoAutoInEntity();
        entity.setCliente(obtenerCliente());
        entity.setNombreApellido("ZAES ZIMNY            M COVADO");
        entity.setBloqueRellamada(ISBANStringUtils.fillStr(ISBANStringUtils.ESPACIO_STRING, 100));
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSDDIADHG", "120"))))
                .thenThrow(new IatxException());

        EmpresasAdheridasDebitoAutoOutEntity res = serviceDAO.consultar(entity);
        Assert.assertNotNull(res);
    }
    
    /**
     * Obtener cliente.
     *
     * @return the cliente
     */
    private Cliente obtenerCliente() {
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        Cliente c1 = new Cliente();
        c1.setNombre("COVADONGA KLAVDIA");
        c1.setApellido1("ZAES");
        c1.setApellido2("ZIMNY            M");
        c1.setSegmento(segmento);
        return c1;
    }
}
