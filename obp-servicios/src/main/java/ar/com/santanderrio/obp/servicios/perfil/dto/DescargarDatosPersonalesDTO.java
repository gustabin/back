package ar.com.santanderrio.obp.servicios.perfil.dto;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

public class DescargarDatosPersonalesDTO extends RsaDTO {

    /**
     * Instantiates a new rsa DTO.
     *
     * @param tipoOperacion the tipo operacion
     */
    public DescargarDatosPersonalesDTO(OperacionesRSAEnum tipoOperacion) {
        super(tipoOperacion);
    }

    @Override
    public Boolean getIgnorarRSA() {
        return ignorarRSA;
    }

    @Override
    public void setIgnorarRSA(Boolean ignorarRSA) {
        this.ignorarRSA = ignorarRSA;
    }

    private Boolean ignorarRSA = Boolean.FALSE;

}
