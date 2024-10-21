/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.mock;

import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;

/**
 * The Class PagoPMCMock.
 *
 * @author florencia.n.martinez
 */
public class PagoPMCMock {

    /**
     * Completar info.
     *
     * @param respuestaOK
     *            the respuesta OK
     * @param nombreEmpresa
     *            the nombre empresa
     * @return the pago PMC
     */
    public static PagoPMC completarInfo(Boolean respuestaOK, String nombreEmpresa) {
        PagoPMC pmc = new PagoPMC();
        pmc.setRespuestaOK(respuestaOK);
        pmc.setNumeroControl("123243455");
        pmc.setComprobantePorServicio("45345");
        pmc.setEmpresaNombreFantasia(nombreEmpresa);
        return pmc;
    }

}
