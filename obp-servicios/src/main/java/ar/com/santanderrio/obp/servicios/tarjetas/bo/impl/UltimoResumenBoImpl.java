/*
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums.Currency;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.UltimoResumenBo;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.CreditCardsServiceConnector;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.exception.ConnectorException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers.CreditCardsSettlementObpMapper;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastSettlements.CreditCardLastSettlementDto;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LineaUltimaLiquidacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.UltimaLiquidacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaUltimoResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.TarjetasLegalUltimoResumenDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenFilaBean;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorUltimaLiquidacionException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Class UltimoResumenBoImpl.
 *
 * @author federico.n.flores
 */
@Component
public class UltimoResumenBoImpl extends TarjetasBOImpl implements UltimoResumenBo {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UltimoResumenBoImpl.class);
	
	/** The Constant ERROR_ULTIMO_RESUMEN. */
    private static final String ERROR_ULTIMO_RESUMEN = "Error en consultar ultimo resumen";

	/** The Constant CODIGO_PARAMETRIZABLE. */
	private static final String CODIGO_PARAMETRIZABLE = "{0}";

	/** The Constant SU_PAGO_EN_PESOS. */
	private static final String SU_PAGO_EN_PESOS = "Su Pago En Pesos";

	/** The Constant VISA_RECARGABLE. */
	public static final String VISA_RECARGABLE = "T";

	/** The parseador. */
	@Autowired
	private ParseadorWSUltimoResumen parseador;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;
	
	@Autowired
    private CreditCardsServiceConnector creditCardServiceConnector;
	
	@Autowired
    private CreditCardsSettlementObpMapper creditCardMapper;

	/**
	 * Obtiene el ultimo resumen.
	 *
	 * @param cliente
	 *            the cliente
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<UltimoResumenDTO> obtenerUltimoResumen(Cliente cliente, IdentificacionCuenta identificacionCuenta)
			throws BusinessException {
		try {
			Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
			RetornoTarjetasEntity entity = null;
        	try{
	            CreditCardLastSettlementDto creditCardLastSettlementDto =
	            		creditCardServiceConnector.getCreditCardLastSettlement(cuenta.getNroTarjetaCredito());
	            entity = creditCardMapper.mapToRetornoTarjetasEntity(creditCardLastSettlementDto);
        	} catch (Exception e){
        		LOGGER.error(ERROR_ULTIMO_RESUMEN, e);
				LOGGER.info("Se ejecuta fallback de ultimo resumen");
        		entity = this.obtenerRespuestaVisa(cuenta,
                        OperacionTarjetaWSEnum.ULTIMALIQUIDACION);
        	}
			LOGGER.error("Continua la ejecucion del servicio ultimo resumen");
			LOGGER.error("obtenerUltimoResumen, entity: {}", entity.toString());
            return generarRespuesta(entity, cuenta, cliente);
		} catch (DAOException e) {
			LOGGER.error("Error al llamar al DAO, armando respuesta error: {}", e);
			return armarRespuestaError();
		} catch (Exception ex) {
			LOGGER.error("Ha ocurrido un error", ex);
			throw new BusinessException();
		}
	}

	/**
	 * Genera la respuesta DTO para el ultimo resumen.
	 *
	 * @param entity
	 *            the entity
	 * @param cuenta
	 *            the cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	private Respuesta<UltimoResumenDTO> generarRespuesta(RetornoTarjetasEntity entity, Cuenta cuenta, Cliente cliente)
			throws ParseadorVisaException, ParseadorUltimaLiquidacionException, TarjetaBOUtilsException {
		if (parseador.tieneErrorDeCredenciales(entity)) {
			return armarRespuestaError();
		}
		String marca = TarjetaUtils.getMarca(cuenta);
		String nroTarjeta = TarjetaUtils.cortarNumeroTarjetaComoTarjetaActiva(cuenta.getNroTarjetaCredito(), marca);
		TarjetaEntity tarjetaEntity = parseador.obtenerTarjetaPorNroTarjetaActivaUltimaLiquidacion(entity, nroTarjeta);
		if (tarjetaEntity == null) {
			if (parseador.noTieneUltimoResumen(entity)) {
				return armarRespuestaSinUltimoResumen(cuenta);
			} else {
				return armarRespuestaError();
			}
		}
		return armarRespuestaUltimoResumen(tarjetaEntity, nroTarjeta, marca, cuenta, cliente);
	}

	/**
	 * Armar respuesta ultimo resumen.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param marca
	 *            the marca
	 * @param cuenta
	 *            the cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 * @Modificacion 180119 - se saca TarjetaUtils.CLASE_CUENTA_L como limites en dolares
	 */
	private Respuesta<UltimoResumenDTO> armarRespuestaUltimoResumen(TarjetaEntity tarjetaEntity, String nroTarjeta,
			String marca, Cuenta cuenta, Cliente cliente)
			throws TarjetaBOUtilsException, ParseadorUltimaLiquidacionException {
		UltimoResumenDTO dto = new UltimoResumenDTO();
		setearAlias(cuenta, dto);
		dto.setFechaCierreActual(obtenerFechaCierreActual(tarjetaEntity));
		dto.setFechaVencimientoActual(obtenerFechaVencimientoActual(tarjetaEntity));
		setearSaldos(dto, cuenta, tarjetaEntity);
		dto.setPagoMinimo(ISBANStringUtils.formatearSaldoString(parseador.obtenerPagoMinimo(tarjetaEntity)));
		dto.setLimiteFinanciacion(
				ISBANStringUtils.formatearSaldoString(parseador.obtenerLimiteFinanciacion(tarjetaEntity)));
		dto.setLimiteCompra(ISBANStringUtils.formatearSaldoString(parseador.obtenerLimiteCompra(tarjetaEntity)));
		dto.setLimiteCompraEnCuotas(
				ISBANStringUtils.formatearSaldoString(parseador.obtenerLimiteCompraEnCuotas(tarjetaEntity)));
		dto.setSonLimitesDolar(obtenerSonLimitesDolar(cuenta));
		dto.setFechaProximoCierre(obtenerFechaProximoCierre(tarjetaEntity));
		dto.setFechaProximoVencimiento(obtenerFechaProximoVencimiento(tarjetaEntity));
		dto.setFechaCierreAnterior(obtenerFechaCierreAnterior(tarjetaEntity));
		dto.setFechaVencimientoAnterior(obtenerFechaVencimientoAnterior(tarjetaEntity));
		dto.setTasaNominalAnualPesos(
				ISBANStringUtils.formatearSaldoString(parseador.obtenerTasaNominalAnualPesos(tarjetaEntity)));
		dto.setTasaNominalAnualDolares(
				ISBANStringUtils.formatearSaldoString(parseador.obtenerTasaNominalAnualDolares(tarjetaEntity)));
		dto.setTasaEfectivaMensualPesos(
				ISBANStringUtils.formatearSaldoString(parseador.obtenerTasaEfectivaMensualPesos(tarjetaEntity)));
		dto.setTasaEfectivaMensualDolares(
				ISBANStringUtils.formatearSaldoString(parseador.obtenerTasaEfectivaMensualDolares(tarjetaEntity)));
		dto.setMarca(marca);
		dto.setNumeroTarjeta(TarjetaUtils.cortarYFormatearNumeroTarjeta(nroTarjeta, marca));
		dto.setMensajeSEUO(this.getLegalBO().buscarLegal(CodigoMensajeConstantes.LEGAL_SEUO).getRespuesta());
		completarTarjetasYTerminos(dto, tarjetaEntity.getUltimaLiquidacion(), cuenta);
		dto.setMuestraTarjetasConCabecera(muestraCabecera(dto, cuenta));
		ModuloPermiso permisoPagoTarjetaCredito = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_PAGO_DE_TARJETA);
		dto.setMostrarOpcionPagoTarjetaCredito(
				(permisoPagoTarjetaCredito.isModuloOculto() || cliente.getCuentasParaEfectuarPago().isEmpty()) ? false : true);
		dto.setMensajeOpcionPagoTarjetaCredito(permisoPagoTarjetaCredito.isModuloOculto() ? permisoPagoTarjetaCredito.getMensaje() : "");

		if (isCreditCardBrand(marca)) {
			if(tarjetaEntity.getUltimaLiquidacion().getCurrency()==null){
				if (StringUtils.equals(cuenta.getClaseCuenta(), TarjetaUtils.CLASE_CUENTA_H)) {
					dto.setSonLimitesDolar(Boolean.TRUE);
				}
			} else if (Currency.USD.equals(tarjetaEntity.getUltimaLiquidacion().getCurrency())){
				dto.setSonLimitesDolar(Boolean.TRUE);
			}
		}

		return armarRespuestaUltimoResumen(dto);

	}
	private boolean isCreditCardBrand(String marca) {
		return StringUtils.equals(marca, TipoCuentaTarjeta.TIPOCTA_VISA.getAbreviatura())
				|| StringUtils.equals(marca, TipoCuentaTarjeta.TIPOCTA_AMEX.getAbreviatura())
				|| StringUtils.equals(marca, TipoCuentaTarjeta.TIPOCTA_MASTER.getAbreviatura());
	}

	/**
	 * Setear saldos.
	 *
	 * @param dto
	 *            the dto
	 * @param cuenta
	 *            the cuenta
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	private void setearSaldos(UltimoResumenDTO dto, Cuenta cuenta, TarjetaEntity tarjetaEntity)
			throws ParseadorUltimaLiquidacionException {
		if (TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum()) && VISA_RECARGABLE.equals(cuenta.getClaseCuenta())) {
			dto.setSaldoPesos(
					ISBANStringUtils.formatearSaldoString(parseador.obtenerSaldoEnPesos(tarjetaEntity)).abs());
		} else {
			dto.setSaldoPesos(ISBANStringUtils.formatearSaldoString(parseador.obtenerSaldoEnPesos(tarjetaEntity)));
			dto.setSaldoDolares(ISBANStringUtils.formatearSaldoString(parseador.obtenerSaldoEnDolares(tarjetaEntity)));
		}
	}

	/**
	 * Completa la lista de tarjetas y los terminos y condiciones.
	 *
	 * @param dto
	 *            the dto
	 * @param ultimaLiquidacion
	 *            the ultima liquidacion
	 * @param cuenta
	 *            the cuenta
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	private void completarTarjetasYTerminos(UltimoResumenDTO dto, UltimaLiquidacionEntity ultimaLiquidacion,
			Cuenta cuenta) throws TarjetaBOUtilsException {
		TarjetasLegalUltimoResumenDTO tarjetasLegalDTO = obtenerTarjetas(ultimaLiquidacion, cuenta);
		dto.setTarjetas(tarjetasLegalDTO.getTarjetasUltimoResumen());
		dto.setTerminosYCondiciones(tarjetasLegalDTO.getTerminosYCondiciones());
		dto.setOtrosConceptos(tarjetasLegalDTO.getOtrosConceptos());
	}

	/**
	 * Armar respuesta ultimo resumen.
	 *
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenDTO> armarRespuestaUltimoResumen(UltimoResumenDTO dto) {
		if (noTieneConsumos(dto)) {
			return crearRespuestaWarningSinConsumos(dto);
		}
		return this.getRespuestaFactory().crearRespuestaOk(UltimoResumenDTO.class, dto);
	}

	/**
	 * Obtener fecha vencimiento anterior.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the date
	 */
	private Date obtenerFechaVencimientoAnterior(TarjetaEntity tarjetaEntity) {
		try {
			String fecha = parseador.obtenerFechaVencimientoAnterior(tarjetaEntity);
			if (fecha == null) {
				return null;
			} else {
				return TarjetaBOUtils.formatearFechaCompleta(fecha);
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener fecha de vencimiento anterior", e);
			return null;
		}
	}

	/**
	 * Obtener fecha cierre anterior.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the date
	 */
	private Date obtenerFechaCierreAnterior(TarjetaEntity tarjetaEntity) {
		try {
			String fecha = parseador.obtenerFechaCierreAnterior(tarjetaEntity);
			if (fecha == null) {
				return null;
			} else {
				return TarjetaBOUtils.formatearFechaCompleta(fecha);
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener fecha de cierre anterior", e);
			return null;
		}
	}

	/**
	 * Obtener fecha proximo vencimiento.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the date
	 */
	private Date obtenerFechaProximoVencimiento(TarjetaEntity tarjetaEntity) {
		try {
			String fecha = parseador.obtenerFechaProximoVencimiento(tarjetaEntity);
			if (fecha == null) {
				return null;
			} else {
				return TarjetaBOUtils.formatearFechaCompleta(fecha);
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener fecha de proximo vencimiento", e);
			return null;
		}
	}

	/**
	 * Obtener fecha proximo cierre.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the date
	 */
	private Date obtenerFechaProximoCierre(TarjetaEntity tarjetaEntity) {
		try {
			String fecha = parseador.obtenerFechaProximoCierre(tarjetaEntity);
			if (fecha == null) {
				return null;
			} else {
				return TarjetaBOUtils.formatearFechaCompleta(fecha);
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener fecha de proximo cierre", e);
			return null;
		}
	}

	/**
	 * Obtener fecha vencimiento actual.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the date
	 */
	private Date obtenerFechaVencimientoActual(TarjetaEntity tarjetaEntity) {
		try {
			String fecha = parseador.obtenerFechaVencimientoActual(tarjetaEntity);
			if (fecha == null) {
				return null;
			} else {
				return TarjetaBOUtils.formatearFechaCompleta(fecha);
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener fecha de vencimiento actual", e);
			return null;
		}
	}

	/**
	 * Obtener fecha cierre actual.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the date
	 */
	private Date obtenerFechaCierreActual(TarjetaEntity tarjetaEntity) {
		try {
			String fecha = parseador.obtenerFechaCierreActual(tarjetaEntity);
			if (fecha == null) {
				return null;
			} else {
				return TarjetaBOUtils.formatearFechaCompleta(fecha);
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener fecha de cierre actual", e);
			return null;
		}
	}

	/**
	 * Obtener son limites dolar.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 * 
	 * @Modificacion 180119 - se saca TarjetaUtils.CLASE_CUENTA_L como limites en dolares
	 */
	private Boolean obtenerSonLimitesDolar(Cuenta cuenta) {
			return Boolean.FALSE;
	}

	/**
	 * Armar respuesta error.
	 *
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenDTO> armarRespuestaError() {
		return this.getRespuestaFactory().crearRespuestaError(
				TagItemMensajeTarjetaEnum.ULTIMORESUMEN.getDescripcionTag(), TipoError.ERROR_CARGA_ULTIMO_RESUMEN,
				CodigoMensajeConstantes.CODIGO_ERROR_CARGA_ULTIMO_RESUMEN);
	}

	/**
	 * Armar respuesta sin ultimo resumen.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenDTO> armarRespuestaSinUltimoResumen(Cuenta cuenta) {
		return this.getRespuestaFactory().crearRespuestaError(UltimoResumenDTO.class,
				obtenerListaItemMensajeParaRespuestaSinUltimoResumen(TarjetaUtils.getMarca(cuenta)));
	}

	/**
	 * Obtener lista item mensaje para respuesta sin ultimo resumen.
	 *
	 * @param marca
	 *            the marca
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerListaItemMensajeParaRespuestaSinUltimoResumen(String marca) {
		List<ItemMensajeRespuesta> itemList = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setMensaje(obtenerMensajeErrorSinUltimoResumen(marca));
		item.setTag(TagItemMensajeTarjetaEnum.ULTIMORESUMEN.getDescripcionTag());
		item.setTipoError(TipoError.ERROR_SIN_ULTIMO_RESUMEN.getDescripcion());
		itemList.add(item);
		return itemList;
	}

	/**
	 * Obtener mensaje error sin ultimo resumen.
	 *
	 * @param marca
	 *            the marca
	 * @return the string
	 */
	private String obtenerMensajeErrorSinUltimoResumen(String marca) {
		Mensaje mensaje = this.getMensajeBO()
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_SIN_ULTIMO_RESUMEN);
		if (!StringUtils.equals(mensaje.getCodigo(), CodigoMensajeConstantes.CODIGO_ERROR_GENERICO)
				&& tieneCodigoParametrizable(mensaje)) {
			return reemplazarCodigoParametrizable(mensaje.getMensaje(), marca);
		} else {
			return mensaje.getMensaje();
		}
	}

	/**
	 * Reemplazar codigo parametrizable.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @param marca
	 *            the marca
	 * @return the string
	 */
	private String reemplazarCodigoParametrizable(String mensaje, String marca) {
		if (StringUtils.equals(marca, TipoCuentaTarjeta.TIPOCTA_VISA.getDescripcion())) {
			return StringUtils.replace(mensaje, CODIGO_PARAMETRIZABLE, marca);
		} else {
			return StringUtils.replace(mensaje, CODIGO_PARAMETRIZABLE, "American Express");
		}
	}

	/**
	 * Tiene codigo parametrizable.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the boolean
	 */
	private Boolean tieneCodigoParametrizable(Mensaje mensaje) {
		if (mensaje.getMensaje().contains(CODIGO_PARAMETRIZABLE)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Obtener tarjetas.
	 *
	 * @param ultimaLiquidacion
	 *            the ultima liquidacion
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	private TarjetasLegalUltimoResumenDTO obtenerTarjetas(UltimaLiquidacionEntity ultimaLiquidacion, Cuenta cuenta)
			throws TarjetaBOUtilsException {
		List<UltimoResumenTarjetaDTO> tarjetas = new ArrayList<UltimoResumenTarjetaDTO>();
		List<LineaUltimoResumenTarjetaDTO> lineasUltimoResumen = new ArrayList<LineaUltimoResumenTarjetaDTO>();
		String legal = "";
		List<LineaUltimaLiquidacionEntity> lineas = ultimaLiquidacion.getDetalleLiquidacion().getLineas();
		for (LineaUltimaLiquidacionEntity linea : lineas) {
			UltimoResumenFilaBean fila = parseador.obtenerCamposDetalleLiquidacion(linea.getDescripcion(), "");
			if (parseador.esMovimiento(fila)) {
				LineaUltimoResumenTarjetaDTO lineaUltimoResumen = new LineaUltimoResumenTarjetaDTO();
				lineaUltimoResumen.setFecha(parseador.formatearFechaDate(fila));
				lineaUltimoResumen.setImportePesos(TarjetaBOUtils.convertirSaldo(parseador.obtenerImportePesos(fila)));
				lineaUltimoResumen
						.setImporteDolares(TarjetaBOUtils.convertirSaldo(parseador.obtenerImporteDolares(fila)));
				lineaUltimoResumen.setComprobante(parseador.obtenerComprobante(fila));
				String descripcion = obtenerDescripcion(fila);
				lineaUltimoResumen.setDescripcion(descripcion);
				lineaUltimoResumen.setCuota(obtenerCuotas(descripcion));
				lineaUltimoResumen.setTieneCuota(lineaUltimoResumen.getCuota() != null);
				completarCuotasTotalesCanceladasYDescripcion(lineaUltimoResumen);
				lineasUltimoResumen.add(lineaUltimoResumen);
			} else if (parseador.tieneTotales(fila)) {
				UltimoResumenTarjetaDTO tarjeta = new UltimoResumenTarjetaDTO();
				tarjeta.setNumeroTarjeta(parseador.obtenerNumeroTarjeta(fila));
				tarjeta.setTotalPesos(obtenerTotalPesos(fila));
				tarjeta.setTotalDolares(obtenerTotalDolares(fila));
				tarjeta.setLineas(lineasUltimoResumen);
				tarjetas.add(tarjeta);
				lineasUltimoResumen = new ArrayList<LineaUltimoResumenTarjetaDTO>();
			} else if (parseador.tieneLegales(fila)) {
				legal = completarLegalesUltimoResumen(fila.getLegal(), legal);
			}
		}
		return completarTarjetasLegalDTO(ordenarTarjetas(tarjetas, cuenta), legal, lineasUltimoResumen);
	}

	/**
	 * Obtiene la descripcion.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	private String obtenerDescripcion(UltimoResumenFilaBean fila) {
		String descripcion = parseador.obtenerDescripcion(fila).trim();
		if (descripcion.contains(SU_PAGO_EN_PESOS)) {
			return SU_PAGO_EN_PESOS;
		} else {
			return descripcion;
		}
	}

	/**
	 * Completa las cuotas totales, las cuotas canceladas y la descripcion.
	 *
	 * @param lineaUltimoResumen
	 *            the linea ultimo resumen
	 */
	private void completarCuotasTotalesCanceladasYDescripcion(LineaUltimoResumenTarjetaDTO lineaUltimoResumen) {
		if (lineaUltimoResumen.getTieneCuota()) {
			String descripcion = StringUtils.remove(lineaUltimoResumen.getDescripcion(),
					"c." + lineaUltimoResumen.getCuota());
			lineaUltimoResumen.setDescripcion(StringUtils.remove(descripcion, "C." + lineaUltimoResumen.getCuota()));
			lineaUltimoResumen.setCuotasCanceladas(StringUtils.substringBeforeLast(lineaUltimoResumen.getCuota(),
					ISBANStringUtils.SEPARATOR_BARRA_LATERAL));
			lineaUltimoResumen.setCuotasTotales(StringUtils.substringAfterLast(lineaUltimoResumen.getCuota(),
					ISBANStringUtils.SEPARATOR_BARRA_LATERAL));
		}
	}

	/**
	 * Completa las tarjetas y legal DTO.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @param legal
	 *            the legal
	 * @param otrosConceptos
	 *            the otros conceptos
	 * @return the tarjetas legal ultimo resumen DTO
	 */
	private TarjetasLegalUltimoResumenDTO completarTarjetasLegalDTO(List<UltimoResumenTarjetaDTO> tarjetas,
			String legal, List<LineaUltimoResumenTarjetaDTO> otrosConceptos) {
		TarjetasLegalUltimoResumenDTO tarjetasLegalUltimoResumenDTO = new TarjetasLegalUltimoResumenDTO();
		tarjetasLegalUltimoResumenDTO.setTarjetasUltimoResumen(tarjetas);
		tarjetasLegalUltimoResumenDTO.setTerminosYCondiciones(legal);
		tarjetasLegalUltimoResumenDTO.setOtrosConceptos(otrosConceptos);
		return tarjetasLegalUltimoResumenDTO;
	}

	/**
	 * Completa los terminos y condiciones del ultimo resumen.
	 *
	 * @param legalLinea
	 *            the legal linea
	 * @param legal
	 *            the legal
	 * @return the string
	 */
	private String completarLegalesUltimoResumen(String legalLinea, String legal) {
		if (StringUtils.isBlank(legal) || StringUtils.isEmpty(legal)) {
			return legalLinea;
		} else {
			StringBuilder sb = new StringBuilder();
			return sb.append(legal).append(" ").append(legalLinea).toString();
		}
	}

	/**
	 * Ordenar tarjetas.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 */
	private List<UltimoResumenTarjetaDTO> ordenarTarjetas(List<UltimoResumenTarjetaDTO> tarjetas, Cuenta cuenta) {
		List<UltimoResumenTarjetaDTO> tarjetasOrdenadas = new ArrayList<UltimoResumenTarjetaDTO>();
		for (UltimoResumenTarjetaDTO tarjeta : tarjetas) {
			if (esTarjetaTitular(tarjeta, cuenta)) {
				tarjetasOrdenadas.add(tarjeta);
				break;
			}
		}
		for (UltimoResumenTarjetaDTO tarjeta : tarjetas) {
			if (!tarjetasOrdenadas.contains(tarjeta)) {
				tarjetasOrdenadas.add(tarjeta);
			}
		}
		return tarjetasOrdenadas;
	}

	/**
	 * Tiene alias.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean tieneAlias(Cuenta cuenta) {
		if (cuenta.getAlias() != null && !StringUtils.equals(cuenta.getAlias(), "")) {
			return true;
		}
		return false;
	}

	/**
	 * Setear alias.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param dto
	 *            the dto
	 */
	private void setearAlias(Cuenta cuenta, UltimoResumenDTO dto) {
		if (tieneAlias(cuenta)) {
			dto.setTieneAlias(true);
			dto.setAlias(cuenta.getAlias());
		} else {
			dto.setTieneAlias(false);
		}
	}

	/**
	 * Muestra cabecera.
	 *
	 * @param dto
	 *            the dto
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean muestraCabecera(UltimoResumenDTO dto, Cuenta cuenta) {
		List<UltimoResumenTarjetaDTO> tarjetas = dto.getTarjetas();
		if (tarjetas.size() > 1 || (tarjetas.size() == 1 && !esTarjetaTitular(tarjetas.get(0), cuenta))) {
			return true;
		}
		return false;
	}

	/**
	 * Es tarjeta titular.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esTarjetaTitular(UltimoResumenTarjetaDTO tarjeta, Cuenta cuenta) {
		String numeroTarjetaDTO = StringUtils.right(tarjeta.getNumeroTarjeta(), 4);
		String numeroTarjetaCuenta = StringUtils.right(cuenta.getNroTarjetaCredito(), 4);
		if (StringUtils.equals(numeroTarjetaDTO, numeroTarjetaCuenta)) {
			return true;
		}
		return false;
	}

	/**
	 * No tiene consumos.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	private Boolean noTieneConsumos(UltimoResumenDTO dto) {
		if (dto.getSaldoPesos().compareTo(BigDecimal.valueOf(0)) == 0
				&& dto.getSaldoDolares().compareTo(BigDecimal.valueOf(0)) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Crear respuesta warning sin consumos.
	 *
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenDTO> crearRespuestaWarningSinConsumos(UltimoResumenDTO dto) {
		return this.getRespuestaFactory().crearRespuestaWarning(dto,
				TagItemMensajeTarjetaEnum.ULTIMORESUMEN.getDescripcionTag(), TipoError.ERROR_SIN_CONSUMOS,
				CodigoMensajeConstantes.CODIGO_ERROR_ULTIMO_RESUMEN_SIN_CONSUMOS);
	}

	/**
	 * Obtener total pesos.
	 *
	 * @param fila
	 *            the fila
	 * @return the big decimal
	 */
	private BigDecimal obtenerTotalPesos(UltimoResumenFilaBean fila) {
		String totalPesos = parseador.obtenerTotalPesos(fila);
		if (totalPesos == null) {
			return BigDecimal.valueOf(0.00);
		}
		try {
			return TarjetaBOUtils.convertirSaldo(totalPesos);
		} catch (TarjetaBOUtilsException e) {
			LOGGER.warn("Error al convertir el total pesos", e);
			return null;
		}
	}

	/**
	 * Obtener total dolares.
	 *
	 * @param fila
	 *            the fila
	 * @return the big decimal
	 */
	private BigDecimal obtenerTotalDolares(UltimoResumenFilaBean fila) {
		String totalDolares = parseador.obtenerTotalDolares(fila);
		if (totalDolares == null) {
			return BigDecimal.valueOf(0.00);
		}
		try {
			return TarjetaBOUtils.convertirSaldo(totalDolares);
		} catch (TarjetaBOUtilsException e) {
			LOGGER.warn("Error al convertir el total dolares", e);
			return null;
		}
	}

}
