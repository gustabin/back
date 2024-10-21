/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * DTO para contener los datos de retorno del servicio de iatx SIMFIPLANV.100
 * 
 * @author manuel.vargas
 *
 */
public class SimulacionPlanVDTO {

	/** Numero de cuenta del producto. */
	private String numeroCuentaProducto;

	/** The cargo tipo. */
	private String cargoTipo;

	/** The cargo valor. */
	private String cargoValor;

	/** The seguro cobra. */
	private String seguroCobra;

	/** The seguro tasa. */
	private String seguroTasa;

	/** The seguro IVA. */
	private String seguroIVA;

	/** The i V aintereses. */
	private String iVAintereses;

	/** The difiere cuota. */
	private String difiereCuota;

	/** The financia cuota. */
	private String financiaCuota;

	/** The penalty tipo. */
	private String penaltyTipo;

	/** The penalty valor. */
	private String penaltyValor;

	/** The tasas rango 1. */
	private String tasasRango1;

	/** The tasas rango 2. */
	private String tasasRango2;

	/** The tasas rango 3. */
	private String tasasRango3;

	/** The tasas rango 4. */
	private String tasasRango4;

	/** The tasas rango 5. */
	private String tasasRango5;

	/** The tasas rango 6. */
	private String tasasRango6;

	/** The tasas valor 1. */
	private String tasasValor1;

	/** The tasas valor 2. */
	private String tasasValor2;

	/** The tasas valor 3. */
	private String tasasValor3;

	/** The tasas valor 4. */
	private String tasasValor4;

	/** The tasas valor 5. */
	private String tasasValor5;

	/** The tasas valor 6. */
	private String tasasValor6;

	/** The tasa efec mensual. */
	private String tasaEfecMensual;

	/** The modelo liq. */
	private String modeloLiq;

	/** The total COC. */
	private String totalCOC;

	/** The total cargo. */
	private String totalCargo;

	/** The total seguro. */
	private String totalSeguro;

	/** The total IVA. */
	private String totalIVA;

	/** The cuota. */
	private String importeCuota;

	/** The costo financiero total. */
	private String costoFinancieroTotal;

	/** The estado simulacion. */
	private String estadoSimulacion;

	/**
	 * Gets the cargo tipo.
	 *
	 * @return the cargo tipo
	 */
	public String getCargoTipo() {
		return cargoTipo;
	}

	/**
	 * Sets the cargo tipo.
	 *
	 * @param cargoTipo
	 *            the new cargo tipo
	 */
	public void setCargoTipo(String cargoTipo) {
		this.cargoTipo = cargoTipo;
	}

	/**
	 * Gets the cargo valor.
	 *
	 * @return the cargo valor
	 */
	public String getCargoValor() {
		return cargoValor;
	}

	/**
	 * Sets the cargo valor.
	 *
	 * @param cargoValor
	 *            the new cargo valor
	 */
	public void setCargoValor(String cargoValor) {
		this.cargoValor = cargoValor;
	}

	/**
	 * Gets the seguro cobra.
	 *
	 * @return the seguro cobra
	 */
	public String getSeguroCobra() {
		return seguroCobra;
	}

	/**
	 * Sets the seguro cobra.
	 *
	 * @param seguroCobra
	 *            the new seguro cobra
	 */
	public void setSeguroCobra(String seguroCobra) {
		this.seguroCobra = seguroCobra;
	}

	/**
	 * Gets the seguro tasa.
	 *
	 * @return the seguro tasa
	 */
	public String getSeguroTasa() {
		return seguroTasa;
	}

	/**
	 * Sets the seguro tasa.
	 *
	 * @param seguroTasa
	 *            the new seguro tasa
	 */
	public void setSeguroTasa(String seguroTasa) {
		this.seguroTasa = seguroTasa;
	}

	/**
	 * Gets the seguro IVA.
	 *
	 * @return the seguro IVA
	 */
	public String getSeguroIVA() {
		return seguroIVA;
	}

	/**
	 * Sets the seguro IVA.
	 *
	 * @param seguroIVA
	 *            the new seguro IVA
	 */
	public void setSeguroIVA(String seguroIVA) {
		this.seguroIVA = seguroIVA;
	}

	/**
	 * Gets the i V aintereses.
	 *
	 * @return the i V aintereses
	 */
	public String getiVAintereses() {
		return iVAintereses;
	}

	/**
	 * Sets the IV aintereses.
	 *
	 * @param iVAintereses
	 *            the new IV aintereses
	 */
	public void setIVAintereses(String iVAintereses) {
		this.iVAintereses = iVAintereses;
	}

	/**
	 * Gets the difiere cuota.
	 *
	 * @return the difiere cuota
	 */
	public String getDifiereCuota() {
		return difiereCuota;
	}

	/**
	 * Sets the difiere cuota.
	 *
	 * @param difiereCuota
	 *            the new difiere cuota
	 */
	public void setDifiereCuota(String difiereCuota) {
		this.difiereCuota = difiereCuota;
	}

	/**
	 * Gets the financia cuota.
	 *
	 * @return the financia cuota
	 */
	public String getFinanciaCuota() {
		return financiaCuota;
	}

