/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CuotasPendientesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetalleCuotaPendienteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaCuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSCuotasPendientes;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Class CuotasPendientesBOImpl.
 */
@Component
public class CuotasPendientesBOImpl extends TarjetasBOImpl implements CuotasPendientesBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuotasPendientesBOImpl.class);

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The cuota pendiente DAO. */
	@Autowired
	private TarjetaDAO tarjetaDAO;
	
	/** The reporte dao. */
	@Autowired
	private ReporteDAO reporteDAO;

	/** The obtencion datos cuotas pendientes. */
	@Autowired
	private ParseadorWSCuotasPendientes parseador;

    /** The Constant CODIGO_ERROR_CONSUMOS. */
    private static final String CODIGO_ERROR_CONSUMOS = "112107";


	/**
	 * Obtiene las cuotas pendientes.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<CuotasPendientesDTO> obtenerCuotasPendientes(IdentificacionCuenta identificacionCuenta,
			Cliente cliente) throws BusinessException {
		try {
			Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
			RetornoTarjetasEntity entity = tarjetaDAO.obtenerTarjetasDeVisaWS(cuenta,
					OperacionTarjetaWSEnum.CUOTASPENDIENTES, cuenta.getCliente());

			if (parseador.tieneErrorDeCredenciales(entity)) {
				return armarRespuestaError();
			}
			return crearRespuesta(entity, cuenta);
		} catch (DAOException e) {
			LOGGER.error("Error al llamar al DAO. Armando respuesta error.", e);
			return armarRespuestaError();
		} catch (Exception e) {
			LOGGER.error("Error en BO.", e);
			throw new BusinessException(e);
		}
	}

	/**
	 * Genera una respuesta a partir del entity. Si no se encuentra la tarjeta
	 * seleccionada en el xml usando el tag
	 * tarjetas/tarjeta/document/datos/tarjetaActiva = tarjeta a mostrar, y el
	 * xml retorno todos codigos de errores=112107, entonces la tarjeta no tiene
	 * consumos.
	 *
	 * @param retorno
	 *            the retorno
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesDTO> crearRespuesta(RetornoTarjetasEntity retorno, Cuenta cuenta) {
		try {
			String marca = TarjetaUtils.getMarca(cuenta);
			String nroTarjetaCreditoCortado = TarjetaBOUtils.cortarNumeroTarjeta(cuenta.getNroTarjetaCredito(), marca);
			TarjetaEntity tarjetaEntity = parseador.obtenerTarjetaPorNroTarjetaActiva(retorno,
					nroTarjetaCreditoCortado);
			if (tarjetaEntity != null) {
				return armarRespuestaCuotasPendientes(tarjetaEntity, retorno, nroTarjetaCreditoCortado, marca);
			}
			if (esTarjetaSinConsumos(retorno, cuenta)) {
				return armarRespuestaSinCuotasPendiente();
			}
			return armarRespuestaError();
		} catch (ParseadorVisaException e) {
			LOGGER.error("Error en Parseador Visa.", e);
			return armarRespuestaError();
		}
	}

    /**
     * Es tarjeta sin consumos.
     *
     * @param entity the entity
     * @param cuenta the cuenta
     * @return the boolean
     * @throws ParseadorVisaException the parseador visa exception
     */
    private Boolean esTarjetaSinConsumos(RetornoTarjetasEntity entity,
            Cuenta cuenta) throws ParseadorVisaException {
        for (TarjetaEntity tarjeta : entity.getTarjetas().getTarjetaList()) {
            if (hayDatosTarjeta(tarjeta, cuenta.getNroTarjetaCredito())) {
                return false;
            }
            if (errorDistintoASinConsumos(tarjeta)) {
                throw new ParseadorVisaException();
            }
        }
        return true;
    }

    /**
     * Hay datos tarjeta.
     *
     * @param tarjeta the tarjeta
     * @param nroCuentaProducto the nro cuenta producto
     * @return the boolean
     */
    private Boolean hayDatosTarjeta(TarjetaEntity tarjeta,
            String nroCuentaProducto) {
        return nroCuentaProducto != null && tarjeta != null
                && tarjeta.getTarjetaDocument() != null
                && tarjeta.getTarjetaDocument().getDatos() != null
                && tarjeta.getTarjetaDocument().getDatos()
                        .getTarjetaActiva() != null
                && TarjetaUtils
                        .sacarCerosDeAdelante(tarjeta.getTarjetaDocument()
                                .getDatos().getTarjetaActiva())
                        .equals(TarjetaUtils
                                .sacarCerosDeAdelante(nroCuentaProducto));
    }

    /**
     * Error distinto A sin consumos.
     *
     * @param tarjeta the tarjeta
     * @return the boolean
     * @throws ParseadorVisaException the parseador visa exception
     */
    private Boolean errorDistintoASinConsumos(TarjetaEntity tarjeta)
            throws ParseadorVisaException {
        String codigoErrorTarjeta = obtenerCodigoError(tarjeta);
		return codigoErrorTarjeta != null
				&& !CODIGO_ERROR_CONSUMOS.equals(codigoErrorTarjeta);
	}

    /**
     * Obtener codigo error.
     *
     * @param tarjeta the tarjeta
     * @return the string
     * @throws ParseadorVisaException the parseador visa exception
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
	 * Armar respuesta cuotas pendientes.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @param retorno
	 *            the retorno
	 * @param nroTarjetaCreditoCortado
	 *            the nro tarjeta credito cortado
	 * @param marca
	 *            the marca
	 * @return the respuesta
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private Respuesta<CuotasPendientesDTO> armarRespuestaCuotasPendientes(TarjetaEntity tarjetaEntity,
			RetornoTarjetasEntity retorno, String nroTarjetaCreditoCortado, String marca)
			throws ParseadorVisaException {
		try {
			CuotasPendientesDTO dto = new CuotasPendientesDTO();
			dto.setTotalCuotasPendientes(parseador.obtenerTotalCuotasPendientes(tarjetaEntity));
			dto.setTarjetasCuotasPendientes(obtenerTarjetasCuotasPendientes(tarjetaEntity, retorno, marca));
			completarMostrarNombreAdicional(dto.getTarjetasCuotasPendientes(), marca, nroTarjetaCreditoCortado);
			return armarRespuestaCuotasPendientes(dto);
		} catch (TarjetaBOUtilsException e) {
			LOGGER.error("Error en Parseador Visa.", e);
			return armarRespuestaError();
		}
	}

	/**
	 * Completa mostrar nombre adicional.
	 *
	 * @param tarjetasCuotasPendientes
	 *            the tarjetas cuotas pendientes
	 * @param marca
	 *            the marca
	 * @param nroTarjetaCortado
	 *            the nro tarjeta cortado
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private void completarMostrarNombreAdicional(List<CuotasPendientesTarjetaDTO> tarjetasCuotasPendientes,
			String marca, String nroTarjetaCortado) throws ParseadorVisaException {
		for (CuotasPendientesTarjetaDTO cpt : tarjetasCuotasPendientes) {
			if (!esTitularTarjetaCuotasPendientes(cpt)
					&& !coincideNroTarjetaConTarjetaCuotasPenditentes(marca, cpt, nroTarjetaCortado)) {
				cpt.setMostrarNombreAdicional(Boolean.TRUE);
			} else {
				cpt.setMostrarNombreAdicional(Boolean.FALSE);
			}
		}
	}

	/**
	 * Coincide el numero de tarjeta de la cuenta con la tarjetaDTO de cuotas
	 * penditentes.
	 *
	 * @param marca
	 *            the marca
	 * @param cpt
	 *            the cpt
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the boolean
	 *
	 */
	private Boolean coincideNroTarjetaConTarjetaCuotasPenditentes(String marca, CuotasPendientesTarjetaDTO cpt,
			String nroTarjeta) {
		if (TarjetaUtils.cortarYFormatearNumeroTarjeta(nroTarjeta, marca).equals(cpt.getNumero())) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Es titular la tarjeta de cuotas pendientes.
	 *
	 * @param cpt
	 *            the cpt
	 * @return the boolean
	 */
	private Boolean esTitularTarjetaCuotasPendientes(CuotasPendientesTarjetaDTO cpt) {
		if (cpt.getEsTitular()) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Obtiene la coleccion tarjetas con cuotas pendientes.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @param retorno
	 *            the retorno
	 * @param marca
	 *            the marca
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	private List<CuotasPendientesTarjetaDTO> obtenerTarjetasCuotasPendientes(TarjetaEntity tarjetaEntity,
			RetornoTarjetasEntity retorno, String marca) throws ParseadorVisaException, TarjetaBOUtilsException {
		List<CuotasPendientesTarjetaDTO> listaDTO = new ArrayList<CuotasPendientesTarjetaDTO>();

		String tarjetaActiva = tarjetaEntity.getTarjetaDocument().getDatos().getTarjetaActiva();
		String ultimos4Activa = StringUtils.substring(tarjetaActiva, tarjetaActiva.length() - 4, tarjetaActiva.length());
		TarjetaEntity tarjeta = parseador.obtenerTarjetaPorUltimosCuatroNros(retorno, ultimos4Activa);
		List<String> codigosTarjetas = parseador.obtenerCodigosDeTarjetas(tarjetaEntity);

		for (String codigo : codigosTarjetas) {
			CuotasPendientesTarjetaDTO dto = new CuotasPendientesTarjetaDTO();

			// si es adicional a tarjeta activa
			dto.setEsTitular(false);
			String numeroTarjetaOculto = "XXXX-" + codigo;

			if (codigo == null || codigo.equals(ultimos4Activa)) {
				dto.setEsTitular(parseador.esCategoriaTitular(tarjeta));
				numeroTarjetaOculto = TarjetaUtils.cortarYFormatearNumeroTarjeta(
						parseador.obtenerTarjetaActiva(tarjeta), marca);
			}

			dto.setLineasCuotasPendientes(obtenerLineasCuotasPendientes(tarjeta, codigo));
			dto.setMarca(marca);
			dto.setNombreAdicional(parseador.obtenerNombre(tarjeta));
			dto.setNumero(numeroTarjetaOculto);
			dto.setTotal(parseador.obtenerSubtotalCuotasPendientes(tarjeta, codigo));

			listaDTO.add(dto);
		}

		return listaDTO;
	}

	/**
	 * Obtiene la lista de lineas de una tarjeta con cuotas pendientes.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param codigo
	 *            the codigo
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	private List<CuotasPendientesLineaDTO> obtenerLineasCuotasPendientes(TarjetaEntity tarjeta, String codigo)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		List<CuotasPendientesLineaDTO> lineasCuotasPendientes = new ArrayList<CuotasPendientesLineaDTO>();

		for (TarjetaCuotasPendientesEntity tarjetaCuotasPendientes : parseador.obtenerListaCuotasPendientes(tarjeta)) {
			if (StringUtils.equals(StringUtils.right(tarjetaCuotasPendientes.getCodigoTarjeta(), 4), codigo)) {
				for (DetalleCuotaPendienteEntity detalleCuota : tarjetaCuotasPendientes
						.getDetalleCuotaPendienteList()) {

					CuotasPendientesLineaDTO lineaDTO = new CuotasPendientesLineaDTO();
					lineaDTO.setCantidadCuotas(parseador.obtenerCuotas(detalleCuota));
					lineaDTO.setCuotasPendientes(parseador.obtenerCuotasPendientes(detalleCuota));
					lineaDTO.setEstablecimiento(parseador.obtenerEstablecimiento(detalleCuota));
					lineaDTO.setFecha(parseador.obtenerFecha(detalleCuota));
					lineaDTO.setOperacion(parseador.obtenerNumeroComprobante(detalleCuota));
					lineaDTO.setRestante(parseador.obtenerImporte(detalleCuota));
					lineasCuotasPendientes.add(lineaDTO);
				}
			}
		}
		return lineasCuotasPendientes;
	}

	/**
	 * Armar respuesta cuotas pendientes.
	 *
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesDTO> armarRespuestaCuotasPendientes(CuotasPendientesDTO dto) {
		return respuestaFactory.crearRespuestaOk(CuotasPendientesDTO.class, dto);
	}

	/**
	 * Armar respuesta SIN CUOTAS PENDIENTES.
	 *
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesDTO> armarRespuestaSinCuotasPendiente() {
		return respuestaFactory.crearRespuestaError(null, TipoError.SIN_CUOTAS_PENDIENTES,
				CodigoMensajeConstantes.TARJETA_SIN_CUOTAS_PENDIENTES);
	}

	/**
	 * Armar respuesta con ERROR.
	 *
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesDTO> armarRespuestaError() {
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.TARJETA_ERROR_CARGA_CUOTAS_PENDIENTES);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.CuotasPendientesBO#obtenerReporte(java.lang.Object, java.lang.String, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporte(Object body, String proceso, Cliente cliente) {
		return reporteDAO.obtenerReporte(body, proceso, cliente,false);
	}

}
