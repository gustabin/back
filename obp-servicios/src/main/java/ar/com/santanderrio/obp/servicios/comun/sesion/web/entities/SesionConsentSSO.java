package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserRepresentationModel;
import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionConsentSSO {
	private UserRepresentationModel userInfo;
	private List<ConsentDTO> userConsents;

	public UserRepresentationModel getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserRepresentationModel userInfo) {
		this.userInfo = userInfo;
	}

	public List<ConsentDTO> getUserConsents() {
		return userConsents;
	}

	public void setUserConsents(List<ConsentDTO> userConsents) {
		this.userConsents = userConsents;
	}
}