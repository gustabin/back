/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.web.view;

import javax.xml.bind.annotation.XmlRootElement;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ChatInView.
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatInView {

	/** The operacion goto chat. */
	private String operacionGotoChat;

	/**
	 * Gets the operacion goto.
	 *
	 * @return the operacion goto
	 */
	public String getOperacionGoto() {
		return operacionGotoChat;
	}

	/**
	 * Sets the operacion goto.
	 *
	 * @param operacionGoto the new operacion goto
	 */
	public void setOperacionGoto(String operacionGoto) {
		this.operacionGotoChat = operacionGoto;
	}

}
