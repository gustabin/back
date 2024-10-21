package ar.com.santanderrio.obp.servicios.loggedinusercache.entities;


import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class CacheEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("body")
    private String body;
    private String type;
    private String status;
    private String touched;
    private String expire;
    private String name; 
    private String prefix;
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
    /**
     * @return the touched
     */
    public String getTouched() {
        return touched;
    }
    /**
     * @param touched the touched to set
     */
    public void setTouched(String touched) {
        this.touched = touched;
    }
    /**
     * @return the expire
     */
    public String getExpire() {
        return expire;
    }
    /**
     * @param expire the expire to set
     */
    public void setExpire(String expire) {
        this.expire = expire;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }
    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }
    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
}
