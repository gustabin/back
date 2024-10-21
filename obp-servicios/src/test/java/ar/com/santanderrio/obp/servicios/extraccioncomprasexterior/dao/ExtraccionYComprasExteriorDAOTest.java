package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao;

import static org.mockito.Mockito.when;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.impl.ExtraccionYComprasExteriorDAOImpl;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ComprobanteOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorOutEntity;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ExtraccionYComprasExteriorDAOTest.ExtraccionYComprasExteriorDAOTestConfiguration.class })
public class ExtraccionYComprasExteriorDAOTest extends IatxBaseDAOTest {
    
    /** The ExtraccionYComprasExteriorDAO. */
    @Autowired
    @InjectMocks
    private ExtraccionYComprasExteriorDAO extraccionYComprasExteriorDAO;
    
    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
    
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    

    private static final String TIPO_TARJETA = "E";
    private static final String CUENTAS_RELACIONADAS = "C";
    private static final String CONSULTA_TARJETA = "T";
    private static final String CNSTARJETAS_PRIMER_LLAMADO = "P";
    private static final String CNSTARJETAS_CANTIDAD_PEDIDA = "50";    
    private static final String CNSTARJETAS_SEGUNDO_LLAMADO = "S";
    private static final int CNSTARJETAS_FIL_80 = 80;
    private static final String CMBTARJETA_TIPO_LLAMADO_M = "M";
    private static final String CMBTARJETA_TIPO_MODIF_T = "T";
    private static final String CMB_TARJETA_CODIGO_11 = "11";
    

    @Configuration
    public static class ExtraccionYComprasExteriorDAOTestConfiguration {

        /**
         * CambioDomicilioDAO.
         *
         * @return the CambioDomicilioDAO
         */
        @Bean
        public ExtraccionYComprasExteriorDAO extraccionYComprasExteriorDAO() {
            return new ExtraccionYComprasExteriorDAOImpl();
        }

    }
    

