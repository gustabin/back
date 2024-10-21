package ar.com.santanderrio.obp.servicios.api.transfers.recipients;

import ar.com.santanderrio.obp.servicios.api.transfers.recipients.dto.RecipientsResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.model.Recipient;

import java.util.List;

public interface RecipientsApi {

    List<Recipient> getAllRecipients(String nup);

    RecipientsResponse getAllRecipientsWithPagination(String nup, int page, int limit);

}
