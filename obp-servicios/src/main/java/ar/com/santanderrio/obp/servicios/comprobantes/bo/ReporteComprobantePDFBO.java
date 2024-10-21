/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ComprobanteOrdenVenta;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Interface ReporteComprobantePDFBO. Cambio
 */
public interface ReporteComprobantePDFBO {

	/**
	 * Obtiene el pdf del comprobante.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerComprobantePDF(DetalleComprobanteView detalleView);

	/**
	 * Obtener comprobante PDF.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerComprobantePDF(TransferenciaView transferenciaView);

	/**
	 * Obtener comprobante PDF.
	 *
	 * @param pagoTarjetaView
	 *            the pago tarjeta view
	 * @param tipoOperacionComprobante
	 *            the tipo operacion comprobante
	 * @param cuentas
	 *            the cuentas
	 * @param comprobantePagoTarjeta
	 *            the comprobante pago tarjeta
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerComprobantePDF(PagoTarjetaCreditoView pagoTarjetaView,
			TipoOperacionComprobanteEnum tipoOperacionComprobante, List<Cuenta> cuentas,
			ComprobantePagoTarjeta comprobantePagoTarjeta);

	/**
	 * Obtener comprobante PDF.
	 *
	 * @param compraVentaView
	 *            the compra venta view
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerComprobantePDF(ComprobanteCompraVentaView compraVentaView);

	/**
	 * Obtener comprobante PDF.
	 *
	 * @param recarga
	 *            the recarga
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerComprobantePDF(ConfirmacionRecargaView recarga);

	/**
	 * Obtener comprobante PDF.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param fechaVencimiento
	 *            the fecha vencimiento
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerComprobantePDF(NuevoPago nuevoPagoView, String fechaVencimiento);

	/**
	 * Obtener comprobante PDF.
	 *
	 * @param pagoMultipleView
	 *            the pago multiple view
	 * @param pagoMultipleView2
	 *            the pago multiple view 2
	 * @param fechaHoraPago
	 *            the fecha hora pago
	 * @param cuitVep
	 *            the cuit vep
	 * @param elementoAdicional
	 *            the elemento adicional
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerComprobantePDF(PagoMultipleView pagoMultipleView, PagoMultipleView pagoMultipleView2,
			String fechaHoraPago, String cuitVep,String elementoAdicional);
	
	/**
	 * Obtener comprobante PDF.
	 *
	 * @param comprobanteOrdenVenta
	 *            the comprobante orden venta
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerComprobantePDF(ComprobanteOrdenVenta comprobanteOrdenVenta);
	
	Respuesta<Reporte> obtenerArchivoPDF(String ubicacion, String nombre);
	
}