	/**
	 * Sets the financia cuota.
	 *
	 * @param financiaCuota
	 *            the new financia cuota
	 */
	public void setFinanciaCuota(String financiaCuota) {
		this.financiaCuota = financiaCuota;
	}

	/**
	 * Gets the penalty tipo.
	 *
	 * @return the penalty tipo
	 */
	public String getPenaltyTipo() {
		return penaltyTipo;
	}

	/**
	 * Sets the penalty tipo.
	 *
	 * @param penaltyTipo
	 *            the new penalty tipo
	 */
	public void setPenaltyTipo(String penaltyTipo) {
		this.penaltyTipo = penaltyTipo;
	}

	/**
	 * Gets the penalty valor.
	 *
	 * @return the penalty valor
	 */
	public String getPenaltyValor() {
		return penaltyValor;
	}

	/**
	 * Sets the penalty valor.
	 *
	 * @param penaltyValor
	 *            the new penalty valor
	 */
	public void setPenaltyValor(String penaltyValor) {
		this.penaltyValor = penaltyValor;
	}

	/**
	 * Gets the tasas rango 1.
	 *
	 * @return the tasas rango 1
	 */
	public String getTasasRango1() {
		return tasasRango1;
	}

	/**
	 * Sets the tasas rango 1.
	 *
	 * @param tasasRango1
	 *            the new tasas rango 1
	 */
	public void setTasasRango1(String tasasRango1) {
		this.tasasRango1 = tasasRango1;
	}

	/**
	 * Gets the tasas rango 2.
	 *
	 * @return the tasas rango 2
	 */
	public String getTasasRango2() {
		return tasasRango2;
	}

	/**
	 * Sets the tasas rango 2.
	 *
	 * @param tasasRango2
	 *            the new tasas rango 2
	 */
	public void setTasasRango2(String tasasRango2) {
		this.tasasRango2 = tasasRango2;
	}

	/**
	 * Gets the tasas rango 3.
	 *
	 * @return the tasas rango 3
	 */
	public String getTasasRango3() {
		return tasasRango3;
	}

	/**
	 * Sets the tasas rango 3.
	 *
	 * @param tasasRango3
	 *            the new tasas rango 3
	 */
	public void setTasasRango3(String tasasRango3) {
		this.tasasRango3 = tasasRango3;
	}

	/**
	 * Gets the tasas rango 4.
	 *
	 * @return the tasas rango 4
	 */
	public String getTasasRango4() {
		return tasasRango4;
	}

	/**
	 * Sets the tasas rango 4.
	 *
	 * @param tasasRango4
	 *            the new tasas rango 4
	 */
	public void setTasasRango4(String tasasRango4) {
		this.tasasRango4 = tasasRango4;
	}

	/**
	 * Gets the tasas rango 5.
	 *
	 * @return the tasas rango 5
	 */
	public String getTasasRango5() {
		return tasasRango5;
	}

	/**
	 * Sets the tasas rango 5.
	 *
	 * @param tasasRango5
	 *            the new tasas rango 5
	 */
	public void setTasasRango5(String tasasRango5) {
		this.tasasRango5 = tasasRango5;
	}

	/**
	 * Gets the tasas rango 6.
	 *
	 * @return the tasas rango 6
	 */
	public String getTasasRango6() {
		return tasasRango6;
	}

	/**
	 * Sets the tasas rango 6.
	 *
	 * @param tasasRango6
	 *            the new tasas rango 6
	 */
	public void setTasasRango6(String tasasRango6) {
		this.tasasRango6 = tasasRango6;
	}

	/**
	 * Gets the tasas valor 1.
	 *
	 * @return the tasas valor 1
	 */
	public String getTasasValor1() {
		return tasasValor1;
	}

	/**
	 * Sets the tasas valor 1.
	 *
	 * @param tasasValor1
	 *            the new tasas valor 1
	 */
	public void setTasasValor1(String tasasValor1) {
		this.tasasValor1 = tasasValor1;
	}

	/**
	 * Gets the tasas valor 2.
	 *
	 * @return the tasas valor 2
	 */
	public String getTasasValor2() {
		return tasasValor2;
	}

	/**
	 * Sets the tasas valor 2.
	 *
	 * @param tasasValor2
	 *            the new tasas valor 2
	 */
	public void setTasasValor2(String tasasValor2) {
		this.tasasValor2 = tasasValor2;
	}

	/**
	 * Gets the tasas valor 3.
	 *
	 * @return the tasas valor 3
	 */
	public String getTasasValor3() {
		return tasasValor3;
	}

	/**
	 * Sets the tasas valor 3.
	 *
	 * @param tasasValor3
	 *            the new tasas valor 3
	 */
	public void setTasasValor3(String tasasValor3) {
		this.tasasValor3 = tasasValor3;
	}

	/**
	 * Gets the tasas valor 4.
	 *
	 * @return the tasas valor 4
	 */
	public String getTasasValor4() {
		return tasasValor4;
	}

