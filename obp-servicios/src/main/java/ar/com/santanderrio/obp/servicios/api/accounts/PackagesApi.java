package ar.com.santanderrio.obp.servicios.api.accounts;


import ar.com.santanderrio.obp.servicios.api.accounts.entities.PackagesResponseEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;


public interface PackagesApi {

    PackagesResponseEntity getPackagesByNup(String customerId) throws ApiException;
}
