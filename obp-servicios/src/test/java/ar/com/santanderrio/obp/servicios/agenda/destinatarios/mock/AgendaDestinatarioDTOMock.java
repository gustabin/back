/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;

/**
 * The Class AgendaDestinatarioDTOMock.
 *
 * @author florencia.n.martinez
 */
public class AgendaDestinatarioDTOMock {

    /**
     * Obtener agenda destinatario DTO.
     *
     * @return the agenda destinatario DTO
     */
    public AgendaDestinatarioDTO obtenerAgendaDestinatarioDTO() {
        AgendaDestinatarioDTO agendaDTO = new AgendaDestinatarioDTO();
        agendaDTO.setListaDestinatarios(obtenerListaDestinatariosAgendaDTO());
        agendaDTO.setCantCuentasPropias(2);
        agendaDTO.setCantidadCuentasNoPropias(3);
        agendaDTO.setMensajeCabecera("¡Utilizá tu agenda!");
        agendaDTO.setTieneErrorCuentasNoPropias(Boolean.FALSE);
        agendaDTO.setTieneErrorCuentasPropias(Boolean.FALSE);
        return agendaDTO;
    }

    /**
     * Obtener lista destinatarios agenda DTO.
     *
     * @return the list
     */
    private List<DestinatarioAgendaDTO> obtenerListaDestinatariosAgendaDTO() {
        List<DestinatarioAgendaDTO> destinatariosDTO = new ArrayList<DestinatarioAgendaDTO>();
        destinatariosDTO
                .add(obtenerDestinatarioDTO(BancoEnum.SANTANDER_RIO.getDescripcion(), "23456789012345678901", "Fer Unica", "Lopez Fernando",
                        "123-678901/2", "Cuenta única", "202345676792", null, null, TipoAgendaEnum.AGENDA_RIO));
        destinatariosDTO.add(obtenerDestinatarioDTO(BancoEnum.SANTANDER_RIO.getDescripcion(), "12345678901234567890", null, "Lopez Fernando",
                "123-456789/1", "Caja de ahorro en $", "202345676792", null, null, TipoAgendaEnum.AGENDA_RIO));
        destinatariosDTO.add(obtenerDestinatarioDTO("Banco Patagonia", "0070000000001234567890", "AAA", "Gerardo S. A.",
                "212-123456789/0", "Cuenta corriente en $", "002000068148", null, null, TipoAgendaEnum.AGENDA_RIO));
        destinatariosDTO.add(obtenerDestinatarioDTO("Banco Francés", "00113579246802468013579", "Transporte",
                "Transporte Carga Pesada S. A.", "122-246801357/9", "Caja de ahorro en $", "002000068147", null, null,
                TipoAgendaEnum.AGENDA_RIO));
        destinatariosDTO.add(obtenerDestinatarioDTO("Banco Nación", "0080000000002345678901", null,
                "ZAutomóvil Club Argentino", "345-234567890/1", "Caja de ahorro en $", "002000068149", null, null, TipoAgendaEnum.AGENDA_RIO));

        return destinatariosDTO;
    }

    /**
     * Obtener destinatario DTO.
     *
     * @param banco
     *            the banco
     * @param cbu
     *            the cbu
     * @param referenciaApodo
     *            the referencia apodo
     * @param titular
     *            the titular
     * @param nroCuenta
     *            the nro cuenta
     * @param tipoCuenta
     *            the tipo cuenta
     * @param cuitCuil
     *            the cuit cuil
     * @param email
     *            the email
     * @param dni
     *            the dni
     * @param moneda
     *            the moneda
     * @param tipoAgenda
     *            the tipo agenda
     * @return the destinatario agenda DTO
     */
    private DestinatarioAgendaDTO obtenerDestinatarioDTO(String banco, String cbu, String referenciaApodo,
            String titular, String nroCuenta, String tipoCuenta, String cuitCuil, String email, String dni,
            TipoAgendaEnum tipoAgenda) {
        DestinatarioAgendaDTO destinatarioDTO = new DestinatarioAgendaDTO();
        destinatarioDTO.setBanco(banco);
        destinatarioDTO.setCbu(cbu);
        destinatarioDTO.setReferenciaApodo(referenciaApodo);
        destinatarioDTO.setTitular(titular);
        destinatarioDTO.setNroCuenta(nroCuenta);
        destinatarioDTO.setTipoCuenta(tipoCuenta);
        destinatarioDTO.setCuitCuil(cuitCuil);
        destinatarioDTO.setEmail(email);
        destinatarioDTO.setDocumento(dni);
        destinatarioDTO.setTipoAgendaEnum(tipoAgenda);
        destinatarioDTO.setMuestraReferenciaApodo(destinatarioDTO.getReferenciaApodo() != null);
        return destinatarioDTO;
    }
}
