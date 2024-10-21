/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.LimitesFiltrosBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.exceptions.ValidarFechaBuscadorException;
import ar.com.santanderrio.obp.servicios.comprobantes.exceptions.ValidarFechaDesdeBuscadorException;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.TransaccionViewIn;

/**
 * The Class LimitesFiltrosBOTest.
 * 
 * @author sabrina.cis
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        LimitesFiltrosBOTest.LimitesFiltrosBOTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "ANIOS.FILTROS = 10","ANIOS.DEBITOSAUTOMATICOS = 10", "ANIOS.PMC = 10", "ANIOS.PAGOTARJETA = 10",
        "ANIOS.PAGOSUELDOSHONORARIOS = 10", "ANIOS.TRANSFERENCIAS = 10","ANIOS.COMPRAVENTA = 10","ANIOS.PAGOCOMPRAS = 10",
        "PARAMETRIZACION.INVOCACION.SCOMP = 01/01/2017", "PARAMETRIZACION.INVOCACION.IATX = 01/01/2017",
        "LIMITE.DATOS.PAGO.SERVICIOS = 1000" })
@ActiveProfiles(value = Profiles.TEST)
public class LimitesFiltrosBOTest {

    /** The limites filtros BO. */
    @Autowired
    private LimitesFiltrosBO limitesFiltrosBO;

    /**
     * Setup mock.
     */
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class LimitesFiltrosBOTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackages = {
            "Open Declaration ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.LimitesFiltrosBOImpl.java,",
            "ar.com.santanderrio.obp.servicios.comprobantes.bo.LimitesFiltrosBO.java" })
    public static class LimitesFiltrosBOTestConfiguration {

        /**
         * Poder leer properties de un properties file.
         * 
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        /**
         * Limites filtros BO.
         * 
         * @return the limites filtros BO impl
         */
        @Bean
        public LimitesFiltrosBOImpl limitesFiltrosBO() {
            return new LimitesFiltrosBOImpl();
        }
    }

    /**
     * Obtener filtro comprobante DTO.
     * 
     * @throws ParseException
     *             the parse exception
     * @throws ValidarFechaDesdeBuscadorException
     * @throws ValidarFechaBuscadorException
     */
    @Test
    public void obtenerFiltroComprobanteDTO()
            throws ParseException, ValidarFechaBuscadorException, ValidarFechaDesdeBuscadorException {

        FiltroComprobanteDTO dto = limitesFiltrosBO.obtenerFiltroComprobanteDTO(
                getComprobantesViewIn("12/06/2017", "24/10/2017", TransaccionEnum.PAGOS_DE_SERVICIOS.getId()),
                obtenerCliente());

        Assert.assertEquals(TransaccionEnum.PAGOS_DE_SERVICIOS, dto.getTransacciones().get(0).getTransaccion());

    }

    /**
     * Gets the comprobantes view in.
     * 
     * @param fechaDesde
     *            the fecha desde
     * @param fechaHasta
     *            the fecha hasta
     * @param transaccion
     *            the transaccion
     * @return the comprobantes view in
     */
    public ComprobantesViewIn getComprobantesViewIn(String fechaDesde, String fechaHasta, Integer transaccion) {
        List<TransaccionViewIn> transacciones = new ArrayList<TransaccionViewIn>();
        transacciones.add(new TransaccionViewIn(transaccion));
        return new ComprobantesViewIn(false, fechaDesde, fechaHasta, transacciones, null, null, null);
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
