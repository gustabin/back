/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.constants.SietePorVeintiCuatroConstant;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;

/**
 * The Class TransferenciaAgendadaCreacionRequest.
 */
public class TransferenciaAgendadaCreacionRequest extends TransferenciaRequest {

	/**
	 * Instantiates a new transferencia agendada creacion request.
	 *
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @param cliente
	 *            the cliente
	 * @param tipoDeAgendamiento
	 *            the tipo de agendamiento
	 * @param sesionParametros
	 *            the sesion parametros
	 * @param errorBanelcoCoelsaHabilitado
	 *            the errorBanelcoCoelsaHabilitado
	 */
	public TransferenciaAgendadaCreacionRequest(TransferenciaAgendadaDTO transferenciaAgendadaDTO, Cliente cliente,
			String tipoDeAgendamiento, SesionParametros sesionParametros, String errorBanelcoCoelsaHabilitado) {
		super(transferenciaAgendadaDTO, cliente, tipoDeAgendamiento, sesionParametros, errorBanelcoCoelsaHabilitado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * TransferenciaRequest#armarRequestRioRio()
	 */
	@Override
	public XMLRequestEntity armarRequestRioRio() {
		TransferenciaAgendada transferenciaAgendada = transferenciaAgendadaDTO.getDatosOrigen()
				.getTransferenciaAgendada();

		XMLRequestEntity request = setearDatosComunesRequest();

		request.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24_TX_BC_RIO);
		request.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24_110);
		request.getMETA().setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24_IA);
		request.getMETA().setNroRecurrencia(transferenciaAgendada.getNroRecurrencia());
		request.getMETA().setIdTransaccion(transferenciaAgendada.getIdTransaccion());
		request.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24);
		request.getMETA().setFechaEjecucion(ISBANStringUtils.formatearFecha(new Date(), FORMATO_FECHA));

		XMLRequestEntity.META.Recurrencias recurrencias = new XMLRequestEntity.META.Recurrencias();

		recurrencias.setTipoRecurrencia(transferenciaAgendada.getTipoRecurrencia());
		recurrencias.setFechaBaseRecurrencia(transferenciaAgendada.getFechaBaseRecurrencia());
		recurrencias.setFrecRecurrencia(transferenciaAgendada.getFrecRecurrencia());
		recurrencias.setMaxRecurrencia(transferenciaAgendada.getMaxRecurrencia());
		request.getMETA().setRecurrencias(recurrencias);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaAgendada.getTipoCtaDebito(), 2, "0"));
		parametros.setSucCtaDebito(StringUtils.leftPad(transferenciaAgendada.getSucCtaDebito(), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(transferenciaAgendada.getNroCtaDebito(), 7, "0"));
		parametros.setImporteDebito(TransferenciaUtil.formatearCualquierImporteABigDecimal(transferenciaAgendadaDTO.getImporte().toString()));
		parametros.setTipoCtaCredito(StringUtils.leftPad(transferenciaAgendada.getTipoCtaCredito(), 2, "0"));
		parametros.setSucCtaCredito(StringUtils.leftPad(transferenciaAgendada.getSucCtaCredito(), 3, "0"));
		parametros.setNroCtaCredito(StringUtils.leftPad(transferenciaAgendada.getNroCtaCredito(), 7, "0"));
		parametros.setNombreCtaCredito(transferenciaAgendada.getNombreCtaCredito());
		parametros.setCotizacionTransferencia(transferenciaAgendada.getCotizacionTransferencia());
		parametros.setImporteCredito(SietePorVeintiCuatroConstant.IMPORTE_CREDITO_7X24);
		parametros.setNroComprobante(transferenciaAgendada.getNroComprobante());
		parametros.setIndicadorAfectarDisponible(transferenciaAgendada.getIndicadorAfectarDisponible());
		parametros.setCodigoConcepto(transferenciaAgendadaDTO.getConcepto().getCodigo());
		parametros.setDescripcionConcepto(transferenciaAgendadaDTO.getReferencia());
		parametros.setCuentaPropia(transferenciaAgendada.getCuentaPropia());
		parametros.setIndicadorLimiteMax("");
		parametros.setIndTransfDiferida(
				transferenciaAgendadaDTO.getPlazoAcreditacion() == PlazoAcreditacion.PLAZO_48_HS ? "S" : "N");
		datosentrada.setParametros(parametros);

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();

		datosMensAvisos.setTitularDebito(transferenciaAgendada.getTitularDebito());
		datosMensAvisos.setTitularCredito(transferenciaAgendada.getTitularCredito());

		if (transferenciaAgendadaDTO.getEmail() == null) {
			datosMensAvisos.setMailCredito("");
		} else {
			datosMensAvisos.setMailCredito(transferenciaAgendadaDTO.getEmail());
		}
		if (transferenciaAgendadaDTO.getEmailMensaje() == null) {
			datosMensAvisos.setComentario("");
		} else {
			datosMensAvisos.setComentario(transferenciaAgendadaDTO.getEmailMensaje());
		}

		datosMensAvisos.setMailClienteReply(transferenciaAgendada.getMailClienteReply());
		datosMensAvisos.setConceptoAdicional1(transferenciaAgendada.getConceptoAdicional1());
		datosMensAvisos.setConceptoAdicional2(transferenciaAgendada.getConceptoAdicional2());
		datosMensAvisos.setConceptoAdicional3(transferenciaAgendada.getConceptoAdicional3());
		datosMensAvisos.setDescripcionAdicional1(transferenciaAgendada.getDescripcionAdicional1());
		datosMensAvisos.setDescripcionAdicional2(transferenciaAgendada.getDescripcionAdicional2());
		datosMensAvisos.setDescripcionAdicional3(transferenciaAgendada.getDescripcionAdicional3());
		datosMensAvisos.setAnotacionesPersonales(transferenciaAgendada.getAnotacionesPersonales());
		datosMensAvisos.setConcepto(transferenciaAgendadaDTO.getConcepto().getCodigo());
		datosMensAvisos.setInfoAdicional(transferenciaAgendadaDTO.getReferencia());
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
	@Override
	public XMLRequestEntity armarRequestOtrosBancos() throws NullPointerException {
		TransferenciaAgendada transferenciaAgendada = transferenciaAgendadaDTO.getDatosOrigen()
				.getTransferenciaAgendada();

		XMLRequestEntity request = setearDatosComunesRequest();
		request.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24_TX_OTROSBANCOS);
		request.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24_130);
		request.getMETA().setIndAuten(SietePorVeintiCuatroConstant.IND_AUTENT_7X24);
		request.getMETA().setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24_IA);
		request.getMETA().setNroRecurrencia(transferenciaAgendada.getNroRecurrencia());
		request.getMETA().setIdTransaccion(transferenciaAgendada.getIdTransaccion());
		request.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24);
		request.getMETA().setFechaEjecucion(ISBANStringUtils.formatearFecha(new Date(), FORMATO_FECHA));

		XMLRequestEntity.META.Recurrencias recurrencias = new XMLRequestEntity.META.Recurrencias();
		recurrencias.setTipoRecurrencia(transferenciaAgendada.getTipoRecurrencia());
		recurrencias.setFechaBaseRecurrencia(transferenciaAgendada.getFechaBaseRecurrencia());
		recurrencias.setFrecRecurrencia(transferenciaAgendada.getFrecRecurrencia());
		recurrencias.setMaxRecurrencia(transferenciaAgendada.getMaxRecurrencia());
		request.getMETA().setRecurrencias(recurrencias);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();

		parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaAgendada.getTipoCtaDebito(), 2, "0"));
		parametros.setSucCtaDebito(StringUtils.leftPad(transferenciaAgendada.getSucCtaDebito(), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(transferenciaAgendada.getNroCtaDebito(), 7, "0"));
		parametros.setFirmanteCtaDebito(transferenciaAgendada.getFirmanteCtaDebito()); // en
																						// prod
																						// son
																						// 2
																						// digitos
																						// aca
																						// va
																						// uno
																						// ¿?
		parametros.setTipoTransferencia(transferenciaAgendada.getTipoTransferencia());
		parametros.setReferenciaUnivoca(transferenciaAgendadaDTO.getConcepto().getCodigo()
		        .concat(transferenciaAgendadaDTO.getConcepto().getDescripcionAbreviada()));
		parametros.setImporteTransferencia(TransferenciaUtil.formatearCualquierImporteABigDecimal(transferenciaAgendadaDTO.getImporte().toString()));
		parametros.setMonedaTransferencia(transferenciaAgendada.getMonedaTransferencia());
		parametros.setInformacDiscrecional(transferenciaAgendada.getInformacDiscrecional());
		parametros.setIdentificBeneficiario(transferenciaAgendada.getIdentificBeneficiario());
		parametros.setEntidadCtaCredito(StringUtils.leftPad(transferenciaAgendada.getEntidadCtaCredito(), 4, "0")); // emilio
		parametros.setSucCtaCredito(StringUtils.leftPad(transferenciaAgendada.getSucCtaCredito(), 4, "0"));
		parametros.setNroCtaCredito(StringUtils.leftPad(transferenciaAgendada.getNroCtaCredito(), 17, "0")); // emilio
		parametros.setNombreCtaCredito(transferenciaAgendada.getNombreCtaCredito());
		parametros.setDigitoCtaCredito(transferenciaAgendada.getDigitoCtaCredito());
		parametros.setCaracteristicaCtaCredito(
				StringUtils.leftPad(transferenciaAgendada.getCaracteristicaCtaCredito(), 2, "0"));
		parametros.setMarcaLimite(transferenciaAgendada.getMarcaLimite());
		parametros.setMarcaGravado(transferenciaAgendada.getMarcaGravado());
		parametros.setIndicadorFuncion(transferenciaAgendada.getIndicadorFuncion());
		parametros.setPlazoAcreditacion(StringUtils.leftPad(transferenciaAgendada.getPlazoAcreditacion(), 3, "0"));
		parametros.setPeriodica(transferenciaAgendada.getPeriodica());
		parametros.setCantidadDias(SietePorVeintiCuatroConstant.CANTIDAD_DIAS); // emilio
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
		parametros.setFiid(StringUtils.leftPad(StringUtils.defaultString(transferenciaAgendada.getFiid()), 4, STRING_VACIO));
		parametros.setUser(StringUtils.leftPad(StringUtils.defaultString(transferenciaAgendada.getUser()), 7, STRING_VACIO));
		parametros.setBancoReceptor(transferenciaAgendada.getBancoReceptor());
		parametros.setCodConcepto(transferenciaAgendadaDTO.getConcepto().getOrdinal());
		parametros.setDescConcepto(transferenciaAgendadaDTO.getConcepto().getCodigo());
		parametros.setBancoDestino(transferenciaAgendada.getBancoDestino());

		datosentrada.setParametros(parametros);

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();

		datosMensAvisos.setTitularDebito(transferenciaAgendada.getTitularDebito());
		datosMensAvisos.setTitularCredito(transferenciaAgendada.getTitularCredito());
		if (transferenciaAgendadaDTO.getEmail() == null) {
			datosMensAvisos.setMailCredito("");
		} else {
			datosMensAvisos.setMailCredito(transferenciaAgendadaDTO.getEmail());
		}
		if (transferenciaAgendadaDTO.getEmailMensaje() == null) {
			datosMensAvisos.setComentario("");
		} else {
			datosMensAvisos.setComentario(transferenciaAgendadaDTO.getEmailMensaje());
		}
		datosMensAvisos.setMailClienteReply(transferenciaAgendada.getMailClienteReply());
		datosMensAvisos.setConceptoAdicional1(transferenciaAgendada.getConceptoAdicional1());
		datosMensAvisos.setConceptoAdicional2(transferenciaAgendada.getConceptoAdicional2());
		datosMensAvisos.setConceptoAdicional3(transferenciaAgendada.getConceptoAdicional3());
		datosMensAvisos.setDescripcionAdicional1(transferenciaAgendada.getDescripcionAdicional1());
		datosMensAvisos.setDescripcionAdicional2(transferenciaAgendada.getDescripcionAdicional2());
		datosMensAvisos.setDescripcionAdicional3(transferenciaAgendada.getDescripcionAdicional3());
		datosMensAvisos.setAnotacionesPersonales(transferenciaAgendada.getAnotacionesPersonales());
		datosMensAvisos.setConcepto(StringUtils.leftPad(transferenciaAgendadaDTO.getConcepto().getCodigo(),LONGITUD_MAX_CONCEPTO,VACIO_LONGITUD_UNO));
		datosMensAvisos.setInfoAdicional(transferenciaAgendadaDTO.getReferencia());

		datosAdicionales.setDatosMensAvisos(datosMensAvisos);

		request.setDATOSENTRADA(datosentrada);
		request.setDatosAdicionales(datosAdicionales);

		return request;
	}

}
