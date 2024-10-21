/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CasuisticaErrorTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.UltimosConsumosTarjetaBO;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.CreditCardsServiceConnector;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.exception.ConnectorException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers.CreditCardsMovementsObpMapper;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements.CreditCardLastMovementsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetallePromocionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.MovimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaUltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ErrorTarjetasEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TipoConsumoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSUltimoConsumoImpl;

/**
 * The Class UltimosConsumosTarjetaBOImpl.
 */
@Component
public class UltimosConsumosTarjetaBOImpl extends TarjetasBOImpl implements UltimosConsumosTarjetaBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UltimosConsumosTarjetaBOImpl.class);

    /** The Constant ERROR_ULTIMOS_CONSUMOS. */
    private static final String LOGGER_ERROR = "Error en calcular los ultimos consumos";

    /** The Constant CODIGO_TARJETA_TI. */
    private static final String CODIGO_TARJETA_TI = "0000000000000000";

    /** The Constant BLANCO. */
    private static final String BLANCO = "";

    /** The Constant FECHA_ANTIGUA. */
    private static final String FECHA_ANTIGUA = "05/04/1900";

    /** The Constant CODIGO_ERROR_CONSUMOS. */
    private static final String CODIGO_ERROR_CONSUMOS = "112107";

    /** The Constant PREFIJO_BONIFICACION. */
    private static final String PREFIJO_BONIFICACION = "Bonif. ";
    
    /** The parseador. */
    @Autowired
    private ParseadorWSUltimoConsumoImpl parseador;

    /** The reporte dao. */
    @Autowired
    private ReporteDAO reporteDAO;

    /** The casuistica. */
    @Autowired
    private CasuisticaErrorTarjetasBO casuistica = new CasuisticaErrorTarjetasBOImpl();
    
    @Autowired
    private CreditCardsServiceConnector creditCardServiceConnector;

    @Autowired
    private CreditCardsMovementsObpMapper creditCardMapper;
    
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.UltimosConsumosTarjetaBO#
     * obtenerReporte(java.lang.Object, java.lang.String,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<Reporte> obtenerReporte(Object body, String proceso, Cliente cliente) {
        return reporteDAO.obtenerReporte(body, proceso, cliente, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.UltimosConsumosTarjetaBO#
     * generarUltimosConsumosDTO(java.util.List,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
     */
    @Override
    public UltimosConsumosDTO generarUltimosConsumosDTO(List<ConsumoTarjetaDTO> ultimosConsumos, Cuenta cuenta) {
        return new UltimosConsumosDTO(ultimosConsumos, muestraCabecera(ultimosConsumos, cuenta));
    }

    /**
     * Obtener ultimos consumos.
     *
     * @param cuenta
     *            the cuenta
     * @return the respuesta
     */
    @Override
    public Respuesta<UltimosConsumosDTO> obtenerUltimosConsumos(Cuenta cuenta) {
        try {
        	RetornoTarjetasEntity retorno = null;
        	try{
	            CreditCardLastMovementsDto creditCardLastMovementDto =
	            		creditCardServiceConnector.getCreditCardLastMovements(cuenta.getNroTarjetaCredito());
	            retorno = creditCardMapper.mapToRetornoTarjetasEntity(creditCardLastMovementDto);
        	} catch (Exception e){
        		LOGGER.error("Error al consultar LastMovements en CreditCardsApi", e);
        		LOGGER.info("Se ejecuta fallback de ultimos consumos");
        		retorno = this.obtenerRespuestaVisa(cuenta,
                        OperacionTarjetaWSEnum.ULTIMOSMOVIMIENTOS);
        	}
        	LOGGER.info("Continua la ejecucion del servicio ultimos consumos");
        	LOGGER.info("obtenerUltimosConsumos, retorno {}.", retorno.toString());
            List<ConsumoTarjetaDTO> consumos = parsearRespuestaWS(retorno, cuenta);
            ordenarConsumosPorTitularidad(consumos);
            return generarRespuesta(consumos);
        } catch (NullPointerException e) {
            LOGGER.error(LOGGER_ERROR, e);
            return crearRespuestaError();
        } catch (BusinessException e) {
            LOGGER.error(LOGGER_ERROR, e);
            return crearRespuestaError();
        } catch (DAOException e) {
            LOGGER.error(LOGGER_ERROR, e);
            return crearRespuestaError();
        } 
    }

    /**
     * Parsear respuesta WS, y retorna la lista de dtos.
     *
     * @param retorno
     *            the retorno
     * @param cuenta
     *            the cuenta
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    private List<ConsumoTarjetaDTO> parsearRespuestaWS(RetornoTarjetasEntity retorno, Cuenta cuenta)
            throws BusinessException {
        if (esTarjetaSinConsumos(retorno, cuenta)) {
            return new ArrayList<ConsumoTarjetaDTO>();
        }
        TarjetaEntity entidadDeTarjetaPrincipal = parseador.obtenerPorNumeroDeTarjetaActiva(retorno,
                this.cortarNroTarjetaDesdeCuenta(cuenta));
        if (entidadDeTarjetaPrincipal != null) {
            return procesarUltimosMovimientos(retorno, cuenta, entidadDeTarjetaPrincipal);
        }
        throw new BusinessException();
    }

    /**
     * Es tarjeta sin consumos.
     *
     * @param retorno
     *            the retorno
     * @param cuenta
     *            the cuenta
     * @return the boolean
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    public Boolean esTarjetaSinConsumos(RetornoTarjetasEntity retorno, Cuenta cuenta) throws ParseadorVisaException {
        List<TarjetaEntity> tarjetasEntity = parseador.obtenerTarjetas(retorno);
        for (TarjetaEntity tarjeta : tarjetasEntity) {
            if (hayDatosTarjeta(tarjeta, cuenta.getNroTarjetaCredito())) {
                return false;
            }
        }
        for (TarjetaEntity tarjeta : tarjetasEntity) {
            if (errorDiferenteSinConsumos(tarjeta)) {
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
    private Boolean hayDatosTarjeta(TarjetaEntity tarjeta, String nroCuentaProducto) {
        return nroCuentaProducto != null && tarjeta != null && tarjeta.getTarjetaDocument() != null
                && tarjeta.getTarjetaDocument().getDatos() != null
                && tarjeta.getTarjetaDocument().getDatos().getTarjetaActiva() != null
                && TarjetaUtils.sacarCerosDeAdelante(tarjeta.getTarjetaDocument().getDatos().getTarjetaActiva())
                        .equals(TarjetaUtils.sacarCerosDeAdelante(nroCuentaProducto));
    }

    /**
     * Error diferente sin consumos.
     *
     * @param tarjeta
     *            the tarjeta
     * @return the boolean
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    private Boolean errorDiferenteSinConsumos(TarjetaEntity tarjeta) throws ParseadorVisaException {
        String codigoErrorTarjeta = obtenerCodigoError(tarjeta);
        if (codigoErrorTarjeta == null || CODIGO_ERROR_CONSUMOS.equals(codigoErrorTarjeta)) {
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
    public String obtenerCodigoError(TarjetaEntity tarjeta) throws ParseadorVisaException {
        if (tarjeta == null) {
            throw new ParseadorVisaException();
        }
        if (tarjeta.getError() != null && tarjeta.getError().getCodigo() == null) {
            throw new ParseadorVisaException();
        }
        if (tarjeta.getError() != null) {
            return tarjeta.getError().getCodigo();
        }
        return null;
    }

    /**
     * Procesar ultimos movimientos de la tarjeta activa. En caso de no poder
     * procesar un consumo, retorna BusinessException.
     *
     * @param retorno
     *            the retorno
     * @param cuenta
     *            the cuenta
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    private List<ConsumoTarjetaDTO> procesarUltimosMovimientos(RetornoTarjetasEntity retorno, Cuenta cuenta,
            TarjetaEntity tarjetaEntity) throws BusinessException {

        List<ConsumoTarjetaDTO> listaConsumoTarjetaDTO = new ArrayList<ConsumoTarjetaDTO>();
        List<TarjetaUltimosConsumosEntity> listaConsumos = parseador.obtenerMovimientosDeUnaTarjeta(tarjetaEntity);
        String tarjetaActivaPPal = parseador.obtenerNumeroTarjetaActiva(tarjetaEntity);
        ConsumoTarjetaDTO movimientosPagos = obtenerPagos(listaConsumos, retorno, cuenta);

        for (TarjetaUltimosConsumosEntity consumoEntity : listaConsumos) {
            String codigoTarjeta = parseador.obtenerCodigoTarjeta(consumoEntity);
            if (!esPago(codigoTarjeta)) {
                ConsumoTarjetaDTO movimiento = procesarMovimiento(consumoEntity, cuenta, retorno, codigoTarjeta);

                if (debeAgregarPagos(codigoTarjeta, tarjetaActivaPPal, movimientosPagos)) {
                    agregarPagosYMovimientosDeCredito(movimientosPagos, movimiento);
                    movimiento.setPrioridad(movimiento.getPrioridad() - 1);
                }
                listaConsumoTarjetaDTO.add(movimiento);
            }
        }

        if (listaConsumoTarjetaDTO.isEmpty()) {
            if (movimientosPagos != null) {
                LOGGER.info("La tarjeta {} no tiene movimientos pero si tiene pagos.", tarjetaActivaPPal);
                String marca = this.obtenerMarcaDeTarjeta(cuenta);
                movimientosPagos.setMarca(marca);
                movimientosPagos.setNumero(TarjetaBOUtils.formatearNumeroTarjeta(tarjetaActivaPPal, marca));
                movimientosPagos.setIsAdicional(parseador.esCategoriaAdicional(tarjetaEntity));
                movimientosPagos.setIsTitular(parseador.esCategoriaTitular(tarjetaEntity));
                if (movimientosPagos.getIsAdicional()) {
                    movimientosPagos.setNombreAdicional(
                            this.obtenerNombreFormateado(parseador.obtenerNombre(tarjetaEntity)));
                    movimientosPagos.setFechaDesde(
                            TarjetaBOUtils.parsearFechaConAnio(parseador.obtenerFechaDesde(tarjetaEntity)));
                }
                listaConsumoTarjetaDTO.add(movimientosPagos);
            } else {
                LOGGER.error("La tarjeta {} no tiene movimientos ni pagos.", tarjetaActivaPPal);
                throw new BusinessException();
            }
        }
        return listaConsumoTarjetaDTO;

    }

    /**
     * Obtener pagos.
     *
     * @param listaConsumos
     *            the lista consumos
     * @param retorno
     *            the retorno
     * @param cuenta
     *            the cuenta
     * @return the consumo tarjeta DTO
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    private ConsumoTarjetaDTO obtenerPagos(List<TarjetaUltimosConsumosEntity> listaConsumos,
            RetornoTarjetasEntity retorno, Cuenta cuenta) throws TarjetaBOUtilsException {
        ConsumoTarjetaDTO movimientosPagos = null;
        for (TarjetaUltimosConsumosEntity consumoEntity : listaConsumos) {
            String codigoTarjeta = parseador.obtenerCodigoTarjeta(consumoEntity);
            if (esPago(codigoTarjeta)) {
                ConsumoTarjetaDTO movimiento = procesarMovimiento(consumoEntity, cuenta, retorno, codigoTarjeta);
                if (movimiento.getNumero() == null) {
                    movimientosPagos = movimiento;
                }
                break;
            }
        }
        return movimientosPagos;
    }

    /**
     * Procesar movimiento.
     *
     * @param consumoEntity
     *            the consumo entity
     * @param cuenta
     *            the cuenta
     * @param retorno
     *            the retorno
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @return the consumo tarjeta DTO
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    private ConsumoTarjetaDTO procesarMovimiento(TarjetaUltimosConsumosEntity consumoEntity, Cuenta cuenta,
            RetornoTarjetasEntity retorno, String codigoTarjeta) throws TarjetaBOUtilsException {

        ConsumoTarjetaDTO dto = crearDTOYCargarCamposComunes(consumoEntity, cuenta, codigoTarjeta);

        if (!esPago(codigoTarjeta)) {
            TarjetaEntity tarjetaEntity = parseador.obtenerPorNumeroDeTarjetaActiva(retorno, codigoTarjeta);
            String marca = this.obtenerMarcaDeTarjeta(cuenta);
            dto.setMarca(marca);
            dto.setNumero(TarjetaBOUtils.formatearNumeroTarjeta(codigoTarjeta, marca));

            if (tarjetaEntity == null) {
                dto.setPrioridad(3);
            } else {
                dto.setIsAdicional(parseador.esCategoriaAdicional(tarjetaEntity));
                dto.setIsTitular(parseador.esCategoriaTitular(tarjetaEntity));
                if (dto.getIsAdicional()) {
                    dto.setNombreAdicional(this.obtenerNombreFormateado(parseador.obtenerNombre(tarjetaEntity)));
                    dto.setFechaDesde(TarjetaBOUtils.parsearFechaConAnio(parseador.obtenerFechaDesde(tarjetaEntity)));
                    dto.setPrioridad(2);
                }
            }
        }
        return dto;
    }

    /**
     * Es pago.
     *
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @return true, if successful
     */
    private boolean esPago(String codigoTarjeta) {
        return codigoTarjeta.equals(BLANCO) || codigoTarjeta.equals(CODIGO_TARJETA_TI);
    }

    /**
     * Crear DTOY cargar campos comunes.
     *
     * @param consumoEntity
     *            the consumo entity
     * @param cuenta
     *            the cuenta
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @return the consumo tarjeta DTO
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    private ConsumoTarjetaDTO crearDTOYCargarCamposComunes(TarjetaUltimosConsumosEntity consumoEntity, Cuenta cuenta,
            String codigoTarjeta) throws TarjetaBOUtilsException {
        ConsumoTarjetaDTO dto = new ConsumoTarjetaDTO();
        dto.setIsAdicional(Boolean.FALSE);
        dto.setIsTitular(Boolean.FALSE);
        dto.setNombreAdicional(BLANCO);
        dto.setPrioridad(1);
        dto.setFechaDesde(TarjetaBOUtils.parsearFechaConAnio(FECHA_ANTIGUA));
        String consumoDolares = TarjetaBOUtils.formatearSaldoSinAbs(parseador.obtenerConsumoDolares(consumoEntity));
        String consumoPesos = TarjetaBOUtils.formatearSaldoSinAbs(parseador.obtenerConsumoPesos(consumoEntity));
        dto.setConsumoDolares(ISBANStringUtils.formatearSaldoString(consumoDolares));
        dto.setConsumoPesos(ISBANStringUtils.formatearSaldoString(consumoPesos));
        dto.setHasConsumoDolaresCero(TarjetaBOUtils.CERO_FLOTANTE.equals(consumoDolares));
        dto.setHasConsumoPesosCero(TarjetaBOUtils.CERO_FLOTANTE.equals(consumoPesos));
        dto.setLineas(obtenerLineas(consumoEntity, codigoTarjeta, cuenta, esRecargable(cuenta)));
        // String consumoCero = TarjetaBOUtils.formatearSaldoSinAbs(new
        // BigDecimal(0));
        return dto;
    }

	/**
	 * Obtener lineas titular.
	 *
	 * @param entity
	 *            the entity
	 * @param numeroTarjetaActiva
	 *            the numero tarjeta activa
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private List<LineaDetalleConsumoTarjetaDTO> obtenerLineas(TarjetaUltimosConsumosEntity entity,
            String numeroTarjetaActiva, Cuenta cuenta, Boolean esRecargable) throws ParseadorVisaException {
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        List<MovimientoEntity> movimientos = parseador.obtenerListaMovimientos(entity);
        for (MovimientoEntity movimientoEntity : movimientos) {
            lineas.add(obtenerLineaDetalle(movimientoEntity, numeroTarjetaActiva, cuenta, esRecargable));
            if (movimientoEntity.getDetallePromocion() != null && StringUtils.isNotBlank(movimientoEntity.getDetallePromocion().getDescuentoUsuario())) {
            	lineas.add(obtenerLineaDetallePromocion(movimientoEntity, numeroTarjetaActiva, cuenta));
            }
        }
        ordenarLineasDTOPorFecha(lineas);
        return lineas;
    }

    /**
	 * Obtener linea detalle.
	 *
	 * @param entity
	 *            the entity
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param cuenta
	 *            the cuenta
	 * @return the linea detalle consumo tarjeta
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private LineaDetalleConsumoTarjetaDTO obtenerLineaDetalle(MovimientoEntity entity, String nroTarjeta, Cuenta cuenta, Boolean esRecargable)
            throws ParseadorVisaException {
        LineaDetalleConsumoTarjetaDTO lineaDTO = new LineaDetalleConsumoTarjetaDTO();
        lineaDTO.setNroTarjeta(nroTarjeta);
        if (StringUtils.isNotBlank(nroTarjeta)){
        	lineaDTO.setNroTarjetaMascara(TarjetaBOUtils.formatearNumeroTarjeta(nroTarjeta, TarjetaUtils.getMarca(cuenta)));
        }
        String descripcion = parseador.obtenerDescripcion(entity);
        lineaDTO.setTieneCuota(tieneRegexpCuotas(descripcion));
        if (lineaDTO.getTieneCuota()) {
            lineaDTO.setCuota(StringUtils.right(descripcion, TarjetaUtils.CINCO_ENTERO));
            lineaDTO.setCuotasCanceladas(Integer.valueOf(StringUtils.substringBeforeLast(lineaDTO.getCuota(), "/")));
            lineaDTO.setCuotasTotales(Integer.valueOf(StringUtils.substringAfterLast(lineaDTO.getCuota(), "/")));
            descripcion = StringUtils.remove(descripcion, lineaDTO.getCuota());
        }
        lineaDTO.setDescripcion(obtenerDescripcionFormada(descripcion));

        String importe = parseador.obtenerImporteValor(entity);
        if (parseador.esImportePesos(entity)) {
            lineaDTO.setImportePesos(ISBANStringUtils.formatearSaldoString(importe));
            lineaDTO.setConsumoPesos(true);
            lineaDTO.setConsumoDolares(false);
        } else {
            lineaDTO.setImporteDolares(ISBANStringUtils.formatearSaldoString(importe));
            lineaDTO.setConsumoPesos(false);
            lineaDTO.setConsumoDolares(true);
        }
        lineaDTO.setFecha(ISBANStringUtils.formatearFecha(parseador.obtenerFecha(entity)));
        lineaDTO.setCodigoEstablecimiento(parseador.obtenerEstablecimientoCodigo(entity));
        lineaDTO.setNroReferencia(parseador.obtenerTicket(entity));
        lineaDTO.setTipoConsumo(obtenerTipoConsumo(nroTarjeta));
        invertirSignoConsumoSiEsRecargable(esRecargable, lineaDTO);
        return lineaDTO;
    }

	/**
     * Obtener linea detalle promocion.
     *
     * @param entity the entity
     * @param nroTarjeta the nro tarjeta
     * @param cuenta the cuenta
     * @return the linea detalle consumo tarjeta DTO
     * @throws ParseadorVisaException the parseador visa exception
     */
    private LineaDetalleConsumoTarjetaDTO obtenerLineaDetallePromocion(MovimientoEntity entity, String nroTarjeta, Cuenta cuenta)
            throws ParseadorVisaException {
        LineaDetalleConsumoTarjetaDTO lineaDTO = new LineaDetalleConsumoTarjetaDTO();
        lineaDTO.setNroTarjeta(nroTarjeta);
        if (StringUtils.isNotBlank(nroTarjeta)) {
        	lineaDTO.setNroTarjetaMascara(TarjetaBOUtils.formatearNumeroTarjeta(nroTarjeta, TarjetaUtils.getMarca(cuenta)));
        }
        String descripcion = parseador.obtenerDescripcion(entity);
        lineaDTO.setTieneCuota(false); 
        if (tieneRegexpCuotas(descripcion)) {
        	String cuota = StringUtils.right(descripcion, TarjetaUtils.CINCO_ENTERO);
            descripcion = StringUtils.remove(descripcion, cuota);
        }
        lineaDTO.setDescripcion(PREFIJO_BONIFICACION + obtenerDescripcionFormada(descripcion));

        DetallePromocionEntity detallePromocion = entity.getDetallePromocion();
        if (detallePromocion != null) {
	        String importe = "-" + detallePromocion.getDescuentoUsuario();
	        if (parseador.esImportePesos(entity)) {
	            lineaDTO.setImportePesos(new BigDecimal(importe));
	            lineaDTO.setConsumoPesos(true);
	            lineaDTO.setConsumoDolares(false);
	        } else {
	            lineaDTO.setImporteDolares(new BigDecimal(importe));
	            lineaDTO.setConsumoPesos(false);
	            lineaDTO.setConsumoDolares(true);
	        }
        }
        lineaDTO.setFecha(ISBANStringUtils.formatearFecha(parseador.obtenerFecha(entity)));
        lineaDTO.setCodigoEstablecimiento(parseador.obtenerEstablecimientoCodigo(entity));
        lineaDTO.setNroReferencia(parseador.obtenerTicket(entity));
        lineaDTO.setTipoConsumo(obtenerTipoConsumo(nroTarjeta));

        return lineaDTO;
    }
    
    /**
     * Obtener tipo consumo.
     *
     * @param nroTarjeta
     *            the nro tarjeta
     * @return the tipo consumo tarjeta
     */
    private TipoConsumoTarjeta obtenerTipoConsumo(String nroTarjeta) {
        if (esPago(nroTarjeta)) {
            return TipoConsumoTarjeta.PAGO;
        }
        return TipoConsumoTarjeta.ULTIMO_CONSUMO;
    }

    /**
     * Obtener nombre formateado.
     *
     * @param nombreAD
     *            the nombre AD
     * @return the string
     */
    private String obtenerNombreFormateado(String nombreAD) {
        if (StringUtils.isNotEmpty(nombreAD)) {
            String nombreApellido = nombreAD;
            String nombre = StringUtils.substringAfter(nombreApellido, "/");
            String apellido = StringUtils.substringBefore(nombreApellido, "/");
            String nombreCompleto = nombre + TarjetaBOUtils.ESPACIO_BLANCO + apellido;
            return WordUtils.capitalizeFully(nombreCompleto);
        }
        return null;
    }

    /**
     * Convierte a camelcase la descripcion.
     *
     * @param descripcion
     *            the descripcion
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    private String obtenerDescripcionFormada(String descripcion) throws ParseadorVisaException {
        if (descripcion.contains(".COM") || StringUtils.startsWith(descripcion, "WWW.")) {
            return StringUtils.lowerCase(descripcion.trim());
        }
        return ISBANStringUtils.convertirStringToCamelcase(descripcion.trim());
    }

    /**
     * Debe agregar pagos.
     *
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @param tarjetaActivaPPal
     *            the tarjeta activa P pal
     * @param movimientosPagos
     *            the movimientos pagos
     * @return true, if successful
     */
    private boolean debeAgregarPagos(String codigoTarjeta, String tarjetaActivaPPal,
            ConsumoTarjetaDTO movimientosPagos) {
        return codigoTarjeta.equals(tarjetaActivaPPal) && movimientosPagos != null;
    }

    /**
     * Agregar pagos Y movimientos de credito.
     *
     * @param pagosYMovimientosCredito
     *            the pagos Y movimientos credito
     * @param consumoDTOPPal
     *            the consumo DTOP pal
     * @throws BusinessException
     *             the business exception
     */
    private void agregarPagosYMovimientosDeCredito(ConsumoTarjetaDTO pagosYMovimientosCredito,
            ConsumoTarjetaDTO consumoDTOPPal) throws BusinessException {
        if (consumoDTOPPal != null && !pagosYMovimientosCredito.getLineas().isEmpty()) {
            for (LineaDetalleConsumoTarjetaDTO linea : consumoDTOPPal.getLineas()) {
                pagosYMovimientosCredito.getLineas().add(linea);
            }
            consumoDTOPPal.setLineas(pagosYMovimientosCredito.getLineas());
        }
    }

    /**
     * Ordenar lineas por fecha.
     *
     * @param lineas
     *            the lineas
     */
    private void ordenarLineasDTOPorFecha(List<LineaDetalleConsumoTarjetaDTO> lineas) {
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
     * Ordenar comprobantes.
     *
     * @param consumos
     *            the comprobantes
     */
    private void ordenarConsumosPorTitularidad(List<ConsumoTarjetaDTO> consumos) {
        Collections.sort(consumos, new Comparator<ConsumoTarjetaDTO>() {
            @Override
            public int compare(ConsumoTarjetaDTO consumo1, ConsumoTarjetaDTO consumo2) {
                int i = consumo1.getPrioridad().compareTo(consumo2.getPrioridad());
                if (i != 0) {
                    return i;
                }
                return consumo1.getPrioridad().compareTo(consumo2.getPrioridad());
            }
        });
    }

    /**
     * Generar respuesta.
     *
     * @param consumos
     *            the consumos
     * @return the respuesta
     */
    private Respuesta<UltimosConsumosDTO> generarRespuesta(List<ConsumoTarjetaDTO> consumos) {
        if (consumos.isEmpty()) {
            return casuistica.crearRespuestaError(ErrorTarjetasEnum.SIN_CONSUMOS);
        }
        return this.getRespuestaFactory().crearRespuestaOk(UltimosConsumosDTO.class, new UltimosConsumosDTO(consumos));
    }

    /**
     * Armar respuesta con ERROR.
     *
     * @return the respuesta
     */
    private Respuesta<UltimosConsumosDTO> crearRespuestaError() {
        return casuistica.crearRespuestaError(ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS);
    }

}
