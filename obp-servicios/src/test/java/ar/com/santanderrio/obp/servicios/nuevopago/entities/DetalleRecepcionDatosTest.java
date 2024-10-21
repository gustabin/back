package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * The Class DetalleRecepcionDatosTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetalleRecepcionDatosTest {

    /** The detalle recepcion datos. */
    private DetalleRecepcionDatos detalleRecepcionDatos = new DetalleRecepcionDatos();

    /**
     * Setter getter test.
     */
    @Test
    public void setterGetterTest() {
        detalleRecepcionDatos.setCodigoErrorPago("23");
        detalleRecepcionDatos.setComprobantePorServicio("FDG");
        detalleRecepcionDatos.setDescripcionError("Descripcion de prueba");
        detalleRecepcionDatos.setImporteSorteo("67,20");
        detalleRecepcionDatos.setLeyendaReciboAdhesion("Leyenda recibo de prueba");
        detalleRecepcionDatos.setLeyendaReciboPago("Leyenda pago de prueba");
        detalleRecepcionDatos.setNroErrorPago("14567");
        detalleRecepcionDatos.setNumeroControl("0098");
        detalleRecepcionDatos.setResultadoSorteo("resultado de prueba");
        detalleRecepcionDatos.setSistemaError("Error sistema");
        detalleRecepcionDatos.setTipoImporteSorteo("ARS");

        Assert.assertEquals("23", detalleRecepcionDatos.getCodigoErrorPago());
        Assert.assertEquals("FDG", detalleRecepcionDatos.getComprobantePorServicio());
        Assert.assertEquals("Descripcion de prueba", detalleRecepcionDatos.getDescripcionError());
        Assert.assertEquals("67,20", detalleRecepcionDatos.getImporteSorteo());
        Assert.assertEquals("Leyenda recibo de prueba", detalleRecepcionDatos.getLeyendaReciboAdhesion());
        Assert.assertEquals("Leyenda pago de prueba", detalleRecepcionDatos.getLeyendaReciboPago());
        Assert.assertEquals("14567", detalleRecepcionDatos.getNroErrorPago());
        Assert.assertEquals("0098", detalleRecepcionDatos.getNumeroControl());
        Assert.assertEquals("resultado de prueba", detalleRecepcionDatos.getResultadoSorteo());
        Assert.assertEquals("Error sistema", detalleRecepcionDatos.getSistemaError());
        Assert.assertEquals("ARS", detalleRecepcionDatos.getTipoImporteSorteo());
    }
}
