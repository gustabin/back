package ar.com.santanderrio.obp.servicios.consent.dto;

import java.util.List;

public class UserInfoDTO {
    private String userId;
    private List<ConsentDTO> consents;

    private UserInfoDTO(Builder builder) {
        this.userId = builder.id;
        this.consents = builder.consents;
    }

    public String getUserId() {
        return userId;
    }

    public List<ConsentDTO> getConsents() {
        return consents;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private List<ConsentDTO> consents;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }
        public Builder withConsents(List<ConsentDTO> consents) {
            this.consents = consents;
            return this;
        }

        public UserInfoDTO build() {
            return new UserInfoDTO(this);
        }
    }
}
