package ar.com.santanderrio.obp.servicios.delete.account.web.enums;

import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.Invalidante;
import org.codehaus.jackson.annotate.JsonCreator;

public enum ErrorCodeEnum {
	
	OBJECTIVE_SITUATION("objective_situation", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_OBJECTIVE_SITUATION) ,
	SUBJECTIVE_SITUATION("subjective_situation", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_SUBJECTIVE_SITUATION) ,
	BALANCES_ARS("balances_ars", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_BALANCES_ARS) {
		@Override
		public Invalidante getInvalidante(ErrorLevel level, String label) {
			Invalidante invalidante = super.getInvalidante(level, label);
			invalidante.setLinkSeccion("Ir a Transferencias");
			invalidante.setGoTo("main.index.transferencias");
			invalidante.setType(Invalidante.ErrorType.ONLINE);
			return invalidante;
		}
	},
	BALANCES_USD("balances_usd", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_BALANCES_USD) {
		@Override
		public Invalidante getInvalidante(ErrorLevel level, String label) {
			Invalidante invalidante = super.getInvalidante(level, label);
			invalidante.setLinkSeccion("Ir a Transferencias");
			invalidante.setGoTo("main.index.transferencias");
			invalidante.setType(Invalidante.ErrorType.ONLINE);
			return invalidante;
		}
	},
	WITHHOLDING("withholding", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_WITHHOLDING) ,
	UNPAYMENT_ARS("unpayment_ars", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_UNPAYMENT) {
		@Override
		public Invalidante getInvalidante(ErrorLevel level, String label) {
			Invalidante invalidante = super.getInvalidante(level, label);
			invalidante.setLinkSeccion("Ir a Saldos y Movimientos");
			invalidante.setGoTo("main.index.cuentas-inicio");
			invalidante.setType(Invalidante.ErrorType.ONLINE);
			return invalidante;
		}
	},
	UNPAYMENT_USD("unpayment_usd", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_BALANCES_USD) {
		@Override
		public Invalidante getInvalidante(ErrorLevel level, String label) {
			Invalidante invalidante = super.getInvalidante(level, label);
			invalidante.setLinkSeccion("Ir a Saldos y Movimientos");
			invalidante.setGoTo("main.index.cuentas-inicio");
			invalidante.setType(Invalidante.ErrorType.ONLINE);
			return invalidante;
		}
	},
	BLOCKED("blocked", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_BLOCKED) ,
	RELATED_PRODUCTS_SAFETY_BOX("related_products_safety_box", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_RELATED_PRODUCTS_SAFETY_BOX) ,
	RELATED_PRODUCTS_ALTERNATE_ACCOUNT("related_products_alternate_account", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_RELATED_PRODUCTS_ALTERNATE_ACCOUNT) ,
	RELATED_PRODUCTS_LOAN("related_products_loan", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_RELATED_PRODUCTS_LOAN) ,
	CHECK_STATUS("check_status", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_CHECK_STATUS) ,
	FIXED_TERMS("fixed_terms", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_FIXED_TERMS) ,
	CASHPOOLING("cashpooling", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_CASHPOOLING) ,
	CUSTODY_ACCOUNT("custody_account", CodigoMensajeConstantes.BAJA_CUENTA_INVALIDANTE_CUSTODY_ACCOUNT) {
		@Override
		public Invalidante getInvalidante(ErrorLevel level, String label) {
			Invalidante invalidante = super.getInvalidante(level, label);
			invalidante.setLinkSeccion("Ir a Inversiones");
			invalidante.setGoTo("main.index.tenencia-consolidada");
			invalidante.setType(Invalidante.ErrorType.ONLINE);
			return invalidante;
		}
	},
	MISCELLANEOUS("miscellaneous", "") ,
	ACCOUNT_CLOSURE("account.closure", "") ,
	NULL_SAFE("null","") ;

	String code;
	String mensaje;

	ErrorCodeEnum(String code, String mensaje) {
		this.code = code;
		this.mensaje = mensaje;
	}

	public String getCode() {
		return code;
	}

	public String getMensaje() {
		return mensaje;
	}

	@JsonCreator
	public static ErrorCodeEnum fromCodigoString(String codigo) {
		ErrorCodeEnum[] values = ErrorCodeEnum.values();

		for (ErrorCodeEnum err : values) {
			if (err.getCode().equals(codigo)) {
				return err;
			}
		}
		return NULL_SAFE;
	}

	public Invalidante getInvalidante(ErrorLevel level, String label) {
		Invalidante invalidante = new Invalidante();
		invalidante.setLabel(label);
		invalidante.setLevel(level);
		invalidante.setErrorCode(this);
		invalidante.setType(Invalidante.ErrorType.SUCURSAL);
		return invalidante;
	};
}
