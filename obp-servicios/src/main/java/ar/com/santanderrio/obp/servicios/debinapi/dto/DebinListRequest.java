package ar.com.santanderrio.obp.servicios.debinapi.dto;

import java.util.List;

public class DebinListRequest {
    private DebinListMode mode;
    private String buyerCuit;
    private String sellerCuit;
    private DebinStatus status;
    private List<DebinType> types;
    private String buyerCbu;
    private String sellerCbu;
    private String pageSize;
    private String pageNumber;
    private String creationFrom;
    private String creationTo;
    private String clientIp;
    private String recurrenceId;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public DebinListMode getMode() {
        return mode;
    }

    public String getBuyerCuit() {
        return buyerCuit;
    }

    public String getSellerCuit() {
        return sellerCuit;
    }

    public DebinStatus getStatus() {
        return status;
    }

    public List<DebinType> getTypes() {
        return types;
    }

    public String getBuyerCbu() {
        return buyerCbu;
    }

    public String getSellerCbu() {
        return sellerCbu;
    }

    public String getPageSize() {
        return pageSize;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public String getCreationFrom() {
        return creationFrom;
    }

    public String getCreationTo() {
        return creationTo;
    }

    public String getRecurrenceId() { return recurrenceId; }

    public void setMode(DebinListMode mode) {
        this.mode = mode;
    }

    public void setBuyerCuit(String buyerCuit) {
        this.buyerCuit = buyerCuit;
    }

    public void setSellerCuit(String sellerCuit) {
        this.sellerCuit = sellerCuit;
    }

    public void setStatus(DebinStatus status) {
        this.status = status;
    }

    public void setTypes(List<DebinType> types) {
        this.types = types;
    }

    public void setBuyerCbu(String buyerCbu) {
        this.buyerCbu = buyerCbu;
    }

    public void setSellerCbu(String sellerCbu) {
        this.sellerCbu = sellerCbu;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setCreationFrom(String creationFrom) {
        this.creationFrom = creationFrom;
    }

    public void setCreationTo(String creationTo) {
        this.creationTo = creationTo;
    }

    public void setRecurrenceId(String recurrenceId) { this.recurrenceId = recurrenceId; }
}
