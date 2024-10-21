/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Interface PagoPrestamoBO.
 */
public interface PagoPrestamoBO extends BusinessObject {

	/**
	 * Obtener mensaje pago ok.
	 *
	 * @param prestamo
	 *            the prestamo
	 * @param comprobante
	 *            the comprobante
	 * @return the string
	 */
	String obtenerMensajePagoOk(Prestamo prestamo, ComprobantePrestamo comprobante);

	/**
	 * Pagar.
	 *
	 * @param pagoPrestamo
	 *            the pago prestamo
	 * @param cantidadCuentasDebito
	 *            the cantidad cuentas debito
	 * @return the respuesta
	 */
	Respuesta<ComprobantePrestamo> pagar(PagoPrestamo pagoPrestamo, int cantidadCuentasDebito);

}
