package ar.com.santanderrio.obp.servicios.premify.contracts;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NotificacionResponse {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("nup")
    public Integer nup;

    @JsonProperty("message")
    public String mensaje;

    @JsonProperty("type")
    public String tipo;

    @JsonProperty("origin")
    public String origen;
}
