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
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesDebitoAutomaticoEnCuentaBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DebitosAutomaticosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesDebitosAutomaticosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDebitoAutomaticoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@Component
public class HistorialComprobantesDebitosAutomaticosBOImpl implements HistorialComprobantesDebitosAutomaticosBO {

	@Autowired
	private ComprobantesDebitoAutomaticoEnCuentaBO comprobantesDebitosAutomaticosBO;

	@Autowired
	private DebitosAutomaticosBO debitosAutomaticosBO;

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Override
	public Respuesta<List<ComprobanteDTO>> obtenerHistorialDebitosAutomaticos(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccionDTO) {
		switch (comprobanteSeleccionado.getHistorial()) {
		case DEBITO_AUTOMATICO_CUENTA:
			return obtenerHistorialDebitosAutomaticosEnCuenta(comprobanteSeleccionado, cliente, transaccionDTO);
		case DEBITO_AUTOMATICO_TARJETA:
			return obtenerHistorialDebitosAutomaticosTarjeta(comprobanteSeleccionado, cliente, transaccionDTO);
		default:
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialDebitosAutomaticosEnCuenta(
			ComprobanteDTO comprobanteSeleccionado, Cliente cliente, TransaccionDTO transaccionDTO) {
		DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle = (DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO) comprobanteSeleccionado
				.getDetalle();
		Respuesta<ComprobantesDTO> respuestaDebitosAutomaticos = comprobantesDebitosAutomaticosBO
				.obtenerComprobantesDebitoAutomaticoEnCuentaPorEmpresa(cliente, detalle.getEmpresa(), transaccionDTO);
		if (EstadoRespuesta.ERROR.equals(respuestaDebitosAutomaticos.getEstadoRespuesta())) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		return respuestaFactory.crearRespuestaOk(respuestaDebitosAutomaticos.getRespuesta().getComprobantes());
	}

	private Respuesta<List<ComprobanteDTO>> armarRespuestaErrorGenericoHistorialComprobantes() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
				CodigoMensajeConstantes.ERROR_COMPROBANTES);
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialDebitosAutomaticosTarjeta(
			ComprobanteDTO comprobanteSeleccionado, Cliente cliente, TransaccionDTO transaccionDTO) {
		Respuesta<ComprobantesDTO> respuesta = debitosAutomaticosBO.obtenerComprobantes(cliente, transaccionDTO);
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		List<ComprobanteDTO> comprobantes = respuesta.getRespuesta().getComprobantes();
		filtrarDebitosAutomaticosTarjeta(comprobanteSeleccionado, comprobantes);
		return respuestaFactory.crearRespuestaOk(comprobantes);

	}

	private void filtrarDebitosAutomaticosTarjeta(final ComprobanteDTO comprobanteSeleccionado,
			List<ComprobanteDTO> comprobantes) {
		CollectionUtils.filter(comprobantes, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ComprobanteDTO comprobante = (ComprobanteDTO) object;
				DetalleComprobanteDebitoAutomaticoTarjetaDTO detalle1 = (DetalleComprobanteDebitoAutomaticoTarjetaDTO) comprobanteSeleccionado
						.getDetalle();
				DetalleComprobanteDebitoAutomaticoTarjetaDTO detalle2 = (DetalleComprobanteDebitoAutomaticoTarjetaDTO) comprobante
						.getDetalle();
				return StringUtils.equals(detalle1.getNombreTarjeta(), detalle2.getNombreTarjeta())
						&& StringUtils.equals(comprobante.getDestinatario(), comprobanteSeleccionado.getDestinatario());
			}
		});
	}

}
