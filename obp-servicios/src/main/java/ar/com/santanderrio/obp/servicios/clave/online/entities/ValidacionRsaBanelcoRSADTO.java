package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.io.Serializable;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

public class ValidacionRsaBanelcoRSADTO extends RsaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String ipClient;

    
	public ValidacionRsaBanelcoRSADTO() {
		super(OperacionesRSAEnum.LOG_IN);
	}

	public String getIpClient() {
		return ipClient;
	}

	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}
    
}
