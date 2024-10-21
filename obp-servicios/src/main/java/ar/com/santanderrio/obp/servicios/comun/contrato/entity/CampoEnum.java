/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contrato.entity;

/**
 * Enum. empleado para representar el valor del campo "P_campo", utilizado para
 * el SP. de Contrato (HB_PKG_CONTRATOS).
 * 
 * @author pablo.d.gargaglione
 *
 */
public enum CampoEnum {

	/** The cpi fci c. */
	CPI_FCI_C("CPI_FCI_C", "valor P_campo utilizado para Contratos"),

	/** The cpi fci t. */
	CPI_FCI_T("CPI_FCI_T", "valor P_campo utilizado para transferencias"),

	/** The opprog. */
	OPPROG("OPPROG", "valor P_campo utilizado para Tarjetas"),

	/** The adhesion_billetera. */
	ADHESION_BILLETERA("ADHESION_BILLETERA", "valor P_campo utilizado para Billetera"),

	/** The cpi acc c. */
	CPI_ACC_C("CPI_ACC_C", "valor P_campo utilizado para orden de compra"),

	/** The cpi bon c. */
	CPI_BON_C("CPI_BON_C", "valor P_campo utilizado para orden de compra"),
	
	/** The echeq. */
	ECHEQ("ECHEQ", "valor P_campo utilizado para orden de compra"),

	/** The aceptacion_tyc_echeq. */
	ACEPTACION_TYC_ECHEQ("ACEPTACION_TYC_ECHEQ", "valor P_campo utilizado para aceptacion de terminos y condiciones de echeq");
	
	/** The campo. */
	String campo;

	/** The descripcion. */
	String descripcion;

	/**
	 * Instantiates a new campo enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	CampoEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
