/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;

import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.WSGC;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.impl.CambioGrupoAfinidadDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.prestamos.dao.StopDebitPrestamosDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CompStopDebitPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.StopDebitPrestamosInEntity;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class StopDebitDAOImpl.
 */
@Component
/**
 * Conector con el servicio STPDEBPAU
 * 
 * @author b039920
 *
 */
public class StopDebitPrestamosDAOImpl implements StopDebitPrestamosDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StopDebitPrestamosDAOImpl.class);
	
	/** Gestionar ws Alias. */
	@Autowired
	@Qualifier("solicitudProductoWS")
	private GestionarWS<WSGC> wsSolicitudClient;
	
	
	/** The Constant LEGAL_PIE. */
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.CambioGrupoAfinidadDAO#altaSolicitudAdhesion(ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.AltaSolicitudCambioAfinidadInEntity)
	 */
	@Override
	public CompStopDebitPrestamoOutEntity confirmarStopDebitPrestamos(StopDebitPrestamosInEntity inEntity, SesionCliente sesionCliente) throws DAOException {
		WSGC services = null;
		CompStopDebitPrestamoOutEntity respuesta = new CompStopDebitPrestamoOutEntity();
		String fechaHora = ISBANStringUtils.formatearFecha(new Date(), ISBANStringUtils.FORMATO_FECHA_HORA_COMPROBANTE);
		ResultadoAltaWS respuestaWS;
		try {
			services = wsSolicitudClient.obtenerPort();
			respuestaWS = services.altaGestion(inEntity.getCodAsociacion(), inEntity.getTipoPersona(), inEntity.getNup(), inEntity.getCodSector(), inEntity.getCodUser(), inEntity.getMedioIngreso(), inEntity.getComentario(),
					inEntity.getInfoRequerida());
			if (respuestaWS.getCodRetorno() == 0) {
				String numeroGestion = respuestaWS.getIdeGestionNro().toString();
				respuesta.setFecha(fechaHora);
				respuesta.setComprobante(numeroGestion);
			}
		} catch (RuntimeException e) {
			LOGGER.error("Hubo un error al invocar al ws confirmarStopDebitPrestamos.", e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (TimeOutException e) {
			LOGGER.error("Hubo un error al invocar al ws confirmarStopDebitPrestamos", e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} finally {
			wsSolicitudClient.liberarPort(services);
		}
		return respuesta;
	}
}
