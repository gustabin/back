package ar.com.santanderrio.obp.servicios.prestamos.utils;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CuentaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SolicitudPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;

@Component
public class PrestamosEncoladosMapper {

	public SolicitudPrestamoDTO toDTO(PrestamoEncoladoEntity prestamoEncoladoEntity, String nroCuenta, BigDecimal disponible) {

		CuentaDTO cuentaDTO = new CuentaDTO();
		cuentaDTO.setNumero(nroCuenta);

		SolicitudPrestamoDTO dto = new SolicitudPrestamoDTO();
		dto.setCuenta(cuentaDTO);
		dto.setCantidadCuotas(prestamoEncoladoEntity.getQuotas());
		dto.setCodigoProducto(prestamoEncoladoEntity.getProductCode());
		dto.setCodigoSubProducto(prestamoEncoladoEntity.getSubProductCode());
		dto.setDisponible(disponible);
		dto.setMontoTotal(prestamoEncoladoEntity.getAmount());
		dto.setTipoOferta(prestamoEncoladoEntity.getLoanType().equals("PREAPROBADO") ? TipoOfertaEnum.PREAPROBADO
				: TipoOfertaEnum.PREACORDADO);
		dto.setLineaUva(prestamoEncoladoEntity.getLineUva());
		dto.setOrigenFront(prestamoEncoladoEntity.getFrontOrigin());
		dto.setFechaPrimerVencimiento(FechaUtils.obtenerFechaFormateada(prestamoEncoladoEntity.getFirstExpirationDate(), "yyyy-MM-dd"));
		return dto;

	}
}
