/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.EspecieDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.IndiceDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.ParametroInfoMercadoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.VariacionInfoMercadoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;

/**
 * The Interface InfoMercadoBO.
 */
public interface InfoMercadoBO {

	/**
	 * Obtiene lista ordenada de parametros para inicio de informacion de
	 * mercado.
	 *
	 * @param cliente
	 *            the cliente
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return Respuesta<List<ParametroInfoMercadoDTO>>
	 */
	Respuesta<List<ParametroInfoMercadoDTO>> obtenerParametrosInfoMercado(Cliente cliente, Boolean esBancaPrivada);

	/**
	 * Obtiene lista de indices informacion mercado.
	 *
	 * @param cliente
	 *            the cliente
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return Respuesta<List<IndiceDTO>>
	 */
	Respuesta<List<IndiceDTO>> obtenerIndices(Cliente cliente, Boolean esBancaPrivada);

	/**
	 * Obtiene grilla informacion de mercado.
	 *
	 * @param cliente
	 *            the cliente
	 * @param view
	 *            the view
	 * @return Respuesta<List<EspecieDTO>>
	 */
	Respuesta<List<EspecieDTO>> obtenerGrillaInfoMercado(Cliente cliente, InfoMercadoViewIn view);

	/**
	 * limpia cache grilla informacion de mercado.
	 *
	 * @param cliente
	 *            the cliente
	 */
	void limpiarCacheGrillaInformacionMercado(Cliente cliente);

	/**
	 * consulta variacion info mercado.
	 *
	 * @param cliente
	 *            the cliente
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	Respuesta<VariacionInfoMercadoDTO> obtenerVariacionInfoMercado(Cliente cliente, InfoMercadoViewIn view);

}
