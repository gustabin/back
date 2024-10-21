/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

/**
 * The Class CuitIn.
 */
public class CuitIn {
	
	/** The input banco. */
	private String inputBanco;
	
	/** The input sucursal. */
	private String inputSucursal;
	
	/** The input CP. */
	private String inputCP;
	
	/** The input verificador 1. */
	private String inputVerificador1;
	
	/** The input cheque. */
	private String inputCheque;
	
	/** The input verificador 2. */
	private String inputVerificador2;
	
	/** The input cuenta. */
	private String inputCuenta;
	
	/** The input verificador 3. */
	private String inputVerificador3;

	/**
	 * Instantiates a new cuit in.
	 *
	 * @param nroCheque
	 *            the nro cheque
	 */
	public CuitIn(String nroCheque) {
		inputBanco = nroCheque.substring(0, 3);
		inputSucursal = nroCheque.substring(3, 6);
		inputCP = nroCheque.substring(6, 10);
		inputVerificador1 = nroCheque.substring(10, 11);
		inputCheque = nroCheque.substring(11, 19);
		inputVerificador2 = nroCheque.substring(19, 20);
		inputCuenta = nroCheque.substring(20, 31);
		inputVerificador3 = nroCheque.substring(31, 32);
	}
	
	/**
	 * Instantiates a new cuit in.
	 */
	public CuitIn() {
		super();
	}

	/**
	 * Gets the input banco.
	 *
	 * @return the input banco
	 */
	public String getInputBanco() {
		return inputBanco;
	}

	/**
	 * Sets the input banco.
	 *
	 * @param inputBanco
	 *            the new input banco
	 */
	public void setInputBanco(String inputBanco) {
		this.inputBanco = inputBanco;
	}

	/**
	 * Gets the input sucursal.
	 *
	 * @return the input sucursal
	 */
	public String getInputSucursal() {
		return inputSucursal;
	}

	/**
	 * Sets the input sucursal.
	 *
	 * @param inputSucursal
	 *            the new input sucursal
	 */
	public void setInputSucursal(String inputSucursal) {
		this.inputSucursal = inputSucursal;
	}

	/**
	 * Gets the input CP.
	 *
	 * @return the input CP
	 */
	public String getInputCP() {
		return inputCP;
	}

	/**
	 * Sets the input CP.
	 *
	 * @param inputCP
	 *            the new input CP
	 */
	public void setInputCP(String inputCP) {
		this.inputCP = inputCP;
	}

	/**
	 * Gets the input verificador 1.
	 *
	 * @return the input verificador 1
	 */
	public String getInputVerificador1() {
		return inputVerificador1;
	}

	/**
	 * Sets the input verificador 1.
	 *
	 * @param inputVerificador1
	 *            the new input verificador 1
	 */
	public void setInputVerificador1(String inputVerificador1) {
		this.inputVerificador1 = inputVerificador1;
	}

	/**
	 * Gets the input cheque.
	 *
	 * @return the input cheque
	 */
	public String getInputCheque() {
		return inputCheque;
	}

	/**
	 * Sets the input cheque.
	 *
	 * @param inputCheque
	 *            the new input cheque
	 */
	public void setInputCheque(String inputCheque) {
		this.inputCheque = inputCheque;
	}

	/**
	 * Gets the input verificador 2.
	 *
	 * @return the input verificador 2
	 */
	public String getInputVerificador2() {
		return inputVerificador2;
	}

	/**
	 * Sets the input verificador 2.
	 *
	 * @param inputVerificador2
	 *            the new input verificador 2
	 */
	public void setInputVerificador2(String inputVerificador2) {
		this.inputVerificador2 = inputVerificador2;
	}

	/**
	 * Gets the input cuenta.
	 *
	 * @return the input cuenta
	 */
	public String getInputCuenta() {
		return inputCuenta;
	}

	/**
	 * Sets the input cuenta.
	 *
	 * @param inputCuenta
	 *            the new input cuenta
	 */
	public void setInputCuenta(String inputCuenta) {
		this.inputCuenta = inputCuenta;
	}

	/**
	 * Gets the input verificador 3.
	 *
	 * @return the input verificador 3
	 */
	public String getInputVerificador3() {
		return inputVerificador3;
	}

	/**
	 * Sets the input verificador 3.
	 *
	 * @param inputVerificador3
	 *            the new input verificador 3
	 */
	public void setInputVerificador3(String inputVerificador3) {
		this.inputVerificador3 = inputVerificador3;
	}

}
