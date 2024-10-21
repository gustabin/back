package ar.com.santanderrio.obp.servicios.api.funds.entities.salespoint;

import java.util.List;

public class FundsSalespointEntity {

    private String id;

    private String custodyCode;

    private String depositaryCode;

    private String typeOrder;

    private String subTypeOrder;

    private List<FundsSalespointInfoEntity> info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustodyCode() {
        return custodyCode;
    }

    public void setCustodyCode(String custodyCode) {
        this.custodyCode = custodyCode;
    }

    public String getDepositaryCode() {
        return depositaryCode;
    }

    public void setDepositaryCode(String depositaryCode) {
        this.depositaryCode = depositaryCode;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }

    public String getSubTypeOrder() {
        return subTypeOrder;
    }

    public void setSubTypeOrder(String subTypeOrder) {
        this.subTypeOrder = subTypeOrder;
    }

    public List<FundsSalespointInfoEntity> getInfo() {
        return info;
    }

    public void setInfo(List<FundsSalespointInfoEntity> info) {
        this.info = info;
    }
}