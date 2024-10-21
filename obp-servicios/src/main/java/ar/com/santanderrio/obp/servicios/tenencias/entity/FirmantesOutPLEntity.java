package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

public class FirmantesOutPLEntity extends FirmantesOutEntity {
	
	/** The custodias. */
	private List<FirmantePLEntity> custodiasParti;

	/**
	 * @return the custodiasParti
	 */
	public List<FirmantePLEntity> getCustodiasParti() {
		return custodiasParti;
	}

	/**
	 * @param custodiasParti the custodiasParti to set
	 */
	public void setCustodiasParti(List<FirmantePLEntity> custodiasParti) {
		this.custodiasParti = custodiasParti;
	}
	
	

}
