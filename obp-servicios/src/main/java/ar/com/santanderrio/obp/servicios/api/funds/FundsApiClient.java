package ar.com.santanderrio.obp.servicios.api.funds;

import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.funds.entities.bouncer.BouncerAccessResponseEntity;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.*;
import ar.com.santanderrio.obp.servicios.api.funds.entities.redemption.*;
import ar.com.santanderrio.obp.servicios.api.funds.exceptions.BouncerAccessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.List;

public class FundsApiClient implements FundsApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(FundsApiClient.class);
    private final Map<String, String> paths;
    private static final String API_HOLDING_CACHE = "api-holding-cache";
    private static final String API_PYL_SERVICE = "api-pyl-service";
    private static final String NUP = "NUP";
    private static final String OBP = "OBP";
    private static final String SUSCRIPCION = "suscripcion";
    private static final String ERROR_DEPRECARD_CACHE = "Error al deprecar cache de %s por operacion de %s %s %s";

    @Autowired
    private RestTemplate simpleRestTemplate;

    protected FundsApiClient(Map<String, String> paths) {
        this.paths = paths;
    }

    @Override
    public HoldingsResponse getHoldingBff(Cliente cliente) throws ApiException {
        String nup = cliente.getNup();
        URI requestUri = UriComponentsBuilder
                .fromUriString(paths.get(FundsApiConstants.Paths.HOLDINGS_BFF) + "/holdings?nup={nup}")
                .buildAndExpand(nup).toUri();

        RequestEntity<Void> getHoldingBffRequest = RequestEntity
                .get(requestUri)
                .header(APIcConfigConstants.CHANNEL_NAME, OBP)
                .build();

        return this.execute(getHoldingBffRequest, HoldingsResponse.class);
    }

    @Override
    public HoldingsSummaryResponse getHoldingSummary(Cliente cliente) throws ApiException {
        String nup = cliente.getNup();
        URI requestUri = UriComponentsBuilder
                .fromUriString(paths.get(FundsApiConstants.Paths.HOLDINGS_BFF) + "/summary?nup={nup}")
                .buildAndExpand(nup).toUri();

        RequestEntity<Void> getHoldingSummary = RequestEntity
                .get(requestUri)
                .header(APIcConfigConstants.CHANNEL_NAME, OBP)
                .build();

        return this.execute(getHoldingSummary, HoldingsSummaryResponse.class);
    }

    @Override
    public void cleanCacheFunds(String account, Cliente cliente, String operacion, String segmento) throws ApiException {
        String nup = cliente.getNup();
        String custodyAccount = formatCustodyAccount(account);

        if (segmento.equalsIgnoreCase("BP")) {
            custodyAccount = getCustodyAccountBp(custodyAccount, cliente, operacion);
        }

        try {
            LOGGER.info("Se depreca cache de api-holding-cache por {} {} {}", operacion, NUP, nup);
            cleanCache(custodyAccount, API_HOLDING_CACHE);
        } catch (ApiException e) {
            LOGGER.error(String.format(ERROR_DEPRECARD_CACHE,
                    API_HOLDING_CACHE, operacion, NUP, nup));
        } catch (Exception e) {
            LOGGER.error(String.format(ERROR_DEPRECARD_CACHE,
                    API_HOLDING_CACHE, operacion, NUP, nup));
        }

        try {
            LOGGER.info("Se depreca cache de api-pyl-service por {} {} {}", operacion, NUP, nup);
            cleanCache(custodyAccount, API_PYL_SERVICE);
        } catch (ApiException e) {
            LOGGER.error(String.format(ERROR_DEPRECARD_CACHE,
                    API_PYL_SERVICE, operacion, NUP, nup));
        } catch (Exception e) {
            LOGGER.error(String.format(ERROR_DEPRECARD_CACHE,
                    API_PYL_SERVICE, operacion, NUP, nup));
        }
    }

    private void cleanCache(String custodyAccount, String serviceName) throws ApiException {
        String url = null;
        if (serviceName.equalsIgnoreCase(API_PYL_SERVICE)) {
            String custodyAccountPyl = String.valueOf(Long.valueOf(custodyAccount));
            url = paths.get(FundsApiConstants.Paths.PYL_SERVICE_CACHE) + "/delete?custodyAccount=" + custodyAccountPyl;
        }

        if (serviceName.equalsIgnoreCase(API_HOLDING_CACHE)) {
            url = paths.get(FundsApiConstants.Paths.HOLDINGS_CACHE)  + "/HOLDING:" + custodyAccount;
        }

        URI requestUri = UriComponentsBuilder
                .fromUriString(url)
                .buildAndExpand().toUri();

        RequestEntity<Void> cleanCacheRequest = RequestEntity
                .delete(requestUri)
                .header(APIcConfigConstants.CHANNEL_NAME, OBP)
                .build();

        if (serviceName.equalsIgnoreCase(API_PYL_SERVICE)) {
            this.execute(cleanCacheRequest, CleanCachePyl.class);
        }

        if (serviceName.equalsIgnoreCase(API_HOLDING_CACHE)) {
            this.execute(cleanCacheRequest, CleanCacheHoldings.class);
        }
    }

    @Cacheable(key = "#cliente.nup", cacheNames = CacheConstants.Names.CACHE_NUP_ACCESS_FONDOS_TENENCIA_BFF)
    @Override
    public Boolean verifyAccessToGetHolding(Cliente cliente) {
        boolean access = false;
        String nup = cliente.getNup();
        try {
            access = checkBouncerAccess(FundsApiConstants.Paths.HOLDINGS_BFF, FundsApiConstants.Bouncer.ACCESS, nup);
        } catch (ApiException e) {
            LOGGER.error("Acceso restringido a FONDOS-TENENCIA-BFF", e);
        } catch (Exception e) {
            LOGGER.error("Acceso restringido a FONDOS-TENENCIA-BFF - Exception", e);
        }

        if (access) {
            LOGGER.info("Acceso permitido a FONDOS-TENENCIA-BFF para el nup {}", nup);
        } else {
            LOGGER.info("Acceso restringido a FONDOS-TENENCIA-BFF para el nup {}", nup);
        }

        return access;
    }

    @Override
    public SimulateRedemptionResponseDataEntity simulateRedemption(SimulateRedemptionRequestEntity request) throws ApiException {

        String baseUrl = paths.get(FundsApiConstants.Paths.REDEMPTIONS_BFF);

        URI requestUri = UriComponentsBuilder
                .fromUriString(baseUrl + "/simulation")
                .build().toUri();

        RequestEntity<SimulateRedemptionRequestEntity> simulateRedemptionRequest = RequestEntity
                .post(requestUri)
                .header(APIcConfigConstants.CHANNEL_NAME, OBP)
                .body(request);

        SimulateRedemptionResponseEntity response = this.executeTransaction(simulateRedemptionRequest, SimulateRedemptionResponseEntity.class);

        return response.getData();
    }

    @Override
    public ConfirmRedemptionResponseDataEntity confirmRedemption(ConfirmRedemptionRequestEntity request) throws ApiException {
        String baseUrl = paths.get(FundsApiConstants.Paths.REDEMPTIONS_BFF);

        URI requestUri = UriComponentsBuilder
                .fromUriString(baseUrl + "/confirmation")
                .build().toUri();

        RequestEntity<ConfirmRedemptionRequestEntity> confirmRedemptionRequest = RequestEntity
                .post(requestUri)
                .header(APIcConfigConstants.CHANNEL_NAME, OBP)
                .body(request);

        ConfirmRedemptionResponseEntity response = this.executeTransaction(confirmRedemptionRequest, ConfirmRedemptionResponseEntity.class);

        return response.getData();
    }

    public boolean checkBouncerAccess(String pathIdentifier, String accessResource, String nup) throws BouncerAccessException {
        String uriAccess = paths.get(pathIdentifier) + accessResource;
        try {
            URI requestUri = UriComponentsBuilder
                    .fromUriString(uriAccess)
                    .queryParam("nup", nup)
                    .build().toUri();

            RequestEntity<Void> bouncerAccessRequest = RequestEntity
                    .get(requestUri)
                    .header(APIcConfigConstants.CHANNEL_NAME, OBP)
                    .build();

            BouncerAccessResponseEntity response = this.execute(bouncerAccessRequest, BouncerAccessResponseEntity.class);

            return response.getPass();
        } catch (ApiException apiException) {
            return false;
        }
    }

    private <T> T execute(RequestEntity<?> request, Class<T> responseClass) throws ApiException {
        try {
            ResponseEntity<T> res = this.simpleRestTemplate.exchange(request, responseClass);
            if ((responseClass == HoldingsResponse.class || responseClass == HoldingsSummaryResponse.class)
                    && res.getStatusCode().value() == 206) {
                // Respuesta parcial, se fuerza la ejecucion de PyL
                throw new ApiException(new ErrorResponse()
                        .code("PARTIAL")
                        .message("Ocurrio respueta parcial (206) al consumir FONDOS-TENENCIA-BFF " +
                                "y se procedio a obtener los datos de PyL"));
            }
            return res.getBody();
        } catch (ResourceAccessException ioException) {
            throw new ApiException(new ErrorResponse()
                    .code("ERROR")
                    .message("NO RESPONSE BODY FOUND FONDOS-BFF"), ioException);
        }
    }

    private <T> T executeTransaction(RequestEntity<?> request, Class<T> responseClass) throws ApiException {
        try {
            ResponseEntity<T> response = this.simpleRestTemplate.exchange(request, responseClass);
            return response.getBody();
        } catch (Exception e) {
            String errorBody = ((HttpClientErrorException) e).getResponseBodyAsString();
            JsonObject jsonObject = new JsonParser().parse(errorBody).getAsJsonObject();
            throw new ApiException(new ErrorResponse()
                    .code(jsonObject.get("code").getAsString())
                    .message(jsonObject.get("message").getAsString()), e);
        }
    }

    private String getCustodyAccountBp(String custodyAccount, Cliente cliente, String operacion) {
        List<CuentaBancaPrivada> accountsBp = cliente.getCuentasBancaPrivada();
        for (CuentaBancaPrivada accountBP : accountsBp) {
            String altairProduct = accountBP.getCuentaOperativa().getNroCuentaProducto();
            if (operacion.equalsIgnoreCase(SUSCRIPCION)) {
                altairProduct = accountBP.getCuentaOperativa().getContratoAltair();
            }
            if (formatCustodyAccount(altairProduct).equals(custodyAccount)) {
                return formatCustodyAccount(accountBP.getCuentaOperativa().getNroCuentaTit());
            }
        }

        return custodyAccount;
    }

    private String formatCustodyAccount(String account) {
        String accountFormatted = account.replace("/", "");

        if (accountFormatted.contains("-")) {
            accountFormatted = accountFormatted.split("-")[1];
        }

        return String.format("%08d", Long.valueOf(accountFormatted));
    }

}
