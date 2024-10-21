/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Enum OperacionTarjetaWSEnum.
 */
public enum OperacionTarjetaWSEnum {

	/** The resumencuenta. */
	RESUMENCUENTA("ResumenCuenta"),
	/** The posicionconsolidada. */
	POSICIONCONSOLIDADA("PosicionConsolidada"),
	/** The ultimosmovimientos. */
	ULTIMOSMOVIMIENTOS("UltimosMovimientos"),
	/** The ultimaliquidacion. */
	ULTIMALIQUIDACION("UltimaLiquidacion"),
	/** The autorizaciones. */
	AUTORIZACIONES("Autorizaciones"),
	/** The cuotaspendientes. */
	CUOTASPENDIENTES("CuotasPendientes"),
	/** The informedebitosautomaticos. */
	INFORMEDEBITOSAUTOMATICOS("InformeDebitosAutomaticos"),

	/** The limitesytotales. */
	LIMITESYTOTALES("LimitesYTotales");

	/** The operacion. */
	private String operacion;

	/**
	 * Instantiates a new operacion tarjeta WS enum.
	 *
	 * @param operacion
	 *            the operacion
	 */
	private OperacionTarjetaWSEnum(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

}
