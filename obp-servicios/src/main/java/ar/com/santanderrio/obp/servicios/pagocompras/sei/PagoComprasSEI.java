/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasView;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoComprasMultiple;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Interface PagoComprasSEI.
 */
@Path("/pagoCompras")
public interface PagoComprasSEI {

    /**
     * Obtener deudas.
     *
     * @param medioPagoView
     *            the medio pago view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerDeudas")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<MedioPagoSelectionView> obtenerDeudas(MedioPagoView medioPagoView);

    /**
     * Obtener configuracion.
     *
     * @param medioPagoView
     *            the medio pago view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerConfiguracion")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<MedioPagoSelectionView> obtenerConfiguracion(MedioPagoView medioPagoView);

    /**
     * Continuar pago compras.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the respuesta
     */
    @POST
    @Path("/continuarPagoCompras")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<Boolean> continuarPagoCompras(NuevoPago pagoCompras);

    /**
     * Ejecutar pago de compras.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the respuesta
     */
    @POST
    @Path("/ejecutarPagoCompras")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<NuevoPago> ejecutarPagoCompras(NuevoPago pagoCompras);

    /**
     * Graba la estadistica correspondiente al comprobante de pago de compras.
     *
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaComprobantePagoCompras")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<Boolean> grabarEstadisticaComprobantePagoCompras();

    /**
     * Descargar comprobante PDF.
     *
     * @return the respuesta
     */
    @POST
    @Path("/descargarComprobantePDF")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ReporteView> descargarComprobantePDF();
    
    
    @POST
    @Path("/grabarEstadisticaPagoComprasMultiple")
    void grabarEstadisticaPagoComprasMultiple();
    
    @POST
    @Path("/continuarPagoComprasMultiple")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Boolean> continuarPagoComprasMultiple(PagoComprasMultiple pagoCompras);

    @POST
    @Path("/ejecutarPagoComprasMultiple")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<PagoComprasMultiple> ejecutarPagoComprasMultiple(PagoComprasMultiple pagoCompras);
    
    @POST
    @Path("/descargarComprobanteSeleccionadoPDF")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ReporteView> descargarComprobanteSeleccionadoPDF(DeudaPagoComprasView deuda);

    @POST
    @Path("/grabarEstadisticaComprobantePagoComprasMultiple")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<Boolean> grabarEstadisticaComprobantePagoComprasMultiple();
}
