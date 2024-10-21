package ar.com.santanderrio.obp.servicios.delete.account.web.dto;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.Invalidante;

import java.util.ArrayList;
import java.util.List;

public class DeleteAccountResponseDTO {

    private String newAccountNumber;
    private List<Invalidante> invalidantes;
    private TipoError tipoInvalidante;
    private EstadoRespuesta estadoRespuesta;
    private boolean containsError = false;
    private boolean containsWarning = false;
    private String mensajeInformativo;

    public String getNewAccountNumber() {
        return newAccountNumber;
    }

    public void setNewAccountNumber(String newAccountNumber) {
        this.newAccountNumber = newAccountNumber;
    }

    public List<Invalidante> getInvalidantes() {
        if(invalidantes == null) {
            invalidantes = new ArrayList<Invalidante>();
        }
        return invalidantes;
    }

    public void setInvalidantes(List<Invalidante> invalidantes) {
        this.invalidantes = invalidantes;
    }

    public TipoError getTipoInvalidante() {
        return tipoInvalidante;
    }

    public void setTipoInvalidante(TipoError tipoInvalidante) {
        this.tipoInvalidante = tipoInvalidante;
    }

    public EstadoRespuesta getEstadoRespuesta() {
        return estadoRespuesta;
    }

    public void setEstadoRespuesta(EstadoRespuesta estadoRespuesta) {
        this.estadoRespuesta = estadoRespuesta;
    }

    public boolean isContainsError() {
        return containsError;
    }

    public void setContainsError(boolean containsError) {
        this.containsError = containsError;
    }

    public boolean isContainsWarning() {
        return containsWarning;
    }

    public void setContainsWarning(boolean containsWarning) {
        this.containsWarning = containsWarning;
    }

    public String getMensajeInformativo() {
        return mensajeInformativo;
    }

    public void setMensajeInformativo(String mensajeInformativo) {
        this.mensajeInformativo = mensajeInformativo;
    }
}
