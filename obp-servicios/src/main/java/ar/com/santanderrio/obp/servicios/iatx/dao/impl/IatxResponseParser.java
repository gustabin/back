/*
 * 
 */
package ar.com.santanderrio.obp.servicios.iatx.dao.impl;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class IatxResponseParser.
 */
@SuppressWarnings("PMD")
public final class IatxResponseParser {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IatxResponseParser.class);

	/** The Constant POSICION_INICIAL_NUMERO_MENSAJE_MULTIPLE. */
	private static final int POSICION_INICIAL_NUMERO_MENSAJE_MULTIPLE = 7;

	/** The Constant POSICION_FINAL_NUMERO_MENSAJE_MULTIPLE. */
	private static final int POSICION_FINAL_NUMERO_MENSAJE_MULTIPLE = 10;

	/** The Constant NUMERO_MENSAJE_MULTIPLE_000. */
	private static final String NUMERO_MENSAJE_MULTIPLE_000 = "000";

	/** The Constant POSICION_INICIAL_ID_SESION_TRANSACCIONAL. */
	private static final int POSICION_INICIAL_ID_SESION_TRANSACCIONAL = 86;

	/** The Constant POSICION_FINAL_ID_SESION_TRANSACCIONAL. */
	private static final int POSICION_FINAL_ID_SESION_TRANSACCIONAL = 94;

	/** The Constant POSICION_INICIAL_SESION_USUARIO. */
	private static final int POSICION_INICIAL_SESION_USUARIO = 48;

	/** The Constant POSICION_FINAL_SESION_USUARIO. */
	private static final int POSICION_FINAL_SESION_USUARIO = 56;

	/** The Constant POSICION_INICIAL_FECHA_HORA_REQUEST. */
	private static final int POSICION_INICIAL_FECHA_HORA_REQUEST = 64;

	/** The Constant POSICION_FINAL_FECHA_HORA_REQUEST. */
	private static final int POSICION_FINAL_FECHA_HORA_REQUEST = 78;

	/** The Constant POSICION_INICIAL_NOMBRE_SERVICIO. */
	private static final int POSICION_INICIAL_NOMBRE_SERVICIO = 106;

	/** The Constant POSICION_FINAL_NOMBRE_SERVICIO. */
	private static final int POSICION_FINAL_NOMBRE_SERVICIO = 116;

	/** The Constant POSICION_INICIAL_VERSION_SERVICIO. */
	private static final int POSICION_INICIAL_VERSION_SERVICIO = 116;

	/** The Constant POSICION_FINAL_VERSION_SERVICIO. */
	private static final int POSICION_FINAL_VERSION_SERVICIO = 119;

	/** The Constant POSICION_INICIAL_IATX_BODY. */
	private static final int POSICION_INICIAL_IATX_BODY = 326;

	/** The Constant POSICION_INICIAL_NUMERO_COMPROBANTE. */
	private static final int POSICION_INICIAL_NUMERO_COMPROBANTE = 161;

	/** The Constant POSICION_FINAL_NUMERO_COMPROBANTE. */
	private static final int POSICION_FINAL_NUMERO_COMPROBANTE = 169;

	/** The Constant POSICION_INICIAL_FECHA. */
	private static final int POSICION_INICIAL_FECHA = 169;

	/** The Constant POSICION_FINAL_FECHA. */
	private static final int POSICION_FINAL_FECHA = 177;

	/** The Constant POSICION_INICIAL_HORA. */
	private static final int POSICION_INICIAL_HORA = 177;

	/** The Constant POSICION_FINAL_HORA. */
	private static final int POSICION_FINAL_HORA = 183;

	/** The Constant SUBSTRING_HORA. */
	private static final int SUBSTRING_HORA = 4;

	/** The Constant POSICION_INICIAL_INCICE_SINONIMO. */
	private static final int POSICION_INICIAL_INCICE_SINONIMO = 158;

	/** The Constant POSICION_FINAL_INCICE_SINONIMO. */
	private static final int POSICION_FINAL_INCICE_SINONIMO = 159;

	/** The Constant SIZE_BODY. */
	private static final int SIZE_BODY = 3;

	/** The Constant POSICION_ERROR_MENSAJE. */
	private static final int POSICION_ERROR_MENSAJE = 3;

	/** The Constant POSICION_INICIAL_DELIMITADOR. */
	private static final int POSICION_INICIAL_DELIMITADOR = 320;

	/** The Constant POSICION_FINAL_DELIMITADOR. */
	private static final int POSICION_FINAL_DELIMITADOR = 321;

	/** The Constant POSICION_INICIAL_DATA_INDEX. */
	private static final int POSICION_INICIAL_DATA_INDEX = 3;

	/** The Constant IATX_LENGHT_326. */
	private static final int IATX_LENGHT_326 = 326;
	
	/** The Constant STRING_BUFFERING. */
	private static final String STRING_BUFFERING = "100P";

	/** The Constant POSICION_INICIAL_DATA_INDEX. */
	private static final int POSICION_INICIAL_STRING_BUFFERING = 9;
	
	/** The Constant POSICION_INICIAL_DATA_INDEX. */
	private static final int POSICION_FINAL_STRING_BUFFERING = 13;
	/**
	 * Instantiates a new iatx response parser.
	 */
	private IatxResponseParser() {
		// not called
	}

	/**
	 * Parses the response.
	 *
	 * @param iatxRes
	 *            the iatx res
	 * @param iatxResponse
	 *            the iatx response
	 * @return the iatx response
	 */
	public static IatxResponse parseResponse(String iatxRes, IatxResponse iatxResponse) {
		IatxResponse iatxAux = iatxResponse;
		boolean esRellamada = false;
		boolean esBuffering = false;
		if (iatxAux != null) {
			esRellamada = true;
			esBuffering = iatxAux.isBuffering();
		} else {
			iatxAux = new IatxResponse();
		}

		if (iatxRes.length() > IATX_LENGHT_326) {
			// para rellamadas
			iatxAux.setNroMjeMultiple(iatxRes.substring(POSICION_INICIAL_NUMERO_MENSAJE_MULTIPLE,
					POSICION_FINAL_NUMERO_MENSAJE_MULTIPLE));
			iatxAux.setBuffering(STRING_BUFFERING.equals(iatxRes.substring(POSICION_INICIAL_STRING_BUFFERING, 
					POSICION_FINAL_STRING_BUFFERING)));
			iatxAux.setIdSesionTransaccional(iatxRes.substring(POSICION_INICIAL_ID_SESION_TRANSACCIONAL,
					POSICION_FINAL_ID_SESION_TRANSACCIONAL));

			// para verificacion de cruce de transacciones
			iatxAux.setSesionUsuario(iatxRes.substring(POSICION_INICIAL_SESION_USUARIO, POSICION_FINAL_SESION_USUARIO));
			iatxAux.setFechaHoraReq(
					iatxRes.substring(POSICION_INICIAL_FECHA_HORA_REQUEST, POSICION_FINAL_FECHA_HORA_REQUEST));
			iatxAux.setNombreServicio(
					iatxRes.substring(POSICION_INICIAL_NOMBRE_SERVICIO, POSICION_FINAL_NOMBRE_SERVICIO));
			iatxAux.setVersionServicio(
					iatxRes.substring(POSICION_INICIAL_VERSION_SERVICIO, POSICION_FINAL_VERSION_SERVICIO));
			String delim = iatxRes.substring(POSICION_INICIAL_DELIMITADOR, POSICION_FINAL_DELIMITADOR);
			
			// al primer llamado, obtengo el status
			if (!esRellamada) {
				iatxAux.setIatxBody(delimStringTokenizer(
						iatxRes.substring(POSICION_INICIAL_IATX_BODY, iatxRes.length() - 1), delim));
				try {
					iatxAux.setErrorCode(Integer.parseInt(iatxAux.getIatxBody().elementAt(0)));
				} catch (Exception e) {
					iatxAux.setErrorCode(-1);
				}
				iatxAux.setNroComprobante(
						iatxRes.substring(POSICION_INICIAL_NUMERO_COMPROBANTE, POSICION_FINAL_NUMERO_COMPROBANTE));
				iatxAux.setFecha(iatxRes.substring(POSICION_INICIAL_FECHA, POSICION_FINAL_FECHA));
				iatxAux.setHora(iatxRes.substring(POSICION_INICIAL_HORA, POSICION_FINAL_HORA));
				iatxAux.setHora(iatxAux.getHora().substring(0, 2) + ":" + iatxAux.getHora().substring(2, SUBSTRING_HORA)
						+ ":" + iatxAux.getHora().substring(SUBSTRING_HORA));
				iatxAux.setIndicSinonimo(
						iatxRes.substring(POSICION_INICIAL_INCICE_SINONIMO, POSICION_FINAL_INCICE_SINONIMO));
				if (iatxAux.getErrorCode() > 0 && iatxAux.getIatxBody().size() >= SIZE_BODY) {
					iatxAux.setErrorSystem(iatxAux.getIatxBody().elementAt(1));
					int cantMsg = Integer.parseInt(iatxAux.getIatxBody().elementAt(2));
					StringBuilder sb = new StringBuilder(
							iatxAux.getIatxBody().elementAt(POSICION_ERROR_MENSAJE).trim());
					for (int i = POSICION_ERROR_MENSAJE + 1; i < POSICION_ERROR_MENSAJE + cantMsg; i++) {
						sb.append(" ");
						sb.append(iatxAux.getIatxBody().elementAt(i).trim());
					}
					iatxAux.setErrorMessage(sb.toString());
					// donde empiezan los datos, si es warning
					iatxAux.setFirstDataIndex(POSICION_INICIAL_DATA_INDEX + cantMsg);
				}
				iatxAux.resetDataIndex();
			} else {
				// si es rellamada, simplemente agrego los datos al vector
				iatxAux.getIatxBody().addAll(delimStringTokenizer(
						iatxRes.substring(POSICION_INICIAL_IATX_BODY, iatxRes.length() - 1), delim));
				
				iatxAux.setTrama(iatxAux.getTrama().concat(iatxRes.substring(POSICION_INICIAL_IATX_BODY, iatxRes.length())));
			}
		} else {
			// vacio
			iatxAux.setIatxBody(new Vector<String>());
			iatxAux.setNroMjeMultiple(NUMERO_MENSAJE_MULTIPLE_000);
			iatxAux.setIdSesionTransaccional("");
			iatxAux.setErrorCode(-1);
		}

		evaluarCondicionRespuesta(iatxAux);

		return iatxAux;
	}

	/**
	 * Evaluar condicion respuesta.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 */
	private static void evaluarCondicionRespuesta(IatxResponse iatxResponse) {

		if (iatxResponse.getErrorCode() == 0) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
			return;
		}

		/*
		 * TODO : Ver uso de key a partir de codigo obp - actual String key
		 * //TODO: buscar key en properties (hbspecialcondition.properties en
		 * proyecto viejo)
		 * 
		 */
		String value = "";

		if (TipoError.LISTAVACIA.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.LISTAVACIA);
		} else if (TipoError.PINVENCIDO.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.PINVENCIDO);
		} else if (TipoError.SINONIMO.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.SINONIMO);
		} else if (TipoError.CLAVEINVALIDA.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.CLAVEINVALIDA);
		} else {
			iatxResponseOtrosTiposError(value, iatxResponse);
		}
	}

	/**
	 * Iatx response otros tipos error.
	 *
	 * @param value
	 *            the value
	 * @param iatxResponse
	 *            the iatx response
	 */
	private static void iatxResponseOtrosTiposError(String value, IatxResponse iatxResponse) {
		if (TipoError.FUERADEHORARIO.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.FUERADEHORARIO);
		} else if (TipoError.USUARIOBLOQUEADO.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.USUARIOBLOQUEADO);
		} else if (TipoError.USUARIOYADEFINIDO.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.USUARIOYADEFINIDO);
		} else if (TipoError.NOCONTESTO.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.NOCONTESTO);
		} else if (TipoError.WARNING.getDescripcion().equals(value)) {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.WARNING);
		} else if (TipoError.WARNINGOK.getDescripcion().equals(value)) {
			iatxResponse.setWarningOk(true);
			iatxResponse.setTipoError(TipoError.WARNINGOK);
		} else if (TipoError.VENCIMIENTOPIN.getDescripcion().equals(value)) {
			// TODO NO HACE NADA
			LOGGER.info("Entra en opcion {} y no hace nada.", TipoError.VENCIMIENTOPIN.getDescripcion());
		} else {
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
			iatxResponse.setTipoError(TipoError.ERROR_GENERICO);
		}

	}

	/**
	 * Delim string tokenizer.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the vector
	 */
	private static Vector<String> delimStringTokenizer(String a, String b) {
		String newA = a;
		Vector<String> v = new Vector<String>();
		String token;
		boolean end = false;
		int posEnd = 0, posBeg = 0;

		while (!end) {
			posEnd = newA.indexOf(b);
			if (posEnd == -1) {
				posEnd = newA.length();
				end = true;
			}
			token = newA.substring(posBeg, posEnd);
			v.add(token);
			if (!end) {
				newA = newA.substring(posEnd + 1, newA.length());
			}
		}
		return v;
	}

}
