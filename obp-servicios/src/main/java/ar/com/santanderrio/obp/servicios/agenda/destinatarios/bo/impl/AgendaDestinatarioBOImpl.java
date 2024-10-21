/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AgendaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.CuentasBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;

/**
 * The Class AgendaDestinatarioBOImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class AgendaDestinatarioBOImpl extends CuentasBOImpl implements AgendaDestinatarioBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaDestinatarioBOImpl.class);

    /** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
    public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

    /** The Constant ERROR_SERVICIO. */
    public static final String ERROR_SERVICIO = "0001";

    /** The cuentas pesos. */
    private String[] cuentasPesos = { TipoCuenta.CUENTA_CORRIENTE_PESOS.getDescripcionConMoneda(),
            TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda() };

    /** The cuentas dolares. */
    private String[] cuentasDolares = { TipoCuenta.CUENTA_CORRIENTE_DOLARES.getDescripcionConMoneda(),
            TipoCuenta.CAJA_AHORRO_DOLARES.getDescripcionConMoneda() };

    /** The cuentas unicas. */
    private String[] cuentasUnicas = { TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda(),
            TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda(),
            TipoCuenta.CUENTA_UNICA_DOLARES.getDescripcionConMoneda() };

    /** The agenda destinatario DAO. */
    @Autowired
    private AgendaDestinatarioDAO agendaDestinatarioDAO;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The casuistica de la agenda de destinatarios. */
    @Autowired
    private CasuisticaAgendaDestinatarios casuisticaAgendaDestinatarios;
    
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /**
     * Obtiene la respuesta de la agenda de destinatarios DTO.
     *
     * @param cliente
     *            the cliente
     * @param fromTransferencia
     *            the from transferencia
     * @return the respuesta
     */
    @Override
    public Respuesta<AgendaDestinatarioDTO> obtenerAgendaDestinatarios(Cliente cliente, boolean fromTransferencia) {
        Errores errores = new Errores();
        AgendaDestinatarioDTO dto = new AgendaDestinatarioDTO();
        List<DestinatarioAgendaDTO> cuentasPropias = obtenerCuentasPropias(cliente);
        if (cuentasPropias.isEmpty()) {
            dto.setCantCuentasPropias(0);
            dto.setTieneCuentaPropias(Boolean.FALSE);
        } else {
            ConsultaAgendaDestinatarioOutEntity entity = realizarConsultaAgendaDestinatario(cliente, errores);
            entity = validarAliasVacio(entity);
            List<DestinatarioAgendaDTO> destinatarios = obtenerDestinatarios(errores, entity);
            ordenarAlfabeticamente(destinatarios);
            crearAgendaDestinatarioDTO(dto, cuentasPropias, destinatarios, errores, entity, fromTransferencia);
        }
        casuisticaAgendaDestinatarios.setFiltroTransferencia(fromTransferencia);
        return casuisticaAgendaDestinatarios.crearRespuesta(dto);
    }

    /**
     * Crea la agenda de destinatarios DTO.
     *
     * @param dto
     *            the dto
     * @param cuentasPropias
     *            the cuentas propias
     * @param destinatarios
     *            the destinatarios
     * @param errores
     *            the errores
     * @param entity
     *            the entity
     * @param fromTransferencia
     *            the from transferencia
     * @return the agenda destinatario DTO
     */
    private AgendaDestinatarioDTO crearAgendaDestinatarioDTO(AgendaDestinatarioDTO dto,
            List<DestinatarioAgendaDTO> cuentasPropias, List<DestinatarioAgendaDTO> destinatarios, Errores errores,
            ConsultaAgendaDestinatarioOutEntity entity, boolean fromTransferencia) {
        List<DestinatarioAgendaDTO> cuentasPropiasVerificadas = obtenerCuentasPropiasValidasComoDestinatario(
                cuentasPropias);
        dto.setCantCuentasPropias(cuentasPropiasVerificadas.size());
        dto.setTieneCuentaPropias(Boolean.TRUE);
        dto.setListaDestinatarios(unificarCuentasPropiasYDestinatariosAgendados(cuentasPropiasVerificadas,
                destinatarios, fromTransferencia));
        dto.setMensajeCabecera(obtenerMensajeCabecera());
        dto.setCantidadCuentasNoPropias(destinatarios.size());
        dto.setTieneErrorCuentasNoPropias(errores.getCodigoErrorExt() != null);
        dto.setTieneErrorTimeOut(errores.getErrorTimeOut());
        dto.setTieneErrorCuentasPropias(errores.getErrorCuentasPropias());
        dto.setTieneErrorRellamado(errores.getErrorRellamada());
        if (entity != null) {
            dto.setDestinatariosEntity(entity.getDestinatarios());
        }
        return dto;
    }

    /**
     * Obtiene las cuentas propias del usuario validas como destinatario.
     * Recorre las cuentas propias validas, cuenta la cantidad de cuentas en
     * pesos y dolares y verifica dichas cantidades para mostrar.
     *
     * @param cuentasPropias
     *            the cuentas propias
     * @return the list
     */
    private List<DestinatarioAgendaDTO> obtenerCuentasPropiasValidasComoDestinatario(
            List<DestinatarioAgendaDTO> cuentasPropias) {
        Integer contPesos = 0;
        Integer contDolares = 0;
        for (DestinatarioAgendaDTO destinatario : cuentasPropias) {
            if (ArrayUtils.contains(cuentasPesos, destinatario.getTipoCuenta())) {
                contPesos++;
            } else {
                if (ArrayUtils.contains(cuentasDolares, destinatario.getTipoCuenta())) {
                    contDolares++;
                } else {
                    contPesos++;
                    contDolares++;
                }
            }
        }
        return verificarCantidadCuentasPropias(cuentasPropias, contPesos, contDolares);
    }

    /**
     * Verifica la cantidad de cuentas propias. Si no cuenta con dos cuentas en
     * cada moneda por lo menos, se suprimen las mismas para mostrar.
     *
     * @param cuentasPropias
     *            the cuentas propias
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @return the list
     */
    private List<DestinatarioAgendaDTO> verificarCantidadCuentasPropias(List<DestinatarioAgendaDTO> cuentasPropias,
            Integer contPesos, Integer contDolares) {
        if (tieneCuentasPropiasEnAmbasMonedas(contPesos, contDolares, cuentasPropias)
                || tieneCuentasEnSolaMoneda(contPesos, contDolares)
                || tieneMixCuentas(contPesos, contDolares, cuentasPropias)
                || tieneCuentasUnicas(contPesos, contDolares, cuentasPropias)) {
            return cuentasPropias;
        }
        if (tieneCuentasPesosYUnaDolares(contPesos, contDolares, cuentasPropias)) {
            return obtenerCuentasPropiasEvaluandoCantidadPorMoneda(cuentasPropias, false);
        }
        if (tieneCuentasDolaresYUnaPesos(contPesos, contDolares, cuentasPropias)) {
            return obtenerCuentasPropiasEvaluandoCantidadPorMoneda(cuentasPropias, true);
        }
        return new ArrayList<DestinatarioAgendaDTO>();
    }

    /**
     * Tiene todas cuentas unicas. Cuenta la cantidad de cuentas unicas y
     * verifica que sean todas de este tipo.
     *
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneTodasCuentasUnicas(List<DestinatarioAgendaDTO> cuentasPropias) {
        Integer contCuentaUnicas = 0;
        for (DestinatarioAgendaDTO destinatarioDTO : cuentasPropias) {
            if (ArrayUtils.contains(cuentasUnicas, destinatarioDTO.getTipoCuenta())) {
                contCuentaUnicas++;
            }
        }
        if (cuentasPropias.size() == contCuentaUnicas && cuentasPropias.size() > 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Obtiene las cuentas propias evaluando cantidad por moneda. Excluye las
     * cuentas unicas que tienen otro tratamiento.
     *
     * @param cuentasPropias
     *            the cuentas propias
     * @param removerPesos
     *            the remover pesos
     * @return the list
     */
    private List<DestinatarioAgendaDTO> obtenerCuentasPropiasEvaluandoCantidadPorMoneda(
            List<DestinatarioAgendaDTO> cuentasPropias, Boolean removerPesos) {
        for (DestinatarioAgendaDTO destinatarioDTO : cuentasPropias) {
            if (ArrayUtils.contains(removerPesos ? cuentasPesos : cuentasDolares, destinatarioDTO.getTipoCuenta())) {
                cuentasPropias.remove(destinatarioDTO);
                break;
            }
        }
        ordenarAlfabeticamente(cuentasPropias);
        return cuentasPropias;
    }

    /**
     * Tiene cuenta unica. Verifica si entre las cuentas propias del usuario
     * existe alguna cuenta unica.
     *
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneCuentaUnica(List<DestinatarioAgendaDTO> cuentasPropias) {
        for (DestinatarioAgendaDTO destinatarioDTO : cuentasPropias) {
            if (StringUtils.equals(TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda(), destinatarioDTO.getTipoCuenta())
                    || StringUtils.equals(TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda(),
                            destinatarioDTO.getTipoCuenta())
                    || StringUtils.equals(TipoCuenta.CUENTA_UNICA_DOLARES.getDescripcionConMoneda(),
                            destinatarioDTO.getTipoCuenta())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Tiene cuentas unicas.
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneCuentasUnicas(Integer contPesos, Integer contDolares,
            List<DestinatarioAgendaDTO> cuentasPropias) {
        return tieneCuentasPesosYCuentaUnica(contPesos, contDolares, cuentasPropias)
                || tieneCuentasDolaresYCuentaUnica(contPesos, contDolares, cuentasPropias)
                || tieneTodasCuentasUnicas(cuentasPropias);
    }

    /**
     * Tiene cuentas en sola moneda.
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @return the boolean
     */
    private Boolean tieneCuentasEnSolaMoneda(Integer contPesos, Integer contDolares) {
        return tieneCuentasDolares(contDolares, contPesos) || tieneCuentasPesos(contPesos, contDolares);
    }

    /**
     * Tiene mix cuentas (Cuentas unicas y demas).
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneMixCuentas(Integer contPesos, Integer contDolares,
            List<DestinatarioAgendaDTO> cuentasPropias) {
        return contPesos > 1 && contDolares > 1 && !tieneTodasCuentasUnicas(cuentasPropias);
    }

    /**
     * Tiene cuentas dolares y cuenta unica.
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneCuentasDolaresYCuentaUnica(Integer contPesos, Integer contDolares,
            List<DestinatarioAgendaDTO> cuentasPropias) {
        return contPesos == 1 && contDolares > 1 && tieneCuentaUnica(cuentasPropias);
    }

    /**
     * Tiene cuentas dolares y una pesos.
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneCuentasDolaresYUnaPesos(Integer contPesos, Integer contDolares,
            List<DestinatarioAgendaDTO> cuentasPropias) {
        return contPesos == 1 && contDolares > 1 && !tieneCuentaUnica(cuentasPropias);
    }

    /**
     * Tiene cuentas pesos y cuenta unica.
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneCuentasPesosYCuentaUnica(Integer contPesos, Integer contDolares,
            List<DestinatarioAgendaDTO> cuentasPropias) {
        return contPesos > 1 && contDolares == 1 && tieneCuentaUnica(cuentasPropias);
    }

    /**
     * Tiene cuentas pesos y una dolares.
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneCuentasPesosYUnaDolares(Integer contPesos, Integer contDolares,
            List<DestinatarioAgendaDTO> cuentasPropias) {
        return contPesos > 1 && contDolares == 1 && !tieneCuentaUnica(cuentasPropias);
    }

    /**
     * Tiene solo cuentas en pesos.
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @return the boolean
     */
    private Boolean tieneCuentasPesos(Integer contPesos, Integer contDolares) {
        return contPesos > 1 && contDolares == 0;
    }

    /**
     * Tiene solo cuentas en dolares.
     *
     * @param contDolares
     *            the cont dolares
     * @param contPesos
     *            the cont pesos
     * @return the boolean
     */
    private Boolean tieneCuentasDolares(Integer contDolares, Integer contPesos) {
        return contPesos == 0 && contDolares > 1;
    }

    /**
     * Tiene cuentas propias en ambas monedas.
     *
     * @param contPesos
     *            the cont pesos
     * @param contDolares
     *            the cont dolares
     * @param cuentasPropias
     *            the cuentas propias
     * @return the boolean
     */
    private Boolean tieneCuentasPropiasEnAmbasMonedas(Integer contPesos, Integer contDolares,
            List<DestinatarioAgendaDTO> cuentasPropias) {
        return contPesos > 1 && contDolares > 1 && !tieneCuentaUnica(cuentasPropias);
    }

    /**
     * Obtiene las cuentas propias del cliente.
     *
     * @param cliente
     *            the cliente
     * @return the list
     */
    public List<DestinatarioAgendaDTO> obtenerCuentasPropias(Cliente cliente) {
        String email = sesionParametros.getCredencialesMya().getEmail();
        List<DestinatarioAgendaDTO> cuentasPropias = new ArrayList<DestinatarioAgendaDTO>();
        for (Cuenta cuenta : cliente.getCuentasMonetarias()) {
            DestinatarioAgendaDTO cuentaPropia = obtenerDestinatarioCuentaPropia(cuenta, cliente);
            cuentaPropia.setEmail(email);
            cuentasPropias.add(cuentaPropia);
        }
        
        for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
        	if( CuentasBancaPrivadaUtil.isCuentaPrivada(cuenta) && ("07".equals(cuenta.getProductoAltair())
                    || (cuenta.getProductoAltair().equals(CuentasBancaPrivadaUtil.PROD_ALTAIR_CTA_REPATR)
                    && cuenta.getSubproductoAltair().equals(CuentasBancaPrivadaUtil.SUBPROD_ALTAIR_CTA_REPATR)))) {
        		DestinatarioAgendaDTO cuentaPropia = obtenerDestinatarioCuentaPropia(cuenta, cliente);
                cuentaPropia.setEmail(email);
                cuentasPropias.add(cuentaPropia);
        	}
        }
        
        ordenarAlfabeticamente(cuentasPropias);
        return cuentasPropias;
    }

    /**
     * Obtiene los destinatarios.
     *
     * @param cliente
     *            the cliente
     * @param errores
     *            the errores
     * @return the list
     */
    public ConsultaAgendaDestinatarioOutEntity realizarConsultaAgendaDestinatario(Cliente cliente, Errores errores) {
        try {
            ConsultaAgendaDestinatarioInEntity inEntity = new ConsultaAgendaDestinatarioInEntity();
            inEntity.setCliente(cliente);
            
            if(!cliente.getCuentasPrivadas().isEmpty()) { //si tiene al menos una cuenta de BP se modifica el canal 
            	inEntity.setTieneCuetaBancaP(Boolean.TRUE);
            }
            return agendaDestinatarioDAO.consultar(inEntity);
        } catch (DAOException e) {
            LOGGER.error("Timeout servicio agenda: {}", e);
            errores.setErrorTimeOut(true);
        } catch (Exception e) {
            LOGGER.error("Error servicio agenda: {}", e);
            errores.setCodigoErrorExt(ERROR_SERVICIO);
        }
        return null;
    }

    /**
     * Obtiene los destinatarios.
     *
     * @param errores
     *            the errores
     * @param entity
     *            the entity
     * @return the list
     */
    public List<DestinatarioAgendaDTO> obtenerDestinatarios(Errores errores,
            ConsultaAgendaDestinatarioOutEntity entity) {
        try {
            if (entity != null) {
                int id = 1;
                for (DestinatarioEntity destinatario : entity.getDestinatarios()) {
                    destinatario.setId(String.valueOf(id));
                    id++;
                }
                errores.setErrorRellamada(entity.getErrorRellamada());
                errores.setCodigoErrorExt(
                        entity.getCodigoRetornoExtendido() != "00000000" ? entity.getCodigoRetornoExtendido() : null);
                List<DestinatarioEntity> destinatariosRio = new ArrayList<DestinatarioEntity>();
                List<DestinatarioEntity> destinatariosOtrosBancos = new ArrayList<DestinatarioEntity>();
                List<DestinatarioEntity> destinatariosEnvioEfectivo = new ArrayList<DestinatarioEntity>();
                rellenarListasDeDestinatarios(destinatariosRio, destinatariosOtrosBancos, destinatariosEnvioEfectivo,
                        entity);
                List<DestinatarioAgendaDTO> destinatariosRioDTO = obtenerListaDTO(destinatariosRio);
                List<DestinatarioAgendaDTO> destinatariosOtrosBancosDTO = obtenerListaDTO(destinatariosOtrosBancos);
                List<DestinatarioAgendaDTO> destinatariosEnvioEfectivoDTO = obtenerListaDTO(destinatariosEnvioEfectivo);
                List<DestinatarioAgendaDTO> destinatariosDTO = new ArrayList<DestinatarioAgendaDTO>();
                destinatariosDTO.addAll(destinatariosRioDTO);
                destinatariosDTO.addAll(destinatariosOtrosBancosDTO);
                destinatariosDTO.addAll(destinatariosEnvioEfectivoDTO);
                return destinatariosDTO;
            }
        } catch (Exception e) {
            LOGGER.error("Error timeout servicio agenda: {}", e);
            errores.setCodigoErrorExt(ERROR_SERVICIO);
        }
        return new ArrayList<DestinatarioAgendaDTO>();
    }

    /**
     * Obtener lista DTO.
     *
     * @param destinatariosRio
     *            the destinatarios rio
     * @return the list
     */
    private List<DestinatarioAgendaDTO> obtenerListaDTO(List<DestinatarioEntity> destinatariosRio) {
        List<DestinatarioAgendaDTO> destinatariosDTO = new ArrayList<DestinatarioAgendaDTO>();
        for (DestinatarioEntity destinatarioEntity : destinatariosRio) {
            DestinatarioAgendaDTO dto = obtenerDestinatarioDTO(destinatarioEntity);
            if (dto != null) {
                destinatariosDTO.add(dto);
            }
        }
        return ordenarAlfabeticamente(destinatariosDTO);
    }

    /**
     * Obtiene el destinatario de una cuenta propia.
     *
     * @param cuenta
     *            the cuenta
     * @param cliente
     *            the cliente
     * @return the destinatario agenda DTO
     */
    private DestinatarioAgendaDTO obtenerDestinatarioCuentaPropia(Cuenta cuenta, Cliente cliente) {
        DestinatarioAgendaDTO cuentaPropia = new DestinatarioAgendaDTO();
        cuentaPropia.setEsCuentaPropia(Boolean.TRUE);
        cuentaPropia.setReferenciaApodo(obtenerReferenciaApodo(cuenta.getAlias()));
        cuentaPropia.setTitular(obtenerTitular(cliente));
        cuentaPropia.setNroCuenta(obtenerNroCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto()));
        cuentaPropia.setTipoCuenta(obtenerTipoCuentaPropia(cuenta.getTipoCuentaEnum()));
        cuentaPropia.setTipoCuentaAbreviatura(
                TipoCuenta.fromDescripcionConMoneda(cuentaPropia.getTipoCuenta()).getAbreviatura());
        cuentaPropia.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        cuentaPropia.setEmail(null);
        cuentaPropia.setTipoAgendaEnum(TipoAgendaEnum.AGENDA_RIO);
        completarMuestraReferenciaApodo(cuentaPropia);
        cuentaPropia.setEsPesos(
                cuenta.getTipoCuentaEnum().getDescripcionConMoneda().contains(DivisaEnum.PESO.getSimbolo()));
        return cuentaPropia;
    }

    /**
     * Obtener tipo cuenta propia.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the string
     */
    private String obtenerTipoCuentaPropia(TipoCuenta tipoCuenta) {
        if (this.esCuentaUnicaPesos(tipoCuenta) || this.esCuentaUnicaDolares(tipoCuenta)) {
            return TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda();
        }
        return tipoCuenta.getDescripcionConMoneda();
    }

    /**
     * Obtiene referencia/apodo.
     *
     * @param referenciaApodo
     *            the referencia apodo
     * @return the string
     */
    private String obtenerReferenciaApodo(String referenciaApodo) {
        if (StringUtils.isNotBlank(referenciaApodo)) {
            return referenciaApodo;
        }
        return null;
    }

    /**
     * Obtiene el numero de cuenta.
     *
     * @param nroSucursal
     *            the nro sucursal
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @return the string
     */
    private String obtenerNroCuenta(String nroSucursal, String nroCuentaProducto) {
        return new IdentificacionCuenta(nroSucursal, nroCuentaProducto).toString();
    }

    /**
     * Obtiene el titular.
     *
     * @param cliente
     *            the cliente
     * @return the string
     */
    private String obtenerTitular(Cliente cliente) {
        return WordUtils.capitalizeFully(
                cliente.getApellido1().trim() + ISBANStringUtils.ESPACIO_STRING + cliente.getNombre().trim());
    }

    /**
     * Unifica las cuentas propias y los destinatarios agendados.
     *
     * @param cuentasPropias
     *            the cuentas propias
     * @param destinatarios
     *            the destinatarios
     * @param fromTransferencia
     *            the from transferencia
     * @return the list
     */
    private List<DestinatarioAgendaDTO> unificarCuentasPropiasYDestinatariosAgendados(
            List<DestinatarioAgendaDTO> cuentasPropias, List<DestinatarioAgendaDTO> destinatarios,
            boolean fromTransferencia) {

        if (fromTransferencia) {
            List<DestinatarioAgendaDTO> removidos = new ArrayList<DestinatarioAgendaDTO>();
            for (DestinatarioAgendaDTO destinatario : destinatarios) {
                for (DestinatarioAgendaDTO cuentaPropia : cuentasPropias) {
                    if (destinatario.getTipoAgendaEnum().equals(TipoAgendaEnum.AGENDA_RIO)) {
                        if (destinatario.getTipoCuenta().equals(cuentaPropia.getTipoCuenta())
                                && destinatario.getNroCuenta().equals(cuentaPropia.getNroCuenta())) {
                            removidos.add(destinatario);
                            break;
                        }
                    } else if (destinatario.getTipoAgendaEnum().equals(TipoAgendaEnum.AGENDA_OTROS_BANCOS)) {
                        if (destinatario.getCbu().equals(cuentaPropia.getCbu())) {
                            removidos.add(destinatario);
                            break;
                        }
                    }
                }
            }
            destinatarios.removeAll(removidos);
        }

        List<DestinatarioAgendaDTO> destinatariosUnificados = new ArrayList<DestinatarioAgendaDTO>();
        destinatariosUnificados.addAll(cuentasPropias);
        destinatariosUnificados.addAll(destinatarios);
        return destinatariosUnificados;
    }

    /**
     * Obtiene el mensajede la cabecera.
     *
     * @return the string
     */
    private String obtenerMensajeCabecera() {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_INICIO_AGENDA_DESTINATARIOS)
                .getMensaje();
    }

    /**
     * Ordena alfabeticamente los destinatarios.
     *
     * @param destinatarios
     *            the destinatarios
     * @return the list
     */
    @Override
    public List<DestinatarioAgendaDTO> ordenarAlfabeticamente(List<DestinatarioAgendaDTO> destinatarios) {
        Collections.sort(destinatarios, new Comparator<DestinatarioAgendaDTO>() {
            @Override
            public int compare(DestinatarioAgendaDTO destinatarioAgendaDTO1,
                    DestinatarioAgendaDTO destinatarioAgendaDTO2) {
                Integer identificador = identificarCamposAComparar(destinatarioAgendaDTO1, destinatarioAgendaDTO2);
                switch (identificador) {
                case 1:
                    return comparar(destinatarioAgendaDTO1.getReferenciaApodo(),
                            destinatarioAgendaDTO2.getReferenciaApodo());
                case 2:
                    return comparar(destinatarioAgendaDTO1.getReferenciaApodo(), destinatarioAgendaDTO2.getTitular());
                case 3:
                    return comparar(destinatarioAgendaDTO1.getTitular(), destinatarioAgendaDTO2.getReferenciaApodo());
                default:
                    return comparar(destinatarioAgendaDTO1.getTitular(), destinatarioAgendaDTO2.getTitular());
                }
            }
        });
        return destinatarios;
    }

    /**
     * Comparar.
     *
     * @param str1
     *            the str 1
     * @param str2
     *            the str 2
     * @return the int
     */
    private int comparar(String str1, String str2) {
        return str1.toLowerCase(Locale.getDefault()).compareTo(str2.toLowerCase(Locale.getDefault()));
    }

    /**
     * Identifica los campos a comparar.
     *
     * @param destinatarioAgendaDTO1
     *            the destinatario agenda DTO 1
     * @param destinatarioAgendaDTO2
     *            the destinatario agenda DTO 2
     * @return the integer
     */
    private Integer identificarCamposAComparar(DestinatarioAgendaDTO destinatarioAgendaDTO1,
            DestinatarioAgendaDTO destinatarioAgendaDTO2) {
        if (destinatarioAgendaDTO1.getMuestraReferenciaApodo() && destinatarioAgendaDTO2.getMuestraReferenciaApodo()) {
            return 1;
        } else if (destinatarioAgendaDTO1.getMuestraReferenciaApodo()
                && !destinatarioAgendaDTO2.getMuestraReferenciaApodo()) {
            return 2;
        } else if (!destinatarioAgendaDTO1.getMuestraReferenciaApodo()
                && destinatarioAgendaDTO2.getMuestraReferenciaApodo()) {
            return 3;
        }
        return 0;
    }

    /**
     * Filta los destinatarios "RIO".
     *
     * @param destinatariosRio
     *            the destinatarios rio
     * @param destinatariosOtrosBancos
     *            the destinatarios otros bancos
     * @param destinatariosEnvioEfectivo
     *            the destinatarios envio efectivo
     * @param entity
     *            the entity
     * @return the list
     */
    private void rellenarListasDeDestinatarios(List<DestinatarioEntity> destinatariosRio,
            List<DestinatarioEntity> destinatariosOtrosBancos, List<DestinatarioEntity> destinatariosEnvioEfectivo,
            ConsultaAgendaDestinatarioOutEntity entity) {
        for (DestinatarioEntity destinatarioEntity : entity.getDestinatarios()) {
            if (!destinatarioEntity.esFiltradoOBE()) {
                if (destinatarioEntity.getTipoAgendaOcurrencia().equals(TipoAgendaEnum.AGENDA_RIO.getCampo())) {
                    destinatariosRio.add(destinatarioEntity);
                } else if (destinatarioEntity.getTipoAgendaOcurrencia()
                        .equals(TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo())) {
                    destinatariosOtrosBancos.add(destinatarioEntity);
                }
                // Envio de efectivo filtrado hasta nuevo aviso
            }
        }
    }

    /**
     * Obtiene el destinatario DTO.
     *
     * @param destinatarioEntity
     *            the destinatario entity
     * @return the destinatario agenda DTO
     */
    @Override
    public DestinatarioAgendaDTO obtenerDestinatarioDTO(DestinatarioEntity destinatarioEntity) {
        if (destinatarioEntity == null || destinatarioEntity.noTieneReferenciaNiTitular()) {
            return null;
        }
        try {
            DestinatarioAgendaDTO destinatarioDTO = new DestinatarioAgendaDTO();
            destinatarioDTO.setId(destinatarioEntity.getId());
            destinatarioDTO.setEsCuentaPropia(Boolean.FALSE);
            destinatarioDTO.setTipoAgendaEnum(obtenerTipoAgenda(destinatarioEntity));
            destinatarioDTO.setEmail(obtenerEmail(destinatarioEntity));
            destinatarioDTO.setAlias(destinatarioEntity.getAlias());
            if (destinatarioDTO.getTipoAgendaEnum().equals(TipoAgendaEnum.AGENDA_EXTRACCIONES)) {
                destinatarioDTO.setReferenciaApodo(WordUtils.capitalizeFully(destinatarioEntity.getTitular().trim()));
                destinatarioDTO.setTipoDocumento(TipoDocumentoEnum
                        .obtenerTipoDocumento(destinatarioEntity.getTipoDocumentoDestinatario()).getDescripcion());
                destinatarioDTO.setDocumento(destinatarioEntity.getDocumentoDestinatario());
                destinatarioDTO.setMuestraReferenciaApodo(true);
                return destinatarioDTO;
            }
            destinatarioDTO.setTitular(WordUtils.capitalizeFully(destinatarioEntity.getTitular().trim()));
            destinatarioDTO.setReferenciaApodo(
                    obtenerReferenciaApodo(destinatarioEntity.getDescripcionCuentaDestinatario().trim()));
            completarMuestraReferenciaApodo(destinatarioDTO);
            destinatarioDTO.setBanco(obtenerBanco(destinatarioEntity, destinatarioDTO.getTipoAgendaEnum()));
            destinatarioDTO.setCuitCuil(obtenerCuitCuil(destinatarioEntity.getDocumentoDestinatario()));
            if (destinatarioDTO.getTipoAgendaEnum().equals(TipoAgendaEnum.AGENDA_RIO)) {
                destinatarioDTO.setNroCuenta(obtenerNroCuenta(
                        ISBANStringUtils.eliminarCeros(destinatarioEntity.getSucursalCuentaDestinatario()),
                        ISBANStringUtils.eliminarCeros(destinatarioEntity.getNumeroCuentaDestinatario())));
                destinatarioDTO.setTipoCuenta(
                        obtenerTipoCuenta(Integer.valueOf(destinatarioEntity.getTipoCuentaDestinatario()))
                                .getDescripcionConMoneda());
                destinatarioDTO.setTipoCuentaAbreviatura(
                        obtenerTipoCuenta(Integer.valueOf(destinatarioEntity.getTipoCuentaDestinatario()))
                                .getAbreviatura());
                destinatarioDTO.setTipoCuentaServicio(destinatarioEntity.getTipoCuentaDestinatario());
                destinatarioDTO.setSucursalServicio(destinatarioEntity.getSucursalCuentaDestinatario());
                destinatarioDTO.setNroCuentaServicio(destinatarioEntity.getNumeroCuentaDestinatario());
            } else {
                destinatarioDTO.setCbu(destinatarioEntity.getCbuDestinatario());
            }
            destinatarioDTO.setFechaCreacion(destinatarioEntity.getTimestampAlta());
            return destinatarioDTO;
        } catch (NumberFormatException nfe) {
            LOGGER.error("Error al intentar parsear un campo con formato erroneo. {}", destinatarioEntity);
        }
        return null;
    }

    /**
     * Obtiene el CUIT/CUIL del destinatario agendado.
     *
     * @param cuitCuil
     *            the cuit cuil
     * @return the string
     */
    private String obtenerCuitCuil(String cuitCuil) {
        if (StringUtils.isNotBlank(cuitCuil)) {
            return ISBANStringUtils.agregarGuionesANumeroCuitCuil(cuitCuil);
        } else {
            return ISBANStringUtils.GUION_STRING;
        }
    }

    /**
     * Obtiene el banco.
     *
     * @param entity
     *            the entity
     * @param tipoAgenda
     *            the tipo agenda
     * @return the string
     */
    private String obtenerBanco(DestinatarioEntity entity, TipoAgendaEnum tipoAgenda) {
        if (tipoAgenda.equals(TipoAgendaEnum.AGENDA_RIO)) {
            return BancoEnum.SANTANDER_RIO.getDescripcion();
        }
        return entity.getBancoDestinatario();
    }

    /**
     * Obtener email.
     *
     * @param entity
     *            the entity
     * @return the string
     */
    private String obtenerEmail(DestinatarioEntity entity) {
        String email = entity.getDireccionCorreo();
        if (StringUtils.isNotBlank(email)) {
            return StringUtils.lowerCase(email);
        }
        return null;
    }

    /**
     * Obtiene el tipo de cuenta de un destinatario agendado.
     *
     * @param tipoCuentaDestinatario
     *            the tipo cuenta destinatario
     * @return the string
     */
    private TipoCuenta obtenerTipoCuenta(Integer tipoCuentaDestinatario) {
        switch (tipoCuentaDestinatario) {
        case 0:
            return TipoCuenta.CUENTA_CORRIENTE_PESOS;
        case 1:
            return TipoCuenta.CAJA_AHORRO_PESOS;
        case 2:
            return TipoCuenta.CUENTA_UNICA;
        case 3:
            return TipoCuenta.CUENTA_CORRIENTE_DOLARES;
        default:
            return TipoCuenta.CAJA_AHORRO_DOLARES;
        }
    }

    /**
     * Obtiene el tipo de agenda.
     *
     * @param destinatarioEntity
     *            the destinatario entity
     * @return the tipo agenda enum
     */
    private TipoAgendaEnum obtenerTipoAgenda(DestinatarioEntity destinatarioEntity) {
        if (StringUtils.equals(destinatarioEntity.getTipoAgendaOcurrencia(), TipoAgendaEnum.AGENDA_RIO.getCampo())) {
            return TipoAgendaEnum.AGENDA_RIO;
        } else if (StringUtils.equals(destinatarioEntity.getTipoAgendaOcurrencia(),
                TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo())) {
            return TipoAgendaEnum.AGENDA_OTROS_BANCOS;
        }
        return TipoAgendaEnum.AGENDA_EXTRACCIONES;
    }

    /**
     * Completa el atributo "muestraReferenciaApodo" de cada destinatario.
     *
     * @param destinatarioDTO
     *            the destinatario DTO
     */
    private void completarMuestraReferenciaApodo(DestinatarioAgendaDTO destinatarioDTO) {
        if (destinatarioDTO.getReferenciaApodo() != null) {
            destinatarioDTO.setMuestraReferenciaApodo(Boolean.TRUE);
        } else {
            destinatarioDTO.setMuestraReferenciaApodo(Boolean.FALSE);
        }
    }

    /**
     * Validar alias vacio.
     *
     * @param outEntity
     *            the out entity
     * @return the consulta agenda destinatario out entity
     */
    private ConsultaAgendaDestinatarioOutEntity validarAliasVacio(ConsultaAgendaDestinatarioOutEntity outEntity) {
        if (outEntity != null) {
            for (DestinatarioEntity destinatario : outEntity.getDestinatarios()) {
                if (StringUtils.isBlank(destinatario.getAlias())) {
                    destinatario.setAlias(null);
                }
            }
        }

        return outEntity;
    }

    /**
     * The Class Errores.
     */
    class Errores {

        /** The error cuentas propias. */
        private Boolean errorCuentasPropias = false;

        /** The error time out. */
        private Boolean errorTimeOut = false;

        /** The error rellamada. */
        private Boolean errorRellamada = false;

        /** The codigo de error extendido. */
        String codigoErrorExt;

        /**
         * Gets the error cuentas propias.
         *
         * @return the error cuentas propias
         */
        public Boolean getErrorCuentasPropias() {
            return errorCuentasPropias;
        }

        /**
         * Sets the error cuentas propias.
         *
         * @param errorCuentasPropias
         *            the new error cuentas propias
         */
        public void setErrorCuentasPropias(Boolean errorCuentasPropias) {
            this.errorCuentasPropias = errorCuentasPropias;
        }

        /**
         * Gets the error time out.
         *
         * @return the error time out
         */
        public Boolean getErrorTimeOut() {
            return errorTimeOut;
        }

        /**
         * Sets the error time out.
         *
         * @param errorTimeOut
         *            the new error time out
         */
        public void setErrorTimeOut(Boolean errorTimeOut) {
            this.errorTimeOut = errorTimeOut;
        }

        /**
         * Gets the error rellamada.
         *
         * @return the error rellamada
         */
        public Boolean getErrorRellamada() {
            return errorRellamada;
        }

        /**
         * Sets the error rellamada.
         *
         * @param errorRellamada
         *            the new error rellamada
         */
        public void setErrorRellamada(Boolean errorRellamada) {
            this.errorRellamada = errorRellamada;
        }

        /**
         * Gets the codigo error ext.
         *
         * @return the codigo error ext
         */
        public String getCodigoErrorExt() {
            return codigoErrorExt;
        }

        /**
         * Sets the codigo error ext.
         *
         * @param codigoErrorExt
         *            the new codigo error ext
         */
        public void setCodigoErrorExt(String codigoErrorExt) {
            this.codigoErrorExt = codigoErrorExt;
        }

    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AgendaDestinatarioBO#obtenerAgendaDestinatarioPuntual(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO)
     */
    @Override
    public DestinatarioAgendaDTO obtenerAgendaDestinatarioPuntual(Cliente cliente,
            TransferenciaAgendadaDTO transferenciaAgendadaDTO) {

        Errores errores = new Errores();
        try {
            ConsultaAgendaDestinatarioInEntity inEntity = null;

            if (StringUtils.isBlank(transferenciaAgendadaDTO.getCbuCuenta())) {
                String tipoCuenta = StringUtils
                        .leftPad(transferenciaAgendadaDTO.getCuentaDestinoTipo().getCodigo().toString(), 2, "0");
                String numeroCuenta = StringUtils
                        .leftPad(ISBANStringUtils.extraerCuenta(transferenciaAgendadaDTO.getCuentaDestino()), 12, "0");
                String sucursal = StringUtils
                        .leftPad(ISBANStringUtils.extraerSucursal(transferenciaAgendadaDTO.getCuentaDestino()), 4, "0");
                inEntity = new ConsultaAgendaDestinatarioInEntity(tipoCuenta, sucursal, numeroCuenta);
            } else {
                inEntity = new ConsultaAgendaDestinatarioInEntity(transferenciaAgendadaDTO.getCbuCuenta());
            }
            inEntity.setCliente(cliente);
            ConsultaAgendaDestinatarioOutEntity consultaAgendaDestinatarioOutEntity = agendaDestinatarioDAO
                    .consultar(inEntity);
            List<DestinatarioAgendaDTO> agendaDestinatarios = obtenerDestinatarios(errores,
                    consultaAgendaDestinatarioOutEntity);
            return CollectionUtils.isEmpty(agendaDestinatarios) ? null : agendaDestinatarios.get(0);
        } catch (DAOException e) {
            LOGGER.error("Timeout servicio agenda: {}", e);
            errores.setErrorTimeOut(true);
        } catch (Exception e) {
            LOGGER.error("Error servicio agenda: {}", e);
            errores.setCodigoErrorExt(ERROR_SERVICIO);
        }
        return null;
    }

}