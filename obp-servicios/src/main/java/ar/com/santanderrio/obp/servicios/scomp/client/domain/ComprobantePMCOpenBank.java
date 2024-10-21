package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobantePMCOpenBank extends ComprobanteAltaBase {

    @XmlElement(name = "Transferencia")
    protected TransferenciaPMCOpenBank transferencia;

    public TransferenciaPMCOpenBank getTransferencia() { return transferencia; }

    public void setTransferencia(TransferenciaPMCOpenBank transferencia) {
        this.transferencia = transferencia;
    }

}