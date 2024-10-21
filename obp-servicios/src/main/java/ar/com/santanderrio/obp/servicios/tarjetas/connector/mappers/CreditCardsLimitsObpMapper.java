package ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.home.entitites.TarjetaHomeDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.CurrencyValueDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.LimitsAndTotalsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums.Currency;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.CreditCardLimitsAndTotalsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.*;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditCardsLimitsObpMapper extends CreditCardsObpMapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardsLimitsObpMapper.class);
	
	public RetornoTarjetasEntity mapToRetornoTarjetasEntity(CreditCardLimitsAndTotalsDto limitsAndTotalsDto) {
		LOGGER.info("Comienza el parseo de CreditCardLimitsAndTotalsDto a RetornoTarjetasEntity");

		TarjetaDocumentEntity tarjetaDocument = new TarjetaDocumentEntity();
		tarjetaDocument.setDatos(mapToDatos(limitsAndTotalsDto.getOwnerData()));
		tarjetaDocument.setDatosCuenta(mapToDatosCuenta(limitsAndTotalsDto));
		LOGGER.info("Finaliza el parseo de CreditCardLimitsAndTotalsDto a RetornoTarjetasEntity");
		return createRetornoTarjetaEntity(tarjetaDocument);
	}

	private DatosCuentaEntity mapToDatosCuenta(CreditCardLimitsAndTotalsDto limitsAndTotalsDto) {
		MovimientosEntity movimientosEntity = new MovimientosEntity();
		movimientosEntity.setPesos(parseAmount(limitsAndTotalsDto.getTotalLocalCurrency()));
		movimientosEntity.setDolares(parseAmount(limitsAndTotalsDto.getTotalDollarCurrency()));
		TotalesEntity totalesEntity = new TotalesEntity();
		totalesEntity.setMovimientos(movimientosEntity);

		LimitesTarjetaLT limitesTarjeta = null;
		if (tieneLimites(limitsAndTotalsDto)) {
		  List<LimitesEntity> limites = new ArrayList<LimitesEntity>();
		  limites.add(mapToLimitesEntity(TarjetaUtils.LIMITE_COMPRAS, parseAmount(limitsAndTotalsDto.getBuyLimit())));
		  limites.add(mapToLimitesEntity(TarjetaUtils.LIMITE_CUOTAS, parseAmount(limitsAndTotalsDto.getInstallmentsLimit())));
		  limites.add(mapToLimitesEntity(TarjetaUtils.DISPONIBLE_COMPRAS, parseAmount(limitsAndTotalsDto.getBuyLimitAvailable())));
		  limites.add(mapToLimitesEntity(TarjetaUtils.DISPONIBLE_CUOTAS, parseAmount(limitsAndTotalsDto.getInstallmentsLimitAvailable())));
		  limites.add(mapToLimitesEntity(TarjetaUtils.ADELANTO_EFECTIVO, parseAmount(limitsAndTotalsDto.getAdvanceLimitAvailable())));
		  limites.add(mapToLimitesEntity(TarjetaUtils.DESCRIPCION_LIMITE_FINANCIAMIENTO, parseAmount(limitsAndTotalsDto.getFinancingLimit())));
		  limites.add(mapToLimitesEntity(TarjetaUtils.LIMITE_ADELANTO, parseAmount(limitsAndTotalsDto.getAdvanceLimit())));
		  limitesTarjeta = new LimitesTarjetaLT();
		  limitesTarjeta.setLimites(limites);
		}

		DatosCuentaEntity datosCuenta = new DatosCuentaEntity();
		datosCuenta.setFechaCierre(parseDate(limitsAndTotalsDto.getDeadLineDate()));
		datosCuenta.setFechaVencimiento(parseDate(limitsAndTotalsDto.getExpirationDate()));
		datosCuenta.setTotales(totalesEntity);
		datosCuenta.setLimites(limitesTarjeta);
		datosCuenta.setLimitesUnificados(limitsAndTotalsDto.getUnifiedLimits());
		return datosCuenta;
	}

	private LimitesEntity mapToLimitesEntity(String description, String pesos) {
		LimitesEntity limitesEntity = new LimitesEntity();
		limitesEntity.setDescripcion(description);
		limitesEntity.setPesos(pesos);
		return limitesEntity;
	}

	private boolean tieneLimites(CreditCardLimitsAndTotalsDto limitsAndTotalsDto) {
		return limitsAndTotalsDto.getBuyLimit() != null && 
		    limitsAndTotalsDto.getInstallmentsLimit() != null && 
		    limitsAndTotalsDto.getBuyLimitAvailable() != null && 
		    limitsAndTotalsDto.getInstallmentsLimitAvailable() != null && 
		    limitsAndTotalsDto.getAdvanceLimitAvailable() != null && 
		    limitsAndTotalsDto.getFinancingLimit() != null && 
		    limitsAndTotalsDto.getAdvanceLimit() != null;
	}


	public RetornoTarjetasEntity mapToRetornoTarjetasEntity(LimitsAndTotalsDto limitsAndTotalsDto) {
		LOGGER.info("Comienza el parseo de CreditCardLimitsAndTotalsDto a RetornoTarjetasEntity");
		TarjetaDocumentEntity tarjetaDocument = new TarjetaDocumentEntity();
		tarjetaDocument.setDatos(mapToDatos(limitsAndTotalsDto.getOwnerData()));
		tarjetaDocument.setDatosCuenta(mapToAccountData(limitsAndTotalsDto));
		LOGGER.info("Finaliza el parseo de LimitsAndTotalsDto a RetornoTarjetasEntity");
		return createRetornoTarjetaEntity(tarjetaDocument);
	}


	private DatosCuentaEntity mapToAccountData(LimitsAndTotalsDto limitsAndTotalsDto) {
		MovimientosEntity movimientosEntity = mapMovements(limitsAndTotalsDto);
		TotalesEntity totalesEntity = new TotalesEntity();
		totalesEntity.setMovimientos(movimientosEntity);

		LimitesTarjetaLT limitesTarjeta = null;
		if (hasLimits(limitsAndTotalsDto)) {
			List<LimitesEntity> limites = new ArrayList<LimitesEntity>();
			limites.add(mapToLimitsEntity(TarjetaUtils.LIMITE_COMPRAS, limitsAndTotalsDto.getBuyLimit()));
			limites.add(mapToLimitsEntity(TarjetaUtils.LIMITE_CUOTAS, limitsAndTotalsDto.getInstallmentsLimit()));
			limites.add(mapToLimitsEntity(TarjetaUtils.DISPONIBLE_COMPRAS, limitsAndTotalsDto.getBuyAvailableLimit()));
			limites.add(mapToLimitsEntity(TarjetaUtils.DISPONIBLE_CUOTAS, limitsAndTotalsDto.getInstallmentsAvailableLimit()));
			limites.add(mapToLimitsEntity(TarjetaUtils.ADELANTO_EFECTIVO, limitsAndTotalsDto.getAdvanceAvailableLimit()));
			limites.add(mapToLimitsEntity(TarjetaUtils.DESCRIPCION_LIMITE_FINANCIAMIENTO, limitsAndTotalsDto.getFinancialLimit()));
			limites.add(mapToLimitsEntity(TarjetaUtils.LIMITE_ADELANTO, limitsAndTotalsDto.getAdvanceLimit()));
			limitesTarjeta = new LimitesTarjetaLT();
			limitesTarjeta.setLimites(limites);
		}

		DatosCuentaEntity datosCuenta = new DatosCuentaEntity();
		datosCuenta.setFechaCierre(parseDate(limitsAndTotalsDto.getStatementClosingDate()));
		datosCuenta.setFechaVencimiento(parseDate(limitsAndTotalsDto.getPaymentDueDate()));
		datosCuenta.setTotales(totalesEntity);
		datosCuenta.setLimites(limitesTarjeta);

		if(limitsAndTotalsDto.getUnifiedLimits()==null) {
			LOGGER.error("La tarjeta solicitada no tiene el valor de limite unificado");
		}else {
			datosCuenta.setLimitesUnificados(limitsAndTotalsDto.getUnifiedLimits().getLegacyCode());
		}
		return datosCuenta;
	}

	private MovimientosEntity mapMovements(LimitsAndTotalsDto limitsAndTotalsDto) {
		MovimientosEntity movimientosEntity = new MovimientosEntity();
		for(CurrencyValueDto currencyValueDto : limitsAndTotalsDto.getCurrentBalance()) {
			if(Currency.ARS.equals(currencyValueDto.getCurrency())) {
				movimientosEntity.setPesos(parseAmount(currencyValueDto.getValue()));
			}
			if(Currency.USD.equals(currencyValueDto.getCurrency())) {
				movimientosEntity.setDolares(parseAmount(currencyValueDto.getValue()));
			}
		}
		return movimientosEntity;
	}

	private boolean hasLimits(LimitsAndTotalsDto limitsAndTotalsDto) {
		return limitsAndTotalsDto.getBuyLimit() != null &&
				limitsAndTotalsDto.getInstallmentsLimit() != null &&
				limitsAndTotalsDto.getBuyAvailableLimit() != null &&
				limitsAndTotalsDto.getInstallmentsAvailableLimit() != null &&
				limitsAndTotalsDto.getAdvanceAvailableLimit() != null &&
				limitsAndTotalsDto.getFinancialLimit() != null &&
				limitsAndTotalsDto.getAdvanceLimit() != null;
	}


	private LimitesEntity mapToLimitsEntity(String description, CurrencyValueDto currencyValueDto) {
		LimitesEntity limitesEntity = new LimitesEntity();
		limitesEntity.setDescripcion(description);
		limitesEntity.setPesos(parseAmount(currencyValueDto.getValue()));
		limitesEntity.setMoneda(currencyValueDto.getCurrency());
		return limitesEntity;
	}

	private RetornoTarjetasEntity createRetornoTarjetaEntity(TarjetaDocumentEntity tarjetaDocument) {
		TarjetaEntity tarjetaEntity = new TarjetaEntity();
		tarjetaEntity.setTarjetaDocument(tarjetaDocument);

		List<TarjetaEntity> tarjetaList = new ArrayList<TarjetaEntity>();
		tarjetaList.add(tarjetaEntity);

		TarjetasEntity tarjetasEntity = new TarjetasEntity();
		tarjetasEntity.setTarjetaList(tarjetaList);

		RetornoTarjetasEntity retornoTarjetasEntity = new RetornoTarjetasEntity();
		retornoTarjetasEntity.setTarjetas(tarjetasEntity);
		return retornoTarjetasEntity;
	}

	public TarjetaHomeDTO mapLimits(Cuenta cuenta, LimitsAndTotalsDto limitsAndTotalsDto) throws TarjetaBOUtilsException {
		MovimientosEntity movimientosEntity = mapMovements(limitsAndTotalsDto);
		String balanceARS = (TarjetaUtils.esRecargable(cuenta) ? parseAmount(limitsAndTotalsDto.getBuyAvailableLimit().getValue()) : movimientosEntity.getPesos());

		TarjetaHomeDTO tarjetaHomeDTO = new TarjetaHomeDTO();
		tarjetaHomeDTO.setCierre(TarjetaUtils.mapSettlementDateDate(limitsAndTotalsDto.getStatementClosingDate()));
		tarjetaHomeDTO.setVencimiento(TarjetaUtils.mapSettlementDateDate(limitsAndTotalsDto.getPaymentDueDate()));
		tarjetaHomeDTO.setSaldoPesos(TarjetaBOUtils.convertirSaldo(balanceARS));
		tarjetaHomeDTO.setSaldoDolares(TarjetaBOUtils.convertirSaldo(movimientosEntity.getDolares()));
		return tarjetaHomeDTO;
	}
}