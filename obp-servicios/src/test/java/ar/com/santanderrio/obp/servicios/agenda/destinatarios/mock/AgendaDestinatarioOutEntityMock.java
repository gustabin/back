package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;

/**
 * The Class AgendaDestinatarioOutEntityMock.
 *
 * @author florencia.n.martinez
 */
public final class AgendaDestinatarioOutEntityMock {

    private AgendaDestinatarioOutEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtiene la entidad agenda destinatario out entity OK.
     *
     * @return the agenda destinatario out entity
     */
    public static AgendaDestinatarioOutEntity obtenerAgendaDestinatarioOutEntityOK() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("00000000");
        outEntity.setFecha("11012016");
        outEntity.setHora("12:34");
        outEntity.setNroComprobante("00000001");
        outEntity.setHeaderTrama(
                "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF34132000000000000ACTAGEDEST1000000            22222225    00        00X000000000201611011234320000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DH");
        return outEntity;
    }

    /**
     * Obtiene la agenda destinatario out entity con tipo de cuenta invalido.
     *
     * @return the agenda destinatario out entity
     */
    public static AgendaDestinatarioOutEntity obtenerAgendaDestinatarioOutEntityTipoCuentaInvalido() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("10014001");
        return outEntity;
    }

    /**
     * Obtiene la agenda destinatario out entity con destinatario agendado.
     *
     * @return the agenda destinatario out entity
     */
    public static AgendaDestinatarioOutEntity obtenerAgendaDestinatarioOutEntityDestinatarioAgendado() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("10011634");
        return outEntity;
    }

    /**
     * Obtiene la agenda destinatario out entity con cuenta invalida.
     *
     * @return the agenda destinatario out entity
     */
    public static AgendaDestinatarioOutEntity obtenerAgendaDestinatarioOutEntityCuentaInvalida() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("10010091");
        return outEntity;
    }

    /**
     * Obtiene la agenda destinatario out entity con cuenta inexistente.
     *
     * @return the agenda destinatario out entity
     */
    public static AgendaDestinatarioOutEntity obtenerAgendaDestinatarioOutEntityCuentaInexistente() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("10000054");
        return outEntity;
    }

    /**
     * Obtiene la agenda destinatario out entity con error de servicio.
     *
     * @return the agenda destinatario out entity
     */
    public static AgendaDestinatarioOutEntity obtenerAgendaDestinatarioOutEntityErrorServicio() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("-1");
        return outEntity;
    }

}
