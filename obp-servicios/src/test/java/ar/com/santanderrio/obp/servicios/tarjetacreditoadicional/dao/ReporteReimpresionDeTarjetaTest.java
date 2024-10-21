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
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.ReimpresionTarjetasDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.impl.ReimpresionTarjetasDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.TarjetaSolicitadaView;

/**
 * The Class ReporteCBUCuentaDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporteReimpresionDeTarjetaTest {

    /** The reporte CBU cuenta DAO. */
    @InjectMocks
    private ReimpresionTarjetasDAO reimpresionTarjetasDAO = new ReimpresionTarjetasDAOImpl();

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
        
        DatosReimpresionComprobante views = new DatosReimpresionComprobante();
        
        List<DomicilioView> domicilios = new ArrayList<DomicilioView>();
        
        DomicilioView domicilioView = new DomicilioView();
        
        
        domicilioView.setDomicilio("Av el sol 654 San Martin");
        domicilioView.setTipoDomicilio("Domicilio Principal");
        
        domicilios.add(domicilioView);
        
        List<TarjetaSolicitadaView> tarjetasAdicionalesSolicitadas = new ArrayList<TarjetaSolicitadaView>();
        TarjetaSolicitadaView tarjeta1 = new TarjetaSolicitadaView();
        TarjetaSolicitadaView tarjeta2 = new TarjetaSolicitadaView();
        tarjeta1.setMotivo("Deterioro y/o Destrucción");
        tarjeta1.setSolicitudNro("12345");
        tarjeta1.setTipoCuenta("Visa Debito");
        tarjeta1.setTitular("Luis Ventocilla");
        tarjeta1.setNroTarjetaConFormato("XXXX-4398");
        
        
        tarjeta2.setMotivo("Deterioro y/o Destruccion");
        tarjeta2.setSolicitudNro("1234443");
        tarjeta2.setTipoCuenta("Visa");
        tarjeta2.setTitular("Luis Ventocilla");
        tarjeta2.setNroTarjetaConFormato("XXXX-4398");
        
        tarjetasAdicionalesSolicitadas.add(tarjeta1);
        tarjetasAdicionalesSolicitadas.add(tarjeta2);
        views.setTarjetas(tarjetasAdicionalesSolicitadas);
        views.setDomicilios(domicilios);
        
        
        FieldUtils.writeField(reimpresionTarjetasDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);

        FieldUtils.writeField(reimpresionTarjetasDAO, "logoPie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        
        FieldUtils.writeField(reimpresionTarjetasDAO, "fileJasper",
                appContext.getResource("classpath:/report/reimpresion/comprobanteReimpresion.jasper"), true);
        Reporte reporte = reimpresionTarjetasDAO.descargarComprobante(views);
        Assert.assertNotNull(reporte.getBytes());
        
      //  FileUtils.writeByteArrayToFile(new File("C:/tools/file10.pdf"), reporte.getBytes());
    }

}

