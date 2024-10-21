package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;

public interface HistorialComprobantesBO {

    Respuesta<ComprobantesDTO> obtenerHistorial(ComprobanteDTO comprobante, Cliente cliente);

    void limpiarCache(Cliente cliente);

}
