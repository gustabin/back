package ar.com.santanderrio.obp.servicios.delete.account.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DeleteAccountRequest {

    private String simulate;
    private String cancellationReason;
    private String keepsAccount;
    private String nup;
    private String formattedAccountId;
    

    public DeleteAccountRequest(Builder builder) {
        this.simulate = builder.simulate;
        this.cancellationReason = builder.cancellationReason;
        this.keepsAccount = builder.keepsAccount;
        this.nup = builder.nup;
        this.formattedAccountId = builder.formattedAccountId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String simulate;
        private String cancellationReason;
        private String keepsAccount;
        private String nup;
        private String formattedAccountId;

        public Builder simulate(String simulate) {
            this.simulate = simulate;
            return this;
        }

        public Builder cancellationReason(String cancellationReason) {
            this.cancellationReason = cancellationReason;
            return this;
        }
        
        public Builder keepsAccount(String keepsAccount) {
            this.keepsAccount = keepsAccount;
            return this;
        }

        public Builder nup(String nup) {
            this.nup = nup;
            return this;
        }
        
        public Builder formattedAccountId(String formattedAccountId) {
            this.formattedAccountId = formattedAccountId;
            return this;
        }
        
        public DeleteAccountRequest build() {
            return new DeleteAccountRequest(this);
        }

    }

	/**
	 * @return the simulate
	 */
	public String getSimulate() {
		return simulate;
	}

	/**
	 * @param simulate the simulate to set
	 */
	public void setSimulate(String simulate) {
		this.simulate = simulate;
	}

	/**
	 * @return the cancellationReason
	 */
	public String getCancellationReason() {
		return cancellationReason;
	}

	/**
	 * @param cancellationReason the cancellationReason to set
	 */
	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	/**
	 * @return the keepsAccount
	 */
	public String getKeepsAccount() {
		return keepsAccount;
	}

	/**
	 * @param keepsAccount the keepsAccount to set
	 */
	public void setKeepsAccount(String keepsAccount) {
		this.keepsAccount = keepsAccount;
	}

	/**
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * @param nup the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * @return the formattedAccountId
	 */
	public String getFormattedAccountId() {
		return formattedAccountId;
	}

	/**
	 * @param formattedAccountId the formattedAccountId to set
	 */
	public void setFormattedAccountId(String formattedAccountId) {
		this.formattedAccountId = formattedAccountId;
	}

}
