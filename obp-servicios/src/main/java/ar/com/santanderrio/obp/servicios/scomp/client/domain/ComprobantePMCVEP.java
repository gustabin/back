package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobantePMCVEP extends ComprobanteAltaBase{

    @XmlElement(name = "Transferencia")
    protected TransferenciaPMCVEP transferencia;

    public TransferenciaPMCVEP getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(TransferenciaPMCVEP transferencia) {
        this.transferencia = transferencia;
    }

    
    
}
