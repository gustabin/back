package ar.com.santanderrio.obp.servicios.mya.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMensajeError;

@XmlRootElement(name = "respuesta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetStatusClienteDTOOut {

    @XmlElement(name = "CodRet")
    private String codigoRetorno;
    
    @XmlElement(name = "ClienteEstado")
    private String clienteEstado;
    
    @XmlElement(name = "IDSistema")
    private String sistemaId;
    
    @XmlElement(name = "CantDescStatusResultado")
    private String statusDescripcion;
    
    @XmlElementWrapper(name = "Destinos")
    @XmlElement(name = "Destino")
    private List<MyaDestino> listMyaDestino;
   
    @XmlElementWrapper(name = "Mensajes")
    @XmlElement(name = "Mensaje")
    private List<MyaMensajeError> listMyaMensajeError;
    

    public String getCodigoRetorno() {
		return codigoRetorno;
	}
	
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	
	public String getClienteEstado() {
		return clienteEstado;
	}
	
	public void setClienteEstado(String clienteEstado) {
		this.clienteEstado = clienteEstado;
	}
	
	public List<MyaDestino> getListMyaDestino() {
		return listMyaDestino;
	}
	
	public void setListMyaDestino(List<MyaDestino> listMyaDestino) {
		this.listMyaDestino = listMyaDestino;
	}
	
	public String getSistemaId() {
		return sistemaId;
	}
	
	public void setSistemaId(String sistemaId) {
		this.sistemaId = sistemaId;
	}
	
	public String getStatusDescripcion() {
		return statusDescripcion;
	}
	
	public void setStatusDescripcion(String statusDescripcion) {
		this.statusDescripcion = statusDescripcion;
	}
	
	public List<MyaMensajeError> getListMyaMensajeError() {
		return listMyaMensajeError;
	}
	
	public void setListMyaMensajeError(List<MyaMensajeError> listMyaMensajeError) {
		this.listMyaMensajeError = listMyaMensajeError;
	}
			
}
