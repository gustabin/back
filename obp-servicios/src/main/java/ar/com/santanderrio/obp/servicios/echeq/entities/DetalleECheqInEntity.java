package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class DetalleECheqInEntity.
 */
public class DetalleECheqInEntity {

	/** The cheque id. */
	private String chequeId;
	
	/** The cuit. */
	private String cuit;
	
	/** The j session id. */
	private String jSessionId;

	/**
	 * Instantiates a new detalle E cheq in entity.
	 */
	public DetalleECheqInEntity() {
		super();
	}
	
	
	/**
	 * Instantiates a new detalle E cheq in entity.
	 *
	 * @param chequeId the cheque id
	 * @param cuit the cuit
	 * @param jSessionId the j session id
	 */
	public DetalleECheqInEntity(String chequeId, String cuit, String jSessionId) {
		super();
		this.chequeId = chequeId;
		this.cuit = cuit;
		this.jSessionId = jSessionId;
	}

	/**
	 * Gets the numero cheque.
	 *
	 * @return the numero cheque
	 */
	public String getChequeId() {
		return chequeId;
	}

	/**
	 * Sets the numero cheque.
	 *
	 * @param numeroCheque the new numero cheque
	 */
	public void setChequeId(String numeroCheque) {
		this.chequeId = numeroCheque;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the j session id.
	 *
	 * @return the j session id
	 */
	public String getjSessionId() {
		return jSessionId;
	}

	/**
	 * Sets the j session id.
	 *
	 * @param jSessionId the new j session id
	 */
	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

}
