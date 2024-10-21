/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.ConsultaPaquetesDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.InformacionCuentaPaqueteInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.PaqueteEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ClienteHabilitadoChequesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.CodigosBancariosDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ConsultaTasasIndicativasDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.DescuentoChequesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.DetalleOperacionesPrecargadasDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EliminarOperacionDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EntidadesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAceptadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesRechazadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DescuentoChequesEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleHistorialOperacionesDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionPrecargadaEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EliminarOperacionEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.MensajesDescuentoEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionDescuentoEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionPrecargadaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionesPrecargadasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasaIndicativa;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasaIndicativaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasEntity;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class DescuentoChequesBOImpl.
 */
@Component
public class DescuentoChequesBOImpl implements DescuentoChequesBO {

	/** The cesion cliente. */
	@Autowired
	private ClienteHabilitadoChequesDAO cesionCliente;

	/** The consulta tasas. */
	@Autowired
	private ConsultaTasasIndicativasDAO consultaTasas;

	/** The descuento cheques. */
	@Autowired
	private DescuentoChequesDAO descuentoCheques;

	/** The eliminar operacion dao. */
	@Autowired
	private EliminarOperacionDAO eliminarOperacionDao;

	/** The detalle precargada dao. */
	@Autowired
	private DetalleOperacionesPrecargadasDAO detallePrecargadaDao;

	/** The consulta. */
	@Autowired
	private ConsultaPaquetesDAO consulta;

	/** The nup dao. */
	@Autowired
	protected NUPDAO nupDao;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The codigos DAO. */
	@Autowired
	private CodigosBancariosDAO codigosDAO;

	/** The entidades DAO. */
	@Autowired
	private EntidadesDAO entidadesDAO;

	/** The Mensaje dao. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The max cant cheques. */
	@Value("${DESCCHEQUES.MAX.CANTIDAD.CHEQUES}")
	private String maxCantCheques;

