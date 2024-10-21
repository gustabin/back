package ar.com.santanderrio.obp.servicios.transferencias.sei;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEITest.TransferenciaSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.transferencias.sei.impl.TransferenciaSEIImpl;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class TransferenciaSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        TransferenciaSEITestConfiguration.class })
@Ignore
public class TransferenciaSEITest extends AbstractSEITest {

    /** The transferencia manager. */
    @Autowired
    private TransferenciaManager transferenciaManager;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class TransferenciaSEITestConfiguration.
     */
    @Configuration
    public static class TransferenciaSEITestConfiguration {

        /**
         * Transferencia manager.
         *
         * @return the transferencia manager
         */
        @Bean
        public TransferenciaManager transferenciaManager() {
            return Mockito.mock(TransferenciaManager.class);
        }

        /**
         * Transferencias SEI.
         *
         * @return the transferencia SEI
         */
        @Bean
        public TransferenciaSEI transferenciasSEI() {
            return new TransferenciaSEIImpl();
        }
    }

    /**
     * Validar CBU.
     */
    //TODO: corregir sacar Ignores
    @SuppressWarnings("unchecked")
    @Test
    public void validarCBU() {

        Respuesta<Boolean> respManager = new Respuesta<Boolean>();
        respManager.setEstadoRespuesta(EstadoRespuesta.ERROR);

        TransferenciaView tv = new TransferenciaView();
        tv.setImporte("1");
        tv.setMoneda("peso");
        tv.setConcepto(new ConceptoView("Alquiler","8", "VAR", "Alquiler", 0));
        tv.setDescripcion("Desc.-Valid_");
        tv.setMensajeEmail("Mensaje_prueba a-enviar v�a email.");
        tv.setNroCuentaDestino("000-063917/0");
        tv.setCbu("0720000788000006391704");

//        Mockito.when(transferenciaManager.validarCBU(Matchers.any(TransferenciaView.class))).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/validarCBU");

        Respuesta<Boolean> respSEI = new Respuesta<Boolean>();
        respSEI.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Respuesta<String> retorno = client.post(tv, Respuesta.class);
        Assert.assertNotNull(retorno);

    }

