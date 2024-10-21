/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.dto.MepRsaDTO;


/**
 * The Class ECheqRsaRequestBuilder.
 */
public class MepRsaRequestBuilder extends AbstractRsaRequestBuilder{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
	 */

	private static final String VENTA_MEP = "VENTA_USD_MEP";	


	@Override
	public EventData build(RsaDTO mepRsaDTO) {
		MepRsaDTO mepRsaDTOInt = (MepRsaDTO) mepRsaDTO;
		EventData edata = new EventData();
		edata.setClientDefinedAttributeList(generarClientDefinedAttributeList(mepRsaDTOInt));
		edata.setClientDefinedEventType(VENTA_MEP);
		edata.setEventType(EventType.PAYMENT);
		edata.setTransactionData(generarTransactionDataEcheq(mepRsaDTOInt));
		return edata;
	}


	private FactList generarClientDefinedAttributeList(MepRsaDTO mepRSADTO) {
		FactList factList = new FactList();
		if (mepRSADTO.getCantDiasUltimoCambioClave() != null) {
			ClientDefinedFact keyClave = new ClientDefinedFact();
			keyClave.setName("cantDiasUltimoCambioClave");
			keyClave.setValue(String.valueOf(mepRSADTO.getCantDiasUltimoCambioClave()));
			keyClave.setDataType(DataType.INTEGER);
			factList.getFact().add(keyClave);
		}

		if (mepRSADTO.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact keyToken = new ClientDefinedFact();
			keyToken.setName("cantDiasUltimoCambioToken");
			keyToken.setValue(String.valueOf(mepRSADTO.getCantDiasUltimoCambioToken()));
			keyToken.setDataType(DataType.INTEGER);
			factList.getFact().add(keyToken);
		}
		
		if (mepRSADTO.getEspecie_USDMEP() != null) {
			ClientDefinedFact keyToken = new ClientDefinedFact();
			keyToken.setName("Especie_USDMEP");
			keyToken.setValue(mepRSADTO.getEspecie_USDMEP());
			keyToken.setDataType(DataType.STRING);
			factList.getFact().add(keyToken);
		}				
		return factList;
	}

	private TransactionData generarTransactionDataEcheq(MepRsaDTO mepRSADTO) {
		TransactionData data = new TransactionData();
		Amount am = this.generarAmount(mepRSADTO.getAmount()); 
		data.setAmount(am);
		data.setExecutionSpeed(ExecutionSpeed.REAL_TIME);
		data.setSchedule(Schedule.IMMEDIATE);
		AccountData myAccountData = new AccountData();
		myAccountData.setAccountNumber(mepRSADTO.getAccount());
        data.setMyAccountData(myAccountData);
		return data;
	}

	private Amount generarAmount(float importe) {
		Amount amount = new Amount();
		amount.setAmountInUSD((long) importe);				
		return amount;
	}
}
