/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Interface CompraVentaCasuisticaRespuestaBO.
 *
 * @author sabrina.cis
 */
public interface CompraVentaCasuisticaRespuestaBO {

	/**
	 * Obtener mensaje compra venta.
	 *
	 * @param importe
	 *            the importe
	 * @param esCompra
	 *            the es compra
	 * @return the string
	 */
	String obtenerMensajeCompraVenta(String importe, boolean esCompra);

	/**
	 * Error tipo warning.
	 *
	 * @param error
	 *            the error
	 * @return true, if successful
	 */
	boolean errorTipoWarning(String error);

	/**
	 * Crear respuesta warning.
	 *
	 * @param tipoError
	 *            the tipo error
	 * @param cuenta
	 *            the cuenta
	 * @param respuestaComprobanteCompraVentaDTO
	 *            the respuesta comprobante compra venta DTO
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the respuesta
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	Respuesta<ComprobanteCompraVentaView> crearRespuestaWarning(String tipoError, Cuenta cuenta,
			Respuesta<ComprobanteCompraVentaDTO> respuestaComprobanteCompraVentaDTO, String tipoOperacion)
			throws CompraVentaDolaresException;

	/**
	 * Crear respuesta erronea con datos exception operacion feedback.
	 *
	 * @param respuestaComprobanteCompraVentaDTO
	 *            the respuesta comprobante compra venta DTO
	 * @param cuenta
	 *            the cuenta
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the respuesta
	 */
	Respuesta<ComprobanteCompraVentaView> crearRespuestaErroneaConDatosExceptionOperacionFeedback(
			Respuesta<ComprobanteCompraVentaDTO> respuestaComprobanteCompraVentaDTO, Cuenta cuenta,
			String tipoOperacion);

	/**
	 * Crear respuesta erronea con datos exception.
	 *
	 * @param respuestaCompraVentaDTO
	 *            the respuesta compra venta DTO
	 * @param nroCuentaOrigen
	 *            the nro cuenta origen
	 * @param tipoOperacionError
	 *            the tipo operacion error
	 * @return the respuesta
	 */
	Respuesta<SimulacionCompraVentaDolarView> crearRespuestaErroneaConDatosException(
			Respuesta<SimulacionCompraVentaDTO> respuestaCompraVentaDTO, String nroCuentaOrigen,
			String tipoOperacionError);

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param error
	 *            the error
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(ErrorCompraVentaEnum error);

	/**
	 * Crear respuesta erronea cuenta origen fuera horario.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaErroneaCuentaOrigenFueraHorario();

	/**
	 * Crear respuesta erronea cuenta inhabilitada 2.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaErroneaCuentaInhabilitada2();

	/**
	 * Crear respuesta erronea cuenta inhabilitada 1.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaErroneaCuentaInhabilitada1();

	/**
	 * Crear respuesta erronea documento invalido.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaErroneaDocumentoInvalido();

	/**
	 * Crear respuesta erronea informacion no disponible.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaErroneaInformacionNoDisponible();

	/**
	 * Es respuesta OK.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	Boolean esRespuestaOK(EstadoRespuesta estadoRespuesta);

	/**
	 * Crear respuesta ok.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaOk(Class<T> respuestaClass, T data);

	/**
	 * Crear respuesta error cotizacion.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaErrorCotizacion();
	
	
	/**
	 * Crear respuesta error cotizacion inactivo.
	 *
	 * @param <T> the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaErrorCotizacionInactivo();

	/**
	 * Crear respuesta warning.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @param items
	 *            the items
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaWarning(Class<T> respuestaClass, T data, List<ItemMensajeRespuesta> items);

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param itemsMensajeRespuesta
	 *            the items mensaje respuesta
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, List<ItemMensajeRespuesta> itemsMensajeRespuesta);

	/**
	 * Crear respuesta warning fuera horario.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param listaCuentasView
	 *            the lista cuentas view
	 * @return the respuesta
	 */
	Respuesta<CompraVentaDolarView> crearRespuestaWarningFueraHorario(Respuesta<CompraVentaInicioDTO> respuesta,
			List<CuentasAdhesionDebitoView> listaCuentasView);

	/**
	 * Es respuesta error fuera de horario.
	 *
	 * @param respuestaCompraVentaDolarView
	 *            the respuesta compra venta dolar view
	 * @return the boolean
	 */
	Boolean esRespuestaErrorFueraDeHorario(Respuesta<CompraVentaInicioDTO> respuestaCompraVentaDolarView);

	/**
	 * Es respuesta error cotizacion.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	Boolean esRespuestaErrorCotizacion(Respuesta<CompraVentaInicioDTO> dto);

	/**
	 * Crear respuesta erronea con datos exception operacion feedback.
	 *
	 * @param respuestaComprobanteCompraVentaDTO
	 *            the respuesta comprobante compra venta DTO
	 * @param cuenta
	 *            the cuenta
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param monto
	 *            the monto
	 * @return the respuesta
	 */
	Respuesta<ComprobanteCompraVentaView> crearRespuestaErroneaConDatosExceptionOperacionFeedback(
			Respuesta<ComprobanteCompraVentaDTO> respuestaComprobanteCompraVentaDTO, Cuenta cuenta,
			String tipoOperacion, String monto);
	
	

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigo
	 *            the codigo
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(String tag, TipoError tipoError, String codigo);

	/**
	 * Crear respuesta error servicio.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaErrorServicio();

	<T> Respuesta<T> crearRespuestaErrorPanico(String mensaje);
	
	<T> Respuesta<T> crearRespuestaErrorOnlineBcra(String mensaje);
	
	Respuesta<CompraVentaDolarView> crearRespuestaErroneaConDatosBotonPanico(Respuesta<CompraVentaInicioDTO> respuesta,
			List<CuentasAdhesionDebitoView> listaCuentasView, List<CuentasAdhesionDebitoView> listaCuentasViewDestino);
	
	/**
	 * Crear respuesta de error con mensaje indicado
	 * @param <T>
	 * @param mensaje
	 * @return
	 */
	<T> Respuesta<T> crearRespuestaErrorMensajePersonalizado(String mensaje);
}
