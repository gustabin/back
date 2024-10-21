package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;

public class BiocatchRsaRequestBuilder {

    private BiocatchRsaRequestBuilder() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    public static FactList buildBiocatchRsaRequestBuilder(BiocatchResponseDataDTO responseDataDTO, FactList factList){
        if(responseDataDTO != null) {
            ClientDefinedFact score = new ClientDefinedFact();
            score.setName("Score_Biocatch");
            score.setDataType(DataType.INTEGER);
            score.setValue(String.valueOf(responseDataDTO.getScore()));
            factList.getFact().add(score);

            ClientDefinedFact policyAction = new ClientDefinedFact();
            policyAction.setName("Rule_Biocatch");
            policyAction.setDataType(DataType.STRING);
            policyAction.setValue(responseDataDTO.getPolicyAction());
            factList.getFact().add(policyAction);

            ClientDefinedFact policyID = new ClientDefinedFact();
            policyID.setName("Rule_ID_Biocatch");
            policyID.setDataType(DataType.INTEGER);
            policyID.setValue(String.valueOf(responseDataDTO.getPolicyID()));
            factList.getFact().add(policyID);

            ClientDefinedFact policyName = new ClientDefinedFact();
            policyName.setName("Rule_Name_Biocatch");
            policyName.setDataType(DataType.STRING);
            policyName.setValue(responseDataDTO.getPolicyName());
            factList.getFact().add(policyName);

            ClientDefinedFact isBot = new ClientDefinedFact();
            isBot.setName("IsBot_BC");
            isBot.setDataType(DataType.BOOLEAN);
            isBot.setValue(String.valueOf(responseDataDTO.isBot()));
            factList.getFact().add(isBot);

            ClientDefinedFact isEmulator = new ClientDefinedFact();
            isEmulator.setName("IsEmulator_BC");
            isEmulator.setDataType(DataType.BOOLEAN);
            isEmulator.setValue(String.valueOf(responseDataDTO.isEmulator()));
            factList.getFact().add(isEmulator);

            ClientDefinedFact isRat = new ClientDefinedFact();
            isRat.setName("IsRat_BC");
            isRat.setDataType(DataType.BOOLEAN);
            isRat.setValue(String.valueOf(responseDataDTO.isRat()));
            factList.getFact().add(isRat);

            ClientDefinedFact isRecentRat = new ClientDefinedFact();
            isRecentRat.setName("IsRecentRat_BC");
            isRecentRat.setDataType(DataType.BOOLEAN);
            isRecentRat.setValue(String.valueOf(responseDataDTO.isRecentRat()));
            factList.getFact().add(isRecentRat);

            ClientDefinedFact isMobileRat = new ClientDefinedFact();
            isMobileRat.setName("IsMobileRat_BC");
            isMobileRat.setDataType(DataType.BOOLEAN);
            isMobileRat.setValue(String.valueOf(responseDataDTO.isMobileRat()));
            factList.getFact().add(isMobileRat);

            ClientDefinedFact voiceScam = new ClientDefinedFact();
            voiceScam.setName("VoiceScam_Score_BC");
            voiceScam.setDataType(DataType.INTEGER);
            voiceScam.setValue(String.valueOf(responseDataDTO.getVoiceScam()));
            factList.getFact().add(voiceScam);

            ClientDefinedFact max30DayScore = new ClientDefinedFact();
            max30DayScore.setName("Maxscore30dias_BC");
            max30DayScore.setDataType(DataType.INTEGER);
            max30DayScore.setValue(String.valueOf(responseDataDTO.getMax30DayScore()));
            factList.getFact().add(max30DayScore);

            for (int i = 0; i < responseDataDTO.getRiskFactor().size(); i++) {
                ClientDefinedFact riskFactor = new ClientDefinedFact();
                riskFactor.setName("RiskFactor" + (i + 1) + "_BC");
                riskFactor.setDataType(DataType.STRING);
                riskFactor.setValue(responseDataDTO.getRiskFactor().get(i));
                factList.getFact().add(riskFactor);

            }

            for (int i = 0; i < responseDataDTO.getGenFactor().size(); i++) {
                ClientDefinedFact genFactor = new ClientDefinedFact();
                genFactor.setName("GenFactor" + (i + 1) + "_BC");
                genFactor.setDataType(DataType.STRING);
                genFactor.setValue(responseDataDTO.getGenFactor().get(i));
                factList.getFact().add(genFactor);

            }
        }
        return factList;
    }
}