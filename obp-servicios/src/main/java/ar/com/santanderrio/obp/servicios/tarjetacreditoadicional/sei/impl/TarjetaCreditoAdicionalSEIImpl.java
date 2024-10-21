/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.TarjetaCreditoAdicionalSEI;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.TarjetaCreditoAdicionalManager;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosAdicionalSolicitudView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DomiciliosView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SolicitudTarjetaCreditoAdicionalView;

/**
 * The Class TarjetaCreditoAdicionalSEIImpl.
 */
@Component
public class TarjetaCreditoAdicionalSEIImpl implements TarjetaCreditoAdicionalSEI {

	/** The datos solicitante manager. */
	@Autowired
	TarjetaCreditoAdicionalManager tarjetaCreditoAdicionalManager;

	/** The Constant MENSAJE_RESPUESTA. */
	private static final String MENSAJE_RESPUESTA = "Respuesta: {}.";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaCreditoAdicionalSEIImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.
	 * TarjetaCreditoAdicionalSEI#getDatosIniciales()
	 */
	@Override
	public Respuesta<SolicitudTarjetaCreditoAdicionalView> getDatosIniciales() {
		Respuesta<SolicitudTarjetaCreditoAdicionalView> respuesta = tarjetaCreditoAdicionalManager.getDatosIniciales();
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.
	 * TarjetaCreditoAdicionalSEI#getDatosDelSolicitante(ar.com.santanderrio.obp
	 * .servicios.tarjetacreditoadicional.web.view.DatosSolicitanteCriterioView)
	 */
	@Override
	public Respuesta<DatosSolicitanteView> getDatosDelSolicitante(
			DatosSolicitanteCriterioView datosSolicitanteCriterioView) {
		datosSolicitanteCriterioView.setDocTipo(
				TipoDocumentoEnum.obtenerTipoDocumento(datosSolicitanteCriterioView.getDocTipo()).getDescripcion());
		Respuesta<DatosSolicitanteView> respuesta = tarjetaCreditoAdicionalManager
				.getDatosDelSolicitante(datosSolicitanteCriterioView);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.
	 * TarjetaCreditoAdicionalSEI#normalizacionDeDomicilios()
	 */
	@Override
	public Respuesta<DomiciliosView> getResultadoMerlin(DatosMerlinInEntity datosDeDomicilio) {
		Respuesta<DomiciliosView> respuesta = tarjetaCreditoAdicionalManager.getResultadoMerlin(datosDeDomicilio);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.
	 * TarjetaCreditoAdicionalSEI#altaTarjetaCreditoAdicional(ar.com.
	 * santanderrio.obp.servicios.tarjetacreditoadicional.web.view.
	 * DatosSolicitanteConfirmadoViewResponse)
	 */
	@Override
	public Respuesta<DatosConfirmadosDelSolicitanteView> altaTarjetaCreditoAdicional(
			DatosConfirmadosDelSolicitanteView datosSolicitanteConfirmadoViewResponse) {
		Respuesta<DatosConfirmadosDelSolicitanteView> respuesta = tarjetaCreditoAdicionalManager
				.altaTarjetaCreditoAdicional(datosSolicitanteConfirmadoViewResponse);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.
	 * TarjetaCreditoAdicionalSEI#vaciarDesafio()
	 */
	@Override
	public Object vaciarDesafio() {
		tarjetaCreditoAdicionalManager.vaciarDesafio();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.
	 * TarjetaCreditoAdicionalSEI#descargaComprobanteAltaTarjeta(ar.com.
	 * santanderrio.obp.servicios.tarjetacreditoadicional.web.view.
	 * ComprobanteAltaTarjetaCreditoAdicionalView)
	 */
	@Override
	public Respuesta<ReporteView> descargaComprobanteAltaTarjeta() {
		Respuesta<ReporteView> respuesta = tarjetaCreditoAdicionalManager.descargaComprobanteAltaTarjeta();
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.TarjetaCreditoAdicionalSEI#continuarSolicitud(ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosAdicionalSolicitudView)
     */
    @Override
    public Respuesta<String> continuarSolicitud(DatosAdicionalSolicitudView datosAdicionalSolicitudView) {
        return tarjetaCreditoAdicionalManager.continuarSolicitud(datosAdicionalSolicitudView);
    }

}
