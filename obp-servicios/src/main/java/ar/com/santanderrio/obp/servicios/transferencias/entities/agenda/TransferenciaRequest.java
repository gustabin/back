/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.constants.SietePorVeintiCuatroConstant;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;

/**
 * The Class TransferenciaRequest.
 */
public abstract class TransferenciaRequest {

	/** The Constant STRING_VACIO. */
	protected static final String STRING_VACIO = "";

	/** The Constant STRING_VACIO_LONGITUD_1. */
	protected static final String STRING_VACIO_LONGITUD_1 = " ";

	/** The Constant STRING_VACIO_LONGITUD_2. */
	protected static final String STRING_VACIO_LONGITUD_2 = "  ";

	/** The Constant STRING_VACIO_LONGITUD_4. */
	protected static final String STRING_VACIO_LONGITUD_4 = "    ";

	/** The Constant STRING_VACIO_LONGITUD_7. */
	protected static final String STRING_VACIO_LONGITUD_7 = "       ";

	/** The Constant STRING_VACIO_11. */
	protected static final String STRING_VACIO_11 = "           ";

	/** The Constant STRING_VACIO_LONGITUD_28. */
	protected static final String STRING_VACIO_LONGITUD_28 = "                            ";

	/** The Constant FORMATO_FECHA. */
	protected static final String FORMATO_FECHA = "yyyyMMdd";

	/** The Constant TIPO_AGENDAMIENTO_RECORDATORIO. */
	protected static final String TIPO_AGENDAMIENTO_RECORDATORIO = "RECORDATORIO";

	/** The Constant NUP_LENGTH. */
	protected static final int NUP_LENGTH = 8;

	/** The transferencia agendada DTO. */
	protected TransferenciaAgendadaDTO transferenciaAgendadaDTO;

	/** The cliente. */
	protected Cliente cliente;

	/** The tipo de agendamiento. */
	protected String tipoDeAgendamiento;

	/** The sesion parametros. */
	protected SesionParametros sesionParametros;

	/** The error banelco coelsa habilitado. */
    protected String errorBanelcoCoelsaHabilitado;
    
	/** The Constant LONGITUD_MAX_CONCEPTO. */
    protected static final int LONGITUD_MAX_CONCEPTO = 12;
	
	/** The Constant VACIO_LONGITUD_UNO. */
    protected static final String VACIO_LONGITUD_UNO = " ";

	/**
	 * Instantiates a new transferencia request.
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
	public TransferenciaRequest(TransferenciaAgendadaDTO transferenciaAgendadaDTO, Cliente cliente,
			String tipoDeAgendamiento, SesionParametros sesionParametros, String errorBanelcoCoelsaHabilitado) {
		this.transferenciaAgendadaDTO = transferenciaAgendadaDTO;
		this.cliente = cliente;
		this.tipoDeAgendamiento = tipoDeAgendamiento;
		this.sesionParametros = sesionParametros;
		this.errorBanelcoCoelsaHabilitado = errorBanelcoCoelsaHabilitado;
	}

	/**
	 * Setear datos comunes request.
	 *
	 * @return the XML request entity
	 */
	protected XMLRequestEntity setearDatosComunesRequest() {
		String nup = StringUtils.leftPad(this.cliente.getNup(), NUP_LENGTH, '0');
		XMLRequestEntity request = new XMLRequestEntity();

		XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
		config.setEcoDatosEntrada(SietePorVeintiCuatroConstant.ECO_DATOS_ENTRADA_7X24);
		config.setVersionXML(SietePorVeintiCuatroConstant.VERSION_XML_7X24);

		XMLRequestEntity.META meta = new XMLRequestEntity.META();
		meta.setIndAuten(SietePorVeintiCuatroConstant.IND_AUTENT_7X24);
		meta.setIdSesionCnt(SietePorVeintiCuatroConstant.ID_SESIONCNT_7X24);
		meta.setLogueoAgendaHistorica(SietePorVeintiCuatroConstant.LOGUEO_AGENDA_HISTORICO);

		XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
		cliente.setTipoPersona(SietePorVeintiCuatroConstant.TIPO_PERSONA_7X24);
		cliente.setTipoId(this.cliente.getTipoDocumento());
		cliente.setIdCliente(this.cliente.getDni());
		cliente.setFechaNac(this.cliente.getFechaNacimiento());
		cliente.setNUP(nup);

		meta.setCliente(cliente);

		XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
		usuario.setUsuarioTipo(SietePorVeintiCuatroConstant.USUARIO_TIPO_7X24);
		usuario.setUsuarioAtrib(SietePorVeintiCuatroConstant.USUARIO_ATRIBUTO_7X24);
		usuario.setUsuarioPwd(this.cliente.getPasswordRacf());
		usuario.setUsuarioId(this.cliente.getUsuarioRacf());
		meta.setUsuario(usuario);

		XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
		canal.setCanalTipo(SietePorVeintiCuatroConstant.CANAL_TIPO_7X24);
		canal.setCanalId(SietePorVeintiCuatroConstant.CANAL_ID_7X24);
		canal.setCanalVersion(SietePorVeintiCuatroConstant.CANAL_VERSION_7X24);
		meta.setCanal(canal);

		XMLRequestEntity.META.Subcanal subcanal = new XMLRequestEntity.META.Subcanal();
		subcanal.setSubcanalTipo(SietePorVeintiCuatroConstant.SUBCANAL_TIPO_7X24);
		subcanal.setSubcanalId(SietePorVeintiCuatroConstant.SUBCANAL_ID_7X24);
		meta.setSubcanal(subcanal);

		request.setCONFIG(config);
		request.setMETA(meta);
		return request;
	}

