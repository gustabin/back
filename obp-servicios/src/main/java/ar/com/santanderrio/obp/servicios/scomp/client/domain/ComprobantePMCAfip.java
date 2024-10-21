package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobantePMCAfip  extends ComprobanteAltaBase{
 
    @XmlElement(name = "Transferencia")
    protected TransferenciaPMCAfip transferencia;

    public TransferenciaPMCAfip getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(TransferenciaPMCAfip transferencia) {
        this.transferencia = transferencia;
    }
}
