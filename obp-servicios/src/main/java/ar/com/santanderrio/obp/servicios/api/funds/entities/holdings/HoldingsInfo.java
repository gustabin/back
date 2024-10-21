package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

import java.util.List;

public class HoldingsInfo {

    private List<HoldingsData> holdings;

    private List<HoldingsTotal> holdingsTotal;

    private List<HoldingsLegal> holdingsLegals;

    public List<HoldingsData> getHoldings() { return holdings; }

    public void setHoldings(List<HoldingsData> value) { this.holdings = value; }

    public List<HoldingsTotal> getHoldingsTotal() { return holdingsTotal; }

    public void setHoldingsTotal(List<HoldingsTotal> value) { this.holdingsTotal = value; }

    public List<HoldingsLegal> getHoldingsLegals() { return holdingsLegals; }

    public void setHoldingsLegals(List<HoldingsLegal> value) { this.holdingsLegals = value; }

}
