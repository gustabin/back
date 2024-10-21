package ar.com.santanderrio.obp.servicios.perfil.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioNombreDTO;

public interface CambioNombreBO {

	Respuesta<CambioNombreDTO> solicitarCambioNombreCliente(String nuevoNombre);
	
}
