package ar.com.santanderrio.obp.servicios.login.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ApiAuthJWT implements Serializable {
    /** The Constant serialVersionUID. */
    public static final long serialVersionUID = 1L;

    @SerializedName("metadata")
    private Metadata metadata;

    /** The exp. */
    @SerializedName("exp")
    private String exp;

    /** The iat. */
    @SerializedName("iat")
    private String iat;

    @SerializedName("nup")
    private String uniquePersonNumber;

    /** The password type. */
    @SerializedName("passwordType")
    private String passwordType;

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getUniquePersonNumber() {
        return uniquePersonNumber;
    }

    public void setUniquePersonNumber(String uniquePersonNumber) {
        this.uniquePersonNumber = uniquePersonNumber;
    }
}
