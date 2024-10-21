/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

/**
 * The Class ChanceDTO.
 *
 */
public class ChanceDTO {
	
	/** The constante COBRO POR SUELDO. */
	public static final String COBRO_POR_SUELDO = "Cobro por sueldo u honorario";
	
	/** The constante COMPRA SANT DEBITO. */
	public static final String COMPRA_SANT_DEBITO = "Compras con Santander Debito";
	
	/** The constante COMPRA SANT VISA. */
	public static final String COMPRA_SANT_VISA = "Compras con Santander Visa";
	
	/** The constante COMPRA SANT AMERICAN EXPRESS. */
	public static final String COMPRA_SANT_AMERICAN_EXPRESS = "Compras con Santander American Express";

	public static final String COMPRA_SANT_MASTER = "Compras con Santander Mastercard";
	
	/** The constante TOTAL. */
	public static final String TOTAL = "Total";
	
	/** The constante ACCION. */
	public static final String ACCION = "Accion";
	
	/** The constante CHANCE. */
	public static final String CHANCES = "Chances";
	
	/** The label. */
	private String label;
	
	/** The valorAnterior. */
	private String valorAnterior;
	
	/** The valorActual. */
	private String valorActual;

	/**
	 *Constructor chance DTO
	 *
	 * @param the label
	 * @param the valor Anterior 
	 * @param the valor Actual  
	 */
	public ChanceDTO(String label,String valorAnterior,String valorActual) {
		this.label = label;
		this.valorAnterior = valorAnterior;
		this.valorActual = valorActual;
	}
	/**
	 *Gets label
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 *Sets label
	 *
	 * @param the label 
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 *Gets valorAnterior
	 *
	 * @return the valorAnterior
	 */
	public String getValorAnterior() {
		return valorAnterior;
	}

	/**
	 *Sets valorAnterior
	 *
	 * @param the valorAnterior 
	 */
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	/**
	 *Gets valorActual
	 *
	 * @return the valorActual
	 */
	public String getValorActual() {
		return valorActual;
	}

	/**
	 *Sets valorActual
	 *
	 * @param the valorActual 
	 */
	public void setValorActual(String valorActual) {
		this.valorActual = valorActual;
	}
	
}
