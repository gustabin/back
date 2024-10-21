package ar.com.santanderrio.obp.servicios.transferencias.feature;

import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.CbuDetailsEntity;

public class AccountEntityFeature {

    public static AccountEntity getAccountEntity(){
        CbuDetailsEntity cbuDetails = new CbuDetailsEntity();
        cbuDetails.setNumber("0720000788000006388018");

        AccountEntity accountEntity= new AccountEntity();
        accountEntity.setCbuDetails(cbuDetails);

        return accountEntity;
    }
}
