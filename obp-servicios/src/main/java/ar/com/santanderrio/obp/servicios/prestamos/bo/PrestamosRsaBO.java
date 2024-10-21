package ar.com.santanderrio.obp.servicios.prestamos.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SolicitudPrestamoDTO;

public interface PrestamosRsaBO {

	Respuesta<SimuladorPrestamoDTO> validarRsa(SolicitudPrestamoDTO solicitudPrestamoDTO, String tokenPrisma, String fase,
			boolean existeDesafioEnCurso);
}