	/** The max importe cheque. */
	@Value("${DESCCHEQUES.MAX.IMPORTE.CHEQUES}")
	private String maxImporteCheque;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DescuentoChequesBOImpl.class);

	/** The Constant ERROR_TASAS_INDICATIVAS. */
	private static final String ERROR_TASAS_INDICATIVAS = "errorTasasIndicativas";

	/** The Constant ERROR_SIN_OPERACIONES. */
	private static final String ERROR_SIN_OPERACIONES = "errorSinOperaciones";

	/** The Constant ERROR_GENERICO. */
	private static final String ERROR_GENERICO = "errorGenerico";

	//mlemos
	@Autowired
	private TransaccionesControllerHomeBO transaccionesControllerHomeBO;
    //mlemos

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO#obtenerMontoCalificadoCesion(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<DatosCesionDTO> obtenerMontoCalificadoCesion(Cliente cliente) {
		DatosCesionEntity entity = null;
		LOGGER.info("ML entre a obtenerMontoCalificadoCesion 1");
       if (Boolean.FALSE.equals(transaccionesControllerHomeBO.aplicaDescuentoCheques(cliente))){
		   LOGGER.info("ML entre a cheques cliente NOK");
		   return respuestaFactory.crearRespuestaWarning("cabeceraNoCalificado",
				   TipoError.NO_CALIFICADO_CABECERA_DESCUENTOCHEQUES,
				   CodigoMensajeConstantes.DESCUENTO_CHEQUES_CABECERA_NO_CALIFICADO);
	   }
	   LOGGER.info("ML pase el IF no hay error");
		try {
			entity = cesionCliente.obtenerHabilitadoCesion(cliente);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		if (entity == null) {
			LOGGER.error("Error de timeOut en el DAO");
			return respuestaFactory.crearRespuestaError("errorServicioCalificacion",
					TipoError.ERROR_SERVICIO_CALIFICACION,
					CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS);
		} else if (0 != Integer.valueOf(entity.getCodigoRetornoExtendido())) {
			LOGGER.error("Codigo de error distinto de 0 en servicio");
			return respuestaFactory.crearRespuestaError("errorCabeceraInicio",
					TipoError.ERROR_CABECERA_DESCUENTOCHEQUES,
					CodigoMensajeConstantes.DESCUENTO_CHEQUES_CABECERA_ERROR);
		}
		if (!"S".equals(entity.getCalificadoS())) {
			LOGGER.error("Cliente no calificado");
			return respuestaFactory.crearRespuestaWarning("cabeceraNoCalificado",
					TipoError.NO_CALIFICADO_CABECERA_DESCUENTOCHEQUES,
					CodigoMensajeConstantes.DESCUENTO_CHEQUES_CABECERA_NO_CALIFICADO);
		}
		LOGGER.info("ML llegue al return");
		return respuestaFactory.crearRespuestaOk(DatosCesionDTO.class, obtenerDatosCesionDTO(entity, cliente));
	}

	/**
	 * Obtener datos cesion DTO.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return the datos cesion DTO
	 */
	private DatosCesionDTO obtenerDatosCesionDTO(DatosCesionEntity entity, Cliente cliente) {
		DatosCesionDTO datosCesion = new DatosCesionDTO();
		//Mlemos
		LOGGER.info("ML entre a obtenerDatosCesionDTO 2");
		if ( sesionParametros.getIsCalificadoModuloDescCheques().equals(true)){
			LOGGER.info("ML entre a cheques cliente OK");
		}else{
			LOGGER.info("Cliente no calificado obtenerDatosCesionDTO");
		}
		//Mlemos
		datosCesion.setCalificadoS(entity.getCalificadoS());
		try {
			datosCesion.setLineaS(ISBANStringUtils.convertirStrToBigDecimal(entity.getLineaS(), 2));
			datosCesion.setMontoDisponibleS(ISBANStringUtils.convertirStrToBigDecimal(entity.getMontoDisponibleS(), 2));
			datosCesion.setNroCuenta(sesionParametros.getCuentaSeleccionadaParaTransferencia("NROCUENTA"));
			NupDTO dto = null;
			try {
				dto = nupDao.obtenerNUP(cliente);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			if (dto != null) {
				obtenerDniCuitCuil(datosCesion, dto);
			}
			datosCesion.setMensajes(obtenerMensajes());
			datosCesion.setMaxCantCheques(maxCantCheques);
			datosCesion.setMaxImporteCheque(maxImporteCheque);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al parsear respuesta:" + e);
		}
		datosCesion.setTipoLineaS(entity.getTipoLineaS());
		return datosCesion;
	}

	/**
	 * Obtener dni cuit cuil.
	 *
	 * @param datosCesion
	 *            the datos cesion
	 * @param dto
	 *            the dto
	 */
	private void obtenerDniCuitCuil(DatosCesionDTO datosCesion, NupDTO dto) {
		if (dto.getNroDocumento() != null) {
			datosCesion.setDni(dto.getNroDocumento());
		} else if (dto.getDetalleDocumento().containsKey(NupDTO.TIPO_DOC_CUIT)) {
			datosCesion.setDni(dto.getDetalleDocumento().get(NupDTO.TIPO_DOC_CUIT).getNroDocumento());
		} else if (dto.getDetalleDocumento().containsKey(NupDTO.TIPO_DOC_CUIL)) {
			datosCesion.setDni(dto.getDetalleDocumento().get(NupDTO.TIPO_DOC_CUIL).getNroDocumento());
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO#obtenerTasasIndicativas(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<TasasIndicativasDTO> obtenerTasasIndicativas(Cliente cliente) {
		if (sesionParametros.getSubproductoPaquete() == null) {
			ConsultaPaquetesOutEntity res = consultarPaquetes(cliente);
			if (res == null || Integer.valueOf(res.getCodigoRetornoExtendido()) != 0) {
				return respuestaFactory.crearRespuestaError(ERROR_TASAS_INDICATIVAS, TipoError.ERROR_TASAS_INDICATIVAS,
						CodigoMensajeConstantes.ERROR_TASAS_INDICATIVAS);
			}
			for (PaqueteEntity paquete : res.getPaquetes()) {
				if ("09".equals(paquete.getTipoCuenta()) && subProductoPaqueteValido(paquete.getSubProductoPaquete())) {
					sesionParametros.setSubproductoPaquete(paquete.getSubProductoPaquete());
					sesionParametros.setCuentaHabilitada(paquete.getTipoCuenta() + "-" + paquete.getNumeroCuenta() + "-"
							+ paquete.getSucursalCuenta());
				}
			}

		}
		if (sesionParametros.getSubproductoPaquete() != null) {
			TasasIndicativasEntity tasas = llenarTasas(sesionParametros.getSubproductoPaquete(), cliente);
			if (tasas == null) {
				return respuestaFactory.crearRespuestaError(ERROR_TASAS_INDICATIVAS, TipoError.ERROR_TASAS_INDICATIVAS,
						CodigoMensajeConstantes.ERROR_TASAS_INDICATIVAS);
			} else {
				return respuestaFactory.crearRespuestaOk(TasasIndicativasDTO.class, obtenerTasasIndicativas(tasas));
			}

		}
		return respuestaFactory.crearRespuestaError(ERROR_TASAS_INDICATIVAS, TipoError.ERROR_TASAS_INDICATIVAS,
				CodigoMensajeConstantes.ERROR_TASAS_INDICATIVAS);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO#eliminarOperacion(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<Void> eliminarOperacion(Cliente cliente, String nroTramite) {
		try {
			EliminarOperacionEntity respuesta = eliminarOperacionDao.eliminarOperacion(cliente, nroTramite);
			if (Integer.valueOf(respuesta.getCodigoRetornoExtendido()) == 0) {
				Respuesta<Void> respuestaConMensaje = respuestaFactory.crearRespuestaOk(Void.class);
				List<ItemMensajeRespuesta> mensaje = new ArrayList<ItemMensajeRespuesta>();
				ItemMensajeRespuesta item = respuestaFactory.generarItemMensajeRespuesta("ok", TipoError.OK,
						CodigoMensajeConstantes.DESCUENTO_CHEQUES_MENSAJE_ELIMINACION_EXITOSA);
				mensaje.add(item);
				respuestaConMensaje.setItemMensajeRespuesta(mensaje);
				return respuestaConMensaje;
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("errorEliminacion", TipoError.DESCUENTO_CHEQUES_ELIMINACION,
					CodigoMensajeConstantes.DESCUENTO_CHEQUES_ELIMINACION);
		}
		return respuestaFactory.crearRespuestaError("errorEliminacion", TipoError.DESCUENTO_CHEQUES_ELIMINACION,
				CodigoMensajeConstantes.DESCUENTO_CHEQUES_ELIMINACION);
	}

	/**
	 * Consultar paquetes.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the consulta paquetes out entity
	 */
	private ConsultaPaquetesOutEntity consultarPaquetes(Cliente cliente) {
		List<Cuenta> cuentasFiltradas = obtenerCuentasUnicas(cliente.getCuentas());
		ConsultaPaquetesInEntity consultaPaquetesInEntity = new ConsultaPaquetesInEntity();
		consultaPaquetesInEntity.setCliente(cliente);
		consultaPaquetesInEntity.setCantidadDeCuentas(
				ISBANStringUtils.formateadorConCerosIzq(String.valueOf(cuentasFiltradas.size()), 3));
		consultaPaquetesInEntity.setCuentasFiltradas(agregarCuentas(cuentasFiltradas));
		return obtenerConsultaPaquetesOutEntity(consultaPaquetesInEntity);
	}

	/**
	 * Obtener consulta paquetes out entity.
	 *
	 * @param consultaPaquetesInEntity
	 *            the consulta paquetes in entity
	 * @return the consulta paquetes out entity
	 */
	private ConsultaPaquetesOutEntity obtenerConsultaPaquetesOutEntity(
			ConsultaPaquetesInEntity consultaPaquetesInEntity) {
		try {
			return consulta.consultar(consultaPaquetesInEntity);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Llenar tasas.
	 *
	 * @param subProducto
	 *            the sub producto
	 * @param cliente
	 *            the cliente
	 * @return the tasas indicativas entity
	 */
	private TasasIndicativasEntity llenarTasas(String subProducto, Cliente cliente) {
		try {
			TasasIndicativasEntity tasas = consultaTasas.obtenerTasasIndicativas(subProducto, cliente);
			if (Integer.valueOf(tasas.getCodigoRetornoExtendido()) == 0) {
				return tasas;
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Obtener tasas indicativas.
	 *
	 * @param tasas
	 *            the tasas
	 * @return the tasas indicativas DTO
	 */
	private TasasIndicativasDTO obtenerTasasIndicativas(TasasIndicativasEntity tasas) {
		TasasIndicativasDTO tasasIndicativas = new TasasIndicativasDTO();
		tasasIndicativas.setLegales(legalBO.obtenerLegalesPorCodigo("1016"));
		for (TasaIndicativa tasa : tasas.getTasaIndicativa()) {
			TasaIndicativaDTO tasaIndicativa = new TasaIndicativaDTO();
			tasaIndicativa.setPlazo(Integer.valueOf(tasa.getPlazo()));
			try {
				if (Integer.valueOf(tasa.getTasa()) == 0) {
					tasaIndicativa.setTasa(new BigDecimal("0.00"));
				} else {
					tasaIndicativa.setTasa(ISBANStringUtils
							.convertirStrToBigDecimal(ISBANStringUtils.sacarCerosYBlancosIzq(tasa.getTasa()), 4));
				}
			} catch (ImporteConvertException e) {
				LOGGER.error("Error al parsear tasa:" + e);
			}
			tasasIndicativas.getLista().add(tasaIndicativa);
		}
		return tasasIndicativas;
	}

	/**
	 * Obtener cuentas unicas.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	private List<Cuenta> obtenerCuentasUnicas(List<Cuenta> cuentas) {
		List<Cuenta> cuentasFiltradas = new ArrayList<Cuenta>();
		LOGGER.info("Obteniendo cuentas unicas infinity");
		for (Cuenta cuenta : cuentas) {
			if (TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().equals(Integer.valueOf(cuenta.getTipoCuentaSinUnificar()))
					&& ("TI".equals(cuenta.getCodigoTitularidad()))) {
				cuentasFiltradas.add(cuenta);
			}
		}
		return cuentasFiltradas;
	}

	/**
	 * Sub producto paquete valido.
	 *
	 * @param subProductoPaquete
	 *            the sub producto paquete
	 * @return true, if successful
	 */
	private boolean subProductoPaqueteValido(String subProductoPaquete) {
		return "0003".equals(subProductoPaquete) || "0351".equals(subProductoPaquete)
				|| "0355".equals(subProductoPaquete) || "0503".equals(subProductoPaquete);
	}

	/**
	 * Agregar cuentas.
	 *
	 * @param cuentasFiltradas
	 *            the cuentas filtradas
	 * @return the list
	 */
	private List<InformacionCuentaPaqueteInEntity> agregarCuentas(List<Cuenta> cuentasFiltradas) {
		List<InformacionCuentaPaqueteInEntity> lista = new ArrayList<InformacionCuentaPaqueteInEntity>();
		for (Cuenta cuenta : cuentasFiltradas) {
			InformacionCuentaPaqueteInEntity informacionCuenta = new InformacionCuentaPaqueteInEntity();
			informacionCuenta.setNumeroCuenta(StringUtils.right(cuenta.getNroCuentaProducto(), 7));
			informacionCuenta.setSucursalCuenta(StringUtils.right(cuenta.getSucursalPaquete(), 3));
			informacionCuenta.setTipoCuenta(cuenta.getTipoCuentaSinUnificar());
			lista.add(informacionCuenta);
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO#obtenerOperacionesPrecargadas(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<OperacionesPrecargadasDTO> obtenerOperacionesPrecargadas(Cliente cliente, String ultimoRegistro) {
		try {
			DescuentoChequesEntity entity = descuentoCheques.obtenerOperaciones(ultimoRegistro, cliente, true, null);
			if (Integer.valueOf(entity.getCodigoRetornoExtendido()) == 0) {
				return respuestaFactory.crearRespuestaOk(OperacionesPrecargadasDTO.class,
						crearOperacionesPrecargadas(entity, true));
			}
			return crearMensajeErrorOperacionesPrecargadas(entity, ultimoRegistro, true);
		} catch (DAOException e) {
			LOGGER.error("Error en el DAO: " + e);
		}
		return crearMensajeErrorOperacionesPrecargadas(null, ultimoRegistro, true);
	}

	/**
	 * Crear mensaje error operaciones precargadas.
	 *
	 * @param entity
	 *            the entity
	 * @param ultimoRegistro
	 *            the ultimo registro
	 * @param isPrecargada
	 *            the is precargada
	 * @return the respuesta
	 */
	private Respuesta<OperacionesPrecargadasDTO> crearMensajeErrorOperacionesPrecargadas(DescuentoChequesEntity entity,
			String ultimoRegistro, Boolean isPrecargada) {
		// Ya hizo un rellamado por lo menos
		if (!ISBANStringUtils.isNullOrBlank(ultimoRegistro)) {
			if (isPrecargada) {
				return respuestaFactory.crearRespuestaError("errorConOperaciones",
						TipoError.ERROR_SERVICIO_OP_PRECARGADAS_CON_OPERACIONES,
						CodigoMensajeConstantes.ERROR_SERVICIO_OP_PRECARGADAS_CON_OPERACIONES);
			} else {
				return respuestaFactory.crearRespuestaError("errorConOperaciones",
						TipoError.ERROR_SERVICIO_HISTORICO_CON_OPERACIONES,
						CodigoMensajeConstantes.ERROR_SERVICIO_OP_PRECARGADAS_CON_OPERACIONES);
			}
		} else if (entity != null && ("10000091".equals(entity.getCodigoRetornoExtendido())
				|| (0 == Integer.valueOf(entity.getCodigoRetornoExtendido())
						&& (!tieneAlgunCasoParaMostrar(entity))))) {
			if (isPrecargada) {
				return respuestaFactory.crearRespuestaError(ERROR_SIN_OPERACIONES,
						TipoError.ERROR_OP_PRECARGADAS_SIN_OPERACIONES,
						CodigoMensajeConstantes.ERROR_OP_PRECARGADAS_SIN_OPERACIONES);
			} else {
				Respuesta<OperacionesPrecargadasDTO> res = respuestaFactory
						.crearRespuestaError(OperacionesPrecargadasDTO.class, "", ERROR_SIN_OPERACIONES);
				res.getItemsMensajeRespuesta().get(0)
						.setTipoError(TipoError.ERROR_SERVICIO_HISTORICO_SIN_OPERACIONES.getDescripcion());
				return res;
			}

		}
		return respuestaFactory.crearRespuestaError(ERROR_GENERICO, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_OP_PRECARGADAS_ERROR_GENERICO);
	}

	/**
	 * Crear operaciones precargadas.
	 *
	 * @param entity
	 *            the entity
	 * @param esPrecargada
	 *            the es precargada
	 * @return the operaciones precargadas DTO
	 */
	private OperacionesPrecargadasDTO crearOperacionesPrecargadas(DescuentoChequesEntity entity, boolean esPrecargada) {
		OperacionesPrecargadasDTO dto = new OperacionesPrecargadasDTO();
		dto.setTieneMasOperaciones("S".equals(entity.getTieneRellamada()));
		int i = 0;
		for (OperacionDescuentoEntity operacion : entity.getOperaciones()) {
			dto.getDto().add(obtenerOperacionPrecargadaDTO(operacion, esPrecargada));
			if (i == entity.getOperaciones().size() - 1) {
				sesionParametros.setValidacionHash(operacion.getNroTramite());
			}
			i++;
		}
		return dto;
	}

	/**
	 * Obtener operacion precargada DTO.
	 *
	 * @param operacion
	 *            the operacion
	 * @param esPrecargada
	 *            the es precargada
	 * @return the operacion precargada DTO
	 */
	private OperacionPrecargadaDTO obtenerOperacionPrecargadaDTO(OperacionDescuentoEntity operacion,
			boolean esPrecargada) {
		OperacionPrecargadaDTO operacionDTO = new OperacionPrecargadaDTO();
		operacionDTO.setFecha(ISBANStringUtils.formatearFecha(operacion.getFechaAlta(), "yyyy-MM-dd"));
		operacionDTO.setNumeroOperacion(operacion.getNroTramite());
		operacionDTO.setCantCheques(Integer.valueOf(operacion.getCantidadCheques()));
		operacionDTO.setChequesRechazados(Integer.valueOf(operacion.getCantidadChequesRechazados()));
		try {
			operacionDTO.setImporteBruto(ISBANStringUtils.convertirStrToBigDecimal(operacion.getImporteBruto(), 2));
			if (!"05".equals(operacion.getEstadoTramite())) {
				operacionDTO.setImporteAcreditado(
						ISBANStringUtils.convertirStrToBigDecimal(operacion.getImporteAAcreditar(), 2));
			}
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al parsear importe bruto o acreditado: " + e);
		}
		if (esPrecargada) {
			operacionDTO.setEstado(operacion.getDescripcionEstado().trim());
		} else {
			operacionDTO.setColor(obtenerColor(operacion.getEstadoTramite()));
			operacionDTO.setEstado(obtenerEstado(operacion.getEstadoTramite()));
		}
		return operacionDTO;
	}

	/**
	 * Obtener estado.
	 *
	 * @param estadoTramite
	 *            the estado tramite
	 * @return the string
	 */
	private String obtenerEstado(String estadoTramite) {
		String res = null;
		if ("04".equals(estadoTramite)) {
			res = "Vencida";
		} else if ("05".equals(estadoTramite)) {
			res = "Rechazada";
		} else if ("15".equals(estadoTramite)) {
			res = "Acreditada";
		} else if ("17".equals(estadoTramite)) {
			res = "Anulado";
		} else if ("18".equals(estadoTramite)) {
			res = "Pendiente";
		}
		return res;
	}

	/**
	 * Obtener color.
	 *
	 * @param estadoTramite
	 *            the estado tramite
	 * @return the string
	 */
	private String obtenerColor(String estadoTramite) {
		String res = null;
		if ("04".equals(estadoTramite) || "05".equals(estadoTramite) || "17".equals(estadoTramite)) {
			res = "ROJO";
		} else if ("18".equals(estadoTramite)) {
			res = "AMARILLO";
		} else if ("15".equals(estadoTramite)) {
			res = "VERDE";
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO#obtenerDetalleOperacionesPrecargadas(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public Respuesta<DetalleOperacionesPrecargadasDTO> obtenerDetalleOperacionesPrecargadas(Cliente cliente,
			String nroTramite, String numeroCuenta, Boolean esSimulacion) {
		try {
			DetalleOperacionesPrecargadasEntity entity = detallePrecargadaDao
					.obtenerDetalleOperacionesPrecargadas(cliente, nroTramite);
			if (Integer.valueOf(entity.getCodigoRetornoExtendido()) == 0) {
				sesionParametros.setFalloLegales(true);
				if (esSimulacion && Integer.valueOf(entity.getCantChqRech()) > 0) {
					Respuesta<DetalleOperacionesPrecargadasDTO> res = respuestaFactory.crearRespuestaWarning(
							obtenerDetallePrecargadas(entity, nroTramite, numeroCuenta, esSimulacion),
							"algunosRechazados", TipoError.SIMULACION_TASAS_CONFIRMACION_RECHAZADAS,
							CodigoMensajeConstantes.SIMULACION_ALGUNOS_CHEQUES_RECHAZADOS);
					int cantRechazados = Integer.valueOf(entity.getCantChqRech());
					res.getItemsMensajeRespuesta().get(0).setMensaje(StringUtils.replace(
							res.getItemsMensajeRespuesta().get(0).getMensaje(), "{0}", String.valueOf(cantRechazados)));
					return res;
				}
				return respuestaFactory.crearRespuestaOk(DetalleOperacionesPrecargadasDTO.class,
						obtenerDetallePrecargadas(entity, nroTramite, numeroCuenta, esSimulacion));

			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			if(sesionParametros.getFalloLegales()){
				sesionParametros.setFalloLegales(false);
				return respuestaFactory.crearRespuestaError("errorLegales", TipoError.ERROR_LEGALES,
						CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS);
			}
		}
		if (esSimulacion) {
			return respuestaFactory.crearRespuestaError(ERROR_GENERICO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_SIMULACION_TASAS);
		} else {
			return respuestaFactory.crearRespuestaError(ERROR_GENERICO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS);
		}
	}

	/**
	 * Obtener detalle precargadas.
	 *
	 * @param entity
	 *            the entity
	 * @param nroTramite
	 *            the nro tramite
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the detalle operaciones precargadas DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private DetalleOperacionesPrecargadasDTO obtenerDetallePrecargadas(DetalleOperacionesPrecargadasEntity entity,
			String nroTramite, String numeroCuenta, Boolean esSimulacion) throws DAOException {
		DetalleOperacionesPrecargadasDTO detalle = obtenerDetalle(entity, nroTramite, numeroCuenta, esSimulacion);
		if (!esSimulacion) {
			detalle.setMensajeInformativo(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.DETALLE_CHEQUES_PRECARGADO_MSJ_INFORMATIVO)
					.getMensaje());
		}
		return detalle;
	}

	/**
	 * Obtener detalle.
	 *
	 * @param entity
	 *            the entity
	 * @param nroTramite
	 *            the nro tramite
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the detalle historial operaciones DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private DetalleHistorialOperacionesDTO obtenerDetalle(DetalleOperacionesPrecargadasEntity entity, String nroTramite,
			String numeroCuenta, Boolean esSimulacion) throws DAOException {
		DetalleHistorialOperacionesDTO detalle = new DetalleHistorialOperacionesDTO();
		detalle.setNumeroOperacion(nroTramite);
		detalle.setCuenta(new IdentificacionCuenta(numeroCuenta));
		detalle.setTotalChequesDescontados(Integer.valueOf(entity.getCantChqAcep()));
		parsearCheques(detalle, entity, esSimulacion);
		detalle.setTotalChequesRechazados(Integer.valueOf(entity.getCantChqRech()));
		detalle.setTasaNominalAnual(parsearPorcentaje(entity.getTasaAplicada()));
		detalle.setTasaEfectivaAnual(parsearPorcentaje(entity.getTasaTea()));
		detalle.setCostoFinancieroTotal(parsearPorcentaje(entity.getTasaCFT()));
		detalle.setImporteAcreditar(obtenerImporteOCero(entity.getImpNetoChqAcep()));
		detalle.setTotal(obtenerImporteOCero(entity.getSumImpChqAcep()));
		detalle.setImpuesto(obtenerImporteOCero(entity.getSumImpuChqAcep()));
		detalle.setIntereses(obtenerImporteOCero(entity.getSumIntChqAcep()));
		detalle.setaAcreditar(obtenerImporteOCero(entity.getImpNetoChqAcep()));
		detalle.setImporte(obtenerImporteOCero(entity.getImpTotalRech()));
		detalle.setComAdminCheques(obtenerImporteOCero(entity.getImpComisionAdic()));
		if (esSimulacion) {
			detalle.setLegalPie1(legalBO.obtenerLegal("1026"));
			detalle.setLegalPie2(legalBO.obtenerLegal("1027"));
			detalle.setLegalPie3(legalBO.obtenerLegal("1028"));
			detalle.setFechaAlta(entity.getFechaDeAltaDelTramite());
		} else {
			detalle.setLegalPie1(legalBO.obtenerLegal("1017"));
			detalle.setLegalPie2(legalBO.obtenerLegal("1018"));
			detalle.setLegalPie3(legalBO.obtenerLegal("1019"));
		}
		sesionParametros.setFalloLegales(false);
		return detalle;
	}

	/**
	 * Parsear porcentaje.
	 *
	 * @param valor
	 *            the valor
	 * @return the string
	 */
	private String parsearPorcentaje(String valor) {
		return ISBANStringUtils.formatearSaldo(ISBANStringUtils.convertirStrToBigDecimalSinException(valor, 4)) + " %";
	}

	/**
	 * Parsear cheques.
	 *
	 * @param detalle
	 *            the detalle
	 * @param entity
	 *            the entity
	 * @param esSimulacion
	 *            the es simulacion
	 */
	private void parsearCheques(DetalleOperacionesPrecargadasDTO detalle, DetalleOperacionesPrecargadasEntity entity,
			Boolean esSimulacion) {
		for (DetalleOperacionPrecargadaEntity operacion : entity.getOperaciones()) {
			if ("1".equals(operacion.getEstChq())) {
				detalle.getChequesAceptados().add(obtenerChequeAceptado(operacion, esSimulacion));
			} else if ("2".equals(operacion.getEstChq())) {
				detalle.getChequesRechazados().add(obtenerChequeRechazado(operacion, esSimulacion));
			}

		}
	}

	/**
	 * Obtener cheque rechazado.
	 *
	 * @param operacion
	 *            the operacion
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the cheques rechazados DTO
	 */
	private ChequesRechazadosDTO obtenerChequeRechazado(DetalleOperacionPrecargadaEntity operacion,
			Boolean esSimulacion) {
		ChequesRechazadosDTO cheque = new ChequesRechazadosDTO();
		cheque.setNumeroCheque(obtenerNumeroCheque(operacion));
		cheque.setFirmante(obtenerTipoDoc(operacion.getTpoDocLibr1(), esSimulacion)
				+ obtenerFirmante(operacion.getTpoDocLibr1(), operacion.getNroDocLibr1(), esSimulacion));
		cheque.setFechaDePago(ISBANStringUtils.formatearFecha(operacion.getFecVtoChq(), "yyyy-MM-dd"));
		cheque.setImporte(obtenerImporteOCero(operacion.getImpCheque()));
		if(esSimulacion) {
			cheque.setDni(operacion.getNroDocLibr2());
			cheque.setTipo(operacion.getTpoDocLibr1());
		}
		return cheque;
	}

	/**
	 * Obtener tipo doc.
	 *
	 * @param tpoDocLibr1
	 *            the tpo doc libr 1
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the string
	 */
	private String obtenerTipoDoc(String tpoDocLibr1, Boolean esSimulacion) {
		if (esSimulacion) {
			if ("C".equals(tpoDocLibr1)) {
				return "CDI ";
			} else if ("T".equals(tpoDocLibr1)) {
				return "CUIT ";
			} else if ("L".equals(tpoDocLibr1)) {
				return "CUIL ";
			} 
		}
		return "";
	}

	/**
	 * Obtener firmante.
	 *
	 * @param tpoDocLibr1
	 *            the tpo doc libr 1
	 * @param nroDocLibr1
	 *            the nro doc libr 1
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the string
	 */
	private String obtenerFirmante(String tpoDocLibr1, String nroDocLibr1, Boolean esSimulacion) {
		String sinCeros = ISBANStringUtils.sacarCerosYBlancosIzq(nroDocLibr1);
		if ("N".equals(tpoDocLibr1) && !esSimulacion) {
			return sinCeros.substring(0, 2) + "." + sinCeros.substring(2, sinCeros.length() - 3) + "."
					+ sinCeros.substring(sinCeros.length() - 3);
		}
		return sinCeros.substring(0, 2) + "-" + sinCeros.substring(2, sinCeros.length() - 1) + "-"
				+ sinCeros.substring(sinCeros.length() - 1);
	}

	/**
	 * Obtener cheque aceptado.
	 *
	 * @param operacion
	 *            the operacion
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the cheques aceptados DTO
	 */
	private ChequesAceptadosDTO obtenerChequeAceptado(DetalleOperacionPrecargadaEntity operacion, Boolean esSimulacion) {
		ChequesAceptadosDTO cheque = new ChequesAceptadosDTO();
		cheque.setNumeroCheque(obtenerNumeroCheque(operacion));
		cheque.setBanco(obtenerBanco(operacion.getBcoGirado()));
		String diasAAdelantar = ISBANStringUtils.sacarCerosYBlancosIzq(operacion.getDiasAdelantar());
		if(diasAAdelantar.isEmpty()) {
			cheque.setDiasAAdelantar("0");
		}else {
			cheque.setDiasAAdelantar(ISBANStringUtils.sacarCerosYBlancosIzq(operacion.getDiasAdelantar()));
		}
		
		cheque.setImporteTotal(obtenerImporteOCero(operacion.getImpCheque()));
		cheque.setImporteImpuestos(obtenerImporteOCero(operacion.getImpImpuesto()));
		cheque.setImporteIntereses(obtenerImporteOCero(operacion.getImpInteres()));
		cheque.setImporteAAcreditar(obtenerImporteOCero(operacion.getImpNeto()));
		if(esSimulacion) {
			cheque.setDni(operacion.getNroDocLibr1());
			cheque.setTipo(operacion.getTpoDocLibr1());
			cheque.setFechaPago(operacion.getFecVtoChq());
		}
		return cheque;
	}

	/**
	 * Obtener importe O cero.
	 *
	 * @param impCheque
	 *            the imp cheque
	 * @return the big decimal
	 */
	private BigDecimal obtenerImporteOCero(String impCheque) {
		if (Integer.valueOf(impCheque) == 0) {
			try {
				return ISBANStringUtils.convertirStrToBigDecimal("0000", 2);
			} catch (ImporteConvertException e) {
				LOGGER.error("Error al parsear importe:" + e);
			}
		}
		try {
			String numero = ISBANStringUtils.sacarCerosYBlancosIzq(impCheque);
			// Para que funcione bien el convertirStrToBigDecimal
			if (numero.length() < 2) {
				numero = "0" + numero;
			}
			return ISBANStringUtils.convertirStrToBigDecimal(numero, 2);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al parsear importe:" + e);
			return null;
		}
	}

	/**
	 * Obtener banco.
	 *
	 * @param bcoGirado
	 *            the bco girado
	 * @return the string
	 */
	private String obtenerBanco(String bcoGirado) {
		int codigoBancario = codigosDAO.obtenerIndiceCodigoBancario(bcoGirado);
		if (codigoBancario == -1) {
			return "";
		}
		return entidadesDAO.obtenerIndiceCodigoBancario(codigoBancario);
	}

	/**
	 * Obtener numero cheque.
	 *
	 * @param operacion
	 *            the operacion
	 * @return the string
	 */
	private String obtenerNumeroCheque(DetalleOperacionPrecargadaEntity operacion) {
		String res = operacion.getBcoGirado();
		res = res + operacion.getSucGirada();
		res = res + operacion.getCodPostal();
		res = res + operacion.getDigVerif1();
		res = res + operacion.getNroCheque();
		res = res + operacion.getDigVerif2();
		res = res + operacion.getCtaGirada();
		res = res + operacion.getDigVerif3();
		return res;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO#obtenerHistorialOperaciones(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<OperacionesPrecargadasDTO> obtenerHistorialOperaciones(Cliente cliente, String ultimoRegistro,
			String filtro) {
		DescuentoChequesEntity entity = null;
		try {
			if (filtro != null) {
				entity = descuentoCheques.obtenerOperaciones(ultimoRegistro, cliente, false, obtenerFiltro(filtro));
			} else {
				Respuesta<DescuentoChequesEntity> entityValidos = obtenerDescuentoChequesEntityValidos(cliente,
						ultimoRegistro, filtro);
				if (EstadoRespuesta.WARNING.equals(entityValidos.getEstadoRespuesta())) {
					return respuestaFactory.crearRespuestaWarning(
							crearOperacionesPrecargadas(entityValidos.getRespuesta(), false), "errorConOperaciones",
							TipoError.ERROR_SERVICIO_HISTORICO_CON_OPERACIONES,
							CodigoMensajeConstantes.ERROR_SERVICIO_OP_PRECARGADAS_CON_OPERACIONES);
				}
				entity = entityValidos.getRespuesta();
			}
			if (Integer.valueOf(entity.getCodigoRetornoExtendido()) == 0 && tieneAlgunCasoParaMostrar(entity)) {
				entity.setOperaciones(filtrarOperaciones(entity.getOperaciones()));
				return respuestaFactory.crearRespuestaOk(OperacionesPrecargadasDTO.class,
						crearOperacionesPrecargadas(entity, false));
			}
			if (filtro == null) {
				return crearMensajeErrorOperacionesPrecargadas(entity, ultimoRegistro, false);
			}
			return crearMensajeErrorHistorialConFiltro(entity);
		} catch (DAOException e) {
			LOGGER.error("Error en el DAO: " + e);
		}
		return crearMensajeErrorHistorialConFiltro(null);
	}

	/**
	 * Obtener descuento cheques entity validos.
	 *
	 * @param cliente
	 *            the cliente
	 * @param ultimoRegistro
	 *            the ultimo registro
	 * @param filtro
	 *            the filtro
	 * @return the respuesta
	 */
	private Respuesta<DescuentoChequesEntity> obtenerDescuentoChequesEntityValidos(Cliente cliente,
			String ultimoRegistro, String filtro) {
		boolean termino = false;
		String ultimoRegistroActual = ultimoRegistro;
		DescuentoChequesEntity res = new DescuentoChequesEntity();
		res.setCodigoRetornoExtendido("00000000");
		res.setTieneRellamada("S");
		while (!termino) {
			DescuentoChequesEntity entity = obtenerEntity(cliente, ultimoRegistroActual, obtenerFiltro(filtro));
			if (entity != null && 0 == Integer.valueOf(entity.getCodigoRetornoExtendido())) {
				ultimoRegistroActual = entity.getOperaciones().get(entity.getOperaciones().size() - 1).getNroTramite();
				res.getOperaciones().addAll(filtrarOperaciones(entity.getOperaciones()));
			} else if ((entity != null && 0 != Integer.valueOf(entity.getCodigoRetornoExtendido())) || entity == null) {
				return armarRespuestaErrorOWarning(res, entity);
			}
			if (!"S".equals(entity.getTieneRellamada()) || res.getOperaciones().size() >= 50) {
				termino = true;
			}
			if (res.getOperaciones().size() <= 50 && !"S".equals(entity.getTieneRellamada())) {
				res.setTieneRellamada("N");
			}
		}
		res.setUltimoRegistro(ultimoRegistroActual);
		if (res.getOperaciones().size() > 50) {
			res.setOperaciones(res.getOperaciones().subList(0, 50));
		}
		return respuestaFactory.crearRespuestaOk(DescuentoChequesEntity.class, res);
	}

	/**
	 * Armar respuesta error O warning.
	 *
	 * @param res
	 *            the res
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	private Respuesta<DescuentoChequesEntity> armarRespuestaErrorOWarning(DescuentoChequesEntity res,
			DescuentoChequesEntity entity) {
		if (res.getOperaciones().size() > 0) {
			res.setTieneRellamada("N");
			return respuestaFactory.crearRespuestaWarning(res, new ArrayList<ItemMensajeRespuesta>());
		} else if (entity == null) {
			DescuentoChequesEntity entityToTrigger = new DescuentoChequesEntity();
			entityToTrigger.setCodigoRetornoExtendido("1000123491");
			return respuestaFactory.crearRespuestaError(DescuentoChequesEntity.class, entityToTrigger,
					new ArrayList<DatoItemMensaje>());
		}
		return respuestaFactory.crearRespuestaError(DescuentoChequesEntity.class, entity,
				new ArrayList<DatoItemMensaje>());
	}

	/**
	 * Obtener entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param ultimoRegistro
	 *            the ultimo registro
	 * @param filtro
	 *            the filtro
	 * @return the descuento cheques entity
	 */
	private DescuentoChequesEntity obtenerEntity(Cliente cliente, String ultimoRegistro, String filtro) {
		try {
			return descuentoCheques.obtenerOperaciones(ultimoRegistro, cliente, false, filtro);
		} catch (DAOException e) {
			LOGGER.error("DAO Exception:" + e);
		}
		return null;
	}

	/**
	 * Filtrar operaciones.
	 *
	 * @param operaciones
	 *            the operaciones
	 * @return the list
	 */
	private List<OperacionDescuentoEntity> filtrarOperaciones(List<OperacionDescuentoEntity> operaciones) {
		List<OperacionDescuentoEntity> result = new ArrayList<OperacionDescuentoEntity>();
		for (OperacionDescuentoEntity entity : operaciones) {
			if (esTipoChequeEspecificado(entity.getEstadoTramite())) {
				result.add(entity);
			}
		}
		return result;
	}

	/**
	 * Tiene algun caso para mostrar.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
	private boolean tieneAlgunCasoParaMostrar(DescuentoChequesEntity entity) {
		boolean res = false;
		if (entity != null && (Integer.valueOf(entity.getCodigoRetornoExtendido()) == 0)) {
			res = tieneChequeValido(entity);
		}
		return res;
	}

	/**
	 * Tiene cheque valido.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
	private boolean tieneChequeValido(DescuentoChequesEntity entity) {
		for (OperacionDescuentoEntity operacion : entity.getOperaciones()) {
			if (esTipoChequeEspecificado(operacion.getEstadoTramite())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Es tipo cheque especificado.
	 *
	 * @param estadoTramite
	 *            the estado tramite
	 * @return true, if successful
	 */
	private boolean esTipoChequeEspecificado(String estadoTramite) {
		boolean res = "04".equals(estadoTramite);
		res = res || "05".equals(estadoTramite);
		res = res || "15".equals(estadoTramite);
		res = res || "17".equals(estadoTramite);
		res = res || "18".equals(estadoTramite);
		return res;
	}

	/**
	 * Crear mensaje error historial con filtro.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	private Respuesta<OperacionesPrecargadasDTO> crearMensajeErrorHistorialConFiltro(DescuentoChequesEntity entity) {
		// el caso en el que es 0 es que no devolvio filtrados ninguno de los
		// tipos pedidos
		if (entity != null && ("10000091".equals(entity.getCodigoRetornoExtendido())
				|| (Integer.valueOf(entity.getCodigoRetornoExtendido()) == 0))) {
			return respuestaFactory.crearRespuestaError(ERROR_SIN_OPERACIONES,
					TipoError.ERROR_HISTORIAL_FILTRO_SIN_OPERACIONES,
					CodigoMensajeConstantes.ERROR_HISTORIAL_FILTRO_SIN_OPERACIONES);
		}
		return respuestaFactory.crearRespuestaError(ERROR_GENERICO, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_OP_PRECARGADAS_ERROR_GENERICO);
	}

	/**
	 * Obtener filtro.
	 *
	 * @param filtro
	 *            the filtro
	 * @return the string
	 */
	private String obtenerFiltro(String filtro) {
		String res = "  ";
		if ("Pendientes".equals(filtro)) {
			res = "18";
		} else if ("Acreditadas".equals(filtro)) {
			res = "15";
		} else if ("Rechazadas".equals(filtro)) {
			res = "05";
		} else if ("Anuladas".equals(filtro)) {
			res = "17";
		} else if ("Vencidas".equals(filtro)) {
			res = "04";
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO#obtenerDetalleHistorialOperaciones(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<DetalleHistorialOperacionesDTO> obtenerDetalleHistorialOperaciones(Cliente cliente,
			String nroTramite, String numeroCuenta) {
		try {
			DetalleOperacionesPrecargadasEntity entity = detallePrecargadaDao
					.obtenerDetalleOperacionesPrecargadas(cliente, nroTramite);
			if (Integer.valueOf(entity.getCodigoRetornoExtendido()) == 0) {
				sesionParametros.setFalloLegales(true);
				return respuestaFactory.crearRespuestaOk(DetalleHistorialOperacionesDTO.class,
						obtenerDetalleHistorial(entity, nroTramite, numeroCuenta));
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			if(sesionParametros.getFalloLegales()){
				sesionParametros.setFalloLegales(false);
				return respuestaFactory.crearRespuestaError("errorLegales", TipoError.ERROR_LEGALES,
						CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS);
			}
		}
		return respuestaFactory.crearRespuestaError(ERROR_GENERICO, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS);
	}

	/**
	 * Obtener detalle historial.
	 *
	 * @param entity
	 *            the entity
	 * @param nroTramite
	 *            the nro tramite
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the detalle historial operaciones DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private DetalleHistorialOperacionesDTO obtenerDetalleHistorial(DetalleOperacionesPrecargadasEntity entity,
			String nroTramite, String numeroCuenta) throws DAOException {
		DetalleHistorialOperacionesDTO detalle = obtenerDetalle(entity, nroTramite, numeroCuenta, false);
		detalle.setEstado(entity.getDescEstTramite().trim());
		detalle.setDescEstado(obtenerDescEstado(entity.getEstadoTramite()));
		return detalle;
	}

	/**
	 * Obtener desc estado.
	 *
	 * @param estadoTramite
	 *            the estado tramite
	 * @return the string
	 */
	private String obtenerDescEstado(String estadoTramite) {
		String res = "";
		if ("04".equals(estadoTramite)) {
			res = "Ha trascurrido el plazo máximo de 5 días para presentar los cheques en la sucursal.";
		} else if ("05".equals(estadoTramite)) {
			res = "No pudimos cursar la operación de descuento de cheques, por favor comunícate con tu ejecutivo.";
		} else if ("18".equals(estadoTramite)) {
			res = "La operación se encuentra en proceso de revisión.";
		} else if ("15".equals(estadoTramite)) {
			res = "El dinero ya se acreditó en tu cuenta.";
		} else if ("17".equals(estadoTramite)) {
			res = "La operación fue eliminada.";
		}
		return res;
	}

	/**
	 * Obtener mensajes.
	 *
	 * @return the mensajes descuento entity
	 */
	private MensajesDescuentoEntity obtenerMensajes() {
		MensajesDescuentoEntity mensajes = new MensajesDescuentoEntity();
		mensajes.setAyudaCargaValidacion(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_AYUDA_CARGA).getMensaje());
		mensajes.setPrimerMensajeAyuda(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_MENSAJE_AYUDA_1).getMensaje());
		mensajes.setSegundoMensajeAyuda(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_MENSAJE_AYUDA_2).getMensaje());
		mensajes.setErrorChequeCargado(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_ERROR_CHEQUE_CARGADO).getMensaje());
		mensajes.setErrorDigitoVerificador(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_ERROR_DIGITO_VERIFICADOR)
						.getMensaje());
		mensajes.setErrorSuperaLimite(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_ERROR_SUPERA_LIMITE).getMensaje());
		mensajes.setMensajeCabeceraAyuda(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_CABECERA_AYUDA).getMensaje());
		mensajes.setErrorNoCalifica(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_CLIENTE_NO_CALIFICA).getMensaje());
		mensajes.setErrorNoBYP(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS).getMensaje());
		mensajes.setAvisoLimiteDiario(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_AVISO_LIMITE_DIARIO).getMensaje());
		return mensajes;
	}
}
