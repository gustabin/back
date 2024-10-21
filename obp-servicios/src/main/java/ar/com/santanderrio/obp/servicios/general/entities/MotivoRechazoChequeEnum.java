/*
 * 
 */
package ar.com.santanderrio.obp.servicios.general.entities;

/**
 * The Enum MotivoRechazoChequeEnum.
 */
public enum MotivoRechazoChequeEnum {

	/** The plazo validez l. */
	PLAZO_VALIDEZ_L("PLAZO VALIDEZ L", "FECHA VENCIDA"),

	/** The orden de no. */
	ORDEN_DE_NO("**ORDEN DE NO P", "ORDEN DE NO PAGAR- MISMO D\315A DE RECHAZO"),

	/** The ORDE n_ d e_ n o1. */
	ORDEN_DE_NO1("**ORDEN DE NO P", "ORDEN NO PAGAR"),

	/** The cheque alterado. */
	CHEQUE_ALTERADO("CHEQUE ALTERADO", "CHEQUE ADULTERADO"),

	/** The cheque ya pagado. */
	CHEQUE_YA_PAGADO("CHEQUE YA PAGAD", "CHEQUE YA PAGADO"),

	/** The cont social vdo. */
	CONT_SOCIAL_VDO("CONT.SOCIAL VDO", "FIRMANTE NO AUTORIZADO"),

	/** The cta cda orden. */
	CTA_CDA_ORDEN("CTA.CDA. ORDEN ", "CUENTA CERRADA ORDEN JUDICIAL"),

	/** The cta cda librado. */
	CTA_CDA_LIBRADO("CTA.CDA.LIBRADO", "CUENTA CERRADA LIBRADOR"),

	/** The cta cda por bco. */
	CTA_CDA_POR_BCO("CTA.CDA.POR BCO", "CUENTA CERRADA BANCO"),

	/** The cuenta cerrada. */
	CUENTA_CERRADA("CUENTA CERRADA", "CUENTA CERRADA"),

	/** The cuenta embargd. */
	CUENTA_EMBARGD("CUENTA EMBARGAD", "CUENTA EMBARGADA"),

	/** The cuenta inexiste. */
	CUENTA_INEXISTE("CUENTA INEXISTE", "CUENTA INEXISTENTE"),

	/** The dia no laboral. */
	DIA_NO_LABORAL("DIA NO LABORABL", "FERIADO LOCAL"),

	/** The difiere firma s. */
	DIFIERE_FIRMA_S("DIFIERE FIRMA S", "DIFIERE FIRMA SALVADO DORSO"),

	/** The dud aut firma l. */
	DUD_AUT_FIRMA_L("DUD.AUT.FIRMA L", "DUDOSA AUTENTICIDAD FIRMA LIBRADOR"),

	/** The enmendadura no. */
	ENMENDADURA_NO("ENMENDADURA NO ", "ENMENDADURA NO SALVADA"),

	/** The error entidad d. */
	ERROR_ENTIDAD_D("ERROR ENTIDAD D", "ERROR ENTIDAD DEPOSITARIA"),

	/** The fal carac legal. */
	FAL_CARAC_LEGAL("FAL.CARAC.LEGAL", "FALTA CARACTER LEGAL ENDOSO"),

	/** The fal end para de. */
	FAL_END_PARA_DE("FAL.END.PARA DE", "FALTA ENDOSO"),

	/** The fal fir resp ba. */
	FAL_FIR_RESP_BA("FAL.FIR.RESP.BA", "FALTA FIRMA RESPONSABILIDAD BANCARIA"),

	/** The fal sello banco. */
	FAL_SELLO_BANCO("FAL.SELLO BANCO", "FALTA SELLO BANCO"),

	/** The falta conf cheq. */
	FALTA_CONF_CHEQ("FALTA CONF.CHEQ", "FALTA CONFORMIDAD LIBRETA"),

	/** The falta fecha. */
	FALTA_FECHA("FALTA FECHA", "FALTA FECHA LIBRADORA"),

	/** The falta firma lib. */
	FALTA_FIRMA_LIB("FALTA FIRMA LIB", "FALTA FIRMA LIBRADOR"),

	/** The falta imagen. */
	FALTA_IMAGEN("FALTA IMAGEN", "FALTA IMAGEN"),

	/** The FALT a_ image n1. */
	FALTA_IMAGEN1("FALTA IMAGEN", "FALTA VALOR FISICO"),

	/** The falta primer en. */
	FALTA_PRIMER_EN("FALTA PRIMER EN", "FALTA 1ER ENDOSO"),

	/** The falta adelantad. */
	FALTA_ADELANTAD("FECHA ADELANTAD", "FECHA ADELANTADA CHEQUE PAGO DIFERIDO"),

	/** The fecha adelantad. */
	FECHA_ADELANTAD("FECHA ADELANTAD", "FECHA ADELANTADA CHEQUE COMUN"),

	/** The fecha en guaris. */
	FECHA_EN_GUARIS("FECHA EN GUARIS", "FECHA EN GUARISMOS"),