    /**
     * Inits.
     */
    @Before
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Silvina");
        cliente.setApellido1("Luque");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");
    }
    
   
    
 
    /**
     * consultarCuentasOperaExteriorTest
     * 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void consultarCuentasOperaExteriorTest() throws IatxException, DAOException{
        String servicio = "CNSMASCTAS";
        String version = "100";
        String tramaResponse ="200000000000P04HTML0009900010300CRQJ37  ********00163354000000362017090412281800000000IBF00A2D000000000000CNSMASCTAS1000000            00276937    00        00 000000000201709041228080000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0091800000000õ005õ4517660051579398   õACTE003300000008199500101õ1õ õ õ           õ20040506A008981 õ20071003A127795 õNõ                                                                                õ4517660051579398   õACTE003300000361253200101õ3õ õ õ           õ20071003A127795 õ20071003A127795 õNõ                                                                                õ4517660051579398   õACAH003300000361253200101õ1õ õ õ           õ20071003A127795 õ20071003A127795 õNõ                                                                                õ4517660051579398   õACCD003300000361253200102õ1õ õ õ           õ20071003A127795 õ20071003A127795 õNõ                                                                                õ4517660051579398   õACAD003300000361253200102õ1õ õ õ           õ20071003A127795 õ20071003A127795 õNõ                                                                                õ";
        ConsultaCuentasOperaExteriorInEntity consultaCuentasInEntity = new ConsultaCuentasOperaExteriorInEntity();
        consultaCuentasInEntity.setCliente(cliente);
        consultaCuentasInEntity.setCuentasRelacionadas(CUENTAS_RELACIONADAS);
        consultaCuentasInEntity.setNumeroTarjeta("4517660051579398");
        consultaCuentasInEntity.setTipoConsulta(CONSULTA_TARJETA);
        consultaCuentasInEntity.setTipoTarjeta(TIPO_TARJETA);

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaCuentasOperaExteriorOutEntity respuesta = extraccionYComprasExteriorDAO.consultarCuentasOperaExterior(consultaCuentasInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    /**
     * consultarCuentasOperaExteriorTest
     * 
     * @throws IatxException
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void consultarCuentasOperaExteriorErrorTest() throws IatxException, DAOException{
        String servicio = "CNSMASCTAS";
        String version = "100";
        String tramaResponse ="200000000000Q04HTML0009900010300CRQJ37  ********00163354000000362017090412281800000000        00000000CNSMASCTAS1000000            00276937    00        00  IN000215871831970042000000        0000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00026TõCõ4517660051579398   õEõ";
        ConsultaCuentasOperaExteriorInEntity consultaCuentasInEntity = new ConsultaCuentasOperaExteriorInEntity();
        consultaCuentasInEntity.setCliente(cliente);
        consultaCuentasInEntity.setCuentasRelacionadas(CUENTAS_RELACIONADAS);
        consultaCuentasInEntity.setNumeroTarjeta("4517669991579398");
        consultaCuentasInEntity.setTipoConsulta(CONSULTA_TARJETA);
        consultaCuentasInEntity.setTipoTarjeta(TIPO_TARJETA);

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaCuentasOperaExteriorOutEntity respuesta = extraccionYComprasExteriorDAO.consultarCuentasOperaExterior(consultaCuentasInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    

    /**
     * consultarTarjetasOperaExteriorTest
     * 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void consultarTarjetasOperaExteriorTest() throws IatxException, DAOException{
        String servicio = "CNSTARXNUP";
        String version = "100";
        String tramaResponse ="200000000000P04HTML0009900010302GLPE92  ********00923278000000032017091316085700000000IBF00IRX000000000000CNSTARXNUP1000000            02615492    00        00 000000000201709131608210000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0062000000000õNõ                                                                                                                        õ001õ4517660021778823   õ0000õEõ*õ04õ1õ00720000007000639170ARS00101õ202010õ19980626        õ19980626õ20140225õ20170829HOMEBANK19980702        õ2017082920151217  õ00000000õ102õ   õ  õIõ                       õ                        õ                   õ  õ   000                                                                          õ00õ0000õ                              õ  õ           õ                              õ               õ                                                  õ";
        ConsultaTarjetasOperaExteriorInEntity consultaTarjetasInEntity = new ConsultaTarjetasOperaExteriorInEntity();
        String tipoLlamado = CNSTARJETAS_PRIMER_LLAMADO;
        String claveSegundoLlamado = "";
        consultaTarjetasInEntity.setCliente(cliente);
        consultaTarjetasInEntity.setNup(cliente.getNup());
        consultaTarjetasInEntity.setCantidadPedida(CNSTARJETAS_CANTIDAD_PEDIDA);
        consultaTarjetasInEntity.setDatosADevolver(CONSULTA_TARJETA);
        consultaTarjetasInEntity.setTipoConsulta(tipoLlamado);
        if(CNSTARJETAS_SEGUNDO_LLAMADO.equals(tipoLlamado)) {
            consultaTarjetasInEntity.setClaveSegundoLlamado(claveSegundoLlamado);
        }else {
            consultaTarjetasInEntity.setClaveSegundoLlamado(ISBANStringUtils.fillStr("", CNSTARJETAS_FIL_80));
        }
        consultaTarjetasInEntity.setDatosAdicionaes(ISBANStringUtils.fillStr("", CNSTARJETAS_FIL_80));  
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaTarjetasOperaExteriorOutEntity respuesta = extraccionYComprasExteriorDAO.consultarTarjetasOperaExterior(consultaTarjetasInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    
    
    /**
     * consultarTarjetasOperaExteriorErrorTest
     * 
     * @throws IatxException
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void consultarTarjetasOperaExteriorErrorTest() throws IatxException, DAOException{
        String servicio = "CNSTARXNUP";
        String version = "100";
        String tramaResponse ="200000000000Q04HTML0009900010300CRQJ37  ********00163354000000362017090412281800000000        00000000CNSMASCTAS1000000            00276937    00        00  IN000215871831970042000000        0000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00026TõCõ4517660051579398   õEõ";
        ConsultaTarjetasOperaExteriorInEntity consultaTarjetasInEntity = new ConsultaTarjetasOperaExteriorInEntity();
        String tipoLlamado = CNSTARJETAS_PRIMER_LLAMADO;
        consultaTarjetasInEntity.setCliente(cliente);
        consultaTarjetasInEntity.setNup(cliente.getNup());
        consultaTarjetasInEntity.setCantidadPedida(CNSTARJETAS_CANTIDAD_PEDIDA);
        consultaTarjetasInEntity.setDatosADevolver(CONSULTA_TARJETA);
        consultaTarjetasInEntity.setTipoConsulta(tipoLlamado);
        consultaTarjetasInEntity.setClaveSegundoLlamado(ISBANStringUtils.fillStr("", CNSTARJETAS_FIL_80));
        consultaTarjetasInEntity.setDatosAdicionaes(ISBANStringUtils.fillStr("", CNSTARJETAS_FIL_80));  
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaTarjetasOperaExteriorOutEntity respuesta = extraccionYComprasExteriorDAO.consultarTarjetasOperaExterior(consultaTarjetasInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    /**
     * cambioTarjetaOperaExteriorTest
     * 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
     public void cambioTarjetaOperaExteriorTest() throws IatxException, DAOException{
        String servicio = "CMBRIOTARJ";
        String version = "100";
        String tramaResponse ="200000000000P04HTML0009900010320064145  ********00130671000000322017091218072700000000IBF4R2UF000000000000CMBRIOTARJ1000000            20064145    00        00 018089357201709121807170000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0004100000000õMõ00001õ001õ4517660118184661   õ";
        CambioTarjetaOperaExteriorInEntity cambioTarjetasInEntity = new CambioTarjetaOperaExteriorInEntity();
        cambioTarjetasInEntity.setCliente(cliente);
        cambioTarjetasInEntity.setTipoLlamado(CMBTARJETA_TIPO_LLAMADO_M);
        cambioTarjetasInEntity.setTipoModificacion(CMBTARJETA_TIPO_MODIF_T);
        cambioTarjetasInEntity.setClaveModificacion(ISBANStringUtils.fillStr("1234567891234567", 26));
        cambioTarjetasInEntity.setCodigoCambio(CMB_TARJETA_CODIGO_11);
        cambioTarjetasInEntity.setDatosVariables("ACTE0100000003750162001N 000030219572                                                                                   ");        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        CambioTarjetaOperaExteriorOutEntity respuesta = extraccionYComprasExteriorDAO.cambioTarjetaOperaExterior(cambioTarjetasInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
     }
    
     /**
      * cambioTarjetaOperaExteriorErrorTest
      * 
      * @throws IatxException
      * @throws DAOException
      */
     @Test(expected = DAOException.class)
      public void cambioTarjetaOperaExteriorErrorTest() throws IatxException, DAOException{
         String servicio = "CMBRIOTARJ";
         String version = "100";
         String tramaResponse ="200000000000Q04HTML0009900010300CRQJ37  ********00163354000000362017090412281800000000        00000000CNSMASCTAS1000000            00276937    00        00  IN000215871831970042000000        0000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00026TõCõ4517660051579398   õEõ";
         CambioTarjetaOperaExteriorInEntity cambioTarjetasInEntity = new CambioTarjetaOperaExteriorInEntity();
         cambioTarjetasInEntity.setCliente(cliente);
         cambioTarjetasInEntity.setTipoLlamado(CMBTARJETA_TIPO_LLAMADO_M);
         cambioTarjetasInEntity.setTipoModificacion(CMBTARJETA_TIPO_MODIF_T);
         cambioTarjetasInEntity.setClaveModificacion(ISBANStringUtils.fillStr("1234567891234567", 26));
         cambioTarjetasInEntity.setCodigoCambio(CMB_TARJETA_CODIGO_11);
         cambioTarjetasInEntity.setDatosVariables("ACTE0100000003750162001N 000030219572                                                                                   ");        
         when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
         CambioTarjetaOperaExteriorOutEntity respuesta = extraccionYComprasExteriorDAO.cambioTarjetaOperaExterior(cambioTarjetasInEntity);
         Assert.assertNotNull(respuesta);
         Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
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
         ComprobanteOperaExteriorInEntity datosComprobante = new ComprobanteOperaExteriorInEntity();
         datosComprobante.setNroComprobante("12312312312");
         datosComprobante.setNroTarjeta("XXXX-1234");
         datosComprobante.setNroCuenta("Cuenta en pesos 123 0003232/0");
         datosComprobante.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
         FieldUtils.writeField(extraccionYComprasExteriorDAO, "logoDebito", appContext.getResource("classpath:/report/comprobantes/logoDebito.png"), true);
         FieldUtils.writeField(extraccionYComprasExteriorDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
         FieldUtils.writeField(extraccionYComprasExteriorDAO, "logoCierre", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
         FieldUtils.writeField(extraccionYComprasExteriorDAO, "fileJasperComprobanteOperaExterior",
                 appContext.getResource("classpath:/report/extraccionYComprasExterior/ComprobanteExtYComprasExterior.jasper"), true);
         Reporte reporte = extraccionYComprasExteriorDAO.descargarComprobante(datosComprobante);
         Assert.assertNotNull(reporte.getBytes());
         //FileUtils.writeByteArrayToFile(new File("C:/tools/file10.pdf"),reporte.getBytes());

     }
    
}
