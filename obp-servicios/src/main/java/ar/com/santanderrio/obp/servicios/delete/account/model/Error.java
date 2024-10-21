package ar.com.santanderrio.obp.servicios.delete.account.model;

import ar.com.santanderrio.obp.servicios.delete.account.web.enums.ErrorCodeEnum;

public class Error {

    private String code;
    private String message;
    private ErrorCodeEnum type;

    Error() {}

    Error(String code, String message, ErrorCodeEnum type) {
        this.code = code;
        this.message = message;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(ErrorCodeEnum type) {
        this.type = type;
    }

	public ErrorCodeEnum getType() {
		return type;
	}
}
