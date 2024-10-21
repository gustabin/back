/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.IndicesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.VariacionInfoMercadoView;

/**
 * The Interface InfoMercadoManager.
 */
public interface InfoMercadoManager {

	/**
	 * Inicio de informacion de mercado (parametros + grilla parametro default).
	 *
	 * @param view
	 *            the view
	 * @return Respuesta<InfoMercadoView>
	 */
	Respuesta<InfoMercadoView> inicioInfoMercado(InfoMercadoViewIn view);

	/**
	 * Indices informacion de mercado.
	 *
	 * @param view
	 *            the view
	 * @return Respuesta<IndicesView>
	 */
	Respuesta<IndicesView> obtenerIndices(InfoMercadoViewIn view);

	/**
	 * Grilla informacion de mercado.
	 *
	 * @param view
	 *            the view
	 * @param esPrimerIngreso
	 *            the es primer ingreso
	 * @return Respuesta<ListasEspeciesView>
	 */
	Respuesta<InfoMercadoView> obtenerGrillaInfoMercado(InfoMercadoViewIn view, Boolean esPrimerIngreso);

	/**
	 * Obtener variacion info mercado.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	Respuesta<VariacionInfoMercadoView> obtenerVariacionInfoMercado(InfoMercadoViewIn view);

	/**
	 * Grabar estadistica go to orden compra banca personal.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaGoToOrdenCompraBancaPersonal();

	/**
	 * Grabar estadistica go to orden compra banca privada.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaGoToOrdenCompraBancaPrivada();

}
