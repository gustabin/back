package ar.com.santanderrio.obp.servicios.oauth2.token;

import java.util.Date;

public abstract class OAuth2ExpirableToken {

    private Date expiration;

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Long getExpiresIn() {
        return expiration != null ? (expiration.getTime() - System.currentTimeMillis()) / 1000 : 0;
    }

    public boolean isExpired() {
        return isExpired(0);
    }

    public boolean isExpired(int skew) {
        return expiration != null && expiration.before(new Date(System.currentTimeMillis() + (skew * 1000)));
    }
}
