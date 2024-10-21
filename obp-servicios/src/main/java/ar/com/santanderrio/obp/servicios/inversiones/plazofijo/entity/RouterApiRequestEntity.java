/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ConsultaTiposPlazoFijoEntity.
 *
 * @author juan.pablo.picate
 */
public class RouterApiRequestEntity {
	/** The cics. */
	@JsonProperty("cics")
	private String cics;

	/** The transactionId. */
	@JsonProperty("transactionId")
	private String transactionId;

	/** The password. */
	@JsonProperty("password")
	private String password;
	
	/** The body. */
	@JsonProperty("body")
	private String body;


	public void setCics(String cics) {
		this.cics = cics;
	}
	
	public String getCics() {
		return cics;
	}
	
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getBody() {
		return body;
	}
}
