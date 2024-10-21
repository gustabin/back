package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobanteTransfInmRioOB extends ComprobanteAltaBase {
    @XmlElement(name = "Transferencia")
    protected TransferenciaOB transferencia;

    public TransferenciaOB getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(TransferenciaOB transferencia) {
        this.transferencia = transferencia;
    }


    
}
