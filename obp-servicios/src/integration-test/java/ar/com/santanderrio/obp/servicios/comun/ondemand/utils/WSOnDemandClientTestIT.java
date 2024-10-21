package ar.com.santanderrio.obp.servicios.comun.ondemand.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandConstants;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODRequest;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODResponse;

/**
 * The Class WSOnDemandClientTest.
 *
 * @author sergio.e.goldentair
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class WSOnDemandClientTestIT {

    /** The w S on demand client. */
    @InjectMocks
    private WSOnDemandClient wSOnDemandClient = new WSOnDemandClientImpl();

    /**
     * Consultar resumenes anteriores.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws WSODException
     *             the WSOD exception
     */
    @Test
    public void consultarResumenesAnteriores() throws IllegalAccessException, WSODException {

        WSODRequest req = new WSODRequest();
        req.setNombre(OnDemandConstants.SERVICIO_CNS_RESUMEN_PUNTUAL);
        req.setVersion(OnDemandConstants.SERVICIO_VERSION);
        req.setCanal(OnDemandConstants.SERVICIO_CANAL);
        req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
        req.setUsuario("odtest");
        req.setClave("prueba2006");
        req.setFormatoRespuesta("PDF");

        req.setUsuarioConsulta("01576531");
        req.addFiltroBusqueda("sucursal", "0000");

        req.addFiltroBusqueda("paquete", OnDemandConstants.VARIABLE_PAQUETE);
        req.addFiltroBusqueda("tpo-cta", "02");
        req.addFiltroBusqueda("cuenta", "0000000000638801");
        req.addFiltroBusqueda("tpo-cta-altair", "");
        req.addFiltroBusqueda("persona", OnDemandConstants.VARIABLE_PERSONA);
        req.addFiltroBusqueda("fecha", "30/04/15");
        req.addFiltroBusqueda("folder", "CONS-INFINITYODWEK");

        req.setGrabarLog(Boolean.TRUE);

        // WSODResponse response = wSOnDemandClient.send(req,
        // "http://desawas.ar.bsch:9156/OnDemandWS/SOAPService");
        WSODResponse response = wSOnDemandClient.send(req, "http://wsod.ar.bsch:10500/wsod/SOAPService");

        try {
            IOUtils.copy(response.getAttach(), new FileOutputStream("c:\\tmp\\resumenes.pdf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(response.getAttach());
    }

}
