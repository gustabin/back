package ar.com.santanderrio.obp.servicios.api.customers;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.customers.model.AdditionalData;
import ar.com.santanderrio.obp.servicios.api.customers.model.AddressesResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.AudiencesResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.Contacts;
import ar.com.santanderrio.obp.servicios.api.customers.model.Customers;
import ar.com.santanderrio.obp.servicios.api.customers.model.CustomersSearch;
import ar.com.santanderrio.obp.servicios.api.customers.model.DocumentsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.EmailsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.JobsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.CustomerEconomicDataResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.PhonesResponse;

public interface CustomersApi {
    AdditionalData getAdditionalData(String id) throws ApiException;

    AddressesResponse getAddressesById(String id) throws ApiException;

    AudiencesResponse getAudiencesById(String id) throws ApiException;

    Contacts getContactsById(String id) throws ApiException;

    Customers getCustomerById(String id) throws ApiException;

    CustomersSearch getCustomerByParams(String documentType, String documentNumber) throws ApiException;

    DocumentsResponse getDocumentsById(String id) throws ApiException;

    CustomerEconomicDataResponse getEconomicDataById(String id) throws ApiException;

    EmailsResponse getEmailsById(String id) throws ApiException;

    JobsResponse getJobsById(String id) throws ApiException;

    PhonesResponse getPhonesById(String id) throws ApiException;
}
