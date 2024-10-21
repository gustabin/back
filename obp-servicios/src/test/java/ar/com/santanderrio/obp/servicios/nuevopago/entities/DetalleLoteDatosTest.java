package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * The Class DetalleLoteDatosTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetalleLoteDatosTest {

    /** The detalle lote datos. */
    private DetalleLoteDatos detalleLoteDatos = new DetalleLoteDatos();

    /**
     * Setter getter test.
     */
    @Test
    public void setterGetterTest() {
        List<DetalleRecepcionDatos> listaDetalle = new ArrayList<DetalleRecepcionDatos>();
        DetalleRecepcionDatos detalleRecepcionDatos = new DetalleRecepcionDatos();

        detalleRecepcionDatos.setCantidadError("9001");
        listaDetalle.add(detalleRecepcionDatos);

        detalleLoteDatos.setCeros("00000001");
        detalleLoteDatos.setCantidadOcurrencias("2");
        detalleLoteDatos.setDetalleDatos(listaDetalle);
        detalleLoteDatos.setIdSistema("24");
        detalleLoteDatos.setCantDescStatusResultado("123");
        detalleLoteDatos.setDescripcionesStatusResultado("Resultado de getter");

        Assert.assertEquals("00000001", detalleLoteDatos.getCeros());
        Assert.assertEquals("2", detalleLoteDatos.getCantidadOcurrencias());
        Assert.assertEquals("9001", detalleLoteDatos.getDetalleDatos().get(0).getCantidadError());
        Assert.assertEquals("24", detalleLoteDatos.getIdSistema());
        Assert.assertEquals("123", detalleLoteDatos.getCantDescStatusResultado());
        Assert.assertEquals("Resultado de getter", detalleLoteDatos.getDescripcionesStatusResultado());
    }
}
