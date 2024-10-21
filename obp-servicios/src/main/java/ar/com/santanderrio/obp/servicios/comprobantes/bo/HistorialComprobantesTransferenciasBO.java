package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

public interface HistorialComprobantesTransferenciasBO {
	
	Respuesta<List<ComprobanteDTO>> obtenerHistorialTransferencias(ComprobanteDTO comprobanteSeleccionado, Cliente cliente,
			TransaccionDTO transaccionDTO);


}
