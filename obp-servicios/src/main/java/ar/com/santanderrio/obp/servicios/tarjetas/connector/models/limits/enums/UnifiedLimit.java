package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums;

public enum UnifiedLimit {
	UNIFIED("S"),
	NOT_UNIFIED("N");
	public final String legacyCode;

	UnifiedLimit(String legacyCode) {
		this.legacyCode = legacyCode;
	}

	public static UnifiedLimit getByLegacyCode(String code) {
		for(UnifiedLimit e : values()) {
			if(e.legacyCode.equals(code)) return e;
		}
		return null;
	}

	public String getLegacyCode() {
		return legacyCode;
	}
}
