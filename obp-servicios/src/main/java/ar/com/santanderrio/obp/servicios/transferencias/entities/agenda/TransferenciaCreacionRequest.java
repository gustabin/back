/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.constants.SietePorVeintiCuatroConstant;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;

/**
 * The Class TransferenciaCreacionRequest.
 */
public class TransferenciaCreacionRequest extends TransferenciaRequest {

	/** The Constant PESO. */
	private static final String PESO = "peso";

	/**
	 * Instantiates a new transferencia creacion request.
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
	public TransferenciaCreacionRequest(TransferenciaAgendadaDTO transferenciaDTO, Cliente cliente,
			String tipoDeAgendamiento, SesionParametros sesionParametros, String errorBanelcoCoelsaHabilitado) {
		super(transferenciaDTO, cliente, tipoDeAgendamiento, sesionParametros, errorBanelcoCoelsaHabilitado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * TransferenciaRequest#armarRequestRioRio()
	 */
	@Override
	public XMLRequestEntity armarRequestRioRio() {

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
		Cuenta cuentaOrigen = transferenciaAgendadaDTO.getCuentaOrigen();
		String nroSucursal = transferenciaAgendadaDTO.getCuentaOrigen().getNroSucursal();
		String nroCuentaProduxcto = transferenciaAgendadaDTO.getCuentaOrigen().getNroCuentaProducto();
		if (cuentaOrigen.getTipoCuenta().equals(TipoCuenta.CUENTA_UNICA)) {
			TipoCuenta tipoCuenta = TransferenciaUtil
					.matchearTipoCuentaDestino(transferenciaAgendadaDTO.getMoneda().getMoneda());
			parametros.setTipoCtaDebito(StringUtils.leftPad(tipoCuenta.getCodigo().toString(), 2, "0"));
		} else {
			parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaAgendadaDTO.getTipoCuentaOrigen(), 2, "0"));
		}
		parametros.setSucCtaDebito(
				StringUtils.leftPad(nroSucursal.substring(nroSucursal.length() - 3, nroSucursal.length()), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(
				nroCuentaProduxcto.substring(nroCuentaProduxcto.length() - 7, nroCuentaProduxcto.length()), 7, "0"));
		parametros.setImporteDebito(transferenciaAgendadaDTO.getImporte().toString());
		parametros.setTipoCtaCredito(
				StringUtils.leftPad(transferenciaAgendadaDTO.getTipoCuentaDestino().getCodigo().toString(), 2, "0"));
		parametros.setSucCtaCredito(
				StringUtils.leftPad(transferenciaAgendadaDTO.getNumeroCuentaDestino().getNroSucursal(), 3, "0"));
		parametros.setNroCtaCredito(
				StringUtils.leftPad(transferenciaAgendadaDTO.getNumeroCuentaDestino().getNroCuentaProducto(), 7, "0"));
		parametros.setNombreCtaCredito(transferenciaAgendadaDTO.getNombreReceptor());
		parametros.setCotizacionTransferencia(SietePorVeintiCuatroConstant.COTIZACION_TRANSFEERENCIA);
		parametros.setImporteCredito(SietePorVeintiCuatroConstant.IMPORTE_CREDITO_7X24);
		parametros.setNroComprobante(SietePorVeintiCuatroConstant.NRO_COMPROBANTE);
		parametros.setIndicadorAfectarDisponible(SietePorVeintiCuatroConstant.INDICADOR_AFECTAR_DISPONIBLE);
		parametros.setIndicadorLimiteMax(SietePorVeintiCuatroConstant.INDICADOR_LIMITE_MAX);
		parametros.setCodigoConcepto(transferenciaAgendadaDTO.getConcepto().getCodigo());
		parametros.setDescripcionConcepto(transferenciaAgendadaDTO.getReferencia());
		parametros.setCuentaPropia(transferenciaAgendadaDTO.isHaciaCuentaPropia() == true ? "S" : "N");
		datosentrada.setParametros(parametros);

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();
		datosMensAvisos.setTitularDebito(cliente.getNombre() + STRING_VACIO_LONGITUD_1 + cliente.getApellido1());
		datosMensAvisos.setTitularCredito(transferenciaAgendadaDTO.getNombreReceptor());
		datosMensAvisos.setMailCredito(transferenciaAgendadaDTO.getEmail());
		datosMensAvisos.setComentario(transferenciaAgendadaDTO.getComentario());
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
	@Override
	public XMLRequestEntity armarRequestOtrosBancos() {
		XMLRequestEntity xmlRequest = setearDatosComunesRequest();

		xmlRequest.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24_TX_OTROSBANCOS);
		xmlRequest.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24_130);
		xmlRequest.getMETA().setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24_A);
		xmlRequest.getMETA().setFechaEjecucion(
				ISBANStringUtils.formatearFecha(transferenciaAgendadaDTO.getFechaProgramada(), FORMATO_FECHA));
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
		xmlRequest.getMETA().getRecurrencias().setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24);
		xmlRequest.getMETA().getRecurrencias().setFechaBaseRecurrencia(
				ISBANStringUtils.formatearFecha(transferenciaAgendadaDTO.getFechaProgramada(), FORMATO_FECHA));
		xmlRequest.getMETA().getRecurrencias().setFrecRecurrencia(obtenerCodigoFrecuenciaParaServicio7x24(
				transferenciaAgendadaDTO.getFrecuencia(), transferenciaAgendadaDTO.getFechaProgramada()));
		xmlRequest.getMETA().getRecurrencias().setMaxRecurrencia(SietePorVeintiCuatroConstant.MAX_RECURRENCIA_7x24);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		parametros.setIndicadorFuncion(SietePorVeintiCuatroConstant.INDICADOR_FUNCION_OB);
		Cuenta cuentaOrigen = transferenciaAgendadaDTO.getCuentaOrigen();
		String nroSucursal = transferenciaAgendadaDTO.getCuentaOrigen().getNroSucursal();
		String nroCuentaProduxcto = transferenciaAgendadaDTO.getCuentaOrigen().getNroCuentaProducto();
		if (cuentaOrigen.getTipoCuenta().equals(TipoCuenta.CUENTA_UNICA)) {
			TipoCuenta tipoCuenta = TransferenciaUtil
					.matchearTipoCuentaDestino(transferenciaAgendadaDTO.getMoneda().getMoneda());
			parametros.setTipoCtaDebito(StringUtils.leftPad(tipoCuenta.getCodigo().toString(), 2, "0"));
		} else {
			parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaAgendadaDTO.getTipoCuentaOrigen(), 2, "0"));
		}
		parametros.setSucCtaDebito(
				StringUtils.leftPad(nroSucursal.substring(nroSucursal.length() - 3, nroSucursal.length()), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(
				nroCuentaProduxcto.substring(nroCuentaProduxcto.length() - 7, nroCuentaProduxcto.length()), 7, "0"));
		parametros.setFirmanteCtaDebito(transferenciaAgendadaDTO.getCuentaOrigen().getNroOrdenFirmante());
		parametros.setTipoTransferencia(SietePorVeintiCuatroConstant.TIPO_TRANSFERENCIA);
		parametros.setReferenciaUnivoca(transferenciaAgendadaDTO.getConcepto().getCodigo()
				.concat(transferenciaAgendadaDTO.getDescFromConcepto()));

		parametros.setImporteTransferencia(transferenciaAgendadaDTO.getImporte().toString());
		parametros.setMonedaTransferencia(PESO.equals(transferenciaAgendadaDTO.getMoneda().getMoneda()) ? "0" : "1");
		parametros.setInformacDiscrecional(SietePorVeintiCuatroConstant.INFORMACION_DISCRECIONAL);

		parametros.setIdentificBeneficiario(transferenciaAgendadaDTO.getCuit().replace("-", ""));
		parametros.setEntidadCtaCredito("0".concat(transferenciaAgendadaDTO.getCbuCuenta().substring(0, 3)));
		parametros.setSucCtaCredito(transferenciaAgendadaDTO.getCbuCuenta().substring(3, 7));
		parametros.setDigitoCtaCredito(transferenciaAgendadaDTO.getCbuCuenta().substring(7, 8));
		parametros.setNroCtaCredito("000".concat(transferenciaAgendadaDTO.getCbuCuenta().substring(8,
				transferenciaAgendadaDTO.getCbuCuenta().length())));
		parametros.setNombreCtaCredito(transferenciaAgendadaDTO.getTitular());
		parametros.setCaracteristicaCtaCredito(SietePorVeintiCuatroConstant.CARACTERISTICA_CUENTA_CREDITO);
		parametros.setMarcaLimite(SietePorVeintiCuatroConstant.MARCAR_LIMITE);
		parametros.setMarcaGravado(
				transferenciaAgendadaDTO.getTipoCuentaDestino() == TipoCuenta.CUENTA_CORRIENTE_DOLARES ? "S" : "N");
		if ("1".equals(errorBanelcoCoelsaHabilitado.trim())) {
			parametros.setPlazoAcreditacion(
					transferenciaAgendadaDTO.isVaPorCoelsa() ? PlazoAcreditacion.INMEDIATO_COELSA.getCodigoTrfcci()
							: PlazoAcreditacion.INMEDIATO.getCodigoTrfcci());
		} else {
			if (ISBANStringUtils.validarCVU(transferenciaAgendadaDTO.getCbuCuenta())) {
				parametros.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO_COELSA.getCodigoTrfcci());
			} else {
				parametros.setPlazoAcreditacion(
						transferenciaAgendadaDTO.isVaPorCoelsa() ? PlazoAcreditacion.PLAZO_24_HS.getCodigoTrfcci()
								: PlazoAcreditacion.INMEDIATO.getCodigoTrfcci());
			}
		}
		parametros.setPeriodica(SietePorVeintiCuatroConstant.PERIODICA);
		parametros.setCantidadDias(SietePorVeintiCuatroConstant.CANTIDAD_DIAS);
		parametros.setiPMaquina(sesionParametros.getRegistroSession().getIp());
		parametros.setTitular(transferenciaAgendadaDTO.getTitular());

		parametros.setCuit1(transferenciaAgendadaDTO.getCuit().replace("-", ""));
		parametros.setCbu(transferenciaAgendadaDTO.getCbuCuenta());
		parametros.setCodConcepto(transferenciaAgendadaDTO.getConcepto().getOrdinal());
		parametros.setDescConcepto(transferenciaAgendadaDTO.getConcepto().getCodigo());

		// si no tiene fiid va vacio
		if (transferenciaAgendadaDTO.getFiid() == null) {
			parametros.setCuit2(STRING_VACIO_11);
			parametros.setCuit3(STRING_VACIO_11);
			parametros.setLongCtaDesBane(STRING_VACIO_LONGITUD_2);
			parametros.setCtaBane(STRING_VACIO_LONGITUD_28);
			parametros.setTpoCtaToBane(STRING_VACIO_LONGITUD_2);
			parametros.setTpoCtaFromBane(STRING_VACIO_LONGITUD_2);
			parametros.setFiid(STRING_VACIO_LONGITUD_4);
			parametros.setUser(STRING_VACIO_LONGITUD_7);
			parametros.setBancoReceptor(STRING_VACIO_LONGITUD_4);
			parametros.setBancoDestino(transferenciaAgendadaDTO.getBancoDestino());

		} else {
			parametros.setCuit2(transferenciaAgendadaDTO.getCuit2().replace(STRING_VACIO_LONGITUD_1, STRING_VACIO));
			parametros.setCuit3(transferenciaAgendadaDTO.getCuit3().replace(STRING_VACIO_LONGITUD_1, STRING_VACIO));
			parametros.setLongCtaDesBane(String.valueOf(transferenciaAgendadaDTO.getCuentaDestinoBanelco().length()));
			parametros.setCtaBane(transferenciaAgendadaDTO.getCuentaDestinoBanelco());
			parametros.setTpoCtaToBane(transferenciaAgendadaDTO.getTipoDeCuentaToBanelco().getCodigoTrfcci());
			parametros.setTpoCtaFromBane(transferenciaAgendadaDTO.getTipoDeCuentaFromBanelco().getCodigoTrfcci());
			parametros.setFiid(StringUtils.leftPad(transferenciaAgendadaDTO.getFiid(), 4, " "));
			parametros.setUser(StringUtils.leftPad(transferenciaAgendadaDTO.getUser(), 7, " "));
			parametros.setBancoReceptor(transferenciaAgendadaDTO.getBancoReceptor());
			parametros.setBancoDestino(transferenciaAgendadaDTO.getBancoDestino());
		}

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();
		datosMensAvisos
				.setTitularDebito(this.cliente.getNombre() + STRING_VACIO_LONGITUD_1 + this.cliente.getApellido1());
		datosMensAvisos.setMailCredito(transferenciaAgendadaDTO.getEmail());
		datosMensAvisos.setComentario(transferenciaAgendadaDTO.getComentario());
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
		datosMensAvisos.setTitularCredito(STRING_VACIO);
		datosAdicionales.setDatosMensAvisos(datosMensAvisos);

		datosentrada.setParametros(parametros);
		xmlRequest.setDATOSENTRADA(datosentrada);

		return xmlRequest;
	}

}
