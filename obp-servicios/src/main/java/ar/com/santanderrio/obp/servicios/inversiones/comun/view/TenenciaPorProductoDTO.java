/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.math.BigDecimal;

/**
 * The Class TenenciaPorProductoDTO.
 *
 * @author juan.pablo.picate
 */
public class TenenciaPorProductoDTO implements Comparable<TenenciaPorProductoDTO> {

	/** The tenencia valuada hoy pesos. */
	private BigDecimal tenenciaValuadaHoyPesos = BigDecimal.valueOf(0);

	/** The tenencia valuada hoy dolares. */
	private BigDecimal tenenciaValuadaHoyDolares = BigDecimal.valueOf(0);

	/** The tenencia valuada costo pesos. */
	private BigDecimal tenenciaValuadaCostoPesos = BigDecimal.valueOf(0);

	/** The tenencia valuada costo dolares. */
	private BigDecimal tenenciaValuadaCostoDolares = BigDecimal.valueOf(0);

	/** The resultado pesos. */
	private BigDecimal resultadoPesos = BigDecimal.valueOf(0);

	/** The resultado dolares. */
	private BigDecimal resultadoDolares = BigDecimal.valueOf(0);

	/** The tenencia reexpresada pesos. */
	private BigDecimal tenenciaReexpresadaPesos = BigDecimal.valueOf(0);

	/** The tenencia reexpresada dolares. */
	private BigDecimal tenenciaReexpresadaDolares = BigDecimal.valueOf(0);

	/** The codigo error. */
	private String codigoError = "0";

	/** The producto. */
	private String producto;

	/**
	 * Gets the tenencia valuada hoy pesos.
	 *
	 * @return the tenenciaValuadaHoyPesos
	 */
	public BigDecimal getTenenciaValuadaHoyPesos() {
		return tenenciaValuadaHoyPesos;
	}

	/**
	 * Sets the tenencia valuada hoy pesos.
	 *
	 * @param tenenciaValuadaHoyPesos
	 *            the tenenciaValuadaHoyPesos to set
	 */
	public void setTenenciaValuadaHoyPesos(BigDecimal tenenciaValuadaHoyPesos) {
		this.tenenciaValuadaHoyPesos = tenenciaValuadaHoyPesos;
	}

	/**
	 * Gets the tenencia valuada hoy dolares.
	 *
	 * @return the tenenciaValuadaHoyDolares
	 */
	public BigDecimal getTenenciaValuadaHoyDolares() {
		return tenenciaValuadaHoyDolares;
	}

	/**
	 * Sets the tenencia valuada hoy dolares.
	 *
	 * @param tenenciaValuadaHoyDolares
	 *            the tenenciaValuadaHoyDolares to set
	 */
	public void setTenenciaValuadaHoyDolares(BigDecimal tenenciaValuadaHoyDolares) {
		this.tenenciaValuadaHoyDolares = tenenciaValuadaHoyDolares;
	}

	/**
	 * Gets the tenencia valuada costo pesos.
	 *
	 * @return the tenenciaValuadaCostoPesos
	 */
	public BigDecimal getTenenciaValuadaCostoPesos() {
		return tenenciaValuadaCostoPesos;
	}

	/**
	 * Sets the tenencia valuada costo pesos.
	 *
	 * @param tenenciaValuadaCostoPesos
	 *            the tenenciaValuadaCostoPesos to set
	 */
	public void setTenenciaValuadaCostoPesos(BigDecimal tenenciaValuadaCostoPesos) {
		this.tenenciaValuadaCostoPesos = tenenciaValuadaCostoPesos;
	}

	/**
	 * Gets the tenencia valuada costo dolares.
	 *
	 * @return the tenenciaValuadaCostoDolares
	 */
	public BigDecimal getTenenciaValuadaCostoDolares() {
		return tenenciaValuadaCostoDolares;
	}

	/**
	 * Sets the tenencia valuada costo dolares.
	 *
	 * @param tenenciaValuadaCostoDolares
	 *            the tenenciaValuadaCostoDolares to set
	 */
	public void setTenenciaValuadaCostoDolares(BigDecimal tenenciaValuadaCostoDolares) {
		this.tenenciaValuadaCostoDolares = tenenciaValuadaCostoDolares;
	}

	/**
	 * Gets the resultado pesos.
	 *
	 * @return the resultadoPesos
	 */
	public BigDecimal getResultadoPesos() {
		return resultadoPesos;
	}

	/**
	 * Sets the resultado pesos.
	 *
	 * @param resultadoPesos
	 *            the resultadoPesos to set
	 */
	public void setResultadoPesos(BigDecimal resultadoPesos) {
		this.resultadoPesos = resultadoPesos;
	}

