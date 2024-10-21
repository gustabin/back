package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationRequest;

public class OperacionAvalEcheqInEntity implements IOperacionECheqInEntity<GuarantyOrderCreationRequest> {

	private final GuarantyOrderCreationRequest request;

	public OperacionAvalEcheqInEntity(GuarantyOrderCreationRequest request) {
		super();
		this.request = request;
	}

    @Override
    public String getNombreServicio() {
        return null;
    }

    @Override
    public String getVersionServicio() {
        return null;
    }

    @Override
    public GuarantyOrderCreationRequest generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> inEntity) {
        return request;
    }
}
