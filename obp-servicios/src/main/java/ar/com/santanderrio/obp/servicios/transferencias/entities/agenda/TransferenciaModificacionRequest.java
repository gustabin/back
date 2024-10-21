/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.constants.SietePorVeintiCuatroConstant;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;;

/**
 * The Class TransferenciaModificacionRequest.
 */
public class TransferenciaModificacionRequest extends TransferenciaRequest {

	/**
	 * Instantiates a new transferencia modificacion request.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cliente
	 *            the cliente
	 * @param tipoDeAgendamiento
	 *            the tipo de agendamiento
	 * @param sesionParametros
	 *            the sesion parametros
	 * @param errorBanelcoCoelsaHabilitado
	 *            the errorBanelcoCoelsaHabilitado
	 */
	public TransferenciaModificacionRequest(TransferenciaAgendadaDTO transferenciaDTO, Cliente cliente,
			String tipoDeAgendamiento, SesionParametros sesionParametros, String errorBanelcoCoelsaHabilitado) {
		super(transferenciaDTO, cliente, tipoDeAgendamiento, sesionParametros, errorBanelcoCoelsaHabilitado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * TransferenciaRequest#armarRequestRioRio()
	 */
	public XMLRequestEntity armarRequestRioRio() {
		TransferenciaAgendada transferenciaAgendada = transferenciaAgendadaDTO.getDatosOrigen()
				.getTransferenciaAgendada();
		XMLRequestEntity request = setearDatosComunesRequest();
		request.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24_TX_BC_RIO);
		request.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24_110);
		request.getMETA().setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24_A);
		if (super.tipoDeAgendamiento.equals(TIPO_AGENDAMIENTO_RECORDATORIO)) {
			request.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24_I);
		} else {
			request.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24);
		}
		request.getMETA().setFechaEjecucion(
				ISBANStringUtils.formatearFecha(transferenciaAgendadaDTO.getFechaProgramada(), FORMATO_FECHA));
		request.getMETA().setNroRecurrencia(transferenciaAgendada.getNroRecurrencia());
		request.getMETA().setIdTransaccion(transferenciaAgendada.getIdTransaccion());

		request.getMETA().getUsuario().setUsuarioId(cliente.getUsuarioRacf());
		request.getMETA().getUsuario().setUsuarioPwd(cliente.getPasswordRacf());

		XMLRequestEntity.META.Recurrencias recurrencias = new XMLRequestEntity.META.Recurrencias();
		if (tipoDeAgendamiento.equals(TIPO_AGENDAMIENTO_RECORDATORIO)) {
			recurrencias.setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24);
		} else {
			recurrencias.setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24_E);
		}
		recurrencias.setFechaBaseRecurrencia(
				ISBANStringUtils.formatearFecha(transferenciaAgendadaDTO.getFechaProgramada(), FORMATO_FECHA));
		recurrencias.setFrecRecurrencia(obtenerCodigoFrecuenciaParaServicio7x24(
				transferenciaAgendadaDTO.getFrecuencia(), transferenciaAgendadaDTO.getFechaProgramada()));
		recurrencias.setMaxRecurrencia(SietePorVeintiCuatroConstant.MAX_RECURRENCIA_7x24);
		request.getMETA().setRecurrencias(recurrencias);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();

		parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaAgendada.getTipoCtaDebito(), 2, "0"));
		parametros.setSucCtaDebito(StringUtils.leftPad(transferenciaAgendada.getSucCtaDebito(), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(transferenciaAgendada.getNroCtaDebito(), 7, "0"));
		parametros.setImporteDebito(transferenciaAgendadaDTO.getImporte().toString());
		parametros.setTipoCtaCredito(StringUtils.leftPad(transferenciaAgendada.getTipoCtaCredito(), 2, "0"));
		parametros.setSucCtaCredito(StringUtils.leftPad(transferenciaAgendada.getSucCtaCredito(), 3, "0"));
		parametros.setNroCtaCredito(StringUtils.leftPad(transferenciaAgendada.getNroCtaCredito(), 7, "0"));
		parametros.setNombreCtaCredito(transferenciaAgendada.getNombreCtaCredito());
		parametros.setCotizacionTransferencia(transferenciaAgendada.getCotizacionTransferencia());
		parametros.setImporteCredito("0");
		parametros.setNroComprobante(transferenciaAgendada.getNroComprobante());
		parametros.setIndicadorAfectarDisponible(transferenciaAgendada.getIndicadorAfectarDisponible());
		parametros.setIndicadorLimiteMax(SietePorVeintiCuatroConstant.INDICADOR_LIMITE_MAX);
		parametros.setCodigoConcepto(transferenciaAgendadaDTO.getConcepto().getCodigo());
		parametros.setDescripcionConcepto(transferenciaAgendadaDTO.getReferencia());
		parametros.setCuentaPropia(transferenciaAgendada.getCuentaPropia());
		datosentrada.setParametros(parametros);

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();
		datosMensAvisos.setTitularDebito(transferenciaAgendada.getTitularDebito());
		datosMensAvisos.setTitularCredito(transferenciaAgendada.getTitularCredito());
		datosMensAvisos.setMailCredito(transferenciaAgendadaDTO.getEmail());
		datosMensAvisos.setComentario(transferenciaAgendadaDTO.getEmailMensaje());
		datosMensAvisos.setConcepto(transferenciaAgendadaDTO.getConcepto().getCodigo());
		datosMensAvisos.setInfoAdicional(transferenciaAgendadaDTO.getReferencia());
		datosMensAvisos.setMailClienteReply(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional1(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional1(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional2(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional2(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional3(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional3(STRING_VACIO);
		datosMensAvisos.setAnotacionesPersonales(STRING_VACIO);
		datosAdicionales.setDatosMensAvisos(datosMensAvisos);

		request.setDATOSENTRADA(datosentrada);
		request.setDatosAdicionales(datosAdicionales);

		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * TransferenciaRequest#armarRequestOtrosBancos()
	 */
	public XMLRequestEntity armarRequestOtrosBancos() {
		TransferenciaAgendada transferenciaAgendada = transferenciaAgendadaDTO.getDatosOrigen()
				.getTransferenciaAgendada();
		XMLRequestEntity xmlRequest = setearDatosComunesRequest();

		xmlRequest.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24_TX_OTROSBANCOS);
		xmlRequest.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24_130);
		xmlRequest.getMETA().setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24_A);
		xmlRequest.getMETA().setFechaEjecucion(
				ISBANStringUtils.formatearFecha(transferenciaAgendadaDTO.getFechaProgramada(), FORMATO_FECHA));
		xmlRequest.getMETA().setNroRecurrencia(transferenciaAgendada.getNroRecurrencia());
		xmlRequest.getMETA().setIdTransaccion(transferenciaAgendada.getIdTransaccion());
		if (tipoDeAgendamiento.equals(TIPO_AGENDAMIENTO_RECORDATORIO)) {
			xmlRequest.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24_I);
		} else {
			xmlRequest.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24);
		}

		xmlRequest.getMETA().setRecurrencias(new XMLRequestEntity.META.Recurrencias());
		if (tipoDeAgendamiento.equals(TIPO_AGENDAMIENTO_RECORDATORIO)) {
			xmlRequest.getMETA().getRecurrencias()
					.setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24);
		} else {
			xmlRequest.getMETA().getRecurrencias()
					.setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24_E);
		}
		xmlRequest.getMETA().getRecurrencias().setFechaBaseRecurrencia(
				ISBANStringUtils.formatearFecha(transferenciaAgendadaDTO.getFechaProgramada(), FORMATO_FECHA));
		xmlRequest.getMETA().getRecurrencias().setFrecRecurrencia(obtenerCodigoFrecuenciaParaServicio7x24(
				transferenciaAgendadaDTO.getFrecuencia(), transferenciaAgendadaDTO.getFechaProgramada()));
		xmlRequest.getMETA().getRecurrencias().setMaxRecurrencia(SietePorVeintiCuatroConstant.MAX_RECURRENCIA_7x24);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		parametros.setIndicadorFuncion(SietePorVeintiCuatroConstant.INDICADOR_FUNCION_OB);

		parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaAgendada.getTipoCtaDebito(), 2, "0"));
		parametros.setSucCtaDebito(StringUtils.leftPad(transferenciaAgendada.getSucCtaDebito(), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(transferenciaAgendada.getNroCtaDebito(), 7, "0"));
		parametros.setFirmanteCtaDebito(transferenciaAgendada.getFirmanteCtaDebito());
		parametros.setTipoTransferencia(transferenciaAgendada.getTipoTransferencia());
		parametros.setReferenciaUnivoca(transferenciaAgendadaDTO.getConcepto().getCodigo()
				.concat(transferenciaAgendadaDTO.getDescFromConcepto()));

		parametros.setImporteTransferencia(transferenciaAgendadaDTO.getImporte().toString());
		parametros.setMonedaTransferencia(transferenciaAgendada.getMonedaTransferencia());
		parametros.setInformacDiscrecional(SietePorVeintiCuatroConstant.INFORMACION_DISCRECIONAL);

		parametros.setIdentificBeneficiario(transferenciaAgendada.getIdentificBeneficiario());
		parametros.setEntidadCtaCredito(StringUtils.leftPad(transferenciaAgendada.getEntidadCtaCredito(), 3, "0"));
		parametros.setSucCtaCredito(StringUtils.leftPad(transferenciaAgendada.getSucCtaCredito(), 4, "0"));
		parametros.setDigitoCtaCredito(transferenciaAgendada.getDigitoCtaCredito());
		parametros.setNroCtaCredito(transferenciaAgendada.getNroCtaCredito());
		parametros.setNombreCtaCredito(transferenciaAgendada.getNombreCtaCredito());
		parametros.setCaracteristicaCtaCredito(
				StringUtils.leftPad(transferenciaAgendada.getCaracteristicaCtaCredito(), 2, "0"));
		parametros.setMarcaLimite(transferenciaAgendada.getMarcaLimite());
		parametros.setMarcaGravado(transferenciaAgendada.getMarcaGravado());
		parametros.setIndicadorFuncion(transferenciaAgendada.getIndicadorFuncion());
		parametros.setPlazoAcreditacion(StringUtils.leftPad(transferenciaAgendada.getPlazoAcreditacion(), 3, "0"));
		parametros.setPeriodica(transferenciaAgendada.getPeriodica());
		parametros.setCantidadDias(transferenciaAgendada.getCantidadDias());
		parametros.setiPMaquina(transferenciaAgendada.getiPMaquina());
		parametros.setTitular(transferenciaAgendada.getTitular());
		parametros.setCuit1(transferenciaAgendada.getCuit1());
		parametros.setCuit2(transferenciaAgendada.getCuit2());
		parametros.setCuit3(transferenciaAgendada.getCuit3());
		parametros.setLongCtaDesBane(transferenciaAgendada.getLongCtaDesBane());
		parametros.setCtaBane(transferenciaAgendada.getCtaBane());
		parametros.setTpoCtaToBane(transferenciaAgendada.getTpoCtaToBane());
		parametros.setTpoCtaFromBane(transferenciaAgendada.getTpoCtaFromBane());
		parametros.setCbu(transferenciaAgendada.getCbu());
		parametros.setFiid(transferenciaAgendada.getFiid());
		parametros.setUser(transferenciaAgendada.getUser());
		parametros.setBancoReceptor(transferenciaAgendada.getBancoReceptor());
		parametros.setCodConcepto(transferenciaAgendadaDTO.getConcepto().getOrdinal());
		parametros.setDescConcepto(transferenciaAgendadaDTO.getConcepto().getCodigo());
		parametros.setBancoDestino(transferenciaAgendada.getBancoDestino());

		// // si no tiene fiid va vacio
		// if (transferenciaAgendadaDTO.getFiid() == null) {
		// parametros.setCuit2(STRING_VACIO_11);
		// parametros.setCuit3(STRING_VACIO_11);
		// parametros.setLongCtaDesBane(STRING_VACIO_LONGITUD_2);
		// parametros.setCtaBane(STRING_VACIO_LONGITUD_28);
		// parametros.setTpoCtaToBane(STRING_VACIO_LONGITUD_2);
		// parametros.setTpoCtaFromBane(STRING_VACIO_LONGITUD_2);
		// parametros.setFiid(STRING_VACIO_LONGITUD_4);
		// parametros.setUser(STRING_VACIO_LONGITUD_7);
		// parametros.setBancoReceptor(STRING_VACIO_LONGITUD_4);
		// parametros.setBancoDestino(transferenciaAgendadaDTO.getBancoDestino());
		//
		// } else {
		// parametros.setCuit2(transferenciaAgendadaDTO.getCuit2().replace(STRING_VACIO_LONGITUD_1,
		// STRING_VACIO));
		// parametros.setCuit3(transferenciaAgendadaDTO.getCuit3().replace(STRING_VACIO_LONGITUD_1,
		// STRING_VACIO));
		// parametros.setLongCtaDesBane(String.valueOf(transferenciaAgendadaDTO.getCuentaDestinoBanelco().length()));
		// parametros.setCtaBane(transferenciaAgendadaDTO.getCuentaDestinoBanelco());
		// parametros.setTpoCtaToBane(transferenciaAgendadaDTO.getTipoDeCuentaToBanelco().getCodigoTrfcci());
		// parametros.setTpoCtaFromBane(transferenciaAgendadaDTO.getTipoDeCuentaFromBanelco().getCodigoTrfcci());
		// parametros.setFiid(transferenciaAgendadaDTO.getFiid());
		// parametros.setUser(transferenciaAgendadaDTO.getUser());
		// parametros.setBancoReceptor(transferenciaAgendadaDTO.getBancoReceptor());
		// }

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();
		datosMensAvisos.setTitularDebito(transferenciaAgendada.getTitularDebito());
		datosMensAvisos.setTitularCredito(transferenciaAgendada.getTitularCredito());
		datosMensAvisos.setMailCredito(transferenciaAgendadaDTO.getEmail());
		datosMensAvisos.setComentario(transferenciaAgendadaDTO.getEmailMensaje());
		datosMensAvisos.setConcepto(StringUtils.leftPad(transferenciaAgendadaDTO.getConcepto().getCodigo(),LONGITUD_MAX_CONCEPTO,VACIO_LONGITUD_UNO));
		datosMensAvisos.setInfoAdicional(transferenciaAgendadaDTO.getReferencia());
		datosMensAvisos.setMailClienteReply(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional1(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional1(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional2(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional2(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional3(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional3(STRING_VACIO);
		datosMensAvisos.setAnotacionesPersonales(STRING_VACIO);
		datosAdicionales.setDatosMensAvisos(datosMensAvisos);

		datosentrada.setParametros(parametros);
		xmlRequest.setDatosAdicionales(datosAdicionales);
		xmlRequest.setDATOSENTRADA(datosentrada);

		return xmlRequest;
	}

}
