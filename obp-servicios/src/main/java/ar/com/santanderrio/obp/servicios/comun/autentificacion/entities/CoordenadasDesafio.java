/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo.TarjetaCoordenadaBO;

/**
 * Esta clase la implementa {@link Desafio} .
 *
 * @author ignacio.valek
 * @author emilio.watemberg
 * @since Sep 22, 2016.
 */
@Component
public class CoordenadasDesafio extends Desafio<AutentificacionDTO> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CoordenadasDesafio.class);

	/** The Constant SOLICITANDO_COORDENADAS. */
	private static final String SOLICITANDO_COORDENADAS = "Fallo la validacion de coordenadas. Solicitando coordenadas nuevamente..";

	/** The tarjeta coordenada BO. */
	@Autowired
	private TarjetaCoordenadaBO tarjetaCoordenadaBO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio#
	 * solicitar()
	 */
	@Override
	public Respuesta<AutentificacionDTO> solicitar() {
		return tarjetaCoordenadaBO.solicitarCoordenadas(super.autentificacionDTO, super.codigoEstadisticaSolicitud,
				super.soloEstaVerificandoSiHayDesafios);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio#
	 * ejecutar(java.lang.Object)
	 */
	@Override
	public Respuesta<AutentificacionDTO> ejecutar(AutentificacionDTO autentificacionDTO) {
		Respuesta<AutentificacionDTO> validarCoordenadas = tarjetaCoordenadaBO.validarCoordenadas(autentificacionDTO,
				super.codigoEstadisticaValidacion);
		if (EstadoRespuesta.WARNING.equals(validarCoordenadas.getEstadoRespuesta())) {
			if (!noEsReintentosAgotados(validarCoordenadas)) {
				// Validacion incorrecta de Coordenadas
				sesionParametros.setRSACantidadIntentos(sesionParametros.getRSACantidadIntentos() + 1);
				// Si falla la ejecucion, se deben volver a solicitar las
				// coordenadas para mostrar en pantalla.
				LOGGER.info(SOLICITANDO_COORDENADAS);
				Respuesta<AutentificacionDTO> solicitarCoordenadas = tarjetaCoordenadaBO.solicitarCoordenadas(
						super.autentificacionDTO, super.codigoEstadisticaSolicitud,
						super.soloEstaVerificandoSiHayDesafios);
				switch (solicitarCoordenadas.getEstadoRespuesta()) {
				case ERROR:
					return solicitarCoordenadas;
				default:
					// Nuevas coordeanadas solicitadas
					validarCoordenadas.setRespuesta(solicitarCoordenadas.getRespuesta());
				}
			}
		}
		return validarCoordenadas;
	}

	/**
	 * No es reintentos agotados.
	 *
	 * @param validarCoordenadas
	 *            the validar coordenadas
	 * @return true, if successful
	 */
	private boolean noEsReintentosAgotados(Respuesta<AutentificacionDTO> validarCoordenadas) {
		return EstadoRespuesta.WARNING.equals(validarCoordenadas.getEstadoRespuesta())
				&& validarCoordenadas.getItemsMensajeRespuesta().get(0).getTipoError()
						.equals(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion().toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Desafio<AutentificacionDTO> o) {
		return Comparators.PRIORIDAD.compare(this, o);
	}

}
