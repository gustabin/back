/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf324931821440052060NillableString;
import ar.com.santanderrio.obp.generated.webservices.productos.InfoRequeridaWS;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo.AumentoLimiteTransferenciaBO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.AumentoLimiteTransferenciaDAO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaInDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaOutDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaOutEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;

/**
 * The Class AumentoLimiteTransferenciaBOImpl.
 */
@Component
public class AumentoLimiteTransferenciaBOImpl implements AumentoLimiteTransferenciaBO {

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The aumento limite transferencia DAO. */
    @Autowired
    private AumentoLimiteTransferenciaDAO aumentoLimiteTransferenciaDAO;

    /** The canal. */
    @Value("${SOLAUMENTOLIMTRANSF.CANAL}")
    private String canal;

    /** The codigo canal. */
    @Value("${SOLAUMENTOLIMTRANSF.CODIGO.CANAL}")
    private String codigoCanal;

    /** The codigo subcanal. */
    @Value("${SOLAUMENTOLIMTRANSF.CODIGO.SUBCANAL}")
    private String codigoSubcanal;

    /** The cuenta origen id. */
    @Value("${SOLAUMENTOLIMTRANSF.CUENTA.ORIGEN.ID}")
    private String cuentaOrigenId;

    /** The cod asociacion. */
    @Value("${SOLAUMENTOLIMTRANSF.CODASOCIACION.ID}")
    private String codAsociacion;

    /** The tipo persona. */
    @Value("${SOLAUMENTOLIMTRANSF.TIPO.PERSONA}")
    private String tipoPersona;

    /** The cod user. */
    @Value("${SOLAUMENTOLIMTRANSF.COD.USER}")
    private String codUser;

    /** The cod sector. */
    @Value("${SOLAUMENTOLIMTRANSF.COD.SECTOR}")
    private String codSector;

    /** The medio ingreso. */
    @Value("${SOLAUMENTOLIMTRANSF.MEDIO.INGRESO}")
    private String medioIngreso;

    /** The comentario. */
    @Value("${SOLAUMENTOLIMTRANSF.COMENTARIO}")
    private String comentario;

    /** The fecha operacion id. */
    @Value("${SOLAUMENTOLIMTRANSF.FECHA.OPERACION.ID}")
    private String fechaOperacionId;

    /** The canal id. */
    @Value("${SOLAUMENTOLIMTRANSF.CANAL.ID}")
    private String canalId;

    /** The codigo canal id. */
    @Value("${SOLAUMENTOLIMTRANSF.CODIGO.CANAL.ID}")
    private String codigoCanalId;

    /** The codigo subcanal id. */
    @Value("${SOLAUMENTOLIMTRANSF.CODIGO.SUBCANAL.ID}")
    private String codigoSubcanalId;

    /** The tipo operacion id. */
    @Value("${SOLAUMENTOLIMTRANSF.TIPO.OPERACION.ID}")
    private String tipoOperacionId;

    /** The importe id. */
    @Value("${SOLAUMENTOLIMTRANSF.IMPORTE.ID}")
    private String importeId;

    /** The moneda id. */
    @Value("${SOLAUMENTOLIMTRANSF.MONEDA.ID}")
    private String monedaId;

    /** The sucursal origen id. */
    @Value("${SOLAUMENTOLIMTRANSF.SUCURSAL.ORIGEN.ID}")
    private String sucursalOrigenId;

    /** The moneda origen id. */
    @Value("${SOLAUMENTOLIMTRANSF.MONEDA.ORIGEN.ID}")
    private String monedaOrigenId;

    /** The codigo producto id. */
    @Value("${SOLAUMENTOLIMTRANSF.CODIGO.PRODUCTO.ID}")
    private String codigoProductoId;

    /** The codigo subproducto id. */
    @Value("${SOLAUMENTOLIMTRANSF.CODIGO.SUBPRODUCTO.ID}")
    private String codigoSubproductoId;

    /** The descripcion producto id. */
    @Value("${SOLAUMENTOLIMTRANSF.DESCRIPCION.PRODUCTO.ID}")
    private String descripcionProductoId;

    /** The tipo destino id. */
    @Value("${SOLAUMENTOLIMTRANSF.TIPO.DESTINO.ID}")
    private String tipoDestinoId;

