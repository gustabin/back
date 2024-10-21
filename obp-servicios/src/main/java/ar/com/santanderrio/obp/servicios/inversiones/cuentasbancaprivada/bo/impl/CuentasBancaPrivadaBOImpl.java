/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.ConsultaTitularInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TitularOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.CuentaBloqueadaException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.DisponibleInsuficienteException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.ExcedeLimiteTransferenciaException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dao.CuentasBancaPrivadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Class CuentasBancaPrivadaBOImpl.
 */
@Component
public class CuentasBancaPrivadaBOImpl implements CuentasBancaPrivadaBO {

    private static final String PRODUCTO_CUENTA_TRANSACCIONAL = "07";

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The cuenta saldo DAO. */
    @Autowired
    private CuentaSaldoDAO cuentaSaldoDAO;

    /** The cuentas banca privada DAO. */
    @Autowired
    private CuentasBancaPrivadaDAO cuentasBancaPrivadaDAO;

    /** The alias favorito producto DAO. */
    @Autowired
    private AliasFavoritoProductoDAO aliasFavoritoProductoDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The session parametros. */
    @Autowired
    private SesionParametros sessionParametros;

    /** The mensaje bo. */
    @Autowired
    private MensajeBO mensajeBO;
    
    /** The ondeman dao. */
    @Autowired
    private OndemandDAO ondemanDao;

    /** The canal bca priv. */
    @Value("${INVERSIONES.CANAL.BANCAPRIVADA}")
    private String canalBcaPriv;
    
