/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.EcheqRSADTO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;


/**
 * The Class ECheqRsaRequestBuilder.
 */
public class ECheqRsaRequestBuilder extends AbstractRsaRequestBuilder{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
	 */

	private static final String EMISION_ECHEQ = "EMISIONECHEQ";

	private static final String ENDOSO_ECHEQ = "ENDOSOECHEQ";


	@Override
	public EventData build(RsaDTO echeqRSADTO) {
		EcheqRSADTO echeqRsaDTOInt = (EcheqRSADTO) echeqRSADTO;
		EventData edata = new EventData();
		edata.setClientDefinedAttributeList(generarClientDefinedAttributeListEcheq(echeqRsaDTOInt));
		edata.setClientDefinedEventType(echeqRsaDTOInt.getOperacionEcheqEnum().equals(OperacionEcheqEnum.ALTA) ? EMISION_ECHEQ: ENDOSO_ECHEQ);
		edata.setEventType(EventType.CLIENT_DEFINED);
		edata.setTransactionData(generarTransactionDataEcheq(echeqRsaDTOInt));
		return edata;
	}


	private FactList generarClientDefinedAttributeListEcheq(EcheqRSADTO echeqRSADTO) {
		FactList factList = new FactList();
		if (echeqRSADTO.getCantDiasUltimoCambioClave() != null) {
			ClientDefinedFact keyClave = new ClientDefinedFact();
			keyClave.setName("cantDiasUltimoCambioClave");
			keyClave.setValue(String.valueOf(echeqRSADTO.getCantDiasUltimoCambioClave()));
			keyClave.setDataType(DataType.INTEGER);
			factList.getFact().add(keyClave);
		}

		if (echeqRSADTO.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact keyToken = new ClientDefinedFact();
			keyToken.setName("cantDiasUltimoCambioToken");
			keyToken.setValue(String.valueOf(echeqRSADTO.getCantDiasUltimoCambioToken()));
			keyToken.setDataType(DataType.INTEGER);
			factList.getFact().add(keyToken);
		}

		if (echeqRSADTO.getCuitBeneficiario() != null) {
			ClientDefinedFact cuit = new ClientDefinedFact();
			cuit.setName("cuit");
			cuit.setValue(String.valueOf(echeqRSADTO.getCuitBeneficiario()));
			cuit.setDataType(DataType.STRING);
			factList.getFact().add(cuit);
		}
		if (echeqRSADTO.getCuitEmisor() != null) {
			ClientDefinedFact cuitComp = new ClientDefinedFact();
			cuitComp.setName("cuit_comprador");
			cuitComp.setValue(String.valueOf(echeqRSADTO.getCuitEmisor()));
			cuitComp.setDataType(DataType.STRING);
			factList.getFact().add(cuitComp);
		}

		if( echeqRSADTO.getOperacionEcheqEnum().equals(OperacionEcheqEnum.ENDOSAR) && echeqRSADTO.getChequeId() != null) {
				ClientDefinedFact cheque = new ClientDefinedFact();
				cheque.setName("chequeid");
				cheque.setValue(String.valueOf(echeqRSADTO.getChequeId()));
				cheque.setDataType(DataType.STRING);
				factList.getFact().add(cheque);
		}
		return factList;
	}

	private TransactionData generarTransactionDataEcheq(EcheqRSADTO echeqRSADTO) {
		TransactionData data = new TransactionData();
		data.setAmount(generarAmountEcheq(echeqRSADTO.getMonto()));
		data.setExecutionSpeed(ExecutionSpeed.REAL_TIME);
		data.setSchedule(Schedule.IMMEDIATE);
		return data;
	}

	private Amount generarAmountEcheq(Long importe) {
		Amount amount = new Amount();
		amount.setAmount(importe);
		amount.setCurrency(CURRENCY);
		return amount;
	}
}
