package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.EcheqEndorsementCancellationRequest;

public class AnularEndosoECheqInEntity extends OperacionesECheqBeneficiarioInEntity
		implements IOperacionECheqInEntity<EcheqEndorsementCancellationRequest> {

	private final EcheqEndorsementCancellationRequest request;

	public AnularEndosoECheqInEntity(EcheqEndorsementCancellationRequest wsRequest) {
		this.request = wsRequest;
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
	public EcheqEndorsementCancellationRequest generateRequestData(Cliente cliente,
			IOperacionECheqInEntity<?> operacionesECheqInEntity) {
		return request;
	}
	
}
