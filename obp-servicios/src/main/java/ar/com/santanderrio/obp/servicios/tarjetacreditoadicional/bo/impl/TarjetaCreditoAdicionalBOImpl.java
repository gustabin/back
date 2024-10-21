/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaInhabilitadosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.merlin.dao.MerlinDAO;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DudaEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinWarningException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.impl.AltaTagMonederoBOImpl;
import ar.com.santanderrio.obp.servicios.monedero.entities.EstadoCivilIatxEnum;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.EstadoYLimitesTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.TarjetaCreditoAdicionalBO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.AltaTarjetaCreditoAdicionalDAO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ComprobanteAltaTarjCredAdicionalDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaOutDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomicilioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomiciliosDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.MensajeRespuestaDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaAdicionalSolicitadaDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.AltaTarjetaCreditoAdicionalInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.AltaTarjetaCreditoAdicionalOutResponseEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.ComprobanteAltaTarjCredAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.TarjetaCandidataView;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;

/**
 * The Class TarjetaCreditoAdicionalBOImpl.
 */
@Component
public class TarjetaCreditoAdicionalBOImpl implements TarjetaCreditoAdicionalBO {

    /** The Constant RESULTADO_WARNING. */
    private static final String RESULTADO_WARNING = "WARNING";

    /** The Constant RESULTADO_ERROR. */
    private static final String RESULTADO_ERROR = "ERROR";

    /** The Constant RESULTADO_OK. */
    private static final String RESULTADO_OK = "OK";

    /** The Constant BLANK_SPACE. */
    private static final String BLANK_SPACE = " ";

    /** The Constant SANTANDER_RÍO. */
    private static final String SANTANDER_RIO = "Tarjeta de crédito Santander";

    /** The Constant MARCA_VISA. */
    private static final String MARCA_VISA = "1";

    /** The Constant MARCA_AMEX. */
    private static final String MARCA_AMEX = "4";
    /** The Constant MARCA_MASTER. */
    private static final String MARCA_MASTER = "3";

    /** The Constant ALGUNA_CON_ERROR. */
    private static final String ALGUNA_CON_ERROR = "7516";

    /** The Constant DOS_DE_DOS_ERROR. */
    private static final String DOS_DE_DOS_ERROR = "7515";

    /** The Constant UNA_DE_UNA_ERROR. */
    private static final String UNA_DE_UNA_ERROR = "7514";

    /** The Constant DOS_DE_DOS_OK. */
    private static final String DOS_DE_DOS_OK = "7513";

    /** The Constant UNA_DE_UNA_OK. */
    private static final String UNA_DE_UNA_OK = "7512";

    /** The Constant TARJETA_BLACK. */
    private static final String MARCA_TARJETA_CLASE_BLACK = "H";

    /** The Constant MONEDA_PESO. */
    private static final String MONEDA_PESO = "peso";

    /** The Constant MONEDA_DOLAR. */
    private static final String MONEDA_DOLAR = "dolar";

    /** The Constant MASCULINO. */
	private static final String MASCULINO = "Masculino";
	
	/** The Constant FEMENINO. */
	private static final String FEMENINO = "Femenino";
	
    /** The Constant MARCA_AMEX. */
    public static final String AMEX = "AMEX";
	
    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The datos solicitante DAO. */
    @Autowired
    private DatosSolicitanteDAO datosSolicitanteDAO;

    /** The descripciones por id. */
    @Autowired
    private DatosSelectoresDAO datosSelectoresDAO;

    /** The consulta inhabilitados DAO. */
    @Autowired
    private ConsultaInhabilitadosDAO consultaInhabilitadosDAO;

    /** The alta tarjeta credito adicional DAO. */
    @Autowired
    private AltaTarjetaCreditoAdicionalDAO altaTarjetaCreditoAdicionalDAO;

    /** The merlin DAO. */
    @Autowired
    private MerlinDAO merlinDAO;

    /** The datos selectores BO. */
    @Autowired
    private DatosSelectoresBO datosSelectoresBO;

