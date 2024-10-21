package ar.com.santanderrio.obp.servicios.delete.account.web.view;


import ar.com.santanderrio.obp.servicios.delete.account.web.enums.ErrorCodeEnum;
import ar.com.santanderrio.obp.servicios.delete.account.web.enums.ErrorLevel;

public class Invalidante {

	private ErrorLevel level;
	private ErrorType type;
	private ErrorCodeEnum errorCode;
	private String label;
	private String linkSeccion;
	private String goTo;

	public Invalidante() {}

	public ErrorLevel getLevel() {
		return level;
	}

	public void setLevel(ErrorLevel level) {
		this.level = level;
	}

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getGoTo() {
		return goTo;
	}

	public void setGoTo(String goTo) {
		this.goTo = goTo;
	}

	public String getLinkSeccion() {
		return linkSeccion;
	}

	public void setLinkSeccion(String linkSeccion) {
		this.linkSeccion = linkSeccion;
	}

	public ErrorType getType() {
		return type;
	}

	public void setType(ErrorType type) {
		this.type = type;
	}

	public enum ErrorType {
		SUCURSAL("SUCURSAL"),
		ONLINE("ONLINE");

		private final String code;

		ErrorType(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
}
