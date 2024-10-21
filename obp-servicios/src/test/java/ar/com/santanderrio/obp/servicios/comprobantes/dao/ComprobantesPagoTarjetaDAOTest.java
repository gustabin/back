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
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ComprobantesPagoTarjetaDAOImpl;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ComprobantesPagoTarjetaDAOTest.CNSTJCPDODAOITConfiguration.class })
public class ComprobantesPagoTarjetaDAOTest extends IatxBaseDAOTest {

    @Autowired
    @InjectMocks
    private ComprobantesPagoTarjetaDAO serviceDAO;

    @Configuration
    public static class CNSTJCPDODAOITConfiguration {

        @Bean
        public ComprobantesPagoTarjetaDAO serviceDAO() {
            return new ComprobantesPagoTarjetaDAOImpl();
        }

    }

    @Test
    public void consultaTest() throws DAOException, IatxException {
        ComprobanteVisaAmexIATXInEntity entity = new ComprobanteVisaAmexIATXInEntity();
        entity.setCliente(obtenerCliente());
        String cnstjcpdoResponse = "200000000000P04HTML0009900010320064145  ********00559754000000142017050202464000000000KU002743000000000000CNSTJCPDO_1100000            20064145    00        00 002403591201705020246330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0038800000000õ005õ20161207õARSõ000000000007790+õ10934049õADOMõAõ20161213õDAõ000000000000000+õ20170104õARSõ000000000008791+õ11449809õADOMõAõ20170106õDAõ000000000000000+õ20170314õARSõ000000000001000+õ12750486õADOMõAõ20170317õDVõ000000000000000+õ20170315õARSõ000000000017938+õ12750485õADOMõAõ20170317õDVõ000000000000000+õ20170403õARSõ000000000007888+õ12923875õ0000õAõ20170405õ04õ000000000000000+õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSTJCPDO_", "110"))))
                .thenReturn(cnstjcpdoResponse);
        /**
         * 
         * ERROR TRAMAS?O ERROR DTF?
         * 
         */
        ComprobanteVisaAmexIATXOutEntity res = serviceDAO.consultar(entity);
        Assert.assertNotNull(res);
    }
    
    @Test
    public void consultaErrorTest() throws DAOException, IatxException {
        ComprobanteVisaAmexIATXInEntity entity = new ComprobanteVisaAmexIATXInEntity();
        entity.setCliente(obtenerCliente());
        String cnstjcpdoResponse = "200000000000P04HTML0009900010320064145  ********00559754000000142017050202464000000000KU002743000000000000CNSTJCPDO_1100000            20064145    00        00 002403591201705020246330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0668900000010õ000õ01õ7593061701          7593061701          7593061701          7593061701          õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSTJCPDO_", "110"))))
                .thenReturn(cnstjcpdoResponse);
        /**
         * 
         * ERROR TRAMAS?O ERROR DTF?
         * 
         */
        ComprobanteVisaAmexIATXOutEntity res = serviceDAO.consultar(entity);
        Assert.assertNotNull(res);
    }
    
    @Test(expected = DAOException.class)
    public void consultaExceptionTest() throws DAOException, IatxException {
        ComprobanteVisaAmexIATXInEntity entity = new ComprobanteVisaAmexIATXInEntity();
        entity.setCliente(obtenerCliente());
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSTJCPDO_", "110"))))
                .thenThrow(new IatxException());
        /**
         * 
         * ERROR TRAMAS?O ERROR DTF?
         * 
         */
        ComprobanteVisaAmexIATXOutEntity res = serviceDAO.consultar(entity);
        Assert.assertNotNull(res);
    }
    
    @Test
    public void obtenerOutEntityAsyncTest() throws DAOException, IatxException {
        
        Future<ComprobanteVisaAmexIATXOutEntity> rta = serviceDAO.obtenerOutEntityErrorAsync();
        Future<ComprobanteVisaAmexIATXOutEntity> rta2 = serviceDAO.obtenerOutEntityErrorAsync();

        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        ComprobanteVisaAmexIATXOutEntity respuesta = null;
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
            ComprobanteVisaAmexIATXOutEntity comp2 = rta.get();
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
    public void consultaAsyncTest() throws DAOException, IatxException {
        ComprobanteVisaAmexIATXInEntity entity = new ComprobanteVisaAmexIATXInEntity();
        entity.setCliente(obtenerCliente());
        String cnstjcpdoResponse = "200000000000P04HTML0009900010320064145  ********00559754000000142017050202464000000000KU002743000000000000CNSTJCPDO_1100000            20064145    00        00 002403591201705020246330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0038800000000õ005õ20161207õARSõ000000000007790+õ10934049õADOMõAõ20161213õDAõ000000000000000+õ20170104õARSõ000000000008791+õ11449809õADOMõAõ20170106õDAõ000000000000000+õ20170314õARSõ000000000001000+õ12750486õADOMõAõ20170317õDVõ000000000000000+õ20170315õARSõ000000000017938+õ12750485õADOMõAõ20170317õDVõ000000000000000+õ20170403õARSõ000000000007888+õ12923875õ0000õAõ20170405õ04õ000000000000000+õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSTJCPDO_", "110"))))
                .thenReturn(cnstjcpdoResponse);
        Future<ComprobanteVisaAmexIATXOutEntity> rta = serviceDAO.consultarAsync(entity);
        Future<ComprobanteVisaAmexIATXOutEntity> rta2 = serviceDAO.consultarAsync(entity);

        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        ComprobanteVisaAmexIATXOutEntity respuesta = null;
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
            ComprobanteVisaAmexIATXOutEntity comp2 = rta.get();
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
