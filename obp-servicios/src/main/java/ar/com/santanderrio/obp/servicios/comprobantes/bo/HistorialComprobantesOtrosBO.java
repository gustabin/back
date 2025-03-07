package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

public interface HistorialComprobantesOtrosBO {

	Respuesta<List<ComprobanteDTO>> obtenerHistorialOtros(ComprobanteDTO comprobanteSeleccionado, Cliente cliente,
			TransaccionDTO transaccionDTO);

}
