/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasCbuException;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaCBU;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestModificaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.ResponseAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.TerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.UsuarioDTO;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCBUCuentaInactivaException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CasuisticaAliasCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AliasCuentaBOImpl.
 */
@Component
public class AliasCuentaBOImpl implements AliasCuentaBO {

    /** The Constant TIPO_DOCUMENTO_CUIT. */
    private static final String TIPO_DOCUMENTO_CUIT = "1";

    /** The Constant TIPO_PERSONA_FISICA. */
    private static final String TIPO_PERSONA_FISICA = "F";

    /** The Constant CANAL_DEFAULT. */
    private static final String CANAL_DEFAULT = "A";

    /** The Constant LOGGER_TEXTO. */
    private static final String LOGGER_TEXTO = "ObtenerAliasCBU=Mensaje error:";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AliasCuentaBOImpl.class);

    /** The Constant TIPO_DOCUMENTO_T. */
    private static final String TIPO_DOCUMENTO_T = "T";

    /** The Constant TIPO_DOCUMENTO_L. */
    private static final String TIPO_DOCUMENTO_L = "L";

    /** The Constant TIPO_DOCUMENTO_D. */
    private static final String TIPO_DOCUMENTO_D = "D";

    /** The Constant ERROR_GENERICO. */
    private static final String ERROR_GENERICO = "errorGenericoAliasCBU";

    /** The alias CBU. */
    @Autowired
    private AliasCbuDAO aliasCBU;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The consultar sucursales. */
    @Autowired
    private ConsultarSucursalesDAO consultarSucursales;

    /** The nup dao. */
    @Autowired
    private NUPDAO nupDao;

    /** The casuistica alias. */
    @Autowired
    private CasuisticaAliasCuenta casuisticaAlias;

    /** The detalle documentos. */
    Map<String, DetalleDocumentoDTO> detalleDocumentos;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO#
     * obtenerAliasCBU(java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public Respuesta<DetalleCBUView> obtenerAliasCBU(String cbu, String ip, String dni, String datosTerminal) {
        try {
            RequestConsultaCBU requestConsultaCBU = crearConsultaCBU(cbu, ip, dni, datosTerminal);
            ResponseAlias response = aliasCBU.obtenerAliasDesdeCBU(requestConsultaCBU);
            LOGGER.info("ObtenerAliasCBU=tipo de error: {}", response.getEstado());
            return respuestaFactory.crearRespuestaOk(DetalleCBUView.class, crearDetalleCBU(response));
        } catch (AliasCBUCuentaInactivaException e) {
            LOGGER.info(LOGGER_TEXTO + e.getMessage(), e);
            return respuestaFactory.crearRespuestaWarning(null, TipoError.SIN_OPCIONES_ALIAS_CBU,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        } catch (AliasCbuException e) {
            LOGGER.info(LOGGER_TEXTO + e.getMessage(), e);
            return respuestaFactory.crearRespuestaWarning(null, TipoError.SOLO_ALTA_ALIAS_CBU,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        } catch (DAOException e) {
            LOGGER.info(LOGGER_TEXTO + e.getMessage(), e);
            return respuestaFactory.crearRespuestaWarning(null, TipoError.SOLO_ALTA_ALIAS_CBU,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO#
     * obtenerDatosCliente(java.lang.String)
     */
    @Override
    public Respuesta<DetalleCBUView> obtenerDatosCliente(String cbu, String alias) {
        DetalleCBUView detalle = new DetalleCBUView();
        Cliente cliente = sesionCliente.getCliente();
        Cuenta cuentaSeleccionada = cliente.getCuenta(cbu);
        detalle.setNombreCliente(WordUtils.capitalizeFully(cliente.getNombre() + " " + cliente.getApellido1()));
        DetalleDocumentoDTO detalleDocumento = null;
        try {
            detalleDocumento = obtenerDocumentoValidoDTO(cliente);
        } catch (DAOException e1) {
            LOGGER.error("falla consulta DetalleDocumentoDTO", e1);
        }
        if (detalleDocumento == null || alias == null) {
            return respuestaFactory.crearRespuestaError(ERROR_GENERICO, TipoError.ERROR_GENERICO_ALIAS_CBU,
                    CodigoMensajeConstantes.CODIGO_ASIGNACION_ALIAS_ERROR_GENERICO);
        }
        detalle.setCuit(obtenerCuitPorDetalle(detalleDocumento));
        detalle.setAliasCbu(alias);
        detalle.setNombreBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        if (cuentaSeleccionada.getTipoCuentaEnum().getDescripcion() == TipoCuenta.CUENTA_UNICA
                .getDescripcionConMoneda()) {
            detalle.setTipoCuenta(cuentaSeleccionada.getTipoCuentaEnum().getDescripcion());
        } else {
            detalle.setTipoCuenta(cuentaSeleccionada.getTipoCuentaEnum().getDescripcionConMoneda());
        }
        if (cuentaSeleccionada.getNroSucursal() != null) {
            String idSucursal = cuentaSeleccionada.getNroSucursal();
            String idSucursalTresDigitos = idSucursal.substring(idSucursal.length() - 3, idSucursal.length());
            try {
                Sucursal sucursal = consultarSucursales
                        .consultarSucursalPorId(idSucursal.substring(idSucursal.length() - 4, idSucursal.length()));
                if (sucursal == null) {
                    detalle.setNumeroSucursal(idSucursalTresDigitos + " - ");
                } else {
                    detalle.setNumeroSucursal(idSucursalTresDigitos + " - " + sucursal.getDescripcion());
                }
            } catch (DAOException e) {
                detalle.setNumeroSucursal(idSucursalTresDigitos + " - ");
            }
            String nroCuenta = cuentaSeleccionada.getNroCuentaProducto();
            String nroCuentaFormateado = nroCuenta.substring(nroCuenta.length() - 7, nroCuenta.length() - 1) + "/"
                    + nroCuenta.substring(nroCuenta.length() - 1, nroCuenta.length());
            detalle.setNumeroCuenta(idSucursalTresDigitos + "-" + nroCuentaFormateado);
            detalle.setCbu(cbu);
            return respuestaFactory.crearRespuestaOk(DetalleCBUView.class, detalle);
        }
        return respuestaFactory.crearRespuestaError(ERROR_GENERICO, TipoError.ERROR_GENERICO_ALIAS_CBU,
                CodigoMensajeConstantes.CODIGO_ASIGNACION_ALIAS_ERROR_GENERICO);

    }
    
    /**
     * Obtener cuit por detalle.
     *
     * @param detalleDocumento
     *            the detalle documento
     * @return the string
     */
    @Override
    public String obtenerCuitPorDetalle(DetalleDocumentoDTO detalleDocumento) {
        if (detalleDocumento == null) {
            return "-";
        } else {
            return ISBANStringUtils.formatearCuil(detalleDocumento.getNroDocumento());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO#
     * confirmarAltaAlias(java.lang.String, java.lang.String, java.lang.String,
     * ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView)
     */
    @Override
    public Respuesta<ComprobanteAltaCBUDTO> confirmarAltaAlias(DetalleCBUDTO detalle) {
        try {
            ResponseAlias response;
            if (detalle.getAliasAnterior() == null) {
                response = aliasCBU.altaAlias(crearAltaCBU(detalle));
                return casuisticaAlias.crearRespuesta(response, detalle.getAliasCBU(), detalle.getNroCuenta(),
                        detalle.getReasigna());
            } else {
                response = aliasCBU.modificarAlias(crearEdicionCBU(detalle));
                return casuisticaAlias.crearRespuestaEditar(response, detalle.getAliasCBU(), detalle.getNroCuenta(),
                        detalle.getReasigna());
            }
        } catch (DAOException e) {
            if (detalle.getAliasAnterior() == null) {
                return casuisticaAlias.obtenerErrorGeneral();
            } else {
                return casuisticaAlias.obtenerErrorGeneralEditarAlias();
            }
        }
    }

    /**
     * Crear edicion CBU.
     *
     * @param detalle
     *            the detalle
     * @return the request modifica alias
     */
    private RequestModificaAlias crearEdicionCBU(DetalleCBUDTO detalle) {
        RequestModificaAlias requestEdicion = new RequestModificaAlias();
        requestEdicion.setAliasOriginal(detalle.getAliasAnterior());
        requestEdicion.setAlias(detalle.getAliasCBU());
        requestEdicion.setCbu(detalle.getCbu());
        requestEdicion.setCuit(StringUtils.remove(detalle.getCuit(), "-"));
        requestEdicion.setReasigna(detalle.getReasigna());
        TerminalDTO terminalDTO = new TerminalDTO();
        terminalDTO.setCanal("A");
        requestEdicion
                .setTipoCta(darTipoCuentaAltaAlias(detalle.getCuenta().getTipoCuentaEnum().getCodigo().toString()));
        terminalDTO.setDatosTerminal(detalle.getDetallesTerminal());
        terminalDTO.setDireccionIp(detalle.getIp());
        terminalDTO.setTerminal(null);
        requestEdicion.setTerminalDTO(terminalDTO);
        requestEdicion.setTipoPersona(TIPO_PERSONA_FISICA);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNroDocumento(detalle.getCliente().getDni());
        usuarioDTO.setTipoDocumento("1");
        requestEdicion.setUsuarioDTO(usuarioDTO);
        return requestEdicion;
    }

    /**
     * Crea el RequestAlias para enviar al servicio de crearAltaCbu.
     *
     * @param detalle
     *            the detalle
     * @return the request alias
     */
    private RequestAlias crearAltaCBU(DetalleCBUDTO detalle) {
        RequestAlias request = new RequestAlias();
        request.setAlias(detalle.getAliasCBU());
        request.setCbu(detalle.getCbu());
        request.setCuit(StringUtils.remove(detalle.getCuit(), "-"));
        request.setReasigna(detalle.getReasigna());
        TerminalDTO terminalDTO = new TerminalDTO();
        terminalDTO.setCanal("A");
        terminalDTO.setDatosTerminal(detalle.getDetallesTerminal());
        terminalDTO.setDireccionIp(detalle.getIp());
        terminalDTO.setTerminal(null);
        request.setTerminalDTO(terminalDTO);
        request.setTipoCta(darTipoCuentaAltaAlias(detalle.getCuenta().getTipoCuentaEnum().getCodigo().toString()));
        request.setTipoPersona("F");
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNroDocumento(detalle.getCliente().getDni());
        usuarioDTO.setTipoDocumento("1");
        request.setUsuarioDTO(usuarioDTO);
        return request;
    }

    /**
     * Dar tipo cuenta alta alias.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the string
     */
    private String darTipoCuentaAltaAlias(String tipoCuenta) {
        String cuenta;
        if ("0".equals(tipoCuenta) || "9".equals(tipoCuenta)) {
            cuenta = "01";
        } else if ("3".equals(tipoCuenta) || "10".equals(tipoCuenta)) {
            cuenta = "02";
        } else if ("1".equals(tipoCuenta) || "2".equals(tipoCuenta)) {
            cuenta = "11";
        } else {
            cuenta = "12";
        }
        return cuenta;
    }

    /**
     * Crear consulta CBU.
     *
     * @param cbu
     *            the cbu
     * @param ip
     *            the ip
     * @param dni
     *            the dni
     * @param datosTerminal
     *            the datos terminal
     * @return the request consulta CBU
     */
    private RequestConsultaCBU crearConsultaCBU(String cbu, String ip, String dni, String datosTerminal) {
        RequestConsultaCBU request = new RequestConsultaCBU();
        request.setCbu(cbu);
        TerminalDTO newDTO = new TerminalDTO();
        newDTO.setCanal("A");
        newDTO.setDatosTerminal(datosTerminal);
        newDTO.setDireccionIp(ip);
        newDTO.setTerminal(null);
        request.setTerminalDTO(newDTO);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNroDocumento(dni);
        usuarioDTO.setTipoDocumento("1");
        request.setUsuarioDTO(usuarioDTO);
        return request;
    }

    /**
     * Crear detalle CBU.
     *
     * @param response
     *            the response
     * @return the detalle CBU view
     */
    private DetalleCBUView crearDetalleCBU(ResponseAlias response) {
        DetalleCBUView detalle = new DetalleCBUView();
        detalle.setAliasCbu(response.getAlias());
        detalle.setReasigna(response.getReasigna());
        return detalle;
    }

    /**
     * Obtener documento valido.
     *
     * @param cliente
     *            the cliente
     * @return the detalle documento DTO
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public DetalleDocumentoDTO obtenerDocumentoValidoDTO(Cliente cliente) throws DAOException {
        DetalleDocumentoDTO dto = null;
        try {
            NupDTO nupDTO = nupDao.obtenerNUP(cliente);
            dto = obtenerDetalleDocumentoValido(nupDTO.getDetalleDocumento());
            this.detalleDocumentos = nupDTO.getDetalleDocumento();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return dto;
    }

    /**
     * Obtener detalle documentos.
     *
     * @return the detalle documentos
     */
    @Override
    public Map<String, DetalleDocumentoDTO> obtenerDetalleDocumentos() {
    	return this.detalleDocumentos;
    }

    /**
     * Retorna un tipo de documento valido.
     *
     * @param detalleDocumento
     *            the detalle documento
     * @return the detalle documento DTO
     */
    private DetalleDocumentoDTO obtenerDetalleDocumentoValido(Map<String, DetalleDocumentoDTO> detalleDocumento) {
        for (DetalleDocumentoDTO d1 : detalleDocumento.values()) {
            if (obtenerTipoDocumento(d1.getTipoDocumento()) != null) {
                return d1;
            }
        }
        return null;
    }

    /**
     * Obtiene la primer letra del tipo de documento Tipo de documento esta
     * formado por dos alfanumericos( Primer byte D:CDI, L:CUIL, T:CUIT).
     *
     * @param tipoDocumento
     *            the tipo documento
     * @return the string
     */
    private String obtenerTipoDocumento(String tipoDocumento) {
        if (tipoDocumento == null) { return null; }
        String primerByte = primerByte(tipoDocumento);
        if (primerByte.equals(TIPO_DOCUMENTO_T)
                || primerByte.equals(TIPO_DOCUMENTO_L)
                || primerByte.equals(TIPO_DOCUMENTO_D)) {
            return primerByte;
        }
        return null;
    }

    /**
     * Obtiene el primer caracter del dato ingresado.
     *
     * @param dato
     *            the dato
     * @return the string
     */
    private String primerByte(String dato) {
        return StringUtils.left(dato, CompraVentaStringUtil.UNO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO#
     * confirmarBajaAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.entities.
     * DetalleCBUDTO)
     */
    @Override
    public Respuesta<ComprobanteAltaCBUDTO> confirmarBajaAliasCBU(DetalleCBUDTO detalleCBU) {

        try {
            RequestAlias alias = crearRequestBajaCBU(detalleCBU);
            ResponseAlias response = aliasCBU.bajaAlias(alias);
            if (!"ERROR".equals(response.getEstado())) {
                return casuisticaAlias.crearRespuestaOkBaja(response, detalleCBU.getAliasCBU(),
                        detalleCBU.getNroCuenta());
            }
            LOGGER.debug("ObtenerAliasCBU=tipo de error:" + response.getEstado() + "--- Mensaje error:"
                    + response.getError().getMensaje() + "---Codigo error" + response.getError().getCodigo() + ";");
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ELIMINAR_ALIAS_CBU,
                CodigoMensajeConstantes.ERROR_ELIMINAR_ALIAS_CBU);
    }

    /**
     * Crear request baja CBU.
     *
     * @param detalle
     *            the detalle
     * @return the request alias
     */
    private RequestAlias crearRequestBajaCBU(DetalleCBUDTO detalle) {
        RequestAlias request = new RequestAlias();
        request.setAlias(detalle.getAliasCBU());
        request.setCbu(detalle.getCbu());
        request.setCuit(StringUtils.remove(detalle.getCuit(), "-"));
        request.setReasigna(null);
        TerminalDTO terminalDTO = new TerminalDTO();
        terminalDTO.setCanal(CANAL_DEFAULT);
        terminalDTO.setDatosTerminal(detalle.getDetallesTerminal());
        terminalDTO.setDireccionIp(detalle.getIp());
        terminalDTO.setTerminal(null);
        request.setTerminalDTO(terminalDTO);
        request.setTipoCta(darTipoCuentaAltaAlias(detalle.getCuenta().getTipoCuentaEnum().getCodigo().toString()));
        request.setTipoPersona(TIPO_PERSONA_FISICA);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNroDocumento(detalle.getCliente().getDni());
        usuarioDTO.setTipoDocumento(TIPO_DOCUMENTO_CUIT);
        request.setUsuarioDTO(usuarioDTO);
        return request;
    }

}
