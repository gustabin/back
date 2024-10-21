/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApi;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountDetailsEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountsResponseEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.BalancesEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCuentaEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaInexistenteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaMigradaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class DetalleCuentaDAOImpl.
 */
@Component
public class DetalleCuentaDAOImpl extends IatxBaseDAO implements DetalleCuentaDAO {

    /** The api-accounts */
    @Autowired
    private AccountsApi accountsApi;

    /** The ModuloPermiso DAO. */
    @Autowired
    private ModuloPermisoDAO moduloPermisoDAO;


    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;
    
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

    /** The servicio cnsctadato. */
    @Value("${SERVICIO.PREFIJO.CNSCTADATO}")
    private String servicioCnsctadato;

    /** The version cnsctadato. */
    @Value("${SERVICIO.VERSION.CNSCTADATO}")
    private String versionCnsctadato;

    /** The version cnsctatit. */
    @Value("${SERVICIO.PREFIJO.CNSCTATIT}")
    private String servicioCnsctatit;

    /** The version cnsctatit. */
    @Value("${SERVICIO.VERSION.CNSCTATIT}")
    private String versionCnsctatit;
    
    /** The canal banca privada. */
	@Value("${CANAL.BANCA.PRIVADA}")
	private String canalBP; 
	
	/** The sub canal banca privada. */
	@Value("${SUB.CANAL.BANCA.PRIVADA}")
	private String subCanalBP;

    /** The Constant COBERTURA_INDEX. */
    private static final int COBERTURA_INDEX = 6;

    /** The Constant POSICIONAMIENTO_INDEX. */
    private static final int POSICIONAMIENTO_INDEX = 7;

    /** The Constant SALDO_PESOS_INDEX. */
    private static final int SALDO_PESOS_INDEX = 8;

    /** The Constant DEPOSITO_24_HORAS_PESOS_INDEX. */
    private static final int DEPOSITO_24_HORAS_PESOS_INDEX = 11;

    /** The Constant DEPOSITO_48_HORAS_PESOS_INDEX. */
    private static final int DEPOSITO_48_HORAS_PESOS_INDEX = 12;

    /** The Constant DEPOSITO_72_HORAS_PESOS_INDEX. */
    private static final int DEPOSITO_72_HORAS_PESOS_INDEX = 13;

    /** The Constant INTERESES_GANADOS_PESOS_INDEX. */
    private static final int INTERESES_GANADOS_PESOS_INDEX = 14;

    /** The Constant LIMITE_ACUERDO_CTA_CTE_PESOS_INDEX. */
    private static final int LIMITE_ACUERDO_CTA_CTE_PESOS_INDEX = 15;

    /** The Constant SALDO_DOLARES_INDEX. */
    private static final int SALDO_DOLARES_INDEX = 18;

    /** The Constant DEPOSITO_24_HORAS_DOLARES_INDEX. */
    private static final int DEPOSITO_24_HORAS_DOLARES_INDEX = 21;

    /** The Constant DEPOSITO_48_HORAS_DOLARES_INDEX. */
    private static final int DEPOSITO_48_HORAS_DOLARES_INDEX = 22;

    /** The Constant DEPOSITO_72_HORAS_DOLARES_INDEX. */
    private static final int DEPOSITO_72_HORAS_DOLARES_INDEX = 23;

    /** The Constant INTERESES_GANADOS_DOLARES_INDEX. */
    private static final int INTERESES_GANADOS_DOLARES_INDEX = 24;

    /** The Constant LIMITE_ACUERDO_CTA_CTE_DOLARES_INDEX. */
    private static final int LIMITE_ACUERDO_CTA_CTE_DOLARES_INDEX = 25;

    /** The Constant TOPE_EXTRACCION_SEMANAL_INDEX. */
    private static final int TOPE_EXTRACCION_SEMANAL_INDEX = 40;

    /** The Constant ACUMULADO_EXTRACCION_SEMANAL_INDEX. */
    private static final int ACUMULADO_EXTRACCION_SEMANAL_INDEX = 36;

    /** The Constant DISPONIBLE_CUENTA_PESOS. */
    private static final int DISPONIBLE_CUENTA_PESOS = 38;

    /** The Constant SALDO_ACTE_INDEX. */
    private static final int SALDO_ACTE_INDEX = 29;

    /** The Constant SALDO_ACAH_INDEX. */
    private static final int SALDO_ACAH_INDEX = 30;

    /** The Constant SALDO_ACCD_INDEX. */
    private static final int SALDO_ACCD_INDEX = 31;

    /** The Constant SALDO_ACAD_INDEX. */
    private static final int SALDO_ACAD_INDEX = 32;

