/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;

/**
 * @author florencia.n.martinez
 *
 */
public class DestinatarioAgendaDTOMock {

    public static DestinatarioAgendaDTO completarInfoRioNoCtaPropia() {
        DestinatarioAgendaDTO dto = new DestinatarioAgendaDTO();
        dto.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        dto.setCuitCuil("-");
        dto.setEsCuentaPropia(Boolean.FALSE);
        dto.setMuestraReferenciaApodo(Boolean.TRUE);
        dto.setNroCuenta("345-567890/1");
        dto.setReferenciaApodo("Club Arg");
        dto.setTipoAgendaEnum(TipoAgendaEnum.AGENDA_RIO);
        dto.setTipoCuenta("Cuenta corriente en u$s");
        dto.setTipoCuentaAbreviatura("CCD");
        dto.setTitular("Club Arg");
        return dto;
    }

    public static DestinatarioAgendaDTO completarInfoOBNoCtaPropia() {
        DestinatarioAgendaDTO dto = new DestinatarioAgendaDTO();
        dto.setBanco("Banco Francés");
        dto.setCbu("0070000000001234567890");
        dto.setCuitCuil("00-20000681-4");
        dto.setEsCuentaPropia(Boolean.FALSE);
        dto.setMuestraReferenciaApodo(Boolean.TRUE);
        dto.setReferenciaApodo("AAA");
        dto.setTipoAgendaEnum(TipoAgendaEnum.AGENDA_OTROS_BANCOS);
        dto.setTitular("Gerardo S. A.");
        return dto;
    }

    public static DestinatarioAgendaDTO completarInfoCtaPropia(String nroCuenta, String tipoCuenta, String titular) {
        DestinatarioAgendaDTO dto = new DestinatarioAgendaDTO();
        dto.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        dto.setEsCuentaPropia(Boolean.TRUE);
        dto.setMuestraReferenciaApodo(Boolean.TRUE);
        dto.setNroCuenta(nroCuenta);
        dto.setTipoAgendaEnum(TipoAgendaEnum.AGENDA_RIO);
        dto.setTipoCuenta(tipoCuenta);
        dto.setTitular(titular);
        return dto;
    }
}
