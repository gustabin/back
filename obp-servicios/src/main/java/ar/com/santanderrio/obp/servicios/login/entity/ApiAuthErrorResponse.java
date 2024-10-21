package ar.com.santanderrio.obp.servicios.login.entity;

public class ApiAuthErrorResponse {

    private String type;
    private String tittle;
    private String title;
    private String status;
    private String code;
    private String detail;
    private String instance;
    
    /**
     * @return the tittle
     */
    public String getTittle() {
        return tittle;
    }
    /**
     * @param tittle the tittle to set
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }
    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
    /**
     * @return the instance
     */
    public String getInstance() {
        return instance;
    }
    /**
     * @param instance the instance to set
     */
    public void setInstance(String instance) {
        this.instance = instance;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
