/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.AccountData;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountOwnershipType;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountRelationType;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class AltaTagMonederoRsaRequestBuilder.
 */
public class AltaTagMonederoRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
	 */
	@Override
	public EventData build(RsaDTO altaTag) {
		EventData ed = new EventData();
		ed.setEventType(EventType.REQUEST_NEW_CARD);
		ed.setTransactionData(crearTransactionData());
		return ed;
	}
	
    private TransactionData crearTransactionData() {
        TransactionData data = new TransactionData();
        data.setOtherAccountData(crearOtherAccountData());
        return data;
    }

    private AccountData crearOtherAccountData () {
    	AccountData otherAccountData = new AccountData();
    	otherAccountData.setAccountOwnershipType(AccountOwnershipType.BUSINESS);
    	otherAccountData.setAccountRelationType(AccountRelationType.PRIMARY_OWNER);
    	otherAccountData.setAccountType(AccountType.USER_DEFINED);
    	otherAccountData.setClientDefinedAccountType("TAG");
    	return otherAccountData;
    }
    
}
