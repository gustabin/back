package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobanteTransfInmRioRio extends ComprobanteAltaBase {

    @XmlElement(name = "Transferencia")
    protected TransferenciaRio transferencia;

    public TransferenciaRio getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(TransferenciaRio transferencia) {
        this.transferencia = transferencia;
    }
}
