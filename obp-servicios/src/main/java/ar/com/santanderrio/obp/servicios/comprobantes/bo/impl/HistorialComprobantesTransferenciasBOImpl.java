package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesTransferenciasUnificadoBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesTransferenciasBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@Component
public class HistorialComprobantesTransferenciasBOImpl implements HistorialComprobantesTransferenciasBO {

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private ComprobantesTransferenciasUnificadoBO comprobantesTransferenciasBO;

	@Override
	public Respuesta<List<ComprobanteDTO>> obtenerHistorialTransferencias(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccionDTO) {
		switch (comprobanteSeleccionado.getHistorial()) {
		case TRANSFERENCIA_RIORIO:
			return obtenerHistorialTransferenciasRioRio(comprobanteSeleccionado, cliente, transaccionDTO);
		case TRANSFERENCIA_OTROSBANCOS:
			return obtenerHistorialTransferenciasOtrosBancos(comprobanteSeleccionado, cliente, transaccionDTO);
		default:
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
	}

	private Respuesta<List<ComprobanteDTO>> armarRespuestaErrorGenericoHistorialComprobantes() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
				CodigoMensajeConstantes.ERROR_COMPROBANTES);
	}

	private Respuesta<List<ComprobanteDTO>> armarRespuestaWarningHistorialTransferencia(
			List<ComprobanteDTO> comprobantes) {
		return respuestaFactory.crearRespuestaWarning(comprobantes, "", TipoError.ALERTA_COMPROBANTES,
				CodigoMensajeConstantes.ERROR_PARCIAL_COMPROBANTES);
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialTransferenciasRioRio(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccionDTO) {
		Respuesta<ComprobantesDTO> respuestaComprobantesTransferenciaRioRio = comprobantesTransferenciasBO
				.obtenerComprobantesTransferenciaRioRio(cliente, transaccionDTO);
		if (EstadoRespuesta.ERROR.equals(respuestaComprobantesTransferenciaRioRio.getEstadoRespuesta())) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		List<ComprobanteDTO> comprobantes = respuestaComprobantesTransferenciaRioRio.getRespuesta().getComprobantes();
		filtrarComprobantesTransferenciaRioRio(comprobantes, comprobanteSeleccionado);
		return EstadoRespuesta.OK.equals(respuestaComprobantesTransferenciaRioRio.getEstadoRespuesta())
				? respuestaFactory.crearRespuestaOk(comprobantes)
				: armarRespuestaWarningHistorialTransferencia(comprobantes);
	}

	private void filtrarComprobantesTransferenciaRioRio(List<ComprobanteDTO> comprobantes,
			final ComprobanteDTO comprobanteSeleccionado) {
		CollectionUtils.filter(comprobantes, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ComprobanteDTO comprobante = (ComprobanteDTO) object;
				DetalleComprobanteTransferenciaDTO detalle = (DetalleComprobanteTransferenciaDTO) comprobante
						.getDetalle();
				DetalleComprobanteTransferenciaDTO detalleSeleccionado = (DetalleComprobanteTransferenciaDTO) comprobanteSeleccionado
						.getDetalle();
				return StringUtils.equals(detalle.getNroCuentaDestino(), detalleSeleccionado.getNroCuentaDestino())
						&& detalle.getTipoCuentaDestino().equals(detalleSeleccionado.getTipoCuentaDestino());
			}
		});
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialTransferenciasOtrosBancos(
			ComprobanteDTO comprobanteSeleccionado, Cliente cliente, TransaccionDTO transaccionDTO) {
		Respuesta<ComprobantesDTO> respuestaComprobantesTransferenciaRioRio = comprobantesTransferenciasBO
				.obtenerComprobantesTransferenciaOtrosBancos(cliente, transaccionDTO);
		if (EstadoRespuesta.ERROR.equals(respuestaComprobantesTransferenciaRioRio.getEstadoRespuesta())) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		List<ComprobanteDTO> comprobantes = respuestaComprobantesTransferenciaRioRio.getRespuesta().getComprobantes();
		filtrarComprobantesTransferenciaOtrosBancos(comprobantes, comprobanteSeleccionado);
		return EstadoRespuesta.OK.equals(respuestaComprobantesTransferenciaRioRio.getEstadoRespuesta())
				? respuestaFactory.crearRespuestaOk(comprobantes)
				: armarRespuestaWarningHistorialTransferencia(comprobantes);
	}

	private void filtrarComprobantesTransferenciaOtrosBancos(List<ComprobanteDTO> comprobantes,
			final ComprobanteDTO comprobanteSeleccionado) {
		CollectionUtils.filter(comprobantes, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ComprobanteDTO comprobante = (ComprobanteDTO) object;
				DetalleComprobanteTransferenciaDTO detalle = (DetalleComprobanteTransferenciaDTO) comprobante
						.getDetalle();
				DetalleComprobanteTransferenciaDTO detalleSeleccionado = (DetalleComprobanteTransferenciaDTO) comprobanteSeleccionado
						.getDetalle();
				return StringUtils.equals(detalle.getNroCuentaDestino(), detalleSeleccionado.getNroCuentaDestino());
			}
		});
	}
}
