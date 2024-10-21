package ar.com.santanderrio.obp.servicios.transferencias.feature;

import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;

import java.util.Collections;

public class BiocatchResponseDataDTOFeature {

    public static BiocatchResponseDataDTO getBiocatchResponseDataDTOAllow() {
        return new BiocatchResponseDataDTO(1,"ALLOW",1,"",false,false, false, false, false,1,1, Collections.<String>emptyList(),Collections.<String>emptyList());
    }

    public static BiocatchResponseDataDTO getBiocatchResponseDataDTOChallenge() {
        return new BiocatchResponseDataDTO(1,"CHALLENGE",1,"",false,false, false, false, false,1,1, Collections.<String>emptyList(),Collections.<String>emptyList());
    }

    public static BiocatchResponseDataDTO getBiocatchResponseDataDTODeny() {
        return new BiocatchResponseDataDTO(1,"DENY",1,"",false,false, false, false, false,1,1, Collections.<String>emptyList(),Collections.<String>emptyList());
    }
}
