package ar.com.santanderrio.obp.servicios.premify.contracts;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MemberResponse {

    @JsonProperty("points")
    public Integer points;
}
