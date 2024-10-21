/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.dto.OperacionTitulosDTO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

/**
 * The Interface TitulosOperacionesBO.
 */
public interface TitulosOperacionesBO {

    /**
	 * Obtener operaciones compra venta.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<List<OperacionTitulosDTO>> obtenerOperacionesCompraVenta(ParametrosOperacionesView parametrosOperacionesView, Cliente cliente);

    /**
	 * Limpiar cache.
	 *
	 * @param cliente
	 *            the cliente
	 */
    void limpiarCache(Cliente cliente);

    /**
	 * Obtener operaciones licitacion.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<List<OperacionTitulosDTO>> obtenerOperacionesLicitacion(
            ParametrosOperacionesView parametrosOperacionesView, Cliente cliente);

}
