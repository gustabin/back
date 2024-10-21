package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.apache.commons.lang3.StringUtils;

public enum TipoOfertaEnum {

	PREAPROBADO, PREACORDADO;

	public String toRSAString() {
		String rsa = StringUtils.capitalize(this.name().toLowerCase());
		return rsa.substring(0, 3) + "-" + rsa.substring(3, rsa.length());
	}
}
