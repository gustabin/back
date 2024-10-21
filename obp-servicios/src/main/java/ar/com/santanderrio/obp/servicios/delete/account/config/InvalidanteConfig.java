package ar.com.santanderrio.obp.servicios.delete.account.config;

import ar.com.santanderrio.obp.servicios.delete.account.web.enums.ErrorCodeEnum;
import ar.com.santanderrio.obp.servicios.delete.account.web.enums.ErrorLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvalidanteConfig {

	private static final Map<ErrorLevel, List<ErrorCodeEnum>> allowedErrors;

	static {
		allowedErrors = new HashMap<ErrorLevel, List<ErrorCodeEnum>>();
		//Warning level
		List<ErrorCodeEnum> warnings = new ArrayList<ErrorCodeEnum>();
		warnings.add(ErrorCodeEnum.BALANCES_ARS);
		warnings.add(ErrorCodeEnum.BALANCES_USD);
		allowedErrors.put(ErrorLevel.WARNING, warnings);
	}

	private InvalidanteConfig() {}

	public static boolean isAllowedConstraint(ErrorLevel level, ErrorCodeEnum error) {
		return allowedErrors.containsKey(level) && allowedErrors.get(level).contains(error);
	}

	public static boolean isAllowedWarning(ErrorCodeEnum error) {
		return isAllowedConstraint(ErrorLevel.WARNING, error);
	}
	
}

