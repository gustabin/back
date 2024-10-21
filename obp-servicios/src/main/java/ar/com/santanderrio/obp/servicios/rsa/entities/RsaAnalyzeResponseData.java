/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;

/**
 * The Class RsaAnalyzeResponseData.
 *
 * @author Ignacio_Valek
 */
public class RsaAnalyzeResponseData extends Entity implements RsaResponseData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The rsa generic response data. */
	private RsaGenericResponseData rsaGenericResponseData;

	/** The id user status. */
	private String idUserStatus;

	/** The id user type. */
	private String idUserType;

	/** The rr action code. */
	// triggeredRule
	@Deprecated
	private String rrActionCode;

	/** The action code. */
	private ActionCode actionCode;

	/** The rr action name. */
	private String rrActionName;

	/**
	 * The rule id. Se utiliza en el caso de transferencias para el rule fact
	 * TRANSFERENCIA24
	 */
	private String ruleId;

	/**
	 * The rule name. Se utiliza en el caso de transferencias para el rule fact
	 * TRANSFERENCIA24
	 */
	private String ruleName;

	/**
	 * Instantiates a new rsa analyze response data.
	 */
	public RsaAnalyzeResponseData() {
		this.rsaGenericResponseData = new RsaGenericResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.rsa.entities.RsaResponseData#
	 * getRsaGenericResponseData()
	 */
	@Override
	public RsaGenericResponseData getRsaGenericResponseData() {
		return this.rsaGenericResponseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.rsa.entities.RsaResponseData#
	 * setRsaGenericResponseData(ar.com.santanderrio.obp.rsa.entities.
	 * RsaGenericResponseData)
	 */
	@Override
	public void setRsaGenericResponseData(RsaGenericResponseData rsaGenericResponseData) {
		this.rsaGenericResponseData = rsaGenericResponseData;
	}

	/**
	 * Gets the id user status.
	 *
	 * @return the id user status
	 */
	public String getIdUserStatus() {
		return idUserStatus;
	}

	/**
	 * Setter para id user status.
	 *
	 * @param idUserStatus
	 *            el nuevo id user status
	 */
	public void setIdUserStatus(String idUserStatus) {
		this.idUserStatus = idUserStatus;
	}

	/**
	 * Gets the id user type.
	 *
	 * @return the id user type
	 */
	public String getIdUserType() {
		return idUserType;
	}

	/**
	 * Setter para id user type.
	 *
	 * @param idUserType
	 *            el nuevo id user type
	 */
	public void setIdUserType(String idUserType) {
		this.idUserType = idUserType;
	}

	/**
	 * Gets the rr action code.
	 *
	 * @return the rr action code
	 */
	@Deprecated
	public String getRrActionCode() {
		return rrActionCode;
	}

	/**
	 * Setter para rr action code.
	 *
	 * @param rrActionCode
	 *            el nuevo rr action code
	 */
	@Deprecated
	public void setRrActionCode(String rrActionCode) {
		this.rrActionCode = rrActionCode;
	}

	/**
	 * Gets the rr action name.
	 *
	 * @return the rr action name
	 */
	public String getRrActionName() {
		return rrActionName;
	}

	/**
	 * Setter para rr action name.
	 *
	 * @param rrActionName
	 *            el nuevo rr action name
	 */
	public void setRrActionName(String rrActionName) {
		this.rrActionName = rrActionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RsaAnalyzeResponseData ["
				+ (rsaGenericResponseData != null ? "rsaGenericResponseData=" + rsaGenericResponseData + ", " : "")
				+ (idUserStatus != null ? "idUserStatus=" + idUserStatus + ", " : "")
				+ (idUserType != null ? "idUserType=" + idUserType + ", " : "")
				+ (actionCode != null ? "actionCode=" + actionCode + ", " : "")
				+ (rrActionCode != null ? "rrActionCode=" + rrActionCode + ", " : "")
				+ (rrActionName != null ? "rrActionName=" + rrActionName : "") + "]";
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idUserStatus == null) ? 0 : idUserStatus.hashCode());
		result = prime * result + ((idUserType == null) ? 0 : idUserType.hashCode());
		result = prime * result + ((rrActionCode == null) ? 0 : rrActionCode.hashCode());
		result = prime * result + ((rrActionName == null) ? 0 : rrActionName.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		RsaAnalyzeResponseData other = (RsaAnalyzeResponseData) obj;
		return new EqualsBuilder().append(idUserStatus, other.idUserStatus).append(idUserType, other.idUserType)
				.append(rrActionCode, other.rrActionCode).append(rrActionName, other.rrActionName).isEquals();
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
		this.ruleId = ruleId;
	}

	/**
	 * Gets the rule name.
	 *
	 * @return the rule name
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * Sets the rule name.
	 *
	 * @param ruleName
	 *            the new rule name
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

}
