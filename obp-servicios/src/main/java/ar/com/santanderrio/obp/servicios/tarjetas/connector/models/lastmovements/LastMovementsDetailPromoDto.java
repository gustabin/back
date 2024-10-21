package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements;

public class LastMovementsDetailPromoDto {

    private String visaCampaignCode;
    private String bankCampaignCode;
    private String campaignDenomination;
    private Double userPercentage;
    private Double establishmentPercentage;
    private Double userMaximumDiscountPercentage;
    private Double establishmentMaximumPercentage;
    private Double userDiscount;
    private Double establishmentDiscount;

    public String getVisaCampaignCode() {
        return visaCampaignCode;
    }

    public void setVisaCampaignCode(String visaCampaignCode) {
        this.visaCampaignCode = visaCampaignCode;
    }

    public String getBankCampaignCode() {
        return bankCampaignCode;
    }

    public void setBankCampaignCode(String bankCampaignCode) {
        this.bankCampaignCode = bankCampaignCode;
    }

    public String getCampaignDenomination() {
        return campaignDenomination;
    }

    public void setCampaignDenomination(String campaignDenomination) {
        this.campaignDenomination = campaignDenomination;
    }

    public Double getUserPercentage() {
        return userPercentage;
    }

    public void setUserPercentage(Double userPercentage) {
        this.userPercentage = userPercentage;
    }

    public Double getEstablishmentPercentage() {
        return establishmentPercentage;
    }

    public void setEstablishmentPercentage(Double establishmentPercentage) {
        this.establishmentPercentage = establishmentPercentage;
    }

    public Double getUserMaximumDiscountPercentage() {
        return userMaximumDiscountPercentage;
    }

    public void setUserMaximumDiscountPercentage(Double userMaximumDiscountPercentage) {
        this.userMaximumDiscountPercentage = userMaximumDiscountPercentage;
    }

    public Double getEstablishmentMaximumPercentage() {
        return establishmentMaximumPercentage;
    }

    public void setEstablishmentMaximumPercentage(Double establishmentMaximumPercentage) {
        this.establishmentMaximumPercentage = establishmentMaximumPercentage;
    }

    public Double getUserDiscount() {
        return userDiscount;
    }

    public void setUserDiscount(Double userDiscount) {
        this.userDiscount = userDiscount;
    }

    public Double getEstablishmentDiscount() {
        return establishmentDiscount;
    }

    public void setEstablishmentDiscount(Double establishmentDiscount) {
        this.establishmentDiscount = establishmentDiscount;
    }

	@Override
	public String toString() {
		return "LastMovementsDetailPromoDto [visaCampaignCode=" + visaCampaignCode + ", bankCampaignCode="
				+ bankCampaignCode + ", campaignDenomination=" + campaignDenomination + ", userPercentage="
				+ userPercentage + ", establishmentPercentage=" + establishmentPercentage
				+ ", userMaximumDiscountPercentage=" + userMaximumDiscountPercentage
				+ ", establishmentMaximumPercentage=" + establishmentMaximumPercentage + ", userDiscount="
				+ userDiscount + ", establishmentDiscount=" + establishmentDiscount + "]";
	}
    
}