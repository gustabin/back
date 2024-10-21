/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPMCUnificadoBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPagoMisCuentasBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DetallePMCBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

/**
 * The Class ComprobantesPMCUnificadoBOImpl.
 */
@Component
public class ComprobantesPMCUnificadoBOImpl extends ComprobantesBOImpl implements ComprobantesPMCUnificadoBO {

	/** The scomp BO. */
	@Autowired
	private ScompBO scompBO;

	/** The comprobantes PMC. */
	@Autowired
	private ComprobantesPagoMisCuentasBO comprobantesPMC;

	/** The detalle PMC bo. */
	@Autowired
	private DetallePMCBO detallePMCBo;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesPMCUnificadoBOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesPMCUnificadoBO#obtenerComprobantesPMC(ar.com.santanderrio.obp
	 * .servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMC(Cliente cliente, TransaccionDTO transaccion) {
		List<Future<Respuesta<ComprobantesDTO>>> listRes = new ArrayList<Future<Respuesta<ComprobantesDTO>>>();
		String nup = cliente.getNup();
		listRes.add(scompBO.obtenerComprobantesPMCConDeudaAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesPMCSinDeudaAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesPMCVEPAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesPMCAfipAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesPMCConDeudaTCAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesPMCSinDeudaTCAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesPMCVEPTCAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesPMCAfipTCAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesPMCOpenBankAsync(nup, transaccion));
		if (Integer.valueOf(0).equals(transaccion.getTransaccion().getId())) {
			listRes.add(scompBO.obtenerComprobantesPagoDeComprasAsync(nup, transaccion));
		}
		Respuesta<ComprobantesDTO> resIatx = comprobantesPMC.obtenerComprobantesPagoMisCuentas(cliente, transaccion);
		esperarHastaFinDeListaDeHilos(listRes);

		List<Respuesta<ComprobantesDTO>> listScomp = obtenerRespuestaDeAsyncFromMap(listRes);
		HashSet<ComprobanteDTO> resIatxFiltrado = removerDuplicados(listScomp, resIatx);
		return unirComprobantes(listScomp, resIatx, resIatxFiltrado);
	}

	/**
	 * Une todas las respuestas en una sola, unificando el estado respuesta.
	 *
	 * @param listScomp
	 *            the list scomp
	 * @param resIatx
	 *            the res iatx
	 * @param resIatxFiltrado
	 *            the res iatx filtrado
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> unirComprobantes(List<Respuesta<ComprobantesDTO>> listScomp,
			Respuesta<ComprobantesDTO> resIatx, HashSet<ComprobanteDTO> resIatxFiltrado) {
		int cantidadConError = 0;
		if (EstadoRespuesta.ERROR.equals(resIatx.getEstadoRespuesta())) {
			cantidadConError++;
		}
		for (Respuesta<ComprobantesDTO> resScomp : listScomp) {
			if (!EstadoRespuesta.OK.equals(resScomp.getEstadoRespuesta())) {
				cantidadConError++;
			}
		}
		if (cantidadConError == 0) {
			resIatx.setEstadoRespuesta(EstadoRespuesta.OK);
			resIatx.getRespuesta().setComprobantes(new LinkedList<ComprobanteDTO>(resIatxFiltrado));
		} else if (cantidadConError > 0 && cantidadConError < listScomp.size() + 1) {
			resIatx.setEstadoRespuesta(EstadoRespuesta.WARNING);
			if (null == resIatx.getRespuesta()) {
				ComprobantesDTO comprobantesAux = new ComprobantesDTO();
				resIatx.setRespuesta(comprobantesAux);
			}
			resIatx.getRespuesta().setComprobantes(new LinkedList<ComprobanteDTO>(resIatxFiltrado));
		} else {
			resIatx.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}
		return resIatx;
	}

	/**
	 * Añade los comprobantes al hashSet, lo cual remueve los duplicados.
	 *
	 * @param listScomp
	 *            the list scomp
	 * @param resIatx
	 *            the res iatx
	 * @return the hash set
	 */
	private HashSet<ComprobanteDTO> removerDuplicados(List<Respuesta<ComprobantesDTO>> listScomp,
			Respuesta<ComprobantesDTO> resIatx) {
		HashSet<ComprobanteDTO> sets = obtenerSetSiNoError(listScomp);
		if (!EstadoRespuesta.ERROR.equals(resIatx.getEstadoRespuesta())) {
			sets.addAll(resIatx.getRespuesta().getComprobantes());
		}
		return sets;
	}

	/**
	 * Transforma la lista en un set, cuidandose de que el estado respuesta de la
	 * lista a pedir no sea error.
	 *
	 * @param listaRes
	 *            the lista res
	 * @return the hash set
	 */
	private HashSet<ComprobanteDTO> obtenerSetSiNoError(List<Respuesta<ComprobantesDTO>> listaRes) {
		HashSet<ComprobanteDTO> set = new HashSet<ComprobanteDTO>();
		for (Respuesta<ComprobantesDTO> res : listaRes) {
			if (!EstadoRespuesta.ERROR.equals(res.getEstadoRespuesta())) {
				HashSet<ComprobanteDTO> comprobantes = new HashSet<ComprobanteDTO>(
						res.getRespuesta().getComprobantes());
				set.addAll(comprobantes);
			}
		}
		return set;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesPMCUnificadoBO#obtenerComprobantesPMCAsync(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCAsync(Cliente cliente, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMC(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesPMCUnificadoBO#obtenerComprobantesPMCPorEmpresaAsync(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCPorEmpresaAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCPorEmpresa(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesPMCUnificadoBO#obtenerComprobantesPMCPorEmpresa(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCPorEmpresa(Cliente cliente, TransaccionDTO transaccion) {
		Respuesta<ComprobantesDTO> respuesta = detallePMCBo.obtenerComprobantesPorEmpresas(transaccion, cliente);
		if (respuesta.getEstadoRespuesta() != EstadoRespuesta.ERROR) {
			filtrarComprobantesPorFechas(respuesta, transaccion.getFechaDesde(), transaccion.getFechaHasta());
		}
		return respuesta;
	}

	/**
	 * Filtrar comprobantes por fechas.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 */
	private void filtrarComprobantesPorFechas(Respuesta<ComprobantesDTO> respuesta, Date fechaDesde, Date fechaHasta) {
		List<ComprobanteDTO> comprobantesFiltrados = new LinkedList<ComprobanteDTO>();
		for (ComprobanteDTO comprobante : respuesta.getRespuesta().getComprobantes()) {
			if ((comprobante.getFecha().after(fechaDesde) && comprobante.getFecha().before(fechaHasta))
					|| comprobante.getFecha().equals(fechaDesde) || comprobante.getFecha().equals(fechaHasta)) {
				comprobantesFiltrados.add(comprobante);
			}
		}
		respuesta.getRespuesta().setComprobantes(comprobantesFiltrados);

	}

}
