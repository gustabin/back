package ar.com.santanderrio.obp.servicios.prestamos.contracts;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GetTokenResponse {

    @JsonProperty("token")
    public String token;
}
