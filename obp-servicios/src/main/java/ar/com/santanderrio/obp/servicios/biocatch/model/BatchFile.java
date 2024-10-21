package ar.com.santanderrio.obp.servicios.biocatch.model;

import java.math.BigDecimal;

public class BatchFile {

    private String creationDate;

    private String editionDate;

    private String id;

    private int payeesNumber;

    private BigDecimal activityAmountTotal;


    public BatchFile(String creationDate, String editionDate, String id, int payeesNumber, BigDecimal activityAmountTotal) {
        this.creationDate = creationDate;
        this.editionDate = editionDate;
        this.id = id;
        this.payeesNumber = payeesNumber;
        this.activityAmountTotal = activityAmountTotal;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(String editionDate) {
        this.editionDate = editionDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPayeesNumber() {
        return payeesNumber;
    }

    public void setPayeesNumber(int payeesNumber) {
        this.payeesNumber = payeesNumber;
    }

    public BigDecimal getActivityAmountTotal() {
        return activityAmountTotal;
    }

    public void setActivityAmountTotal(BigDecimal activityAmountTotal) {
        this.activityAmountTotal = activityAmountTotal;
    }
}
