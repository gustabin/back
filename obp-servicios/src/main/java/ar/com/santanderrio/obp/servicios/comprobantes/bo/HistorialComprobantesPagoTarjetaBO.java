package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

public interface HistorialComprobantesPagoTarjetaBO {

	Respuesta<List<ComprobanteDTO>> obtenerHistorialPagoTarjeta(ComprobanteDTO comprobanteSeleccionado, Cliente cliente,
			TransaccionDTO transaccionDTO);

}
