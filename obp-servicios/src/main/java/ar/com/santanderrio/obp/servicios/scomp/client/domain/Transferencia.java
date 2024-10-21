package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ TransferenciaRio.class, TransferenciaOB.class, TransferenciaPMCConDeuda.class, TransferenciaPMCSinDeuda.class, TransferenciaPMCOpenBank.class })
//@XmlRootElement(name = "Transferencia")
public abstract class Transferencia {

    
}
