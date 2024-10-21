/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;

/**
 * The Enum ConceptoTransferenciaEnum.
 *
 * @author B039636
 */
public enum ConceptoTransferenciaEnum {

	/** The varios. */
	VARIOS("8", "Varios", "VAR","Varios",0),
	/** The alquiler. */
	ALQUILER("1", "Alquiler", "ALQ","Alquiler",0),
	/** The cuota. */
	CUOTA("2", "Cuota", "CUO","Cuota",0),
	/** The expensas. */
	EXPENSAS("3", "Expensas", "EXP","Expensas",0),
	/** The factura. */
	FACTURA("4", "Factura", "FAC","Factura",0),
	/** The prestamo. */
	PRESTAMO("5", "Préstamo", "PRE","Prestamo",0),
	/** The seguro. */
	SEGURO("6", "Seguro", "SEG","Seguro",0),
	/** The honorarios. */
	HONORARIOS("7", "Honorarios", "HON","Honorarios",0),
	/** The haberes. */
	HABERES("9", "Haberes", "HAB","Haberes",0),
	/** The inmobiliarias. */
	INMOBILIARIAS("A", "Inmobiliarias", "OIN","OIN",1),
	/** The inmobiliarias habitualista. */
	INMOBILIARIAS_HABITUALISTAS("B", "Inmob. Habitualista", "OIH","OIH",1),
	/** The bienes registrables habitualistas. */
	BIENES_REG_HABIT("C", "Bienes Reg. Habit.", "BRH","BRH",1),
	/** The bienes registrables no habitualistas. */
	BIENES_REG_NO_HABIT("D", "Bienes Reg. No Hab.", "BRN","BRN",1),
	/** The suscripcion obligaciones negociables. */
	SUSCRIP_OBLIG_NEG("E", "Suscrip. Oblig. Neg.", "SON","SON",1),
	/** The Reintegros de Obras Sociales y Prepagas. */
	REINTEGROS_DE_OBRAS_SOCIALES_Y_PREPAGAS("F", "Reintegros de Obras Sociales y Prepagas", "ROP","ROP",1),
	/** The Siniestros de Seguros. */
	SINIESTROS_DE_SEGUROS("G", "Siniestros de Seguros", "SIS","SIS",1),

	/** The aportes de capital. */
	APORTES_CAPITAL("H", "Aportes de Capital", "APC","APC",1);

	/** The ordinal. */
	private String ordinal;

	/** The descripcion. */
	private String descripcion;

	/** The codigo. */
	private String codigo;
	
	/** The descripcionAbreviada. */
	private String descripcionAbreviada;
	
	/** The habilitaLegal. */
	private int habilitaLegal;

	/**
	 * Instantiates a new concepto transferencia enum.
	 *
	 * @param ordinal
	 *            the ordinal
	 * @param descripcion
	 *            the descripcion
	 * @param codigo
	 *            the codigo
	 * @param descripcionAbreviada
	 *            the descripcion abreviada
	 * @param habilitaLegal
	 *            the habilita legal
	 */
	ConceptoTransferenciaEnum(String ordinal, String descripcion, String codigo, String descripcionAbreviada, int habilitaLegal) {
		this.ordinal = ordinal;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.descripcionAbreviada = descripcionAbreviada;
		this.habilitaLegal = habilitaLegal;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public static List<String> getValues() {
		List<String> values = new ArrayList<String>();
		for (ConceptoTransferenciaEnum concepto : ConceptoTransferenciaEnum.values()) {
			values.add(concepto.getDescripcion());
		}
		return values;
	}
	
	/**
	 * Gets the concepto view.
	 *
	 * @return the concepto view
	 */
	public static List<ConceptoView> getConceptoView() {
		List<ConceptoView> values = new ArrayList<ConceptoView>();
		for (ConceptoTransferenciaEnum concepto : ConceptoTransferenciaEnum.values()) {
			values.add(new ConceptoView(concepto.getDescripcion(), concepto.getOrdinal(), concepto.getCodigo(),concepto.getDescripcionAbreviada(), concepto.habilitaLegal));
		}
		return values;
	}

	/**
	 * Gets the concepto.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the concepto
	 */
	public static ConceptoTransferenciaEnum getConcepto(String descripcion) {
		if (descripcion == null || "".equalsIgnoreCase(descripcion)) {
			return null;
		}
		for (ConceptoTransferenciaEnum concepto : ConceptoTransferenciaEnum.values()) {
			if (ISBANStringUtils.removerCaraceteresEspeciales(concepto.getDescripcion())
					.equalsIgnoreCase(ISBANStringUtils.removerCaraceteresEspeciales(descripcion))) {
				return concepto;
			}
		}
		return null;
	}

	/**
	 * Gets the concepto from codigo.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the concepto from codigo
	 */
	public static ConceptoTransferenciaEnum getConceptoFromDescripcion(String descripcion) {
		if (descripcion == null || "".equalsIgnoreCase(descripcion)) {
			return null;
		} else {
			return ConceptoTransferenciaEnum.getConcepto(descripcion.trim());
		}
	}

	/**
	 * Gets the concepto from codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the concepto from codigo
	 */
	public static ConceptoTransferenciaEnum getConceptoFromCodigo(String codigo) {
		if (codigo == null || "".equalsIgnoreCase(codigo)) {
			return null;
		}
		for (ConceptoTransferenciaEnum concepto : ConceptoTransferenciaEnum.values()) {
			if (concepto.getCodigo().equalsIgnoreCase(codigo)) {
				return concepto;
			}
		}
		return null;
	}

	/**
	 * Gets the concepto from ordinal.
	 *
	 * @param ordinal
	 *            the ordinal
	 * @return the concepto from ordinal
	 */
	public static ConceptoTransferenciaEnum getConceptoFromOrdinal(String ordinal) {
		for (ConceptoTransferenciaEnum concepto : ConceptoTransferenciaEnum.values()) {
			if (concepto.getOrdinal().equals(ordinal)) {
				return concepto;
			}
		}
		return null;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Gets the ordinal.
	 *
	 * @return the ordinal
	 */
	public String getOrdinal() {
		return ordinal;
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
	 * Gets the descripcion abreviada.
	 *
	 * @return the descripcionAbreviada
	 */
	public String getDescripcionAbreviada() {
		return descripcionAbreviada;
	}

	/**
	 * Gets the habilita legal.
	 *
	 * @return the habilitaLegal
	 */
	public int getHabilitaLegal() {
		return habilitaLegal;
	}
	
}
