package ar.com.santanderrio.obp.servicios.api.funds.entities.salespoint;

import java.util.List;

public class FundsSalespointResponseEntity {

    private String id;

    private String name;

    private List<FundsSalespointEntity> funds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FundsSalespointEntity> getFunds() {
        return funds;
    }

    public void setFunds(List<FundsSalespointEntity> funds) {
        this.funds = funds;
    }

}
