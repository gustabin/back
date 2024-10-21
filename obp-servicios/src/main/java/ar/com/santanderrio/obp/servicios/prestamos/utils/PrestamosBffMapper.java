package ar.com.santanderrio.obp.servicios.prestamos.utils;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SolicitudPrestamoDTO;

@Component
public class PrestamosBffMapper {

	private static final String CANAL_OBP = "OBP";

	public EncolamientoRequestDTO toDTO(SolicitudPrestamoDTO solicitud) {
		EncolamientoRequestDTO dto = new EncolamientoRequestDTO();
		dto.setNup(solicitud.getNup());
		dto.setMontoTotal(solicitud.getMontoTotal());
		dto.setCanal(CANAL_OBP);
		dto.setTipoPrestamo(solicitud.getTipoOferta().name());
		dto.setCuenta(solicitud.getCuenta());
		dto.setCodigoProducto(solicitud.getCodigoProducto());
		dto.setCodigoSubProducto(solicitud.getCodigoSubProducto());
		dto.setDestinoFondos(solicitud.getDestinoFondos());
		dto.setSucursalPaquete(solicitud.getSucursalPaquete());
		dto.setNumeroPaquete(solicitud.getNumeroPaquete());
		dto.setCodigoContable(solicitud.getCodigoContable());
		dto.setRiesgoContable(solicitud.getRiesgoContable());
		dto.setCantidadCuotas(solicitud.getCantidadCuotas());
		dto.setFechaCierre(solicitud.getFechaPrimerVencimiento());
		dto.setTna(solicitud.getTna());
		dto.setLineaUva(solicitud.isLineaUva() ? "S" : "N");
		dto.setFechaAprobacion(solicitud.getFechaAprobacion());
		dto.setFechaFormalizacion(solicitud.getFechaFormalizacion());
		dto.setTipoTasa(solicitud.getTipoTasa() != null ? solicitud.getTipoTasa().name() : null);
		dto.setIndiceNegociable(solicitud.getIndiceNegociable());
		dto.setIndicadorBonifCuenta(solicitud.getIndicadorBonifCuenta());
		dto.setCodigoAgente(solicitud.getCodigoAgente());
		dto.setCodigoModalidadAcreditacion(solicitud.getCodigoModalidadAcreditacion());
		dto.setCodigoInstrumento(solicitud.getCodigoInstrumento());
		dto.setIndicadorIndexacion(solicitud.getIndicadorIndexacion());
		dto.setCodigoRiesgo(solicitud.getCodigoRiesgo());
		dto.setCodigoCondicionAlternativa(solicitud.getCodigoCondicionAlternativa());
		dto.setTipoCondicionAlternativa(solicitud.getTipoCondicionAlternativa());
		dto.setTokenSimulacion(solicitud.getTokenSimulacion());
		dto.setOrigenFront(solicitud.getOrigenFront());
		return dto;
	}

}