	/**
	 * Setear datos comunes con datos adicionales request.
	 *
	 * @return the XML request entity
	 */
	public XMLRequestEntity setearDatosComunesConDatosAdicionalesRequest() {

		XMLRequestEntity xmlRequest = this.setearDatosComunesRequest();
		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();
		datosMensAvisos.setTitularDebito(cliente.getNombre() + STRING_VACIO_LONGITUD_1 + cliente.getApellido1());
		datosMensAvisos.setMailCredito(transferenciaAgendadaDTO.getEmail());
		datosMensAvisos.setComentario(transferenciaAgendadaDTO.getComentario());
		datosMensAvisos.setConcepto(StringUtils.leftPad(transferenciaAgendadaDTO.getConcepto().getCodigo(),LONGITUD_MAX_CONCEPTO ,VACIO_LONGITUD_UNO));
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

		xmlRequest.setDatosAdicionales(datosAdicionales);
		return xmlRequest;
	}

	/**
	 * Obtener codigo frecuencia para servicio 7 x 24.
	 *
	 * @param frecuencia
	 *            the frecuencia
	 * @param fechaProgramada
	 *            the fecha programada
	 * @return the string
	 */
	protected String obtenerCodigoFrecuenciaParaServicio7x24(FrecuenciaTransferenciaAgendada frecuencia,
			Date fechaProgramada) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaProgramada);
		String diaDelMes = StringUtils.leftPad(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)), 2, "0");
		String mes = StringUtils.leftPad(Integer.toString(calendar.get(Calendar.MONTH)+1), 2, "0");
		int diaDeLaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		String codigoFrecuencia7x24 = "";

		if ("TS".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "S1".concat(obtenerInicialDelDia(diaDeLaSemana));
		}
		if ("2S".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "S2".concat(obtenerInicialDelDia(diaDeLaSemana));
		}
		if ("TM".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "M1".concat(diaDelMes).concat("01");
		}
		if ("2M".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "M1".concat(diaDelMes).concat("02");
		}
		if ("3M".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "M1".concat(diaDelMes).concat("03");
		}
		if ("6M".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "M1".concat(diaDelMes).concat("06");
		}
		if ("1A".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "A1".concat(mes).concat(diaDelMes);
		}

		return codigoFrecuencia7x24;
	}

	/**
	 * Obtener inicial del dia.
	 *
	 * @param diaDeLaSemana
	 *            the dia de la semana
	 * @return the string
	 */
	private String obtenerInicialDelDia(int diaDeLaSemana) {
		String inicialDelDia = "";
		switch (diaDeLaSemana) {
		case 1:
			inicialDelDia = "D";
			break;
		case 2:
			inicialDelDia = "L";
			break;
		case 3:
			inicialDelDia = "T";
			break;
		case 4:
			inicialDelDia = "M";
			break;
		case 5:
			inicialDelDia = "J";
			break;
		case 6:
			inicialDelDia = "V";
			break;
		case 7:
			inicialDelDia = "S";
			break;
		}
		return inicialDelDia;
	}

	/**
	 * Armar request rio rio.
	 *
	 * @return the XML request entity
	 */
	public abstract XMLRequestEntity armarRequestRioRio();

	/**
	 * Armar request otros bancos.
	 *
	 * @return the XML request entity
	 */
	public abstract XMLRequestEntity armarRequestOtrosBancos();

}
