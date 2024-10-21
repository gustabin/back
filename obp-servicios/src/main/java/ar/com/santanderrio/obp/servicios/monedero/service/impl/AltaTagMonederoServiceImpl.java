/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.service.AltaTagMonederoService;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;

/**
 * The Class AltaTagMonederoServiceImpl.
 */
@Component
public class AltaTagMonederoServiceImpl implements AltaTagMonederoService {

	/** The alta tag monedero BO. */
	@Autowired
	private AltaTagMonederoBO altaTagMonederoBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.monedero.service.AltaTagMonederoService
	 * #ejecutarAltaTagMonedero(ar.com.santanderrio.obp.servicios.monedero.
	 * entities.DatosAltaTagMonedero,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobanteAltaTagMonederoView> ejecutarAltaTagMonedero(
			DatosAltaTagMonederoEntity datosAltaTagMonedero, Cliente cliente) {
		return altaTagMonederoBO.ejecutarAltaTagMonedero(datosAltaTagMonedero, cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.monedero.service.AltaTagMonederoService
	 * #generarComprobanteAltaTagMonedero(ar.com.santanderrio.obp.servicios.
	 * monedero.web.view.ComprobanteAltaTagMonederoView)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteAltaTagMonedero(
			ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView) {
		return altaTagMonederoBO.generarComprobanteAltaTagMonedero(comprobanteAltaTagMonederoView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.monedero.service.AltaTagMonederoService
	 * #activarMonederoTag(ar.com.santanderrio.obp.servicios.monedero.web.view.
	 * DatosParaActivacionView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> activarMonederoTag(DatosParaActivacionView datosParaActivacionView,
			Cliente cliente) {
		return altaTagMonederoBO.activarMonederoTag(datosParaActivacionView, cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.monedero.service.AltaTagMonederoService
	 * #generarComprobanteActivacionTagMonedero(ar.com.santanderrio.obp.
	 * servicios.monedero.web.view.ComprobanteActivacionTagMonederoView)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView) {
		return altaTagMonederoBO.generarComprobanteActivacionTagMonedero(comprobanteActivacionTagMonederoView);
	}

	/**
	 * Es persona habilitada.
	 *
	 * @param entity
	 *            the entity
	 * @return the consulta inhabilitados out entity
	 */
	@Override
	public ConsultaInhabilitadosOutEntity esPersonaHabilitada(ConsultaInhabilitadosInEntity entity) {
		return altaTagMonederoBO.esPersonaHabilitada(entity);
	}

	/**
	 * Obtener legal.
	 *
	 * @param codigoLegal
	 *            the codigo legal
	 * @return the string
	 */
	public String obtenerLegal(String codigoLegal) {
		return null;
	}
}
