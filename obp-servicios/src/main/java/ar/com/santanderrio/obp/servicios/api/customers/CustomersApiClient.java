package ar.com.santanderrio.obp.servicios.api.customers;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.utils.BaseApiClient;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.api.customers.model.AdditionalData;
import ar.com.santanderrio.obp.servicios.api.customers.model.AddressesResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.AudiencesResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.Contacts;
import ar.com.santanderrio.obp.servicios.api.customers.model.CustomerEconomicDataResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.Customers;
import ar.com.santanderrio.obp.servicios.api.customers.model.CustomersSearch;
import ar.com.santanderrio.obp.servicios.api.customers.model.DocumentTypeEnum;
import ar.com.santanderrio.obp.servicios.api.customers.model.DocumentsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.EmailsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.JobsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.PhonesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class CustomersApiClient extends BaseApiClient implements CustomersApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomersApiClient.class);

    protected CustomersApiClient(URI baseUrl, OAuth2RestTemplate restTemplate) {
        super(baseUrl, restTemplate);
    }

    @Override
    public AdditionalData getAdditionalData(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}/additional-data")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, AdditionalData.class);
    }

    @Override
    public AddressesResponse getAddressesById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}/addresses")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, AddressesResponse.class);
    }

    @Override
    public AudiencesResponse getAudiencesById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}/audiences")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, AudiencesResponse.class);
    }

    @Override
    public Contacts getContactsById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}/contacts")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, Contacts.class);
    }

    @Override
    public Customers getCustomerById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, Customers.class);
    }

    @Override
    public CustomersSearch getCustomerByParams(String documentType, String documentNumber) throws ApiException {
        DocumentTypeEnum documentTypeEnum = null;
        if(documentType != null) {
            try {
                documentTypeEnum = DocumentTypeEnum.fromValue(documentType);
            } catch (IllegalArgumentException illegalArgumentException) {
                LOGGER.info("invalid document type specified");
            }
        }

        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers?document_type={document_type}&document_number={document_number}")
                .replaceQueryParam("document_type", documentTypeEnum != null ? documentTypeEnum.toString() : null)
                .replaceQueryParam("document_number", documentNumber)
                .build().toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, CustomersSearch.class);
    }

    @Override
    public DocumentsResponse getDocumentsById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}/documents")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, DocumentsResponse.class);
    }

    @Override
    public CustomerEconomicDataResponse getEconomicDataById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}/economic-data")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, CustomerEconomicDataResponse.class);
    }

    @Override
    public EmailsResponse getEmailsById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() +"/customers/{id}/emails")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, EmailsResponse.class);
    }

    @Override
    public JobsResponse getJobsById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}/jobs")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, JobsResponse.class);
    }

    @Override
    public PhonesResponse getPhonesById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/customers/{id}/phones")
                .buildAndExpand(id).toUri();

        RequestEntity<Void> getAdditionalDataRequest = RequestEntity.get(requestUri).build();
        return this.execute(getAdditionalDataRequest, PhonesResponse.class);
    }
}
