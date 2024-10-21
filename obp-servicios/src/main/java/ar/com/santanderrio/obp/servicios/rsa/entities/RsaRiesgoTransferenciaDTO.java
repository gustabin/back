package ar.com.santanderrio.obp.servicios.rsa.entities;

import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;

/**
 * The Class RsaRiesgoTransferenciaDTO.
 */
public class RsaRiesgoTransferenciaDTO {

	/** The prefijoRuleTransferenciaRiesgoAlto. */
	private String prefijoRuleTransferenciaRiesgoAlto;

	/** The action code. */
	ActionCode actionCode;

	/** The rule id. */
	String ruleId;

	/** The riesgo alto. */
	Boolean riesgoAlto;

	/**
	 * Instantiates a new rsa riesgo transferencia DTO.
	 *
	 * @param actionCode
	 *            the action code
	 * @param riesgoAlto
	 *            the riesgo alto
	 * @param prefijoRuleTransferenciaRiesgoAlto 
	 *            the prefijoRuleTransferenciaRiesgoAlto
	 */
	public RsaRiesgoTransferenciaDTO(ActionCode actionCode, Boolean riesgoAlto, String prefijoRuleTransferenciaRiesgoAlto) {
		super();
		this.actionCode = actionCode;
		this.riesgoAlto = riesgoAlto;
		this.prefijoRuleTransferenciaRiesgoAlto = prefijoRuleTransferenciaRiesgoAlto;
	}

	/**
	 * Gets the action code.
	 *
	 * @return the action code
	 */
	public ActionCode getActionCode() {
		return actionCode;
	}

	/**
	 * Sets the action code.
	 *
	 * @param actionCode
	 *            the new action code
	 */
	public void setActionCode(ActionCode actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * Gets the riesgo alto.
	 *
	 * @return the riesgo alto
	 */
	public Boolean getRiesgoAlto() {
		return riesgoAlto;
	}

	/**
	 * Sets the riesgo alto.
	 *
	 * @param riesgoAlto
	 *            the new riesgo alto
	 */
	public void setRiesgoAlto(Boolean riesgoAlto) {
		this.riesgoAlto = riesgoAlto;
	}

	/**
	 * Gets the rule id.
	 *
	 * @return the rule id
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * Sets the rule id.
	 *
	 * @param ruleId
	 *            the new rule id
	 */
	public void setRuleId(String ruleId) {
		if (ruleId != null) {
			riesgoAlto = ruleId.trim().startsWith(this.prefijoRuleTransferenciaRiesgoAlto);
		} else {
			riesgoAlto = Boolean.FALSE;
		}
		this.ruleId = ruleId;
	}

}
