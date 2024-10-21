package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlElement;

public class ComprobantePMCConDeuda extends ComprobanteAltaBase{

    @XmlElement(name = "Transferencia")
    protected TransferenciaPMCConDeuda transferencia;


    public TransferenciaPMCConDeuda getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(TransferenciaPMCConDeuda transferencia) {
        this.transferencia = transferencia;
    }

}
