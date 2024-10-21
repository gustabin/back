package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresaAdheridaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ConsultaDebitoDirectoDAOImpl;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ConsultaDebitoDirectoDAOTest.CNSDDIDEREDAOITConfiguration.class })
public class ConsultaDebitoDirectoDAOTest extends IatxBaseDAOTest {

    @Autowired
    @InjectMocks
    private ConsultaDebitoDirectoDAO serviceDAO;

    @Configuration
    public static class CNSDDIDEREDAOITConfiguration {

        @Bean
        public ConsultaDebitoDirectoDAO serviceDAO() {
            return new ConsultaDebitoDirectoDAOImpl();
        }

    }

    @Test
    public void consultaTest() throws DAOException, IatxException {
        ComprobanteDebitoAutomaticoInEntity inEntity = new ComprobanteDebitoAutomaticoInEntity();
        inEntity.setCliente(obtenerCliente());
        inEntity.setEmpresa(new EmpresaAdheridaEntity());
        inEntity.setFechaContinuacion("        ");
        inEntity.setFechaDesde("20182014");
        inEntity.setFechaHasta("20182014");
        inEntity.setIDDebitoDDICont("               ");
        String cnsddidereResponse = "200000000000P04HTML0009900010303901526  ********00404358000000302018071818020500000000IBF4A0OO000000000000CNSDDIDERE1000000            03901526    00        00 018011287201807181801450000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0117300000000õ300300õ0012õ20180216õ20180216õR3920I836044016õ00000000000000õ00000000051188õ000000000õ02õ059õ03652185õ1õR00õ20180316õ20180316õR3920I836044017õ00000000000000õ00000000051188õ000000000õ02õ059õ03652185õ1õR00õ20180416õ20180416õR3920I836044018õ00000000000000õ00000000051188õ000000000õ02õ059õ03652185õ1õR00õ20180516õ20180516õR3920I836044019õ00000000000000õ00000000051188õ000000000õ02õ059õ03652185õ1õR00õ20180618õ20180618õR3920I836044020õ00000000000000õ00000000051188õ000000000õ02õ059õ03652185õ1õR00õ20180716õ20180716õR3920I836044021õ00000000000000õ00000000051188õ000000000õ02õ059õ03652185õ1õR00õ20180216õ20180216õR3920I841725016õ00000000000000õ00000000075209õ000000000õ02õ059õ03652185õ1õR00õ20180316õ20180316õR3920I841725017õ00000000000000õ00000000075209õ000000000õ02õ059õ03652185õ1õR00õ20180416õ20180416õR3920I841725018õ00000000000000õ00000000075209õ000000000õ02õ059õ03652185õ1õR00õ20180516õ20180516õR3920I841725019õ00000000000000õ00000000075209õ000000000õ02õ059õ03652185õ1õR00õ20180618õ20180618õR3920I841725020õ00000000000000õ00000000075209õ000000000õ02õ059õ03652185õ1õR00õ20180716õ20180716õR3920I841725021õ00000000000000õ00000000075209õ000000000õ02õ059õ03652185õ1õR00õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSDDIDERE", "100"))))
                .thenReturn(cnsddidereResponse);

        ComprobanteDebitoAutomaticoOutEntity res = serviceDAO.consultar(inEntity);
        Assert.assertNotNull(res);
    }
    
    @Test
    public void consultaAsyncTest() throws DAOException, IatxException {
        ComprobanteDebitoAutomaticoInEntity inEntity = new ComprobanteDebitoAutomaticoInEntity();
        inEntity.setCliente(obtenerCliente());
        inEntity.setEmpresa(new EmpresaAdheridaEntity());
        inEntity.setFechaContinuacion("        ");
        inEntity.setFechaDesde("20172014");
        inEntity.setFechaHasta("20172014");
        inEntity.setIDDebitoDDICont("               ");
        String cnsddidereResponse = "200000000000P04HTML0009900010303348599  ********00880735000000132017050314281100000000IBF37272000000000000CNSDDIDERE1000000            03348599    00        00 014239644201705031428040000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0155700000000õ300300õ0016õ20170119õ20170119õ03464269800SCIAõ00000000000000õ00000000118332õ000000000õ02õ100õ03561278õ1õR00õ20170119õ20170119õ03464269801SCIAõ00000000000000õ00000000072036õ000000000õ02õ100õ03561278õ1õR00õ20170119õ20170119õ03464269802SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170119õ20170119õ03464269803SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170215õ20170215õ05634269800SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170215õ20170215õ05634269801SCIAõ00000000000000õ00000000082536õ000000000õ02õ100õ03561278õ1õR00õ20170215õ20170215õ05634269802SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170215õ20170215õ05634269803SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170317õ20170317õ08134269800SCIAõ00000000000000õ00000000118332õ000000000õ02õ100õ03561278õ1õR00õ20170317õ20170317õ08134269801SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170317õ20170317õ08134269802SCIAõ00000000000000õ00000000072036õ000000000õ02õ100õ03561278õ1õR00õ20170317õ20170317õ08134269803SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170418õ20170418õ10474269800SCIAõ00000000000000õ00000000082536õ000000000õ02õ100õ03561278õ1õR00õ20170418õ20170418õ10474269801SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170418õ20170418õ10474269802SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ20170418õ20170418õ10474269803SCIAõ00000000000000õ00000000107832õ000000000õ02õ100õ03561278õ1õR00õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSDDIDERE", "100"))))
                .thenReturn(cnsddidereResponse);
        
        Future<ComprobanteDebitoAutomaticoOutEntity> rta = serviceDAO.consultarAsync(inEntity);
        Future<ComprobanteDebitoAutomaticoOutEntity> rta2 = serviceDAO.consultarAsync(inEntity);

        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        ComprobanteDebitoAutomaticoOutEntity respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            ComprobanteDebitoAutomaticoOutEntity comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void consultaErrorTest() throws DAOException, IatxException {
        ComprobanteDebitoAutomaticoInEntity inEntity = new ComprobanteDebitoAutomaticoInEntity();
        inEntity.setCliente(obtenerCliente());
        inEntity.setEmpresa(new EmpresaAdheridaEntity());
        inEntity.setFechaContinuacion("        ");
        inEntity.setFechaDesde("20172014");
        inEntity.setFechaHasta("20172014");
        inEntity.setIDDebitoDDICont("               ");
        String cnsddidereResponse = "200000000000P04HTML0009900010303348599  ********00880735000000132017050314281100000000IBF37272000000000000CNSDDIDERE1000000            03348599    00        00 014239644201705031428040000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0668900000010õ000õ01õ7593061701          7593061701          7593061701          7593061701          õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSDDIDERE", "100"))))
                .thenReturn(cnsddidereResponse);

        ComprobanteDebitoAutomaticoOutEntity res = serviceDAO.consultar(inEntity);
        Assert.assertNotNull(res);
        Assert.assertEquals("10", res.getCodigoRetornoExtendido());
    }
    
    @Test
    public void consultaExceptionTest() throws DAOException, IatxException {
        ComprobanteDebitoAutomaticoInEntity inEntity = new ComprobanteDebitoAutomaticoInEntity();
        inEntity.setCliente(obtenerCliente());
        inEntity.setEmpresa(new EmpresaAdheridaEntity());
        inEntity.setFechaContinuacion("        ");
        inEntity.setFechaDesde("20172014");
        inEntity.setFechaHasta("20172014");
        inEntity.setIDDebitoDDICont("               ");
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSDDIDERE", "100"))))
                .thenThrow(new IatxException());

        ComprobanteDebitoAutomaticoOutEntity res = serviceDAO.consultar(inEntity);
        Assert.assertEquals("1", res.getCodigoRetornoExtendido());
        
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
        c1.setNombre("CONSTANCIO PERCY");
        c1.setApellido1("MILANDO");
        c1.setApellido2("M");
        c1.setSegmento(segmento);
        return c1;
    }

}
