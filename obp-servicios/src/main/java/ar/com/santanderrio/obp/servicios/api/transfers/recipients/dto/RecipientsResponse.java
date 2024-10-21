package ar.com.santanderrio.obp.servicios.api.transfers.recipients.dto;

import ar.com.santanderrio.obp.servicios.api.transfers.recipients.model.Recipient;

import java.util.List;

public class RecipientsResponse {

    private List<Recipient> recipients;
    private long offset;
    private int limit;
    private long total;

    public List<Recipient> getRecipients() {
        return recipients;
    }
    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
