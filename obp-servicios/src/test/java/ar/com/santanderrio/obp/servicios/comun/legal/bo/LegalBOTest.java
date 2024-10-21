/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.impl.LegalBOImpl;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class LegalBOTest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(MockitoJUnitRunner.class)
public class LegalBOTest {
    /** The legal BO. */
    @InjectMocks
    private LegalBOImpl legalBO = new LegalBOImpl();

    /** The legal DAO. */
    @Mock
    private LegalDAO legalDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** generador de respuestas. */
    @Spy
    private RespuestaFactory respuestaFactory;

    /**
     * Obtener texto legal test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTextoLegalTest() throws DAOException {
        String legalDetallePrestamo = new String("Legal Detalle Prestamo");
        String legalDetalleDefault = new String("Legal Detalle Importes");
        String legalDetallePrestamoPersonal = new String("Legal Detalle Prestamo Personal");
        String legalDetalleTasas = new String("Legal Detalle Tasas");
        String legalComprobante = new String("Legal Comprobante");

        when(legalDAO.obtenerLegal(Matchers.anyString())).thenReturn(legalDetallePrestamo)
                .thenReturn(legalDetalleDefault).thenReturn(legalDetallePrestamoPersonal).thenReturn(legalDetalleTasas)
                .thenReturn(legalComprobante);

        String obtenerLegalDetallePrestamo = legalBO.obtenerTextoLegal(CodigoMensajeConstantes.LEGAL_DETALLE_PRESTAMO);
        String obtenerLegalDetalleDefault = legalBO.obtenerTextoLegal(CodigoMensajeConstantes.LEGAL_DETALLE_DEFAULT);
        String obtenerLegalDetallePrestamoPersonal = legalBO
                .obtenerTextoLegal(CodigoMensajeConstantes.LEGAL_DETALLE_PRESTAMO_PERSONAL);
        String obtenerLegalDetalleTasas = legalBO.obtenerTextoLegal(CodigoMensajeConstantes.LEGAL_DETALLE_TASAS);
        String obtenerLegalComprobante = legalBO.obtenerTextoLegal(CodigoMensajeConstantes.LEGAL_COMPROBANTE);

        assertEquals(obtenerLegalDetallePrestamo, "Legal Detalle Prestamo");
        assertEquals(obtenerLegalDetalleDefault, "Legal Detalle Importes");
        assertEquals(obtenerLegalDetallePrestamoPersonal, "Legal Detalle Prestamo Personal");
        assertEquals(obtenerLegalDetalleTasas, "Legal Detalle Tasas");
        assertEquals(obtenerLegalComprobante, "Legal Comprobante");
    }

    /**
     * Limpiar legales.
     */
    @Test
    public void limpiarLegales() {
        Mockito.doNothing().when(legalDAO).limpiarLegales();
        Respuesta<Boolean> respuesta = legalBO.limpiarLegales();
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Buscar legal.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void buscarLegal() throws DAOException {
        String salida = "salida";
        Mockito.when(legalDAO.obtenerLegal(Matchers.anyString())).thenReturn(salida);
        Respuesta<String> resultado = legalBO.buscarLegal("30001");
        assertEquals(resultado.getRespuesta(), salida);
    }
}
