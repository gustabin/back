package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class DestinatarioEntityMock.
 *
 * @author florencia.n.martinez
 */
public final class DestinatarioEntityMock {

    /**
     * Instantiates a new destinatario entity mock.
     */
    private DestinatarioEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Completa la informacion del objeto destinatario entity.
     *
     * @return the destinatario entity
     */
    public static DestinatarioEntity completarInfoDestinatarioEntity() {
        DestinatarioEntity entity = new DestinatarioEntity();
        entity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_RIO.getCampo());
        entity.setTipoCuentaDestinatario("02");
        entity.setSucursalCuentaDestinatario("0027");
        entity.setNumeroCuentaDestinatario("000001234567");
        entity.setCbuDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        entity.setBancoDestinatario(
                BancoEnum.SANTANDER_RIO.getDescripcion() + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 41));
        entity.setTipoDocumentoDestinatario(TipoDocumentoEnum.CUIL.getCampo() + ISBANStringUtils.ESPACIO_STRING);
        entity.setDocumentoDestinatario("30173299881");
        entity.setDescripcionCuentaDestinatario("Contador" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        entity.setCaracteristicasCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 10));
        entity.setTitular("MUÑOZ, CÉSAR ALBERTO." + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 43));
        entity.setDireccionCorreo("cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 85));
        entity.setTelefonoDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 16));
        return entity;
    }

    /**
     * Completar info destinatario entity rio.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the destinatario entity
     */
    @SuppressWarnings("static-access")
    public static DestinatarioEntity completarInfoDestinatarioEntityRio(TipoCuenta tipoCuenta) {
        DestinatarioEntity entity = new DestinatarioEntity();
        entity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_RIO.getCampo());
        entity.setTipoCuentaDestinatario(tipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        entity.setSucursalCuentaDestinatario("0027");
        entity.setNumeroCuentaDestinatario("000001234567");
        entity.setCbuDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        entity.setBancoDestinatario(
                BancoEnum.SANTANDER_RIO.getDescripcion() + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 37));
        entity.setTipoDocumentoDestinatario(TipoDocumentoEnum.CUIL.getCampo() + ISBANStringUtils.ESPACIO_STRING);
        entity.setDocumentoDestinatario("30173299881");
        entity.setDescripcionCuentaDestinatario("Contador" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        entity.setCaracteristicasCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 10));
        entity.setTitular("MUÑOZ, CÉSAR ALBERTO." + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 43));
        entity.setDireccionCorreo("cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 85));
        entity.setTelefonoDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 16));
        return entity;
    }

    /**
     * Completar info destinatario entity.
     *
     * @param tipoAgenda
     *            the tipo agenda
     * @return the destinatario entity
     */
    public static DestinatarioEntity completarInfoDestinatarioEntity(TipoAgendaEnum tipoAgenda) {
        DestinatarioEntity entity = completarInfoDestinatarioEntity();
        entity.setTipoAgendaOcurrencia(tipoAgenda.getCampo());
        return entity;
    }
}