/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesDebitoAutomaticoEnCuentaBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesDebitosAutomaticosUnificadosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPagoTarjetasBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DebitosAutomaticosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class ComprobantesDebitosAutomaticosUnificadosBOImpl.
 */
@Component
public class ComprobantesDebitosAutomaticosUnificadosBOImpl extends ComprobantesBOImpl
		implements ComprobantesDebitosAutomaticosUnificadosBO {

	/** The debitos automaticos BO. */
	@Autowired
	private DebitosAutomaticosBO debitosAutomaticosBO;

	/** The debito automatico en cuenta BO. */
	@Autowired
	private ComprobantesDebitoAutomaticoEnCuentaBO debitoAutomaticoEnCuentaBO;
	
	/** The pago tarjetas BO. */
	@Autowired
	private ComprobantesPagoTarjetasBO pagoTarjetasBO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebitosAutomaticosBOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesDebitosAutomaticosUnificadosBO#obtenerComprobantes(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantes(Cliente cliente, TransaccionDTO dto) {
		List<Future<Respuesta<ComprobantesDTO>>> listRes = new ArrayList<Future<Respuesta<ComprobantesDTO>>>();
		listRes.add(debitosAutomaticosBO.obtenerComprobantesAsync(cliente, dto));
		listRes.add(debitoAutomaticoEnCuentaBO.obtenerComprobantesDebitoAutomaticoEnCuentaFiltradosAsync(cliente, dto));
		listRes.add(pagoTarjetasBO.obtenerComprobantesPagoTarjetasAsync(cliente, dto, Boolean.TRUE));
		esperarHastaFinDeListaDeHilos(listRes);
		List<Respuesta<ComprobantesDTO>> respuestaDeServicios = obtenerRespuestaDeAsyncFromMap(listRes);
		int cantidadDeErrores = 0;

		return generarListaDeComprobantes(respuestaDeServicios, cantidadDeErrores, dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesDebitosAutomaticosUnificadosBO#obtenerComprobantesAsync(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	@Async
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesAsync(Cliente cliente, TransaccionDTO dto) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantes(cliente, dto));
	}

	/**
	 * Une la lista de respuestas en una sola.
	 *
	 * @param respuestaDeServicios
	 *            the respuesta de servicios
	 * @param cantidadDeErrores
	 *            the cantidad de errores
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> generarListaDeComprobantes(List<Respuesta<ComprobantesDTO>> respuestaDeServicios,
			int cantidadDeErrores, TransaccionDTO dto) {
		List<ComprobanteDTO> comprobantes = new LinkedList<ComprobanteDTO>();
		for (Respuesta<ComprobantesDTO> res : respuestaDeServicios) {
			if (EstadoRespuesta.ERROR.equals(res.getEstadoRespuesta())) {
				cantidadDeErrores++;
			} else {
				comprobantes.addAll(res.getRespuesta().getComprobantes());
			}
		}
		return armarRespuesta(filtrarDiaDelMes(comprobantes, dto), cantidadDeErrores);
	}

	/**
	 * Filtra los comprobantes por aquellos que sean validos, osea por aquellos
	 * cuya fecha desde viene antes o en el mismo momento de fecha hasta.
	 *
	 * @param comprobantes
	 *            the comprobantes
	 * @param dto
	 *            the dto
	 * @return the list
	 */
	private List<ComprobanteDTO> filtrarDiaDelMes(List<ComprobanteDTO> comprobantes, TransaccionDTO dto) {
		List<ComprobanteDTO> comprobantesFiltrados = new LinkedList<ComprobanteDTO>();
		for (ComprobanteDTO comprobante : comprobantes) {
			if ((comprobante.getFecha().after(dto.getFechaDesde())
					&& comprobante.getFecha().before(dto.getFechaHasta()))
					|| comprobante.getFecha().equals(dto.getFechaDesde())
					|| comprobante.getFecha().equals(dto.getFechaHasta())) {
				comprobantesFiltrados.add(comprobante);
			}
		}
		return comprobantesFiltrados;

	}

	/**
	 * Ordenar comprobantes.
	 *
	 * @param comprobantes
	 *            the comprobantes
	 * @return the linked list
	 */
	private LinkedList<ComprobanteDTO> ordenarComprobantes(LinkedList<ComprobanteDTO> comprobantes) {
		Collections.sort(comprobantes, new Comparator<ComprobanteDTO>() {
			@Override
			public int compare(ComprobanteDTO comprobante1, ComprobanteDTO comprobante2) {
				int i = comprobante2.getFecha().compareTo(comprobante1.getFecha());
				if (i != 0) {
					return i;
				}
				return comprobante1.getTipoOperacion().getPrioridad()
						.compareTo(comprobante2.getTipoOperacion().getPrioridad());
			}
		});
		return comprobantes;
	}

	/**
	 * Armar respuesta.
	 *
	 * @param comprobantes
	 *            the comprobantes
	 * @param cantidadDeErrores
	 *            the cantidad de errores
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> armarRespuesta(List<ComprobanteDTO> comprobantes, int cantidadDeErrores) {
		if (cantidadDeErrores == 0) {
			LinkedList<ComprobanteDTO> comprobantesOrdenados = ordenarComprobantes(
					new LinkedList<ComprobanteDTO>(comprobantes));
			return getRespuestaFactory().crearRespuestaOk(new ComprobantesDTO(comprobantesOrdenados));
		} else if (cantidadDeErrores == 1) {
			LinkedList<ComprobanteDTO> comprobantesOrdenados = ordenarComprobantes(
					new LinkedList<ComprobanteDTO>(comprobantes));
			return getRespuestaFactory().crearRespuestaWarning(new ComprobantesDTO(comprobantesOrdenados), "",
					TipoError.ALERTA_COMPROBANTES, CodigoMensajeConstantes.ERROR_PARCIAL_COMPROBANTES);
		} else {
			return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
					CodigoMensajeConstantes.ERROR_COMPROBANTES);
		}

	}

}
