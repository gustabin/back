package ar.com.santanderrio.obp.servicios.echeq.dto;

import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;

/**
 * The Class ECheqDTO.
 */
public class ECheqDTO {

	/** The echeq validado. */
	private Cheque echeqValidado;

	/** The beneficiario validado. */
	private BeneficiarioDTO beneficiarioValidado;

	/**
	 * Gets the echeq.
	 *
	 * @return the echeq
	 */
	public Cheque getEcheqValidado() {
		return echeqValidado;
	}

	/**
	 * Sets the echeq.
	 *
	 * @param echeq the new echeq
	 */
	public void setEcheqValidado(Cheque echeq) {
		this.echeqValidado = echeq;
	}

	/**
	 * Gets the beneficiario echeq validado.
	 *
	 * @return the beneficiario echeq validado
	 */
	public BeneficiarioDTO getBeneficiarioValidado() {
		return beneficiarioValidado;
	}

	/**
	 * Sets the beneficiario echeq validado.
	 *
	 * @param beneficiarioEcheqValidado the new beneficiario echeq validado
	 */
	public void setBeneficiarioValidado(BeneficiarioDTO beneficiarioEcheqValidado) {
		this.beneficiarioValidado = beneficiarioEcheqValidado;
	}

}
