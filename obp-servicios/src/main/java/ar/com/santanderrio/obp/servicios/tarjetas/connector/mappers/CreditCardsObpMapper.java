package ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.OwnerDataDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements.CreditCardLastMovementsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.*;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class CreditCardsObpMapper {
    
	private final DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMAN);

    public RetornoTarjetasEntity createNoServiceResponse(String codigo) {
        ErrorTarjetasEntity errorTarjetasEntity = new ErrorTarjetasEntity();
        errorTarjetasEntity.setCodigo(codigo);
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        tarjetaEntity.setError(errorTarjetasEntity);
        List<TarjetaEntity> tarjetas = new ArrayList<TarjetaEntity>();
        tarjetas.add(tarjetaEntity);
        TarjetasEntity tarjetasEntity = new TarjetasEntity();
        tarjetasEntity.setTarjetaList(tarjetas);
        RetornoTarjetasEntity retorno = new RetornoTarjetasEntity();
        retorno.setTarjetas(tarjetasEntity);
        return retorno;
    }

    public DatosEntity mapToDatos(OwnerDataDto ownerDataDto) {
        DatosEntity datos = new DatosEntity();
        datos.setTarjetaActiva(String.valueOf(ownerDataDto.getActiveCardNumber()));
        datos.setCategoria(ownerDataDto.getCategory());
        datos.setTitular(ownerDataDto.getOwnerName());
        datos.setFechaDesde(parseDate(ownerDataDto.getDateSince()));
        datos.setHabiente(ownerDataDto.getCardHolderName());
        return datos;
    }

    public String parseAmount(Double value){
        format.setGroupingUsed(false);
        format.setDecimalSeparatorAlwaysShown(true);
        format.setGroupingUsed(false);
        format.applyPattern("####0.00");
        return format.format(value);
    }

    public String parseDate(String value){
        return ISBANStringUtils.formatearFechaConGuiones(value);
    }
    
}
