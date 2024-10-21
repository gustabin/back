package ar.com.santanderrio.obp.servicios.prestamos.contracts;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GenerateTokenRequest {

    public GenerateTokenRequest(String nup, String companiaCelular){
        this.nup = nup;
        this.companiaCelular = companiaCelular;
    }

    @JsonProperty("nup")
    public String nup;

    @JsonProperty("carrier")
    public String companiaCelular;
}