    /** The Constant PETICION_CAMBIO_INDEX. */
    private static final int PETICION_CAMBIO_INDEX = 28;

    /** The Constant PETICION_CAMBIO_START_INDEX. */
    private static final int PETICION_CAMBIO_START_INDEX = 1;

    /** The Constant SALDO_CUENTA_PESOS_INDEX. */
    private static final int SALDO_CUENTA_PESOS_INDEX = 8;

    /** The Constant SALDO_CUENTA_DOLARES. */
    private static final int SALDO_CUENTA_DOLARES = 18;

    /** The Constant DIRECCIONA_CA_INDEX. */
    private static final int DIRECCIONA_CA_INDEX = 28;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DetalleCuentaDAOImpl.class);

    /** The Constant CUENTA_UNICA_PESOS. */
    private static final String CUENTA_UNICA_PESOS = "09";

    /** The Constant CUENTA_UNICA_DOLARES. */
    private static final String CUENTA_UNICA_DOLARES = "10";
    
    private static final String CURRENCY_ARS = "ars";
    
    private static final String CURRENCY_USD = "usd";
    
    private static final String TYPE_MAIN = "main";

    /** The servicio cnsctadato. */
    @Value("${SERVICIO.PREFIJO.CNSCTASDOM}")
    private String servicioCnsctasdom;

    /** The version cnsctadato. */
    @Value("${SERVICIO.VERSION.CNSCTASDOM150}")
    private String versionCnsctasdom150;

    /** The Constant SUCURSAL_LARGO. */
    private static final int SUCURSAL_LARGO = 3;

    /** The Constant CUENTA_LARGO. */
    private static final int CUENTA_LARGO = 7;

    /** The Constant CNSDCTADATO. */
    private static final String CNSDCTADATO = "CNSCTADATO";

    /** The Constant VERSION_211. */
    private static final String VERSION_220 = "220";

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;
    
	/** The Constant ERROR_CUENTA_MIGRADA. */
	private static final int ERROR_CUENTA_MIGRADA = 10010094; // Cierre de sucursales    
	
	private static final String HOST_NOCTURNO = "N";
	
	private static final String HOST_DIURNO = "D";
	
	private static final String SALDO_CU_PESOS = "SALDO_CU_PESOS";
    
	private static final String SALDO_CU_DOLARES = "SALDO_CU_DOLARES";
	
	private static final String SALDO_CUENTA = "SALDO_CUENTA";

    private static final String PESOS = "ars";

    private static final String DOLARES = "usd";

    private static final String C_CORRIENTE = "current";

    private static final String C_AHORRO = "saving";

    private static final String SOBREGIRO = "overdraft";

    private static final String PETICION_CAMBIO = "account_positioning";

    private static final String LIMITE_ACUERDO = "total_agreement";

    private static final String REAL = "real";

    private static final String MAIN = "main";

    private static final String DEPOSITO = "withholding";


    /*
>>>>>>> feature/adopcion-api-accounts-ctadato
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO#
     * consultaDetalleCuenta(ar.com.santanderrio.obp.servicios.cuentas.entities.
     * ConsultaDetalleCuentaInEntity)
     */
    @Override
    public ConsultaDetalleCuentaOutEntity consultaDetalleCuenta(
            ConsultaDetalleCuentaInEntity consultaDetalleCuentaInEntity) throws DAOException {

        AccountEntity cuenta = null;
        ConsultaDetalleCuentaOutEntity cuentaEntity = null;

        Map<String, ModuloPermiso> modulosPermisos = moduloPermisoDAO.obtenerModulosPermisos();
        ModuloPermiso moduloPermiso = modulosPermisos.get(AccionController.CONSUMO_API_ACCOUNTS.getDescripcion());

        if(moduloPermiso != null && moduloPermiso.tienePermisoDeVisibilidad()){
            try {
                LOGGER.info("Se consume API Accounts para el usuario-NUP: {}", consultaDetalleCuentaInEntity.getCliente().getNup());
                String accountNumber = consultaDetalleCuentaInEntity.getEntidad() + consultaDetalleCuentaInEntity.getSucursal() + consultaDetalleCuentaInEntity.getNroCuentaAdd();
                cuenta = accountsApi.getAccountByAccountId(accountNumber, consultaDetalleCuentaInEntity.getCliente().getNup());
                cuentaEntity = toConsultaDetalleCuentaOutEntityEntity(cuenta);
            }catch (ApiException e) {
                LOGGER.error("Error al consultar Api", e);
                throw new DAOException();
            }
        } else {
            LOGGER.info("Se consume CNSCTADATO para el usuario-NUP: {}", consultaDetalleCuentaInEntity.getCliente().getNup());
            IatxRequest iatxRequest = new IatxRequest(CNSDCTADATO, VERSION_220);

            try {
                IatxRequestData iatxRequestData = generateRequestDataCNSCTADATO(consultaDetalleCuentaInEntity);
                iatxRequest.setData(iatxRequestData);
                IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
                int errorCode = iatxResponse.getErrorCode();
                if (OK_CODIGO_RETORNO == errorCode) {
                    cuentaEntity = processTrama(iatxResponse.getTrama(), ConsultaDetalleCuentaOutEntity.class);
                } else {
                    LOGGER.error("Error en el servicio, " + iatxResponse.getErrorMessage());
                    throw new DAOException(iatxResponse.getErrorMessage());
                }
            } catch (IatxException e) {
                if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
                    LOGGER.error(e.getMessage(), e);
                    throw new TimeOutException(e.getMessage());
                } else {
                    LOGGER.error(e.getMessage(), e);
                    throw new DAOException(e);
                }
            }
        }
        return cuentaEntity;
    }

    /**
     * Realiza un parse de la respuesta de API y lo acomoda al entity
     * @param cuenta
     * @return ConsultaDetalleCuentaOutEntity
     */
    private ConsultaDetalleCuentaOutEntity toConsultaDetalleCuentaOutEntityEntity(AccountEntity cuenta){
        ConsultaDetalleCuentaOutEntity cuentaEntity = new ConsultaDetalleCuentaOutEntity();

        cuentaEntity.setSaldoCuenta(getEnBalances(cuenta.getBalances(), REAL, PESOS));
        cuentaEntity.setSaldoCuentaUSD(getEnBalances(cuenta.getBalances(), REAL, DOLARES));
        cuentaEntity.setDepositoEfectivo(getEnBalances(cuenta.getBalances(), DEPOSITO, PESOS));
        cuentaEntity.setDisponibleARSCuenta(getEnBalances(cuenta.getBalances(), REAL, PESOS));
        cuentaEntity.setDisponibleUSDCuenta(getEnBalances(cuenta.getBalances(), REAL, DOLARES));
        cuentaEntity.setLimiteAcuerdoCC(getEnBalances(cuenta.getBalances(), LIMITE_ACUERDO, PESOS));
        cuentaEntity.setLimiteAcuerdoCCUSD(getEnBalances(cuenta.getBalances(), LIMITE_ACUERDO, DOLARES));
        cuentaEntity.setIndicadorDireccionaCAPeticionCambio(cuenta.getTags().contains(PETICION_CAMBIO)? "A " : "N ");
        cuentaEntity.setIndSobregiro(cuenta.getTags().contains(SOBREGIRO)? "S" : "N");
        cuentaEntity.setSaldoACTE(getEnDetalleCuentas(cuenta.getAccountDetails(), C_CORRIENTE, REAL, PESOS));
        cuentaEntity.setSaldoACAH(getEnDetalleCuentas(cuenta.getAccountDetails(), C_AHORRO, REAL, PESOS));
        cuentaEntity.setSaldoACCD(getEnDetalleCuentas(cuenta.getAccountDetails(), C_CORRIENTE, REAL, DOLARES));
        cuentaEntity.setSaldoACAD(getEnDetalleCuentas(cuenta.getAccountDetails(), C_AHORRO, REAL, DOLARES));
        cuentaEntity.setFechaApertura(cuenta.getCreationDate().substring(0,10));
        cuentaEntity.setFechaAperturaUSD(cuenta.getCreationDate().substring(0,10));

        return cuentaEntity;
    }

    /**
     * Busca monto en array de balances por tipo de cuentas
     * @param detalleCuentas
     * @param tipoCuenta
     * @param tipo
     * @param moneda
     * @return String monto
     */
    private String getEnDetalleCuentas(List<AccountDetailsEntity> detalleCuentas, String tipoCuenta,
                                       String tipo, String moneda){
        String monto = "";

        for(AccountDetailsEntity detalleCuenta: detalleCuentas){
            if(detalleCuenta.getType().equals(tipoCuenta) && detalleCuenta.getCurrency().equals(moneda)){
                monto = getEnBalances(detalleCuenta.getBalances(), tipo, moneda);
            }
        }
        return monto;
    }

    /**
     * Busca monto en array de balances
     * @param balances
     * @param tipo
     * @param moneda
     * @return
     */
    private String getEnBalances(List<BalancesEntity> balances, String tipo, String moneda){
        String monto = "";

        for(BalancesEntity balance: balances){
            if(balance.getType().equals(tipo) && balance.getCurrency().equals(moneda)){
                monto = balance.getAmount().toString().replace(".", "");
            }
        }
        return monto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO#
     * obtenerDetalleCuenta(ar.com.santanderrio.obp.servicios.cuentas.entities.
     * Cuenta)
     */
    @Override
    public Respuesta<DetalleCuentaEntity> obtenerDetalleCuenta(Cuenta cuenta) {

        Respuesta<DetalleCuentaEntity> respuestaDetalleCuenta = new Respuesta<DetalleCuentaEntity>();

        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        try {
            IatxRequest request = new IatxRequest(servicioCnsctadato, versionCnsctadato);

            IatxRequestData requestData = new IatxRequestData(cuenta.getCliente());

            requestData.addBodyValue(cuenta.getOficinaAltair());
            requestData.addBodyValue(cuenta.getContratoAltair());
            String direccionaCASession = cuenta.getEstadoTarjetaCredito().substring(0, 1);
            if ("N".equals(direccionaCASession)) {
                requestData.addBodyValue("S");
            } else {
                requestData.addBodyValue("N");
            }

            requestData.addBodyValue(cuenta.getTipoCuenta());

            request.setData(requestData);

            IatxResponse iatxResponse = iatxComm.exec(request);

            DetalleCuentaEntity detalleCuenta = parsearDetalleCuenta(iatxResponse, direccionaCASession);

            itemMensajeRespuesta.setMensaje(iatxResponse.getErrorMessage());
            if (iatxResponse.getTipoError() != null) {
                itemMensajeRespuesta.setTipoError(iatxResponse.getTipoError().getDescripcion());
            }

            respuestaDetalleCuenta.add(itemMensajeRespuesta);
            respuestaDetalleCuenta.setRespuesta(detalleCuenta);
            respuestaDetalleCuenta.setEstadoRespuesta(iatxResponse.getEstadoRespuesta());
        } catch (IatxException e) {
            LOGGER.error("Error en IATX", e);
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());

            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaDetalleCuenta.add(itemMensajeRespuesta);
        } catch (Exception e) {
            LOGGER.error("Resultado no esperado", e);
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaDetalleCuenta.add(itemMensajeRespuesta);
        }

        return respuestaDetalleCuenta;
    }


    /**
     * Parsear detalle cuenta.
     *
     * TODO log ficticio para mantener unused parameter
     * 
     * @param iatxResponse
     *            the iatx response
     * @param direccionaCASession
     *            the direcciona ca session
     * @return the detalle cuenta
     */
    private DetalleCuentaEntity parsearDetalleCuenta(IatxResponse iatxResponse, String direccionaCASession) {
        LOGGER.info(direccionaCASession);
        DetalleCuentaEntity detalleCuenta = new DetalleCuentaEntity();
        try {
            detalleCuenta.setCobertura(ISBANStringUtils.coberturaCanonico(iatxResponse.getData(COBERTURA_INDEX)));
            detalleCuenta.setPosicionamiento(
                    ISBANStringUtils.posicionamientoCanonico(iatxResponse.getData(POSICIONAMIENTO_INDEX)));
            detalleCuenta
                    .setSaldoPesos(ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(SALDO_PESOS_INDEX)));
            detalleCuenta.setDeposito24HorasPesos(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(DEPOSITO_24_HORAS_PESOS_INDEX)));
            detalleCuenta.setDeposito48HorasPesos(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(DEPOSITO_48_HORAS_PESOS_INDEX)));
            detalleCuenta.setDeposito72HorasPesos(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(DEPOSITO_72_HORAS_PESOS_INDEX)));
            detalleCuenta.setInteresesGanadosPesos(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(INTERESES_GANADOS_PESOS_INDEX)));
            detalleCuenta.setLimiteAcuerdoCtaCtePesos(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(LIMITE_ACUERDO_CTA_CTE_PESOS_INDEX)));

            detalleCuenta.setSaldoDolares(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(SALDO_DOLARES_INDEX)));
            detalleCuenta.setDeposito24HorasDolares(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(DEPOSITO_24_HORAS_DOLARES_INDEX)));
            detalleCuenta.setDeposito48HorasDolares(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(DEPOSITO_48_HORAS_DOLARES_INDEX)));
            detalleCuenta.setDeposito72HorasDolares(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(DEPOSITO_72_HORAS_DOLARES_INDEX)));
            detalleCuenta.setInteresesGanadosDolares(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(INTERESES_GANADOS_DOLARES_INDEX)));
            detalleCuenta.setLimiteAcuerdoCtaCteDolares(ISBANStringUtils
                    .importePtoFijo2Canonico(iatxResponse.getData(LIMITE_ACUERDO_CTA_CTE_DOLARES_INDEX)));
            detalleCuenta.setTopeExtraccionSemanal(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(TOPE_EXTRACCION_SEMANAL_INDEX)));
            detalleCuenta.setAcumuladoExtraccionSemanal(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(ACUMULADO_EXTRACCION_SEMANAL_INDEX)));
            detalleCuenta.setDisponibleCuentaPesos(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(DISPONIBLE_CUENTA_PESOS)));

            // para nuevo paquete
            // saldo saldoACTE
            detalleCuenta
                    .setSaldoACTE(ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(SALDO_ACTE_INDEX)));
            // saldo saldoACAH
            detalleCuenta
                    .setSaldoACAH(ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(SALDO_ACAH_INDEX)));
            // saldo saldoACCD
            detalleCuenta
                    .setSaldoACCD(ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(SALDO_ACCD_INDEX)));
            // saldo saldoACAD
            detalleCuenta
                    .setSaldoACAD(ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(SALDO_ACAD_INDEX)));
            // lo tomo del login. en el detalle nunca viene el blanco
            detalleCuenta.setDireccionaCA(iatxResponse.getData(DIRECCIONA_CA_INDEX));
            detalleCuenta.setPeticionCambio(
                    iatxResponse.getData(PETICION_CAMBIO_INDEX).substring(PETICION_CAMBIO_START_INDEX));

            detalleCuenta.setSaldoCuentaPesos(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(SALDO_CUENTA_PESOS_INDEX)));
            detalleCuenta.setSaldoCuentaDolares(
                    ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getData(SALDO_CUENTA_DOLARES)));
        } catch (RuntimeException e) {
            // en el caso de error, por ahora devolvemos vacio
            LOGGER.error("Error al parsear trama", e);
            detalleCuenta = null;
        }

        return detalleCuenta;
    }

    /**
	 * Metodo verificarCuenta. Usa CNSCTATIT v110. Ref: US 14435
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param sucursalCuenta
	 *            the sucursal cuenta
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the cliente
	 * @throws DAOException
	 *             the DAO exception
	 * @throws CuentaMigradaException
	 *             the cuenta migrada exception
	 */
    @Override
    public Cliente obtenerCuenta(Cliente cliente, String tipoCuenta, String sucursalCuenta, String nroCuenta)
            throws DAOException, CuentaMigradaException {
        Cliente respuestaCliente = null;
        try {
            IatxRequest request = new IatxRequest(servicioCnsctatit, versionCnsctatit);
            IatxRequestData requestData = new IatxRequestData(cliente);

            requestData.addBodyValue(tipoCuenta);
            requestData.addBodyValue(sucursalCuenta);
            requestData.addBodyValue(nroCuenta);
            requestData.addBodyValue("1");

            if(CuentasBancaPrivadaUtil.isCuentaBP(sucursalCuenta)) {
            	requestData.setCanalTipo(canalBP);
            	requestData.setSubCanalTipo(subCanalBP);
            }

            request.setData(requestData);
            IatxResponse iatxResponse = iatxComm.exec(request);
            if (iatxResponse.getErrorCode() == 0) {
                respuestaCliente = parsearCliente(iatxResponse);
            } else if (iatxResponse.getErrorCode() == ERROR_CUENTA_MIGRADA) {
				// Cierre de sucursales
				LOGGER.info("El codigo de error no es {}, retorna null", iatxResponse.getErrorCode());
				LOGGER.error(iatxResponse.getErrorMessage());
				throw new CuentaMigradaException(iatxResponse.getErrorMessage());       	
            } else {
                LOGGER.info("El codigo de error no es {}, retorna null", iatxResponse.getErrorCode());
                return null;
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return respuestaCliente;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO#
     * obtenerCuenta2(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Cliente obtenerCuenta2(Cliente cliente, String tipoCuenta, String sucursalCuenta, String nroCuenta)
            throws DAOException, CuentaInexistenteException, CuentaMigradaException {
        Cliente respuestaCliente = null;
        try {
            IatxRequest request = new IatxRequest(servicioCnsctatit, versionCnsctatit);
            IatxRequestData requestData = new IatxRequestData(cliente);

            requestData.addBodyValue(tipoCuenta);
            requestData.addBodyValue(sucursalCuenta);
            requestData.addBodyValue(nroCuenta);
            requestData.addBodyValue("1");

            if(CuentasBancaPrivadaUtil.isCuentaBP(sucursalCuenta)) {
            	requestData.setCanalTipo(canalBP);
            	requestData.setSubCanalTipo(subCanalBP);
            }

            request.setData(requestData);
            IatxResponse iatxResponse = iatxComm.exec(request);
            
            switch (iatxResponse.getErrorCode()) {
            case 0:
                respuestaCliente = parsearCliente(iatxResponse);
                return respuestaCliente;
			case ERROR_CUENTA_MIGRADA:
				 // Cierre de sucursales
                LOGGER.info("El codigo de error no es {}, retorna null", iatxResponse.getErrorCode());
                LOGGER.error(iatxResponse.getErrorMessage());
                throw new CuentaMigradaException(iatxResponse.getErrorMessage());            	
            case 10000054:
                LOGGER.info("El codigo de error no es {}, retorna null", iatxResponse.getErrorCode());
                LOGGER.error(iatxResponse.getErrorMessage());
                throw new CuentaInexistenteException(iatxResponse.getErrorMessage());
            default:
                LOGGER.info("El codigo de error no es {}, retorna null", iatxResponse.getErrorCode());
                LOGGER.error(iatxResponse.getErrorMessage());
                throw new DAOException();
            }

        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO#
     * obtenerDatosCliente(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta,
     * java.lang.String, java.lang.String)
     */
    @Override
    public DatosCliente obtenerDatosCliente(Cliente cliente, TipoCuenta tipoCuenta, String sucursalCuenta,
            String nroCuenta) throws DAOException {
        DatosCliente datosCliente = null;
        try {
            IatxRequest request = new IatxRequest(servicioCnsctatit, versionCnsctatit);
            IatxRequestData requestData = new IatxRequestData(cliente);

            String topoCuentaString = StringUtils.leftPad(tipoCuenta.getCodigo().toString(), 2, '0');

            requestData.addBodyValue(topoCuentaString);
            requestData.addBodyValue(sucursalCuenta);
            requestData.addBodyValue(nroCuenta);
            requestData.addBodyValue("1");

            request.setData(requestData);
            IatxResponse iatxResponse = iatxComm.exec(request);
            if (iatxResponse.getErrorCode() == 0) {
                datosCliente = parsearDatosCliente(iatxResponse);
            } else {
                throw new DAOException();
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return datosCliente;
    }

    /**
     * Parsear datos cliente.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the datos cliente
     */
    private DatosCliente parsearDatosCliente(IatxResponse iatxResponse) {
        DatosCliente datosCliente = new DatosCliente();
        datosCliente.setNombre(iatxResponse.getNextData());
        datosCliente.setTipoCUILCUIT(iatxResponse.getNextData());
        datosCliente.setNumeroCUILCUIT(iatxResponse.getNextData());
        datosCliente.setNup(iatxResponse.getNextData());
        return datosCliente;
    }

    /**
     * Parser del resultado de obtener cuenta de CNSCTATIT. Ref: US 14435
     *
     * @param iatxResponse
     *            the iatx response
     * @return the cliente
     */
    private Cliente parsearCliente(IatxResponse iatxResponse) {
        Cliente cliente = new Cliente();
        cliente.setNombre(iatxResponse.getNextData());
        cliente.setTipoCUILCUIT(iatxResponse.getNextData());
        cliente.setNumeroCUILCUIT(iatxResponse.getNextData());
        cliente.setNup(iatxResponse.getNextData());
        return cliente;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO#
     * actualizarSaldo(java.util.List,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, int)
     */
    @Override
    public List<Cuenta> actualizarSaldo(List<Cuenta> cuentasMonetarias, Cliente cliente, int cantidadCuentas)
            throws DAOException {
    	
        LOGGER.info("Se obtiene el permiso para saber si consultar API o IATX");
    	 Map<String, ModuloPermiso> modulosPermisos = moduloPermisoDAO.obtenerModulosPermisos();
         ModuloPermiso moduloPermiso = modulosPermisos.get(AccionController.UTILIZAR_API_CUENTAS.getDescripcion());
    	
         if(moduloPermiso != null && moduloPermiso.tienePermisoDeVisibilidad()) {
        	return actualizarCuentasViaApi(cliente.getNup(), cuentasMonetarias);
         } else {
        	return actualizarCuentasViaIATX(cliente, cantidadCuentas, cuentasMonetarias);  
         }
    }
    
    
    private List<Cuenta> actualizarCuentasViaApi (String nup, List<Cuenta> cuentasMonetarias) throws DAOException{
    	
   	 List<Cuenta> cuentasSaldoActualizado = new ArrayList<Cuenta>();
	  	try {
	    	AccountsResponseEntity cuentasApi = accountsApi.getAccountsByCustomerId(nup);
	    	cuentasSaldoActualizado = actualizarSaldosCuentas(cuentasMonetarias, cuentasApi.getAccounts());
	    }catch (ApiException e) {
	    	LOGGER.error("Error al consultar Api", e);
	    	throw new DAOException();
	    }
 	 return cuentasSaldoActualizado;
    	
    }
    
    
    private List<Cuenta> actualizarSaldosCuentas(List<Cuenta> cuentasMonetarias, List<AccountEntity> cuentasApi) {
    	
    	List<Cuenta> cuentasSaldoActualizado = new ArrayList<Cuenta>();
    	
    	for (AccountEntity cuentaApi : cuentasApi) {
    		for (Cuenta cuenta : cuentasMonetarias) {
    			if (cuentaApi.getBranch().equals(cuenta.getNroSucursal()) && 
    				StringUtils.right(cuentaApi.getAccountNumber(), CUENTA_LARGO).equals(StringUtils.right(cuenta.getNroCuentaProducto(), CUENTA_LARGO))) {
    				if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))) {
    					actualizarSaldosCuentaUnica(cuenta, cuentaApi);
    				} else {
    					actualizarSaldoCuenta(cuenta, cuentaApi);
    				}
    				cuentasSaldoActualizado.add(cuenta);
    			}
    		}
    	}
    	
    	return cuentasSaldoActualizado;	
    }

    private void actualizarSaldosCuentaUnica(Cuenta cuenta, AccountEntity cuentaApi) {
    	
    	for (BalancesEntity balance : cuentaApi.getBalances()) {
if (CURRENCY_ARS.equals(balance.getCurrency()) && REAL.equals(balance.getType())) {
    			actualizarSaldoEnCampoCorrecto(cuenta, balance, SALDO_CU_PESOS);
    		} else if (CURRENCY_USD.equals(balance.getCurrency())) {
    			actualizarSaldoEnCampoCorrecto(cuenta, balance, SALDO_CU_DOLARES);
    		}
    	}
    	
    }
    
    private void actualizarSaldoCuenta(Cuenta cuenta, AccountEntity cuentaApi) {
    	
    	for (BalancesEntity balance : cuentaApi.getBalances()) {
    		actualizarSaldoEnCampoCorrecto(cuenta, balance, SALDO_CUENTA);
    		}     	
    }
    
    private void actualizarSaldoEnCampoCorrecto (Cuenta cuenta, BalancesEntity balance, String campoActualizar) {
    	
	    if (REAL.equals(balance.getType())) {
			if (SALDO_CU_PESOS.equals(campoActualizar)) {
				cuenta.setSaldoCUPesos(balance.getAmount().toString());
			} else if (SALDO_CU_DOLARES.equals(campoActualizar)) {
				cuenta.setSaldoCUDls(balance.getAmount().toString());
			} else {
				cuenta.setSaldoCuenta(balance.getAmount().toString());
			}
		}
	}
    
    private List<Cuenta> actualizarCuentasViaIATX(Cliente cliente, int cantidadCuentas, List<Cuenta> cuentasMonetarias) throws DAOException {
        IatxRequest request = new IatxRequest(servicioCnsctasdom, versionCnsctasdom150);
        IatxRequestData requestData = new IatxRequestData(cliente);
        requestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(String.valueOf(cantidadCuentas), 3));

        for (int i = 0; i < cuentasMonetarias.size(); i++) {
            if ("02".equals(cuentasMonetarias.get(i).getTipoCuenta())) {
                requestData.addBodyValue(CUENTA_UNICA_PESOS);
                requestData.addBodyValue(ISBANStringUtils.formatearSucursal(cuentasMonetarias.get(i).getNroSucursal()));
                requestData.addBodyValue(String.valueOf(
                        ISBANStringUtils.formateadorConCerosIzq(cuentasMonetarias.get(i).getNroCuentaProducto(), 7)));
                requestData.addBodyValue(CUENTA_UNICA_DOLARES);
                requestData.addBodyValue(ISBANStringUtils.formatearSucursal(cuentasMonetarias.get(i).getNroSucursal()));
                requestData.addBodyValue(String.valueOf(
                        ISBANStringUtils.formateadorConCerosIzq(cuentasMonetarias.get(i).getNroCuentaProducto(), 7)));
            }
            if (!"02".equals(cuentasMonetarias.get(i).getTipoCuenta())) {
                requestData.addBodyValue(StringUtils
                        .leftPad(String.valueOf(Integer.parseInt(cuentasMonetarias.get(i).getTipoCuenta())), 2, '0'));
                requestData.addBodyValue(ISBANStringUtils.formatearSucursal(cuentasMonetarias.get(i).getNroSucursal()));
                requestData.addBodyValue(String.valueOf(
                        ISBANStringUtils.formateadorConCerosIzq(cuentasMonetarias.get(i).getNroCuentaProducto(), 7)));
            }

        }
        request.setData(requestData);
        try {
            IatxResponse iatxResponse = iatxComm.exec(request);
            if (iatxResponse.getErrorCode() == 0) {
	            	return procesarRespuestaSaldo(cuentasMonetarias, iatxResponse);
            } else {
                throw new IatxException("Error de Iatx");
            }

        } catch (IatxException e) {
            LOGGER.error("Error de Iatx", e);
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error("Error inesperado", e);
            throw new DAOException(e);
        }
     	
    }
    
    /**
     * Setea los saldos segun la cuenta original.
     *
     * @param cuentasMonetarias
     *            the cuentas monetarias
     * @param iatxResponse
     *            the iatx response
     * @return the list
     */
    private List<Cuenta> procesarRespuestaSaldo(List<Cuenta> cuentasMonetarias, IatxResponse iatxResponse) {
        List<Cuenta> cuentasSaldoActualizado = new ArrayList<Cuenta>();
        // Estado
        //String estadoHostNocturno = iatxResponse.getNextData() != null ? iatxResponse.getNextData() : "";
        String estadoHostNocturno = iatxResponse.getNextData();
        
        if (HOST_NOCTURNO.equals(estadoHostNocturno)) {
        	sesionCliente.getCliente().setHostNocturno(true);
        }else if (HOST_DIURNO.equals(estadoHostNocturno)) {
        	sesionCliente.getCliente().setHostNocturno(false);
        }
        int cantidadCuentas = Integer.parseInt(iatxResponse.getNextData().trim());
        for (int i = 0; i < cantidadCuentas; i++) {
            String tipo = iatxResponse.getNextData().trim();
            String sucursal = iatxResponse.getNextData().trim();
            String numero = iatxResponse.getNextData();
            String saldoSigno = ISBANStringUtils
                    .importePtoFijo2Canonico(iatxResponse.getNextData() + iatxResponse.getNextData());
            for (Cuenta cuenta : cuentasMonetarias) {
                if (StringUtils.right(cuenta.getNroSucursal(), SUCURSAL_LARGO).equalsIgnoreCase(sucursal)
                        && StringUtils.right(cuenta.getNroCuentaProducto(), CUENTA_LARGO).equalsIgnoreCase(numero)) {
                    if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))) {
                        if (CUENTA_UNICA_PESOS.equalsIgnoreCase(tipo)) {
                            cuenta.setSaldoCUPesos(saldoSigno);
                        } else {
                            cuenta.setSaldoCUDls(saldoSigno);
                        }
                    } else {
                        cuenta.setSaldoCuenta(saldoSigno);
                    }
                    cuentasSaldoActualizado.add(cuenta);
                    break;
                }
            }
            iatxResponse.getNextData();
            iatxResponse.getNextData();
            iatxResponse.getNextData();
            iatxResponse.getNextData();
            iatxResponse.getNextData();
            iatxResponse.getNextData();
            iatxResponse.getNextData();
        }

        return cuentasSaldoActualizado;
    }

    /**
     * Generate request data CNSPFTIPO.
     *
     * @param consultaDetalleCuentaInEntity
     *            the consulta detalle cuenta in entity
     * @return el objeto para llamar al servicio IATX
     */
    private IatxRequestData generateRequestDataCNSCTADATO(ConsultaDetalleCuentaInEntity consultaDetalleCuentaInEntity) {
        IatxRequestData iatxRequestData = new IatxRequestData(consultaDetalleCuentaInEntity.getCliente());
        iatxRequestData.addBodyValue(consultaDetalleCuentaInEntity.getTipoCuenta());
        iatxRequestData.addBodyValue(consultaDetalleCuentaInEntity.getSucursalCuenta());
        iatxRequestData.addBodyValue(consultaDetalleCuentaInEntity.getNroCuenta());
        iatxRequestData.addBodyValue(consultaDetalleCuentaInEntity.getEntidad());
        iatxRequestData.addBodyValue(consultaDetalleCuentaInEntity.getSucursal());
        iatxRequestData.addBodyValue(consultaDetalleCuentaInEntity.getNroCuentaAdd());
        iatxRequestData.addBodyValue(consultaDetalleCuentaInEntity.getDivisa());
        return iatxRequestData;
    }
    
 

}
