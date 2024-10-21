package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.impl.AumentoLimiteTransferenciaDAOImpl;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;

@RunWith(MockitoJUnitRunner.class)
public class AumentoLimiteTransferenciaDAOTest {
    
    @InjectMocks
    private AumentoLimiteTransferenciaDAO aumentoLimiteTransferenciaDAO = new AumentoLimiteTransferenciaDAOImpl();
    
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
    
    @Test
    public void generarComprobanteAumentoLimiteTransferenciaTest() throws IOException, IllegalAccessException {
        DatosComprobanteAumentoLimiteTransferencia datosComprobante = new DatosComprobanteAumentoLimiteTransferencia();
        datosComprobante.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        datosComprobante.setDestinatario("Milando Constancio Percy");
        datosComprobante.setFechaEjecucion("12/12/2017");
        datosComprobante.setFechaOperacion("10/05/2017 - 13:15");
        datosComprobante.setImporte("$ 350.000,01");
        datosComprobante.setNroCuentaDestino("1231231/12");
        datosComprobante.setNroCuentaOrigen("12312341/28");
        datosComprobante.setNroComprobante("12312311");
        datosComprobante.setTipoCuentaDestino("Cuenta unica");
        datosComprobante.setTipoCuentaOrigen("Cuenta unica");
        FieldUtils.writeField(aumentoLimiteTransferenciaDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                true);
        FieldUtils.writeField(aumentoLimiteTransferenciaDAO, "fileJasperSolicitudAumentoLimiteTransferencia",
                appContext.getResource("classpath:/report/aumentoLimiteTransferencia/ComprobanteSolicitudAumentoLimiteTransferencia.jasper"), true);
        
        FieldUtils.writeField(aumentoLimiteTransferenciaDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);

        Reporte reporte = aumentoLimiteTransferenciaDAO.generarComprobanteAumentoLimiteTransferencia(datosComprobante);
        FileUtils.writeByteArrayToFile(new File("C:/tools/comprobanteTest.pdf"),reporte.getBytes());
        Assert.assertNotNull(reporte);
    }

}
