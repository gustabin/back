package ar.com.santanderrio.obp.servicios.delete.account.web.enums;

import org.codehaus.jackson.annotate.JsonCreator;

public enum ErrorLevel {

	ERROR("ERROR"),
	WARNING("WARNING"),
	NULL("NULL");

	private final String code;

	ErrorLevel(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@JsonCreator
	public static ErrorLevel fromCodigoString(String codigo) {
		for (ErrorLevel errorLevel : ErrorLevel.values()) {
			if (errorLevel.getCode().equals(codigo)) {
				return errorLevel;
			}
		}
		return NULL;
	}
}
