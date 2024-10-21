/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosMensAvisos;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaEjecutadaDTO;

/**
 * The Class AgendaTransferenciaMapper.
 */
public class AgendaTransferenciaMapper {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaTransferenciaMapper.class);

	/** The Constant MSJ_INFO_ERRO_MAPEO_DATOS. */
	private static final String MSJ_INFO_ERRO_MAPEO_DATOS = "Ha ocurrido un error al mapear los datos";

	/** The Constant MSJ_INFO_ERRO_MAPEO_DATOS_DE_ADICIONALES. */
	private static final String MSJ_INFO_ERRO_MAPEO_DATOS_DE_ADICIONALES = "Ha ocurrido un error al mapear DatosAdicionales, es nulo";

	/**
	 * Instantiates a new agenda transferencia mapper.
	 */
	private AgendaTransferenciaMapper() {

	}

	/**
	 * Obtener transferencia agendada.
	 *
	 * @param regResponse
	 *            the reg response
	 * @param isRioRio
	 *            the is rio rio
	 * @return the transferencia agendada
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	public static TransferenciaAgendada obtenerTransferenciaAgendada(Registro regResponse, boolean isRioRio)
			throws JAXBException {
		try {
			TransferenciaAgendada transferenciaAgendada;
			transferenciaAgendada = new TransferenciaAgendada();
			transferenciaAgendada.setCanalTipo(regResponse.getCanalTipo());
			if (null != regResponse.getDatosAdicionales().getDatosAdicionales()) {
				mapearDatosAdicionales(transferenciaAgendada,
						regResponse.getDatosAdicionales().getDatosAdicionales().getDatosMensAvisos());
			} else {
				LOGGER.error(MSJ_INFO_ERRO_MAPEO_DATOS_DE_ADICIONALES);
			}
			mapearDatosEntrada(transferenciaAgendada, regResponse.getDATOSENTRADA(), isRioRio);
			transferenciaAgendada.setFechaAgendamiento(regResponse.getFechaAgendamiento());
			transferenciaAgendada.setFechaBaseRecurrencia(regResponse.getFechaBaseRecurrencia());
			transferenciaAgendada.setFechaEjecucion(regResponse.getFechaEjecucion());
			transferenciaAgendada.setFrecRecurrencia(regResponse.getFrecRecurrencia());
			transferenciaAgendada.setFrecuenciaRec(regResponse.getFrecRecurrencia());
			transferenciaAgendada.setIdTransaccion(regResponse.getIdTransaccion());
			transferenciaAgendada.setId(Long.parseLong(regResponse.getIdTransaccion()));
			transferenciaAgendada.setMaxRecurrencia(regResponse.getMaxRecurrencia());
			transferenciaAgendada.setNombre(regResponse.getNombre());
			transferenciaAgendada.setNroRecurrencia(regResponse.getNroRecurrencia());
			transferenciaAgendada.setSubcanalTipo(regResponse.getSubcanalTipo());
			transferenciaAgendada.setTipoAgendamiento(regResponse.getTipoAgendamiento());
			transferenciaAgendada.setTipoRecurrencia(regResponse.getTipoRecurrencia());
			transferenciaAgendada.setVersion(regResponse.getVersion());
			return transferenciaAgendada;
		} catch (StringIndexOutOfBoundsException e) {
			LOGGER.error(MSJ_INFO_ERRO_MAPEO_DATOS, e);
			throw new JAXBException(MSJ_INFO_ERRO_MAPEO_DATOS);
		}
	}

	/**
	 * Mapear datos adicionales.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @param datosMensAvisos
	 *            the datos mens avisos
	 */
	private static void mapearDatosAdicionales(TransferenciaAgendada transferenciaAgendada,
			DatosMensAvisos datosMensAvisos) {
		transferenciaAgendada.setConcepto(datosMensAvisos.getConcepto());
		transferenciaAgendada.setTitularCredito(datosMensAvisos.getTitularCredito());
		transferenciaAgendada.setDescripcionAdicional3(datosMensAvisos.getDescripcionAdicional3());
		transferenciaAgendada.setDescripcionAdicional2(datosMensAvisos.getDescripcionAdicional2());
		transferenciaAgendada.setDescripcionAdicional1(datosMensAvisos.getDescripcionAdicional1());
		transferenciaAgendada.setMailClienteReply(datosMensAvisos.getMailClienteReply());
		transferenciaAgendada.setConceptoAdicional3(datosMensAvisos.getConceptoAdicional3());
		transferenciaAgendada.setConceptoAdicional2(datosMensAvisos.getConceptoAdicional2());
		transferenciaAgendada.setInfoAdicional(datosMensAvisos.getInfoAdicional());
		transferenciaAgendada.setConceptoAdicional1(datosMensAvisos.getConceptoAdicional1());
		transferenciaAgendada.setAnotacionesPersonales(datosMensAvisos.getAnotacionesPersonales());
		transferenciaAgendada.setComentario(datosMensAvisos.getComentario());
		transferenciaAgendada.setMailCredito(datosMensAvisos.getMailCredito());
		transferenciaAgendada.setTitularDebito(datosMensAvisos.getTitularDebito());
	}

	/**
	 * Mapear datos entrada.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @param datosentrada
	 *            the datosentrada
	 * @param isRioRio
	 *            the is rio rio
	 */
	private static void mapearDatosEntrada(TransferenciaAgendada transferenciaAgendada, DATOSENTRADA datosentrada,
			boolean isRioRio) {
		if (isRioRio) {
			transferenciaAgendada.setNroComprobante(datosentrada.getNroComprobante());
			transferenciaAgendada.setCotizacionTransferencia(datosentrada.getCotizacionTransferencia());
			transferenciaAgendada.setUsuarioTipo(datosentrada.getUsuarioTipo());
			transferenciaAgendada.setIndicadorLimiteMax(datosentrada.getIndicadorLimiteMax());
			transferenciaAgendada.setLogueoAgendaHistorica(datosentrada.getLogueoAgendaHistorica());
			transferenciaAgendada.setNroCtaDebito(datosentrada.getNroCtaDebito());
			transferenciaAgendada.setImporteDebito(datosentrada.getImporteDebito());
			transferenciaAgendada.setIdSesionCnt(datosentrada.getIdSesionCnt());
			transferenciaAgendada.setIndAuten(datosentrada.getIndAuten());
			transferenciaAgendada.setSubcanalId(datosentrada.getSubcanalId());
			transferenciaAgendada.setUsuarioAtrib(datosentrada.getUsuarioAtrib());
			transferenciaAgendada.setFechaNac(datosentrada.getFechaNac());
			transferenciaAgendada.setIndicadorAfectarDisponible(datosentrada.getIndicadorAfectarDisponible());
			transferenciaAgendada.setTipoPersona(datosentrada.getTipoPersona());
			transferenciaAgendada.setCanalId(datosentrada.getCanalId());
			transferenciaAgendada.setSucCtaCredito(datosentrada.getSucCtaCredito());
			transferenciaAgendada.setImporteCredito(datosentrada.getImporteCredito());
			transferenciaAgendada.setNombreCtaCredito(datosentrada.getNombreCtaCredito());
			transferenciaAgendada.setEcoDatosEntrada(datosentrada.getEcoDatosEntrada());
			transferenciaAgendada.setNroCtaCredito(datosentrada.getNroCtaCredito());
			transferenciaAgendada.setSucCtaDebito(datosentrada.getSucCtaDebito());
			transferenciaAgendada.setCuentaPropia(datosentrada.getCuentaPropia());
			transferenciaAgendada.setCodigoConcepto(datosentrada.getCodigoConcepto());
			transferenciaAgendada.setTipoCtaDebito(datosentrada.getTipoCtaDebito());
			transferenciaAgendada.setIndTransfDiferida(datosentrada.getIndTransfDiferida());
			transferenciaAgendada.setTipoCtaCredito(datosentrada.getTipoCtaCredito());
			ConceptoTransferenciaEnum conceptoTransferenciaEnum = ConceptoTransferenciaEnum
					.getConceptoFromCodigo(datosentrada.getDescripcionConcepto());
			transferenciaAgendada.setDescripcionConcepto(conceptoTransferenciaEnum != null
					? conceptoTransferenciaEnum.getDescripcion() : datosentrada.getDescConcepto());
			transferenciaAgendada.setTipoId(datosentrada.getTipoId());
			transferenciaAgendada.setCanalVersion(datosentrada.getCanalVersion());
			transferenciaAgendada.setIdCliente(datosentrada.getIdCliente());
			transferenciaAgendada.setVersionXML(datosentrada.getVersionXML());
		} else {
			transferenciaAgendada.setInformacDiscrecional(datosentrada.getInformacDiscrecional());
			transferenciaAgendada.setCbu(datosentrada.getCbu());
			transferenciaAgendada.setCaracteristicaCtaCredito(datosentrada.getCaracteristicaCtaCredito());
			transferenciaAgendada.setMonedaTransferencia(datosentrada.getMonedaTransferencia());
			transferenciaAgendada.setLongCtaDesBane(datosentrada.getLongCtaDesBane());
			transferenciaAgendada.setCuit1(datosentrada.getCuit1());
			transferenciaAgendada.setCuit2(datosentrada.getCuit2());
			transferenciaAgendada.setCuit3(datosentrada.getCuit3());
			transferenciaAgendada.setUsuarioTipo(datosentrada.getUsuarioTipo());
			transferenciaAgendada.setMarcaGravado(datosentrada.getMarcaGravado());
			transferenciaAgendada.setLogueoAgendaHistorica(datosentrada.getLogueoAgendaHistorica());
			transferenciaAgendada.setNroCtaDebito(datosentrada.getNroCtaDebito());
			transferenciaAgendada.setIndicadorFuncion(datosentrada.getIndicadorFuncion());
			transferenciaAgendada.setFiid(datosentrada.getFiid());
			transferenciaAgendada.setIdSesionCnt(datosentrada.getIdSesionCnt());
			transferenciaAgendada.setIndAuten(datosentrada.getIndAuten());
			transferenciaAgendada.setTitular(datosentrada.getTitular());
			transferenciaAgendada.setTpoCtaFromBane(datosentrada.getTpoCtaFromBane());
			transferenciaAgendada.setSubcanalId(datosentrada.getSubcanalId());
			transferenciaAgendada.setCtaBane(datosentrada.getCtaBane());
			transferenciaAgendada.setUser(datosentrada.getUser());
			transferenciaAgendada.setPlazoAcreditacion(datosentrada.getPlazoAcreditacion());
			transferenciaAgendada.setDigitoCtaCredito(datosentrada.getDigitoCtaCredito());
			transferenciaAgendada.setUsuarioAtrib(datosentrada.getUsuarioAtrib());
			transferenciaAgendada.setFechaNac(datosentrada.getFechaNac());
			transferenciaAgendada.setTipoPersona(datosentrada.getTipoPersona());
			ConceptoTransferenciaEnum conceptoTransferenciaEnum = ConceptoTransferenciaEnum
					.getConceptoFromCodigo(datosentrada.getDescConcepto());
			transferenciaAgendada.setDescConcepto(conceptoTransferenciaEnum != null
					? conceptoTransferenciaEnum.getDescripcion() : datosentrada.getDescConcepto());
			transferenciaAgendada.setMarcaLimite(datosentrada.getMarcaLimite());
			transferenciaAgendada.setReferenciaUnivoca(datosentrada.getReferenciaUnivoca().substring(3));
			transferenciaAgendada.setIdentificBeneficiario(datosentrada.getIdentificBeneficiario());
			transferenciaAgendada.setFirmanteCtaDebito(datosentrada.getFirmanteCtaDebito());
			transferenciaAgendada.setCanalId(datosentrada.getCanalId());
			transferenciaAgendada.setCodConcepto(datosentrada.getCodConcepto());
			transferenciaAgendada.setSucCtaCredito(datosentrada.getSucCtaCredito());
			transferenciaAgendada.setBancoReceptor(datosentrada.getBancoReceptor());
			transferenciaAgendada.setiPMaquina(datosentrada.getiPMaquina());
			transferenciaAgendada.setPeriodica(datosentrada.getPeriodica());
			transferenciaAgendada.setNombreCtaCredito(datosentrada.getNombreCtaCredito());
			transferenciaAgendada.setEcoDatosEntrada(datosentrada.getEcoDatosEntrada());
			transferenciaAgendada.setNroCtaCredito(datosentrada.getNroCtaCredito());
			transferenciaAgendada.setSucCtaDebito(datosentrada.getSucCtaDebito());
			transferenciaAgendada.setTipoCtaDebito(datosentrada.getTipoCtaDebito());
			transferenciaAgendada.setCantidadDias(datosentrada.getCantidadDias());
			transferenciaAgendada.setEntidadCtaCredito(datosentrada.getEntidadCtaCredito());
			transferenciaAgendada.setBancoDestino(datosentrada.getBancoDestino());
			transferenciaAgendada.setImporteTransferencia(datosentrada.getImporteTransferencia());
			transferenciaAgendada.setTipoId(datosentrada.getTipoId());
			transferenciaAgendada.setCanalVersion(datosentrada.getCanalVersion());
			transferenciaAgendada.setIdCliente(datosentrada.getIdCliente());
			transferenciaAgendada.setTipoTransferencia(datosentrada.getTipoTransferencia());
			transferenciaAgendada.setVersionXML(datosentrada.getVersionXML());
			transferenciaAgendada.setTpoCtaToBane(datosentrada.getTpoCtaToBane());
		}
	}

	/**
	 * Obtener transferencia ejecutada.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param isRioRio
	 *            the is rio rio
	 * @return the transferencia ejecutada
	 */
	public static TransferenciaEjecutadaDTO obtenerTransferenciaEjecutada(XMLResponseEntity respuesta,
			Boolean isRioRio) {
		TransferenciaEjecutadaDTO transferenciaEjecutada = new TransferenciaEjecutadaDTO();
		// comun:
		transferenciaEjecutada.setSeveridad(respuesta.getDATOSRESULTADO().getSeveridad());
		transferenciaEjecutada.setCodRet(respuesta.getDATOSRESULTADO().getCodRet());
		transferenciaEjecutada.setNroOpCanal(respuesta.getDATOSRESULTADO().getNroOpCanal());
		transferenciaEjecutada.setCodRet(respuesta.getDATOSRESULTADO().getCodRet());
		transferenciaEjecutada.setIdTransaccion(respuesta.getDATOSRESULTADO().getIdTransaccion());
		transferenciaEjecutada.setIdRecibo(respuesta.getDATOSRESULTADO().getIdRecibo());
		String fechaFormateada = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (isRioRio) {
			transferenciaEjecutada.setCotizacion(respuesta.getDATOSRESULTADO().getCotizacion());
			transferenciaEjecutada.setImporteDestino(respuesta.getDATOSRESULTADO().getImporteDestino());
			transferenciaEjecutada.setComprobanteBackEnd(respuesta.getDATOSRESULTADO().getComprobanteBackEnd());
			transferenciaEjecutada.setImporteOrigen(respuesta.getDATOSRESULTADO().getImporteOrigen());
			fechaFormateada = sdf.format(new Date());
			transferenciaEjecutada.setFechaCompensacion(fechaFormateada);
		} else {
			fechaFormateada = respuesta.getDATOSRESULTADO().getFechaCompensacion().substring(0, 7);
			transferenciaEjecutada.setFechaCompensacion(fechaFormateada);
			transferenciaEjecutada.setNroComprobante(respuesta.getDATOSRESULTADO().getNroComprobante());
			transferenciaEjecutada.setNroSecuencia(respuesta.getDATOSRESULTADO().getNroSecuencia());
			transferenciaEjecutada.setImporteDebitado(respuesta.getDATOSRESULTADO().getImporteDebitado());
			transferenciaEjecutada.setCodigoEstado(respuesta.getDATOSRESULTADO().getCodigoEstado());
		}
		return transferenciaEjecutada;
	}

}
