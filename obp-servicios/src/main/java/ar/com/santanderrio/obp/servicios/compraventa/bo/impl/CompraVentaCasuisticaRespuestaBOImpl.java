/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaCasuisticaRespuestaBO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CompraVentaCasuisticaRespuestaBOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class CompraVentaCasuisticaRespuestaBOImpl implements CompraVentaCasuisticaRespuestaBO {

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The Constant TIPO_OPERACION. */
	public static final String TIPO_OPERACION = "V";

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/**
	 * Es respuesta OK.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	@Override
	public Boolean esRespuestaOK(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.OK);
	}

	/**
	 * Crear respuesta erronea informacion no disponible.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaErroneaInformacionNoDisponible() {
		return crearRespuestaError(ErrorCompraVentaEnum.SIN_ACCESO_A_LA_INFORMACION);
	}

	/**
	 * Crear respuesta erronea documento invalido.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaErroneaDocumentoInvalido() {
		return crearRespuestaError(ErrorCompraVentaEnum.NO_SE_ENCONTRO_DOC_VALIDO);
	}

	/**
	 * Crear respuesta error servicio.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaErrorServicio() {
		return crearRespuestaError(ErrorCompraVentaEnum.CUENTA_INHABILITADA_1);
	}

	/**
	 * Crear respuesta erronea cuenta inhabilitada 1.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaErroneaCuentaInhabilitada1() {
		return crearRespuestaError(ErrorCompraVentaEnum.CUENTA_INHABILITADA_1);
	}

	/**
	 * Respuesta ERROR.
	 * TipoError.CODIGO_ERROR_SIN_CUENTA_DOLARES_PESOS_HABILITADA (1083)
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaErroneaCuentaInhabilitada2() {
		return crearRespuestaError(ErrorCompraVentaEnum.CUENTA_INHABILITADA_2);
	}

	/**
	 * Crear respuesta erronea cuenta origen fuera horario.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaErroneaCuentaOrigenFueraHorario() {
		Respuesta<T> respuesta = crearRespuestaError(ErrorCompraVentaEnum.CUENTA_ORIGEN_FUERA_HORARIO);
		String mensajeParseado = StringUtils.replace(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "{0}",
				"compra y venta");
		respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeParseado);
		return respuesta;

	}

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param error
	 *            the error
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaError(ErrorCompraVentaEnum error) {
		return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(), error.getCodigoMensaje());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.compraventa.bo.
	 * CompraVentaCasuisticaRespuestaBO#crearRespuestaOk(java.lang.Class,
	 * java.lang.Object)
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaOk(Class<T> respuestaClass, T data) {
		return respuestaFactory.crearRespuestaOk(respuestaClass, data);
	}

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
	@Override
	public <T> Respuesta<T> crearRespuestaWarning(Class<T> respuestaClass, T data, List<ItemMensajeRespuesta> items) {
		return respuestaFactory.crearRespuestaWarning(respuestaClass, data, items);
	}

	/**
	 * Crear respuesta error cotizacion.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaErrorCotizacion() {
		return crearRespuestaError(ErrorCompraVentaEnum.COTIZACION_ERROR_SERVICIO);
	}
	
	@Override
	public <T> Respuesta<T> crearRespuestaErrorPanico(String mensaje) {
		return respuestaFactory.crearRespuestaErrorPersonalizado(null, mensaje, TipoError.ERROR_BOTON_PANICO.getDescripcion());
	}
	
	@Override
	public <T> Respuesta<T> crearRespuestaErrorOnlineBcra(String mensaje) {
		return respuestaFactory.crearRespuestaErrorPersonalizado(null, mensaje, TipoError.CODIGO_ONLINE_BCRA.getDescripcion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.compraventa.bo.
	 * CompraVentaCasuisticaRespuestaBO#crearRespuestaErrorCotizacionInactivo()
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaErrorCotizacionInactivo() {
		return crearRespuestaError(ErrorCompraVentaEnum.COTIZACION_ERROR_SERVICIO);
	}

	/**
	 * Crear respuesta error con items de mensajes creados con anticipacion.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param items
	 *            the items
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, List<ItemMensajeRespuesta> items) {
		return respuestaFactory.crearRespuestaError(respuestaClass, items);
	}

	/**
	 * Crear respuesta error con items de mensajes creados con anticipacion.
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
	@Override
	public <T> Respuesta<T> crearRespuestaError(String tag, TipoError tipoError, String codigo) {
		return respuestaFactory.crearRespuestaError(tag, tipoError, codigo);
	}

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
	@Override
	public Respuesta<SimulacionCompraVentaDolarView> crearRespuestaErroneaConDatosException(
			Respuesta<SimulacionCompraVentaDTO> respuestaCompraVentaDTO, String nroCuentaOrigen,
			String tipoOperacionError) {
		if (respuestaCompraVentaDTO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			Respuesta<SimulacionCompraVentaDolarView> res = new Respuesta<SimulacionCompraVentaDolarView>();
			res.setEstadoRespuesta(EstadoRespuesta.WARNING);
			res.setRespuesta(new SimulacionCompraVentaDolarView());
			res.getRespuesta().setNumeroCuentaError(nroCuentaOrigen);
			if (tipoOperacionError.equals(CompraVentaStringUtil.OPERACION_VENTA)) {
				res.getRespuesta().setEsCompra(false);
				res.getRespuesta().setEsVenta(true);
			}
			res.setItemMensajeRespuesta(respuestaCompraVentaDTO.getItemsMensajeRespuesta());
			res.setRespuestaVacia(false);
			return res;
		}
		return respuestaFactory.crearRespuestaError(SimulacionCompraVentaDolarView.class,
				respuestaCompraVentaDTO.getItemsMensajeRespuesta());
	}
	

	/**
	 * Crear respuesta erronea con datos exception operacion feedback.
	 *
	 * @param rtaDto
	 *            the respuesta comprobante compra venta DTO
	 * @param cuenta
	 *            the cuenta
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobanteCompraVentaView> crearRespuestaErroneaConDatosExceptionOperacionFeedback(
			Respuesta<ComprobanteCompraVentaDTO> rtaDto, Cuenta cuenta, String tipoOperacion) {
		if (errorTipoWarning(rtaDto.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return crearRespuestaWarning(rtaDto.getItemsMensajeRespuesta().get(0).getTipoError(), cuenta, rtaDto,
					tipoOperacion);
		}
		return respuestaFactory.crearRespuestaError(ComprobanteCompraVentaView.class,
				rtaDto.getItemsMensajeRespuesta());
	}

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
	@Override
	public Respuesta<ComprobanteCompraVentaView> crearRespuestaErroneaConDatosExceptionOperacionFeedback(
			Respuesta<ComprobanteCompraVentaDTO> respuestaComprobanteCompraVentaDTO, Cuenta cuenta,
			String tipoOperacion, String monto) {
		String tipoError = respuestaComprobanteCompraVentaDTO.getItemsMensajeRespuesta().get(0).getTipoError();
		if (errorTipoWarning(tipoError)) {
			return crearRespuestaWarning(
					tipoError, cuenta,
					respuestaComprobanteCompraVentaDTO, tipoOperacion);
		}
		Respuesta<ComprobanteCompraVentaView> res = respuestaFactory.crearRespuestaError(
				ComprobanteCompraVentaView.class, respuestaComprobanteCompraVentaDTO.getItemsMensajeRespuesta());
		String mensajeAParsear = "";
		
		if (TipoError.ERROR_BOTON_PANICO.getDescripcion()
				.equals(tipoError)
		   || TipoError.CODIGO_ONLINE_BCRA.getDescripcion()
				.equals(tipoError)
			|| TipoError.ERROR_OPERACION_INHABILITADA.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_COMPRA_USD_NO_DOCUMENTA_INGRESOS.getDescripcion().equals(tipoError)
			|| TipoError.OPERACION_NO_PERMITIDA.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_EXCEDE_LIMITE_ATESORAMIENTO.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_EXCEDE_LIMITE_TRJ.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_2.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_3.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_SUB_ANSES.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_VENDE_BONOS_USD.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_YA_REGISTRA_OPERACION_MENSUAL.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_INHABILITADO_BCRA.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_FUNCIONARIO_PUBLICO.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_OPERA_USD_CERTIFICACION_POSITIVA.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_INHABILITADO_BCRA_AMEC_43.getDescripcion().equals(tipoError)
			|| TipoError.ERROR_INHABILITADO_BCRA_AMEC_42.getDescripcion().equals(tipoError)
				) {
			mensajeAParsear = respuestaComprobanteCompraVentaDTO.getItemsMensajeRespuesta().get(0).getMensaje();
		}else {
			mensajeAParsear = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_FEEDBACK_COMPRA_VENTA).getMensaje();
		}
		/*String mensajeAParsear = TipoError.ERROR_BOTON_PANICO.getDescripcion()
				.equals(respuestaComprobanteCompraVentaDTO.getItemsMensajeRespuesta().get(0).getTipoError())
						? respuestaComprobanteCompraVentaDTO.getItemsMensajeRespuesta().get(0).getMensaje()
						: mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_FEEDBACK_COMPRA_VENTA)
								.getMensaje();*/
		// String mensajeAParsear = mensajeManager
		// .obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_FEEDBACK_COMPRA_VENTA).getMensaje();
		String tipo = CompraVentaStringUtil.OPERACION_VENTA_DETALLE;
		if (CompraVentaStringUtil.OPERACION_COMPRA.equals(tipoOperacion)) {
			tipo = CompraVentaStringUtil.OPERACION_COMPRA_DETALLE;
		}
		mensajeAParsear = StringUtils.replace(mensajeAParsear, "{0}", tipo + " ");
		mensajeAParsear = StringUtils.replace(mensajeAParsear, "{1}", monto);
		res.getItemsMensajeRespuesta().get(0).setMensaje(mensajeAParsear);
		return res;
	}

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
	 */
	@Override
	public Respuesta<ComprobanteCompraVentaView> crearRespuestaWarning(String tipoError, Cuenta cuenta,
			Respuesta<ComprobanteCompraVentaDTO> respuestaComprobanteCompraVentaDTO, String tipoOperacion) {
		Respuesta<ComprobanteCompraVentaView> res = new Respuesta<ComprobanteCompraVentaView>();
		res.setEstadoRespuesta(EstadoRespuesta.WARNING);
		res.setRespuestaVacia(true);
		if (tipoError.equals(TipoError.ERROR_CAMBIO_COTIZACION_DOLAR.getDescripcion())) {
			ComprobanteCompraVentaView compraVentaView = new ComprobanteCompraVentaView();
			compraVentaView.setNumeroCuentaError(cuenta.getNroCuentaProducto());
			if (tipoOperacion.equals(TIPO_OPERACION)) {
				compraVentaView.setEsCompra(false);
				compraVentaView.setEsVenta(true);
			}
			res.setRespuesta(compraVentaView);
			res.setRespuestaVacia(false);
		}
		res.setItemMensajeRespuesta(respuestaComprobanteCompraVentaDTO.getItemsMensajeRespuesta());
		return res;
	}

	/**
	 * Crear respuesta warning fuera horario.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param listaCuentasView
	 *            the lista cuentas view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CompraVentaDolarView> crearRespuestaWarningFueraHorario(Respuesta<CompraVentaInicioDTO> respuesta,
			List<CuentasAdhesionDebitoView> listaCuentasView) {
		ItemMensajeRespuesta item = respuesta.getItemsMensajeRespuesta().get(0);
		item.setTipoError(ErrorCompraVentaEnum.OPERACION_FUERA_HORARIO.getTipoError().getDescripcion());
		item.setMensaje(mensajeManager
				.obtenerMensajePorCodigo(
						CodigoMensajeConstantes.ERROR_OPERACION_NO_DISPONIBLE_HORARIO_SUCURSAL_VARIAS_SUCURSALES)
				.getMensaje());
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(item);
		if (listaCuentasView != null) {
			CompraVentaDolarView view = new CompraVentaDolarView();
			view.setCuentasOrigen(listaCuentasView);
			return respuestaFactory.crearRespuestaWarning(CompraVentaDolarView.class, view, items);
		} else {
			return respuestaFactory.crearRespuestaError(CompraVentaDolarView.class, items);
		}
	}

	/**
	 * Error tipo warning.
	 *
	 * @param error
	 *            the error
	 * @return true, if successful
	 */
	@Override
	public boolean errorTipoWarning(String error) {
		return error.equals(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion())
				|| error.equals(TipoError.WARNING_SUCURSAL_ORIGEN_FUERA_HORARIO.getDescripcion())
				|| error.equals(TipoError.ERROR_CAMBIO_COTIZACION_DOLAR.getDescripcion());
	}

	/**
	 * Es respuesta error fuera de horario.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	@Override
	public Boolean esRespuestaErrorFueraDeHorario(Respuesta<CompraVentaInicioDTO> dto) {
		return dto.getEstadoRespuesta().equals(EstadoRespuesta.ERROR) && dto.getItemsMensajeRespuesta().get(0)
				.getTipoError().equals(TipoError.ERROR_SUCURSAL_ORIGEN_FUERA_HORARIO.getDescripcion());
	}

	/**
	 * Es respuesta error fuera de horario.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	@Override
	public Boolean esRespuestaErrorCotizacion(Respuesta<CompraVentaInicioDTO> dto) {
		return dto.getItemsMensajeRespuesta().get(0).getTag().equals(CompraVentaStringUtil.COTIZACION_SERVICIO_ERROR);
	}

	/**
	 * Obtener mensaje compra.
	 *
	 * @param importe
	 *            the importe
	 * @param esCompra
	 *            the es compra
	 * @return the string
	 */
	@Override
	public String obtenerMensajeCompraVenta(String importe, boolean esCompra) {
		String mensaje = mensajeManager.obtenerMensajePorCodigo("1387").getMensaje();
		if (esCompra) {
			mensaje = StringUtils.replace(mensaje, "{0}", "compra");
		} else {
			mensaje = StringUtils.replace(mensaje, "{0}", "venta");
		}
		return StringUtils.replace(mensaje, "{1}", importe);
	}


	@Override
	public Respuesta<CompraVentaDolarView> crearRespuestaErroneaConDatosBotonPanico(
			Respuesta<CompraVentaInicioDTO> respuesta, List<CuentasAdhesionDebitoView> listaCuentasView, List<CuentasAdhesionDebitoView> listaCuentasViewDestino) {
			ItemMensajeRespuesta item = respuesta.getItemsMensajeRespuesta().get(0);			
			item.setTipoError(item.getTipoError());
			item.setMensaje(item.getMensaje());
			List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
			items.add(item);
			CompraVentaDolarView view = new CompraVentaDolarView();
			if (listaCuentasView != null && listaCuentasViewDestino != null) {				
				view.setCuentasOrigen(listaCuentasView);
				view.setCuentasDestino(listaCuentasViewDestino);
				//return respuestaFactory.crearRespuestaWarning(CompraVentaDolarView.class, view, items);
			}/* else {
				return respuestaFactory.crearRespuestaError(CompraVentaDolarView.class, items);
			}*/
			return respuestaFactory.crearRespuestaWarning(CompraVentaDolarView.class, view, items);
					
	}

	@Override
	public <T> Respuesta<T> crearRespuestaErrorMensajePersonalizado(String mensaje) {
		return respuestaFactory.crearRespuestaErrorPersonalizado(null, mensaje, TipoError.ERROR_GENERICO.getDescripcion());
	}

}
