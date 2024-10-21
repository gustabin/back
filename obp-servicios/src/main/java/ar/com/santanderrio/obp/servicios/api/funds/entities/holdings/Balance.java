package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

public class Balance {

    private  String shares;

    private  String price;

    private  String currency;

    private  String status;

    private  String amount;

    private  String sharesAvailable;

    private  String amountAvailable;

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSharesAvailable() {
        return sharesAvailable;
    }

    public void setSharesAvailable(String sharesAvailable) {
        this.sharesAvailable = sharesAvailable;
    }

    public String getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(String amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

}
