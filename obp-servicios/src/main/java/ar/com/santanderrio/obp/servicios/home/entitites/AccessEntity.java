package ar.com.santanderrio.obp.servicios.home.entitites;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class AccessEntity {

		private String url;
		private String formId;
		private String microfrontName;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getFormId() {
			return formId;
		}
		public void setFormId(String formId) {
			this.formId = formId;
		}
		public String getMicrofrontName() {
			return microfrontName;
		}
		public void setMicrofrontName(String microfrontName) {
			this.microfrontName = microfrontName;
		}
}