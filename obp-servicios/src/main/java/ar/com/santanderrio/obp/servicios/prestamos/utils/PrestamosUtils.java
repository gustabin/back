package ar.com.santanderrio.obp.servicios.prestamos.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoNormativoEnum;
import ar.com.santanderrio.obp.servicios.prestamos.constants.Constants;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

public class PrestamosUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamosUtils.class);

	public static void parseNumeroPropuesta(Prestamo target, String numeroPropuesta) {
		try {
			PrestamoNormativoEnum normativoEnum = PrestamoNormativoEnum.fromNumeroPropuesta(numeroPropuesta);
			target.setCodigoPrestamoNormativo(normativoEnum);
			target.setNumeroPrestamoViejo("");

			switch (normativoEnum) {
			case COV:
				new COVParser().parseNumeroPropuesta(target, numeroPropuesta);
				break;
			default:
			}
		} catch (IllegalArgumentException ex) {
			// Sneaky catch
		}
	}

	interface NumeroPropuestaPreFormalizacionParser {

		void parseNumeroPropuesta(Prestamo target, String numeroPropuesta);
	}

	/**
	 * For each PrestamoNormativoEnum there is a different way to parse the response
	 * as the numeroPropuesta doesnt contain the same data.
	 */
	static class COVParser implements NumeroPropuestaPreFormalizacionParser {

		private static final int SUCURSAL_INDEX = 3;
		private static final int NUMERO_PRESTAMO_INDEX = 6;
		private static final int NUMERO_CUOTA_INDEX = 17;

		@Override
		public void parseNumeroPropuesta(Prestamo target, String numeroPropuesta) {
			String sucursal = "0" + numeroPropuesta.substring(SUCURSAL_INDEX, NUMERO_PRESTAMO_INDEX);
			String numeroPrestamo = "0" + numeroPropuesta.substring(NUMERO_PRESTAMO_INDEX, NUMERO_CUOTA_INDEX);
			String reciboFinanciado = numeroPropuesta.substring(NUMERO_CUOTA_INDEX);
			target.setNumeroPrestamoViejo(ISBANStringUtils.formatearNroPrestamo(sucursal, numeroPrestamo));
			target.setNumeroReciboRefinanciado(reciboFinanciado);
		}
	}

	/**
	 * Cargamos datos para el desafío
	 * 
	 * @param rsaDTO
	 * @return
	 */
	public static AutentificacionDTO cargarAutentificacionDTO(RsaDTO rsaDTO, String valorDesafioPrestamo) {
		Integer operacion = Integer.parseInt(valorDesafioPrestamo);
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(operacion);
		autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
				new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

		autentificacionDTO
				.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLUCITUD_COORDENADAS_PRESTAMOS);
		autentificacionDTO
				.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_PRESTAMOS);
		autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLICITUD_TOKEN_PRESTAMOS);
		autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_PRESTAMOS);

		autentificacionDTO.setRsaDTO(rsaDTO);
		return autentificacionDTO;
	}

	/**
	 * Retorna la diferencia en dias de la fecha recibida como parametro string en
	 * formato yyyy-MM-dd a la fecha actual
	 * 
	 * @param fecha
	 * @return cantidadDeDias
	 */
	public static Integer calcularDias(String fecha) {
		Integer cantidadDias = null;
		if (fecha != null) {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date fechaModificacion = myFormat.parse(fecha);
				cantidadDias = ISBANStringUtils.diferenciaEnDias(new Date(), fechaModificacion);
			} catch (ParseException e) {
				LOGGER.error("Error al parsear la fecha");
			}
		}
		return cantidadDias;
	}

	public static Integer obtenerCantidadDiasCambio(List<BigDecimal> antiguedades, Integer pos) {
		if (antiguedades != null && pos < antiguedades.size()) {
			BigDecimal antiguedad = antiguedades.get(pos);
			if (antiguedad != null) {
				return antiguedad.intValue();
			}
		}
		return null;
	}

	/**
	 * Permite obtener el id de session del microfront de prestamos. En caso de
	 * 
	 * @return sessionId
	 */
	public static String getSessionId() {
		try {
			HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			return curRequest.getHeader(Constants.X_SAN_SESSION_ID_HEADER);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Permite obtener el correlation id. En caso de no existir crea un UUID y
	 * guarda en el contexto del request.
	 * 
	 * @return sessionId
	 */
	public static String getCorrelationId() {
		try {
			HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			String correlationId = (String) curRequest.getAttribute(Constants.X_SAN_CORRELATION_ID_HEADER);
			if (correlationId == null) {
				correlationId = UUID.randomUUID().toString();
				curRequest.setAttribute(Constants.X_SAN_CORRELATION_ID_HEADER, correlationId);
			}
			return correlationId;
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
	}

}
