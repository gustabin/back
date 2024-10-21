/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesSietePorVenticuatroBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesTransferenciasUnificadoBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;

/**
 * The Class ComprobantesTransferenciasUnificadoBOImpl.
 */
@Component
public class ComprobantesTransferenciasUnificadoBOImpl extends ComprobantesBOImpl
		implements ComprobantesTransferenciasUnificadoBO {

	/** The scomp BO. */
	@Autowired
	private ScompBO scompBO;

	/** The comp 7 x 24 BO. */
	@Autowired
	private ComprobantesSietePorVenticuatroBO comp7x24BO;

	/** The Constant CREANDO_RESPUESTA_OK. */
	public static final int INMEDIATA_RIO = 0;

	/** The Constant INMEDIATA_OB. */
	public static final int INMEDIATA_OB = 1;

	/** The Constant INMEDIATA_RIO_TBANCO. */
	public static final int INMEDIATA_RIO_TBANCO = 2;

	/** The Constant INMEDIATA_OB_TBANCO. */
	public static final int INMEDIATA_OB_TBANCO = 3;

	/** The Constant INMEDIATA_OTROS_COMPROBANTES. */
	public static final int INMEDIATA_OTROS_COMPROBANTES = 4;

	/** The Constant PROGRAMADA_RIO. */
	public static final int PROGRAMADA_RIO = 5;

	/** The Constant PROGRAMADA_RIO_NOEFEC. */
	public static final int PROGRAMADA_RIO_NOEFEC = 6;

	/** The Constant PROGRAMADA_OB. */
	public static final int PROGRAMADA_OB = 7;

	/** The Constant PROGRAMADA_OB_NOEFEC. */
	public static final int PROGRAMADA_OB_NOEFEC = 8;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesTransferenciasUnificadoBO#
	 * obtenerComprobantesTransferenciaAsync(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesTransferenciaAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesTransferencia(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesTransferenciasUnificadoBO#obtenerComprobantesTransferencia(ar
	 * .com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesTransferencia(Cliente cliente, TransaccionDTO transaccion) {
		List<Future<Respuesta<ComprobantesDTO>>> listRes = new ArrayList<Future<Respuesta<ComprobantesDTO>>>();
		String nup = cliente.getNup();
		listRes.add(scompBO.obtenerComprobantesRioAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesOtrosBancosAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesRioTBancoAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesOBTBancoAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesOtrosComprobantesAsync(nup, transaccion));

		listRes.add(comp7x24BO.obtenerComprobantesRioAsync(cliente, transaccion));
		listRes.add(comp7x24BO.obtenerComprobantesRioNoEfecAsync(cliente, transaccion));
		listRes.add(comp7x24BO.obtenerComprobantesOtrosBancosAsync(cliente, transaccion));
		listRes.add(comp7x24BO.obtenerComprobantesOtrosBancosNoEfecAsync(cliente, transaccion));

		esperarHastaFinDeListaDeHilos(listRes);

		List<Respuesta<ComprobantesDTO>> listaTotal = obtenerRespuestaDeAsyncFromMap(listRes);
		LinkedList<ComprobanteDTO> compUnificados = unirYagregarNoEfectuados(listaTotal);
		
		return crearRespuesta(compUnificados, listaTotal);
	}

	/**
	 * Crear respuesta.
	 *
	 * @param compUnificados
	 *            the comp unificados
	 * @param listaTotal
	 *            the lista total
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuesta(LinkedList<ComprobanteDTO> compUnificados,
			List<Respuesta<ComprobantesDTO>> listaTotal) {
		int cantidadConError = 0;
		Respuesta<ComprobantesDTO> res;
		for (Respuesta<ComprobantesDTO> resScomp : listaTotal) {
			if (!EstadoRespuesta.OK.equals(resScomp.getEstadoRespuesta())) {
				cantidadConError++;
			}
		}
		if (cantidadConError == 0) {
			res = respuestaFactory.crearRespuestaOk(ComprobantesDTO.class, new ComprobantesDTO(compUnificados, false));
		} else if (cantidadConError > 0 && cantidadConError < 9) {
			res = respuestaFactory.crearRespuestaWarning(new ComprobantesDTO(compUnificados, false),
					new ArrayList<ItemMensajeRespuesta>());
		} else {
			res = respuestaFactory.crearRespuestaError(ComprobantesDTO.class, new ArrayList<ItemMensajeRespuesta>());
		}
		return res;
	}

	/**
	 * Unir y agregar no efectuados.
	 *
	 * @param listaTotal
	 *            the lista total
	 * @return the linked list
	 */
	private LinkedList<ComprobanteDTO> unirYagregarNoEfectuados(List<Respuesta<ComprobantesDTO>> listaTotal) {
		LinkedList<ComprobanteDTO> res = new LinkedList<ComprobanteDTO>();
		for (Respuesta<ComprobantesDTO> respComprobanteDTO : listaTotal) {
		    res.addAll(agregarSiNoError(respComprobanteDTO));
        }
		HashMap<String, ComprobanteDTO> comprobantesUnificadosMap = new HashMap<String, ComprobanteDTO>();
		List<ComprobanteDTO> comprobantesRechazados = new ArrayList<ComprobanteDTO>();
        
        for (ComprobanteDTO comprobante : res) {
        	if (StringUtils.defaultString(comprobante.getDetalle().getNroComprobante()).isEmpty()) {
        		comprobantesRechazados.add(validarConceptoSiVieneDeOBP(comprobante));
        		continue;
        	}
            comprobantesUnificadosMap.put(ISBANStringUtils.eliminarCeros(StringUtils.defaultString(comprobante.getDetalle().getNroComprobante())), validarConceptoSiVieneDeOBP(comprobante));
        }
        LinkedList<ComprobanteDTO> listaTotal2 = new LinkedList<ComprobanteDTO>(comprobantesUnificadosMap.values());
        listaTotal2.addAll(comprobantesRechazados);
		return listaTotal2;
	}
	
	/**
	 * Validar concepto si viene de OBP.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO validarConceptoSiVieneDeOBP(ComprobanteDTO comprobante) {
		ConceptoTransferenciaEnum concepto = ConceptoTransferenciaEnum.getConceptoFromCodigo(((DetalleComprobanteTransferenciaDTO)comprobante.getDetalle()).getConcepto());
		if (null != concepto) {
			((DetalleComprobanteTransferenciaDTO)comprobante.getDetalle()).setConcepto(concepto.getDescripcion());
		}
		return comprobante;
	}

	/**
	 * Agregar si no error.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the list
	 */
	private List<ComprobanteDTO> agregarSiNoError(Respuesta<ComprobantesDTO> respuesta) {
		if (!EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return respuesta.getRespuesta().getComprobantes();
		}
		return new LinkedList<ComprobanteDTO>();
	}

	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesTransferenciaRioRio(Cliente cliente,
			TransaccionDTO transaccion) {
		List<Future<Respuesta<ComprobantesDTO>>> listRes = new ArrayList<Future<Respuesta<ComprobantesDTO>>>();
		String nup = cliente.getNup();
		listRes.add(scompBO.obtenerComprobantesRioAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesRioTBancoAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesOtrosComprobantesAsync(nup, transaccion));

		listRes.add(comp7x24BO.obtenerComprobantesRioAsync(cliente, transaccion));
		listRes.add(comp7x24BO.obtenerComprobantesRioNoEfecAsync(cliente, transaccion));

		esperarHastaFinDeListaDeHilos(listRes);

		List<Respuesta<ComprobantesDTO>> listaTotal = obtenerRespuestaDeAsyncFromMap(listRes);
		LinkedList<ComprobanteDTO> compUnificados = unirYagregarNoEfectuados(listaTotal);
		
		return crearRespuesta(compUnificados, listaTotal);
	}

	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesTransferenciaOtrosBancos(Cliente cliente,
			TransaccionDTO transaccion) {
		List<Future<Respuesta<ComprobantesDTO>>> listRes = new ArrayList<Future<Respuesta<ComprobantesDTO>>>();
		String nup = cliente.getNup();
		listRes.add(scompBO.obtenerComprobantesOtrosBancosAsync(nup, transaccion));
		listRes.add(scompBO.obtenerComprobantesOBTBancoAsync(nup, transaccion));

		listRes.add(comp7x24BO.obtenerComprobantesOtrosBancosAsync(cliente, transaccion));
		listRes.add(comp7x24BO.obtenerComprobantesOtrosBancosNoEfecAsync(cliente, transaccion));

		esperarHastaFinDeListaDeHilos(listRes);

		List<Respuesta<ComprobantesDTO>> listaTotal = obtenerRespuestaDeAsyncFromMap(listRes);
		LinkedList<ComprobanteDTO> compUnificados = unirYagregarNoEfectuados(listaTotal);
		
		return crearRespuesta(compUnificados, listaTotal);
	}
}
