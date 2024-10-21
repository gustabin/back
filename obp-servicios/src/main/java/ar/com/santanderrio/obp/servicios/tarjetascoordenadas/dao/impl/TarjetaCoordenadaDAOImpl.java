/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.ValidarCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.TarjetaCoordenadaDAO;

/**
 * The Class TarjetaCoordenadaDAOImpl.
 */
@Component
public class TarjetaCoordenadaDAOImpl implements TarjetaCoordenadaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaCoordenadaDAOImpl.class);

	/** The Constant CODIGO_ERROR_TARJETA_BLOQUEADA. */
	private static final int CODIGO_ERROR_TARJETA_BLOQUEADA = 10000066;
	
	/** The Constant CODIGO_ERROR_TARJETA_NO_ASOCIADA. */
	private static final int CODIGO_ERROR_TARJETA_NO_ASOCIADA = 10000065;

	/** The Constant CLAVE_INVALIDA. */
	private static final int CLAVE_INVALIDA = 10000053;

	/** The Constant TARJETA_COORDENADA_BLOQUEADA. */
	private static final String TARJETA_COORDENADA_BLOQUEADA = "1166";

	/** The Constant MENSAJE_INGRESAR_COORDENADAS. */
	private static final String MENSAJE_INGRESAR_COORDENADAS = "1212";

	/** The Constant MSJ_INFO_VALORES_ADICIONALES. */
	private static final String MSJ_INFO_VALORES_ADICIONALES = "Se agregan los valores necesarios para hacer la busqueda";

	/** The Constant CARACTER_BLANCO. */
	private static final String CARACTER_BLANCO = " ";

	/** The Constant IMPORTE_OPCIONAL_12_CEROS. */
	private static final String IMPORTE_OPCIONAL_12_CEROS = "000000000000";

	/** The Constant CBU_NULO. */
	private static final String CBU_NULO = "0000000000000000000000";

	/** The Constant LLAMANDO_METODO_COORDENADAS. */
	private static final String LLAMANDO_METODO_COORDENADAS = "Obteniendo coordenadas del cliente";

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDao;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The servicio pedido tarjeta coord. */
	@Value("${SERVICIO.PREFIJO.PEDTJCOORD}")
	private String servicioPedidoTarjetaCoord;

	/** The version 100 ped tj coord. */
	@Value("${SERVICIO.VERSION.PEDTJCOORD}")
	private String version100PedTjCoord;

	/** The servicio validacion tarjeta coord. */
	@Value("${SERVICIO.PREFIJO.VALTJCOORD}")
	private String servicioValidacionTarjetaCoord;

	/** The version 100 validacion tj coord. */
	@Value("${SERVICIO.VERSION.VALTJCOORD}")
	private String version100ValidacionTjCoord;
	
	/** The file path. */
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.
	 * TarjetaCoordenadaDAO#solicitarCoordenadas(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.
	 * PedidoCoordenada)
	 */
	@Override
	@Deprecated
	public Respuesta<TarjetaCoordenada> solicitarCoordenadas(Cliente cliente, PedidoCoordenada pedidoCoordenada)
			throws DAOException {

		Respuesta<TarjetaCoordenada> respuesta = new Respuesta<TarjetaCoordenada>();
		IatxRequest request = new IatxRequest(servicioPedidoTarjetaCoord, version100PedTjCoord);
		try {
			request.setData(generarRequest(cliente, pedidoCoordenada));
			IatxResponse iatxResponse = iatxComm.exec(request);

			LOGGER.info("Se llama al metodo que extrae las coordenadas para pedirle al cliente");

			if (iatxResponse.getErrorCode() == 0) {
				respuesta.setRespuesta(extraerCoordenadasSolicitadas(iatxResponse));
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

			} else if (iatxResponse.getErrorCode() == CODIGO_ERROR_TARJETA_BLOQUEADA) {
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
						this.mensajeDao.obtenerMensajePorCodigo(TARJETA_COORDENADA_BLOQUEADA).getMensaje());
				itemMensajeRespuesta.setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuesta.add(itemMensajeRespuesta);
				respuesta.setRespuestaVacia(true);
			} else {
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(iatxResponse.getErrorMessage());
				// Validado con el cliente. En este caso deberia devolver un
				// error
				itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				respuesta.add(itemMensajeRespuesta);
				respuesta.setRespuestaVacia(true);
			}

			return respuesta;

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.
	 * TarjetaCoordenadaDAO#validarCoordenadas(ar.com.santanderrio.obp.servicios
	 * .clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.
	 * ValidarCoordenada)
	 */
	@Deprecated
	public Respuesta<Boolean> validarCoordenadas(Cliente cliente, ValidarCoordenada validarCoordenada)
			throws DAOException {
		Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
		IatxRequest request = new IatxRequest(servicioValidacionTarjetaCoord, version100ValidacionTjCoord);
		try {
			IatxRequestData requestData = generarRequest(cliente, validarCoordenada.getPedidoCoordenada());

			requestData.addBodyValue(validarCoordenada.getCoordenadasHash());

			requestData.addBodyValue(validarCoordenada.getTarjetaCoordenada().getNumero());
			requestData.addBodyValue(validarCoordenada.getTarjetaCoordenada().getColumnaY1());
			requestData.addBodyValue(validarCoordenada.getTarjetaCoordenada().getFilaX1());
			requestData.addBodyValue(validarCoordenada.getTarjetaCoordenada().getColumnaY2());
			requestData.addBodyValue(validarCoordenada.getTarjetaCoordenada().getFilaX2());

			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);

			LOGGER.info("Se llama al metodo que extrae las coordenadas para pedirle al cliente");

			if (iatxResponse.getErrorCode() == 0) {
				respuesta.setRespuesta(true);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuestaVacia(false);
			} else if (iatxResponse.getErrorCode() == CODIGO_ERROR_TARJETA_BLOQUEADA) {
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
						this.mensajeDao.obtenerMensajePorCodigo(TARJETA_COORDENADA_BLOQUEADA).getMensaje());
				itemMensajeRespuesta.setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);

				respuesta.add(itemMensajeRespuesta);
				respuesta.setRespuestaVacia(true);
				respuesta.setRespuesta(false);
			} else {
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(this.mensajeDao
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_COORDENADA_INVALIDA)
						.getMensaje());
				itemMensajeRespuesta.setTipoError(TipoError.ERROR_DESAFIO.getDescripcion());

				respuesta.add(itemMensajeRespuesta);
				respuesta.setRespuestaVacia(true);
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuesta.setRespuesta(false);
			}

			return respuesta;

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Generar request.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pedidoCoordenada
	 *            the pedido coordenada
	 * @return the iatx request data
	 */
	@Deprecated
	private IatxRequestData generarRequest(Cliente cliente, PedidoCoordenada pedidoCoordenada) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		LOGGER.info("Se agregan los valores necesarios para hacer la busqueda");
		requestData.addBodyValue(pedidoCoordenada.getIp());
		requestData.addBodyValue(pedidoCoordenada.getOperacion().getValor());
		requestData.addBodyValue(IMPORTE_OPCIONAL_12_CEROS);
		String cbu = cliente.obtenerCBUCuenta(pedidoCoordenada.getNroCuenta());
		requestData.addBodyValue((cbu.isEmpty() ? CBU_NULO : cbu));
		return requestData;
	}

	/**
	 * Extraer coordenadas solicitadas.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the tarjeta coordenada
	 */
	@Deprecated
	public TarjetaCoordenada extraerCoordenadasSolicitadas(IatxResponse iatxResponse) {

		LOGGER.info("Se llena el objeto correspondiente con las coordenadas requeridas");
		TarjetaCoordenada coordenadasSolicitadas = new TarjetaCoordenada(
				// numeroTarjeta
				iatxResponse.getNextData().trim(),
				// Columna Y1
				iatxResponse.getNextData().trim(),
				// Fila X1
				iatxResponse.getNextData().trim(),
				// Columna Y2
				iatxResponse.getNextData().trim(),
				// Fila X2
				iatxResponse.getNextData().trim());

		LOGGER.info("Una vez completo, se devuelve el objeto");
		coordenadasSolicitadas
				.setMensajeCoordenadas(mensajeDao.obtenerMensajeDescripcion(MENSAJE_INGRESAR_COORDENADAS));
		return coordenadasSolicitadas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.
	 * TarjetaCoordenadaDAO#solicitarCoordenadas(ar.com.santanderrio.obp.
	 * servicios.comun.autentificacion.entities.ClienteDTO,
	 * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.
	 * CoordenadasOperacionDTO)
	 */
	@Override
	public Respuesta<CoordenadasOperacionDTO> solicitarCoordenadas(Cliente cliente,
			CoordenadasOperacionDTO coordenadasOperacionDTO) throws DAOException {
		IatxRequest request = new IatxRequest(servicioPedidoTarjetaCoord, version100PedTjCoord);
		try {
			request.setData(generarRequest(cliente, coordenadasOperacionDTO));
			IatxResponse iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getErrorCode() == 0) {
				LOGGER.info(LLAMANDO_METODO_COORDENADAS);
				//TODO
				CoordenadasOperacionDTO respCoordenadasOperacionDTO = extraerCoordenadas(iatxResponse, cliente);
				return respuestaFactory.crearRespuestaWarning(CoordenadasOperacionDTO.class,
						respCoordenadasOperacionDTO, TipoError.DESAFIO, null, "");
			} else if (iatxResponse.getErrorCode() == CODIGO_ERROR_TARJETA_BLOQUEADA) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
						CodigoMensajeConstantes.TARJETA_COORDENADA_BLOQUEADA);
			} else if (iatxResponse.getErrorCode() == CODIGO_ERROR_TARJETA_NO_ASOCIADA) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.ADHERIR_METODO_DESAFIO_MENSAJE);
            }
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Generar request.
	 *
	 * @param cliente
	 *            the cliente
	 * @param coordenadasOperacionDTO
	 *            the coordenadas operacion DTO
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequest(Cliente cliente, CoordenadasOperacionDTO coordenadasOperacionDTO) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		LOGGER.info(MSJ_INFO_VALORES_ADICIONALES);
		requestData.addBodyValue(coordenadasOperacionDTO.getPedidoCoordenada().getIp().replace(".", "").replace(":", ""));
		/*
		 * en OBP Prod no se usa el valor, viaja un CARACTER_BLANCO (Blanco
		 * indica no informado por el Banco).
		 * requestData.addBodyValue(coordenadasOperacionDTO.getPedidoCoordenada(
		 * ).getOperacion().getValor());
		 */
		requestData.addBodyValue(CARACTER_BLANCO);
		requestData.addBodyValue(IMPORTE_OPCIONAL_12_CEROS);
		String cbu = cliente.obtenerCBUCuenta(coordenadasOperacionDTO.getPedidoCoordenada().getNroCuenta());
		requestData.addBodyValue((cbu.isEmpty() ? CBU_NULO : cbu));
		return requestData;
	}

	/**
	 * Extraer coordenadas.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the coordenadas operacion DTO
	 * @throws DAOException 
	 */
	private CoordenadasOperacionDTO extraerCoordenadas(IatxResponse iatxResponse, Cliente cliente) throws DAOException {
		LOGGER.info("Se llena el objeto correspondiente con las coordenadas requeridas");
		CoordenadasOperacionDTO coordenadasSolicitadas = new CoordenadasOperacionDTO(iatxResponse.getNextData().trim(),
				iatxResponse.getNextData().trim(), iatxResponse.getNextData().trim(), iatxResponse.getNextData().trim(),
				iatxResponse.getNextData().trim());
		LOGGER.info("Una vez completo, se devuelve el objeto");

		List<String> nupsCambioMensajeTCOORD = new ArrayList<String>();
		List<String> nupsCambioMensajeTCOORD240621 = new ArrayList<String>();
		
		try {
			nupsCambioMensajeTCOORD = obtenerNupsProximaBajaTarjCoord(ExternalPropertyType.FILE_NUPS_PROX_VENC_TARJ_COORD);
			nupsCambioMensajeTCOORD240621 = obtenerNupsProximaBajaTarjCoord(ExternalPropertyType.FILE_NUPS_PROX_VENC_TARJ_COORD_240621);
		} catch (DAOException e) {
			LOGGER.error("Error al tratar de obtener los nups a los que cambiaremos el mensaje de confirmación de operacion TCOORD: ", e);
			throw new DAOException(e);
		}
		
		Mensaje respMensaje;
		
		if (nupsCambioMensajeTCOORD.contains(cliente.getNup())) {
			respMensaje = this.mensajeDao
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.NEW_CONFIRMAR_OPERACION_TARJETA_COORD);
		} else if (nupsCambioMensajeTCOORD240621.contains(cliente.getNup())) {
			respMensaje = this.mensajeDao
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CONFIRMAR_OPERACION_TARJETA_COORD_BAJA_240621);
		} else {
			respMensaje = this.mensajeDao
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CONFIRMAR_OPERACION_TARJETA_COORD);
		} 
		
		String finalMessage = MessageFormat.format(respMensaje.getMensaje(),
				coordenadasSolicitadas.getNumero().substring(coordenadasSolicitadas.getNumero().length() - 4));
		coordenadasSolicitadas.setMensaje(finalMessage);

		coordenadasSolicitadas
				.setMensajeCoordenadas(mensajeDao.obtenerMensajePorCodigo(MENSAJE_INGRESAR_COORDENADAS).getMensaje());
		return coordenadasSolicitadas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.
	 * TarjetaCoordenadaDAO#validarCoordenadas(ar.com.santanderrio.obp.servicios
	 * .comun.autentificacion.entities.ClienteDTO,
	 * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.
	 * CoordenadasOperacionDTO)
	 */
	@Override
	public Respuesta<CoordenadasOperacionDTO> validarCoordenadas(Cliente cliente,
			CoordenadasOperacionDTO coordenadasOperacionDTO) throws DAOException {

		CoordenadasOperacionDTO respCoordenadasOperacionDTO = new CoordenadasOperacionDTO();
		IatxRequest request = new IatxRequest(servicioValidacionTarjetaCoord, version100ValidacionTjCoord);
		try {
			IatxRequestData requestData = generarRequest(cliente, coordenadasOperacionDTO);
			requestData.addBodyValue(coordenadasOperacionDTO.getCoordenadasHash());
			requestData.addBodyValue(coordenadasOperacionDTO.getNumero());
			requestData.addBodyValue(coordenadasOperacionDTO.getColumnaY1());
			requestData.addBodyValue(coordenadasOperacionDTO.getFilaX1());
			requestData.addBodyValue(coordenadasOperacionDTO.getColumnaY2());
			requestData.addBodyValue(coordenadasOperacionDTO.getFilaX2());
			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);

			LOGGER.info("Se llama al metodo que extrae las coordenadas para pedirle al cliente");

			if (iatxResponse.getErrorCode() == 0) {
				respCoordenadasOperacionDTO.setCoordenadasValidas(true);
				return respuestaFactory.crearRespuestaOk(CoordenadasOperacionDTO.class, respCoordenadasOperacionDTO);
			}
			if (iatxResponse.getErrorCode() == CLAVE_INVALIDA) {
				return respuestaFactory.crearRespuestaWarning(CoordenadasOperacionDTO.class,
						respCoordenadasOperacionDTO, TipoError.ERROR_DESAFIO,
						CodigoMensajeConstantes.TRANSFERENCIA_COORDENADA_INVALIDA, "");
			}
			if (iatxResponse.getErrorCode() == CODIGO_ERROR_TARJETA_BLOQUEADA) {
				return respuestaFactory.crearRespuestaWarning(CoordenadasOperacionDTO.class,
						respCoordenadasOperacionDTO, TipoError.ERROR_REINTENTOS_AGOTADOS,
						CodigoMensajeConstantes.TARJETA_COORDENADA_BLOQUEADA_POR_REINTENTOS, "");
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * Obetngo una lista de NUPS
	 * TarjetaCoordenadaDAOImpl#obtener()
	 */
	@Override
	public List<String> obtener() throws DAOException {
		List<String> destinosPrestamo = new LinkedList<String>();

		int linea = 0;
		int largoDatosMinimo = 5;
		for (String lineaTexto : archivoResource.leerArchivo(ExternalPropertyType.FILE_NUPS_VENC_TARJ_COORD)) {
			++linea;

			if (largoDatosMinimo < lineaTexto.length()) {
				destinosPrestamo.add(lineaTexto);
			} else {
				LOGGER.debug("Error de formato en la linea: " + linea + " del archivo: "
						+ ExternalPropertyType.FILE_NUPS_VENC_TARJ_COORD.getName() + ".txt");
			}
		}
		return destinosPrestamo;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.TarjetaCoordenadaDAO#obtenerNupsProximaBajaTarjCoord()
	 */
	@Override
	public List<String> obtenerNupsProximaBajaTarjCoord(ExternalPropertyType archivo) throws DAOException {
		List<String> nupsProximaBajaTarjCoord = new LinkedList<String>();

		int linea = 0;
		int largoDatosMinimo = 5;
		for (String lineaTexto : archivoResource.leerArchivo(archivo)) {
			++linea;

			if (largoDatosMinimo < lineaTexto.length()) {
				nupsProximaBajaTarjCoord.add(lineaTexto);
			} else {
				LOGGER.debug("Error de formato en la linea: " + linea + " del archivo: "
						+ ExternalPropertyType.FILE_NUPS_PROX_VENC_TARJ_COORD.getName() + ".txt");
			}
		}
		return nupsProximaBajaTarjCoord;
	}

}
