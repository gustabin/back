package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesDebitosAutomaticosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesOtrosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesPagoServiciosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesPagoTarjetaBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesTransferenciasBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@Component
public class HistorialComprobanteBOImpl implements HistorialComprobantesBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HistorialComprobanteBOImpl.class);

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private HistorialComprobantesPagoServiciosBO historialPagoServicios;

	@Autowired
	private HistorialComprobantesTransferenciasBO historialTransferencias;

	@Autowired
	private HistorialComprobantesDebitosAutomaticosBO historialDebitosAutomaticos;

	@Autowired
	private HistorialComprobantesPagoTarjetaBO historialPagoTarjeta;
	
	@Autowired
	private HistorialComprobantesOtrosBO historialOtros;

	@Cacheable(value = CacheConstants.Names.CACHE_COMPROBANTES_HISTORIAL, key = "#cliente.nup")
	@Override
	public Respuesta<ComprobantesDTO> obtenerHistorial(ComprobanteDTO comprobanteSeleccionado, Cliente cliente) {
		if (comprobanteSeleccionado.getHistorial() == null) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		Respuesta<List<ComprobanteDTO>> respuesta;
		TransaccionDTO transaccionDTO = obtenerTransaccionDTOConFechas();
		switch (comprobanteSeleccionado.getHistorial()) {
		case PAGO_SERVICIOS_A:
		case PAGO_SERVICIOS_B:
		case PAGO_SERVICIOS_C:
			respuesta = historialPagoServicios.obtenerHistorialPagoServicios(comprobanteSeleccionado, cliente,
					transaccionDTO);
			break;
		case TRANSFERENCIA_RIORIO:
		case TRANSFERENCIA_OTROSBANCOS:
			respuesta = historialTransferencias.obtenerHistorialTransferencias(comprobanteSeleccionado, cliente,
					transaccionDTO);
			break;
		case DEBITO_AUTOMATICO_CUENTA:
		case DEBITO_AUTOMATICO_TARJETA:
			respuesta = historialDebitosAutomaticos.obtenerHistorialDebitosAutomaticos(comprobanteSeleccionado, cliente,
					transaccionDTO);
			break;
		case DEBITO_AUTOMATICO_PAGO_TARJETA:
		case PAGO_TARJETA:
			respuesta = historialPagoTarjeta.obtenerHistorialPagoTarjeta(comprobanteSeleccionado, cliente,
					transaccionDTO);
			break;
		case COMPRA_VENTA:
		case PAGO_SUELDOS:
			respuesta = historialOtros.obtenerHistorialOtros(comprobanteSeleccionado, cliente, transaccionDTO);
			break;
		default:
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		return ordenarYArmarRespuesta(respuesta);
	}

	private TransaccionDTO obtenerTransaccionDTOConFechas() {
		TransaccionDTO transaccion = new TransaccionDTO();
		transaccion.setFechaHasta(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
		transaccion.setFechaDesde(cal.getTime());
		return transaccion;
	}

	private Respuesta<ComprobantesDTO> armarRespuestaErrorGenericoHistorialComprobantes() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
				CodigoMensajeConstantes.ERROR_COMPROBANTES);
	}

	private Respuesta<ComprobantesDTO> ordenarYArmarRespuesta(Respuesta<List<ComprobanteDTO>> respuesta) {
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, respuesta.getItemsMensajeRespuesta());
		}
		List<ComprobanteDTO> comprobantes = respuesta.getRespuesta();
		ordenarComprobantesPorFechaDescendente(comprobantes);
		ComprobantesDTO comprobantesDTO = new ComprobantesDTO(
				comprobantes.subList(0, Math.min(12, comprobantes.size())));
		return EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())
				? respuestaFactory.crearRespuestaOk(comprobantesDTO)
				: respuestaFactory.crearRespuestaWarning(comprobantesDTO, respuesta.getItemsMensajeRespuesta());
	}

	private void ordenarComprobantesPorFechaDescendente(List<ComprobanteDTO> comprobantes) {
		Collections.sort(comprobantes, new Comparator<ComprobanteDTO>() {
			@Override
			public int compare(ComprobanteDTO comprobante1, ComprobanteDTO comprobante2) {
				return comprobante2.getFecha().compareTo(comprobante1.getFecha());
			}
		});
	}

	@CacheEvict(value = CacheConstants.Names.CACHE_COMPROBANTES_HISTORIAL, key = "#cliente.nup")
	@Override
	public void limpiarCache(Cliente cliente) {
		LOGGER.info("Se limpia la cache de {}", CacheConstants.Names.CACHE_COMPROBANTES_HISTORIAL);
	}
}
