/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum TipoCuentaBanelco.
 *
 * @author B015167
 */
public enum TipoCuentaBanelco {

	/** The tipo 00. CU */
	TIPO_00("00"),
	/** The tipo 01. CCPesos*/
	TIPO_01("01"),
	/** The tipo 02. CCDolares*/
	TIPO_02("02"),
	/** The tipo 11. CAPesos*/
	TIPO_11("11"),
	/** The tipo 12. CADolares*/
	TIPO_12("12"),
	/** The tipo 21. CVUPesos*/
	TIPO_21("21");

	/** The codigo trfcci. */
	private String codigoTrfcci;

	/** The map codigo trfcci. */
	private static Map<String, TipoCuentaBanelco> mapCodigoTrfcci = new HashMap<String, TipoCuentaBanelco>(
			TipoCuentaBanelco.values().length);
	static {
		for (TipoCuentaBanelco tipoCuentaBanelco : TipoCuentaBanelco.values()) {
			mapCodigoTrfcci.put(tipoCuentaBanelco.getCodigoTrfcci(), tipoCuentaBanelco);
		}
	}

	/**
	 * Instantiates a new tipo cuenta banelco.
	 *
	 * @param codigoTrfcci
	 *            the codigo trfcci
	 */
	private TipoCuentaBanelco(String codigoTrfcci) {
		this.codigoTrfcci = codigoTrfcci;
	}

	/**
	 * Gets the codigo trfcci.
	 *
	 * @return the codigo trfcci
	 */
	public String getCodigoTrfcci() {
		return codigoTrfcci;
	}

	/**
	 * Buscar por codigo trfcci.
	 *
	 * @param codigoTrfcci
	 *            the codigo trfcci
	 * @return the tipo cuenta banelco
	 */
	public static TipoCuentaBanelco buscarPorCodigoTrfcci(String codigoTrfcci) {
		TipoCuentaBanelco tipoCuentaBanelco = mapCodigoTrfcci.get(codigoTrfcci);
		if (tipoCuentaBanelco == null) {
			throw new IllegalArgumentException("No existe codigo TRFCCI: [" + codigoTrfcci + "].");
		}
		return tipoCuentaBanelco;
	}
}