    /** The cuenta destion id. */
    @Value("${SOLAUMENTOLIMTRANSF.CUENTA.DESTINO.ID}")
    private String cuentaDestionId;

    /** The titular destino id. */
    @Value("${SOLAUMENTOLIMTRANSF.TITULAR.DESTINO.ID}")
    private String titularDestinoId;

    /** The Constant FECHAOPERACIONID_DESC. */
    private static final String FECHAOPERACIONID_DESC = "Fecha de la operacion";

    /** The Constant CANAL_DESC. */
    private static final String CANAL_DESC = "Canal";

    /** The Constant CODIGOCANALID_DESC. */
    private static final String CODIGOCANALID_DESC = "Codigo de canal";

    /** The Constant CODIGOSUBCANALID_DESC. */
    private static final String CODIGOSUBCANALID_DESC = "Codigo de subcanal";

    /** The Constant TIPOOPERACION_DESC. */
    private static final String TIPOOPERACION_DESC = "Tipo de operacion";

    /** The Constant IMPORTE_TRANSF. */
    private static final String IMPORTE_TRANSF = "Importe a transferir";

    /** The Constant MONEDA. */
    private static final String MONEDA = "Moneda";

    /** The Constant SUCURSAL_ORIGEN. */
    private static final String SUCURSAL_ORIGEN = "Sucursal de origen";

    /** The Constant MONEDA_ORIGEN. */
    private static final String MONEDA_ORIGEN = "Moneda de origen";

    /** The Constant CODIGO_PRODUCTO. */
    private static final String CODIGO_PRODUCTO = "Codigo del producto";

    /** The Constant CODIGO_SUBPRODUCTO. */
    private static final String CODIGO_SUBPRODUCTO = "Codigo del subproducto";

    /** The Constant DESCRIPCION_PRODUCTO. */
    private static final String DESCRIPCION_PRODUCTO = "Descripcion del prooducto";

    /** The Constant TIPO_DESTINO. */
    private static final String TIPO_DESTINO = "Tipo de destino";

    /** The Constant CUENTA_DESTINO. */
    private static final String CUENTA_DESTINO = "Cuenta de destino";

    /** The Constant CUENTA_ORIGEN. */
    private static final String CUENTA_ORIGEN = "Cuenta de origen";

    /** The Constant TITULAR_DESTINO. */
    private static final String TITULAR_DESTINO = "Titular de destino";

    /** The Constant CODIGO_2963. */
    private static final String CODIGO_2963 = "2963";

    /** The Constant TRF_TERCERO_MISMO_BANCO. */
    private static final String TRF_TERCERO_MISMO_BANCO = "TTMF";

    /** The Constant TRF_TERCERO_OTRO_BANCO. */
    private static final String TRF_TERCERO_OTRO_BANCO = "TTOF";

