package ar.com.santanderrio.obp.servicios.comprobantes.web.manager.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.CoordinadorComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxPagoDeTarjetaCreditoDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.HistorialComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.HistorialComprobantesManager;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteHistorialView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.HistorialComprobantesView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@Component
public class HistorialComprobantesManagerImpl implements HistorialComprobantesManager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The comprobantes BO. */
	@Autowired
	private CoordinadorComprobantesBO coordinadorBO;

	/** The historial comprobantes BO. */
	@Autowired
	private HistorialComprobantesBO historialBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	@Override
	public Respuesta<HistorialComprobantesView> consultaHistorial(String id) {
		Cliente cliente = sesionCliente.getCliente();
		historialBO.limpiarCache(cliente);
		FiltroComprobanteDTO dto = new FiltroComprobanteDTO();
		dto.setCliente(cliente);
		Respuesta<ComprobantesDTO> respuestaDTO = coordinadorBO.obtenerComprobantes(cliente, dto);
		if (EstadoRespuesta.ERROR.equals(respuestaDTO.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
					CodigoMensajeConstantes.ERROR_COMPROBANTES);
		}
		ComprobanteDTO comprobanteSeleccionado = respuestaDTO.getRespuesta().getComprobantes().get(Integer.valueOf(id));
		Respuesta<ComprobantesDTO> respuestaHistorial = historialBO.obtenerHistorial(comprobanteSeleccionado, cliente);
		grabarEstadistica(respuestaHistorial, comprobanteSeleccionado);
		if (EstadoRespuesta.ERROR.equals(respuestaHistorial.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(HistorialComprobantesView.class,
					respuestaHistorial.getItemsMensajeRespuesta());
		} else if (EstadoRespuesta.WARNING.equals(respuestaHistorial.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaWarning(
					armarView(respuestaHistorial.getRespuesta().getComprobantes(), comprobanteSeleccionado),
					respuestaHistorial.getItemsMensajeRespuesta());
		}
		return respuestaFactory.crearRespuestaOk(
				armarView(respuestaHistorial.getRespuesta().getComprobantes(), comprobanteSeleccionado));
	}

	private void grabarEstadistica(Respuesta<ComprobantesDTO> respuestaHistorial,
			ComprobanteDTO comprobanteSeleccionado) {
		String resultado;
		if (EstadoRespuesta.OK.equals(respuestaHistorial.getEstadoRespuesta())) {
			resultado = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		} else if (EstadoRespuesta.ERROR.equals(respuestaHistorial.getEstadoRespuesta())) {
			resultado = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		} else {
			resultado = EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL;
		}
		estadisticaManager.add(comprobanteSeleccionado.getHistorial().getCodigoEstadistica(), resultado);
	}

	private HistorialComprobantesView armarView(List<ComprobanteDTO> comprobantes,
			ComprobanteDTO comprobanteSeleccionado) {
		HistorialComprobantesView historialView = new HistorialComprobantesView();
		historialView.setEmpresa(obtenerEmpresaDestinatario(comprobanteSeleccionado));
		historialView.setIdentificacion(obtenerIdentificacion(comprobantes, comprobanteSeleccionado));
		historialView.setTipoCuentaDestino(obtenerTipoCuentaDestino(comprobanteSeleccionado.getDetalle()));
		List<ComprobanteHistorialView> comprobantesView = new ArrayList<ComprobanteHistorialView>();
		for (ComprobanteDTO comprobante : comprobantes) {
			ComprobanteHistorialView comprobanteView = new ComprobanteHistorialView();
			comprobanteView.setId(Integer.toString(comprobante.getIndice()));
			comprobanteView.setFecha(
					formatearFechaHistorial(comprobante.getFecha(), sesionParametros.getRegistroSession().isMobile()));
			setearImportes(comprobante, comprobanteView);
			comprobantesView.add(comprobanteView);
		}
		historialView.setComprobantes(comprobantesView);
		return historialView;
	}

	private String obtenerIdentificacion(List<ComprobanteDTO> comprobantes, ComprobanteDTO comprobanteSeleccionado) {
		if (TipoDetalleComprobanteEnum.IATX_PMC_PAGO_PUNTUAL
				.equals(comprobanteSeleccionado.getDetalle().getTipoComprobante())
				&& TipoDetalleComprobanteEnum.IATX_PMC_AFIP
						.equals(comprobantes.get(0).getDetalle().getTipoComprobante())) {
			return StringUtils.left(comprobanteSeleccionado.getDetalle().obtenerIdentificacionHistorial(), 11);
		}
		return comprobanteSeleccionado.getDetalle().obtenerIdentificacionHistorial();
	}

	private String obtenerTipoCuentaDestino(DetalleComprobanteDTO detalle) {
		if (detalle instanceof DetalleComprobanteTransferenciaDTO) {
			DetalleComprobanteTransferenciaDTO detalleTransferencia = (DetalleComprobanteTransferenciaDTO) detalle;
			return detalleTransferencia.getTipoCuentaDestino() != null
					? detalleTransferencia.getTipoCuentaDestino().getDescripcion()
					: null;
		}
		return null;
	}

	private String formatearFechaHistorial(Date fecha, Boolean isMobile) {
		DateFormat sdf = isMobile ? new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA_ANIO_CORTO)
				: new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ar"));
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(fecha);
	}

	private void setearImportes(ComprobanteDTO comprobante, ComprobanteHistorialView comprobanteView) {
		if (comprobante.getImportePesos() != null && comprobante.getImporteDolares() != null) {
			comprobanteView.setImporte(DivisaEnum.PESO.getSimbolo() + ISBANStringUtils.ESPACIO_STRING
					+ ISBANStringUtils.formatearSaldoConSigno(comprobante.getImportePesos()));
			comprobanteView.setImporteDolares(DivisaEnum.DOLAR.getSimbolo() + ISBANStringUtils.ESPACIO_STRING
					+ ISBANStringUtils.formatearSaldoConSigno(comprobante.getImporteDolares()));
		} else {
			String importe = comprobante.getImportePesos() != null
					? ISBANStringUtils.formatearSaldoConSigno(comprobante.getImportePesos())
					: ISBANStringUtils.formatearSaldoConSigno(comprobante.getImporteDolares());
			String moneda = comprobante.getImportePesos() != null ? DivisaEnum.PESO.getSimbolo()
					: DivisaEnum.DOLAR.getSimbolo();
			comprobanteView.setImporte(moneda + ISBANStringUtils.ESPACIO_STRING + importe);
		}
	}

	private String obtenerEmpresaDestinatario(ComprobanteDTO comprobanteSeleccionado) {
		if (HistorialComprobanteEnum.DEBITO_AUTOMATICO_PAGO_TARJETA.equals(comprobanteSeleccionado.getHistorial())
				|| HistorialComprobanteEnum.PAGO_TARJETA.equals(comprobanteSeleccionado.getHistorial())) {
			DetalleComprobanteIatxPagoDeTarjetaCreditoDTO detalle = (DetalleComprobanteIatxPagoDeTarjetaCreditoDTO) comprobanteSeleccionado
					.getDetalle();
			return TipoCuentaTarjeta.getTipoCuentaTarjetaFromTipoCuenta(detalle.getTipoCuenta()).getDescripcion();
		} else if (HistorialComprobanteEnum.COMPRA_VENTA.equals(comprobanteSeleccionado.getHistorial())) {
			return comprobanteSeleccionado.getTipoOperacion().getEtiqueta();
		}
		return comprobanteSeleccionado.getDestinatario();
	}
}
