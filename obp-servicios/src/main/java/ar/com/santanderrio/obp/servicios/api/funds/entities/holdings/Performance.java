package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

public class Performance {

    private String fundCode;

    private String holding;

    private double pppFifo;

    private double actualShareValue;

    private double costHoldingValue;

    private double actualHoldingValue;

    private double result;

    private double resultPercent;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getHolding() {
        return holding;
    }

    public void setHolding(String holding) {
        this.holding = holding;
    }

    public double getPppFifo() {
        return pppFifo;
    }

    public void setPppFifo(double pppFifo) {
        this.pppFifo = pppFifo;
    }

    public double getActualShareValue() {
        return actualShareValue;
    }

    public void setActualShareValue(double actualShareValue) {
        this.actualShareValue = actualShareValue;
    }

    public double getCostHoldingValue() {
        return costHoldingValue;
    }

    public void setCostHoldingValue(double costHoldingValue) {
        this.costHoldingValue = costHoldingValue;
    }

    public double getActualHoldingValue() {
        return actualHoldingValue;
    }

    public void setActualHoldingValue(double actualHoldingValue) {
        this.actualHoldingValue = actualHoldingValue;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getResultPercent() {
        return resultPercent;
    }

    public void setResultPercent(double resultPercent) {
        this.resultPercent = resultPercent;
    }

}