    /** The cantidad meses. */
    @Value("${RESUMENONDEMAND.PRIVADA.MESES}")
    private String cantidadMeses;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CuentasBancaPrivadaBOImpl.class);

    /** The Constant MAX_ALIAS_LENGTH. */
    private static final int MAX_ALIAS_LENGTH = 18;

    /** The Constant LARGO_NRO_CUENTA_DEBITO. */
    private static final int LARGO_NRO_CUENTA = 9;

    /** The Constant LARGO_NRO_CUENTA_TOTAL. */
    private static final int LARGO_NRO_CUENTA_TOTAL = 10;

    /** The LARGO_SUCURSAL. */
    private static final int LARGO_SUCURSAL = 4;

    /** The Constant TRANSFERENCIA_CORRECTA. */
    private static final String TRANSFERENCIA_CORRECTA = "10511";

    /** The Constant ERROR_NO_CONTEMPLADO_TRANSFERENCIA_BP. */
    private static final String ERROR_NO_CONTEMPLADO_TRANSFERENCIA_BP = "10512 ";

    /** The Constant TIPO_MOVIMIENTO_E. */
    private static final String TIPO_MOVIMIENTO_E = "E";

    /** The Constant TIPO_MOVIMIENTO_I. */
    private static final String TIPO_MOVIMIENTO_I = "I";

    /** The Constant MIN_VALOR_SUCURSAL_BP. */
    private static final int MIN_VALOR_SUCURSAL_BP = 250;

    /** The Constant MAX_VALOR_SUCURSAL_BP. */
    private static final int MAX_VALOR_SUCURSAL_BP = 259;

	private static final String FORMATO_FECHA = "dd/MM/yy";

	
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.
     * CuentasBancaPrivadaBO#consultarSaldosCuenta(ar.com.santanderrio.obp.servicios
     * .clientes.entities.Cliente)
     */
    @Override
    public List<CuentaIntermedioDTO> consultarSaldosCuenta(Cliente cliente) throws SQLException, BusinessException {

        List<Cuenta> cuentasBP = obtenerCuentasBancaPrivada(cliente);
        List<CuentaIntermedioDTO> listaSaldos = new ArrayList<CuentaIntermedioDTO>();
        for (Cuenta cuentaBP : cuentasBP) {
            CuentaSaldoInEntity cuentaSaldoInEntity = new CuentaSaldoInEntity(); 
            cuentaSaldoInEntity.setCuenta(ISBANStringUtils.sacarCerosYBlancosIzq(cuentaBP.getProductoAltair()) + StringUtils.right(cuentaBP.getNroCuentaProducto(), 9));

            Credential credencial = credentialSecurityFactory
                    .getCredentialBySistema(DataBase.BPRIVRACF.getNombreTarget());
            cuentaSaldoInEntity.setUsuario(credencial.getUsuario());
            cuentaSaldoInEntity.setPass(credencial.getPassword());
            CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
            cuentaIntermedioDTO.setIsCuentaUnica(cuentaBP.isCuentaUnica());
            cuentaIntermedioDTO.setTipoCuenta(cuentaBP.getTipoCuentaEnum());
            cuentaIntermedioDTO.setApodo(cuentaBP.getAlias());
            cuentaIntermedioDTO.setNumeroCuenta(cuentaBP.obtenerNroCuentaFormateado());
            cuentaIntermedioDTO.setCbu(cuentaBP.getCbu());
            cuentaIntermedioDTO.setIsCuentaTransacional(PRODUCTO_CUENTA_TRANSACCIONAL.equals(cuentaBP.getProductoAltair()));
            if(cuentaIntermedioDTO.getIsCuentaTransaccional()) {
                try {
                    cuentaIntermedioDTO
                    .setSaldosStoredProcedure(cuentaSaldoDAO.verSaldosCuentasBancaPrivada(cuentaSaldoInEntity));
                } catch (DAOException e) {
                    CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
                    cuentaSaldoOutEntity.setErrorEnConsulta(true);
                    cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
                }
            }

            try {
                cuentaIntermedioDTO.setSaldosServicioIatx(
                        consultarServicioIatxSaldosCuentas(cuentaBP, cliente, cuentaSaldoInEntity));
            } catch (DAOException e) {
                ConsultaSaldoCtasConAperturaOutEntity consultaSaldoCtasConAperturaOutEntity = new ConsultaSaldoCtasConAperturaOutEntity();
                consultaSaldoCtasConAperturaOutEntity.setErrorEnConsulta(true);
                cuentaIntermedioDTO.setSaldosServicioIatx(consultaSaldoCtasConAperturaOutEntity);
            }
            listaSaldos.add(cuentaIntermedioDTO);
        }

        return listaSaldos;
    }

    /**
     * Consultar servicio iatx saldos cuentas.
     *
     * @param cuenta
     *            the cuenta
     * @param cliente
     *            the cliente
     * @param cuentaSaldoInEntity
     *            the cuenta saldo in entity
     * @return the consulta saldo ctas con apertura out entity
     * @throws DAOException
     *             the DAO exception
     */
    private ConsultaSaldoCtasConAperturaOutEntity consultarServicioIatxSaldosCuentas(Cuenta cuenta, Cliente cliente,
            CuentaSaldoInEntity cuentaSaldoInEntity) throws DAOException {

        ConsultaSaldoCtasConAperturaInEntity datosParaConsulta = new ConsultaSaldoCtasConAperturaInEntity();

        datosParaConsulta.setCuenta("0072" + cuenta.getNroSucursal() + "0" + cuenta.getProductoAltair()
                + StringUtils.right(cuenta.getNroCuentaProducto(), 9));
        datosParaConsulta.setMoneda(cuenta.isCuentaDolares() ? "USD" : "ARS");

        return cuentasBancaPrivadaDAO.consultarSaldoCtasConApertura(datosParaConsulta, cliente, cuentaSaldoInEntity);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.
     * CuentasBancaPrivadaBO#actualizarApodo(ar.com.santanderrio.obp.servicios.
     * cuentas.entities.IdentificacionCuenta, java.lang.String,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<Void> actualizarApodo(IdentificacionCuenta idCuenta, String apodo, Cliente cliente) {
        try {
            if (apodo.length() > MAX_ALIAS_LENGTH) {
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ACTUALIZAR_ALIAS,
                        CodigoMensajeConstantes.CODIGO_ERROR_MODIFICAR_APODO_BCAPRIV);
            }
            AbstractCuenta cuenta = buscarCuentaPorId(cliente, idCuenta);
            Long nup = Long.valueOf(cliente.getNup());
            String nroCtaProducto = cuenta.getNroCuentaProducto();
            aliasFavoritoProductoDAO.actualizaAlias(nup, nroCtaProducto, apodo);
            cuenta.setAlias(apodo);
        } catch (DAOException e) {
            LOGGER.error("Error al actualizar Alias", e);
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ACTUALIZAR_ALIAS,
                    CodigoMensajeConstantes.CODIGO_ERROR_MODIFICAR_APODO_BCAPRIV);
        }
        return respuestaFactory.crearRespuestaOk(Void.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.
     * CuentasBancaPrivadaBO#buscarCuentaPorId(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta)
     */
    @Override
    public AbstractCuenta buscarCuentaPorId(Cliente cliente, IdentificacionCuenta idCuenta) {
        List<Cuenta> cuentas = obtenerCuentasBancaPrivada(cliente);
        if (cuentas != null) {
            for (Cuenta cuentaBP : cuentas) {
                if (coincideCuentaId(cuentaBP, idCuenta)) {
                    return cuentaBP;
                }
            }
        }
        return null;
    }

    /**
     * Coincide cuenta id.
     *
     * @param cuenta
     *            the cuenta
     * @param id
     *            the id
     * @return true, if successful
     */
    private boolean coincideCuentaId(AbstractCuenta cuenta, IdentificacionCuenta id) {
        boolean eqNroCuentaProducto = Long.parseLong(cuenta.getNroCuentaProducto()) == Long
                .parseLong(id.getNroCuentaProducto());
        boolean eqNroSucursal = Long.parseLong(cuenta.getNroSucursal()) == Long.parseLong(id.getNroSucursal());
        return eqNroCuentaProducto && eqNroSucursal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.
     * CuentasBancaPrivadaBO#obtenerCuentasBancaPrivada(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    public List<Cuenta> obtenerCuentasBancaPrivada(Cliente cliente) {
        List<Cuenta> cuentasBP = new ArrayList<Cuenta>();
        for (Cuenta cuentaPrivada : cliente.getCuentasPrivadas()) {
            if (cuentaPrivada.isCuentaPesos() || cuentaPrivada.isCuentaDolares() || cuentaPrivada.isCuentaUnica()) {
                cuentasBP.add(cuentaPrivada);
            }
        }
        return cuentasBP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.
     * CuentasBancaPrivadaBO#obtenerSaldosCuentas(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente)
     */
    @Override
    public List<CuentasAdhesionDebitoView> obtenerSaldosCuentas(Cliente cliente) throws BusinessException {

        List<Cuenta> cuentasBP = obtenerCuentasBancaPrivada(cliente);
        List<CuentasAdhesionDebitoView> listaSaldos = new ArrayList<CuentasAdhesionDebitoView>();
        for (Cuenta cuentaBP : cuentasBP) {
            
            if(PRODUCTO_CUENTA_TRANSACCIONAL.equals(cuentaBP.getProductoAltair())) {
                CuentaSaldoInEntity cuentaSaldoInEntity = new CuentaSaldoInEntity();
                cuentaSaldoInEntity.setCuenta(ISBANStringUtils.sacarCerosYBlancosIzq(cuentaBP.getProductoAltair()) + StringUtils.right(cuentaBP.getNroCuentaProducto(), 9));
                
                Credential credencial;
                try {
                    credencial = credentialSecurityFactory.getCredentialBySistema(DataBase.BPRIVRACF.getNombreTarget());
                } catch (SQLException e1) {
                    throw new BusinessException();
                }
                cuentaSaldoInEntity.setUsuario(credencial.getUsuario());
                cuentaSaldoInEntity.setPass(credencial.getPassword());
                
                CuentasAdhesionDebitoView cuentaSaldo = new CuentasAdhesionDebitoView();
                try {
                    CuentaSaldoOutEntity saldoCuenta = cuentaSaldoDAO.verSaldosCuentasBancaPrivada(cuentaSaldoInEntity);
                    cuentaSaldo.setNumero(cuentaBP.obtenerNroCuentaFormateado());
                    cuentaSaldo.setAlias(cuentaBP.getAlias());
                    cuentaSaldo.setDescripcionTipoCuenta(cuentaBP.getTipoCuentaEnum().getDescripcion());
                    cuentaSaldo.setSaldoPesos(ISBANStringUtils
                            .formatearSaldoConSigno(new BigDecimal(saldoCuenta.getRespuesta().get(0).getcAhorroPesos())));
                    cuentaSaldo.setSaldoDolares(ISBANStringUtils
                            .formatearSaldoConSigno(new BigDecimal(saldoCuenta.getRespuesta().get(0).getcAhorroDolares())));
                    cuentaSaldo.setAbreviaturaTipoCuenta(TipoCuenta.fromCodigo(cuentaBP.getTipoCuenta()).getAbreviatura());
                    listaSaldos.add(cuentaSaldo);
                } catch (DAOException e) {
                    throw new BusinessException();
                }
            }
        }
        return listaSaldos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.
     * CuentasBancaPrivadaBO#obtenerInterviniente(ar.com.santanderrio.obp.servicios.
     * cuentas.web.view.IntervinienteinEntity)
     */
    @Override
    public Respuesta<IntervinienteView> obtenerInterviniente(IntervinienteinEntity entity)
            throws DAOException, SQLException {

        IntervinienteView intervinienteView = new IntervinienteView();

        try {
            TitularOutEntity titularOutEntity = cuentaSaldoDAO
                    .consultaTitular(createRequestServiceInterviniente(entity));
            intervinienteView = entityToView(titularOutEntity);
        } catch (DAOException e) {
            LOGGER.error("Error al obtener la lista de comprobantes.");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
        return respuestaFactory.crearRespuestaOk(IntervinienteView.class, intervinienteView);
    }

    /**
     * Entity to view.
     *
     * @param outEntity
     *            the out entity
     * @return the interviniente view
     */
    private IntervinienteView entityToView(TitularOutEntity outEntity) {
        IntervinienteView viewResponse = new IntervinienteView();
        if (outEntity.getRespuesta() != null) {
            viewResponse.setApellido(
                    ISBANStringUtils.formateoStringPrimeraLetraMayuscula(outEntity.getRespuesta().getApellido()));
            viewResponse.setNombre(
                    ISBANStringUtils.formateoStringPrimeraLetraMayuscula(outEntity.getRespuesta().getNombre()));
        }
        return viewResponse;
    }

    /**
     * Creates the request service interviniente.
     *
     * @param entity
     *            the entity
     * @return the consulta titular in entity
     * @throws SQLException
     *             the SQL exception
     */
    private ConsultaTitularInEntity createRequestServiceInterviniente(IntervinienteinEntity entity)
            throws SQLException {
        ConsultaTitularInEntity consultaTitularInEntity = new ConsultaTitularInEntity();
        String[] partesCuenta = entity.getNumeroCuenta().split("-");
        String sucursal = partesCuenta[0];
        String numeroCuenta = partesCuenta[1];
        consultaTitularInEntity.setCuentaAltair(
                "7" + ISBANStringUtils.formateadorConCerosIzq(numeroCuenta.replace("/", ""), LARGO_NRO_CUENTA));
        consultaTitularInEntity.setSucursalAltair(ISBANStringUtils.formateadorConCerosIzq(sucursal, LARGO_SUCURSAL));
        Credential credencial = credentialSecurityFactory.getCredentialBySistema(DataBase.BPRIVRACF.getNombreTarget());
        consultaTitularInEntity.setUsuario(credencial.getUsuario());
        consultaTitularInEntity.setPass(credencial.getPassword());
        consultaTitularInEntity.setCanal(canalBcaPriv);
        return consultaTitularInEntity;

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.
     * CuentasBancaPrivadaBO#confirmarTransferencia(ar.com.santanderrio.obp.
     * servicios.cuentas.web.view.ConfirmarTransferenciaInEntity,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ConfirmarTransferenciaView> confirmarTransferencia(ConfirmarTransferenciaInEntity entity,
            Cliente cliente) {

        boolean permiteReintentar;
        if (sessionParametros.getContador() != null) {
            permiteReintentar = sessionParametros.getContador().permiteReintentar();
        } else {
            LOGGER.debug("Error, Contador no inicializado!!");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
        TransferenciaBPInEntity transferenciaBPInEntity = null;
        TransferenciaBPOutEntity rtaDAO;
        try {
            transferenciaBPInEntity = createRequestServiceConfirmarTransferencia(entity, cliente);
        } catch (SQLException e1) {
            LOGGER.error("Error de Conversion. ", e1);

        }
        try {
            rtaDAO = cuentaSaldoDAO.ejecutarTransferenciaBancaPrivada(transferenciaBPInEntity);
        } catch (DisponibleInsuficienteException e) {
            LOGGER.error("Error en BO Saldo Insuficiente. ", e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE,
                    CodigoMensajeConstantes.TRANSFERENCIA_BP_SALDO_INSUFICIENTE);
        } catch (ExcedeLimiteTransferenciaException e) {
            LOGGER.error("Error en BO supera limite diario de transferencia. ", e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.LIMITE_TRANSFERENCIA_DIARIO_SUPERADO,
                    CodigoMensajeConstantes.TRANSFERENCIA_BP_LIMITE_TRANSFERENCIA);
        } catch (CuentaBloqueadaException e) {
            LOGGER.error("Error en BO cuenta bloqueada. ", e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.CUENTA_BLOQUEADA,
                    CodigoMensajeConstantes.TRANSFERENCIA_BP_CUENTA_BLOQUEADA);
        } catch (FueraDeHorarioException e) {
            LOGGER.error("Error en BO Fuera de horario. ", e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.FUERADEHORARIO,
                    CodigoMensajeConstantes.TRANSFERENCIA_BP_FUERA_DE_HORARIO);
        } catch (DAOException e) {
            return manejarReintento(transferenciaBPInEntity, permiteReintentar, e);
        }
        return manejarCasoFeliz(transferenciaBPInEntity, rtaDAO);
    }

    /**
     * Manejar caso feliz.
     *
     * @param transferenciaBPInEntity
     *            the transferencia BP in entity
     * @param rtaDAO
     *            the rta DAO
     * @return the respuesta
     */
    Respuesta<ConfirmarTransferenciaView> manejarCasoFeliz(TransferenciaBPInEntity transferenciaBPInEntity,
            TransferenciaBPOutEntity rtaDAO) {
        ConfirmarTransferenciaView confirmarTransferenciaView = cargarDatos(transferenciaBPInEntity, rtaDAO);
        return respuestaFactory.crearRespuestaOk(ConfirmarTransferenciaView.class, confirmarTransferenciaView);
    }

    /**
     * Cargar datos.
     *
     * @param transferenciaBPInEntity
     *            the transferencia BP in entity
     * @param rtaDAO
     *            the rta DAO
     * @return the confirmar transferencia view
     */
    public ConfirmarTransferenciaView cargarDatos(TransferenciaBPInEntity transferenciaBPInEntity,
            TransferenciaBPOutEntity rtaDAO) {
        String signoMoneda = "u$s";
        if ("ARS".equals(transferenciaBPInEntity.getDivisaMovimiento())) {
            signoMoneda = "$";
        }
        ConfirmarTransferenciaView dtoResponse = new ConfirmarTransferenciaView();
        String mensaje = mensajeBO.obtenerMensajePorCodigo(TRANSFERENCIA_CORRECTA).getMensaje();
        String mensajeTransferencia = MessageFormat.format(mensaje,
                signoMoneda + " "
                        + ISBANStringUtils.formatearConComaYDosDecimales(
                                new BigDecimal(transferenciaBPInEntity.getImporteMovimiento())
                                        .setScale(2, BigDecimal.ROUND_DOWN).toString()));
        dtoResponse.setMensajeConfirmacion(mensajeTransferencia);
        dtoResponse.setComprobanteTransaccion(rtaDAO.getRespuesta().getComprobanteTransaccion());
        return dtoResponse;

    }

    /**
     * Manejar reintento.
     *
     * @param transferenciaBPInEntity
     *            the transferencia BP in entity
     * @param permiteReintentar
     *            the permite reintentar
     * @param e
     *            the e
     * @return the respuesta
     */
    Respuesta<ConfirmarTransferenciaView> manejarReintento(TransferenciaBPInEntity transferenciaBPInEntity,
            boolean permiteReintentar, DAOException e) {
        Respuesta<ConfirmarTransferenciaView> respuesta;
        String signoMoneda = "u$s";
        if ("ARS".equals(transferenciaBPInEntity.getDivisaMovimiento())) {
            signoMoneda = "$";
        }
        LOGGER.error("Error en cuentaSaldoDAO metodo ejecutarTransferenciaBancaPrivada. ", e);
        String mensaje = mensajeBO.obtenerMensajePorCodigo(ERROR_NO_CONTEMPLADO_TRANSFERENCIA_BP).getMensaje();
        String mensajeError = MessageFormat.format(mensaje,
                signoMoneda + " "
                        + ISBANStringUtils.formatearConComaYDosDecimales(
                                new BigDecimal(transferenciaBPInEntity.getImporteMovimiento())
                                        .setScale(2, BigDecimal.ROUND_DOWN).toString()));
        if (permiteReintentar) {
            respuesta = respuestaFactory.crearRespuestaError(ConfirmarTransferenciaView.class, mensajeError,
                    TipoError.ERROR_FINALIZAR_TRANSFERENCIA_BP_CON_REINTENTO.toString());

        } else {
            respuesta = respuestaFactory.crearRespuestaError(ConfirmarTransferenciaView.class, mensajeError,
                    TipoError.ERROR_FINALIZAR_TRANSFERENCIA_BP_SIN_REINTENTO.toString());
        }
        return respuesta;
    }

    /**
     * Creates the request service confirmar transferencia.
     *
     * @param entity
     *            the entity
     * @return the transferencia BP in entity
     * @throws SQLException
     *             the SQL exception
     */
    private TransferenciaBPInEntity createRequestServiceConfirmarTransferencia(ConfirmarTransferenciaInEntity entity,
            Cliente cliente) throws SQLException {
        TransferenciaBPInEntity transferenciaBPInEntity = new TransferenciaBPInEntity();
        String[] partesCuentaOrigen = entity.getNroCuentaOrigen().split("-");
        String sucursalOrigen = partesCuentaOrigen[0];
        String numeroCuentaOrigen = partesCuentaOrigen[1];

        String[] partesCuentaSecundaria = entity.getNroCuentaDestino().split("-");
        String sucursalDestino = partesCuentaSecundaria[0];
        String numeroCuentaDestino = partesCuentaSecundaria[1];

        if (esCuentaPrivada(sucursalOrigen) && esCuentaPrivada(sucursalDestino)) {
            transferenciaBPInEntity.setCuenta("7"
                    + ISBANStringUtils.formateadorConCerosIzq(numeroCuentaOrigen.replace("/", ""), LARGO_NRO_CUENTA));
            transferenciaBPInEntity.setCuentaSecundaria("7"
                    + ISBANStringUtils.formateadorConCerosIzq(numeroCuentaDestino.replace("/", ""), LARGO_NRO_CUENTA));
            transferenciaBPInEntity
                    .setSucursalSecundaria(ISBANStringUtils.formateadorConCerosIzq(sucursalDestino, LARGO_SUCURSAL));
            transferenciaBPInEntity.setTipoMovimiento(TIPO_MOVIMIENTO_E);
        }

        if (esCuentaPrivada(sucursalOrigen) && !esCuentaPrivada(sucursalDestino)) {
            String tipoProdAltair = obtenerCuenta(numeroCuentaDestino, cliente.getCuentas());
            transferenciaBPInEntity.setCuenta("7"
                    + ISBANStringUtils.formateadorConCerosIzq(numeroCuentaOrigen.replace("/", ""), LARGO_NRO_CUENTA));
            transferenciaBPInEntity.setCuentaSecundaria(tipoProdAltair + ISBANStringUtils.formateadorConCerosIzq(
                    numeroCuentaDestino.replace("/", ""), LARGO_NRO_CUENTA_TOTAL - tipoProdAltair.length()));
            transferenciaBPInEntity
                    .setSucursalSecundaria(ISBANStringUtils.formateadorConCerosIzq(sucursalDestino, LARGO_SUCURSAL));
            transferenciaBPInEntity.setTipoMovimiento(TIPO_MOVIMIENTO_E);
        }

        if (!esCuentaPrivada(sucursalOrigen) && esCuentaPrivada(sucursalDestino)) {
            String tipoProdAltair = obtenerCuenta(numeroCuentaOrigen, cliente.getCuentas());
            transferenciaBPInEntity.setCuenta("7"
                    + ISBANStringUtils.formateadorConCerosIzq(numeroCuentaDestino.replace("/", ""), LARGO_NRO_CUENTA));
            transferenciaBPInEntity.setCuentaSecundaria(tipoProdAltair + ISBANStringUtils.formateadorConCerosIzq(
                    numeroCuentaOrigen.replace("/", ""), LARGO_NRO_CUENTA_TOTAL - tipoProdAltair.length()));
            transferenciaBPInEntity
                    .setSucursalSecundaria(ISBANStringUtils.formateadorConCerosIzq(sucursalOrigen, LARGO_SUCURSAL));
            transferenciaBPInEntity.setTipoMovimiento(TIPO_MOVIMIENTO_I);
        }

        transferenciaBPInEntity.setDivisaMovimiento(entity.getDivisa());
        transferenciaBPInEntity.setImporteMovimiento(
                new BigDecimal(entity.getImporte()).setScale(2, BigDecimal.ROUND_DOWN).toString());
        transferenciaBPInEntity.setNombreDestino(entity.getInterviniente());
        Credential credencial = credentialSecurityFactory.getCredentialBySistema(DataBase.BPRIVRACF.getNombreTarget());
        transferenciaBPInEntity.setUsuario(credencial.getUsuario());
        transferenciaBPInEntity.setPass(credencial.getPassword());
        transferenciaBPInEntity.setCanal(canalBcaPriv);

        return transferenciaBPInEntity;
    }

    /**
     * Buscar la cuenta personal para obtener el producto altair a enviar en el
     * store cuando la cuenta no es privada.
     * 
     * @param numeroCuentaDestino
     * @param cuentas
     * @return
     */
    private String obtenerCuenta(String numeroCuentaDestino, List<Cuenta> cuentas) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNroCuentaProducto().replace("/", "").contains(numeroCuentaDestino.replace("/", ""))) {
                return StringUtils.stripStart(cuenta.getProductoAltair(), "0");
            }
        }
        return "7";
    }

    /**
     * Es cuenta privada.
     *
     * @param sucursal
     *            the sucursal
     * @return true, if successful
     */
    public boolean esCuentaPrivada(String sucursal) {
        int valorSucursal = Integer.parseInt(sucursal);
        if (valorSucursal >= MIN_VALOR_SUCURSAL_BP && valorSucursal <= MAX_VALOR_SUCURSAL_BP) {
            return true;
        }
        return false;
    }

	@Override
	public Respuesta<List<ResumenMensualCuenta>> obtenerListaResumen(AbstractCuenta cuenta) throws BusinessException, WSODException {

		ResumenParams params = configurarParamentrosConsultaLista(cuenta);

        List<ResumenMensualCuenta> listaResumenes = ondemanDao.obtenerListaResumenBP(params);
    	
	    // Recorre cada resumen y le agrega un id que sera utilizado para
	    // identicar que resumen se quiere descargar
	    if (listaResumenes != null) {
	        Long id = Long.valueOf(0);
	        for (ResumenMensualCuenta resumenMensualCuenta : listaResumenes) {
	            resumenMensualCuenta.setId(id);
	            id++;
	        }
	    }
	    	    
    	return respuestaFactory.crearRespuestaOk(listaResumenes);
	}
	
    private ResumenParams configurarParamentrosConsultaLista(AbstractCuenta cuenta) {
        ResumenParams params = new ResumenParams();
        params.setUsuarioConsulta(cuenta.getCliente().getNup());
        params.setCuenta(cuenta);
        params.setMotivoConsulta("request");
        Date fechaHasta = new Date();
        Date fechaDesde = restarMeses(fechaHasta, Integer.parseInt(cantidadMeses));
        if (validarFechas(fechaDesde, fechaHasta)) {
            params.setFechaDesde(ISBANStringUtils.formatearFecha(fechaDesde, FORMATO_FECHA));
            params.setFechaHasta(ISBANStringUtils.formatearFecha(fechaHasta, FORMATO_FECHA));
        } else {
            return null;
        }
        return params;
    }
    
    private boolean validarFechas(Date fechaDesde, Date fechaHasta) {
        boolean result = false;
        if (fechaDesde != null && fechaHasta != null && fechaDesde.compareTo(fechaHasta) <= 0) {
            result = true;
        }
        return result;
    }
    
    private Date restarMeses(Date fecha, int cantMeses) {
        Calendar fechaCalendar = new GregorianCalendar();
        fechaCalendar.setTime(fecha);
        fechaCalendar.add(Calendar.MONTH, -cantMeses);
        return fechaCalendar.getTime();
    }
    
    @Override
    public Respuesta<ReporteResumenMensualCuenta> obtenerResumenesPDF(ResumenMensualCuenta resumenSeleccionado,
            AbstractCuenta cuenta) {

    	Respuesta<ReporteResumenMensualCuenta> respuesta; 
    	
        try {
        	ReporteResumenMensualCuenta reporteResumenMensualCuenta = ondemanDao.obtenerReporteMensualBP(resumenSeleccionado, cuenta);
            reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);
            respuesta = respuestaFactory.crearRespuestaOk(reporteResumenMensualCuenta);
        } catch (WSODException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, null, null, "");
        } catch (RuntimeException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, null, null, "");
        }
        return respuesta;
    }

    //TODO: UPDATE THIS TO RETURN A RELATIONSHIP BETWEEN ACCOUNT AND BALANCE
    /**
     * Obtener saldo servicio iatx.
     *
     * @param cliente the cliente
     * @return the list
     * @throws SQLException the SQL exception
     */
    @Override
    public List<CuentaIntermedioDTO> obtenerSaldoServicioIatx(Cliente cliente) throws SQLException {
        List<Cuenta> cuentasBP = obtenerCuentasBancaPrivada(cliente);
        List<CuentaIntermedioDTO> listaSaldos = new ArrayList<CuentaIntermedioDTO>();
        for (Cuenta cuentaBP : cuentasBP) {
            CuentaSaldoInEntity cuentaSaldoInEntity = new CuentaSaldoInEntity();
            cuentaSaldoInEntity.setCuenta(ISBANStringUtils.sacarCerosYBlancosIzq(cuentaBP.getProductoAltair())
                    + StringUtils.right(cuentaBP.getNroCuentaProducto(), 9));
            Credential credencial = credentialSecurityFactory
                    .getCredentialBySistema(DataBase.BPRIVRACF.getNombreTarget());
            cuentaSaldoInEntity.setUsuario(credencial.getUsuario());
            cuentaSaldoInEntity.setPass(credencial.getPassword());
            CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
            cuentaIntermedioDTO.setIsCuentaUnica(cuentaBP.isCuentaUnica());
            cuentaIntermedioDTO.setTipoCuenta(cuentaBP.getTipoCuentaEnum());
            cuentaIntermedioDTO.setApodo(cuentaBP.getAlias());
            cuentaIntermedioDTO.setNumeroCuenta(cuentaBP.obtenerNroCuentaFormateado());
            cuentaIntermedioDTO.setCbu(cuentaBP.getCbu());
            cuentaIntermedioDTO.setIsCuentaTransacional(PRODUCTO_CUENTA_TRANSACCIONAL.equals(cuentaBP.getProductoAltair()));
            try {
                cuentaIntermedioDTO.setSaldosServicioIatx(
                        consultarServicioIatxSaldosCuentas(cuentaBP, cliente, cuentaSaldoInEntity));
            } catch (DAOException e) {
                ConsultaSaldoCtasConAperturaOutEntity consultaSaldoCtasConAperturaOutEntity = new ConsultaSaldoCtasConAperturaOutEntity();
                consultaSaldoCtasConAperturaOutEntity.setErrorEnConsulta(true);
                cuentaIntermedioDTO.setSaldosServicioIatx(consultaSaldoCtasConAperturaOutEntity);
            }
            listaSaldos.add(cuentaIntermedioDTO);
        }
        return listaSaldos;
    }

    @Override
    public CuentaIntermedioDTO obtenerSaldoServicioIatx(Cuenta cuenta) throws SQLException {
        final CuentaSaldoInEntity cuentaSaldoInEntity = new CuentaSaldoInEntity();
        final Credential credencial = credentialSecurityFactory.getCredentialBySistema(DataBase.BPRIVRACF.getNombreTarget());
        final CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();

        cuentaSaldoInEntity.setCuenta(ISBANStringUtils.sacarCerosYBlancosIzq(cuenta.getProductoAltair())
                + StringUtils.right(cuenta.getNroCuentaProducto(), 9));
        cuentaSaldoInEntity.setUsuario(credencial.getUsuario());
        cuentaSaldoInEntity.setPass(credencial.getPassword());
        cuentaIntermedioDTO.setIsCuentaUnica(cuenta.isCuentaUnica());
        cuentaIntermedioDTO.setTipoCuenta(cuenta.getTipoCuentaEnum());
        cuentaIntermedioDTO.setApodo(cuenta.getAlias());
        cuentaIntermedioDTO.setNumeroCuenta(cuenta.obtenerNroCuentaFormateado());
        cuentaIntermedioDTO.setCbu(cuenta.getCbu());
        cuentaIntermedioDTO.setIsCuentaTransacional(PRODUCTO_CUENTA_TRANSACCIONAL.equals(cuenta.getProductoAltair()));
        try {
            cuentaIntermedioDTO.setSaldosServicioIatx(consultarServicioIatxSaldosCuentas(cuenta, cuenta.getCliente(), cuentaSaldoInEntity));
        } catch (DAOException e) {
            ConsultaSaldoCtasConAperturaOutEntity consultaSaldoCtasConAperturaOutEntity = new ConsultaSaldoCtasConAperturaOutEntity();
            consultaSaldoCtasConAperturaOutEntity.setErrorEnConsulta(true);
            cuentaIntermedioDTO.setSaldosServicioIatx(consultaSaldoCtasConAperturaOutEntity);
        }
        return cuentaIntermedioDTO;
    }
}
