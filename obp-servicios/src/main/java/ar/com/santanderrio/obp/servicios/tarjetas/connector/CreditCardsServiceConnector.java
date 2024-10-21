package ar.com.santanderrio.obp.servicios.tarjetas.connector;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.exception.ConnectorException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard.CreditCardDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastSettlements.CreditCardLastSettlementDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements.CreditCardLastMovementsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.CreditCardLimitsAndTotalsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.LimitsAndTotalsDto;

import java.util.List;

public interface CreditCardsServiceConnector {
    String getCreditCardId(String cardNumber) throws ConnectorException;
    String getCreditAccountId(String cardNumber) throws ConnectorException;
    CreditCardLastMovementsDto getCreditCardLastMovements(String cardNumber) throws ConnectorException;
    List<CreditCardDto> getAllCreditCardsByAccountNup(String nup) throws ConnectorException;
    List<CreditCardDto> getAllCreditCardsByNup(String nup) throws ConnectorException;
    CreditCardLastSettlementDto getCreditCardLastSettlement(String cardNumber) throws ConnectorException;
    LimitsAndTotalsDto getCreditCardLimits(String id) throws ConnectorException;
}
