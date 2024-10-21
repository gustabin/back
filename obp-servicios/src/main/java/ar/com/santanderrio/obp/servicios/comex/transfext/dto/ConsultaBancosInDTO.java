/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

/**
 * The Class ConsultaBancosInDTO.
 */
public class ConsultaBancosInDTO {
	
	/** The tipo de codigo. */
	private String tipoCodigo;
	
	/** The codigo bancario. */
	private String codigoBancario;
	
	
	/**
	 * Gets the tipo codigo.
	 *
	 * @return the tipoCodigo
	 */
	public String getTipoCodigo() {
		return tipoCodigo;
	}
	
	/**
	 * Sets the tipo codigo.
	 *
	 * @param tipoCodigo
	 *            the tipoCodigo to set
	 */
	public void setTipoCodigo(String tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}
	
	/**
	 * Gets the codigo bancario.
	 *
	 * @return the codigoBancario
	 */
	public String getCodigoBancario() {
		return codigoBancario;
	}
	
	/**
	 * Sets the codigo bancario.
	 *
	 * @param codigoBancario
	 *            the codigoBancario to set
	 */
	public void setCodigoBancario(String codigoBancario) {
		this.codigoBancario = codigoBancario;
	}

}