	/**
	 * Sets the tasas valor 4.
	 *
	 * @param tasasValor4
	 *            the new tasas valor 4
	 */
	public void setTasasValor4(String tasasValor4) {
		this.tasasValor4 = tasasValor4;
	}

	/**
	 * Gets the tasas valor 5.
	 *
	 * @return the tasas valor 5
	 */
	public String getTasasValor5() {
		return tasasValor5;
	}

	/**
	 * Sets the tasas valor 5.
	 *
	 * @param tasasValor5
	 *            the new tasas valor 5
	 */
	public void setTasasValor5(String tasasValor5) {
		this.tasasValor5 = tasasValor5;
	}

	/**
	 * Gets the tasas valor 6.
	 *
	 * @return the tasas valor 6
	 */
	public String getTasasValor6() {
		return tasasValor6;
	}

	/**
	 * Sets the tasas valor 6.
	 *
	 * @param tasasValor6
	 *            the new tasas valor 6
	 */
	public void setTasasValor6(String tasasValor6) {
		this.tasasValor6 = tasasValor6;
	}

	/**
	 * Gets the tasa efec mensual.
	 *
	 * @return the tasa efec mensual
	 */
	public String getTasaEfecMensual() {
		return tasaEfecMensual;
	}

	/**
	 * Sets the tasa efec mensual.
	 *
	 * @param tasaEfecMensual
	 *            the new tasa efec mensual
	 */
	public void setTasaEfecMensual(String tasaEfecMensual) {
		this.tasaEfecMensual = tasaEfecMensual;
	}

	/**
	 * Gets the modelo liq.
	 *
	 * @return the modelo liq
	 */
	public String getModeloLiq() {
		return modeloLiq;
	}

	/**
	 * Sets the modelo liq.
	 *
	 * @param modeloLiq
	 *            the new modelo liq
	 */
	public void setModeloLiq(String modeloLiq) {
		this.modeloLiq = modeloLiq;
	}

	/**
	 * Gets the total COC.
	 *
	 * @return the total COC
	 */
	public String getTotalCOC() {
		return totalCOC;
	}

	/**
	 * Sets the total COC.
	 *
	 * @param totalCOC
	 *            the new total COC
	 */
	public void setTotalCOC(String totalCOC) {
		this.totalCOC = totalCOC;
	}

	/**
	 * Gets the total cargo.
	 *
	 * @return the total cargo
	 */
	public String getTotalCargo() {
		return totalCargo;
	}

	/**
	 * Sets the total cargo.
	 *
	 * @param totalCargo
	 *            the new total cargo
	 */
	public void setTotalCargo(String totalCargo) {
		this.totalCargo = totalCargo;
	}

	/**
	 * Gets the total seguro.
	 *
	 * @return the total seguro
	 */
	public String getTotalSeguro() {
		return totalSeguro;
	}

	/**
	 * Sets the total seguro.
	 *
	 * @param totalSeguro
	 *            the new total seguro
	 */
	public void setTotalSeguro(String totalSeguro) {
		this.totalSeguro = totalSeguro;
	}

	/**
	 * Gets the total IVA.
	 *
	 * @return the total IVA
	 */
	public String getTotalIVA() {
		return totalIVA;
	}

	/**
	 * Sets the total IVA.
	 *
	 * @param totalIVA
	 *            the new total IVA
	 */
	public void setTotalIVA(String totalIVA) {
		this.totalIVA = totalIVA;
	}

	/**
	 * Gets the cuota.
	 *
	 * @return the cuota
	 */
	public String getImporteCuota() {
		return importeCuota;
	}

	/**
	 * Sets the cuota.
	 *
	 * @param importeCuota
	 *            the new cuota
	 */
	public void setImporteCuota(String importeCuota) {
		this.importeCuota = importeCuota;
	}

	/**
	 * Gets the costo financiero total.
	 *
	 * @return the costo financiero total
	 */
	public String getCostoFinancieroTotal() {
		return costoFinancieroTotal;
	}

	/**
	 * Sets the costo financiero total.
	 *
	 * @param costoFinancieroTotal
	 *            the new costo financiero total
	 */
	public void setCostoFinancieroTotal(String costoFinancieroTotal) {
		this.costoFinancieroTotal = costoFinancieroTotal;
	}

	/**
	 * Gets the estado simulacion.
	 *
	 * @return the estado simulacion
	 */
	public String getEstadoSimulacion() {
		return estadoSimulacion;
	}

	/**
	 * Sets the estado simulacion.
	 *
	 * @param estadoSimulacion
	 *            the new estado simulacion
	 */
	public void setEstadoSimulacion(String estadoSimulacion) {
		this.estadoSimulacion = estadoSimulacion;
	}

	/**
	 * Gets the numero cuenta producto.
	 *
	 * @return the numeroCuentaProducto
	 */
	public String getNumeroCuentaProducto() {
		return numeroCuentaProducto;
	}

	/**
	 * Sets the numero cuenta producto.
	 *
	 * @param numeroCuentaProducto
	 *            the numeroCuentaProducto to set
	 */
	public void setNumeroCuentaProducto(String numeroCuentaProducto) {
		this.numeroCuentaProducto = numeroCuentaProducto;
	}
}
