package ar.com.santanderrio.obp.servicios.api.sso.consent.entities;

import java.util.List;
import java.util.Map;

public class UserRepresentationModel {
    private String id;
    private String username;
    private Map<String, List<String>> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, List<String>> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, List<String>> attributes) {
        this.attributes = attributes;
    }
}
