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
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesSietePorVenticuatroBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesOtrosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaProgramadaPAGHABONDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@Component
public class HistorialComprobantesOtrosBOImpl implements HistorialComprobantesOtrosBO {

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private ScompBO scompBO;

	@Autowired
	private ComprobantesSietePorVenticuatroBO comprobantes7x24BO;

	@Override
	public Respuesta<List<ComprobanteDTO>> obtenerHistorialOtros(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccionDTO) {
		switch (comprobanteSeleccionado.getHistorial()) {
		case COMPRA_VENTA:
			return obtenerHistorialCompraVenta(comprobanteSeleccionado, cliente, transaccionDTO);
		case PAGO_SUELDOS:
			return obtenerHistorialPagoSueldos(comprobanteSeleccionado, cliente, transaccionDTO);
		default:
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialCompraVenta(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccionDTO) {
		Respuesta<ComprobantesDTO> respuestaCompraVenta = scompBO.obtenerComprobantesCompraVentaDolar(cliente.getNup(),
				transaccionDTO);
		if (EstadoRespuesta.ERROR.equals(respuestaCompraVenta.getEstadoRespuesta())) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		List<ComprobanteDTO> comprobantes = respuestaCompraVenta.getRespuesta().getComprobantes();
		filtrarComprobantesPorDestinatario(comprobantes, comprobanteSeleccionado);
		return respuestaFactory.crearRespuestaOk(comprobantes);
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialPagoSueldos(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccionDTO) {
		Respuesta<ComprobantesDTO> respuestaPagoSueldos = comprobantes7x24BO
				.obtenerComprobantesPagoHaberesYHonorariosUnificados(cliente, transaccionDTO);
		if (EstadoRespuesta.ERROR.equals(respuestaPagoSueldos.getEstadoRespuesta())) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		List<ComprobanteDTO> comprobantes = respuestaPagoSueldos.getRespuesta().getComprobantes();
		filtrarComprobantesPorCuentaCredito(comprobantes, comprobanteSeleccionado);
		if (EstadoRespuesta.WARNING.equals(respuestaPagoSueldos.getEstadoRespuesta())) {
			if (CollectionUtils.isEmpty(comprobantes)) {
				return armarRespuestaErrorGenericoHistorialComprobantes();
			}
			return respuestaFactory.crearRespuestaWarning(comprobantes, "", TipoError.ALERTA_COMPROBANTES,
					CodigoMensajeConstantes.ERROR_PARCIAL_COMPROBANTES);
		}
		return respuestaFactory.crearRespuestaOk(comprobantes);
	}

	private void filtrarComprobantesPorDestinatario(List<ComprobanteDTO> comprobantes,
			final ComprobanteDTO comprobanteSeleccionado) {
		CollectionUtils.filter(comprobantes, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ComprobanteDTO comprobante = (ComprobanteDTO) object;
				return StringUtils.equals(comprobante.getDestinatario(), comprobanteSeleccionado.getDestinatario())
						&& comprobante.getTipoCuentaDestino().equals(comprobanteSeleccionado.getTipoCuentaDestino());
			}
		});
	}

	private void filtrarComprobantesPorCuentaCredito(List<ComprobanteDTO> comprobantes,
			final ComprobanteDTO comprobanteSeleccionado) {
		CollectionUtils.filter(comprobantes, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ComprobanteDTO comprobante = (ComprobanteDTO) object;
				DetalleComprobanteTransferenciaProgramadaPAGHABONDTO detalle = (DetalleComprobanteTransferenciaProgramadaPAGHABONDTO) comprobante
						.getDetalle();
				DetalleComprobanteTransferenciaProgramadaPAGHABONDTO detalle2 = (DetalleComprobanteTransferenciaProgramadaPAGHABONDTO) comprobanteSeleccionado
						.getDetalle();
				return StringUtils.equals(detalle.getNroCuentaDestino(), detalle2.getNroCuentaDestino())
						&& detalle.getTipoCuentaDestino().equals(detalle2.getTipoCuentaDestino());
			}
		});
	}

	private Respuesta<List<ComprobanteDTO>> armarRespuestaErrorGenericoHistorialComprobantes() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
				CodigoMensajeConstantes.ERROR_COMPROBANTES);
	}

}
