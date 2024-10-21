package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

import java.util.List;

public class HoldingsSummaryResponse {

    private List<HoldingsSummary> summary;

    public List<HoldingsSummary> getSummary() {
        return summary;
    }

    public void setSummary(List<HoldingsSummary> summary) {
        this.summary = summary;
    }

}
