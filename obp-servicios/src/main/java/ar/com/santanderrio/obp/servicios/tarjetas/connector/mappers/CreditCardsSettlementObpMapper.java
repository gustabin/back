package ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.*;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastSettlements.CreditCardLastSettlementDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastSettlements.CreditCardSettlementDto;

@Component
public class CreditCardsSettlementObpMapper extends CreditCardsObpMapper {
	private static final String CODIGO_ERROR_RESUMEN = "112407";
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardsMovementsObpMapper.class);

	public RetornoTarjetasEntity mapToRetornoTarjetasEntity(CreditCardLastSettlementDto creditCardLastSettlementDto) {
		LOGGER.info("Comienza el parseo de CreditCardLastSettlementDto a RetornoTarjetasEntity");
		if (creditCardLastSettlementDto.getCreditCardSettlementsDto().isEmpty()) {
			LOGGER.info("No se encontro ultimo resumen fin del parseo");
        	return createNoServiceResponse(CODIGO_ERROR_RESUMEN);
        }
		
		CreditCardSettlementDto creditCardSettlementDto = creditCardLastSettlementDto.getCreditCardSettlementsDto().get(0);
		
		List<TarjetaEntity> tarjetaEntityList = new ArrayList<TarjetaEntity>();
		
		UltimaLiquidacionEntity ultimaLiquidacion = mapToTarjetaDocument(creditCardSettlementDto);
		
		TarjetaEntity tarjeta = new TarjetaEntity();
        
		tarjeta.setUltimaLiquidacion(ultimaLiquidacion);
        tarjetaEntityList.add(tarjeta);
		
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(tarjetaEntityList);
		
        RetornoTarjetasEntity retornoTarjetasEntity = new RetornoTarjetasEntity();
		retornoTarjetasEntity.setTarjetas(tarjetas);
		LOGGER.info("Finaliza el parseo de CreditCardLastSettlementDto a RetornoTarjetasEntity");
		return retornoTarjetasEntity;
	}
	
	private Total mapToTotal(CreditCardSettlementDto creditCardSettlementDto) {

		Total total = new Total();
		total.setSessionID(creditCardSettlementDto.getSessionId());
		total.setValor(creditCardSettlementDto.getBalanceLocalCurrency());
		
		return total;
	}
	
	private DatosEntity mapToDatos(CreditCardSettlementDto creditCardSettlementDto) {
		DatosEntity datos = new DatosEntity();
		datos.setId(creditCardSettlementDto.getId());
		datos.setTarjetaActiva(creditCardSettlementDto.getActiveCard());
		datos.setCuenta(creditCardSettlementDto.getAccount());

		return datos;
	}
	
	private DatosExtra mapToDatosExtra(CreditCardSettlementDto creditCardSettlementDto) {
		// TO DO
		return new DatosExtra();
	}
	
	private FechasUltimaLiquidacion mapToFechasUltimaLiquidacion(CreditCardSettlementDto creditCardSettlementDto) {
		List<FechaUltimaLiquidacion> fechas = new ArrayList<FechaUltimaLiquidacion>();
		
		FechaUltimaLiquidacion fechaUltimaLiquidacionAnterior = new FechaUltimaLiquidacion();
		
		fechaUltimaLiquidacionAnterior.setCierre(parseDate(creditCardSettlementDto.getPreviousDeadline()));
		fechaUltimaLiquidacionAnterior.setVencimiento(parseDate(creditCardSettlementDto.getPreviousExpirationDate()));
		fechaUltimaLiquidacionAnterior.setDescripcion(TarjetaUtils.DESCRIPCION_ANTERIOR);
		
		fechas.add(fechaUltimaLiquidacionAnterior);
		
		FechaUltimaLiquidacion fechaUltimaLiquidacionActual = new FechaUltimaLiquidacion();
		
		fechaUltimaLiquidacionActual.setCierre(parseDate(creditCardSettlementDto.getCurrentDeadline()));
		fechaUltimaLiquidacionActual.setVencimiento(parseDate(creditCardSettlementDto.getCurrentExpirationDate()));
		fechaUltimaLiquidacionActual.setDescripcion(TarjetaUtils.DESCRIPCION_ACTUAL);
		
		fechas.add(fechaUltimaLiquidacionActual);
		
		FechaUltimaLiquidacion fechaUltimaLiquidacionProximo = new FechaUltimaLiquidacion();
		
		fechaUltimaLiquidacionProximo.setCierre(parseDate(creditCardSettlementDto.getNextDeadline()));
		fechaUltimaLiquidacionProximo.setVencimiento(parseDate(creditCardSettlementDto.getNextExpirationDate()));
		fechaUltimaLiquidacionProximo.setDescripcion("proximo");
		
		fechas.add(fechaUltimaLiquidacionProximo);
		
		FechasUltimaLiquidacion fechasUltimaLiquidacion = new FechasUltimaLiquidacion();
		fechasUltimaLiquidacion.setFechas(fechas);
		return fechasUltimaLiquidacion;
	}
	
	private PagosUltimaLiquidacion mapToPagosUltimaLiquidacion(CreditCardSettlementDto creditCardSettlementDto) {
		PagosUltimaLiquidacion pagosUltimaLiquidacion = new PagosUltimaLiquidacion();
		List<PagoUltimaLiquidacion> pagos = new ArrayList<PagoUltimaLiquidacion>();
		
		PagoUltimaLiquidacion pagoMinimo = new PagoUltimaLiquidacion();
		
		pagoMinimo.setDescripcion(TarjetaUtils.DESCRIPCION_PAGO_MINIMO);
		pagoMinimo.setTipoMoneda(TarjetaUtils.TIPO_MONEDA_PAGO_MINIMO);
		pagoMinimo.setTotal(creditCardSettlementDto.getMinimumPayment());

		//TO DO
		pagoMinimo.setUsdTotal("");
		
		pagos.add(pagoMinimo);
		
		pagosUltimaLiquidacion.setPagos(pagos);
		return pagosUltimaLiquidacion;
	}
	
	private LimitesUltimaLiquidacionEntity mapToLimitesUltimaLiquidacionEntity(CreditCardSettlementDto creditCardSettlementDto) {
		LimitesUltimaLiquidacionEntity limitesUltimaLiquidacionEntity = new LimitesUltimaLiquidacionEntity();
		
		List<LimiteUltimaLiquidacionEntity> limites = new ArrayList<LimiteUltimaLiquidacionEntity>();
		
		LimiteUltimaLiquidacionEntity limiteAdelanto = new LimiteUltimaLiquidacionEntity();
		limiteAdelanto.setDescripcion(TarjetaUtils.LIMITE_ADELANTO);
		limiteAdelanto.setTotal(creditCardSettlementDto.getAdvanceLimit());
		
		limites.add(limiteAdelanto);
		
		LimiteUltimaLiquidacionEntity limiteFinanciero = new LimiteUltimaLiquidacionEntity();
		limiteFinanciero.setDescripcion(TarjetaUtils.DESCRIPCION_LIMITE_FINANCIAMIENTO);
		limiteFinanciero.setTotal(creditCardSettlementDto.getFinancingLimit());
		
		limites.add(limiteFinanciero);
		
		LimiteUltimaLiquidacionEntity limiteCompraCuota = new LimiteUltimaLiquidacionEntity();
		limiteCompraCuota.setDescripcion(TarjetaUtils.DESCRIPCION_LIMITE_COMPRA_CUOTAS);
		limiteCompraCuota.setTotal(creditCardSettlementDto.getFeeBuyLimit());
		
		limites.add(limiteCompraCuota);
		
		LimiteUltimaLiquidacionEntity limiteCompra = new LimiteUltimaLiquidacionEntity();
		limiteCompra.setDescripcion(TarjetaUtils.DESCRIPCION_LIMITE_COMPRA);
		limiteCompra.setTotal(creditCardSettlementDto.getBuyLimit());
		
		limites.add(limiteCompra);
		
		limitesUltimaLiquidacionEntity.setLimites(limites);
		return limitesUltimaLiquidacionEntity;
	}
	
	private TasasUltimaLiquidacion mapToTasasUltimaLiquidacion(CreditCardSettlementDto creditCardSettlementDto) {
		TasasUltimaLiquidacion tasasUltimaLiquidacion = new TasasUltimaLiquidacion();
		List<SaldoTasaUltimaLiquidacion> tasas = new ArrayList<SaldoTasaUltimaLiquidacion>();
		
		SaldoTasaUltimaLiquidacion tasaTNA = new SaldoTasaUltimaLiquidacion();
		
		tasaTNA.setDescripcion(TarjetaUtils.DESCRIPCION_TNA);
		tasaTNA.setTotal(creditCardSettlementDto.getYearNominalRateLocalCurrency());
		tasaTNA.setUsdTotal(creditCardSettlementDto.getYearNominalRateDollarCurrency());
		
		tasas.add(tasaTNA);
		
		SaldoTasaUltimaLiquidacion tasaTEM = new SaldoTasaUltimaLiquidacion();
		
		tasaTEM.setDescripcion(TarjetaUtils.DESCRIPCION_TEM);
		tasaTEM.setTotal(creditCardSettlementDto.getMonthEffectiveRateLocalCurrency());
		tasaTEM.setUsdTotal(creditCardSettlementDto.getMonthEffectiveRateDollarCurrency());
		
		tasas.add(tasaTEM);
		
		tasasUltimaLiquidacion.setTasas(tasas);
		return tasasUltimaLiquidacion;
	}
	
	private DetalleLiquidacion mapToDetalleLiquidacion(CreditCardSettlementDto creditCardSettlementDto) {
		DetalleLiquidacion detalleLiquidacion = new DetalleLiquidacion();
		List<LineaUltimaLiquidacionEntity> lineas = new ArrayList<LineaUltimaLiquidacionEntity>();

		for (String linea : creditCardSettlementDto.getTransactions()) {
			LineaUltimaLiquidacionEntity lineaEntity = new LineaUltimaLiquidacionEntity();			
			lineaEntity.setDescripcion(mapToDescripcionDetalleLiquidacion(linea));
			lineas.add(lineaEntity);
		}
		detalleLiquidacion.setLineas(lineas);

		return detalleLiquidacion;
	}
	
	private String mapToDescripcionDetalleLiquidacion(String linea) {
		return linea;
	}

	private SaldosUltimaLiquidacion mapToSaldos(CreditCardSettlementDto creditCardSettlementDto) {
		SaldosUltimaLiquidacion saldosEntity = new SaldosUltimaLiquidacion();
		List<SaldoTasaUltimaLiquidacion> saldos = new ArrayList<SaldoTasaUltimaLiquidacion>();

		SaldoTasaUltimaLiquidacion saldoActual = new SaldoTasaUltimaLiquidacion();
		saldoActual.setDescripcion(TarjetaUtils.DESCRIPCION_ACTUAL);
		saldoActual.setTotal(creditCardSettlementDto.getBalanceLocalCurrency());
		saldoActual.setUsdTotal(creditCardSettlementDto.getBalanceDollarCurrency());
		saldos.add(saldoActual);

		saldosEntity.setSaldos(saldos);

		return saldosEntity;
	}
	
	private UltimaLiquidacionEntity mapToTarjetaDocument(CreditCardSettlementDto creditCardSettlementDto) {
		UltimaLiquidacionEntity ultimaLiquidacionEntity = new UltimaLiquidacionEntity();
		ultimaLiquidacionEntity.setAnterior("");
		ultimaLiquidacionEntity.setDatos(mapToDatos(creditCardSettlementDto));
		ultimaLiquidacionEntity.setDatosExtra(mapToDatosExtra(creditCardSettlementDto));
		ultimaLiquidacionEntity.setDetalleLiquidacion(mapToDetalleLiquidacion(creditCardSettlementDto));
		ultimaLiquidacionEntity.setFechas(mapToFechasUltimaLiquidacion(creditCardSettlementDto));
		ultimaLiquidacionEntity.setLimites(mapToLimitesUltimaLiquidacionEntity(creditCardSettlementDto));
		ultimaLiquidacionEntity.setNumero("");
		ultimaLiquidacionEntity.setPagos(mapToPagosUltimaLiquidacion(creditCardSettlementDto));
		ultimaLiquidacionEntity.setProxima("");
		ultimaLiquidacionEntity.setResumen("");
		ultimaLiquidacionEntity.setSaldos(mapToSaldos(creditCardSettlementDto));
		ultimaLiquidacionEntity.setTasas(mapToTasasUltimaLiquidacion(creditCardSettlementDto));
		ultimaLiquidacionEntity.setTotal(mapToTotal(creditCardSettlementDto));
		ultimaLiquidacionEntity.setCurrency(creditCardSettlementDto.getCurrency());

		return ultimaLiquidacionEntity;
	}
}
