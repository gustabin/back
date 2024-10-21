package ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements.CreditCardLastMovementsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements.CreditCardMovement;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements.LastMovementCreditCardDto;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ConsumosCuentasAuthEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetallePromocionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.EstablecimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ImporteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.MovimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaUltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.UltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

@Component
public class CreditCardsMovementsObpMapper extends CreditCardsObpMapper {
	private static final String BONIF_CARD_NUMBER = "0000000000000000";
	private static final String CODIGO_ERROR_CONSUMOS = "112107";
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardsMovementsObpMapper.class);

    public RetornoTarjetasEntity mapToRetornoTarjetasEntity(CreditCardLastMovementsDto responseDto) {
		LOGGER.info("Comienza el parseo de CreditCardLastMovementsDto a RetornoTarjetasEntity");
        if (responseDto.getCards().isEmpty()) {
			LOGGER.info("No se encontraron registros en Cards. Fin del parseo");
        	return createNoServiceResponse(CODIGO_ERROR_CONSUMOS);
        }
        
        ConsumosCuentasAuthEntity totales = mapToConsumoCuentaAuth(responseDto);
        List<TarjetaEntity> tarjetaEntityList = new ArrayList<TarjetaEntity>();
        List<TarjetaUltimosConsumosEntity> tarjetaList = mapToListTarjetaUltimosConsumos(responseDto, tarjetaEntityList);
        DatosEntity datos = mapToDatos(responseDto.getOwnerData());
        UltimosConsumosEntity ultimosMovimientos = mapToUltimosConsumos(totales, tarjetaList);
        TarjetaDocumentEntity tarjetaDocument = mapToTarjetaDocument(datos, ultimosMovimientos);
        TarjetaEntity tarjeta = new TarjetaEntity();
        tarjeta.setTarjetaDocument(tarjetaDocument);
        tarjetaEntityList.add(tarjeta);
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(tarjetaEntityList);
        RetornoTarjetasEntity retornoTarjetasEntity = new RetornoTarjetasEntity();
		retornoTarjetasEntity.setTarjetas(tarjetas);
		LOGGER.info("Finaliza el parseo de CreditCardLastMovementsDto a RetornoTarjetasEntity");
        return retornoTarjetasEntity;
    }

	private TarjetaDocumentEntity mapToTarjetaDocument(DatosEntity datos, UltimosConsumosEntity ultimosMovimientos) {
		TarjetaDocumentEntity tarjetaDocument = new TarjetaDocumentEntity();
        tarjetaDocument.setDatos(datos);
        tarjetaDocument.setUltimosMovimientos(ultimosMovimientos);
		return tarjetaDocument;
	}

	private UltimosConsumosEntity mapToUltimosConsumos(ConsumosCuentasAuthEntity totales,
			List<TarjetaUltimosConsumosEntity> tarjetaList) {
		UltimosConsumosEntity ultimosMovimientos = new UltimosConsumosEntity();
        ultimosMovimientos.setTotales(totales);
        ultimosMovimientos.setTarjetaList(tarjetaList);
		return ultimosMovimientos;
	}

	private List<TarjetaUltimosConsumosEntity> mapToListTarjetaUltimosConsumos(CreditCardLastMovementsDto responseDto, List<TarjetaEntity> tarjetaEntityList) {
		if(responseDto.getCards() != null){
			List<TarjetaUltimosConsumosEntity> tarjetaList = new ArrayList<TarjetaUltimosConsumosEntity>();
	        for(LastMovementCreditCardDto card : responseDto.getCards()){
	        	TarjetaUltimosConsumosEntity tarjeta = mapToTarjetasUltimosConsumos(card, tarjetaEntityList, responseDto.getOwnerData().getCategory());
	        	List<MovimientoEntity> movements = mapToListMovimiento(card);
	        	tarjeta.setMovimientosList(movements);
	        	tarjetaList.add(tarjeta);
	        }
	        return tarjetaList;
		}
		return null;
	}

	private List<MovimientoEntity> mapToListMovimiento(LastMovementCreditCardDto card) {
		if(card.getMovements() != null){
			List<MovimientoEntity> movements = new ArrayList<MovimientoEntity>();
			for(CreditCardMovement creditCardMovement : card.getMovements()){
				EstablecimientoEntity establecimiento = mapToEstablecimiento(creditCardMovement);
				ImporteEntity importe = mapToImporte(card, creditCardMovement);
				DetallePromocionEntity detallePromocion = mapToDetallePromocion(creditCardMovement);
				MovimientoEntity movement = new MovimientoEntity();
				movement.setFecha(parseDate(creditCardMovement.getDate()));
				movement.setTicket(creditCardMovement.getTicketNumber());
				movement.setTipoMoneda(creditCardMovement.getCurrency());
				movement.setDetallePromocion(detallePromocion);
				movement.setEstablecimiento(establecimiento);
				movement.setImporte(importe);
				movements.add(movement);
			}
			return movements;
		}
		return null;
	}

	private DetallePromocionEntity mapToDetallePromocion(CreditCardMovement creditCardMovement) {
		if(creditCardMovement.getPromoDetail() !=null){
			DetallePromocionEntity detallePromocion = new DetallePromocionEntity();
			detallePromocion.setCodigoCampaniaBanco(creditCardMovement.getPromoDetail().getBankCampaignCode());
			detallePromocion.setCodigoCampaniaVisa(creditCardMovement.getPromoDetail().getVisaCampaignCode());
			detallePromocion.setDenominacionCampania(creditCardMovement.getPromoDetail().getCampaignDenomination());
			detallePromocion.setDescuentoEstablecimiento(String.valueOf(creditCardMovement.getPromoDetail().getEstablishmentDiscount()));
			detallePromocion.setDescuentoUsuario(String.valueOf(creditCardMovement.getPromoDetail().getUserDiscount()));
			detallePromocion.setPorcentajeEstablecimiento(String.valueOf(creditCardMovement.getPromoDetail().getEstablishmentPercentage()));
			detallePromocion.setPorcentajeTopeEstablecimiento(String.valueOf(creditCardMovement.getPromoDetail().getEstablishmentMaximumPercentage()));
			detallePromocion.setPorcentajeTopeUsuario(String.valueOf(creditCardMovement.getPromoDetail().getUserMaximumDiscountPercentage()));
			detallePromocion.setPorcentajeUsuario(String.valueOf(creditCardMovement.getPromoDetail().getUserPercentage()));
			return detallePromocion;
		}
		return null;
	}

	private ImporteEntity mapToImporte(LastMovementCreditCardDto card, CreditCardMovement creditCardMovement) {
		ImporteEntity importe = new ImporteEntity();
		importe.setCodigoTarjeta(parseCardNumber(card));
		importe.setValor(creditCardMovement.getAmount() != null ? parseAmount(creditCardMovement.getAmount()) : null);
		return importe;
	}

	private String parseCardNumber(LastMovementCreditCardDto card) {
		return card.getCardNumber() == 0 ? BONIF_CARD_NUMBER : String.valueOf(card.getCardNumber());
	}

	private EstablecimientoEntity mapToEstablecimiento(CreditCardMovement creditCardMovement) {
		if(creditCardMovement.getCommercialEstablishment() != null){
			EstablecimientoEntity establecimiento = new EstablecimientoEntity();
			establecimiento.setCodigo(creditCardMovement.getCommercialEstablishmentCode());
			establecimiento.setDescripcion(creditCardMovement.getCommercialEstablishment());
			return establecimiento;
		}
		return null;
	}

	private TarjetaUltimosConsumosEntity mapToTarjetasUltimosConsumos(LastMovementCreditCardDto card, List<TarjetaEntity> tarjetaEntityList, String ownerCategory) {
		TarjetaUltimosConsumosEntity tarjeta = new TarjetaUltimosConsumosEntity();
		tarjeta.setCodigoTarjeta(parseCardNumber(card));
		tarjeta.setDolares(parseAmount(card.getUsdTotal()));
		tarjeta.setPesos(parseAmount(card.getLocalCurrencyTotal()));
		
		validateAdittionalCard(card, tarjetaEntityList, ownerCategory);
		return tarjeta;
	}

	private void validateAdittionalCard(LastMovementCreditCardDto card, List<TarjetaEntity> tarjetaEntityList, String ownerCategory) {
		if(!TarjetaUtils.CATEGORIA_TITULAR.equals(card.getCategory())
				&& TarjetaUtils.CATEGORIA_TITULAR.equals(ownerCategory)
				&& card.getCardHolderName() != null){
			DatosEntity datos = new DatosEntity();
			datos.setTitular(card.getCardHolderName());
			datos.setCategoria(card.getCategory());
			datos.setFechaDesde(parseDate(card.getDateSince()));
			datos.setTarjetaActiva(parseCardNumber(card));
			TarjetaDocumentEntity tarjetaDocumentEntity = new TarjetaDocumentEntity();
			tarjetaDocumentEntity.setDatos(datos);
			TarjetaEntity tarjetaEntity = new TarjetaEntity();
			tarjetaEntity.setTarjetaDocument(tarjetaDocumentEntity);
			tarjetaEntityList.add(tarjetaEntity);
		}
	}

	private ConsumosCuentasAuthEntity mapToConsumoCuentaAuth(CreditCardLastMovementsDto responseDto) {
		if(responseDto.getMovementsTotal() != null){
			ConsumosCuentasAuthEntity totales = new ConsumosCuentasAuthEntity();
			totales.setDolares(parseAmount(responseDto.getMovementsTotal().getTotalDollarCurrency()));
			totales.setPesos(parseAmount(responseDto.getMovementsTotal().getTotalLocalCurrency()));
			return totales;
		}
		return null;
	}

}