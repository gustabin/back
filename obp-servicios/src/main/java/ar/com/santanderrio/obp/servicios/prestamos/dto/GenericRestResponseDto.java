package ar.com.santanderrio.obp.servicios.prestamos.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GenericRestResponseDto {

	public GenericRestResponseDto() {
	}

	public GenericRestResponseDto(String code, String message, String title) {
		this();
		this.code = code;
		this.message = message;
		this.title = title;
	}

	public GenericRestResponseDto(String code, String message, String title, String tag) {
		this();
		this.code = code;
		this.message = message;
		this.title = title;
		this.tag = tag;
	}

	@JsonProperty("code")
	private String code;

	@JsonProperty("message")
	private String message;

	@JsonProperty("title")
	private String title;

	@JsonProperty("tag")
	private String tag;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}