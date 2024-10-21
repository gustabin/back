/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CasuisticaErrorTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ConsumoPendienteConfirmacionBo;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ConsumoPendienteConfirmacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ErrorTarjetasEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSConsumosPendientes;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TipoConsumoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Class ConsumoPendienteConfirmacionBoImpl.
 */
@Component
public class ConsumoPendienteConfirmacionBoImpl extends TarjetasBOImpl implements ConsumoPendienteConfirmacionBo {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumoPendienteConfirmacionBoImpl.class);

	/** The Constant ERROR_ULTIMOS_CONSUMOS. */
	private static final String LOGGER_ERROR = "Error en calcular los consumos pendientes";

	/** The Constant ULTIMAS_CINCO_POSICIONES. */
	private static final Integer ULTIMAS_CINCO_POSICIONES = 5;

	/** The Constant PESOS. */
	private static final String PESOS = "pesos";

	/** The Constant ESPACIO_BLANCO. */
	private static final String ESPACIO_BLANCO = " ";

	/** The Constant BLANCO. */
	private static final String BLANCO = "";
    /** The Constant CODIGO_ERROR_SIN_CONSUMOS. */
    private static final String CODIGO_ERROR_SIN_CONSUMOS = "112107";

	/** The parseador. */
	@Autowired
	private ParseadorWSConsumosPendientes parseador;

	/** The casuistica. */
	@Autowired
	private CasuisticaErrorTarjetasBO casuistica = new CasuisticaErrorTarjetasBOImpl();

	/**
	 * Obtener consumo pendiente.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<UltimosConsumosDTO> obtenerConsumoPendiente(Cuenta cuenta) {
		try {
			RetornoTarjetasEntity entity = this.obtenerRespuestaVisa(cuenta, OperacionTarjetaWSEnum.AUTORIZACIONES);
			return armarRespuestaDependiendoDeEntity(entity, cuenta);
		} catch (DAOException e) {
			LOGGER.error("Error al llamar al DAO, armando respuesta error: {}", e);
			return crearRespuestaError();
		}
	}

	/**
	 * Armar respuesta dependiendo de entity.
	 *
	 * @param entity
	 *            the entity
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
    private Respuesta<UltimosConsumosDTO> armarRespuestaDependiendoDeEntity(RetornoTarjetasEntity entity,
            Cuenta cuenta) {
        try {
            if (!esRespuestaConError(entity)) {
                if (esTarjetaSinConsumos(entity, cuenta)) {
                    return armarRespuestaSinConsumos();
                }
                return crearRespuesta(entity, cuenta);
            }
        } catch (NullPointerException e) {
            LOGGER.error(LOGGER_ERROR, e);
        } catch (ParseadorVisaException e) {
            LOGGER.error(LOGGER_ERROR, e);
        }
        return crearRespuestaError();
    }

    /**
	 * Es tarjeta sin consumos.
	 *
	 * @param entity
	 *            the entity
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private Boolean esTarjetaSinConsumos(RetornoTarjetasEntity entity,
            Cuenta cuenta) throws ParseadorVisaException {
        for (TarjetaEntity tarjeta : entity.getTarjetas().getTarjetaList()) {
            if (hayDatosTarjeta(tarjeta, cuenta.getNroTarjetaCredito())) {
                return false;
            }
        }
        for (TarjetaEntity tarjeta : entity.getTarjetas().getTarjetaList()) {
            if (errorDistintoASinConsumos(tarjeta)) {
                throw new ParseadorVisaException();
            }
        }
        return true;
    }

    /**
	 * Hay datos tarjeta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 * @return the boolean
	 */
    private Boolean hayDatosTarjeta(TarjetaEntity tarjeta,
            String nroCuentaProducto) {
        return nroCuentaProducto != null && tarjeta != null
                && tarjeta.getTarjetaDocument() != null
                && tarjeta.getTarjetaDocument().getDatos() != null
                && tarjeta.getTarjetaDocument().getDatos().getTarjetaActiva() != null
                && TarjetaUtils.sacarCerosDeAdelante(tarjeta.getTarjetaDocument().getDatos().getTarjetaActiva())
                .equals(TarjetaUtils.sacarCerosDeAdelante(nroCuentaProducto));
    }

    /**
	 * Error distinto A sin consumos.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private Boolean errorDistintoASinConsumos(TarjetaEntity tarjeta) throws ParseadorVisaException {
        String codigoErrorTarjeta = obtenerCodigoError(tarjeta);
        if (codigoErrorTarjeta == null
                || CODIGO_ERROR_SIN_CONSUMOS.equals(codigoErrorTarjeta)) {
            return false;
        } 
        return true;
    }
    
    /**
	 * Obtener codigo error.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    public String obtenerCodigoError(TarjetaEntity tarjeta)
            throws ParseadorVisaException {
        if (tarjeta == null) {
            throw new ParseadorVisaException();
        }
        if (tarjeta.getError() != null
                && tarjeta.getError().getCodigo() == null) {
            throw new ParseadorVisaException();
        }
        if (tarjeta.getError() != null) {
            return tarjeta.getError().getCodigo();
        }
        return null;
    }
    /**
	 * Es respuesta con error.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
	private boolean esRespuestaConError(RetornoTarjetasEntity entity) {
		try {
			return parseador.tieneErrorDeCredenciales(entity) || parseador.tienenTodasTarjetasErrorConsumos(entity);
		} catch (ParseadorVisaException e) {
			LOGGER.error(LOGGER_ERROR, e);
			return true;
		}
	}

	/**
	 * Obtiene los consumos pendientes de autorizacion de la cuenta.
	 *
	 * @param entity
	 *            the respuesta consumos pendientes
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private Respuesta<UltimosConsumosDTO> crearRespuesta(RetornoTarjetasEntity entity, Cuenta cuenta)
			throws ParseadorVisaException {
		String nroTarjetaCreditoCortado = this.cortarNroTarjetaDesdeCuenta(cuenta);
		TarjetaEntity tarjetaEntity = parseador.obtenerPorNumeroDeTarjetaActiva(entity, nroTarjetaCreditoCortado);
		if (tarjetaEntity != null) {
			return armarRespuestaOk(
					completarConsumosPendientesParaTarjeta(entity, nroTarjetaCreditoCortado, tarjetaEntity, cuenta));
		}
		if (parseador.tienenErrorConsumos(entity)) {
			return crearRespuestaError();
		}
		return armarRespuestaSinConsumos();
	}

	/**
	 * Obtiene las autorizaciones para una tarjeta titular y sus adicionales.
	 *
	 * @param entity
	 *            the respuesta consumos pendientes
	 * @param nroTarjetaCreditoCortado
	 *            the nro tarjeta credito cortado
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @param cuenta
	 *            the cuenta
	 * @return the consumos pendientes DTO
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private UltimosConsumosDTO completarConsumosPendientesParaTarjeta(RetornoTarjetasEntity entity,
			String nroTarjetaCreditoCortado, TarjetaEntity tarjetaEntity, Cuenta cuenta) throws ParseadorVisaException {

		List<ConsumoTarjetaDTO> consumosPendientes = new ArrayList<ConsumoTarjetaDTO>();
		List<ConsumoPendienteConfirmacionEntity> consumosTarjetas = parseador
				.obtenerAutorizacionesDeTarjetaEntity(tarjetaEntity);
		for (ConsumoPendienteConfirmacionEntity consumosTarjeta : consumosTarjetas) {
			if (!consumosTarjeta.getCodigoTarjeta().equals(BLANCO)) {
				ConsumoTarjetaDTO consumoPendiente = obtenerConsumosPendienteTarjeta(consumosTarjeta,
						nroTarjetaCreditoCortado, entity, cuenta);
				if (consumoPendiente != null) {
					consumosPendientes.add(consumoPendiente);
				}
			}
		}
		return this.generarUltimosConsumosDTO(consumosPendientes, cuenta);
	}

	/**
	 * Generar ultimos consumos DTO.
	 *
	 * @param ultimosConsumos
	 *            the ultimos consumos
	 * @param cuenta
	 *            the cuenta
	 * @return the ultimos consumos DTO
	 */
	private UltimosConsumosDTO generarUltimosConsumosDTO(List<ConsumoTarjetaDTO> ultimosConsumos, Cuenta cuenta) {
		UltimosConsumosDTO dto = new UltimosConsumosDTO();
		dto.setUltimosConsumos(ultimosConsumos);
		dto.setMuestraTarjetasConCabecera(muestraCabecera(ultimosConsumos, cuenta));
		return dto;

	}

	/**
	 * Obtener consumos pendiente tarjeta.
	 *
	 * @param consumoPendiente
	 *            the consumo pendiente
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param entity
	 *            the respuesta consumos pendientes
	 * @param cuenta
	 *            the cuenta
	 * @return the consumo pendiente
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private ConsumoTarjetaDTO obtenerConsumosPendienteTarjeta(ConsumoPendienteConfirmacionEntity consumoPendiente,
			String nroTarjeta, RetornoTarjetasEntity entity, Cuenta cuenta) throws ParseadorVisaException {
		ConsumoTarjetaDTO consumoPendienteDTO = new ConsumoTarjetaDTO();
		consumoPendienteDTO.setConsumoPesosPendientes(
				ISBANStringUtils.formatearSaldoString(parseador.obtenerConsumoTotalEnPesos(consumoPendiente)));
		consumoPendienteDTO.setConsumoDolaresPendientes(
				ISBANStringUtils.formatearSaldoString(parseador.obtenerConsumoTotalEnDolares(consumoPendiente)));
		consumoPendienteDTO.setHasConsumoPesosCero(parseador.esConsumoTotalEnPesosCero(consumoPendiente));
		consumoPendienteDTO.setHasConsumoDolaresCero(parseador.esConsumoTotalEnDolaresCero(consumoPendiente));
		String marca = this.obtenerMarcaDeTarjeta(cuenta);
		consumoPendienteDTO.setMarca(marca);
		String numeroTarjetaActiva = parseador
				.recuperarNumeroTarjetaDesdeCodigoTarjeta(consumoPendiente.getCodigoTarjeta(), entity);
		consumoPendienteDTO.setNumero(TarjetaBOUtils.formatearNumeroTarjeta(numeroTarjetaActiva, marca));
		consumoPendienteDTO.setLineas(obtenerLineas(consumoPendiente, consumoPendienteDTO.getNumero(), cuenta));
		if (this.esTitularCuenta(cuenta)) {
			consumoPendienteDTO.setIsTitular(Boolean.TRUE);
			consumoPendienteDTO.setIsAdicional(Boolean.FALSE);
			consumoPendienteDTO.setPrioridad(1);
		} else {
			consumoPendienteDTO.setIsTitular(Boolean.FALSE);
			consumoPendienteDTO.setIsAdicional(Boolean.TRUE);
			consumoPendienteDTO.setPrioridad(2);
			consumoPendienteDTO.setNombreAdicional(obtenerNombreAdicional(nroTarjeta, entity));
		}
		return consumoPendienteDTO;
	}

	/**
	 * Obtener nombre adicional.
	 *
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private String obtenerNombreAdicional(String numeroTarjeta, RetornoTarjetasEntity entity)
			throws ParseadorVisaException {
		TarjetaEntity tarjetaEntity = parseador.obtenerPorNumeroDeTarjetaActiva(entity, numeroTarjeta);
		if (numeroTarjeta.equals(parseador.obtenerNumeroTarjetaActiva(parseador.obtenerDatos(tarjetaEntity)))) {
			return this.obtenerNombreFormateado(parseador.obtenerNombreHabiente(tarjetaEntity));
		} else {
			return "";
		}
	}

	/**
	 * Obtener nombre formateado.
	 *
	 * @param nombreApellido
	 *            the nombre apellido
	 * @return the string
	 */
	private String obtenerNombreFormateado(String nombreApellido) {
		String nombre = StringUtils.substringAfter(nombreApellido, "/");
		String apellido = StringUtils.substringBefore(nombreApellido, "/");
		String nombreCompleto = nombre + ESPACIO_BLANCO + apellido;
		return WordUtils.capitalizeFully(nombreCompleto);
	}

	/**
	 * Obtener lineas.
	 *
	 * @param consumoPendienteConfirmacion
	 *            the consumo pendiente confirmacion
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private List<LineaDetalleConsumoTarjetaDTO> obtenerLineas(
			ConsumoPendienteConfirmacionEntity consumoPendienteConfirmacion, String nroTarjeta, Cuenta cuenta)
			throws ParseadorVisaException {
		List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
		List<AutorizacionEntity> autorizaciones = parseador
				.obtenerAutorizacionesDeConsumoPendienteConfirmacion(consumoPendienteConfirmacion);
		for (int j = 0; j < autorizaciones.size(); j++) {
			AutorizacionEntity autorizacion = autorizaciones.get(j);
			lineas.add(obtenerLineaDetalle(autorizacion, nroTarjeta, cuenta, esRecargable(cuenta)));
		}
		ordenarLineasPorFecha(lineas);
		return lineas;
	}

	/**
	 * Ordenar lineas por fecha.
	 *
	 * @param lineas
	 *            the lineas
	 */
	public void ordenarLineasPorFecha(List<LineaDetalleConsumoTarjetaDTO> lineas) {
		Collections.sort(lineas, new Comparator<LineaDetalleConsumoTarjetaDTO>() {
			@Override
			public int compare(LineaDetalleConsumoTarjetaDTO linea1, LineaDetalleConsumoTarjetaDTO linea2) {
				if (linea1.getFecha() == null || linea2.getFecha() == null) {
					return 0;
				}
				return linea2.getFecha().compareTo(linea1.getFecha());
			}
		});
	}

	/**
	 * Obtener linea detalle.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param cuenta
	 *            the cuenta
	 * @return the linea detalle consumo pendiente DTO
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private LineaDetalleConsumoTarjetaDTO obtenerLineaDetalle(AutorizacionEntity autorizacion, String nroTarjeta, Cuenta cuenta, Boolean esRecargable)
			throws ParseadorVisaException {
		LineaDetalleConsumoTarjetaDTO linea = new LineaDetalleConsumoTarjetaDTO();
		linea.setEsPendienteConfirmacion(true);
		linea.setNroTarjeta(nroTarjeta);
		linea.setNroTarjetaMascara(TarjetaBOUtils.formatearNumeroTarjeta(nroTarjeta, TarjetaUtils.getMarca(cuenta)));
		linea.setDescripcion(obtenerDescripcion(autorizacion));
		linea.setCuota(this.obtenerCuotas(parseador.obtenerDescripcion(autorizacion)));
		linea.setTieneCuota(linea.getCuota() != null && StringUtils.isNumeric(linea.getCuota().replaceAll("\\/", "")));
		if (linea.getTieneCuota()) {
			linea.setCuotasCanceladas(Integer.valueOf(StringUtils.substringBeforeLast(linea.getCuota(), "/")));
			linea.setCuotasTotales(Integer.valueOf(StringUtils.substringAfterLast(linea.getCuota(), "/")));
		}
		completarImporte(autorizacion, linea);
		linea.setFecha(obtenerFecha(autorizacion));
		linea.setCodigoEstablecimiento(autorizacion.getEstablecimiento().getCodigo());
		linea.setCodigoEstablecimiento(parseador.obtenerCodigoEstablecimiento(autorizacion));
		linea.setTipoConsumo(TipoConsumoTarjeta.CONSUMO_PENDIENTE);
		linea.setNroReferencia(parseador.obtenerNroComprobante(autorizacion));
		completarConsumoPesosYDolares(linea);
        invertirSignoConsumoSiEsRecargable(esRecargable, linea);
		return linea;
	}

	/**
	 * Completar consumo pesos Y dolares.
	 *
	 * @param linea
	 *            the linea
	 */
	public void completarConsumoPesosYDolares(LineaDetalleConsumoTarjetaDTO linea) {
		if (linea.getImportePesos() != null) {
			linea.setConsumoPesos(Boolean.TRUE);
		} else {
			if (linea.getImporteDolares() != null) {
				linea.setConsumoPesos(Boolean.FALSE);
			}
		}
	}

	/**
	 * Obtener fecha.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the date
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private Date obtenerFecha(AutorizacionEntity autorizacion) throws ParseadorVisaException {
		String fecha = parseador.obtenerFecha(autorizacion);
		return ISBANStringUtils.formatearFecha(fecha);
	}

	/**
	 * Completar importe.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @param linea
	 *            the linea
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private void completarImporte(AutorizacionEntity autorizacion, LineaDetalleConsumoTarjetaDTO linea)
			throws ParseadorVisaException {
		String importe = parseador.obtenerImporte(autorizacion);
		setearImporte(autorizacion, importe, linea);
	}

	/**
	 * Setear importe.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @param importe
	 *            the importe
	 * @param linea
	 *            the linea
	 */
	private void setearImporte(AutorizacionEntity autorizacion, String importe, LineaDetalleConsumoTarjetaDTO linea) {
		if (esImportePesos(autorizacion)) {
			linea.setImportePesos(ISBANStringUtils.formatearSaldoString(importe));
			linea.setConsumoPesos(Boolean.TRUE);
			linea.setConsumoDolares(Boolean.FALSE);
		} else {
			linea.setImporteDolares(ISBANStringUtils.formatearSaldoString(importe));
			linea.setConsumoPesos(Boolean.FALSE);
			linea.setConsumoDolares(Boolean.TRUE);
		}
	}

	/**
	 * Es importe pesos.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the boolean
	 */
	private Boolean esImportePesos(AutorizacionEntity autorizacion) {
		if (StringUtils.equals(autorizacion.getMoneda(), PESOS)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Obtener descripcion.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private String obtenerDescripcion(AutorizacionEntity autorizacion) throws ParseadorVisaException {
		String descripcion = parseador.obtenerDescripcion(autorizacion);
		if (tieneRegexpCuotas(descripcion)) {
			descripcion = cortarDescripcion(autorizacion.getEstablecimiento().getDescripcion());
		}
		if (esDescripcionURL(descripcion)) {
			return ISBANStringUtils.convertirStringToLowercase(descripcion);
		} else {
			return ISBANStringUtils.convertirStringToCamelcase(descripcion);
		}
	}

	/**
	 * Es descripcion URL.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the boolean
	 */
	private Boolean esDescripcionURL(String descripcion) {
		return descripcion.contains(".COM") || StringUtils.startsWith(descripcion, "WWW.");
	}

	/**
	 * Cortar descripcion.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the string
	 */
	private String cortarDescripcion(String descripcion) {
		String cuotas = cortarCuotas(descripcion);
		return (StringUtils.remove(descripcion, cuotas)).trim();
	}

	/**
	 * Cortar cuotas.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the string
	 */
	private String cortarCuotas(String descripcion) {
		return StringUtils.right(descripcion, ULTIMAS_CINCO_POSICIONES);
	}

	/**
	 * Armar respuesta OK.
	 *
	 * @param consumos
	 *            the consumos
	 * @return the respuesta
	 */
	private Respuesta<UltimosConsumosDTO> armarRespuestaOk(UltimosConsumosDTO consumos) {
		return casuistica.crearRespuestaOk(UltimosConsumosDTO.class, consumos);
	}

	/**
	 * Armar respuesta sin consumos.
	 *
	 * @return the respuesta
	 */
	private Respuesta<UltimosConsumosDTO> armarRespuestaSinConsumos() {
		return casuistica.crearRespuestaError(ErrorTarjetasEnum.SIN_CONSUMOS);
	}

	/**
	 * Armar respuesta error.
	 *
	 * @return the respuesta
	 */
	private Respuesta<UltimosConsumosDTO> crearRespuestaError() {
		return casuistica.crearRespuestaError(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES);
	}

}