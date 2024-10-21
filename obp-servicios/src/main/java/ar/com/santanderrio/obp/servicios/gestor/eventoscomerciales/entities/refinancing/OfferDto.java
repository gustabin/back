package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing;


import java.math.BigDecimal;
import java.util.List;

public class OfferDto {

    private Long offerId;

    private String nup;

    private String state;

    private String type;

    private String validityDate;

    private BigDecimal maxAmount;

    private List<TermResponseDto> terms;

    private List<RefinancingProductDto> products;

    private RefinancingResponseDto refinancing;


    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<TermResponseDto> getTerms() {
        return terms;
    }

    public void setTerms(List<TermResponseDto> terms) {
        this.terms = terms;
    }

    public List<RefinancingProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<RefinancingProductDto> products) {
        this.products = products;
    }

    public RefinancingResponseDto getRefinancing() {
        return refinancing;
    }

    public void setRefinancing(RefinancingResponseDto refinancing) {
        this.refinancing = refinancing;
    }
}