	/**
	 * Gets the resultado dolares.
	 *
	 * @return the resultadoDolares
	 */
	public BigDecimal getResultadoDolares() {
		return resultadoDolares;
	}

	/**
	 * Sets the resultado dolares.
	 *
	 * @param resultadoDolares
	 *            the resultadoDolares to set
	 */
	public void setResultadoDolares(BigDecimal resultadoDolares) {
		this.resultadoDolares = resultadoDolares;
	}

	/**
	 * Gets the tenencia reexpresada pesos.
	 *
	 * @return the tenenciaReexpresadaPesos
	 */
	public BigDecimal getTenenciaReexpresadaPesos() {
		return tenenciaReexpresadaPesos;
	}

	/**
	 * Sets the tenencia reexpresada pesos.
	 *
	 * @param tenenciaReexpresadaPesos
	 *            the tenenciaReexpresadaPesos to set
	 */
	public void setTenenciaReexpresadaPesos(BigDecimal tenenciaReexpresadaPesos) {
		this.tenenciaReexpresadaPesos = tenenciaReexpresadaPesos;
	}

	/**
	 * Gets the tenencia reexpresada dolares.
	 *
	 * @return the tenenciaReexpresadaDolares
	 */
	public BigDecimal getTenenciaReexpresadaDolares() {
		return tenenciaReexpresadaDolares;
	}

	/**
	 * Sets the tenencia reexpresada dolares.
	 *
	 * @param tenenciaReexpresadaDolares
	 *            the tenenciaReexpresadaDolares to set
	 */
	public void setTenenciaReexpresadaDolares(BigDecimal tenenciaReexpresadaDolares) {
		this.tenenciaReexpresadaDolares = tenenciaReexpresadaDolares;
	}

	/**
	 * Adds the tenencia valuada hoy pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaValuadaHoyPesos(BigDecimal valor) {
		this.tenenciaValuadaHoyPesos = this.tenenciaValuadaHoyPesos.add(valor);
	}

	/**
	 * Adds the tenencia valuada hoy dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaValuadaHoyDolares(BigDecimal valor) {
		this.tenenciaValuadaHoyDolares = this.tenenciaValuadaHoyDolares.add(valor);
	}

	/**
	 * Adds the tenencia valuada costo pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaValuadaCostoPesos(BigDecimal valor) {
		if(valor != null && this.tenenciaValuadaCostoPesos!= null){
		        this.tenenciaValuadaCostoPesos = this.tenenciaValuadaCostoPesos.add(valor);
		}else{
			//SI EN ALGUNO DE LOS ADD VIENE NUL, AUTOMATICAMENTE DEJO EN NULL LA SUMATORIA
			//PARA QUE DESPUES SE LE COLOQUE UN GUION EN LA VISTA
			this.tenenciaValuadaCostoPesos = null;
		}
	}

	/**
	 * Adds the tenencia valuada costo dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaValuadaCostoDolares(BigDecimal valor) {
		if(valor != null && this.tenenciaValuadaCostoDolares != null){
			this.tenenciaValuadaCostoDolares = this.tenenciaValuadaCostoDolares.add(valor);
		}else{
			//SI EN ALGUNO DE LOS ADD VIENE NUL, AUTOMATICAMENTE DEJO EN NULL LA SUMATORIA
			//PARA QUE DESPUES SE LE COLOQUE UN GUION EN LA VISTA
			this.tenenciaValuadaCostoDolares = null;
		}
	}

	/**
	 * Adds the resultado pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addResultadoPesos(BigDecimal valor) {
		this.resultadoPesos = this.resultadoPesos.add(valor);
	}

	/**
	 * Adds the resultado dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addResultadoDolares(BigDecimal valor) {
		this.resultadoDolares = this.resultadoDolares.add(valor);
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Adds the tenencia reexpresada pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaReexpresadaPesos(BigDecimal valor) {
		this.tenenciaReexpresadaPesos = this.tenenciaReexpresadaPesos.add(valor);
	}

	/**
	 * Adds the tenencia reexpresada dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaReexpresadaDolares(BigDecimal valor) {
		this.tenenciaReexpresadaDolares = this.tenenciaReexpresadaDolares.add(valor);
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the codigo error.
	 *
	 * @param codigoError
	 *            the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the ordinal.
	 *
	 * @return the ordinal
	 */
	public int getOrdinal() {
		if ("PF".equals(this.getProducto())) {
			return 1;
		}
		if ("FCI".equals(this.getProducto())) {
			return 2;
		}
		if ("TV".equals(this.getProducto())) {
			return 3;
		}
		if ("CUS".equals(this.getProducto())) {
			return 4;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TenenciaPorProductoDTO tenenciaFondoSuscritoDTO) {
		if (this.getOrdinal() < tenenciaFondoSuscritoDTO.getOrdinal()) {
			return -1;
		} else {
			return 1;
		}
	}
}
