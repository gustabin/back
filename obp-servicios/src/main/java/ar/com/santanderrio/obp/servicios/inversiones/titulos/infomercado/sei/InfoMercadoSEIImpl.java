/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager.InfoMercadoManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.IndicesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.VariacionInfoMercadoView;

/**
 * The Class InfoMercadoSEIImpl.
 */
@Component
public class InfoMercadoSEIImpl implements InfoMercadoSEI {

	/** The info mercado manager. */
	@Autowired
	private InfoMercadoManager infoMercadoManager;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei.InfoMercadoSEI#inicioInfoMercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Override
	public Respuesta<InfoMercadoView> inicioInfoMercado(InfoMercadoViewIn view) {
		return infoMercadoManager.inicioInfoMercado(view);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei.InfoMercadoSEI#obtenerIndices(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Override
	public Respuesta<IndicesView> obtenerIndices(InfoMercadoViewIn view) {
		return infoMercadoManager.obtenerIndices(view);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei.InfoMercadoSEI#obtenerInfoMercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Override
	public Respuesta<InfoMercadoView> obtenerInfoMercado(InfoMercadoViewIn view) {
		return infoMercadoManager.obtenerGrillaInfoMercado(view, false);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei.InfoMercadoSEI#obtenerVariacionInfoMercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Override
	public Respuesta<VariacionInfoMercadoView> obtenerVariacionInfoMercado(InfoMercadoViewIn view) {
		return infoMercadoManager.obtenerVariacionInfoMercado(view);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei.InfoMercadoSEI#estadisticaGoToCompraBancaPersonal()
	 */
	@Override
	public Respuesta<Void> estadisticaGoToCompraBancaPersonal() {
		return infoMercadoManager.grabarEstadisticaGoToOrdenCompraBancaPersonal();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei.InfoMercadoSEI#estadisticaGoToCompraBancaPrivada()
	 */
	@Override
	public Respuesta<Void> estadisticaGoToCompraBancaPrivada() {
		return infoMercadoManager.grabarEstadisticaGoToOrdenCompraBancaPrivada();
	}


}
