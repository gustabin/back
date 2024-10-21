/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.sei.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.nuevarecarga.sei.NuevaRecargaSEI;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager.NuevaRecargaManager;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;

/**
 * The Class NuevaRecargaSEIImpl.
 *
 * @author florencia.n.martinez
 */
@Component("nuevaRecargaSEI")
public class NuevaRecargaSEIImpl implements NuevaRecargaSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NuevaRecargaSEIImpl.class);

	/** The Constant POST_OK. */
	private static final String POST_OK = "Post OK: /{}.";

	/** The Constant REQUEST_FEEDBACK. */
	private static final String REQUEST_FEEDBACK = "recargar";

	/** The nueva recarga manager. */
	@Autowired
	private NuevaRecargaManager nuevaRecargaManager;
	
    @Autowired
    private EstadisticaManager estadisticaManager;

	/**
	 * Obtiene la confirmacion de la recarga.
	 *
	 * @param datosConfirmacionRecarga
	 *            the datos confirmacion recarga
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConfirmacionRecargaView> obtenerConfirmacionNuevaRecarga(
			ConfirmacionRecargaView datosConfirmacionRecarga) {
		LOGGER.info(POST_OK, REQUEST_FEEDBACK);
		return nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(datosConfirmacionRecarga);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevarecarga.sei.NuevaRecargaSEI#
	 * estadisticaVerComprobante()
	 */
	@Override
	public boolean estadisticaVerComprobante() {
		return nuevaRecargaManager.estadisticaVerComprobante();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * descargarComprobantePDF()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF() {
		return nuevaRecargaManager.descargarComprobantePDF();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.nuevarecarga.sei.NuevaRecargaSEI#continuarPago(ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView)
	 */
	@Override
	public Respuesta<Void> continuarPago(ConfirmacionRecargaView nuevoPagoView) {
		return nuevaRecargaManager.continuarPago(nuevoPagoView);

	}

    @Override
    public Boolean grabarEstadisticaCargaDatosRecargaCelulares() {
        estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_CARGA_DATOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return true;
    }

    @Override
    public Boolean grabarEstadisticaConfirmacionRecargaCelulares() {
        estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_CONFIRMACION,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return true;
    }
    
    @Override
    public Boolean grabarEstadisticaIngresoConfiguracionDesdeAgenda() {
        estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_INGRESO_DESDE_AGENDA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return true;
    }

    @Override
	public Respuesta<Void> eliminarCelular(CelularView nuevoNumero) {
		return nuevaRecargaManager.eliminarCelular(nuevoNumero);
	}

	@Override
	public Respuesta<Void> actualizarAliasCelular(CelularView nuevoNumero) {
		return nuevaRecargaManager.actualizarAliasCelular(nuevoNumero);
	}
    
	@Override
	public Respuesta<List<CelularView>> obtenerCelulares() {
		return nuevaRecargaManager.obtenerCelulares();
	}
}
