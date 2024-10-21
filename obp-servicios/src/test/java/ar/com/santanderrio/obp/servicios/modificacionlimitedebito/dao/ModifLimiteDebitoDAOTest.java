package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.blanqueopin.dao.BlanqueoPinDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.impl.ModifLimiteDebitoDAOImpl;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ConsultaDatosTarjetaDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ModificarLimiteDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ModifLimiteDebitoDAOTest.CMBTARBANEConfiguration.class })
@TestPropertySource(properties = { "SERVICIO.PREFIJO.CNSTARBANE = CNSTARBANE", "SERVICIO.VERSION.CNSTARBANE = 100", "SERVICIO.PREFIJO.CMBTARBANE = CMBTARBANE",
		"SERVICIO.VERSION.CMBTARBANE = 110"})
public class ModifLimiteDebitoDAOTest extends IatxBaseDAOTest {

    @Autowired
    @InjectMocks
    private ModifLimiteDebitoDAO serviceDAO;
    
    @Mock
    private BlanqueoPinDAO blanqueoPinDAO;
    
    @Configuration
    public static class CMBTARBANEConfiguration {

        @Bean
        public ModifLimiteDebitoDAO serviceDAO() {
            return new ModifLimiteDebitoDAOImpl();
        }
        
        @Bean
        public BlanqueoPinDAO blanqueoPinDAO() {
            return Mockito.mock(BlanqueoPinDAO.class);
        }
    }
    
    @Test
    public void getClaseTarjetaBanelcoTest() throws DAOException, IatxException{
        ConsultaDatosTarjetaDebitoEntity res;
        String servicio = "CNSTARBANE";
        String version = "100";
        String response = "200000000000P04HTML0009900010302FLND81  ********00493216000000032017080814400300000000IBF007E3000000000000CNSTARBANE1000000            02513381    00        00 014311311201708081439530000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0040800000000õ0000õEõ09õ1õACTEõ0000õ000003523294õ01õ1999-02-09õ          õ2017-07-26õ202007õ2017-07-25õ1999-02-09õ104õ07õ0001õ03õ                   õANSELMI SUAREZ ULA  DANIA MILAõC õ00009991532õ654564564 65456 3 C           õ20 DE JUNIO    õNõ005õACAHõ0000õ00õ000007328123õ01õ9õ õACTEõ0000õ00õ000003523294õ01õ3õ õACAHõ0000õ00õ000003523294õ01õ1õ õACCDõ0000õ00õ000003523294õ01õ1õ õACADõ0000õ00õ000003523294õ01õ1õ õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
        .thenReturn(response);
        
    	res = serviceDAO.getClaseTarjetaBanelco("158", "4517660096225932", obtenerCliente());
    	Assert.assertNotNull(res);
    	Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    
    
//    TODO: Arreglar TEST
//    @Test
//    public void cargarLimitesDebito() throws DAOException{
//    	
//    	  File inputFile = new File(this.getClass()
//                  .getResource("/config/obp-config/mock/files/" + ExternalPropertyType.FILE_CLASES_BANELCO.getName()).getFile());
//          final int sinDatosEncontrados = 0;
//
//          Mockito.when(filePath.getFilePath()).thenReturn(
//                  inputFile.getAbsolutePath().replace(ExternalPropertyType.FILE_CLASES_BANELCO.getName(), ""));
//
//          // then
//          List<LimiteDebito> lineasEnArchivo = serviceDAO.cargarLimitesDebito();
//
//          // expect
//          Assert.assertTrue(
//                  "No se encontraron datos para el archivo: " + ExternalPropertyType.FILE_CLASES_BANELCO.getName(),
//                  sinDatosEncontrados < lineasEnArchivo.size());
//    }
    
    @Test
    public void modificarLimitesExtraccion() throws IatxException, DAOException{
    	
    	ModificarLimiteDebitoEntity entity = new ModificarLimiteDebitoEntity();
        entity.setCliente(new Cliente());        
        entity.setClase("08");
        entity.setNumTarjetaBanelco("4517660096225932");
        String servicio = "CMBTARBANE";
        String version = "110";
        String response = "200000000000P04HTML0009900010302FLND81  ********00493216000000032017080814400300000000IBF007E3000000000000CNSTARBANE1000000            02513381    00        00 014311311201708081439530000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0040800000000õ0000õEõ09õ1õACTEõ0000õ000003523294õ01õ1999-02-09õ          õ2017-07-26õ202007õ2017-07-25õ1999-02-09õ104õ07õ0001õ03õ                   õANSELMI SUAREZ ULA  DANIA MILAõC õ00009991532õ654564564 65456 3 C           õ20 DE JUNIO    õNõ005õACAHõ0000õ00õ000007328123õ01õ9õ õACTEõ0000õ00õ000003523294õ01õ3õ õACAHõ0000õ00õ000003523294õ01õ1õ õACCDõ0000õ00õ000003523294õ01õ1õ õACADõ0000õ00õ000003523294õ01õ1õ õ";
		Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
        .thenReturn(response);

        Respuesta<ResultadoTransaccion> res = serviceDAO.modificarLimitesExtraccion(entity);
        Assert.assertNotNull(res);
    	
    }
    
    @Test
    public void comprobanteModifLimitesExtraccion() throws IOException, IllegalAccessException {
    	
        ComprobanteDescargaCambioLimiteView comprobanteView = new ComprobanteDescargaCambioLimiteView();
        comprobanteView.setFechaHora("27/06/2017 15:47");
        comprobanteView.setLegalesSEO("Conserve este ticket como comprobante S.E.");
        comprobanteView.setLimiteCompra("10000");
        comprobanteView.setLimiteExtActual("5000");
        comprobanteView.setLimiteExtNuevo("10000");
        comprobanteView.setNumeroComprobante("15418208");
        comprobanteView.setNumeroTarjetaFormateado("VISA XXXX-8823");
        comprobanteView.setNuevoLimCompra("100");
        
//        FieldUtils.writeField(modifLimiteDebitoDAO, "logoCierre", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
//                true);
//        FieldUtils.writeField(modifLimiteDebitoDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
//                true);
//        FieldUtils.writeField(modifLimiteDebitoDAO, "logoDebito", appContext.getResource("classpath:/report/comprobantes/logoDebito.png"),
//                true);
//        FieldUtils.writeField(modifLimiteDebitoDAO, "fileJasperComprobanteCambioLimiteDebito",
//                appContext.getResource("classpath:/report/modificacionLimiteDebito/ComprobanteCambioLimiteDebito.jasper"), true);
        Reporte reporte = serviceDAO.comprobanteModifLimitesExtraccion(comprobanteView);
        Assert.assertNotNull(reporte.getBytes());
    }
    
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
