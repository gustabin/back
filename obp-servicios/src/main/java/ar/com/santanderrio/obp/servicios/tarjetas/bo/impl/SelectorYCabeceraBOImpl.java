package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.LimitesYDisponiblesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.CreditCardsServiceConnector;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.exception.ConnectorException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers.CreditCardsLimitsObpMapper;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.OwnerDataDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.LimitsAndTotalsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LimitesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSResumenCuentaImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class TarjetaBoImpl.
 */
@Component
public class SelectorYCabeceraBOImpl extends TarjetasBOImpl implements SelectorYCabeceraBO {

    private static final String TIPO_CUENTA_TARJETA_RECARGABLE = "77";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectorYCabeceraBOImpl.class);

    /** The Constant ERROR_CODE_SIN_CONSUMOS. */
    private static final String LOGGER_ERROR = "Error en calcular los resumenes de las tarjetas";

    private static final String CERO = "0,00";

    /** The validacion tarjeta. */
    @Autowired
    private LimitesYDisponiblesBO limitesYDisponiblesBO;

    /** The mensajes tarjeta. */
    @Autowired
    private ManejoDeMensajesTarjeta mensajesTarjeta;

	/** The parseador. */
    @Autowired
    private ParseadorWSResumenCuentaImpl parseador;
    
    /** The modulo permiso BO. */
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;

    @Autowired
    private CreditCardsServiceConnector creditCardServiceConnector;

    @Autowired
    private CreditCardsLimitsObpMapper creditCardMapper;


    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO#
     * obtenerTooltipFavorito()
     */
    @Override
    public String obtenerTooltipFavorito() {
        return mensajesTarjeta.obtenerTooltipFavorito();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO#
     * obtenerTooltipNoFavorito()
     */
    @Override
    public String obtenerTooltipNoFavorito() {
        return mensajesTarjeta.obtenerTooltipNoFavorito();
    }

    /**
     * Obtiene la lista de tarjetas con los datos de resumen cuenta y posicion
     * consolidada.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    @Override
    public Respuesta<DisponiblesYConsumoDTO> obtenerTarjetas(Cliente cliente) {
        try {
            LOGGER.info("[Tarjetas] Productos del Cliente: {}. {}.", cliente, cliente.getCuentas());
            List<Cuenta> productos = TarjetaBOUtils.filtrarCuentasDeTipoCuentaTarjeta(cliente.getCuentas());
            List<Cuenta> productosVisa = TarjetaBOUtils.filtrarVisasYRecargables(productos);
            productosVisa.addAll(TarjetaBOUtils.filtrarRecargables(productos));
            List<Cuenta> productosAmex = TarjetaBOUtils.filtrarAmexs(productos);
            List<Cuenta> productosMasterCard = TarjetaBOUtils.filtrarMastercard(productos);

            if (!productosVisa.isEmpty() || !productosAmex.isEmpty() || !productosMasterCard.isEmpty()) {
                return crearRespuesta(generarListaResumenDTO(productosVisa, productosAmex, productosMasterCard));
            }

            return crearRespuestaSinTarjetas();

        } catch (BusinessException e) {
            LOGGER.error(LOGGER_ERROR, e);
            return crearRespuestaErrorYEncabezado();
        } catch (NullPointerException e) {
            LOGGER.error(LOGGER_ERROR, e);
            return crearRespuestaErrorYEncabezado();
        }
    }

    /**
     * Genera los resumenes dto de cada producto Visa y Amex.
     *
     * @param productosVisa
     *            the productos visa
     * @param productosAmex
     *            the productos amex
	 * @param productosMasterCard
	 *            the productos master card
     * @return the list
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     * @throws BusinessException
     *             the business exception
     */
    public List<ResumenTarjetaDTO> generarListaResumenDTO(List<Cuenta> productosVisa, List<Cuenta> productosAmex,
            List<Cuenta> productosMasterCard) throws TarjetaBOUtilsException, BusinessException {

        List<ResumenTarjetaDTO> listaResumenDTO = new ArrayList<ResumenTarjetaDTO>();

        if (!productosVisa.isEmpty()) {
            listaResumenDTO.addAll(this.generarResumenesParaVisas(productosVisa));
        }

        if (!productosAmex.isEmpty()) {
            listaResumenDTO.addAll(this.generarResumenesParaAmexs(productosAmex));
        }

        if (!productosMasterCard.isEmpty()) {
            listaResumenDTO.addAll(this.generarResumenesParaMastercard(productosMasterCard));
        }

        return ordenarListadoPorCriterioYFechaDeCreacion(listaResumenDTO);
    }

    /**
     * Generar resumenes para productos Visa.
     *
     * @param productosVisa
     *            the productos visa
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    private List<ResumenTarjetaDTO> generarResumenesParaVisas(List<Cuenta> productosVisa) throws BusinessException {
        List<ResumenTarjetaDTO> resumenes = generarListaDTOConDisponibles(productosVisa);
        if (resumenes.isEmpty()) {
            throw new BusinessException();
        }
        LOGGER.info("[Tarjetas] Obteniendo Resumenes Visa {}.", resumenes);
        return resumenes;
    }

    /**
     * Generar resumenes para productos Mastercard.
     *
	 * @param productosMastercard
	 *            the productos mastercard
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    private List<ResumenTarjetaDTO> generarResumenesParaMastercard(List<Cuenta> productosMastercard)
            throws BusinessException {
        List<ResumenTarjetaDTO> resumenes = generarListaDTOConDisponibles(productosMastercard);
        if (resumenes.isEmpty()) {
            throw new BusinessException();
        }
        LOGGER.info("[Tarjetas] Obteniendo Resumenes Visa {}.", resumenes);
        return resumenes;
    }

    /**
     * Generar resumenes para productos Amexs.
     *
     * @param productosAmex
     *            the productos amex
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    private List<ResumenTarjetaDTO> generarResumenesParaAmexs(List<Cuenta> productosAmex) throws BusinessException {
        List<ResumenTarjetaDTO> resumenes = generarListaDTOConDisponibles(productosAmex);
        if (resumenes.isEmpty()) {
            throw new BusinessException();
        }
        LOGGER.info("[Tarjetas] Obteniendo Resumenes Visa {}.", resumenes);
        return resumenes;
    }

    /**
     * Ordenar listado por criterio Y fecha de creacion. [ORDENAR POR CRITERIO]
     * Obtiene las tarjetas ordenadas segun el criterio de Ordenacion y fechas
     * de creacion para las adicionales y recargables: 1)Visa titular 2)
     * American express titular 3) Visa adicionales (con orden de fecha) 4)
     * American Express adicionales (con orden de fecha) 5) Visa Recargables
     * (con orden de fecha)
     *
     * @param listaResumenDTO
     *            the lista resumen DTO
     * @return the list
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    private List<ResumenTarjetaDTO> ordenarListadoPorCriterioYFechaDeCreacion(List<ResumenTarjetaDTO> listaResumenDTO)
            throws TarjetaBOUtilsException {
        List<ResumenTarjetaDTO> tarjetasOrdenadas = new ArrayList<ResumenTarjetaDTO>();
        tarjetasOrdenadas.addAll(TarjetaBOUtils.obtenerTarjetasVisaTitulares(listaResumenDTO));
        tarjetasOrdenadas.addAll(TarjetaBOUtils.obtenerTarjetasAmexTitulares(listaResumenDTO));
        tarjetasOrdenadas.addAll(TarjetaBOUtils.obtenerTarjetasMastercardTitulares(listaResumenDTO));
        tarjetasOrdenadas.addAll(TarjetaBOUtils.obtenerVisaAdicionalOrdenadasPorFecha(listaResumenDTO));
        tarjetasOrdenadas.addAll(TarjetaBOUtils.obtenerAmexAdicionalOrdenadasPorFecha(listaResumenDTO));
        tarjetasOrdenadas.addAll(TarjetaBOUtils.obtenerTarjetasMastercardAdicionales(listaResumenDTO));
        tarjetasOrdenadas.addAll(TarjetaBOUtils.obtenerVisaRecargableOrdenadasPorFecha(listaResumenDTO));
        return tarjetasOrdenadas;
    }

    /**
	 * Generar lista DTO con disponibles.
	 *
     * @param cuentas
	 *            the cuentas
	 * @return the list
     */
    private List<ResumenTarjetaDTO> generarListaDTOConDisponibles(List<Cuenta> cuentas) {
        RetornoTarjetasEntity entity = null;
        try {
            LOGGER.info("[Tarjetas] Obteniendo Disponibles de Productos {}.", cuentas);
            for (Cuenta cuenta : cuentas) {
				if(cuenta.esEstadoTarjetaCredito() && TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad().trim())) {
                    try {
                        entity = this.agregarARetornoTarjetasEntity(entity, this.callCreditCardLimitsAndTotalsService(cuenta));
                    } catch (Exception e) {
                        LOGGER.error(LOGGER_ERROR, e);
                    }
				}
			}

            /* Armó una lista de tarjetas */
            if (entity != null) LOGGER.info("TITULAR entity {}.", entity.toString());
            for (Cuenta cuenta : cuentas) {
                if(cuenta.esEstadoTarjetaCredito() && TIPO_CUENTA_TARJETA_RECARGABLE.equals(cuenta.getTipoCuenta()) && !existeEnLimitesYTotales(cuenta, entity)){
                	entity = this.agregarTarjetaRecargableAdicional(entity, cuenta,
                            OperacionTarjetaWSEnum.LIMITESYTOTALES);
                }
            }
            if (entity != null) LOGGER.info("TITULAR + RECARGABLE entity {}.", entity.toString());
            return obtenerDisponiblesDesdeRetornoWS(cuentas, entity);
        } catch (Exception e) {
            LOGGER.error(LOGGER_ERROR, e);
        }
        return crearListaDTOSinDisponibles(cuentas);
    }

    private RetornoTarjetasEntity agregarARetornoTarjetasEntity(RetornoTarjetasEntity entity, RetornoTarjetasEntity newEntity) {
    	if (entity == null) {
    		return newEntity;
    	}
    	try {
	    	entity.getTarjetas().getTarjetaList().addAll(newEntity.getTarjetas().getTarjetaList());
	    	entity.setError(entity.getError() && newEntity.getError());
	    	return entity;
    	} catch (Exception e) {
            LOGGER.error("Error agregando nuevo retorno entity a entity original. Retorna nuevo entity.", e);
            return newEntity;
        }
    }
    
    private RetornoTarjetasEntity callCreditCardLimitsAndTotalsService(Cuenta cuenta) throws ConnectorException {
        String cardId = creditCardServiceConnector.getCreditCardId(cuenta.getNroTarjetaCredito());
        LimitsAndTotalsDto limitsAndTotalsDtoV2 = creditCardServiceConnector.getCreditCardLimits(cardId);
        limitsAndTotalsDtoV2.setOwnerData(fillOwnerData(cuenta));
        return creditCardMapper.mapToRetornoTarjetasEntity(limitsAndTotalsDtoV2);
    }

    private OwnerDataDto fillOwnerData(Cuenta cuenta) {
        OwnerDataDto ownerDataDto = new OwnerDataDto();
        ownerDataDto.setActiveCardNumber(Long.parseLong(cuenta.getNroTarjetaCredito()));
        ownerDataDto.setCategory(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCalidadDeParticipacion()) ? TarjetaUtils.CATEGORIA_TITULAR : TarjetaUtils.CATEGORIA_ADICIONAL);
        ownerDataDto.setOwnerName(null);
        ownerDataDto.setCardHolderName(null);
        ownerDataDto.setDateSince(null);
        return ownerDataDto;
    }

    /**
     * Agrego tarjetas recargables adicionales no devueltas en consulta de visa titular.
     *  
     * @param entity original
     *            cuentas
     * @return the list
     */
	private RetornoTarjetasEntity  agregarTarjetaRecargableAdicional(RetornoTarjetasEntity entity, Cuenta cuenta,
            OperacionTarjetaWSEnum limitesytotales) {

        try {
        	RetornoTarjetasEntity entityRecargable = this.obtenerRespuestaVisa(cuenta,
                    OperacionTarjetaWSEnum.LIMITESYTOTALES);
            if(entity == null) {
                return entityRecargable;
            }
            entity.getTarjetas().getTarjetaList().addAll(entityRecargable.getTarjetas().getTarjetaList());
            entity.setError(entity.getError() && entityRecargable.getError());
        } catch (DAOException e) {
            LOGGER.error(LOGGER_ERROR, e);
        }
        return entity;
    }

    private boolean existeEnLimitesYTotales(Cuenta cuenta, RetornoTarjetasEntity tarjetasEntity) {
        String nroTarjetaCuenta = cuenta.getNroTarjetaCredito()!= null ? ISBANStringUtils.sacarCerosYBlancosIzq(cuenta.getNroTarjetaCredito()):"";
        if(tarjetasEntity == null){
            return false;
        }
	    for (TarjetaEntity tarjeta : tarjetasEntity.getTarjetas().getTarjetaList()) {
	        String nroTarjetaEntity = StringUtils.EMPTY;
	        if((tarjeta.getTarjetaDocument() != null 
	                && tarjeta.getTarjetaDocument().getDatos()!= null) 
	                && tarjeta.getTarjetaDocument().getDatos().getTarjetaActiva()!= null) {
	            nroTarjetaEntity = ISBANStringUtils.sacarCerosYBlancosIzq(tarjeta.getTarjetaDocument().getDatos().getTarjetaActiva());
	        }
	        if(nroTarjetaEntity.equals(nroTarjetaCuenta)) {
	           return true;
	        }
	    }
        return false;
    }

    /**
	 * Obtener saldo cuenta.
	 *
	 * @param retornoTarjetasEntity
	 *            the retorno tarjetas entity
	 * @param tipoSaldo
	 *            the tipo saldo
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
    public BigDecimal obtenerSaldoCuenta(RetornoTarjetasEntity retornoTarjetasEntity, String tipoSaldo)
            throws ParseadorVisaException, TarjetaBOUtilsException {
        BigDecimal r1 = null;
        for (TarjetaEntity tarjeta : retornoTarjetasEntity.getTarjetas().getTarjetaList()) {
            String saldo = obtenerPesos(tarjeta, tipoSaldo);
            if (saldo != null) {
                r1 = TarjetaBOUtils.convertirSaldo(saldo);
                break;
            }
        }
        return r1;
    }

	/**
	 * Obtener consumo dolares.
	 *
	 * @param retornoTarjetasEntity
	 *            the retorno tarjetas entity
	 * @param tipoSaldo
	 *            the tipo saldo
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
    public BigDecimal obtenerConsumoDolares(RetornoTarjetasEntity retornoTarjetasEntity, String tipoSaldo)
            throws ParseadorVisaException, TarjetaBOUtilsException {
        BigDecimal r1 = null;

        for (TarjetaEntity tarjeta : retornoTarjetasEntity.getTarjetas().getTarjetaList()) {
            String saldo = obtenerDolares(tarjeta, tipoSaldo);
            if (saldo != null) {
                r1 = TarjetaBOUtils.convertirSaldo(saldo);
                break;
            }
        }
        return r1;
    }

	/**
	 * Obtener pesos.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param tipoSaldo
	 *            the tipo saldo
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    public String obtenerPesos(TarjetaEntity tarjeta, String tipoSaldo) throws ParseadorVisaException {
        if (tarjeta == null) {
            throw new ParseadorVisaException();
        }
        if (tarjeta.getTarjetaDocument().getDatosCuenta().getTotales().getMovimientos().getPesos() == null) {
            throw new ParseadorVisaException();
        } else {
            return tarjeta.getTarjetaDocument().getDatosCuenta().getTotales().getMovimientos().getPesos();

        }
    }

	/**
	 * Obtener dolares.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param tipoSaldo
	 *            the tipo saldo
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    public String obtenerDolares(TarjetaEntity tarjeta, String tipoSaldo) throws ParseadorVisaException {
        if (tarjeta == null) {
            throw new ParseadorVisaException();
        }
        if (tarjeta.getTarjetaDocument().getDatosCuenta().getTotales().getMovimientos().getDolares() == null) {
            throw new ParseadorVisaException();
        } else {
            return tarjeta.getTarjetaDocument().getDatosCuenta().getTotales().getMovimientos().getDolares();

        }
    }


	/**
	 * Comparar limite.
	 *
	 * @param l
	 *            the l
	 * @return the boolean
	 */
    public Boolean compararLimite(String l) {
        String n2 = l.replace(".", "");
        String n3 = n2.replace(",", ".");

        BigDecimal n = new BigDecimal(n3);
        return n.compareTo(TarjetaUtils.LIMITE_ADELANTO_VALOR) < 0;
    }

    /**
     * Generar resumenes desde la respuesta.
     *
     * @param cuentas
     *            the cuentas
     * @param entity
     *            the entity
     * @return the list
     */
    private List<ResumenTarjetaDTO> obtenerDisponiblesDesdeRetornoWS(List<Cuenta> cuentas,
            RetornoTarjetasEntity entity) {

        try {
            List<ResumenTarjetaDTO> resumenes = new ArrayList<ResumenTarjetaDTO>();
            LOGGER.info("tieneErrorDeCredenciales: {}", parseador.tieneErrorDeCredenciales(entity));
            if (!parseador.tieneErrorDeCredenciales(entity)) {
                for (Cuenta cuenta : cuentas) {
                    resumenes.add(generarResumenConDisponibles(cuenta, entity));
                    
                }
                return resumenes;
            }
            return crearListaDTOSinDisponibles(cuentas);
        } catch (ParseadorVisaException e) {
            LOGGER.error(LOGGER_ERROR, e);
            return crearListaDTOSinDisponibles(cuentas);
        }

    }

    /**
     * Generar resumen con disponibles.
     *
     * @param cuenta
     *            the cuenta
     * @param retorno
     *            the retorno
     * @return the resumen tarjeta DTO
     */
    private ResumenTarjetaDTO generarResumenConDisponibles(Cuenta cuenta, RetornoTarjetasEntity retorno) {
        try {
            if (retorno != null) LOGGER.info("obtenerPorNumeroDeTarjetaActiva: retorno {}", retorno.toString());
            LOGGER.info("obtenerPorNumeroDeTarjetaActiva: tarjeta {}", cortarNroTarjetaDesdeCuenta(cuenta));
            TarjetaEntity tarjeta = parseador.obtenerPorNumeroDeTarjetaActiva(retorno,
                    cortarNroTarjetaDesdeCuenta(cuenta));
        	boolean esReimprimible = false;
            LOGGER.info("parseador.tarjetaTieneError: tarjeta {}", tarjeta);
            if (!parseador.tarjetaTieneError(tarjeta)) {
            	
            	if (validarSiEsImprimibleTitular(cuenta)) {
            		esReimprimible = true;
				}
            	
                if (TarjetaBOUtils.esTitularCuenta(cuenta) || TIPO_CUENTA_TARJETA_RECARGABLE.equals(cuenta.getTipoCuenta())) {

                    try {
                        LimitesYDisponiblesDTO limitesYDisponiblesDTO = limitesYDisponiblesBO
                                .obtenerLimiteYDisponibleDTO(cuenta, retorno);
                        return new ResumenTarjetaDTO(generarDetalleTarjetaDTOConDisponibles(cuenta, tarjeta, esReimprimible), false,
                                false, null, limitesYDisponiblesDTO);

                    } catch (ParseadorVisaException e) {

                        LOGGER.error(LOGGER_ERROR, e);

                        return consultarTitularDTOError(cuenta);
                    } catch (NullPointerException e) {

                        LOGGER.error(LOGGER_ERROR, e);

                        return consultarTitularDTOError(cuenta);
                    }

                }

                if (TarjetaBOUtils.esAdicionalCuenta(cuenta)) {

                    try {
                        return new ResumenTarjetaDTO(generarDetalleDeTarjetaConFechas(cuenta, tarjeta, esReimprimible), false, false,
                                null, null);

                    } catch (ParseadorVisaException e) {

                        LOGGER.error(LOGGER_ERROR, e);

                        return consultarAdicionalesDTOError(cuenta);
                    } catch (NullPointerException e) {

                        LOGGER.error(LOGGER_ERROR, e);

                        return consultarAdicionalesDTOError(cuenta);
                    }
                }
            }
            return crearResumenTarjetaDTOError(cuenta);
        } catch (ParseadorVisaException e) {
            LOGGER.error(LOGGER_ERROR, e);
            return crearResumenTarjetaDTOError(cuenta);
        } catch (TarjetaBOUtilsException e) {
            LOGGER.error(LOGGER_ERROR, e);
            return crearResumenTarjetaDTOError(cuenta);
        }
    }

    public boolean validarSiEsImprimibleTitular(Cuenta cuenta){
    	if (cuenta.getTipoCuentaEnum().getCodigo().equals(TipoCuenta.VISA.getCodigo()) && (cuenta.getNroOrdenFirmante().equals("001")|| cuenta.getNroOrdenFirmante().equals("002")) && cuenta.getCodigoTitularidad().equals("AD") && cuenta.getSubproductoAltair().equals("PREN")) {
    		return true;
    	 } else {
    	return false;
    	}
    }
    
    /**
     * Genera la lista de resumenes con error en disponibles.
     *
     * @param cuentas
     *            the cuentas
     * @return the list
     */
    private List<ResumenTarjetaDTO> crearListaDTOSinDisponibles(List<Cuenta> cuentas) {
        List<ResumenTarjetaDTO> resumenes = new ArrayList<ResumenTarjetaDTO>();        
        for (Cuenta cuenta : cuentas) {
            resumenes.add(generarResumenSinDisponibles(cuenta));
        }
        return resumenes;
    }

    /**
     * Generar resumen sin disponibles.
     *
     * @param cuenta
     *            the cuenta
     * @return the resumen tarjeta
     */
    private ResumenTarjetaDTO generarResumenSinDisponibles(Cuenta cuenta) {
    	boolean esReimprimible = false;
    	DetalleTarjetaDTO detalle = null;
    	if (validarSiEsImprimibleTitular(cuenta)) {
    		esReimprimible = true;
		}
        if (TarjetaBOUtils.esTitularCuenta(cuenta)) {
        	detalle = generarEncabezadoDeTarjetaConError(cuenta);
        	detalle.setIsReimprimibleTitular(esReimprimible);
            return new ResumenTarjetaDTO(detalle, true, false, TarjetaBOUtils.getCodigoErrorAlConsultarDisponibles(), null);
        }
        detalle = generarEncabezadoDeTarjeta(cuenta);
        detalle.setIsReimprimibleTitular(esReimprimible);
        return new ResumenTarjetaDTO(detalle, false, false, null, null);
    }

    /**
     * Generar resumen tarjeta.
     *
     * @param cuenta
     *            the cuenta
     * @param tarjeta
     *            the tarjeta
     * @return the resumen tarjeta
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    private DetalleTarjetaDTO generarDetalleTarjetaDTOConDisponibles(Cuenta cuenta, TarjetaEntity tarjeta,boolean esReimprimible)
            throws TarjetaBOUtilsException, ParseadorVisaException {

        DetalleTarjetaDTO detalleTarjeta = generarEncabezadoDeTarjeta(cuenta);

        detalleTarjeta.setFechaCierre(TarjetaBOUtils.formatearFecha(obtenerFechaDeCierreFormada(tarjeta)));
        detalleTarjeta.setFechaVencimiento(TarjetaBOUtils.formatearFecha(obtenerFechaDeVencimientoFormada(tarjeta)));
        detalleTarjeta.setFechaDesde(TarjetaBOUtils.formatearFechaCompleta(parseador.obtenerFechaDesde(tarjeta)));

        if (esReimprimible) {
			detalleTarjeta.setIsReimprimibleTitular(true);
		}else{
			detalleTarjeta.setIsReimprimibleTitular(false);
		}
        
        if (parseador.esCategoriaTitular(tarjeta) || TIPO_CUENTA_TARJETA_RECARGABLE.equals(cuenta.getTipoCuenta())) {
            String disponibleCompras = ISBANStringUtils
                    .formatearSaldo(obtenerSaldoTipo(tarjeta, TarjetaUtils.DISPONIBLE_COMPRAS));
            String disponiblesCuotas = ISBANStringUtils
                    .formatearSaldo(obtenerSaldoTipo(tarjeta, TarjetaUtils.DISPONIBLE_CUOTAS));
            Boolean hasLimiteUnificado = tieneLimiteUnificado(tarjeta);
            detalleTarjeta.setSaldoDisponibleCompras(disponibleCompras);
            detalleTarjeta.setSaldoDisponibleCuotas(disponiblesCuotas);
            detalleTarjeta.setHasLimiteUnificado(hasLimiteUnificado);
            detalleTarjeta.setMostrarSaldoEnCompras(true);
            detalleTarjeta.setMostrarSaldoEnCuotas(!hasLimiteUnificado);
            detalleTarjeta.setEsSaldoEnComprasCero(TarjetaBOUtils.CERO_FLOTANTE.equals(disponibleCompras));
            detalleTarjeta.setEsSaldoEnCuotasCero(TarjetaBOUtils.CERO_FLOTANTE.equals(disponiblesCuotas));
            actualizarResumenTarjetaConConsumos(detalleTarjeta, tarjeta);
        } else {
            detalleTarjeta.setHasLimiteUnificado(false);
            detalleTarjeta.setMostrarSaldoEnCompras(false);
            detalleTarjeta.setMostrarSaldoEnCuotas(false);
            detalleTarjeta.setEsSaldoEnComprasCero(null);
            detalleTarjeta.setEsSaldoEnCuotasCero(null);
        }
        detalleTarjeta.setError(false);
        return detalleTarjeta;
    }

	/**
	 * Actualizar resumen tarjeta con consumos.
	 *
	 * @param resumenTarjeta
	 *            the resumen tarjeta
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
    private void actualizarResumenTarjetaConConsumos(DetalleTarjetaDTO resumenTarjeta, TarjetaEntity tarjetaEntity)
            throws TarjetaBOUtilsException {

        String consumoPesos = TarjetaBOUtils.formatearSaldoSinAbs(obtenerConsumosPesos(tarjetaEntity));
        String consumoDolar = TarjetaBOUtils.formatearSaldoSinAbs(obtenerConsumosDolares(tarjetaEntity));
        resumenTarjeta.setConsumoPesos(consumoPesos);
        resumenTarjeta.setConsumoDolares(consumoDolar);
        resumenTarjeta.setEsConsumoPesosCero(TarjetaBOUtils.CERO_FLOTANTE.equals(consumoPesos));
        resumenTarjeta.setEsConsumoDolarCero(TarjetaBOUtils.CERO_FLOTANTE.equals(consumoDolar));
    }

	/**
	 * Obtener consumos pesos.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private BigDecimal obtenerConsumosPesos(TarjetaEntity tarjetaEntity) throws ParseadorVisaException {

        try {
            return TarjetaBOUtils.convertirSaldo(
                    tarjetaEntity.getTarjetaDocument().getDatosCuenta().getTotales().getMovimientos().getPesos());
        } catch (TarjetaBOUtilsException e) {
            throw new ParseadorVisaException();
        }
    }

	/**
	 * Obtener consumos dolares.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private BigDecimal obtenerConsumosDolares(TarjetaEntity tarjetaEntity) throws ParseadorVisaException {
        try {
            return TarjetaBOUtils.convertirSaldo(
                    tarjetaEntity.getTarjetaDocument().getDatosCuenta().getTotales().getMovimientos().getDolares());
        } catch (TarjetaBOUtilsException e) {
            throw new ParseadorVisaException();
        }
    }

	/**
	 * Tiene limite unificado.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private Boolean tieneLimiteUnificado(TarjetaEntity tarjeta) throws ParseadorVisaException {
        return parseador.esCategoriaTitular(tarjeta)
                && TarjetaUtils.LIMITE_UNIFICADO.equals(obtenerLimitesUnificados(tarjeta).trim());
    }

	/**
	 * Obtener limites unificados.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private String obtenerLimitesUnificados(TarjetaEntity tarjeta) throws ParseadorVisaException {

        try {
            return tarjeta.getTarjetaDocument().getDatosCuenta().getLimitesUnificados();
        } catch (Exception e) {
            throw new ParseadorVisaException();
        }
    }

	/**
	 * Obtener saldo tipo.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @param tipoLimite
	 *            the tipo limite
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private BigDecimal obtenerSaldoTipo(TarjetaEntity tarjetaEntity, String tipoLimite) throws ParseadorVisaException {
        try {
            for (LimitesEntity limite : tarjetaEntity.getTarjetaDocument().getDatosCuenta().getLimites().getLimites()) {
                if (tipoLimite.equals(limite.getDescripcion())) {
                    return TarjetaBOUtils.convertirSaldo(limite.getPesos());
                }

            }
        } catch (Exception e) {
            throw new ParseadorVisaException();
        }
        throw new ParseadorVisaException();
    }

	/**
	 * Obtener fecha de vencimiento formada.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private String obtenerFechaDeVencimientoFormada(TarjetaEntity tarjeta) throws ParseadorVisaException {
        try {
            return tarjeta.getTarjetaDocument().getDatosCuenta().getFechaVencimiento();
        } catch (Exception e) {
            throw new ParseadorVisaException();
        }
    }

	/**
	 * Obtener fecha de cierre formada.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    private String obtenerFechaDeCierreFormada(TarjetaEntity tarjeta) throws ParseadorVisaException {
        try {
            return tarjeta.getTarjetaDocument().getDatosCuenta().getFechaCierre();
        } catch (Exception e) {
            throw new ParseadorVisaException();
        }
    }

    /**
     * Generar encabezado de detalle tarjeta.
     *
     * @param cuenta
     *            the cuenta
     * @return the detalle tarjeta
     */
    private DetalleTarjetaDTO generarEncabezadoDeTarjeta(Cuenta cuenta) {
        DetalleTarjetaDTO detalleTarjeta = new DetalleTarjetaDTO();
        detalleTarjeta.setAlias(cuenta.getAlias());
        detalleTarjeta.setIsFavorita(cuenta.getIsFavorita());
        detalleTarjeta.setIsTitular(TarjetaBOUtils.esTitularCuenta(cuenta));
        detalleTarjeta.setIsAdicional(TarjetaBOUtils.esAdicionalCuenta(cuenta));
        detalleTarjeta.setIsRecargable(esVisaRecargable(cuenta));
        detalleTarjeta.setFechaCierre(ISBANStringUtils.inicializarFecha());
        detalleTarjeta.setFechaVencimiento(ISBANStringUtils.inicializarFecha());
        detalleTarjeta.setMarca(obtenerMarcaDeTarjeta(cuenta));
        detalleTarjeta.setNroCuentaProducto(cuenta.getNroCuentaProducto());
        detalleTarjeta.setNroSucursal(cuenta.getNroSucursal());
        detalleTarjeta.setNroTarjeta(cuenta.getNroTarjetaCredito());
        detalleTarjeta.setNroTarjetaConFormato(
                TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), obtenerMarcaDeTarjeta(cuenta)));
        detalleTarjeta.setTipoCuenta(cuenta.getTipoCuenta());
        detalleTarjeta.setPermiteResumenAnual(TarjetaUtils.permiteResumenAnual(cuenta) && 
        		moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_RESUMEN_ANUAL).tienePermisoDeVisibilidad());
        return detalleTarjeta;
    }

    /**
     * Generar encabezado de detalle tarjeta con error.
     *
     * @param cuenta
     *            the cuenta
     * @return the detalle tarjeta
     */
    private DetalleTarjetaDTO generarEncabezadoDeTarjetaConError(Cuenta cuenta) {
        DetalleTarjetaDTO detalleTarjeta = generarEncabezadoDeTarjeta(cuenta);
        if (validarSiEsImprimibleTitular(cuenta)) {
			detalleTarjeta.setIsReimprimibleTitular(true);
		}else{
			detalleTarjeta.setIsReimprimibleTitular(false);
		}
        detalleTarjeta.setError(true);
        return detalleTarjeta;
    }

    /**
     * Genera un DatoItemMensaje.
     *
     * @param iTag
     *            the i tag
     * @param codigoError
     *            the codigo error
     * @param tipoError
     *            the tipo error
     * @return the dato item mensaje
     */
    private DatoItemMensaje obtenerDatoItemMensaje(Integer iTag, String codigoError, TipoError tipoError) {
        return this.getRespuestaFactory()
                .generarDatoItemMensaje(
                        TagItemMensajeTarjetaEnum.TAG_TARJETA.getDescripcionTag() + String.valueOf(iTag)
                                + TagItemMensajeTarjetaEnum.TAG_TARJETA_CIERRE.getDescripcionTag(),
                        tipoError, codigoError);
    }

    /**
     * Es visa recargable.
     *
     * @param cuenta
     *            the cuenta
     * @return the boolean
     */
    private Boolean esVisaRecargable(Cuenta cuenta) {
        return TarjetaBOUtils.esVisaRecargable(cuenta);
    }

    /**
     * Obtener dato item error sin tarjetas.
     *
     * @return the dato item mensaje
     */
    private DatoItemMensaje obtenerDatoItemErrorSinTarjetas() {
        return this.getRespuestaFactory().generarDatoItemMensaje(
                TagItemMensajeTarjetaEnum.TAG_TARJETA.getDescripcionTag() + String.valueOf(0)
                        + TagItemMensajeTarjetaEnum.TAG_TARJETA_CIERRE.getDescripcionTag(),
                TipoError.ERROR_GENERICO, TarjetaBOUtils.getCodigoErrorSinTarjetas());
    }

    /**
     * Obtener lista de dato item mensaje sin tarjetas.
     *
     * @return the list
     */
    private List<DatoItemMensaje> obtenerListaDeDatoItemMensajeSinTarjetas() {
        List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
        datos.add(obtenerDatoItemErrorSinTarjetas());
        return datos;
    }

    /**
     * Obtiene la lista de items mensaje respuesta, para cada tarjeta que tenga
     * error .
     *
     * @param dto
     *            the dto
     * @return the list
     */
    private List<DatoItemMensaje> generarDatosItemMensaje(DisponiblesYConsumoDTO dto) {
        if (!dto.getResumenes().isEmpty()) {
            return obtenerDatoItemMensajeSiHayResumenes(dto.getResumenes());
        }
        return obtenerDatoItemMensajeSiNoResumenes();
    }

    /**
     * Obtener dato item mensaje para selector.
     *
     * @param resumenTarjetas
     *            the resumen tarjetas
     * @return the list
     */
    private List<DatoItemMensaje> obtenerDatoItemMensajeSiHayResumenes(List<ResumenTarjetaDTO> resumenTarjetas) {
        List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
        for (int i = 0; i < resumenTarjetas.size(); i++) {
            DatoItemMensaje dato = obtenerDatoItemMensaje(resumenTarjetas.get(i), i);
            if (dato != null) {
                datos.add(dato);
            }
        }
        return datos;
    }

    /**
     * Genera el item de mensaje para cada tipo de error de las tarjetas.
     *
     * @param resumenTarjeta
     *            the resumen tarjeta
     * @param i
     *            the i
     * @return the dato item mensaje
     */
    private DatoItemMensaje obtenerDatoItemMensaje(ResumenTarjetaDTO resumenTarjeta, Integer i) {
        if (resumenTarjeta.getErrorDisponibles() && resumenTarjeta.getErrorConsumos()) {
            return obtenerDatoItemMensaje(i, TarjetaBOUtils.getCodigoErrorTotal(), TipoError.ERROR_GENERICO);
        }

        if (resumenTarjeta.getDetalle().getIsTitular()) {

            if (resumenTarjeta.getErrorDisponibles()) {
                return obtenerDatoItemMensaje(i, TarjetaBOUtils.getCodigoErrorAlConsultarDisponibles(),
                        TipoError.ERROR_CONSULTA_MOVIMIENTOS);
            }

            if (resumenTarjeta.getErrorConsumos()) {
                return obtenerDatoItemMensaje(i, TarjetaBOUtils.getCodigoErrorAlConsultarConsumos(), TipoError.WARNING);
            }

        }

        if (resumenTarjeta.getDetalle().getIsAdicional()) {
            if (resumenTarjeta.getErrorConsumos()) {
                return obtenerDatoItemMensaje(i, TarjetaBOUtils.getCodigoErrorAlConsultarAdicionales(),
                        TipoError.WARNING);
            }

        }

        return null;
    }

    /**
     * Obtener dato item mensaje si no resumenes.
     *
     * @return the list
     */
    private List<DatoItemMensaje> obtenerDatoItemMensajeSiNoResumenes() {
        List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
        datos.add(mensajesTarjeta.obtenerDatoItemSiNoHayResumenes());
        return datos;
    }

    /**
     * Crear resumen tarjeta DTO error.
     *
     * @param cuenta
     *            the cuenta
     * @return the resumen tarjeta DTO
     */
    private ResumenTarjetaDTO crearResumenTarjetaDTOError(Cuenta cuenta) {
        return new ResumenTarjetaDTO(generarEncabezadoDeTarjetaConError(cuenta), true, false,
                TarjetaBOUtils.getCodigoErrorAlConsultarDisponibles(), null);
    }

	/**
	 * Consultar adicionales DTO error.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the resumen tarjeta DTO
	 */
    private ResumenTarjetaDTO consultarAdicionalesDTOError(Cuenta cuenta) {
        return new ResumenTarjetaDTO(generarEncabezadoDeTarjetaConError(cuenta), false, true,
                TarjetaBOUtils.getCodigoErrorAlConsultarAdicionales(), null);
    }

	/**
	 * Consultar titular DTO error.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the resumen tarjeta DTO
	 */
    private ResumenTarjetaDTO consultarTitularDTOError(Cuenta cuenta) {
        return new ResumenTarjetaDTO(generarEncabezadoDeTarjetaConError(cuenta), false, true,
                TarjetaBOUtils.getCodigoErrorAlConsultarDisponiblesYConsumos(), null);
    }

    /**
     * Obtener limites ydisponibles.
     *
     * @param listaResumenDTO
     *            the lista resumen DTO
     * @return the list
     */
    private List<LimitesYDisponiblesDTO> obtenerLimitesYdisponibles(List<ResumenTarjetaDTO> listaResumenDTO) {
        List<LimitesYDisponiblesDTO> list = new ArrayList<LimitesYDisponiblesDTO>();
        for (ResumenTarjetaDTO resumenTarjetaDTO : listaResumenDTO) {
            if (resumenTarjetaDTO.getLimitesYDisponiblesDTO() != null) {
                list.add(resumenTarjetaDTO.getLimitesYDisponiblesDTO());
            }
        }
        return list;
    }

    /**
     * Generar detalle de tarjeta con fechas.
     *
     * @param cuenta
     *            the cuenta
     * @param tarjeta
     *            the tarjeta
     * @return the detalle tarjeta
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    private DetalleTarjetaDTO generarDetalleDeTarjetaConFechas(Cuenta cuenta, TarjetaEntity tarjeta, boolean esReimprimible)
            throws ParseadorVisaException {
        DetalleTarjetaDTO detalleTarjeta = generarEncabezadoDeTarjeta(cuenta);

        String consumoPesos = TarjetaBOUtils.formatearSaldoSinAbs(obtenerConsumosPesos(tarjeta));
        String consumoDolar = TarjetaBOUtils.formatearSaldoSinAbs(obtenerConsumosDolares(tarjeta));
        detalleTarjeta.setConsumoPesos(consumoPesos);
        detalleTarjeta.setConsumoDolares(consumoDolar);
        detalleTarjeta.setEsConsumoPesosCero(TarjetaBOUtils.CERO_FLOTANTE.equals(consumoPesos));
        detalleTarjeta.setEsConsumoDolarCero(TarjetaBOUtils.CERO_FLOTANTE.equals(consumoDolar));

        if (esReimprimible) {
			detalleTarjeta.setIsReimprimibleTitular(true);
		}else{
			detalleTarjeta.setIsReimprimibleTitular(false);
		}
        
        if (TIPO_CUENTA_TARJETA_RECARGABLE.equals(cuenta.getTipoCuenta())) {
            String disponibleCompras = ISBANStringUtils
                    .formatearSaldo(obtenerSaldoTipo(tarjeta, TarjetaUtils.DISPONIBLE_COMPRAS));
            String disponiblesCuotas = ISBANStringUtils
                    .formatearSaldo(obtenerSaldoTipo(tarjeta, TarjetaUtils.DISPONIBLE_CUOTAS));

            if ("0".equals(disponibleCompras) || StringUtils.EMPTY.equals(disponibleCompras)
                    || disponibleCompras == null) {
                disponibleCompras = CERO;
            } else {
                detalleTarjeta.setSaldoDisponibleCompras(disponibleCompras);
            }

            if ("0".equals(disponiblesCuotas) || StringUtils.EMPTY.equals(disponiblesCuotas)
                    || disponiblesCuotas == null) {
                disponiblesCuotas = CERO;
            } else {
                detalleTarjeta.setSaldoDisponibleCuotas(disponiblesCuotas);
            }
        }

        detalleTarjeta.setFechaCierre(obtenerFechaPorCierre(tarjeta));
        detalleTarjeta.setFechaVencimiento(TarjetaBOUtils.formatearFecha(obtenerFechaPorVencimiento(tarjeta)));
        detalleTarjeta.setFechaDesde(TarjetaBOUtils.formatearFechaCompleta(parseador.obtenerFechaDesde(tarjeta)));
        return detalleTarjeta;
    }

	/**
	 * Obtener fecha por cierre.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    public String obtenerFechaPorCierre(TarjetaEntity tarjeta) throws ParseadorVisaException {

        try {

            if (tarjeta.getTarjetaDocument().getDatosCuenta().getFechaCierre() != null) {

                String fechaCierre = tarjeta.getTarjetaDocument().getDatosCuenta().getFechaCierre();

                return TarjetaBOUtils.formatearFecha(fechaCierre);
            }

        } catch (NullPointerException e) {

            throw new ParseadorVisaException();
            // TODO: handle exception
        }

        return null;
    }

	/**
	 * Obtener fecha por vencimiento.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    public String obtenerFechaPorVencimiento(TarjetaEntity tarjeta) throws ParseadorVisaException {

        try {

            if (tarjeta.getTarjetaDocument().getDatosCuenta().getFechaVencimiento() != null) {

                String fechaCierre = tarjeta.getTarjetaDocument().getDatosCuenta().getFechaVencimiento();

                return TarjetaBOUtils.formatearFecha(fechaCierre);
            }

        } catch (NullPointerException e) {

            throw new ParseadorVisaException();
            // TODO: handle exception
        }

        return null;
    }

    /**
     * Crear respuesta.
     *
     * @param listaResumenDTO
     *            the lista resumen DTO
     * @return the respuesta
     */
    private Respuesta<DisponiblesYConsumoDTO> crearRespuesta(List<ResumenTarjetaDTO> listaResumenDTO) {
        DisponiblesYConsumoDTO dto = new DisponiblesYConsumoDTO(listaResumenDTO, null);
        List<DatoItemMensaje> datos = generarDatosItemMensaje(dto);
        if (dto.getResumenes().isEmpty()) {
            return this.getRespuestaFactory().crearRespuestaError(datos);
        }
        dto.setLimitesYDisponiblesDTO(obtenerLimitesYdisponibles(listaResumenDTO));
        if (!datos.isEmpty() && (datos.size() > TarjetaBOUtils.CERO_FLOAT)) {
            return this.getRespuestaFactory().crearRespuestaWarning(datos, dto);
        }
        return this.getRespuestaFactory().crearRespuestaOk(DisponiblesYConsumoDTO.class, dto);
    }

    /**
     * Retorna una respuesta para el caso de que no se haya encontrado ningun
     * producto Visa o Amex.
     *
     * @return the respuesta
     */
    private Respuesta<DisponiblesYConsumoDTO> crearRespuestaSinTarjetas() {
        return this.getRespuestaFactory().crearRespuestaError(obtenerListaDeDatoItemMensajeSinTarjetas());
    }

    /**
     * Genera una respuesta con solo los encabezados de las tarjetas y los
     * mensajes de error correspondientes.
     *
     * @return the respuesta
     */
    private Respuesta<DisponiblesYConsumoDTO> crearRespuestaErrorYEncabezado() {
        return this.getRespuestaFactory().crearRespuestaError(obtenerDatoItemMensajeSiNoResumenes());
    }

}