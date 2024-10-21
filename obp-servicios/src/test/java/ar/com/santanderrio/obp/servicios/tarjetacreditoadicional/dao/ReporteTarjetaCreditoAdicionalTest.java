package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.impl.AltaTarjetaCreditoAdicionalDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.ComprobanteAltaTarjCredAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.TarjetaAdicionalSolicitadaView;

/**
 * The Class ReporteCBUCuentaDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporteTarjetaCreditoAdicionalTest {

    /** The reporte CBU cuenta DAO. */
    @InjectMocks
    private AltaTarjetaCreditoAdicionalDAO altaTarjetaCreditoAdicionalDAO = new AltaTarjetaCreditoAdicionalDAOImpl();

    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();

    /**
     * Inits the.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test reporte.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    
    @Test
    public void testReporte() throws IOException, IllegalAccessException {
        
        ComprobanteAltaTarjCredAdicionalView views = new ComprobanteAltaTarjCredAdicionalView();
        views.setApellido("apellido");
        views.setDniAdicional("21321");
        
        views.setNombre("Nombre");
        views.setFechaHora("01/09/2017");
        
        List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas = new ArrayList<TarjetaAdicionalSolicitadaView>();
        TarjetaAdicionalSolicitadaView tarjeta1 = new TarjetaAdicionalSolicitadaView();
        tarjeta1.setPorcentajeLimiteDeCompra("1000");
        tarjeta1.setSolicitudNro("1234443");
        tarjeta1.setTipoCuenta("Tipo de Cuenta");
        tarjeta1.setNombreTarjeta("VISA");
        
        tarjetasAdicionalesSolicitadas.add(tarjeta1);
        views.setTarjetasAdicionalesSolicitadas(tarjetasAdicionalesSolicitadas);
        
        
        FieldUtils.writeField(altaTarjetaCreditoAdicionalDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);

        FieldUtils.writeField(altaTarjetaCreditoAdicionalDAO, "logoCierre", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        
        FieldUtils.writeField(altaTarjetaCreditoAdicionalDAO, "logoDefault", appContext.getResource("classpath:/report/logo_rio.jpg"),
                true);
        FieldUtils.writeField(altaTarjetaCreditoAdicionalDAO, "reportFile",
                appContext.getResource("classpath:/report/tarjetaCreditoAdicional/ComprobanteTarjCredAdic.jasper"), true);
        Reporte reporte = altaTarjetaCreditoAdicionalDAO.generarComprobante(views);
        Assert.assertNotNull(reporte.getBytes());
        
        //FileUtils.writeByteArrayToFile(new File("C:\\Users\\desa\\Desktop\\fuentes\\file10.pdf"), reporte.getBytes());
    }

}

