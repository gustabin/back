package ar.com.santanderrio.obp.servicios.login.entity;

import com.google.gson.annotations.SerializedName;

public class Metadata {
    @SerializedName("passwordType")
    private String passwordType;

    @SerializedName("userType")
    private String userType;

    @SerializedName("birthDate")
    private String birthDate;

    @SerializedName("synonym")
    private String synonym;
    
    @SerializedName("anphFlag")
    private String anphFlag;

    @SerializedName("racfPassword")
    private String racfPassword;
    
    @SerializedName("racfUser")
    private String racfUser;
    
    @SerializedName("documentNumber")
    private String documentNumber;

    @SerializedName("documentType")
    private String documentType;

    @SerializedName("cuil")
    private String cuil;
    
    @SerializedName("lastLogin")
    private String lastLogin;

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the synonym
     */
    public String getSynonym() {
        return synonym;
    }

    /**
     * @param synonym the synonym to set
     */
    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    /**
     * @return the anphFlag
     */
    public String getAnphFlag() {
        return anphFlag;
    }

    /**
     * @param anphFlag the anphFlag to set
     */
    public void setAnphFlag(String anphFlag) {
        this.anphFlag = anphFlag;
    }

    /**
     * @return the racfPassword
     */
    public String getRacfPassword() {
        return racfPassword;
    }

    /**
     * @param racfPassword the racfPassword to set
     */
    public void setRacfPassword(String racfPassword) {
        this.racfPassword = racfPassword;
    }

    /**
     * @return the racfUser
     */
    public String getRacfUser() {
        return racfUser;
    }

    /**
     * @param racfUser the racfUser to set
     */
    public void setRacfUser(String racfUser) {
        this.racfUser = racfUser;
    }

    /**
     * @return the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber the documentNumber to set
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the cuil
     */
    public String getCuil() {
        return cuil;
    }

    /**
     * @param cuil the cuil to set
     */
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    /**
     * @return the lastLogin
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    
}