    /** The estado Y limites tarjeta credito BO. */
    @Autowired
    private EstadoYLimitesTarjetaCreditoBO estadoYLimitesTarjetaCreditoBO;

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AltaTagMonederoBOImpl.class);

    /** The Constant CODIGO_OK. */
    private static final String CODIGO_OK = "00000000";

    /** The Constant MERLIN_DUDCPA_AI. */
    private static final String MERLIN_DUDCPA_AI = "AI";

    /** The Constant MERLIN_DUDCPA_LI. */
    private static final String MERLIN_DUDCPA_LI = "LI";

    /** The Constant MERLIN_DUDCPA_CA. */
    private static final String MERLIN_DUDCPA_CA = "CA";

    /** The Constant PERSONA_INEXISTENTE. */
    private static final String PERSONA_INEXISTENTE = "10000221";

    /** The Constant FALTAN_DATOS_ENTRADA. */
    private static final String FALTAN_DATOS_ENTRADA = "10000226";

    /** The Constant ESTADO_TARJETA_ACTIVA. */
    private static final String ESTADO_TARJETA_ACTIVA = "10";

    /** The Constant TIPOCTA_VISA. */
    public static final String TIPOCTA_VISA = "07";

    /** The Constant TIPOCTA_AMEX. */
    public static final String TIPOCTA_AMEX = "42";
    
    /** The Constant TIPOCTA_MASTER. */
    public static final String TIPOCTA_MASTER = "06";

    /** The Constant TIPO_TARJETA_VISA. */
    public static final String TIPO_TARJETA_VISA = "1";

    /** The Constant TIPO_TARJETA_AMEX. */
    public static final String TIPO_TARJETA_AMEX = "3";
    
    /** The Constant TIPO_TARJETA_MASTER. */
    public static final String TIPO_TARJETA_MASTER = "3";

    /** The Constant TAG_PROVINCIA. */
    private static final String TAG_PROVINCIA = "provincias";

    /** The Constant INT_10. */
    private static final int INT_10 = 10;

    /** The Constant MARCA_VISA. */
    public static final String MARCA_VISA_DESC = "VISA";

    /** The Constant MARCA_AMEX. */
    public static final String MARCA_AMEX_DESC = "AMEX";
    /** The Constant MARCA_MASTER. */
    public static final String MARCA_MASTER_DESC = "MASTER";

    /** The Constant LOG_ERROR. */
    private static final String LOG_ERROR = "Error ";

    /** The Constant ERROR_NORMALIZACION_17. */
    private static final String ERROR_NORMALIZACION_17 = "20000017";

    /** The Constant ERROR_NORMALIZACION_16. */
    private static final String ERROR_NORMALIZACION_16 = "20000016";

    /**
     * Gets the tarjetas candidatas.
     *
     * @return the tarjetas candidatas
     */

    @Override
    public Respuesta<List<TarjetaCandidataDTO>> getTarjetasCandidatas() {
        List<TarjetaCandidataDTO> tarjetasCandidatas = new ArrayList<TarjetaCandidataDTO>();
        Cliente cliente = sesionCliente.getCliente();
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.esTitularTarjetaVisa() || cuenta.esTitularTarjetaAmex() || cuenta.esTitularTarjetaMaster()) {
                ConsultaDetalleDatosTarjetaInDTO consultaDetalleDatosTarjetaInDTO = new ConsultaDetalleDatosTarjetaInDTO();
                consultaDetalleDatosTarjetaInDTO.setCliente(cliente);
                consultaDetalleDatosTarjetaInDTO.setNroCuentaTarjeta(
                        cuenta.getNroCuentaProducto().substring(cuenta.getNroCuentaProducto().length() - INT_10));
                if(TIPOCTA_VISA.equals(cuenta.getTipoCuenta()))
                	consultaDetalleDatosTarjetaInDTO.setTipoTarjeta(TIPO_TARJETA_VISA);
                else if(TIPOCTA_AMEX.equals(cuenta.getTipoCuenta()))
                	consultaDetalleDatosTarjetaInDTO.setTipoTarjeta(TIPO_TARJETA_AMEX);
                else if(TIPOCTA_MASTER.equals(cuenta.getTipoCuenta()))
                	consultaDetalleDatosTarjetaInDTO.setTipoTarjeta(TIPO_TARJETA_MASTER);
                ConsultaDetalleDatosTarjetaOutDTO consultaDetalleDatosTarjetaOutDTO = null;
                try {
                    LOGGER.info("Obtengo el limite de la tarjeta.");
                    consultaDetalleDatosTarjetaOutDTO = estadoYLimitesTarjetaCreditoBO
                            .obtenerDetalleDatosTarjeta(consultaDetalleDatosTarjetaInDTO, cliente);
                } catch (DAOException e) {
                    LOGGER.error("getTarjetasCandidatasConLimite", e);
                    return respuestaFactory.crearRespuestaError("", TipoError.SOLICITUDES_ERROR_GENERICO,
                            CodigoMensajeConstantes.ERROR_SOLICITUDES_GENERICO);
                }

                String limiteCompraPesos = consultaDetalleDatosTarjetaOutDTO.getLimiteDeCompra();
                int intLimiteCompraPesos = Integer.parseInt(limiteCompraPesos);
                if (intLimiteCompraPesos > 1
                        && ESTADO_TARJETA_ACTIVA.equals(consultaDetalleDatosTarjetaOutDTO.getEstado())) {
                    tarjetasCandidatas.add(tarjetaADTO(cuenta, intLimiteCompraPesos));
                }

            }
        }
        return respuestaFactory.crearRespuestaOk(tarjetasCandidatas);
    }

    /**
	 * Tarjeta ADTO.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param intLimiteCompraPesos
	 *            the int limite compra pesos
	 * @return the tarjeta candidata DTO
	 * @Modificacion 20190927 - se deja de aplicar dolar para clase Platinum
	 */
    private TarjetaCandidataDTO tarjetaADTO(Cuenta cuenta, int intLimiteCompraPesos) {
        String marca = cuenta.getTipoCuenta();
        String marcaDescripcion = TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum()) ? 
        		MARCA_VISA_DESC : (TipoCuenta.AMEX.equals(cuenta.getTipoCuentaEnum()) ? MARCA_AMEX_DESC : MARCA_MASTER_DESC);
        TarjetaCandidataDTO tarjetaCandidataDTO = new TarjetaCandidataDTO();
        tarjetaCandidataDTO.setTipoCuenta(marca);
        tarjetaCandidataDTO.setTipoCuentaDescripcion(marcaDescripcion);
        tarjetaCandidataDTO.setAlias(cuenta.getAlias());
        tarjetaCandidataDTO.setNroTarjetaConFormato(
                TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), marcaDescripcion));
        tarjetaCandidataDTO.setLimiteDeCompra(String.valueOf(intLimiteCompraPesos));
        tarjetaCandidataDTO.setCuentaId(cuenta.getId().toString());
        tarjetaCandidataDTO.setMoneda(MONEDA_PESO);
        if (MARCA_TARJETA_CLASE_BLACK.equals(cuenta.getClaseCuenta())) {
            tarjetaCandidataDTO.setMoneda(MONEDA_DOLAR);
        }
        return tarjetaCandidataDTO;
    }

    /**
	 * Gets the datos del solicitante altair.
	 *
	 * @param datosSolicitanteCriterioDTO
	 *            the datos solicitante criterio DTO
	 * @param cliente
	 *            the cliente
	 * @return the datos del solicitante altair
	 * @throws EdadIncorrectaException
	 *             the edad incorrecta exception
	 */
    @Override
    public Respuesta<DatosSolicitanteDTO> getDatosDelSolicitanteAltair(
            DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO, Cliente cliente) throws EdadIncorrectaException {
        try {
            validarEdadCliente(datosSolicitanteCriterioDTO);
        } catch (EdadIncorrectaException ex) {
            LOGGER.error("El cliente no tiene la edad permitida para esta operacion. ", ex);
            throw new EdadIncorrectaException(ex.getMessage());
        }
        try {
            datosSolicitanteCriterioDTO.setAdicional(true);
            DatosSolicitanteCriterioEntity datosSolicitanteCriterioEntity = new DatosSolicitanteCriterioEntity(
                    datosSolicitanteCriterioDTO);
            DatosSolicitanteEntity datosSolicitanteEntity = datosSolicitanteDAO
                    .getDatosDelSolicitante(datosSolicitanteCriterioEntity, cliente);
            

            return respuestaFactory.crearRespuestaOk(datosSolicitanteDTO(datosSolicitanteEntity));
        } catch (DAOException e) {
            LOGGER.error("No se encontraron datos. ", e);
            if (PERSONA_INEXISTENTE.equals(e.getErrorCode()) || FALTAN_DATOS_ENTRADA.equals(e.getErrorCode())) {
                return respuestaFactory.crearRespuestaError(DatosSolicitanteDTO.class, e.getMessage(), "");
            }
        } catch (SinAccesoALaInformacionException e) {
            LOGGER.error(LOG_ERROR, e);
        } catch (OperacionFueraHorarioSucursalException e) {
            LOGGER.error(LOG_ERROR, e);
        }

        return respuestaFactory.crearRespuestaError(DatosSolicitanteDTO.class, "", "");
    }

    /**
     * Valida si la edad del cliente es correcta para la operacion.
     *
     * @param datosSolicitanteCriterioDTO
     *            the datos solicitante criterio DTO
     * @throws EdadIncorrectaException
     *             the edad incorrecta exception
     */
    private void validarEdadCliente(DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO)
            throws EdadIncorrectaException {
        Integer edad = obtenerEdadCliente(datosSolicitanteCriterioDTO);
        if (edad < 14) {
            throw new EdadIncorrectaException("El cliente no tiene la edad permitida para la operación.");
        }
    }

    /**
     * Obtiene la edad del cliente.
     *
     * @param datosSolicitanteCriterioDTO
     *            the datos solicitante criterio DTO
     * @return the integer
     */
    private Integer obtenerEdadCliente(DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO) {
        if (StringUtils.isNotBlank(datosSolicitanteCriterioDTO.getFechaNacimiento())) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fecNac = new Date();
            try {
                fecNac = format.parse(datosSolicitanteCriterioDTO.getFechaNacimiento());
            } catch (ParseException e) {
                LOGGER.error("Error en el parseo de la fecha de nacimiento.", e);
                return 0;
            }
            Calendar fechaNac = new GregorianCalendar();
            Calendar hoy = Calendar.getInstance();
            fechaNac.setTime(fecNac);
            int diffAnio = hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
            int diffMes = hoy.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
            int diffDia = hoy.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
            if (diffMes < 0 || (diffMes == 0 && diffDia < 0)) {
                diffAnio = diffAnio - 1;
            }
            return diffAnio;
        } else {
            return 0;
        }
    }

    /**
	 * Sets the datos solicitante DTO.
	 *
	 * @param datosSolicitanteEntity
	 *            the datos solicitante entity
	 * @return the datos solicitante DTO
	 */
    private DatosSolicitanteDTO datosSolicitanteDTO(DatosSolicitanteEntity datosSolicitanteEntity) {
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
        datosSolicitanteDTO.setNup(datosSolicitanteEntity.getNup());
        datosSolicitanteDTO.setApellido(datosSolicitanteEntity.getApellido().trim());
        datosSolicitanteDTO.setNombre(datosSolicitanteEntity.getNombre().trim());
        datosSolicitanteDTO.setDocumentoNro(ISBANStringUtils.eliminarCeros(datosSolicitanteEntity.getDoc().trim()));
        datosSolicitanteDTO.setDocumentoTipo(datosSolicitanteEntity.getDocTipo());
        datosSolicitanteDTO.setFechaNacimiento(datosSolicitanteEntity.getFechaNacimiento());
        datosSolicitanteDTO.setIdNacionalidad(datosSolicitanteEntity.getNacionalidad());
        datosSolicitanteDTO.setNacionalidad(datosSelectoresBO.obtenerDescripcionPorOptionId("Nacionalidades",
                datosSolicitanteEntity.getNacionalidad()));
        datosSolicitanteDTO.setIdPaisNacimiento(datosSolicitanteEntity.getPaisNacimiento());
        datosSolicitanteDTO.setPaisNacimiento(datosSelectoresBO.obtenerDescripcionPorOptionId("paisNacimiento",
                datosSolicitanteEntity.getPaisNacimiento()));
        datosSolicitanteDTO.setSexo("H".equals(datosSolicitanteEntity.getSexo()) ? "Masculino" : "Femenino");
        if (MASCULINO.equals(datosSolicitanteDTO.getSexo()))
        datosSolicitanteDTO.setEstadoCivil(EstadoCivilIatxEnum
                .obtenerEstadoCivilIatxDescripcion(datosSolicitanteEntity.getEstadoCivil()).getDescripcionM());
        if (FEMENINO.equals(datosSolicitanteDTO.getSexo()))
        datosSolicitanteDTO.setEstadoCivil(EstadoCivilIatxEnum
    			.obtenerEstadoCivilIatxDescripcion(datosSolicitanteEntity.getEstadoCivil()).getDescripcionF());
        datosSolicitanteDTO.setCalle(datosSolicitanteEntity.getCalle());
        datosSolicitanteDTO.setCalleNro(datosSolicitanteEntity.getCalleNro());
        datosSolicitanteDTO.setPiso(datosSolicitanteEntity.getPiso());
        datosSolicitanteDTO.setDepto(datosSolicitanteEntity.getDepto());
        datosSolicitanteDTO.setLocalidad(datosSolicitanteEntity.getLocalidad());
        datosSolicitanteDTO.setCp(datosSolicitanteEntity.getCp());
        datosSolicitanteDTO.setTelefono(datosSolicitanteEntity.getTelefono());
        return datosSolicitanteDTO;

    }

    /**
     * Es persona habilitada.
     *
     * @param entity
     *            the entity
     * @return the consulta inhabilitados out entity
     */
    @Override
    public ConsultaInhabilitadosOutEntity esPersonaHabilitada(ConsultaInhabilitadosInEntity entity) {
        ConsultaInhabilitadosOutEntity consultaInhabilitadosOutEntity = new ConsultaInhabilitadosOutEntity();
        try {
            consultaInhabilitadosOutEntity = consultaInhabilitadosDAO.consultaInhabilitados(entity);
        } catch (DAOException e) {
            LOGGER.error("esPersonaHabilitada", e);
        }
        return consultaInhabilitadosOutEntity;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.TarjetaCreditoAdicionalBO#getResultadoMerlin(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity)
     */
    @Override
    public DomiciliosDTO getResultadoMerlin(Cliente cliente, DatosMerlinInEntity datosDeDomicilio)
            throws BusinessException {
        datosDeDomicilio.setCliente(cliente);
        datosDeDomicilio.setNup(cliente.getNup());
        try {
            ResultadoMerlinEntity resultadoMerlinEntity = merlinDAO.busquedaMerlin(datosDeDomicilio);
            DomiciliosDTO domiciliosDTO = new DomiciliosDTO();
            List<DomicilioDTO> domiciliosDTOList = new ArrayList<DomicilioDTO>();
            domiciliosDTO.setDomiciliosDTO(domiciliosDTOList);

            if (resultadoMerlinEntity.esCodigoOk() || resultadoMerlinEntity.esCodigoWarningPorCambioDomicilio()) {
                LOGGER.info("La direccion ingresada se normaliza.");
                domiciliosDTOList.add(new DomicilioDTO(resultadoMerlinEntity));
            } else {
                LOGGER.info("Hay dudas en la direccion ingresada.");
                domiciliosDTO.setEsPopUp(true);
                domiciliosDTOList.addAll(mapeaDatos(resultadoMerlinEntity));
            }
            for (DomicilioDTO domicilioDTO : domiciliosDTOList) {
                domicilioDTO.setDescProv(
                        datosSelectoresDAO.obtenerOpcionDescripcion(TAG_PROVINCIA, domicilioDTO.getCodProv()));
            }
            return domiciliosDTO;
        } catch (MerlinError1Exception me) {
            LOGGER.error("getResultadoMerlin - DAOException", me);
        } catch (DAOException e) {
            LOGGER.error("getResultadoMerlin - DAOException", e);
        }
        throw new BusinessException();
    }

    /**
	 * Mapea datos.
	 *
	 * @param resultadoMerlinEntity
	 *            the resultado merlin entity
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
    private List<DomicilioDTO> mapeaDatos(ResultadoMerlinEntity resultadoMerlinEntity) throws BusinessException {
        List<DomicilioDTO> domiciliosDTOList = new ArrayList<DomicilioDTO>();
        if (!resultadoMerlinEntity.getDudas().isEmpty()) {
            for (DudaEntity duda : resultadoMerlinEntity.getDudas()) {
                if (!tieneDatoInvalido(duda.getDudCPA())) {
                    DomicilioDTO domicilioDTO = new DomicilioDTO(duda, resultadoMerlinEntity);
                    domiciliosDTOList.add(domicilioDTO);
                }
            }
            if (domiciliosDTOList.isEmpty()) {
                throw new MerlinWarningException(
                        tipoErrorDatoInvalido(resultadoMerlinEntity.getDudas().get(0).getDudCPA()));
            }
        } else {
            if (ERROR_NORMALIZACION_17.equals(resultadoMerlinEntity.getCodigoRetornoExtendido())
                    || ERROR_NORMALIZACION_16.equals(resultadoMerlinEntity.getCodigoRetornoExtendido())) {
                if (!resultadoMerlinEntity.getNoLoc().trim().isEmpty()) {
                    TipoError tipoError = TipoError.DUDA_DOMICILIO_LOCALIDAD;
                    throw new MerlinWarningException(tipoError);
                } else if (!resultadoMerlinEntity.getNoCal().trim().isEmpty()) {
                    TipoError tipoError = TipoError.DUDA_DOMICILIO_CALLE;
                    throw new MerlinWarningException(tipoError);
                } else if (!resultadoMerlinEntity.getNoAlt().trim().isEmpty()) {
                    TipoError tipoError = TipoError.DUDA_DOMICILIO_ALTURA;
                    throw new MerlinWarningException(tipoError);
                }
            } else {
                throw new BusinessException("En caso de warning con alternativas tiene que tener dudas.");
            }
        }
        return domiciliosDTOList;
    }

    /**
	 * Tipo error dato invalido.
	 *
	 * @param codPostal
	 *            the cod postal
	 * @return the tipo error
	 */
    private TipoError tipoErrorDatoInvalido(String codPostal) {
        codPostal = StringUtils.trim(codPostal);
        TipoError tipoError;
        if (MERLIN_DUDCPA_AI.equals(codPostal)) {
            tipoError = TipoError.DUDA_DOMICILIO_ALTURA;
        } else if (MERLIN_DUDCPA_LI.equals(codPostal)) {
            tipoError = TipoError.DUDA_DOMICILIO_LOCALIDAD;
        } else if (MERLIN_DUDCPA_CA.equals(codPostal)) {
            tipoError = TipoError.DUDA_DOMICILIO_CALLE;
        } else {
            tipoError = TipoError.ERROR_GENERICO;
        }
        return tipoError;
    }

    /**
	 * Tiene dato invalido.
	 *
	 * @param codPostal
	 *            the cod postal
	 * @return true, if successful
	 */
    private boolean tieneDatoInvalido(String codPostal) {
        codPostal = StringUtils.trim(codPostal);
        return MERLIN_DUDCPA_AI.equals(codPostal) || MERLIN_DUDCPA_LI.equals(codPostal)
                || MERLIN_DUDCPA_CA.equals(codPostal);
    }

    /**
     * Alta tarjeta credito adicional.
     *
     * @param datosSolicitanteConfirmadoDTO
     *            the datos solicitante confirmado DTO
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    @Override
    public Respuesta<ComprobanteAltaTarjCredAdicionalDTO> altaTarjetaCreditoAdicional(
            DatosConfirmadosDelSolicitanteView datosSolicitanteConfirmadoDTO, Cliente cliente) {

        List<TarjetaAdicionalSolicitadaDTO> tarjetasAdicionalesSolicitadas = new ArrayList<TarjetaAdicionalSolicitadaDTO>();

        for (TarjetaCandidataView tarjetaCandidataView : datosSolicitanteConfirmadoDTO.getTarjetas()) {

            String cuentaId = tarjetaCandidataView.getCuentaId();
            Cuenta cuenta = cuentaBO.obtenerCuentaPorId(cliente, cuentaId);
            String marca = TIPOCTA_VISA.equals(cuenta.getTipoCuenta()) ? MARCA_VISA : (TIPOCTA_AMEX.equals(cuenta.getTipoCuenta()) ? MARCA_AMEX : MARCA_MASTER);
            String marcaDescripcion = TIPOCTA_VISA.equals(cuenta.getTipoCuenta()) ? MARCA_VISA_DESC : (TIPOCTA_AMEX.equals(cuenta.getTipoCuenta()) ? MARCA_AMEX_DESC : MARCA_MASTER_DESC);
            AltaTarjetaCreditoAdicionalInEntity altaTarjetaCreditoAdicionalInEntity = setEntityIn(
                    datosSolicitanteConfirmadoDTO, cliente, tarjetaCandidataView, cuenta, marca);
            try {
                AltaTarjetaCreditoAdicionalOutResponseEntity altaTarjetaCreditoAdicionalOutResponseEntity = altaTarjetaCreditoAdicionalDAO
                        .altaTarjetaCreditoAdicional(altaTarjetaCreditoAdicionalInEntity);
                boolean altaExitosa = altaTarjetaCreditoAdicionalOutResponseEntity != null
                        && CODIGO_OK.equals(altaTarjetaCreditoAdicionalOutResponseEntity.getCodigoRetornoExtendido());
                addTarjetaComprobante(tarjetasAdicionalesSolicitadas, cuenta, marcaDescripcion,
                        altaTarjetaCreditoAdicionalInEntity, altaTarjetaCreditoAdicionalOutResponseEntity, altaExitosa);
            } catch (DAOException e) {
                LOGGER.error("Error dando de alta la tarjeta de credito", e);
                break;
            }
        }
        if (!tarjetasAdicionalesSolicitadas.isEmpty()) {
            ComprobanteAltaTarjCredAdicionalDTO comprobanteAltaTarjCredAdicionalDTO = setComprobante(
                    datosSolicitanteConfirmadoDTO, tarjetasAdicionalesSolicitadas);
            Respuesta<ComprobanteAltaTarjCredAdicionalDTO> respuesta = null;
            respuesta = respuestaFactory.crearRespuestaOk(ComprobanteAltaTarjCredAdicionalDTO.class,
                    comprobanteAltaTarjCredAdicionalDTO);
            seteaMensajeRespuesta(respuesta);
            return respuesta;
        }
        return respuestaFactory.crearRespuestaError("", TipoError.SOLICITUDES_ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_SOLICITUDES_GENERICO);
    }

    /**
     * Gets the estado respuesta.
     *
     * @param tarjetasAdicionalesSolicitadas
     *            the tarjetas adicionales solicitadas
     * @return the estado respuesta
     */
    private EstadoRespuesta getEstadoRespuesta(List<TarjetaAdicionalSolicitadaDTO> tarjetasAdicionalesSolicitadas) {
        int tarjetasSize = tarjetasAdicionalesSolicitadas.size();
        int tarjetasConError = 0;
        for (Iterator<TarjetaAdicionalSolicitadaDTO> iterator = tarjetasAdicionalesSolicitadas.iterator(); iterator
                .hasNext();) {
            TarjetaAdicionalSolicitadaDTO tarjetaAdicionalSolicitadaDTO = (TarjetaAdicionalSolicitadaDTO) iterator
                    .next();
            if (tarjetaAdicionalSolicitadaDTO.isConError()) {
                tarjetasConError++;
            }
        }
        if (tarjetasConError > 0 && tarjetasConError == tarjetasSize) {
            return EstadoRespuesta.ERROR;
        } else if (tarjetasConError > 0 && tarjetasConError < tarjetasSize) {
            return EstadoRespuesta.WARNING;
        }
        return EstadoRespuesta.OK;
    }

    /**
     * Sets the comprobante.
     *
     * @param datosSolicitanteConfirmadoDTO
     *            the datos solicitante confirmado DTO
     * @param tarjetasAdicionalesSolicitadas
     *            the tarjetas adicionales solicitadas
     * @return the comprobante alta tarj cred adicional DTO
     */
    private ComprobanteAltaTarjCredAdicionalDTO setComprobante(
            DatosConfirmadosDelSolicitanteView datosSolicitanteConfirmadoDTO,
            List<TarjetaAdicionalSolicitadaDTO> tarjetasAdicionalesSolicitadas) {
        ComprobanteAltaTarjCredAdicionalDTO comprobanteAltaTarjCredAdicionalDTO = new ComprobanteAltaTarjCredAdicionalDTO();
        comprobanteAltaTarjCredAdicionalDTO.setEstadoRespuesta(getEstadoRespuesta(tarjetasAdicionalesSolicitadas));
        comprobanteAltaTarjCredAdicionalDTO.setApellido(datosSolicitanteConfirmadoDTO.getApellido().trim());
        comprobanteAltaTarjCredAdicionalDTO.setNombre(datosSolicitanteConfirmadoDTO.getNombre().trim());
        comprobanteAltaTarjCredAdicionalDTO.setDniAdicional(datosSolicitanteConfirmadoDTO.getDocumentoNro());
        comprobanteAltaTarjCredAdicionalDTO.setFechaHora(DateFormatUtils.format(new Date(), "dd-MM-yyyy HH:mm"));
        comprobanteAltaTarjCredAdicionalDTO.setLegal("Conserve este ticket como comprobante S.E.U.O.");
        comprobanteAltaTarjCredAdicionalDTO.setTarjetasAdicionalesSolicitadas(tarjetasAdicionalesSolicitadas);
        return comprobanteAltaTarjCredAdicionalDTO;
    }

    /**
     * Adds the tarjeta comprobante.
     *
     * @param tarjetasAdicionalesSolicitadas
     *            the tarjetas adicionales solicitadas
     * @param cuenta
     *            the cuenta
     * @param marcaDescripcion
     *            the marca descripcion
     * @param altaTarjetaCreditoAdicionalInEntity
     *            the alta tarjeta credito adicional in entity
     * @param altaTarjetaCreditoAdicionalOutResponseEntity
     *            the alta tarjeta credito adicional out response entity
     * @param altaExitosa
     *            the alta exitosa
     */
    private void addTarjetaComprobante(List<TarjetaAdicionalSolicitadaDTO> tarjetasAdicionalesSolicitadas,
            Cuenta cuenta, String marcaDescripcion,
            AltaTarjetaCreditoAdicionalInEntity altaTarjetaCreditoAdicionalInEntity,
            AltaTarjetaCreditoAdicionalOutResponseEntity altaTarjetaCreditoAdicionalOutResponseEntity,
            boolean altaExitosa) {
        String porcentaje = ISBANStringUtils
                .eliminarCeros(altaTarjetaCreditoAdicionalInEntity.getPorcentajeLimiteCompra());
        String porcentajeFormateado = porcentaje.substring(0, porcentaje.length() - 2);
        TarjetaAdicionalSolicitadaDTO tarjetaAdicionalSolicitadaDTO = new TarjetaAdicionalSolicitadaDTO();
        tarjetaAdicionalSolicitadaDTO.setTipoCuenta(SANTANDER_RIO + BLANK_SPACE + marcaDescripcion);
        tarjetaAdicionalSolicitadaDTO.setNroTarjetaConFormato(
                TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), marcaDescripcion));
        tarjetaAdicionalSolicitadaDTO.setCuenta(
                ISBANStringUtils.formatearNumeroCuenta(altaTarjetaCreditoAdicionalInEntity.getNroCuentaTarjeta()));
        tarjetaAdicionalSolicitadaDTO.setPorcentajeLimiteDeCompra(porcentajeFormateado);
        tarjetaAdicionalSolicitadaDTO.setNombreTarjeta("Cuenta" + BLANK_SPACE + marcaDescripcion);
        tarjetaAdicionalSolicitadaDTO
                .setSolicitudNro(altaExitosa ? altaTarjetaCreditoAdicionalOutResponseEntity.getNroSolicitud() : "");
        tarjetaAdicionalSolicitadaDTO.setConError(altaExitosa ? false : true);
        tarjetasAdicionalesSolicitadas.add(tarjetaAdicionalSolicitadaDTO);
    }

    /**
     * Sets the entity in.
     *
     * @param datosSolicitanteConfirmadoDTO
     *            the datos solicitante confirmado DTO
     * @param cliente
     *            the cliente
     * @param tarjetaCandidataView
     *            the tarjeta candidata view
     * @param cuenta
     *            the cuenta
     * @param marca
     *            the marca
     * @return the alta tarjeta credito adicional in entity
     */
    private AltaTarjetaCreditoAdicionalInEntity setEntityIn(
            DatosConfirmadosDelSolicitanteView datosSolicitanteConfirmadoDTO, Cliente cliente,
            TarjetaCandidataView tarjetaCandidataView, Cuenta cuenta, String marca) {
        AltaTarjetaCreditoAdicionalInEntity altaTarjetaCreditoAdicionalInEntity = new AltaTarjetaCreditoAdicionalInEntity();
        altaTarjetaCreditoAdicionalInEntity.setApellidoYNombre(datosSolicitanteConfirmadoDTO.getApellido().trim() + "/"
                + datosSolicitanteConfirmadoDTO.getNombre().trim());
        altaTarjetaCreditoAdicionalInEntity
                .setNroCuentaTarjeta(getNroCuentaProducto(cuenta, marca));
        altaTarjetaCreditoAdicionalInEntity
                .setSucursalUsuario(ISBANStringUtils.formateadorConCerosIzq(cuenta.getSucursalPaquete(), 3)); // cliente.getSegmento().getPesucadm()
        altaTarjetaCreditoAdicionalInEntity.setCentroDeCosto(cuenta.getSucursalPaquete());
        altaTarjetaCreditoAdicionalInEntity.setGrupoAfinidad(cuenta.getGrupoAfinidad());
        altaTarjetaCreditoAdicionalInEntity.setMarca(marca);
        altaTarjetaCreditoAdicionalInEntity.setNup(datosSolicitanteConfirmadoDTO.getNup());
        String porcentajeLimiteCompra = ISBANStringUtils
                .formateadorConCerosIzq(tarjetaCandidataView.getPorcentajeLimiteDeCompra() + "00", 5);
        altaTarjetaCreditoAdicionalInEntity.setPorcentajeLimiteCompra(porcentajeLimiteCompra);
        altaTarjetaCreditoAdicionalInEntity.setCliente(cliente);
        return altaTarjetaCreditoAdicionalInEntity;
    }

    /**
     * Sets the ea mensaje respuesta.
     *
     * @param respuesta
     *            the new ea mensaje respuesta
     */
    private void seteaMensajeRespuesta(Respuesta<ComprobanteAltaTarjCredAdicionalDTO> respuesta) {
        String solicitante = respuesta.getRespuesta().getApellido().trim() + ", "
                + respuesta.getRespuesta().getNombre().trim();
        List<TarjetaAdicionalSolicitadaDTO> tarjetas = respuesta.getRespuesta().getTarjetasAdicionalesSolicitadas();
        String mensajeFormateado = "";

        int tarjetasSize = respuesta.getRespuesta().getTarjetasAdicionalesSolicitadas().size();

        MensajeRespuestaDTO mensajeRespuestaDTO = obtieneMensajeRespuestaDTO(tarjetas);

        Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(mensajeRespuestaDTO.getCodigoMensaje());

        if (tarjetasSize == 2) {
            mensajeFormateado = MessageFormat.format(mensaje.getMensaje(),
                    mensajeRespuestaDTO.getTiposTarjetas().get(0), mensajeRespuestaDTO.getTiposTarjetas().get(1),
                    solicitante);
        } else {
            mensajeFormateado = MessageFormat.format(mensaje.getMensaje(),
                    mensajeRespuestaDTO.getTiposTarjetas().get(0), solicitante);
        }

        ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
        mensajeRespuesta.setMensaje(mensajeFormateado);
        List<ItemMensajeRespuesta> mensajes = new ArrayList<ItemMensajeRespuesta>();
        mensajes.add(0, mensajeRespuesta);
        respuesta.setItemMensajeRespuesta(mensajes);
        respuesta.setEstadoRespuesta(respuesta.getRespuesta().getEstadoRespuesta());
    }

    /**
     * Obtiene mensaje respuesta DTO.
     *
     * @param tarjetas
     *            the tarjetas
     * @return the mensaje respuesta DTO
     */
    private MensajeRespuestaDTO obtieneMensajeRespuestaDTO(List<TarjetaAdicionalSolicitadaDTO> tarjetas) {
        MensajeRespuestaDTO mensaje = null;
        List<String> tiposTarjeta = new ArrayList<String>();
        for (TarjetaAdicionalSolicitadaDTO tarjeta : tarjetas) {
            tiposTarjeta.add(tarjeta.getTipoCuenta());
        }
        mensaje = setMensaje(tarjetas);
        mensaje.setTiposTarjetas(tiposTarjeta);
        return mensaje;
    }

    /**
     * Sets the mensaje.
     *
     * @param tarjetas
     *            the tarjetas
     * @return the mensaje respuesta DTO
     */
    private MensajeRespuestaDTO setMensaje(List<TarjetaAdicionalSolicitadaDTO> tarjetas) {
        MensajeRespuestaDTO mensaje = new MensajeRespuestaDTO();
        int errores = 0;
        for (TarjetaAdicionalSolicitadaDTO tarjeta : tarjetas) {
            if (tarjeta.isConError()) {
                errores++;
            }
        }
        setMensaje(tarjetas, mensaje, errores);
        return mensaje;
    }

    /**
     * Sets the mensaje.
     *
     * @param tarjetas
     *            the tarjetas
     * @param mensaje
     *            the mensaje
     * @param errores
     *            the errores
     */
    private void setMensaje(List<TarjetaAdicionalSolicitadaDTO> tarjetas, MensajeRespuestaDTO mensaje, int errores) {
        if (errores == 0 && tarjetas.size() == 1) {
            mensaje.setCodigoMensaje(UNA_DE_UNA_OK);
            mensaje.setResultado(RESULTADO_OK);
        } else if (errores == 0 && tarjetas.size() > 1) {
            mensaje.setCodigoMensaje(DOS_DE_DOS_OK);
            mensaje.setResultado(RESULTADO_OK);
        } else if (errores == 1 && tarjetas.size() == 1) {
            mensaje.setCodigoMensaje(UNA_DE_UNA_ERROR);
            mensaje.setResultado(RESULTADO_ERROR);
        } else if (errores > 1 && tarjetas.size() > 1) {
            mensaje.setCodigoMensaje(DOS_DE_DOS_ERROR);
            mensaje.setResultado(RESULTADO_ERROR);
        } else {
            mensaje.setCodigoMensaje(ALGUNA_CON_ERROR);
            mensaje.setResultado(RESULTADO_WARNING);
        }
    }

    /**
     * Generar comprobante.
     *
     * @param view
     *            the view
     * @return the respuesta
     */
    @Override
    public Respuesta<Reporte> generarComprobante(ComprobanteAltaTarjCredAdicionalView view) {
        Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
        Reporte reporte = altaTarjetaCreditoAdicionalDAO.generarComprobante(view);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(reporte);
        return respuesta;
    }

    /**
     * Gets the nro cuenta producto.
     *
     * @param nroCtaProducto
     *            the nro cta producto
     * @param marca
     *            the marca
     * @return the nro cuenta producto
     */
    private String getNroCuentaProducto(Cuenta cuenta, String marca) {
        String nroCtaProductoFormateado = "";
        if (MARCA_VISA.equals(marca)) {
            nroCtaProductoFormateado = ISBANStringUtils.formateadorConCerosIzq(cuenta.getNroCuentaProducto(), 10);
        } else if (MARCA_AMEX.equals(marca)){
            nroCtaProductoFormateado = ISBANStringUtils.formateadorConCerosIzq(cuenta.getNroCuentaProducto(), 9) + "0";
        } else if (MARCA_MASTER.equals(marca)) {
        	nroCtaProductoFormateado = ISBANStringUtils.formateadorConCerosIzq(cuenta.getNroCuentaProducto(), 9) + cuenta.getCbu().charAt(18);
        }
        return nroCtaProductoFormateado;
    }
}