    /** The Constant TRF_CUENTA_PROPIA. */
    private static final String TRF_CUENTA_PROPIA = "TPMF";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AumentoLimiteTransferenciaBOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo.
     * AumentoLimiteTransferenciaBO#altaSolicitudAumentoLimiteTransferencia(ar.
     * com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.
     * AumentoLimiteTransferenciaInDTO)
     */
    @Override
    public Respuesta<AumentoLimiteTransferenciaOutDTO> altaSolicitudAumentoLimiteTransferencia(
            AumentoLimiteTransferenciaInDTO inDTO) throws DAOException {
        LOGGER.info(
                "Se ejecuta el alta de solicitud de aumento de limite de transferencia desde AumentoLimiteTransferenciaBOImpl");
        Respuesta<AumentoLimiteTransferenciaOutEntity> respuestaDAO = new Respuesta<AumentoLimiteTransferenciaOutEntity>();
        AumentoLimiteTransferenciaOutDTO respuestaDTO = new AumentoLimiteTransferenciaOutDTO();
        AumentoLimiteTransferenciaInEntity inEntity = new AumentoLimiteTransferenciaInEntity();

        this.cargarInfoCabeceraWS(inEntity, inDTO.getCliente().getNup());
        this.cargarInfoRequeridaWS(inEntity, inDTO);

        Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaFinal = new Respuesta<AumentoLimiteTransferenciaOutDTO>();

        try {
            respuestaDAO = aumentoLimiteTransferenciaDAO.altaSolicitudAumentoLimiteTransferencia(inEntity);
            if (EstadoRespuesta.OK.equals(respuestaDAO.getEstadoRespuesta())) {
                respuestaDTO.setNroGestion(respuestaDAO.getRespuesta().getNroGestion());
                respuestaFinal = respuestaFactory.crearRespuestaOk(AumentoLimiteTransferenciaOutDTO.class,
                        respuestaDTO);
            } else {
                respuestaFinal = respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuestaFinal = respuestaFactory.crearRespuestaError(null);
        }
        LOGGER.info("Se devuelve la respuesta " + respuestaFinal.getEstadoRespuesta() + ISBANStringUtils.ESPACIO_STRING
                + "del alta de solicitud desde AumentoLimiteTransferenciaBOImpl");
        return respuestaFinal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo.
     * AumentoLimiteTransferenciaBO#generarComprobanteAumentoLimiteTransferencia
     * (ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.
     * DatosComprobanteAumentoLimiteTransferencia)
     */
    @Override
    public Respuesta<Reporte> generarComprobanteAumentoLimiteTransferencia(
            DatosComprobanteAumentoLimiteTransferencia datosComprobante) {

        Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
        Reporte reporte = aumentoLimiteTransferenciaDAO.generarComprobanteAumentoLimiteTransferencia(datosComprobante);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(reporte);
        return respuesta;

    }

    /**
     * Procesa y carga los datos requeridos para el WS.
     *
     * @param inEntity
     *            the in entity
     * @param inDTO
     *            the in DTO
     */
    private void cargarInfoRequeridaWS(AumentoLimiteTransferenciaInEntity inEntity,
            AumentoLimiteTransferenciaInDTO inDTO) {
        LOGGER.info(
                "Se ejecuta el metodo de carga de info requerida para el WS AumentoLimiteTransferenciaBOImpl.cargarInfoRequerida");
        ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida = new ArrayOf158770343432493182NillableInfoRequeridaWS();
        List<InfoRequeridaWS> listInfo = new ArrayList<InfoRequeridaWS>();

        Cliente cliente = inDTO.getCliente();

        String nroProductoCuenta = ISBANStringUtils
                .formateadorConCerosIzq(ISBANStringUtils.extraerCuenta(inDTO.getNroCuenta()), 16);
        Cuenta cuentaSeleccionada = cliente.getCuentaPorNumero(nroProductoCuenta);

        listInfo.add(this.crearCampoRequeridoWS(FECHAOPERACIONID_DESC, Integer.parseInt(fechaOperacionId),
                inDTO.getFechaEjecucion()));
        listInfo.add(this.crearCampoRequeridoWS(CANAL_DESC, Integer.parseInt(canalId), canal));
        listInfo.add(this.crearCampoRequeridoWS(CODIGOCANALID_DESC, Integer.parseInt(codigoCanalId), codigoCanal));
        listInfo.add(
                this.crearCampoRequeridoWS(CODIGOSUBCANALID_DESC, Integer.parseInt(codigoSubcanalId), codigoSubcanal));
        listInfo.add(this.crearCampoRequeridoWS(TIPOOPERACION_DESC, Integer.parseInt(tipoOperacionId),
                establecerTipoOperacion(inDTO.isRioRio(), inDTO.isCuentaPropia())));
        try {
            listInfo.add(this.crearCampoRequeridoWS(IMPORTE_TRANSF, Integer.parseInt(importeId),
                    ISBANStringUtils.convertirImporteConNCantDeDecimalesATipoString(inDTO.getImporte(),2)));
        } catch (ImporteConvertException e) {
            Log.error("Error cuando se intenta realizar el formateo de importe. Detalle: " + e.getMessage() );
        }
        listInfo.add(this.crearCampoRequeridoWS(MONEDA, Integer.parseInt(monedaId),
                TipoMonedaInversionEnum.fromMonedaString(inDTO.getMoneda()).getCodigo2()));
        listInfo.add(this.crearCampoRequeridoWS(SUCURSAL_ORIGEN, Integer.parseInt(sucursalOrigenId),
                cuentaSeleccionada.getNroSucursal()));
        String cuentaOrigen = CODIGO_2963.equalsIgnoreCase(cuentaOrigenId.trim())
                ? cuentaSeleccionada.getContratoAltair() : inDTO.getNroCuenta();
        listInfo.add(this.crearCampoRequeridoWS(CUENTA_ORIGEN, Integer.parseInt(cuentaOrigenId), cuentaOrigen));
        String monedaOrigen = TipoCuenta.CUENTA_UNICA.getDescripcion()
                .equalsIgnoreCase(cuentaSeleccionada.getTipoCuenta()) ? cuentaSeleccionada.getMonedaAltair()
                        : TipoMonedaInversionEnum.fromMonedaString(inDTO.getMoneda()).getCodigo2();
        listInfo.add(this.crearCampoRequeridoWS(MONEDA_ORIGEN, Integer.parseInt(monedaOrigenId), monedaOrigen));
        listInfo.add(this.crearCampoRequeridoWS(CODIGO_PRODUCTO, Integer.parseInt(codigoProductoId),
                cuentaSeleccionada.getProductoAltair()));
        listInfo.add(this.crearCampoRequeridoWS(CODIGO_SUBPRODUCTO, Integer.parseInt(codigoSubproductoId),
                cuentaSeleccionada.getSubproductoAltair()));
        int maxLengthCtaOrigen = cuentaSeleccionada.getTipoCuentaEnum().getDescripcion().length() >= 22 ? 22
                : cuentaSeleccionada.getTipoCuentaEnum().getDescripcion().length();
        String descripcionProd = ISBANStringUtils.normalizarCaraceteres(
                cuentaSeleccionada.getTipoCuentaEnum().getDescripcion().substring(0, maxLengthCtaOrigen));
        listInfo.add(this.crearCampoRequeridoWS(DESCRIPCION_PRODUCTO, Integer.parseInt(descripcionProductoId),
                descripcionProd));

        boolean isCuentaPropia = inDTO.isCuentaPropia();
        boolean tieneAlias = !ISBANStringUtils.isEmptyOrNull(inDTO.getAliasDestino());
        String titular = this.obtenerTitular(inDTO, cliente, isCuentaPropia);
        String alias = tieneAlias ? inDTO.getAliasDestino() : StringUtils.EMPTY;
        String cbu = this.obtenerCBU(inDTO, isCuentaPropia);
        String tipoDestino = this.obtenerTipoDestino(alias, cbu);

        String cuentaDestino = tieneAlias ? alias : !cbu.isEmpty() ? cbu : inDTO.getNroCuentaDestino();
        String titularDestino = !titular.isEmpty() ? ISBANStringUtils.normalizarCaraceteres(titular) : "No disponible";

        listInfo.add(this.crearCampoRequeridoWS(TIPO_DESTINO, Integer.parseInt(tipoDestinoId), tipoDestino));
        listInfo.add(this.crearCampoRequeridoWS(CUENTA_DESTINO, Integer.parseInt(cuentaDestionId),
                ISBANStringUtils.normalizarCaraceteres(cuentaDestino)));
        listInfo.add(this.crearCampoRequeridoWS(TITULAR_DESTINO, Integer.parseInt(titularDestinoId), titularDestino));

        infoRequerida.getInfoRequeridaWS().addAll(listInfo);
        inEntity.setInfoRequerida(infoRequerida);

    }

    /**
     * Obtener titular.
     *
     * @param inDTO
     *            the in DTO
     * @param cliente
     *            the cliente
     * @param isCuentaPropia
     *            the is cuenta propia
     * @return the string
     */
    private String obtenerTitular(AumentoLimiteTransferenciaInDTO inDTO, Cliente cliente, boolean isCuentaPropia) {
        String ap2 = (cliente.getApellido2() != null && !"".equals(cliente.getApellido2()))
                ? cliente.getApellido2() + ISBANStringUtils.ESPACIO_STRING : StringUtils.EMPTY;
        String nyap = cliente.getNombre() + " " + cliente.getApellido1() + ISBANStringUtils.ESPACIO_STRING + ap2;
        return !isCuentaPropia ? inDTO.getTitular() : nyap;
    }

    /**
     * Obtener CBU.
     *
     * @param inDTO
     *            the in DTO
     * @param isCuentaPropia
     *            the is cuenta propia
     * @return the string
     */
    private String obtenerCBU(AumentoLimiteTransferenciaInDTO inDTO, boolean isCuentaPropia) {
        return !isCuentaPropia && inDTO.getCbu() != null ? inDTO.getCbu().trim() : StringUtils.EMPTY;
    }

    /**
     * Obtener tipo destino.
     *
     * @param alias
     *            the alias
     * @param cbu
     *            the cbu
     * @return the string
     */
    private String obtenerTipoDestino(String alias, String cbu) {
        String tipoDestino = "Cuenta";
        if (!alias.isEmpty()) {
            tipoDestino = "Alias";
        } else if (!cbu.isEmpty()) {
            tipoDestino = "Cbu";
        }
        return tipoDestino;
    }

    /**
     * Setea info de cabecera del XML para el consumo del WS.
     *
     * @param inEntity
     *            the in entity
     * @param nup
     *            the nup
     */
    private void cargarInfoCabeceraWS(AumentoLimiteTransferenciaInEntity inEntity, String nup) {
        LOGGER.info("Se ejecuta el metodo de carga de info parcial AumentoLimiteTransferenciaBOImpl.cargarInfoParcial");
        inEntity.setCodAsociacion(88);
        inEntity.setTipoPersona(tipoPersona);
        inEntity.setNup(Integer.parseInt(nup));
        inEntity.setCodUser(codUser);
        inEntity.setCodSector(codSector);
        inEntity.setMedioIngreso(Integer.parseInt(medioIngreso));
        inEntity.setComentario(comentario);

    }

    /**
     * Crea y devuelve un campo de datos para enviar al WS.
     *
     * @param descripcion
     *            the descripcion
     * @param codigo
     *            the codigo
     * @param valor
     *            the valor
     * @return InfoRequeridaWS
     */
    private InfoRequeridaWS crearCampoRequeridoWS(String descripcion, int codigo, String valor) {
        InfoRequeridaWS info = new InfoRequeridaWS();
        info.setDescripcion(descripcion);
        info.setCodigo(codigo);
        info.setValor(new ArrayOf324931821440052060NillableString());
        info.getValor().getString().add(valor);
        return info;
    }

    /**
     * Establece el tipo de operacion.
     *
     * @param isRioRio
     *            the is rio rio
     * @param isCuentaPropia
     *            the is cuenta propia
     * @return the string
     */
    private String establecerTipoOperacion(Boolean isRioRio, Boolean isCuentaPropia) {
        String tipoOperacion = "";

        if (isRioRio && !isCuentaPropia) {
            tipoOperacion = TRF_TERCERO_MISMO_BANCO;
        }
        if (!isRioRio && !isCuentaPropia) {
            tipoOperacion = TRF_TERCERO_OTRO_BANCO;
        }
        if (isCuentaPropia) {
            tipoOperacion = TRF_CUENTA_PROPIA;
        }
        return tipoOperacion;
    }

    /**
     * Formatear importe.
     *
     * @param importe
     *            the importe
     * @return the string
     */

    /**
     * Sets the respuesta factory.
     *
     * @param respuestaFactory
     *            the new respuesta factory
     */
    public void setRespuestaFactory(RespuestaFactory respuestaFactory) {
        this.respuestaFactory = respuestaFactory;
    }

    /**
     * Sets the canal.
     *
     * @param canal
     *            the new canal
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
     * Sets the codigo canal.
     *
     * @param codigoCanal
     *            the new codigo canal
     */
    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

    /**
     * Sets the codigo subcanal.
     *
     * @param codigoSubcanal
     *            the new codigo subcanal
     */
    public void setCodigoSubcanal(String codigoSubcanal) {
        this.codigoSubcanal = codigoSubcanal;
    }

    /**
     * Sets the cuenta origen id.
     *
     * @param cuentaOrigenId
     *            the new cuenta origen id
     */
    public void setCuentaOrigenId(String cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    /**
     * Sets the cod asociacion.
     *
     * @param codAsociacion
     *            the new cod asociacion
     */
    public void setCodAsociacion(String codAsociacion) {
        this.codAsociacion = codAsociacion;
    }

    /**
     * Sets the tipo persona.
     *
     * @param tipoPersona
     *            the new tipo persona
     */
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * Sets the cod user.
     *
     * @param codUser
     *            the new cod user
     */
    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }

    /**
     * Sets the cod sector.
     *
     * @param codSector
     *            the new cod sector
     */
    public void setCodSector(String codSector) {
        this.codSector = codSector;
    }

    /**
     * Sets the medio ingreso.
     *
     * @param medioIngreso
     *            the new medio ingreso
     */
    public void setMedioIngreso(String medioIngreso) {
        this.medioIngreso = medioIngreso;
    }

    /**
     * Sets the comentario.
     *
     * @param comentario
     *            the new comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Sets the fecha operacion id.
     *
     * @param fechaOperacionId
     *            the new fecha operacion id
     */
    public void setFechaOperacionId(String fechaOperacionId) {
        this.fechaOperacionId = fechaOperacionId;
    }

    /**
     * Sets the canal id.
     *
     * @param canalId
     *            the new canal id
     */
    public void setCanalId(String canalId) {
        this.canalId = canalId;
    }

    /**
     * Sets the codigo canal id.
     *
     * @param codigoCanalId
     *            the new codigo canal id
     */
    public void setCodigoCanalId(String codigoCanalId) {
        this.codigoCanalId = codigoCanalId;
    }

    /**
     * Sets the codigo subcanal id.
     *
     * @param codigoSubcanalId
     *            the new codigo subcanal id
     */
    public void setCodigoSubcanalId(String codigoSubcanalId) {
        this.codigoSubcanalId = codigoSubcanalId;
    }

    /**
     * Sets the tipo operacion id.
     *
     * @param tipoOperacionId
     *            the new tipo operacion id
     */
    public void setTipoOperacionId(String tipoOperacionId) {
        this.tipoOperacionId = tipoOperacionId;
    }

    /**
     * Sets the importe id.
     *
     * @param importeId
     *            the new importe id
     */
    public void setImporteId(String importeId) {
        this.importeId = importeId;
    }

    /**
     * Sets the moneda id.
     *
     * @param monedaId
     *            the new moneda id
     */
    public void setMonedaId(String monedaId) {
        this.monedaId = monedaId;
    }

    /**
     * Sets the sucursal origen id.
     *
     * @param sucursalOrigenId
     *            the new sucursal origen id
     */
    public void setSucursalOrigenId(String sucursalOrigenId) {
        this.sucursalOrigenId = sucursalOrigenId;
    }

    /**
     * Sets the moneda origen id.
     *
     * @param monedaOrigenId
     *            the new moneda origen id
     */
    public void setMonedaOrigenId(String monedaOrigenId) {
        this.monedaOrigenId = monedaOrigenId;
    }

    /**
     * Sets the codigo producto id.
     *
     * @param codigoProductoId
     *            the new codigo producto id
     */
    public void setCodigoProductoId(String codigoProductoId) {
        this.codigoProductoId = codigoProductoId;
    }

    /**
     * Sets the codigo subproducto id.
     *
     * @param codigoSubproductoId
     *            the new codigo subproducto id
     */
    public void setCodigoSubproductoId(String codigoSubproductoId) {
        this.codigoSubproductoId = codigoSubproductoId;
    }

    /**
     * Sets the descripcion producto id.
     *
     * @param descripcionProductoId
     *            the new descripcion producto id
     */
    public void setDescripcionProductoId(String descripcionProductoId) {
        this.descripcionProductoId = descripcionProductoId;
    }

    /**
     * Sets the tipo destino id.
     *
     * @param tipoDestinoId
     *            the new tipo destino id
     */
    public void setTipoDestinoId(String tipoDestinoId) {
        this.tipoDestinoId = tipoDestinoId;
    }

    /**
     * Sets the cuenta destion id.
     *
     * @param cuentaDestionId
     *            the new cuenta destion id
     */
    public void setCuentaDestionId(String cuentaDestionId) {
        this.cuentaDestionId = cuentaDestionId;
    }

    /**
     * Sets the titular destino id.
     *
     * @param titularDestinoId
     *            the new titular destino id
     */
    public void setTitularDestinoId(String titularDestinoId) {
        this.titularDestinoId = titularDestinoId;
    }

}
