package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobantePMCSinDeuda extends ComprobanteAltaBase{

    @XmlElement(name = "Transferencia")
    protected TransferenciaPMCSinDeuda transferencia;

    public TransferenciaPMCSinDeuda getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(TransferenciaPMCSinDeuda transferencia) {
        this.transferencia = transferencia;
    }


}
