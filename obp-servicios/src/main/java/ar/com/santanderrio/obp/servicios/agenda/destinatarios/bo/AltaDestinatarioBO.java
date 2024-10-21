/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * BO para alta de destinatario.
 */
public interface AltaDestinatarioBO {

    /**
     * Validacion de cuenta al continuar en la pantalla.
     *
     * @param cliente
     *            the cliente
     * @param numeroCuenta
     *            the numero cuenta
     * @param tipoCuenta
     *            the tipo cuenta
     * @return Respuesta<ConfiguracionAltaDestinatarioDTO>
     */
    Respuesta<ConfiguracionAltaDestinatarioDTO> continuarConfiguracionAltaDestinatario(Cliente cliente,
            String numeroCuenta, String tipoCuenta);

    /**
     * Confirmacion del alta de un destinatario de envío de efectivo.
     *
     * @param ip
     *            the ip
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @param cuitCuil
     *            the cuit cuil
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ComprobanteAltaDestinatarioDTO> confirmarAltaDestinatarioEnvioEfectivo(String ip, Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String cuitCuil) throws BusinessException;

    /**
     * Confirmación del alta de un destinatario a través de CBU.
     *
     * @param ip
     *            the ip
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @param referenciaTitular
     *            the referencia titular
     * @param cuitCuil
     *            the cuit cuil
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ComprobanteAltaDestinatarioDTO> confirmarAltaDestinatarioOtrosBancos(String ip, Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String referenciaTitular, String cuitCuil)
            throws BusinessException;

    /**
     * Confirmación del alta de un destinatario Río.
     *
     * @param ip
     *            the ip
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @param referenciaTitular
     *            the referencia titular
     * @param cuitCuil
     *            the cuit cuil
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ComprobanteAltaDestinatarioDTO> confirmarAltaDestinatarioRio(String ip, Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String referenciaTitular, String cuitCuil)
            throws BusinessException;

    /**
     * Confirmar alta destinatario alias.
     *
     * @param ip
     *            the ip
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @param referenciaTitular
     *            the referencia titular
     * @param cuitCuil
     *            the cuit cuil
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ComprobanteAltaDestinatarioDTO> confirmarAltaDestinatarioAlias(String ip, Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String referenciaTitular, String cuitCuil)
            throws BusinessException;

    /**
     * Cuenta ingresada es propia.
     *
     * @param cliente
     *            the cliente
     * @param numeroCuentaAlta
     *            the numero cuenta alta
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the boolean
     */
    Boolean cuentaIngresadaEsPropia(Cliente cliente, String numeroCuentaAlta, String tipoCuenta);

    /**
     * Realizar alta destinatario.
     *
     * @param ip
     *            the ip
     * @param cliente
     *            the cliente
     * @param transferenciaView
     *            the transferencia view
     * @param referenciaTitular
     *            the referencia titular
     * @param cuitCuil
     *            the cuit cuil
     * @return the string
     */
    String realizarAltaDestinatario(String ip, Cliente cliente, TransferenciaView transferenciaView,
            String referenciaTitular, String cuitCuil);

    /**
     * Existe destinatario vacio.
     *
     * @param inEntity
     *            the in entity
     * @return true, if successful
     */
    boolean existeDestinatarioVacio(AgendaDestinatarioInEntity inEntity);

}
