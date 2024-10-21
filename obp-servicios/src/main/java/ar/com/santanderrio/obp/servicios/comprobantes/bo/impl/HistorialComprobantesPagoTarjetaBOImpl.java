package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPagoTarjetasBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesPagoTarjetaBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxPagoDeTarjetaCreditoDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.HistorialComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@Component
public class HistorialComprobantesPagoTarjetaBOImpl implements HistorialComprobantesPagoTarjetaBO {

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private ComprobantesPagoTarjetasBO pagoTarjetasBO;

	@Override
	public Respuesta<List<ComprobanteDTO>> obtenerHistorialPagoTarjeta(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccionDTO) {
		Cuenta cuenta = buscarCuentaPorNumeroTarjetaComprobante(
				(DetalleComprobanteIatxPagoDeTarjetaCreditoDTO) comprobanteSeleccionado.getDetalle(), cliente);
		Boolean esDebitoAutomatico = HistorialComprobanteEnum.DEBITO_AUTOMATICO_PAGO_TARJETA
				.equals(comprobanteSeleccionado.getHistorial());
		Respuesta<ComprobantesDTO> respuesta = pagoTarjetasBO.obtenerComprobantesPagoTarjetasPorCuenta(cliente,
				transaccionDTO, esDebitoAutomatico, cuenta);
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		return respuestaFactory.crearRespuestaOk(respuesta.getRespuesta().getComprobantes());
	}

	private Cuenta buscarCuentaPorNumeroTarjetaComprobante(
			DetalleComprobanteIatxPagoDeTarjetaCreditoDTO detalleComprobanteSeleccionado, Cliente cliente) {
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (StringUtils.equals(detalleComprobanteSeleccionado.getNroTarjetaCredito(),
					cuenta.getNroTarjetaCredito())) {
				return cuenta;
			}
		}
		return null;
	}

	private Respuesta<List<ComprobanteDTO>> armarRespuestaErrorGenericoHistorialComprobantes() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
				CodigoMensajeConstantes.ERROR_COMPROBANTES);
	}

}
