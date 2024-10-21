package ar.com.santanderrio.obp.servicios.loggedinusercache.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthErrorResponse;

@JsonTypeInfo(use = Id.NAME, property = "status", include = As.PROPERTY, defaultImpl = CacheErrorResponse.class)
@JsonSubTypes(value = { @JsonSubTypes.Type(value = CacheErrorResponse.class, name = "401"),
        @JsonSubTypes.Type(value = CacheErrorResponse.class, name = "500")})
public class AbstractCacheResponse {
    @JsonProperty
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
