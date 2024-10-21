package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard;

public class AccountPercentageLimitDto extends PercentageLimitsDto {
    private Integer bonusPercentageAtm;

    private Integer bonusPercentage;

	public Integer getBonusPercentageAtm() {
		return bonusPercentageAtm;
	}

	public void setBonusPercentageAtm(Integer bonusPercentageAtm) {
		this.bonusPercentageAtm = bonusPercentageAtm;
	}

	public Integer getBonusPercentage() {
		return bonusPercentage;
	}

	public void setBonusPercentage(Integer bonusPercentage) {
		this.bonusPercentage = bonusPercentage;
	}
}