    /**
     * Gets the nueva transferencia.
     *
     * @return the nueva transferencia
     */
    // @SuppressWarnings("unchecked")
    // @Test
    // public void getNuevaTransferencia() {
    //
    // Respuesta<TransferenciaView> respManager = new
    // Respuesta<TransferenciaView>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    // respManager.setRespuestaVacia(true);
    //
    // Mockito.when(transferenciaManager.nuevaTransferencia(Matchers.any(TransferenciaView.class)))
    // .thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/nuevaTransferencia");
    //
    // Respuesta<TransferenciaView> respSEI = new
    // Respuesta<TransferenciaView>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    // respSEI.setRespuesta(transferenciaView);
    // respSEI.setRespuestaVacia(false);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }
    //
    // /**
    // * Validar nueva transferencia.
    // *
    // * @throws ManagerException
    // * the manager exception
    // */
    // @SuppressWarnings("unchecked")
    // @Test
    // public void validarNuevaTransferencia() {
    //
    // Respuesta<TransferenciaView> respManager = new
    // Respuesta<TransferenciaView>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    // respManager.setRespuestaVacia(true);
    //
    // Mockito.when(transferenciaManager.validarNuevaTransferencia(Matchers.any(TransferenciaView.class)))
    // .thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/validarNuevaTransferencia");
    //
    // Respuesta<TransferenciaView> respSEI = new
    // Respuesta<TransferenciaView>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    // respSEI.setRespuesta(transferenciaView);
    // respSEI.setRespuestaVacia(false);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Test
    // public void confirmarTransferencia() {
    //
    // Respuesta<TransferenciaView> respManager = new
    // Respuesta<TransferenciaView>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    // respManager.setRespuestaVacia(true);
    //
    // Mockito.when(transferenciaManager.confirmarTransferencia(Matchers.any(TransferenciaView.class)))
    // .thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/confirmarTransferencia");
    //
    // Respuesta<TransferenciaView> respSEI = new
    // Respuesta<TransferenciaView>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    // respSEI.setRespuesta(transferenciaView);
    // respSEI.setRespuestaVacia(false);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Test
    // public void destinatarioNoVerificado() {
    //
    // Respuesta<TransferenciaView> respManager = new
    // Respuesta<TransferenciaView>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    // respManager.setRespuestaVacia(true);
    //
    // Mockito.when(transferenciaManager.obtenerCbuEnSesion(Matchers.any(TransferenciaView.class)))
    // .thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/destinatarioNoVerificado");
    //
    // Respuesta<TransferenciaView> respSEI = new
    // Respuesta<TransferenciaView>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    // respSEI.setRespuesta(transferenciaView);
    // respSEI.setRespuestaVacia(false);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Test
    // public void confirmacionDestinatario() {
    //
    // Respuesta<TransferenciaView> respManager = new
    // Respuesta<TransferenciaView>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    // respManager.setRespuestaVacia(true);
    //
    // Mockito.when(transferenciaManager.confirmarDestinatario(Matchers.any(TransferenciaView.class)))
    // .thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/confirmacionDestinatario");
    //
    // Respuesta<TransferenciaView> respSEI = new
    // Respuesta<TransferenciaView>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    // respSEI.setRespuesta(transferenciaView);
    // respSEI.setRespuestaVacia(false);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }
    //
    // /**
    // * Ejecutar transferencia.
    // */
    // @SuppressWarnings("unchecked")
    // @Test
    // public void ejecutarTransferencia() {
    //
    // Respuesta<TransferenciaView> respManager = new
    // Respuesta<TransferenciaView>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    // respManager.setRespuestaVacia(true);
    //
    // Mockito.when(transferenciaManager.ejecutarTransferencia(Matchers.any(TransferenciaView.class)))
    // .thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/ejecutarTransferencia");
    //
    // Respuesta<TransferenciaView> respSEI = new
    // Respuesta<TransferenciaView>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    // respSEI.setRespuesta(transferenciaView);
    // respSEI.setRespuestaVacia(false);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }
    //
    // /**
    // * Generar comprobante transferencia.
    // */
    // @SuppressWarnings("unchecked")
    // @Test
    // public void generarComprobanteTransferencia() {
    //
    // Respuesta<ReporteView> respManager = new Respuesta<ReporteView>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    // respManager.setRespuestaVacia(true);
    //
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    //
    // Mockito.when(transferenciaManager.generarComprobanteTransferencia(Matchers.any(TransferenciaView.class)))
    // .thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/comprobanteTransferencia");
    //
    // Respuesta<ReporteView> respSEI = new Respuesta<ReporteView>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    // ReporteView reporteView = new ReporteView();
    //
    // // se crea el archivo PDF
    // // TODO: MOCK PDF
    // // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    // // JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    // //
    // // byte[] byteArray = outStream.toByteArray();
    // // reporte.setBytes(byteArray);
    // // reporte.setNombre("Comprobante_Transferencia_" +
    // // transferencia.getNumeroComprobante() + PDF_EXTENSION);
    // // reporte.setTipoArchivo(TipoArchivoEnum.PDF);
    //
    // respSEI.setRespuesta(reporteView);
    // respSEI.setRespuestaVacia(false);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }
    //
    // /**
    // * Checks if is cliente habilitado.
    // *
    // * @throws ManagerException
    // * the manager exception
    // */
    // @SuppressWarnings("unchecked")
    // @Test
    // public void isClienteHabilitado() {
    //
    // Respuesta<Boolean> respManager = new Respuesta<Boolean>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    //
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    //
    // Mockito.when(transferenciaManager.isClienteHabilitado()).thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/isClienteHabilitado");
    //
    // Respuesta<Boolean> respSEI = new Respuesta<Boolean>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }
    //
    // /**
    // * Obtener tipos de cuenta.
    // */
    // @SuppressWarnings("unchecked")
    // @Test
    // public void obtenerTiposDeCuenta() {
    //
    // Respuesta<List<TipoDeCuentaView>> respManager = new
    // Respuesta<List<TipoDeCuentaView>>();
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    //
    // TransferenciaView transferenciaView = new TransferenciaView();
    // transferenciaView.setBanco("ICBC");
    // transferenciaView.setCbu("23303384569");
    // transferenciaView.setConcepto("CONCEPTO");
    // transferenciaView.setCoordenada1("COORDENADA1");
    // transferenciaView.setDescripcion("DESCRIPCION");
    // transferenciaView.setEmail("email@email.com");
    //
    // Mockito.when(transferenciaManager.obtenerTiposDeCuenta()).thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/transferencias/obtenerTiposDeCuenta");
    //
    // Respuesta<Boolean> respSEI = new Respuesta<Boolean>();
    // respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
    //
    // Respuesta<String> retorno = client.post(transferenciaView,
    // Respuesta.class);
    // Assert.assertNotNull(retorno);
    // }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("transferenciasSEI");
    }
}