	/** The FECH a_ inexiste n1. */
	FECHA_INEXISTEN1("FECHA INEXISTEN", "FECHA INVALIDA"),

	/** The fir lib no coin. */
	FIR_LIB_NO_COIN("FIR.LIB.NO COIN", "DIFIERE FIRMA LIBRADOR"),

	/** The firmante inexis. */
	FIRMANTE_INEXIS("FIRMANTE INEXIS", "FIRMANTE INEXISTENTE"),

	/** The firmante inhabi. */
	FIRMANTE_INHABI("FIRMANTE INHABI", "FIRMANTE INHABILITADO"),

	/** The formato no comp. */
	FORMATO_NO_COMP("FORMATO NO COMP", "DISE\321O NO COMPENSABLE"),

	/** The formula cheque. */
	FORMULA_CHEQUE("FORMULA CHEQUE ", "FORMULA CHEQUE FALSA"),

	/** The imagen ilegible. */
	IMAGEN_ILEGIBLE("IMAGEN ILEGIBLE", "IMAGEN ILEGIBLE"),

	/** The importe no coin. */
	IMPORTE_NO_COIN("IMPORTE NO COIN", "IMPORTE DISTINTO AL REGISTRO"),

	/** The intransferible. */
	INTRANSFERIBLE("INTRANSFERIBLE", "INTRANSFERIBLE"),

	/** The librador en qui. */
	LIBRADOR_EN_QUI("LIBRADOR EN QUI", "LIBRADOR EN QUIEBRA"),

	/** The limite endoso e. */
	LIMITE_ENDOSO_E("LIMITE ENDOSO E", "EXCEDE LIMITE DE ENDOSOS"),

	/** The no corres canje. */
	NO_CORRES_CANJE("NO CORRES.CANJE", "NO CORRESPONDE CANJE"),

	/** The N o_ corres p_2 da. */
	NO_CORRESP_2DA("NO CORRESP.2DA.", "NO CORRESPONDE 2DA. PRESENTACION"),

	/** The no moneda de cu. */
	NO_MONEDA_DE_CU("NO MONEDA DE CU", "NO MONEDA CURSO LEGAL"),

	/** The o n p denuncia. */
	O_N_P_DENUNCIA("O.N.P.DENUNCIA ", "ORDEN NO PAGO CHEQ. NO ENTREGADA AL CLIENTE EN DIA"),

	/** The onp denuncia ba. */
	ONP_DENUNCIA_BA("ONP.DENUNCIA BA", "ORDEN NO PAGOCHEQUERA NO ENTREGADA AL CLIENTE"),

	/** The ord agreg incom. */
	ORD_AGREG_INCOM("ORD.AGREG.INCOM", "ORDEN NO REPRESENTA. PERSONA FISICA NI JURIDICA"),

	/** The PLAZ o_ valide z_ l1. */
	PLAZO_VALIDEZ_L1("PLAZO VALIDEZ L", "FECHA VENCIDA"),

	/** The presentacion en. */
	PRESENTACION_EN("PRESENTACION EN", "PRESENTACION CONCURSO PREVENTIVO"),

	/** The propaganda inse. */
	PROPAGANDA_INSE("PROPAGANDA INSE", "PROPAGANDA INSERTA EN CHEQUE"),

	/** The redaccion en id. */
	REDACCION_EN_ID("REDACCION EN ID", "LIBRADO EN IDIOMA EXTRANJERO"),

	/** The sello banco ile. */
	SELLO_BANCO_ILE("SELLO BANCO ILE", "SELLO BANCO ILEGIBLE"),

	/** The sin fondos. */
	SIN_FONDOS("SIN FONDOS", "SIN FONDOS"),

	/** The situacion de em. */
	SITUACION_DE_EM("SITUACION DE EM", "SITUACION DE EMERGENCIA"),

	/** The tex exten fuera. */
	TEX_EXTEN_FUERA("TEX.EXTEN.FUERA", "TEXTO EXTENDIDO FUERA DE LUGAR"),

	/** The texto deficient. */
	TEXTO_DEFICIENT("TEXTO DEFICIENT", "TEXTO DEFICIENTE"),

	/** The texto ilegible. */
	TEXTO_ILEGIBLE("TEXTO ILEGIBLE", "TEXTO ILEGIBLE");

	/** The nombre. */
	private String nombre;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Instantiates a new motivo rechazo cheque enum.
	 *
	 * @param nombre
	 *            the nombre
	 * @param descripcion
	 *            the descripcion
	 */
	MotivoRechazoChequeEnum(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/**
	 * From referencia interna.
	 *
	 * @param nombre
	 *            the nombre
	 * @return the string
	 */
	public static String fromReferenciaInterna(String nombre) {
		MotivoRechazoChequeEnum[] values = MotivoRechazoChequeEnum.values();

		String response = null;

		for (MotivoRechazoChequeEnum motivoRechazoChequeEnum : values) {
			if (motivoRechazoChequeEnum.getNombre().equalsIgnoreCase(nombre)) {
				response = motivoRechazoChequeEnum.getDescripcion();
			}
		}
		return response;
	}

}
